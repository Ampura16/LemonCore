package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当生物选定目标或取消选定另一个实体时调用。
 * <p>原文：
 * Called when a creature targets or untargets another entity
 */
public class EntityTargetEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Entity target;
    private final TargetReason reason;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityTargetEvent(@NotNull final Entity entity, @Nullable final Entity target, @NotNull final TargetReason reason) {
        super(entity);
        this.target = target;
        this.reason = reason;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回选定目标的原因。
     * <p>原文：
     * Returns the reason for the targeting
     *
     * @return 原因
     * <p>原文：The reason
     */
    @NotNull
    public TargetReason getReason() {
        return this.reason;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体正在 targeting 的实体。
     * <p>
     * 在事件因生物忘记其目标而被调用时，此值将为 null。
     * <p>原文：
     * Get the entity that this is targeting.
     * <p>
     * This will be {@code null} in the case that the event is called when the mob
     * forgets its target.
     *
     * @return 实体
     * <p>原文：The entity
     */
    @Nullable
    public Entity getTarget() {
        return this.target;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置你希望生物 targeting 的实体。
     * <p>
     * 可以设置为 null，null 会导致实体没有目标。
     * <p>
     * 这与取消事件不同。取消事件会导致实体保留原始目标，而设置为 null 会导致实体被重置。
     * <p>原文：
     * Set the entity that you want the mob to target instead.
     * <p>
     * It is possible to be {@code null}, {@code null} will cause the entity to be
     * target-less.
     * <p>
     * This is different from cancelling the event. Cancelling the event will
     * cause the entity to keep an original target, while setting to be null
     * will cause the entity to be reset.
     *
     * @param target 要 targeting 的实体
     * <p>原文：The entity to target
     */
    public void setTarget(@Nullable Entity target) {
        this.target = target;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 指定选定目标原因的枚举。
     * <p>原文：
     * An enum to specify the reason for the targeting
     */
    public enum TargetReason {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体的目标已死亡，因此不再 targeting 它时。
         * <p>原文：
         * When the entity's target has died, and so it no longer targets it
         */
        TARGET_DIED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体没有目标时，它会攻击最近的玩家。
         * <p>原文：
         * When the entity doesn't have a target, so it attacks the nearest
         * player
         */
        CLOSEST_PLAYER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当目标攻击实体时，实体会 targeting 它。
         * <p>原文：
         * When the target attacks the entity, so entity targets it
         */
        TARGET_ATTACKED_ENTITY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当目标攻击一个猪灵僵尸同伴时，整个群体都会以此原因 targeting 他。
         * <p>原文：
         * When the target attacks a fellow pig zombie, so the whole group
         * will target him with this reason.
         *
         * @deprecated 已被 {@link #TARGET_ATTACKED_NEARBY_ENTITY} 取代。
         * <p>原文：obsoleted by {@link #TARGET_ATTACKED_NEARBY_ENTITY}
         */
        @Deprecated(since = "1.13", forRemoval = true)
        PIG_ZOMBIE_TARGET,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当目标因任何原因被忘记时。
         * <p>原文：
         * When the target is forgotten for whatever reason.
         */
        FORGOT_TARGET,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当目标攻击实体的主人时，实体会 targeting 它。
         * <p>原文：
         * When the target attacks the owner of the entity, so the entity
         * targets it.
         */
        TARGET_ATTACKED_OWNER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体的主人攻击目标时，实体会 targeting 它。
         * <p>原文：
         * When the owner of the entity attacks the target attacks, so the
         * entity targets it.
         */
        OWNER_ATTACKED_TARGET,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体没有目标时，实体会随机选择一个。
         * <p>原文：
         * When the entity has no target, so the entity randomly chooses one.
         */
        RANDOM_TARGET,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体在保卫村庄时选择目标。
         * <p>原文：
         * When an entity selects a target while defending a village.
         */
        DEFEND_VILLAGE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当目标攻击附近同类型的实体时，实体会 targeting 它。
         * <p>原文：
         * When the target attacks a nearby entity of the same type, so the entity targets it
         */
        TARGET_ATTACKED_NEARBY_ENTITY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当僵尸 targeting 一个实体时召唤援军，援军会 targeting 同一个实体。
         * <p>原文：
         * When a zombie targeting an entity summons reinforcements, so the reinforcements target the same entity
         */
        REINFORCEMENT_TARGET,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体与另一个实体碰撞后 targeting 它。
         * <p>原文：
         * When an entity targets another entity after colliding with it.
         */
        COLLISION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 用于自定义调用事件。
         * <p>原文：
         * For custom calls to the event.
         */
        CUSTOM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体没有目标时，它会攻击最近的实体。
         * <p>原文：
         * When the entity doesn't have a target, so it attacks the nearest
         * entity
         */
        CLOSEST_ENTITY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当袭击实体选择与其同伴相同的目标时。
         * <p>原文：
         * When a raiding entity selects the same target as one of its compatriots.
         */
        FOLLOW_LEADER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当另一个实体通过手持所需物品（如小麦）来引诱此实体时。
         * <p>原文：
         * When another entity tempts this entity by having a desired item such
         * as wheat in its hand.
         */
        TEMPT,
        /**
         * When the target is in a different dimension
         */
        TARGET_OTHER_LEVEL,
        /**
         * When the target is in creative or spectator gamemode, or the difficulty is peaceful, or other reasons
         */
        TARGET_INVALID,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 实体改变目标的当前未知原因。
         * <p>原文：
         * A currently unknown reason for the entity changing target.
         */
        UNKNOWN;
    }
}
