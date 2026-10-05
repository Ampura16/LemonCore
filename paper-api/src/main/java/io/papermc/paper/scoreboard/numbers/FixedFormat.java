package io.papermc.paper.scoreboard.numbers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 用聊天组件替换分数数字的计分板数字格式。
 * <p>原文：
 * A scoreboard number format that replaces the score number with a chat component.
 */
@NullMarked
public interface FixedFormat extends NumberFormat, ComponentLike {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 分数处显示的组件，代替数字
     * <p>原文：
     * The component shown instead of the number for a score
     *
     * @return 聊天组件
     * <p>原文：the chat component
     */
    Component component();

}
