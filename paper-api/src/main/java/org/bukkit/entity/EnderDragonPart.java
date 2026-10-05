package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表末影龙的组成部分
 * <p>原文：
 * Represents an ender dragon part
 */
public interface EnderDragonPart extends ComplexEntityPart, Damageable {
    @Override
    @NotNull
    public EnderDragon getParent();
}
