package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表狐狸.
 * <p>原文：
 * What does the fox say?
 */
public interface Fox extends Animals, Sittable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此狐狸品种.
     * <p>原文：
     * Gets the current type of this fox.
     *
     * @return 狐狸品种.
     * <p>原文：Type of the fox.
     */
    @NotNull
    public Type getFoxType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置狐狸的品种.
     * <p>原文：
     * Sets the current type of this fox.
     *
     * @param type 要设置的新品种.
     * <p>原文：New type of this fox.
     */
    public void setFoxType(@NotNull Type type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断是否蜷缩.
     * <p>原文：
     * Checks if this animal is crouching
     *
     * @return 蜷缩中返回true.
     * <p>原文：true if crouching
     */
    boolean isCrouching();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置是否蜷缩.
     * <p>原文：
     * Sets if this animal is crouching.
     *
     * @param crouching 设置为true则蜷缩.
     * <p>原文：true if crouching
     */
    void setCrouching(boolean crouching);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置是否正在睡觉.
     * <p>原文：
     * Sets if this animal is sleeping.
     *
     * @param sleeping 设置为true则进入睡眠状态
     * <p>原文：true if sleeping
     */
    void setSleeping(boolean sleeping);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取第一个受信任的玩家.
     * <p>原文：
     * Gets the first trusted player.
     *
     * @return 拥有此狐狸的 AnimalTamer，若无则返回 null.
     * <p>原文：the owning AnimalTamer, or null if not owned
     */
    @Nullable
    public AnimalTamer getFirstTrustedPlayer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置第一个受信任的玩家.
     * <p>
     * 第一个受信任的玩家只能在第二个之后被移除.
     * <p>原文：
     * Set the first trusted player.
     * <p>
     * The first trusted player may only be removed after the second.
     *
     * @param player 要信任的 AnimalTamer.
     * <p>原文：the AnimalTamer to be trusted
     */
    public void setFirstTrustedPlayer(@Nullable AnimalTamer player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取第二个受信任的玩家.
     * <p>原文：
     * Gets the second trusted player.
     *
     * @return 拥有此狐狸的 AnimalTamer，若无则返回 null.
     * <p>原文：the owning AnimalTamer, or null if not owned
     */
    @Nullable
    public AnimalTamer getSecondTrustedPlayer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置第二个受信任的玩家.
     * <p>
     * 第二个受信任的玩家只能在第一个之后被添加.
     * <p>原文：
     * Set the second trusted player.
     * <p>
     * The second trusted player may only be added after the first.
     *
     * @param player 要信任的 AnimalTamer.
     * <p>原文：the AnimalTamer to be trusted
     */
    public void setSecondTrustedPlayer(@Nullable AnimalTamer player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断狐狸是否正在面部朝地.
     * <p>原文：
     * Gets whether the fox is faceplanting the ground
     *
     * @return 如果狐狸正在面部朝地则返回 true.
     * <p>原文：Whether the fox is faceplanting the ground
     */
    boolean isFaceplanted();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表各种狐狸品种.
     * <p>原文：
     * Represents the various different fox types there are.
     */
    public enum Type {
        // Start generate - FoxType
        RED,
        SNOW;
        // End generate - FoxType
    }

    // Paper start - Add more fox behavior API
    /**
     * Sets if the fox is interested.
     *
     * @param interested is interested
     */
    public void setInterested(boolean interested);

    /**
     * Gets if the fox is interested.
     *
     * @return fox is interested
     */
    public boolean isInterested();

    /**
     * Sets if the fox is leaping.
     *
     * @param leaping is leaping
     */
    public void setLeaping(boolean leaping);

    /**
     * Gets if the fox is leaping.
     *
     * @return fox is leaping
     */
    public boolean isLeaping();

    /**
     * Sets if the fox is defending.
     *
     * @param defending is defending
     */
    public void setDefending(boolean defending);

    /**
     * Gets if the fox is defending.
     *
     * @return fox is defending
     */
    public boolean isDefending();

    /**
     * Sets if the fox face planted.
     *
     * @param faceplanted face planted
     */
    public void setFaceplanted(boolean faceplanted);
    // Paper end - Add more fox behavior API
}
