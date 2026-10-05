package org.bukkit.attribute;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个属性的可变实例以及它的修饰符和数据值.
 * <p>原文：
 * Represents a mutable instance of an attribute and its associated modifiers
 * and values.
 */
public interface AttributeInstance {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 属于该实例的属性.
     * <p>原文：
     * The attribute pertaining to this instance.
     *
     * @return 返回对应实例的属性
     * <p>原文：the attribute
     */
    @NotNull
    Attribute getAttribute();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有属性的基准值 (也就是未更改之前的默认值).
     * <p>原文：
     * Base value of this instance before modifiers are applied.
     *
     * @return 默认属性值
     * <p>原文：base value
     */
    double getBaseValue();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置基准值为某个属性.
     * <p>原文：
     * Set the base value of this instance.
     *
     * @param value 基准值
     * <p>原文：new base value
     */
    void setBaseValue(double value);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该实例上的所有修饰符 (属性).
     * <p>原文：
     * Get all modifiers present on this instance.
     *
     * @return 所有修饰符的副本
     * <p>原文：a copied collection of all modifiers
     */
    @NotNull
    Collection<AttributeModifier> getModifiers();

    // Paper start
    /**
     * Gets the modifier with the corresponding key.
     *
     * @param key the key of the modifier
     * @return the modifier, if it exists
     */
    @org.jetbrains.annotations.Nullable AttributeModifier getModifier(@NotNull net.kyori.adventure.key.Key key);

    /**
     * Remove a modifier with the corresponding key from this instance.
     *
     * @param key the key of the modifier
     */
    void removeModifier(@NotNull net.kyori.adventure.key.Key key);

    /**
     * Gets the modifier with the corresponding UUID.
     *
     * @param uuid the UUID of the modifier
     * @return the modifier, if it exists
     * @deprecated use {@link #getModifier(net.kyori.adventure.key.Key)}, modifiers are no longer stored by UUID
     */
    @Deprecated(forRemoval = true, since = "1.21")
    @org.jetbrains.annotations.Nullable AttributeModifier getModifier(@NotNull java.util.UUID uuid);

    /**
     * Remove a modifier with the corresponding UUID from this instance.
     *
     * @param uuid the UUID of the modifier
     * @deprecated use {@link #removeModifier(net.kyori.adventure.key.Key)}, modifiers are no longer stored by UUID
     */
    @Deprecated(forRemoval = true, since = "1.21")
    void removeModifier(@NotNull java.util.UUID uuid);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向次实例添加要修改的修饰符 (属性).
     * <p>原文：
     * Add a modifier to this instance.
     *
     * @param modifier 添加的属性
     * <p>原文：to add
     */
    void addModifier(@NotNull AttributeModifier modifier);

    // Paper start - Transient modifier API
    /**
     * Add a transient modifier to this instance.
     * Transient modifiers are not persisted (saved with the NBT data)
     *
     * @param modifier to add
     */
    void addTransientModifier(@NotNull AttributeModifier modifier);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从此实例内移除一个修饰符 (属性).
     * <p>原文：
     * Remove a modifier from this instance.
     *
     * @param modifier 移除的属性
     * <p>原文：to remove
     */
    void removeModifier(@NotNull AttributeModifier modifier);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前实例在应用所有修饰符后的值.
     * <p>原文：
     * Get the value of this instance after all associated modifiers have been
     * applied.
     *
     * @return 总属性值
     * <p>原文：the total attribute value
     */
    double getValue();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该实例所指向属性的默认值.
     * <p>原文：
     * Gets the default value of the Attribute attached to this instance.
     *
     * @return 服务器默认属性值
     * <p>原文：server default value
     */
    double getDefaultValue();
}
