package org.bukkit.block.data;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'waterlogged' 表示方块是否处于流体之中.
 * <p>
 * 译注: 值得注意的是, 如果方块只是被水流穿过, 则该值为 false 而非 true.
 * 在该方块上放水或者在充满水的地方 (比如小池, 河流, 海洋) 放置方块, 则该值为 true.
 * <p>原文：
 * 'waterlogged' denotes whether this block has fluid in it.
 */
public interface Waterlogged extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'waterlogged' 属性值.
     * <p>原文：
     * Gets the value of the 'waterlogged' property.
     *
     * @return 方块是否处于流体之中
     * <p>原文：the 'waterlogged' value
     */
    boolean isWaterlogged();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'waterlogged' 属性值.
     * <p>原文：
     * Sets the value of the 'waterlogged' property.
     *
     * @param waterlogged 方块是否处于流体之中
     * <p>原文：the new 'waterlogged' value
     */
    void setWaterlogged(boolean waterlogged);
}
