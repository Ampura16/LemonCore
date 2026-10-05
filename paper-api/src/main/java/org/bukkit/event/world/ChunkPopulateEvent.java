package org.bukkit.event.world;

import org.bukkit.Chunk;
import org.bukkit.event.HandlerList;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个新生成的区块填充完毕时调用.
 * <p>
 * <b>注意:</b>请不要利用此事件在新生成的区块中生成方块, 应该使用 {@link BlockPopulator}.
 * <p>原文：
 * Thrown when a newly generated chunk has finished being populated.
 * <p>
 * <b>Note:</b> Do not use this to generated blocks in a newly generated chunk.
 * Use a {@link BlockPopulator} instead.
 */
public class ChunkPopulateEvent extends ChunkEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @ApiStatus.Internal
    public ChunkPopulateEvent(@NotNull final Chunk chunk) {
        super(chunk);
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
