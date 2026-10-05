package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.Powerable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'in_wall" 值表示栅栏门是否固定在墙上,
 * 若为 true, 栅栏门的材质会被稍微降低一点以更好的融入周围 (墙, 比如圆石墙).
 * <p>原文：
 * 'in_wall' indicates if the fence gate is attached to a wall, and if true the
 * texture is lowered by a small amount to blend in better.
 */
public interface Gate extends Directional, Openable, Powerable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'in_wall' 属性的值.
     * <p>原文：
     * Gets the value of the 'in_wall' property.
     *
     * @return 属性 'in_wall' 的值
     * <p>原文：the 'in_wall' value
     */
    boolean isInWall();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'in_wall' 属性的值.
     * <p>原文：
     * Sets the value of the 'in_wall' property.
     *
     * @param inWall 新的 'in_wall' 属性值
     * <p>原文：the new 'in_wall' value
     */
    void setInWall(boolean inWall);
}
