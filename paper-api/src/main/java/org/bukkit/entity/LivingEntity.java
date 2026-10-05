package org.bukkit.entity;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import io.papermc.paper.world.damagesource.CombatTracker;
import io.papermc.paper.world.damagesource.FallLocationType;
import net.kyori.adventure.key.Key;
import org.bukkit.Color;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attributable;
import org.bukkit.block.Block;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个生物实体，比如一只怪物或一名玩家.
 * <p>原文：
 * Represents a living entity, such as a monster or player
 */
public interface LivingEntity extends Attributable, Damageable, ProjectileSource, io.papermc.paper.entity.Frictional { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取生物实体眼睛离脚高度.
     * <p>原文：
     * Gets the height of the living entity's eyes above its Location.
     *
     * @return 生物实体眼睛离脚高度
     * <p>原文：height of the living entity's eyes above its location
     */
    public double getEyeHeight();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取生物实体眼睛离脚高度.
     * <p>原文：
     * Gets the height of the living entity's eyes above its Location.
     *
     * @param ignorePose 若为true则会无视姿势改变的效果,例如潜行和滑翔
     * <p>原文：if set to true, the effects of pose changes, eg
     *     sneaking and gliding will be ignored
     * @return 生物实体眼睛离脚高度
     * <p>原文：height of the living entity's eyes above its location
     */
    public double getEyeHeight(boolean ignorePose);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取生物实体眼睛的详细方位的Location对象.
     * <p>原文：
     * Get a Location detailing the current eye position of the living entity.
     *
     * @return 生物实体眼睛的详细方位的Location对象
     * <p>原文：a location at the eyes of the living entity
     */
    @NotNull
    public Location getEyeLocation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取沿生物实体视线上的所有方块.
     * <p>
     * 这个列表包含生物实体眼睛到目标位置的所有方块.
     * 本方法认为所有方块体积为1x1x1.
     * <p>原文：
     * Gets all blocks along the living entity's line of sight.
     * <p>
     * This list contains all blocks from the living entity's eye position to
     * target inclusive. This method considers all blocks as 1x1x1 in size.
     *
     * @param transparent 将包含的所有透明方块的ID的Set（设置为null则只包含空气）
     * <p>原文：Set containing all transparent block Materials (set to
     *     null for only air)
     * @param maxDistance 扫描的最大距离（可能被服务器限制，但至少为100个方块）
     * <p>原文：this is the maximum distance to scan (may be limited
     *     by server by at least 100 blocks, no less)
     * @return 包含沿生物实体视线上的所有方块的列表
     * <p>原文：list containing all blocks along the living entity's line of
     *     sight
     */
    @NotNull
    public List<Block> getLineOfSight(@Nullable Set<Material> transparent, int maxDistance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取生物实体的目标方块.
     * <p>
     * 本方法认为所有方块体积为1x1x1. To take exact block
     * collision shapes into account, see {@link #getTargetBlockExact(int,
     * FluidCollisionMode)}.
     * <p>原文：
     * Gets the block that the living entity has targeted.
     * <p>
     * This method considers all blocks as 1x1x1 in size. To take exact block
     * collision shapes into account, see {@link #getTargetBlockExact(int,
     * FluidCollisionMode)}.
     *
     * @param transparent 将包含的所有透明方块的ID的Set（设置为null则只包含空气）
     * <p>原文：Set containing all transparent block Materials (set to
     *     null for only air)
     * @param maxDistance 扫描的最大距离（可能被服务器限制，但至少为100个方块）
     * <p>原文：this is the maximum distance to scan (may be limited
     *     by server by at least 100 blocks, no less)
     * @return block 生物实体的目标方块
     * <p>原文：block that the living entity has targeted
     */
    @NotNull
    public Block getTargetBlock(@Nullable Set<Material> transparent, int maxDistance);

    // Paper start
    /**
     * Gets the block that the living entity has targeted, ignoring fluids
     *
     * @param maxDistance this is the maximum distance to scan
     * @return block that the living entity has targeted,
     *     or null if no block is within maxDistance
     * @deprecated use {@link #getTargetBlockExact(int)}
     */
    @Deprecated(forRemoval = true, since = "1.19.3")
    @Nullable
    public default Block getTargetBlock(int maxDistance) {
        return getTargetBlock(maxDistance, com.destroystokyo.paper.block.TargetBlockInfo.FluidMode.NEVER);
    }

    /**
     * Gets the block that the living entity has targeted
     *
     * @param maxDistance this is the maximum distance to scan
     * @param fluidMode whether to check fluids or not
     * @return block that the living entity has targeted,
     *     or null if no block is within maxDistance
     * @deprecated use {@link #getTargetBlockExact(int, FluidCollisionMode)}
     */
    @Deprecated(forRemoval = true, since = "1.19.3")
    @Nullable
    public Block getTargetBlock(int maxDistance, @NotNull com.destroystokyo.paper.block.TargetBlockInfo.FluidMode fluidMode);

    /**
     * Gets the blockface of that block that the living entity has targeted, ignoring fluids
     *
     * @param maxDistance this is the maximum distance to scan
     * @return blockface of the block that the living entity has targeted,
     *     or null if no block is targeted
     */
    @Nullable
    public default org.bukkit.block.BlockFace getTargetBlockFace(int maxDistance) {
        return getTargetBlockFace(maxDistance, org.bukkit.FluidCollisionMode.NEVER);
    }

    /**
     * Gets the blockface of that block that the living entity has targeted
     *
     * @param maxDistance this is the maximum distance to scan
     * @param fluidMode whether to check fluids or not
     * @return blockface of the block that the living entity has targeted,
     *     or null if no block is targeted
     * @deprecated use {@link #getTargetBlockFace(int, FluidCollisionMode)}
     */
    @Deprecated(forRemoval = true, since = "1.19.3")
    @Nullable
    public org.bukkit.block.BlockFace getTargetBlockFace(int maxDistance, @NotNull com.destroystokyo.paper.block.TargetBlockInfo.FluidMode fluidMode);

    /**
     * Gets the blockface of that block that the living entity has targeted
     *
     * @param maxDistance this is the maximum distance to scan
     * @param fluidMode whether to check fluids or not
     * @return blockface of the block that the living entity has targeted,
     *     or null if no block is targeted
     */
    @Nullable
    public org.bukkit.block.BlockFace getTargetBlockFace(int maxDistance, @NotNull FluidCollisionMode fluidMode);

    /**
     * Gets information about the block the living entity has targeted, ignoring fluids
     *
     * @param maxDistance this is the maximum distance to scan
     * @return TargetBlockInfo about the block the living entity has targeted,
     *     or null if no block is targeted
     * @deprecated use {@link #rayTraceBlocks(double)}
     */
    @Deprecated(forRemoval = true, since = "1.19.3")
    @Nullable
    public default com.destroystokyo.paper.block.TargetBlockInfo getTargetBlockInfo(int maxDistance) {
        return getTargetBlockInfo(maxDistance, com.destroystokyo.paper.block.TargetBlockInfo.FluidMode.NEVER);
    }

    /**
     * Gets information about the block the living entity has targeted
     *
     * @param maxDistance this is the maximum distance to scan
     * @param fluidMode whether to check fluids or not
     * @return TargetBlockInfo about the block the living entity has targeted,
     *     or null if no block is targeted
     * @deprecated use {@link #rayTraceBlocks(double, FluidCollisionMode)}
     */
    @Deprecated(forRemoval = true, since = "1.19.3")
    @Nullable
    public com.destroystokyo.paper.block.TargetBlockInfo getTargetBlockInfo(int maxDistance, @NotNull com.destroystokyo.paper.block.TargetBlockInfo.FluidMode fluidMode);

    /**
     * Gets information about the entity being targeted
     *
     * @param maxDistance this is the maximum distance to scan
     * @return entity being targeted, or null if no entity is targeted
     */
    @Nullable
    public default Entity getTargetEntity(int maxDistance) {
        return getTargetEntity(maxDistance, false);
    }

    /**
     * Gets information about the entity being targeted
     *
     * @param maxDistance this is the maximum distance to scan
     * @param ignoreBlocks true to scan through blocks
     * @return entity being targeted, or null if no entity is targeted
     */
    @Nullable
    public Entity getTargetEntity(int maxDistance, boolean ignoreBlocks);

    /**
     * Gets information about the entity being targeted
     *
     * @param maxDistance this is the maximum distance to scan
     * @return TargetEntityInfo about the entity being targeted,
     *     or null if no entity is targeted
     * @deprecated use {@link #rayTraceEntities(int)}
     */
    @Deprecated(forRemoval = true, since = "1.19.3")
    @Nullable
    public default com.destroystokyo.paper.entity.TargetEntityInfo getTargetEntityInfo(int maxDistance) {
        return getTargetEntityInfo(maxDistance, false);
    }

    /**
     * Gets information about the entity being targeted
     *
     * @param maxDistance this is the maximum distance to scan
     * @return RayTraceResult about the entity being targeted,
     *     or null if no entity is targeted
     */
    @Nullable
    default RayTraceResult rayTraceEntities(int maxDistance) {
        return this.rayTraceEntities(maxDistance, false);
    }

    /**
     * Gets information about the entity being targeted
     *
     * @param maxDistance this is the maximum distance to scan
     * @param ignoreBlocks true to scan through blocks
     * @return TargetEntityInfo about the entity being targeted,
     *     or null if no entity is targeted
     * @deprecated use {@link #rayTraceEntities(int, boolean)}
     */
    @Deprecated(forRemoval = true, since = "1.19.3")
    @Nullable
    public com.destroystokyo.paper.entity.TargetEntityInfo getTargetEntityInfo(int maxDistance, boolean ignoreBlocks);

    /**
     * Gets information about the entity being targeted
     *
     * @param maxDistance this is the maximum distance to scan
     * @param ignoreBlocks true to scan through blocks
     * @return RayTraceResult about the entity being targeted,
     *     or null if no entity is targeted
     */
    @Nullable
    RayTraceResult rayTraceEntities(int maxDistance, boolean ignoreBlocks);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取沿生物实体视线上最后两个方块.
     * <p>
     * 目标方块将是列表中最后的方块. 本方法认为所有方块体积为1x1x1.
     * <p>原文：
     * Gets the last two blocks along the living entity's line of sight.
     * <p>
     * The target block will be the last block in the list. This method
     * considers all blocks as 1x1x1 in size.
     *
     * @param transparent 将包含的所有透明方块的ID的Set（设置为null则只包含空气）
     * <p>原文：Set containing all transparent block Materials (set to
     *     null for only air)
     * @param maxDistance 扫描的最大距离。可能被服务器限制，但不会低于100个方块
     * <p>原文：this is the maximum distance to scan. This may be
     *     further limited by the server, but never to less than 100 blocks
     * @return 包含沿生物实体视线上最后两个方块的列表
     * <p>原文：list containing the last 2 blocks along the living entity's
     *     line of sight
     */
    @NotNull
    public List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> transparent, int maxDistance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该生物实体瞄准的方块。
     * <p>
     * 这会考虑方块的精确碰撞形状。流体将被忽略。
     * <p>
     * 此操作可能会导致区块加载！某些实现可能会对最大距离施加人工限制。
     * <p>原文：
     * Gets the block that the living entity has targeted.
     * <p>
     * This takes the blocks' precise collision shapes into account. Fluids are
     * ignored.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param maxDistance 扫描的最大距离
     * <p>原文：the maximum distance to scan
     * @return 生物实体瞄准的方块
     * <p>原文：block that the living entity has targeted
     * @see #getTargetBlockExact(int, org.bukkit.FluidCollisionMode)
     */
    @Nullable
    default Block getTargetBlockExact(int maxDistance) {
        return this.getTargetBlockExact(maxDistance, FluidCollisionMode.NEVER);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该生物实体瞄准的方块。
     * <p>
     * 这会考虑方块的精确碰撞形状。
     * <p>
     * 此操作可能会导致区块加载！某些实现可能会对最大距离施加人工限制。
     * <p>原文：
     * Gets the block that the living entity has targeted.
     * <p>
     * This takes the blocks' precise collision shapes into account.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param maxDistance 扫描的最大距离
     * <p>原文：the maximum distance to scan
     * @param fluidCollisionMode 流体碰撞模式
     * <p>原文：the fluid collision mode
     * @return 生物实体瞄准的方块
     * <p>原文：block that the living entity has targeted
     * @see #rayTraceBlocks(double, FluidCollisionMode)
     */
    @Nullable
    public Block getTargetBlockExact(int maxDistance, @NotNull FluidCollisionMode fluidCollisionMode);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行一次射线追踪，提供有关目标方块的信息。
     * <p>
     * 这会考虑方块的精确碰撞形状。流体将被忽略。
     * <p>
     * 此操作可能会导致区块加载！某些实现可能会对最大距离施加人工限制。
     * <p>原文：
     * Performs a ray trace that provides information on the targeted block.
     * <p>
     * This takes the blocks' precise collision shapes into account. Fluids are
     * ignored.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param maxDistance 扫描的最大距离
     * <p>原文：the maximum distance to scan
     * @return 目标方块的信息，如果范围内没有目标方块则返回 <code>null</code>
     * <p>原文：information on the targeted block, or <code>null</code> if there
     *     is no targeted block in range
     * @see #rayTraceBlocks(double, FluidCollisionMode)
     */
    @Nullable
    default RayTraceResult rayTraceBlocks(double maxDistance) {
        return this.rayTraceBlocks(maxDistance, FluidCollisionMode.NEVER);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行一次射线追踪，提供有关目标方块的信息。
     * <p>
     * 这会考虑方块的精确碰撞形状。
     * <p>
     * 此操作可能会导致区块加载！某些实现可能会对最大距离施加人工限制。
     * <p>原文：
     * Performs a ray trace that provides information on the targeted block.
     * <p>
     * This takes the blocks' precise collision shapes into account.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param maxDistance 扫描的最大距离
     * <p>原文：the maximum distance to scan
     * @param fluidCollisionMode 流体碰撞模式
     * <p>原文：the fluid collision mode
     * @return 目标方块的信息，如果范围内没有目标方块则返回 <code>null</code>
     * <p>原文：information on the targeted block, or <code>null</code> if there
     *     is no targeted block in range
     * @see World#rayTraceBlocks(Location, Vector, double, FluidCollisionMode)
     */
    @Nullable
    public RayTraceResult rayTraceBlocks(double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回生物实体剩余的氧气值，单位为tick.
     * <p>原文：
     * Returns the amount of air that the living entity has remaining, in
     * ticks.
     *
     * @return 剩余的氧气值
     * <p>原文：amount of air remaining
     */
    public int getRemainingAir();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置生物实体剩余的氧气值，单位为tick.
     * <p>原文：
     * Sets the amount of air that the living entity has remaining, in ticks.
     *
     * @param ticks 剩余的氧气值
     * <p>原文：amount of air remaining
     */
    public void setRemainingAir(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回生物实体所能拥有的氧气最大值，单位为tick.
     * <p>原文：
     * Returns the maximum amount of air the living entity can have, in ticks.
     *
     * @return 氧气最大值
     * <p>原文：maximum amount of air
     */
    public int getMaximumAir();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置生物实体所能拥有的氧气最大值，单位为tick.
     * <p>原文：
     * Sets the maximum amount of air the living entity can have, in ticks.
     *
     * @param ticks 氧气最大值
     * <p>原文：maximum amount of air
     */
    public void setMaximumAir(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家正在使用的物品（吃食物、拉弓、格挡等）。
     * <p>原文：
     * Gets the item that the player is using (eating food, drawing back a bow,
     * blocking, etc.)
     *
     * @return 玩家正在使用的物品，如果未使用物品则返回 null
     * <p>原文：the item being used by the player, or null if they are not using
     * an item
     * @deprecated Use {@link #getActiveItem()}
     */
    @Nullable
    @Deprecated(forRemoval = true, since = "1.20.4") // Paper
    public ItemStack getItemInUse();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前物品使用的剩余 tick 数。
     * <p>原文：
     * Gets the number of ticks remaining for the current item's usage.
     *
     * @return 剩余的 tick 数
     * <p>原文：The number of ticks remaining
     * @deprecated use {@link #getActiveItemRemainingTime()}
     */
    @Deprecated(forRemoval = true, since = "1.20.4") // Paper
    public int getItemInUseTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前物品使用的剩余 tick 数。
     * 适用于需要时间使用的物品，如吃食物、拉弓或投掷三叉戟。
     * <p>原文：
     * Sets the number of ticks that remain for the current item's usage.
     * Applies to items that take time to use, like eating food, drawing a bow,
     * or throwing a trident.
     *
     * @param ticks 剩余的 tick 数
     * <p>原文：The number of ticks remaining
     * @deprecated use {@link #setActiveItemRemainingTime(int)}
     */
    @Deprecated(forRemoval = true, since = "1.20.4") // Paper
    public void setItemInUseTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取箭矢离开实体身体的剩余时间（以 tick 为单位）。
     * <p>原文：
     * Gets the time in ticks until the next arrow leaves the entity's body.
     *
     * @return 箭矢离开的 tick 数
     * <p>原文：ticks until arrow leaves
     */
    public @NonNegative int getArrowCooldown();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置箭矢离开实体身体的剩余时间（以 tick 为单位）。
     * <p>原文：
     * Sets the time in ticks until the next arrow leaves the entity's body.
     * <p>
     * A value of 0 will cause the server to re-calculate the time on the next tick.
     *
     * @param ticks 箭矢离开的时间
     * <p>原文：time until arrow leaves
     */
    public void setArrowCooldown(@NonNegative int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体身体内的箭矢数量。
     * <p>原文：
     * Gets the amount of arrows in an entity's body.
     *
     * @return 身体内的箭矢数量
     * <p>原文：amount of arrows in body
     */
    public @NonNegative int getArrowsInBody();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体身体内的箭矢数量。
     * <p>原文：
     * Set the amount of arrows in the entity's body.
     * <p>
     * Does not fire the {@link org.bukkit.event.entity.ArrowBodyCountChangeEvent}.
     *
     * @param count 实体身体内的箭矢数量
     * <p>原文：amount of arrows in entity's body
     */
    default void setArrowsInBody(final @NonNegative int count) {
        this.setArrowsInBody(count, false);
    }

    /**
     * Set the amount of arrows in the entity's body.
     *
     * @param count amount of arrows in entity's body
     * @param fireEvent whether to fire the {@link org.bukkit.event.entity.ArrowBodyCountChangeEvent} event
     */
    void setArrowsInBody(@NonNegative int count, boolean fireEvent); // Paper

    /**
     * Sets the amount of ticks before the next arrow gets removed from the entities body.
     * <p>
     * A value of 0 will cause the server to re-calculate the amount of ticks on the next tick.
     *
     * @param ticks Amount of ticks
     * @deprecated use {@link #setArrowCooldown(int)}
     */
    @Deprecated(since = "1.21.10")
    default void setNextArrowRemoval(@NonNegative int ticks) {
        this.setArrowCooldown(ticks);
    }

    /**
     * Gets the amount of ticks before the next arrow gets removed from the entities body.
     *
     * @return ticks Amount of ticks
     * @deprecated use {@link #getArrowCooldown()}
     */
    @Deprecated(since = "1.21.10")
    default @NonNegative int getNextArrowRemoval() {
        return this.getArrowCooldown();
    }

    /**
     * Gets the time in ticks until the next bee stinger leaves the entity's body.
     *
     * @return ticks until bee stinger leaves
     */
    public @NonNegative int getBeeStingerCooldown();

    /**
     * Sets the time in ticks until the next stinger leaves the entity's body.
     * <p>
     * A value of 0 will cause the server to re-calculate the time on the next tick.
     *
     * @param ticks time until bee stinger leaves
     */
    public void setBeeStingerCooldown(@NonNegative int ticks);

    /**
     * Gets the amount of bee stingers in an entity's body.
     *
     * @return amount of bee stingers in body
     */
    public @NonNegative int getBeeStingersInBody();

    /**
     * Set the amount of bee stingers in the entity's body.
     *
     * @param count amount of bee stingers in entity's body
     */
    public void setBeeStingersInBody(@NonNegative int count);

    /**
     * Sets the amount of ticks before the next bee stinger gets removed from the entities body.
     * <p>
     * A value of 0 will cause the server to re-calculate the amount of ticks on the next tick.
     *
     * @param ticks Amount of ticks
     * @deprecated use {@link #setBeeStingerCooldown(int)}
     */
    @Deprecated(since = "1.21.10")
    default void setNextBeeStingerRemoval(@NonNegative int ticks) {
        this.setBeeStingerCooldown(ticks);
    }

    /**
     * Gets the amount of ticks before the next bee stinger gets removed from the entities body.
     *
     * @return ticks Amount of ticks
     * @deprecated use {@link #getBeeStingerCooldown()}
     */
    @Deprecated(since = "1.21.10")
    default @NonNegative int getNextBeeStingerRemoval() {
        return this.getBeeStingerCooldown();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回生物实体当前最大无伤害时间，单位为tick.
     * <p>
     * 即生物实体不会受到伤害的最大持续时间.
     * <p>原文：
     * Returns the living entity's current maximum no damage ticks.
     * <p>
     * This is the maximum duration in which the living entity will not take
     * damage.
     *
     * @return 最大无伤害时间，单位为tick
     * <p>原文：maximum no damage ticks
     */
    public int getMaximumNoDamageTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置生物实体当前当前最大无伤害时间，单位为tick.
     * <p>原文：
     * Sets the living entity's current maximum no damage ticks.
     *
     * @param ticks 最大无伤害时间，单位为tick
     * <p>原文：maximum amount of no damage ticks
     */
    public void setMaximumNoDamageTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回生物实体在当前无伤害时间最后受到的伤害.
     * <p>
     * 只有当伤害高于这个数值时生物实体才会进一步受到伤害.
     * <p>原文：
     * Returns the living entity's last damage taken in the current no damage
     * ticks time.
     * <p>
     * Only damage higher than this amount will further damage the living
     * entity.
     *
     * @return 上一个无伤害时间段内受到的伤害
     * <p>原文：damage taken since the last no damage ticks time period
     */
    public double getLastDamage();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前无伤害时间段内处理的伤害.
     * <p>原文：
     * Sets the damage dealt within the current no damage ticks time period.
     *
     * @param damage 伤害总量
     * <p>原文：amount of damage
     */
    public void setLastDamage(double damage);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回生物实体当前无伤害时间，单位为tick.
     * <p>原文：
     * Returns the living entity's current no damage ticks.
     *
     * @return 无伤害时间，单位为tick
     * <p>原文：amount of no damage ticks
     */
    public int getNoDamageTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置生物实体当前无伤害时间，单位为tick.
     * <p>原文：
     * Sets the living entity's current no damage ticks.
     *
     * @param ticks 无伤害时间，单位为tick
     * <p>原文：amount of no damage ticks
     */
    public void setNoDamageTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体未执行任何操作的 tick 数。
     * <p>
     * "未操作 tick" 的具体含义因实体而异，无法明确定义。
     * 一些示例包括乌贼使用此值来判断何时游泳，袭击者判断何时被驱逐出袭击，
     * 或生物（如凋灵）作为消失的条件。
     * <p>原文：
     * Get the ticks that this entity has performed no action.
     * <p>
     * The details of what "no action ticks" entails varies from entity to entity
     * and cannot be specifically defined. Some examples include squid using this
     * value to determine when to swim, raiders for when they are to be expelled
     * from raids, or creatures (such as withers) as a requirement to be despawned.
     *
     * @return 未操作的 tick 数
     * <p>原文：amount of no action ticks
     */
    public int getNoActionTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体未执行任何操作的 tick 数。
     * <p>
     * "未操作 tick" 的具体含义因实体而异，无法明确定义。
     * 一些示例包括乌贼使用此值来判断何时游泳，袭击者判断何时被驱逐出袭击，
     * 或生物（如凋灵）作为消失的条件。
     * <p>原文：
     * Set the ticks that this entity has performed no action.
     * <p>
     * The details of what "no action ticks" entails varies from entity to entity
     * and cannot be specifically defined. Some examples include squid using this
     * value to determine when to swim, raiders for when they are to be expelled
     * from raids, or creatures (such as withers) as a requirement to be despawned.
     *
     * @param ticks 未操作的 tick 数
     * <p>原文：amount of no action ticks
     */
    public void setNoActionTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取击杀指定生物实体的玩家.
     * <p>
     * 可能为空.
     * <p>原文：
     * Gets the player identified as the killer of the living entity.
     * <p>
     * May be null.
     *
     * @return 击杀指定生物实体的玩家，如果找不到则返回null
     * <p>原文：killer player, or null if none found
     */
    @Nullable
    public Player getKiller();

    // Paper start
    /**
     * Sets the player identified as the killer of the living entity.
     *
     * @param killer player
     */
    public void setKiller(@Nullable Player killer);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向生物实体添加指定的{@link PotionEffect}（药水效果）.
     * <p>原文：
     * Adds the given {@link PotionEffect} to the living entity.
     * <p>
     * Note: {@link PotionEffect#getHiddenPotionEffect()} is ignored when
     * adding the effect to the entity.
     *
     * @param effect 添加的药水效果
     * <p>原文：PotionEffect to be added
     * @return 效果是否添加
     * <p>原文：whether the effect could be added
     */
    default boolean addPotionEffect(@NotNull PotionEffect effect) {
        return this.addPotionEffect(effect, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向生物实体添加指定的{@link PotionEffect}（药水效果）.
     * <p>
     * 一个指定的{@link PotionEffectType}（药水效果类型）只能有一种药水效果存在.
     * <p>原文：
     * Adds the given {@link PotionEffect} to the living entity.
     * <p>
     * Only one potion effect can be present for a given {@link PotionEffectType}.
     *
     * @param effect 添加的药水效果
     * <p>原文：PotionEffect to be added
     * @param force 是否移除冲突的效果
     * <p>原文：whether conflicting effects should be removed
     * @return 效果是否添加
     * <p>原文：whether the effect could be added
     * @deprecated 由于现已支持多个同种类型的药水效果, 没必要强制添加.
     * <p>译注:在某次版本更新后, Minecraft 自身支持了多个同种药水效果, 效果弱的会被隐藏;
     * 但为兼容性考虑, 如确有覆盖效果需求的, 还是指定force为true
     * <p>原文：no need to force since multiple effects of the same type are
     * now supported.
     */
    @Deprecated(since = "1.15.2")
    public boolean addPotionEffect(@NotNull PotionEffect effect, boolean force);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试向生物实体添加所有指定的{@link PotionEffect}（药水效果）.
     * <p>原文：
     * Attempts to add all of the given {@link PotionEffect} to the living
     * entity.
     * <p>
     * Note: {@link PotionEffect#getHiddenPotionEffect()} is ignored when
     * adding the effect to the entity.
     *
     * @param effects 添加的效果
     * <p>原文：the effects to add
     * @return 所有效果是否添加
     * <p>原文：whether all of the effects could be added
     */
    public boolean addPotionEffects(@NotNull Collection<PotionEffect> effects);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回生物实体是否已经存在一个指定的{@link PotionEffectType}效果.
     * <p>原文：
     * Returns whether the living entity already has an existing effect of
     * the given {@link PotionEffectType} applied to it.
     *
     * @param type 检查的药水类型
     * <p>原文：the potion type to check
     * @return 是否有指定的药水效果作用于生物实体
     * <p>原文：whether the living entity has this potion effect active on them
     */
    public boolean hasPotionEffect(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回指定类型的有效{@link PotionEffect}.
     * <p>
     * 如果效果不存在则会返回null.
     * <p>原文：
     * Returns the active {@link PotionEffect} of the specified type.
     * <p>
     * If the effect is not present on the entity then null will be returned.
     *
     * @param type 检查的药水类型
     * <p>原文：the potion type to check
     * @return 作用于实体的效果，如果没有则返回null。
     * <p>原文：the effect active on this entity, or null if not active.
     */
    @Nullable
    public PotionEffect getPotionEffect(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除当前所有指定的{@link PotionEffectType}效果.
     * <p>原文：
     * Removes any effects present of the given {@link PotionEffectType}.
     *
     * @param type 移除的药水类型
     * <p>原文：the potion type to remove
     */
    public void removePotionEffect(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回当前作用于生物实体的所有{@link PotionEffect}.
     * <p>
     * 译注:不要向获得的列表中添加条目, 没有任何效果.
     * <p>原文：
     * Returns all currently active {@link PotionEffect}s on the living
     * entity.
     *
     * @return 一个{@link PotionEffect}的集合
     * <p>原文：a collection of {@link PotionEffect}s
     */
    @NotNull
    public Collection<PotionEffect> getActivePotionEffects();

    /**
     * Removes all active potion effects for this entity.
     *
     * @return true if any were removed
     */
    boolean clearActivePotionEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查生物实体是否阻挡另一个的视线.
     * <p>
     * 这使用了与敌对怪物寻找最近玩家相同的算法.
     * <p>原文：
     * Checks whether the living entity has block line of sight to another.
     * <p>
     * This uses the same algorithm that hostile mobs use to find the closest
     * player.
     *
     * @param other 确定视线的实体
     * <p>原文：the entity to determine line of sight to
     * @return 如果存在视线则返回true，否则返回false
     * <p>原文：true if there is a line of sight, false if not
     */
    public boolean hasLineOfSight(@NotNull Entity other);

    /**
     * Checks whether the living entity has block line of sight to the given block.
     * <p>
     * This uses the same algorithm that hostile mobs use to find the closest
     * player.
     *
     * @param location the location to determine line of sight to
     * @return true if there is a line of sight, false if not
     */
    public boolean hasLineOfSight(@NotNull Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回生物实体是否会在远离玩家时消失.
     * <p>
     * 默认情况下，动物不会被移除而怪物会.
     * <p>原文：
     * Returns if the living entity despawns when away from players or not.
     * <p>
     * By default, animals are not removed while other mobs are.
     *
     * @return 如果生物实体会在远离玩家时消失则返回true
     * <p>原文：true if the living entity is removed when away from players
     */
    public boolean getRemoveWhenFarAway();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置生物实体是否会在远离玩家时消失.
     * <p>原文：
     * Sets whether or not the living entity despawns when away from players
     * or not.
     *
     * @param remove 移除状态
     * <p>原文：the removal status
     */
    public void setRemoveWhenFarAway(boolean remove);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取生物实体穿戴的装备背包栏.
     * <p>原文：
     * Gets the inventory with the equipment worn by the living entity.
     *
     * @return 生物实体的背包栏
     * <p>原文：the living entity's inventory
     */
    @Nullable
    public EntityEquipment getEquipment();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置生物实体是否能捡拾物品.
     * <p>原文：
     * Sets whether or not the living entity can pick up items.
     *
     * @param pickup 生物实体是否能捡拾物品
     * <p>原文：whether or not the living entity can pick up items
     */
    public void setCanPickupItems(boolean pickup);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取生物实体是否能捡拾物品.
     * <p>原文：
     * Gets if the living entity can pick up items.
     *
     * @return 生物实体是否能捡拾物品
     * <p>原文：whether or not the living entity can pick up items
     */
    public boolean getCanPickupItems();

    /**
     * Returns whether the entity is currently leashed.
     *
     * @return whether the entity is leashed
     */
    public boolean isLeashed();

    /**
     * Gets the entity that is currently leading this entity.
     *
     * @return the entity holding the leash
     * @throws IllegalStateException if not currently leashed
     */
    @NotNull
    public Entity getLeashHolder() throws IllegalStateException;

    /**
     * Sets the leash on this entity to be held by the supplied entity.
     * <p>
     * This method has no effect on players.
     * Non-living entities excluding leashes will not persist as leash
     * holders.
     *
     * @param holder the entity to leash this entity to, or null to unleash
     * @return whether the operation was successful
     */
    public boolean setLeashHolder(@Nullable Entity holder);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查实体是否正在滑翔，如正在使用鞘翅.
     * <p>原文：
     * Checks to see if an entity is gliding, such as using an Elytra.
     *
     * @return 如果实体正在滑翔则返回true
     * <p>原文：True if this entity is gliding.
     */
    public boolean isGliding();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使实体开始或停止滑翔。即使没有装备鞘翅，这也会有效，但除非有事件取消机制，否则服务器会立即恢复。
     * <p>原文：
     * Makes entity start or stop gliding. This will work even if an Elytra
     * is not equipped, but will be reverted by the server immediately after
     * unless an event-cancelling mechanism is put in place.
     *
     * @param gliding 如果实体正在滑翔，则为 true。
     * <p>原文：True if the entity is gliding.
     */
    public void setGliding(boolean gliding);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查实体是否正在游泳。
     * <p>原文：
     * Checks to see if an entity is swimming.
     *
     * @return 如果该实体正在游泳，则返回 true。
     * <p>原文：True if this entity is swimming.
     */
    public boolean isSwimming();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使实体开始或停止游泳。
     *
     * 如果实体不在水中，可能会产生意想不到的结果。
     * <p>原文：
     * Makes entity start or stop swimming.
     *
     * This may have unexpected results if the entity is not in water.
     *
     * @param swimming 如果实体正在游泳，则为 true。
     * <p>原文：True if the entity is swimming.
     * @deprecated This does nothing and is immediately reverted by the server, in the next tick <!-- Paper - future note: should wait a mojang input client/server side -->
     */
    @Deprecated // Paper
    public void setSwimming(boolean swimming);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查实体是否正在激流。
     * <p>原文：
     * Checks to see if an entity is currently riptiding.
     *
     * @return 如果该实体当前正在激流，则返回 true。
     * <p>原文：True if this entity is currently riptiding.
     */
    public boolean isRiptiding();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使实体开始或停止激流。
     * <p>
     * 注意：这不会对可攻击实体造成伤害。
     * <p>原文：
     * Makes entity start or stop riptiding.
     * <p>
     * Note: This does not damage attackable entities.
     *
     * @param riptiding 实体是否应开始激流。
     * <p>原文：whether the entity should start riptiding.
     * @see HumanEntity#startRiptideAttack(int, float, ItemStack)
     */
    public void setRiptiding(boolean riptiding);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体是否正在睡觉.
     * <p>原文：
     * Returns whether this entity is slumbering.
     *
     * @return 实体睡眠状态
     * <p>原文：slumber state
     */
    public boolean isSleeping();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体是否正在攀爬.
     * <p>原文：
     * Gets if the entity is climbing.
     *
     * @return 实体攀爬状态
     * <p>原文：if the entity is climbing
     */
    public boolean isClimbing();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体是否具有AI. 实体若无AI将完全无法自主移动.
     * <p>原文：
     * Sets whether an entity will have AI.
     *
     * The entity will be completely unable to move if it has no AI.
     *
     * @param ai 怪物是否具有AI
     * <p>原文：whether the mob will have AI or not.
     */
    void setAI(boolean ai);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查实体是否具有AI. 实体若无AI将完全无法自主移动.
     * <p>原文：
     * Checks whether an entity has AI.
     *
     * The entity will be completely unable to move if it has no AI.
     *
     * @return 如果实体具有AI则返回true
     * <p>原文：true if the entity has AI, otherwise false.
     */
    boolean hasAI();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让此实体对指定实体进行近战攻击。
     * 攻击伤害由服务器根据该生物的属性和装备计算，
     * 根据需要对 {@code target} 施加击退效果。
     * <p>原文：
     * Makes this entity attack the given entity with a melee attack.
     *
     * Attack damage is calculated by the server from the attributes and
     * equipment of this mob, and knockback is applied to {@code target} as
     * appropriate.
     *
     * @param target 要攻击的实体。
     * <p>原文：entity to attack.
     */
    public void attack(@NotNull Entity target);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让此实体挥动其主手。
     * 如果此实体没有挥动主手的动画，则此方法不会执行任何操作。
     * <p>原文：
     * Makes this entity swing their main hand.
     *
     * This method does nothing if this entity does not have an animation for
     * swinging their main hand.
     */
    public void swingMainHand();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让此实体挥动其副手。
     * 如果此实体没有挥动副手的动画，则此方法不会执行任何操作。
     * <p>原文：
     * Makes this entity swing their off hand.
     *
     * This method does nothing if this entity does not have an animation for
     * swinging their off hand.
     */
    public void swingOffHand();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使此实体闪烁红色，仿佛它们受到了伤害。
     * <p>原文：
     * Makes this entity flash red as if they were damaged.
     *
     * @param yaw 伤害来源相对于实体的方向，其中 0 表示玩家面前，90 表示右侧，180 表示后方，270 表示左侧。
     * <p>原文：The direction the damage is coming from in relation to the
     * entity, where 0 is in front of the player, 90 is to the right, 180 is
     * behind, and 270 is to the left
     */
    public void playHurtAnimation(float yaw);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体是否会与其他实体发生碰撞。
     * <p>
     * 对此规则的豁免可以通过 {@link #getCollidableExemptions()} 进行管理。
     * <p>
     * 请注意，客户端可能会预测其与其他实体之间的碰撞，导致此标志在玩家碰撞时无效。
     * 因此，此方法应仅用于设置非玩家实体的碰撞状态。
     * <p>
     * 要控制玩家碰撞，请使用 {@link Team.Option#COLLISION_RULE}，结合 {@link Scoreboard} 和 {@link Team}。
     * <p>原文：
     * Set if this entity will be subject to collisions with other entities.
     * <p>
     * Exemptions to this rule can be managed with
     * {@link #getCollidableExemptions()}
     * <p>
     * Note that the client may predict the collision between itself and another
     * entity, resulting in this flag not working for player collisions. This
     * method should therefore only be used to set the collision status of
     * non-player entities.
     * <p>
     * To control player collisions, use {@link Team.Option#COLLISION_RULE} in
     * combination with a {@link Scoreboard} and a {@link Team}.
     *
     * @param collidable 碰撞状态
     * <p>原文：collision status
     */
    void setCollidable(boolean collidable);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体是否会与其他实体发生碰撞。
     * <p>
     * 某些实体可能会被豁免于此实体的可碰撞规则。
     * 使用 {@link #getCollidableExemptions()} 获取这些实体。
     * <p>
     * 请注意，此方法仅返回自定义的可碰撞状态，
     * 而不考虑实体因其他原因（如死亡）而变得不可碰撞的情况。
     * <p>
     * 请注意，客户端可能会预测其与其他实体之间的碰撞，
     * 导致此标志在玩家碰撞时不准确。
     * 因此，此方法应仅用于检查非玩家实体的碰撞状态。
     * <p>
     * 要检查玩家的碰撞行为，请使用
     * {@link Team.Option#COLLISION_RULE}，结合 {@link Scoreboard} 和 {@link Team}。
     * <p>原文：
     * Gets if this entity is subject to collisions with other entities.
     * <p>
     * Some entities might be exempted from the collidable rule of this entity.
     * Use {@link #getCollidableExemptions()} to get these.
     * <p>
     * Please note that this method returns only the custom collidable state,
     * not whether the entity is non-collidable for other reasons such as being
     * dead.
     * <p>
     * Note that the client may predict the collision between itself and another
     * entity, resulting in this flag not being accurate for player collisions.
     * This method should therefore only be used to check the collision status
     * of non-player entities.
     * <p>
     * To check the collision behavior for a player, use
     * {@link Team.Option#COLLISION_RULE} in combination with a
     * {@link Scoreboard} and a {@link Team}.
     *
     * @return 碰撞状态
     * <p>原文：collision status
     */
    boolean isCollidable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可变的 UUID 集合，其中包含被豁免于实体可碰撞规则的实体，
     * 这些实体与此实体的碰撞行为将与之相反。
     * <p>
     * 此集合可以修改，以添加或移除豁免。
     * <p>
     * 例如，如果可碰撞为 true，且某个实体在豁免集合中，
     * 则该实体将不会与其发生碰撞。类似地，如果可碰撞为 false，
     * 而某个实体在此集合中，则仍会与其发生碰撞。
     * <p>
     * 请注意，这些豁免当前不是持久的。
     * <p>
     * 请注意，客户端可能会预测其与其他实体之间的碰撞，
     * 导致这些豁免在玩家碰撞时不准确。
     * 因此，此方法应仅用于豁免非玩家实体。
     * <p>
     * 要为玩家豁免碰撞，请使用 {@link Team.Option#COLLISION_RULE}
     * ，结合 {@link Scoreboard} 和 {@link Team}。
     * <p>原文：
     * Gets a mutable set of UUIDs of the entities which are exempt from the
     * entity's collidable rule and which's collision with this entity will
     * behave the opposite of it.
     * <p>
     * This set can be modified to add or remove exemptions.
     * <p>
     * For example if collidable is true and an entity is in the exemptions set
     * then it will not collide with it. Similarly if collidable is false and an
     * entity is in this set then it will still collide with it.
     * <p>
     * Note these exemptions are not (currently) persistent.
     * <p>
     * Note that the client may predict the collision between itself and another
     * entity, resulting in those exemptions not being accurate for player
     * collisions. This method should therefore only be used to exempt
     * non-player entities.
     * <p>
     * To exempt collisions for a player, use {@link Team.Option#COLLISION_RULE}
     * in combination with a {@link Scoreboard} and a {@link Team}.
     *
     * @return 可碰撞豁免集合
     * <p>原文：the collidable exemption set
     */
    @NotNull
    Set<UUID> getCollidableExemptions();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回指定记忆的值。
     * <p>
     * 请注意，当特定实体默认没有该值时，值为 null。
     * <p>原文：
     * Returns the value of the memory specified.
     * <p>
     * Note that the value is null when the specific entity does not have that
     * value by default.
     *
     * @param memoryKey 要访问的记忆
     * <p>原文：memory to access
     * @param <T> 返回值的类型
     * <p>原文：the type of the return value
     * @return 记忆部分值的实例，如果不存在则返回 null
     * <p>原文：an instance of the memory section value or null if not present
     */
    @Nullable
    <T> T getMemory(@NotNull MemoryKey<T> memoryKey);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定记忆的值。
     * <p>
     * 请注意，当特定实体默认没有该值时，值不会被持久化。
     * <p>原文：
     * Sets the value of the memory specified.
     * <p>
     * Note that the value will not be persisted when the specific entity does
     * not have that value by default.
     *
     * @param memoryKey 要访问的记忆
     * <p>原文：the memory to access
     * @param memoryValue 一个类型化的记忆值
     * <p>原文：a typed memory value
     * @param <T> 传递值的类型
     * <p>原文：the type of the passed value
     */
    <T> void setMemory(@NotNull MemoryKey<T> memoryKey, @Nullable T memoryValue);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体在受伤时发出的 {@link Sound}。
     * <p>原文：
     * Get the {@link Sound} this entity will make when damaged.
     *
     * @return 受伤声音，如果实体不发出声音则返回 null
     * <p>原文：the hurt sound, or null if the entity does not make any sound
     */
    @Nullable
    public Sound getHurtSound();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体在死亡时发出的 {@link Sound}。
     * <p>原文：
     * Get the {@link Sound} this entity will make on death.
     *
     * @return 死亡声音，如果实体不发出声音则返回 null
     * <p>原文：the death sound, or null if the entity does not make any sound
     */
    @Nullable
    public Sound getDeathSound();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体从给定高度（以方块为单位）坠落时发出的 {@link Sound}。
     * 如果高度超过 4 个方块，声音通常会在小坠落和大坠落伤害声之间有所不同。
     * <p>原文：
     * Get the {@link Sound} this entity will make when falling from the given
     * height (in blocks). The sound will often differ between either a small
     * or a big fall damage sound if the height exceeds 4 blocks.
     *
     * @param fallHeight 坠落高度（以方块为单位）
     * <p>原文：the fall height in blocks
     * @return 坠落伤害声音
     * <p>原文：the fall damage sound
     * @see #getFallDamageSoundSmall()
     * @see #getFallDamageSoundBig()
     */
    @NotNull
    public Sound getFallDamageSound(int fallHeight);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体从小高度坠落时发出的 {@link Sound}。
     * <p>原文：
     * Get the {@link Sound} this entity will make when falling from a small
     * height.
     *
     * @return 坠落伤害声音
     * <p>原文：the fall damage sound
     */
    @NotNull
    public Sound getFallDamageSoundSmall();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体从大高度坠落时发出的 {@link Sound}。
     * <p>原文：
     * Get the {@link Sound} this entity will make when falling from a large
     * height.
     *
     * @return 坠落伤害声音
     * <p>原文：the fall damage sound
     */
    @NotNull
    public Sound getFallDamageSoundBig();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体饮用给定 {@link ItemStack} 时发出的 {@link Sound}。
     * <p>原文：
     * Get the {@link Sound} this entity will make when drinking the given
     * {@link ItemStack}.
     *
     * @param itemStack 正在饮用的物品堆
     * <p>原文：the item stack being drank
     * @return 饮用声音
     * <p>原文：the drinking sound
     */
    @NotNull
    public Sound getDrinkingSound(@NotNull ItemStack itemStack);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体食用给定 {@link ItemStack} 时发出的 {@link Sound}。
     * <p>原文：
     * Get the {@link Sound} this entity will make when eating the given
     * {@link ItemStack}.
     *
     * @param itemStack 正在食用的物品堆
     * <p>原文：the item stack being eaten
     * @return 食用声音
     * <p>原文：the eating sound
     */
    @NotNull
    public Sound getEatingSound(@NotNull ItemStack itemStack);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果此实体可以在水下呼吸，并且在空气供应达到零时不会受到窒息伤害，则返回 true。
     * <p>原文：
     * Returns true if this entity can breathe underwater and will not take
     * suffocation damage when its air supply reaches zero.
     *
     * @return <code>true</code> 如果实体可以在水下呼吸
     * <p>原文：<code>true</code> if the entity can breathe underwater
     */
    public boolean canBreatheUnderwater();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体所属的类别。
     * 类别可能会使此实体受到额外的效果、收益或负面影响。
     * <p>原文：
     * Get the category to which this entity belongs.
     *
     * Categories may subject this entity to additional effects, benefits or
     * debuffs.
     *
     * @return 实体类别
     * <p>原文：the entity category
     * @deprecated 实体分组现在由标签管理，而不是类别
     * <p>原文：entity groupings are now managed by tags, not categories
     */
    @NotNull
    @Deprecated(since = "1.20.5", forRemoval = true) @org.jetbrains.annotations.Contract("-> fail") // Paper
    public EntityCategory getCategory();

    /**
     * Get the number of arrows stuck in this entity
     *
     * @return Number of arrows stuck
     * @deprecated use {@link #getArrowsInBody()}
     */
    @Deprecated
    default @NonNegative int getArrowsStuck() {
        return this.getArrowsInBody();
    }

    /**
     * Set the number of arrows stuck in this entity
     *
     * @param arrows Number of arrows to stick in this entity
     * @deprecated use {@link #setArrowsInBody(int, boolean)}. <b>This method previously fired {@link org.bukkit.event.entity.ArrowBodyCountChangeEvent} so if
     * you want to retain exact functionality, pass {@code true} for {@code fireEvent}.</b>
     */
    @Deprecated
    default void setArrowsStuck(@NonNegative int arrows) {
        this.setArrowsInBody(arrows, true);
    }

    /**
     * Get the delay (in ticks) before blocking is effective for this entity
     *
     * @return Delay in ticks
     * @deprecated no longer applicable, check the blocks_attacks component on the shield
     */
    @Deprecated(since = "1.21.9")
    default int getShieldBlockingDelay() {
        return 5;
    }

    /**
     * Set the delay (in ticks) before blocking is effective for this entity
     *
     * @param delay Delay in ticks
     * @deprecated no longer applicable, use the blocks_attacks component on the shield
     */
    @Deprecated(since = "1.21.9")
    default void setShieldBlockingDelay(int delay) {
    }

    /**
     * Retrieves the sideways movement direction of the entity.
     * <p>
     * The returned value ranges from -1 to 1, where:
     * <ul>
     *     <li>Positive 1 represents movement to the left.</li>
     *     <li>Negative 1 represents movement to the right.</li>
     * </ul>
     *
     * Please note that for entities of type {@link Player}, this value will only return whole numbers depending
     * on what keys are held, see {@link Player#getCurrentInput()}.
     * <p>
     * This method specifically provides information about the entity's sideways movement, whereas {@link #getVelocity()} returns
     * a vector representing the entity's overall current momentum.
     *
     * @return Sideways movement direction, ranging from -1 (right) to 1 (left).
     */
    float getSidewaysMovement();

    /**
     * Retrieves the upwards movement direction of the entity.
     * <p>
     * The returned value ranges from -1 to 1, where:
     * <ul>
     *     <li>Positive 1 represents upward movement.</li>
     *     <li>Negative 1 represents downward movement.</li>
     * </ul>
     *
     * Please note that for entities of type {@link Player}, this value is never updated.
     * <p>
     * This method specifically provides information about the entity's vertical movement,
     * whereas {@link #getVelocity()} returns a vector representing the entity's overall
     * current momentum.
     *
     * @return Upwards movement direction, ranging from -1 (downward) to 1 (upward).
     */
    float getUpwardsMovement();

    /**
     * Retrieves the forwards movement direction of the entity.
     * <p>
     * The returned value ranges from -1 to 1, where:
     * <ul>
     *     <li>Positive 1 represents movement forwards.</li>
     *     <li>Negative 1 represents movement backwards.</li>
     * </ul>
     *
     * Please note that for entities of type {@link Player}, this value will only return whole numbers depending
     * on what keys are held, see {@link Player#getCurrentInput()}.
     * <p>
     * This method specifically provides information about the entity's forward and backward movement,
     * whereas {@link #getVelocity()} returns a vector representing the entity's overall current momentum.
     *
     * @return Forwards movement direction, ranging from -1 (backward) to 1 (forward).
     */
    float getForwardsMovement();

    // Paper start - active item API
    /**
     * Starts using the item in the specified hand, making it the
     * currently active item. When, for example, called on a skeleton,
     * this will cause it to start drawing its bow.
     * <p>
     * Only HAND or OFF_HAND may be used for the hand parameter.
     * <p>
     * When used on a player, the client will stop using the item
     * if right click is held down.
     * <p>
     * This method does not make any guarantees about the effect of this method
     * as such depends on the entity and its state.
     *
     * @param hand the hand that contains the item to be used
     */
    @org.jetbrains.annotations.ApiStatus.Experimental
    void startUsingItem(@NotNull org.bukkit.inventory.EquipmentSlot hand);

    /**
     * Finishes using the currently active item. When, for example, a
     * skeleton is drawing its bow, this will cause it to release and
     * fire the arrow.
     * <p>
     * This method does not make any guarantees about the effect of this method
     * as such depends on the entity and its state.
     */
    @org.jetbrains.annotations.ApiStatus.Experimental
    void completeUsingActiveItem();

    /**
     * Gets the item being actively "used" or consumed.
     *
     * @return the item
     */
    org.bukkit.inventory.@NotNull ItemStack getActiveItem();

    /**
     * Interrupts any ongoing active "usage" or consumption or an item.
     */
    void clearActiveItem();

    /**
     * Gets the remaining number of ticks for {@link #getActiveItem()}'s usage.
     *
     * @return remaining ticks to use {@link #getActiveItem()}
     */
    int getActiveItemRemainingTime();

    /**
     * Sets the number of ticks that remain for {@link #getActiveItem()}'s
     * usage.
     * <p>
     * Valid values are between 0 and the max item use duration for
     * the specific item type.
     *
     * @param ticks time in ticks remaining
     */
    void setActiveItemRemainingTime(@org.jetbrains.annotations.Range(from = 0, to = Integer.MAX_VALUE) int ticks);

    /**
     * Gets if the entity is using an item (eating, drinking, etc).
     *
     * @return true if using an item
     */
    boolean hasActiveItem();

    /**
     * Get how long the {@link #getActiveItem()} has been in use for.
     *
     * @return time used in ticks
     */
    int getActiveItemUsedTime();

    /**
     * Get the hand using the active item. Will be either
     * {@link org.bukkit.inventory.EquipmentSlot#HAND} or
     * {@link org.bukkit.inventory.EquipmentSlot#OFF_HAND}.
     *
     * @return the hand being used
     */
    org.bukkit.inventory.@NotNull EquipmentSlot getActiveItemHand();

    /**
     * Gets remaining time a player needs to keep hands raised with an item to finish using it.
     *
     * @return remaining ticks to use the item
     * @see #getActiveItemRemainingTime()
     */
    @org.jetbrains.annotations.ApiStatus.Obsolete(since = "1.20.4")
    default int getItemUseRemainingTime() {
        return this.getActiveItemRemainingTime();
    }

    /**
     * Get how long the entity's hands have been raised (Charging Bow attack, using a potion, etc)
     *
     * @return Get how long the players hands have been raised (Charging Bow attack, using a potion, etc)
     * @see #getActiveItemUsedTime()
     */
    @org.jetbrains.annotations.ApiStatus.Obsolete(since = "1.20.4")
    default int getHandRaisedTime() {
        return this.getActiveItemUsedTime();
    }

    /**
     * Whether this entity is using or charging an attack (Bow pulled back, drinking potion, eating food)
     *
     * @return whether this entity is using or charging an attack (Bow pulled back, drinking potion, eating food)
     * @see #hasActiveItem()
     */
    @org.jetbrains.annotations.ApiStatus.Obsolete(since = "1.20.4")
    default boolean isHandRaised() {
        return this.hasActiveItem();
    }

    /**
     * Gets the hand raised by this living entity. Will be either
     * {@link org.bukkit.inventory.EquipmentSlot#HAND} or
     * {@link org.bukkit.inventory.EquipmentSlot#OFF_HAND}.
     *
     * @return the hand raised
     * @see #getActiveItemHand()
     */
    @NotNull
    @org.jetbrains.annotations.ApiStatus.Obsolete(since = "1.20.4")
    default org.bukkit.inventory.EquipmentSlot getHandRaised() {
        return this.getActiveItemHand();
    }
    // Paper end - active item API

    // Paper start - entity jump API
    /**
     * Get entity jump state.
     * <p>
     * Jump state will be true when the entity has been marked to jump.
     *
     * @return entity jump state.
     */
    boolean isJumping();

    /**
     * Set entity jump state
     * <p>
     * Setting to true will mark the entity to jump.
     * <p>
     * Setting to false will unmark the entity to jump but will not stop a jump already in-progress.
     *
     * @param jumping entity jump state
     */
    void setJumping(boolean jumping);
    // Paper end - entity jump API

    // Paper start - pickup animation API
    /**
     * Plays pickup item animation towards this entity.
     * <p>
     * <b>This will remove the item on the client.</b>
     * <p>
     * Quantity is inferred to be that of the {@link Item}.
     *
     * @param item item to pickup
     */
    default void playPickupItemAnimation(@NotNull Item item) {
        playPickupItemAnimation(item, item.getItemStack().getAmount());
    }

    /**
     * Plays pickup item animation towards this entity.
     * <p>
     * <b>This will remove the item on the client.</b>
     *
     * @param item item to pickup
     * @param quantity quantity of item
     */
    void playPickupItemAnimation(@NotNull Item item, int quantity);
    // Paper end - pickup animation API

    // Paper start - hurt direction API
    /**
     * Gets player hurt direction
     *
     * @return hurt direction
     */
    float getHurtDirection();

    /**
     * Sets player hurt direction
     *
     * @param hurtDirection hurt direction
     * @deprecated use {@link Player#setHurtDirection(float)}
     */
    @Deprecated
    void setHurtDirection(float hurtDirection);
    // Paper end - hurt direction API

    // Paper start - swing hand API
    /**
     * Makes this entity swing their hand.
     *
     * <p>This method does nothing if this entity does not
     * have an animation for swinging their hand.
     *
     * @param hand hand to be swung, either {@link org.bukkit.inventory.EquipmentSlot#HAND} or {@link org.bukkit.inventory.EquipmentSlot#OFF_HAND}
     * @throws IllegalArgumentException if invalid hand is passed
     */
    default void swingHand(@NotNull org.bukkit.inventory.EquipmentSlot hand) {
        com.google.common.base.Preconditions.checkArgument(hand != null && hand.isHand(), String.format("Expected a valid hand, got \"%s\" instead!", hand));
        if (hand == org.bukkit.inventory.EquipmentSlot.HAND) {
            this.swingMainHand();
        } else {
            this.swingOffHand();
        }
    }
    // Paper end - swing hand API

    // Paper start - knockback API
    /**
     * Knocks back this entity from a specific direction with a specified strength. Mechanics such
     * as knockback resistance will be factored in.
     *
     * The direction specified in this method will be the direction of the source of the knockback,
     * so the entity will be pushed in the opposite direction.
     * @param strength The strength of the knockback. Must be greater than 0.
     * @param directionX The relative x position of the knockback source direction
     * @param directionZ The relative z position of the knockback source direction
     */
    void knockback(double strength, double directionX, double directionZ);
    // Paper end - knockback API

    // Paper start - ItemStack damage API
    /**
     * Notifies all clients tracking this entity that the item in
     * the slot of this entity broke.
     * <p>
     * NOTE: this does not mutate any entity state
     *
     * @param slot the slot
     */
    void broadcastSlotBreak(org.bukkit.inventory.@NotNull EquipmentSlot slot);

    /**
     * Notifies specified players that the item in the slot
     * of this entity broke.
     * <p>
     * NOTE: this does not mutate any entity state
     *
     * @param slot the slot
     * @param players the players to notify
     */
    void broadcastSlotBreak(org.bukkit.inventory.@NotNull EquipmentSlot slot, @NotNull Collection<Player> players);

    /**
     * Damages the itemstack in this slot by the specified amount.
     * <p>
     * This runs all logic associated with damaging an itemstack like
     * gamemode and enchantment checks, events, stat changes, and advancement
     * triggers.
     *
     * @param stack the itemstack to damage
     * @param amount the amount of damage to do
     * @return the damaged itemstack, or an empty stack if it broke. There are no
     * guarantees the returned itemstack is the same instance
     */
    @NotNull ItemStack damageItemStack(@NotNull ItemStack stack, int amount);

    /**
     * Damages the itemstack in this slot by the specified amount.
     * <p>
     * This runs all logic associated with damaging an itemstack like
     * gamemode and enchantment checks, events, stat changes, advancement
     * triggers, and notifying clients to play break animations.
     *
     * @param slot the slot of the stack to damage
     * @param amount the amount of damage to do
     */
    void damageItemStack(org.bukkit.inventory.@NotNull EquipmentSlot slot, int amount);
    // Paper end - ItemStack damage API

    // Paper start - body yaw API
    /**
     * Gets entity body yaw
     *
     * @return entity body yaw
     * @see Location#getYaw()
     */
    float getBodyYaw();

    /**
     * Sets entity body yaw
     *
     * @param bodyYaw new entity body yaw
     * @see Location#setYaw(float)
     */
    void setBodyYaw(float bodyYaw);
    // Paper end - body yaw API

    // Paper start - Expose canUseSlot
    /**
     * Checks whether this entity can use the equipment slot.
     * <br>For example, not all entities may have {@link org.bukkit.inventory.EquipmentSlot#BODY}.
     *
     * @param slot equipment slot
     * @return whether this entity can use the equipment slot
     */
    boolean canUseEquipmentSlot(org.bukkit.inventory.@NotNull EquipmentSlot slot);
    // Paper end - Expose canUseSlot

    /**
     * Gets the entity's combat tracker
     *
     * @return the entity's combat tracker
     */
    @ApiStatus.Experimental
    @NotNull CombatTracker getCombatTracker();

    /**
     * Sets the visual style of the waypoint icon using the specified {@link Key}.
     * <p>
     * Minecraft has the keys "minecraft:bowtie" and "minecraft:default" built in.
     *
     * @param key the key representing the style, or {@code null} to use the default style
     * @see <a href="https://minecraft.wiki/w/Waypoint_style">https://minecraft.wiki/w/Waypoint_style</a>
     */
    void setWaypointStyle(@Nullable Key key);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体的路径点颜色，null 则重置为默认值。
     * <p>原文：
     * Sets the color of the waypoint icon.
     *
     * @param color 新颜色
     * <p>原文：the new color to apply to the waypoint icon, or {@code null} to use the default color.
     *              While the color may contain alpha values, the client does not receive them.
     * @see <a href="https://minecraft.wiki/w/Locator_Bar#Usage">https://minecraft.wiki/w/Locator_Bar#Usage</a>
     */
    void setWaypointColor(@Nullable Color color);

    /**
     * Gets the current visual style of the waypoint icon.
     *
     * @return the {@link Key} representing the icon's style
     * @see #setWaypointStyle(Key)
     * @see <a href="https://minecraft.wiki/w/Waypoint_style">https://minecraft.wiki/w/Waypoint_style</a>
     */
    @NotNull
    Key getWaypointStyle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体的路径点颜色，如果为默认/未设置则返回 null。
     * <p>原文：
     * Gets the current color of the waypoint icon, if any.
     *
     * @return 路径点颜色
     * <p>原文：the icon's color, or {@code null} if the default color is used.
     *         While the color may contain alpha values, the client does not receive them.
     * @see #setWaypointColor(Color)
     * @see <a href="https://minecraft.wiki/w/Locator_Bar#Usage">https://minecraft.wiki/w/Locator_Bar#Usage</a>
     */
    @Nullable
    Color getWaypointColor();
}
