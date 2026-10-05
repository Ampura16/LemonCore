package org.bukkit.event.entity;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当爬行者被闪电击中时触发该事件.
 * <p>
 * 若该事件被取消,那么爬行者将不会被强化.
 * <p>原文：
 * Called when a Creeper is struck by lightning.
 * <p>
 * If this event is cancelled, the Creeper will not be powered.
 */
public class CreeperPowerEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PowerCause cause;
    private LightningStrike bolt;

    private boolean cancelled;

    @ApiStatus.Internal
    public CreeperPowerEvent(@NotNull final Creeper creeper, @NotNull final LightningStrike bolt, @NotNull final PowerCause cause) {
        this(creeper, cause);
        this.bolt = bolt;
    }

    @ApiStatus.Internal
    public CreeperPowerEvent(@NotNull final Creeper creeper, @NotNull final PowerCause cause) {
        super(creeper);
        this.cause = cause;
    }

    @NotNull
    @Override
    public Creeper getEntity() {
        return (Creeper) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回击中爬行者的闪电
     * <p>原文：
     * Gets the lightning bolt which is striking the Creeper.
     *
     * @return 击中爬行者的闪电实体
     * <p>原文：The Entity for the lightning bolt which is striking the Creeper
     */
    @Nullable
    public LightningStrike getLightning() {
        return this.bolt;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回爬行者被强(弱)化的原因
     * <p>原文：
     * Gets the cause of the creeper being (un)powered.
     *
     * @return 爬行者被强(弱)化的原因
     * <p>原文：A PowerCause value detailing the cause of change in power.
     */
    @NotNull
    public PowerCause getCause() {
        return this.cause;
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
     * 造成这次强化的原因
     * <p>原文：
     * An enum to specify the cause of the change in power
     */
    public enum PowerCause {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于闪电击中导致的强化
         * <p>
         * Powered state: true (强化)
         * <p>原文：
         * Power change caused by a lightning bolt
         * <p>
         * Powered state: {@code true}
         */
        LIGHTNING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于其他原因造成的强化 (可能是插件)
         * <p>
         * Powered state: true (强化)
         * <p>原文：
         * Power change caused by something else (probably a plugin)
         * <p>
         * Powered state: {@code true}
         */
        SET_ON,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于其他原因造成的弱化 (可能是插件)
         * <p>
         * Powered state: false (弱化)
         * <p>原文：
         * Power change caused by something else (probably a plugin)
         * <p>
         * Powered state: {@code false}
         */
        SET_OFF
    }
}
