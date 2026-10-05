package com.destroystokyo.paper.event.player;

import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在玩家获得某个进度中的准则后调用。若取消，该准则将被撤销。
 * <p>原文：
 * Called after a player is granted a criteria in an advancement.
 * If cancelled the criteria will be revoked.
 */
@NullMarked
public class PlayerAdvancementCriterionGrantEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Advancement advancement;
    private final String criterion;
    private final AdvancementProgress advancementProgress;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerAdvancementCriterionGrantEvent(final Player player, final Advancement advancement, final String criterion) {
        super(player);
        this.advancement = advancement;
        this.criterion = criterion;
        this.advancementProgress = player.getAdvancementProgress(advancement);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取受影响的进度。
     * <p>原文：
     * Get the advancement which has been affected.
     *
     * @return 受影响的进度
     * <p>原文：affected advancement
     */
    public Advancement getAdvancement() {
        return this.advancement;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取已被授予的准则。
     * <p>原文：
     * Get the criterion which has been granted.
     *
     * @return 被授予的准则
     * <p>原文：granted criterion
     */
    public String getCriterion() {
        return this.criterion;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取当前的 AdvancementProgress。
     * <p>原文：
     * Gets the current AdvancementProgress.
     *
     * @return 进度的完成情况
     * <p>原文：advancement progress
     */
    public AdvancementProgress getAdvancementProgress() {
        return this.advancementProgress;
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
