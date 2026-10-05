package org.bukkit.entity;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个下落方块.
 * <p>原文：
 * Represents a falling block
 */
public interface FallingBlock extends Entity {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取下落方块的类型.
     * <p>原文：
     * Get the Material of the falling block
     *
     * @return 方块的材质
     * <p>原文：Material of the block
     * @deprecated 请使用 {@link #getBlockData()}
     * <p>原文：use {@link #getBlockData()}
     */
    @Deprecated(since = "1.6.2")
    @NotNull
    Material getMaterial();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取下落方块的方块数据.
     * <p>原文：
     * Get the data for the falling block
     *
     * @return 方块的数据
     * <p>原文：data of the block
     */
    @NotNull
    BlockData getBlockData();
    // Paper start
    /**
     * Sets the data for the falling block.
     * <br>
     * Any potential additional data currently stored in the falling blocks {@link #getBlockState()} will be
     * purged by calling this setter.
     *
     * @param blockData the data to use as the block
     */
    void setBlockData(@NotNull BlockData blockData);

    /**
     * Get the data of the falling block represented as a {@link org.bukkit.block.BlockState BlockState}
     * which includes potential NBT data that gets applied when the block gets placed on landing.
     *
     * @return the BlockState representing this block
     */
    @NotNull
    org.bukkit.block.BlockState getBlockState();

    /**
     * Sets the {@link BlockData} and possibly present block entity data for the falling block.
     *
     * @param blockState the BlockState to use
     */
    void setBlockState(@NotNull org.bukkit.block.BlockState blockState);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取下落方块被阻挡时是否会变为掉落物.
     * <p>
     * 请注意, 如果 {@link #getCancelDrop()} 为 {@code true}, 则下落方块将不会掉落物品,
     * 无论此方法是否返回 {@code true}.
     * <p>原文：
     * Get if the falling block will break into an item if it cannot be placed.
     * <p>
     * Note that if {@link #getCancelDrop()} is {@code true}, the falling block
     * will not drop an item regardless of whether or not the returned value is
     * {@code true}.
     *
     * @return 方块在被阻挡时是否会变为掉落物.
     * <p>原文：true if the block will break into an item when obstructed
     */
    boolean getDropItem();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置下落方块被阻挡时是否会变为掉落物.
     * <p>
     * 请注意, 如果 {@link #getCancelDrop()} 为 {@code true}, 则下落方块将不会掉落物品,
     * 无论是否设置为 {@code true}.
     * <p>原文：
     * Set if the falling block will break into an item if it cannot be placed.
     * <p>
     * Note that if {@link #getCancelDrop()} is {@code true}, the falling block
     * will not drop an item regardless of whether or not the value is set to
     * {@code true}.
     *
     * @param drop 当被阻挡时变为掉落物时返回 true.
     * <p>原文：true to break into an item when obstructed
     */
    void setDropItem(boolean drop);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取下落方块落地后是否会消失且不掉落物品.
     * <p>
     * 与 {@link #getDropItem()} 不同, 此属性会阻止方块落地时形成方块, 使其消失.
     * 如果此属性为 true 且 {@link #getDropItem()} 为 true, 则 <strong>不会</strong> 掉落物品.
     * <p>原文：
     * Get if the falling block will not become a block upon landing and not drop
     * an item.
     * <p>
     * Unlike {@link #getDropItem()}, this property will prevent the block from
     * forming into a block when it lands, causing it to disappear. If this property
     * is true and {@link #getDropItem()} is true, an item will <strong>NOT</strong>
     * be dropped.
     *
     * @return 方块是否会消失
     * <p>原文：true if the block will disappear
     */
    boolean getCancelDrop();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置下落方块落地后是否会消失且不掉落物品.
     * <p>
     * 与 {@link #setDropItem(boolean)} 不同, 此属性会阻止方块落地时形成方块, 使其消失.
     * 如果此属性为 true 且 {@link #getDropItem()} 为 true, 则 <strong>不会</strong> 掉落物品.
     * <p>原文：
     * Get if the falling block will not become a block upon landing and not drop
     * an item.
     * <p>
     * Unlike {@link #setDropItem(boolean)}, this property will prevent the block
     * from forming into a block when it lands, causing it to disappear. If this
     * property is true and {@link #getDropItem()} is true, an item will
     * <strong>NOT</strong> be dropped.
     *
     * @param cancelDrop 设为 true 使方块落地时消失
     * <p>原文：true to make the block disappear when landing
     */
    void setCancelDrop(boolean cancelDrop);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块能否砸伤实体.
     * <p>原文：
     * Get the HurtEntities state of this block.
     *
     * @return 此方块能否砸伤实体.
     * <p>原文：whether entities will be damaged by this block.
     */
    boolean canHurtEntities();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此方块能否砸伤实体.
     * <p>原文：
     * Set the HurtEntities state of this block.
     *
     * @param hurtEntities 此方块能否砸伤实体.
     * <p>原文：whether entities will be damaged by this block.
     */
    void setHurtEntities(boolean hurtEntities);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取下落方块对实体造成的伤害乘以下落距离的值.
     * <p>原文：
     * Get the amount of damage inflicted upon entities multiplied by the distance
     * that the block had fallen when this falling block lands on them.
     *
     * @return 每格伤害
     * <p>原文：the damage per block
     */
    float getDamagePerBlock();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置下落方块对实体造成的伤害乘以下落距离的值.
     * <p>
     * 如果 {@code damage} 非零, 此方法会自动调用
     * {@link #setHurtEntities(boolean) setHurtEntities(true)}.
     * <p>原文：
     * Set the amount of damage inflicted upon entities multiplied by the distance
     * that the block had fallen when this falling block lands on them.
     * <p>
     * If {@code damage} is non-zero, this method will automatically call
     * {@link #setHurtEntities(boolean) setHurtEntities(true)}.
     *
     * @param damage 要设置的每格伤害. 必须 >= 0.0
     * <p>原文：the damage per block to set. Must be >= 0.0
     */
    void setDamagePerBlock(float damage);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取下落方块落地时对实体造成的最大伤害.
     * <p>原文：
     * Get the maximum amount of damage that can be inflicted upon entities when
     * this falling block lands on them.
     *
     * @return 最大伤害
     * <p>原文：the max damage
     */
    int getMaxDamage();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置下落方块落地时对实体造成的最大伤害.
     * <p>
     * 如果 {@code damage} 非零, 此方法会自动调用
     * {@link #setHurtEntities(boolean) setHurtEntities(true)}.
     * <p>原文：
     * Set the maximum amount of damage that can be inflicted upon entities when
     * this falling block lands on them.
     * <p>
     * If {@code damage} is non-zero, this method will automatically call
     * {@link #setHurtEntities(boolean) setHurtEntities(true)}.
     *
     * @param damage 要设置的最大伤害. 必须 >= 0
     * <p>原文：the max damage to set. Must be >= 0
     */
    void setMaxDamage(int damage);

     /**
     * Gets the source block location of the FallingBlock
     *
     * @return the source block location the FallingBlock was spawned from
     * @deprecated replaced by {@link Entity#getOrigin()}
     */
    @Deprecated
    default org.bukkit.Location getSourceLoc() {
        return this.getOrigin();
    }
    // Paper start - Auto expire setting
    /**
     * Sets if this falling block should expire after:
     * - 30 seconds
     * - 5 seconds and is outside of the world
     *
     * @return if this behavior occurs
     */
    boolean doesAutoExpire();

    /**
     * Sets if this falling block should expire after:
     * - 30 seconds
     * - 5 seconds and is outside of the world
     *
     * @param autoExpires if this behavior should occur
     */
    void shouldAutoExpire(boolean autoExpires);
    // Paper end - Auto expire setting
}
