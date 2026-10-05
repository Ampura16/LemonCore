package org.bukkit.command;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当执行命令过程中出现未处理的异常时抛出.
 * <p>原文：
 * Thrown when an unhandled exception occurs during the execution of a Command
 */
@SuppressWarnings("serial")
public class CommandException extends RuntimeException {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个没有详细信息的新的<code>CommandException</code>实例.
     * <p>原文：
     * Creates a new instance of <code>CommandException</code> without detail
     * message.
     */
    public CommandException() {}

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用指定的详细信息构造一个新的<code>CommandException</code>实例.
     * <p>原文：
     * Constructs an instance of <code>CommandException</code> with the
     * specified detail message.
     *
     * @param msg 报错详细信息
     * <p>原文：the detail message.
     */
    public CommandException(String msg) {
        super(msg);
    }

    public CommandException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
