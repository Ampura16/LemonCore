package org.bukkit.attribute;

import com.google.common.base.Preconditions;
import io.papermc.paper.registry.RegistryKey;
import java.util.Locale;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Translatable;
import org.bukkit.util.OldEnum;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 在 {@link Attributable} 里面的枚举.
 * <p>原文：
 * Types of attributes which may be present on an {@link Attributable}.
 */
public interface Attribute extends OldEnum<Attribute>, Keyed, Translatable, net.kyori.adventure.translation.Translatable { // Paper - Adventure translations

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生物的最大血量
     * <p>原文：
     * Maximum health of an Entity.
     */
    Attribute MAX_HEALTH = getAttribute("max_health");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当一个生物正在跟随其他生物/人的时候的跟随范围
     * <p>原文：
     * Range at which an Entity will follow others.
     */
    Attribute FOLLOW_RANGE = getAttribute("follow_range");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当生物被攻击的时候的击退抗性
     * <p>原文：
     * Resistance of an Entity to knockback.
     */
    Attribute KNOCKBACK_RESISTANCE = getAttribute("knockback_resistance");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生物的移动速度
     * <p>原文：
     * Movement speed of an Entity.
     */
    Attribute MOVEMENT_SPEED = getAttribute("movement_speed");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体的飞行速度
     * <p>原文：
     * Flying speed of an Entity.
     */
    Attribute FLYING_SPEED = getAttribute("flying_speed");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当生物攻击其他生物/实体时所造成的伤害
     * <p>原文：
     * Attack damage of an Entity.
     */
    Attribute ATTACK_DAMAGE = getAttribute("attack_damage");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生物的攻击击退力度
     * <p>原文：
     * Attack knockback of an Entity.
     */
    Attribute ATTACK_KNOCKBACK = getAttribute("attack_knockback");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生物的攻击速率
     * <p>原文：
     * Attack speed of an Entity.
     */
    Attribute ATTACK_SPEED = getAttribute("attack_speed");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 护甲的防御值
     * <p>原文：
     * Armor bonus of an Entity.
     */
    Attribute ARMOR = getAttribute("armor");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 护甲的韧性
     * <p>原文：
     * Armor durability bonus of an Entity.
     */
    Attribute ARMOR_TOUGHNESS = getAttribute("armor_toughness");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体的坠落伤害倍增器。
     * <p>原文：
     * The fall damage multiplier of an Entity.
     */
    Attribute FALL_DAMAGE_MULTIPLIER = getAttribute("fall_damage_multiplier");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生物的可能的掉落物
     * <p>原文：
     * Luck bonus of an Entity.
     */
    Attribute LUCK = getAttribute("luck");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体的最大吸收值（即护甲吸收）。
     * <p>原文：
     * Maximum absorption of an Entity.
     */
    Attribute MAX_ABSORPTION = getAttribute("max_absorption");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体可以坠落而不受伤害的高度。
     * <p>原文：
     * The distance which an Entity can fall without damage.
     */
    Attribute SAFE_FALL_DISTANCE = getAttribute("safe_fall_distance");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体的相对大小。
     * <p>原文：
     * The relative scale of an Entity.
     */
    Attribute SCALE = getAttribute("scale");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体可以跨越的高度。
     * <p>原文：
     * The height which an Entity can walk over.
     */
    Attribute STEP_HEIGHT = getAttribute("step_height");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 施加在实体上的重力。
     * <p>原文：
     * The gravity applied to an Entity.
     */
    Attribute GRAVITY = getAttribute("gravity");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体跳跃的力量。
     * <p>原文：
     * Strength with which an Entity will jump.
     */
    Attribute JUMP_STRENGTH = getAttribute("jump_strength");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体在着火后保持燃烧的时间。
     * <p>原文：
     * How long an entity remains burning after ignition.
     */
    Attribute BURNING_TIME = getAttribute("burning_time");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 摄像机放置的距离。
     * <p>原文：
     * The camera distance of a player to their own entity.
     */
    Attribute CAMERA_DISTANCE = getAttribute("camera_distance");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 对爆炸造成的击退的抗性。
     * <p>原文：
     * Resistance to knockback from explosions.
     */
    Attribute EXPLOSION_KNOCKBACK_RESISTANCE = getAttribute("explosion_knockback_resistance");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在困难地形中的移动速度。
     * <p>原文：
     * Movement speed through difficult terrain.
     */
    Attribute MOVEMENT_EFFICIENCY = getAttribute("movement_efficiency");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 水下使用的氧气。
     * <p>原文：
     * Oxygen use underwater.
     */
    Attribute OXYGEN_BONUS = getAttribute("oxygen_bonus");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在水中移动的速度。
     * <p>原文：
     * Movement speed through water.
     */
    Attribute WATER_MOVEMENT_EFFICIENCY = getAttribute("water_movement_efficiency");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生物被物品吸引的范围。
     * <p>原文：
     * Range at which mobs will be tempted by items.
     */
    Attribute TEMPT_RANGE = getAttribute("tempt_range");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家可以触及的方块距离。
     * <p>原文：
     * The block reach distance of a Player.
     */
    Attribute BLOCK_INTERACTION_RANGE = getAttribute("block_interaction_range");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家可以触及的实体距离。
     * <p>原文：
     * The entity reach distance of a Player.
     */
    Attribute ENTITY_INTERACTION_RANGE = getAttribute("entity_interaction_range");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家破坏方块的速度。
     * <p>原文：
     * Block break speed of a Player.
     */
    Attribute BLOCK_BREAK_SPEED = getAttribute("block_break_speed");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 正确工具的挖矿速度。
     * <p>原文：
     * Mining speed for correct tools.
     */
    Attribute MINING_EFFICIENCY = getAttribute("mining_efficiency");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 潜行速度。
     * <p>原文：
     * Sneaking speed.
     */
    Attribute SNEAKING_SPEED = getAttribute("sneaking_speed");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 水下挖掘速度。
     * <p>原文：
     * Underwater mining speed.
     */
    Attribute SUBMERGED_MINING_SPEED = getAttribute("submerged_mining_speed");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 横扫伤害。
     * <p>原文：
     * Sweeping damage.
     */
    Attribute SWEEPING_DAMAGE_RATIO = getAttribute("sweeping_damage_ratio");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 僵尸增援的几率
     * <p>原文：
     * Chance of a zombie to spawn reinforcements.
     */
    Attribute SPAWN_REINFORCEMENTS = getAttribute("spawn_reinforcements");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 路径点传输范围。
     * <p>原文：
     * Attribute controlling the range an entity transmits itself as a waypoint.
     */
    Attribute WAYPOINT_TRANSMIT_RANGE = getAttribute("waypoint_transmit_range");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 路径点接收范围。
     * <p>原文：
     * Attribute controlling the range an entity receives other waypoints from.
     */
    Attribute WAYPOINT_RECEIVE_RANGE = getAttribute("waypoint_receive_range");

