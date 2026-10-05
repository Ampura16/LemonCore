package org.bukkit.entity;

import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 烟花火箭实体.
 */
public interface Firework extends Projectile {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取烟花火箭元数据的副本.
     * <p>原文：
     * Get a copy of the fireworks meta
     *
     * @return 烟花火箭元数据的副本
     * <p>原文：A copy of the current Firework meta
     */
    @NotNull
    FireworkMeta getFireworkMeta();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为此烟花火箭应用提供的烟花火箭元数据.
     * <p>原文：
     * Apply the provided meta to the fireworks
     * <p>
     * Adjusts detonation ticks automatically.
     *
     * @param meta 要应用的 FireworkMeta
     * <p>原文：The FireworkMeta to apply
     */
    void setFireworkMeta(@NotNull FireworkMeta meta);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此烟花火箭附着的 {@link LivingEntity}.
     * <p>
     * 当附着到实体时, 烟花效果将正常作用但保持在实体位置.
     * 如果实体 {@code LivingEntity#isGliding() 正在滑翔}, 则实体将获得朝向其视线方向的推进力.
     * <p>原文：
     * Set the {@link LivingEntity} to which this firework is attached.
     * <p>
     * When attached to an entity, the firework effect will act as normal but
     * remain positioned on the entity. If the entity {@code LivingEntity#isGliding()
     * is gliding}, then the entity will receive a boost in the direction that
     * they are looking.
     *
     * @param entity 烟花应附着的实体, 或 null 以移除附着的实体
     * <p>原文：the entity to which the firework should be attached, or
     * null to remove the attached entity
     * @return 如果实体可以附着则为 true, 如果烟花已经引爆则为 false
     * <p>原文：true if the entity could be attached, false if the firework was
     * already detonated
     */
    boolean setAttachedTo(@Nullable LivingEntity entity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此烟花火箭附着的 {@link LivingEntity}.
     * <p>
     * 当附着到实体时, 烟花效果将正常作用但保持在实体位置.
     * 如果实体 {@code LivingEntity#isGliding() 正在滑翔}, 则实体将获得朝向其视线方向的推进力.
     * <p>原文：
     * Get the {@link LivingEntity} to which this firework is attached.
     * <p>
     * When attached to an entity, the firework effect will act as normal but
     * remain positioned on the entity. If the entity {@code LivingEntity#isGliding()
     * is gliding}, then the entity will receive a boost in the direction that
     * they are looking.
     *
     * @return 附着的实体, 如果没有则为 null
     * <p>原文：the attached entity, or null if none
     */
    @Nullable
    LivingEntity getAttachedTo();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此烟花火箭已存活的 tick 数. 如果此值超过 {@link #getMaxLife()}, 烟花将引爆.
     * <p>原文：
     * Set the ticks that this firework has been alive. If this value exceeds
     * {@link #getMaxLife()}, the firework will detonate.
     *
     * @param ticks 要设置的 tick 数. 必须大于或等于 0
     * <p>原文：the ticks to set. Must be greater than or equal to 0
     * @deprecated use {@link #setTicksFlown(int)}
     * @return 如果设置成功则为 true, 如果烟花已经引爆则为 false
     * <p>原文：true if the life was set, false if this firework has already detonated
     */
    @Deprecated(forRemoval = true) // Paper
    boolean setLife(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此烟花火箭已存活的 tick 数. 当此值达到 {@link #getMaxLife()} 时, 烟花将引爆.
     * <p>原文：
     * Get the ticks that this firework has been alive. When this value reaches
     * {@link #getMaxLife()}, the firework will detonate.
     *
     * @deprecated use {@link #getTicksFlown()}
     * @return 存活 tick 数
     * <p>原文：the life ticks
     */
    @Deprecated(forRemoval = true) // Paper
    int getLife();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此烟花火箭在引爆前存在的时间 (以 tick 为单位).
     * <p>原文：
     * Set the time in ticks this firework will exist until it is detonated.
     *
     * @param ticks 要设置的 tick 数. 必须大于 0
     * <p>原文：the ticks to set. Must be greater than 0
     * @deprecated use {@link #setTicksToDetonate(int)}
     * @return 如果设置成功则为 true, 如果烟花已经引爆则为 false
     * <p>原文：true if the time was set, false if this firework has already detonated
     */
    @Deprecated(forRemoval = true) // Paper
    boolean setMaxLife(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此烟花火箭在引爆前存在的时间 (以 tick 为单位).
     * <p>原文：
     * Get the time in ticks this firework will exist until it is detonated.
     *
     * @deprecated use {@link #getTicksToDetonate()}
     * @return 最大存活时间 (以 tick 为单位)
     * <p>原文：the maximum life in ticks
     */
    @Deprecated(forRemoval = true) // Paper
    int getMaxLife();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使烟花尽早爆炸, 像是它的引火线已燃烧殆尽.
     * <p>原文：
     * Cause this firework to explode at earliest opportunity, as if it has no
     * remaining fuse.
     */
    void detonate();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此烟花是否已经引爆.
     * <p>原文：
     * Check whether or not this firework has detonated.
     *
     * @return 如果已引爆则为 true, 如果仍在世界中则为 false
     * <p>原文：true if detonated, false if still in the world
     */
    boolean isDetonated();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此烟花是否以一定角度发射 (比如使用弩).
     *
     * 若此值为false, 烟花将以垂直向上的方向飞行.
     * <p>原文：
     * Gets if the firework was shot at an angle (i.e. from a crossbow).
     *
     * A firework which was not shot at an angle will fly straight upwards.
     *
     * @return 此烟花是否以一定角度发射
     * <p>原文：shot at angle status
     */
    boolean isShotAtAngle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此烟花是否以一定角度发射 (比如使用弩).
     *
     * 若设置此值为false, 烟花将以垂直向上的方向飞行.
     * <p>原文：
     * Sets if the firework was shot at an angle (i.e. from a crossbow).
     *
     * A firework which was not shot at an angle will fly straight upwards.
     *
     * @param shotAtAngle 此烟花是否以一定角度发射
     * <p>原文：the new shotAtAngle
     */
    void setShotAtAngle(boolean shotAtAngle);

    // Paper start
    @org.jetbrains.annotations.Nullable
    public java.util.UUID getSpawningEntity();
    /**
     * If this firework is boosting an entity, return it
     * @deprecated use {@link #getAttachedTo()}
     * @see #setAttachedTo(LivingEntity)
     * @return The entity being boosted
     */
    @org.jetbrains.annotations.Nullable
    @Deprecated
    default LivingEntity getBoostedEntity() {
        return getAttachedTo();
    }
    // Paper end

    // Paper start - Firework API
    /**
     * Gets the item used in the firework.
     *
     * @return firework item
     */
    @NotNull
    public org.bukkit.inventory.ItemStack getItem();

    /**
     * Sets the item used in the firework.
     * <p>
     * Firework explosion effects are used from this item.
     *
     * @param itemStack item to set
     */
    void setItem(@org.jetbrains.annotations.Nullable org.bukkit.inventory.ItemStack itemStack);

    /**
     * Gets the number of ticks the firework has flown.
     *
     * @return ticks flown
     */
    int getTicksFlown();

    /**
     * Sets the number of ticks the firework has flown.
     * Setting this greater than detonation ticks will cause the firework to explode.
     *
     * @param ticks ticks flown
     */
    void setTicksFlown(int ticks);

    /**
     * Gets the number of ticks the firework will detonate on.
     *
     * @return the tick to detonate on
     */
    int getTicksToDetonate();

    /**
     * Set the amount of ticks the firework will detonate on.
     *
     * @param ticks ticks to detonate on
     */
    void setTicksToDetonate(int ticks);
    // Paper end
}
