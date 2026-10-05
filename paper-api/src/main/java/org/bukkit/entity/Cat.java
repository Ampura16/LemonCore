package org.bukkit.entity;

import com.google.common.base.Preconditions;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import java.util.Locale;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.DyeColor;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.util.OldEnum;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 喵.
 * <p>原文：
 * Meow.
 */
public interface Cat extends Tameable, Sittable, io.papermc.paper.entity.CollarColorable { // Paper - CollarColorable

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这只猫的品种.
     * <p>原文：
     * Gets the current type of this cat.
     *
     * @return 猫的品种
     * <p>原文：Type of the cat.
     */
    @NotNull
    public Type getCatType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这只猫的品种.
     * <p>原文：
     * Sets the current type of this cat.
     *
     * @param type 为这只猫设置的新品种
     * <p>原文：New type of this cat.
     */
    public void setCatType(@NotNull Type type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这只猫的项圈颜色.
     * <p>原文：
     * Get the collar color of this cat
     *
     * @return 项圈的颜色
     * <p>原文：the color of the collar
     */
    @NotNull
    @Override // Paper
    public DyeColor getCollarColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这只猫的项圈颜色.
     * <p>原文：
     * Set the collar color of this cat
     *
     * @param color 要设置的颜色
     * <p>原文：the color to apply
     */
    @Override // Paper
    public void setCollarColor(@NotNull DyeColor color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表了各种猫的品种.
     * <p>
     * Represents the various different cat types there are.
     * <p>原文：
     * Represents the various different cat types there are.
     */
    interface Type extends OldEnum<Type>, Keyed {

        // Start generate - CatType
        Type ALL_BLACK = getType("all_black");

        Type BLACK = getType("black");

        Type BRITISH_SHORTHAIR = getType("british_shorthair");

        Type CALICO = getType("calico");

        Type JELLIE = getType("jellie");

        Type PERSIAN = getType("persian");

        Type RAGDOLL = getType("ragdoll");

        Type RED = getType("red");

        Type SIAMESE = getType("siamese");

        Type TABBY = getType("tabby");

        Type WHITE = getType("white");
        // End generate - CatType

        @NotNull
        private static Type getType(@NotNull @KeyPattern.Value String key) {
            return RegistryAccess.registryAccess().getRegistry(RegistryKey.CAT_VARIANT).getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         *
         *
         * @param name 猫品种的名称。
         * <p>原文：of the cat type.
         * @return 具有给定名称的猫品种。
         * <p>原文：the cat type with the given name.
         * @deprecated 仅用于向后兼容，请使用 {@link Registry#get(NamespacedKey)} 代替。
         * <p>原文：only for backwards compatibility, use {@link Registry#get(NamespacedKey)} instead.
         */
        @NotNull
        @Deprecated(since = "1.21", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
        static Type valueOf(@NotNull String name) {
            final NamespacedKey key = NamespacedKey.fromString(name.toLowerCase(Locale.ROOT));
            Type type = key == null ? null : RegistryAccess.registryAccess().getRegistry(RegistryKey.CAT_VARIANT).get(key);
            Preconditions.checkArgument(type != null, "No cat type found with the name %s", name);
            return type;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         *
         *
         * @return 所有已知猫品种的数组。
         * <p>原文：an array of all known cat types.
         * @deprecated 请使用 {@link Registry#iterator()}。
         * <p>原文：use {@link Registry#stream()}.
         */
        @NotNull
        @Deprecated(since = "1.21", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
        static Type[] values() {
            return RegistryAccess.registryAccess().getRegistry(RegistryKey.CAT_VARIANT).stream().toArray(Type[]::new);
        }
    }

    /**
     * Sets if the cat is lying down.
     * This is visual and does not affect the behaviour of the cat.
     *
     * @param lyingDown whether the cat should lie down
     */
    public void setLyingDown(boolean lyingDown);

    /**
     * Gets if the cat is lying down.
     *
     * @return whether the cat is lying down
     */
    public boolean isLyingDown();

    /**
     * Sets if the cat has its head up.
     * This is visual and does not affect the behaviour of the cat.
     *
     * @param headUp head is up
     */
    public void setHeadUp(boolean headUp);

    /**
     * Gets if the cat has its head up.
     *
     * @return head is up
     */
    public boolean isHeadUp();
}
