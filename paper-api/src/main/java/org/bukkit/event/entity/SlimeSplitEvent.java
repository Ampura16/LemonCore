package org.bukkit.event.entity;

import org.bukkit.entity.Slime;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当史莱姆死亡后分裂成小史莱姆时触发本事件
 * <p>原文：
 * Called when a Slime splits into smaller Slimes upon death
 */
public class SlimeSplitEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private int count;
    private boolean cancelled;

    @ApiStatus.Internal
    public SlimeSplitEvent(@NotNull final Slime slime, final int count) {
        super(slime);
        this.count = count;
    }

    @NotNull
    @Override
    public Slime getEntity() {
        return (Slime) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取生成的小史莱姆的数量.
     * <p>原文：
     * Gets the amount of smaller slimes to spawn
     *
     * @return 生成的小史莱姆的数量
     * <p>原文：the amount of slimes to spawn
     */
    public int getCount() {
        return this.count;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置分裂时将生成多少小史莱姆
     * <p>原文：
     * Sets how many smaller slimes will spawn on the split
     *
     * @param count 生成的小史莱姆的数量
     * <p>原文：the amount of slimes to spawn
     */
    public void setCount(int count) {
        this.count = count;
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
