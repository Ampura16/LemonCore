package io.papermc.paper.world.damagesource;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示实体的战斗追踪器
 * <p>原文：
 * Represents entity's combat tracker
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface CombatTracker {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此战斗追踪器所属的实体。
     * <p>原文：
     * Gets the entity behind this combat tracker.
     *
     * @return 此战斗追踪器所属的实体
     * <p>原文：the entity behind this combat tracker
     */
    LivingEntity getEntity();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取已记录的战斗记录列表。
     * <p>
     * 返回的列表是副本，因此对其内容的任何修改都不会影响此实体的战斗历史。
     * <p>原文：
     * Gets the list of recorded combat entries.
     * <p>
     * The returned list is a copy, so any modifications
     * to its contents won't affect this entity's
     * combat history.
     *
     * @return 战斗条目列表
     * <p>原文：the list of combat entries
     * @see #setCombatEntries(List)
     */
    List<CombatEntry> getCombatEntries();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置实体的战斗历史。
     * <p>
     * 注意，覆盖实体的战斗历史不会影响实体当前或新的战斗状态。如果希望新历史影响战斗状态，请重置当前战斗状态并注册新的战斗记录。
     * <p>原文：
     * Sets the entity's combat history.
     * <p>
     * Note that overriding the entity's combat history won't
     * affect the entity's current or new combat state.
     * Reset the current combat state and register new combat entries instead
     * if you want the new history to affect the combat state.
     *
     * @param combatEntries 战斗条目
     * <p>原文：combat entries
     * @see #resetCombatState()
     * @see #addCombatEntry(CombatEntry)
     */
    void setCombatEntries(List<CombatEntry> combatEntries);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 计算最主要的摔落伤害记录。
     * <p>原文：
     * Calculates the most significant fall damage entry.
     *
     * @return 最重要的摔落伤害条目
     * <p>原文：the most significant fall damage entry
     */
    @Nullable CombatEntry computeMostSignificantFall();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查实体是否处于战斗中，即自战斗追踪开始以来是否受到过实体伤害。
     * <p>原文：
     * Checks whether the entity is in combat,
     * i.e. has taken damage from an entity
     * since the combat tracking has begun.
     *
     * @return 实体是否处于战斗中
     * <p>原文：whether the entity is in combat
     */
    boolean isInCombat();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查实体是否已开始记录伤害，即其战斗追踪是否处于活动状态。
     * <p>原文：
     * Checks whether the entity has started recording damage,
     * i.e. its combat tracking is active.
     *
     * @return 实体是否已开始记录伤害
     * <p>原文：whether the entity has started recording damage
     */
    boolean isTakingDamage();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取上一次或当前的战斗持续时间。
     * <p>原文：
     * Gets the last or current combat duration.
     *
     * @return 战斗持续时间
     * <p>原文：the combat duration
     * @see #isInCombat()
     */
    int getCombatDuration();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 向战斗记录池添加一条新记录，并更新实体的战斗状态。
     * <p>原文：
     * Adds a new entry the pool of combat entries,
     * updating the entity's combat state.
     *
     * @param combatEntry 战斗条目
     * <p>原文：combat entry
     */
    void addCombatEntry(CombatEntry combatEntry);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 根据当前战斗历史构造死亡消息。
     * <p>原文：
     * Constructs a death message based on the current combat history.
     *
     * @return 死亡消息
     * <p>原文：a death message
     */
    Component getDeathMessage();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 重置实体的战斗状态，清除战斗历史。
     * <p>原文：
     * Resets entity's combat state, clearing combat history.
     */
    void resetCombatState();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 根据实体当前位置计算摔落位置类型。
     * <p>原文：
     * Calculates the fall location type from the current entity's location.
     *
     * @return 摔落位置类型
     * <p>原文：the fall location type
     */
    @Nullable FallLocationType calculateFallLocationType();
}
