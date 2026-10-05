package org.bukkit.entity;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一种仅用于记录交互的实体。
 * <p>原文：
 * Represents an entity designed to only record interactions.
 */
public interface Interaction extends Entity {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此交互实体的宽度。
     * <p>原文：
     * Gets the width of this interaction entity.
     *
     * @return width
     */
    public float getInteractionWidth();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此交互实体的宽度。
     * <p>原文：
     * Sets the width of this interaction entity.
     *
     * @param width new width
     */
    public void setInteractionWidth(float width);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此交互实体的高度。
     * <p>原文：
     * Gets the height of this interaction entity.
     *
     * @return height
     */
    public float getInteractionHeight();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此交互实体的高度。
     * <p>原文：
     * Sets the height of this interaction entity.
     *
     * @param height new height
     */
    public void setInteractionHeight(float height);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此交互实体被交互时是否触发响应。
     * <p>原文：
     * Gets if this interaction entity should trigger a response when interacted
     * with.
     *
     * @return response setting
     */
    public boolean isResponsive();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此交互实体被交互时是否触发响应。
     * <p>原文：
     * Sets if this interaction entity should trigger a response when interacted
     * with.
     *
     * @param response new setting
     */
    public void setResponsive(boolean response);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此交互实体的最后一次攻击。
     * <p>原文：
     * Gets the last attack on this interaction entity.
     *
     * @return last attack data, if present
     */
    @Nullable
    public PreviousInteraction getLastAttack();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体的最后一次交互。
     * <p>原文：
     * Gets the last interaction on this entity.
     *
     * @return last interaction data, if present
     */
    @Nullable
    public PreviousInteraction getLastInteraction();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表与此实体的一次先前交互。
     * <p>原文：
     * Represents a previous interaction with this entity.
     */
    public interface PreviousInteraction {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取先前交互的玩家。
         * <p>原文：
         * Get the previous interacting player.
         *
         * @return interacting player
         */
        @NotNull
        public OfflinePlayer getPlayer();

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取此交互发生的Unix时间戳。
         * <p>原文：
         * Gets the Unix timestamp at when this interaction occurred.
         *
         * @return interaction timestamp
         */
        public long getTimestamp();
    }
}
