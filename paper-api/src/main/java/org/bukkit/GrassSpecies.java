package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同种类的草丛.
 * <p>原文：
 * Represents the different types of grass.
 *
 * @deprecated use {@link org.bukkit.block.data.BlockData}
 */
@Deprecated(forRemoval = true, since = "1.13")
public enum GrassSpecies {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表枯死的灌木.
     * <p>原文：
     * Represents the dead looking grass.
     */
    DEAD(0x0),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表普通种类的草丛.
     * <p>原文：
     * Represents the normal grass species.
     */
    NORMAL(0x1),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表蕨类草丛.
     * <p>原文：
     * Represents the fern-looking grass species.
     */
    FERN_LIKE(0x2);

    private final byte data;
    private static final Map<Byte, GrassSpecies> BY_DATA = Maps.newHashMap();

    private GrassSpecies(final int data) {
        this.data = (byte) data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取相关数据代表的品种.
     * <p>原文：
     * Gets the associated data value representing this species
     *
     * @return 包含这种草丛的数据值字节
     * <p>原文：A byte containing the data value of this grass species
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public byte getData() {
        return data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用给定值获取草丛品种.
     * <p>原文：
     * Gets the GrassSpecies with the given data value
     *
     * @param data 要获取的数据值
     * <p>原文：Data value to fetch
     * @return 给定值代表的草丛品种({@link GrassSpecies})，如果不存在则为null
     * <p>原文：The {@link GrassSpecies} representing the given value, or null
     *     if it doesn't exist
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    @Nullable
    public static GrassSpecies getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (GrassSpecies grassSpecies : values()) {
            BY_DATA.put(grassSpecies.getData(), grassSpecies);
        }
    }
}
