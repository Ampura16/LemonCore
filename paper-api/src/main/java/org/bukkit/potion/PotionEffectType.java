package org.bukkit.potion;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Locale;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.Color;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Translatable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表应用于实体上的药水和效果类型.
 * <p>原文：
 * Represents a type of potion and its effect on an entity.
 */
public abstract class PotionEffectType implements Keyed, Translatable, net.kyori.adventure.translation.Translatable, io.papermc.paper.world.flag.FeatureDependant { // Paper - implement Translatable & feature flag API
    private static final BiMap<Integer, PotionEffectType> ID_MAP = HashBiMap.create();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 速度.
     * <p>原文：
     * Increases movement speed.
     */
    public static final PotionEffectType SPEED = getPotionEffectType(1, "speed");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 缓慢.
     * <p>原文：
     * Decreases movement speed.
     */
    public static final PotionEffectType SLOWNESS = getPotionEffectType(2, "slowness");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 急迫.
     * <p>原文：
     * Increases dig speed.
     */
    public static final PotionEffectType HASTE = getPotionEffectType(3, "haste");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 挖掘疲劳.
     * <p>原文：
     * Decreases dig speed.
     */
    public static final PotionEffectType MINING_FATIGUE = getPotionEffectType(4, "mining_fatigue");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 力量.
     * <p>原文：
     * Increases damage dealt.
     */
    public static final PotionEffectType STRENGTH = getPotionEffectType(5, "strength");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 瞬间治疗.
     * <p>原文：
     * Heals an entity.
     */
    public static final PotionEffectType INSTANT_HEALTH = getPotionEffectType(6, "instant_health");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 瞬间伤害.
     * <p>原文：
     * Hurts an entity.
     */
    public static final PotionEffectType INSTANT_DAMAGE = getPotionEffectType(7, "instant_damage");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 跳跃提升.
     * <p>原文：
     * Increases jump height.
     */
    public static final PotionEffectType JUMP_BOOST = getPotionEffectType(8, "jump_boost");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 反胃.
     * <p>原文：
     * Warps vision on the client.
     */
    public static final PotionEffectType NAUSEA = getPotionEffectType(9, "nausea");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生命恢复.
     * <p>原文：
     * Regenerates health.
     */
    public static final PotionEffectType REGENERATION = getPotionEffectType(10, "regeneration");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 抗性提升.
     * <p>原文：
     * Decreases damage dealt to an entity.
     */
    public static final PotionEffectType RESISTANCE = getPotionEffectType(11, "resistance");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 防火.
     * <p>原文：
     * Stops fire damage.
     */
    public static final PotionEffectType FIRE_RESISTANCE = getPotionEffectType(12, "fire_resistance");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 水下呼吸.
     * <p>原文：
     * Allows breathing underwater.
     */
    public static final PotionEffectType WATER_BREATHING = getPotionEffectType(13, "water_breathing");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 隐身.
     * <p>原文：
     * Grants invisibility.
     */
    public static final PotionEffectType INVISIBILITY = getPotionEffectType(14, "invisibility");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 失明.
     * <p>原文：
     * Blinds an entity.
     */
    public static final PotionEffectType BLINDNESS = getPotionEffectType(15, "blindness");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 夜视.
     * <p>原文：
     * Allows an entity to see in the dark.
     */
    public static final PotionEffectType NIGHT_VISION = getPotionEffectType(16, "night_vision");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 饥饿.
     * <p>原文：
     * Increases hunger.
     */
    public static final PotionEffectType HUNGER = getPotionEffectType(17, "hunger");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 虚弱.
     * <p>原文：
     * Decreases damage dealt by an entity.
     */
    public static final PotionEffectType WEAKNESS = getPotionEffectType(18, "weakness");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 中毒.
     * <p>原文：
     * Deals damage to an entity over time.
     */
    public static final PotionEffectType POISON = getPotionEffectType(19, "poison");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 凋零.
     * <p>原文：
     * Deals damage to an entity over time and gives the health to the
     * shooter.
     */
    public static final PotionEffectType WITHER = getPotionEffectType(20, "wither");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生命提升.
     * <p>原文：
     * Increases the maximum health of an entity.
     */
    public static final PotionEffectType HEALTH_BOOST = getPotionEffectType(21, "health_boost");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 伤害吸收.
     * <p>原文：
     * Increases the maximum health of an entity with health that cannot be
     * regenerated, but is refilled every 30 seconds.
     */
    public static final PotionEffectType ABSORPTION = getPotionEffectType(22, "absorption");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 饱和.
     * <p>原文：
     * Increases the food level of an entity each tick.
     */
    public static final PotionEffectType SATURATION = getPotionEffectType(23, "saturation");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发光.
     * <p>原文：
     * Outlines the entity so that it can be seen from afar.
     */
    public static final PotionEffectType GLOWING = getPotionEffectType(24, "glowing");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 漂浮.
     * <p>原文：
     * Causes the entity to float into the air.
     */
    public static final PotionEffectType LEVITATION = getPotionEffectType(25, "levitation");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 幸运.
     * <p>原文：
     * Loot table luck.
     */
    public static final PotionEffectType LUCK = getPotionEffectType(26, "luck");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 霉运.
     * <p>原文：
     * Loot table unluck.
     */
    public static final PotionEffectType UNLUCK = getPotionEffectType(27, "unluck");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 缓降.
     * <p>原文：
     * Slows entity fall rate.
     */
    public static final PotionEffectType SLOW_FALLING = getPotionEffectType(28, "slow_falling");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 潮涌能量.
     * <p>原文：
     * Effects granted by a nearby conduit. Includes enhanced underwater abilities.
     */
    public static final PotionEffectType CONDUIT_POWER = getPotionEffectType(29, "conduit_power");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 海豚的恩惠.
     * <p>原文：
     * Increases underwater movement speed.<br>
     * Squee'ek uh'k kk'kkkk squeek eee'eek.
     */
    public static final PotionEffectType DOLPHINS_GRACE = getPotionEffectType(30, "dolphins_grace");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 不祥之兆.
     * <p>原文：
     * Triggers an ominous event when the player enters a village or trial chambers.<br>
     * oof.
     */
    public static final PotionEffectType BAD_OMEN = getPotionEffectType(31, "bad_omen");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 村庄英雄.
     * <p>原文：
     * Reduces the cost of villager trades.<br>
     * \o/.
     */
    public static final PotionEffectType HERO_OF_THE_VILLAGE = getPotionEffectType(32, "hero_of_the_village");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 黑暗
     * <p>原文：
     * Causes the player's vision to dim occasionally.
     */
    public static final PotionEffectType DARKNESS = getPotionEffectType(33, "darkness");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 试炼之兆
     * <p>原文：
     * Causes trial spawners to become ominous.
     */
    public static final PotionEffectType TRIAL_OMEN = getPotionEffectType(34, "trial_omen");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 袭击之兆
     * <p>原文：
     * Triggers a raid when a player enters a village.
     */
    public static final PotionEffectType RAID_OMEN = getPotionEffectType(35, "raid_omen");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 蓄风
     * <p>原文：
     * Emits a wind burst upon death.
     */
    public static final PotionEffectType WIND_CHARGED = getPotionEffectType(36, "wind_charged");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 盘丝
     * <p>原文：
     * Creates cobwebs upon death.
     */
    public static final PotionEffectType WEAVING = getPotionEffectType(37, "weaving");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 渗浆
     * <p>原文：
     * Causes slimes to spawn upon death.
     */
    public static final PotionEffectType OOZING = getPotionEffectType(38, "oozing");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 寄生
     * <p>原文：
     * Chance of spawning silverfish when hurt.
     */
    public static final PotionEffectType INFESTED = getPotionEffectType(39, "infested");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 鹦鹉螺之息
     * <p>原文：
     * Prevent the oxygen bar from depleting underwater.
     */
    public static final PotionEffectType BREATH_OF_THE_NAUTILUS = getPotionEffectType(40, "breath_of_the_nautilus");

