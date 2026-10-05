package io.papermc.paper.math;

import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示具有指定俯仰角和偏航角的旋转。
 * <p>原文：
 * Represents a rotation with specified pitch and yaw values.
 */
@NullMarked
public interface Rotation {
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定的偏航角和俯仰角创建新的旋转。
     * <p>原文：
     * Creates a new rotation with the specified yaw and pitch values.
     *
     * @param yaw 旋转的偏航分量（度）
     * <p>原文：the yaw component of the rotation, measured in degrees
     * @param pitch 旋转的俯仰分量（度）
     * <p>原文：the pitch component of the rotation, measured in degrees
     * @return 具有指定偏航角和俯仰角的新 {@code Rotation} 实例
     * <p>原文：a new {@code Rotation} instance with the specified yaw and pitch
     */
    static Rotation rotation(float yaw, float pitch) {
        return new RotationImpl(yaw, pitch);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取旋转的俯仰角分量（以度为单位）。
     * <p>原文：
     * Retrieves the pitch component of the rotation, measured in degrees.
     *
     * @return 俯仰角（度）
     * <p>原文：the pitch value in degrees
     */
    float pitch();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取旋转的偏航角分量（以度为单位）。
     * <p>原文：
     * Retrieves the yaw component of the rotation, measured in degrees.
     *
     * @return 偏航角（度）
     * <p>原文：the yaw value in degrees
     */
    float yaw();
}
