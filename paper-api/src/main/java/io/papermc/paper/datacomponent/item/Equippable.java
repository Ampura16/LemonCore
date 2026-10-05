package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.BuildableDataComponent;
import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.registry.set.RegistryKeySet;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有物品的可装备属性。
 * <p>原文：
 * Holds the equippable properties of an item.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#EQUIPPABLE
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface Equippable extends BuildableDataComponent<Equippable, Equippable.Builder> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的 {@link Equippable.Builder} 实例。
     * <p>原文：
     * Creates a new {@link Equippable.Builder} instance.
     *
     * @param slot 新的可装备物可装备到的槽位。
     * <p>原文：The slot for the new equippable to be equippable in.
     * @return 新的构建器
     * <p>原文：a new builder
     */
    @Contract(value = "_ -> new", pure = true)
    static Equippable.Builder equippable(final EquipmentSlot slot) {
        return ItemComponentTypesBridge.bridge().equippable(slot);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此物品可装备的装备槽位。
     * <p>原文：
     * Gets the equipment slot this item can be equipped in.
     *
     * @return 装备槽位
     * <p>原文：the equipment slot
     */
    @Contract(pure = true)
    EquipmentSlot slot();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取装备音效键。
     * <p>原文：
     * Gets the equip sound key.
     *
     * @return 装备音效键
     * <p>原文：the equip sound key
     */
    @Contract(pure = true)
    Key equipSound();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取资源 ID（如果存在）。
     * <p>原文：
     * Gets the asset id if present.
     *
     * @return 资源 ID 或 null
     * <p>原文：the asset id or null
     */
    @Contract(pure = true)
    @Nullable Key assetId();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取镜头覆盖层键（如果存在）。
     * <p>原文：
     * Gets the camera overlay key if present.
     *
     * @return 镜头覆盖层键或 null
     * <p>原文：the camera overlay key or null
     */
    @Contract(pure = true)
    @Nullable Key cameraOverlay();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取可以装备此物品的实体集合。如果允许所有实体则可能为 null。
     * <p>原文：
     * Gets the set of allowed entities that can equip this item.
     * May be null if all entities are allowed.
     *
     * @return 允许的实体集合
     * <p>原文：the set of allowed entities
     */
    @Contract(pure = true)
    @Nullable RegistryKeySet<EntityType> allowedEntities();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查该物品是否可被发射。
     * <p>原文：
     * Checks if the item is dispensable.
     *
     * @return 如果可被发射器发射则为 true，否则为 false
     * <p>原文：true if dispensable, false otherwise
     */
    @Contract(pure = true)
    boolean dispensable();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查该物品是否可交换。
     * <p>原文：
     * Checks if the item is swappable.
     *
     * @return 如果可交换则为 true，否则为 false
     * <p>原文：true if swappable, false otherwise
     */
    @Contract(pure = true)
    boolean swappable();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查穿戴者受伤时该物品是否会受损。
     * <p>原文：
     * Checks if the item takes damage when the wearer is hurt.
     *
     * @return 如果受伤时会损耗耐久则为 true，否则为 false
     * <p>原文：true if it damages on hurt, false otherwise
     */
    @Contract(pure = true)
    boolean damageOnHurt();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查与实体交互时是否应装备该物品。
     * <p>原文：
     * Checks if the item should be equipped when interacting with an entity.
     *
     * @return 如果交互时会装备则为 true，否则为 false
     * <p>原文：true if it equips on interact, false otherwise
     */
    @Contract(pure = true)
    boolean equipOnInteract();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查该物品是否可以从实体身上剪下。
     * <p>原文：
     * Checks if the item can be sheared off an entity.
     *
     * @return 如果可以从实体身上剪下则为 true，否则为 false
     * <p>原文：true if can be sheared off an entity, false otherwise
     */
    @Contract(pure = true)
    boolean canBeSheared();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回从实体身上剪下此装备时播放的声音。
     * <p>原文：
     * Returns the sound that is played when shearing this equipment off an entity.
     *
     * @return 剪切声音
     * <p>原文：shear sound
     */
    @Contract(pure = true)
    Key shearSound();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link Equippable} 的构建器。
     * <p>原文：
     * Builder for {@link Equippable}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<Equippable> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 为此物品设置装备音效键。
         * <p>原文：
         * Sets the equip sound key for this item.
         *
         * @param sound 装备音效键
         * <p>原文：the equip sound key
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder equipSound(Key sound);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 为此物品设置资源 ID。
         * <p>原文：
         * Sets the asset id for this item.
         *
         * @param assetId 资源 ID，可为 null
         * <p>原文：the asset id, nullable
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder assetId(@Nullable Key assetId);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 为此物品设置镜头覆盖层键。
         * <p>原文：
         * Sets the camera overlay key for this item.
         *
         * @param cameraOverlay 镜头覆盖层键，可为 null
         * <p>原文：the camera overlay key, nullable
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder cameraOverlay(@Nullable Key cameraOverlay);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置可以装备此物品的实体。
         * <p>原文：
         * Sets the allowed entities that can equip this item.
         *
         * @param allowedEntities 允许的实体类型集合；如果任意均可则为 null
         * <p>原文：the set of allowed entity types, or null if any
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder allowedEntities(@Nullable RegistryKeySet<EntityType> allowedEntities);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置该物品是否可被发射。
         * <p>原文：
         * Sets whether the item is dispensable.
         *
         * @param dispensable 如果可被发射器发射则为 true
         * <p>原文：true if dispensable
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder dispensable(boolean dispensable);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置该物品是否可交换。
         * <p>原文：
         * Sets whether the item is swappable.
         *
         * @param swappable 如果可交换则为 true
         * <p>原文：true if swappable
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder swappable(boolean swappable);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置穿戴者受伤时该物品是否会受损。
         * <p>原文：
         * Sets whether the item takes damage when the wearer is hurt.
         *
         * @param damageOnHurt 如果受伤时会损耗耐久则为 true
         * <p>原文：true if it damages on hurt
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder damageOnHurt(boolean damageOnHurt);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置与实体交互时是否应装备该物品。
         * <p>原文：
         * Sets whether the item should be equipped when interacting with an entity.
         *
         * @param equipOnInteract 如果交互时会装备则为 true
         * <p>原文：true if it equips on interact
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder equipOnInteract(boolean equipOnInteract);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置该物品是否可以从实体身上剪下。
         * <p>原文：
         * Sets whether the item can be sheared off an entity.
         *
         * @param canBeSheared 如果可以从实体身上剪下则为 true
         * <p>原文：true if can be sheared off an entity
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder canBeSheared(boolean canBeSheared);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置从实体身上剪下此装备时播放的声音。
         * <p>原文：
         * Sets the sound that is played when shearing this equipment off an entity.
         *
         * @param shearSound 剪切音效键
         * <p>原文：the shear sound key
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder shearSound(Key shearSound);
    }
}
