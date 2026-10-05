package org.bukkit.map;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示可在地图上绘制的位图字体。
 * <p>原文：
 * Represents a bitmap font drawable to a map.
 */
public class MapFont {

    private final HashMap<Character, CharacterSprite> chars = new HashMap<Character, CharacterSprite>();
    private int height = 0;
    protected boolean malleable = true;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为给定字符设置精灵图。
     * <p>原文：
     * Set the sprite for a given character.
     *
     * @param ch 要设置精灵图的字符。
     * <p>原文：The character to set the sprite for.
     * @param sprite 要设置的CharacterSprite。
     * <p>原文：The CharacterSprite to set.
     * @throws IllegalStateException 如果此字体是静态的。
     * <p>原文：if this font is static.
     */
    public void setChar(char ch, @NotNull CharacterSprite sprite) {
        if (!malleable) {
            throw new IllegalStateException("this font is not malleable");
        }

        chars.put(ch, sprite);
        if (sprite.getHeight() > height) {
            height = sprite.getHeight();
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定字符的精灵图。
     * <p>原文：
     * Get the sprite for a given character.
     *
     * @param ch 要获取精灵图的字符。
     * <p>原文：The character to get the sprite for.
     * @return 与该字符关联的CharacterSprite，如果没有则返回null。
     * <p>原文：The CharacterSprite associated with the character, or null if
     *     there is none.
     */
    @Nullable
    public CharacterSprite getChar(char ch) {
        return chars.get(ch);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取使用此字体渲染给定文本时的宽度。
     * <p>原文：
     * Get the width of the given text as it would be rendered using this
     * font.
     *
     * @param text 文本。
     * <p>原文：The text.
     * @return 宽度（像素）。
     * <p>原文：The width in pixels.
     */
    public int getWidth(@NotNull String text) {
        if (!isValid(text)) {
            throw new IllegalArgumentException("text contains invalid characters");
        }

        if (text.length() == 0) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < text.length(); ++i) {
            char ch = text.charAt(i);
            if (ch == ChatColor.COLOR_CHAR) {
                int j = text.indexOf(';', i);
                if (j >= 0) {
                    i = j;
                    continue;
                }
                throw new IllegalArgumentException("Text contains unterminated color string");
            }
            result += chars.get(ch).getWidth();
        }
        result += text.length() - 1; // Account for 1px spacing between characters

        return result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此字体的高度。
     * <p>原文：
     * Get the height of this font.
     *
     * @return 字体的高度。
     * <p>原文：The height of the font.
     */
    public int getHeight() {
        return height;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查给定文本是否有效。
     * <p>原文：
     * Check whether the given text is valid.
     *
     * @param text 文本。
     * <p>原文：The text.
     * @return 如果字符串仅包含已定义字符则返回true，否则返回false。
     * <p>原文：True if the string contains only defined characters, false
     *     otherwise.
     */
    public boolean isValid(@NotNull String text) {
        for (int i = 0; i < text.length(); ++i) {
            char ch = text.charAt(i);
            if (ch == ChatColor.COLOR_CHAR || ch == '\n') continue;
            if (chars.get(ch) == null) return false;
        }
        return true;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示MapFont中单个字符的图形。
     * <p>原文：
     * Represents the graphics for a single character in a MapFont.
     */
    public static class CharacterSprite {

        private final int width;
        private final int height;
        private final boolean[] data;

        public CharacterSprite(int width, int height, boolean @NotNull [] data) {
            this.width = width;
            this.height = height;
            this.data = data;

            if (data.length != width * height) {
                throw new IllegalArgumentException("size of data does not match dimensions");
            }
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取字符像素的值。
         * <p>原文：
         * Get the value of a pixel of the character.
         *
         * @param row 行，范围为[0,8)。
         * <p>原文：The row, in the range [0,8).
         * @param col 列，范围为[0,8)。
         * <p>原文：The column, in the range [0,8).
         * @return 如果像素是实心的则返回true，如果是透明的则返回false。
         * <p>原文：True if the pixel is solid, false if transparent.
         */
        public boolean get(int row, int col) {
            if (row < 0 || col < 0 || row >= height || col >= width) return false;
            return data[row * width + col];
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取字符精灵图的宽度。
         * <p>原文：
         * Get the width of the character sprite.
         *
         * @return 字符的宽度。
         * <p>原文：The width of the character.
         */
        public int getWidth() {
            return width;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取字符精灵图的高度。
         * <p>原文：
         * Get the height of the character sprite.
         *
         * @return 字符的高度。
         * <p>原文：The height of the character.
         */
        public int getHeight() {
            return height;
        }

    }

}
