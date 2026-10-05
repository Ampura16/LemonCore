package org.bukkit.block.sign;

import org.bukkit.material.Colorable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示告示牌的一面。
 * <p>原文：
 * Represents a side of a sign.
 */
public interface SignSide extends Colorable {
    // Paper start
    /**
     * Gets all the lines of text currently on the sign.
     *
     * @return List of components containing each line of text
     */
    @NotNull
    public java.util.List<net.kyori.adventure.text.Component> lines();

    /**
     * Gets the line of text at the specified index.
     * <p>
     * For example, getLine(0) will return the first line of text.
     *
     * @param index Line number to get the text from, starting at 0
     * @throws IndexOutOfBoundsException Thrown when the line does not exist
     * @return Text on the given line
     */
    @NotNull
    public net.kyori.adventure.text.Component line(int index) throws IndexOutOfBoundsException;

    /**
     * Sets the line of text at the specified index.
     * <p>
     * For example, setLine(0, "Line One") will set the first line of text to
     * "Line One".
     *
     * @param index Line number to set the text at, starting from 0
     * @param line New text to set at the specified index
     * @throws IndexOutOfBoundsException If the index is out of the range 0..3
     */
    public void line(int index, net.kyori.adventure.text.@NotNull Component line) throws IndexOutOfBoundsException;
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前在此告示牌一面上的所有文本行。
     * <p>原文：
     * Gets all the lines of text currently on this side of the sign.
     *
     * @return 包含所有文本行的字符串数组
     * <p>原文：Array of Strings containing each line of text
     * @deprecated in favour of {@link #lines()}
     */
    @NotNull
    @Deprecated // Paper
    public String[] getLines();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此告示牌一面上指定索引处的文本行。
     * <p>
     * 例如，getLine(0) 将返回第一行文本。
     * <p>原文：
     * Gets the line of text at the specified index on this side of the sign.
     * <p>
     * For example, getLine(0) will return the first line of text.
     *
     * @param index 获取哪一行的文本, 从0开始数
     * <p>原文：Line number to get the text from, starting at 0
     * @return 指定行的文本
     * <p>原文：Text on the given line
     * @throws IndexOutOfBoundsException 如果这一行不存在(范围0-3)
     * <p>原文：Thrown when the line does not exist
     * @deprecated in favour of {@link #line(int)}
     */
    @NotNull
    @Deprecated // Paper
    public String getLine(int index) throws IndexOutOfBoundsException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此告示牌一面上指定索引处的文本行。
     * <p>
     * 例如，setLine(0, "第一行") 将把第一行文本设置为 "第一行"。
     * <p>原文：
     * Sets the line of text at the specified index on this side of the sign.
     * <p>
     * For example, setLine(0, "Line One") will set the first line of text to
     * "Line One".
     *
     * @param index 设置哪一行的文本, 从0开始数
     * <p>原文：Line number to set the text at, starting from 0
     * @param line 指定行的新文本
     * <p>原文：New text to set at the specified index
     * @throws IndexOutOfBoundsException 如果index值不在[0-3]内
     * <p>原文：If the index is out of the range 0..3
     * @deprecated in favour of {@link #line(int, net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    public void setLine(int index, @NotNull String line) throws IndexOutOfBoundsException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此告示牌一面上的文本是否发光。
     * <p>原文：
     * Gets whether this side of the sign has glowing text.
     *
     * @return 此告示牌一面上的文本是否发光
     * <p>原文：if this side of the sign has glowing text
     */
    public boolean isGlowingText();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此告示牌一面上的文本是否发光。
     * <p>原文：
     * Sets whether this side of the sign has glowing text.
     *
     * @param glowing 此告示牌一面上的文本是否发光
     * <p>原文：if this side of the sign has glowing text
     */
    public void setGlowingText(boolean glowing);
}
