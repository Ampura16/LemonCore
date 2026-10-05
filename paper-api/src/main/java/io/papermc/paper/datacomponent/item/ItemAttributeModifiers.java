package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.datacomponent.item.attribute.AttributeModifierDisplay;
import java.util.List;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有应用于任意物品的属性修饰符。
 * <p>原文：
 * Holds attribute modifiers applied to any item.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#ATTRIBUTE_MODIFIERS
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ItemAttributeModifiers {

    @Contract(value = "-> new", pure = true)
    static ItemAttributeModifiers.Builder itemAttributes() {
        return ItemComponentTypesBridge.bridge().modifiers();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 列出此物品上存在的属性修饰符。
     * <p>原文：
     * Lists the attribute modifiers that are present on this item.
     *
     * @return 修饰符
     * <p>原文：modifiers
     */
    @Contract(pure = true)
    @Unmodifiable List<Entry> modifiers();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有一个属性条目。
     * <p>原文：
     * Holds an attribute entry.
     */
    @ApiStatus.NonExtendable
    interface Entry {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取所配对修饰符的目标属性。
         * <p>原文：
         * Gets the target attribute for the paired modifier.
         *
         * @return 属性
         * <p>原文：the attribute
         */
        @Contract(pure = true)
        Attribute attribute();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 所配对属性的修饰符。
         * <p>原文：
         * The modifier for the paired attribute.
         *
         * @return 修饰符
         * <p>原文：the modifier
         */
        @Contract(pure = true)
        AttributeModifier modifier();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取所配对属性的槽位组。
         * <p>原文：
         * Gets the slot group for the paired attribute.
         *
         * @return 槽位组
         * <p>原文：the slot group
         */
        default EquipmentSlotGroup getGroup() {
            return this.modifier().getSlotGroup();
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 属性修饰符的显示行为。
         * <p>原文：
         * The display behavior for the attribute modifier.
         *
         * @return 显示行为
         * <p>原文：the display behavior
         */
        @Contract(pure = true)
        AttributeModifierDisplay display();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link ItemAttributeModifiers} 的构建器。
     * <p>原文：
     * Builder for {@link ItemAttributeModifiers}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<ItemAttributeModifiers> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个修饰符。
         * <p>原文：
         * Adds a modifier to this builder.
         *
         * @param attribute 属性
         * <p>原文：the attribute
         * @param modifier 修饰符
         * <p>原文：the modifier
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #modifiers()
         */
        @Contract(value = "_, _ -> this", mutates = "this")
        default Builder addModifier(Attribute attribute, AttributeModifier modifier) {
            return this.addModifier(attribute, modifier, modifier.getSlotGroup());
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个修饰符。
         * <p>原文：
         * Adds a modifier to this builder.
         *
         * @param attribute 属性
         * <p>原文：the attribute
         * @param modifier 修饰符
         * <p>原文：the modifier
         * @param equipmentSlotGroup 此修饰符适用的槽位组（覆盖修饰符中的任何槽位组）
         * <p>原文：the slot group this modifier applies to (overrides any slot group in the modifier)
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #modifiers()
         */
        @Contract(value = "_, _, _ -> this", mutates = "this")
        default Builder addModifier(Attribute attribute, AttributeModifier modifier, EquipmentSlotGroup equipmentSlotGroup) {
            return this.addModifier(attribute, modifier, equipmentSlotGroup, AttributeModifierDisplay.reset());
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个修饰符。
         * <p>原文：
         * Adds a modifier to this builder.
         *
         * @param attribute 属性
         * <p>原文：the attribute
         * @param modifier 修饰符
         * <p>原文：the modifier
         * @param display 修饰符显示行为
         * <p>原文：the modifier display behavior
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #modifiers()
         */
        @Contract(value = "_, _, _ -> this", mutates = "this")
        default Builder addModifier(Attribute attribute, AttributeModifier modifier, AttributeModifierDisplay display) {
            return this.addModifier(attribute, modifier, modifier.getSlotGroup(), display);
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个修饰符。
         * <p>原文：
         * Adds a modifier to this builder.
         *
         * @param attribute 属性
         * <p>原文：the attribute
         * @param modifier 修饰符
         * <p>原文：the modifier
         * @param equipmentSlotGroup 此修饰符适用的槽位组（覆盖修饰符中的任何槽位组）
         * <p>原文：the slot group this modifier applies to (overrides any slot group in the modifier)
         * @param display 修饰符显示行为
         * <p>原文：the modifier display behavior
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #modifiers()
         */
        @Contract(value = "_, _, _, _ -> this", mutates = "this")
        Builder addModifier(Attribute attribute, AttributeModifier modifier, EquipmentSlotGroup equipmentSlotGroup, AttributeModifierDisplay display);
    }
}
