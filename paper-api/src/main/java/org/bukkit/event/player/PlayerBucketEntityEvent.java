package org.bukkit.event.player;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家用桶捕获实体时触发此事件.
 * <p>原文：
 * This event is called whenever a player captures an entity in a bucket.
 */
public class PlayerBucketEntityEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    protected final Entity entity;
    private final ItemStack originalBucket;
    private final ItemStack entityBucket;
    private final EquipmentSlot hand;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerBucketEntityEvent(@NotNull Player player, @NotNull Entity entity, @NotNull ItemStack originalBucket, @NotNull ItemStack entityBucket, @NotNull EquipmentSlot hand) {
        super(player);
        this.entity = entity;
        this.originalBucket = originalBucket;
        this.entityBucket = entityBucket;
        this.hand = hand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被放入桶中的 {@link Entity}.
     * <p>原文：
     * Gets the {@link Entity} being put into the bucket.
     *
     * @return 被放入桶中的 {@link Entity}
     * <p>原文：The {@link Entity} being put into the bucket
     */
    @NotNull
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于捕获 {@link Entity} 的桶.
     * <p>原文：
     * Gets the bucket used to capture the {@link Entity}.
     * <br>
     * This refers to the bucket clicked with, eg {@link Material#WATER_BUCKET}.
     *
     * @return 使用的桶
     * <p>原文：The used bucket
     */
    @NotNull
    public ItemStack getOriginalBucket() {
        return this.originalBucket;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 {@link Entity} 将被放入的桶.
     * <p>原文：
     * Gets the bucket that the {@link Entity} will be put into.
     * <br>
     * This refers to the bucket with the entity, eg
     * {@link Material#PUFFERFISH_BUCKET}.
     *
     * @return {@link Entity} 将被放入的桶
     * <p>原文：The bucket that the {@link Entity} will be put into
     */
    @NotNull
    public ItemStack getEntityBucket() {
        return this.entityBucket;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于捕获实体的手.
     * <p>原文：
     * Get the hand that was used to bucket the entity.
     *
     * @return 使用的手
     * <p>原文：the hand
     */
    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
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
