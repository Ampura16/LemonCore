package io.papermc.paper.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在钟被敲响时调用。
 * <p>原文：
 * Called when a bell is rung.
 *
 * @deprecated 请使用 {@link org.bukkit.event.block.BellRingEvent}
 * <p>原文：use {@link org.bukkit.event.block.BellRingEvent}
 */
@Deprecated(since = "1.19.4")
public class BellRingEvent extends org.bukkit.event.block.BellRingEvent {

    @ApiStatus.Internal
    public BellRingEvent(@NotNull Block block, @NotNull BlockFace direction, @Nullable Entity entity) {
        super(block, direction, entity);
    }
}
