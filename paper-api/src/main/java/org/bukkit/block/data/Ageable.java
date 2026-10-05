package org.bukkit.block.data;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'age' 表示农作物的不同生长阶段.
 * <br>
 * '0' 值意味着这个农作物刚刚种下去，同时如果 'age' 值与 {@link #getMaximumAge()}
 * 相等, 则表明此农作物已成熟并可被收获.
 * 译注: Minecraft 中 (测试版本 1.13) 农作物最大有八个生长阶段, 数值范围从 0 到 7.
 * 不过不同农作物可能有差异, 比如小麦有 8 个阶段, 而甜菜只有 4 个阶段.
 * <p>原文：
 * 'age' represents the different growth stages that a crop-like block can go
 * through.
 * <br>
 * A value of 0 indicates that the crop was freshly planted, whilst a value
 * equal to {@link #getMaximumAge()} indicates that the crop is ripe and ready
 * to be harvested.
 */
public interface Ageable extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'age' 属性值.
     * <p>原文：
     * Gets the value of the 'age' property.
     *
     * @return 生长阶段值, 以整数表示
     * <p>原文：the 'age' value
     */
    int getAge();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'age' 属性值.
     * <p>原文：
     * Sets the value of the 'age' property.
     *
     * @param age 生长阶段值, 以整数表示
     * <p>原文：the new 'age' value
     */
    void setAge(int age);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该农作物 'age' 的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'age' property.
     *
     * @return 生长阶段最大值
     * <p>原文：the maximum 'age' value
     */
    int getMaximumAge();
}
