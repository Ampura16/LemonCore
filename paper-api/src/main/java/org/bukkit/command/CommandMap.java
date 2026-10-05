package org.bukkit.command;

import java.util.List;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandMap {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 注册所有属于某个插件的命令.
     * <p>
     * 调用者可使用:-
     * <ul>
     * <li>用 command.getName() 确定这个别名已注册给这个命令
     * <li>用 command.getAliases() 确定这个别名在哪注册
     * </ul>
     * <p>原文：
     * Registers all the commands belonging to a certain plugin.
     * <p>
     * Caller can use:-
     * <ul>
     * <li>command.getName() to determine the label registered for this
     *     command
     * <li>command.getAliases() to determine the aliases which where
     *     registered
     * </ul>
     *
     * @param fallbackPrefix 预置在命令之前的前缀（即fallbackPrefix:命令，用英文冒号分开，前面的部分就是fallbackPrefix），以使命令独一无二
     * <p>原文：a prefix which is prepended to each command with
     *     a ':' one or more times to make the command unique
     * @param commands 要注册的命令的列表
     * <p>原文：a list of commands to register
     */
    public void registerAll(@NotNull String fallbackPrefix, @NotNull List<Command> commands);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 注册一个命令.如果成功返回true，如果名称已被占据、fallback已被使用则返回false.
     * <p>
     * 调用者可使用:-
     * <ul>
     * <li>用 command.getName() 确定这个别名已注册给这个命令
     * <li>用 command.getAliases() 确定这个别名在哪注册
     * </ul>
     * <p>原文：
     * Registers a command. Returns true on success; false if name is already
     * taken and fallback had to be used.
     * <p>
     * Caller can use:-
     * <ul>
     * <li>command.getName() to determine the label registered for this
     *     command
     * <li>command.getAliases() to determine the aliases which where
     *     registered
     * </ul>
     *
     * @param label 命令的别名，即“prefix”，没有“/”
     * <p>原文：the label of the command, without the '/'-prefix.
     * @param fallbackPrefix 预置在命令之前的前缀（即fallbackPrefix:命令，用英文冒号分开，前面的部分就是fallbackPrefix），以使命令独一无二
     * <p>原文：a prefix which is prepended to the command with a
     *     ':' one or more times to make the command unique
     * @param command 要注册的命令
     * <p>原文：the command to register
     * @return 如果用传递的label注册了命令则为true，如果指定的fallbackPrefix已被使用一次或多次则为false
     * <p>原文：true if command was registered with the passed in label, false
     *     otherwise, which indicates the fallbackPrefix was used one or more
     *     times
     */
    public boolean register(@NotNull String label, @NotNull String fallbackPrefix, @NotNull Command command);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 注册一个命令.如果成功返回true，如果名称已被占据、fallback已被使用则返回false.
     * <p>
     * 调用者可使用:-
     * <ul>
     * <li>用 command.getName() 确定这个别名已注册给这个命令
     * <li>用 command.getAliases() 确定这个别名在哪注册
     * </ul>
     * <p>原文：
     * Registers a command. Returns true on success; false if name is already
     * taken and fallback had to be used.
     * <p>
     * Caller can use:-
     * <ul>
     * <li>command.getName() to determine the label registered for this
     *     command
     * <li>command.getAliases() to determine the aliases which where
     *     registered
     * </ul>
     *
     * @param fallbackPrefix 预置在命令之前的前缀（即fallbackPrefix:命令，用英文冒号分开，前面的部分就是fallbackPrefix），以使命令独一无二
     * <p>原文：a prefix which is prepended to the command with a
     *     ':' one or more times to make the command unique
     * @param command 要注册的命令，从此命令的别名确定
     * <p>原文：the command to register, from which label is determined
     *     from the command name
     * @return 如果用传递的label注册了命令则为true，如果指定的fallbackPrefix已被使用一次或多次则为false
     * <p>原文：true if command was registered with the passed in label, false
     *     otherwise, which indicates the fallbackPrefix was used one or more
     *     times
     */
    public boolean register(@NotNull String fallbackPrefix, @NotNull Command command);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找请求的命令并执行，如果找到了。
     * <p>原文：
     * Looks for the requested command and executes it if found.
     *
     * @param sender 命令发送者
     * <p>原文：The command's sender
     * @param cmdLine 命令 + 参数. 梨子: "/test abc 123"
     * <p>原文：command + arguments. Example: "/test abc 123"
     * @return 如果没有目标找到返回false，true反之
     * <p>原文：returns false if no target is found, true otherwise.
     * @throws CommandException 当给定命令的执行器执行命令时有异常未处理原文则抛出
     * <p>原文：Thrown when the executor for the given command
     *     fails with an unhandled exception
     */
    public boolean dispatch(@NotNull CommandSender sender, @NotNull String cmdLine) throws CommandException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 清除所有已注册命令.
     * <p>原文：
     * Clears all registered commands.
     */
    public void clearCommands();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定名称的注册命令.
     * <p>原文：
     * Gets the command registered to the specified name
     *
     * @param name 要检索的命令名称
     * <p>原文：Name of the command to retrieve
     * @return 指定名称的Cimmand，如果不存在指定名称/别名的命令则返回false
     * <p>原文：Command with the specified name or null if a command with that
     *     label doesn't exist
     */
    @Nullable
    public Command getCommand(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找请求的命令并执行适当的tab补全器，如果找到了.
     * <p>
     * Looks for the requested command and executes an appropriate
     * tab-completer if found. This method will also tab-complete partial
     * commands.
     * <p>原文：
     * Looks for the requested command and executes an appropriate
     * tab-completer if found. This method will also tab-complete partial
     * commands.
     *
     * @param sender 命令发送者
     * <p>原文：The command's sender.
     * @param cmdLine 整个被tab补全的命令字符串，不包括最前的“/”
     * <p>原文：The entire command string to tab-complete, excluding
     *     initial slash.
     * @return 可能的tab补全项的列表.这个列表可能是不变的.如果命令发送者没有匹配命令的权限将会为null
     * <p>原文：a list of possible tab-completions. This list may be immutable.
     *     Will be null if no matching command of which sender has permission.
     * @throws CommandException 当给定命令的tab补全器处理时有异常未处理则抛出
     * <p>原文：Thrown when the tab-completer for the given
     *     command fails with an unhandled exception
     * @throws IllegalArgumentException 如果sender或cmdLine其一为null
     * <p>原文：if either sender or cmdLine are null
     */
    @Nullable
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找请求的命令并执行适当的tab补全器，如果找到了. 此方法也会对部分命令进行tab补全.
     * <p>原文：
     * Looks for the requested command and executes an appropriate
     * tab-completer if found. This method will also tab-complete partial
     * commands.
     *
     * @param sender 命令发送者
     * <p>原文：The command's sender.
     * @param cmdLine 整个被tab补全的命令字符串，不包括最前的"/"
     * <p>原文：The entire command string to tab-complete, excluding
     *     initial slash.
     * @param location 发送者注视的位置，如果没有则为null
     * <p>原文：The position looked at by the sender, or null if none
     * @return 可能的tab补全项的列表. 这个列表可能是不变的. 如果命令发送者没有匹配命令的权限将会为null
     * <p>原文：a list of possible tab-completions. This list may be immutable.
     *     Will be null if no matching command of which sender has permission.
     * @throws CommandException 当给定命令的tab补全器处理时有异常未处理则抛出
     * <p>原文：Thrown when the tab-completer for the given
     *     command fails with an unhandled exception
     * @throws IllegalArgumentException 如果sender或cmdLine其一为null
     * <p>原文：if either sender or cmdLine are null
     */
    @Nullable
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine, @Nullable Location location) throws IllegalArgumentException;

    // Paper start - Expose Known Commands
    /**
     * Return a Map of known commands
     *
     * @return known commands
     */
    @NotNull
    public java.util.Map<String, Command> getKnownCommands();
    // Paper end
}
