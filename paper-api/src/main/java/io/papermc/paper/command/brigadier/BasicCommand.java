package io.papermc.paper.command.brigadier;

import java.util.Collection;
import java.util.Collections;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

/**
 * Implementing this interface allows for easily creating "Bukkit-style" {@code String[] args} commands.
 * The implementation handles converting the command to a representation compatible with Brigadier on registration, usually in the form of {@literal /commandlabel <greedy_string>}.
 */
@FunctionalInterface
public interface BasicCommand {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用给定的 {@link CommandSourceStack} 和参数执行命令。
     * <p>原文：
     * Executes the command with the given {@link CommandSourceStack} and arguments.
     *
     * @param commandSourceStack 命令的 commandSourceStack
     * <p>原文：the commandSourceStack of the command
     * @param args 忽略重复空格的命令参数
     * <p>原文：the arguments of the command ignoring repeated spaces
     */
    @ApiStatus.OverrideOnly
    void execute(CommandSourceStack commandSourceStack, String[] args);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为给定的命令 {@link CommandSourceStack} 和参数建议可能的补全。
     * <p>原文：
     * Suggests possible completions for the given command {@link CommandSourceStack} and arguments.
     *
     * @param commandSourceStack 命令的 commandSourceStack
     * <p>原文：the commandSourceStack of the command
     * @param args 包含重复空格的命令参数
     * <p>原文：the arguments of the command including repeated spaces
     * @return 建议集合
     * <p>原文：a collection of suggestions
     */
    @ApiStatus.OverrideOnly
    default Collection<String> suggest(final CommandSourceStack commandSourceStack, final String[] args) {
        return Collections.emptyList();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查命令发送者能否接收并运行根命令。
     * <p>原文：
     * Checks whether a command sender can receive and run the root command.
     *
     * @param sender 尝试执行该命令的命令发送者
     * <p>原文：the command sender trying to execute the command
     * @return 命令发送者是否满足根命令的要求
     * <p>原文：whether the command sender fulfills the root command requirement
     * @see #permission()
     */
    @ApiStatus.OverrideOnly
    default boolean canUse(final CommandSender sender) {
        final String permission = this.permission();
        return permission == null || sender.hasPermission(permission);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回 {@link #canUse(CommandSender)} 中默认使用的根命令权限。
     * <p>原文：
     * Returns the permission for the root command used in {@link #canUse(CommandSender)} by default.
     *
     * @return {@link #canUse(CommandSender)} 中使用的根命令的权限
     * <p>原文：the permission for the root command used in {@link #canUse(CommandSender)}
     */
    @ApiStatus.OverrideOnly
    default @Nullable String permission() {
        return null;
    }
}
