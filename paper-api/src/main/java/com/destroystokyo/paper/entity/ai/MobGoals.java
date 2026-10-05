package com.destroystokyo.paper.entity.ai;

import java.util.Collection;
import org.bukkit.entity.Mob;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示生物“大脑”的一部分。它追踪所有任务（无论是否正在运行），并允许添加和移除目标
 * <p>原文：
 * Represents a part of the "brain" of a mob. It tracks all tasks (running or not), allows adding and removing goals
 */
@NullMarked
public interface MobGoals {

    <T extends Mob> void addGoal(T mob, int priority, Goal<T> goal);

    <T extends Mob> void removeGoal(T mob, Goal<T> goal);

    <T extends Mob> void removeAllGoals(T mob);

    <T extends Mob> void removeAllGoals(T mob, GoalType type);

    <T extends Mob> void removeGoal(T mob, GoalKey<T> key);

    <T extends Mob> boolean hasGoal(T mob, GoalKey<T> key);

    <T extends Mob> @Nullable Goal<T> getGoal(T mob, GoalKey<T> key);

    <T extends Mob> Collection<Goal<T>> getGoals(T mob, GoalKey<T> key);

    <T extends Mob> Collection<Goal<T>> getAllGoals(T mob);

    <T extends Mob> Collection<Goal<T>> getAllGoals(T mob, GoalType type);

    <T extends Mob> Collection<Goal<T>> getAllGoalsWithout(T mob, GoalType type);

    <T extends Mob> Collection<Goal<T>> getRunningGoals(T mob);

    <T extends Mob> Collection<Goal<T>> getRunningGoals(T mob, GoalType type);

    <T extends Mob> Collection<Goal<T>> getRunningGoalsWithout(T mob, GoalType type);
}
