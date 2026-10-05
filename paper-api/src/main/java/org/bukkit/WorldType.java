package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Locale;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表世界的种类.
 * <p>原文：
 * Represents various types of worlds that may exist
 */
public enum WorldType {
    NORMAL("DEFAULT"),
    FLAT("FLAT"),
    LARGE_BIOMES("LARGEBIOMES"),
    AMPLIFIED("AMPLIFIED");

    private static final Map<String, WorldType> BY_NAME = Maps.newHashMap();
    private final String name;

    private WorldType(/*@NotNull*/ String name) {
        this.name = name;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界种类名称.
     * <p>原文：
     * Gets the name of this WorldType
     *
     * @return 世界种类名称
     * <p>原文：Name of this type
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用世界种类名获取世界种类.
     * <p>原文：
     * Gets a WorldType by its name
     *
     * @param name 世界种类名
     * <p>原文：Name of the WorldType to get
     * @return 世界种类，不存在就为null
     * <p>原文：Requested WorldType, or null if not found
     */
    @Nullable
    public static WorldType getByName(@NotNull String name) {
        return BY_NAME.get(name.toUpperCase(Locale.ROOT));
    }

    static {
        for (WorldType type : values()) {
            BY_NAME.put(type.name, type);
        }
    }
}
