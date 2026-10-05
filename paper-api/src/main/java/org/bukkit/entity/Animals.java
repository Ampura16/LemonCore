package org.bukkit.entity;

import java.util.UUID;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表动物.
 * <p>原文：
 * Represents an Animal.
 */
public interface Animals extends Breedable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取导致此实体进入 {@link #canBreed()} 状态的实体UUID.
     * <p>原文：
     * Get the UUID of the entity that caused this entity to enter the
     * {@link #canBreed()} state.
     *
     * @return 如果已设置则返回UUID，否则返回null
     * <p>原文：uuid if set, or null
     */
    @Nullable
    UUID getBreedCause();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置导致此实体进入 {@link #canBreed()} 状态的实体UUID.
     * <p>原文：
     * Set the UUID of the entity that caused this entity to enter the
     * {@link #canBreed()} state.
     *
     * @param uuid 新的UUID，或null
     * <p>原文：new uuid, or null
     */
    void setBreedCause(@Nullable UUID uuid);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体是否处于发情状态，并将与另一个处于发情状态的实体产生后代。当且仅当 {@link #getLoveModeTicks()} 大于0时返回true.
     * <p>原文：
     * Get whether or not this entity is in love mode and will produce
     * offspring with another entity in love mode. Will return true if
     * and only if {@link #getLoveModeTicks()} is greater than 0.
     *
     * @return 如果处于发情状态则返回true，否则返回false
     * <p>原文：true if in love mode, false otherwise
     */
    boolean isLoveMode();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体剩余的发情状态tick数。如果实体不处于发情状态，将返回0.
     * <p>原文：
     * Get the amount of ticks remaining for this entity in love mode.
     * If the entity is not in love mode, 0 will be returned.
     *
     * @return 剩余的发情状态tick数
     * <p>原文：the remaining love mode ticks
     */
    int getLoveModeTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体应处于发情状态的tick数。将发情状态tick数设置为600等同于玩家喂食该实体其选择的繁殖物品.
     * <p>原文：
     * Set the amount of ticks for which this entity should be in love mode.
     * Setting the love mode ticks to 600 is the equivalent of a player
     * feeding the entity their breeding item of choice.
     *
     * @param ticks 发情状态tick数，必须为正数
     * <p>原文：the love mode ticks. Must be positive
     */
    void setLoveModeTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查提供的ItemStack是否是用于繁殖此实体的正确物品.
     * <p>原文：
     * Check if the provided ItemStack is the correct item used for breeding
     * this entity.
     *
     * @param stack 要检查的ItemStack
     * <p>原文：ItemStack to check.
     * @return 如果提供的ItemStack是此实体的正确食物则返回true
     * <p>原文：if the provided ItemStack is the correct food item for this
     * entity.
     */
    boolean isBreedItem(@NotNull ItemStack stack);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查提供的Material是否是用于繁殖此实体的正确物品.
     * <p>原文：
     * Check if the provided Material is the correct item used for breeding
     * this entity.
     *
     * @param material 要检查的Material
     * <p>原文：Material to check.
     * @return 如果提供的Material是此实体的正确食物则返回true
     * <p>原文：if the provided Material is the correct food item for this
     * entity.
     */
    boolean isBreedItem(@NotNull Material material);
}
