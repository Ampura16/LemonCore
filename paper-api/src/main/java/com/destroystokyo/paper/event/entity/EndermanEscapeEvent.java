package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.Enderman;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class EndermanEscapeEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Reason reason;
    private boolean cancelled;

    @ApiStatus.Internal
    public EndermanEscapeEvent(final Enderman entity, final Reason reason) {
        super(entity);
        this.reason = reason;
    }

    @Override
    public Enderman getEntity() {
        return (Enderman) super.getEntity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取末影人尝试逃脱的原因。
     * <p>原文：
     * Gets the reason the enderman is trying to escape.
     *
     * @return 原因
     * <p>原文：The reason
     */
    public Reason getReason() {
        return this.reason;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 取消此次逃脱。
     * <p>
     * 如果此次逃脱原本会避免间接伤害等伤害，末影人现在将受到伤害。但这不会改变末影人的固有免疫或伤害行为，例如箭矢伤害本就不会发生。
     * <p>原文：
     * Cancels the escape.
     * <p>
     * If this escape normally had resulted in damage avoidance such as indirect,
     * the enderman will now take damage. However, this does not change the Enderman's
     * innate immunities or damage behavior like arrows where the damage never happens.
     */
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

    public enum Reason {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 末影人已停止攻击并逃跑
         * <p>原文：
         * The enderman has stopped attacking and ran away
         */
        RUNAWAY,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 末影人因间接（远程）伤害而传送离开
         * <p>原文：
         * The enderman has teleported away due to indirect damage (ranged)
         */
        INDIRECT,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 末影人因暴击而传送离开
         * <p>原文：
         * The enderman has teleported away due to a critical hit
         */
        CRITICAL_HIT,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 末影人因战斗中玩家注视它而传送离开
         * <p>原文：
         * The enderman has teleported away due to the player staring at it during combat
         */
        STARE,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * {@link #CRITICAL_HIT} 的特定情形，即末影人因溺水（例如淋雨）而受到伤害
         * <p>原文：
         * Specific case for {@link #CRITICAL_HIT} where the enderman is taking damage by drowning (ex: rain)
         */
        DROWN
    }
}
