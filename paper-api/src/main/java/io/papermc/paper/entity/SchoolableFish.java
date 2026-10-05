package io.papermc.paper.entity;

import org.bukkit.entity.Fish;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示可以与其他鱼成群游动的鱼。
 * <p>原文：
 * Represents a fish that can school with other fish.
 */
@NullMarked
public interface SchoolableFish extends Fish {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 强制此鱼跟随给定的鱼。
     * <p>原文：
     * Forces this fish to follow the given fish.
     *
     * @param leader 要跟随的鱼
     * <p>原文：fish to follow
     */
    void startFollowing(SchoolableFish leader);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使鱼停止跟随其当前的领头鱼。
     * <p>原文：
     * Causes the fish to stop following their current
     * leader.
     */
    void stopFollowing();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取当前跟随这条鱼的鱼的数量。
     * <p>原文：
     * Gets the amount of fish currently following this fish.
     *
     * @return 鱼群规模
     * <p>原文：school size
     */
    int getSchoolSize();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取会自然跟随这条鱼的鱼的最大数量。
     * <p>原文：
     * Gets the maximum number of fish that will naturally follow this fish.
     *
     * @return 最大鱼群规模
     * <p>原文：max school size
     */
    int getMaxSchoolSize();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此实体当前跟随的鱼。
     * <p>原文：
     * Gets the fish that this entity is currently following.
     *
     * @return 跟随的鱼
     * <p>原文：following fish
     */
    @Nullable SchoolableFish getSchoolLeader();

}
