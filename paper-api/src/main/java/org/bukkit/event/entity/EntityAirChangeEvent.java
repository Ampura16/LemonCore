package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体的氧气值发生变化时触发本事件
 * <p>原文：
 * Called when the amount of air an entity has remaining changes.
 */
public class EntityAirChangeEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private int amount;
    private boolean cancelled;

    @ApiStatus.Internal
    public EntityAirChangeEvent(@NotNull Entity entity, int amount) {
        super(entity);
        this.amount = amount;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体剩余的氧气值 (单位为刻)
     * <p>原文：
     * Gets the amount of air the entity has left (measured in ticks).
     *
     * @return 实体剩余的氧气值
     * <p>原文：amount of air remaining
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体剩余的氧气值 (单位为刻)
     * <p>原文：
     * Sets the amount of air remaining for the entity (measured in ticks).
     *
     * @param amount 实体剩余的氧气值
     * <p>原文：amount of air remaining
     */
    public void setAmount(int amount) {
        this.amount = amount;
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
