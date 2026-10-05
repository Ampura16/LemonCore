package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个单独的烟花效果。
 * <p>原文：
 * Represents a single firework effect.
 */
@SerializableAs("Firework")
public final class FireworkEffect implements ConfigurationSerializable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 效果的类型或形状。
     * <p>原文：
     * The type or shape of the effect.
     */
    public enum Type implements net.kyori.adventure.translation.Translatable { // Paper - Adventure translations
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 小型球状效果。
         * <p>原文：
         * A small ball effect.
         */
        BALL("small_ball"), // Paper - add name
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 大型球状效果。
         * <p>原文：
         * A large ball effect.
         */
        BALL_LARGE("large_ball"), // Paper - add name
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 星形效果。
         * <p>原文：
         * A star-shaped effect.
         */
        STAR("star"), // Paper - add name
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 爆裂效果。
         * <p>原文：
         * A burst effect.
         */
        BURST("burst"), // Paper - add name
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 苦力怕脸型效果。
         * <p>原文：
         * A creeper-face effect.
         */
        CREEPER("creeper"), // Paper - add name
        ;
        // Paper start
        /**
         * The name map.
         */
        public static final net.kyori.adventure.util.Index<String, org.bukkit.FireworkEffect.Type> NAMES = net.kyori.adventure.util.Index.create(Type.class, type -> type.name);
        private final String name;

        Type(final String name) {
            this.name = name;
        }

        @Override
        public @NotNull String translationKey() {
            return "item.minecraft.firework_star.shape." + this.name;
        }
        // Paper end
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构建一个烟花效果。
     * <p>原文：
     * Construct a firework effect.
     *
     * @return 一个用于构建烟花效果的实用对象
     * <p>原文：A utility object for building a firework effect
     */
    @NotNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 这是一个烟花效果的生成器。
     * <p>原文：
     * This is a builder for FireworkEffects.
     *
     * @see FireworkEffect#builder()
     */
    public static final class Builder {
        boolean flicker = false;
        boolean trail = false;
        final ImmutableList.Builder<Color> colors = ImmutableList.builder();
        ImmutableList.Builder<Color> fadeColors = null;
        Type type = Type.BALL;

        Builder() {}

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 指定烟花效果的类型。
         * <p>原文：
         * Specify the type of the firework effect.
         *
         * @param type 效果类型
         * <p>原文：The effect type
         * @return 用于链接的对象
         * <p>原文：This object, for chaining
         * @throws IllegalArgumentException 如果类型为空则抛出错误
         * <p>原文：If type is null
         */
        @NotNull
        public Builder with(@NotNull Type type) throws IllegalArgumentException {
            Preconditions.checkArgument(type != null, "Cannot have null type");
            this.type = type;
            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向烟花添加一个闪烁效果。
         * <p>原文：
         * Add a flicker to the firework effect.
         *
         * @return 用于链接的对象
         * <p>原文：This object, for chaining
         */
        @NotNull
        public Builder withFlicker() {
            flicker = true;
            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 设置烟花是否具有闪烁效果。
         * <p>原文：
         * Set whether the firework effect should flicker.
         *
         * @param flicker 具有闪烁效果为true，否则为false
         * <p>原文：true if it should flicker, false if not
         * @return 用于链接的对象
         * <p>原文：This object, for chaining
         */
        @NotNull
        public Builder flicker(boolean flicker) {
            this.flicker = flicker;
            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向烟花添加一个尾迹效果。
         * <p>原文：
         * Add a trail to the firework effect.
         *
         * @return 用于链接的对象
         * <p>原文：This object, for chaining
         */
        @NotNull
        public Builder withTrail() {
            trail = true;
            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 设置烟花是否有尾迹效果。
         * <p>原文：
         * Set whether the firework effect should have a trail.
         *
         * @param trail 如果具有尾迹效果则返回true，否则返回false
         * <p>原文：true if it should have a trail, false for no trail
         * @return 用于链接的对象
         * <p>原文：This object, for chaining
         */
        @NotNull
        public Builder trail(boolean trail) {
            this.trail = trail;
            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向烟花效果添加一种主颜色。
         * <p>原文：
         * Add a primary color to the firework effect.
         *
         * @param color 添加的颜色
         * <p>原文：The color to add
         * @return 用于链接的对象
         * <p>原文：This object, for chaining
         * @throws IllegalArgumentException 如果颜色为空则抛出错误
         * <p>原文：If color is null
         */
        @NotNull
        public Builder withColor(@NotNull Color color) throws IllegalArgumentException {
            Preconditions.checkArgument(color != null, "Cannot have null color");

            colors.add(color);

            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向烟花效果添加几种主颜色。
         * <p>原文：
         * Add several primary colors to the firework effect.
         *
         * @param colors 添加的颜色
         * <p>原文：The colors to add
         * @return 用于链接的对象
         * <p>原文：This object, for chaining
         * @throws IllegalArgumentException 如果颜色为空则抛出错误
         * <p>原文：If colors is null
         * @throws IllegalArgumentException 如果颜色为空则抛出错误
         * <p>原文：If any color is null (may be
         *     thrown after changes have occurred)
         */
        @NotNull
        public Builder withColor(@NotNull Color... colors) throws IllegalArgumentException {
            Preconditions.checkArgument(colors != null, "Cannot have null colors");
            if (colors.length == 0) {
                return this;
            }

            ImmutableList.Builder<Color> list = this.colors;
            for (Color color : colors) {
                Preconditions.checkArgument(color != null, "Color cannot be null");
                list.add(color);
            }

            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向烟花效果添加几种主颜色。
         * <p>原文：
         * Add several primary colors to the firework effect.
         *
         * @param colors 一个可迭代的对象，其迭代器可产生所需的颜色。
         * <p>原文：An iterable object whose iterator yields the desired
         *     colors
         * @return 用于链接的对象
         * <p>原文：This object, for chaining
         * @throws IllegalArgumentException 如果颜色为空则抛出错误
         * <p>原文：If colors is null
         * @throws IllegalArgumentException 如果颜色为空则抛出错误
         * <p>原文：If any color is null (may be
         *     thrown after changes have occurred)
         */
        @NotNull
        public Builder withColor(@NotNull Iterable<?> colors) throws IllegalArgumentException {
            Preconditions.checkArgument(colors != null, "Cannot have null colors");

            ImmutableList.Builder<Color> list = this.colors;
            for (Object color : colors) {
                if (!(color instanceof Color)) {
                    throw new IllegalArgumentException(color + " is not a Color in " + colors);
                }
                list.add((Color) color);
            }

            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向烟花效果添加一种淡出颜色。
         * <p>原文：
         * Add a fade color to the firework effect.
         *
         * @param color 添加的颜色
         * <p>原文：The color to add
         * @return 用于链接的对象
         * <p>原文：This object, for chaining
         * @throws IllegalArgumentException 如果颜色为空则抛出错误
         * <p>原文：If colors is null
         * @throws IllegalArgumentException 如果颜色为空则抛出错误
         * <p>原文：If any color is null (may be
         *     thrown after changes have occurred)
         */
        @NotNull
        public Builder withFade(@NotNull Color color) throws IllegalArgumentException {
            Preconditions.checkArgument(color != null, "Cannot have null color");

            if (fadeColors == null) {
                fadeColors = ImmutableList.builder();
            }

            fadeColors.add(color);

            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向烟花效果添加几种淡出颜色。
         * <p>原文：
         * Add several fade colors to the firework effect.
         *
         * @param colors 添加的颜色
         * <p>原文：The colors to add
         * @return 用于链接的对象
         * <p>原文：This object, for chaining
         * @throws IllegalArgumentException 如果颜色为空则抛出错误
         * <p>原文：If colors is null
         * @throws IllegalArgumentException 如果颜色为空则抛出错误
         * <p>原文：If any color is null (may be
         *     thrown after changes have occurred)
         */
        @NotNull
        public Builder withFade(@NotNull Color... colors) throws IllegalArgumentException {
            Preconditions.checkArgument(colors != null, "Cannot have null colors");
            if (colors.length == 0) {
                return this;
            }

            ImmutableList.Builder<Color> list = this.fadeColors;
            if (list == null) {
                list = this.fadeColors = ImmutableList.builder();
            }

            for (Color color : colors) {
                Preconditions.checkArgument(color != null, "Color cannot be null");
                list.add(color);
            }

            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向烟花效果添加几种淡出颜色。
         * <p>原文：
         * Add several fade colors to the firework effect.
         *
         * @param colors 一个可迭代的对象，其迭代器可产生所需的颜色。
         * <p>原文：An iterable object whose iterator yields the desired
         *     colors
         * @return 用于链接的对象
         * <p>原文：This object, for chaining
         * @throws IllegalArgumentException 如果颜色为空则抛出错误
         * <p>原文：If colors is null
         * @throws IllegalArgumentException 如果颜色为空则抛出错误
         * <p>原文：If any color is null (may be
         *     thrown after changes have occurred)
         */
        @NotNull
        public Builder withFade(@NotNull Iterable<?> colors) throws IllegalArgumentException {
            Preconditions.checkArgument(colors != null, "Cannot have null colors");

            ImmutableList.Builder<Color> list = this.fadeColors;
            if (list == null) {
                list = this.fadeColors = ImmutableList.builder();
            }

            for (Object color : colors) {
                if (!(color instanceof Color)) {
                    throw new IllegalArgumentException(color + " is not a Color in " + colors);
                }
                list.add((Color) color);
            }

            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 根据这个生成器的当前内容创建一个{@link FireworkEffect}。
         * <p>
         * 成功生成需要指定至少一种颜色。
         * <p>原文：
         * Create a {@link FireworkEffect} from the current contents of this
         * builder.
         * <p>
         * To successfully build, you must have specified at least one color.
         *
         * @return 烟花效果模板
         * <p>原文：The representative firework effect
         */
        @NotNull
        public FireworkEffect build() {
            return new FireworkEffect(
                flicker,
                trail,
                colors.build(),
                fadeColors == null ? ImmutableList.<Color>of() : fadeColors.build(),
                type
            );
        }
    }

    private static final String FLICKER = "flicker";
    private static final String TRAIL = "trail";
    private static final String COLORS = "colors";
    private static final String FADE_COLORS = "fade-colors";
    private static final String TYPE = "type";

    private final boolean flicker;
    private final boolean trail;
    private final ImmutableList<Color> colors;
    private final ImmutableList<Color> fadeColors;
    private final Type type;
    private String string = null;

    FireworkEffect(boolean flicker, boolean trail, @NotNull ImmutableList<Color> colors, @NotNull ImmutableList<Color> fadeColors, @NotNull Type type) {
        // Paper - can have empty colors
        this.flicker = flicker;
        this.trail = trail;
        this.colors = colors;
        this.fadeColors = fadeColors;
        this.type = type;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取烟花是否具有闪烁效果。
     * <p>原文：
     * Get whether the firework effect flickers.
     *
     * @return 如果具有闪烁效果则返回true，否则返回false
     * <p>原文：true if it flickers, false if not
     */
    public boolean hasFlicker() {
        return flicker;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取烟花是否具有尾迹效果。
     * <p>原文：
     * Get whether the firework effect has a trail.
     *
     * @return 如果具有尾迹效果则返回true，否则返回false
     * <p>原文：true if it has a trail, false if not
     */
    public boolean hasTrail() {
        return trail;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取烟花效果的主颜色。
     * <p>原文：
     * Get the primary colors of the firework effect.
     *
     * @return 获取一个主颜色的不可变列表
     * <p>原文：An immutable list of the primary colors
     */
    @NotNull
    public List<Color> getColors() {
        return colors;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取烟花效果的淡出颜色。
     * <p>原文：
     * Get the fade colors of the firework effect.
     *
     * @return 获取一个淡出颜色的不可变列表
     * <p>原文：An immutable list of the fade colors
     */
    @NotNull
    public List<Color> getFadeColors() {
        return fadeColors;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取烟花效果的类型。
     * <p>原文：
     * Get the type of the firework effect.
     *
     * @return 效果类型
     * <p>原文：The effect type
     */
    @NotNull
    public Type getType() {
        return type;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param map 用于反序列化的Map
     * <p>原文：the map to deserialize
     * @return 序列化的结果
     * <p>原文：the resulting serializable
     * @see ConfigurationSerializable
     */
    @NotNull
    public static ConfigurationSerializable deserialize(@NotNull Map<String, Object> map) {
        Type type = Type.valueOf((String) map.get(TYPE));

        return builder()
            .flicker((Boolean) map.get(FLICKER))
            .trail((Boolean) map.get(TRAIL))
            .withColor((Iterable<?>) map.get(COLORS))
            .withFade((Iterable<?>) map.get(FADE_COLORS))
            .with(type)
            .build();
    }

    @NotNull
    @Override
    public Map<String, Object> serialize() {
        return ImmutableMap.<String, Object>of(
            FLICKER, flicker,
            TRAIL, trail,
            COLORS, colors,
            FADE_COLORS, fadeColors,
            TYPE, type.name()
        );
    }

    @Override
    public String toString() {
        final String string = this.string;
        if (string == null) {
            return this.string = "FireworkEffect:" + serialize();
        }
        return string;
    }

    @Override
    public int hashCode() {
        // TRUE and FALSE as per boolean.hashCode()
        final int PRIME = 31, TRUE = 1231, FALSE = 1237;
        int hash = 1;
        hash = hash * PRIME + (flicker ? TRUE : FALSE);
        hash = hash * PRIME + (trail ? TRUE : FALSE);
        hash = hash * PRIME + type.hashCode();
        hash = hash * PRIME + colors.hashCode();
        hash = hash * PRIME + fadeColors.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof FireworkEffect)) {
            return false;
        }

        FireworkEffect that = (FireworkEffect) obj;
        return this.flicker == that.flicker
                && this.trail == that.trail
                && this.type == that.type
                && this.colors.equals(that.colors)
                && this.fadeColors.equals(that.fadeColors);
    }
}
