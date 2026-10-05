package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'mode' 值代表结构方块可执行的不同行为模式.
 * <p>原文：
 * 'mode' represents the different modes in which this structure block may
 * operate.
 */
public interface StructureBlock extends BlockData {

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
     * 结构方块的执行模式.
     * <p>原文：
     * Operating mode of a structure block.
     */
    public enum Mode {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 将选区保存为一个结构
         * <p>原文：
         * Allows selection and saving of a structure.
         */
        SAVE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 加载一个结构
         * <p>原文：
         * Allows loading of a structure.
         */
        LOAD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 用于检测结构中两个相对的顶点
         * <p>原文：
         * Used for detection of two opposite corners of a structure.
         */
        CORNER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在世界生成中, 自身被移除之前运行一个 (指定相关结构的) 自定义函数.
         * <p>原文：
         * Dummy block used to run a custom function during world generation
         * before being removed.
         */
        DATA;
    }
}
