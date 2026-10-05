package org.bukkit.potion;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 *
 *
 * @deprecated 仅为保障向后兼容性, PotionEffectTypeWrapper 已不再使用.
 * <p>原文：only for backwards compatibility, PotionEffectTypeWrapper is no longer used.
 */
@Deprecated(since = "1.20.3", forRemoval = true)
public abstract class PotionEffectTypeWrapper extends PotionEffectType {
    protected PotionEffectTypeWrapper() {
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取绑定到此包装类的药水效果类型.
     * <p>原文：
     * Get the potion type bound to this wrapper.
     *
     * @return 药水效果类型
     * <p>原文：The potion effect type
     */
    @NotNull
    public PotionEffectType getType() {
        return this;
    }
}
