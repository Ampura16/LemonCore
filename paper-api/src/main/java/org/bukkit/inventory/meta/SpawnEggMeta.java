package org.bukkit.inventory.meta;

import org.bukkit.entity.EntitySnapshot;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表刷怪蛋和它刷出的实体的类型.
 * <p>原文：
 * Represents a spawn egg and it's spawned type.
 */
public interface SpawnEggMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取刷怪蛋生成的实体的类型.
     * <p>原文：
     * Get the type of entity this egg will spawn.
     *
     * @return 实体类型. 根据实现指定的默认值, 可能为 null
     * <p>原文：The entity type. May be null for implementation specific default.
     * @deprecated 不同实体类型对应不同的刷怪蛋物品
     * <p>原文：different types are different items
     */
    @Deprecated(since = "1.13", forRemoval = true) // Paper
    @Contract("-> fail")
    EntityType getSpawnedType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置刷怪蛋生成的实体的类型.
     * <p>原文：
     * Set the type of entity this egg will spawn.
     *
     * @param type 实体类型. 根据实现指定的默认值, 可能为 null
     * <p>原文：The entity type. May be null for implementation specific
     * default.
     * @deprecated 不同实体类型对应不同的刷怪蛋物品
     * <p>原文：different types are different items
     */
    @Deprecated(since = "1.13", forRemoval = true) // Paper
    @Contract("_ -> fail")
    void setSpawnedType(EntityType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此刷怪蛋将会生成的 {@link EntitySnapshot}, 如果未设置实体则返回 null.
     * <p>
     * 蛋中所有适用的数据都会被复制, 例如自定义名称、生命值和速度.
     * <p>原文：
     * Gets the {@link EntitySnapshot} that will be spawned by this spawn egg or null if no entity
     * has been set. <br>
     * <p>
     * All applicable data from the egg will be copied, such as custom name, health,
     * and velocity. <br>
     *
     * @return 实体快照, 如果未设置实体则返回 null
     * <p>原文：the entity snapshot or null if no entity has been set
     */
    @Nullable
    EntitySnapshot getSpawnedEntity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此刷怪蛋将会生成的 {@link EntitySnapshot}.
     * <p>
     * 实体中所有适用的数据都会被复制, 例如自定义名称、生命值和速度.
     * <p>原文：
     * Sets the {@link EntitySnapshot} that will be spawned by this spawn egg. <br>
     * <p>
     * All applicable data from the entity will be copied, such as custom name,
     * health, and velocity. <br>
     *
     * @param snapshot 实体快照
     * <p>原文：the snapshot
     */
    void setSpawnedEntity(@NotNull EntitySnapshot snapshot);

    // Paper start
    /**
     * Get the custom type of entity this egg will spawn.
     *
     * @return the entity type or null if no custom type is set
     */
    @org.jetbrains.annotations.Nullable EntityType getCustomSpawnedType();

    /**
     * Set the custom type of entity this egg will spawn.
     *
     * @param type the entity type or null to clear the custom type
     */
    void setCustomSpawnedType(@org.jetbrains.annotations.Nullable EntityType type);
    // Paper end

    @NotNull
    @Override
    SpawnEggMeta clone();
}