    @NotNull
    private static Attribute getAttribute(@NotNull @KeyPattern.Value String key) {
        return Registry.ATTRIBUTE.getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
    }

    /**
     * {@return the sentiment of this attribute}
     */
    @NotNull
    Sentiment getSentiment();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param name 属性的名称.
     * <p>原文：of the attribute.
     * @return 具有给定名称的属性.
     * <p>原文：the attribute with the given name.
     * @deprecated 仅为向后兼容，请改用 {@link Registry#get(NamespacedKey)}.
     * <p>
     * 原文: only for backwards compatibility, use {@link Registry#get(NamespacedKey)} instead.
     * <p>原文：only for backwards compatibility, use {@link Registry#get(NamespacedKey)} instead.
     */
    @NotNull
    @Deprecated(since = "1.21.3", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
    static Attribute valueOf(@NotNull String name) {
        final NamespacedKey key = NamespacedKey.fromString(name.toLowerCase(Locale.ROOT));
        Attribute attribute = key == null ? null : Bukkit.getUnsafe().get(RegistryKey.ATTRIBUTE, key);
        Preconditions.checkArgument(attribute != null, "No attribute found with the name %s", name);
        return attribute;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 所有已知属性的数组.
     * <p>原文：an array of all known attributes.
     * @deprecated 请使用 {@link Registry#iterator()}.
     * <p>原文：use {@link Registry#stream()}.
     */
    @NotNull
    @Deprecated(since = "1.21.3", forRemoval = true) @org.jetbrains.annotations.ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
    static Attribute[] values() {
        return Registry.ATTRIBUTE.stream().toArray(Attribute[]::new);
    }

    /**
     * An attribute sentiment describes the intent behind the attribute, meaning
     * whether it is supposed to be a positive, neutral, or negative attribute.
     */
    enum Sentiment {
        // Start generate - AttributeSentiment
        POSITIVE,
        NEUTRAL,
        NEGATIVE;
        // End generate - AttributeSentiment
    }
}
