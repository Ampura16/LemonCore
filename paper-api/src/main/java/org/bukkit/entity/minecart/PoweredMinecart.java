package org.bukkit.entity.minecart;

import org.bukkit.entity.Minecart;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表动力矿车.当玩家往动力矿车里放{@link org.bukkit.Material#COAL 燃料}后它就会自己移动.
 * <p>原文：
 * Represents a powered minecart. A powered minecart moves on its own when a
 * player deposits {@link org.bukkit.Material#COAL fuel}.
 */
public interface PoweredMinecart extends Minecart {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取矿车燃料耗尽前的剩余 tick 数.
     * <p>原文：
     * Get the number of ticks until the minecart runs out of fuel.
     *
     * @return 矿车燃料耗尽前的剩余 tick 数
     * <p>原文：Number of ticks until the minecart runs out of fuel
     */
    public int getFuel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置矿车燃料耗尽前的剩余 tick 数.
     * <p>原文：
     * Set the number of ticks until the minecart runs out of fuel.
     *
     * @param fuel 矿车燃料耗尽前的剩余 tick 数
     * <p>原文：Number of ticks until the minecart runs out of fuel
     */
    public void setFuel(int fuel);

    // Paper start
    /**
     * Get the x push of the minecart.
     *
     * @return The x push of the minecart
     */
    public double getPushX();

    /**
     * Get the z push of the minecart.
     *
     * @return The z push of the minecart
     */
    public double getPushZ();

    /**
     * Set the x push of the minecart.
     *
     * @param xPush The new x push of the minecart
     */
    public void setPushX(double xPush);

    /**
     * Set the z push of the minecart.
     *
     * @param zPush The new z push of the minecart
     */
    public void setPushZ(double zPush);
    // Paper end
}
