package org.bukkit.event.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当箭矢射进实体或从实体上拔出(脱落)时触发本事件.
 * <p>原文：
 * Called when an arrow enters or exists an entity's body.
 */
public class ArrowBodyCountChangeEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final boolean reset;
    private final int oldAmount;
    private int newAmount;

    private boolean cancelled;

    @ApiStatus.Internal
    public ArrowBodyCountChangeEvent(@NotNull LivingEntity entity, int oldAmount, int newAmount, boolean reset) {
        super(entity);

        this.oldAmount = oldAmount;
        this.newAmount = newAmount;
        this.reset = reset;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回本事件是否因为实体被重置而触发.
     * <p>原文：
     * Whether the event was called because the entity was reset.
     *
     * @return 实体是否被重置
     * <p>原文：was reset
     */
    public boolean isReset() {
        return this.reset;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取事件发生前实体身上有多少支箭.
     * <p>原文：
     * Gets the old amount of arrows in the entity's body.
     *
     * @return 实体身上曾有多少支箭
     * <p>原文：amount of arrows
     */
    public int getOldAmount() {
        return this.oldAmount;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在此事件后实体身上有多少支箭.
     * <p>原文：
     * Get the new amount of arrows in the entity's body.
     *
     * @return 实体身上将有多少支箭
     * <p>原文：amount of arrows
     */
    public int getNewAmount() {
        return this.newAmount;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此事件后实体身上最终的箭矢数量.
     * <p>原文：
     * Sets the final amount of arrows in the entity's body.
     *
     * @param newAmount 实体身上将有多少支箭
     * <p>原文：amount of arrows
     */
    public void setNewAmount(int newAmount) {
        Preconditions.checkArgument(newAmount >= 0, "New arrow amount must be >= 0");
        this.newAmount = newAmount;
    }

    @Override
    @NotNull
    public LivingEntity getEntity() {
        return (LivingEntity) this.entity;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
