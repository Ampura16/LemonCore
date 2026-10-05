package com.destroystokyo.paper.event.entity;

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
 * 当女巫饮用手中的药水为自己增益时触发。
 * <p>原文：
 * Fired when a witch consumes the potion in their hand to buff themselves.
 */
@NullMarked
public class WitchConsumePotionEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private @Nullable ItemStack potion;
    private boolean cancelled;

    @ApiStatus.Internal
    public WitchConsumePotionEvent(final Witch witch, final @Nullable ItemStack potion) {
        super(witch);
        this.potion = potion;
    }

    @Override
    public Witch getEntity() {
        return (Witch) super.getEntity();
    }

    /**
     * @return 女巫将饮用并获得其效果的药水。
     * <p>原文：the potion the witch will consume and have the effects applied.
     */
    public @Nullable ItemStack getPotion() {
        return this.potion;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置将被女巫饮用并施加给自身的药水。
     * <p>原文：
     * Sets the potion to be consumed and applied to the witch.
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
