package org.bukkit.block.data;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'hatch' 值为这些蛋中孵化出实体的数量.
 * <p>原文：
 * 'hatch' is the number of entities which may hatch from these eggs.
 */
public interface Hatchable extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'hatch' 属性的值.
     * <p>原文：
     * Gets the value of the 'hatch' property.
     *
     * @return 属性 'hatch' 的值
     * <p>原文：the 'hatch' value
     */
    int getHatch();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'hatch' 属性的值.
     * <p>原文：
     * Sets the value of the 'hatch' property.
     *
     * @param hatch 新的 'hatch' 属性值
     * <p>原文：the new 'hatch' value
     */
    void setHatch(int hatch);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'hatch' 属性所能允许的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'hatch' property.
     *
     * @return 最大 'hatch' 属性值.
     * <p>原文：the maximum 'hatch' value
     */
    int getMaximumHatch();
}
