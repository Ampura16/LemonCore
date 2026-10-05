package com.destroystokyo.paper.exception;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 服务器抛出的所有异常的包装异常。
 * <p>原文：
 * Wrapper exception for all exceptions that are thrown by the server.
 */
public class ServerException extends Exception {

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

    protected ServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
