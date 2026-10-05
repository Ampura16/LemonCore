package org.bukkit.entity;

import java.util.UUID;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表掉落物实体.
 * <p>原文：
 * Represents a dropped item.
 */
public interface Item extends Entity, io.papermc.paper.entity.Frictional { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与此掉落物相关的物品堆.
     * <p>原文：
     * Gets the item stack associated with this item drop.
     *
     * @return 物品堆
     * <p>原文：An item stack.
     */
    @NotNull
    public ItemStack getItemStack();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置与此掉落物相关的物品堆.
     * <p>原文：
     * Sets the item stack associated with this item drop.
     *
     * @param stack 物品堆
     * <p>原文：An item stack.
     */
    public void setItemStack(@NotNull ItemStack stack);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此掉落物剩余的不可被捡起的时间.
     * <p>原文：
     * Gets the delay before this Item is available to be picked up by players
     *
     * @return 掉落物剩余的不可被捡起的时间
     * <p>原文：Remaining delay
     */
    public int getPickupDelay();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置掉落物剩余的不可被捡起的时间.
     * <p>原文：
     * Sets the delay before this Item is available to be picked up by players
     *
     * @param delay 掉落物剩余的不可被捡起的时间
     * <p>原文：New delay
     */
    public void setPickupDelay(int delay);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此掉落物是否应永久存在.
     * <p>原文：
     * Sets if this Item should live forever
     *
     * @param unlimited 如果为true则掉落物的生存时间无限制
     * <p>原文：true if the lifetime is unlimited
     */
    public void setUnlimitedLifetime(boolean unlimited);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此掉落物是否永久存在.
     * <p>原文：
     * Gets if this Item lives forever
     *
     * @return 如果生存时间无限制则返回true
     * <p>原文：true if the lifetime is unlimited
     */
    public boolean isUnlimitedLifetime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此掉落物的所有者.
     * <p>
     * 设置所有者后, 其他实体将无法拾取此掉落物.
     * <p>原文：
     * Sets the owner of this item.
     *
     * Other entities will not be able to pickup this item when an owner is set.
     *
     * @param owner 新所有者的UUID
     * <p>原文：UUID of new owner
     */
    public void setOwner(@Nullable UUID owner);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此掉落物的所有者.
     * <p>原文：
     * Get the owner of this item.
     *
     * @return 所有者的UUID
     * <p>原文：UUID of owner
     */
    @Nullable
    public UUID getOwner();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此掉落物的投掷者.
     * <p>
     * 投掷者是掉落此物品的实体. 这会影响物品拾取的触发条件, 例如进度的触发.
     * <p>原文：
     * Set the thrower of this item.
     *
     * The thrower is the entity which dropped the item. This affects the
     * trigger criteria for item pickups, for things such as advancements.
     *
     * @param uuid 投掷者的UUID
     * <p>原文：UUID of thrower
     */
    public void setThrower(@Nullable UUID uuid);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此掉落物的投掷者.
     * <p>
     * 投掷者是掉落此物品的实体.
     * <p>原文：
     * Get the thrower of this item.
     *
     * The thrower is the entity which dropped the item.
     *
     * @return 投掷者的UUID
     * <p>原文：UUID of thrower
     */
    @Nullable
    public UUID getThrower();

    // Paper start
    /**
     * Gets if non-player entities can pick this Item up
     *
     * @return True if non-player entities can pickup
     */
     public boolean canMobPickup();

    /**
     * Sets if non-player entities can pick this Item up
     *
     * @param canMobPickup True to allow non-player entity pickup
     */
    public void setCanMobPickup(boolean canMobPickup);

    /**
     * Gets whether the player can pickup the item or not
     *
     * @return True if a player can pickup the item
     */
    public boolean canPlayerPickup();

    /**
     * Sets whether the item can be picked up or not. Modifies the pickup delay value to do so.
     *
     * @param canPlayerPickup True if the player can pickup the item
     */
    public void setCanPlayerPickup(boolean canPlayerPickup);

    /**
     * Gets whether the item will age and despawn from being on the ground too long
     *
     * @return True if the item will age
     */
    public boolean willAge();

    /**
     * Sets whether the item will age or not. If the item is not ageing, it will not despawn
     * by being on the ground for too long.
     *
     * @param willAge True if the item should age
     */
    public void setWillAge(boolean willAge);

    /**
     * Gets the health of item stack.
     * <p>
     * Currently the default max health is 5.
     *
     * @return the health
     */
    public int getHealth();

    /**
     * Sets the health of the item stack. If the value is non-positive
     * the itemstack's normal "on destroy" functionality will be run.
     * <p>
     * Currently, the default max health is 5.
     *
     * @param health the health, a non-positive value will destroy the entity
     */
    public void setHealth(int health);
    // Paper end
}
