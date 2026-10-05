package org.bukkit;

// Paper start
/**
 * @deprecated use {@link org.bukkit.block.data.BlockData} and {@link org.bukkit.block.data.Ageable}
 */
@Deprecated(forRemoval = true, since = "1.13")
// Paper end
public enum NetherWartsState {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 播种时的状态.
     * <p>原文：
     * State when first seeded
     */
    SEEDED,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 第一生长阶段.
     * <p>原文：
     * First growth stage
     */
    STAGE_ONE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 第二生长阶段.
     * <p>原文：
     * Second growth stage
     */
    STAGE_TWO,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 成熟，准备收获.
     * <p>原文：
     * Ready to harvest
     */
    RIPE;
}
