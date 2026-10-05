package io.papermc.paper.event.entity;

import org.bukkit.entity.PufferFish;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在 {@link PufferFish} 膨胀或收缩之前调用。
 * <p>原文：
 * Called just before a {@link PufferFish} inflates or deflates.
 */
@NullMarked
public class PufferFishStateChangeEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final int newPuffState;
    private boolean cancelled;

    @ApiStatus.Internal
    public PufferFishStateChangeEvent(final PufferFish entity, final int newPuffState) {
        super(entity);
        this.newPuffState = newPuffState;
    }

    @Override
    public PufferFish getEntity() {
        return (PufferFish) super.getEntity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取 {@link PufferFish} 的<strong>新</strong>膨胀状态。
     * <p>
     * 如果该事件未被取消，这就是 {@link PufferFish} 在事件之后的新膨胀状态。<br> 要获取当前膨胀状态，请参阅 {@link PufferFish#getPuffState()}。
     * <p>原文：
     * Get the <strong>new</strong> puff state of the {@link PufferFish}.
     * <p>
     * This is what the {@link PufferFish}'s new puff state will be after this event if it isn't cancelled.<br>
     * Refer to {@link PufferFish#getPuffState()} to get the current puff state.
     *
     * @return <strong>新</strong>膨胀状态，0 表示未膨胀，1 表示轻微膨胀，2 表示完全膨胀
     * <p>原文：The <strong>new</strong> puff state, 0 being not inflated, 1 being slightly inflated and 2 being fully inflated
     */
    public int getNewPuffState() {
        return this.newPuffState;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取 {@link PufferFish} 是否将要膨胀。
     * <p>原文：
     * Get if the {@link PufferFish} is going to inflate.
     *
     * @return 是否将要膨胀
     * <p>原文：If it's going to inflate
     */
    public boolean isInflating() {
        return this.newPuffState > this.getEntity().getPuffState();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取 {@link PufferFish} 是否将要收缩。
     * <p>原文：
     * Get if the {@link PufferFish} is going to deflate.
     *
     * @return 是否将要收缩
     * <p>原文：If it's going to deflate
     */
    public boolean isDeflating() {
        return this.newPuffState < this.getEntity().getPuffState();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否取消 {@link PufferFish} 的膨胀/收缩。
     * <p>原文：
     * Set whether to cancel the {@link PufferFish} (in/de)flating.
     *
     * @param cancel 如果希望取消（膨胀/收缩）则为 {@code true}
     * <p>原文：{@code true} if you wish to cancel the (in/de)flation
     */
    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
