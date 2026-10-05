package org.bukkit.entity;

import org.bukkit.Color;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示文本显示实体。
 * <p>原文：
 * Represents a text display entity.
 */
public interface TextDisplay extends Display {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取显示的文本。
     * <p>原文：
     * Gets the displayed text.
     *
     * @return 显示的文本。
     * <p>
     * 原文：Gets the displayed text.
     * <p>原文：the displayed text.
     * @deprecated in favour of {@link #text()}
     */
    @Nullable
    @Deprecated // Paper
    String getText();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置显示的文本。
     * <p>原文：
     * Sets the displayed text.
     *
     * @param text 新文本
     * <p>
     * 原文：Sets the displayed text.
     * <p>原文：the new text
     * @deprecated in favour of {@link #text(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setText(@Nullable String text);

    /**
     * Gets the displayed text.
     *
     * @return the displayed text
     */
    net.kyori.adventure.text.@NotNull Component text();

    /**
     * Sets the displayed text.
     *
     * @param text the new text
     */
    void text(net.kyori.adventure.text.@Nullable Component text);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取换行前的最大行宽。
     * <p>原文：
     * Gets the maximum line width before wrapping.
     *
     * @return 行宽
     * <p>
     * 原文：Gets the maximum line width before wrapping.
     * <p>原文：the line width
     */
    int getLineWidth();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置换行前的最大行宽。
     * <p>原文：
     * Sets the maximum line width before wrapping.
     *
     * @param width 新行宽
     * <p>
     * 原文：Sets the maximum line width before wrapping.
     * <p>原文：new line width
     */
    void setLineWidth(int width);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取文本背景颜色。
     * <p>原文：
     * Gets the text background color.
     *
     * @return 背景颜色
     * <p>
     * 原文：Gets the text background color.
     * <p>原文：the background color
     */
    @Nullable
    Color getBackgroundColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置文本背景颜色。
     * <p>原文：
     * Sets the text background color.
     *
     * @param color 新背景颜色
     * <p>
     * 原文：Sets the text background color.
     * <p>原文：new background color
     */
    void setBackgroundColor(@Nullable Color color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取文本不透明度。
     * <p>原文：
     * Gets the text opacity.
     *
     * @return 不透明度，如果未设置则返回-1
     * <p>
     * 原文：Gets the text opacity.
     * <p>原文：opacity or -1 if not set
     */
    byte getTextOpacity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置文本不透明度。
     * <p>原文：
     * Sets the text opacity.
     *
     * @param opacity 新不透明度，如果为默认值则为-1
     * <p>
     * 原文：Sets the text opacity.
     * <p>原文：new opacity or -1 if default
     */
    void setTextOpacity(byte opacity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取文本是否有阴影。
     * <p>原文：
     * Gets if the text is shadowed.
     *
     * @return 阴影状态
     * <p>
     * 原文：Gets if the text is shadowed.
     * <p>原文：shadow status
     */
    boolean isShadowed();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置文本是否有阴影。
     * <p>原文：
     * Sets if the text is shadowed.
     *
     * @param shadow 是否有阴影
     * <p>
     * 原文：Sets if the text is shadowed.
     * <p>原文：if shadowed
     */
    void setShadowed(boolean shadow);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取文本是否透明。
     * <p>原文：
     * Gets if the text is seen through.
     *
     * @return 透明状态
     * <p>
     * 原文：Gets if the text is see through.
     * <p>原文：see through status
     */
    boolean isSeeThrough();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置文本是否透明。
     * <p>原文：
     * Sets if the text is seen through.
     *
     * @param seeThrough 是否透明
     * <p>
     * 原文：Sets if the text is see through.
     * <p>原文：if see through
     */
    void setSeeThrough(boolean seeThrough);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取文本是否有默认背景。
     * <p>原文：
     * Gets if the text has its default background.
     *
     * @return 默认背景
     * <p>
     * 原文：Gets if the text has its default background.
     * <p>原文：default background
     */
    boolean isDefaultBackground();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置文本是否有默认背景。
     * <p>原文：
     * Sets if the text has its default background.
     *
     * @param defaultBackground 是否为默认
     * <p>
     * 原文：Sets if the text has its default background.
     * <p>原文：if default
     */
    void setDefaultBackground(boolean defaultBackground);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此显示的文本对齐方式。
     * <p>原文：
     * Gets the text alignment for this display.
     *
     * @return 文本对齐方式
     * <p>
     * 原文：Gets the text alignment for this display.
     * <p>原文：text alignment
     */
    @NotNull
    TextAlignment getAlignment();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此显示的文本对齐方式。
     * <p>原文：
     * Sets the text alignment for this display.
     *
     * @param alignment 新对齐方式
     * <p>
     * 原文：Sets the text alignment for this display.
     * <p>原文：new alignment
     */
    void setAlignment(@NotNull TextAlignment alignment);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示此显示可能的文本对齐方式。
     * <p>原文：
     * Represents possible text alignments for this display.
     */
    public enum TextAlignment {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 居中对齐文本（默认）。
         * <p>原文：
         * Center aligned text (default).
         */
        CENTER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 左对齐文本。
         * <p>原文：
         * Left aligned text.
         */
        LEFT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 右对齐文本。
         * <p>原文：
         * Right aligned text.
         */
        RIGHT;
    }
}
