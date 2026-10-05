package org.bukkit.plugin;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当试图加载无效插件描述文件(plugin.yml)时抛出此异常.
 * <p>原文：
 * Thrown when attempting to load an invalid PluginDescriptionFile
 */
public class InvalidDescriptionException extends Exception {
    private static final long serialVersionUID = 5721389122281775896L;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据给定的异常构造一个新的 InvalidDescriptionException.
     * <p>原文：
     * Constructs a new InvalidDescriptionException based on the given
     * Exception
     *
     * @param message 异常原因的简要说明
     * <p>原文：Brief message explaining the cause of the exception
     * @param cause 引发此异常的异常
     * <p>原文：Exception that triggered this Exception
     */
    public InvalidDescriptionException(final Throwable cause, final String message) {
        super(message, cause);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据给定的异常构造一个新的 InvalidDescriptionException.
     * <p>原文：
     * Constructs a new InvalidDescriptionException based on the given
     * Exception
     *
     * @param cause 引发此异常的异常
     * <p>原文：Exception that triggered this Exception
     */
    public InvalidDescriptionException(final Throwable cause) {
        super("Invalid plugin.yml", cause);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据给定消息构造一个新的 InvalidDescriptionException.
     * <p>原文：
     * Constructs a new InvalidDescriptionException with the given message
     *
     * @param message 异常原因的简要说明
     * <p>原文：Brief message explaining the cause of the exception
     */
    public InvalidDescriptionException(final String message) {
        super(message);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个新的InvalidDescriptionException.
     * <p>原文：
     * Constructs a new InvalidDescriptionException
     */
    public InvalidDescriptionException() {
        super("Invalid plugin.yml");
    }
}
