package com.destroystokyo.paper.entity.ai;

import java.util.EnumSet;
import org.bukkit.entity.Mob;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示实体的 AI 目标
 * <p>原文：
 * Represents an AI goal of an entity
 */
@NullMarked
public interface Goal<T extends Mob> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此目标是否应被激活
     * <p>原文：
     * Checks if this goal should be activated
     *
     * @return 此目标是否应被激活
     * <p>原文：if this goal should be activated
     */
    boolean shouldActivate();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此目标是否应保持激活，默认为 {@link Goal#shouldActivate()}
     * <p>原文：
     * Checks if this goal should stay active, defaults to {@link Goal#shouldActivate()}
     *
     * @return 此目标是否应保持激活
     * <p>原文：if this goal should stay active
     */
    default boolean shouldStayActive() {
        return this.shouldActivate();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在此目标被激活时调用
     * <p>原文：
     * Called when this goal gets activated
     */
    default void start() {
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在此目标被停止时调用
     * <p>原文：
     * Called when this goal gets stopped
     */
    default void stop() {
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 目标激活期间每刻调用
     * <p>原文：
     * Called each tick the goal is activated
     */
    default void tick() {
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 标识该类目标的唯一键。插件应使用自己的命名空间，而不是 minecraft 命名空间。此外，该键还指定了此目标可应用于哪些生物
     * <p>原文：
     * A unique key that identifies this type of goal. Plugins should use their own namespace, not the minecraft
     * namespace. Additionally, this key also specifies to what mobs this goal can be applied to
     *
     * @return 目标键
     * <p>原文：the goal key
     */
    GoalKey<T> getKey();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此目标的所有适用标志列表。<br>
     * <p>
     * 该方法仅在构造时调用。
     * <p>原文：
     * Returns a list of all applicable flags for this goal.<br>
     * <p>
     * This method is only called on construction.
     *
     * @return 子类型。
     * <p>原文：the subtypes.
     */
    EnumSet<GoalType> getTypes();
}
