package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表三种不同的沙石.
 * <p>原文：
 * Represents the three different types of Sandstone
 *
 * @deprecated use {@link org.bukkit.block.data.BlockData}
 */
@Deprecated(forRemoval = true, since = "1.13")
public enum SandstoneType {
    CRACKED(0x0),
    GLYPHED(0x1),
    SMOOTH(0x2);

    private final byte data;
    private static final Map<Byte, SandstoneType> BY_DATA = Maps.newHashMap();

    private SandstoneType(final int data) {
        this.data = (byte) data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取沙石的数据值.
     * <p>原文：
     * Gets the associated data value representing this type of sandstone
     *
     * @return 沙石的数据值
     * <p>原文：A byte containing the data value of this sandstone type
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public byte getData() {
        return data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用数据值获取沙石种类.
     * <p>原文：
     * Gets the type of sandstone with the given data value
     *
     * @param data 数据值
     * <p>原文：Data value to fetch
     * @return 用给定的值获取到的{@link SandstoneType},如不存在就为null,
     * <p>原文：The {@link SandstoneType} representing the given value, or null
     *     if it doesn't exist
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    @Nullable
    public static SandstoneType getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (SandstoneType type : values()) {
            BY_DATA.put(type.data, type);
        }
    }
}
