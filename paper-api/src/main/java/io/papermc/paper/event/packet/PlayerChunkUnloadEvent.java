package io.papermc.paper.event.packet;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.ChunkEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当 {@link Player} 收到区块卸载数据包时调用。
 * <p>
 * 只应用于数据包/客户端相关的内容，不应用于修改服务端。
 * <p>原文：
 * Is called when a {@link Player} receives a chunk unload packet.
 * <p>
 * Should only be used for packet/clientside related stuff.
 * Not intended for modifying server side.
 */
@NullMarked
public class PlayerChunkUnloadEvent extends ChunkEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;

    @ApiStatus.Internal
    public PlayerChunkUnloadEvent(final Chunk chunk, final Player player) {
        super(chunk);
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
