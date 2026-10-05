package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'has_record' 数据值可用来快速检查此唱片机是否装入了唱片.
 * <p>原文：
 * 'has_record' is a quick flag to check whether this jukebox has a record
 * inside it.
 */
public interface Jukebox extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'has_record' 数据值.
     * <p>原文：
     * Gets the value of the 'has_record' property.
     *
     * @return 此唱片机是否装入了唱片
     * <p>原文：the 'has_record' value
     */
    boolean hasRecord();

    /**
     * Sets the value of the 'has_record' property.
     *
     * @param hasRecord the new 'has_record' value
     */
    void setHasRecord(boolean hasRecord);
}
