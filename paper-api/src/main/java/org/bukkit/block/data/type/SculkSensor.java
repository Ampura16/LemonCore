package org.bukkit.block.data.type;

import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'sculk_sensor_phase' 表示传感器当前的运行阶段.
 * <p>原文：
 * 'sculk_sensor_phase' indicates the current operational phase of the sensor.
 */
public interface SculkSensor extends AnaloguePowerable, Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'sculk_sensor_phase' 属性的值.
     * <p>原文：
     * Gets the value of the 'sculk_sensor_phase' property.
     *
     * @return 'sculk_sensor_phase' 的值
     * <p>原文：the 'sculk_sensor_phase' value
     * @deprecated bad name, use {@link #getSculkSensorPhase()}
     */
    @NotNull
    @Deprecated
    default Phase getPhase() {
        return this.getSculkSensorPhase();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'sculk_sensor_phase' 属性的值.
     * <p>原文：
     * Sets the value of the 'sculk_sensor_phase' property.
     *
     * @param phase 新的 'sculk_sensor_phase' 值
     * <p>原文：the new 'sculk_sensor_phase' value
     * @deprecated bad name, use {@link #setSculkSensorPhase(Phase)}
     */
    @Deprecated
    default void setPhase(@NotNull Phase phase) {
        this.setSculkSensorPhase(phase);
    }

    /**
     * Gets the value of the 'sculk_sensor_phase' property.
     *
     * @return the 'sculk_sensor_phase' value
     */
    @NotNull
    Phase getSculkSensorPhase();

    /**
     * Sets the value of the 'sculk_sensor_phase' property.
     *
     * @param phase the new 'sculk_sensor_phase' value
     */
    void setSculkSensorPhase(@NotNull Phase phase);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 传感器的阶段.
     * <p>原文：
     * The Phase of the sensor.
     */
    public enum Phase {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 传感器未激活.
         * <p>原文：
         * The sensor is inactive.
         */
        INACTIVE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 传感器已激活.
         * <p>原文：
         * The sensor is active.
         */
        ACTIVE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 传感器正在冷却.
         * <p>原文：
         * The sensor is cooling down.
         */
        COOLDOWN;
    }
}
