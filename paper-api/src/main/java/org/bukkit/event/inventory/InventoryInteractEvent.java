package org.bukkit.event.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 描述 HumanEntity 与物品栏内容之间交互的事件的抽象基类。
 * <p>原文：
 * An abstract base class for events that describe an interaction between a
 * HumanEntity and the contents of an Inventory.
 */
public abstract class InventoryInteractEvent extends InventoryEvent implements Cancellable {

    private Result result = Result.DEFAULT;

    @ApiStatus.Internal
    public InventoryInteractEvent(@NotNull InventoryView transaction) {
        super(transaction);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取执行点击的玩家。
     * <p>原文：
     * Gets the player who performed the click.
     *
     * @return 点击的玩家
     * <p>原文：Gets the player who performed the click.
     * <p>原文：The clicking player.
     */
    @NotNull
    public HumanEntity getWhoClicked() {
        return this.getView().getPlayer();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此事件的结果。这将改变此事件是否被视为已取消。
     * <p>原文：
     * Sets the result of this event. This will change whether this
     * event is considered cancelled.
     *
     * @param newResult 此事件的新 {@link org.bukkit.event.Event.Result}
     * <p>原文：the new {@link org.bukkit.event.Event.Result} for this event
     * @see #isCancelled()
     */
    public void setResult(@NotNull Result newResult) {
        this.result = newResult;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件的 {@link org.bukkit.event.Event.Result}。Result 描述了将应用于此事件相关物品栏的行为。
     * <p>原文：
     * Gets the {@link org.bukkit.event.Event.Result} of this event. The Result describes the
     * behavior that will be applied to the inventory in relation to this
     * event.
     *
     * @return 此事件的结果
     * <p>原文：Gets the {@link org.bukkit.event.Event.Result} of this event. The Result describes the
     * behavior that will be applied to the inventory in relation to this
     * event.
     * <p>原文：the Result of this event.
     */
    @NotNull
    public Result getResult() {
        return this.result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件是否被取消。这基于 {@link #getResult()} 返回的 Result 值。Result.ALLOW 和 Result.DEFAULT 将导致返回值为 false，但 Result.DENY 将导致返回值为 true。
     * <p>
     * {@inheritDoc}
     * <p>原文：
     * Gets whether this event is cancelled. This is based off of the
     * Result value returned by {@link #getResult()}. {@link Result#ALLOW} and
     * {@link Result#DEFAULT} will result in a returned value of false, but
     * {@link Result#DENY} will result in a returned value of {@code true}.
     * <p>
     * {@inheritDoc}
     *
     * @return 事件是否被取消
     * <p>原文：Gets whether or not this event is cancelled. This is based off of the
     * Result value returned by {@link #getResult()}.  Result.ALLOW and
     * Result.DEFAULT will result in a returned value of false, but
     * Result.DENY will result in a returned value of true.
     * <p>原文：whether the event is cancelled
     */
    @Override
    public boolean isCancelled() {
        return this.getResult() == Result.DENY;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * Cancellable 接口的 {@link #setResult(org.bukkit.event.Event.Result)} 代理方法。推荐使用 {@link #setResult(org.bukkit.event.Event.Result)}，因为它允许您指定超出 Result.DENY 和 Result.ALLOW 的结果。
     * <p>
     * {@inheritDoc}
     * <p>原文：
     * Proxy method to {@link #setResult(org.bukkit.event.Event.Result)} for the Cancellable
     * interface. {@link #setResult(org.bukkit.event.Event.Result)} is preferred, as it allows
     * you to specify the Result beyond {@link Result#DENY} and {@link Result#ALLOW}.
     * <p>
     * {@inheritDoc}
     *
     * @param cancel result becomes {@link Result#DENY} if {@code true}, {@link Result#ALLOW} if {@code false}
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.setResult(cancel ? Result.DENY : Result.ALLOW);
    }
}