    @NotNull
    private static PotionEffectType getPotionEffectType(int typeId, @NotNull @KeyPattern.Value String key) {
        PotionEffectType potionEffectType = Registry.MOB_EFFECT.getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));

        if (typeId > 0) {
            ID_MAP.put(typeId, potionEffectType);
        }
        return potionEffectType;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以本效果类型创建一个药水效果, 并应用持续时间与倍率.
     * <p>原文：
     * Creates a PotionEffect from this PotionEffectType, applying duration
     * modifiers and checks.
     *
     * @param duration 效果持续时间, 以 tick 为单位
     * <p>原文：time in ticks
     * @param amplifier 效果的倍率
     * <p>原文：the effect's amplifier
     * @return 创建的药水效果
     * <p>原文：a resulting potion effect
     */
    @NotNull
    public abstract PotionEffect createEffect(int duration, int amplifier);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回本效果是否为瞬时型效果.
     * <p>原文：
     * Returns whether the effect of this type happens once, immediately.
     *
     * @return 是否为瞬时型效果
     * <p>原文：whether this type is normally instant
     */
    public abstract boolean isInstant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回本效果所属的{@link PotionEffectTypeCategory 分类}.
     * <p>原文：
     * Returns the {@link PotionEffectTypeCategory category} of this effect type.
     *
     * @return 分类
     * <p>原文：the category
     */
    @NotNull
    public abstract PotionEffectTypeCategory getCategory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回本效果的颜色.
     * <p>原文：
     * Returns the color of this effect type.
     *
     * @return 颜色
     * <p>原文：the color
     */
    @NotNull
    public abstract Color getColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回应用于此效果类型的持续时间.
     * <p>原文：
     * Returns the duration modifier applied to effects of this type.
     *
     * @return 持续时间
     * <p>原文：duration modifier
     * @deprecated 未被使用, 总是 1.0
     * <p>原文：unused, always 1.0
     */
    @Deprecated(since = "1.14", forRemoval = true)
    public abstract double getDurationModifier();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回本状态效果类型的唯一 ID.
     * <p>原文：
     * Returns the unique ID of this type.
     *
     * @return 唯一 ID
     * <p>原文：Unique ID
     * @deprecated 魔法值
     * <p>原文：use {@link #key()}
     */
    @Deprecated(since = "1.6.2", forRemoval = true) // Paper
    public abstract int getId();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回本状态效果类型的名称.
     * <p>原文：
     * Returns the name of this effect type.
     *
     * @return 状态效果名
     * <p>原文：The name of this effect type
     * @deprecated 仅为保障向后兼容性, 请使用 {@link #getKey()}
     * <p>原文：only for backwards compatibility, use {@link #getKey()} instead.
     */
    @NotNull
    @Deprecated(since = "1.20.3")
    public abstract String getName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据指定 key 获取效果.
     * <p>原文：
     * Gets the PotionEffectType at the specified key
     *
     * @param key 效果键名
     * <p>原文：key to fetch
     * @return 对应的效果类型, 如果找不到返回 null
     * <p>原文：Resulting PotionEffectType, or null if not found
     * @deprecated 仅为保障向后兼容性, 请使用 {@link Registry#get(NamespacedKey)}
     * <p>原文：only for backwards compatibility, use {@link Registry#get(NamespacedKey)} instead.
     */
    @Contract("null -> null")
    @Nullable
    @Deprecated(since = "1.20.3")
    public static PotionEffectType getByKey(@Nullable NamespacedKey key) {
        if (key == null) {
            return null;
        }

        return Registry.MOB_EFFECT.get(key);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据唯一 ID 获取效果.
     * <p>原文：
     * Gets the effect type specified by the unique id.
     *
     * @param id 唯一 ID
     * <p>原文：Unique ID to fetch
     * @return 对应的效果类型, 如果找不到返回 null
     * <p>原文：Resulting type, or null if not found.
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    @Nullable
    public static PotionEffectType getById(int id) {
        PotionEffectType type = ID_MAP.get(id);

        if (type != null) {
            return type;
        }

        for (PotionEffectType other : Registry.MOB_EFFECT) {
            if (other.getId() == id) {
                ID_MAP.put(id, other);
                return other;
            }
        }

        return null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据指定名称获取效果.
     * <p>原文：
     * Gets the effect type specified by the given name.
     *
     * @param name 状态效果名
     * <p>原文：Name of PotionEffectType to fetch
     * @return 对应的效果类型, 如果找不到返回 null
     * <p>原文：Resulting PotionEffectType, or null if not found.
     * @deprecated 仅为保障向后兼容性, 请使用 {@link Registry#get(NamespacedKey)}
     * <p>原文：only for backwards compatibility, use {@link Registry#get(NamespacedKey)} instead.
     */
    @Nullable
    @Deprecated(since = "1.20.3")
    public static PotionEffectType getByName(@NotNull String name) {
        Preconditions.checkArgument(name != null, "name cannot be null");
        final NamespacedKey key = NamespacedKey.fromString(name.toLowerCase(Locale.ROOT));
        if (key == null) {
            return null;
        }
        return Registry.MOB_EFFECT.get(key);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 所有已知效果类型的数组
     * <p>原文：an array of all known PotionEffectTypes.
     * @deprecated 请使用 {@link Registry#iterator()}.
     * <p>原文：use {@link Registry#stream()}.
     */
    @NotNull
    @Deprecated(since = "1.20.3")
    public static PotionEffectType[] values() {
        return Registry.MOB_EFFECT.stream().toArray(PotionEffectType[]::new);
    }

    // Paper start
    /**
     * Gets the effect attributes in an immutable map.
     *
     * @return the attribute map
     */
    public abstract @NotNull java.util.Map<org.bukkit.attribute.Attribute, org.bukkit.attribute.AttributeModifier> getEffectAttributes();

    /**
     * Gets the true modifier amount based on the effect amplifier.
     *
     * @param attribute the attribute
     * @param effectAmplifier the effect amplifier (0 indexed)
     * @return the modifier amount
     * @throws IllegalArgumentException if the supplied attribute is not present in the map from {@link #getEffectAttributes()}
     */
    public abstract double getAttributeModifierAmount(@NotNull org.bukkit.attribute.Attribute attribute, int effectAmplifier);

    /**
     * Gets the category of this effect
     *
     * @return the category
     */
    public abstract @NotNull PotionEffectType.Category getEffectCategory();

    /**
     * Category of {@link PotionEffectType}s
     */
    public enum Category {

        BENEFICIAL(net.kyori.adventure.text.format.NamedTextColor.BLUE),
        HARMFUL(net.kyori.adventure.text.format.NamedTextColor.RED),
        NEUTRAL(net.kyori.adventure.text.format.NamedTextColor.BLUE);

        private final net.kyori.adventure.text.format.TextColor color;

        Category(net.kyori.adventure.text.format.TextColor color) {
            this.color = color;
        }

        /**
         * Gets the text color used when displaying potions
         * of this category.
         *
         * @return the text color
         */
        @NotNull
        public net.kyori.adventure.text.format.TextColor getColor() {
            return color;
        }
    }
    // Paper end
}
