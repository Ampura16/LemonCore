package org.bukkit.command;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.GameRules;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.CommandMinecart;
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个命令，在用户输入时执行各种任务.
 * <p>原文：
 * Represents a Command, which executes various tasks upon user input
 */
public abstract class Command {
    private String name;
    private String nextLabel;
    private String label;
    private List<String> aliases;
    private List<String> activeAliases;
    private CommandMap commandMap;
    protected String description;
    protected String usageMessage;
    private String permission;
    private net.kyori.adventure.text.Component permissionMessage; // Paper
    /**
     * @deprecated Timings will be removed in the future
     */
    @Deprecated(forRemoval = true)
    public co.aikar.timings.Timing timings; // Paper
    /**
     * @deprecated Timings will be removed in the future
     */
    @Deprecated(forRemoval = true)
    @NotNull public String getTimingName() {return getName();} // Paper

    protected Command(@NotNull String name) {
        this(name, "", "/" + name, new ArrayList<String>());
    }

    protected Command(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        this.name = name;
        this.nextLabel = name;
        this.label = name;
        this.description = (description == null) ? "" : description;
        this.usageMessage = (usageMessage == null) ? "/" + name : usageMessage;
        this.aliases = aliases;
        this.activeAliases = new ArrayList<String>(aliases);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行命令.
     * <p>原文：
     * Executes the command, returning its success
     *
     * @param sender 执行此命令的对象
     * <p>原文：Source object which is executing this command
     * @param commandLabel 执行命令所用的别名
     * <p>原文：The alias of the command used
     * @param args 传递给此命令的所有参数，用' '分割
     * <p>原文：All arguments passed to the command, split via ' '
     * @return 如果命令执行成功则为true，false反之
     * <p>原文：true if the command was successful, otherwise false
     */
    public abstract boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 对此命令进行tab补全并返回补全项列表.
     * <p>原文：
     * Executed on tab completion for this command, returning a list of
     * options the player can tab through.
     *
     * @param sender 执行此命令的对象
     * <p>原文：Source object which is executing this command
     * @param alias 执行命令所用的别名
     * <p>原文：the alias being used
     * @param args 传递给此命令的所有参数，用' '分割
     * <p>原文：All arguments passed to the command, split via ' '
     * @return 指定参数的tab补全项列表.列表永远不会为null. 列表可能是不可变的.
     * <p>原文：a list of tab-completions for the specified arguments. This
     *     will never be null. List may be immutable.
     * @throws IllegalArgumentException 如果sender, alias, args任意一参数为null
     * <p>原文：if sender, alias, or args is null
     */
    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args) throws IllegalArgumentException {
        return tabComplete0(sender, alias, args, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 对此命令进行tab补全并返回补全项列表.
     * <p>原文：
     * Executed on tab completion for this command, returning a list of
     * options the player can tab through.
     *
     * @param sender 执行此命令的对象
     * <p>原文：Source object which is executing this command
     * @param alias 执行命令所用的别名
     * <p>原文：the alias being used
     * @param args 传递给此命令的所有参数，用' '分割
     * <p>原文：All arguments passed to the command, split via ' '
     * @param location 执行此命令对象所在的坐标，当无法获取命令执行者的坐标时该参数为null
     * <p>原文：The position looked at by the sender, or null if none
     * @return 指定参数的tab补全项列表.列表永远不会为null. 列表可能是不可变的.
     * <p>原文：a list of tab-completions for the specified arguments. This
     *     will never be null. List may be immutable.
     * @throws IllegalArgumentException 如果参数sender, alias或args为null
     * <p>原文：if sender, alias, or args is null
     */
    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args, @Nullable Location location) throws IllegalArgumentException {
        return tabComplete(sender, alias, args);
    }

    @NotNull
    private List<String> tabComplete0(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args, @Nullable Location location) throws IllegalArgumentException {
        Preconditions.checkArgument(sender != null, "Sender cannot be null");
        Preconditions.checkArgument(args != null, "Arguments cannot be null");
        Preconditions.checkArgument(alias != null, "Alias cannot be null");

        if (args.length == 0 || !sender.getServer().suggestPlayerNamesWhenNullTabCompletions()) { // Paper - allow preventing player name suggestions by default
            return ImmutableList.of();
        }

        String lastWord = args[args.length - 1];

        Player senderPlayer = sender instanceof Player ? (Player) sender : null;

        ArrayList<String> matchedPlayers = new ArrayList<String>();
        for (Player player : sender.getServer().getOnlinePlayers()) {
            String name = player.getName();
            if ((senderPlayer == null || senderPlayer.canSee(player)) && StringUtil.startsWithIgnoreCase(name, lastWord)) {
                matchedPlayers.add(name);
            }
        }

        Collections.sort(matchedPlayers, String.CASE_INSENSITIVE_ORDER);
        return matchedPlayers;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个命令的名称.
     * <p>原文：
     * Returns the name of this command
     *
     * @return 这个命令的名称
     * <p>原文：Name of this command
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个命令的名称.
     * <p>
     * 只能在注册之前使用本方法.
     * <p>
     * 如果成功设置新的名称将返回true，命令已被注册返回false.
     * <p>原文：
     * Sets the name of this command.
     * <p>
     * May only be used before registering the command.
     * Will return true if the new name is set, and false
     * if the command has already been registered.
     *
     * @param name 新的命令名称
     * <p>原文：New command name
     * @return 如果新的名称被更改返回true，命令已被注册返回false
     * <p>原文：returns true if the name change happened instantly or false if
     *     the command was already registered
     */
    public boolean setName(@NotNull String name) {
        if (!isRegistered()) {
            this.name = (name == null) ? "" : name;
            return true;
        }
        return false;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用户执行此命令所需的权限.
     * <p>原文：
     * Gets the permission required by users to be able to perform this
     * command
     *
     * @return 权限名，未指定权限则为null
     * <p>原文：Permission name, or null if none
     */
    @Nullable
    public String getPermission() {
        return permission;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置用户执行此命令所需的权限.
     * <p>原文：
     * Sets the permission required by users to be able to perform this
     * command
     *
     * @param permission 权限名，无需任何权限则为null
     * <p>原文：Permission name or null
     */
    public void setPermission(@Nullable String permission) {
        this.permission = permission;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试给定的{@link CommandSender}能否执行此命令.
     * <p>
     * 如果他们没有权限，将提示他们无权执行此命令.
     * <p>原文：
     * Tests the given {@link CommandSender} to see if they can perform this
     * command.
     * <p>
     * If they do not have permission, they will be informed that they cannot
     * do this.
     *
     * @param target 要测试的用户
     * <p>原文：User to test
     * @return 如果他们有权限则为true，false反之
     * <p>原文：true if they can use it, otherwise false
     */
    public boolean testPermission(@NotNull CommandSender target) {
        if (testPermissionSilent(target)) {
            return true;
        }

            // Paper start - use components for permissionMessage
        net.kyori.adventure.text.Component permissionMessage = this.permissionMessage != null ? this.permissionMessage : Bukkit.permissionMessage();
        if (!permissionMessage.equals(net.kyori.adventure.text.Component.empty())) {
            target.sendMessage(permissionMessage.replaceText(net.kyori.adventure.text.TextReplacementConfig.builder().matchLiteral("<permission>").replacement(permission).build()));
            // Paper end
        }

        return false;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试给定的{@link CommandSender}能否执行这个命令.
     * <p>
     * 此方法不会给sender发送错误消息.
     * <p>原文：
     * Tests the given {@link CommandSender} to see if they can perform this
     * command.
     * <p>
     * No error is sent to the sender.
     *
     * @param target 要测试的用户
     * <p>原文：User to test
     * @return 他们能不能使用命令
     * <p>原文：true if they can use it, otherwise false
     */
    public boolean testPermissionSilent(@NotNull CommandSender target) {
        if ((permission == null) || (permission.length() == 0)) {
            return true;
        }

        for (String p : permission.split(";")) {
            if (target.hasPermission(p)) {
                return true;
            }
        }

        return false;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个命令的别名.
     * <p>原文：
     * Returns the label for this command
     *
     * @return 这个命令的别名
     * <p>原文：Label of this command
     */
    @NotNull
    public String getLabel() {
        return label;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个命令的别名.
     * <p>
     * 只能在注册之前使用本方法.
     * <p>
     * 如果成功设置新的别名将返回true，命令已被注册则返回false.
     * <p>原文：
     * Sets the label of this command.
     * <p>
     * May only be used before registering the command.
     * Will return true if the new name is set, and false
     * if the command has already been registered.
     *
     * @param name 新的命令别名
     * <p>原文：The command's name
     * @return 如果新的别名被更改返回true，命令已被注册则返回false
     * <p>原文：returns true if the name change happened instantly or false if
     *     the command was already registered
     */
    public boolean setLabel(@NotNull String name) {
        if (name == null) {
            name = "";
        }
        this.nextLabel = name;
        if (!isRegistered()) {
            this.label = name;
            return true;
        }
        return false;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把这个命令注册给一个CommandMap.
     * <p>
     * 若此命令已经注册给某个 CommandMap, 则无法再注册给别的 CommandMap,
     * 只能修改注册过的 CommandMap.
     * <p>原文：
     * Registers this command to a CommandMap.
     * Once called it only allows changes the registered CommandMap
     *
     * @param commandMap 注册此命令给这个CommandMap
     * <p>原文：the CommandMap to register this command to
     * @return 如果注册成功则为true(当前注册的CommandMap是传递的CommandMap或null)，false反之
     * <p>原文：true if the registration was successful (the current registered
     *     CommandMap was the passed CommandMap or null) false otherwise
     */
    public boolean register(@NotNull CommandMap commandMap) {
        if (allowChangesFrom(commandMap)) {
            this.commandMap = commandMap;
            return true;
        }

        return false;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从给定的 CommandMap 中注销此命令, 并应用所有未完成的更改.
     * <p>原文：
     * Unregisters this command from the passed CommandMap applying any
     * outstanding changes
     *
     * @param commandMap 指定的CommandMap
     * <p>原文：the CommandMap to unregister
     * @return 如果成功注销则为true(当前注册的CommandMap是传递的CommandMap或null)，false反之
     * <p>原文：true if the unregistration was successful (the current
     *     registered CommandMap was the passed CommandMap or null) false
     *     otherwise
     */
    public boolean unregister(@NotNull CommandMap commandMap) {
        if (allowChangesFrom(commandMap)) {
            this.commandMap = null;
            this.activeAliases = new ArrayList<String>(this.aliases);
            this.label = this.nextLabel;
            return true;
        }

        return false;
    }

    private boolean allowChangesFrom(@NotNull CommandMap commandMap) {
        return (null == this.commandMap || this.commandMap == commandMap);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个命令的注册状态.
     * <p>原文：
     * Returns the current registered state of this command
     *
     * @return 这个命令当前是否已注册
     * <p>原文：true if this command is currently registered false otherwise
     */
    public boolean isRegistered() {
        return (null != this.commandMap);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个命令激活的别名列表.
     * <p>原文：
     * Returns a list of active aliases of this command
     *
     * @return 别名列表
     * <p>原文：List of aliases
     */
    @NotNull
    public List<String> getAliases() {
        return activeAliases;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回当没有这个命令的权限时显示的消息.
     * <p>原文：
     * Returns a message to be displayed on a failed permission check for this
     * command
     *
     * @return 无权提示消息
     * <p>原文：Permission check failed message
     * @deprecated 自1.13版本, 权限消息对玩家执行的命令已不再可用,
     * 因为没有执行相关命令的权限的客户端不知晓其存在性, 因此不会向服务端发送执行的命令.
     * 此消息将只能在控制台上看到,
     * 或命令使用 {@link Bukkit#dispatchCommand(CommandSender, String)} 执行时也能看到.
     * <p>原文：permission messages have not worked for player-executed
     * commands since 1.13 as clients without permission to execute a command
     * are unaware of its existence and therefore will not send an unknown
     * command execution to the server. This message will only ever be shown to
     * consoles or when this command is executed with
     * {@link Bukkit#dispatchCommand(CommandSender, String)}.
     */
    @Deprecated(since = "1.20.4")
    @Nullable
    public String getPermissionMessage() {
        return net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().serializeOrNull(permissionMessage); // Paper
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个命令的简介.
     * <p>原文：
     * Gets a brief description of this command
     *
     * @return 命令简介
     * <p>原文：Description of this command
     */
    @NotNull
    public String getDescription() {
        return description;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个命令的用法示例.
     * <p>原文：
     * Gets an example usage of this command
     *
     * @return 一个或多个用法示例
     * <p>原文：One or more example usages
     */
    @NotNull
    public String getUsage() {
        return usageMessage;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个命令的别名.
     * 如果别名未在 plugin.yml 中 commands 节点下的 aliases 节点定义, 则操作无效.
     * <p>原文：
     * Sets the list of aliases to request on registration for this command.
     * This is not effective outside of defining aliases in the {@link
     * PluginDescriptionFile#getCommands()} (under the
     * `<code>aliases</code>' node) is equivalent to this method.
     *
     * @param aliases 要为这个命令注册的别名
     * <p>原文：aliases to register to this command
     * @return 这个命令对象，可用于链式调用
     * <p>原文：this command object, for chaining
     */
    @NotNull
    public Command setAliases(@NotNull List<String> aliases) {
        this.aliases = aliases;
        if (!isRegistered()) {
            this.activeAliases = new ArrayList<String>(aliases);
        }
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此命令的简介.在{@link PluginDescriptionFile#getCommands()}的 <code>description</code> 节点上定义介绍与本方法是等效的.
     * <p>原文：
     * Sets a brief description of this command. Defining a description in the
     * {@link PluginDescriptionFile#getCommands()} (under the
     * `<code>description</code>' node) is equivalent to this method.
     *
     * @param description 新的命令介绍
     * <p>原文：new command description
     * @return 这个命令对象，可用于链式调用
     * <p>原文：this command object, for chaining
     */
    @NotNull
    public Command setDescription(@NotNull String description) {
        this.description = (description == null) ? "" : description;
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当没有这个命令的权限时发送的消息.
     * <p>原文：
     * Sets the message sent when a permission check fails
     *
     * @param permissionMessage 新的无权提示消息，null表示默认消息，空字符串表示没有提示消息
     * <p>原文：new permission message, null to indicate
     *     default message, or an empty string to indicate no message
     * @return 这个命令对象，可用于链式调用
     * <p>原文：this command object, for chaining
     * @deprecated 自1.13版本, 权限消息对玩家执行的命令已不再可用,
     * 因为没有执行相关命令的权限的客户端不知晓其存在性, 因此不会向服务端发送执行的命令.
     * 此消息将只能在控制台上看到,
     * 或命令使用 {@link Bukkit#dispatchCommand(CommandSender, String)} 执行时也能看到.
     * <p>原文：permission messages have not worked for player-executed
     * commands since 1.13 as clients without permission to execute a command
     * are unaware of its existence and therefore will not send an unknown
     * command execution to the server. This message will only ever be shown to
     * consoles or when this command is executed with
     * {@link Bukkit#dispatchCommand(CommandSender, String)}.
     */
    @Deprecated(since = "1.20.4")
    @NotNull
    public Command setPermissionMessage(@Nullable String permissionMessage) {
        this.permissionMessage = net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserializeOrNull(permissionMessage); // Paper
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个命令的用法示例.
     * <p>原文：
     * Sets the example usage of this command
     *
     * @param usage 新的用法示例
     * <p>原文：new example usage
     * @return 命令对象
     * <p>原文：this command object, for chaining
     */
    @NotNull
    public Command setUsage(@NotNull String usage) {
        this.usageMessage = (usage == null) ? "" : usage;
        return this;
    }
    // Paper start
    /**
     * Gets the permission message.
     *
     * @return the permission message
     * @deprecated permission messages have not worked for player-executed
     * commands since 1.13 as clients without permission to execute a command
     * are unaware of its existence and therefore will not send an unknown
     * command execution to the server. This message will only ever be shown to
     * consoles or when this command is executed with
     * {@link Bukkit#dispatchCommand(CommandSender, String)}.
     */
    @Deprecated
    public net.kyori.adventure.text.@Nullable Component permissionMessage() {
        return this.permissionMessage;
    }

    /**
     * Sets the permission message.
     *
     * @param permissionMessage the permission message
     * @deprecated permission messages have not worked for player-executed
     * commands since 1.13 as clients without permission to execute a command
     * are unaware of its existence and therefore will not send an unknown
     * command execution to the server. This message will only ever be shown to
     * consoles or when this command is executed with
     * {@link Bukkit#dispatchCommand(CommandSender, String)}.
     */
    @Deprecated
    public void permissionMessage(net.kyori.adventure.text.@Nullable Component permissionMessage) {
        this.permissionMessage = permissionMessage;
    }
    // Paper end

    public static void broadcastCommandMessage(@NotNull CommandSender source, @NotNull String message) {
        broadcastCommandMessage(source, message, true);
    }

    public static void broadcastCommandMessage(@NotNull CommandSender source, @NotNull String message, boolean sendToSource) {
        // Paper start
        broadcastCommandMessage(source, net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(message), sendToSource);
    }

    public static void broadcastCommandMessage(@NotNull CommandSender source, net.kyori.adventure.text.@NotNull Component message) {
        broadcastCommandMessage(source, message, true);
    }

    public static void broadcastCommandMessage(@NotNull CommandSender source, net.kyori.adventure.text.@NotNull Component message, boolean sendToSource) {
        net.kyori.adventure.text.TextComponent.Builder result = net.kyori.adventure.text.Component.text()
            .color(net.kyori.adventure.text.format.NamedTextColor.WHITE)
            .decoration(net.kyori.adventure.text.format.TextDecoration.ITALIC, false)
            .append(source.name())
            .append(net.kyori.adventure.text.Component.text(": "))
            .append(message);
        // Paper end

        if (source instanceof BlockCommandSender) {
            BlockCommandSender blockCommandSender = (BlockCommandSender) source;

            if (!blockCommandSender.getBlock().getWorld().getGameRuleValue(GameRules.COMMAND_BLOCK_OUTPUT)) {
                Bukkit.getConsoleSender().sendMessage(result);
                return;
            }
        } else if (source instanceof CommandMinecart) {
            CommandMinecart commandMinecart = (CommandMinecart) source;

            if (!commandMinecart.getWorld().getGameRuleValue(GameRules.COMMAND_BLOCK_OUTPUT)) {
                Bukkit.getConsoleSender().sendMessage(result);
                return;
            }
        }

        Set<Permissible> users = Bukkit.getPluginManager().getPermissionSubscriptions(Server.BROADCAST_CHANNEL_ADMINISTRATIVE);
        // Paper start
        net.kyori.adventure.text.TextComponent.Builder colored = net.kyori.adventure.text.Component.text()
            .color(net.kyori.adventure.text.format.NamedTextColor.GRAY)
            .decorate(net.kyori.adventure.text.format.TextDecoration.ITALIC)
            .append(net.kyori.adventure.text.Component.text("["), result, net.kyori.adventure.text.Component.text("]"));
        // Paper end

        if (sendToSource && !(source instanceof ConsoleCommandSender)) {
            source.sendMessage(message);
        }

        for (Permissible user : users) {
            if (user instanceof CommandSender && user.hasPermission(Server.BROADCAST_CHANNEL_ADMINISTRATIVE)) {
                CommandSender target = (CommandSender) user;

                if (target instanceof ConsoleCommandSender) {
                    target.sendMessage(result);
                } else if (target != source) {
                    target.sendMessage(colored);
                }
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + '(' + name + ')';
    }

    // Paper start
    @org.jetbrains.annotations.ApiStatus.Internal
    public boolean canBeOverriden() { return false; }
    // Paper end
}
