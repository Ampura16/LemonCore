package org.bukkit;

import com.google.common.base.Preconditions;
import io.papermc.paper.registry.RegistryKey;
import java.util.Locale;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.util.OldEnum;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示流体类型。
 * <p>原文：
 * Represents a fluid type.
 */
public interface Fluid extends OldEnum<Fluid>, Keyed {

    // Start generate - Fluid
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 无流体。
     */
    Fluid EMPTY = getFluid("empty");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 流动的岩浆。
     */
    Fluid FLOWING_LAVA = getFluid("flowing_lava");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 流动的水。
     */
    Fluid FLOWING_WATER = getFluid("flowing_water");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 静止的岩浆。
     */
    Fluid LAVA = getFluid("lava");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 静止的水。
     */
    Fluid WATER = getFluid("water");
    // End generate - Fluid

    @NotNull
    private static Fluid getFluid(@NotNull @KeyPattern.Value String key) {
        return Registry.FLUID.getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param name 流体的名称。
     * <p>原文：of the fluid.
     * @return 具有给定名称的流体。
     * <p>原文：the fluid with the given name.
     * @deprecated 仅用于向后兼容，请改用 {@link Registry#get(NamespacedKey)}。
     *
     * 原文：
     * <p>原文：only for backwards compatibility, use {@link Registry#get(NamespacedKey)} instead.
     */
    @NotNull
    @Deprecated(since = "1.21.3", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
    static Fluid valueOf(@NotNull String name) {
        final NamespacedKey key = NamespacedKey.fromString(name.toLowerCase(Locale.ROOT));
        Fluid fluid = key == null ? null : Bukkit.getUnsafe().get(RegistryKey.FLUID, key);
        Preconditions.checkArgument(fluid != null, "No fluid found with the name %s", name);
        return fluid;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 所有已知流体的数组。
     * <p>原文：an array of all known fluids.
     * @deprecated 请使用 {@link Registry#iterator()}。
     *
     * 原文：
     * <p>原文：use {@link Registry#stream()}.
     */
    @NotNull
    @Deprecated(since = "1.21.3", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
    static Fluid[] values() {
        return Registry.FLUID.stream().toArray(Fluid[]::new);
    }
}
