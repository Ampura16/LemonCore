package io.papermc.paper.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家设置信标效果时调用
 * <p>原文：
 * Called when a player sets the effect for a beacon
 */
@NullMarked
public class PlayerChangeBeaconEffectEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block beacon;
    private @Nullable PotionEffectType primary;
    private @Nullable PotionEffectType secondary;
    private boolean consumeItem = true;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerChangeBeaconEffectEvent(final Player player, final @Nullable PotionEffectType primary, final @Nullable PotionEffectType secondary, final Block beacon) {
        super(player);
        this.primary = primary;
        this.secondary = secondary;
        this.beacon = beacon;
    }

    /**
     * @return 主效果
     * <p>原文：the primary effect
     */
    public @Nullable PotionEffectType getPrimary() {
        return this.primary;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置主效果
     * <p>
     * 注意：主效果仍必须是信标的有效效果之一。
     * <p>原文：
     * Sets the primary effect
     * <p>
     * NOTE: The primary effect still has to be one of the valid effects for a beacon.
     *
     * @param primary 主效果
     * <p>原文：the primary effect
     */
    public void setPrimary(final @Nullable PotionEffectType primary) {
        this.primary = primary;
    }

    /**
     * @return 次效果
     * <p>原文：the secondary effect
     */
    public @Nullable PotionEffectType getSecondary() {
        return this.secondary;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置副效果
     * <p>
     * 仅当信标能够接受副效果时才会生效。注意：副效果仍必须是信标的有效效果。
     * <p>原文：
     * Sets the secondary effect
     * <p>
     * This only has an effect when the beacon is able to accept a secondary effect.
     * NOTE: The secondary effect still has to be a valid effect for a beacon.
     *
     * @param secondary 次效果
     * <p>原文：the secondary effect
     */
    public void setSecondary(final @Nullable PotionEffectType secondary) {
        this.secondary = secondary;
    }

    /**
     * @return 与此事件关联的信标方块
     * <p>原文：the beacon block associated with this event
     */
    public Block getBeacon() {
        return this.beacon;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取用于更改信标的物品是否会被消耗。
     * <p>
     * 与 {@link #isCancelled()} 无关。如果事件被取消，物品<b>不会</b>被消耗。
     * <p>原文：
     * Gets if the item used to change the beacon will be consumed.
     * <p>
     * Independent of {@link #isCancelled()}. If the event is cancelled
     * the item will <b>NOT</b> be consumed.
     *
     * @return 如果物品将被消耗则为 {@code true}
     * <p>原文：{@code true} if item will be consumed
     */
    public boolean willConsumeItem() {
        return this.consumeItem;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置用于更改信标的物品是否应被消耗。
     * <p>
     * 与 {@link #isCancelled()} 无关。如果事件被取消，物品<b>不会</b>被消耗。
     * <p>原文：
     * Sets if the item used to change the beacon should be consumed.
     * <p>
     * Independent of {@link #isCancelled()}. If the event is cancelled
     * the item will <b>NOT</b> be consumed.
     *
     * @param consumeItem 如果物品应被消耗则为 {@code true}
     * <p>原文：{@code true} if item should be consumed
     */
    public void setConsumeItem(final boolean consumeItem) {
        this.consumeItem = consumeItem;
    }

    /**
     * {@inheritDoc}
     * <p>
     * If a {@link PlayerChangeBeaconEffectEvent} is cancelled, the changes will
     * not take effect
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * {@inheritDoc}
     * <p>
     * If cancelled, the item will <b>NOT</b> be consumed regardless of what {@link #willConsumeItem()} says
     * <p>
     * If a {@link PlayerChangeBeaconEffectEvent} is cancelled, the changes will not be applied
     * or saved.
     */
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
