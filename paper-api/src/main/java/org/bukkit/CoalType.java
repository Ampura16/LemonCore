package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表两种煤.
 * <p>原文：
 * Represents the two types of coal
 */
@Deprecated(forRemoval = true, since = "1.13")
public enum CoalType {
    COAL(0x0),
    CHARCOAL(0x1);

    private final byte data;
    private static final Map<Byte, CoalType> BY_DATA = Maps.newHashMap();

    private CoalType(final int data) {
        this.data = (byte) data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取表示该类型的煤的相关数据值.
     * <p>原文：
     * Gets the associated data value representing this type of coal
     *
     * @return 煤的数据值
     * <p>原文：A byte containing the data value of this coal type
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public byte getData() {
        return data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用数据值获取煤的类型.
     * <p>原文：
     * Gets the type of coal with the given data value
     *
     * @param data 要获取的煤的类型
     * <p>原文：Data value to fetch
     * @return {@link CoalType} 代表给定的值, 如果不存在则为null
     * <p>原文：The {@link CoalType} representing the given value, or null if
     *     it doesn't exist
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    @Nullable
    public static CoalType getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (CoalType type : values()) {
            BY_DATA.put(type.data, type);
        }
    }
}
