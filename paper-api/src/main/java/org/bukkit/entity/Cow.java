package org.bukkit.entity;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.Keyed;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表牛.
 * <p>原文：
 * Represents a Cow.
 */
@NullMarked
public interface Cow extends AbstractCow {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此牛的变种.
     * <p>原文：
     * Gets the variant of this cow.
     *
     * @return 牛的变种
     * <p>原文：the cow variant
     */
    Variant getVariant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此牛的变种.
     * <p>原文：
     * Sets the variant of this cow.
     *
     * @param variant 牛的变种
     * <p>原文：the cow variant
     */
    void setVariant(Variant variant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表牛的变种.
     * <p>原文：
     * Represents the variant of a cow.
     */
    interface Variant extends Keyed {

        // Start generate - CowVariant
        Variant COLD = getVariant("cold");

        Variant TEMPERATE = getVariant("temperate");

        Variant WARM = getVariant("warm");
        // End generate - CowVariant

        private static Variant getVariant(@KeyPattern.Value String key) {
            return RegistryAccess.registryAccess().getRegistry(RegistryKey.COW_VARIANT).getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
        }
    }
}
