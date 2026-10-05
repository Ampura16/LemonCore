package org.bukkit.enchantments;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 方便选择{@link Enchantment}的一个简单的封装类.
 * <p>原文：
 * A simple wrapper for ease of selecting {@link Enchantment}s
 *
 * @deprecated 仅为保障向后兼容性, EnchantmentWrapper 已不再使用
 * <p>原文：only for backwards compatibility, EnchantmentWrapper is no longer used.
 */
@Deprecated(since = "1.20.3", forRemoval = true)
public abstract class EnchantmentWrapper extends Enchantment {
    protected EnchantmentWrapper() {
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个包装类所对应的的附魔.
     * <p>原文：
     * Gets the enchantment bound to this wrapper
     *
     * @return 附魔
     * <p>原文：Enchantment
     */
    @NotNull
    public Enchantment getEnchantment() {
        return this;
    }
}
