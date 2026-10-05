package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当猪灵被另一个实体激怒时调用。
 * <p>
 * 如果事件被取消，猪灵将不会被激怒。
 * <p>原文：
 * Called when a Zombified piglin is angered by another entity.
 * <p>
 * If the event is cancelled, the zombified piglin will not be angered.
 */
public class PigZombieAngerEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity target;
    private int newAnger;

    private boolean cancelled;

    @ApiStatus.Internal
    public PigZombieAngerEvent(@NotNull final PigZombie zombifiedPiglin, @Nullable final Entity target, final int newAnger) {
        super(zombifiedPiglin);
        this.target = target;
        this.newAnger = newAnger;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取触发此愤怒更新的实体（如果有）。
     * <p>原文：
     * Gets the entity (if any) which triggered this anger update.
     *
     * @return 触发实体，或 null
     * <p>原文：triggering entity, or {@code null}
     */
    @Nullable
    public Entity getTarget() {
        return this.target;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件产生的新愤怒值。
     * <p>原文：
     * Gets the new anger resulting from this event.
     *
     * @return 新愤怒值
     * <p>原文：new anger
     * @see PigZombie#getAnger()
     */
    public int getNewAnger() {
        return this.newAnger;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此事件产生的新愤怒值。
     * <p>原文：
     * Sets the new anger resulting from this event.
     *
     * @param newAnger 新愤怒值
     * <p>原文：the new anger
     * @see PigZombie#setAnger(int)
     */
    public void setNewAnger(int newAnger) {
        this.newAnger = newAnger;
    }

    @NotNull
    @Override
    public PigZombie getEntity() {
        return (PigZombie) this.entity;
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
