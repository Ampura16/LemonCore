package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.Waterlogged;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'signal_fire' 值表示该营火是否因为底部放了干草块而冒出更多的烟 (信号营火).
 * <p>原文：
 * 'signal_fire' denotes whether the fire is extra smokey due to having a hay
 * bale placed beneath it.
 */
public interface Campfire extends Directional, Lightable, Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'signal_fire' 属性的值.
     * <p>原文：
     * Gets the value of the 'signal_fire' property.
     *
     * @return 属性 'signal_fire' 的值
     * <p>原文：the 'signal_fire' value
     */
    boolean isSignalFire();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'signal_fire' 属性的值.
     * <p>原文：
     * Sets the value of the 'signal_fire' property.
     *
     * @param signalFire 新的 'signal_fire' 属性值
     * <p>原文：the new 'signal_fire' value
     */
    void setSignalFire(boolean signalFire);
}
