package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.block.banner.Pattern;
import org.jetbrains.annotations.NotNull;

public interface BannerMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个旗帜的图案的列表.
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
     * 设置这个旗帜的图案.
     * <p>原文：
     * Sets the patterns used on this banner
     *
     * @param patterns 新的图案的列表
     * <p>原文：the new list of patterns
     */
    void setPatterns(@NotNull List<Pattern> patterns);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在现有的图案顶上新增图案.
     * <p>原文：
     * Adds a new pattern on top of the existing
     * patterns
     *
     * @param pattern 要新增的图案
     * <p>原文：the new pattern to add
     */
    void addPattern(@NotNull Pattern pattern);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回在指定索引处的图案.
     * <p>原文：
     * Returns the pattern at the specified index
     *
     * @param i 索引
     * <p>原文：the index
     * @return 图案
     * <p>原文：the pattern
     * @throws IndexOutOfBoundsException 当索引值不在<code>"[0, numberOfPatterns())"</code>区间内
     * <p>原文：when index is not in [0, numberOfPatterns()) range
     */
    @NotNull
    Pattern getPattern(int i);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定索引处移除图案.
     * <p>原文：
     * Removes the pattern at the specified index
     *
     * @param i 索引
     * <p>原文：the index
     * @return 移除的图案
     * <p>原文：the removed pattern
     * @throws IndexOutOfBoundsException 当索引值不在<code>"[0, numberOfPatterns())"</code>区间内
     * <p>原文：when index is not in [0, numberOfPatterns()) range
     */
    @NotNull
    Pattern removePattern(int i);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的索引处设置图案.
     * <p>原文：
     * Sets the pattern at the specified index
     *
     * @param i 索引
     * <p>原文：the index
     * @param pattern 新的图案
     * <p>原文：the new pattern
     * @throws IndexOutOfBoundsException 当索引值不在<code>"[0, numberOfPatterns())"</code>区间内
     * <p>原文：when index is not in [0, numberOfPatterns()) range
     */
    void setPattern(int i, @NotNull Pattern pattern);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回在这个旗帜的图案数量.
     * <p>原文：
     * Returns the number of patterns on this
     * banner
     *
     * @return 图案数量
     * <p>原文：the number of patterns
     */
    int numberOfPatterns();
}
