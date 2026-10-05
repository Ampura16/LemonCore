package io.papermc.paper.event.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有生物实体移动事件的信息
 * <p>
 * 不会对玩家触发；玩家移动请使用 {@link PlayerMoveEvent}。
 * <p>原文：
 * Holds information for living entity movement events
 * <p>
 * Does not fire for players; use {@link PlayerMoveEvent} for player movement.
 */
@NullMarked
public class EntityMoveEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Location from;
    private Location to;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityMoveEvent(final LivingEntity entity, final Location from, final Location to) {
        super(entity);
        this.from = from;
        this.to = to;
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此实体移动前的位置
     * <p>原文：
     * Gets the location this entity moved from
     *
     * @return 实体移动前的位置
     * <p>原文：Location the entity moved from
     */
    public Location getFrom() {
        return this.from;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置标记为实体移动起点的位置
     * <p>原文：
     * Sets the location to mark as where the entity moved from
     *
     * @param from 要标记为实体先前位置的新位置
     * <p>原文：New location to mark as the entity's previous location
     */
    public void setFrom(final Location from) {
        this.validateLocation(from);
        this.from = from.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此实体移动到的位置
     * <p>原文：
     * Gets the location this entity moved to
     *
     * @return 实体移动到的位置
     * <p>原文：Location the entity moved to
     */
    public Location getTo() {
        return this.to;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此实体将要移动到的位置
     * <p>原文：
     * Sets the location that this entity will move to
     *
     * @param to 此实体将移动到的新位置
     * <p>原文：New Location this entity will move to
     */
    public void setTo(final Location to) {
        this.validateLocation(to);
        this.to = to.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查事件中实体是否改变了位置（即使仍在同一方块内）
     * <p>原文：
     * Check if the entity has changed position (even within the same block) in the event
     *
     * @return 实体是否改变了位置
     * <p>原文：whether the entity has changed position or not
     */
    public boolean hasChangedPosition() {
        return this.hasExplicitlyChangedPosition() || !this.from.getWorld().equals(this.to.getWorld());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查事件中实体是否改变了位置（即使仍在同一方块内），不考虑可能的世界变化
     * <p>原文：
     * Check if the entity has changed position (even within the same block) in the event, disregarding a possible world change
     *
     * @return 实体是否改变了位置
     * <p>原文：whether the entity has changed position or not
     */
    public boolean hasExplicitlyChangedPosition() {
        return this.from.getX() != this.to.getX() || this.from.getY() != this.to.getY() || this.from.getZ() != this.to.getZ();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查事件中实体是否移动到了新方块
     * <p>原文：
     * Check if the entity has moved to a new block in the event
     *
     * @return 实体是否移动到了新方块
     * <p>原文：whether the entity has moved to a new block or not
     */
    public boolean hasChangedBlock() {
        return this.hasExplicitlyChangedBlock() || !this.from.getWorld().equals(this.to.getWorld());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查事件中实体是否移动到了新方块，不考虑可能的世界变化
     * <p>原文：
     * Check if the entity has moved to a new block in the event, disregarding a possible world change
     *
     * @return 实体是否移动到了新方块
     * <p>原文：whether the entity has moved to a new block or not
     */
    public boolean hasExplicitlyChangedBlock() {
        return this.from.getBlockX() != this.to.getBlockX() || this.from.getBlockY() != this.to.getBlockY() || this.from.getBlockZ() != this.to.getBlockZ();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查事件中实体是否改变了朝向
     * <p>原文：
     * Check if the entity has changed orientation in the event
     *
     * @return 实体是否改变了朝向
     * <p>原文：whether the entity has changed orientation or not
     */
    public boolean hasChangedOrientation() {
        return this.from.getPitch() != this.to.getPitch() || this.from.getYaw() != this.to.getYaw();
    }

    private void validateLocation(final Location loc) {
        Preconditions.checkArgument(loc != null, "Cannot use null location!");
        Preconditions.checkArgument(loc.getWorld() != null, "Cannot use null location with null world!");
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
