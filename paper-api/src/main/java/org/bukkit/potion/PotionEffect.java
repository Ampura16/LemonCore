package org.bukkit.potion;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import io.papermc.paper.registry.RegistryKey;
import java.util.Map;
import java.util.NoSuchElementException;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个药水效果, 可应用于 {@link LivingEntity}.
 * 药水效果有持续时长、倍率、作用于实体上的{@link PotionEffectType 效果类型}属性.
 * <p>原文：
 * Represents a potion effect, that can be added to a {@link LivingEntity}. A
 * potion effect has a duration that it will last for, an amplifier that will
 * enhance its effects, and a {@link PotionEffectType}, that represents its
 * effect on an entity.
 */
@SerializableAs("PotionEffect")
public class PotionEffect implements ConfigurationSerializable {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示无限药水持续时间的常量.
     * <p>原文：
     * A constant denoting infinite potion duration.
     */
    public static final int INFINITE_DURATION = -1;

    private static final String HIDDEN_EFFECT = "hidden_effect"; // Paper
    private static final String AMPLIFIER = "amplifier";
    private static final String DURATION = "duration";
    private static final String TYPE = "effect";
    private static final String AMBIENT = "ambient";
    private static final String PARTICLES = "has-particles";
    private static final String ICON = "has-icon";
    private final int amplifier;
    private final int duration;
    private final PotionEffectType type;
    private final boolean ambient;
    private final boolean particles;
    private final boolean icon;
    private final PotionEffect hiddenEffect; // Paper

