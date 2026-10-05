package org.bukkit.entity;

import com.google.common.base.Preconditions;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import java.util.Locale;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.util.OldEnum;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 青蛙.
 * <p>原文：
 * A Frog.
 */
public interface Frog extends Animals {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此青蛙的舌头目标.
     * <p>原文：
     * Gets the tongue target of this frog.
     *
     * @return 舌头目标, 如果未设置则为 null
     * <p>原文：tongue target or null if not set
     */
    @Nullable
    Entity getTongueTarget();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此青蛙的舌头目标.
     * <p>原文：
     * Sets the tongue target of this frog.
     *
     * @param target 舌头目标, 或 null 以清除
     * <p>原文：tongue target or null to clear
     */
    void setTongueTarget(@Nullable Entity target);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此青蛙的变种.
     * <p>原文：
     * Get the variant of this frog.
     *
     * @return 青蛙变种
     * <p>原文：frog variant
     */
    @NotNull
    Variant getVariant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此青蛙的变种.
     * <p>原文：
     * Set the variant of this frog.
     *
     * @param variant 青蛙变种
     * <p>原文：frog variant
     */
    void setVariant(@NotNull Variant variant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 青蛙的变种 - 即其颜色.
     * <p>原文：
     * Represents the variant of a frog - ie its color.
     */
    interface Variant extends OldEnum<Variant>, Keyed {

        // Start generate - FrogVariant
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 寒冷 (绿色) 青蛙.
         */
        Variant COLD = getVariant("cold");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 温带 (棕橙色) 青蛙.
         */
        Variant TEMPERATE = getVariant("temperate");

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 温暖 (灰色) 青蛙.
         */
        Variant WARM = getVariant("warm");
        // End generate - FrogVariant

        @NotNull
        private static Variant getVariant(@NotNull @KeyPattern.Value String key) {
            return RegistryAccess.registryAccess().getRegistry(RegistryKey.FROG_VARIANT).getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         *
         *
         * @param name 青蛙变种的名称.
         * <p>原文：of the frog variant.
         * @return 具有给定名称的青蛙变种.
         * <p>原文：the frog variant with the given name.
         * @deprecated 仅用于向后兼容, 请改用 {@link Registry#get(NamespacedKey)}.
         * <p>原文：only for backwards compatibility, use {@link Registry#get(NamespacedKey)} instead.
         */
        @NotNull
        @Deprecated(since = "1.21", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
        static Variant valueOf(@NotNull String name) {
            final NamespacedKey key = NamespacedKey.fromString(name.toLowerCase(Locale.ROOT));
            Variant variant = key == null ? null : RegistryAccess.registryAccess().getRegistry(RegistryKey.FROG_VARIANT).get(key);
            Preconditions.checkArgument(variant != null, "No frog variant found with the name %s", name);
            return variant;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         *
         *
         * @return 所有已知青蛙变种的数组.
         * <p>原文：an array of all known frog variants.
         * @deprecated 请改用 {@link Registry#iterator()}.
         * <p>原文：use {@link Registry#stream()}.
         */
        @NotNull
        @Deprecated(since = "1.21", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
        static Variant[] values() {
            return RegistryAccess.registryAccess().getRegistry(RegistryKey.FROG_VARIANT).stream().toArray(Variant[]::new);
        }
    }
}
