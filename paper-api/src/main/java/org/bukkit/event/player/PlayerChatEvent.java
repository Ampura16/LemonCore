package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Warning;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 存储玩家聊天和命令的信息。玩家聊天/使用命令会触发本事件。
 * <p>原文：
 * Holds information for player chat and commands
 *
 * @deprecated 这个事件将被主线程触发，允许使用所有的 Bukkit API，不像{@link AsyncPlayerChatEvent}.
 *     <p>
 *     监听这个事件迫使聊天等待主线程，会导致聊天消息延迟展现。建议使用线程安全的{@link AsyncPlayerChatEvent}
 * <p>原文：Listening to this event forces chat to wait for the main thread, delaying chat messages. It is recommended to use {@link io.papermc.paper.event.player.AsyncChatEvent} instead, wherever possible.
 */
@Deprecated(since = "1.3.1")
@Warning(reason = "Listening to this event forces chat to wait for the main thread, delaying chat messages.")
public class PlayerChatEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Set<Player> recipients;
    private String message;
    private String format;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerChatEvent(@NotNull final Player player, @NotNull final String message) {
        super(player);
        this.message = message;
        this.format = "<%1$s> %2$s";
        this.recipients = new HashSet<>(player.getServer().getOnlinePlayers());
    }

    @ApiStatus.Internal
    public PlayerChatEvent(@NotNull final Player player, @NotNull final String message, @NotNull final String format, @NotNull final Set<Player> recipients) {
        super(player);
        this.message = message;
        this.format = format;
        this.recipients = recipients;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个玩家尝试发送的消息。
     * <p>原文：
     * Gets the message that the player is attempting to send
     *
     * @return 玩家尝试发送的消息
     * <p>原文：Message the player is attempting to send
     */
    @NotNull
    public String getMessage() {
        return this.message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个玩家将发送的消息。
     * <p>原文：
     * Sets the message that the player will send
     *
     * @param message 玩家将发送的新消息
     * <p>原文：New message that the player will send
     */
    public void setMessage(@NotNull String message) {
        this.message = message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这条消息显示成的玩家，或谁将执行这条命令.
     * <p>原文：
     * Sets the player that this message will display as, or command will be
     * executed as
     *
     * @param player 作为这个新玩家执行这个事件
     * <p>原文：New player which this event will execute as
     */
    public void setPlayer(@NotNull final Player player) {
        Preconditions.checkArgument(player != null, "Player cannot be null");
        this.player = player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于展示这条消息的格式。
     * <p>原文：
     * Gets the format to use to display this chat message
     *
     * @return 字符串格式:String.Format
     * <p>原文：String.Format compatible format string
     */
    @NotNull
    public String getFormat() {
        return this.format;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置用于展示这条消息的格式。
     * <p>原文：
     * Sets the format to use to display this chat message
     *
     * @param format 字符串格式:String.Format
     * <p>原文：String.Format compatible format string
     */
    public void setFormat(@NotNull final String format) {
        // Oh for a better way to do this!
        try {
            String.format(format, this.player, this.message);
        } catch (RuntimeException ex) {
            ex.fillInStackTrace();
            throw ex;
        }

        this.format = format;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个将被展示这条消息的玩家的集合。
     * <p>原文：
     * Gets a set of recipients that this chat message will be displayed to
     *
     * @return 将会看到本消息的所有玩家
     * <p>原文：All Players who will see this chat message
     */
    @NotNull
    public Set<Player> getRecipients() {
        return this.recipients;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
