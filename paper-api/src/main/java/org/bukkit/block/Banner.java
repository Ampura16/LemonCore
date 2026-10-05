package org.bukkit.block;

import java.util.List;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表旗帜.
 * <p>原文：
 * Represents a captured state of a banner.
 */
public interface Banner extends TileState, org.bukkit.Nameable { // Paper - Nameable API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个旗帜的底色.
     * <p>原文：
     * Returns the base color for this banner
     *
     * @return 底色
     * <p>原文：the base color
     */
    @NotNull
    DyeColor getBaseColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个旗帜的底色.
     * <b>仅对盾牌伪旗帜有效, 否则取决于方块的类型</b>.
     * <p>原文：
     * Sets the base color for this banner.
     * <b>Only valid for shield pseudo banners, otherwise base depends on block
     * type</b>
     *
     * @param color 底色
     * <p>原文：the base color
     */
    void setBaseColor(@NotNull DyeColor color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个旗帜的所有图案.
     * <p>原文：
     * Returns a list of patterns on this banner
     *
     * @return 图案列表
     * <p>原文：the patterns
     */
    @NotNull
    List<Pattern> getPatterns();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个旗帜使用的图案.
     * <p>原文：
     * Sets the patterns used on this banner
     *
     * @param patterns 新的图案列表
     * <p>原文：the new list of patterns
     */
    void setPatterns(@NotNull List<Pattern> patterns);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在现有的图案上添加一个图案.
     * <p>原文：
     * Adds a new pattern on top of the existing
     * patterns
     *
     * @param pattern 要添加的新的图案
     * <p>原文：the new pattern to add
     */
    void addPattern(@NotNull Pattern pattern);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回指定索引处的图案.
     * <p>原文：
     * Returns the pattern at the specified index
     *
     * @param i 索引
     * <p>原文：the index
     * @return 图案
     * <p>原文：the pattern
     */
    @NotNull
    Pattern getPattern(int i);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除指定索引处的图案.
     * <p>原文：
     * Removes the pattern at the specified index
     *
     * @param i 索引
     * <p>原文：the index
     * @return 被移除的图案
     * <p>原文：the removed pattern
     */
    @NotNull
    Pattern removePattern(int i);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定索引处的图案.
     * <p>原文：
     * Sets the pattern at the specified index
     *
     * @param i 索引
     * <p>原文：the index
     * @param pattern 新的图案
     * <p>原文：the new pattern
     */
    void setPattern(int i, @NotNull Pattern pattern);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回在这个旗帜上的图案的数量.
     * <p>原文：
     * Returns the number of patterns on this
     * banner
     *
     * @return 图案数量
     * <p>原文：the number of patterns
     */
    int numberOfPatterns();
}
