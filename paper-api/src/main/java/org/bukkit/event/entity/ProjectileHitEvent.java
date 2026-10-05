package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当弹射物命中物体时调用。
 * <p>原文：
 * Called when a projectile hits an object
 */
public class ProjectileHitEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity hitEntity;
    private final Block hitBlock;
    private final BlockFace hitFace;

    private boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public ProjectileHitEvent(@NotNull final Projectile projectile) {
        this(projectile, null, null, null);
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public ProjectileHitEvent(@NotNull final Projectile projectile, @Nullable Entity hitEntity) {
        this(projectile, hitEntity, null, null);
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public ProjectileHitEvent(@NotNull final Projectile projectile, @Nullable Block hitBlock) {
        this(projectile, null, hitBlock, null);
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public ProjectileHitEvent(@NotNull final Projectile projectile, @Nullable Entity hitEntity, @Nullable Block hitBlock) {
        this(projectile, hitEntity, hitBlock, null);
    }

    @ApiStatus.Internal
    public ProjectileHitEvent(@NotNull final Projectile projectile, @Nullable Entity hitEntity, @Nullable Block hitBlock, @Nullable BlockFace hitFace) {
        super(projectile);
        this.hitEntity = hitEntity;
        this.hitBlock = hitBlock;
        this.hitFace = hitFace;
    }

    @NotNull
    @Override
    public Projectile getEntity() {
        return (Projectile) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被命中的实体（如果命中实体）。
     * <p>原文：
     * Gets the entity that was hit, if it was an entity that was hit.
     *
     * @return 被命中的实体，否则返回 null
     * <p>原文：hit entity or else {@code null}
     */
    @Nullable
    public Entity getHitEntity() {
        return this.hitEntity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被命中的方块（如果命中方块）。
     * <p>原文：
     * Gets the block that was hit, if it was a block that was hit.
     *
     * @return 被命中的方块，否则返回 null
     * <p>原文：hit block or else {@code null}
     */
    @Nullable
    public Block getHitBlock() {
        return this.hitBlock;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被命中的方块面（如果命中方块且事件中提供了该面）。
     * <p>原文：
     * Gets the block face that was hit, if it was a block that was hit and the
     * face was provided in the event.
     *
     * @return 被命中的面，否则返回 null
     * <p>原文：hit face or else {@code null}
     */
    @Nullable
    public BlockFace getHitBlockFace() {
        return this.hitFace;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 是否取消弹射物命中时发生的动作。
     * <p>
     * 对于实体，它将不会碰撞（除非是烟花火箭，然后使用 {@link FireworkExplodeEvent}）。
     * <br>
     * 对于方块，某些方块（例如标靶、钟）将不会执行相关动作。
     * <br>
     * 这不会阻止方块碰撞，除非它们各自的事件被取消，否则爆炸仍会发生。
     * <p>原文：
     * Whether to cancel the action that occurs when the projectile hits.
     * <p>
     * In the case of an entity, it will not collide (unless it's a firework,
     * then use {@link FireworkExplodeEvent}).
     * <br>
     * In the case of a block, some blocks (e.g. target block, bell) will not
     * perform the action associated.
     * <p>
     * This does NOT prevent block collisions, and explosions will still occur
     * unless their respective events are cancelled.
     *
     * @param cancel 如果你希望取消此事件则设置为 true
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
