package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表弩.
 */
public interface CrossbowMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回弩是否装填了抛射物.
     * <p>原文：
     * Returns whether the item has any charged projectiles.
     *
     * @return 弩是否装填了抛射物
     * <p>原文：whether charged projectiles are present
     */
    boolean hasChargedProjectiles();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回弩装填的抛射物的不可变列表.
     * <p>原文：
     * Returns an immutable list of the projectiles charged on this item.
     *
     * @return 装填的抛射物
     * <p>原文：charged projectiles
     */
    @NotNull
    List<ItemStack> getChargedProjectiles();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置弩装填的抛射物.
     *
     * 为 null 时移除所有抛射物.
     * <p>原文：
     * Sets the projectiles charged on this item.
     *
     * Removes all projectiles when given null.
     *
     * @param projectiles 要装填的抛射物
     * <p>原文：the projectiles to set
     * @throws IllegalArgumentException 如果任意一个抛射物不是箭或烟花火箭
     * <p>原文：if one of the projectiles is empty
     */
    void setChargedProjectiles(@Nullable List<ItemStack> projectiles);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向本弩装填一个抛射物.
     * <p>原文：
     * Adds a charged projectile to this item.
     *
     * @param item 抛射物
     * <p>原文：projectile
     * @throws IllegalArgumentException 若抛射物不是箭或烟花火箭
     * <p>原文：if the projectile is empty
     */
    void addChargedProjectile(@NotNull ItemStack item);
}