    /**
     * Creates a potion effect.
     * @param type effect type
     * @param duration measured in ticks, see {@link
     *     PotionEffect#getDuration()}
     * @param amplifier the amplifier, see {@link PotionEffect#getAmplifier()}
     * @param ambient the ambient status, see {@link PotionEffect#isAmbient()}
     * @param particles the particle status, see {@link PotionEffect#hasParticles()}
     * @param icon the icon status, see {@link PotionEffect#hasIcon()}
     * @param hiddenEffect the hidden PotionEffect
     * @hidden Internal-- hidden effects are only shown internally
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean icon, @Nullable PotionEffect hiddenEffect) { // Paper
        Preconditions.checkArgument(type != null, "effect type cannot be null");
        this.type = type;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.particles = particles;
        this.icon = icon;
        // Paper start
        this.hiddenEffect = hiddenEffect;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个药水效果.
     * <p>原文：
     * Creates a potion effect.
     *
     * @param type 效果类型
     * <p>原文：effect type
     * @param duration 持续时长 (单位为tick), 见 {@link
     *     PotionEffect#getDuration()}
     * <p>原文：measured in ticks, see {@link
     *     PotionEffect#getDuration()}
     * @param amplifier 效果倍率, 见 {@link PotionEffect#getAmplifier()}
     * <p>原文：the amplifier, see {@link PotionEffect#getAmplifier()}
     * @param ambient ambient 状态, 见 {@link PotionEffect#isAmbient()}
     * <p>原文：the ambient status, see {@link PotionEffect#isAmbient()}
     * @param particles 粒子可见性, 见 {@link PotionEffect#hasParticles()}
     * <p>原文：the particle status, see {@link PotionEffect#hasParticles()}
     * @param icon 图标可见性, 见 {@link PotionEffect#hasIcon()}
     * <p>原文：the icon status, see {@link PotionEffect#hasIcon()}
     */
    public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles, boolean icon) {
        this(type, duration, amplifier, ambient, particles, icon, null);
        // Paper end
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个药水效果, 不定义粒子颜色.
     * <p>原文：
     * Creates a potion effect with no defined color.
     *
     * @param type 效果类型
     * <p>原文：effect type
     * @param duration 持续时长 (单位为tick), 见 {@link
     *     PotionEffect#getDuration()}
     * <p>原文：measured in ticks, see {@link
     *     PotionEffect#getDuration()}
     * @param amplifier 效果倍率, 见 {@link PotionEffect#getAmplifier()}
     * <p>原文：the amplifier, see {@link PotionEffect#getAmplifier()}
     * @param ambient ambient 状态, 见 {@link PotionEffect#isAmbient()}
     * <p>原文：the ambient status, see {@link PotionEffect#isAmbient()}
     * @param particles 粒子可见性, 见 {@link PotionEffect#hasParticles()}
     * <p>原文：the particle status, see {@link PotionEffect#hasParticles()}
     */
    public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles) {
        this(type, duration, amplifier, ambient, particles, particles);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个药水效果, 使粒子效果可见.
     * <p>原文：
     * Creates a potion effect. Assumes that particles are visible
     *
     * @param type 效果类型
     * <p>原文：effect type
     * @param duration 持续时长 (单位为tick), 见 {@link
     *     PotionEffect#getDuration()}
     * <p>原文：measured in ticks, see {@link
     *     PotionEffect#getDuration()}
     * @param amplifier 效果倍率, 见 {@link PotionEffect#getAmplifier()}
     * <p>原文：the amplifier, see {@link PotionEffect#getAmplifier()}
     * @param ambient ambient 状态, 见 {@link PotionEffect#isAmbient()}
     * <p>原文：the ambient status, see {@link PotionEffect#isAmbient()}
     */
    public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier, boolean ambient) {
        this(type, duration, amplifier, ambient, true);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个药水效果, 使 ambient 为 true.
     * <p>原文：
     * Creates a potion effect. Assumes ambient is true.
     *
     * @param type 效果类型
     * <p>原文：Effect type
     * @param duration 持续时长 (单位为tick)
     * <p>原文：measured in ticks
     * @param amplifier 效果倍率
     * <p>原文：the amplifier for the effect
     * @see PotionEffect#PotionEffect(PotionEffectType, int, int, boolean)
     */
    public PotionEffect(@NotNull PotionEffectType type, int duration, int amplifier) {
        this(type, duration, amplifier, true);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于反序列化的构造器.
     * <p>原文：
     * Constructor for deserialization.
     *
     * @param map 反序列化使用的map
     * <p>原文：the map to deserialize from
     */
    public PotionEffect(@NotNull Map<String, Object> map) {
        this(getEffectType(map), getInt(map, DURATION), getInt(map, AMPLIFIER), getBool(map, AMBIENT, false), getBool(map, PARTICLES, true), getBool(map, ICON, getBool(map, PARTICLES, true)), (PotionEffect) map.get(HIDDEN_EFFECT)); // Paper
    }

    // Paper start
    @NotNull
    public PotionEffect withType(@NotNull PotionEffectType type) {
        return new PotionEffect(type, duration, amplifier, ambient, particles, icon);
    }
    @NotNull
    public PotionEffect withDuration(int duration) {
        return new PotionEffect(this.type, duration, amplifier, ambient, particles, icon);
    }
    @NotNull
    public PotionEffect withAmplifier(int amplifier) {
        return new PotionEffect(this.type, duration, amplifier, ambient, particles, icon);
    }
    @NotNull
    public PotionEffect withAmbient(boolean ambient) {
        return new PotionEffect(this.type, duration, amplifier, ambient, particles, icon);
    }
    @NotNull
    public PotionEffect withParticles(boolean particles) {
        return new PotionEffect(this.type, duration, amplifier, ambient, particles, icon);
    }
    @NotNull
    public PotionEffect withIcon(boolean icon) {
        return new PotionEffect(this.type, duration, amplifier, ambient, particles, icon);
    }

    /**
     * Returns the PotionEffect that will become active
     * after the current PotionEffect has run out.
     * <p>
     * Note: This value is only applicable to type applied to living entities.
     *
     * @return The hidden PotionEffect.
     */
    @Nullable
    public PotionEffect getHiddenPotionEffect() {
        return hiddenEffect;
    }
    // Paper end

    @NotNull
    private static PotionEffectType getEffectType(@NotNull Map<?, ?> map) {
        PotionEffectType effect;
        if (map.get(TYPE) instanceof String value) {
            effect = Bukkit.getUnsafe().get(RegistryKey.MOB_EFFECT, NamespacedKey.fromString(value));
        } else {
            int type = getInt(map, TYPE);
            effect = PotionEffectType.getById(type);
        }
        if (effect != null) {
            return effect;
        }
        throw new NoSuchElementException(map + " does not contain " + TYPE);
    }

    private static int getInt(@NotNull Map<?, ?> map, @NotNull Object key) {
        Object num = map.get(key);
        if (num instanceof Integer) {
            return (Integer) num;
        }
        throw new NoSuchElementException(map + " does not contain " + key);
    }

    private static boolean getBool(@NotNull Map<?, ?> map, @NotNull Object key, boolean def) {
        Object bool = map.get(key);
        if (bool instanceof Boolean) {
            return (Boolean) bool;
        }
        return def;
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.<String, Object>builder() // Paper
            .put(TYPE, type.getKey().toString())
            .put(DURATION, duration)
            .put(AMPLIFIER, amplifier)
            .put(AMBIENT, ambient)
            .put(PARTICLES, particles)
            .put(ICON, icon);
        // Paper start
        if (this.hiddenEffect != null) {
            builder.put(HIDDEN_EFFECT, this.hiddenEffect);
        }
        return builder.build();
        // Paper end
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试添加此效果到指定的{@link LivingEntity 生物}上.
     * <p>原文：
     * Attempts to add the effect represented by this object to the given
     * {@link LivingEntity}.
     * <p>
     * Note: {@link PotionEffect#getHiddenPotionEffect()} is ignored when
     * adding the effect to the entity.
     *
     * @param entity 效果施加实体对象
     * <p>原文：The entity to add this effect to
     * @return 效果是否可添加/添加成功
     * <p>原文：Whether the effect could be added
     * @see LivingEntity#addPotionEffect(PotionEffect)
     */
    public boolean apply(@NotNull LivingEntity entity) {
        return entity.addPotionEffect(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PotionEffect)) {
            return false;
        }
        PotionEffect that = (PotionEffect) obj;
        return this.type.equals(that.type) && this.ambient == that.ambient && this.amplifier == that.amplifier && this.duration == that.duration && this.particles == that.particles && this.icon == that.icon && java.util.Objects.equals(this.hiddenEffect, that.hiddenEffect); // Paper
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此效果的倍率. 更高的倍率意味着此药水效果在其作用时间内作用更频繁,
     * 或者对目标有更多(更强)的效果.
     * <p>原文：
     * Returns the amplifier of this effect. A higher amplifier means the
     * potion effect happens more often over its duration and in some cases
     * has more effect on its target.
     *
     * @return 效果倍率
     * <p>原文：The effect amplifier
     */
    public int getAmplifier() {
        return amplifier;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此效果的持续时间 (单位为tick).
     * <p>原文：
     * Returns the duration (in ticks) that this effect will run for when
     * applied to a {@link LivingEntity}.
     *
     * @return 效果持续时间, 如果效果持续时间无限, 则为{@value #INFINITE_DURATION}
     * <p>原文：The duration of the effect, or {@value #INFINITE_DURATION} if
     * this effect is infinite
     * @see #isInfinite()
     */
    public int getDuration() {
        return duration;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此药水效果是否具有无限持续时间. 具有无限持续时间的药水效果将显示无限符号,
     * 除非手动移除, 否则永远不会过期.
     * <p>原文：
     * Returns whether or not this potion effect has an infinite duration. Potion
     * effects with infinite durations will display an infinite symbol and never
     * expire unless manually removed.
     *
     * @return 此持续时间是否为无限
     * <p>原文：whether this duration is infinite or not
     */
    public boolean isInfinite() {
        return duration == INFINITE_DURATION;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此药水效果的持续时间是否比提供的药水效果短.
     * <p>
     * 无限持续时间被认为比非无限持续时间长. 如果两个药水效果都具有无限持续时间,
     * 则两者都不比对方短, 此方法将返回 false.
     * <p>原文：
     * Returns whether or not this potion effect has a shorter duration than the
     * provided potion effect.
     * <p>
     * An infinite duration is considered longer than non-infinite durations. If
     * both potion effects have infinite durations, then neither is shorter than
     * the other and this method will return false.
     *
     * @param other 另一个效果
     * <p>原文：the other effect
     * @return 如果此效果比另一个短则为 true, 如果更长或相等则为 false
     * <p>原文：true if this effect is shorter than the other, false if longer or equal
     */
    public boolean isShorterThan(@NotNull PotionEffect other) {
        return !isInfinite() && (duration < other.duration || other.isInfinite());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此效果的类型.
     * <p>原文：
     * Returns the {@link PotionEffectType} of this effect.
     *
     * @return 药水效果的类型
     * <p>原文：The potion type of this effect
     */
    @NotNull
    public PotionEffectType getType() {
        return type;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此状态使药水效果产生更多的、半透明的粒子.
     * <p>原文：
     * Makes potion effect produce more, translucent, particles.
     *
     * @return 此药水效果是否为环境型效果 (译注:比如信标产生的效果)
     * <p>原文：if this effect is ambient
     */
    public boolean isAmbient() {
        return ambient;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 此效果是否有粒子效果
     * <p>原文：whether this effect has particles or not
     */
    public boolean hasParticles() {
        return particles;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 药水效果粒子的颜色. 如果无粒子效果/未定义颜色, 将可能为null
     * <p>原文：color of this potion's particles. May be null if the potion has no particles or defined color.
     * @deprecated 现在粒子效果颜色不属于药水效果的一部分
     * <p>原文：color is not part of potion effects
     */
    @Deprecated(since = "1.13")
    @Nullable
    @Contract("-> null")
    public Color getColor() {
        return null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 此效果是否有图标
     * <p>原文：whether this effect has an icon or not
     */
    public boolean hasIcon() {
        return icon;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + type.hashCode();
        hash = hash * 31 + amplifier;
        hash = hash * 31 + duration;
        hash ^= 0x22222222 >> (ambient ? 1 : -1);
        hash ^= 0x22222222 >> (particles ? 1 : -1);
        hash ^= 0x22222222 >> (icon ? 1 : -1);
        if (hiddenEffect != null) hash = hash * 31 + hiddenEffect.hashCode(); // Paper
        return hash;
    }

    @Override
    public String toString() {
        return "PotionEffect{" + "amplifier=" + amplifier + ", duration=" + duration + ", type=" + type + ", ambient=" + ambient + ", particles=" + particles + ", icon=" + icon + ", hiddenEffect=" + hiddenEffect + '}'; // Paper
    }
}
