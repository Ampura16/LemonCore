package com.destroystokyo.paper.event.brigadier;

import com.destroystokyo.paper.brigadier.BukkitBrigadierCommand;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import org.bukkit.Warning;
import org.bukkit.command.Command;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 每当服务器将 Bukkit 命令同步到 Brigadier 时触发。
 * <p>
 * 允许插件控制其命令的命令节点结构。该过程在命令注册完毕后的插件启用时执行，但也可能因插件、服务器重载等原因在服务器生命周期的较晚时刻运行。
 * </p>
 * <p>原文：
 * Fired anytime the server synchronizes Bukkit commands to Brigadier.
 *
 * <p>Allows a plugin to control the command node structure for its commands.
 * This is done at Plugin Enable time after commands have been registered, but may also
 * run at a later point in the server lifetime due to plugins, a server reload, etc.</p>
 *
 * @deprecated 将被移除，请使用新版 Brigadier API。
 * <p>原文：For removal, use the new brigadier api.
 */
@Deprecated(since = "1.20.6")
@Warning(reason = "This event has been superseded by the Commands API and will be removed in a future release. Listen to LifecycleEvents.COMMANDS instead.", value = true)
public class CommandRegisteredEvent<S extends com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource> extends ServerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final String commandLabel;
    private final Command command;
    private final com.destroystokyo.paper.brigadier.BukkitBrigadierCommand<S> brigadierCommand;
    private final RootCommandNode<S> root;
    private final ArgumentCommandNode<S, String> defaultArgs;
    private LiteralCommandNode<S> literal;
    private boolean rawCommand = false;

    private boolean cancelled;

    public CommandRegisteredEvent(String commandLabel, com.destroystokyo.paper.brigadier.BukkitBrigadierCommand<S> brigadierCommand, Command command, RootCommandNode<S> root, LiteralCommandNode<S> literal, ArgumentCommandNode<S, String> defaultArgs) {
        this.commandLabel = commandLabel;
        this.brigadierCommand = brigadierCommand;
        this.command = command;
        this.root = root;
        this.literal = literal;
        this.defaultArgs = defaultArgs;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在注册的 {@link Command} 的命令标签。
     * <p>原文：
     * Gets the command label of the {@link Command} being registered.
     *
     * @return 命令标签
     * <p>原文：the command label
     */
    public String getCommandLabel() {
        return this.commandLabel;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在注册的 {@link Command} 的 {@link BukkitBrigadierCommand}。可用作 {@link com.mojang.brigadier.tree.CommandNode} 的 {@link com.mojang.brigadier.Command command executor} 或 {@link com.mojang.brigadier.suggestion.SuggestionProvider}，以委托给正在注册的 {@link Command}。
     * <p>原文：
     * Gets the {@link BukkitBrigadierCommand} for the {@link Command} being registered. This can be used
     * as the {@link com.mojang.brigadier.Command command executor} or
     * {@link com.mojang.brigadier.suggestion.SuggestionProvider} of a {@link com.mojang.brigadier.tree.CommandNode}
     * to delegate to the {@link Command} being registered.
     *
     * @return {@link BukkitBrigadierCommand}
     * <p>原文：the {@link BukkitBrigadierCommand}
     */
    public BukkitBrigadierCommand<S> getBrigadierCommand() {
        return this.brigadierCommand;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在注册的 {@link Command}。
     * <p>原文：
     * Gets the {@link Command} being registered.
     *
     * @return {@link Command}
     * <p>原文：the {@link Command}
     */
    public Command getCommand() {
        return this.command;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取注册目标 {@link RootCommandNode}。
     * <p>原文：
     * Gets the {@link RootCommandNode} which is being registered to.
     *
     * @return {@link RootCommandNode}
     * <p>原文：the {@link RootCommandNode}
     */
    public RootCommandNode<S> getRoot() {
        return this.root;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取 Bukkit API 的默认参数节点（贪婪字符串），以便你复用。
     * <p>原文：
     * Gets the Bukkit APIs default arguments node (greedy string), for if
     * you wish to reuse it.
     *
     * @return 默认参数节点
     * <p>原文：default arguments node
     */
    public ArgumentCommandNode<S, String> getDefaultArgs() {
        return this.defaultArgs;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取要为 {@link Command} 注册的 {@link LiteralCommandNode}。
     * <p>原文：
     * Gets the {@link LiteralCommandNode} to be registered for the {@link Command}.
     *
     * @return {@link LiteralCommandNode}
     * <p>原文：the {@link LiteralCommandNode}
     */
    public LiteralCommandNode<S> getLiteral() {
        return this.literal;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置用于注册此命令的 {@link LiteralCommandNode}。默认字面量是可变的，因此这主要用于你想完全替换该对象的情况。
     * <p>原文：
     * Sets the {@link LiteralCommandNode} used to register this command. The default literal is mutable, so
     * this is primarily if you want to completely replace the object.
     *
     * @param literal 新节点
     * <p>原文：new node
     */
    public void setLiteral(LiteralCommandNode<S> literal) {
        this.literal = literal;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此命令是否被视为“原始”命令。
     * <p>原文：
     * Gets whether this command should is treated as "raw".
     *
     * @see #setRawCommand(boolean)
     * @return 此命令是否被视为“原始”命令
     * <p>原文：whether this command is treated as "raw"
     */
    public boolean isRawCommand() {
        return this.rawCommand;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此命令是否应被视为“原始”命令。
     * <p>
     * “原始”命令只会使用此事件提供的节点向客户端发送命令树。在执行方面，使用标准 Bukkit {@link Command} 的默认贪婪字符串执行方式。
     * </p>
     * <p>
     * 在旧版 Paper 中，这是此事件默认且唯一的行为。
     * </p>
     * <p>原文：
     * Sets whether this command should be treated as "raw".
     *
     * <p>A "raw" command will only use the node provided by this event for
     * sending the command tree to the client. For execution purposes, the default
     * greedy string execution of a standard Bukkit {@link Command} is used.</p>
     *
     * <p>On older versions of Paper, this was the default and only behavior of this
     * event.</p>
     *
     * @param rawCommand 此命令是否应被视为“原始”命令
     * <p>原文：whether this command should be treated as "raw"
     */
    public void setRawCommand(final boolean rawCommand) {
        this.rawCommand = rawCommand;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Cancels registering this command to Brigadier, but will remain in Bukkit Command Map. Can be used to hide a
     * command from all players.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
