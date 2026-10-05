package org.bukkit.entity;

import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示载具实体。
 * <p>原文：
 * Represents a vehicle entity.
 */
public interface Vehicle extends Entity {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取载具的速度.
     * <p>原文：
     * Gets the vehicle's velocity.
     *
     * @return 速度向量
     * <p>原文：velocity vector
     */
    @Override
    @NotNull
    public Vector getVelocity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置载具的速度(单位: 米/刻).
     * <p>原文：
     * Sets the vehicle's velocity in meters per tick.
     *
     * @param vel 速度向量
     * <p>原文：velocity vector
     */
    @Override
    public void setVelocity(@NotNull Vector vel);
}
