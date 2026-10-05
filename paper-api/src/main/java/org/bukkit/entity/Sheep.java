package org.bukkit.entity;

import org.bukkit.material.Colorable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表羊.
 * <p>原文：
 * Represents a Sheep.
 */
public interface Sheep extends Animals, Colorable, Shearable, io.papermc.paper.entity.Shearable { // Paper - Shear API

    /**
     * Gets whether the sheep is in its sheared state.
     *
     * @return Whether the sheep is sheared.
     */
    boolean isSheared();

    /**
     * Sets whether the sheep is in its sheared state.
     *
     * @param flag Whether to shear the sheep
     */
    void setSheared(boolean flag);
}
