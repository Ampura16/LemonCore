package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'orientation' 指拼图方块的朝向.
 * <p>原文：
 * 'orientation' is the direction the block is facing.
 */
public interface Jigsaw extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 获取 'orientation' 属性的值.
     * <p>原文：
     * Gets the value of the 'orientation' property.
     *
     * @return the 'orientation' value
     */
    @NotNull
    org.bukkit.block.Orientation getOrientation();

    /**
     * Sets the value of the 'orientation' property.
     *
     * @param orientation the new 'orientation' value
     */
    void setOrientation(@NotNull org.bukkit.block.Orientation orientation);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 拼图方块可朝向的方向.
     * <p>原文：
     * The directions the Jigsaw can be oriented.
     *
     * @deprecated this property is not specific to the Jigsaw, use
     * {@link org.bukkit.block.Orientation} instead. All references
     * to this enum will be redirected to that enum at runtime.
     */
    @Deprecated
    public enum Orientation {

        DOWN_EAST,
        DOWN_NORTH,
        DOWN_SOUTH,
        DOWN_WEST,
        UP_EAST,
        UP_NORTH,
        UP_SOUTH,
        UP_WEST,
        WEST_UP,
        EAST_UP,
        NORTH_UP,
        SOUTH_UP;
    }
}
