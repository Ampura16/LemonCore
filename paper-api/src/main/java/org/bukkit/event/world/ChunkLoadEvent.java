package org.bukkit.event.world;

import org.bukkit.Chunk;
import org.bukkit.event.HandlerList;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个区块被加载时调用.
 * <p>原文：
 * Called when a chunk is loaded
 */
public class ChunkLoadEvent extends ChunkEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final boolean newChunk;

    @ApiStatus.Internal
    public ChunkLoadEvent(@NotNull final Chunk chunk, final boolean newChunk) {
        super(chunk);
        this.newChunk = newChunk;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个区块是否为最近创建的.
     * <p>
     * <b>注意:</b>请不要利用本事件在新生成的区块中生成方块, 你应该使用 {@link BlockPopulator}.
     * <p>原文：
     * Gets if this chunk was newly created or not.
     * <p>
     * <b>Note:</b> Do not use this to generated blocks in a newly generated chunk.
     * Use a {@link BlockPopulator} instead.
     *
     * @return 如果区块是新的，则为true，否则为false
     * <p>原文：{@code true} if the chunk is new, otherwise {@code false}
     */
    public boolean isNewChunk() {
        return this.newChunk;
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
