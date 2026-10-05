package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;

public interface Rabbit extends Animals {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 兔子的种类.
     * <p>原文：The type of rabbit.
     */
    @NotNull
    public Type getRabbitType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type 设置兔子的种类.
     * <p>原文：Sets the type of rabbit for this entity.
     */
    public void setRabbitType(@NotNull Type type);
    // Paper start
    /**
     * Sets how many ticks this rabbit will wait
     * until trying to find more carrots.
     *
     * @param ticks ticks
     */
    void setMoreCarrotTicks(int ticks);

    /**
     * Returns how many ticks this rabbit
     * will wait until trying to find more carrots.
     *
     * @return ticks
     */
    int getMoreCarrotTicks();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表不同种类的兔子.
     * <p>原文：
     * Represents the various types a Rabbit might be.
     */
    public enum Type {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 棕色的兔子.
         * <p>原文：
         * Chocolate colored rabbit.
         */
        BROWN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 白色的兔子.
         * <p>原文：
         * Pure white rabbit.
         */
        WHITE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 黑色的兔子.
         * <p>原文：
         * Black rabbit.
         */
        BLACK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 黑白相间的兔子.
         * <p>原文：
         * Black with white patches, or white with black patches?
         */
        BLACK_AND_WHITE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 金色的兔子.
         * <p>原文：
         * Golden bunny.
         */
        GOLD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 胡椒盐色的兔子.
         * <p>原文：
         * Salt and pepper colored, whatever that means.
         */
        SALT_AND_PEPPER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 杀手兔.
         * <p>原文：
         * Rabbit with pure white fur, blood red horizontal eyes, and is hostile to players.
         */
        THE_KILLER_BUNNY
    }
}
