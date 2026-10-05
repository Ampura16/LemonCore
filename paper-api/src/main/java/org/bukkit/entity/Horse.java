package org.bukkit.entity;

import org.bukkit.inventory.HorseInventory;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一匹马.
 * <p>原文：
 * Represents a Horse.
 */
public interface Horse extends AbstractHorse {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @deprecated 不同的种类对应不同的类.
     * <p>原文：different variants are differing classes
     */
    @Deprecated(since = "1.11")
    public enum Variant {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 普通马.
         * <p>原文：
         * A normal horse
         */
        HORSE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 驴.
         * <p>原文：
         * A donkey
         */
        DONKEY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 骡子.
         * <p>原文：
         * A mule
         */
        MULE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 不死马(僵尸马).
         * <p>原文：
         * An undead horse
         */
        UNDEAD_HORSE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 骷髅马.
         * <p>原文：
         * A skeleton horse
         */
        SKELETON_HORSE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 不是真的马:(
         * <p>原文：
         * Not really a horse :)
         */
        LLAMA,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 这也是个假货 :)
         * <p>原文：
         * Also not really a horse :)
         */
        CAMEL,
        ;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示马拥有的基本颜色.
     * <p>原文：
     * Represents the base color that the horse has.
     */
    public enum Color {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 雪白色
         * <p>原文：
         * Snow white
         */
        WHITE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 奶油色
         * <p>原文：
         * Very light brown
         */
        CREAMY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 栗色
         * <p>原文：
         * Chestnut
         */
        CHESTNUT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 褐色
         * <p>原文：
         * Light brown
         */
        BROWN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 黑色
         * <p>原文：
         * Pitch black
         */
        BLACK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 灰色
         * <p>原文：
         * Gray
         */
        GRAY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 深褐色
         * <p>原文：
         * Dark brown
         */
        DARK_BROWN,
        ;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表马的花纹图案.
     * <p>原文：
     * Represents the style, or markings, that the horse has.
     */
    public enum Style {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 无花纹
         * <p>原文：
         * No markings
         */
        NONE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 白短袜或条纹
         * <p>原文：
         * White socks or stripes
         */
        WHITE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 乳白色斑点
         * <p>原文：
         * Milky splotches
         */
        WHITEFIELD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 圆白点
         * <p>原文：
         * Round white dots
         */
        WHITE_DOTS,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 小黑点
         * <p>原文：
         * Small black dots
         */
        BLACK_DOTS,
        ;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取马的颜色.
     * <p>
     * 该颜色只适用于马,而不适用于驴,骡子,骷髅马或僵尸马.
     * <p>原文：
     * Gets the horse's color.
     * <p>
     * Colors only apply to horses, not to donkeys, mules, skeleton horses
     * or undead horses.
     *
     * @return {@link Color} 马的颜色.
     * <p>原文：a {@link Color} representing the horse's group
     */
    @NotNull
    public Color getColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置马的颜色.
     * <p>
     * 试图为任何驴,骡子,骷髅马或僵尸马设定颜色不会有任何变化.
     * <p>原文：
     * Sets the horse's color.
     * <p>
     * Attempting to set a color for any donkey, mule, skeleton horse or
     * undead horse will not result in a change.
     *
     * @param color 马的{@link Color 颜色}
     * <p>原文：a {@link Color} for this horse
     */
    public void setColor(@NotNull Color color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到马的外貌特征.
     * 这些特征决定马有什么样的图案.
     * <p>
     * 这些特征只适用于马,而不适用于驴,骡子,骷髅马或僵尸马.
     * <p>原文：
     * Gets the horse's style.
     * Styles determine what kind of markings or patterns a horse has.
     * <p>
     * Styles only apply to horses, not to donkeys, mules, skeleton horses
     * or undead horses.
     *
     * @return 马的{@link Style 花纹与图案}
     * <p>原文：a {@link Style} representing the horse's style
     */
    @NotNull
    public Style getStyle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置马的外貌特征. 风格决定马有什么样的标记或图案.
     * <p>
     * 试图为任何驴,骡子,骷髅马或僵尸马设定这些特征不会有任何变化.
     * <p>原文：
     * Sets the style of this horse.
     * Styles determine what kind of markings or patterns a horse has.
     * <p>
     * Attempting to set a style for any donkey, mule, skeleton horse or
     * undead horse will not result in a change.
     *
     * @param style 马的{@link Style 花纹与图案}
     * <p>原文：a {@link Style} for this horse
     */
    public void setStyle(@NotNull Style style);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 是否携带箱子
     * <p>原文：carrying chest status
     * @deprecated 请参阅 {@link ChestedHorse}
     * <p>原文：see {@link ChestedHorse}
     */
    @Deprecated(since = "1.11", forRemoval = true)
    public boolean isCarryingChest();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param chest 是否携带箱子
     * <p>原文：chest
     * @deprecated 请参阅 {@link ChestedHorse}
     * <p>原文：see {@link ChestedHorse}
     */
    @Deprecated(since = "1.11", forRemoval = true)
    public void setCarryingChest(boolean chest);

    @NotNull
    @Override
    public HorseInventory getInventory();
}
