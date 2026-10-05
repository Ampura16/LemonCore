package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'type' 值代表该 (技术) 活塞方块对应的类型.
 * <p>原文：
 * 'type' represents the type of piston which this (technical) block corresponds
 * to.
 */
public interface TechnicalPiston extends Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'type' 属性的值.
     * <p>原文：
     * Gets the value of the 'type' property.
     *
     * @return 属性 'type' 的值
     * <p>原文：the 'type' value
     */
    @NotNull
    Type getType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'type' 属性的值.
     * <p>原文：
     * Sets the value of the 'type' property.
     *
     * @param type 新的 'type' 属性值
     * <p>原文：the new 'type' value
     */
    void setType(@NotNull Type type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 不同的活塞变种.
     * <p>原文：
     * Different piston variants.
     */
    public enum Type {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 普通的活塞, 不会在回缩时将方块拉回来
         * <p>原文：
         * A normal piston which does not pull connected blocks backwards on
         * retraction.
         */
        NORMAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 有粘性的活塞, 会在回缩时把方块带回来
         * <p>原文：
         * A sticky piston which will also retract connected blocks.
         */
        STICKY;
    }
}
