package org.bukkit.inventory.meta;

import org.bukkit.DyeColor;
import org.jetbrains.annotations.Nullable;

public interface ShieldMeta extends BannerMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此盾牌的基础颜色.
     * <p>原文：
     * Gets the base color for this shield.
     *
     * @return 基础颜色, 如果未设置则返回 null
     * <p>原文：the base color or null
     */
    @Nullable
    DyeColor getBaseColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此盾牌的基础颜色.
     * <p>
     * <b>注意:</b> 如果盾牌包含 {@link org.bukkit.block.banner.Pattern} (旗帜图案),
     * 那么将基础颜色设置为 null 会保留图案, 但会将基础颜色默认设为 {@link DyeColor#WHITE} (白色).
     * <p>原文：
     * Sets the base color for this shield.
     * <br>
     * <b>Note:</b> If the shield contains a
     * {@link org.bukkit.block.banner.Pattern}, then a null base color will
     * retain the pattern but default the base color to {@link DyeColor#WHITE}.
     *
     * @param color 基础颜色, 或 null
     * <p>原文：the base color or null
     */
    void setBaseColor(@Nullable DyeColor color);

}
