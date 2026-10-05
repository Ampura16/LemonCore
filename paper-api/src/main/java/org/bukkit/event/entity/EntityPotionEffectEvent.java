package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体上的药水效果发生改变时触发本事件.
 * <p>
 * 如果此事件被取消, 将不会对实体做出改变.
 * <p>原文：
 * Called when a potion effect is modified on an entity.
 * <p>
 * If the event is cancelled, no change will be made on the entity.
 */
public class EntityPotionEffectEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PotionEffect oldEffect;
    private final PotionEffect newEffect;
    private final Cause cause;
    private final Action action;
    private boolean override;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityPotionEffectEvent(@NotNull LivingEntity livingEntity, @Nullable PotionEffect oldEffect, @Nullable PotionEffect newEffect, @NotNull Cause cause, @NotNull Action action, boolean override) {
        super(livingEntity);
        this.oldEffect = oldEffect;
        this.newEffect = newEffect;
        this.cause = cause;
        this.action = action;
        this.override = override;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被移除的旧药水效果.
     * <p>原文：
     * Gets the old potion effect of the changed type, which will be removed.
     *
     * @return 旧药水效果, 如果效果类型没变则为 null (也就是新增的效果)
     * <p>原文：The old potion effect or {@code null} if the entity did not have the
     * changed effect type.
     */
    @Nullable
    public PotionEffect getOldEffect() {
        return this.oldEffect;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被应用的新药水效果.
     * <p>原文：
     * Gets new potion effect of the changed type to be applied.
     *
     * @return 新药水效果, 如果药水效果将被移除则为 null
     * <p>原文：The new potion effect or {@code null} if the effect of the changed type
     * will be removed.
     */
    @Nullable
    public PotionEffect getNewEffect() {
        return this.newEffect;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取药水效果改变的原因.
     * <p>原文：
     * Gets the cause why the effect has changed.
     *
     * @return 药水效果改变的原因
     * <p>原文：A Cause value why the effect has changed.
     */
    @NotNull
    public Cause getCause() {
        return this.cause;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取对此药水效果采取的动作.
     * <p>原文：
     * Gets the action which will be performed on the potion effect type.
     *
     * @return 对此药水效果采取的动作
     * <p>原文：An action to be performed on the potion effect type.
     */
    @NotNull
    public Action getAction() {
        return this.action;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取发生调整的药水效果类型.
     * <p>原文：
     * Gets the modified potion effect type.
     *
     * @return 发生调整的药水效果类型
     * <p>原文：The effect type which will be modified on the entity.
     */
    @NotNull
    public PotionEffectType getModifiedType() {
        return (this.oldEffect == null) ? ((this.newEffect == null) ? null : this.newEffect.getType()) : this.oldEffect.getType(); // todo not null?
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回新效果是否将覆盖旧效果 (仅适用于 CHANGED 动作).
     * <p>原文：
     * Returns if the new potion effect will override the old potion effect
     * (Only applicable for the CHANGED Action).
     *
     * @return 新效果是否将覆盖旧效果
     * <p>原文：If the new effect will override the old one.
     */
    public boolean isOverride() {
        return this.override;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置新效果是否将覆盖旧效果 (仅适用于 CHANGED 动作).
     * <p>原文：
     * Sets if the new potion effect will override the old potion effect (Only
     * applicable for the CHANGED action).
     *
     * @param override 新效果是否将覆盖旧效果
     * <p>原文：If the new effect will override the old one.
     */
    public void setOverride(boolean override) {
        this.override = override;
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
     * 指定对药水效果采取的动作的枚举.
     * <p>原文：
     * An enum to specify the action to be performed.
     */
    public enum Action {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 因实体尚无对应类型的药水效果时而添加药水效果.
         * <p>原文：
         * When the potion effect is added because the entity didn't have its
         * type.
         */
        ADDED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 实体已有对应类型的药水效果, 但效果属性发生改变.
         * <p>原文：
         * When the entity already had the potion effect type, but the effect is
         * changed.
         */
        CHANGED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 效果因所有状态效果被清除时而被移除.
         * <p>原文：
         * When the effect is removed due to all effects being removed.
         */
        CLEARED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 对应类型的药水效果被完全移除.
         * <p>原文：
         * When the potion effect type is completely removed.
         */
        REMOVED
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 指定药水效果改变原因的枚举.
     * <p>原文：
     * An enum to specify the cause why an effect was changed.
     */
    public enum Cause {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体站在区域效果云里面时.
         * <p>原文：
         * When the entity stands inside an area effect cloud.
         */
        AREA_EFFECT_CLOUD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体被光灵箭或药箭击中时.
         * <p>原文：
         * When the entity is hit by a spectral or tipped arrow.
         */
        ARROW,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体由于其它实体的攻击(例如:洞穴蜘蛛或潜影贝导弹)而遭受药水效果的影响时.
         * <p>原文：
         * When the entity is inflicted with a potion effect due to an entity
         * attack (e.g. a cave spider or a shulker bullet).
         */
        ATTACK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体获得美西螈给予的效果时.
         * <p>原文：
         * When an entity gets the effect from an axolotl.
         */
        AXOLOTL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当信标的增益效果应用到附近的实体时.
         * <p>原文：
         * When beacon effects get applied due to the entity being nearby.
         */
        BEACON,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当使用 /effect 命令改变药水效果时.
         * <p>原文：
         * When a potion effect is changed due to the /effect command.
         */
        COMMAND,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体获得潮涌核心赋予的效果时.
         * <p>原文：
         * When the entity gets the effect from a conduit.
         */
        CONDUIT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当僵尸村民到村民的转化开始或完成时.
         * <p>原文：
         * When a conversion from a villager zombie to a villager is started or
         * finished.
         */
        CONVERSION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当所有效果因死亡而被移除时.
         * <p>原文：
         * When all effects are removed due to death.
         */
        DEATH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体受到海豚给予的效果时.
         * <p>原文：
         * When the entity gets the effect from a dolphin.
         */
        DOLPHIN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当效果因过期而被移除时.
         * <p>原文：
         * When the effect was removed due to expiration.
         */
        EXPIRATION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因食用食物而受到药水效果影响时
         * (例如:当玩家吃下部分食物或将曲奇喂给鹦鹉时).
         * <p>原文：
         * When an effect is inflicted due to food (e.g. when a player eats or a
         * cookie is given to a parrot).
         */
        FOOD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当幻术师使自己隐身时.
         * <p>原文：
         * When an illusion illager makes himself disappear.
         */
        ILLUSION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当所有效果因喝下一桶牛奶而被清除时.
         * <p>原文：
         * When all effects are removed due to a bucket of milk.
         */
        MILK,
        /**
         * When the entity gets the effect from a nautilus.
         */
        NAUTILUS,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当玩家击杀灾厄巡逻队队长, 获得不详之兆效果时.
         * <p>原文：
         * When a player gets bad omen after killing a patrol captain.
         *
         * @deprecated no longer used, player now gets an ominous bottle instead
         */
        @Deprecated(since = "1.21", forRemoval = true)
        PATROL_CAPTAIN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当药水效果被插件修改时.
         * <p>原文：
         * When a potion effect is modified through the plugin methods.
         */
        PLUGIN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体喝下药水时.
         * <p>原文：
         * When the entity drinks a potion.
         */
        POTION_DRINK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因喷溅药水受到药水效果影响时.
         * <p>原文：
         * When the entity is inflicted with an effect due to a splash potion.
         */
        POTION_SPLASH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当蜘蛛在困难模式下生成时获得效果时.
         * <p>原文：
         * When a spider gets effects when spawning on hard difficulty.
         */
        SPIDER_SPAWN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体被不死图腾挽救, 获得增益效果时.
         * <p>原文：
         * When the entity gets effects from a totem item saving its life.
         */
        TOTEM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体戴上海龟壳下水, 获得水下呼吸效果时.
         * <p>原文：
         * When the entity gets water breathing by wearing a turtle helmet.
         */
        TURTLE_HELMET,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当原因缺失时.
         * <p>原文：
         * When the Cause is missing.
         */
        UNKNOWN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当村民交易后, 恢复生命值时.
         * <p>原文：
         * When a villager gets regeneration after a trade.
         */
        VILLAGER_TRADE,
        /**
         * When an entity gets the effect from a warden.
         */
        WARDEN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体触碰凋零玫瑰时.
         * <p>原文：
         * When an entity comes in contact with a wither rose.
         */
        WITHER_ROSE
    }
}
