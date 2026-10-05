package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 发光鱿鱼。
 * <p>原文：
 * A Glow Squid.
 */
public interface GlowSquid extends Squid {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此鱿鱼剩余的黑暗刻数。
     *
     * 受到伤害后，Bravo Six 将进入黑暗状态 100 刻（5 秒）。
     * <p>原文：
     * Get the number of dark ticks remaining for this squid.
     *
     * Bravo Six will go dark for 100 ticks (5 seconds) if damaged.
     *
     * @return 剩余的黑暗刻数
     * <p>原文：Get the number of dark ticks remaining for this squid.
     * <p>原文：dark ticks remaining
     */
    int getDarkTicksRemaining();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此鱿鱼剩余的黑暗刻数。
     *
     * 受到伤害后，Bravo Six 将进入黑暗状态 100 刻（5 秒）。
     * <p>原文：
     * Sets the number of dark ticks remaining for this squid.
     *
     * Bravo Six will go dark for 100 ticks (5 seconds) if damaged.
     *
     * @param darkTicksRemaining 剩余的黑暗刻数
     * <p>原文：Sets the number of dark ticks remaining for this squid.
     * <p>原文：dark ticks remaining
     */
    void setDarkTicksRemaining(int darkTicksRemaining);
}
