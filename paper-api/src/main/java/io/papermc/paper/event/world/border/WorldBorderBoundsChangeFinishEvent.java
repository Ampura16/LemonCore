package io.papermc.paper.event.world.border;

import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在移动中的世界边界完成移动时调用。
 * <p>原文：
 * Called when a moving world border has finished its move.
 */
@NullMarked
public class WorldBorderBoundsChangeFinishEvent extends WorldBorderEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final double oldSize;
    private final double newSize;
    private final double duration;

    @ApiStatus.Internal
    public WorldBorderBoundsChangeFinishEvent(final World world, final WorldBorder worldBorder, final double oldSize, final double newSize, final double duration) {
        super(world, worldBorder);
        this.oldSize = oldSize;
        this.newSize = newSize;
        this.duration = duration;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取世界边界原来的大小。
     * <p>原文：
     * Gets the old size of the worldborder.
     *
     * @return 原来的大小
     * <p>原文：the old size
     */
    public double getOldSize() {
        return this.oldSize;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取世界边界的新大小。
     * <p>原文：
     * Gets the new size of the worldborder.
     *
     * @return 新的大小
     * <p>原文：the new size
     */
    public double getNewSize() {
        return this.newSize;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此世界边界完成变化所用的时长。
     * <p>
     * 如果 {@link WorldBorderCenterChangeEvent} 的处理器将时长设为 0，则可能为 0。
     * <p>原文：
     * Gets the duration this worldborder took to make the change.
     * <p>
     * Can be 0 if handlers for {@link WorldBorderCenterChangeEvent} set the duration to 0.
     *
     * @return 过渡的持续时间
     * <p>原文：the duration of the transition
     */
    public double getDuration() {
        return this.duration;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
