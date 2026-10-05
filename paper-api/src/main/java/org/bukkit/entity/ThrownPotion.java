package org.bukkit.entity;

import java.util.Collection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个被抛出的药水瓶.
 * <p>原文：
 * Represents a thrown potion bottle
 */
public interface ThrownPotion extends ThrowableProjectile {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回应用于此药水瓶的药水效果.
     * <p>原文：
     * Returns the effects that are applied by this potion.
     *
     * @return 药水效果
     * <p>原文：The potion effects
     */
    @NotNull
    public Collection<PotionEffect> getEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此药水瓶物品的副本.
     * <p>
     * 修改此物品副本并不会直接修改这个被抛出的药水瓶本身.
     * 如果你希望修改此药水瓶, 你必须使用{@link
     * #setItem(ItemStack) setItemStack}方法.
     * <p>原文：
     * Returns a copy of the ItemStack for this thrown potion.
     * <p>
     * Altering this copy will not alter the thrown potion directly. If you want
     * to alter the thrown potion, you must use the {@link
     * #setItem(ItemStack) setItemStack} method.
     *
     * @return 此药水瓶的副本
     * <p>原文：A copy of the ItemStack for this thrown potion.
     */
    @NotNull
    public ItemStack getItem();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此药水瓶物品.
     * <p>
     * 设置的物品必须为药水, 否则此方法会抛出异常.
     * <p>原文：
     * Set the ItemStack for this thrown potion.
     *
     * @param item 要设置的药水物品.
     * <p>原文：New ItemStack
     */
    public void setItem(@NotNull ItemStack item);

    // Paper start - Projectile API
    /**
     * Gets a copy of the PotionMeta for this thrown potion.
     * This includes what effects will be applied by this potion.
     *
     * @return potion meta
     */
    @NotNull
    org.bukkit.inventory.meta.PotionMeta getPotionMeta();

    /**
     * Sets the PotionMeta of this thrown potion.
     * This will modify the effects applied by this potion.
     * <p>
     * Note that the type of {@link #getItem()} is irrelevant
     *
     * @param meta potion meta
     */
    void setPotionMeta(@NotNull org.bukkit.inventory.meta.PotionMeta meta);

    /**
     * Splashes the potion at its current location.
     */
    void splash();
    // Paper end
}
