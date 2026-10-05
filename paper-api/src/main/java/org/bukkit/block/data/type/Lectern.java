package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'has_book' 可用于快速检测讲台内是否有书.
 * <p>原文：
 * 'has_book' is a quick flag to check whether this lectern has a book inside
 * it.
 */
public interface Lectern extends Directional, Powerable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'has_book' 属性的值.
     * <p>原文：
     * Gets the value of the 'has_book' property.
     *
     * @return 属性 'has_book' 的值
     * <p>原文：the 'has_book' value
     */
    boolean hasBook();

    /**
     * Sets the value of the 'has_book' property.
     *
     * @param hasBook the new 'has_book' value
     */
    void setHasBook(boolean hasBook);
}
