package org.bukkit;

import com.google.common.base.Preconditions;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public enum Particle implements Keyed {
    POOF("poof"),
    EXPLOSION("explosion"),
    EXPLOSION_EMITTER("explosion_emitter"),
    FIREWORK("firework"),
    BUBBLE("bubble"),
    SPLASH("splash"),
    FISHING("fishing"),
    UNDERWATER("underwater"),
    CRIT("crit"),
    ENCHANTED_HIT("enchanted_hit"),
    SMOKE("smoke"),
    LARGE_SMOKE("large_smoke"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link Spell} 作为数据类型。
     * <p>原文：
     * Uses {@link Spell} as DataType
     */
    EFFECT("effect", Spell.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link Spell} 作为数据类型。
     * <p>原文：
     * Uses {@link Spell} as DataType
     */
    INSTANT_EFFECT("instant_effect", Spell.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link Color} 作为数据类型。
     * <p>原文：
     * Uses {@link Color} as DataType (with alpha support)
     */
    ENTITY_EFFECT("entity_effect", Color.class),
    WITCH("witch"),
    DRIPPING_WATER("dripping_water"),
    DRIPPING_LAVA("dripping_lava"),
    ANGRY_VILLAGER("angry_villager"),
    HAPPY_VILLAGER("happy_villager"),
    MYCELIUM("mycelium"),
    NOTE("note"),
    PORTAL("portal"),
    ENCHANT("enchant"),
    FLAME("flame"),
    LAVA("lava"),
    CLOUD("cloud"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link Particle.DustOptions} 作为数据类型。
     * <p>原文：
     * Uses {@link DustOptions} as DataType
     */
    DUST("dust", DustOptions.class),
    ITEM_SNOWBALL("item_snowball"),
    ITEM_SLIME("item_slime"),
    HEART("heart"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link ItemStack} 作为数据类型。
     * <p>原文：
     * Uses {@link ItemStack} as DataType
     */
    ITEM("item", ItemStack.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link BlockData} 作为数据类型。
     * <p>原文：
     * Uses {@link BlockData} as DataType
     */
    BLOCK("block", BlockData.class),
    RAIN("rain"),
    ELDER_GUARDIAN("elder_guardian"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link Float} 作为数据类型。
     * <p>原文：
     * Uses {@link Float} as DataType, for the power of the breath
     */
    DRAGON_BREATH("dragon_breath", Float.class),
    END_ROD("end_rod"),
    DAMAGE_INDICATOR("damage_indicator"),
    SWEEP_ATTACK("sweep_attack"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link BlockData} 作为数据类型。
     * <p>原文：
     * Uses {@link BlockData} as DataType
     */
    FALLING_DUST("falling_dust", BlockData.class),
    TOTEM_OF_UNDYING("totem_of_undying"),
    SPIT("spit"),
    SQUID_INK("squid_ink"),
    BUBBLE_POP("bubble_pop"),
    CURRENT_DOWN("current_down"),
    BUBBLE_COLUMN_UP("bubble_column_up"),
    NAUTILUS("nautilus"),
    DOLPHIN("dolphin"),
    SNEEZE("sneeze"),
    CAMPFIRE_COSY_SMOKE("campfire_cosy_smoke"),
    CAMPFIRE_SIGNAL_SMOKE("campfire_signal_smoke"),
    COMPOSTER("composter"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link Color} 作为数据类型。
     * <p>原文：
     * Uses {@link Color} as DataType
     */
    FLASH("flash", Color.class),
    FALLING_LAVA("falling_lava"),
    LANDING_LAVA("landing_lava"),
    FALLING_WATER("falling_water"),
    DRIPPING_HONEY("dripping_honey"),
    FALLING_HONEY("falling_honey"),
    LANDING_HONEY("landing_honey"),
    FALLING_NECTAR("falling_nectar"),
    SOUL_FIRE_FLAME("soul_fire_flame"),
    ASH("ash"),
    CRIMSON_SPORE("crimson_spore"),
    WARPED_SPORE("warped_spore"),
    SOUL("soul"),
    DRIPPING_OBSIDIAN_TEAR("dripping_obsidian_tear"),
    FALLING_OBSIDIAN_TEAR("falling_obsidian_tear"),
    LANDING_OBSIDIAN_TEAR("landing_obsidian_tear"),
    REVERSE_PORTAL("reverse_portal"),
    WHITE_ASH("white_ash"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link DustTransition} 作为数据类型。
     * <p>原文：
     * Uses {@link DustTransition} as DataType
     */
    DUST_COLOR_TRANSITION("dust_color_transition", DustTransition.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link Vibration} 作为数据类型。
     * <p>原文：
     * Uses {@link Vibration} as DataType
     */
    VIBRATION("vibration", Vibration.class),
    FALLING_SPORE_BLOSSOM("falling_spore_blossom"),
    SPORE_BLOSSOM_AIR("spore_blossom_air"),
    SMALL_FLAME("small_flame"),
    SNOWFLAKE("snowflake"),
    DRIPPING_DRIPSTONE_LAVA("dripping_dripstone_lava"),
    FALLING_DRIPSTONE_LAVA("falling_dripstone_lava"),
    DRIPPING_DRIPSTONE_WATER("dripping_dripstone_water"),
    FALLING_DRIPSTONE_WATER("falling_dripstone_water"),
    GLOW_SQUID_INK("glow_squid_ink"),
    GLOW("glow"),
    WAX_ON("wax_on"),
    WAX_OFF("wax_off"),
    ELECTRIC_SPARK("electric_spark"),
    SCRAPE("scrape"),
    SONIC_BOOM("sonic_boom"),
    SCULK_SOUL("sculk_soul"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link Float} 作为数据类型。
     * <p>原文：
     * Uses {@link Float} as DataType, the angle in radians
     */
    SCULK_CHARGE("sculk_charge", Float.class),
    SCULK_CHARGE_POP("sculk_charge_pop"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link Integer} 作为数据类型。
     * <p>原文：
     * Uses {@link Integer} as DataType
     */
    SHRIEK("shriek", Integer.class),
    CHERRY_LEAVES("cherry_leaves"),
    PALE_OAK_LEAVES("pale_oak_leaves"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link Color} 作为数据类型。
     * <p>原文：
     * Uses {@link Color} as DataType
     */
    TINTED_LEAVES("tinted_leaves", Color.class),
    EGG_CRACK("egg_crack"),
    DUST_PLUME("dust_plume"),
    WHITE_SMOKE("white_smoke"),
    GUST("gust"),
    SMALL_GUST("small_gust"),
    GUST_EMITTER_LARGE("gust_emitter_large"),
    GUST_EMITTER_SMALL("gust_emitter_small"),
    TRIAL_SPAWNER_DETECTION("trial_spawner_detection"),
    TRIAL_SPAWNER_DETECTION_OMINOUS("trial_spawner_detection_ominous"),
    VAULT_CONNECTION("vault_connection"),
    INFESTED("infested"),
    ITEM_COBWEB("item_cobweb"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link BlockData} 作为数据类型。
     * <p>原文：
     * Uses {@link BlockData} as DataType
     */
    DUST_PILLAR("dust_pillar", BlockData.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link BlockData} 作为数据类型。
     * <p>原文：
     * Uses {@link BlockData} as DataType
     */
    BLOCK_CRUMBLE("block_crumble", BlockData.class),
    FIREFLY("firefly"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link Trail} 作为数据类型。
     * <p>原文：
     * Uses {@link Trail} as DataType
     */
    TRAIL("trail", Trail.class),
    OMINOUS_SPAWNING("ominous_spawning"),
    RAID_OMEN("raid_omen"),
    TRIAL_OMEN("trial_omen"),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用 {@link BlockData} 作为数据类型。
     * <p>原文：
     * Uses {@link BlockData} as DataType
     */
    BLOCK_MARKER("block_marker", BlockData.class),
    COPPER_FIRE_FLAME("copper_fire_flame"),
    ;

    private final NamespacedKey key;
    private final Class<?> dataType;
    // Paper - all particles are registered

    Particle(String key) {
        this(key, Void.class);
    }

    // Paper - all particles are registered

    Particle(String key, /*@NotNull*/ Class<?> data) {
        // Paper - all particles are registered
        if (key != null) {
            this.key = NamespacedKey.minecraft(key);
        } else {
            this.key = null;
        }
        dataType = data;
        // Paper - all particles are registered
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回粒子所需的数据类型。
     * <p>原文：
     * Returns the required data type for the particle
     *
     * @return 所需的数据类型。
     *
     * 原文：
     * Returns the required data type for the particle
     * <p>原文：the required data type
     */
    @NotNull
    public Class<?> getDataType() {
        return dataType;
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        if (key == null) {
            throw new UnsupportedOperationException("Cannot get key from legacy particle");
        }

        return key;
    }

    // Paper start - Particle API expansion
    /**
     * Creates a {@link com.destroystokyo.paper.ParticleBuilder}
     *
     * @return a {@link com.destroystokyo.paper.ParticleBuilder} for the particle
     */
    @NotNull
    public com.destroystokyo.paper.ParticleBuilder builder() {
        return new com.destroystokyo.paper.ParticleBuilder(this);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 可以应用于红石粉粒子的选项 - 粒子颜色和大小。
     * <p>原文：
     * Options which can be applied to dust particles - a particle
     * color and size.
     */
    public static class DustOptions {

        private final Color color;
        private final float size;

        public DustOptions(@NotNull Color color, float size) {
            Preconditions.checkArgument(color != null, "color");
            this.color = color;
            this.size = size;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 要显示的粒子的颜色。
         * <p>原文：
         * The color of the particles to be displayed.
         *
         * @return 粒子颜色。
         *
         * 原文：
         * The color of the particles to be displayed.
         * <p>原文：particle color
         */
        @NotNull
        public Color getColor() {
            return color;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 粒子的相对大小。
         * <p>原文：
         * Relative size of the particle.
         *
         * @return 粒子的相对大小。
         *
         * 原文：
         * Relative size of the particle.
         * <p>原文：relative particle size
         */
        public float getSize() {
            return size;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 可以应用于颜色渐变粒子的选项。
     * <p>原文：
     * Options which can be applied to a color transitioning dust particles.
     */
    public static class DustTransition extends DustOptions {

        private final Color toColor;

        public DustTransition(@NotNull Color fromColor, @NotNull Color toColor, float size) {
            super(fromColor, size);

            Preconditions.checkArgument(toColor != null, "toColor");
            this.toColor = toColor;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 要显示的粒子的最终颜色。
         * <p>原文：
         * The final of the particles to be displayed.
         *
         * @return 粒子的最终颜色。
         *
         * 原文：
         * The final of the particles to be displayed.
         * <p>原文：final particle color
         */
        @NotNull
        public Color getToColor() {
            return toColor;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 可以应用于轨迹粒子的选项 - 位置、颜色和持续时间。
     * <p>原文：
     * Options which can be applied to trail particles - a location, color and duration.
     */
    public static class Trail {

        private final Location target;
        private final Color color;
        private final int duration;

        public Trail(@NotNull Location target, @NotNull Color color, int duration) {
            this.target = target;
            this.color = color;
            this.duration = duration;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 要显示的粒子的目标。
         * <p>原文：
         * The target of the particles to be displayed.
         *
         * @return 粒子目标。
         *
         * 原文：
         * The target of the particles to be displayed.
         * <p>原文：particle target
         */
        @NotNull
        public Location getTarget() {
            return target;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 要显示的粒子的颜色。
         * <p>原文：
         * The color of the particles to be displayed.
         *
         * @return 粒子颜色。
         *
         * 原文：
         * The color of the particles to be displayed.
         * <p>原文：particle color
         */
        @NotNull
        public Color getColor() {
            return color;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 要显示的轨迹的持续时间。
         * <p>原文：
         * The duration of the trail to be displayed.
         *
         * @return 轨迹持续时间。
         *
         * 原文：
         * The duration of the trail to be displayed.
         * <p>原文：trail duration
         */
        public int getDuration() {
            return duration;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 可以应用于法术效果粒子的选项 - 颜色和强度。
     */
    public static class Spell {

        private final Color color;
        private final float power;

        public Spell(@NotNull Color color, float power) {
            this.color = color;
            this.power = power;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 要显示的粒子的颜色。
         * <p>原文：
         * The color of the particles to be displayed.
         *
         * @return 粒子颜色。
         *
         * 原文：
         * The color of the particles to be displayed.
         * <p>原文：particle color
         */
        public @NotNull Color getColor() {
            return color;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 要显示的效果的强度。
         * <p>原文：
         * The power of the particles to be displayed.
         *
         * @return 强度。
         *
         * 原文：
         * The power of the effect to be displayed.
         * <p>原文：particle power
         */
        public float getPower() {
            return power;
        }
    }
}
