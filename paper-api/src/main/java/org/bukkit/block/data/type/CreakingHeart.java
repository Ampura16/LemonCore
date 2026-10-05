package org.bukkit.block.data.type;

import org.bukkit.block.data.Orientable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'creaking_heart_state' 表示方块当前的运行阶段.
 * <br>
 * 'natural' 表示这是否是自然生成的方块.
 * <p>原文：
 * 'creaking_heart_state' is the state of the block depending on the time and its position.
 * <br>
 * 'natural' is whether this is a naturally generated block.
 */
public interface CreakingHeart extends Orientable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'active' 属性的值.
     * <p>原文：
     * Gets the value of the 'active' property.
     *
     * @return 'active' 的值
     * <p>原文：the 'active' value
     * @deprecated 使用 {@link #getCreakingHeartState()}
     * <p>原文：use {@link #getCreakingHeartState()}
     */
    @Deprecated(since = "1.21.5", forRemoval = true)
    default boolean isActive() {
        return this.getCreakingHeartState() != State.UPROOTED;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'active' 属性的值.
     * <p>原文：
     * Sets the value of the 'active' property.
     *
     * @param active 新的 'active' 值
     * <p>原文：the new 'active' value
     * @deprecated 使用 {@link #setCreakingHeartState(org.bukkit.block.data.type.CreakingHeart.State)}
     * <p>原文：use {@link #setCreakingHeartState(State)}
     */
    @Deprecated(since = "1.21.5", forRemoval = true)
    default void setActive(boolean active) {
        this.setCreakingHeartState(active ? State.AWAKE : State.UPROOTED);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'creaking_heart_state' 属性的值.
     * <p>原文：
     * Gets the value of the 'creaking_heart_state' property.
     *
     * @return 'creaking_heart_state' 的值
     * <p>原文：the 'creaking_heart_state' value
     */
    @NotNull
    State getCreakingHeartState();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'creaking_heart_state' 属性的值.
     * <p>原文：
     * Sets the value of the 'creaking_heart_state' property.
     *
     * @param state 新的 'creaking_heart_state' 值
     * <p>原文：the new 'creaking_heart_state' value
     */
    void setCreakingHeartState(@NotNull State state);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'natural' 属性的值.
     * <p>原文：
     * Gets the value of the 'natural' property.
     *
     * @return 'natural' 的值
     * <p>原文：the 'natural' value
     */
    boolean isNatural();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'natural' 属性的值.
     * <p>原文：
     * Sets the value of the 'natural' property.
     *
     * @param natural 新的 'natural' 值
     * <p>原文：the new 'natural' value
     */
    void setNatural(boolean natural);

    enum State {
        UPROOTED,
        DORMANT,
        AWAKE
    }
}
