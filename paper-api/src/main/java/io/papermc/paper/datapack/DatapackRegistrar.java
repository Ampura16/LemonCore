package io.papermc.paper.datapack;

import io.papermc.paper.plugin.configuration.PluginMeta;
import io.papermc.paper.plugin.lifecycle.event.registrar.Registrar;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Consumer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 数据包的注册器。每当游戏尝试在任何已配置的位置发现数据包时，都会调用此注册器的事件。这意味着，如果某个数据包应对服务器保持可用，那么每次触发此事件时都必须发现它。<p> 下面是插件从自身 jar 中加载数据包的示例，假设数据包位于 {@code resources/pack} 文件夹下：<pre>{@code
 * public class YourPluginBootstrap implements PluginBootstrap {
 *     @Override
 *     public void bootstrap(BootstrapContext context) {
 *         final LifecycleEventManager<BootstrapContext> manager = context.getLifecycleManager();
 *         manager.registerEventHandler(LifecycleEvents.DATAPACK_DISCOVERY, event -> {
 *             DatapackRegistrar registrar = event.registrar();
 *             try {
 *                 final URI uri = Objects.requireNonNull(
 *                     YourPluginBootstrap.class.getResource("/pack")
 *                 ).toURI();
 *                 registrar.discoverPack(uri, "packId");
 *             } catch (final URISyntaxException | IOException e) {
 *                 throw new RuntimeException(e);
 *             }
 *         });
 *     }
 * }
 * }</pre>
 * <p>原文：
 * The registrar for datapacks. The event for this registrar
 * is called anytime the game tries to discover datapacks at any of the
 * configured locations. This means that if a datapack should stay available to the server,
 * it must always be discovered whenever this event fires.
 * <p>
 * An example of a plugin loading a datapack from within its own jar is below,
 * assuming the datapack is included under {@code resources/pack} folder:
 * <pre>{@code
 * public class YourPluginBootstrap implements PluginBootstrap {
 *     @Override
 *     public void bootstrap(BootstrapContext context) {
 *         final LifecycleEventManager<BootstrapContext> manager = context.getLifecycleManager();
 *         manager.registerEventHandler(LifecycleEvents.DATAPACK_DISCOVERY, event -> {
 *             DatapackRegistrar registrar = event.registrar();
 *             try {
 *                 final URI uri = Objects.requireNonNull(
 *                     YourPluginBootstrap.class.getResource("/pack")
 *                 ).toURI();
 *                 registrar.discoverPack(uri, "packId");
 *             } catch (final URISyntaxException | IOException e) {
 *                 throw new RuntimeException(e);
 *             }
 *         });
 *     }
 * }
 * }</pre>
 *
 * @see io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents#DATAPACK_DISCOVERY
 */
