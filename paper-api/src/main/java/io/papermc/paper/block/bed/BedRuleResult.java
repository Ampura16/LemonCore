package io.papermc.paper.block.bed;

import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示 {@link org.bukkit.event.player.PlayerBedEnterEvent} 和 {@link io.papermc.paper.event.player.PlayerBedFailEnterEvent} 期间床规则的结果。床规则负责决定是否允许玩家睡觉以及设置重生点
 * <p>原文：
 * Represents the result of a bed rule during {@link org.bukkit.event.player.PlayerBedEnterEvent}
 * and {@link io.papermc.paper.event.player.PlayerBedFailEnterEvent}. Bed rules are responsible
 * for allowing players to sleep and to set their spawn point
 */
@ApiStatus.Experimental
public sealed interface BedRuleResult permits BedRuleResultImpl {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 床规则允许时使用
     * <p>原文：
     * Used when the bed rule is allowed
     */
    BedRuleResult ALLOWED = new BedRuleResultImpl(true);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 床规则因光照过强而拒绝时使用。无雷暴的白天即属此情况
     * <p>原文：
     * Used when the bed rule is denied due to there
     * being too much light. This is the case during
     * daytime without thunderstorms
     */
    BedRuleResult TOO_MUCH_LIGHT = new BedRuleResultImpl(false);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 床规则被设为始终拒绝时使用
     * <p>原文：
     * Used when the bed rule is set to always be denied
     */
    BedRuleResult NEVER = new BedRuleResultImpl(false);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果此结果为成功，则返回 {@code true}
     * <p>原文：
     * Returns {@code true} if this result is a success
     *
     * @return 此结果是否为成功
     * <p>原文：whether this result is a success
     */
    boolean success();

}
