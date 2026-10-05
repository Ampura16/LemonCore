package io.papermc.paper.event.packet;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.ChunkEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当 {@link Player} 收到 {@link Chunk} 时调用
 * <p>
 * 例如，可用于在玩家收到区块时生成虚假实体。
 * <p>
 * 只应用于数据包/客户端相关的内容，不应用于修改服务端状态。
 * <p>原文：
 * Is called when a {@link Player} receives a {@link Chunk}
 * <p>
 * Can for example be used for spawning a fake entity when the player receives a chunk.
 * <p>
 * Should only be used for packet/clientside related stuff.
 * Not intended for modifying server side state.
 */
@NullMarked
public class PlayerChunkLoadEvent extends ChunkEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;

    @ApiStatus.Internal
    public PlayerChunkLoadEvent(final Chunk chunk, final Player player) {
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
