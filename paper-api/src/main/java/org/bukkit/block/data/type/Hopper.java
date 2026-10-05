package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 与 {@link Powerable} 相似, 'enabled' 数据值表示此漏斗是否被激活.
 * <br>
 * 不像其它大多数方块, 漏斗不接受任何红石信号时为激活态.
 * (当它被激活时, 漏斗停止工作, 可以玩玩红石试试)
 * <p>原文：
 * Similar to {@link Powerable}, 'enabled' indicates whether or not the hopper
 * is currently activated.
 * <br>
 * Unlike most other blocks, a hopper is only enabled when it is <b>not</b>
 * receiving any power.
 */
public interface Hopper extends Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'enabled' 属性数据值.
     * <p>原文：
     * Gets the value of the 'enabled' property.
     *
     * @return 'enabled' 数据值
     * <p>原文：the 'enabled' value
     */
    boolean isEnabled();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'enabled' 属性数据值.
     * <p>原文：
     * Sets the value of the 'enabled' property.
     *
     * @param enabled 'enabled' 数据值
     * <p>原文：the new 'enabled' value
     */
    void setEnabled(boolean enabled);
}
