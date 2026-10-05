package com.destroystokyo.paper.entity.villager;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 与 {@link org.bukkit.entity.Villager Villager} 相关的声望类型。
 * <p>
 * 除 {@link #MAJOR_POSITIVE} 以外的所有类型都会共享给其他村民。
 * <p>原文：
 * A type of reputation gained with a {@link org.bukkit.entity.Villager Villager}.
 * <p>
 * All types but {@link #MAJOR_POSITIVE} are shared to other villagers.
 */
public enum ReputationType {
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 具有重大负面影响的流言。仅能通过杀死附近的村民获得。
     * <p>原文：
     * A gossip with a majorly negative effect. This is only gained through killing a nearby
     * villager.
     */
    MAJOR_NEGATIVE,

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 具有轻微负面影响的流言。仅能通过伤害村民获得。
     * <p>原文：
     * A gossip with a minor negative effect. This is only gained through damaging a villager.
     */
    MINOR_NEGATIVE,

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 具有轻微正面影响的流言。仅能通过治愈僵尸村民获得。
     * <p>原文：
     * A gossip with a minor positive effect. This is only gained through curing a zombie
     * villager.
     */
    MINOR_POSITIVE,

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 具有重大正面影响的流言。仅能通过治愈僵尸村民获得。
     * <p>原文：
     * A gossip with a major positive effect. This is only gained through curing a zombie
     * villager.
     */
    MAJOR_POSITIVE,

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 具有轻微正面影响的流言。仅能通过与村民交易获得。
     * <p>原文：
     * A gossip with a minor positive effect. This is only gained through trading with a villager.
     */
    TRADING,
}
