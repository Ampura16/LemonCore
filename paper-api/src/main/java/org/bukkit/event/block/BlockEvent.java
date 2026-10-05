package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表与方块相关的事件.
 * <p>原文：
 * Represents a block related event.
 */
public abstract class BlockEvent extends Event {

    protected Block block;

    protected BlockEvent(@NotNull final Block block) {
        this.block = block;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个事件涉及的方块.
     * <p>原文：
     * Gets the block involved in this event.
     *
     * @return 这个事件涉及的方块
     * <p>原文：The Block which block is involved in this event
     */
    @NotNull
    public final Block getBlock() {
        return this.block;
    }
}
