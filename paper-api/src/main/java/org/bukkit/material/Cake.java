package org.bukkit.material;

import org.bukkit.Material;

/**
 * @deprecated all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Cake extends MaterialData {
    public Cake() {
        super(Material.LEGACY_CAKE_BLOCK);
    }

    public Cake(Material type) {
        super(type);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type the type
     * @param data the raw data value
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public Cake(Material type, byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取蛋糕被吃掉了多少片.
     * <p>原文：
     * Gets the number of slices eaten from this cake
     *
     * @return 蛋糕被吃掉了多少片
     * <p>原文：The number of slices eaten
     */
    public int getSlicesEaten() {
        return getData();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取剩下多少片蛋糕没吃.
     * <p>原文：
     * Gets the number of slices remaining on this cake
     *
     * @return 剩下多少片蛋糕没吃
     * <p>原文：The number of slices remaining
     */
    public int getSlicesRemaining() {
        return 6 - getData();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置蛋糕被吃掉了多少片.
     * <p>原文：
     * Sets the number of slices eaten from this cake
     *
     * @param n 蛋糕被吃掉了多少片.
     * <p>原文：The number of slices eaten
     */
    public void setSlicesEaten(int n) {
        if (n < 6) {
            setData((byte) n);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置剩下多少片蛋糕没吃.
     * <p>原文：
     * Sets the number of slices remaining on this cake
     *
     * @param n 剩下多少片蛋糕没吃
     * <p>原文：The number of slices remaining
     */
    public void setSlicesRemaining(int n) {
        if (n > 6) {
            n = 6;
        }
        setData((byte) (6 - n));
    }

    @Override
    public String toString() {
        return super.toString() + " " + getSlicesEaten() + "/" + getSlicesRemaining() + " slices eaten/remaining";
    }

    @Override
    public Cake clone() {
        return (Cake) super.clone();
    }
}
