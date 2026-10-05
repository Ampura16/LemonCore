package org.bukkit.event.world;

import org.bukkit.Chunk;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个区块被卸载时调用
 * <p>原文：
 * Called when a chunk is unloaded
 */
public class ChunkUnloadEvent extends ChunkEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private boolean saveChunk;

    @ApiStatus.Internal
    public ChunkUnloadEvent(@NotNull final Chunk chunk) {
        this(chunk, true);
    }

    @ApiStatus.Internal
    public ChunkUnloadEvent(@NotNull Chunk chunk, boolean save) {
        super(chunk);
        this.saveChunk = save;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个区块是否将被保存.
     * <p>原文：
     * Return whether this chunk will be saved to disk.
     *
     * @return 区块保存状态
     * <p>原文：chunk save status
     */
    public boolean isSaveChunk() {
        return this.saveChunk;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个区块是否将被保存.
     * <p>原文：
     * Set whether this chunk will be saved to disk.
     *
     * @param saveChunk 区块保存状态
     * <p>原文：chunk save status
     */
    public void setSaveChunk(boolean saveChunk) {
        this.saveChunk = saveChunk;
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
