package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个方块被点燃时触发.
 * <p>
 * 若要判断玩家是否放了个火方块,请看{@link BlockPlaceEvent}.
 * <p>
 * 若本事件被取消，方块将不会被点燃
 * <p>原文：
 * Called when a block is ignited. If you want to catch when a Player places
 * fire, you need to use {@link BlockPlaceEvent}.
 * <p>
 * If this event is cancelled, the block will not be ignited.
 */
public class BlockIgniteEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final IgniteCause cause;
    private final Entity ignitingEntity;
    private final Block ignitingBlock;

    private boolean cancelled;

    @ApiStatus.Internal
    public BlockIgniteEvent(@NotNull final Block block, @NotNull final IgniteCause cause, @Nullable final Entity ignitingEntity) {
        this(block, cause, ignitingEntity, null);
    }

    @ApiStatus.Internal
    public BlockIgniteEvent(@NotNull final Block block, @NotNull final IgniteCause cause, @NotNull final Block ignitingBlock) {
        this(block, cause, null, ignitingBlock);
    }

    @ApiStatus.Internal
    public BlockIgniteEvent(@NotNull final Block block, @NotNull final IgniteCause cause, @Nullable final Entity ignitingEntity, @Nullable final Block ignitingBlock) {
        super(block);
        this.cause = cause;
        this.ignitingEntity = ignitingEntity;
        this.ignitingBlock = ignitingBlock;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取点燃方块的原因.
     * <p>原文：
     * Gets the cause of block ignite.
     *
     * @return 点燃方块的原因
     * <p>原文：An IgniteCause value detailing the cause of block ignition
     */
    @NotNull
    public IgniteCause getCause() {
        return this.cause;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取点燃方块的玩家,如果不是玩家点燃的就返回null.
     * <p>原文：
     * Gets the player who ignited this block
     *
     * @return 点燃方块/放置火方块的玩家，如果不是玩家点燃的返回null
     * <p>原文：The Player that placed/ignited the fire block, or {@code null} if not ignited by a Player.
     */
    @Nullable
    public Player getPlayer() {
        if (this.ignitingEntity instanceof Player) {
            return (Player) this.ignitingEntity;
        }

        return null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取点燃方块的实体.
     * <p>原文：
     * Gets the entity who ignited this block
     *
     * @return 点燃方块的实体，如果不是实体点燃的返回null
     * <p>原文：The Entity that placed/ignited the fire block, or {@code null} if not ignited by an Entity.
     */
    @Nullable
    public Entity getIgnitingEntity() {
        return this.ignitingEntity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取点燃方块的方块.
     * <p>原文：
     * Gets the block which ignited this block
     *
     * @return 点燃方块的方块,如果不是方块点燃的返回null
     * <p>原文：The Block that placed/ignited the fire block, or {@code null} if not ignited by a Block.
     */
    @Nullable
    public Block getIgnitingBlock() {
        return this.ignitingBlock;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 枚举：方块被点燃的原因
     * <p>原文：
     * An enum to specify the cause of the ignite
     */
    public enum IgniteCause {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 方块被岩浆点燃
         * <p>原文：
         * Block ignition caused by lava.
         */
        LAVA,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 方块被发射器/玩家使用打火石点燃
         * <p>原文：
         * Block ignition caused by a player or dispenser using flint-and-steel.
         */
        FLINT_AND_STEEL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 方块由于火的蔓延而被点燃
         * <p>原文：
         * Block ignition caused by dynamic spreading of fire.
         */
        SPREAD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 方块被闪电点燃
         * <p>原文：
         * Block ignition caused by lightning.
         */
        LIGHTNING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 方块被火球点燃
         * <p>原文：
         * Block ignition caused by an entity using a fireball.
         */
        FIREBALL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 方块被末影水晶点燃
         * <p>原文：
         * Block ignition caused by an Ender Crystal.
         */
        ENDER_CRYSTAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 方块被爆炸点燃
         * <p>原文：
         * Block ignition caused by explosion.
         */
        EXPLOSION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 方块被燃烧的箭点燃
         * <p>原文：
         * Block ignition caused by a flaming arrow.
         */
        ARROW
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
}
