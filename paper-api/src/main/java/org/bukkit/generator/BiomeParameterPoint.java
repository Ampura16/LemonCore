package org.bukkit.generator;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示可能传递给世界生成器的生物群系噪声参数.
 * <p>原文：
 * Represents the biome noise parameters which may be passed to a world
 * generator.
 */
public interface BiomeParameterPoint {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取噪声生成器建议的此点生物群系的温度.
     * <p>原文：
     * Gets the temperature of the biome at this point that is suggested by the
     * NoiseGenerator.
     *
     * @return 此点生物群系的温度
     * <p>原文：The temperature of the biome at this point
     */
    double getTemperature();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最高温度.
     * <p>原文：
     * Gets the maximum temperature that is possible.
     *
     * @return 最高温度
     * <p>原文：The maximum temperature
     */
    double getMaxTemperature();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最低温度.
     * <p>原文：
     * Gets the minimum temperature that is possible.
     *
     * @return 最低温度
     * <p>原文：The minimum temperature
     */
    double getMinTemperature();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取噪声生成器建议的此点生物群系的湿度.
     * <p>原文：
     * Gets the humidity of the biome at this point that is suggested by the
     * NoiseGenerator.
     *
     * @return 此点生物群系的湿度
     * <p>原文：The humidity of the biome at this point
     */
    double getHumidity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最高湿度.
     * <p>原文：
     * Gets the maximum humidity that is possible.
     *
     * @return 最高湿度
     * <p>原文：The maximum humidity
     */
    double getMaxHumidity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最低湿度.
     * <p>原文：
     * Gets the minimum humidity that is possible.
     *
     * @return 最低湿度
     * <p>原文：The minimum humidity
     */
    double getMinHumidity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取噪声生成器建议的此点生物群系的大陆性.
     * <p>原文：
     * Gets the continentalness of the biome at this point that is suggested by
     * the NoiseGenerator.
     *
     * @return 此点生物群系的大陆性
     * <p>原文：The continentalness of the biome at this point
     */
    double getContinentalness();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最高大陆性.
     * <p>原文：
     * Gets the maximum continentalness that is possible.
     *
     * @return 最高大陆性
     * <p>原文：The maximum continentalness
     */
    double getMaxContinentalness();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最低大陆性.
     * <p>原文：
     * Gets the minimum continentalness that is possible.
     *
     * @return 最低大陆性
     * <p>原文：The minimum continentalness
     */
    double getMinContinentalness();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取噪声生成器建议的此点生物群系的侵蚀度.
     * <p>原文：
     * Gets the erosion of the biome at this point that is suggested by the
     * NoiseGenerator.
     *
     * @return 此点生物群系的侵蚀度
     * <p>原文：The erosion of the biome at this point
     */
    double getErosion();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最高侵蚀度.
     * <p>原文：
     * Gets the maximum erosion that is possible.
     *
     * @return 最高侵蚀度
     * <p>原文：The maximum erosion
     */
    double getMaxErosion();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最低侵蚀度.
     * <p>原文：
     * Gets the minimum erosion that is possible.
     *
     * @return 最低侵蚀度
     * <p>原文：The minimum erosion
     */
    double getMinErosion();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取噪声生成器建议的此点生物群系的深度.
     * <p>原文：
     * Gets the depth of the biome at this point that is suggested by the
     * NoiseGenerator.
     *
     * @return 此点生物群系的深度
     * <p>原文：The depth of the biome at this point
     */
    double getDepth();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最高深度.
     * <p>原文：
     * Gets the maximum depth that is possible.
     *
     * @return 最高深度
     * <p>原文：The maximum depth
     */
    double getMaxDepth();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最低深度.
     * <p>原文：
     * Gets the minimum depth that is possible.
     *
     * @return 最低深度
     * <p>原文：The minimum depth
     */
    double getMinDepth();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取噪声生成器建议的此点生物群系的奇异度.
     * <p>原文：
     * Gets the weirdness of the biome at this point that is suggested by the
     * NoiseGenerator.
     *
     * @return 此点生物群系的奇异度
     * <p>原文：The weirdness of the biome at this point
     */
    double getWeirdness();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最高奇异度.
     * <p>原文：
     * Gets the maximum weirdness that is possible.
     *
     * @return 最高奇异度
     * <p>原文：The maximum weirdness
     */
    double getMaxWeirdness();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可能的最低奇异度.
     * <p>原文：
     * Gets the minimum weirdness that is possible.
     *
     * @return 最低奇异度
     * <p>原文：The minimum weirdness
     */
    double getMinWeirdness();
}
