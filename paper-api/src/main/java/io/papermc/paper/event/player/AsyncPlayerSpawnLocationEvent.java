package io.papermc.paper.event.player;

import io.papermc.paper.connection.PlayerConfigurationConnection;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在选择玩家的出生位置时调用（即加入服务器或重新配置时）。
 * <p>
 * 在所有事件处理器返回且出生位置加载完成之前，玩家将一直停留在配置阶段。
 * </p>
 * <p>原文：
 * Called when selecting a player's spawn location (i.e. joining the server or reconfiguring).
 *
 * <p>The player will be kept in the configuration phase until all event handlers return and
 * the spawn location is loaded.</p>
 */
@ApiStatus.Experimental
@NullMarked
public class AsyncPlayerSpawnLocationEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PlayerConfigurationConnection connection;
    private final boolean newPlayer;
    private Location spawnLocation;

    @ApiStatus.Internal
    public AsyncPlayerSpawnLocationEvent(final PlayerConfigurationConnection connection, final Location spawnLocation, final boolean newPlayer) {
        super(true);
        this.connection = connection;
        this.spawnLocation = spawnLocation;
        this.newPlayer = newPlayer;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在出生的玩家的连接。
     * <p>原文：
     * Gets the spawning player's connection.
     *
     * @return 玩家连接
     * <p>原文：the player connection
     */
    public PlayerConfigurationConnection getConnection() {
        return this.connection;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家的出生位置。
     * <p>
     * 如果玩家之前在此服务器上玩过，默认为玩家数据文件中的位置。对于新玩家，默认出生位置为 {@link Server#getRespawnWorld() respawn world} 的 {@link World#getSpawnLocation() spawn location}。
     * </p>
     * <p>原文：
     * Gets the player's spawn location.
     *
     * <p>If the player has played on this server before, defaults to the location inside the player data file.
     * For new players, the default spawn location is the {@link World#getSpawnLocation() spawn location}
     * of the {@link Server#getRespawnWorld() respawn world}.</p>
     *
     * @return 出生位置
     * <p>原文：the spawn location
     */
    public Location getSpawnLocation() {
        return this.spawnLocation;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置玩家的出生位置。
     * <p>原文：
     * Sets player's spawn location.
     *
     * @param location 出生位置
     * <p>原文：the spawn location
     */
    public void setSpawnLocation(final Location location) {
        this.spawnLocation = location.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果玩家是首次加入服务器，则返回 true。
     * <p>原文：
     * Returns true if the player is joining the server for the first time.
     *
     * @return 玩家是否为新玩家
     * <p>原文：whether the player is new
     */
    public boolean isNewPlayer() {
        return this.newPlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
