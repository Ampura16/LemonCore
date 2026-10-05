package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'unstable' 值表示该 TNT 是否会在破坏后引爆.
 * <p>原文：
 * 'unstable' indicates whether this TNT will explode on punching.
 */
public interface TNT extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'unstable' 属性的值.
     * <p>原文：
     * Gets the value of the 'unstable' property.
     *
     * @return 属性 'unstable' 的值
     * <p>原文：the 'unstable' value
     */
    boolean isUnstable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'unstable' 属性的值.
     * <p>原文：
     * Sets the value of the 'unstable' property.
     *
     * @param unstable 新的 'unstable' 属性值
     * <p>原文：the new 'unstable' value
     */
    void setUnstable(boolean unstable);
}