@ApiStatus.NonExtendable
@ApiStatus.Experimental
@NullMarked
public interface DatapackRegistrar extends Registrar {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查是否已发现具有指定名称的数据包。
     * <p>原文：
     * Checks if a datapack with the specified name has been discovered.
     *
     * @param name 数据包的名称
     * <p>原文：the name of the pack
     * @return 如果数据包已被发现则为 true
     * <p>原文：true if the pack has been discovered
     * @see Datapack#getName()
     */
    @Contract(pure = true)
    boolean hasPackDiscovered(String name);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按名称获取已发现的数据包。
     * <p>原文：
     * Gets a discovered datapack by its name.
     *
     * @param name 数据包的名称
     * <p>原文：the name of the pack
     * @return 数据包
     * <p>原文：the datapack
     * @throws java.util.NoSuchElementException 如果数据包未被发现
     * <p>原文：if the pack is not discovered
     * @see Datapack#getName()
     */
    @Contract(pure = true)
    DiscoveredDatapack getDiscoveredPack(String name);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按名称移除已发现的数据包。
     * <p>原文：
     * Removes a discovered datapack by its name.
     *
     * @param name 数据包的名称
     * <p>原文：the name of the pack
     * @return 如果数据包已被移除则为 true
     * <p>原文：true if the pack was removed
     * @see Datapack#getName()
     */
    @Contract(mutates = "this")
    boolean removeDiscoveredPack(String name);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取所有已发现的数据包。
     * <p>原文：
     * Gets all discovered datapacks.
     *
     * @return 已发现数据包的不可修改映射
     * <p>原文：an unmodifiable map of discovered packs
     */
    @Contract(pure = true)
    @Unmodifiable Map<String, DiscoveredDatapack> getDiscoveredPacks();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在指定 {@link URI} 处以该 ID 发现数据包。
     * <p>
     * 符号链接遵循服务器根目录中的 {@code allowed_symlinks.txt}。
     * </p>
     * <p>原文：
     * Discovers a datapack at the specified {@link URI} with the id.
     * <p>Symlinks obey the {@code allowed_symlinks.txt} in the server root directory.</p>
     *
     * @param uri 数据包的位置
     * <p>原文：the location of the pack
     * @param id 唯一 ID（将与插件组合作为数据包名称）
     * <p>原文：a unique id (will be combined with plugin for the datapacks name)
     * @return 发现的数据包（失败时为 null）
     * <p>原文：the discovered datapack (or null if it failed)
     * @throws IOException 如果发生任何 IO 错误
     * <p>原文：if any IO error occurs
     */
    default @Nullable DiscoveredDatapack discoverPack(final URI uri, final String id) throws IOException {
        return this.discoverPack(uri, id, c -> {});
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在指定 {@link URI} 处以该 ID 发现数据包。
     * <p>
     * 符号链接遵循服务器根目录中的 {@code allowed_symlinks.txt}。
     * </p>
     * <p>原文：
     * Discovers a datapack at the specified {@link URI} with the id.
     * <p>Symlinks obey the {@code allowed_symlinks.txt} in the server root directory.</p>
     *
     * @param uri 数据包的位置
     * <p>原文：the location of the pack
     * @param id 唯一 ID（将与插件组合作为数据包名称）
     * <p>原文：a unique id (will be combined with plugin for the datapacks name)
     * @param configurer 额外选项的配置器
     * <p>原文：a configurer for extra options
     * @return 发现的数据包（失败时为 null）
     * <p>原文：the discovered datapack (or null if it failed)
     * @throws IOException 如果发生任何 IO 错误
     * <p>原文：if any IO error occurs
     */
    @Nullable DiscoveredDatapack discoverPack(URI uri, String id, Consumer<Configurer> configurer) throws IOException;

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在指定 {@link Path} 处以该 ID 发现数据包。
     * <p>
     * 符号链接遵循服务器根目录中的 {@code allowed_symlinks.txt}。
     * </p>
     * <p>原文：
     * Discovers a datapack at the specified {@link Path} with the id.
     * <p>Symlinks obey the {@code allowed_symlinks.txt} in the server root directory.</p>
     *
     * @param path 数据包的位置
     * <p>原文：the location of the pack
     * @param id 唯一 ID（将与插件组合作为数据包名称）
     * <p>原文：a unique id (will be combined with plugin for the datapacks name)
     * @return 发现的数据包（失败时为 null）
     * <p>原文：the discovered datapack (or null if it failed)
     * @throws IOException 如果发生任何 IO 错误
     * <p>原文：if any IO error occurs
     */
    default @Nullable DiscoveredDatapack discoverPack(final Path path, final String id) throws IOException {
        return this.discoverPack(path, id, c -> {});
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在指定 {@link Path} 处以该 ID 发现数据包。
     * <p>
     * 符号链接遵循服务器根目录中的 {@code allowed_symlinks.txt}。
     * </p>
     * <p>原文：
     * Discovers a datapack at the specified {@link Path} with the id.
     * <p>Symlinks obey the {@code allowed_symlinks.txt} in the server root directory.</p>
     *
     * @param path 数据包的位置
     * <p>原文：the location of the pack
     * @param id 唯一 ID（将与插件组合作为数据包名称）
     * <p>原文：a unique id (will be combined with plugin for the datapacks name)
     * @param configurer 额外选项的配置器
     * <p>原文：a configurer for extra options
     * @return 发现的数据包（失败时为 null）
     * <p>原文：the discovered datapack (or null if it failed)
     * @throws IOException 如果发生任何 IO 错误
     * <p>原文：if any IO error occurs
     */
    @Nullable DiscoveredDatapack discoverPack(Path path, String id, Consumer<Configurer> configurer) throws IOException;

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在指定 {@link URI} 处以该 ID 发现数据包。
     * <p>
     * 符号链接遵循服务器根目录中的 {@code allowed_symlinks.txt}。
     * </p>
     * <p>原文：
     * Discovers a datapack at the specified {@link URI} with the id.
     * <p>Symlinks obey the {@code allowed_symlinks.txt} in the server root directory.</p>
     *
     * @param pluginMeta 将作为此数据包“所有者”的插件
     * <p>原文：the plugin which will be the "owner" of this datapack
     * @param uri 数据包的位置
     * <p>原文：the location of the pack
     * @param id 唯一 ID（将与插件组合作为数据包名称）
     * <p>原文：a unique id (will be combined with plugin for the datapacks name)
     * @param configurer 额外选项的配置器
     * <p>原文：a configurer for extra options
     * @return 发现的数据包（失败时为 null）
     * <p>原文：the discovered datapack (or null if it failed)
     * @throws IOException 如果发生任何 IO 错误
     * <p>原文：if any IO error occurs
     */
    @Nullable DiscoveredDatapack discoverPack(PluginMeta pluginMeta, URI uri, String id, Consumer<Configurer> configurer) throws IOException;

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在指定 {@link Path} 处以该 ID 发现数据包。
     * <p>
     * 符号链接遵循服务器根目录中的 {@code allowed_symlinks.txt}。
     * </p>
     * <p>原文：
     * Discovers a datapack at the specified {@link Path} with the id.
     * <p>Symlinks obey the {@code allowed_symlinks.txt} in the server root directory.</p>
     *
     * @param pluginMeta 将作为此数据包“所有者”的插件
     * <p>原文：the plugin which will be the "owner" of this datapack
     * @param path 数据包的位置
     * <p>原文：the location of the pack
     * @param id 唯一 ID（将与插件组合作为数据包名称）
     * <p>原文：a unique id (will be combined with plugin for the datapacks name)
     * @param configurer 额外选项的配置器
     * <p>原文：a configurer for extra options
     * @return 发现的数据包（失败时为 null）
     * <p>原文：the discovered datapack (or null if it failed)
     * @throws IOException 如果发生任何 IO 错误
     * <p>原文：if any IO error occurs
     */
    @Nullable DiscoveredDatapack discoverPack(PluginMeta pluginMeta, Path path, String id, Consumer<Configurer> configurer) throws IOException;

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 配置数据包的附加可选详细信息。
     * <p>原文：
     * Configures additional, optional, details about a datapack.
     */
    @ApiStatus.NonExtendable
    @ApiStatus.Experimental
    interface Configurer {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 更改数据包的标题，默认标题只是 {@code registerPack} 方法中的“id”。
         * <p>原文：
         * Changes the title of the datapack from the default which
         * is just the "id" in the {@code registerPack} methods.
         *
         * @param title 新标题
         * <p>原文：the new title
         * @return 配置器，用于链式调用
         * <p>原文：the configurer for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Configurer title(Component title);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此数据包是否在服务器启动时自动启用，即使之前被禁用过。默认为 false。
         * <p>原文：
         * Sets whether this pack is going to be automatically enabled on server starts even if previously disabled.
         * Defaults to false.
         *
         * @param autoEnableOnServerStart true 表示确保该数据包在服务器启动时被启用。
         * <p>原文：true to ensure the pack is enabled on server starts.
         * @return 配置器，用于链式调用
         * <p>原文：the configurer for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Configurer autoEnableOnServerStart(boolean autoEnableOnServerStart);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置此数据包在加载顺序中的位置。
         * <p>原文：
         * Configures the position in the
         * load order of this datapack.
         *
         * @param fixed 在添加/移除数据包时不会在加载顺序中移动
         * <p>原文：won't move around in the load order as packs are added/removed
         * @param position 尝试插入到顺序的顶部还是底部
         * <p>原文：try to insert at the top of the order or bottom
         * @return 配置器，用于链式调用
         * <p>原文：the configurer for chaining
         */
        @Contract(value = "_, _ -> this", mutates = "this")
        Configurer position(boolean fixed, Datapack.Position position);
    }
}
