package org.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示实体状态的不可变副本. 可随时用于创建存储实体的实例.
 * <p>原文：
 * Represents an immutable copy of an entity's state. Can be used at any time to
 * create an instance of the stored entity.
 */
public interface EntitySnapshot {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用此模板创建实体. 不会在世界中生成副本.
     * <p>原文：
     * Creates an entity using this template. Does not spawn the copy in the world.
     * <br>
     *
     * @param world 创建实体的世界
     * <p>原文：the world to create the entity in
     * @return 此实体的副本
     * <p>原文：a copy of this entity.
     */
    @NotNull
    Entity createEntity(@NotNull World world);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用此模板创建实体并在指定位置生成.
     * <p>原文：
     * Creates an entity using this template and spawns it at the provided location.
     *
     * @param to 复制到的位置
     * <p>原文：the location to copy to
     * @return 新实体
     * <p>原文：the new entity.
     */
    @NotNull
    Entity createEntity(@NotNull Location to);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此模板持有的实体类型.
     * <p>原文：
     * Gets the type of entity this template holds.
     *
     * @return 类型
     * <p>原文：the type
     */
    @NotNull
    EntityType getEntityType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此 EntitySnapshot 作为 NBT 字符串获取.
     * <p>
     * 此字符串不应作为可序列化值使用.
     * <p>原文：
     * Get this EntitySnapshot as an NBT string.
     * <p>
     * This string should not be relied upon as a serializable value.
     *
     * @return NBT 字符串
     * <p>原文：the NBT string
     */
    @NotNull
    @ApiStatus.Experimental
    String getAsString();
}
