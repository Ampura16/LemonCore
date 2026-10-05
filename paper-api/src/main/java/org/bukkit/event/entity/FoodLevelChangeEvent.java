package org.bukkit.event.entity;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个人类实体的饥饿值发生变化时触发本事件
 * <p>原文：
 * Called when a human entity's food level changes
 */
public class FoodLevelChangeEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private int level;
    private final ItemStack item;

    private boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(since = "1.21.5", forRemoval = true)
    public FoodLevelChangeEvent(@NotNull final HumanEntity human, final int level) {
        this(human, level, null);
    }

    @ApiStatus.Internal
    public FoodLevelChangeEvent(@NotNull final HumanEntity human, final int level, @Nullable final ItemStack item) {
        super(human);
        this.level = level;
        this.item = item;
    }

    @NotNull
    @Override
    public HumanEntity getEntity() {
        return (HumanEntity) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 若存在的话, 返回触发本事件的物品.
     * <p>原文：
     * Gets the item that triggered this event, if any.
     *
     * @return 被吃的食物
     * <p>原文：an ItemStack for the item being consumed
     */
    @Nullable
    public ItemStack getItem() {
        return this.item == null ? null : this.item.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本事件涉及的实体将被设置的饥饿值
     * <p>
     * 20为饱和状态，0为饥饿状态.
     * <p>原文：
     * Gets the resultant food level that the entity involved in this event
     * should be set to.
     * <p>
     * Where 20 is a full food bar and 0 is an empty one.
     *
     * @return 饥饿值
     * <p>原文：The resultant food level
     */
    public int getFoodLevel() {
        return this.level;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体将被设置的饥饿值
     * <p>原文：
     * Sets the resultant food level that the entity involved in this event
     * should be set to
     *
     * @param level 饥饿值
     * <p>原文：the resultant food level that the entity involved in this
     *     event should be set to
     */
    public void setFoodLevel(int level) {
        if (level < 0) level = 0;

        this.level = level;
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
