package org.bukkit.map;

import java.awt.Color;
import java.awt.Image;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个画地图的画布，每个画布都关联着一个特定的
 * {@link MapRenderer 地图渲染器}并表现为地图上渲染器的一层.
 * <p>原文：
 * Represents a canvas for drawing to a map. Each canvas is associated with a
 * specific {@link MapRenderer} and represents that renderer's layer on the
 * map.
 */
public interface MapCanvas {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取画布依附的地图.
     * <p>原文：
     * Get the map this canvas is attached to.
     *
     * @return 画布依附的地图控件
     * <p>原文：The MapView this canvas is attached to.
     */
    @NotNull
    public MapView getMapView();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与该画布相关联的游标集合.
     * <p>原文：
     * Get the cursor collection associated with this canvas.
     *
     * @return 与该画布相关联的游标集合
     * <p>原文：The MapCursorCollection associated with this canvas.
     */
    @NotNull
    public MapCursorCollection getCursors();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置与该画布相关联的游标集合,自从地图游标集合(MapCursorCollection)提供后这个方法通常不需要被调用.
     * <p>原文：
     * Set the cursor collection associated with this canvas. This does not
     * usually need to be called since a MapCursorCollection is already
     * provided.
     *
     * @param cursors 与画布相关联的游标集合
     * <p>原文：The MapCursorCollection to associate with this canvas.
     */
    public void setCursors(@NotNull MapCursorCollection cursors);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向画布绘制一个像素。
     * <p>原文：
     * Draw a pixel to the canvas.
     * <p>
     * The provided color might be converted to another color,
     * which is in the map color range. This means, that
     * {@link #getPixelColor(int, int)} might return another
     * color than set.
     *
     * If null is used as color, then the color returned by
     * {@link #getBasePixelColor(int, int)} is shown on the map.
     *
     * @param x x 坐标，从 0 到 127。
     * <p>原文：The x coordinate, from 0 to 127.
     * @param y y 坐标，从 0 到 127。
     * <p>原文：The y coordinate, from 0 to 127.
     * @param color 颜色。
     * <p>原文：The color.
     */
    void setPixelColor(int x, int y, @Nullable Color color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从画布获取一个像素。
     * <p>原文：
     * Get a pixel from the canvas.
     *
     * If no color is set at the given position for this canvas, then null is
     * returned and the color returned by {@link #getBasePixelColor(int, int)}
     * is shown on the map.
     *
     * @param x x 坐标，从 0 到 127。
     * <p>原文：The x coordinate, from 0 to 127.
     * @param y y 坐标，从 0 到 127。
     * <p>原文：The y coordinate, from 0 to 127.
     * @return 颜色，如果未设置颜色则为 null。
     * <p>原文：The color, or null if no color is set.
     */
    @Nullable
    Color getPixelColor(int x, int y);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从画布下方的图层获取一个像素。
     * <p>原文：
     * Get a pixel from the layers below this canvas.
     *
     * @param x x 坐标，从 0 到 127。
     * <p>原文：The x coordinate, from 0 to 127.
     * @param y y 坐标，从 0 到 127。
     * <p>原文：The y coordinate, from 0 to 127.
     * @return 颜色。
     * <p>原文：The color.
     */
    @NotNull
    Color getBasePixelColor(int x, int y);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 画一个像素到画布上.
     * <p>原文：
     * Draw a pixel to the canvas.
     *
     * @param x x坐标值,从0到127
     * <p>原文：The x coordinate, from 0 to 127.
     * @param y y坐标值,从0到127
     * <p>原文：The y coordinate, from 0 to 127.
     * @param color 颜色,参考{@link MapPalette}
     * <p>原文：The color. See {@link MapPalette}.
     * @deprecated 魔法值, 请使用 {@link #setPixelColor(int, int, Color)}
     * <p>原文：Magic value, use {@link #setPixelColor(int, int, Color)}
     */
    @Deprecated(since = "1.20.5")
    public void setPixel(int x, int y, byte color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取画布上的一个像素.
     * <p>原文：
     * Get a pixel from the canvas.
     *
     * @param x x坐标值,从0到127
     * <p>原文：The x coordinate, from 0 to 127.
     * @param y y坐标值,从0到127
     * <p>原文：The y coordinate, from 0 to 127.
     * @return 颜色. 参阅 {@link MapPalette}.
     * <p>原文：The color. See {@link MapPalette}.
     * @deprecated 魔法值, 请使用 {@link #getPixelColor(int, int)}
     * <p>原文：use {@link #getPixelColor(int, int)}
     */
    @Deprecated(since = "1.19", forRemoval = true) // Paper
    public byte getPixel(int x, int y);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取画布下层的一个像素.
     * <p>原文：
     * Get a pixel from the layers below this canvas.
     *
     * @param x x坐标值,从0到127
     * <p>原文：The x coordinate, from 0 to 127.
     * @param y y坐标值,从0到127
     * <p>原文：The y coordinate, from 0 to 127.
     * @return 颜色. 参阅 {@link MapPalette}.
     * <p>原文：The color. See {@link MapPalette}.
     * @deprecated 魔法值, 请使用 {@link #getBasePixelColor(int, int)}
     * <p>原文：use {@link #getBasePixelColor(int, int)}
     */
    @Deprecated(since = "1.19", forRemoval = true) // Paper
    public byte getBasePixel(int x, int y);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 画一张图片到地图上,如果必要的话,图像将被裁剪.
     * <p>原文：
     * Draw an image to the map. The image will be clipped if necessary.
     *
     * @param x 图片的x坐标
     * <p>原文：The x coordinate of the image.
     * @param y 图片的y坐标
     * <p>原文：The y coordinate of the image.
     * @param image 要画的图片
     * <p>原文：The Image to draw.
     */
    public void drawImage(int x, int y, @NotNull Image image);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用你想要的格式渲染文字到地图上.换行符(\n)会移动到下一行并返回原来的列,
     * 字体颜色可以使用带字符的数字来改变如"§12;",这会把字体颜色换为颜色表中
     * 第12个颜色(参考 {@link MapPalette}).
     * <p>原文：
     * Render text to the map using fancy formatting. Newline (\n) characters
     * will move down one line and return to the original column, and the text
     * color can be changed using sequences such as "§12;", replacing 12 with
     * the palette index of the color (see {@link MapPalette}).
     *
     * @param x 开始渲染的列
     * <p>原文：The column to start rendering on.
     * @param y 开始渲染的行
     * <p>原文：The row to start rendering on.
     * @param font 使用的字体
     * <p>原文：The font to use.
     * @param text 要渲染的格式化文字
     * <p>原文：The formatted text to render.
     */
    public void drawText(int x, int y, @NotNull MapFont font, @NotNull String text);

}
