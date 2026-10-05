package org.bukkit.block;

import java.util.Collection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表潮涌核心.
 * <p>原文：
 * Represents a captured state of a conduit.
 */
public interface Conduit extends TileState {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此潮涌核心是否处于激活状态.
     * <p>原文：
     * Checks whether or not this conduit is active.
     * <p>
     * A conduit is considered active if there are at least 16 valid frame
     * blocks surrounding it and the conduit is surrounded by a 3x3x3 area of
     * water source blocks (or waterlogged blocks), at which point its animation
     * will activate, start spinning, and apply effects to nearby players.
     *
     * @return 如果激活返回true, 否则返回false
     * <p>原文：true if active, false otherwise
     */
    public boolean isActive();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此潮涌核心是否正在积极寻找附近的敌对生物.
     * <p>原文：
     * Get whether or not this conduit is actively hunting for nearby hostile
     * creatures.
     * <p>
     * A conduit will hunt if it is active (see {@link #isActive()}) and its
     * frame is complete (it is surrounded by at least 42 valid frame blocks).
     * While hunting, the {@link #getTarget()
     * conduit's target}, if within its {@link #getHuntingArea() hunting area},
     * will be damaged every 2 seconds.
     *
     * @return 如果正在狩猎返回true, 否则返回false
     * <p>原文：true if hunting, false otherwise
     */
    public boolean isHunting();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取构成此潮涌核心框架的所有 {@link Block 方块} 的 {@link Collection 集合}.
     * 返回的集合将只包含潮涌核心所需的有效框架类型的方块,
     * <strong>不包括</strong>潮涌核心正在搜索的方块,
     * 这意味着集合的大小将根据调用时潮涌核心周围有多少有效框架而变化.
     * <p>原文：
     * Get a {@link Collection} of all {@link Block Blocks} that make up the
     * frame of this conduit. The returned collection will contain only blocks
     * that match the types required by the conduit to make up a valid frame,
     * <strong>not</strong> the blocks at which the conduit is searching,
     * meaning it will be of variable size depending on how many valid frames
     * are surrounding the conduit at the time of invocation.
     *
     * @return 框架方块
     * <p>原文：the frame blocks
     */
    @NotNull
    public Collection<Block> getFrameBlocks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前围绕潮涌核心的有效框架方块数量.
     * <p>原文：
     * Get the amount of valid frame blocks that are currently surrounding the
     * conduit.
     *
     * @return 框架方块数量
     * <p>原文：the frame block count
     */
    public int getFrameBlockCount();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家将获得潮涌核心益处的范围(以方块为单位).
     * <p>原文：
     * Get the range (measured in blocks) within which players will receive the
     * conduit's benefits.
     *
     * @return 潮涌核心范围
     * <p>原文：the conduit range
     */
    public int getRange();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置潮涌核心的狩猎目标.
     * <p>原文：
     * Set the conduit's hunting target.
     * <p>
     * Note that the value set by this method may be overwritten by the
     * conduit's periodic hunting logic. If the target is ever set to
     * {@code null}, the conduit will continue to look for a new target.
     * Additionally, if the target is set to an entity that does not meet a
     * conduit's hunting conditions (e.g. the entity is not within the
     * {@link #getHuntingArea() hunting area}, has already been killed, etc.)
     * then the passed entity will be ignored and the conduit will also continue
     * to look for a new target.
     *
     * @param target 目标实体, 或null以移除目标
     * <p>原文：the target entity, or null to remove the target
     * @return 如果目标被更改返回true, 如果目标相同返回false
     * <p>原文：true if the target was changed, false if the target was the same
     */
    public boolean setTarget(@Nullable LivingEntity target);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取潮涌核心的狩猎目标.
     * <p>原文：
     * Get the conduit's hunting target.
     *
     * @return 狩猎目标, 如果潮涌核心没有目标则返回null
     * <p>原文：the hunting target, or null if the conduit does not have a target
     */
    @Nullable
    public LivingEntity getTarget();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此潮涌核心是否有活动的(存活的)狩猎目标.
     * <p>原文：
     * Check whether or not this conduit has an active (alive) hunting target.
     *
     * @return 如果有狩猎目标返回true, 否则返回false
     * <p>原文：true if has a hunting target, false otherwise
     */
    public boolean hasTarget();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取潮涌核心将在其中搜索敌对实体作为目标的 {@link BoundingBox 边界框}(相对于真实世界坐标).
     * <p>原文：
     * Get a {@link BoundingBox} (relative to real-world coordinates) in which
     * the conduit will search for hostile entities to target.
     *
     * @return 狩猎区域边界框
     * <p>原文：the hunting area bounding box
     */
    @NotNull
    public BoundingBox getHuntingArea();
}
