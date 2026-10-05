package org.bukkit.event.player;

import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家抛出鸡蛋时触发本事件，鸡蛋可能孵化.
 * <p>原文：
 * Called when a player throws an egg and it might hatch
 */
public class PlayerEggThrowEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Egg egg;
    private boolean hatching;
    private EntityType hatchType;
    private byte numHatches;

    @ApiStatus.Internal
    public PlayerEggThrowEvent(@NotNull final Player player, @NotNull final Egg egg, final boolean hatching, final byte numHatches, @NotNull final EntityType hatchingType) {
        super(player);
        this.egg = egg;
        this.hatching = hatching;
        this.numHatches = numHatches;
        this.hatchType = hatchingType;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取事件中的鸡蛋.
     * <p>原文：
     * Gets the egg involved in this event.
     *
     * @return 事件中的鸡蛋
     * <p>原文：the egg involved in this event
     */
    @NotNull
    public Egg getEgg() {
        return this.egg;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测鸡蛋是否将被孵化。服务器可能设置无互动.
     * <p>原文：
     * Gets whether the egg is hatching or not. Will be what the server
     * would've done without interaction.
     *
     * @return 鸡蛋是否将被孵化
     * <p>原文：boolean Whether the egg is going to hatch or not
     */
    public boolean isHatching() {
        return this.hatching;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置鸡蛋是否将被孵化.
     * <p>原文：
     * Sets whether the egg will hatch or not.
     *
     * @param hatching 你是否希望鸡蛋孵化
     * <p>原文：{@code true} if you want the egg to hatch, {@code false} if you want it
     *     not to
     */
    public void setHatching(boolean hatching) {
        this.hatching = hatching;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被孵化的生物类型 (默认为EntityType.CHICKEN ).
     * <p>原文：
     * Get the type of the mob being hatched (EntityType.CHICKEN by default)
     *
     * @return 将被孵化的生物类型
     * <p>原文：The type of the mob being hatched by the egg
     */
    @NotNull
    public EntityType getHatchingType() {
        return this.hatchType;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 修改将被孵化生物的类型.
     * <p>原文：
     * Change the type of mob being hatched by the egg
     *
     * @param hatchType 将被孵化生物的类型
     * <p>原文：The type of the mob being hatched by the egg
     */
    public void setHatchingType(@NotNull EntityType hatchType) {
        if (!hatchType.isSpawnable()) throw new IllegalArgumentException("Can't spawn that entity type from an egg!");
        this.hatchType = hatchType;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测将被孵化生物的数量。默认由服务器进行设置.
     * <ul>
     * <li>7/8 几率不生成
     * <li>31/256 ~= 1/8 的几率生成 1 只
     * <li>1/256 的几率生成 4 只
     * </ul>
     * <p>原文：
     * Get the number of mob hatches from the egg. By default the number will
     * be the number the server would've done
     * <ul>
     * <li>7/8 chance of being 0
     * <li>31/256 ~= 1/8 chance to be 1
     * <li>1/256 chance to be 4
     * </ul>
     *
     * @return 将被孵化生物的数量
     * <p>原文：The number of mobs going to be hatched by the egg
     */
    public byte getNumHatches() {
        return this.numHatches;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 改变将被孵化生物的数量.
     * <p>
     * {@link hatching}的值将覆盖该数值的作用。
     * 如果{@link hatching}被设为false, 则本数值不会生效。
     * <p>原文：
     * Change the number of mobs coming out of the hatched egg
     * <p>
     * The boolean hatching will override this number. I.e. If hatching =
     * false, this number will not matter
     *
     * @param numHatches 将被孵化生物的数量
     * <p>原文：The number of mobs coming out of the egg
     */
    public void setNumHatches(byte numHatches) {
        this.numHatches = numHatches;
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
