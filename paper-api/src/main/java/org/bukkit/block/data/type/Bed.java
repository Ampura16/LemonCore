package org.bukkit.block.data.type;

import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Directional;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 与 {@link Bisected} 相似, 'part' 值表示该方块对应的是床的哪一部分.
 * <br>
 * 'occupied' 值是一个用于检查当前是否有玩家睡在该床方块上的快速标识.
 * <p>原文：
 * Similar to {@link Bisected}, 'part' denotes which half of the bed this block
 * corresponds to.
 * <br>
 * 'occupied' property is a quick flag to check if a player is currently
 * sleeping in this bed block.
 */
public interface Bed extends Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'part' 属性的值.
     * <p>原文：
     * Gets the value of the 'part' property.
     *
     * @return 属性 'part' 的值
     * <p>原文：the 'part' value
     */
    @NotNull
    Part getPart();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'part' 属性的值.
     * <p>原文：
     * Sets the value of the 'part' property.
     *
     * @param part 新的 'part' 属性值
     * <p>原文：the new 'part' value
     */
    void setPart(@NotNull Part part);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'occupied' 属性的值.
     * <p>原文：
     * Gets the value of the 'occupied' property.
     *
     * @return 属性 'occupied' 的值
     * <p>原文：the 'occupied' value
     */
    boolean isOccupied();

    // Paper start
    /**
     * Sets the value of the 'occupied' property.
     *
     * @param occupied the new 'occupied' value
     */
    void setOccupied(boolean occupied);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将床水平平分为两半.
     * <p>原文：
     * Horizontal half of a bed.
     */
    public enum Part {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 床的上半部分, 包含枕头的那部分
         * <p>原文：
         * The head is the upper part of the bed containing the pillow.
         */
        HEAD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 床的下半部分
         * <p>原文：
         * The foot is the lower half of the bed.
         */
        FOOT;
    }
}
