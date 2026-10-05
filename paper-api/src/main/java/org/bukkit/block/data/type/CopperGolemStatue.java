package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'copper_golem_pose' 表示铜傀儡雕像的姿态.
 * <p>原文：
 * 'copper_golem_pose' indicates the pose the statue stands.
 */
@NullMarked
public interface CopperGolemStatue extends BlockData, Directional, Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 获取 'copper_golem_pose' 属性的值.
     * <p>原文：
     * Gets the value of the 'copper_golem_pose' property.
     *
     * @return the 'copper_golem_pose' value
     */
    Pose getCopperGolemPose();

    /**
     * Sets the value of the 'copper_golem_pose' property.
     *
     * @param pose the new 'copper_golem_pose' value
     */
    void setCopperGolemPose(Pose pose);

    enum Pose {
        STANDING,
        SITTING,
        RUNNING,
        STAR
    }
}
