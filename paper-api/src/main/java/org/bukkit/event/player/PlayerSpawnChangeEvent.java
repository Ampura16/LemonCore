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
 * 当玩家的重生点改变时触发此事件.
 * <p>原文：
 * This event is fired when the spawn point of the player is changed.
 *
 * @deprecated use {@link com.destroystokyo.paper.event.player.PlayerSetSpawnEvent}
 */
@Deprecated(forRemoval = true)
public class PlayerSpawnChangeEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Cause cause;
    private Location newSpawn;
    private boolean forced;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerSpawnChangeEvent(@NotNull final Player player, @Nullable Location newSpawn, boolean forced, @NotNull final Cause cause) {
        super(player);
        this.newSpawn = newSpawn;
        this.cause = cause;
        this.forced = forced;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取要设置的新重生点.
     * <p>原文：
     * Gets the new spawn to be set.
     *
     * @return 新重生点位置
     * <p>原文：new spawn location
     */
    @Nullable
    public Location getNewSpawn() {
        return this.newSpawn;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置新的重生点位置.
     * <p>原文：
     * Sets the new spawn location.
     *
     * @param newSpawn 新重生点位置，世界不能为 null
     * <p>原文：new spawn location, with non-null world
     */
    public void setNewSpawn(@Nullable Location newSpawn) {
        if (newSpawn != null) {
            Preconditions.checkArgument(newSpawn.getWorld() != null, "Spawn location must have a world set");
            this.newSpawn = newSpawn.clone();
        } else {
            this.newSpawn = null;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取重生点改变的原因.
     * <p>原文：
     * Gets the cause of spawn change.
     *
     * @return 改变原因
     * <p>原文：change cause
     */
    @NotNull
    public Cause getCause() {
        return this.cause;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取重生位置是否将被使用，无论床是否被阻挡.
     * <p>原文：
     * Gets if the spawn position will be used regardless of bed obstruction
     * rules.
     *
     * @return 如果是强制的则为 true
     * <p>原文：{@code true} if is forced
     */
    public boolean isForced() {
        return this.forced;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置重生位置是否将被使用，无论床是否被阻挡.
     * <p>原文：
     * Sets if the spawn position will be used regardless of bed obstruction
     * rules.
     *
     * @param forced 如果是强制的则为 true
     * <p>原文：{@code true} if forced
     */
    public void setForced(boolean forced) {
        this.forced = forced;
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

    public enum Cause {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示重生点由命令设置.
         * <p>原文：
         * Indicate the spawn was set by a command.
         */
        COMMAND,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示重生点由玩家与床交互设置.
         * <p>原文：
         * Indicate the spawn was set by the player interacting with a bed.
         */
        BED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示重生点由玩家与重生锚交互设置.
         * <p>原文：
         * Indicate the spawn was set by the player interacting with a respawn
         * anchor.
         */
        RESPAWN_ANCHOR,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示重生点由插件使用设置.
         * <p>原文：
         * Indicate the spawn was set by the use of plugins.
         */
        PLUGIN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示重生点因无效的床位置或空的重生锚而重置.
         * <p>原文：
         * Indicate the spawn was reset by an invalid bed position or empty
         * respawn anchor.
         */
        RESET,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示重生点因未知原因而改变.
         * <p>原文：
         * Indicate the spawn was caused by an unknown reason.
         */
        UNKNOWN
    }
}
