package org.bukkit.block.data.type;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 此类包含 'north'、'east'、'south'、'west' 高度标志，
 * 用于设置面的高度.
 *
 * 'bottom' 表示这是否是底部方块.
 * <p>原文：
 * This class encompasses the 'north', 'east', 'south', 'west', height flags
 * which are used to set the height of a face.
 *
 * 'bottom' denotes whether this is a bottom block.
 */
public interface MossyCarpet extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'bottom' 属性的值.
     * <p>原文：
     * Gets the value of the 'bottom' property.
     *
     * @return 'bottom' 的值
     * <p>原文：the 'bottom' value
     */
    boolean isBottom();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'bottom' 属性的值.
     * <p>原文：
     * Sets the value of the 'bottom' property.
     *
     * @param bottom 新的 'bottom' 值
     * <p>原文：the new 'bottom' value
     */
    void setBottom(boolean bottom);

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 获取指定面的高度.
     * <p>原文：
     * Gets the height of the specified face.
     *
     * @param face to check
     * @return if face is enabled
     */
    @NotNull
    Wall.Height getHeight(@NotNull BlockFace face);

    /**
     * Set the height of the specified face.
     *
     * @param face to set
     * @param height the height
     */
    void setHeight(@NotNull BlockFace face, @NotNull Wall.Height height);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 面可能具有的不同高度.
     * <p>原文：
     * The different heights a face may have.
     *
     * @deprecated this property is not specific to the MossyCarpet, use
     * {@link org.bukkit.block.data.type.Wall.Height} instead. All references
     * to this enum will be redirected to that enum at runtime.
     */
    @Deprecated
    public enum Height {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 不存在.
         * <p>原文：
         * Not present.
         */
        NONE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 低面存在.
         * <p>原文：
         * Low face present.
         */
        LOW,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 高面存在.
         * <p>原文：
         * Tall face present.
         */
        TALL;
    }
}
