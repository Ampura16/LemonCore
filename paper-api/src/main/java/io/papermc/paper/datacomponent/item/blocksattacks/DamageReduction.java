package io.papermc.paper.datacomponent.item.blocksattacks;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.registry.set.RegistryKeySet;
import org.bukkit.damage.DamageType;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有给定攻击中应格挡的伤害值。
 * <p>原文：
 * Hold how much damage should be blocked in a given attack.
 *
 * @see io.papermc.paper.datacomponent.DataComponentTypes#BLOCKS_ATTACKS
 * @see io.papermc.paper.datacomponent.item.BlocksAttacks#damageReductions()
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface DamageReduction {

    @Contract(value = "-> new", pure = true)
    static DamageReduction.Builder damageReduction() {
        return BlocksAttacksBridge.bridge().blocksAttacksDamageReduction();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 要格挡的伤害类型。
     * <p>原文：
     * The damage types to block.
     *
     * @return 伤害类型集合
     * <p>原文：the set of damage type
     */
    @Nullable RegistryKeySet<DamageType> type();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取可被格挡的攻击来袭方向与使用者朝向之间的最大角度。
     * <p>原文：
     * Get the maximum angle between the users facing direction and the direction of the incoming attack to be blocked.
     *
     * @return 角度
     * <p>原文：the angle
     */
    @Positive float horizontalBlockingAngle();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取要格挡的固定伤害值。
     * <p>原文：
     * Get the constant amount of damage to be blocked.
     *
     * @return 基础
     * <p>原文：the base
     */
    float base();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取所受伤害中要格挡的比例。
     * <p>原文：
     * Get the fraction of the dealt damage to be blocked.
     *
     * @return 因子
     * <p>原文：the factor
     */
    float factor();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link DamageReduction} 的构建器。
     * <p>原文：
     * Builder for {@link DamageReduction}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<DamageReduction> {

        @Contract(value = "_ -> this", mutates = "this")
        DamageReduction.Builder type(RegistryKeySet<DamageType> type);

        @Contract(value = "_ -> this", mutates = "this")
        DamageReduction.Builder horizontalBlockingAngle(@Positive float horizontalBlockingAngle);

        @Contract(value = "_ -> this", mutates = "this")
        DamageReduction.Builder base(float base);

        @Contract(value = "_ -> this", mutates = "this")
        DamageReduction.Builder factor(float factor);
    }

}
