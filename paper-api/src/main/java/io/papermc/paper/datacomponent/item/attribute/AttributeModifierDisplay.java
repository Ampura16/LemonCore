package io.papermc.paper.datacomponent.item.attribute;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 专用属性条目的显示行为。
 * <p>原文：
 * The display behavior for a dedicated attribute entry.
 *
 * @see io.papermc.paper.datacomponent.DataComponentTypes#ATTRIBUTE_MODIFIERS
 * @see io.papermc.paper.datacomponent.item.ItemAttributeModifiers#itemAttributes()
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface AttributeModifierDisplay {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将属性修饰符显示文本的任何覆盖重置为默认行为，即显示数值。
     * <p>原文：
     * Reset any override of the text displayed by the attribute modifier
     * to its default behavior displaying the statistics.
     *
     * @return 新的显示行为实例
     * <p>原文：the new display behavior instance
     */
    @Contract(value = "-> new", pure = true)
    static Default reset() {
        return AttributeModifierDisplayBridge.bridge().reset();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 隐藏属性修饰符显示的数值。
     * <p>原文：
     * Hides the statistics displayed by the attribute modifier.
     *
     * @return 新的显示行为实例
     * <p>原文：the new display behavior instance
     */
    @Contract(value = "-> new", pure = true)
    static Hidden hidden() {
        return AttributeModifierDisplayBridge.bridge().hidden();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将属性修饰符显示的数值覆盖为任意文本。
     * <p>原文：
     * Override the statistics displayed by the attribute modifier
     * to an arbitrary text.
     *
     * @param text 被覆盖的文本
     * <p>原文：the overridden text
     * @return 新的显示行为实例
     * <p>原文：the new display behavior instance
     */
    @Contract(value = "_ -> new", pure = true)
    static OverrideText override(final ComponentLike text) {
        return AttributeModifierDisplayBridge.bridge().override(text);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 属性修饰符的隐藏数值显示。
     * <p>原文：
     * Hidden statistics display for the attribute modifier.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Hidden extends AttributeModifierDisplay {
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 属性修饰符的默认显示，显示其效果的数值。
     * <p>原文：
     * Default display for the attribute modifier, showing
     * the statistic of its effect.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Default extends AttributeModifierDisplay {
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 指定要显示的覆盖文本，以代替属性修饰符的默认行为。
     * <p>原文：
     * Specifies an overridden text to show instead of
     * the default behavior for the attribute modifier.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface OverrideText extends AttributeModifierDisplay {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 被覆盖的文本
         * <p>原文：
         * Overridden text
         *
         * @return 被覆盖的文本
         * <p>原文：the overridden text
         */
        Component text();
    }
}
