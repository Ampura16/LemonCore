package io.papermc.paper.plugin.loader;

import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import io.papermc.paper.plugin.loader.library.ClassPathLibrary;
import io.papermc.paper.plugin.loader.library.LibraryStore;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 可变构建器，用于收集并注册 {@link PluginLoader} 打算在运行时提供给其插件的所有 {@link ClassPathLibrary} 实例。
 * <p>原文：
 * A mutable builder that may be used to collect and register all {@link ClassPathLibrary} instances a
 * {@link PluginLoader} aims to provide to its plugin at runtime.
 */
@ApiStatus.Experimental
@NullMarked
@ApiStatus.NonExtendable
public interface PluginClasspathBuilder {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 向此类路径构建器添加一个新的类路径库。
     * <p>
     * 作为构建器，该方法不会调用 {@link ClassPathLibrary#register(LibraryStore)}，因此运行时不会触发 {@link ClassPathLibrary} 在解析期间可能执行的 IO。
     * <p>
     * Paper API 提供了预先实现的 {@link ClassPathLibrary} 类型，便于引入磁盘上或远程 Maven 仓库中的现有库。
     * <p>原文：
     * Adds a new classpath library to this classpath builder.
     * <p>
     * As a builder, this method does not invoke {@link ClassPathLibrary#register(LibraryStore)} and
     * may hence be run without invoking potential IO performed by a {@link ClassPathLibrary} during resolution.
     * <p>
     * The paper api provides pre implemented {@link ClassPathLibrary} types that allow easy inclusion of existing
     * libraries on disk or on remote maven repositories.
     *
     * @param classPathLibrary 要添加到此构建器的库实例
     * <p>原文：the library instance to add to this builder
     * @return 自身
     * <p>原文：self
     * @see io.papermc.paper.plugin.loader.library.impl.JarLibrary
     * @see io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver
     */
    @Contract("_ -> this")
    PluginClasspathBuilder addLibrary(ClassPathLibrary classPathLibrary);

    PluginProviderContext getContext();
}
