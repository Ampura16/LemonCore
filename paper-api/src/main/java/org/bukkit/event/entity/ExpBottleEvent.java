package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当投掷的经验瓶命中并释放经验时调用。
 * <p>原文：
 * Called when a ThrownExpBottle hits and releases experience.
 */
public class ExpBottleEvent extends ProjectileHitEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private int exp;
    private boolean showEffect = true;

    @ApiStatus.Internal
    @Deprecated(since = "1.20.2", forRemoval = true)
    public ExpBottleEvent(@NotNull final ThrownExpBottle bottle, final int exp) {
        this(bottle, null, null, null, exp);
    }

    @ApiStatus.Internal
    public ExpBottleEvent(@NotNull final ThrownExpBottle bottle, @Nullable Entity hitEntity, @Nullable Block hitBlock, @Nullable BlockFace hitFace, final int exp) {
        super(bottle, hitEntity, hitBlock, hitFace);
        this.exp = exp;
    }

    @NotNull
    @Override
    public ThrownExpBottle getEntity() {
        return (ThrownExpBottle) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此方法指示是否应显示粒子效果。
     * <p>原文：
     * This method indicates if the particle effect should be shown.
     *
     * @return 如果显示效果则返回 true，否则返回 false
     * <p>原文：{@code true} if the effect will be shown, {@code false} otherwise
     */
    public boolean getShowEffect() {
        return this.showEffect;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此方法设置是否显示粒子效果。
     * <p>
     * 这不会改变产生的经验。
     * <p>原文：
     * This method sets if the particle effect will be shown.
     * <p>
     * This does not change the experience created.
     *
     * @param showEffect true 表示显示效果，false 表示不显示效果
     * <p>原文：{@code true} indicates the effect will be shown, false
     *     indicates no effect will be shown
     */
    public void setShowEffect(final boolean showEffect) {
        this.showEffect = showEffect;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此方法检索将要产生的经验量。
     * <p>
     * 该数字表示要分成多个经验球的总量。
     * <p>原文：
     * This method retrieves the amount of experience to be created.
     * <p>
     * The number indicates a total amount to be divided into orbs.
     *
     * @return 将要产生的经验总量
     * <p>原文：the total amount of experience to be created
     */
    public int getExperience() {
        return this.exp;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此方法设置将要产生的经验量。
     * <p>
     * 该数字表示要分成多个经验球的总量。
     * <p>原文：
     * This method sets the amount of experience to be created.
     * <p>
     * The number indicates a total amount to be divided into orbs.
     *
     * @param exp 将要产生的经验总量
     * <p>原文：the total amount of experience to be created
     */
    public void setExperience(final int exp) {
        this.exp = exp;
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
