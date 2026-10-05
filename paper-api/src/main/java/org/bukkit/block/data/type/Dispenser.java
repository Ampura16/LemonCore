package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 与 {@link Powerable} 相似, 'triggered' 数据值表示此发射器是否被激活.
 * <p>原文：
 * Similar to {@link Powerable}, 'triggered' indicates whether or not the
 * dispenser is currently activated.
 */
public interface Dispenser extends Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'triggered' 数据值.
     * <p>原文：
     * Gets the value of the 'triggered' property.
     *
     * @return 此发射器是否被激活
     * <p>原文：the 'triggered' value
     */
    boolean isTriggered();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'triggered' 数据值.
     * <p>原文：
     * Sets the value of the 'triggered' property.
     *
     * @param triggered 此发射器是否被激活
     * <p>原文：the new 'triggered' value
     */
    void setTriggered(boolean triggered);
}
