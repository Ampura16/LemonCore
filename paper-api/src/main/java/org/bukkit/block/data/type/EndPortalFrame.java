package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'eye' 值表示该末地传送门上是否放置了一个末影之眼 (被激活).
 * <p>原文：
 * 'eye' denotes whether this end portal frame has been activated by having an
 * eye of ender placed in it.
 */
public interface EndPortalFrame extends Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'eye' 属性的值.
     * <p>原文：
     * Gets the value of the 'eye' property.
     *
     * @return 属性 'eye' 的值
     * <p>原文：the 'eye' value
     */
    boolean hasEye();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'eye' 属性的值.
     * <p>原文：
     * Sets the value of the 'eye' property.
     *
     * @param eye 新的 'eye' 属性值
     * <p>原文：the new 'eye' value
     */
    void setEye(boolean eye);
}
