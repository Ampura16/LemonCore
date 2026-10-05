package io.papermc.paper.datacomponent.item.blocksattacks;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有给定攻击应对物品施加的损伤值。
 * <p>原文：
 * Hold how much damage should be applied to the item from a given attack.
 *
 * @see io.papermc.paper.datacomponent.DataComponentTypes#BLOCKS_ATTACKS
 * @see io.papermc.paper.datacomponent.item.BlocksAttacks#itemDamage()
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ItemDamageFunction {

    @Contract(value = "-> new", pure = true)
    static ItemDamageFunction.Builder itemDamageFunction() {
        return BlocksAttacksBridge.bridge().blocksAttacksItemDamageFunction();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取攻击造成的伤害达到多少时才会对物品施加损伤的最小值。
     * <p>原文：
     * Get the minimum amount of damage dealt by the attack before item damage is applied to the item.
     *
     * @return 阈值
     * <p>原文：the threshold
     */
    @NonNegative
    float threshold();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取超过阈值时对物品施加的固定损伤值。
     * <p>原文：
     * Get the constant amount of damage applied to the item, if threshold is passed.
     *
     * @return 基础
     * <p>原文：the base
     */
    float base();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取超过阈值时所受伤害中应施加给物品的比例。
     * <p>原文：
     * Get the fraction of the dealt damage that should be applied to the item, if threshold is passed.
     *
     * @return 因子
     * <p>原文：the factor
     */
    float factor();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取要对物品施加的损伤值。
     * <p>原文：
     * Get the damage to apply for the item.
     *
     * @apiNote 这不适用于 {@link org.bukkit.enchantments.Enchantment#UNBREAKING} 之类的魔咒
     * <p>原文：this doesn't apply enchantments like {@link org.bukkit.enchantments.Enchantment#UNBREAKING}
     * @return 要施加的损伤
     * <p>原文：the damage to apply
     */
    int damageToApply(float damage);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link ItemDamageFunction} 的构建器。
     * <p>原文：
     * Builder for {@link ItemDamageFunction}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<ItemDamageFunction> {

        @Contract(value = "_ -> this", mutates = "this")
        ItemDamageFunction.Builder threshold(@NonNegative final float threshold);

        @Contract(value = "_ -> this", mutates = "this")
        ItemDamageFunction.Builder base(final float base);

        @Contract(value = "_ -> this", mutates = "this")
        ItemDamageFunction.Builder factor(final float factor);
    }
}
