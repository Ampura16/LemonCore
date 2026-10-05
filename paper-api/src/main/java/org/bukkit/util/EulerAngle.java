package org.bukkit.util;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * EulerAngle即欧拉角,是指用给定的三个角,
 * 每一个表示一个轴(x,y,z)的偏转角.这些角用弧度表示.
 * <p>原文：
 * EulerAngle is used to represent 3 angles, one for each
 * axis (x, y, z). The angles are in radians
 */
public class EulerAngle {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 一个所有偏转角都为0的欧拉角.
     * <p>
     * A EulerAngle with every axis set to 0
     * <p>原文：
     * A EulerAngle with every axis set to 0
     */
    public static final EulerAngle ZERO = new EulerAngle(0, 0, 0);

    private final double x;
    private final double y;
    private final double z;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个EularAngle,每一个坐标轴都设置成给定弧度角.
     * <p>原文：
     * Creates a EularAngle with each axis set to the
     * passed angle in radians
     *
     * @param x x轴的弧度角
     * <p>原文：the angle for the x axis in radians
     * @param y y轴的弧度角
     * <p>原文：the angle for the y axis in radians
     * @param z z轴的弧度角
     * <p>原文：the angle for the z axis in radians
     */
    public EulerAngle(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回x轴的弧度角.
     * <p>原文：
     * Returns the angle on the x axis in radians
     *
     * @return 弧度角
     * <p>原文：the angle in radians
     */
    public double getX() {
        return x;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回y轴的弧度角.
     * <p>原文：
     * Returns the angle on the y axis in radians
     *
     * @return 弧度角
     * <p>原文：the angle in radians
     */
    public double getY() {
        return y;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回z轴的弧度角.
     * <p>原文：
     * Returns the angle on the z axis in radians
     *
     * @return 弧度角
     * <p>原文：the angle in radians
     */
    public double getZ() {
        return z;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回x轴设为了给定弧度角的新EulerAngle对象.
     * <p>原文：
     * Return a EulerAngle which is the result of changing
     * the x axis to the passed angle
     *
     * @param x x轴弧度角
     * <p>原文：the angle in radians
     * @return 一个新的EulerAngle
     * <p>原文：the resultant EulerAngle
     */
    @NotNull
    public EulerAngle setX(double x) {
        return new EulerAngle(x, y, z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回y轴设为了给定弧度角的新EulerAngle对象.
     * <p>原文：
     * Return a EulerAngle which is the result of changing
     * the y axis to the passed angle
     *
     * @param y y轴弧度角
     * <p>原文：the angle in radians
     * @return 一个新的EulerAngle
     * <p>原文：the resultant EulerAngle
     */
    @NotNull
    public EulerAngle setY(double y) {
        return new EulerAngle(x, y, z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回z轴设为了给定弧度角的新EulerAngle对象.
     * <p>原文：
     * Return a EulerAngle which is the result of changing
     * the z axis to the passed angle
     *
     * @param z z轴弧度角
     * <p>原文：the angle in radians
     * @return 一个新的EulerAngle
     * <p>原文：the resultant EulerAngle
     */
    @NotNull
    public EulerAngle setZ(double z) {
        return new EulerAngle(x, y, z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个新的EulerAngle,这个新的欧拉角将原欧拉角的三个轴的弧度均添加某个偏移量.
     * <p>原文：
     * Creates a new EulerAngle which is the result of adding
     * the x, y, z components to this EulerAngle
     *
     * @param x x轴的弧度偏移量
     * <p>原文：the angle to add to the x axis in radians
     * @param y y轴的弧度偏移量
     * <p>原文：the angle to add to the y axis in radians
     * @param z z轴的弧度偏移量
     * <p>原文：the angle to add to the z axis in radians
     * @return 一个新的EulerAngle
     * <p>原文：the resultant EulerAngle
     */
    @NotNull
    public EulerAngle add(double x, double y, double z) {
        return new EulerAngle(
                this.x + x,
                this.y + y,
                this.z + z
        );
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个新的EulerAngle,这个新的欧拉角将给定的三个轴的弧度均减去某个偏移量.
     * <p>原文：
     * Creates a new EulerAngle which is the result of subtracting
     * the x, y, z components to this EulerAngle
     *
     * @param x x轴的弧度偏移量
     * <p>原文：the angle to subtract to the x axis in radians
     * @param y y轴的弧度偏移量
     * <p>原文：the angle to subtract to the y axis in radians
     * @param z z轴的弧度偏移量
     * <p>原文：the angle to subtract to the z axis in radians
     * @return 一个新的EulerAngle
     * <p>原文：the resultant EulerAngle
     */
    @NotNull
    public EulerAngle subtract(double x, double y, double z) {
        return add(-x, -y, -z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EulerAngle that = (EulerAngle) o;

        return Double.compare(that.x, x) == 0
                && Double.compare(that.y, y) == 0
                && Double.compare(that.z, z) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
