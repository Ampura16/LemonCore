package io.papermc.paper.event.player;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 每当玩家的盾牌因能够禁用盾牌的其他实体攻击而被禁用时调用。最常见的情况是另一名玩家用斧头攻击。
 * <p>
 * 值得注意的是，该事件不同于 {@link PlayerItemCooldownEvent}，会在物品进入冷却之前触发。因此，如果该事件被取消，则不会调用 {@link PlayerItemCooldownEvent}，因为盾牌根本没有被禁用。
 * <p>原文：
 * Called whenever a players shield is disabled due to an attack from another entity that was capable of disabling the
 * shield. This, most commonly, may be another player attacking with an axe.
 * <p>
 * Notably, this even is distinct from a {@link PlayerItemCooldownEvent} and will fire prior to the item going on
 * cooldown.
 * It follows that, if this event is cancelled, no {@link PlayerItemCooldownEvent} is called as the shield is never
 * disabled in the first place.
 */
@NullMarked
public class PlayerShieldDisableEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity damager;
    private int cooldown;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerShieldDisableEvent(final Player player, final Entity damager, final int cooldown) {
        super(player);
        this.damager = damager;
        this.cooldown = cooldown;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供禁用盾牌的攻击者。
     * <p>原文：
     * Provides the damager that disabled the shield.
     *
     * @return 以导致盾牌被禁用的方式伤害玩家的实体实例。
     * <p>原文：the entity instance that damaged the player in a way that caused the shield to be disabled.
     */
    public Entity getDamager() {
        return this.damager;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取被禁用的盾牌将被禁用的冷却时长（刻）。
     * <p>
     * 值得注意的是，该值并非最终值，之后可能被 {@link PlayerItemCooldownEvent} 更改，因为若此事件未被取消就会调用该事件。
     * <p>原文：
     * Gets the cooldown the disabled shield will be disabled for in ticks.
     * <p>
     * Notably, this value is not final as it might be changed by a {@link PlayerItemCooldownEvent} down the line,
     * as said event is called if this event is not cancelled.
     *
     * @return 冷却时间（刻）
     * <p>原文：cooldown in ticks
     */
    public int getCooldown() {
        return this.cooldown;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置盾牌的冷却时间（刻）。
     * <p>
     * 值得注意的是，该值并非最终值，之后可能被 {@link PlayerItemCooldownEvent} 更改，因为若此事件未被取消就会调用该事件。
     * <p>原文：
     * Sets the cooldown of the shield in ticks.
     * <p>
     * Notably, this value is not final as it might be changed by a {@link PlayerItemCooldownEvent} down the line,
     * as said event is called if this event is not cancelled.
     *
     * @param cooldown 冷却时间（刻），必须为正数
     * <p>原文：cooldown in ticks, has to be a positive number
     */
    public void setCooldown(final int cooldown) {
        Preconditions.checkArgument(cooldown >= 0, "The cooldown has to be equal to or greater than 0!");
        this.cooldown = cooldown;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
