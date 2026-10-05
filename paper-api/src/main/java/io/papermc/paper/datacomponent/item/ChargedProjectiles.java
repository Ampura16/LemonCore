package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有已装填到弩中的所有弹射物。
 * <p>原文：
 * Holds all projectiles that have been loaded into a Crossbow.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#CHARGED_PROJECTILES
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ChargedProjectiles {

    @Contract(value = "_ -> new", pure = true)
    static ChargedProjectiles chargedProjectiles(final List<ItemStack> projectiles) {
        return chargedProjectiles().addAll(projectiles).build();
    }

    @Contract(value = "-> new", pure = true)
    static ChargedProjectiles.Builder chargedProjectiles() {
        return ItemComponentTypesBridge.bridge().chargedProjectiles();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 列出当前装填到此组件中的弹射物。
     * <p>原文：
     * Lists the projectiles that are currently loaded into this component.
     *
     * @return 已装填的弹射物
     * <p>原文：the loaded projectiles
     */
    @Contract(value = "-> new", pure = true)
    @Unmodifiable List<ItemStack> projectiles();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link ChargedProjectiles} 的构建器。
     * <p>原文：
     * Builder for {@link ChargedProjectiles}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<ChargedProjectiles> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个待装填的弹射物。
         * <p>原文：
         * Adds a projectile to be loaded in this builder.
         *
         * @param stack 弹射物
         * <p>原文：projectile
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #projectiles()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder add(ItemStack stack);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加多个待装填的弹射物。
         * <p>原文：
         * Adds projectiles to be loaded in this builder.
         *
         * @param stacks 弹射物
         * <p>原文：projectiles
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #projectiles()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addAll(List<ItemStack> stacks);
    }
}
