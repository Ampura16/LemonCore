package org.bukkit.block.data.type;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'short' 值表示该活塞臂是否由于正在回缩的原因比平时短.
 * <p>原文：
 * 'short' denotes this piston head is shorter than the usual amount because it
 * is currently retracting.
 */
public interface PistonHead extends TechnicalPiston {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'short' 属性的值.
     * <p>原文：
     * Gets the value of the 'short' property.
     *
     * @return 属性 'short' 的值
     * <p>原文：the 'short' value
     */
    boolean isShort();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'short' 属性的值.
     * <p>原文：
     * Sets the value of the 'short' property.
     *
     * @param _short 新的 'short' 属性值
     * <p>原文：the new 'short' value
     */
    void setShort(boolean _short);
}
