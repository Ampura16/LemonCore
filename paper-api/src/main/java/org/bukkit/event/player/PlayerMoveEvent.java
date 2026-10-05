package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家移动事件.
 * <p>原文：
 * Holds information for player movement events
 */
public class PlayerMoveEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Location from;
    private Location to;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerMoveEvent(@NotNull final Player player, @NotNull final Location from, @Nullable final Location to) {
        super(player);
        this.from = from;
        this.to = to;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到玩家发生移动前的位置.
     * <p>原文：
     * Gets the location this player moved from
     *
     * @return 玩家移动之前的位置
     * <p>原文：Location the player moved from
     */
    @NotNull
    public Location getFrom() {
        return this.from;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家将要移动到此位置之前的位置.
     * <p>原文：
     * Sets the location to mark as where the player moved from
     *
     * @param from 记录玩家移动之前的位置
     * <p>原文：New location to mark as the players previous location
     */
    public void setFrom(@NotNull Location from) {
        this.validateLocation(from);
        this.from = from.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到玩家移动到的位置.
     * <p>原文：
     * Gets the location this player moved to
     *
     * @return 玩家移动到的位置
     * <p>原文：Location the player moved to
     */
    @NotNull // Paper
    public Location getTo() {
        return to;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家将要移动的位置.
     * <p>原文：
     * Sets the location that this player will move to
     *
     * @param to 玩家将要移动到的位置
     * <p>原文：New Location this player will move to
     */
    public void setTo(@NotNull Location to) {
        this.validateLocation(to);
        this.to = to.clone();
    }

    /**
     * Check if the player has changed position (even within the same block) in the event
     *
     * @return whether the player has changed position or not
     */
    public boolean hasChangedPosition() {
        return this.hasExplicitlyChangedPosition() || !this.from.getWorld().equals(this.to.getWorld());
    }

    /**
     * Check if the player has changed position (even within the same block) in the event, disregarding a possible world change
     *
     * @return whether the player has changed position or not
     */
    public boolean hasExplicitlyChangedPosition() {
        return this.from.getX() != this.to.getX() || this.from.getY() != this.to.getY() || this.from.getZ() != this.to.getZ();
    }

    /**
     * Check if the player has moved to a new block in the event
     *
     * @return whether the player has moved to a new block or not
     */
    public boolean hasChangedBlock() {
        return this.hasExplicitlyChangedBlock() || !this.from.getWorld().equals(this.to.getWorld());
    }

    /**
     * Check if the player has moved to a new block in the event, disregarding a possible world change
     *
     * @return whether the player has moved to a new block or not
     */
    public boolean hasExplicitlyChangedBlock() {
        return this.from.getBlockX() != this.to.getBlockX() || this.from.getBlockY() != this.to.getBlockY() || this.from.getBlockZ() != this.to.getBlockZ();
    }

    /**
     * Check if the player has changed orientation in the event
     *
     * @return whether the player has changed orientation or not
     */
    public boolean hasChangedOrientation() {
        return this.from.getPitch() != this.to.getPitch() || this.from.getYaw() != this.to.getYaw();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件的取消状态. 被取消的事件将不会在服务器上执行，但将仍然传递给其他插件.
     * <p>
     * 如果一个移动或传送事件被取消,玩家将被移动或传送回getFrom()定义的位置。这不会触发此事件.
     * <p>原文：
     * {@inheritDoc}
     * <p>
     * If a move or teleport event is cancelled, the player will be moved or
     * teleported back to the Location as defined by getFrom(). This will not
     * fire an event
     *
     * @return 是否取消了这个事件
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置取消状态的事件。被取消的事件不会在服务器执行,但仍将传递给其他插件执行.
     * <p>
     * 如果一个移动或传送事件被取消,玩家将被移动或传送回getFrom()定义的位置。这不会触发此事件.
     * <p>原文：
     * {@inheritDoc}
     * <p>
     * If a move or teleport event is cancelled, the player will be moved or
     * teleported back to the Location as defined by getFrom(). This will not
     * fire an event
     *
     * @param cancel 是否取消这个事件
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    private void validateLocation(@NotNull Location loc) {
        Preconditions.checkArgument(loc != null, "Cannot use null location!");
        Preconditions.checkArgument(loc.getWorld() != null, "Cannot use null location with null world!");
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
