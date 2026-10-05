package io.papermc.paper.raytracing;

import java.util.function.Predicate;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 用于以起始位置和方向配置射线追踪的构建器。
 * <p>原文：
 * A builder for configuring a raytrace with a starting location
 * and direction.
 */
@NullMarked
public interface PositionedRayTraceConfigurationBuilder {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置起始位置。
     * <p>原文：
     * Sets the starting location.
     *
     * @param start 新的起始位置
     * <p>原文：the new starting location
     * @return 对此对象的引用
     * <p>原文：a reference to this object
     */
    @Contract(value = "_ -> this", mutates = "this")
    PositionedRayTraceConfigurationBuilder start(Location start);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置方向。
     * <p>原文：
     * Sets the direction.
     *
     * @param direction 新的方向
     * <p>原文：the new direction
     * @return 对此对象的引用
     * <p>原文：a reference to this object
     */
    @Contract(value = "_ -> this", mutates = "this")
    PositionedRayTraceConfigurationBuilder direction(Vector direction);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置最大距离。
     * <p>原文：
     * Sets the maximum distance.
     *
     * @param maxDistance 新的 maxDistance
     * <p>原文：the new maxDistance
     * @return 对此对象的引用
     * <p>原文：a reference to this object
     */
    @Contract(value = "_ -> this", mutates = "this")
    PositionedRayTraceConfigurationBuilder maxDistance(@NonNegative double maxDistance);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置检测方块碰撞时的 FluidCollisionMode。
     * <p>原文：
     * Sets the FluidCollisionMode when looking for block collisions.
     *
     * @param fluidCollisionMode 新的 FluidCollisionMode
     * <p>原文：the new FluidCollisionMode
     * @return 对此对象的引用
     * <p>原文：a reference to this object
     */
    @Contract(value = "_ -> this", mutates = "this")
    PositionedRayTraceConfigurationBuilder fluidCollisionMode(FluidCollisionMode fluidCollisionMode);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置检测方块碰撞时的 BlockCollisionMode。
     * <p>原文：
     * Sets the BlockCollisionMode when looking for block collisions.
     *
     * @param blockCollisionMode 新的 BlockCollisionMode
     * <p>原文：the new BlockCollisionMode
     * @return 对此对象的引用
     * <p>原文：a reference to this object
     */
    @Contract(value = "_ -> this", mutates = "this")
    PositionedRayTraceConfigurationBuilder blockCollisionMode(BlockCollisionMode blockCollisionMode);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置射线追踪在检测方块碰撞时是否应忽略可穿过的方块。
     * <p>原文：
     * Sets whether the raytrace should ignore passable blocks when looking for
     * block collisions.
     *
     * @param ignorePassableBlocks 射线追踪是否应忽略可穿过的方块
     * <p>原文：if the raytrace should ignore passable blocks
     * @return 对此对象的引用
     * <p>原文：a reference to this object
     */
    @Contract(value = "_ -> this", mutates = "this")
    PositionedRayTraceConfigurationBuilder ignorePassableBlocks(boolean ignorePassableBlocks);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置检测实体碰撞时射线追踪的大小。
     * <p>原文：
     * Sets the size of the raytrace when looking for entity collisions.
     *
     * @param raySize 新的射线追踪大小
     * <p>原文：the new raytrace size
     * @return 对此对象的引用
     * <p>原文：a reference to this object
     */
    @Contract(value = "_ -> this", mutates = "this")
    PositionedRayTraceConfigurationBuilder raySize(@NonNegative double raySize);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置检测实体碰撞时的当前实体过滤器。
     * <p>原文：
     * Sets the current entity filter when looking for entity collisions.
     *
     * @param entityFilter 射线可能碰撞的实体的谓词
     * <p>原文：predicate for entities the ray can potentially collide with
     * @return 对此对象的引用
     * <p>原文：a reference to this object
     */
    @Contract(value = "_ -> this", mutates = "this")
    PositionedRayTraceConfigurationBuilder entityFilter(Predicate<? super Entity> entityFilter);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置检测方块碰撞时的当前方块过滤器。
     * <p>原文：
     * Sets the current block filter when looking for block collisions.
     *
     * @param blockFilter 射线可能碰撞的方块的谓词
     * <p>原文：predicate for blocks the ray can potentially collide with
     * @return 对此对象的引用
     * <p>原文：a reference to this object
     */
    @Contract(value = "_ -> this", mutates = "this")
    PositionedRayTraceConfigurationBuilder blockFilter(Predicate<? super Block> blockFilter);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置射线追踪的目标。
     * <p>原文：
     * Sets the targets for the rayTrace.
     *
     * @param first 第一个目标
     * <p>原文：the first target
     * @param others 其他目标
     * <p>原文：the other targets
     * @return 对此对象的引用
     * <p>原文：a reference to this object
     */
    @Contract(value = "_, _ -> this", mutates = "this")
    PositionedRayTraceConfigurationBuilder targets(RayTraceTarget first, RayTraceTarget... others);
}
