package org.bukkit.block;

import org.bukkit.entity.Entity;
import org.bukkit.event.block.BellRingEvent;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表钟.
 * <p>原文：
 * Represents a captured state of Bell.
 */
public interface Bell extends TileState {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 敲响此钟. 这将调用一个 {@link BellRingEvent}.
     * <p>原文：
     * Ring this bell. This will call a {@link BellRingEvent}.
     *
     * @param entity 敲响钟的实体
     * <p>原文：the entity ringing the bell
     * @param direction 钟被敲响的方向, 或null表示按钟面对的方向敲响
     * <p>原文：the direction from which the bell was rung or null to
     * ring in the direction that the bell is facing
     * @return 如果成功敲响返回true, 如果事件被取消返回false
     * <p>原文：true if rung successfully, false if the event was cancelled
     */
    public boolean ring(@Nullable Entity entity, @Nullable BlockFace direction);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 按钟面对的方向敲响此钟. 这将调用一个 {@link BellRingEvent}.
     * <p>原文：
     * Ring this bell in the direction that the bell is facing. This will call a
     * {@link BellRingEvent}.
     *
     * @param entity 敲响钟的实体
     * <p>原文：the entity ringing the bell
     * @return 如果成功敲响返回true, 如果事件被取消返回false
     * <p>原文：true if rung successfully, false if the event was cancelled
     */
    default boolean ring(@Nullable Entity entity) {
        return this.ring(entity, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 敲响此钟. 这将调用一个 {@link BellRingEvent}.
     * <p>原文：
     * Ring this bell. This will call a {@link BellRingEvent}.
     *
     * @param direction 钟被敲响的方向, 或null表示按钟面对的方向敲响
     * <p>原文：the direction from which the bell was rung or null to
     * ring in the direction that the bell is facing
     * @return 如果成功敲响返回true, 如果事件被取消返回false
     * <p>原文：true if rung successfully, false if the event was cancelled
     */
    default boolean ring(@Nullable BlockFace direction) {
        return this.ring(null, direction);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 按钟面对的方向敲响此钟. 这将调用一个 {@link BellRingEvent}.
     * <p>原文：
     * Ring this bell in the direction that the bell is facing. This will call a
     * {@link BellRingEvent}.
     *
     * @return 如果成功敲响返回true, 如果事件被取消返回false
     * <p>原文：true if rung successfully, false if the event was cancelled
     */
    default boolean ring() {
        return this.ring(null, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此钟是否正在摇晃. 如果钟最近被敲响, 则认为它正在摇晃.
     * <p>原文：
     * Check whether or not this bell is shaking. A bell is considered to be
     * shaking if it was recently rung.
     * <p>
     * A bell will typically shake for 50 ticks.
     *
     * @return 如果正在摇晃返回true, 否则返回false
     * <p>原文：true if shaking, false otherwise
     */
    public boolean isShaking();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此钟开始摇晃以来的游戏刻数, 如果钟当前未摇晃则返回0.
     * <p>原文：
     * Get the amount of ticks since this bell has been shaking, or 0 if the
     * bell is not currently shaking.
     * <p>
     * A bell will typically shake for 50 ticks.
     *
     * @return 自钟被敲响以来的时间(以游戏刻计), 如果未摇晃则返回0
     * <p>原文：the time in ticks since the bell was rung, or 0 if not shaking
     */
    public int getShakingTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此钟是否正在共鸣. 如果在 {@link #isShaking() 摇晃时}, 在区域内检测到袭击者并准备向附近玩家高亮显示, 则认为钟正在共鸣.
     * <p>原文：
     * Check whether or not this bell is resonating. A bell is considered to be
     * resonating if {@link #isShaking() while shaking}, raiders were detected
     * in the area and are ready to be highlighted to nearby players.
     * <p>
     * A bell will typically resonate for 40 ticks.
     *
     * @return 如果正在共鸣返回true, 否则返回false
     * <p>原文：true if resonating, false otherwise
     */
    public boolean isResonating();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此钟开始共鸣以来的游戏刻数, 如果钟当前未共鸣则返回0.
     * <p>原文：
     * Get the amount of ticks since this bell has been resonating, or 0 if the
     * bell is not currently resonating.
     * <p>
     * A bell will typically resonate for 40 ticks.
     *
     * @return 自钟开始共鸣以来的时间(以游戏刻计), 如果未共鸣则返回0
     * <p>原文：the time in ticks since the bell has been resonating, or 0 if not
     * resonating
     */
    public int getResonatingTicks();
}
