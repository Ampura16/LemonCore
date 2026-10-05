package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当世界中的 TNT 方块被激活时触发.
 * <p>
 * 如果 TNT 激活事件被取消，TNT 方块将不会被激活.
 * <p>原文：
 * Called when a block of TNT in the world become primed.
 * <p>
 * If this event is cancelled, the block of TNT will not become primed.
 */
public class TNTPrimeEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PrimeCause igniteCause;
    private final Entity primingEntity;
    private final Block primingBlock;

    private boolean cancelled;

    @ApiStatus.Internal
    public TNTPrimeEvent(@NotNull final Block block, @NotNull final PrimeCause igniteCause, @Nullable final Entity primingEntity, @Nullable final Block primingBlock) {
        super(block);
        this.igniteCause = igniteCause;
        this.primingEntity = primingEntity;
        this.primingBlock = primingBlock;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 TNT 被激活的原因.
     * <p>原文：
     * Get the cause of the TNT becoming primed.
     *
     * @return 原因
     * <p>原文：the cause
     */
    @NotNull
    public PrimeCause getCause() {
        return this.igniteCause;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取导致 TNT 被激活的实体.
     * <p>原文：
     * Get the entity that caused the TNT to be primed.
     *
     * @return 导致 TNT 被激活的实体，如果不是由实体导致的则返回 null
     * <p>原文：the entity that caused the TNT to be primed, or {@code null} if it was
     * not caused by an entity.
     */
    @Nullable
    public Entity getPrimingEntity() {
        return this.primingEntity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取导致 TNT 被激活的方块.
     * <p>原文：
     * Get the block that caused the TNT to be primed.
     *
     * @return 导致 TNT 被激活的方块，如果不是由方块导致的则返回 null
     * <p>原文：the block that caused the TNT to be primed, or {@code null} if it was not
     * caused by a block.
     */
    @Nullable
    public Block getPrimingBlock() {
        return this.primingBlock;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

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

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示 TNT 方块被激活原因的枚举.
     * <p>原文：
     * An enum to represent the cause of a TNT block becoming primed.
     */
    public enum PrimeCause {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当 TNT 因火势蔓延而被激活时.
         * <p>原文：
         * When TNT is primed by fire spreading.
         */
        FIRE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当 TNT 因红石信号而被激活时.
         * <p>原文：
         * When TNT is primed by a redstone signal.
         */
        REDSTONE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当 TNT 被玩家直接交互而激活时.
         * <p>原文：
         * When TNT is primed by a player interacting with it directly.
         */
        PLAYER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当 TNT 因附近爆炸而被激活时.
         * <p>原文：
         * When TNT is primed by a nearby explosion.
         */
        EXPLOSION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当 TNT 被燃烧的投射物击中而激活时.
         * <p>原文：
         * When TNT is primed after getting hit with a burning projectile.
         */
        PROJECTILE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当不稳定方块状态设置为 true 的 TNT 被破坏时.
         * <p>
         * 注意：取消此原因导致的激活事件将阻止激活的 TNT 生成，但不会阻止方块被破坏.
         * <p>原文：
         * When TNT with the unstable block state set to {@code true} is broken.
         * <p>
         * Note: Canceling a prime event with this cause will stop the primed
         * TNT from spawning but will not stop the block from being broken.
         */
        BLOCK_BREAK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当 TNT 被持有打火石的发射器激活时.
         * <p>
         * 注意：发射器直接发射 TNT 时不会触发此事件.
         * <p>原文：
         * When TNT is primed by a dispenser holding flint and steel.
         * <p>
         * Note: This event is not called for a dispenser dispensing TNT
         * directly.
         */
        DISPENSER;
    }
}
