package org.bukkit.event.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家进入服务器事件.
 * <p>原文：
 * Called when a player joins a server and has been placed in loaded chunks.
 *
 * <p>Teleporting the player during this event will result in undefined behavior.
 * Use {@link io.papermc.paper.event.player.AsyncPlayerSpawnLocationEvent} to change the spawn location.</p>
 */
public class PlayerJoinEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Component joinMessage;

    @ApiStatus.Internal
    public PlayerJoinEvent(@NotNull final Player playerJoined, @Nullable final Component joinMessage) {
        super(playerJoined);
        this.joinMessage = joinMessage;
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public PlayerJoinEvent(@NotNull final Player playerJoined, @Nullable final String joinMessage) {
        super(playerJoined);
        this.joinMessage = joinMessage != null ? LegacyComponentSerializer.legacySection().deserialize(joinMessage) : null;
    }

    /**
     * Gets the join message to send to all online players
     *
     * @return string join message. Can be {@code null}
     */
    public @Nullable Component joinMessage() {
        return this.joinMessage;
    }

    /**
     * Sets the join message to send to all online players
     *
     * @param joinMessage join message. If {@code null}, no message will be sent
     */
    public void joinMessage(@Nullable Component joinMessage) {
        this.joinMessage = joinMessage;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家加入游戏后发送给全体玩家的信息.
     * <p>原文：
     * Gets the join message to send to all online players
     *
     * @return 加入信息,可以为null
     * <p>原文：string join message. Can be {@code null}
     * @deprecated in favour of {@link #joinMessage()}
     */
    @Nullable
    @Deprecated
    public String getJoinMessage() {
        return this.joinMessage == null ? null : LegacyComponentSerializer.legacySection().serialize(this.joinMessage);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家加入游戏后发送给全体玩家的信息.
     * <p>原文：
     * Sets the join message to send to all online players
     *
     * @param joinMessage 加入信息, 若为null则不会发送加入信息
     * <p>原文：join message. If {@code null}, no message will be sent
     * @deprecated in favour of {@link #joinMessage(Component)}
     */
    @Deprecated
    public void setJoinMessage(@Nullable String joinMessage) {
        this.joinMessage = joinMessage != null ? LegacyComponentSerializer.legacySection().deserialize(joinMessage) : null;
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
