package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Witch;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当女巫向玩家投掷药水时触发
 * <p>原文：
 * Fired when a witch throws a potion at a player
 */
@NullMarked
public class WitchThrowPotionEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final LivingEntity target;
    private @Nullable ItemStack potion;
    private boolean cancelled;

    @ApiStatus.Internal
    public WitchThrowPotionEvent(final Witch witch, final LivingEntity target, final @Nullable ItemStack potion) {
        super(witch);
        this.target = target;
        this.potion = potion;
    }

    @Override
    public Witch getEntity() {
        return (Witch) super.getEntity();
    }

    /**
     * @return 药水的目标
     * <p>原文：The target of the potion
     */
    public LivingEntity getTarget() {
        return this.target;
    }

    /**
     * @return 女巫将投掷向玩家的药水
     * <p>原文：The potion the witch will throw at a player
     */
    public @Nullable ItemStack getPotion() {
        return this.potion;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置将要投掷向玩家的药水
     * <p>原文：
     * Sets the potion to be thrown at a player
     *
     * @param potion 药水
     * <p>原文：The potion
     */
    public void setPotion(final @Nullable ItemStack potion) {
        this.potion = potion != null ? potion.clone() : null;
    }

    /**
     * @return 事件已被取消或药水为 {@code null}
     * <p>原文：Event was cancelled or potion was {@code null}
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled || this.potion == null;
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
