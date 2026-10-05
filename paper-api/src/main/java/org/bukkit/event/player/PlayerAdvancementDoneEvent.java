package org.bukkit.event.player;

import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.kyori.adventure.text.Component;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家完成一个进度中所有的标准时触发此事件.
 * <p>原文：
 * Called when a player has completed all criteria in an advancement.
 */
public class PlayerAdvancementDoneEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Advancement advancement;
    private Component message;

    @ApiStatus.Internal
    @Deprecated(since = "1.21.5", forRemoval = true)
    public PlayerAdvancementDoneEvent(@NotNull Player player, @NotNull Advancement advancement) {
        this(player, advancement, null);
    }

    @ApiStatus.Internal
    public PlayerAdvancementDoneEvent(@NotNull Player player, @NotNull Advancement advancement, @Nullable Component message) {
        super(player);
        this.advancement = advancement;
        this.message = message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得已完成的进度.
     * <p>原文：
     * Get the advancement which has been completed.
     *
     * @return 已完成的 {@link Advancement}
     * <p>原文：completed advancement
     */
    @NotNull
    public Advancement getAdvancement() {
        return this.advancement;
    }

    /**
     * Gets the message to send to all online players.
     * <p>
     * Will be {@code null} if the advancement does not announce to chat, for example if
     * it is a recipe unlock or a root advancement.
     *
     * @return The announcement message, or {@code null}
     */
    @Nullable
    public Component message() {
        return this.message;
    }

    /**
     * Sets the message to send to all online players.
     * <p>
     * If set to {@code null} the message will not be sent.
     *
     * @param message The new message
     */
    public void message(@Nullable Component message) {
        this.message = message;
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
