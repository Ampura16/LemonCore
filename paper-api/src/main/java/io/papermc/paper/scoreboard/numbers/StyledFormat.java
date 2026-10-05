package io.papermc.paper.scoreboard.numbers;

import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.StyleBuilderApplicable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 对分数数字应用自定义格式的计分板数字格式。
 * <p>原文：
 * A scoreboard number format that applies a custom formatting to the score number.
 */
@NullMarked
public interface StyledFormat extends NumberFormat, StyleBuilderApplicable {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 应用于分数中数字的样式
     * <p>原文：
     * The style that is being applied to the number in the score
     *
     * @return 要应用的样式
     * <p>原文：the style to apply
     */
    Style style();

}
