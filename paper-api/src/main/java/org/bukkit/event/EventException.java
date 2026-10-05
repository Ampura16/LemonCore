package org.bukkit.event;

public class EventException extends Exception {
    private static final long serialVersionUID = 3532808232324183999L;
    private final Throwable cause;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在给定的异常的基础上构建个新的EventException。
     * <p>原文：
     * Constructs a new EventException based on the given Exception
     *
     * @param throwable 触发这个异常的异常
     * <p>原文：Exception that triggered this Exception
     */
    public EventException(Throwable throwable) {
        cause = throwable;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个新的EventException。
     * <p>原文：
     * Constructs a new EventException
     */
    public EventException() {
        cause = null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在给定的信息上构造一个新的EventException。
     * <p>原文：
     * Constructs a new EventException with the given message
     *
     * @param cause 因为什么而触发此异常
     * <p>原文：The exception that caused this
     * @param message 信息
     * <p>原文：The message
     */
    public EventException(Throwable cause, String message) {
        super(message);
        this.cause = cause;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在给定的信息的基础上构造一个新的EventException。
     * <p>原文：
     * Constructs a new EventException with the given message
     *
     * @param message 信息
     * <p>原文：The message
     */
    public EventException(String message) {
        super(message);
        cause = null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果适用，就会返回触发这个异常的异常。
     * <p>原文：
     * If applicable, returns the Exception that triggered this Exception
     *
     * @return 内部异常, 找不到返回null
     * <p>原文：Inner exception, or {@code null} if one does not exist
     */
    @Override
    public Throwable getCause() {
        return cause;
    }
}
