package org.bukkit.inventory.meta.components;

import java.util.Collection;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.Tag;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示一个可以将任何物品转变为可装备护甲的组件。
 * <p>原文：
 * Represents a component which can turn any item into equippable armor.
 */
@ApiStatus.Experimental
public interface EquippableComponent extends ConfigurationSerializable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品可以装备到的槽位。
     * <p>原文：
     * Gets the slot the item can be equipped to.
     *
     * @return 槽位
     * <p>原文：slot
     */
    @NotNull
    EquipmentSlot getSlot();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品可以装备到的槽位。
     * <p>原文：
     * Sets the slot the item can be equipped to.
     *
     * @param slot 新的槽位
     * <p>原文：new slot
     */
    void setSlot(@NotNull EquipmentSlot slot);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取装备物品时播放的音效。
     * <p>原文：
     * Gets the sound to play when the item is equipped.
     *
     * @return 音效
     * <p>原文：the sound
     */
    @NotNull // Paper
    Sound getEquipSound();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置装备物品时播放的音效。
     * <p>原文：
     * Sets the sound to play when the item is equipped.
     *
     * @param sound 音效或null表示使用当前默认值
     * <p>原文：sound or null for current default
     */
    void setEquipSound(@Nullable Sound sound);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取装备时使用的模型键。
     * <p>原文：
     * Gets the key of the model to use when equipped.
     *
     * @return 模型键
     * <p>原文：model key
     */
    @Nullable
    NamespacedKey getModel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置装备时使用的模型键。
     * <p>原文：
     * Sets the key of the model to use when equipped.
     *
     * @param key 模型键
     * <p>原文：model key
     */
    void setModel(@Nullable NamespacedKey key);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取装备时使用的相机覆盖层键。
     * <p>原文：
     * Gets the key of the camera overlay to use when equipped.
     *
     * @return 相机覆盖层键
     * <p>原文：camera overlay key
     */
    @Nullable
    NamespacedKey getCameraOverlay();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置装备时使用的相机覆盖层键。
     * <p>原文：
     * Sets the key of the camera overlay to use when equipped.
     *
     * @param key 相机覆盖层键
     * <p>原文：camera overlay key
     */
    void setCameraOverlay(@Nullable NamespacedKey key);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可以装备此物品的实体。
     * <p>原文：
     * Gets the entities which can equip this item.
     *
     * @return 实体集合
     * <p>原文：the entities
     */
    @Nullable
    Collection<EntityType> getAllowedEntities();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置可以装备此物品的实体。
     * <p>原文：
     * Sets the entities which can equip this item.
     *
     * @param entities 实体类型
     * <p>原文：the entity types
     */
    void setAllowedEntities(@Nullable EntityType entities);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置可以装备此物品的实体。
     * <p>原文：
     * Sets the entities which can equip this item.
     *
     * @param entities 实体类型
     * <p>原文：the entity types
     */
    void setAllowedEntities(@Nullable Collection<EntityType> entities);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置可以装备此物品的实体类型（表示为实体 {@link Tag}）。
     * <p>原文：
     * Set the entity types (represented as an entity {@link Tag}) which can
     * equip this item.
     *
     * @param tag 实体标签
     * <p>原文：the entity tag
     * @throws IllegalArgumentException 如果传入的 {@code tag} 不是实体标签
     * <p>原文：if the passed {@code tag} is not an entity
     * tag
     */
    void setAllowedEntities(@Nullable Tag<EntityType> tag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品是否可以被发射器装备。
     * <p>原文：
     * Gets whether the item can be equipped by a dispenser.
     *
     * @return 可装备状态
     * <p>原文：equippable status
     */
    boolean isDispensable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品是否可以被发射器装备。
     * <p>原文：
     * Sets whether the item can be equipped by a dispenser.
     *
     * @param dispensable 新的可装备状态
     * <p>原文：new equippable status
     */
    void setDispensable(boolean dispensable);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品是否可以通过右键交换。
     * <p>原文：
     * Gets if the item is swappable by right clicking.
     *
     * @return 可交换状态
     * <p>原文：swappable status
     */
    boolean isSwappable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品是否可以通过右键交换。
     * <p>原文：
     * Sets if the item is swappable by right clicking.
     *
     * @param swappable 新的状态
     * <p>原文：new status
     */
    void setSwappable(boolean swappable);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当穿戴实体受到伤害时物品是否会受损。
     * <p>原文：
     * Gets if the item will be damaged when the wearing entity is damaged.
     *
     * @return 物品是否会受损
     * <p>原文：whether the item will be damaged
     */
    boolean isDamageOnHurt();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当穿戴实体受到伤害时物品是否会受损。
     * <p>原文：
     * Sets if the item will be damaged when the wearing entity is damaged.
     *
     * @param damage 物品是否会受损
     * <p>原文：whether the item will be damaged
     */
    void setDamageOnHurt(boolean damage);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品是否会在交互时装备。
     * <p>原文：
     * Gets if the item should be equipped when interacting with an entity.
     *
     * @return 物品是否会被装备
     * <p>原文：whether the item equips on interact
     */
    boolean isEquipOnInteract();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品是否会在交互时装备。
     * <p>原文：
     * Sets if the item should be equipped when interacting with an entity.
     *
     * @param equip 物品是否会被装备
     * <p>原文：whether the item equips on interact
     */
    void setEquipOnInteract(boolean equip);
}
