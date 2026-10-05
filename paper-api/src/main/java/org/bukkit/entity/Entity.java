package org.bukkit.entity;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import io.papermc.paper.datacomponent.DataComponentView;
import io.papermc.paper.entity.LookAnchor;
import net.kyori.adventure.util.TriState;
import org.bukkit.Chunk; // Paper
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Nameable;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.command.CommandSender;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Directional;
import org.bukkit.metadata.Metadatable;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示世界中的基本实体.
 * <p>
 * 当 {@link #isInWorld()} 返回 false 时, 不保证所有方法都可用/或者有副作用.
 * <p>原文：
 * Represents a base entity in the world
 * <p>
 * Not all methods are guaranteed to work/may have side effects when
 * {@link #isInWorld()} is false.
 */
public interface Entity extends Metadatable, CommandSender, Nameable, PersistentDataHolder, net.kyori.adventure.text.event.HoverEventSource<net.kyori.adventure.text.event.HoverEvent.ShowEntity>, net.kyori.adventure.sound.Sound.Emitter, DataComponentView { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体当前位置
     * <p>原文：
     * Gets the entity's current position
     *
     * @return 一个新的 Location, 包含此实体的位置
     * <p>原文：a new copy of Location containing the position of this entity
     */
    @NotNull
    public Location getLocation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将实体的当前位置存储到提供的Location中.
     * <br>
     * 如果提供的Location为null，则此方法不会执行任何操作并返回null.
     * <p>原文：
     * Stores the entity's current position in the provided Location object.
     * <p>
     * If the provided Location is null this method does nothing and returns
     * null.
     *
     * @param loc 要复制到的Location实例
     * <p>原文：the location to copy into
     * @return 提供的Location或null
     * <p>原文：The Location object provided or null
     */
    @Contract("null -> null; !null -> !null")
    @Nullable
    public Location getLocation(@Nullable Location loc);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体的速度 (单位: 米每tick).
     * <p>原文：
     * Sets this entity's velocity in meters per tick
     *
     * @param velocity 新的行进速度(向量)
     * <p>原文：New velocity to travel with
     */
    public void setVelocity(@NotNull Vector velocity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体的速度.
     * <p>原文：
     * Gets this entity's current velocity
     *
     * @return 实体当前行进速度(向量)
     * <p>原文：Current traveling velocity of this entity
     */
    @NotNull
    public Vector getVelocity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体高度
     * <p>原文：
     * Gets the entity's height
     *
     * @return 实体高度
     * <p>原文：height of entity
     */
    public double getHeight();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体宽度
     * <p>原文：
     * Gets the entity's width
     *
     * @return 实体宽度
     * <p>原文：width of entity
     */
    public double getWidth();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体当前的边界框.
     * <p>
     * 返回的边界框反映了实体的当前位置和大小.
     * <p>原文：
     * Gets the entity's current bounding box.
     * <p>
     * The returned bounding box reflects the entity's current location and
     * size.
     *
     * @return 实体当前的边界框
     * <p>原文：the entity's current bounding box
     */
    @NotNull
    public BoundingBox getBoundingBox();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体是否站在地面上.
     * <br>
     * 此值状态由服务器更新, 除非实体移动，否则不会重新计算.
     * <p>原文：
     * Returns true if the entity is supported by a block. This value is a
     * state updated by the server and is not recalculated unless the entity
     * moves.
     *
     * @return 实体是否站在地上
     * <p>原文：True if entity is on ground.
     * @see Player#isOnGround()
     */
    public boolean isOnGround();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体是否在水中.
     * <p>原文：
     * Returns true if the entity is in water.
     *
     * @return 实体是否在水中
     * <p>原文：<code>true</code> if the entity is in water.
     */
    public boolean isInWater();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体当前所在的世界
     * <p>原文：
     * Gets the current world this entity resides in
     *
     * @return 世界
     * <p>原文：World
     */
    @NotNull
    public World getWorld();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体的旋转角度.
     * <p>
     * 注意: 如果实体受 AI 影响, AI 可能会覆盖此旋转.
     * <p>原文：
     * Sets the entity's rotation.
     * <p>
     * Note that if the entity is affected by AI, it may override this rotation.
     *
     * @param yaw 偏航角
     * <p>原文：the yaw
     * @param pitch 俯仰角
     * <p>原文：the pitch
     */
    public void setRotation(float yaw, float pitch);

    // Paper start - Teleport API
    /**
     * Teleports this entity to the given location.
     * <p>
     * Note: This uses default in game behavior for teleportation, especially in regard to handling
     * passengers and vehicles across dimensions. It should be noted at this moment, teleporting a {@link Player}
     * with passengers across dimensions is not supported and will cause this to return false. This behavior may
     * change in future versions.
     *
     * @param location New location to teleport this entity to
     * @param teleportFlags Flags to be used in this teleportation
     * @return <code>true</code> if the teleport was successful
     */
    default boolean teleport(@NotNull Location location, @NotNull io.papermc.paper.entity.TeleportFlag @NotNull... teleportFlags) {
        return this.teleport(location, TeleportCause.PLUGIN, teleportFlags);
    }

    /**
     * Teleports this entity to the given location.
     * <p>
     * Note: This uses default in game behavior for teleportation, especially in regard to handling
     * passengers and vehicles across dimensions. It should be noted at this moment, teleporting a {@link Player}
     * with passengers across dimensions is not supported and will cause this to return false. This behavior may
     * change in future versions.
     *
     * @param location New location to teleport this entity to
     * @param cause The cause of this teleportation
     * @param teleportFlags Flags to be used in this teleportation
     * @return <code>true</code> if the teleport was successful
     */
    boolean teleport(@NotNull Location location, @NotNull TeleportCause cause, @NotNull io.papermc.paper.entity.TeleportFlag @NotNull... teleportFlags);

    /**
     * Causes the entity to look towards the given position.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     * @param entityAnchor What part of the entity should face the given position
     */
    void lookAt(double x, double y, double z, @NotNull LookAnchor entityAnchor);

    /**
     * Causes the entity to look towards the given position.
     *
     * @param position Position to look at in the player's current world
     * @param entityAnchor What part of the entity should face the given position
     */
    default void lookAt(@NotNull io.papermc.paper.math.Position position, @NotNull LookAnchor entityAnchor) {
        this.lookAt(position.x(), position.y(), position.z(), entityAnchor);
    }
    // Paper end - Teleport API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此实体传送到给定位置.
     * <br>
     * 如果该实体乘坐载具, 则在传送之前卸下载具.
     * <p>原文：
     * Teleports this entity to the given location.
     * <p>
     * Note: This uses default in game behavior for teleportation, especially in regard to handling
     * passengers and vehicles across dimensions. It should be noted at this moment, teleporting a {@link Player}
     * with passengers across dimensions is not supported and will cause this to return false. This behavior may
     * change in future versions.
     *
     * @param location 此实体要传送到的新位置
     * <p>原文：New location to teleport this entity to
     * @return 是否传送成功
     * <p>原文：<code>true</code> if the teleport was successful
     */
    public boolean teleport(@NotNull Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此实体传送到给定位置.
     * <br>
     * 如果该实体乘坐载具, 则在传送之前卸下载具.
     * <p>原文：
     * Teleports this entity to the given location.
     * <p>
     * Note: This uses default in game behavior for teleportation, especially in regard to handling
     * passengers and vehicles across dimensions. It should be noted at this moment, teleporting a {@link Player}
     * with passengers across dimensions is not supported and will cause this to return false. This behavior may
     * change in future versions.
     *
     * @param location 此实体要传送到的新位置
     * <p>原文：New location to teleport this entity to
     * @param cause 传送原因
     * <p>原文：The cause of this teleportation
     * @return 是否传送成功
     * <p>原文：<code>true</code> if the teleport was successful
     */
    public boolean teleport(@NotNull Location location, @NotNull TeleportCause cause);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此实体传送到目标实体.
     * <br>
     * 如果该实体乘坐载具, 则在传送之前离开载具.
     * <p>原文：
     * Teleports this entity to the target Entity.
     * <p>
     * Note: This uses default in game behavior for teleportation, especially in regard to handling
     * passengers and vehicles across dimensions. It should be noted at this moment, teleporting a {@link Player}
     * with passengers across dimensions is not supported and will cause this to return false. This behavior may
     * change in future versions.
     *
     * @param destination 目标实体
     * <p>原文：Entity to teleport this entity to
     * @return 是否传送成功
     * <p>原文：<code>true</code> if the teleport was successful
     */
    public boolean teleport(@NotNull Entity destination);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此实体传送到目标实体.
     * <br>
     * 如果该实体乘坐载具, 则在传送之前离开载具.
     * <p>原文：
     * Teleports this entity to the target Entity.
     * <p>
     * Note: This uses default in game behavior for teleportation, especially in regard to handling
     * passengers and vehicles across dimensions. It should be noted at this moment, teleporting a {@link Player}
     * with passengers across dimensions is not supported and will cause this to return false. This behavior may
     * change in future versions.
     *
     * @param destination 目标实体
     * <p>原文：Entity to teleport this entity to
     * @param cause 传送原因
     * <p>原文：The cause of this teleportation
     * @return 是否传送成功
     * <p>原文：<code>true</code> if the teleport was successful
     */
    public boolean teleport(@NotNull Entity destination, @NotNull TeleportCause cause);

    // Paper start
    /**
     * Loads/Generates(in 1.13+) the Chunk asynchronously, and then teleports the entity when the chunk is ready.
     * <p>
     * Note: This uses default in game behavior for teleportation, especially in regard to handling
     * passengers and vehicles across dimensions. It should be noted at this moment, teleporting a {@link Player}
     * with passengers across dimensions is not supported and will cause the future to return false. This behavior may
     * change in future versions.
     *
     * @param loc Location to teleport to
     * @return A future that will be completed with the result of the teleport
     */
    default java.util.concurrent.@NotNull CompletableFuture<Boolean> teleportAsync(final @NotNull Location loc) {
        return this.teleportAsync(loc, TeleportCause.PLUGIN);
    }

    /**
     * Loads/Generates(in 1.13+) the Chunk asynchronously, and then teleports the entity when the chunk is ready.
     * <p>
     * Note: This uses default in game behavior for teleportation, especially in regard to handling
     * passengers and vehicles across dimensions. It should be noted at this moment, teleporting a {@link Player}
     * with passengers across dimensions is not supported and will cause the future to return false. This behavior may
     * change in future versions.
     *
     * @param loc Location to teleport to
     * @param cause Reason for teleport
     * @return A future that will be completed with the result of the teleport
     */
    default java.util.concurrent.@NotNull CompletableFuture<Boolean> teleportAsync(final @NotNull Location loc, final @NotNull TeleportCause cause) {
        final class Holder {
            static final io.papermc.paper.entity.TeleportFlag[] EMPTY_FLAGS = new io.papermc.paper.entity.TeleportFlag[0];
        }
        return this.teleportAsync(loc, cause, Holder.EMPTY_FLAGS);
    }

    /**
     * Loads/Generates(in 1.13+) the Chunk asynchronously, and then teleports the entity when the chunk is ready.
     * <p>
     * Note: This uses default in game behavior for teleportation, especially in regard to handling
     * passengers and vehicles across dimensions. It should be noted at this moment, teleporting a {@link Player}
     * with passengers across dimensions is not supported and will cause the future to return false. This behavior may
     * change in future versions.
     *
     * @param loc Location to teleport to
     * @param teleportFlags Flags to be used in this teleportation
     * @return A future that will be completed with the result of the teleport
     */
    default java.util.concurrent.@NotNull CompletableFuture<Boolean> teleportAsync(@NotNull Location loc, @NotNull io.papermc.paper.entity.TeleportFlag @NotNull... teleportFlags) {
        return this.teleportAsync(loc, TeleportCause.PLUGIN, teleportFlags);
    }

    /**
     * Loads/Generates(in 1.13+) the Chunk asynchronously, and then teleports the entity when the chunk is ready.
     * <p>
     * Note: This uses default in game behavior for teleportation, especially in regard to handling
     * passengers and vehicles across dimensions. It should be noted at this moment, teleporting a {@link Player}
     * with passengers across dimensions is not supported and will cause the future to return false. This behavior may
     * change in future versions.
     *
     * @param loc Location to teleport to
     * @param cause Reason for teleport
     * @param teleportFlags Flags to be used in this teleportation
     *
     * @return A future that will be completed with the result of the teleport
     */
    java.util.concurrent.@NotNull CompletableFuture<Boolean> teleportAsync(@NotNull Location loc, @NotNull TeleportCause cause, @NotNull io.papermc.paper.entity.TeleportFlag @NotNull... teleportFlags);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回以此实体为中心的边界框内的所有实体.
     * <p>原文：
     * Returns a list of entities within a bounding box centered around this
     * entity
     *
     * @param x 边界框X轴半径
     * <p>原文：1/2 the size of the box along x axis
     * @param y 边界框Y轴半径
     * <p>原文：1/2 the size of the box along y axis
     * @param z 边界框Z轴半径
     * <p>原文：1/2 the size of the box along z axis
     * @return {@code List<Entity>} 附近的实体列表
     * <p>原文：{@code List<Entity>} List of entities nearby
     */
    @NotNull
    public List<org.bukkit.entity.Entity> getNearbyEntities(double x, double y, double z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此实体的唯一ID
     * <p>原文：
     * Returns the network protocol ID for this entity. This is
     * not to be used as an identifier for the entity except in
     * network-related operations. Use {@link #getUniqueId()} as
     * an entity identifier instead.
     *
     * @return 实体ID
     * <p>原文：the network protocol ID
     * @see #getUniqueId()
     */
    public int getEntityId();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体当前着火的tick (还有多久停止着火)
     * <p>原文：
     * Returns the entity's current fire ticks (ticks before the entity stops
     * being on fire).
     *
     * @return 着火刻的数值
     * <p>原文：int fireTicks
     */
    public int getFireTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体的最大着火刻
     * <p>原文：
     * Returns the entity's maximum fire ticks.
     *
     * @return 最大着火刻的数值
     * <p>原文：int maxFireTicks
     */
    public int getMaxFireTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体当前着火的tick (还有多久停止着火)
     * <p>原文：
     * Sets the entity's current fire ticks (ticks before the entity stops
     * being on fire).
     *
     * @param ticks 剩余的刻数
     * <p>原文：Current ticks remaining
     */
    public void setFireTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体是否有视觉上的火焰效果 (实体将始终显示为着火状态).
     * <p>原文：
     * Sets if the entity has visual fire (it will always appear to be on fire).
     *
     * @deprecated This method doesn't allow visually extinguishing a burning entity,
     * use {@link #setVisualFire(TriState)} instead
     * @param fire 是否启用视觉火焰
     * <p>原文：whether visual fire is enabled
     */
    @Deprecated
    void setVisualFire(boolean fire);

    /**
     * Sets if the entity has visual fire (it will always appear to be on fire).
     * <ul>
     *     <li>{@link TriState#NOT_SET} – will revert the entity's visual fire to default</li>
     *     <li>{@link TriState#TRUE} – will make the entity appear to be on fire</li>
     *     <li>{@link TriState#FALSE} – will make the entity appear to be not on fire</li>
     * </ul>
     *
     * @param fire a TriState value representing the state of the visual fire.
     */
    void setVisualFire(@NotNull TriState fire);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体是否有视觉上的火焰效果 (实体将始终显示为着火状态).
     * <p>原文：
     * Gets if the entity has visual fire (it will always appear to be on fire).
     *
     * @deprecated This method can't properly reflect the three possible states of visual fire,
     * use {@link #getVisualFire()} instead
     * @return 是否启用了视觉火焰
     * <p>原文：whether visual fire is enabled
     */
    @Deprecated
    boolean isVisualFire();

    /**
     * Retrieves the visual fire state of the entity.
     *
     * @return A TriState indicating the current visual fire state.
     */
    @NotNull
    TriState getVisualFire();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体当前的冰冻刻 (实体处于细雪中的刻数).
     * <p>原文：
     * Returns the entity's current freeze ticks (amount of ticks the entity has
     * been in powdered snow).
     *
     * @return 冰冻刻数值
     * <p>原文：int freeze ticks
     */
    int getFreezeTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体的最大冰冻刻 (完全冰冻所需的刻数).
     * <p>原文：
     * Returns the entity's maximum freeze ticks (amount of ticks before it will
     * be fully frozen)
     *
     * @return 最大冰冻刻数值
     * <p>原文：int max freeze ticks
     */
    int getMaxFreezeTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体当前的冰冻刻 (实体处于细雪中的刻数).
     * <p>原文：
     * Sets the entity's current freeze ticks (amount of ticks the entity has
     * been in powdered snow).
     *
     * @param ticks 当前刻数
     * <p>原文：Current ticks
     */
    void setFreezeTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体是否已完全冰冻 (在细雪中待满了最大冰冻刻).
     * <p>原文：
     * Gets if the entity is fully frozen (it has been in powdered snow for max
     * freeze ticks).
     *
     * @return 冰冻状态
     * <p>原文：freeze status
     */
    boolean isFrozen();

    /**
     * Sets whether the entity is invisible or not.
     * <p>
     * This setting is undefined for non-living entities like boats or paintings.
     * Non-living entities that are marked as invisible through this method may e.g. only hide their shadow.
     * To hide such entities from players completely, see {@link Player#hideEntity(org.bukkit.plugin.Plugin, Entity)}.
     *
     * @param invisible If the entity is invisible
     */
    void setInvisible(boolean invisible);

    /**
     * Gets whether the entity is invisible or not.
     *
     * @return Whether the entity is invisible
     */
    boolean isInvisible();

    /**
     * Sets this entity no physics status.
     *
     * @param noPhysics boolean indicating if the entity should not have physics.
     */
    void setNoPhysics(boolean noPhysics);

    /**
     * Gets if this entity has no physics.
     *
     * @return true if the entity does not have physics.
     */
    boolean hasNoPhysics();

    /**
     * Gets if the entity currently has its freeze ticks locked
     * to a set amount.
     * <p>
     * This is only set by plugins
     *
     * @return locked or not
     */
    boolean isFreezeTickingLocked();

    /**
     * Sets if the entity currently has its freeze ticks locked,
     * preventing default vanilla freeze tick modification.
     *
     * @param locked prevent vanilla modification or not
     */
    void lockFreezeTicks(boolean locked);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将实体标记为删除.
     * <p>原文：
     * Mark the entity's removal.
     *
     * @throws UnsupportedOperationException 如果你试图移除一名 {@link Player 玩家}. 对于此场景请使用 {@link Player#kickPlayer(String)}
     * <p>原文：if you try to remove a {@link Player} use {@link Player#kick(net.kyori.adventure.text.Component)} in this case instead
     */
    public void remove();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此实体是否已被标记为删除.
     * <p>原文：
     * Returns true if this entity has been marked for removal.
     *
     * @return 实体是否死亡
     * <p>原文：True if it is dead.
     */
    public boolean isDead();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果此实体已经死亡、因为其他原因被抹去或尚未添加到世界, 则返回false.
     * <p>原文：
     * Returns false if the entity has died, been despawned for some other
     * reason, or has not been added to the world.
     *
     * @return 实体是否有效.
     * <p>原文：True if valid.
     */
    public boolean isValid();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回包含此实体的服务器
     * <p>原文：
     * Gets the {@link Server} that contains this Entity
     *
     * @return 运行此实体的服务器
     * <p>原文：Server instance running this Entity
     */
    @Override
    @NotNull
    public Server getServer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体是否会被持久化.
     * <p>
     * 默认情况下所有实体都是持久化的. 如果实体骑乘的载具不是持久化的, 则该实体也不会被持久化.
     * <p>
     * 玩家的持久化标志控制在玩家退出时是否保存其玩家数据文件. 如果玩家直接或间接骑乘非持久化实体, 则根级载具及其所有乘客都不会被持久化.
     * <p>
     * <b>注意: 不要与 {@link LivingEntity#setRemoveWhenFarAway(boolean)} 混淆, 后者控制生物实体的消失.</b>
     * <p>原文：
     * Returns true if the entity gets persisted.
     * <p>
     * By default all entities are persistent. An entity will also not get
     * persisted, if it is riding an entity that is not persistent.
     * <p>
     * The persistent flag on players controls whether or not to save their
     * playerdata file when they quit. If a player is directly or indirectly
     * riding a non-persistent entity, the vehicle at the root and all its
     * passengers won't get persisted.
     * <p>
     * <b>This should not be confused with
     * {@link LivingEntity#setRemoveWhenFarAway(boolean)} which controls
     * despawning of living entities. </b>
     *
     * @return 实体是否持久化
     * <p>原文：true if this entity is persistent
     */
    public boolean isPersistent();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体是否会被持久化.
     * <p>原文：
     * Sets whether or not the entity gets persisted.
     *
     * @param persistent 持久化状态
     * <p>原文：the persistence status
     * @see #isPersistent()
     */
    public void setPersistent(boolean persistent);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取乘坐载具的主要乘客.
     * <br>
     * 可能会有多个乘客, 这个方法只返回主要乘客.
     * <p>原文：
     * Gets the primary passenger of a vehicle. For vehicles that could have
     * multiple passengers, this will only return the primary passenger.
     *
     * @return 一个实体
     * <p>原文：an entity
     * @deprecated 实体可能有多名乘客. 请使用
     * {@link #getPassengers()}
     * <p>原文：entities may have multiple passengers, use
     * {@link #getPassengers()}
     */
    @Deprecated(since = "1.11.2")
    @Nullable
    public Entity getPassenger();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置乘坐载具的主要乘客.
     * <p>原文：
     * Set the passenger of a vehicle.
     *
     * @param passenger 新乘客
     * <p>原文：The new passenger.
     * @return 如果因为某种原因不能设置, 则返回false
     * <p>原文：false if it could not be done for whatever reason
     * @deprecated 实体可能有多名乘客. 请使用
     * {@link #addPassenger(org.bukkit.entity.Entity)}
     * <p>原文：entities may have multiple passengers, use
     * {@link #addPassenger(org.bukkit.entity.Entity)}
     */
    @Deprecated(since = "1.11.2")
    public boolean setPassenger(@NotNull Entity passenger);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取载具的乘客列表.
     * <br>
     * 对返回列表的操作不会反映到实体的当前乘客列表上, 也不保证返回列表的可变性.
     * <p>原文：
     * Gets a list of passengers of this vehicle.
     * <p>
     * The returned list will not be directly linked to the entity's current
     * passengers, and no guarantees are made as to its mutability.
     *
     * @return 与当前乘客相对应的实体列表.
     * <p>原文：list of entities corresponding to current passengers.
     */
    @NotNull
    public List<Entity> getPassengers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加一名乘客.
     * <p>原文：
     * Add a passenger to the vehicle.
     *
     * @param passenger 要添加的乘客
     * <p>原文：The passenger to add
     * @return 如果因为某种原因不能添加, 则返回false
     * <p>原文：false if it could not be done for whatever reason
     */
    public boolean addPassenger(@NotNull Entity passenger);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除一名乘客.
     * <p>原文：
     * Remove a passenger from the vehicle.
     *
     * @param passenger 要移除的乘客
     * <p>原文：The passenger to remove
     * @return 如果因为某种原因不能删除, 则返回false
     * <p>原文：false if it could not be done for whatever reason
     */
    public boolean removePassenger(@NotNull Entity passenger);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否有乘客.
     * <p>原文：
     * Check if a vehicle has passengers.
     *
     * @return 载具是否为空.
     * <p>原文：True if the vehicle has no passengers.
     */
    public boolean isEmpty();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 弹出所有乘客.
     * <p>原文：
     * Eject any passenger.
     *
     * @return 是否有乘客被弹出.
     * <p>原文：True if there was a passenger.
     */
    public boolean eject();

    /**
     * Gets the {@link ItemStack} that a player would select / create (in creative mode)
     * when using the pick block action on this entity.
     *
     * @return item stack result or an empty item stack
     */
    @NotNull
    ItemStack getPickItemStack();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此实体已下落的距离.
     * <p>原文：
     * Returns the distance this entity has fallen
     *
     * @return 距离.
     * <p>原文：The distance.
     */
    public float getFallDistance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体已下落的距离.
     * <p>原文：
     * Sets the fall distance for this entity
     *
     * @param distance 新距离.
     * <p>原文：The new distance.
     */
    public void setFallDistance(float distance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 记录此实体上的最后一个{@link EntityDamageEvent}.
     * <p>原文：
     * Record the last {@link EntityDamageEvent} inflicted on this entity
     *
     * @param event 一个 {@link EntityDamageEvent}
     * <p>原文：a {@link EntityDamageEvent}
     * @deprecated 此方法仅供内部使用, 将被移除
     * <p>原文：method is for internal use only and will be removed
     */
    @ApiStatus.Internal
    @Deprecated(since = "1.20.4", forRemoval = true)
    public void setLastDamageCause(@Nullable EntityDamageEvent event);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体上的最后一个{@link EntityDamageEvent}.
     * <p>原文：
     * Retrieve the last {@link EntityDamageEvent} inflicted on this entity.
     * This event may have been cancelled.
     *
     * @return 最后一个已知的{@link EntityDamageEvent}, 如果迄今为止没有受到伤害, 则为null
     * <p>原文：the last known {@link EntityDamageEvent} or null if hitherto
     *     unharmed
     */
    @Nullable
    public EntityDamageEvent getLastDamageCause();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体的UUID (唯一且持久的id)
     * <p>原文：
     * Returns a unique and persistent id for this entity
     *
     * @return unique id
     */
    @NotNull
    public UUID getUniqueId();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体生存时间刻.
     * <br>
     * 这相当于实体的"年龄".
     * <p>原文：
     * Gets the amount of ticks this entity has lived for.
     * <p>
     * This is the equivalent to "age" in entities.
     *
     * @return 实体年龄
     * <p>原文：Age of entity
     */
    public int getTicksLived();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体生存时间刻.
     * <br>
     * 这相当于实体中的"年龄". 不得少于一个tick.
     * <p>原文：
     * Sets the amount of ticks this entity has lived for.
     * <p>
     * This is the equivalent to "age" in entities. May not be less than one
     * tick.
     *
     * @param value 实体年龄
     * <p>原文：Age of entity
     */
    public void setTicksLived(int value);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为此实体执行指定的{@link EntityEffect}.
     * <br>
     * 这将对实体附近的所有玩家都是可见的.
     * <br>
     * 如果效果不适用于此类实体，则不会播放.
     * <p>原文：
     * Performs the specified {@link EntityEffect} for this entity.
     * <p>
     * This will be viewable to all players near the entity.
     * <p>
     * If the effect is not applicable to this class of entity, it will not play.
     *
     * @param effect Effect to play.
     */
    public void playEffect(@NotNull EntityEffect effect);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体类型
     * <p>原文：
     * Get the type of the entity.
     *
     * @return 实体类型.
     * <p>原文：The entity type.
     */
    @NotNull
    public EntityType getType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体游泳时发出的 {@link Sound}.
     * <p>原文：
     * Get the {@link Sound} this entity makes while swimming.
     *
     * @return 游泳声音
     * <p>原文：the swimming sound
     */
    @NotNull
    public Sound getSwimSound();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体溅入水中时发出的 {@link Sound}. 对于大多数实体, 这只是 {@link Sound#ENTITY_GENERIC_SPLASH}.
     * <p>原文：
     * Get the {@link Sound} this entity makes when splashing in water. For most
     * entities, this is just {@link Sound#ENTITY_GENERIC_SPLASH}.
     *
     * @return 溅水声
     * <p>原文：the splash sound
     */
    @NotNull
    public Sound getSwimSplashSound();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体高速溅入水中时发出的 {@link Sound}. 对于大多数实体, 这只是 {@link Sound#ENTITY_GENERIC_SPLASH}.
     * <p>原文：
     * Get the {@link Sound} this entity makes when splashing in water at high
     * speeds. For most entities, this is just {@link Sound#ENTITY_GENERIC_SPLASH}.
     *
     * @return 溅水声
     * <p>原文：the splash sound
     */
    @NotNull
    public Sound getSwimHighSpeedSplashSound();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此实体是否在载具内.
     * <p>原文：
     * Returns whether this entity is inside a vehicle.
     *
     * @return 此实体是否在载具内.
     * <p>原文：True if the entity is in a vehicle.
     */
    public boolean isInsideVehicle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 离开当前的载具. 如果实体当前在载具中(并从中移除), 则返回true, 否则将返回false.
     * <p>原文：
     * Leave the current vehicle. If the entity is currently in a vehicle (and
     * is removed from it), true will be returned, otherwise false will be
     * returned.
     *
     * @return 是否成功离开.
     * <p>原文：True if the entity was in a vehicle.
     */
    public boolean leaveVehicle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体所在的载具, 若没有则返回null.
     * <p>原文：
     * Get the vehicle that this entity is inside. If there is no vehicle,
     * null will be returned.
     *
     * @return 当前载具
     * <p>原文：The current vehicle.
     */
    @Nullable
    public Entity getVehicle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置是否在客户端上显示实体的自定义名称. 名称将会像玩家一样显示在实体上方.
     * <br>
     * 这个值对玩家无效, 玩家会一直显示.
     * <p>原文：
     * Sets whether or not to display the mob's custom name client side. The
     * name will be displayed above the mob similarly to a player.
     * <p>
     * This value has no effect on players, they will always display their
     * name.
     *
     * @param flag 是否显示自定义名称
     * <p>原文：custom name or not
     */
    public void setCustomNameVisible(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否在客户端上显示实体的自定义名称. 名称将会像玩家一样显示在实体上方.
     * <br>
     * 这个值对玩家无效, 玩家会一直显示.
     * <p>原文：
     * Gets whether or not the mob's custom name is displayed client side.
     * <p>
     * This value has no effect on players, they will always display their
     * name.
     *
     * @return 是否显示自定义名称
     * <p>原文：if the custom name is displayed
     */
    public boolean isCustomNameVisible();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体是否默认可见.
     * <p>
     * 如果此实体默认不可见, 则需要调用
     * {@link Player#showEntity(org.bukkit.plugin.Plugin, org.bukkit.entity.Entity)}
     * 才能使指定玩家看到该实体.
     * <p>原文：
     * Sets whether or not this entity is visible by default.
     *
     * If this entity is not visible by default, then
     * {@link Player#showEntity(org.bukkit.plugin.Plugin, org.bukkit.entity.Entity)}
     * will need to be called before the entity is visible to a given player.
     *
     * @param visible 默认可见性状态
     * <p>原文：default visibility status
     */
    public void setVisibleByDefault(boolean visible);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体是否默认可见.
     * <p>
     * 如果此实体默认不可见, 则需要调用
     * {@link Player#showEntity(org.bukkit.plugin.Plugin, org.bukkit.entity.Entity)}
     * 才能使指定玩家看到该实体.
     * <p>原文：
     * Gets whether or not this entity is visible by default.
     *
     * If this entity is not visible by default, then
     * {@link Player#showEntity(org.bukkit.plugin.Plugin, org.bukkit.entity.Entity)}
     * will need to be called before the entity is visible to a given player.
     *
     * @return 默认可见性状态
     * <p>原文：default visibility status
     */
    public boolean isVisibleByDefault();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前正在追踪此实体的所有玩家.
     * <p>
     * "追踪"意味着此实体已被发送给玩家, 且玩家正在接收其状态更新. 注意: 客户端的 {@code '实体距离'} 设置不会影响实体被追踪的范围.
     * <p>原文：
     * Get all players that are currently tracking this entity.
     * <p>
     * 'Tracking' means that this entity has been sent to the player and that
     * they are receiving updates on its state. Note that the client's {@code
     * 'Entity Distance'} setting does not affect the range at which entities
     * are tracked.
     *
     * @return 正在追踪此实体的玩家集合, 若无则为空集合
     * <p>原文：the players tracking this entity, or an empty set if none
     */
    @NotNull
    Set<Player> getTrackedBy();

    /**
     * Checks to see if a player is currently tracking this entity.
     *
     * @param player the player to check
     * @return if the player is currently tracking this entity
     * @see #getTrackedBy()
     */
    boolean isTrackedBy(@NotNull Player player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体是否有团队颜色(默认白色)的发光.
     * <b>注意:本方法用到了实体的'Glowing'属性, 不是给实体应用发光药水效果.</b>
     * <p>原文：
     * Sets whether the entity has a team colored (default: white) glow.
     *
     * <b>nb: this refers to the 'Glowing' entity property, not whether a
     * glowing potion effect is applied</b>
     *
     * @param flag 是否发光
     * <p>原文：if the entity is glowing
     */
    void setGlowing(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体是否发光.
     * <b>注意:本方法用到了实体的'Glowing'属性, 不是给实体应用发光药水效果.</b>
     * <p>原文：
     * Gets whether the entity is glowing or not.
     *
     * <b>nb: this refers to the 'Glowing' entity property, not whether a
     * glowing potion effect is applied</b>
     *
     * @return 是否发光
     * <p>原文：whether the entity is glowing
     */
    boolean isGlowing();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体是否无敌.
     * <br>
     * 当一个实体无敌时, 它只能被创造性模式中的玩家攻击.
     * <p>原文：
     * Sets whether the entity is invulnerable or not.
     * <p>
     * When an entity is invulnerable it can only be damaged by players in
     * creative mode.
     *
     * @param flag 是否无敌
     * <p>原文：if the entity is invulnerable
     */
    public void setInvulnerable(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体是否无敌.
     * <p>原文：
     * Gets whether the entity is invulnerable or not.
     *
     * @return 是否无敌
     * <p>原文：whether the entity is
     */
    public boolean isInvulnerable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体是否保持安静.
     * <p>原文：
     * Gets whether the entity is silent or not.
     *
     * @return 实体是否保持安静
     * <p>原文：whether the entity is silent.
     */
    public boolean isSilent();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体是否保持安静.
     * <br>
     * 当实体静默时, 它不会发出任何声音.
     * <p>原文：
     * Sets whether the entity is silent or not.
     * <p>
     * When an entity is silent it will not produce any sound.
     *
     * @param flag 实体是否保持安静
     * <p>原文：if the entity is silent
     */
    public void setSilent(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回重力是否适用于此实体.
     * <p>原文：
     * Returns whether gravity applies to this entity.
     *
     * @return 重力是否适用于此实体
     * <p>原文：whether gravity applies
     */
    boolean hasGravity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置重力是否适用于此实体.
     * <p>原文：
     * Sets whether gravity applies to this entity.
     *
     * @param gravity 重力是否适用于此实体
     * <p>原文：whether gravity should apply
     */
    void setGravity(boolean gravity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体能再次使用传送门前需要等待的时间 (以刻为单位).
     * <p>原文：
     * Gets the period of time (in ticks) before this entity can use a portal.
     *
     * @return portal cooldown ticks
     */
    int getPortalCooldown();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体使用传送门前需要等待的时间 (以刻为单位).
     * <p>原文：
     * Sets the period of time (in ticks) before this entity can use a portal.
     *
     * @param cooldown 传送门冷却刻数
     * <p>原文：portal cooldown ticks
     */
    void setPortalCooldown(int cooldown);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体标签集合.
     * <br>
     * 实体的标签数不得超过1024个.
     * <p>原文：
     * Returns a set of tags for this entity.
     * <br>
     * Entities can have no more than 1024 tags.
     *
     * @return 此实体的标签集合
     * <p>原文：a set of tags for this entity
     */
    @NotNull
    Set<String> getScoreboardTags();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为此实体添加一个标签.
     * <br>
     * 实体的标签数不得超过1024个.
     * <p>原文：
     * Add a tag to this entity.
     * <br>
     * Entities can have no more than 1024 tags.
     *
     * @param tag 要添加的标签
     * <p>原文：the tag to add
     * @return 是否成功添加
     * <p>原文：true if the tag was successfully added
     */
    boolean addScoreboardTag(@NotNull String tag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 删除此实体的一个标签.
     * <br>
     * 实体的标签数不得超过1024个.
     * <p>原文：
     * Removes a given tag from this entity.
     *
     * @param tag 要删除的标签
     * <p>原文：the tag to remove
     * @return 是否成功删除
     * <p>原文：true if the tag was successfully removed
     */
    boolean removeScoreboardTag(@NotNull String tag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体被活塞移动时的反应.
     * <p>原文：
     * Returns the reaction of the entity when moved by a piston.
     *
     * @return 反应
     * <p>原文：reaction
     */
    @NotNull
    PistonMoveReaction getPistonMoveReaction();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体当前朝向的最接近的基本 {@link BlockFace} 方向.
     * <br>
     * 不会返回任何非基本方向, 如 {@link BlockFace#UP} 或 {@link BlockFace#DOWN}.
     * <br>
     * {@link Hanging} 实体会覆盖此调用, 因此其行为可能不同.
     * <p>原文：
     * Get the closest cardinal {@link BlockFace} direction an entity is
     * currently facing.
     * <br>
     * This will not return any non-cardinal directions such as
     * {@link BlockFace#UP} or {@link BlockFace#DOWN}.
     * <br>
     * {@link Hanging} entities will override this call and thus their behavior
     * may be different.
     *
     * @return 实体当前的基本朝向
     * <p>原文：the entity's current cardinal facing.
     * @see Hanging
     * @see Directional#getFacing()
     */
    @NotNull
    BlockFace getFacing();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体当前的姿势.
     * <p>
     * <b>注意: 姿势仅在 tick 结束时更新, 因此可能与其他方法不一致. 例如 {@link Player#isSneaking()} 返回 true 并不意味着当前姿势为 {@link Pose#SNEAKING}.</b>
     * <p>原文：
     * Gets the entity's current pose.
     *
     * <b>Note that the pose is only updated at the end of a tick, so may be
     * inconsistent with other methods. eg {@link Player#isSneaking()} being
     * true does not imply the current pose will be {@link Pose#SNEAKING}</b>
     *
     * @return 当前姿势
     * <p>原文：current pose
     */
    @NotNull
    Pose getPose();

    // Paper start
    /**
     * Returns if the entity is in sneak mode
     *
     * @return true if the entity is in sneak mode
     */
    boolean isSneaking();

    /**
     * Sets the sneak mode the entity.
     * <p>
     * Note: For most Entities this does not update Entity's pose
     * and just makes its name tag less visible.
     *
     * @param sneak true if the entity should be sneaking
     */
    void setSneaking(boolean sneak);

    /**
     * Sets the entity's current {@link Pose}.
     *
     * <p>Note: While poses affect some things like hitboxes, they do not change the entity's state
     * (e.g. having {@link Pose#SNEAKING} does not guarantee {@link #isSneaking()} being {@code true}).
     *
     * <p>If applied to the {@link Player}, they might see a different pose client-side.
     *
     * @param pose a new {@link Pose}
     * @see #setPose(Pose, boolean)
     */
    default void setPose(@NotNull Pose pose) {
        setPose(pose, false);
    }

    /**
     * Sets the entity's current {@link Pose}.
     *
     * <p>Note: While poses affect some things like hitboxes, they do not change the entity's state
     * (e.g. having {@link Pose#SNEAKING} does not guarantee {@link #isSneaking()} being {@code true}).
     *
     * <p>If applied to the {@link Player}, they might see a different pose client-side.
     *
     * @param pose a new {@link Pose}
     * @param fixed whether the new {@link Pose} should stay until manually changed
     */
    void setPose(@NotNull Pose pose, boolean fixed);

    /**
     * Checks whether the entity has a fixed {@link Pose}
     *
     * @see #setPose(Pose, boolean)
     * @return whether the entity has a fixed {@link Pose}
     */
    boolean hasFixedPose();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体所属的生成类别.
     * <p>原文：
     * Get the category of spawn to which this entity belongs.
     *
     * @return 实体的生成类别
     * <p>原文：the entity´s category spawn
     */
    @NotNull
    SpawnCategory getSpawnCategory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此实体是否已生成在世界中.
     * <br>
     * 未在世界中生成的实体不会被 tick 更新, 不会发送给玩家, 也不会保存到服务器文件中.
     * <p>原文：
     * Checks if this entity has been spawned in a world. <br>
     * Entities not spawned in a world will not tick, be sent to players, or be
     * saved to the server files.
     *
     * @return 实体是否已生成在世界中
     * <p>原文：whether the entity has been spawned in a world
     */
    boolean isInWorld();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体的 NBT 字符串.
     * <p>
     * 不应将此字符串作为可序列化的值使用.
     * <p>原文：
     * Get this entity as an NBT string.
     * <p>
     * This string should not be relied upon as a serializable value.
     *
     * @return NBT 字符串, 若无法生成则返回 null
     * <p>原文：the NBT string or null if one cannot be made
     */
    @Nullable
    @ApiStatus.Experimental
    String getAsString();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个表示此实体当前状态的 {@link EntitySnapshot}.
     * <p>原文：
     * Crates an {@link EntitySnapshot} representing the current state of this entity.
     *
     * @return 表示此实体的快照, 若无法创建则返回 null
     * <p>原文：a snapshot representing this entity or null if one cannot be made
     */
    @Nullable
    @ApiStatus.Experimental
    EntitySnapshot createSnapshot();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建此实体及其所有数据的副本. 不会在世界中生成该副本.
     * <br>
     * <b>注意:</b> 玩家无法被复制.
     * <p>原文：
     * Creates a copy of this entity and all its data. Does not spawn the copy in
     * the world. <br>
     * <b>Note:</b> Players cannot be copied.
     *
     * @return 此实体的副本
     * <p>原文：a copy of this entity.
     */
    @NotNull
    @ApiStatus.Experimental
    Entity copy();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建此实体及其所有数据的副本. 在给定位置生成该副本.
     * <br>
     * <b>注意:</b> 玩家无法被复制.
     * <p>原文：
     * Creates a copy of this entity and all its data. Spawns the copy at the given location. <br>
     * <b>Note:</b> Players cannot be copied.
     *
     * @param to 要复制到的位置
     * <p>原文：the location to copy to
     * @return 此实体的副本
     * <p>原文：a copy of this entity.
     */
    @NotNull
    @ApiStatus.Experimental
    Entity copy(@NotNull Location to);

    // Spigot start
    public class Spigot extends CommandSender.Spigot {

    }

    @NotNull
    @Override
    Spigot spigot();
    // Spigot end

    // Paper start
    /**
     * Gets the entity's display name formatted with their team prefix/suffix and
     * the entity's default hover/click events.
     *
     * @return the team display name
     */
    net.kyori.adventure.text.@NotNull Component teamDisplayName();

    @NotNull
    @Override
    default net.kyori.adventure.text.event.HoverEvent<net.kyori.adventure.text.event.HoverEvent.ShowEntity> asHoverEvent(final @NotNull java.util.function.UnaryOperator<net.kyori.adventure.text.event.HoverEvent.ShowEntity> op) {
        return net.kyori.adventure.text.event.HoverEvent.showEntity(op.apply(net.kyori.adventure.text.event.HoverEvent.ShowEntity.of(this.getType().getKey(), this.getUniqueId(), this.customName())));
    }

    /**
     * Gets the location where this entity originates from.
     * <p>
     * This value can be null if the entity hasn't yet been added to the world.
     *
     * @return Location where entity originates or null if not yet added
     */
    @Nullable
    Location getOrigin();

    /**
     * Returns whether this entity was spawned from a mob spawner.
     *
     * @return True if entity spawned from a mob spawner
     */
    boolean fromMobSpawner();

    /**
     * Gets the latest chunk an entity is currently or was in.
     *
     * @return The current, or most recent chunk if the entity is invalid (which may load the chunk)
     */
    @NotNull
    default Chunk getChunk() {
        // TODO remove impl here
        return getLocation().getChunk();
    }

    /**
     * @return The {@link org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason} that initially spawned this entity. <!-- Paper - added "initially" to clarify that the SpawnReason doesn't change after the Entity was initially spawned" -->
     */
    @NotNull
    org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason getEntitySpawnReason();

    /**
     * Check if entity is underwater
     */
    boolean isUnderWater();

    /**
     * Check if entity is in rain
     */
    boolean isInRain();

    /**
     * Check if entity is in bubble column
     *
     * @deprecated check the block at the position of the entity
     */
    @Deprecated(since = "1.21.5")
    default boolean isInBubbleColumn() {
        return this.getWorld().getBlockAt(this.getLocation()).getType() == Material.BUBBLE_COLUMN;
    }

    /**
     * Check if entity is in water or rain
     *
     * @deprecated use {@link #isInWater()} and {@link #isInRain()}
     */
    @Deprecated(since = "1.21.5")
    default boolean isInWaterOrRain() {
        return this.isInWater() || this.isInRain();
    }

    /**
     * Check if entity is in water or bubble column
     *
     * @deprecated use {@link #isInWater()}, bubble column is considered as water
     */
    @Deprecated(since = "1.21.5")
    default boolean isInWaterOrBubbleColumn() {
        return this.isInWater();
    }

    /**
     * Check if entity is in water or rain or bubble column
     *
     * @deprecated bubble column is considered as water, use {@link #isInWater()} and {@link #isInRain()}
     */
    @Deprecated(since = "1.21.5")
    default boolean isInWaterOrRainOrBubbleColumn() {
        return this.isInWaterOrRain();
    }

    /**
     * Check if entity is in lava
     */
    boolean isInLava();

    /**
     * Check if entity is inside a ticking chunk
     */
    boolean isTicking();

    /**
     * Returns a set of {@link Player Players} within this entity's tracking range (players that can "see" this entity).
     *
     * @return players in tracking range
     * @deprecated slightly misleading name, use {@link #getTrackedBy()}
     */
    @Deprecated
    @NotNull Set<Player> getTrackedPlayers();

    /**
     * Spawns the entity in the world at the given {@link Location} with the default spawn reason.
     * <p>
     * This will not spawn the entity if the entity is already spawned or has previously been despawned.
     * <p>
     * Also, this method will fire the same events as a normal entity spawn.
     *
     * @param location the location to spawn the entity at
     * @return whether the entity was successfully spawned
     * @since 1.17.1
     */
    default boolean spawnAt(@NotNull Location location) {
        return spawnAt(location, CreatureSpawnEvent.SpawnReason.DEFAULT);
    }

    /**
     * Spawns the entity in the world at the given {@link Location} with the reason given.
     * <p>
     * This will not spawn the entity if the entity is already spawned or has previously been despawned.
     * <p>
     * Also, this method will fire the same events as a normal entity spawn.
     *
     * @param location the location to spawn the entity at
     * @param reason   the reason for the entity being spawned
     * @return whether the entity was successfully spawned
     * @since 1.17.1
     */
    boolean spawnAt(@NotNull Location location, @NotNull CreatureSpawnEvent.SpawnReason reason);

    /**
     * Check if entity is inside powdered snow.
     *
     * @return true if in powdered snow.
     */
    boolean isInPowderedSnow();

    /**
     * Gets the x-coordinate of this entity
     *
     * @return x-coordinate
     */
    double getX();

    /**
     * Gets the y-coordinate of this entity
     *
     * @return y-coordinate
     */
    double getY();

    /**
     * Gets the z-coordinate of this entity
     *
     * @return z-coordinate
     */
    double getZ();

    /**
     * Gets this entity's pitch
     *
     * @see Location#getPitch()
     * @return the entity's pitch
     */
    float getPitch();

    /**
     * Gets this entity's yaw
     *
     * @see Location#getYaw()
     * @return the entity's yaw
     */
    float getYaw();
    // Paper end

    // Paper start - Collision API
    /**
     * Checks for any collisions with the entity's bounding box at the provided location.
     * This will check for any colliding entities (boats, shulkers) / worldborder / blocks.
     * Does not load chunks that are within the bounding box at the specified location.
     *
     * @param location the location to check collisions in
     * @return collides or not
     */
    boolean collidesAt(@NotNull Location location);

    /**
     * This checks using the given boundingbox as the entity's boundingbox if the entity would collide with anything.
     * This will check for any colliding entities (boats, shulkers) / worldborder / blocks.
     * Does not load chunks that are within the bounding box.
     *
     * @param boundingBox the box to check collisions in
     * @return collides or not
     */
    boolean wouldCollideUsing(@NotNull BoundingBox boundingBox);
    // Paper end - Collision API

    // Paper start - Folia schedulers
    /**
     * Returns the task scheduler for this entity. The entity scheduler can be used to schedule tasks
     * that are guaranteed to always execute on the tick thread that owns the entity.
     * <p><b>If you do not need/want to make your plugin run on Folia, use {@link org.bukkit.Server#getScheduler()} instead.</b></p>
     * @return the task scheduler for this entity.
     * @see io.papermc.paper.threadedregions.scheduler.EntityScheduler
     */
    @NotNull io.papermc.paper.threadedregions.scheduler.EntityScheduler getScheduler();
    // Paper end - Folia schedulers

    // Paper start - entity scoreboard name
    /**
     * Gets the string name of the entity used to track it in {@link org.bukkit.scoreboard.Scoreboard Scoreboards}.
     *
     * @return the scoreboard entry name
     * @see org.bukkit.scoreboard.Scoreboard#getScores(String)
     * @see org.bukkit.scoreboard.Scoreboard#getEntries()
     */
    @NotNull String getScoreboardEntryName();
    // Paper end - entity scoreboard name

    // Paper start - broadcast hurt animation
    /**
     * Broadcasts a hurt animation. This fakes incoming damage towards the target entity.
     * <p>
     * The target players cannot include {@code this} player. For self-damage, use
     * {@link Player#sendHurtAnimation(float)}.
     *
     * @param players the players to broadcast to (cannot include {@code this}
     * @throws IllegalArgumentException if {@code this} is contained in {@code players}
     */
    void broadcastHurtAnimation(@NotNull java.util.Collection<Player> players);
    // Paper end - broadcast hurt animation
}
