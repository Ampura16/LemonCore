package org.bukkit.block.data.type;

import org.bukkit.block.data.Waterlogged;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'can_summon' 表示幽匿尖啸体是否可以召唤监守者.
 * <p>
 * 'shrieking' 表示幽匿尖啸体是否正在尖啸.
 * <p>原文：
 * 'can_summon' indicates whether the sculk shrieker can summon the warden.
 * <p>
 * 'shrieking' indicated whether the sculk shrieker is shrieking or not.
 */
public interface SculkShrieker extends Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'can_summon' 属性的值.
     * <p>原文：
     * Gets the value of the 'can_summon' property.
     *
     * @return 'can_summon' 的值
     * <p>原文：the 'can_summon' value
     */
    boolean isCanSummon();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'can_summon' 属性的值.
     * <p>原文：
     * Sets the value of the 'can_summon' property.
     *
     * @param can_summon 新的 'can_summon' 值
     * <p>原文：the new 'can_summon' value
     */
    void setCanSummon(boolean can_summon);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'shrieking' 属性的值.
     * <p>原文：
     * Gets the value of the 'shrieking' property.
     *
     * @return 'shrieking' 的值
     * <p>原文：the 'shrieking' value
     */
    boolean isShrieking();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'shrieking' 属性的值.
     * <p>原文：
     * Sets the value of the 'shrieking' property.
     *
     * @param shrieking 新的 'shrieking' 值
     * <p>原文：the new 'shrieking' value
     */
    void setShrieking(boolean shrieking);
}
