package org.bukkit.command;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个或多个命令别名的命令
 * <p>原文：
 * Represents a command that delegates to one or more other commands
 */
public class MultipleCommandAlias extends Command {
    private Command[] commands;

    public MultipleCommandAlias(@NotNull String name, @NotNull Command[] commands) {
        super(name);
        this.commands = commands;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与多命令别名相关的命令.
     * <p>原文：
     * Gets the commands associated with the multi-command alias.
     *
     * @return 相关的别名命令
     * <p>原文：commands associated with alias
     */
    public @NotNull Command @NotNull [] getCommands() {
        return commands;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        boolean result = false;

        for (Command command : commands) {
            result |= command.execute(sender, commandLabel, args);
        }

        return result;
    }
}
