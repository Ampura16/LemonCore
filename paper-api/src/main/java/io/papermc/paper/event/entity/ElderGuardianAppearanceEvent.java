package io.papermc.paper.event.entity;

import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当 {@link ElderGuardian} 出现在 {@link Player} 面前时调用。
 * <p>原文：
 * Is called when an {@link ElderGuardian} appears in front of a {@link Player}.
 */
@NullMarked
public class ElderGuardianAppearanceEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player affectedPlayer;
    private boolean cancelled;

    @ApiStatus.Internal
    public ElderGuardianAppearanceEvent(final ElderGuardian guardian, final Player affectedPlayer) {
        super(guardian);
        this.affectedPlayer = affectedPlayer;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取受远古守卫者幻象影响的玩家。
     * <p>原文：
     * Get the player affected by the guardian appearance.
     *
     * @return 受幻象影响的玩家
     * <p>原文：Player affected by the appearance
     */
    public Player getAffectedPlayer() {
        return this.affectedPlayer;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 播放该效果的远古守卫者。
     * <p>原文：
     * The elder guardian playing the effect.
     *
     * @return 远古守卫者
     * <p>原文：The elder guardian
     */
    @Override
    public ElderGuardian getEntity() {
        return (ElderGuardian) super.getEntity();
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
