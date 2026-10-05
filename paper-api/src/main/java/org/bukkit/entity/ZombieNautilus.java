package org.bukkit.entity;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.Keyed;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示僵尸鹦鹉螺。
 */
@NullMarked
public interface ZombieNautilus extends AbstractNautilus {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此僵尸鹦鹉螺的变体。
     * <p>原文：
     * Gets the variant of this zombie nautilus.
     *
     * @return 僵尸鹦鹉螺变体
     * <p>
     * 原文：Get the variant of this zombie nautilus.
     * <p>原文：the variant
     */
    Variant getVariant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此僵尸鹦鹉螺的变体。
     * <p>原文：
     * Sets the variant of this zombie nautilus.
     *
     * @param variant 僵尸鹦鹉螺变体
     * <p>
     * 原文：Set the variant of this zombie nautilus.
     * <p>原文：the variant
     */
    void setVariant(Variant variant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示僵尸鹦鹉螺的变体。
     * <p>原文：
     * Represents the variant of a Zombie Nautilus.
     */
    interface Variant extends Keyed {

        // Start generate - ZombieNautilusVariant
        Variant TEMPERATE = getVariant("temperate");

        Variant WARM = getVariant("warm");
        // End generate - ZombieNautilusVariant

        private static Variant getVariant(@KeyPattern.Value String key) {
            return RegistryAccess.registryAccess().getRegistry(RegistryKey.ZOMBIE_NAUTILUS_VARIANT).getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
        }
    }
}
