package org.bukkit.event.weather;

import org.bukkit.World;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 雷击事件
 * <p>原文：
 * Stores data for lightning striking
 */
public class LightningStrikeEvent extends WeatherEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final LightningStrike bolt;
    private final Cause cause;

    private boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(since = "1.13.1", forRemoval = true)
    public LightningStrikeEvent(@NotNull final World world, @NotNull final LightningStrike bolt) {
        this(world, bolt, Cause.UNKNOWN);
    }

    @ApiStatus.Internal
    public LightningStrikeEvent(@NotNull final World world, @NotNull final LightningStrike bolt, @NotNull final Cause cause) {
        super(world);
        this.bolt = bolt;
        this.cause = cause;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取从天空中打下的雷电.
     * <p>原文：
     * Gets the bolt which is striking the earth.
     *
     * @return 雷电的实体
     * <p>原文：lightning entity
     */
    @NotNull
    public LightningStrike getLightning() {
        return this.bolt;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取发生此次雷击的原因.
     * <p>原文：
     * Gets the cause of this lightning strike.
     *
     * @return 雷击发生原因
     * <p>原文：strike cause
     */
    @NotNull
    public Cause getCause() {
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

    public enum Cause {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由"/summon"命令触发.
         * <p>原文：
         * Triggered by the /summon command.
         */
        COMMAND,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由插件触发.
         * <p>原文：
         * Triggered by a Plugin.
         */
        CUSTOM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由刷怪笼触发.
         * <p>原文：
         * Triggered by a Spawner.
         */
        SPAWNER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由附魔的三叉戟触发.
         * <p>原文：
         * Triggered by an enchanted trident.
         */
        TRIDENT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由骷髅马陷阱触发.
         * <p>原文：
         * Triggered by a skeleton horse trap.
         */
        TRAP,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在雷暴天气中产生.
         * <p>原文：
         * Triggered by weather.
         */
        WEATHER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由附魔触发（非三叉戟）.
         * <p>原文：
         * Triggered by an enchantment but not a trident.
         */
        ENCHANTMENT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 未知触发原因.
         * <p>原文：
         * Unknown trigger.
         */
        UNKNOWN
    }
}
