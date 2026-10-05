package io.papermc.paper.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当实体尝试执行重锤猛击时调用。
 * <p>原文：
 * Called when an entity attempts to perform a smash attack.
 */
@NullMarked
public class EntityAttemptSmashAttackEvent extends EntityEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final LivingEntity target;
    private final ItemStack weapon;
    private final boolean originalResult;
    private Result result = Result.DEFAULT;

    @ApiStatus.Internal
    public EntityAttemptSmashAttackEvent(
        final LivingEntity attacker,
        final LivingEntity target,
        final ItemStack weapon,
        final boolean originalResult
    ) {
        super(attacker);
        this.target = target;
        this.weapon = weapon;
        this.originalResult = originalResult;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回重锤猛击尝试的目标。
     * <p>原文：
     * Yields the target of the attempted smash attack.
     *
     * @return 目标实体
     * <p>原文：the target entity
     */
    public LivingEntity getTarget() {
        return target;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回重锤猛击尝试中所用物品堆的副本。
     * <p>原文：
     * Yields a copy of the itemstack used in the smash attack attempt.
     *
     * @return 物品堆
     * <p>原文：the itemstack
     */
    public ItemStack getWeapon() {
        return weapon.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回服务器计算出的原始结果。
     * <p>原文：
     * Yields the original result the server computed.
     *
     * @return 如果按原版逻辑此次尝试会成功则为 {@code true}，否则为 {@code false}。
     * <p>原文：{@code true} if this attempt would have been successful by vanilla's logic, {@code false} otherwise.
     */
    public boolean getOriginalResult() {
        return originalResult;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此事件的实际结果。结果可取以下三个值之一：
     * <ul>
     * <li>{@link Result#ALLOW}：尝试将成功。</li>
     * <li>{@link Result#DENY}：尝试将失败。</li>
     * <li>{@link Result#DEFAULT}：如果 {@link #getOriginalResult()} 为 {@code true} 则尝试成功，否则失败。</li>
     * </ul>
     * <p>原文：
     * Yields the effective result of this event.
     * The result may take one of three values:
     *
     * <ul>
     *     <li>{@link Result#ALLOW}: The attempt will succeed.</li>
     *     <li>{@link Result#DENY}: The attempt will fail.</li>
     *     <li>{@link Result#DEFAULT}: The attempt will succeed if {@link #getOriginalResult()} is {@code true} and fail otherwise.</li>
     * </ul>
     *
     * @return 结果。
     * <p>原文：the result.
     */
    public Result getResult() {
        return this.result;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为该事件配置新的结果。传入的结果可取以下三个值之一：
     * <ul>
     * <li>{@link Result#ALLOW}：尝试将成功。</li>
     * <li>{@link Result#DENY}：尝试将失败。</li>
     * <li>{@link Result#DEFAULT}：如果 {@link #getOriginalResult()} 为 {@code true} 则尝试成功，否则失败。</li>
     * </ul>
     * <p>原文：
     * Configures a new result for this event.
     * The passes result may take one of three values:
     *
     * <ul>
     *     <li>{@link Result#ALLOW}: The attempt will succeed.</li>
     *     <li>{@link Result#DENY}: The attempt will fail.</li>
     *     <li>{@link Result#DEFAULT}: The attempt will succeed if {@link #getOriginalResult()} is {@code true} and fail otherwise.</li>
     * </ul>
     *
     * @param result 事件的新结果。
     * <p>原文：the new result of the event.
     */
    public void setResult(final Result result) {
        this.result = result;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
