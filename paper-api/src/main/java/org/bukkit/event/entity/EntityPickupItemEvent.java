package org.bukkit.event.entity;

import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体捡起地面上的掉落物时触发本事件.
 * <p>原文：
 * Thrown when an entity picks an item up from the ground
 */
public class EntityPickupItemEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Item item;
    private final int remaining;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityPickupItemEvent(@NotNull final LivingEntity entity, @NotNull final Item item, final int remaining) {
        super(entity);
        this.item = item;
        this.remaining = remaining;
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体捡起的掉落物.
     * <p>原文：
     * Gets the Item picked up by the entity.
     *
     * @return 掉落物
     * <p>原文：Item
     */
    @NotNull
    public Item getItem() {
        return this.item;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取掉落物剩余未被拾起的堆叠数量, 如果还有的话.
     * <p>原文：
     * Gets the amount remaining on the ground, if any
     *
     * @return 掉落物剩余未被拾起的堆叠数量
     * <p>原文：amount remaining on the ground
     */
    public int getRemaining() {
        return this.remaining;
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
