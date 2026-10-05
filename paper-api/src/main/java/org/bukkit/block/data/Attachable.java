package org.bukkit.block.data;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'attached' 值表示绊线钩或绊线是否构成了一个完整的绊线回路并准备好触发.
 * <br>
 * 对一个绊线钩更新此属性将会改变其材质,
 * 但是对绊线本身更新没有任何效果.
 * 然而它还是可以拿来检查该绊线是否构成了一个完整的绊线回路.
 * <p>原文：
 * 'attached' denotes whether a tripwire hook or string forms a complete
 * tripwire circuit and is ready to trigger.
 * <br>
 * Updating the property on a tripwire hook will change the texture to indicate
 * a connected string, but will not have any effect when used on the tripwire
 * string itself. It may however still be used to check whether the string forms
 * a circuit.
 */
public interface Attachable extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'attached' 属性的值.
     * <p>原文：
     * Gets the value of the 'attached' property.
     *
     * @return 属性 'attached' 的值
     * <p>原文：the 'attached' value
     */
    boolean isAttached();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'attached' 属性的值.
     * <p>原文：
     * Sets the value of the 'attached' property.
     *
     * @param attached 新的 'attached' 属性值
     * <p>原文：the new 'attached' value
     */
    void setAttached(boolean attached);
}
