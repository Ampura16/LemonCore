package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体恢复生命值时触发本事件.
 * <p>原文：
 * Stores data for health-regain events
 */
public class EntityRegainHealthEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private double amount;
    private final RegainReason regainReason;
    private final boolean isFastRegen;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityRegainHealthEvent(@NotNull final Entity entity, final double amount, @NotNull final RegainReason regainReason) {
        this(entity, amount, regainReason, false);
    }

    @ApiStatus.Internal
    public EntityRegainHealthEvent(@NotNull final Entity entity, final double amount, @NotNull final RegainReason regainReason, boolean isFastRegen) {
        super(entity);
        this.amount = amount;
        this.regainReason = regainReason;
        this.isFastRegen = isFastRegen;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体本次恢复的生命值.
     * <p>原文：
     * Gets the amount of regained health
     *
     * @return 恢复的生命值
     * <p>原文：The amount of health regained
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体恢复的生命值.
     * <p>原文：
     * Sets the amount of regained health
     *
     * @param amount 实体将恢复的生命值
     * <p>原文：the amount of health the entity will regain
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体恢复生命值的原因.
     * <p>原文：
     * Gets the reason for why the entity is regaining health
     *
     * @return 详细说明生命值恢复原因的 RegainReason 枚举
     * <p>原文：A RegainReason detailing the reason for the entity regaining
     *     health
     */
    @NotNull
    public RegainReason getRegainReason() {
        return this.regainReason;
    }

    /**
     * Is this event a result of the fast regeneration mechanic
     *
     * @return Whether the event is the result of a fast regeneration mechanic
     */
    public boolean isFastRegen() {
        return this.isFastRegen;
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
     * 详细说明实体生命值恢复原因的枚举.
     * <p>原文：
     * An enum to specify the type of health regaining that is occurring
     */
    public enum RegainReason {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家因和平模式 (difficulty=0) 带来的再生效果恢复生命值.
         * <p>原文：
         * When a player regains health from regenerating due to Peaceful mode
         * (difficulty=0)
         */
        REGEN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家因饥饿度已满, 自然恢复生命值.
         * <p>原文：
         * When a player regains health from regenerating due to their hunger
         * being satisfied
         */
        SATIATED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家通过食用食物恢复生命值.
         * <p>原文：
         * When an animal regains health from eating consumables
         */
        EATING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 末影龙通过末影水晶恢复生命值.
         * <p>原文：
         * When an ender dragon regains health from an ender crystal
         */
        ENDER_CRYSTAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家被药水或魔法治愈.
         * <p>原文：
         * When a player is healed by a potion or spell
         */
        MAGIC,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家被药水或魔法随时间持续治疗.
         * <p>原文：
         * When a player is healed over time by a potion or spell
         */
        MAGIC_REGEN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 凋零生成时填补其生命值.
         * <p>原文：
         * When a wither is filling its health during spawning
         */
        WITHER_SPAWN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 实体因凋零效果受到伤害.
         * <p>原文：
         * When an entity is damaged by the Wither potion effect
         */
        WITHER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 上述原因未能涵盖的任何其它原因.
         * <p>原文：
         * Any other reason not covered by the reasons above
         */
        CUSTOM
    }
}
