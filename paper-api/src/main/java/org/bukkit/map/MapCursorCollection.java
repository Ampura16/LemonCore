package org.bukkit.map;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示{@link MapCanvas}上的所有地图光标。与MapCanvas类似，MapCursorCollection与特定的{@link MapRenderer}相关联。
 * <p>原文：
 * Represents all the map cursors on a {@link MapCanvas}. Like MapCanvas, a
 * MapCursorCollection is linked to a specific {@link MapRenderer}.
 */
public final class MapCursorCollection {
    private List<MapCursor> cursors = new ArrayList<MapCursor>();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此集合中的光标数量。
     * <p>原文：
     * Get the amount of cursors in this collection.
     *
     * @return 此集合的大小。
     * <p>原文：The size of this collection.
     */
    public int size() {
        return cursors.size();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从此集合中获取一个光标。
     * <p>原文：
     * Get a cursor from this collection.
     *
     * @param index 光标的索引。
     * <p>原文：The index of the cursor.
     * @return 该MapCursor对象。
     * <p>原文：The MapCursor.
     */
    @NotNull
    public MapCursor getCursor(int index) {
        return cursors.get(index);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从集合中移除一个光标。
     * <p>原文：
     * Remove a cursor from the collection.
     *
     * @param cursor 要移除的MapCursor。
     * <p>原文：The MapCursor to remove.
     * @return 光标是否被成功移除。
     * <p>原文：Whether the cursor was removed successfully.
     */
    public boolean removeCursor(@NotNull MapCursor cursor) {
        return cursors.remove(cursor);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向集合中添加一个光标。
     * <p>原文：
     * Add a cursor to the collection.
     *
     * @param cursor 要添加的MapCursor。
     * <p>原文：The MapCursor to add.
     * @return 传入的MapCursor对象。
     * <p>原文：The MapCursor that was passed.
     */
    @NotNull
    public MapCursor addCursor(@NotNull MapCursor cursor) {
        cursors.add(cursor);
        return cursor;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向集合中添加一个光标。
     * <p>原文：
     * Add a cursor to the collection.
     *
     * @param x x坐标，范围从-128到127。
     * <p>原文：The x coordinate, from -128 to 127.
     * @param y y坐标，范围从-128到127。
     * <p>原文：The y coordinate, from -128 to 127.
     * @param direction 光标的朝向，范围从0到15。
     * <p>原文：The facing of the cursor, from 0 to 15.
     * @return 新添加的MapCursor对象。
     * <p>原文：The newly added MapCursor.
     */
    @NotNull
    public MapCursor addCursor(int x, int y, byte direction) {
        return addCursor(x, y, direction, (byte) 0, true);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向集合中添加一个光标。
     * <p>原文：
     * Add a cursor to the collection.
     *
     * @param x x坐标，范围从-128到127。
     * <p>原文：The x coordinate, from -128 to 127.
     * @param y y坐标，范围从-128到127。
     * <p>原文：The y coordinate, from -128 to 127.
     * @param direction 光标的朝向，范围从0到15。
     * <p>原文：The facing of the cursor, from 0 to 15.
     * @param type 地图光标的类型（颜色/样式）。
     * <p>原文：The type (color/style) of the map cursor.
     * @return 新添加的MapCursor对象。
     * <p>原文：The newly added MapCursor.
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    @NotNull
    public MapCursor addCursor(int x, int y, byte direction, byte type) {
        return addCursor(x, y, direction, type, true);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向集合中添加一个光标。
     * <p>原文：
     * Add a cursor to the collection.
     *
     * @param x x坐标，范围从-128到127。
     * <p>原文：The x coordinate, from -128 to 127.
     * @param y y坐标，范围从-128到127。
     * <p>原文：The y coordinate, from -128 to 127.
     * @param direction 光标的朝向，范围从0到15。
     * <p>原文：The facing of the cursor, from 0 to 15.
     * @param type 地图光标的类型（颜色/样式）。
     * <p>原文：The type (color/style) of the map cursor.
     * @param visible 光标是否可见。
     * <p>原文：Whether the cursor is visible.
     * @return 新添加的MapCursor对象。
     * <p>原文：The newly added MapCursor.
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    @NotNull
    public MapCursor addCursor(int x, int y, byte direction, byte type, boolean visible) {
        return addCursor(new MapCursor((byte) x, (byte) y, direction, type, visible));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向集合中添加一个光标。
     * <p>原文：
     * Add a cursor to the collection.
     *
     * @param x x坐标，范围从-128到127。
     * <p>原文：The x coordinate, from -128 to 127.
     * @param y y坐标，范围从-128到127。
     * <p>原文：The y coordinate, from -128 to 127.
     * @param direction 光标的朝向，范围从0到15。
     * <p>原文：The facing of the cursor, from 0 to 15.
     * @param type 地图光标的类型（颜色/样式）。
     * <p>原文：The type (color/style) of the map cursor.
     * @param visible 光标是否可见。
     * <p>原文：Whether the cursor is visible.
     * @param caption 横幅标题
     * <p>原文：banner caption
     * @return 新添加的MapCursor对象。
     * <p>原文：The newly added MapCursor.
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.13")
    @NotNull
    public MapCursor addCursor(int x, int y, byte direction, byte type, boolean visible, @Nullable String caption) {
        return addCursor(new MapCursor((byte) x, (byte) y, direction, type, visible, caption));
    }
    // Paper start
    /**
     * Add a cursor to the collection.
     *
     * @param x The x coordinate, from -128 to 127.
     * @param y The y coordinate, from -128 to 127.
     * @param direction The facing of the cursor, from 0 to 15.
     * @param type The type (color/style) of the map cursor.
     * @param visible Whether the cursor is visible.
     * @param caption banner caption
     * @return The newly added MapCursor.
     * @deprecated Magic value
     */
    @Deprecated
    public @NotNull MapCursor addCursor(int x, int y, byte direction, byte type, boolean visible, net.kyori.adventure.text.@Nullable Component caption) {
        return addCursor(new MapCursor((byte) x, (byte) y, direction, type, visible, caption));
    }
    // Paper end
}
