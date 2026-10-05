package com.destroystokyo.paper.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 警告：此事件只在有限的情况下触发，并非 {@link CreatureSpawnEvent} 触发的所有情况都会触发。
 * <p>
 * 你仍应监听 {@link CreatureSpawnEvent} 作为后备，只将此事件用作“增强”。此事件的目的是提升服务器性能，因此即使之后生成可能失败（例如因空间或光照受限而无法生成实体），也会触发。
 * <p>
 * 目前：基于 NATURAL 和 SPAWNER 的原因。<!-- Please submit a Pull Request for future additions. --> 此外，用自己的自定义实体替换实体注册的插件可能不会触发此事件。
 * <p>原文：
 * WARNING: This event only fires for a limited number of cases, and not for every case that {@link CreatureSpawnEvent} does.
 * <p>
 * You should still listen to {@link CreatureSpawnEvent} as a backup, and only use this event as an "enhancement".
 * The intent of this event is to improve server performance, so it fires even if the spawning might fail later, for
 * example when the entity would be unable to spawn due to limited space or lighting.
 * <p>
 * Currently: NATURAL and SPAWNER based reasons. <!-- Please submit a Pull Request for future additions. -->
 * Also, Plugins that replace Entity Registrations with their own custom entities might not fire this event.
 */
@NullMarked
public class PreCreatureSpawnEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Location location;
    private final EntityType type;
    private final CreatureSpawnEvent.SpawnReason reason;
    private boolean shouldAbortSpawn;

    private boolean cancelled;

    @ApiStatus.Internal
    public PreCreatureSpawnEvent(final Location location, final EntityType type, final CreatureSpawnEvent.SpawnReason reason) {
        this.location = location;
        this.type = type;
        this.reason = reason;
    }

    /**
     * @return 此生物生成的位置
     * <p>原文：The location this creature is being spawned at
     */
    public Location getSpawnLocation() {
        return this.location.clone();
    }

    /**
     * @return 正在生成的生物类型
     * <p>原文：The type of creature being spawned
     */
    public EntityType getType() {
        return this.type;
    }

    /**
     * @return 此生物生成的原因（例如 NATURAL 与 SPAWNER）
     * <p>原文：Reason this creature is spawning (ie, NATURAL vs SPAWNER)
     */
    public CreatureSpawnEvent.SpawnReason getReason() {
        return this.reason;
    }

    /**
     * @return 是否应中止生成过程，而不是继续尝试
     * <p>原文：If the spawn process should be aborted vs trying more attempts
     */
    public boolean shouldAbortSpawn() {
        return this.shouldAbortSpawn;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果你要全面阻止此类生成，并希望在此次取消后中止生成过程、不再进行更多尝试，请设置此项。
     * <p>原文：
     * Set this if you are more blanket blocking all types of these spawns, and wish to abort the spawn process from
     * trying more attempts after this cancellation.
     *
     * @param shouldAbortSpawn 设置是否应中止生成过程，而不是继续尝试
     * <p>原文：Set if the spawn process should be aborted vs trying more attempts
     */
    public void setShouldAbortSpawn(final boolean shouldAbortSpawn) {
        this.shouldAbortSpawn = shouldAbortSpawn;
    }

    /**
     * @return 此生物的生成是否被取消
     * <p>原文：If the spawn of this creature is cancelled or not
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 取消该事件比取消 {@link CreatureSpawnEvent} 更高效
     * <p>原文：
     * Cancelling this event is more efficient than cancelling {@link CreatureSpawnEvent}
     *
     * @param cancel 如果希望取消此事件并中止该生物的生成则为 {@code true}
     * <p>原文：{@code true} if you wish to cancel this event, and abort the spawn of this creature
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
