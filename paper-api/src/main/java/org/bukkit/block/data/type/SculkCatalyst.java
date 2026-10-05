package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'bloom' 表示幽匿催发体是否正在积极传播幽匿块.
 * <p>原文：
 * 'bloom' indicates whether the sculk catalyst is actively spreading the sculk
 * or not.
 */
public interface SculkCatalyst extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'bloom' 属性的值.
     * <p>原文：
     * Gets the value of the 'bloom' property.
     *
     * @return 'bloom' 的值
     * <p>原文：the 'bloom' value
     */
    boolean isBloom();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'bloom' 属性的值.
     * <p>原文：
     * Sets the value of the 'bloom' property.
     *
     * @param bloom 新的 'bloom' 值
     * <p>原文：the new 'bloom' value
     */
    void setBloom(boolean bloom);
}
