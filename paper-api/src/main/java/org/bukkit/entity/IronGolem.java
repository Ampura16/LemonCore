package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 村民守卫者——铁傀儡.
 * <p>原文：
 * An iron Golem that protects Villages.
 */
public interface IronGolem extends Golem {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个铁傀儡是否由玩家建造.
     * <p>原文：
     * Gets whether this iron golem was built by a player.
     *
     * @return 这个铁傀儡是否由玩家建造
     * <p>原文：Whether this iron golem was built by a player
     */
    public boolean isPlayerCreated();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个铁傀儡是否由玩家建造.
     * <p>原文：
     * Sets whether this iron golem was built by a player or not.
     *
     * @param playerCreated 这个铁傀儡是否由玩家建造，如果想让这个铁傀儡是自然的那就false
     * <p>原文：true if you want to set the iron golem as being
     *     player created, false if you want it to be a natural village golem.
     */
    public void setPlayerCreated(boolean playerCreated);
}
