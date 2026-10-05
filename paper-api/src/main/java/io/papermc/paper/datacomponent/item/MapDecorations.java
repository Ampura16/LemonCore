package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import java.util.Map;
import org.bukkit.map.MapCursor;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有要放置在已填充地图上的标记列表（用于探险家地图）。
 * <p>原文：
 * Holds a list of markers to be placed on a Filled Map (used for Explorer Maps).
 * @see io.papermc.paper.datacomponent.DataComponentTypes#MAP_DECORATIONS
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface MapDecorations {

    @Contract(value = "_ -> new", pure = true)
    static MapDecorations mapDecorations(final Map<String, DecorationEntry> entries) {
        return mapDecorations().putAll(entries).build();
    }

    @Contract(value = "-> new", pure = true)
    static MapDecorations.Builder mapDecorations() {
        return ItemComponentTypesBridge.bridge().mapDecorations();
    }

    @Contract(value = "_, _, _, _ -> new", pure = true)
    static DecorationEntry decorationEntry(final MapCursor.Type type, final double x, final double z, final float rotation) {
        return ItemComponentTypesBridge.bridge().decorationEntry(type, x, z, rotation);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取具有给定 ID 的装饰条目。
     * <p>原文：
     * Gets the decoration entry with the given id.
     *
     * @param id ID
     * <p>原文：id
     * @return 装饰条目；如果不存在则为 {@code null}
     * <p>原文：decoration entry, or {@code null} if not present
     */
    @Contract(pure = true)
    @Nullable DecorationEntry decoration(String id);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取装饰条目。
     * <p>原文：
     * Gets the decoration entries.
     *
     * @return 装饰条目
     * <p>原文：the decoration entries
     */
    @Contract(pure = true)
    @Unmodifiable Map<String, DecorationEntry> decorations();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 地图上的装饰。
     * <p>原文：
     * Decoration present on the map.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface DecorationEntry {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 装饰的类型。
         * <p>原文：
         * Type of decoration.
         *
         * @return 类型
         * <p>原文：type
         */
        @Contract(pure = true)
        MapCursor.Type type();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 装饰的 X 世界坐标。
         * <p>原文：
         * X world coordinate of the decoration.
         *
         * @return x 坐标
         * <p>原文：x coordinate
         */
        @Contract(pure = true)
        double x();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 装饰的 Z 世界坐标。
         * <p>原文：
         * Z world coordinate of the decoration.
         *
         * @return z 坐标
         * <p>原文：z coordinate
         */
        @Contract(pure = true)
        double z();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 从北方起顺时针旋转的角度（度）。
         * <p>原文：
         * Clockwise rotation from north in degrees.
         *
         * @return 旋转
         * <p>原文：rotation
         */
        @Contract(pure = true)
        float rotation();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link MapDecorations} 的构建器。
     * <p>原文：
     * Builder for {@link MapDecorations}.
     */
    @ApiStatus.NonExtendable
    @ApiStatus.Experimental
    interface Builder extends DataComponentBuilder<MapDecorations> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将具有给定 ID 的装饰放入此构建器。
         * <p>原文：
         * Puts the decoration with the given id in this builder.
         *
         * @param id ID
         * <p>原文：id
         * @param entry 装饰
         * <p>原文：decoration
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #decorations()
         */
        @Contract(value = "_, _ -> this", mutates = "this")
        MapDecorations.Builder put(String id, DecorationEntry entry);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将具有给定 ID 的所有装饰放入此构建器。
         * <p>原文：
         * Puts all the decoration with the given id in this builder.
         *
         * @param entries 装饰
         * <p>原文：decorations
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #decorations()
         */
        @Contract(value = "_ -> this", mutates = "this")
        MapDecorations.Builder putAll(Map<String, DecorationEntry> entries);
    }
}
