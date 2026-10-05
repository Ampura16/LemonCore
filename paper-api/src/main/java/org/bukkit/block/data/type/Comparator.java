package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'mode' 值表示该 (红石) 比较器将会以什么模式运算.
 * <p>原文：
 * 'mode' indicates what mode this comparator will operate in.
 */
public interface Comparator extends Directional, Powerable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'mode' 属性的值.
     * <p>原文：
     * Gets the value of the 'mode' property.
     *
     * @return 属性 'mode' 的值
     * <p>原文：the 'mode' value
     */
    @NotNull
    Mode getMode();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'mode' 属性的值.
     * <p>原文：
     * Sets the value of the 'mode' property.
     *
     * @param mode 新的 'mode' 属性值
     * <p>原文：the new 'mode' value
     */
    void setMode(@NotNull Mode mode);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 比较器运算的方式.
     * <p>原文：
     * The mode in which a comparator will operate in.
     */
    public enum Mode {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 默认 (比较器) 模式, 与晶体管类似. 比较器将在后部输入信号强度大于侧部输入信号强度时停止输出信号
         * <p>原文：
         * The default mode, similar to a transistor. The comparator will turn
         * off if either side input is greater than the rear input.
         */
        COMPARE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 减法器模式. 输出信号强度为 max(后部输入信号强度 - max(左侧, 右侧输入信号强度), 0)
         * <p>原文：
         * Alternate subtraction mode. The output signal strength will be equal
         * to max(rear-max(left,right),0).
         */
        SUBTRACT;
    }
}
