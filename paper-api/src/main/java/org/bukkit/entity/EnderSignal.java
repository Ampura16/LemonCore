package org.bukkit.entity;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表抛出的末影之眼.
 * <p>原文：
 * Represents an EnderSignal, which is created upon throwing an ender eye.
 */
public interface EnderSignal extends Entity {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取末影之眼移向的位置.
     * <p>原文：
     * Get the location this EnderSignal is moving towards.
     *
     * @return 末影之眼移向的{@link Location 位置}
     * <p>原文：the {@link Location} this EnderSignal is moving towards.
     */
    @Nullable
    public Location getTargetLocation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置末影之眼移向的位置.
     * <br>
     * 当设置了新的目标地点时, {@link #getDropItem()}将被重置为一个随机值
     * , 其消失时间将重置为0.
     * <p>原文：
     * Set the {@link Location} this EnderSignal is moving towards.
     * <br>
     * When setting a new target location, the {@link #getDropItem()} resets to
     * a random value and the despawn timer gets set back to 0.
     *
     * @param location 新目标位置
     * <p>原文：the new target location
     */
    public void setTargetLocation(@NotNull Location location);

    // Paper start
    /**
     * Set the {@link Location} this EnderSignal is moving towards.
     *
     * @param location the new target location
     * @param update true to reset the {@link #getDropItem()}
     *               to a random value and {@link #getDespawnTimer()} to 0
     */
    public void setTargetLocation(@NotNull Location location, boolean update);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取末影之眼最终是否会掉落为物品.<br>
     * 如果为true, 将为掉落为物品, 反之它会破碎并湮灭.
     * <p>原文：
     * Gets if the EnderSignal should drop an item on death.<br>
     * If {@code true}, it will drop an item. If {@code false}, it will shatter.
     *
     * @return 末影之眼最终是否会掉落为物品
     * <p>原文：true if the EnderSignal will drop an item on death, or false if
     * it will shatter
     */
    public boolean getDropItem();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置末影之眼最终是否会掉落为物品, 亦或是湮灭于空气中.
     * <p>原文：
     * Sets if the EnderSignal should drop an item on death; or if it should
     * shatter.
     *
     * @param drop 末影之眼最终是否会掉落为物品
     * <p>原文：true if the EnderSignal should drop an item on death, or
     * false if it should shatter.
     */
    public void setDropItem(boolean drop);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取末影之眼在空中时显示的{@link ItemStack 物品堆}以及死亡时掉落的物品.
     * <p>原文：
     * Get the {@link ItemStack} to be displayed while in the air and to be
     * dropped on death.
     *
     * @return 物品堆
     * <p>原文：the item stack
     */
    @NotNull
    public ItemStack getItem();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置末影之眼在空中时显示的{@link ItemStack 物品堆}以及死亡时掉落的物品.
     * <p>原文：
     * Set the {@link ItemStack} to be displayed while in the air and to be
     * dropped on death.
     *
     * @param item 要设置的物品. 如果为null, 则重置为默认的末影之眼
     * <p>原文：the item to set. If null, resets to the default eye of ender
     */
    public void setItem(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取末影之眼已在空气漂浮的时间 (单位为tick).
     * <br>
     * 当此值大于80时, 此实体会在下一tick消失, 是否掉落为物品取决于getDropItem().
     * <p>原文：
     * Gets the amount of time this entity has been alive (in ticks).
     * <br>
     * When this number is greater than 80, it will despawn on the next tick.
     *
     * @return 此末影之眼实体已存活时间
     * <p>原文：the number of ticks this EnderSignal has been alive.
     */
    public int getDespawnTimer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置末影之眼已在空气漂浮的时间 (单位为tick).
     * <br>
     * 当此值大于80时, 此实体会在下一tick消失, 是否掉落为物品取决于getDropItem().
     * <p>原文：
     * Set how long this entity has been alive (in ticks).
     * <br>
     * When this number is greater than 80, it will despawn on the next tick.
     *
     * @param timer 此末影之眼实体已存活时间
     * <p>原文：how long (in ticks) this EnderSignal has been alive.
     */
    public void setDespawnTimer(int timer);
}
