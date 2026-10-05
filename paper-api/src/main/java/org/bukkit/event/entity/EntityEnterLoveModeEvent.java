package org.bukkit.event.entity;

import org.bukkit.entity.Animals;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体进入求爱模式时触发本事件.
 * <br>
 * 本事件可被取消, 但喂给实体的食物仍会被消耗.
 * <p>原文：
 * Called when an entity enters love mode.
 * <br>
 * This can be cancelled but the item will still be consumed that was used to
 * make the entity enter into love mode.
 */
public class EntityEnterLoveModeEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final HumanEntity humanEntity;
    private int ticksInLove;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityEnterLoveModeEvent(@NotNull Animals animalInLove, @Nullable HumanEntity humanEntity, int ticksInLove) {
        super(animalInLove);
        this.humanEntity = humanEntity;
        this.ticksInLove = ticksInLove;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取正进入求爱模式的动物.
     * <p>原文：
     * Gets the animal that is entering love mode.
     *
     * @return 正进入求爱模式的动物
     * <p>原文：The animal that is entering love mode
     */
    @NotNull
    @Override
    public Animals getEntity() {
        return (Animals) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取导致动物进入求爱模式的人类实体.
     * <p>原文：
     * Gets the Human Entity that caused the animal to enter love mode.
     *
     * @return 人类实体, 如果不因人类而发情则为 null
     * <p>原文：The Human entity that caused the animal to enter love mode, or
     * {@code null} if there wasn't one.
     */
    @Nullable
    public HumanEntity getHumanEntity() {
        return this.humanEntity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取动物爱意的持续时长 (单位为tick).
     * <p>原文：
     * Gets the amount of ticks that the animal will fall in love for.
     *
     * @return 动物爱意的持续时长 (单位为tick)
     * <p>原文：The amount of ticks that the animal will fall in love for
     */
    public int getTicksInLove() {
        return this.ticksInLove;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置动物爱意的持续时长 (单位为tick).
     * <p>原文：
     * Sets the amount of ticks that the animal will fall in love for.
     *
     * @param ticksInLove 动物爱意的持续时长 (单位为tick)
     * <p>原文：The amount of ticks that the animal will fall in love
     * for
     */
    public void setTicksInLove(int ticksInLove) {
        this.ticksInLove = ticksInLove;
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
