package org.bukkit.util.noise;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 使用无偏倍频创建一个噪音.
 * <p>原文：
 * Creates noise using unbiased octaves
 */
public abstract class OctaveGenerator {
    @NotNull
    protected final NoiseGenerator[] octaves;
    protected double xScale = 1;
    protected double yScale = 1;
    protected double zScale = 1;

    protected OctaveGenerator(@NotNull NoiseGenerator @NotNull [] octaves) {
        this.octaves = octaves;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置用于此生成器的所有坐标的区间大小.
     * <p>原文：
     * Sets the scale used for all coordinates passed to this generator.
     * <p>
     * This is the equivalent to setting each coordinate to the specified
     * value.
     *
     * @param scale 每个坐标的新区间大小值
     * <p>原文：New value to scale each coordinate by
     */
    public void setScale(double scale) {
        setXScale(scale);
        setYScale(scale);
        setZScale(scale);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每个X坐标通过的区间大小.
     * <p>原文：
     * Gets the scale used for each X-coordinates passed
     *
     * @return X区间大小
     * <p>原文：X scale
     */
    public double getXScale() {
        return xScale;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置每个X坐标通过的区间大小.
     * <p>原文：
     * Sets the scale used for each X-coordinates passed
     *
     * @param scale 新的X区间大小
     * <p>原文：New X scale
     */
    public void setXScale(double scale) {
        xScale = scale;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每个Y坐标通过的区间大小.
     * <p>原文：
     * Gets the scale used for each Y-coordinates passed
     *
     * @return Y区间大小
     * <p>原文：Y scale
     */
    public double getYScale() {
        return yScale;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置每个Y坐标通过的区间大小.
     * <p>原文：
     * Sets the scale used for each Y-coordinates passed
     *
     * @param scale 新的Y区间大小
     * <p>原文：New Y scale
     */
    public void setYScale(double scale) {
        yScale = scale;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每个Z坐标通过的区间大小.
     * <p>原文：
     * Gets the scale used for each Z-coordinates passed
     *
     * @return Z区间大小
     * <p>原文：Z scale
     */
    public double getZScale() {
        return zScale;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置每个Z坐标通过的区间大小.
     * <p>原文：
     * Sets the scale used for each Z-coordinates passed
     *
     * @param scale 新的Z区间大小
     * <p>原文：New Z scale
     */
    public void setZScale(double scale) {
        zScale = scale;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个生成器内的倍频的复制
     * <p>原文：
     * Gets a clone of the individual octaves used within this generator
     *
     * @return 复制产生的倍频
     * <p>原文：Clone of the individual octaves
     */
    @NotNull
    public NoiseGenerator @NotNull [] getOctaves() {
        return octaves.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用既定的倍频和参数产生一个一维坐标的噪音.
     * <p>原文：
     * Generates noise for the 1D coordinates using the specified number of
     * octaves and parameters
     *
     * @param x X坐标
     * <p>原文：X-coordinate
     * @param frequency 每一个倍频改变多少频率
     * <p>原文：How much to alter the frequency by each octave
     * @param amplitude 每一个倍频改变多少振幅
     * <p>原文：How much to alter the amplitude by each octave
     * @return 噪音结果
     * <p>原文：Resulting noise
     */
    public double noise(double x, double frequency, double amplitude) {
        return noise(x, 0, 0, frequency, amplitude);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用既定倍频和参数产生一个一维坐标的噪音.
     * <p>原文：
     * Generates noise for the 1D coordinates using the specified number of
     * octaves and parameters
     *
     * @param x X坐标
     * <p>原文：X-coordinate
     * @param frequency 每一个倍频改变多少频率
     * <p>原文：How much to alter the frequency by each octave
     * @param amplitude 每一个倍频改变多少振幅
     * <p>原文：How much to alter the amplitude by each octave
     * @param normalized 如果为true,规范这个值为[-1, 1]
     * <p>原文：If true, normalize the value to [-1, 1]
     * @return 噪音结果
     * <p>原文：Resulting noise
     */
    public double noise(double x, double frequency, double amplitude, boolean normalized) {
        return noise(x, 0, 0, frequency, amplitude, normalized);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用既定倍频和参数产生一个二维坐标的噪音.
     * <p>原文：
     * Generates noise for the 2D coordinates using the specified number of
     * octaves and parameters
     *
     * @param x X坐标
     * <p>原文：X-coordinate
     * @param y Y坐标
     * <p>原文：Y-coordinate
     * @param frequency 每一个倍频改变多少频率
     * <p>原文：How much to alter the frequency by each octave
     * @param amplitude 每一个倍频改变多少振幅
     * <p>原文：How much to alter the amplitude by each octave
     * @return 噪音结果
     * <p>原文：Resulting noise
     */
    public double noise(double x, double y, double frequency, double amplitude) {
        return noise(x, y, 0, frequency, amplitude);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用既定倍频和参数产生一个二维坐标的噪音.
     * <p>原文：
     * Generates noise for the 2D coordinates using the specified number of
     * octaves and parameters
     *
     * @param x X坐标
     * <p>原文：X-coordinate
     * @param y Y坐标
     * <p>原文：Y-coordinate
     * @param frequency 每一个倍频改变多少频率
     * <p>原文：How much to alter the frequency by each octave
     * @param amplitude 每一个倍频改变多少振幅
     * <p>原文：How much to alter the amplitude by each octave
     * @param normalized 如果为true,规范这个值为[-1, 1]
     * <p>原文：If true, normalize the value to [-1, 1]
     * @return 噪音结果
     * <p>原文：Resulting noise
     */
    public double noise(double x, double y, double frequency, double amplitude, boolean normalized) {
        return noise(x, y, 0, frequency, amplitude, normalized);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用既定倍频和参数产生一个三维坐标的噪音.
     * <p>原文：
     * Generates noise for the 3D coordinates using the specified number of
     * octaves and parameters
     *
     * @param x X坐标
     * <p>原文：X-coordinate
     * @param y Y坐标
     * <p>原文：Y-coordinate
     * @param z Z坐标
     * <p>原文：Z-coordinate
     * @param frequency 每一个倍频改变多少频率
     * <p>原文：How much to alter the frequency by each octave
     * @param amplitude 每一个倍频改变多少振幅
     * <p>原文：How much to alter the amplitude by each octave
     * @return 噪音结果
     * <p>原文：Resulting noise
     */
    public double noise(double x, double y, double z, double frequency, double amplitude) {
        return noise(x, y, z, frequency, amplitude, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用既定倍频和参数产生一个三维坐标的噪音.
     * <p>原文：
     * Generates noise for the 3D coordinates using the specified number of
     * octaves and parameters
     *
     * @param x X坐标
     * <p>原文：X-coordinate
     * @param y Y坐标
     * <p>原文：Y-coordinate
     * @param z Z坐标
     * <p>原文：Z-coordinate
     * @param frequency 每一个倍频改变多少频率
     * <p>原文：How much to alter the frequency by each octave
     * @param amplitude 每一个倍频改变多少振幅
     * <p>原文：How much to alter the amplitude by each octave
     * @param normalized 如果为true,规范这个值为[-1, 1]
     * <p>原文：If true, normalize the value to [-1, 1]
     * @return 噪音结果
     * <p>原文：Resulting noise
     */
    public double noise(double x, double y, double z, double frequency, double amplitude, boolean normalized) {
        double result = 0;
        double amp = 1;
        double freq = 1;
        double max = 0;

        x *= xScale;
        y *= yScale;
        z *= zScale;

        for (NoiseGenerator octave : octaves) {
            result += octave.noise(x * freq, y * freq, z * freq) * amp;
            max += amp;
            freq *= frequency;
            amp *= amplitude;
        }

        if (normalized) {
            result /= max;
        }

        return result;
    }
}
