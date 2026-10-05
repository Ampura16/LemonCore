package org.bukkit.command;

import com.google.common.base.Preconditions;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表属于某个插件的{@link Command 命令}.
 * <p>原文：
 * Represents a {@link Command} belonging to a plugin
 */
public final class PluginCommand extends Command implements PluginIdentifiableCommand {
    private final Plugin owningPlugin;
    private CommandExecutor executor;
    private TabCompleter completer;

    PluginCommand(@NotNull String name, @NotNull Plugin owner) {
        super(name);
        this.executor = owner;
        this.owningPlugin = owner;
        this.usageMessage = "";
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行一个命令.
     * <p>原文：
     * Executes the command, returning its success
     *
     * @param sender 谁正在执行这个命令
     * <p>原文：Source object which is executing this command
     * @param commandLabel 命令的别名
     * <p>原文：The alias of the command used
     * @param args 所有传递给命令的参数，用' '(空格)分割
     * <p>原文：All arguments passed to the command, split via ' '
     * @return 如果命令执行成功则为true，false反之
     * <p>原文：true if the command was successful, otherwise false
     */
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        boolean success = false;

        if (!owningPlugin.isEnabled()) {
            throw new CommandException("Cannot execute command '" + commandLabel + "' in plugin " + owningPlugin.getDescription().getFullName() + " - plugin is disabled.");
        }

        if (!testPermission(sender)) {
            return true;
        }

        try {
            success = executor.onCommand(sender, this, commandLabel, args);
        } catch (Throwable ex) {
            throw new CommandException("Unhandled exception executing command '" + commandLabel + "' in plugin " + owningPlugin.getDescription().getFullName(), ex);
        }

        if (!success && usageMessage.length() > 0) {
            for (String line : usageMessage.replace("<command>", commandLabel).split("\n")) {
                sender.sendMessage(line);
            }
        }

        return success;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当命令解析时运行的{@link CommandExecutor}.
     * <p>原文：
     * Sets the {@link CommandExecutor} to run when parsing this command
     *
     * @param executor 要运行的新Executor
     * <p>原文：New executor to run
     */
    public void setExecutor(@Nullable CommandExecutor executor) {
        this.executor = executor == null ? owningPlugin : executor;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与这个命令关联的{@link CommandExecutor}.
     * <p>原文：
     * Gets the {@link CommandExecutor} associated with this command
     *
     * @return 这个命令关联的{@link CommandExecutor}
     * <p>原文：CommandExecutor object linked to this command
     */
    @NotNull
    public CommandExecutor getExecutor() {
        return executor;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当(玩家)进行命令补全时执行的{@link TabCompleter}.
     * <p>
     * 如果没有指定 TabCompleter，这个命令执行器实现了TabCompleter，执行器将用来做命令补全.
     * <p>原文：
     * Sets the {@link TabCompleter} to run when tab-completing this command.
     * <p>
     * If no TabCompleter is specified, and the command's executor implements
     * TabCompleter, then the executor will be used for tab completion.
     *
     * @param completer 新的TabComplete
     * <p>原文：New tab completer
     */
    public void setTabCompleter(@Nullable TabCompleter completer) {
        this.completer = completer;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与这个命令关联的{@link TabCompleter}.
     * <p>原文：
     * Gets the {@link TabCompleter} associated with this command.
     *
     * @return 与这个命令关联的{@link TabCompleter}
     * <p>原文：TabCompleter object linked to this command
     */
    @Nullable
    public TabCompleter getTabCompleter() {
        return completer;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取拥有这个PluginCommand的插件.
     * <p>原文：
     * Gets the owner of this PluginCommand
     *
     * @return 拥有这个命令的插件
     * <p>原文：Plugin that owns this command
     */
    @Override
    @NotNull
    public Plugin getPlugin() {
        return owningPlugin;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * {@inheritDoc}
     * <p>
     * 委托的tab补全器(如果存在).
     * <p>
     * 如果它不存在或者返回null，将委托给当前命令的执行器如果它实现了{@link TabCompleter}.如果找不到非null的list，将默认在{@link Command#tabComplete(CommandSender, String, String[])}里使用玩家名字.
     * <p>
     * 这个方法不会判断权限.
     * <p>原文：
     * {@inheritDoc}
     * <p>
     * Delegates to the tab completer if present.
     * <p>
     * If it is not present or returns null, will delegate to the current
     * command executor if it implements {@link TabCompleter}. If a non-null
     * list has not been found, will default to standard player name
     * completion in {@link
     * Command#tabComplete(CommandSender, String, String[])}.
     * <p>
     * This method does not consider permissions.
     *
     * @throws CommandException 如果补全器或执行器在补全过程中抛出了异常
     * <p>原文：if the completer or executor throw an
     *     exception during the process of tab-completing.
     * @throws IllegalArgumentException 如果参数sender, alias, 或 args 是null
     * <p>原文：if sender, alias, or args is null
     */
    @NotNull
    @Override
    public java.util.List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws CommandException, IllegalArgumentException {
        Preconditions.checkArgument(sender != null, "Sender cannot be null");
        Preconditions.checkArgument(args != null, "Arguments cannot be null");
        Preconditions.checkArgument(alias != null, "Alias cannot be null");

        List<String> completions = null;
        try {
            if (completer != null) {
                completions = completer.onTabComplete(sender, this, alias, args);
            }
            if (completions == null && executor instanceof TabCompleter) {
                completions = ((TabCompleter) executor).onTabComplete(sender, this, alias, args);
            }
        } catch (Throwable ex) {
            StringBuilder message = new StringBuilder();
            message.append("Unhandled exception during tab completion for command '/").append(alias).append(' ');
            for (String arg : args) {
                message.append(arg).append(' ');
            }
            message.deleteCharAt(message.length() - 1).append("' in plugin ").append(owningPlugin.getDescription().getFullName());
            throw new CommandException(message.toString(), ex);
        }

        if (completions == null) {
            return super.tabComplete(sender, alias, args);
        }
        return completions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(", ").append(owningPlugin.getDescription().getFullName()).append(')');
        return stringBuilder.toString();
    }
}
