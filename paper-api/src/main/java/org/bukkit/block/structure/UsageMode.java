package org.bukkit.block.structure;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表{@link org.bukkit.block.Structure 结构方块}的使用模式.
 * <p>原文：
 * Represents how a {@link org.bukkit.block.Structure} can be used.
 */
public enum UsageMode {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 储存模式.
     * <p>原文：
     * The mode used when saving a structure.
     */
    SAVE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 加载模式.
     * <p>原文：
     * The mode used when loading a structure.
     */
    LOAD,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 角落模式, 用于保存结构时简单计算结构尺寸. 当使用此模式时,
     * 此结构名必须与处于{@link UsageMode#SAVE 储存模式}下的第二个结构方块的结构名相匹配.
     * <p>原文：
     * Used when saving a structure for easy size calculation. When using this
     * mode, the Structure name MUST match the name in the second Structure
     * block that is in {@link UsageMode#SAVE}.
     */
    CORNER,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 数据模式, 用于运行特定的自定义的函数, 这些函数仅可用于某些结构.
     * 当这个函数执行完毕后, 此结构方块将被删除.
     * 所有的数据标签(函数)可在
     * <a href="https://zh.minecraft.wiki/w/%E7%BB%93%E6%9E%84%E6%96%B9%E5%9D%97#%E6%95%B0%E6%8D%AE%E5%80%BC">wiki</a>
     * 中找到.
     * <p>原文：
     * Used to run specific custom functions, which can only be used for certain
     * Structures. The structure block is removed after this function completes.
     * The data tags (functions) can be found on the
     * <a href="https://minecraft.wiki/w/Structure_Block#Data">wiki</a>.
     */
    DATA;
}
