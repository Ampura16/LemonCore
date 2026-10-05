package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'stage' 值代表树苗的成长阶段.
 * <br>
 * 当树苗的成长阶段达到 {@link #getMaximumStage()} 时,
 * 它会尝试在下一个成长阶段长成一棵树.
 * <p>原文：
 * 'stage' represents the growth stage of a sapling.
 * <br>
 * When the sapling reaches {@link #getMaximumStage()} it will attempt to grow
 * into a tree as the next stage.
 */
public interface Sapling extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'stage' 属性的值.
     * <p>原文：
     * Gets the value of the 'stage' property.
     *
     * @return 属性 'stage' 的值
     * <p>原文：the 'stage' value
     */
    int getStage();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'stage' 属性的值.
     * <p>原文：
     * Sets the value of the 'stage' property.
     *
     * @param stage 新的 'stage' 属性值
     * <p>原文：the new 'stage' value
     */
    void setStage(int stage);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'stage' 属性所能允许的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'stage' property.
     *
     * @return 最大 'stage' 属性值
     * <p>原文：the maximum 'stage' value
     */
    int getMaximumStage();
}
