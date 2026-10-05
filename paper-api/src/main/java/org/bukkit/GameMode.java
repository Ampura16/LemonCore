package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Map;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表{@link HumanEntity}能有的不同种类的游戏模式.
 * <p>原文：
 * Represents the various type of game modes that {@link HumanEntity}s may
 * have
 */
public enum GameMode implements net.kyori.adventure.translation.Translatable { // Paper - implement Translatable
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创造模式，能飞行、瞬间破坏、无敌、无限的物品.
     * <p>原文：
     * Creative mode may fly, build instantly, become invulnerable and create
     * free items.
     */
    CREATIVE(1),

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生存模式，是普通的游戏类型，没有特别的特性.
     * <p>原文：
     * Survival mode is the "normal" gameplay type, with no special features.
     */
    SURVIVAL(0),

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 冒险模式，没有适当的工具将不能破坏方块.
     * <p>原文：
     * Adventure mode cannot break blocks without the correct tools.
     */
    ADVENTURE(2),

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 旁观模式，在任何地方都不能进行交互，看不见普通玩家.旁观者会一直处于飞行状态.处于旁观模式时不会受到伤害，除了虚空或/kill伤害。
     * <p>原文：
     * Spectator mode cannot interact with the world in any way and is
     * invisible to normal players. This grants the player the
     * ability to no-clip through the world.
     */
    SPECTATOR(3);

    private final int value;
    private static final Map<Integer, GameMode> BY_ID = Maps.newHashMap();
    // Paper start - translation keys
    private final String translationKey;

    @Override
    public @org.jetbrains.annotations.NotNull String translationKey() {
        return this.translationKey;
    }
    // Paper end

    private GameMode(final int value) {
        this.value = value;
        this.translationKey = "gameMode." +  this.name().toLowerCase(java.util.Locale.ENGLISH); // Paper
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取相关模式的游戏模式值.
     * <p>原文：
     * Gets the mode value associated with this GameMode
     *
     * @return 游戏模式
     * <p>原文：An integer value of this gamemode
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    public int getValue() {
        return value;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定值表示的游戏模式.
     * <p>原文：
     * Gets the GameMode represented by the specified value
     *
     * @param value 要检测的值
     * <p>原文：Value to check
     * @return 给定值关联的{@link GameMode},如果为null则不存在
     * <p>原文：Associative {@link GameMode} with the given value, or null if
     *     it doesn't exist
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    @Nullable
    public static GameMode getByValue(final int value) {
        return BY_ID.get(value);
    }

    static {
        for (GameMode mode : values()) {
            BY_ID.put(mode.getValue(), mode);
        }
    }

    // Paper start - Add GameMode#isInvulnerable
    /**
     * Checks whether this game mode is invulnerable
     * (i.e. is either {@link #CREATIVE} or {@link #SPECTATOR})
     *
     * @return whether this game mode is invulnerable
     */
    public boolean isInvulnerable() {
        return this == CREATIVE || this == SPECTATOR;
    }
    // Paper end - Add GameMode#isInvulnerable
}
