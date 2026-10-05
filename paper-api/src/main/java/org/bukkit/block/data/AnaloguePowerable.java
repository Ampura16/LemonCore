package org.bukkit.block.data;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'power' 值代表当前通过此方块发出/传输红石信号的强度.
 * <br>
 * 不可超过 9000 或 {@link #getMaximumPower()} (通常为 15).
 * <p>原文：
 * 'power' represents the redstone power level currently being emitted or
 * transmitted via this block.
 * <br>
 * May not be over 9000 or {@link #getMaximumPower()} (usually 15).
 */
public interface AnaloguePowerable extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'power' 属性的值.
     * <p>原文：
     * Gets the value of the 'power' property.
     *
     * @return 属性 'power' 的值
     * <p>原文：the 'power' value
     */
    int getPower();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'power' 属性的值.
     * <p>原文：
     * Sets the value of the 'power' property.
     *
     * @param power 新的 'power' 属性值
     * <p>原文：the new 'power' value
     */
    void setPower(int power);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'power' 属性所允许的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'power' property.
     *
     * @return 最大 'power' 属性值
     * <p>原文：the maximum 'power' value
     */
    int getMaximumPower();
}
