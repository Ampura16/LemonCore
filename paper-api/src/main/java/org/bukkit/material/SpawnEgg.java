package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.meta.SpawnEggMeta;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表刷怪蛋.
 * <p>原文：
 * Represents a spawn egg that can be used to spawn mobs
 *
 * @deprecated 请使用 {@link SpawnEggMeta}
 * <p>原文：use {@link SpawnEggMeta}
 */
@Deprecated(since = "1.13", forRemoval = true)
public class SpawnEgg extends MaterialData {

    public SpawnEgg() {
        super(Material.LEGACY_MONSTER_EGG);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type the type
     * @param data the raw data value
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public SpawnEgg(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param data the raw data value
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public SpawnEgg(byte data) {
        super(Material.LEGACY_MONSTER_EGG, data);
    }

    public SpawnEgg(EntityType type) {
        this();
        setSpawnedType(type);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个刷怪蛋刷出实体的种类.
     * <p>原文：
     * Get the type of entity this egg will spawn.
     *
     * @return 实体种类
     * <p>原文：The entity type.
     * @deprecated 现存储在 {@link SpawnEggMeta}.
     * <p>原文：This is now stored in {@link SpawnEggMeta}.
     */
    @Deprecated(since = "1.9")
    public EntityType getSpawnedType() {
        return EntityType.fromId(getData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个刷怪蛋刷出实体的种类.
     * <p>原文：
     * Set the type of entity this egg will spawn.
     *
     * @param type 实体种类
     * <p>原文：The entity type.
     * @deprecated 现存储在 {@link SpawnEggMeta}.
     * <p>原文：This is now stored in {@link SpawnEggMeta}.
     */
    @Deprecated(since = "1.9")
    public void setSpawnedType(EntityType type) {
        setData((byte) type.getTypeId());
    }

    @Override
    public String toString() {
        return "SPAWN EGG{" + getSpawnedType() + "}";
    }

    @Override
    public SpawnEgg clone() {
        return (SpawnEgg) super.clone();
    }
}
