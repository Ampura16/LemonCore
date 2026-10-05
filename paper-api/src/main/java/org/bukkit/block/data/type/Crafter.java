package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'orientation' 是方块的朝向方向.
 * <br>
 * 类似于 {@link Powerable}，'triggered' 表示发射器当前是否被激活.
 * <br>
 * 'crafting' 表示合成器的嘴是否张开且顶部是否发光.
 * <p>原文：
 * 'orientation' is the direction the block is facing.
 * <br>
 * Similar to {@link Powerable}, 'triggered' indicates whether or not the
 * dispenser is currently activated.
 * <br>
 * 'crafting' is whether crafter's mouth is open and top is glowing.
 */
public interface Crafter extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'crafting' 属性的值.
     * <p>原文：
     * Gets the value of the 'crafting' property.
     *
     * @return 'crafting' 的值
     * <p>原文：the 'crafting' value
     */
    boolean isCrafting();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'crafting' 属性的值.
     * <p>原文：
     * Sets the value of the 'crafting' property.
     *
     * @param crafting 新的 'crafting' 值
     * <p>原文：the new 'crafting' value
     */
    void setCrafting(boolean crafting);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'triggered' 属性的值.
     * <p>原文：
     * Gets the value of the 'triggered' property.
     *
     * @return 'triggered' 的值
     * <p>原文：the 'triggered' value
     */
    boolean isTriggered();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'triggered' 属性的值.
     * <p>原文：
     * Sets the value of the 'triggered' property.
     *
     * @param triggered 新的 'triggered' 值
     * <p>原文：the new 'triggered' value
     */
    void setTriggered(boolean triggered);

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
     * 合成器可以朝向的方向.
     * <p>原文：
     * The directions the Crafter can be oriented.
     *
     * @deprecated this property is not specific to the Crafter, use
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
