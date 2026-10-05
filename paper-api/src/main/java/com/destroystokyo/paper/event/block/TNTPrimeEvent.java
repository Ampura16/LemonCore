package com.destroystokyo.paper.event.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在 TNT 方块即将变为 {@link TNTPrimed} 时调用
 * <p>
 * 取消该事件将不会把 TNT 变为 {@link TNTPrimed}，并保持 TNT 方块原样
 * <p>原文：
 * Called when TNT block is about to turn into {@link TNTPrimed}
 * <p>
 * Cancelling it won't turn TNT into {@link TNTPrimed} and leaves
 * the TNT block as-is
 *
 * @author Mark Vainomaa
 * @deprecated 请使用 {@link org.bukkit.event.block.TNTPrimeEvent}
 * <p>原文：use {@link org.bukkit.event.block.TNTPrimeEvent}
 */
@Deprecated(forRemoval = true, since = "1.19.4")
public class TNTPrimeEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @NotNull private final PrimeReason reason;
    @Nullable private final Entity primerEntity;

    private boolean cancelled;

    @ApiStatus.Internal
    public TNTPrimeEvent(@NotNull Block block, @NotNull PrimeReason reason, @Nullable Entity primerEntity) {
        super(block);
        this.reason = reason;
        this.primerEntity = primerEntity;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取 TNT 点燃原因
     * <p>原文：
     * Gets the TNT prime reason
     *
     * @return 点燃原因
     * <p>原文：Prime reason
     */
    @NotNull
    public PrimeReason getReason() {
        return this.reason;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取 TNT 的点燃者 {@link Entity}。
     * <p>
     * 如果 {@link #getReason()} 为 {@link PrimeReason#REDSTONE} 或 {@link PrimeReason#FIRE}，则为 {@code null}。如果 {@link #getReason()} 为 {@link PrimeReason#ITEM} 或 {@link PrimeReason#PROJECTILE}，则不为 {@code null} 如果 {@link #getReason()} 为 {@link PrimeReason#EXPLOSION}，则可能为 {@code null}
     * <p>原文：
     * Gets the TNT primer {@link Entity}.
     * <p>
     * It's {@code null} if {@link #getReason()} is {@link PrimeReason#REDSTONE} or {@link PrimeReason#FIRE}.
     * It's not {@code null} if {@link #getReason()} is {@link PrimeReason#ITEM} or {@link PrimeReason#PROJECTILE}
     * It might be {@code null} if {@link #getReason()} is {@link PrimeReason#EXPLOSION}
     *
     * @return 点燃 TNT 的 {@link Entity}
     * <p>原文：The {@link Entity} who primed the TNT
     */
    @Nullable
    public Entity getPrimerEntity() {
        return this.primerEntity;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否应取消生成 {@link TNTPrimed}
     * <p>原文：
     * Gets whether spawning {@link TNTPrimed} should be cancelled or not
     *
     * @return 是否应取消生成 {@link TNTPrimed}
     * <p>原文：Whether spawning {@link TNTPrimed} should be cancelled or not
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否取消生成 {@link TNTPrimed}
     * <p>原文：
     * Sets whether to cancel spawning {@link TNTPrimed} or not
     *
     * @param cancel 是否应取消生成 {@link TNTPrimed}
     * <p>原文：whether spawning {@link TNTPrimed} should be cancelled or not
     */
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

    public enum PrimeReason {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 当 TNT 被点燃是由于其他爆炸（连锁反应）所致
         * <p>原文：
         * When TNT prime was caused by other explosion (chain reaction)
         */
        EXPLOSION,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 当 TNT 被点燃是由于火所致
         * <p>原文：
         * When TNT prime was caused by fire
         */
        FIRE,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 当 {@link Player} 对给定的 TNT 方块使用 {@link Material#FLINT_AND_STEEL} 或 {@link Material#FIRE_CHARGE} 时
         * <p>原文：
         * When {@link Player} used {@link Material#FLINT_AND_STEEL} or
         * {@link Material#FIRE_CHARGE} on given TNT block
         */
        ITEM,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 当 TNT 被点燃是由于 {@link Entity} 使用带 {@link Enchantment#FLAME} 魔咒的弓射击 TNT 所致
         * <p>原文：
         * When TNT prime was caused by an {@link Entity} shooting TNT
         * using a bow with {@link Enchantment#FLAME} enchantment
         */
        PROJECTILE,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 当红石信号触发 TNT 点燃时
         * <p>原文：
         * When redstone power triggered the TNT prime
         */
        REDSTONE
    }
}
