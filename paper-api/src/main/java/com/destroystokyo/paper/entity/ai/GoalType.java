package com.destroystokyo.paper.entity.ai;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示目标的子类型。Minecraft 在需要时用它来禁用某些类型的目标。
 * <p>原文：
 * Represents the subtype of a goal. Used by minecraft to disable certain types of goals if needed.
 */
public enum GoalType {

    MOVE,
    LOOK,
    JUMP,
    TARGET,
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于映射属于行为目标但未设置类型的原版目标……
     * <p>原文：
     * Used to map vanilla goals, that are a behavior goal but don't have a type set...
     */
    UNKNOWN_BEHAVIOR,

}
