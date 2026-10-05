package io.papermc.paper.math;

import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * Rotations 是一个不可变对象，以度为单位存储每个轴（X、Y、Z）上的旋转。
 * <p>原文：
 * Rotations is an immutable object that stores rotations
 * in degrees on each axis (X, Y, Z).
 */
@NullMarked
public interface Rotations {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 所有轴都设为 0 的 Rotations 实例
     * <p>原文：
     * Rotations instance with every axis set to 0
     */
    Rotations ZERO = ofDegrees(0, 0, 0);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建持有所提供旋转值的新 Rotations 实例
     * <p>原文：
     * Creates a new Rotations instance holding the provided rotations
     *
     * @param x X 轴角度（度）
     * <p>原文：the angle for the X axis in degrees
     * @param y Y 轴角度（度）
     * <p>原文：the angle for the Y axis in degrees
     * @param z Z 轴角度（度）
     * <p>原文：the angle for the Z axis in degrees
     * @return 持有所提供旋转值的 Rotations 实例
     * <p>原文：Rotations instance holding the provided rotations
     */
    static Rotations ofDegrees(final double x, final double y, final double z) {
        return new RotationsImpl(x, y, z);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回 X 轴上的角度（度）
     * <p>原文：
     * Returns the angle on the X axis in degrees
     *
     * @return 角度（度）
     * <p>原文：the angle in degrees
     */
    double x();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回 Y 轴上的角度（度）
     * <p>原文：
     * Returns the angle on the Y axis in degrees
     *
     * @return 角度（度）
     * <p>原文：the angle in degrees
     */
    double y();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回 Z 轴上的角度（度）
     * <p>原文：
     * Returns the angle on the Z axis in degrees
     *
     * @return 角度（度）
     * <p>原文：the angle in degrees
     */
    double z();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回一个新的 Rotations 实例，其结果为将 X 轴改为传入的角度
     * <p>原文：
     * Returns a new Rotations instance which is the result
     * of changing the X axis to the passed angle
     *
     * @param x 角度（度）
     * <p>原文：the angle in degrees
     * @return 得到的 Rotations
     * <p>原文：the resultant Rotations
     */
    Rotations withX(double x);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回一个新的 Rotations 实例，其结果为将 Y 轴改为传入的角度
     * <p>原文：
     * Returns a new Rotations instance which is the result
     * of changing the Y axis to the passed angle
     *
     * @param y 角度（度）
     * <p>原文：the angle in degrees
     * @return 得到的 Rotations
     * <p>原文：the resultant Rotations
     */
    Rotations withY(double y);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回一个新的 Rotations 实例，其结果为将 Z 轴改为传入的角度
     * <p>原文：
     * Returns a new Rotations instance which is the result
     * of changing the Z axis to the passed angle
     *
     * @param z 角度（度）
     * <p>原文：the angle in degrees
     * @return 得到的 Rotations
     * <p>原文：the resultant Rotations
     */
    Rotations withZ(double z);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回一个新的 Rotations 实例，其结果为此 Rotations 加上 x、y、z 分量
     * <p>原文：
     * Returns a new Rotations instance which is the result of adding
     * the x, y, z components to this Rotations
     *
     * @param x 要加到 X 轴上的角度（度）
     * <p>原文：the angle to add to the X axis in degrees
     * @param y 要加到 Y 轴上的角度（度）
     * <p>原文：the angle to add to the Y axis in degrees
     * @param z 要加到 Z 轴上的角度（度）
     * <p>原文：the angle to add to the Z axis in degrees
     * @return 得到的 Rotations
     * <p>原文：the resultant Rotations
     */
    Rotations add(double x, double y, double z);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回一个新的 Rotations 实例，其结果为此 Rotations 减去 x、y、z 分量
     * <p>原文：
     * Returns a new Rotations instance which is the result of subtracting
     * the x, y, z components from this Rotations
     *
     * @param x 要从 X 轴减去的角度（度）
     * <p>原文：the angle to subtract from the X axis in degrees
     * @param y 要从 Y 轴减去的角度（度）
     * <p>原文：the angle to subtract from the Y axis in degrees
     * @param z 要从 Z 轴减去的角度（度）
     * <p>原文：the angle to subtract from the Z axis in degrees
     * @return 得到的 Rotations
     * <p>原文：the resultant Rotations
     */
    default Rotations subtract(final double x, final double y, final double z) {
        return this.add(-x, -y, -z);
    }

}
