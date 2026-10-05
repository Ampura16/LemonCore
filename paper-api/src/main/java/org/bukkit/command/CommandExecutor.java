package org.bukkit.command;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 包含了一个执行命令的方法的类.
 * <p>原文：
 * Represents a class which contains a single method for executing commands
 */
public interface CommandExecutor {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行给定的命令，成功时返回.
     * <br>
     * 若返回假（false），则输出plugin.yml下所对应usage的内容返回至玩家聊天窗口（前提需要被定义）.
     * <br>
     * 译注：为假（false）输出一串例如：Usage: /gamemode [mode] [player]；如果为真（true）则不返回除方法内编写内容外任何内容.
     * <p>原文：
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender 命令执行的来源（可以是 player 可以是 console）
     * <p>原文：Source of the command
     * @param command 被执行的命令
     * <p>原文：Command which was executed
     * @param label 使用的命令别名
     * <p>原文：Alias of the command which was used
     * @param args 传递的命令参数（不包含命令主体）
     * <p>原文：Passed command arguments
     * @return 如果是合法的命令则为true，否则为false
     * <p>原文：true if a valid command, otherwise false
     */
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args);
}
