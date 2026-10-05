package org.bukkit.damage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个伤害来源.
 * <p>原文：
 * Represents a source of damage.
 */
public interface DamageSource {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取{@link DamageType}.
     * <p>原文：
     * Get the {@link DamageType}.
     *
     * @return 伤害类型
     * <p>原文：the damage type
     */
    @NotNull
    public DamageType getDamageType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取造成伤害的{@link Entity}.
     * <p>
     * 不要与{@link #getDirectEntity()}混淆, 造成伤害的实体是当受伤害者死亡时最终归因的实体.
     * 例如, 如果受伤害者被弹射物击中, 将返回射击者/投掷者.
     * <p>原文：
     * Get the {@link Entity} that caused the damage to occur.
     * <p>
     * Not to be confused with {@link #getDirectEntity()}, the causing entity is
     * the entity to which the damage is ultimately attributed if the receiver
     * is killed. If, for example, the receiver was damaged by a projectile, the
     * shooter/thrower would be returned.
     *
     * @return 一个Entity或null
     * <p>原文：an Entity or null
     */
    @Nullable
    public Entity getCausingEntity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取直接造成伤害的{@link Entity}.
     * <p>
     * 不要与{@link #getCausingEntity()}混淆, 直接实体是实际造成伤害的实体.
     * 例如, 如果受伤害者被弹射物击中, 将返回弹射物.
     * <p>原文：
     * Get the {@link Entity} that directly caused the damage.
     * <p>
     * Not to be confused with {@link #getCausingEntity()}, the direct entity is
     * the entity that actually inflicted the damage. If, for example, the
     * receiver was damaged by a projectile, the projectile would be returned.
     *
     * @return 一个Entity或null
     * <p>原文：an Entity or null
     */
    @Nullable
    public Entity getDirectEntity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取伤害来源的{@link Location}. 仅在伤害不是由实体造成时存在.
     * <p>原文：
     * Get the {@link Location} from where the damage originated. This will only
     * be present if an entity did not cause the damage.
     *
     * @return 位置, 如果没有则返回null
     * <p>原文：the location, or null if none
     * @apiNote the world of the location might be null for positioned-only damage source
     * not caused by any entity
     */
    @Nullable
    public Location getDamageLocation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取伤害来源的{@link Location}.
     * <p>
     * 这是一个便捷方法来获取伤害的最终位置.
     * 此方法将尝试返回{@link #getDamageLocation() 伤害位置}. 如果为null, 将返回{@link #getCausingEntity() 造成伤害实体的位置}.
     * 最后如果既没有伤害位置也没有造成伤害的实体, 将返回null.
     * <p>原文：
     * Get the {@link Location} from where the damage originated.
     * <p>
     * This is a convenience method to get the final location of the damage.
     * This method will attempt to return
     * {@link #getDamageLocation() the damage location}. If this is null, the
     * {@link #getCausingEntity() causing entity location} will be returned.
     * Finally if there is no damage location nor a causing entity, null will be
     * returned.
     *
     * @return 位置来源或null.
     * <p>原文：the source of the location or null.
     * @apiNote the world of the location might be null for positioned-only damage source
     * not caused by any entity
     */
    @Nullable
    public Location getSourceLocation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此伤害是否为间接伤害.
     * <p>
     * 如果{@link #getCausingEntity()}不等于{@link #getDirectEntity()}, 则伤害被视为间接的.
     * 例如, 如果骷髅射箭或玩家投掷药水, 就是这种情况.
     * <p>原文：
     * Get if this damage is indirect.
     * <p>
     * Damage is considered indirect if {@link #getCausingEntity()} is not equal
     * to {@link #getDirectEntity()}. This will be the case, for example, if a
     * skeleton shot an arrow or a player threw a potion.
     *
     * @return {@code true} 如果是间接伤害, {@code false} 否则.
     * <p>原文：{@code true} if is indirect, {@code false} otherwise.
     */
    public boolean isIndirect();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此伤害造成的饥饿消耗量.
     * <p>原文：
     * Get the amount of hunger exhaustion caused by this damage.
     *
     * @return 造成的饥饿消耗量.
     * <p>原文：the amount of hunger exhaustion caused.
     */
    public float getFoodExhaustion();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此伤害来源是否随难度缩放.
     * <p>原文：
     * Gets if this source of damage scales with difficulty.
     *
     * @return {@code True} 如果缩放.
     * <p>原文：{@code True} if scales.
     */
    public boolean scalesWithDifficulty();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个新的{@link DamageSource.Builder}.
     * <p>原文：
     * Create a new {@link DamageSource.Builder}.
     *
     * @param damageType 要使用的{@link DamageType}
     * <p>原文：the {@link DamageType} to use
     * @return 一个{@link DamageSource.Builder}
     * <p>原文：a {@link DamageSource.Builder}
     */
    @NotNull
    @SuppressWarnings("deprecation")
    public static Builder builder(@NotNull DamageType damageType) {
        return Bukkit.getUnsafe().createDamageSourceBuilder(damageType);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使构建{@link DamageSource}更容易的工具类. 只需要一个{@link DamageType}.
     * <p>原文：
     * Utility class to make building a {@link DamageSource} easier. Only a
     * {@link DamageType} is required.
     */
    public static interface Builder {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 设置造成伤害的{@link Entity}.
         * <p>原文：
         * Set the {@link Entity} that caused the damage.
         *
         * @param entity 实体
         * <p>原文：the entity
         * @return 此实例. 允许链式方法调用
         * <p>原文：this instance. Allows for chained method calls
         * @see DamageSource#getCausingEntity()
         */
        @NotNull
        public Builder withCausingEntity(@NotNull Entity entity);

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 设置直接造成伤害的{@link Entity}.
         * <p>原文：
         * Set the {@link Entity} that directly inflicted the damage.
         *
         * @param entity 实体
         * <p>原文：the entity
         * @return 此实例. 允许链式方法调用
         * <p>原文：this instance. Allows for chained method calls
         * @see DamageSource#getDirectEntity()
         */
        @NotNull
        public Builder withDirectEntity(@NotNull Entity entity);

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 设置伤害来源的{@link Location}.
         * <p>原文：
         * Set the {@link Location} of the source of damage.
         *
         * @param location 伤害发生的位置
         * <p>原文：the location where the damage occurred
         * @return 此实例. 允许链式方法调用
         * <p>原文：this instance. Allows for chained method calls
         * @see DamageSource#getSourceLocation()
         */
        @NotNull
        public Builder withDamageLocation(@NotNull Location location);

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 使用提供的参数创建一个新的{@link DamageSource}实例.
         * <p>原文：
         * Create a new {@link DamageSource} instance using the supplied
         * parameters.
         *
         * @return 伤害来源实例
         * <p>原文：the damage source instance
         */
        @NotNull
        public DamageSource build();
    }
}
