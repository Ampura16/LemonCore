package com.destroystokyo.paper.inventory.meta;

import org.bukkit.inventory.meta.ItemMeta;

public interface ArmorStandMeta extends ItemMeta {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取盔甲架生成时是否应不可见
     * <p>原文：
     * Gets whether the ArmorStand should be invisible when spawned
     *
     * @return 如果应不可见则为 true
     * <p>原文：true if this should be invisible
     */
    boolean isInvisible();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此盔甲架生成时是否应没有底座
     * <p>原文：
     * Gets whether this ArmorStand should have no base plate when spawned
     *
     * @return 如果将没有底座则为 true
     * <p>原文：true if it will not have a base plate
     */
    boolean hasNoBasePlate();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此盔甲架生成时是否应显示手臂
     * <p>原文：
     * Gets whether this ArmorStand should show arms when spawned
     *
     * @return 如果将显示手臂则为 true
     * <p>原文：true if it will show arms
     */
    boolean shouldShowArms();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此盔甲架生成时是否为小型
     * <p>原文：
     * Gets whether this ArmorStand will be small when spawned
     *
     * @return 如果将是小型的则为 true
     * <p>原文：true if it will be small
     */
    boolean isSmall();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此盔甲架生成时是否为标记。该标志的具体细节属于实现细节
     * <p>原文：
     * Gets whether this ArmorStand will be a marker when spawned
     * The exact details of this flag are an implementation detail
     *
     * @return 如果将成为标记则为 true
     * <p>原文：true if it will be a marker
     */
    boolean isMarker();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此盔甲架生成时应不可见
     * <p>原文：
     * Sets that this ArmorStand should be invisible when spawned
     *
     * @param invisible 如果设为不可见则为 true
     * <p>原文：true if set invisible
     */
    void setInvisible(boolean invisible);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此盔甲架生成时应没有底座
     * <p>原文：
     * Sets that this ArmorStand should have no base plate when spawned
     *
     * @param noBasePlate 如果没有底座则为 true
     * <p>原文：true if no base plate
     */
    void setNoBasePlate(boolean noBasePlate);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此盔甲架生成时应显示手臂
     * <p>原文：
     * Sets that this ArmorStand should show arms when spawned
     *
     * @param showArms 如果显示手臂则为 true
     * <p>原文：true if show arms
     */
    void setShowArms(boolean showArms);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此盔甲架生成时应为小型
     * <p>原文：
     * Sets that this ArmorStand should be small when spawned
     *
     * @param small 如果为小型则为 true
     * <p>原文：true if small
     */
    void setSmall(boolean small);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此盔甲架生成时应为标记。该标志的具体细节属于实现细节
     * <p>原文：
     * Sets that this ArmorStand should be a marker when spawned
     * The exact details of this flag are an implementation detail
     *
     * @param marker 如果为标记则为 true
     * <p>原文：true if a marker
     */
    void setMarker(boolean marker);
}
