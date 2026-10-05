package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当 {@link Entity} 被移除时调用。
 * <p>
 * 此事件仅应用于监控。在此事件期间或之后修改实体的结果是未指定的。
 * 此事件不会为 {@link org.bukkit.entity.Player} 调用。
 * <p>原文：
 * Called when an {@link Entity} is removed.
 * <p>
 * This event should only be used for monitoring. The result
 * of modifying the entity during or after this event is unspecified.
 * This event is not called for a {@link org.bukkit.entity.Player}.
 * <p>
 * It differs from {@link com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent} as to when it is called.
 * Modifications to the entity, as noted above, are not defined and are expected to not be persisted in e.g., chunk
 * unloads.
 */
public class EntityRemoveEvent extends EntityEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Cause cause;

    @ApiStatus.Internal
    public EntityRemoveEvent(@NotNull Entity entity, @NotNull Cause cause) {
        super(entity);
        this.cause = cause;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体被移除的原因。
     * <p>原文：
     * Gets the cause why the entity got removed.
     *
     * @return 实体被移除的原因
     * <p>原文：the cause why the entity got removed
     */
    @NotNull
    public Cause getCause() {
        return this.cause;
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
     * 表示实体被移除的各种方式。
     * <p>原文：
     * Represents various ways an entity gets removed.
     */
    public enum Cause {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体死亡时。
         * <p>原文：
         * When an entity dies.
         */
        DEATH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体消失时。这包括距离太远的生物、在地面上放置时间过长的物品或箭矢，或区域效果云。
         * <p>原文：
         * When an entity does despawn. This includes mobs which are too far away,
         * items or arrows which lay to long on the ground or area effect cloud.
         */
        DESPAWN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因作为物品掉落而被移除时。
         * 例如，三叉戟或下落的沙子。
         * <p>
         * <b>注意：</b> 根据其他因素，如游戏规则，实际上可能不会掉落物品，但原因仍然是掉落。
         * <p>原文：
         * When an entity gets removed because it drops as an item.
         * For example, trident or falling sand.
         * <p>
         * <b>Note:</b> Depending on other factors, such as gamerules, no item will actually drop,
         * the cause, however, will still be drop.
         */
        DROP,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因进入方块而被移除时。
         * 例如，蜜蜂或蠹虫。
         * <p>原文：
         * When an entity gets removed because it enters a block.
         * For example, bees or silverfish.
         */
        ENTER_BLOCK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因爆炸而被移除时。
         * 例如，苦力怕、TNT 或烟花火箭。
         * <p>原文：
         * When an entity gets removed because it exploded.
         * For example, creepers, tnt or firework.
         */
        EXPLODE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因击中某物而被移除时。这主要适用于弹射物。
         * <p>原文：
         * When an entity gets removed because it hit something. This mainly applies to projectiles.
         */
        HIT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因与另一个实体合并而被移除时。
         * 例如，物品或经验值。
         * <p>原文：
         * When an entity gets removed because it merges with another one.
         * For example, items or xp.
         */
        MERGE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因位于世界底部以下而被移除时。
         * 这仅适用于立即被移除的实体，有些实体会受到伤害。
         * <p>原文：
         * When an entity gets removed because it is too far below the world.
         * This only applies to entities which get removed immediately,
         * some entities get damage instead.
         */
        OUT_OF_WORLD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因被拾取而被移除时。
         * 例如，物品、箭矢、经验值或落在玩家肩膀上的鹦鹉。
         * <p>原文：
         * When an entity gets removed because it got pickup.
         * For example, items, arrows, xp or parrots which get on a player shoulder.
         */
        PICKUP,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因玩家退出游戏而与玩家一起被移除时。
         * 例如，玩家退出时与玩家一起被移除的船。
         * <p>原文：
         * When an entity gets removed with a player because the player quits the game.
         * For example, a boat which gets removed with the player when he quits.
         */
        PLAYER_QUIT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当插件手动移除实体时。
         * <p>原文：
         * When a plugin manually removes an entity.
         */
        PLUGIN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因转化为另一个实体而被移除时。
         * <p>原文：
         * When an entity gets removed because it transforms into another one.
         */
        TRANSFORMATION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体所在的区块被卸载时。
         * <p>原文：
         * When the chunk an entity is in gets unloaded.
         */
        UNLOAD,
        DISCARD
    }
}
