package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'trial_spawner_state' 表示试炼刷怪笼当前的运行阶段.
 * <br>
 * 'ominous' 表示方块是否具有不祥效果.
 * <p>原文：
 * 'trial_spawner_state' indicates the current operational phase of the spawner.
 * <br>
 * 'ominous' indicates if the block has ominous effects.
 */
public interface TrialSpawner extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'trial_spawner_state' 属性的值.
     * <p>原文：
     * Gets the value of the 'trial_spawner_state' property.
     *
     * @return 'trial_spawner_state' 的值
     * <p>原文：the 'trial_spawner_state' value
     */
    @NotNull
    State getTrialSpawnerState();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'trial_spawner_state' 属性的值.
     * <p>原文：
     * Sets the value of the 'trial_spawner_state' property.
     *
     * @param state 新的 'trial_spawner_state' 值
     * <p>原文：the new 'trial_spawner_state' value
     */
    void setTrialSpawnerState(@NotNull State state);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'ominous' 属性的值.
     * <p>原文：
     * Gets the value of the 'ominous' property.
     *
     * @return 'ominous' 的值
     * <p>原文：the 'ominous' value
     */
    boolean isOminous();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'ominous' 属性的值.
     * <p>原文：
     * Sets the value of the 'ominous' property.
     *
     * @param ominous 新的 'ominous' 值
     * <p>原文：the new 'ominous' value
     */
    void setOminous(boolean ominous);

    public enum State {

        INACTIVE,
        WAITING_FOR_PLAYERS,
        ACTIVE,
        WAITING_FOR_REWARD_EJECTION,
        EJECTING_REWARD,
        COOLDOWN;
    }
}
