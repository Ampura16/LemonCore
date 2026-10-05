package org.bukkit.entity;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示曾经是{@link Villager}的{@link Zombie}。
 * <p>原文：
 * Represents a {@link Zombie} which was once a {@link Villager}.
 */
public interface ZombieVillager extends Zombie {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此僵尸的村民职业。
     * <p>原文：
     * Sets the villager profession of this zombie.
     */
    @Override
    void setVillagerProfession(@NotNull Villager.Profession profession); // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此僵尸的村民职业。
     * <p>原文：
     * Returns the villager profession of this zombie.
     *
     * @return 职业或null
     * <p>
     * 原文：Returns the villager profession of this zombie.
     * <p>原文：the profession
     */
    @Override
    @NotNull // Paper
    Villager.Profession getVillagerProfession();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此村民的当前类型。
     * <p>原文：
     * Gets the current type of this villager.
     *
     * @return 当前类型。
     * <p>
     * 原文：Gets the current type of this villager.
     * <p>原文：Current type.
     */
    @NotNull
    public Villager.Type getVillagerType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此村民的新类型。
     * <p>原文：
     * Sets the new type of this villager.
     *
     * @param type 新类型。
     * <p>
     * 原文：Sets the new type of this villager.
     * <p>原文：New type.
     */
    public void setVillagerType(@NotNull Villager.Type type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体是否正在因被治愈而转换为村民。
     * <p>原文：
     * Get if this entity is in the process of converting to a Villager as a
     * result of being cured.
     *
     * @return 转换状态
     * <p>
     * 原文：Get if this entity is in the process of converting to a Villager as a
     * result of being cured.
     * <p>原文：conversion status
     */
    @Override
    boolean isConverting();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体因被治愈而转换为村民的剩余刻数。
     *
     * 当此值达到0时，实体将被转换。
     * <p>原文：
     * Gets the amount of ticks until this entity will be converted to a
     * Villager as a result of being cured.
     *
     * When this reaches 0, the entity will be converted.
     *
     * @return 转换时间
     * <p>原文：conversion time
     * @throws IllegalStateException 如果{@link #isConverting()}为false。
     * <p>
     * 原文：Gets the amount of ticks until this entity will be converted to a
     * Villager as a result of being cured.
     *
     * When this reaches 0, the entity will be converted.
     * <p>原文：if {@link #isConverting()} is false.
     */
    @Override
    int getConversionTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体因被治愈而转换为村民的剩余刻数。
     *
     * 当此值达到0时，实体将被转换。小于0的值将停止当前转换过程，而不会转换当前实体。
     * <p>原文：
     * Sets the amount of ticks until this entity will be converted to a
     * Villager as a result of being cured.
     *
     * When this reaches 0, the entity will be converted. A value of less than 0
     * will stop the current conversion process without converting the current
     * entity.
     *
     * @param time 新转换时间
     * <p>
     * 原文：Sets the amount of ticks until this entity will be converted to a
     * Villager as a result of being cured.
     *
     * When this reaches 0, the entity will be converted. A value of less than 0
     * will stop the current conversion process without converting the current
     * entity.
     * <p>原文：new conversion time
     */
    @Override
    void setConversionTime(int time);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取发起转换的玩家。
     * <p>原文：
     * Gets the player who initiated the conversion.
     *
     * @return 玩家，如果玩家未知或实体当前未在转换则返回<code>null</code>
     * <p>
     * 原文：Gets the player who initiated the conversion.
     * <p>原文：the player, or <code>null</code> if the player is unknown or the
     * entity isn't converting currently
     */
    @Nullable
    OfflinePlayer getConversionPlayer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置发起转换的玩家。
     * <p>
     * 如果此实体当前未在转换，则此方法无效。
     * <p>原文：
     * Sets the player who initiated the conversion.
     * <p>
     * This has no effect if this entity isn't converting currently.
     *
     * @param conversionPlayer 玩家
     * <p>
     * 原文：Sets the player who initiated the conversion.
     * <p>
     * This has no effect if this entity isn't converting currently.
     * <p>原文：the player
     */
    void setConversionPlayer(@Nullable OfflinePlayer conversionPlayer);

    // Paper start - missing entity behaviour api - converting without entity event
    /**
     * Sets the amount of ticks until this entity will be converted to a
     * Villager as a result of being cured.
     * <p>
     * When this reaches 0, the entity will be converted. A value of less than 0
     * will stop the current conversion process without converting the current
     * entity.
     *
     * @param time new conversion time
     * @param broadcastEntityEvent whether this conversion time mutation should broadcast the
     *                             org.bukkit.{@link org.bukkit.EntityEffect#ZOMBIE_TRANSFORM} entity event to the
     *                             world. If false, no entity event is published, preventing for example the
     *                             org.bukkit.{@link org.bukkit.Sound#ENTITY_ZOMBIE_VILLAGER_CURE} from playing.
     */
    void setConversionTime(int time, boolean broadcastEntityEvent);
    // Paper end - missing entity behaviour api - converting without entity event
}
