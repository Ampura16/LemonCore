package io.papermc.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当实体身上或所用的物品因被击中/被使用而损失耐久时调用。
 * <p>
 * 注意：按原版默认行为，生物捡起的盔甲/工具不会受损（荆棘除外）。
 * <p>原文：
 * Called when an item on or used by an entity takes durability damage as a result of being hit/used.
 * <p>
 * NOTE: default vanilla behaviour dictates that armor/tools picked up by
 * mobs do not take damage (except via Thorns).
 */
@NullMarked
public class EntityDamageItemEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemStack item;
    private int damage;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityDamageItemEvent(final Entity entity, final ItemStack item, final int damage) {
        super(entity);
        this.item = item;
        this.damage = damage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在受损的物品。
     * <p>原文：
     * Gets the item being damaged.
     *
     * @return 物品
     * <p>原文：the item
     */
    public ItemStack getItem() {
        return this.item;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此物品将受到的耐久损伤值。
     * <p>原文：
     * Gets the amount of durability damage this item will be taking.
     *
     * @return 耐久度变化
     * <p>原文：durability change
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此物品将受到的耐久损伤值。
     * <p>原文：
     * Sets the amount of durability damage this item will be taking.
     *
     * @param damage 要造成的伤害量
     * <p>原文：the damage amount to cause
     */
    public void setDamage(final int damage) {
        this.damage = damage;
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
