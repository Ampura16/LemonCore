package org.bukkit.event.entity;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当人类实体经历饥饿时调用。
 *
 * 饥饿等级大于 4.0 会导致饱和度降低 1。
 * <p>原文：
 * Called when a human entity experiences exhaustion.
 * <br>
 * An exhaustion level greater than 4.0 causes a decrease in saturation by 1.
 */
public class EntityExhaustionEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ExhaustionReason exhaustionReason;
    private float exhaustion;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityExhaustionEvent(@NotNull HumanEntity human, @NotNull ExhaustionReason exhaustionReason, float exhaustion) {
        super(human);
        this.exhaustionReason = exhaustionReason;
        this.exhaustion = exhaustion;
    }

    @NotNull
    @Override
    public HumanEntity getEntity() {
        return (HumanEntity) super.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件的 {@link ExhaustionReason}。
     * <p>原文：
     * Gets the {@link ExhaustionReason} for this event
     *
     * @return 饥饿原因
     * <p>原文：the exhaustion reason
     */
    @NotNull
    public ExhaustionReason getExhaustionReason() {
        return this.exhaustionReason;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取要添加到玩家当前饥饿值的饥饿量。
     * <p>原文：
     * Get the amount of exhaustion to add to the player's current exhaustion.
     *
     * @return 饥饿量
     * <p>原文：amount of exhaustion
     */
    public float getExhaustion() {
        return this.exhaustion;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置要应用于玩家的饥饿值。
     * <p>
     * 玩家最大饥饿值为 40。达到此限制时不会抛出错误。此值可以为负数，但当饥饿值低于 0 时行为未知。
     * <p>原文：
     * Set the exhaustion to apply to the player.
     * <p>
     * The maximum exhaustion that a player can have is 40. No error will be
     * thrown if this limit is hit. This value may be negative, but there is
     * unknown behavior for when exhaustion is below 0.
     *
     * @param exhaustion 要添加的新饥饿值
     * <p>原文：new exhaustion to add
     */
    public void setExhaustion(float exhaustion) {
        this.exhaustion = exhaustion;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家饥饿事件发生的原因。
     * <p>原文：
     * The reason for why a PlayerExhaustionEvent takes place
     */
    public enum ExhaustionReason {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家挖掘方块。
         * <p>原文：
         * Player mines a block
         */
        BLOCK_MINED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家拥有饥饿药水效果。
         * <p>原文：
         * Player has the hunger potion effect
         */
        HUNGER_EFFECT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家受到伤害。
         * <p>原文：
         * Player takes damage
         */
        DAMAGED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家攻击其他实体。
         * <p>原文：
         * Player attacks another entity
         */
        ATTACK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家冲刺跳跃。
         * <p>原文：
         * Player is sprint jumping
         */
        JUMP_SPRINT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家跳跃。
         * <p>原文：
         * Player jumps
         */
        JUMP,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家游泳一厘米。
         * <p>原文：
         * Player swims one centimeter
         */
        SWIM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家在水下行走一厘米。
         * <p>原文：
         * Player walks underwater one centimeter
         */
        WALK_UNDERWATER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家在水面移动一厘米。
         * <p>原文：
         * Player moves on the surface of water one centimeter
         */
        WALK_ON_WATER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家冲刺一厘米。
         * <p>原文：
         * Player sprints one centimeter
         */
        SPRINT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家潜行一厘米（不影响饥饿值，但仍会触发事件）。
         * <p>原文：
         * Player crouches one centimeter (does not effect exhaustion, but fires
         * nonetheless)
         */
        CROUCH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家行走一厘米（不影响饥饿值，但仍会触发事件）。
         * <p>原文：
         * Player walks one centimeter (does not effect exhaustion, but fires
         * nonetheless)
         */
        WALK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家恢复生命值。
         * <p>原文：
         * Player regenerated health
         */
        REGEN,
        /**
         * Player was affected by an enchantment
         * @see <a href="https://minecraft.wiki/w/Enchantment_definition#apply_exhaustion">Minecraft Wiki: Enchantment Effects "apply_exhaustion"</a>
         */
        ENCHANTMENT_EFFECT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 未知饥饿原因。
         * <p>原文：
         * Unknown exhaustion reason
         */
        UNKNOWN
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
}
