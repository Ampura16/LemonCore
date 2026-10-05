package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个{@link Material#FIREWORK_ROCKET 烟花火箭}及其效果.
 * <p>原文：
 * Represents a {@link Material#FIREWORK_ROCKET} and its effects.
 */
public interface FireworkMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向烟花火箭添加一个烟花效果.
     * <p>原文：
     * Add another effect to this firework.
     *
     * @param effect 要添加的烟花效果
     * <p>原文：The firework effect to add
     * @throws IllegalArgumentException 若 effect 为 null
     * <p>原文：If effect is null
     */
    void addEffect(@NotNull FireworkEffect effect) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向烟花火箭添加多个烟花效果.
     * <p>原文：
     * Add several effects to this firework.
     *
     * @param effects 要添加的烟花效果
     * <p>原文：The firework effects to add
     * @throws IllegalArgumentException 若 effects 为 null
     * <p>原文：If effects is null
     * @throws IllegalArgumentException 若 effects 为 null
     * <p>原文：If any effect is null (may be thrown
     *     after changes have occurred)
     */
    void addEffects(@NotNull FireworkEffect... effects) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向烟花火箭添加多个烟花效果.
     * <p>原文：
     * Add several firework effects to this firework.
     *
     * @param effects 一个可以迭代所需的烟花效果的可迭代对象
     * <p>原文：An iterable object whose iterator yields the desired
     *     firework effects
     * @throws IllegalArgumentException 若 effects 为 null
     * <p>原文：If effects is null
     * @throws IllegalArgumentException 若 effects 为 null
     * <p>原文：If any effect is null (may be thrown
     *     after changes have occurred)
     */
    void addEffects(@NotNull Iterable<FireworkEffect> effects) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此烟花火箭的所有烟花效果.
     * <p>原文：
     * Get the effects in this firework.
     *
     * @return 所有烟花效果的不可变列表
     * <p>原文：An immutable list of the firework effects
     */
    @NotNull
    List<FireworkEffect> getEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取烟花效果的数量.
     * <p>原文：
     * Get the number of effects in this firework.
     *
     * @return 烟花效果的数量
     * <p>原文：The number of effects
     */
    int getEffectsSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除烟花火箭中的一个烟花效果.
     * <p>原文：
     * Remove an effect from this firework.
     *
     * @param index 烟花效果的序号
     * <p>原文：The index of the effect to remove
     * @throws IndexOutOfBoundsException 若 index {@literal < 0 或 index >} {@link
     *     #getEffectsSize()}
     * <p>原文：If index {@literal < 0 or index >} {@link
     *     #getEffectsSize()}
     */
    void removeEffect(int index) throws IndexOutOfBoundsException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除烟花火箭附带的所有效果.
     * <p>原文：
     * Remove all effects from this firework.
     */
    void clearEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断烟花火箭是否含有烟花效果.
     * <p>原文：
     * Get whether this firework has any effects.
     *
     * @return 烟花火箭是否含有烟花效果.
     * <p>原文：true if it has effects, false if there are no effects
     */
    boolean hasEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断这个烟花火箭是否含有能量.
     * <p>原文：
     * Get whether this firework has power set by component.
     *
     * @return 是否含有能量
     * <p>原文：true if it has power set, false if there are no power set
     */
    boolean hasPower();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个烟花火箭的飞行时间的近似值.
     * <br>
     * 插件调用此方法前应检查 hasPower() 是否返回 <code>true</code>.
     * <p>
     * 译注:
     * <p>原文：
     * Gets the approximate height the firework will fly.
     * <br>
     * Plugins should check that hasPower() returns <code>true</code>
     * before calling this method.
     *
     * @return 飞行时间近似值
     * <p>原文：approximate flight height of the firework.
     * @see #hasPower()
     */
    int getPower();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个烟花火箭的飞行时间的近似值. 每一级能量大约是半秒的飞行时间.
     * <p>原文：
     * Sets the approximate power of the firework. Each level of power is half
     * a second of flight time.
     *
     * @param power 烟花火箭的能量 (持续时间), 取值范围 0 {@literal <=} power {@literal <=} 255
     * <p>原文：the power of the firework, from 0-255
     * @throws IllegalArgumentException 若 {@literal power<0 或 power>255}
     * <p>原文：if {@literal power<0 or power>255}
     */
    void setPower(int power) throws IllegalArgumentException;

    @Override
    @NotNull
    FireworkMeta clone();
}
