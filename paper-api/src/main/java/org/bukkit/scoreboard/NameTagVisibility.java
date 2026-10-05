package org.bukkit.scoreboard;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 *
 *
 * @deprecated 被 {@link Team.OptionStatus} 取代
 * <p>原文：replaced by {@link Team.OptionStatus}
 */
@Deprecated(since = "1.9")
public enum NameTagVisibility {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 一直显示玩家名称.
     * <p>原文：
     * Always show the player's nametag.
     */
    ALWAYS,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 不显示玩家名称.
     * <p>原文：
     * Never show the player's nametag.
     */
    NEVER,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家名仅显示给同队队友.
     * <p>原文：
     * Show the player's nametag only to his own team members.
     */
    HIDE_FOR_OTHER_TEAMS,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家名仅显示给其他队伍的成员.
     * <p>原文：
     * Show the player's nametag only to members of other teams.
     */
    HIDE_FOR_OWN_TEAM;
}
