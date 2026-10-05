package org.bukkit.inventory.meta;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表能追踪指定位置的指南针.
 * <p>原文：
 * Represents a compass that can track a specific location.
 */
public interface CompassMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测此指南针是否与某个磁石配对 (是否为磁石指针).
     * <p>原文：
     * Checks if this compass has been paired to a lodestone.
     *
     * @return 配对状态
     * <p>原文：paired status
     */
    boolean hasLodestone();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指南针指向的位置.
     *
     * 调用本方法前先检查 {@link #hasLodestone()}!
     * <p>原文：
     * Gets the location that this compass will point to.
     *
     * Check {@link #hasLodestone()} first!
     *
     * @return 磁石位置
     * <p>原文：lodestone location
     */
    @Nullable
    Location getLodestone();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指南针指向的位置.
     * <p>原文：
     * Sets the location this lodestone compass will point to.
     *
     * @param lodestone 新位置, 或 null 以清除位置
     * <p>原文：new location or null to clear the targeted location
     * @see #clearLodestone() to reset the compass to a normal compass
     */
    void setLodestone(@Nullable Location lodestone);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指南针是否正追踪一个指定的磁石 (与磁石相绑定).
     *
     * 若为 true, 仅在追踪的位置有磁石时指南针才会正常工作.
     * <p>原文：
     * Gets if this compass is tracking a specific lodestone.
     *
     * If true the compass will only work if there is a lodestone at the tracked
     * location.
     *
     * @return 是否追踪磁石
     * <p>原文：lodestone tracked
     */
    boolean isLodestoneTracked();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指南针是否正追踪一个指定的磁石.
     *
     * 若为 true, 仅在追踪的位置有磁石时指南针才会正常工作.
     * <p>原文：
     * Sets if this compass is tracking a specific lodestone.
     *
     * If true the compass will only work if there is a lodestone at the tracked
     * location.
     *
     * @param tracked 新的追踪状态
     * <p>原文：new tracked status
     * @see #clearLodestone() to reset the compass to a normal compass
     */
    void setLodestoneTracked(boolean tracked);

    // Paper start - Add more lodestone compass methods
    /**
     * Checks if this compass is considered a lodestone compass.
     * @see #hasLodestone() to check if a position is being tracked
     * @see #isLodestoneTracked() to check if it verifies the position is a lodestone
     */
    boolean isLodestoneCompass();

    /**
     * Reset this compass to a normal compass, removing any tracked
     * location.
     */
    void clearLodestone();
    // Paper end - Add more lodestone compass methods

    @Override
    CompassMeta clone();
}
