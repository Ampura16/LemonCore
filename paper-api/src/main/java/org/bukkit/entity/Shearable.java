package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个可以被剪刀剪毛的实体.
 * <p>原文：
 * Represents an entity which can be shorn with shears.
 *
 * @deprecated Spigots shearable API miserably fails at capturing all entities that may be sheared by a player, like
 * mushroom cows which, once sheared, convert into normal cows. For such entities, methods like
 * {@link #setSheared(boolean)} or {@link #isSheared()} make no sense, making this API and interface dead API from
 * the get-go.
 */
@Deprecated(forRemoval = true, since = "1.21")
public interface Shearable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体是否处于已剪毛状态.
     * <p>原文：
     * Gets whether the entity is in its sheared state.
     *
     * @return 实体是否已剪毛
     * <p>原文：Whether the entity is sheared.
     * @deprecated Use {@link io.papermc.paper.entity.Shearable#readyToBeSheared()} instead.
     */
    @Deprecated(forRemoval = true, since = "1.21")
    boolean isSheared();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体是否处于已剪毛状态.
     * <p>原文：
     * Sets whether the entity is in its sheared state.
     *
     * @param flag 是否剪毛
     * <p>原文：Whether to shear the entity
     * @deprecated Use {@link io.papermc.paper.entity.Shearable#shear()} instead if applicable.
     * Some entities cannot be "unsheared".
     */
    @Deprecated(forRemoval = true, since = "1.21")
    void setSheared(boolean flag);
}
