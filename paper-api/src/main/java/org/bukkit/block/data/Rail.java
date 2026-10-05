package org.bukkit.block.data;

import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'shape' 数据值表示此铁轨的形态 (铁轨连接方向).
 * <br>
 * 某些铁轨可能不能够摆放成所有的形态, 使用
 * {@link #getShapes()} 来获悉此铁轨可以怎样摆放.
 * <p>原文：
 * 'shape' represents the current layout of a minecart rail.
 * <br>
 * Some types of rail may not be able to be laid out in all shapes, use
 * {@link #getShapes()} to get those applicable to this block.
 */
public interface Rail extends Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'shape' 的数据值.
     * <p>原文：
     * Gets the value of the 'shape' property.
     *
     * @return 铁轨的形态
     * <p>原文：the 'shape' value
     */
    @NotNull
    Shape getShape();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'shape' 的数据值.
     * <p>原文：
     * Sets the value of the 'shape' property.
     *
     * @param shape 铁轨的形态
     * <p>原文：the new 'shape' value
     */
    void setShape(@NotNull Shape shape);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取适用于此铁轨的形态.
     * <p>原文：
     * Gets the shapes which are applicable to this block.
     *
     * @return 适用于此铁轨的形态
     * <p>原文：the allowed 'shape' values
     */
    @NotNull
    Set<Shape> getShapes();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 铁轨方块可使用的不同形态。
     * <p>原文：
     * The different types of shapes a rail block can occupy.
     */
    public enum Shape {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 铁轨沿北/南（Z轴）方向平铺。
         * <p>原文：
         * The rail runs flat along the north/south (Z) axis.
         */
        NORTH_SOUTH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 铁轨沿东/西（X轴）方向平铺。
         * <p>原文：
         * The rail runs flat along the east/west (X) axis.
         */
        EAST_WEST,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 铁轨向东（正X方向）上升。
         * <p>原文：
         * The rail ascends in the east (positive X) direction.
         */
        ASCENDING_EAST,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 铁轨向西（负X方向）上升。
         * <p>原文：
         * The rail ascends in the west (negative X) direction.
         */
        ASCENDING_WEST,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 铁轨向北（负Z方向）上升。
         * <p>原文：
         * The rail ascends in the north (negative Z) direction.
         */
        ASCENDING_NORTH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 铁轨向南（正Z方向）上升。
         * <p>原文：
         * The rail ascends in the south (positive Z) direction.
         */
        ASCENDING_SOUTH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 铁轨形成连接方块南面和东面的曲线。
         * <p>原文：
         * The rail forms a curve connecting the south and east faces of the
         * block.
         */
        SOUTH_EAST,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 铁轨形成连接方块南面和西面的曲线。
         * <p>原文：
         * The rail forms a curve connecting the south and west faces of the
         * block.
         */
        SOUTH_WEST,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 铁轨形成连接方块北面和西面的曲线。
         * <p>原文：
         * The rail forms a curve connecting the north and west faces of the
         * block.
         */
        NORTH_WEST,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 铁轨形成连接方块北面和东面的曲线。
         * <p>原文：
         * The rail forms a curve connecting the north and east faces of the
         * block.
         */
        NORTH_EAST;
    }
}
