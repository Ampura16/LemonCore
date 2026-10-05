package io.papermc.paper.plugin.loader.library;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示加载库时发生了异常。
 * <p>原文：
 * Indicates that an exception has occurred while loading a library.
 */
public class LibraryLoadingException extends RuntimeException {

    public LibraryLoadingException(String s) {
        super(s);
    }

    public LibraryLoadingException(String s, Exception e) {
        super(s, e);
    }
}
