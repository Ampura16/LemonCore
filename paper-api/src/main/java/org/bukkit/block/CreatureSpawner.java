package org.bukkit.block;

import org.bukkit.spawner.Spawner;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表刷怪笼.
 * <p>原文：
 * Represents a captured state of a creature spawner.
 */
public interface CreatureSpawner extends TileState, Spawner {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置刷怪笼要生成的生物名.
     * <p>原文：
     * Set the spawner mob type.
     *
     * @param creatureType 要刷出的生物的名称, 设为 null 清除类型.
     * <p>原文：The creature type's name or null to clear.
     * @deprecated 不安全的参数，请使用
     * {@link #setSpawnedType(org.bukkit.entity.EntityType)}.
     * <p>原文：magic value, use
     * {@link #setSpawnedType(org.bukkit.entity.EntityType)}.
     */
    @Deprecated(since = "1.11.2")
    public void setCreatureTypeByName(@Nullable String creatureType);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取要刷出的生物的名称.
     * <p>原文：
     * Get the spawner's creature type.
     *
     * @return 要刷出的生物的名称(如已设置).
     * <p>原文：The creature type's name if is set.
     * @deprecated 不安全的参数，请使用{@link #getSpawnedType()}.
     * <p>原文：magic value, use {@link #getSpawnedType()}.
     */
    @Deprecated(since = "1.11.2")
    @Nullable
    public String getCreatureTypeName();
}
