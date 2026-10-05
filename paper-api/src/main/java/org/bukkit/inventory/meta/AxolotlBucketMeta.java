package org.bukkit.inventory.meta;

import org.bukkit.entity.Axolotl;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一桶美西螈.
 * <p>原文：
 * Represents a bucket of axolotl.
 */
public interface AxolotlBucketMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取桶中美西螈的变种.
     * <p>
     * 插件应该在调用此方法之前检查 hasVariant() 是否返回 <code>true</code>.
     * <p>原文：
     * Get the variant of the axolotl in the bucket.
     * <p>
     * Plugins should check that hasVariant() returns <code>true</code> before
     * calling this method.
     *
     * @return 美西螈变种
     * <p>原文：axolotl variant
     */
    @NotNull
    Axolotl.Variant getVariant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此桶中美西螈的变种.
     * <p>原文：
     * Set the variant of this axolotl in the bucket.
     *
     * @param variant 美西螈变种
     * <p>原文：axolotl variant
     */
    void setVariant(@NotNull Axolotl.Variant variant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否存在变种标签，该标签表示将生成特定的美西螈.
     * <p>原文：
     * Checks for existence of a variant tag indicating a specific axolotl will be
     * spawned.
     *
     * @return 是否存在变种
     * <p>原文：if there is a variant
     */
    boolean hasVariant();

    @Override
    @NotNull
    AxolotlBucketMeta clone();
}
