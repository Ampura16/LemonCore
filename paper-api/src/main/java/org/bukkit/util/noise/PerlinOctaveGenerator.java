package org.bukkit.util.noise;

import java.util.Random;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 使用无偏倍频创建一个柏林噪声.
 * <p>原文：
 * Creates perlin noise through unbiased octaves
 */
public class PerlinOctaveGenerator extends OctaveGenerator {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定的World创建一个PerlinOctaveGenerator.
     * <p>原文：
     * Creates a perlin octave generator for the given world
     *
     * @param world 创建这个Generator的World实例
     * <p>原文：World to construct this generator for
     * @param octaves 倍频值
     * <p>原文：Amount of octaves to create
     */
    public PerlinOctaveGenerator(@NotNull World world, int octaves) {
        this(new Random(world.getSeed()), octaves);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定的种子创建一个PerlinOctaveGenerator.
     * <p>原文：
     * Creates a perlin octave generator for the given world
     *
     * @param seed 创建这个Generator的long型种子
     * <p>原文：Seed to construct this generator for
     * @param octaves 倍频值
     * <p>原文：Amount of octaves to create
     */
    public PerlinOctaveGenerator(long seed, int octaves) {
        this(new Random(seed), octaves);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定的{@link Random}创建一个PerlinOctaveGenerator.
     * <p>原文：
     * Creates a perlin octave generator for the given {@link Random}
     *
     * @param rand 创建这个Generator的Random
     * <p>原文：Random object to construct this generator for
     * @param octaves 倍频值
     * <p>原文：Amount of octaves to create
     */
    public PerlinOctaveGenerator(@NotNull Random rand, int octaves) {
        super(createOctaves(rand, octaves));
    }

    @NotNull
    private static NoiseGenerator[] createOctaves(@NotNull Random rand, int octaves) {
        NoiseGenerator[] result = new NoiseGenerator[octaves];

        for (int i = 0; i < octaves; i++) {
            result[i] = new PerlinNoiseGenerator(rand);
        }

        return result;
    }
}
