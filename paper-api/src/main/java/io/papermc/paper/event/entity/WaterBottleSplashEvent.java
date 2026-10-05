package io.papermc.paper.event.entity;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.entity.PotionSplashEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当喷溅型水瓶“溅开”并以不同方式影响不同实体时调用。
 * <p>原文：
 * Called when a splash water potion "splashes" and affects
 * different entities in different ways.
 */
@NullMarked
public class WaterBottleSplashEvent extends PotionSplashEvent {

    private final Set<LivingEntity> rehydrate;
    private final Set<LivingEntity> extinguish;

    @ApiStatus.Internal
    public WaterBottleSplashEvent(
        final ThrownPotion potion,
        final @Nullable Entity hitEntity,
        final @Nullable Block hitBlock,
        final @Nullable BlockFace hitFace,
        final Map<LivingEntity, Double> affectedEntities,
        final Set<LivingEntity> rehydrate,
        final Set<LivingEntity> extinguish
    ) {
        super(potion, hitEntity, hitBlock, hitFace, affectedEntities);
        this.rehydrate = rehydrate;
        this.extinguish = extinguish;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将因此事件而受到伤害的实体的不可变集合。请使用该类的其他方法修改哪些实体受到伤害。
     * <p>原文：
     * Gets an immutable collection of entities that
     * will take damage as a result of this event. Use
     * other methods on this class to modify which entities
     * take damage.
     *
     * @return 实体的不可变集合
     * <p>原文：an immutable collection of entities
     * @see #doNotDamageAsWaterSensitive(LivingEntity)
     * @see #damageAsWaterSensitive(LivingEntity)
     */
    public @Unmodifiable Collection<LivingEntity> getToDamage() {
        return this.affectedEntities.entrySet().stream().filter(entry -> entry.getValue() > 0).map(Map.Entry::getKey).collect(Collectors.toUnmodifiableSet());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将此实体从将受到伤害的群组中移除。
     * <p>原文：
     * Removes this entity from the group that
     * will be damaged.
     *
     * @param entity 要移除的实体
     * <p>原文：entity to remove
     */
    public void doNotDamageAsWaterSensitive(final LivingEntity entity) {
        this.affectedEntities.remove(entity);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将此实体加入将受到伤害的群组
     * <p>原文：
     * Adds this entity to the group that
     * will be damaged
     *
     * @param entity 要添加的实体
     * <p>原文：entity to add
     */
    public void damageAsWaterSensitive(final LivingEntity entity) {
        this.affectedEntities.put(entity, 1.0);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将因此被补水的实体的可变集合。
     * <p>
     * 截至 1.19.3，其中只会包含美西螈，因为它是唯一可被补水的实体类型，但将来可能会改变。
     * <p>原文：
     * Get a mutable collection of entities
     * that will be rehydrated by this.
     * <p>
     * As of 1.19.3 this only will contain Axolotls as they
     * are the only entity type that can be rehydrated, but
     * it may change in the future.
     *
     * @return 实体
     * <p>原文：the entities
     */
    public Collection<LivingEntity> getToRehydrate() {
        return this.rehydrate;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取因此事件而被熄灭的实体的可变集合。
     * <p>原文：
     * Get a mutable collection of entities that will
     * be extinguished as a result of this event.
     *
     * @return 将被熄灭的实体
     * <p>原文：entities to be extinguished
     */
    public Collection<LivingEntity> getToExtinguish() {
        return this.extinguish;
    }

    /**
     * @return 令人困惑的集合，请勿使用
     * <p>原文：a confusing collection, don't use it
     * @deprecated 请使用 {@link #getToDamage()}
     * <p>原文：Use {@link #getToDamage()}
     */
    @Deprecated(since = "1.19.3")
    @Override
    public Collection<LivingEntity> getAffectedEntities() {
        return super.getAffectedEntities();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 对该事件没有意义，因为强度不会变化。
     * <p>原文：
     * Doesn't make sense for this event as intensity doesn't vary.
     *
     * @return 令人困惑的值
     * <p>原文：a confusing value
     * @deprecated 检查 {@link #getToDamage()} 是否包含实体
     * <p>原文：check if {@link #getToDamage()} contains an entity
     */
    @Deprecated(since = "1.19.3")
    @Override
    public double getIntensity(final LivingEntity entity) {
        return super.getIntensity(entity);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 对该事件没有意义，因为强度不会变化。
     * <p>原文：
     * Doesn't make sense for this event as intensity doesn't vary.
     *
     * @deprecated 请使用 {@link #damageAsWaterSensitive(LivingEntity)} 或 {@link #doNotDamageAsWaterSensitive(LivingEntity)} 更改受到伤害的实体
     * <p>原文：use {@link #damageAsWaterSensitive(LivingEntity)}
     * or {@link #doNotDamageAsWaterSensitive(LivingEntity)} to change which entities are
     * damaged
     */
    @Deprecated(since = "1.19.3")
    @Override
    public void setIntensity(final LivingEntity entity, final double intensity) {
        super.setIntensity(entity, intensity);
    }
}
