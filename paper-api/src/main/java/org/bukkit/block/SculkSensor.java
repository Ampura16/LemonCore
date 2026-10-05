package org.bukkit.block;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表幽匿感测体的捕获状态.
 * <p>原文：
 * Represents a captured state of a sculk sensor
 */
public interface SculkSensor extends TileState {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此感测体的最后振动频率.
     * <p>
     * 感测体检测到的不同活动会产生不同的频率,
     * 并决定连接的比较器的输出.
     * <p>原文：
     * Gets the last vibration frequency of this sensor.
     *
     * Different activities detected by the sensor will produce different
     * frequencies and dictate the output of connected comparators.
     *
     * @return 0-15之间的频率值
     * <p>原文：frequency between 0-15.
     */
    int getLastVibrationFrequency();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此感测体的最后振动频率.
     * <p>
     * 感测体检测到的不同活动会产生不同的频率,
     * 并决定连接的比较器的输出.
     * <p>原文：
     * Sets the last vibration frequency of this sensor.
     *
     * Different activities detected by the sensor will produce different
     * frequencies and dictate the output of connected comparators.
     *
     * @param lastVibrationFrequency 0-15之间的频率值
     * <p>原文：frequency between 0-15.
     */
    void setLastVibrationFrequency(int lastVibrationFrequency);
    // Paper start
    /**
     * Gets the range this sensor listens to events at.
     *
     * @return the range (defaults to 8)
     */
    int getListenerRange();

    /**
     * Sets the range this sensor will listen to events from.
     *
     * @param range the range (must be greater than 0)
     */
    void setListenerRange(int range);
    // Paper end
}
