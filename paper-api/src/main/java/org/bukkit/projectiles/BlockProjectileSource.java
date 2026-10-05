package org.bukkit.projectiles;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface BlockProjectileSource extends ProjectileSource {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个抛射物所属的方块。
     * <p>原文：
     * Gets the block this projectile source belongs to.
     *
     * @return 抛射物所属方块
     * <p>原文：Block for the projectile source
     */
    @NotNull
    public Block getBlock();
}
