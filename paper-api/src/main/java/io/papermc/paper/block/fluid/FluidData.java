package io.papermc.paper.block.fluid;

import org.bukkit.Fluid;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 处于特定数据状态的流体的表示。该类型不与具体位置关联，因此基本类似于 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：
 * A representation of a fluid in a specific state of data.
 * This type is not linked to a specific location and hence mostly resembles a {@link org.bukkit.block.data.BlockData}.
 */
@NullMarked
public interface FluidData extends Cloneable {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此流体数据的流体类型。
     * <p>原文：
     * Gets the fluid type of this fluid data.
     *
     * @return 流体类型
     * <p>原文：the fluid type
     */
    Fluid getFluidType();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此 FluidData 的副本。
     * <p>原文：
     * Returns a copy of this FluidData.
     *
     * @return 流体数据的副本
     * <p>原文：a copy of the fluid data
     */
    FluidData clone();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 以向量形式计算给定位置处液体的流动方向。
     * <p>
     * 该方法要求传入位置所在的区块已加载。如果调用时该区块未加载，将先加载区块再计算，这可能导致较慢的同步区块加载。
     * <p>原文：
     * Computes the direction of the flow of the liquid at the given location as a vector.
     * <p>
     * This method requires the passed location's chunk to be loaded.
     * If said chunk is not loaded when this method is called, the chunk will first be loaded prior to the computation
     * which leads to a potentially slow sync chunk load.
     *
     * @param location 要检查液体流动的位置
     * <p>原文：- the location to check the liquid flow
     * @return 给定位置的流动方向向量
     * <p>原文：the flow direction vector at the given location
     */
    Vector computeFlowDirection(Location location);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此流体数据所持有的液体高度等级。
     * <p>原文：
     * Returns the level of liquid this fluid data holds.
     *
     * @return 数量，为 0 到 8 之间的整数
     * <p>原文：the amount as an integer, between 0 and 8
     */
    @Range(from = 0, to = 8)
    int getLevel();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 计算世界中流体的高度。
     * <p>
     * 该方法要求传入位置所在的区块已加载。如果调用时该区块未加载，将先加载区块再计算，这可能导致较慢的同步区块加载。
     * <p>原文：
     * Computes the height of the fluid in the world.
     * <p>
     * This method requires the passed location's chunk to be loaded.
     * If said chunk is not loaded when this method is called, the chunk will first be loaded prior to the computation
     * which leads to a potentially slow sync chunk load.
     *
     * @param location 用于检查此流体数据高度的位置。
     * <p>原文：the location at which to check the high of this fluid data.
     * @return float 类型的高度值
     * <p>原文：the height as a float value
     */
    @Range(from = 0, to = 1)
    float computeHeight(Location location);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此流体是否为源方块
     * <p>原文：
     * Returns whether this fluid is a source block
     *
     * @return 如果该流体是源方块则为 true，否则为 false
     * <p>原文：true if the fluid is a source block, false otherwise
     */
    boolean isSource();
}
