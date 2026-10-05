package org.bukkit.scoreboard;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 计分板部分准则名称的常量. 准则可触发计分项数据的修改 (生命、击杀信息、死亡次数等).
 * <p>原文：
 * Criteria names which trigger an objective to be modified by actions in-game
 *
 * @deprecated 应使用 {@link Criteria} 内定义的常量
 * <p>原文：use the constants declared in {@link Criteria} instead
 */
@Deprecated(since = "1.19.2", forRemoval = true)
public final class Criterias {

    public static final String HEALTH = "health";
    public static final String PLAYER_KILLS = "playerKillCount";
    public static final String TOTAL_KILLS = "totalKillCount";
    public static final String DEATHS = "deathCount";

    private Criterias() {}
}
