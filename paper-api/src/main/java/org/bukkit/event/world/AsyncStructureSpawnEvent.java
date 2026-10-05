package org.bukkit.event.world;

import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.generator.structure.Structure;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当 {@link Structure} 在世界中自然生成时触发。
 * <p>原文：
 * Called when a {@link Structure} is naturally generated in the world.
 */
public class AsyncStructureSpawnEvent extends WorldEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Structure structure;
    private final BoundingBox boundingBox;

    private final int chunkX, chunkZ;

    private boolean cancelled;

    @ApiStatus.Internal
    public AsyncStructureSpawnEvent(@NotNull World world, @NotNull Structure structure, @NotNull BoundingBox boundingBox, int chunkX, int chunkZ) {
        super(world, true);
        this.structure = structure;
        this.boundingBox = boundingBox;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取生成的结构引用。
     * <p>原文：
     * Get the structure reference that is generated.
     *
     * @return 结构
     * <p>原文：Get the structure reference that is generated.
     * <p>原文：the structure
     */
    @NotNull
    public Structure getStructure() {
        return this.structure;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取结构的边界框。
     * <p>原文：
     * Get the bounding box of the structure.
     *
     * @return 边界框
     * <p>原文：Get the bounding box of the structure.
     * <p>原文：the bounding box
     */
    @NotNull
    public BoundingBox getBoundingBox() {
        return this.boundingBox.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取结构起始区块的 x 坐标。
     *
     * <b>注意，尝试检索或与此区块交互是不安全的。此事件仅供参考！</b>
     * <p>原文：
     * Get the x coordinate of the origin chunk of the structure.
     * <p>
     * <b>Note, it is not safe to attempt to retrieve or interact with this
     * chunk. This event is informative only!</b>
     *
     * @return 区块的 x 坐标
     * <p>原文：Get the x coordinate of the origin chunk of the structure.
     * <p>原文：the chunk x coordinate
     */
    public int getChunkX() {
        return this.chunkX;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取结构起始区块的 z 坐标。
     *
     * <b>注意，尝试检索或与此区块交互是不安全的。此事件仅供参考！</b>
     * <p>原文：
     * Get the z coordinate of the origin chunk of the structure.
     * <p>
     * <b>Note, it is not safe to attempt to retrieve or interact with this
     * chunk. This event is informative only!</b>
     *
     * @return 区块的 z 坐标
     * <p>原文：Get the z coordinate of the origin chunk of the structure.
     * <p>原文：the chunk z coordinate
     */
    public int getChunkZ() {
        return this.chunkZ;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
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
