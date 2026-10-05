package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同的树木种类.
 * <p>原文：
 * Represents the different species of trees regardless of size.
 *
 * @deprecated 已弃用, see usage methods for replacement(s)
 * <p>原文：Deprecated, see usage methods for replacement(s)
 */
@Deprecated(since = "1.19", forRemoval = true)
public enum TreeSpecies {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表普通树种(橡树).
     * <p>原文：
     * Represents the common tree species.
     */
    GENERIC(0x0),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表叶子颜色较暗的树.
     * <p>原文：
     * Represents the darker barked/leaved tree species.
     */
    REDWOOD(0x1),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表桦树.
     * <p>原文：
     * Represents birches.
     */
    BIRCH(0x2),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表丛林树.
     * <p>原文：
     * Represents jungle trees.
     */
    JUNGLE(0x3),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表金合欢树.
     * <p>原文：
     * Represents acacia trees.
     */
    ACACIA(0x4),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表深色橡木树.
     * <p>原文：
     * Represents dark oak trees.
     */
    DARK_OAK(0x5),
    ;

    private final byte data;
    private static final Map<Byte, TreeSpecies> BY_DATA = Maps.newHashMap();

    private TreeSpecies(final int data) {
        this.data = (byte) data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该树种相关的数据值。
     * <p>原文：
     * Gets the associated data value representing this species
     *
     * @return 包含该物种相关数据值的字节
     * <p>原文：A byte containing the data value of this tree species
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public byte getData() {
        return data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定值代表的树种。
     * <p>原文：
     * Gets the TreeSpecies with the given data value
     *
     * @param data 要获取的数据值
     * <p>原文：Data value to fetch
     * @return {@link TreeSpecies}所代表的值，如果不存在则为null
     * <p>原文：The {@link TreeSpecies} representing the given value, or null
     *     if it doesn't exist
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    @Nullable
    public static TreeSpecies getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (TreeSpecies species : values()) {
            BY_DATA.put(species.data, species);
        }
    }
}
