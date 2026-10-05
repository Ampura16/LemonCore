package org.bukkit.material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示一种可以携带或产生红石信号的材料.
 * <p>原文：
 * Indicated a Material that may carry or create a Redstone current
 */
@Deprecated(forRemoval = true, since = "1.13")
public interface Redstone {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此材料的当前状态, 表示其是否被充能.
     * <p>原文：
     * Gets the current state of this Material, indicating if it's powered or
     * unpowered
     *
     * @return 如果被充能则返回true, 否则返回false
     * <p>原文：true if powered, otherwise false
     */
    public boolean isPowered();
}
