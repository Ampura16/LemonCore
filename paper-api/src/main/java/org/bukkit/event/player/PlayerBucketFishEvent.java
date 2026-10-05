package org.bukkit.event.player;

import org.bukkit.Material;
import org.bukkit.Warning;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家尝试将鱼放入桶中时触发此事件.
 * <p>原文：
 * This event is called whenever a player attempts to put a fish in a bucket.
 *
 * @deprecated 请使用更通用的 {@link PlayerBucketEntityEvent}
 * <p>原文：Use the more generic {@link PlayerBucketEntityEvent}
 */
@Deprecated(since = "1.16.5")
@Warning
public class PlayerBucketFishEvent extends PlayerBucketEntityEvent {

    @ApiStatus.Internal
    public PlayerBucketFishEvent(@NotNull Player player, @NotNull Fish fish, @NotNull ItemStack waterBucket, @NotNull ItemStack fishBucket, @NotNull EquipmentSlot hand) {
        super(player, fish, waterBucket, fishBucket, hand);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件涉及的鱼.
     * <p>原文：
     * Gets the fish involved with this event.
     *
     * @return 此事件涉及的鱼
     * <p>原文：The fish involved with this event
     */
    @NotNull
    @Override
    public Fish getEntity() {
        return (Fish) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取使用的桶.
     * <p>原文：
     * Gets the bucket used.
     * <br>
     * This refers to the bucket clicked with, ie {@link Material#WATER_BUCKET}.
     *
     * @return 使用的桶
     * <p>原文：The used bucket
     * @deprecated 请使用 {@link #getOriginalBucket()}
     * <p>原文：Use {@link #getOriginalBucket()}
     */
    @NotNull
    @Deprecated(since = "1.16.5")
    public ItemStack getWaterBucket() {
        return this.getOriginalBucket();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取鱼将被放入的桶.
     * <p>原文：
     * Gets the bucket that the fish will be put into.
     * <br>
     * This refers to the bucket with the fish, ie
     * {@link Material#PUFFERFISH_BUCKET}.
     *
     * @return 鱼将被放入的桶
     * <p>原文：The bucket that the fish will be put into
     * @deprecated 请使用 {@link #getEntityBucket()}
     * <p>原文：Use {@link #getEntityBucket()}
     */
    @NotNull
    @Deprecated(since = "1.16.5")
    public ItemStack getFishBucket() {
        return this.getEntityBucket();
    }
}
