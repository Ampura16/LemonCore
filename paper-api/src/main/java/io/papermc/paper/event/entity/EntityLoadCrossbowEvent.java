package io.papermc.paper.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当 LivingEntity 为弩装填弹射物时调用。
 * <p>原文：
 * Called when a LivingEntity loads a crossbow with a projectile.
 */
@NullMarked
public class EntityLoadCrossbowEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemStack crossbow;
    private final EquipmentSlot hand;

    private boolean consumeItem = true;
    private boolean cancelled;

    @ApiStatus.Internal
    public EntityLoadCrossbowEvent(final LivingEntity entity, final ItemStack crossbow, final EquipmentSlot hand) {
        super(entity);
        this.crossbow = crossbow;
        this.hand = hand;
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在装填的弩 {@link ItemStack}。
     * <p>原文：
     * Gets the crossbow {@link ItemStack} being loaded.
     *
     * @return 此事件涉及的弩
     * <p>原文：the crossbow involved in this event
     */
    public ItemStack getCrossbow() {
        return this.crossbow;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取装填弩所用的手。
     * <p>原文：
     * Gets the hand from which the crossbow was loaded.
     *
     * @return 手
     * <p>原文：the hand
     */
    public EquipmentSlot getHand() {
        return this.hand;
    }

    /**
     * @return 是否应消耗该物品堆
     * <p>原文：should the itemstack be consumed
     */
    public boolean shouldConsumeItem() {
        return this.consumeItem;
    }

    /**
     * @param consume 是否应消耗该物品
     * <p>原文：should the item be consumed
     */
    public void setConsumeItem(final boolean consume) {
        this.consumeItem = consume;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否取消装填弩。如果取消，本应装入弩的弹射物不会被消耗。
     * <p>原文：
     * Set whether to cancel the crossbow being loaded. If canceled, the
     * projectile that would be loaded into the crossbow will not be consumed.
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
}
