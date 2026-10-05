package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import io.papermc.paper.event.player.AbstractRespawnEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import java.util.Set;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家重生事件.
 * <p>原文：
 * Called when a player respawns.
 * <p>
 * If changing player state, see {@link com.destroystokyo.paper.event.player.PlayerPostRespawnEvent}
 * because the player is "reset" between this event and that event and some changes won't persist.
 */
public class PlayerRespawnEvent extends AbstractRespawnEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @ApiStatus.Internal
    @Deprecated(since = "1.16.1", forRemoval = true)
    public PlayerRespawnEvent(@NotNull final Player respawnPlayer, @NotNull final Location respawnLocation, final boolean isBedSpawn) {
        this(respawnPlayer, respawnLocation, isBedSpawn, false);
    }

    @ApiStatus.Internal
    @Deprecated(since = "1.19.4", forRemoval = true)
    public PlayerRespawnEvent(@NotNull final Player respawnPlayer, @NotNull final Location respawnLocation, final boolean isBedSpawn, final boolean isAnchorSpawn) {
        this(respawnPlayer, respawnLocation, isBedSpawn, isAnchorSpawn, false, RespawnReason.PLUGIN);
    }

    @ApiStatus.Internal
    public PlayerRespawnEvent(@NotNull final Player respawnPlayer, @NotNull final Location respawnLocation, final boolean isBedSpawn, final boolean isAnchorSpawn, final boolean missingRespawnBlock, @NotNull final RespawnReason respawnReason) {
        super(respawnPlayer, respawnLocation, isBedSpawn, isAnchorSpawn, missingRespawnBlock, respawnReason);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置一个新的重生位置.
     * <p>原文：
     * Sets the new respawn location.
     *
     * @param respawnLocation 新的重生位置
     * <p>原文：new location for the respawn
     */
    public void setRespawnLocation(@NotNull Location respawnLocation) {
        Preconditions.checkArgument(respawnLocation != null, "Respawn location can not be null");
        Preconditions.checkArgument(respawnLocation.getWorld() != null, "Respawn world can not be null");

        this.respawnLocation = respawnLocation.clone();
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

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于指定重生事件被触发原因的枚举.
     * <p>原文：
     * An enum to specify the reason a respawn event was called.
     */
    public enum RespawnReason {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当玩家死亡并按下重生按钮时.
         * <p>原文：
         * When the player dies and presses the respawn button.
         */
        DEATH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当玩家通过末地传送门离开末地时.
         * <p>原文：
         * When the player exits the end through the end portal.
         */
        END_PORTAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当插件使玩家重生时.
         * <p>原文：
         * When a plugin respawns the player.
         */
        PLUGIN
    }

    public enum RespawnFlag {
        /**
         * Will use the bed spawn location
         */
        BED_SPAWN,
        /**
         * Will use the respawn anchor location
         */
        ANCHOR_SPAWN,
        /**
         * Is caused by going to the end portal in the end.
         */
        END_PORTAL
    }
}
