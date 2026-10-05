package org.bukkit.entity;

import java.util.List;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一片即将对处于其中的生物施加药水效果的区域效果云 (即喷溅药水使用后形成的雾).
 * <p>
 * 参考：
 * <a href='http://zh.minecraft.wiki/w/%E5%8C%BA%E5%9D%97%E6%A0%BC%E5%BC%8F#AreaEffectCloud'>区块格式#AreaEffectCloud</a>
 * <a href='http://zh.minecraft.wiki/w/%E6%BB%9E%E7%95%99%E8%8D%AF%E6%B0%B4'>滞留药水</a>
 * <p>原文：
 * Represents an area effect cloud which will imbue a potion effect onto
 * entities which enter it.
 */
public interface AreaEffectCloud extends Entity {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这片云存在的时间 (单位为tick).
     * <p>原文：
     * Gets the duration which this cloud will exist for (in ticks).
     *
     * @return 持续时间
     * <p>原文：cloud duration or {@link PotionEffect#INFINITE_DURATION} for no duration
     */
    int getDuration();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这片云存在的时间 (单位为tick).
     * <p>原文：
     * Sets the duration which this cloud will exist for (in ticks).
     *
     * @param duration 持续时间
     * <p>原文：cloud duration or {@link PotionEffect#INFINITE_DURATION} for no duration
     */
    void setDuration(int duration);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体受到效果前的延迟的时间.
     * <p>原文：
     * Gets the time which an entity has to be exposed to the cloud before the
     * effect is applied.
     *
     * @return 延迟时间
     * <p>原文：wait time
     */
    int getWaitTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体受到效果前的延迟时间.
     * <p>原文：
     * Sets the time which an entity has to be exposed to the cloud before the
     * effect is applied.
     *
     * @param waitTime 延迟时间
     * <p>原文：wait time
     */
    void setWaitTime(int waitTime);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体受到效果后的免疫时间.
     * <p>原文：
     * Gets the time that an entity will be immune from subsequent exposure.
     *
     * @return 免疫时间
     * <p>原文：reapplication delay
     */
    int getReapplicationDelay();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体受到效果后的免疫时间.
     * <p>原文：
     * Sets the time that an entity will be immune from subsequent exposure.
     *
     * @param delay 免疫时间
     * <p>原文：reapplication delay
     */
    void setReapplicationDelay(int delay);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这片云对一个实体产生效果后持续时间应减少多少.
     * <p>原文：
     * Gets the amount that the duration of this cloud will decrease by when it
     * applies an effect to an entity.
     *
     * @return 持续时间的变化量
     * <p>原文：duration on use delta
     */
    int getDurationOnUse();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这片云对一个实体产生效果后持续时间应减少多少.
     * <p>原文：
     * Sets the amount that the duration of this cloud will decrease by when it
     * applies an effect to an entity.
     *
     * @param duration 持续时间的变化量
     * <p>原文：duration on use delta
     */
    void setDurationOnUse(int duration);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这片云的初始半径.
     * <p>原文：
     * Gets the initial radius of the cloud.
     *
     * @return 半径
     * <p>原文：radius
     */
    float getRadius();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这片云的初始半径.
     * <p>原文：
     * Sets the initial radius of the cloud.
     *
     * @param radius 半径
     * <p>原文：radius
     */
    void setRadius(float radius);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这片云对一个实体产生效果后半径应该减少多少.
     * <p>原文：
     * Gets the amount that the radius of this cloud will decrease by when it
     * applies an effect to an entity.
     *
     * @return 半径的变化量
     * <p>原文：radius on use delta
     */
    float getRadiusOnUse();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这片云对一个实体产生效果后半径应该减少多少.
     * <p>原文：
     * Sets the amount that the radius of this cloud will decrease by when it
     * applies an effect to an entity.
     *
     * @param radius 半径的变化量
     * <p>原文：radius on use delta
     */
    void setRadiusOnUse(float radius);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每tick这片云的半径应缩小多少.
     * <p>原文：
     * Gets the amount that the radius of this cloud will decrease by each tick.
     *
     * @return 每 tick 这片云的半径减少量
     * <p>原文：radius per tick delta
     */
    float getRadiusPerTick();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置每一tick这片云的半径应缩小多少.
     * <p>原文：
     * Gets the amount that the radius of this cloud will decrease by each tick.
     *
     * @param radius 每 tick 这片云的半径减少量
     * <p>原文：per tick delta
     */
    void setRadiusPerTick(float radius);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取组成这片云的粒子.
     * <p>原文：
     * Gets the particle which this cloud will be composed of
     *
     * @return 粒子类型
     * <p>原文：particle the set particle type
     */
    @NotNull
    Particle getParticle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置组成这片云的粒子.
     * <p>原文：
     * Sets the particle which this cloud will be composed of
     *
     * @param particle 新的粒子类型
     * <p>原文：the new particle type
     */
    void setParticle(@NotNull Particle particle);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置组成这片云的粒子.
     * <p>原文：
     * Sets the particle which this cloud will be composed of
     *
     * @param <T> 粒子效果数据的类型 (参阅 {@link Particle#getDataType()}
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 新的粒子类型
     * <p>原文：the new particle type
     * @param data 为此粒子效果使用的数据, 可为null, 数据类型取决于 {@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    <T> void setParticle(@NotNull Particle particle, @Nullable T data);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置基本药水数据.
     * <p>原文：
     * Sets the underlying potion data
     *
     * @param data 用于设置基本药水状态的 PotionData
     * <p>原文：PotionData to set the base potion state to
     * @deprecated 升级 / 延长的药水现在是独立的 {@link PotionType}, 请使用 {@link #setBasePotionType} 代替.
     * <p>原文：Upgraded / extended potions are now their own {@link PotionType} use {@link #setBasePotionType} instead.
     */
    @Deprecated(since = "1.20.6", forRemoval = true)
    void setBasePotionData(@Nullable PotionData data);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回基本药水的药水数据.
     * <p>原文：
     * Returns the potion data about the base potion
     *
     * @return 一个 PotionData 对象
     * <p>原文：a PotionData object
     * @deprecated 升级 / 延长的药水现在是独立的 {@link PotionType}, 请使用 {@link #getBasePotionType()} 代替.
     * <p>原文：Upgraded / extended potions are now their own {@link PotionType} use {@link #getBasePotionType()} instead.
     */
    @Nullable
    @Deprecated(since = "1.20.6", forRemoval = true)
    PotionData getBasePotionData();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置基本药水类型.
     * <p>原文：
     * Sets the underlying potion type
     *
     * @param type 用于设置基本药水状态的 {@link PotionType}
     * <p>原文：PotionType to set the base potion state to
     */
    void setBasePotionType(@Nullable PotionType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回基本药水的药水类型.
     * <p>原文：
     * Returns the potion type about the base potion
     *
     * @return 一个 PotionType 对象.
     * <p>原文：a PotionType object
     */
    @Nullable
    PotionType getBasePotionType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查自定义药水效果是否有效.
     * <p>原文：
     * Checks for the presence of custom potion effects.
     *
     * @return 如果自定义药水效果有效则返回true
     * <p>原文：true if custom potion effects are applied
     */
    boolean hasCustomEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这片云的全部自定义药水效果.
     * 插件应该在调用这个方法之前检查 hasCustomEffects() 是否返回 true.
     * <p>原文：
     * Gets an immutable list containing all custom potion effects applied to
     * this cloud.
     * <p>
     * Plugins should check that hasCustomEffects() returns true before calling
     * this method.
     *
     * @return 自定义药水效果的不可变集合
     * <p>原文：the immutable list of custom potion effects
     */
    @NotNull
    List<PotionEffect> getCustomEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向这片云添加一个自定义药水效果.
     * <p>原文：
     * Adds a custom potion effect to this cloud.
     *
     * @param effect 添加的药水效果
     * <p>原文：the potion effect to add
     * @param overwrite 是否覆盖当前存在的相同类型效果
     * <p>原文：true if any existing effect of the same type should be
     * overwritten
     * @return 如果这次调用成功添加效果则返回true
     * <p>原文：true if the effect was added as a result of this call
     */
    boolean addCustomEffect(@NotNull PotionEffect effect, boolean overwrite);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从这片云移除一个自定义药水效果.
     * <p>原文：
     * Removes a custom potion effect from this cloud.
     *
     * @param type 移除的药水效果类型
     * <p>原文：the potion effect type to remove
     * @return 如果这次调用成功移除效果则返回true
     * <p>原文：true if the effect was removed as a result of this call
     */
    boolean removeCustomEffect(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查这片云中是否存在一种特定的自定义药水效果类型.
     * <p>原文：
     * Checks for a specific custom potion effect type on this cloud.
     *
     * @param type 检查的药水效果类型
     * <p>原文：the potion effect type to check for
     * @return 存在这种效果则返回true
     * <p>原文：true if the potion has this effect
     */
    boolean hasCustomEffect(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从这片云移除所有自定义药水效果.
     * <p>原文：
     * Removes all custom potion effects from this cloud.
     */
    void clearCustomEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这片云的颜色, 即粒子的颜色.
     * <p>原文：
     * Gets the color of this cloud. Will be applied as a tint to its particles.
     *
     * @return 云的颜色
     * <p>原文：cloud color
     */
    @NotNull
    Color getColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这片云的颜色, 即粒子的颜色.
     * <p>原文：
     * Sets the color of this cloud. Will be applied as a tint to its particles.
     *
     * @param color 云的颜色
     * <p>原文：cloud color or {@code null} to reset to default
     */
    void setColor(@Nullable Color color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检索这片云的初始来源.
     * <p>原文：
     * Retrieve the original source of this cloud.
     *
     * @return 投掷药水的 {@link ProjectileSource}
     * <p>原文：the {@link ProjectileSource} that threw the LingeringPotion
     */
    @Nullable
    public ProjectileSource getSource();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这片云的初始来源.
     * <p>原文：
     * Set the original source of this cloud.
     *
     * @param source 投掷药水的{@link ProjectileSource}
     * <p>原文：the {@link ProjectileSource} that threw the LingeringPotion
     */
    public void setSource(@Nullable ProjectileSource source);

    // Paper start - owner API
    /**
     * Get the entity UUID for the owner of this area effect cloud.
     *
     * @return the entity owner uuid or null
     */
    @Nullable java.util.UUID getOwnerUniqueId();

    /**
     * Sets the entity UUID for the owner of this area effect cloud.
     *
     * @param ownerUuid the entity owner uuid or null to clear
     */
    void setOwnerUniqueId(@Nullable java.util.UUID ownerUuid);
    // Paper end
}
