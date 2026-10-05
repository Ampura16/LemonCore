package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'extended' 值表示当前活塞臂是否伸出.
 * <p>原文：
 * 'extended' denotes whether the piston head is currently extended or not.
 */
public interface Piston extends Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'extended' 属性的值.
     * <p>原文：
     * Gets the value of the 'extended' property.
     *
     * @return 属性 'extended' 的值
     * <p>原文：the 'extended' value
     */
    boolean isExtended();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'extended' 属性的值.
     * <p>原文：
     * Sets the value of the 'extended' property.
     *
     * @param extended 新的 'extended' 属性值
     * <p>原文：the new 'extended' value
     */
    void setExtended(boolean extended);
}
