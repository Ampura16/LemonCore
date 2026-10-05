package org.bukkit;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示应用于实体的移动输入。
 * <p>原文：
 * Represents a movement input applied to an entity.
 */
public interface Input {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否应用了前进输入。
     * <p>原文：
     * Gets whether a forward input is applied.
     *
     * @return 前进输入。
     *
     * 原文：
     * Gets whether a forward input is applied.
     * <p>原文：forward input
     */
    boolean isForward();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否应用了后退输入。
     * <p>原文：
     * Gets whether a backward input is applied.
     *
     * @return 后退输入。
     *
     * 原文：
     * Gets whether a backward input is applied.
     * <p>原文：backward input
     */
    boolean isBackward();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否应用了向左输入。
     * <p>原文：
     * Gets whether a left input is applied.
     *
     * @return 向左输入。
     *
     * 原文：
     * Gets whether a left input is applied.
     * <p>原文：left input
     */
    boolean isLeft();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否应用了向右输入。
     * <p>原文：
     * Gets whether a right input is applied.
     *
     * @return 向右输入。
     *
     * 原文：
     * Gets whether a right input is applied.
     * <p>原文：right input
     */
    boolean isRight();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否应用了跳跃输入。
     * <p>原文：
     * Gets whether a jump input is applied.
     *
     * @return 跳跃输入。
     *
     * 原文：
     * Gets whether a jump input is applied.
     * <p>原文：jump input
     */
    boolean isJump();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否应用了潜行输入。
     * <p>原文：
     * Gets whether a sneak input is applied.
     *
     * @return 潜行输入。
     *
     * 原文：
     * Gets whether a sneak input is applied.
     * <p>原文：sneak input
     */
    boolean isSneak();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否应用了冲刺输入。
     * <p>原文：
     * Gets whether a sprint input is applied.
     *
     * @return 冲刺输入。
     *
     * 原文：
     * Gets whether a sprint input is applied.
     * <p>原文：sprint input
     */
    boolean isSprint();
}
