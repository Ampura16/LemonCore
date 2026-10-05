package org.bukkit.event.world;

import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当世界内的世界发生快进时调用.
 * <p>
 * 如果本事件被取消, 时间将不会改变.
 * <p>原文：
 * Called when the time skips in a world.
 * <p>
 * If the event is cancelled the time will not change.
 */
public class TimeSkipEvent extends WorldEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final SkipReason skipReason;
    private long skipAmount;

    private boolean cancelled;

    @ApiStatus.Internal
    public TimeSkipEvent(@NotNull World world, @NotNull SkipReason skipReason, long skipAmount) {
        super(world);
        this.skipReason = skipReason;
        this.skipAmount = skipAmount;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取发生时间快进的原因.
     * <p>原文：
     * Gets the reason why the time has skipped.
     *
     * @return 时间快进的原因
     * <p>原文：a SkipReason value detailing why the time has skipped
     */
    @NotNull
    public SkipReason getSkipReason() {
        return this.skipReason;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取快进了多少时间.
     * <p>原文：
     * Gets the amount of time that was skipped.
     *
     * @return 时间快进了多久
     * <p>原文：Amount of time skipped
     */
    public long getSkipAmount() {
        return this.skipAmount;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置快进的时间.
     * <p>原文：
     * Sets the amount of time to skip.
     *
     * @param skipAmount 快进的时间
     * <p>原文：Amount of time to skip
     */
    public void setSkipAmount(long skipAmount) {
        this.skipAmount = skipAmount;
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

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 时间快进原因枚举.
     * <p>原文：
     * An enum specifying the reason the time skipped.
     */
    public enum SkipReason {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 因使用"/time"命令.
         * <p>原文：
         * When time is changed using the vanilla /time command.
         */
        COMMAND,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 因插件调整了时间.
         * <p>原文：
         * When time is changed by a plugin.
         */
        CUSTOM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 世界内的所有玩家在床上睡觉, 度过夜晚.
         * <p>原文：
         * When time is changed by all players sleeping in their beds and the
         * night skips.
         */
        NIGHT_SKIP
    }
}
