package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个可以携带物品的类马生物.
 * 译注: 就是有物品栏的由马衍生的生物, 例如驴、羊驼等.
 * <p>原文：
 * Represents Horse-like creatures which can carry an inventory.
 */
public interface ChestedHorse extends AbstractHorse {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此马是否有装备箱子.
     * <p>原文：
     * Gets whether the horse has a chest equipped.
     *
     * @return 如果此马有存储空间返回true
     * <p>原文：true if the horse has chest storage
     */
    public boolean isCarryingChest();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此马是否装备箱子. 移除箱子将会清空箱子内所有物品.
     * <p>原文：
     * Sets whether the horse has a chest equipped. Removing a chest will also
     * clear the chest's inventory.
     *
     * @param chest 设置为true则此马将拥有箱子
     * <p>原文：true if the horse should have a chest
     */
    public void setCarryingChest(boolean chest);
}
