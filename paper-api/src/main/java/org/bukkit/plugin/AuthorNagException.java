package org.bukkit.plugin;

@SuppressWarnings("serial")
public class AuthorNagException extends RuntimeException {
    private final String message;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 基于所给异常来构建一个新的AuthorNagException类型错误.
     * <p>原文：
     * Constructs a new AuthorNagException based on the given Exception
     *
     * @param message 该错误原因的简要信息
     * <p>原文：Brief message explaining the cause of the exception
     */
    public AuthorNagException(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
