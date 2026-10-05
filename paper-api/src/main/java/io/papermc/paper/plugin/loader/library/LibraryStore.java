package io.papermc.paper.plugin.loader.library;

import java.nio.file.Path;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示存储库 jar 的存储。
 * <p>
 * 库存储 API 允许插件在其 {@link io.papermc.paper.plugin.loader.PluginLoader} 被处理时将特定依赖注册到其运行时类加载器中。
 * <p>原文：
 * Represents a storage that stores library jars.
 * <p>
 * The library store api allows plugins to register specific dependencies into their runtime classloader when their
 * {@link io.papermc.paper.plugin.loader.PluginLoader} is processed.
 *
 * @see io.papermc.paper.plugin.loader.PluginLoader
 */
@ApiStatus.Internal
@NullMarked
public interface LibraryStore {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将提供的库路径添加到此库存储。
     * <p>原文：
     * Adds the provided library path to this library store.
     *
     * @param library 磁盘上库 jar 文件的路径
     * <p>原文：path to the libraries jar file on the disk
     */
    void addLibrary(Path library);

}
