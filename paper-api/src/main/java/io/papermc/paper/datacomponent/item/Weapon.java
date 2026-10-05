package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface Weapon {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回用于创建武器的新构建器。
     * <p>原文：
     * Returns a new builder for creating a Weapon.
     *
     * @return 构建器实例。
     * <p>原文：a builder instance.
     */
    static Builder weapon() {
        return ItemComponentTypesBridge.bridge().weapon();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 每次使用该武器攻击时扣除的耐久度。
     * <p>原文：
     * Amount of durability to remove each time the weapon is used to attack.
     *
     * @return 耐久度
     * <p>原文：durability
     */
    int itemDamagePerAttack();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 格挡被禁用的秒数。
     * <p>原文：
     * The number of seconds that blocking is disabled.
     *
     * @return 秒数
     * <p>原文：seconds
     */
    float disableBlockingForSeconds();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link Weapon} 的构建器。
     * <p>原文：
     * Builder for {@link Weapon}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<Weapon> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 控制每次使用该武器攻击时扣除的耐久度。
         * <p>原文：
         * Controls the amount of durability to remove each time the weapon is used to attack.
         *
         * @param damage 要移除的耐久度
         * <p>原文：durability to remove
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #itemDamagePerAttack()
         */
        Builder itemDamagePerAttack(int damage);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置禁用格挡的持续时间（秒）。
         * <p>原文：
         * Sets the disable blocking duration (in seconds).
         *
         * @param seconds 持续时间（秒）。
         * <p>原文：the duration in seconds.
         * @return 构建器，用于链式调用。
         * <p>原文：the builder for chaining.
         */
        Builder disableBlockingForSeconds(float seconds);
    }
}
