package io.papermc.paper.scoreboard.numbers;

import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.StyleBuilderApplicable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 描述对计分板分数应用自定义格式的计分板数字格式。
 * <p>原文：
 * Describes a scoreboard number format that applies custom formatting to scoreboard scores.
 */
@NullMarked
public interface NumberFormat {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建空白计分板数字格式，完全移除分数数字。
     * <p>原文：
     * Creates a blank scoreboard number format that removes the score number entirely.
     *
     * @return 空白数字格式
     * <p>原文：a blank number format
     */
    static NumberFormat blank() {
        return BlankFormatImpl.INSTANCE;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取无样式的数字格式。
     * <p>原文：
     * Gets an un-styled number format.
     *
     * @return 无样式的数字格式
     * <p>原文：an un-styled number format
     */
    static StyledFormat noStyle() {
        return StyledFormatImpl.NO_STYLE;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建对分数数字应用自定义格式的计分板数字格式。
     * <p>原文：
     * Creates a scoreboard number format that applies a custom formatting to the score number.
     *
     * @param style 应用到数字上的样式
     * <p>原文：the style to apply on the number
     * @return 带样式的数字格式
     * <p>原文：a styled number format
     */
    static StyledFormat styled(final Style style) {
        return new StyledFormatImpl(style);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建对分数数字应用自定义格式的计分板数字格式。
     * <p>原文：
     * Creates a scoreboard number format that applies a custom formatting to the score number.
     *
     * @param styleBuilderApplicables 应用到数字上的样式
     * <p>原文：the style to apply on the number
     * @return 带样式的数字格式
     * <p>原文：a styled number format
     */
    static StyledFormat styled(final StyleBuilderApplicable... styleBuilderApplicables) {
        return styled(Style.style(styleBuilderApplicables));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建用聊天组件替换分数数字的计分板数字格式。
     * <p>原文：
     * Creates a scoreboard number format that replaces the score number with a chat component.
     *
     * @param component 用于替换数字的组件
     * <p>原文：the component to replace the number with
     * @return 固定数字格式
     * <p>原文：a fixed number format
     */
    static FixedFormat fixed(final ComponentLike component) {
        return new FixedFormatImpl(component.asComponent());
    }
}
