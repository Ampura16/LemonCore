package org.bukkit.event.raid;

import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当{@link Raid 袭击}终止时触发本事件.
 * <p>原文：
 * Called when a {@link Raid} is stopped.
 */
public class RaidStopEvent extends RaidEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Reason reason;

    @ApiStatus.Internal
    public RaidStopEvent(@NotNull Raid raid, @NotNull World world, @NotNull Reason reason) {
        super(raid, world);
        this.reason = reason;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回袭击终止原因.
     * <p>原文：
     * Returns the stop reason.
     *
     * @return 原因
     * <p>原文：Reason
     */
    @NotNull
    public Reason getReason() {
        return this.reason;
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

    public enum Reason {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于游戏难度被调为和平.
         * <p>原文：
         * Because the difficulty has been changed to peaceful.
         */
        PEACE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 此袭击耗时太长, 没有最终结果.
         * <p>原文：
         * The raid took a long time without a final result.
         */
        TIMEOUT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 袭击正常结束.
         * <p>原文：
         * Finished the raid.
         */
        FINISHED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 找不到合适的地点生成袭击者.
         * <p>原文：
         * Couldn't find a suitable place to spawn raiders.
         */
        UNSPAWNABLE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 发生袭击的位置不再是一个村庄.
         * <p>原文：
         * The place where the raid occurs no longer be a village.
         */
        NOT_IN_VILLAGE
    }
}
