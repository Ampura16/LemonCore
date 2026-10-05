package com.destroystokyo.paper.entity;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 处理实体的寻路操作
 * <p>原文：
 * Handles pathfinding operations for an Entity
 */
@NullMarked
public interface Pathfinder {

    /**
     * @return 由此寻路器控制的实体
     * <p>原文：The entity that is controlled by this pathfinder
     */
    Mob getEntity();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 指示实体停止尝试导航到其当前期望的位置
     * <p>原文：
     * Instructs the Entity to stop trying to navigate to its current desired location
     */
    void stopPathfinding();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果实体当前正在尝试导航到某个目的地，则返回 true
     * <p>原文：
     * If the entity is currently trying to navigate to a destination, this will return true
     *
     * @return 如果实体正在导航前往目的地则为 true
     * <p>原文：true if the entity is navigating to a destination
     */
    boolean hasPath();

    /**
     * @return 实体尝试导航前往的位置；如果没有目的地则为 null
     * <p>原文：The location the entity is trying to navigate to, or null if there is no destination
     */
    @Nullable PathResult getCurrentPath();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为实体计算导航目的地，但不将其设为当前目标。可用于在设置之前预先计算结果。
     * <p>原文：
     * Calculates a destination for the Entity to navigate to, but does not set it
     * as the current target. Useful for calculating what would happen before setting it.
     *
     * @param loc 要导航前往的位置
     * <p>原文：Location to navigate to
     * @return 此次导航中实体能到达的最近位置；如果无法计算路径则为 null
     * <p>原文：The closest Location the Entity can get to for this navigation, or null if no path could be calculated
     */
    @Nullable PathResult findPath(Location loc);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为实体计算到达目标实体的导航目的地，但不将其设为当前目标。可用于在设置之前预先计算结果。
     * <p>
     * 该 PathResult 的行为受游戏寻路规则约束，寻路可能会自动更新以跟随目标实体。
     * <p>
     * 但这种行为无法保证，取决于游戏自身的行为。
     * <p>原文：
     * Calculates a destination for the Entity to navigate to reach the target entity,
     * but does not set it as the current target.
     * Useful for calculating what would happen before setting it.
     * <p>
     * The behavior of this PathResult is subject to the games pathfinding rules, and may
     * result in the pathfinding automatically updating to follow the target Entity.
     * <p>
     * However, this behavior is not guaranteed, and is subject to the game's behavior.
     *
     * @param target 要导航前往的实体
     * <p>原文：the Entity to navigate to
     * @return 此次导航中实体能到达的最近位置；如果无法计算路径则为 null
     * <p>原文：The closest Location the Entity can get to for this navigation, or null if no path could be calculated
     */
    @Nullable
    default PathResult findPath(LivingEntity target) {
        return this.findPath((Entity) target);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为实体计算到达目标实体的导航目的地，但不将其设为当前目标。可用于在设置之前预先计算结果。
     * <p>
     * 该 PathResult 的行为受游戏寻路规则约束，寻路可能会自动更新以跟随目标实体。
     * <p>
     * 但这种行为无法保证，取决于游戏自身的行为。
     * <p>原文：
     * Calculates a destination for the Entity to navigate to reach the target entity,
     * but does not set it as the current target.
     * Useful for calculating what would happen before setting it.
     * <p>
     * The behavior of this PathResult is subject to the games pathfinding rules, and may
     * result in the pathfinding automatically updating to follow the target Entity.
     * <p>
     * However, this behavior is not guaranteed, and is subject to the game's behavior.
     *
     * @param target 要导航前往的实体
     * <p>原文：the Entity to navigate to
     * @return 此次导航中实体能到达的最近位置；如果无法计算路径则为 null
     * <p>原文：The closest Location the Entity can get to for this navigation, or null if no path could be calculated
     */
    @Nullable PathResult findPath(Entity target);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为实体计算导航目的地，并以默认速度将其设为当前目标。
     * <p>原文：
     * Calculates a destination for the Entity to navigate to, and sets it with default speed
     * as the current target.
     *
     * @param loc 要导航前往的位置
     * <p>原文：Location to navigate to
     * @return 寻路是否成功开始
     * <p>原文：If the pathfinding was successfully started
     */
    default boolean moveTo(Location loc) {
        return this.moveTo(loc, 1);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为实体计算导航目的地，并以期望速度将其设为当前目标。
     * <p>原文：
     * Calculates a destination for the Entity to navigate to, with desired speed
     * as the current target.
     *
     * @param loc 要导航前往的位置
     * <p>原文：Location to navigate to
     * @param speed 导航速度倍率，1 为“正常”
     * <p>原文：Speed multiplier to navigate at, where 1 is 'normal'
     * @return 寻路是否成功开始
     * <p>原文：If the pathfinding was successfully started
     */
    default boolean moveTo(Location loc, double speed) {
        PathResult path = this.findPath(loc);
        return path != null && this.moveTo(path, speed);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为实体计算到达目标实体的导航目的地，并以默认速度设置。
     * <p>
     * 该 PathResult 的行为受游戏寻路规则约束，寻路可能会自动更新以跟随目标实体。
     * <p>
     * 但这种行为无法保证，取决于游戏自身的行为。
     * <p>原文：
     * Calculates a destination for the Entity to navigate to reach the target entity,
     * and sets it with default speed.
     * <p>
     * The behavior of this PathResult is subject to the games pathfinding rules, and may
     * result in the pathfinding automatically updating to follow the target Entity.
     * <p>
     * However, this behavior is not guaranteed, and is subject to the game's behavior.
     *
     * @param target 要导航前往的实体
     * <p>原文：the Entity to navigate to
     * @return 寻路是否成功开始
     * <p>原文：If the pathfinding was successfully started
     */
    default boolean moveTo(LivingEntity target) {
        return this.moveTo(target, 1);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为实体计算到达目标实体的导航目的地，并以指定速度设置。
     * <p>
     * 该 PathResult 的行为受游戏寻路规则约束，寻路可能会自动更新以跟随目标实体。
     * <p>
     * 但这种行为无法保证，取决于游戏自身的行为。
     * <p>原文：
     * Calculates a destination for the Entity to navigate to reach the target entity,
     * and sets it with specified speed.
     * <p>
     * The behavior of this PathResult is subject to the games pathfinding rules, and may
     * result in the pathfinding automatically updating to follow the target Entity.
     * <p>
     * However, this behavior is not guaranteed, and is subject to the game's behavior.
     *
     * @param target 要导航前往的实体
     * <p>原文：the Entity to navigate to
     * @param speed 导航速度倍率，1 为“正常”
     * <p>原文：Speed multiplier to navigate at, where 1 is 'normal'
     * @return 寻路是否成功开始
     * <p>原文：If the pathfinding was successfully started
     */
    default boolean moveTo(LivingEntity target, double speed) {
        return this.moveTo((Entity) target, speed);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为实体计算到达目标实体的导航目的地，并以默认速度设置。
     * <p>
     * 该 PathResult 的行为受游戏寻路规则约束，寻路可能会自动更新以跟随目标实体。
     * <p>
     * 但这种行为无法保证，取决于游戏自身的行为。
     * <p>原文：
     * Calculates a destination for the Entity to navigate to reach the target entity,
     * and sets it with default speed.
     * <p>
     * The behavior of this PathResult is subject to the games pathfinding rules, and may
     * result in the pathfinding automatically updating to follow the target Entity.
     * <p>
     * However, this behavior is not guaranteed, and is subject to the game's behavior.
     *
     * @param target 要导航前往的实体
     * <p>原文：the Entity to navigate to
     * @return 寻路是否成功开始
     * <p>原文：If the pathfinding was successfully started
     */
    default boolean moveTo(Entity target) {
        return this.moveTo(target, 1);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为实体计算到达目标实体的导航目的地，并以指定速度设置。
     * <p>
     * 该 PathResult 的行为受游戏寻路规则约束，寻路可能会自动更新以跟随目标实体。
     * <p>
     * 但这种行为无法保证，取决于游戏自身的行为。
     * <p>原文：
     * Calculates a destination for the Entity to navigate to reach the target entity,
     * and sets it with specified speed.
     * <p>
     * The behavior of this PathResult is subject to the games pathfinding rules, and may
     * result in the pathfinding automatically updating to follow the target Entity.
     * <p>
     * However, this behavior is not guaranteed, and is subject to the game's behavior.
     *
     * @param target 要导航前往的实体
     * <p>原文：the Entity to navigate to
     * @param speed 导航速度倍率，1 为“正常”
     * <p>原文：Speed multiplier to navigate at, where 1 is 'normal'
     * @return 寻路是否成功开始
     * <p>原文：If the pathfinding was successfully started
     */
    default boolean moveTo(Entity target, double speed) {
        PathResult path = this.findPath(target);
        return path != null && this.moveTo(path, speed);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取先前寻路计算的结果，并以默认速度将其设为活动寻路。
     * <p>原文：
     * Takes the result of a previous pathfinding calculation and sets it
     * as the active pathfinding with default speed.
     *
     * @param path 要开始跟随的 Path
     * <p>原文：The Path to start following
     * @return 寻路是否成功开始
     * <p>原文：If the pathfinding was successfully started
     */
    default boolean moveTo(PathResult path) {
        return this.moveTo(path, 1);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取先前寻路计算的结果，并将其设为活动寻路，
     * <p>原文：
     * Takes the result of a previous pathfinding calculation and sets it
     * as the active pathfinding,
     *
     * @param path 要开始跟随的 Path
     * <p>原文：The Path to start following
     * @param speed 导航速度倍率，1 为“正常”
     * <p>原文：Speed multiplier to navigate at, where 1 is 'normal'
     * @return 寻路是否成功开始
     * <p>原文：If the pathfinding was successfully started
     */
    boolean moveTo(PathResult path, double speed);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此寻路器是否允许穿过关闭的门。
     * <p>原文：
     * Checks if this pathfinder allows passing through closed doors.
     *
     * @return 此寻路器是否允许穿过关闭的门
     * <p>原文：if this pathfinder allows passing through closed doors
     */
    boolean canOpenDoors();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此寻路器是否可以穿过关闭的门
     * <p>原文：
     * Allows this pathfinder to pass through closed doors, or not
     *
     * @param canOpenDoors 生物能否穿过关闭的门
     * <p>原文：if the mob can pass through closed doors, or not
     */
    void setCanOpenDoors(boolean canOpenDoors);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此寻路器是否允许穿过打开的门。
     * <p>原文：
     * Checks if this pathfinder allows passing through open doors.
     *
     * @return 此寻路器是否允许穿过打开的门
     * <p>原文：if this pathfinder allows passing through open doors
     */
    boolean canPassDoors();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此寻路器是否可以穿过打开的门
     * <p>原文：
     * Allows this pathfinder to pass through open doors, or not
     *
     * @param canPassDoors 生物能否穿过打开的门
     * <p>原文：if the mob can pass through open doors, or not
     */
    void setCanPassDoors(boolean canPassDoors);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此寻路器是否假定生物可以漂浮
     * <p>原文：
     * Checks if this pathfinder assumes that the mob can float
     *
     * @return 此寻路器是否假定生物可以漂浮
     * <p>原文：if this pathfinder assumes that the mob can float
     */
    boolean canFloat();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此寻路器是否假定生物可以漂浮
     * <p>原文：
     * Makes this pathfinder assume that the mob can float, or not
     *
     * @param canFloat 生物能否漂浮
     * <p>原文：if the mob can float, or not
     */
    void setCanFloat(boolean canFloat);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示寻路计算的结果
     * <p>原文：
     * Represents the result of a pathfinding calculation
     */
    interface PathResult {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 为到达目标位置而沿路径行进的当前全部已计算路径点
         * <p>
         * 会返回实体已经经过的点，参见 {@link #getNextPointIndex()}
         * <p>原文：
         * All currently calculated points to follow along the path to reach the destination location
         * <p>
         * Will return points the entity has already moved past, see {@link #getNextPointIndex()}
         *
         * @return 路径点列表
         * <p>原文：List of points
         */
        List<Location> getPoints();

        /**
         * @return 返回实体正尝试到达的当前路径点在 {@link #getPoints()} 返回的路径点中的索引。如果寻路已完成，该值将大于 {@link #getPoints()} 的最大索引。
         * <p>原文：Returns the index of the current point along the points returned in {@link #getPoints()} the entity
         * is trying to reach. This value will be higher than the maximum index of {@link #getPoints()} if this path finding is done.
         */
        int getNextPointIndex();

        /**
         * @return 实体尝试到达的路径点中的下一个位置；如果没有下一个点则为 null
         * <p>原文：The next location in the path points the entity is trying to reach, or null if there is no next point
         */
        @Nullable Location getNextPoint();

        /**
         * @return 路径能到达的离目标位置最近的点
         * <p>原文：The closest point the path can get to the target location
         */
        @Nullable Location getFinalPoint();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 检查最终路径点是否可达
         * <p>原文：
         * Checks whether the final point can be reached
         *
         * @return 能否到达终点
         * <p>原文：whether the final point can be reached
         * @see #getFinalPoint()
         */
        boolean canReachFinalPoint();
    }
}
