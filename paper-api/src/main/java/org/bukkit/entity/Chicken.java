package org.bukkit.entity;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.Keyed;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表鸡.
 * <p>原文：
 * Represents a Chicken.
 */
@NullMarked
public interface Chicken extends Animals {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这只鸡的变种.
     * <p>原文：
     * Gets the variant of this chicken.
     *
     * @return 鸡的变种
     * <p>原文：the chicken variant
     */
    Variant getVariant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这只鸡的变种.
     * <p>原文：
     * Sets the variant of this chicken.
     *
     * @param variant 鸡的变种
     * <p>原文：the chicken variant
     */
    void setVariant(Variant variant);

    /**
     * Gets if this chicken was spawned as a chicken jockey.
     *
     * @return is chicken jockey
     */
    boolean isChickenJockey();

    /**
     * Sets if this chicken was spawned as a chicken jockey.
     *
     * @param isChickenJockey is chicken jockey
     */
    void setIsChickenJockey(boolean isChickenJockey);

    /**
     * Gets the number of ticks till this chicken lays an egg.
     *
     * @return ticks till the chicken lays an egg
     */
    int getEggLayTime();

    /**
     * Sets the number of ticks till this chicken lays an egg.
     *
     * @param eggLayTime ticks till the chicken lays an egg
     */
    void setEggLayTime(int eggLayTime);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表鸡的变种.
     * <p>原文：
     * Represents the variant of a chicken.
     */
    interface Variant extends Keyed {

        // Start generate - ChickenVariant
        Variant COLD = getVariant("cold");

        Variant TEMPERATE = getVariant("temperate");

        Variant WARM = getVariant("warm");
        // End generate - ChickenVariant

        private static Variant getVariant(@KeyPattern.Value String key) {
            return RegistryAccess.registryAccess().getRegistry(RegistryKey.CHICKEN_VARIANT).getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
        }
    }
}
