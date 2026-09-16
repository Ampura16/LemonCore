package io.papermc.paper.plugin;

import com.mojang.logging.LogUtils;
import io.papermc.paper.configuration.PaperConfigurations;
import io.papermc.paper.plugin.entrypoint.Entrypoint;
import io.papermc.paper.plugin.entrypoint.LaunchEntryPointHandler;
import io.papermc.paper.plugin.provider.PluginProvider;
import io.papermc.paper.plugin.provider.type.paper.PaperPluginParent;
import io.papermc.paper.plugin.provider.type.spigot.SpigotPluginProvider;
import io.papermc.paper.pluginremap.PluginRemapper;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import joptsimple.OptionSet;
import net.minecraft.server.dedicated.DedicatedServer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.LibraryLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PluginInitializerManager {

    private static final Logger LOGGER = LogUtils.getClassLogger();
    private static PluginInitializerManager impl;
    private final Path pluginDirectory;
    private final Path updateDirectory;
    public final io.papermc.paper.pluginremap.@org.checkerframework.checker.nullness.qual.MonotonicNonNull PluginRemapper pluginRemapper; // Paper

    PluginInitializerManager(final Path pluginDirectory, final Path updateDirectory) {
        this.pluginDirectory = pluginDirectory;
        this.updateDirectory = updateDirectory;
        this.pluginRemapper = Boolean.getBoolean("paper.disablePluginRemapping")
            ? null
            : PluginRemapper.create(pluginDirectory);
        LibraryLoader.REMAPPER = this.pluginRemapper == null ? Function.identity() : this.pluginRemapper::remapLibraries;
    }

    private static PluginInitializerManager parse(@NotNull final OptionSet minecraftOptionSet) throws Exception {
        // 我们必须加载 bukkit 配置以获取更新文件夹的位置.
        final File configFileLocationBukkit = (File) minecraftOptionSet.valueOf("bukkit-settings");

        final Path pluginDirectory = ((File) minecraftOptionSet.valueOf("plugins")).toPath();

        final YamlConfiguration configuration = PaperConfigurations.loadLegacyConfigFile(configFileLocationBukkit);

        final String updateDirectoryName = configuration.getString("settings.update-folder", "update");
        if (updateDirectoryName.isBlank()) {
            return new PluginInitializerManager(pluginDirectory, null);
        }

        final Path resolvedUpdateDirectory = pluginDirectory.resolve(updateDirectoryName);
        if (!Files.isDirectory(resolvedUpdateDirectory)) {
            if (Files.exists(resolvedUpdateDirectory)) {
                LOGGER.error("更新目录配置错误!");
                LOGGER.error("你在 bukkit.yml 中配置的更新目录（{}）指向了一个非目录路径." +
                    "自动更新功能将无法工作.", resolvedUpdateDirectory);
            }
            return new PluginInitializerManager(pluginDirectory, null);
        }

        boolean isSameFile;
        try {
            isSameFile = Files.isSameFile(resolvedUpdateDirectory, pluginDirectory);
        } catch (final IOException e) {
            LOGGER.error("更新目录配置错误!");
            LOGGER.error("比较更新目录/插件目录时失败", e);
            return new PluginInitializerManager(pluginDirectory, null);
        }

        if (isSameFile) {
            LOGGER.error("更新目录配置错误!");
            LOGGER.error(("你在 bukkit.yml 中配置的更新目录（%s）与插件目录（%s）指向了相同位置." +
                "已禁用自动更新功能.").formatted(resolvedUpdateDirectory, pluginDirectory));

            return new PluginInitializerManager(pluginDirectory, null);
        }

        return new PluginInitializerManager(pluginDirectory, resolvedUpdateDirectory);
    }

    public static PluginInitializerManager init(final OptionSet optionSet) throws Exception {
        impl = parse(optionSet);
        return impl;
    }

    public static PluginInitializerManager instance() {
        return impl;
    }

    @NotNull
    public Path pluginDirectoryPath() {
        return pluginDirectory;
    }

    @Nullable
    public Path pluginUpdatePath() {
        return updateDirectory;
    }

    public static void load(OptionSet optionSet) throws Exception {
        LOGGER.info("正在初始化插件,请稍候...");
        // 我们必须加载 bukkit 配置以获取更新文件夹的位置.
        io.papermc.paper.plugin.PluginInitializerManager pluginSystem = io.papermc.paper.plugin.PluginInitializerManager.init(optionSet);
        if (pluginSystem.pluginRemapper != null) pluginSystem.pluginRemapper.loadingPlugins();

        // 注册默认插件目录
        io.papermc.paper.plugin.util.EntrypointUtil.registerProvidersFromSource(io.papermc.paper.plugin.provider.source.DirectoryProviderSource.INSTANCE, pluginSystem.pluginDirectoryPath());

        // 从命令行参数注册插件
        @SuppressWarnings("unchecked")
        java.util.List<Path> files = ((java.util.List<File>) optionSet.valuesOf("add-plugin")).stream().map(File::toPath).toList();
        io.papermc.paper.plugin.util.EntrypointUtil.registerProvidersFromSource(io.papermc.paper.plugin.provider.source.PluginFlagProviderSource.INSTANCE, files);

        @SuppressWarnings("unchecked")
        java.util.List<Path> dirs = ((java.util.List<File>) optionSet.valuesOf("add-plugin-dir")).stream().map(File::toPath).toList();
        dirs.forEach(pluginDir -> io.papermc.paper.plugin.util.EntrypointUtil.registerProvidersFromSource(io.papermc.paper.plugin.provider.source.DirectoryProviderSource.INSTANCE_NO_CREATE, pluginDir));

        final Set<String> paperPluginNames = new TreeSet<>();
        final Set<String> legacyPluginNames = new TreeSet<>();
        LaunchEntryPointHandler.INSTANCE.getStorage().forEach((entrypoint, providerStorage) -> {
            providerStorage.getRegisteredProviders().forEach(provider -> {
                if (provider instanceof final SpigotPluginProvider legacy) {
                    legacyPluginNames.add(String.format("%s (%s)", legacy.getMeta().getName(), legacy.getMeta().getVersion()));
                } else if (provider instanceof final PaperPluginParent.PaperServerPluginProvider paper) {
                    paperPluginNames.add(String.format("%s (%s)", provider.getMeta().getName(), provider.getMeta().getVersion()));
                }
            });
        });
        final int total = paperPluginNames.size() + legacyPluginNames.size();
        LOGGER.info("已初始化 {} 个插件", total);
        if (!paperPluginNames.isEmpty()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Paper 插件（{}）:\n - {}", paperPluginNames.size(), String.join("\n - ", paperPluginNames));
            } else {
                LOGGER.info("Paper 插件（{}）:\n - {}", paperPluginNames.size(), String.join(", ", paperPluginNames));
            }
        }
        if (!legacyPluginNames.isEmpty()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Bukkit 插件（{}）:\n - {}", legacyPluginNames.size(), String.join("\n - ", legacyPluginNames));
            } else {
                LOGGER.info("Bukkit 插件（{}）:\n - {}", legacyPluginNames.size(), String.join(", ", legacyPluginNames));
            }
        }
    }

    // 这大概会是Owen1212055的末日...
    public static void reload(DedicatedServer dedicatedServer) {
        // 清空 provider 存储
        LaunchEntryPointHandler.INSTANCE.populateProviderStorage();
        try {
            load(dedicatedServer.options);
        } catch (Exception e) {
            throw new RuntimeException("重载失败!", e);
        }

        boolean hasPaperPlugin = false;
        for (PluginProvider<?> provider : LaunchEntryPointHandler.INSTANCE.getStorage().get(Entrypoint.PLUGIN).getRegisteredProviders()) {
            if (provider instanceof PaperPluginParent.PaperServerPluginProvider) {
                hasPaperPlugin = true;
                break;
            }
        }

        if (hasPaperPlugin) {
            LOGGER.warn("======== 警告 ========");
            LOGGER.warn("你正在重载服务器,但服务器上安装了 Paper 插件.");
            LOGGER.warn("Paper 插件不支持重载.这将导致一些意外问题.");
            LOGGER.warn("=========================");
        }
    }
}
