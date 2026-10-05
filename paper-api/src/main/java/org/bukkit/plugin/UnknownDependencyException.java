package org.bukkit.plugin;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当试图加载无效插件文件时抛出(插件缺少依赖的异常)
 * <p>原文：
 * Thrown when attempting to load an invalid Plugin file
 */
public class UnknownDependencyException extends RuntimeException {

    private static final long serialVersionUID = 5721389371901775895L;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据给定的异常构造一个新的 UnknownDependencyException.
     * <p>原文：
     * Constructs a new UnknownDependencyException based on the given
     * Exception
     *
     * @param throwable 引发此异常的异常
     * <p>原文：Exception that triggered this Exception
     */
    public UnknownDependencyException(final Throwable throwable) {
        super(throwable);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以给定消息构造一个新的 UnknownDependencyException.
     * <p>原文：
     * Constructs a new UnknownDependencyException with the given message
     *
     * @param message 异常原因的简要说明
     * <p>原文：Brief message explaining the cause of the exception
     */
    public UnknownDependencyException(final String message) {
        super(message);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据给定的异常构造一个新的 UnknownDependencyException.
     * <p>原文：
     * Constructs a new UnknownDependencyException based on the given
     * Exception
     *
     * @param message 异常原因的简要说明
     * <p>原文：Brief message explaining the cause of the exception
     * @param throwable 引发此异常的异常
     * <p>原文：Exception that triggered this Exception
     */
    public UnknownDependencyException(final Throwable throwable, final String message) {
        super(message, throwable);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个新的UnknownDependencyException.
     * <p>原文：
     * Constructs a new UnknownDependencyException
     */
    public UnknownDependencyException() {

    }
    // Paper start
    /**
     * Create a new {@link UnknownDependencyException} with a message informing
     * about which dependencies are missing for what plugin.
     *
     * @param missingDependencies missing dependencies
     * @param pluginName plugin which is missing said dependencies
     */
    public UnknownDependencyException(final @org.jetbrains.annotations.NotNull java.util.Collection<String> missingDependencies, final @org.jetbrains.annotations.NotNull String pluginName) {
        this("Unknown/missing dependency plugins: [" + String.join(", ", missingDependencies) + "]. Please download and install these plugins to run '" + pluginName + "'.");
    }
    // Paper end
}
