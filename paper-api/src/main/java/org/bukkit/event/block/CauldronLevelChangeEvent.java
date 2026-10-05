package org.bukkit.event.block;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 炼药锅水位发生变化时触发本事件.
 */
public class CauldronLevelChangeEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity entity;
    private final ChangeReason reason;
    private final BlockState newState;

    private boolean cancelled;

    @ApiStatus.Internal
    public CauldronLevelChangeEvent(@NotNull Block block, @Nullable Entity entity, @NotNull ChangeReason reason, @NotNull BlockState newBlock) {
        super(block);
        this.entity = entity;
        this.reason = reason;
        this.newState = newBlock;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取触发本事件的实体,值有可能为null.
     * <p>原文：
     * Get entity which did this. May be {@code null}.
     *
     * @return 触发本事件的实体
     * <p>原文：acting entity
     */
    @Nullable
    public Entity getEntity() {
        return this.entity;
    }

    @NotNull
    public ChangeReason getReason() {
        return this.reason;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取炼药锅的新状态.
     * <p>原文：
     * Gets the new state of the cauldron.
     *
     * @return 将被更改的方块的状态
     * <p>原文：The block state of the block that will be changed
     */
    @NotNull
    public BlockState getNewState() {
        return this.newState;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取炼药锅的旧水位.
     * <p>原文：
     * Gets the old level of the cauldron.
     *
     * @return 旧水位
     * <p>原文：old level
     * @see #getBlock()
     * @deprecated 并非所有炼药锅的内容物都是 Levelled 类型的
     * <p>原文：not all cauldron contents are Levelled
     */
    @Deprecated(since = "1.17")
    public int getOldLevel() {
        BlockData oldBlock = this.getBlock().getBlockData();
        return (oldBlock instanceof Levelled) ? ((Levelled) oldBlock).getLevel() : ((oldBlock.getMaterial() == Material.CAULDRON) ? 0 : 3);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取炼药锅的新水位.
     * <p>原文：
     * Gets the new level of the cauldron.
     *
     * @return 新水位
     * <p>原文：new level
     * @see #getNewState()
     * @deprecated 并非所有炼药锅的内容物都是 Levelled 类型的
     * <p>原文：not all cauldron contents are Levelled
     */
    @Deprecated(since = "1.17")
    public int getNewLevel() {
        BlockData newBlock = this.newState.getBlockData();
        return (newBlock instanceof Levelled) ? ((Levelled) newBlock).getLevel() : ((newBlock.getMaterial() == Material.CAULDRON) ? 0 : 3);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置炼药锅的新水位.
     * <p>原文：
     * Sets the new level of the cauldron.
     *
     * @param newLevel 新水位
     * <p>原文：new level
     * @see #getNewState()
     * @deprecated 并非所有炼药锅的内容物都是 Levelled 类型的
     * <p>原文：not all cauldron contents are Levelled
     */
    @Deprecated(since = "1.17")
    public void setNewLevel(int newLevel) {
        Preconditions.checkArgument(0 <= newLevel && newLevel <= 3, "Cauldron level out of bounds 0 <= %s <= 3", newLevel);
        if (newLevel == 0) {
            this.newState.setType(Material.CAULDRON);
        } else if (this.newState.getBlockData() instanceof Levelled) {
            ((Levelled) this.newState.getBlockData()).setLevel(newLevel);
        } else {
            // Error, non-levellable block
        }
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

    public enum ChangeReason {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家将炼药锅内的水装入水桶.
         * <p>原文：
         * Player emptying the cauldron by filling their bucket.
         */
        BUCKET_FILL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家用水桶装满炼药锅.
         * <p>原文：
         * Player filling the cauldron by emptying their bucket.
         */
        BUCKET_EMPTY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家用水瓶将炼药锅内的水用完.
         * <p>原文：
         * Player emptying the cauldron by filling their bottle.
         */
        BOTTLE_FILL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家将瓶子内的水装入炼药锅.
         * <p>原文：
         * Player filling the cauldron by emptying their bottle.
         */
        BOTTLE_EMPTY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家用炼药锅内的水清洗旗帜.
         * <p>原文：
         * Player cleaning their banner.
         */
        BANNER_WASH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家用炼药锅内的水清洗护甲装备.
         * <p>原文：
         * Player cleaning their armor.
         */
        ARMOR_WASH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家清洗潜影盒.
         * <p>原文：
         * Player cleaning a shulker box.
         */
        SHULKER_WASH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家跳进炼药锅灭火.
         * <p>原文：
         * Entity being extinguished.
         */
        EXTINGUISH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 天气过于炎热导致炼药锅内的水自然蒸发.
         * <p>原文：
         * Evaporating due to biome dryness.
         */
        EVAPORATE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 自然流体源导致的填充, 例如雨水或滴水石.
         * <p>原文：
         * Filling due to natural fluid sources, e.g. rain or dripstone.
         */
        NATURAL_FILL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 未知情况.
         * <p>原文：
         * Unknown.
         */
        UNKNOWN
    }
}
