package com.destroystokyo.paper.event.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当扔出的鸡蛋可能孵化时调用。
 * <p>
 * 该事件对所有可能孵化的扔出鸡蛋触发，包括玩家、发射器等扔出的鸡蛋。
 * <p>原文：
 * Called when a thrown egg might hatch.
 * <p>
 * This event fires for all thrown eggs that may hatch, players, dispensers, etc.
 */
@NullMarked
public class ThrownEggHatchEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Egg egg;
    private boolean hatching;
    private byte numHatches;
    private EntityType hatchType;

    @ApiStatus.Internal
    public ThrownEggHatchEvent(final Egg egg, final boolean hatching, final byte numHatches, final EntityType hatchingType) {
        this.egg = egg;
        this.hatching = hatching;
        this.numHatches = numHatches;
        this.hatchType = hatchingType;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件涉及的蛋。
     * <p>原文：
     * Gets the egg involved in this event.
     *
     * @return 此事件涉及的蛋
     * <p>原文：the egg involved in this event
     */
    public Egg getEgg() {
        return this.egg;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取蛋是否正在孵化。其值为服务器在无干预时原本会产生的结果。
     * <p>原文：
     * Gets whether the egg is hatching or not. Will be what the server
     * would've done without interaction.
     *
     * @return 布尔值，表示蛋是否将要孵化
     * <p>原文：boolean Whether the egg is going to hatch or not
     */
    public boolean isHatching() {
        return this.hatching;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置蛋是否会孵化。
     * <p>原文：
     * Sets whether the egg will hatch or not.
     *
     * @param hatching 如果希望蛋孵化则为 {@code true}，如果不希望则为 {@code false}
     * <p>原文：{@code true} if you want the egg to hatch, {@code false} if you want it
     *                 not to
     */
    public void setHatching(final boolean hatching) {
        this.hatching = hatching;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取蛋孵化出的生物类型（默认为 {@link EntityType#CHICKEN}）
     * <p>原文：
     * Get the type of the mob being hatched ({@link EntityType#CHICKEN} by default)
     *
     * @return 蛋孵化出的生物类型
     * <p>原文：The type of the mob being hatched by the egg
     */
    public EntityType getHatchingType() {
        return this.hatchType;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 更改蛋孵化出的生物类型
     * <p>原文：
     * Change the type of mob being hatched by the egg
     *
     * @param hatchType 从蛋中孵化的生物类型
     * <p>原文：The type of the mob being hatched by the egg
     */
    public void setHatchingType(final EntityType hatchType) {
        Preconditions.checkArgument(hatchType.isSpawnable(), "Can't spawn that entity type from an egg!");
        this.hatchType = hatchType;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取蛋孵化出的生物数量。默认情况下为服务器原本会产生的数量
     * <ul>
     * <li>7/8 的几率为 0
     * <li>31/256 ≈ 1/8 的几率为 1
     * <li>1/256 的几率为 4
     * </ul>
     * <p>原文：
     * Get the number of mob hatches from the egg. By default, the number will
     * be the number the server would've done
     * <ul>
     *  <li>7/8 chance of being 0
     *  <li>31/256 ~= 1/8 chance to be 1
     *  <li>1/256 chance to be 4
     * </ul>
     *
     * @return 蛋将孵化出的生物数量
     * <p>原文：The number of mobs going to be hatched by the egg
     */
    public byte getNumHatches() {
        return this.numHatches;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 更改孵化的蛋中出来的生物数量
     * <p>
     * 布尔值 hatching 会覆盖该数字。即如果 hatching 为 {@code false}，该数字无关紧要
     * <p>原文：
     * Change the number of mobs coming out of the hatched egg
     * <p>
     * The boolean hatching will override this number. I.e. If hatching is
     * {@code false}, this number will not matter
     *
     * @param numHatches 从蛋中出来的生物数量
     * <p>原文：The number of mobs coming out of the egg
     */
    public void setNumHatches(final byte numHatches) {
        this.numHatches = numHatches;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
