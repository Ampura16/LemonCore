package org.bukkit.block.data.type;

import org.bukkit.block.data.AnaloguePowerable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'inverted' 值表示阳光探测器是否被调整了反转模式,
 * 即在光照不足时再激活.
 * <p>原文：
 * 'inverted' denotes whether this daylight detector is in the inverted mode,
 * i.e. activates in the absence of light rather than presence.
 */
public interface DaylightDetector extends AnaloguePowerable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'inverted' 属性的值.
     * <p>原文：
     * Gets the value of the 'inverted' property.
     *
     * @return 属性 'inverted' 的值
     * <p>原文：the 'inverted' value
     */
    boolean isInverted();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'inverted' 属性的值.
     * <p>原文：
     * Sets the value of the 'inverted' property.
     *
     * @param inverted 新的 'inverted' 属性值
     * <p>原文：the new 'inverted' value
     */
    void setInverted(boolean inverted);
}
