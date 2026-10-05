package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'moisture' 值的的大小表示耕地距离水源 (如果有的话) .
 * <br>
 * 'moisture' 的值越高会使该方块上种植作物的生长速度更快,
 * 但是无法比 {@link #getMaximumMoisture()} 的值更高.
 * <p>原文：
 * The 'moisture' level of farmland indicates how close it is to a water source
 * (if any).
 * <br>
 * A higher moisture level leads, to faster growth of crops on this block, but
 * cannot be higher than {@link #getMaximumMoisture()}.
 */
public interface Farmland extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'moisture' 属性的值.
     * <p>原文：
     * Gets the value of the 'moisture' property.
     *
     * @return 属性 'moisture' 的值
     * <p>原文：the 'moisture' value
     */
    int getMoisture();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'moisture' 属性的值.
     * <p>原文：
     * Sets the value of the 'moisture' property.
     *
     * @param moisture 新的 'moisture' 属性值
     * <p>原文：the new 'moisture' value
     */
    void setMoisture(int moisture);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'moisture' 属性所允许的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'moisture' property.
     *
     * @return 最大 'moisture' 属性值
     * <p>原文：the maximum 'moisture' value
     */
    int getMaximumMoisture();
}
