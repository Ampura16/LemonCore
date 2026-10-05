package com.destroystokyo.paper.event.brigadier;

import com.mojang.brigadier.tree.RootCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 每当为玩家生成 Brigadier RootCommandNode 以告知客户端命令时触发。你可以操作此 CommandNode 来改变客户端看到的内容。
 * <p>
 * 该事件可能在登录、切换世界、权限重建时触发，也可能因插件请求及将来的其他方式触发。
 * </p>
 * <p>
 * 该事件会在 {@link org.bukkit.event.player.PlayerCommandSendEvent} 之前触发，因此其他插件尚未进行任何过滤。
 * </p>
 * <p>
 * 警告：该事件可能（而且很可能）触发两次！一次异步，一次同步。务必检查 event.isAsynchronous() 和 event.hasFiredAsync()，以确保只处理一次。如果将来由于某种原因无法异步发送，则只会触发同步版本。
 * </p>
 * <p>
 * 你的逻辑应如下所示：{@code if (event.isAsynchronous() || !event.hasFiredAsync()) { // do stuff }}
 * </p>
 * <p>
 * 如果你的逻辑无法安全地异步运行，请只响应同步版本。
 * </p>
 * <p>
 * 这是草案/实验性 API，可能会发生变化。
 * </p>
 * <p>原文：
 * Fired any time a Brigadier RootCommandNode is generated for a player to inform the client of commands.
 * You may manipulate this CommandNode to change what the client sees.
 *
 * <p>This event may fire on login, world change, and permission rebuilds, by plugin request, and potentially future means.</p>
 *
 * <p>This event will fire before {@link org.bukkit.event.player.PlayerCommandSendEvent}, so no filtering has been done by
 * other plugins yet.</p>
 *
 * <p>WARNING: This event will potentially (and most likely) fire twice! Once for Async, and once again for Sync.
 * It is important that you check event.isAsynchronous() and event.hasFiredAsync() to ensure you only act once.
 * If for some reason we are unable to send this asynchronously in the future, only the sync method will fire.</p>
 *
 * <p>Your logic should look like this:
 * {@code if (event.isAsynchronous() || !event.hasFiredAsync()) { // do stuff }}</p>
 *
 * <p>If your logic is not safe to run asynchronously, only react to the synchronous version.</p>
 *
 * <p>This is a draft/experimental API and is subject to change.</p>
 */
@ApiStatus.Experimental
@NullMarked
public class AsyncPlayerSendCommandsEvent<S extends CommandSourceStack> extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final RootCommandNode<S> node;
    private final boolean hasFiredAsync;

    @ApiStatus.Internal
    public AsyncPlayerSendCommandsEvent(final Player player, final RootCommandNode<S> node, final boolean hasFiredAsync) {
        super(player, !Bukkit.isPrimaryThread());
        this.node = node;
        this.hasFiredAsync = hasFiredAsync;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在发送给客户端的完整根命令节点，该节点是可变的。
     * <p>原文：
     * Gets the full Root Command Node being sent to the client, which is mutable.
     *
     * @return 根命令节点
     * <p>原文：the root command node
     */
    public RootCommandNode<S> getCommandNode() {
        return this.node;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件是否已经异步触发过。
     * <p>原文：
     * Gets if this event has already fired asynchronously.
     *
     * @return 此事件是否已经异步触发过
     * <p>原文：whether this event has already fired asynchronously
     */
    public boolean hasFiredAsync() {
        return this.hasFiredAsync;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
