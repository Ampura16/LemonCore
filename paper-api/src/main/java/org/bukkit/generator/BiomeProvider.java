package org.bukkit.generator;

import java.util.List;
import org.bukkit.block.Biome;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 用于提供生物群系的类.
 * <p>原文：
 * Class for providing biomes.
 */
public abstract class BiomeProvider {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回在指定位置应该存在的生物群系.
     * <p>原文：
     * Return the Biome which should be present at the provided location.
     * <p>
     * Notes:
     * <p>
     * This method <b>must</b> be completely thread safe and able to handle
     * multiple concurrent callers.
     * <p>
     * This method should only return biomes which are present in the list
     * returned by {@link #getBiomes(WorldInfo)}
     * <p>
     * This method should <b>never</b> return {@link Biome#CUSTOM}.
     *
     * @param worldInfo 生物群系将用于的世界的世界信息
     * <p>原文：The world info of the world the biome will be used for
     * @param x 世界原点的X坐标
     * <p>原文：The X-coordinate from world origin
     * @param y 世界原点的Y坐标
     * <p>原文：The Y-coordinate from world origin
     * @param z 世界原点的Z坐标
     * <p>原文：The Z-coordinate from world origin
     * @return 给定位置的生物群系
     * <p>原文：Biome for the given location
     */
    @NotNull
    public abstract Biome getBiome(@NotNull WorldInfo worldInfo, int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回在指定位置应该存在的生物群系.
     * <p>原文：
     * Return the Biome which should be present at the provided location.
     * <p>
     * Notes:
     * <p>
     * This method <b>must</b> be completely thread safe and able to handle
     * multiple concurrent callers.
     * <p>
     * This method should only return biomes which are present in the list
     * returned by {@link #getBiomes(WorldInfo)}
     * <p>
     * This method should <b>never</b> return {@link Biome#CUSTOM}.
     * Only this method is called if both this and
     * {@link #getBiome(WorldInfo, int, int, int)} are overridden.
     *
     * @param worldInfo 生物群系将用于的世界的世界信息
     * <p>原文：The world info of the world the biome will be used for
     * @param x 世界原点的X坐标
     * <p>原文：The X-coordinate from world origin
     * @param y 世界原点的Y坐标
     * <p>原文：The Y-coordinate from world origin
     * @param z 世界原点的Z坐标
     * <p>原文：The Z-coordinate from world origin
     * @param biomeParameterPoint 该位置默认提供的参数点（包含温度、湿度、
     *                       大陆性、侵蚀度、深度和奇异度）
     * <p>原文：The parameter point that is provided by default
     *                       for this location (contains temperature, humidity,
     *                       continentalness, erosion, depth and weirdness)
     * @return 给定位置的生物群系
     * <p>原文：Biome for the given location
     * @see #getBiome(WorldInfo, int, int, int)
     */
    @NotNull
    public Biome getBiome(@NotNull WorldInfo worldInfo, int x, int y, int z, @NotNull BiomeParameterPoint biomeParameterPoint) {
        return getBiome(worldInfo, x, y, z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回一个包含此 {@link BiomeProvider} 将用于给定世界的所有生物群系的列表.
     * <p>原文：
     * Returns a list with every biome the {@link BiomeProvider} will use for
     * the given world.
     * <p>
     * Notes:
     * <p>
     * This method only gets called once, when the world is loaded. Returning
     * another list or modifying the values from the initial returned list later
     * one, are not respected.
     * <p>
     * This method should <b>never</b> return a list which contains
     * {@link Biome#CUSTOM}.
     *
     * @param worldInfo 列表将用于的世界的世界信息
     * <p>原文：The world info of the world the list will be used for
     * @return 包含此 {@link BiomeProvider} 使用的所有生物群系的列表
     * <p>原文：A list with every biome the {@link BiomeProvider} uses
     */
    @NotNull
    public abstract List<Biome> getBiomes(@NotNull WorldInfo worldInfo);
}
