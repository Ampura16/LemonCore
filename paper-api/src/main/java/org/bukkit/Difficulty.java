package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同的游戏难度.
 * <p>原文：
 * Represents the various difficulty levels that are available.
 */
public enum Difficulty implements net.kyori.adventure.translation.Translatable { // Paper - Adventure translations
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家随时间回血，敌对生物不会生成，饥饿度不减少。
     * <p>原文：
     * Players regain health over time, hostile mobs don't spawn, the hunger
     * bar does not deplete.
     */
    PEACEFUL(0),

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 敌对生物会生成，敌人会造成比在NORMAL难度更少的伤害。饥饿度会减少，
     * 最高减少5颗心（默认值）.
     * <p>原文：
     * Hostile mobs spawn, enemies deal less damage than on normal difficulty,
     * the hunger bar does deplete and starving deals up to 5 hearts of
     * damage. (Default value)
     */
    EASY(1),

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 敌对生物会生成，敌人会造成正常的伤害.
     * 饥饿度会减少，最减少9.5颗心（默认值）.
     * <p>原文：
     * Hostile mobs spawn, enemies deal normal amounts of damage, the hunger
     * bar does deplete and starving deals up to 9.5 hearts of damage.
     */
    NORMAL(2),

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 敌对生物会生成，敌人会造成更高的伤害.饥饿度会减少，最高可能会致死.
     * <p>原文：
     * Hostile mobs spawn, enemies deal greater damage than on normal
     * difficulty, the hunger bar does deplete and starving can kill players.
     */
    HARD(3);

    private final int value;
    private static final Map<Integer, Difficulty> BY_ID = Maps.newHashMap();

    private Difficulty(final int value) {
        this.value = value;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得难度数值.
     * <p>原文：
     * Gets the difficulty value associated with this Difficulty.
     *
     * @return 返回一个整数的难度数值.
     * <p>原文：An integer value of this difficulty
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    public int getValue() {
        return value;
    }

    // Paper start
    @Override
    public @org.jetbrains.annotations.NotNull String translationKey() {
        return "options.difficulty." + this.name().toLowerCase(java.util.Locale.ENGLISH);
    }
    // Paper end
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过难度数值获得难度.
     * <p>原文：
     * Gets the Difficulty represented by the specified value
     *
     * @param value 难度数值
     * <p>原文：Value to check
     * @return 返回一个难度 {@link Difficulty} 若不存在，返回 null
     * <p>原文：Associative {@link Difficulty} with the given value, or null if
     *     it doesn't exist
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    @Nullable
    public static Difficulty getByValue(final int value) {
        return BY_ID.get(value);
    }

    static {
        for (Difficulty diff : values()) {
            BY_ID.put(diff.value, diff);
        }
    }
}
