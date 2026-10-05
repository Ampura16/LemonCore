package com.destroystokyo.paper.exception;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当命令抛出异常时抛出
 * <p>原文：
 * Thrown when a command throws an exception
 */
public class ServerCommandException extends ServerException {

    private final Command command;
    private final CommandSender commandSender;
    private final String[] arguments;

    public ServerCommandException(String message, Throwable cause, Command command, CommandSender commandSender, String[] arguments) {
        super(message, cause);
        this.commandSender = checkNotNull(commandSender, "commandSender");
        this.arguments = checkNotNull(arguments, "arguments");
        this.command = checkNotNull(command, "command");
    }

    public ServerCommandException(Throwable cause, Command command, CommandSender commandSender, String[] arguments) {
        super(cause);
        this.commandSender = checkNotNull(commandSender, "commandSender");
        this.arguments = checkNotNull(arguments, "arguments");
        this.command = checkNotNull(command, "command");
    }

    protected ServerCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Command command, CommandSender commandSender, String[] arguments) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.commandSender = checkNotNull(commandSender, "commandSender");
        this.arguments = checkNotNull(arguments, "arguments");
        this.command = checkNotNull(command, "command");
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取抛出异常的命令
     * <p>原文：
     * Gets the command which threw the exception
     *
     * @return 抛出异常的命令
     * <p>原文：exception throwing command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取执行命令请求的命令发送者
     * <p>原文：
     * Gets the command sender which executed the command request
     *
     * @return 抛出异常的命令请求的命令发送者
     * <p>原文：command sender of exception thrown command request
     */
    public CommandSender getCommandSender() {
        return commandSender;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取导致命令抛出异常的参数
     * <p>原文：
     * Gets the arguments which threw the exception for the command
     *
     * @return 抛出异常的命令请求的参数
     * <p>原文：arguments of exception thrown command request
     */
    public String[] getArguments() {
        return arguments;
    }
}
