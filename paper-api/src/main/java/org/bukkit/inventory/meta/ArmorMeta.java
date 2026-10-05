package org.bukkit.inventory.meta;

import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示实体可以装备的盔甲。
 * <p>
 * <strong>注意：盔甲纹饰是 Minecraft 的实验性功能的一部分，因此可能会有所变动。</strong>
 * <p>原文：
 * Represents armor that an entity can equip.
 * <p>
 * <strong>Note: Armor trims are part of an experimental feature of Minecraft
 * and hence subject to change.</strong>
 */
public interface ArmorMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查该物品是否有盔甲纹饰。
     * <p>原文：
     * Check whether or not this item has an armor trim.
     *
     * @return 如果有纹饰则返回 true，否则返回 false
     * <p>原文：true if has a trim, false otherwise
     */
    boolean hasTrim();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 {@link ArmorTrim}.
     * <p>原文：
     * Set the {@link ArmorTrim}.
     *
     * @param trim 要设置的纹饰，传入 null 则移除纹饰
     * <p>原文：the trim to set, or null to remove it
     */
    void setTrim(@Nullable ArmorTrim trim);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 {@link ArmorTrim}。
     * <p>原文：
     * Get the {@link ArmorTrim}.
     *
     * @return 盔甲纹饰，如果没有则返回 null
     * <p>原文：the armor trim, or null if none
     */
    @Nullable
    ArmorTrim getTrim();

    @Override
    @NotNull
    ArmorMeta clone();
}
