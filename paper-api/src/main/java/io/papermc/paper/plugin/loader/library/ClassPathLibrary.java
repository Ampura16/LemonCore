package io.papermc.paper.plugin.loader.library;

import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 类路径库接口表示能够在任意给定 {@link LibraryStore} 上通过 {@link #register(LibraryStore)} 注册自身的库。
 * <p>原文：
 * The classpath library interface represents libraries that are capable of registering themselves via
 * {@link #register(LibraryStore)} on any given {@link LibraryStore}.
 */
@NullMarked
public interface ClassPathLibrary {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调用以将此类路径库所代表的库注册到传入的库存储中。插件可以在需要复杂逻辑时自行实现该方法，也可以使用 API 公开的该接口的现有实现。
     * <p>原文：
     * Called to register the library this class path library represents into the passed library store.
     * This method may either be implemented by the plugins themselves if they need complex logic, or existing
     * API exposed implementations of this interface may be used.
     *
     * @param store 要将此库注册到的库存储实例
     * <p>原文：the library store instance to register this library into
     * @throws LibraryLoadingException 如果此类路径库的加载失败
     * <p>原文：if library loading failed for this classpath library
     */
    void register(LibraryStore store) throws LibraryLoadingException;
}
