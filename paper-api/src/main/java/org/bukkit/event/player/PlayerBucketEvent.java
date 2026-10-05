package org.bukkit.event.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家使用桶时触发本事件.
 * <p>原文：
 * Called when a player interacts with a Bucket
 */
public abstract class PlayerBucketEvent extends PlayerEvent implements Cancellable {

    private final Block block;
    private final Block blockClicked;
    private final BlockFace blockFace;
    private final Material bucket;
    private final EquipmentSlot hand;
    private ItemStack itemStack;

    private boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(since = "1.14.4", forRemoval = true)
    public PlayerBucketEvent(@NotNull final Player player, @NotNull final Block blockClicked, @NotNull final BlockFace blockFace, @NotNull final Material bucket, @NotNull final ItemStack itemInHand) {
        this(player, null, blockClicked.getRelative(blockFace), blockFace, bucket, itemInHand, EquipmentSlot.HAND);
    }

    @ApiStatus.Internal
    @Deprecated(since = "1.19.2", forRemoval = true)
    public PlayerBucketEvent(@NotNull final Player player, @NotNull final Block block, @NotNull final Block blockClicked, @NotNull final BlockFace blockFace, @NotNull final Material bucket, @NotNull final ItemStack itemInHand) {
        this(player, block, blockClicked, blockFace, bucket, itemInHand, EquipmentSlot.HAND);
    }

    @ApiStatus.Internal
    public PlayerBucketEvent(@NotNull final Player player, @NotNull final Block block, @NotNull final Block blockClicked, @NotNull final BlockFace blockFace, @NotNull final Material bucket, @NotNull final ItemStack itemInHand, @NotNull final EquipmentSlot hand) {
        super(player);
        this.block = block;
        this.blockClicked = blockClicked;
        this.blockFace = blockFace;
        this.itemStack = itemInHand;
        if (bucket != null && bucket.isLegacy()) {
            this.bucket = Bukkit.getUnsafe().fromLegacy(new MaterialData(bucket), true);
        } else {
            this.bucket = bucket;
        }
        this.hand = hand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本事件所涉及的方块 (岩浆/水方块等).
     * <p>原文：
     * Gets the block involved in this event.
     *
     * @return 方块
     * <p>原文：The Block which block is involved in this event
     */
    @NotNull
    public final Block getBlock() {
        return this.block;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家正在对着哪个方块使用桶,返回那个方块.
     * <p>原文：
     * Return the block clicked
     *
     * @return 方块
     * <p>原文：the clicked block
     */
    @NotNull
    public Block getBlockClicked() {
        return this.blockClicked;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取方块被点击的面.
     * <p>
     * 每个方块有6个面,玩家究竟是点击的哪个呢.
     * <p>原文：
     * Get the face on the clicked block
     *
     * @return the clicked face
     */
    @NotNull
    public BlockFace getBlockFace() {
        return this.blockFace;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回玩家手里的桶的类型.
     * <p>
     * 假设玩家使用空桶装水,则会返回空桶的类型
     * <p>原文：
     * Returns the bucket used in this event
     *
     * @return 使用中的桶
     * <p>原文：the used bucket
     */
    @NotNull
    public Material getBucket() {
        return this.bucket;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在此事件中使用的手.
     * <p>原文：
     * Get the hand that was used in this event.
     *
     * @return 使用的手
     * <p>原文：the hand
     */
    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到使用桶之后会得到的物品堆叠.
     * <p>
     * 假设玩家使用空桶装水,则会返回一个装满水的桶
     * <p>原文：
     * Get the resulting item in hand after the bucket event
     *
     * @return 返回事件结果
     * <p>原文：ItemStack hold in hand after the event.
     */
    @Nullable
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置使用桶之后得到的物品.
     * <p>
     * 假设玩家使用空桶装水,原来玩家会一个装满水的桶,你可以使用这个方法改变这个结果. 例如改成岩浆桶.
     * <p>原文：
     * Set the item in hand after the event
     *
     * @param itemStack 新的事件结果
     * <p>原文：the new held ItemStack after the bucket event.
     */
    public void setItemStack(@Nullable ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
