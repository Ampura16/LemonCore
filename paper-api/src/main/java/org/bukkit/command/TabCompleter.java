package org.bukkit.command;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表可以为命令提供补全建议的类.
 * <p>原文：
 * Represents a class which can suggest tab completions for commands.
 */
public interface TabCompleter {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用命令传递的参数请求可能的补全项的list.
     * <p>原文：
     * Requests a list of possible completions for a command argument.
     *
     * @param sender 发起命令的来源. 至于玩家在命令方块内补全命令，这个来源就是玩家，而不是命令方块.
     * <p>原文：Source of the command.  For players tab-completing a
     *     command inside of a command block, this will be the player, not
     *     the command block.
     * @param command 执行的Command
     * <p>原文：Command which was executed
     * @param label 使用的别名
     * <p>原文：Alias of the command which was used
     * @param args 传递给这个命令的参数，包括用来补全的部分最终参数
     * <p>原文：The arguments passed to the command, including final
     *     partial argument to be completed
     * @return 可能的最终补全参数列表(就是list里都是已经补全了的)，或为null则传递给命令执行器
     * <p>原文：A List of possible completions for the final argument, or null
     *     to default to the command executor
     */
    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args);
}
