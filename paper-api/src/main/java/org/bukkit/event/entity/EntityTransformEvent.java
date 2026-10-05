package org.bukkit.event.entity;

import java.util.Collections;
import java.util.List;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体将转化/蜕变为其它实体时触发本事件.
 * <p>原文：
 * Called when an entity is about to be replaced by another entity.
 */
@NullMarked
public class EntityTransformEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final List<Entity> convertedList;
    private final Entity converted;
    private final TransformReason transformReason;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityTransformEvent(Entity original, List<Entity> convertedList, TransformReason transformReason) {
        super(original);
        this.convertedList = Collections.unmodifiableList(convertedList);
        this.converted = convertedList.getFirst();
        this.transformReason = transformReason;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取原实体将转变成的实体.
     * <p>原文：
     * Gets the entities that the original entity was transformed to.
     *
     * @return 实体转化列表
     * <p>原文：The transformed entities.
     */
    public List<Entity> getTransformedEntities() {
        return this.convertedList;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取原实体将转变成的实体.
     *
     * 将返回实体转化列表中的第一个实体.
     * <p>原文：
     * Gets the entity that the original entity was transformed to.
     * <br>
     * This returns the first entity in the transformed entity list.
     *
     * @return 转变成的实体
     * <p>原文：The transformed entity.
     * @see #getTransformedEntities()
     */
    public Entity getTransformedEntity() {
        return this.converted;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取发生此次转化的原因.
     * <p>原文：
     * Gets the reason for the conversion that has occurred.
     *
     * @return 发生此次转化的原因
     * <p>原文：The reason for conversion that has occurred.
     */
    public TransformReason getTransformReason() {
        return this.transformReason;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public enum TransformReason {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 僵尸受到疗愈, 变为村民.
         * <p>原文：
         * When a zombie gets cured and a villager is spawned.
         */
        CURED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 实体在细雪中发抖, 转化为新实体.
         * <p>原文：
         * When an entity is shaking in Powder Snow and a new entity spawns.
         */
        FROZEN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 村民受到感染, 变为僵尸.
         * <p>原文：
         * When a villager gets infected and a zombie villager spawns.
         */
        INFECTION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 实体在水中溺亡, 转化为新实体.
         * <p>原文：
         * When an entity drowns in water and a new entity spawns.
         */
        DROWNED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 哞菇的毛被修剪, 变成普通牛.
         * <p>原文：
         * When a mooshroom (or MUSHROOM_COW) is sheared and a cow spawns.
         */
        SHEARED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 雷击中了某个实体.
         * <p>原文：
         * When lightning strikes a entity.
         */
        LIGHTNING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 史莱姆分裂成多个小史莱姆.
         * <p>原文：
         * When a slime splits into multiple smaller slimes.
         */
        SPLIT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 猪灵(或疣猪兽)因身处主世界而转化为僵尸化变种.
         * <p>原文：
         * When a piglin (or hoglin) converts to a zombified version from overworld presence.
         */
        PIGLIN_ZOMBIFIED,
        /**
         * When a tadpole converts to a frog
         */
        METAMORPHOSIS,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 未知原因.
         * <p>原文：
         * When reason is unknown.
         */
        UNKNOWN
    }
}
