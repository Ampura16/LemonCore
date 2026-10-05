package org.bukkit.event.player;

import io.papermc.paper.datacomponent.item.consumable.ConsumeEffect;
import io.papermc.paper.entity.TeleportFlag;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import java.util.Collections;
import java.util.Set;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家传送事件.
 * <p>原文：
 * Holds information for player teleport events
 */
public class PlayerTeleportEvent extends PlayerMoveEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Set<TeleportFlag.Relative> teleportFlags;
    private TeleportCause cause = TeleportCause.UNKNOWN;

    @ApiStatus.Internal
    public PlayerTeleportEvent(@NotNull final Player player, @NotNull final Location from, @Nullable final Location to) {
        super(player, from, to);
        this.teleportFlags = Collections.emptySet();
    }

    @ApiStatus.Internal
    public PlayerTeleportEvent(@NotNull final Player player, @NotNull final Location from, @Nullable final Location to, @NotNull final TeleportCause cause) {
        this(player, from, to);
        this.cause = cause;
    }

    @ApiStatus.Internal
    public PlayerTeleportEvent(@NotNull final Player player, @NotNull final Location from, @Nullable final Location to, @NotNull final TeleportCause cause, @NotNull Set<TeleportFlag.Relative> teleportFlags) {
        super(player, from, to);
        this.cause = cause;
        this.teleportFlags = teleportFlags;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此次传送的传送理由.
     * <p>原文：
     * Gets the cause of this teleportation event
     *
     * @return 传送理由
     * <p>原文：Cause of the event
     */
    @NotNull
    public TeleportCause getCause() {
        return this.cause;
    }

    /**
     * Returns the relative teleportation flags used in this teleportation.
     * This determines which axis the player will not lose their velocity in.
     *
     * @return an immutable set of relative teleportation flags
     */
    @NotNull
    public @Unmodifiable Set<TeleportFlag.Relative> getRelativeTeleportationFlags() {
        return this.teleportFlags;
    }

    /**
     * Gets if the player will be dismounted in this teleportation.
     *
     * @return dismounted or not
     * @deprecated dismounting on tp is no longer controlled by the server
     */
    @Deprecated(forRemoval = true)
    @Contract("-> true")
    public boolean willDismountPlayer() {
        return true;
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

    public enum TeleportCause {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家抛出末影珍珠传送
         * <p>原文：
         * Indicates the teleportation was caused by a player throwing an Ender
         * Pearl
         */
        ENDER_PEARL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由命令进行传送.
         * <p>原文：
         * Indicates the teleportation was caused by a player executing a
         * command
         */
        COMMAND,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由插件进行传送.
         * <p>原文：
         * Indicates the teleportation was caused by a plugin
         */
        PLUGIN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由地狱传送门进行传送.
         * <p>原文：
         * Indicates the teleportation was caused by a player entering a
         * Nether portal
         */
        NETHER_PORTAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由末地传送门进行传送.
         * <p>原文：
         * Indicates the teleportation was caused by a player entering an End
         * portal
         */
        END_PORTAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由旁观者菜单传送到一个实体/玩家.
         * <p>原文：
         * Indicates the teleportation was caused by a player teleporting to an
         * Entity/Player via the spectator menu
         */
        SPECTATE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由末地折跃门进行传送.
         * <p>原文：
         * Indicates the teleportation was caused by a player entering an End
         * gateway
         */
        END_GATEWAY,
        /**
         * Indicates the teleportation was caused by a player consuming an item with a {@link ConsumeEffect.TeleportRandomly} effect
         */
        CONSUMABLE_EFFECT,
        /**
         * Indicates the teleportation was caused by a player exiting a vehicle
         */
        DISMOUNT,
        /**
         * Indicates the teleportation was caused by a player exiting a bed
         */
        EXIT_BED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由除已知枚举外的其他情况进行了传送.
         * <p>原文：
         * Indicates the teleportation was caused by an event not covered by
         * this enum
         */
        UNKNOWN;

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由紫影果进行传送.
         * <p>原文：
         * Indicates the teleportation was caused by a player consuming chorus
         * fruit
         *
         * @deprecated in favor of {@link #CONSUMABLE_EFFECT}
         */
        @Deprecated(since = "1.21.5", forRemoval = true)
        public static final TeleportCause CHORUS_FRUIT = CONSUMABLE_EFFECT;
    }
}
