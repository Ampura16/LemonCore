package org.bukkit.loot;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示 {@link LootTable} 可以用来修改其生成的战利品的附加信息。
 * <p>原文：
 * Represents additional information a {@link LootTable} can use to modify it's
 * generated loot.
 */
public final class LootContext {

    public static final int DEFAULT_LOOT_MODIFIER = -1;

    private final Location location;
    private final float luck;
    private final int lootingModifier;
    private final Entity lootedEntity;
    private final HumanEntity killer;

    private LootContext(@NotNull Location location, float luck, int lootingModifier, @Nullable Entity lootedEntity, @Nullable HumanEntity killer) {
        Preconditions.checkArgument(location != null, "LootContext location cannot be null");
        Preconditions.checkArgument(location.getWorld() != null, "LootContext World cannot be null");
        this.location = location.clone();
        this.luck = luck;
        this.lootingModifier = lootingModifier;
        this.lootedEntity = lootedEntity;
        this.killer = killer;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 存储战利品生成位置的 {@link Location}。
     * <p>原文：
     * The {@link Location} to store where the loot will be generated.
     *
     * @return 战利品将生成的位置
     * <p>原文：the Location of where the loot will be generated
     */
    @NotNull
    public Location getLocation() {
        return location.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示实体可以拥有的 {@link org.bukkit.potion.PotionEffectType#LUCK}。值越高，获得更多战利品的几率越大。
     * <p>原文：
     * Represents the {@link org.bukkit.potion.PotionEffectType#LUCK} that an
     * entity can have. The higher the value the better chance of receiving more
     * loot.
     *
     * @return 幸运值
     * <p>原文：luck
     */
    public float getLuck() {
        return luck;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示 {@link #getKiller()} 实体在其装备物品上的 {@link org.bukkit.enchantments.Enchantment#LOOTING}。
     *
     * 此值仅通过 {@link LootContext.Builder#lootingModifier(int)} 设置。如果未设置，将使用 {@link #getKiller()} 实体的抢夺等级。
     * <p>原文：
     * Represents the
     * {@link org.bukkit.enchantments.Enchantment#LOOTING} the
     * {@link #getKiller()} entity has on their equipped item.
     *
     * This value is only set via
     * {@link LootContext.Builder#lootingModifier(int)}. If not set, the
     * {@link #getKiller()} entity's looting level will be used instead.
     *
     * @return 抢夺等级
     * <p>原文：the looting level
     * @deprecated 不再起作用
     * <p>原文：no longer functional
     */
    @Deprecated(since = "1.21", forRemoval = true)
    public int getLootingModifier() {
        return lootingModifier;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被杀死的 {@link Entity}。可能为 null。
     * <p>原文：
     * Get the {@link Entity} that was killed. Can be null.
     *
     * @return 被掠夺的实体或 null
     * <p>原文：the looted entity or null
     */
    @Nullable
    public Entity getLootedEntity() {
        return lootedEntity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取杀死 {@link #getLootedEntity()} 的 {@link HumanEntity}。可能为 null。
     * <p>原文：
     * Get the {@link HumanEntity} who killed the {@link #getLootedEntity()}.
     * Can be null.
     *
     * @return 击杀者实体，或 null。
     * <p>原文：the killer entity, or null.
     */
    @Nullable
    public HumanEntity getKiller() {
        return killer;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于更轻松地构建 {@link LootContext} 的实用工具类。唯一必需的参数是具有有效（非 null）{@link org.bukkit.World} 的 {@link Location}。
     * <p>原文：
     * Utility class to make building {@link LootContext} easier. The only
     * required argument is {@link Location} with a valid (non-null)
     * {@link org.bukkit.World}.
     */
    public static class Builder {

        private final Location location;
        private float luck;
        private int lootingModifier = LootContext.DEFAULT_LOOT_MODIFIER;
        private Entity lootedEntity;
        private HumanEntity killer;

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 创建一个新的 LootContext.Builder 实例，以便于轻松创建 {@link LootContext}。
         * <p>原文：
         * Creates a new LootContext.Builder instance to facilitate easy
         * creation of {@link LootContext}s.
         *
         * @param location LootContext 应使用的位置
         * <p>原文：the location the LootContext should use
         */
        public Builder(@NotNull Location location) {
            this.location = location.clone();
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 设置生成战利品时的幸运值。
         * <p>原文：
         * Set how much luck to have when generating loot.
         *
         * @param luck 幸运等级
         * <p>原文：the luck level
         * @return Builder 实例
         * <p>原文：the Builder
         */
        @NotNull
        public Builder luck(float luck) {
            this.luck = luck;
            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 设置生成战利品时使用的 {@link org.bukkit.enchantments.Enchantment#LOOTING} 等级等效值。小于或等于 0 的值将强制 {@link LootTable} 每个池只返回一个 {@link org.bukkit.inventory.ItemStack}。
         * <p>原文：
         * Set the {@link org.bukkit.enchantments.Enchantment#LOOTING}
         * level equivalent to use when generating loot. Values less than or
         * equal to 0 will force the {@link LootTable} to only return a single
         * {@link org.bukkit.inventory.ItemStack} per pool.
         *
         * @param modifier 抢夺等级修饰符
         * <p>原文：the looting level modifier
         * @return Builder 实例
         * <p>原文：the Builder
         * @deprecated 不再起作用
         * <p>原文：no longer functional
         */
        @NotNull
        @Deprecated(since = "1.21", forRemoval = true)
        public Builder lootingModifier(int modifier) {
            this.lootingModifier = modifier;
            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 被杀死的实体。
         * <p>原文：
         * The entity that was killed.
         *
         * @param lootedEntity 被掠夺的实体
         * <p>原文：the looted entity
         * @return Builder 实例
         * <p>原文：the Builder
         */
        @NotNull
        public Builder lootedEntity(@Nullable Entity lootedEntity) {
            this.lootedEntity = lootedEntity;
            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 设置杀死 {@link #getLootedEntity()} 的 {@link org.bukkit.entity.HumanEntity}。如果未设置 {@link #lootingModifier(int)}，此实体将用于获取抢夺等级。
         * <p>原文：
         * Set the {@link org.bukkit.entity.HumanEntity} that killed
         * {@link #getLootedEntity()}. This entity will be used to get the
         * looting level if {@link #lootingModifier(int)} is not set.
         *
         * @param killer 击杀者实体
         * <p>原文：the killer entity
         * @return Builder 实例
         * <p>原文：the Builder
         */
        @NotNull
        public Builder killer(@Nullable HumanEntity killer) {
            this.killer = killer;
            return this;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 使用提供的参数创建一个新的 {@link LootContext} 实例。
         * <p>原文：
         * Create a new {@link LootContext} instance using the supplied
         * parameters.
         *
         * @return 一个新的 {@link LootContext} 实例
         * <p>原文：a new {@link LootContext} instance
         */
        @NotNull
        public LootContext build() {
            return new LootContext(location, luck, lootingModifier, lootedEntity, killer);
        }
    }
}
