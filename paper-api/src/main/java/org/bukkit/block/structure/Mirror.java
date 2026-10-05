package org.bukkit.block.structure;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表{@link org.bukkit.block.Structure 结构方块}加载时如何产生镜像.
 * <p>原文：
 * Represents how a {@link org.bukkit.block.Structure} can be mirrored upon
 * being loaded.
 */
public enum Mirror {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 无镜像.
     * <br>
     * X轴正半轴到Z轴正半轴.
     * <p>原文：
     * No mirroring.
     * <br>
     * Positive X to Positive Z
     */
    NONE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 结构左右镜像翻转.
     * <br>
     * 与在镜子中看东西类似. X轴正半轴到Z轴负半轴.
     * <p>原文：
     * Structure is mirrored left to right.
     * <br>
     * Similar to looking in a mirror. Positive X to Negative Z
     */
    LEFT_RIGHT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 结构前后镜像翻转.
     * <br>
     * Z轴正半轴到X轴负半轴.
     * <p>原文：
     * Structure is mirrored front to back.
     * <br>
     * Positive Z to Negative X
     */
    FRONT_BACK;
}
