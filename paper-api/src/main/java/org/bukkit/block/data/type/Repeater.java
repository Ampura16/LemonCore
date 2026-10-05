package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'delay' 为中继器传递信号的延迟, 即在当前信号传递过来后, 等待多少刻再激活自身以传递信号到下一个方块.
 * <br>
 * 延迟不可低于 {@link #getMinimumDelay()} 也不可高于 {@link #getMaximumDelay()}.
 * <br>
 * 'locked' 值表示中继器是否处于锁存状态.
 * <br>
 * 处于锁存状态的中继器在状态解除之前不会变更它的输出信号. 在游戏中, 从侧面对中继器充能以锁存它.
 * <p>原文：
 * 'delay' is the propagation delay of a repeater, i.e. how many ticks before it
 * will be activated from a current change and propagate it to the next block.
 * <br>
 * Delay may not be lower than {@link #getMinimumDelay()} or higher than
 * {@link #getMaximumDelay()}.
 * <br>
 * 'locked' denotes whether the repeater is in the locked state or not.
 * <br>
 * A locked repeater will not change its output until it is unlocked. In game, a
 * locked repeater is created by having a constant current perpendicularly
 * entering the block.
 */
public interface Repeater extends Directional, Powerable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'delay' 属性的值.
     * <p>原文：
     * Gets the value of the 'delay' property.
     *
     * @return 属性 'delay' 的值
     * <p>原文：the 'delay' value
     */
    int getDelay();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'delay' 属性的值.
     * <p>原文：
     * Sets the value of the 'delay' property.
     *
     * @param delay 新的 'delay' 属性值
     * <p>原文：the new 'delay' value
     */
    void setDelay(int delay);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'delay' 属性所允许的最小值.
     * <p>原文：
     * Gets the minimum allowed value of the 'delay' property.
     *
     * @return 最小 'delay' 属性值
     * <p>原文：the minimum 'delay' value
     */
    int getMinimumDelay();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'delay' 属性所允许的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'delay' property.
     *
     * @return 最大 'delay' 属性值
     * <p>原文：the maximum 'delay' value
     */
    int getMaximumDelay();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'locked' 属性的值.
     * <p>原文：
     * Gets the value of the 'locked' property.
     *
     * @return 属性 'locked' 的值.
     * <p>原文：the 'locked' value
     */
    boolean isLocked();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'locked' 属性的值.
     * <p>原文：
     * Sets the value of the 'locked' property.
     *
     * @param locked 新的 'locked' 属性值
     * <p>原文：the new 'locked' value
     */
    void setLocked(boolean locked);
}
