package io.papermc.paper.entity;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示可以被装进桶的实体。
 * <p>原文：
 * Represents an entity that can be bucketed.
 */
@NullMarked
public interface Bucketable extends Entity {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此实体是否来自桶。
     * <p>原文：
     * Gets if this entity originated from a bucket.
     *
     * @return 是否来自桶
     * <p>原文：originated from bucket
     */
    boolean isFromBucket();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此实体是否来自桶。
     * <p>原文：
     * Sets if this entity originated from a bucket.
     *
     * @param fromBucket 是否来自桶
     * <p>原文：is from a bucket
     */
    void setFromBucket(boolean fromBucket);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此实体桶装形式的基础物品堆。
     * <p>原文：
     * Gets the base itemstack of this entity in a bucket form.
     *
     * @return 桶装形式
     * <p>原文：bucket form
     */
    ItemStack getBaseBucketItem();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此实体被桶装起时播放的声音。
     * <p>原文：
     * Gets the sound that is played when this entity
     * is picked up in a bucket.
     * @return 桶装拾取声音
     * <p>原文：bucket pickup sound
     */
    Sound getPickupSound();
}
