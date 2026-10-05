package org.bukkit.event.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 活塞相关事件
 * <p>原文：
 * Called when a piston block is triggered
 */
public abstract class BlockPistonEvent extends BlockEvent implements Cancellable {

    private final BlockFace direction;
    private boolean cancelled;

    protected BlockPistonEvent(@NotNull final Block block, @NotNull final BlockFace direction) {
        super(block);
        this.direction = direction;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断是否是粘性活塞.
     * <p>原文：
     * Returns {@code true} if the Piston in the event is sticky.
     *
     * @return 如是粘性活塞则返回true.
     * <p>原文：stickiness of the piston
     */
    public boolean isSticky() {
        return this.block.getType() == Material.STICKY_PISTON || this.block.getType() == Material.MOVING_PISTON;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回活塞的朝向.
     * <p>原文：
     * Return the direction in which the piston will operate.
     *
     * @return 活塞的朝向
     * <p>原文：direction of the piston
     */
    @NotNull
    public BlockFace getDirection() {
        // Both are meh!
        // return ((PistonBaseMaterial) block.getType().getNewData(block.getData())).getFacing();
        // return ((PistonBaseMaterial) block.getState().getData()).getFacing();
        return this.direction;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
