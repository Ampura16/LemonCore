package org.bukkit.entity;

import com.destroystokyo.paper.entity.RangedEntity;
import org.bukkit.inventory.LlamaInventory;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示一只羊驼.
 * <p>原文：
 * Represents a Llama.
 */
public interface Llama extends ChestedHorse, RangedEntity { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表羊驼的颜色.
     * <p>原文：
     * Represents the base color that the llama has.
     */
    public enum Color {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 奶油色的羊驼.
         * <p>原文：
         * A cream-colored llama.
         */
        CREAMY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 白色的羊驼.
         * <p>原文：
         * A white llama.
         */
        WHITE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 棕色的羊驼.
         * <p>原文：
         * A brown llama.
         */
        BROWN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 灰色的羊驼.
         * <p>原文：
         * A gray llama.
         */
        GRAY;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取羊驼的颜色.
     * <p>原文：
     * Gets the llama's color.
     *
     * @return 返回羊驼的{@linkplain Color 颜色}
     * <p>原文：a {@link Color} representing the llama's color
     */
    @NotNull
    Color getColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置羊驼的颜色.
     * <p>原文：
     * Sets the llama's color.
     *
     * @param color 给定的羊驼的{@linkplain Color 颜色}
     * <p>原文：a {@link Color} for this llama
     */
    void setColor(@NotNull Color color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取羊驼的强度, 强度大的羊驼的背包将有更大的容量, 并对其他实体更具威胁性.
     * <p>
     * 译注:此处的强度采用的是 中文Wiki 中的翻译, 但个人认为翻译为力气会好些
     * <p>原文：
     * Gets the llama's strength. A higher strength llama will have more
     * inventory slots and be more threatening to entities.
     *
     * @return 羊驼的强度, 范围会在 [1-5] 之间
     * <p>原文：llama strength [1,5]
     */
    int getStrength();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置羊驼的强度, 强度大的羊驼的背包将有更大的容量, 并对其他实体更具威胁性.
     * 羊驼的背包容量的计算公式: 强度值 * 3.
     * <p>
     * 译注:此处的强度采用的是 中文Wiki 中的翻译, 但个人认为翻译为力气会好些. <p>
     * 当给定的强度值不在 [1-5] 该范围内时,将会抛出异常 IllegalArgumentException.
     * <p>原文：
     * Sets the llama's strength. A higher strength llama will have more
     * inventory slots and be more threatening to entities. Inventory slots are
     * equal to strength * 3.
     *
     * @param strength 给定的强度值, 强度须在 [1-5] 之间
     * <p>原文：llama strength [1,5]
     */
    void setStrength(int strength);

    @NotNull
    @Override
    LlamaInventory getInventory();

    // Paper start
    /**
     * Checks if this llama is in a caravan.
     * This means that this llama is currently following
     * another llama.
     *
     * @return is in caravan
     */
    boolean inCaravan();

    /**
     * Joins a caravan, with the provided llama being the leader
     * of the caravan.
     * This llama will then follow the provided llama.
     *
     * @param llama head of caravan to join
     */
    void joinCaravan(@NotNull Llama llama);

    /**
     * Leaves the current caravan that they are in.
     */
    void leaveCaravan();

    /**
     * Get the llama that this llama is following.
     * <p>
     * Does not necessarily mean the leader of the entire caravan.
     *
     * @return the llama currently being followed
     */
    @org.jetbrains.annotations.Nullable
    Llama getCaravanHead();

    /**
     * Checks if another llama is currently following behind
     * this llama.
     *
     * @return true if being followed in the caravan
     */
    boolean hasCaravanTail();

    /**
     * Gets the llama that is currently following behind
     * this llama.
     *
     * @return the llama following this llama, or null if none is following them
     */
    @org.jetbrains.annotations.Nullable
    Llama getCaravanTail();
    // Paper end
}
