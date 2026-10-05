package org.bukkit.entity;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.Keyed;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表猪.
 * <p>原文：
 * Represents a Pig.
 */
@NullMarked
public interface Pig extends Steerable, Vehicle {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此猪的变种。
     * <p>原文：
     * Gets the variant of this pig.
     *
     * @return 猪的变种.
     * <p>
     * 原文: Get the variant of this pig.
     * <p>原文：the pig variant
     */
    Variant getVariant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此猪的变种。
     * <p>原文：
     * Sets the variant of this pig.
     *
     * @param variant 猪的变种.
     * <p>
     * 原文: Set the variant of this pig.
     * <p>原文：the pig variant
     */
    void setVariant(Variant variant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表猪的变种。
     * <p>原文：
     * Represents the variant of a pig.
     */
    interface Variant extends Keyed {

        // Start generate - PigVariant
        Variant COLD = getVariant("cold");

        Variant TEMPERATE = getVariant("temperate");

        Variant WARM = getVariant("warm");
        // End generate - PigVariant

        private static Variant getVariant(@KeyPattern.Value String key) {
            return RegistryAccess.registryAccess().getRegistry(RegistryKey.PIG_VARIANT).getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
        }
    }
}
