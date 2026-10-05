package org.bukkit;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 染料和布料所有支持的颜色值.
 * <p>原文：
 * All supported color values for dyes and cloth
 */
public enum DyeColor {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表白色染料.
     * <p>原文：
     * Represents white dye.
     */
    WHITE(0x0, 0xF, Color.fromRGB(0xF9FFFE), Color.fromRGB(0xF0F0F0)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表橙色染料.
     * <p>原文：
     * Represents orange dye.
     */
    ORANGE(0x1, 0xE, Color.fromRGB(0xF9801D), Color.fromRGB(0xEB8844)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表品红色染料.
     * <p>原文：
     * Represents magenta dye.
     */
    MAGENTA(0x2, 0xD, Color.fromRGB(0xC74EBD), Color.fromRGB(0xC354CD)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表淡蓝色染料.
     * <p>原文：
     * Represents light blue dye.
     */
    LIGHT_BLUE(0x3, 0xC, Color.fromRGB(0x3AB3DA), Color.fromRGB(0x6689D3)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表黄色染料.
     * <p>原文：
     * Represents yellow dye.
     */
    YELLOW(0x4, 0xB, Color.fromRGB(0xFED83D), Color.fromRGB(0xDECF2A)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表黄绿色染料.
     * <p>原文：
     * Represents lime dye.
     */
    LIME(0x5, 0xA, Color.fromRGB(0x80C71F), Color.fromRGB(0x41CD34)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表粉色染料.
     * <p>原文：
     * Represents pink dye.
     */
    PINK(0x6, 0x9, Color.fromRGB(0xF38BAA), Color.fromRGB(0xD88198)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表灰色染料.
     * <p>原文：
     * Represents gray dye.
     */
    GRAY(0x7, 0x8, Color.fromRGB(0x474F52), Color.fromRGB(0x434343)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表淡灰色染料.
     * <p>原文：
     * Represents light gray dye.
     */
    LIGHT_GRAY(0x8, 0x7, Color.fromRGB(0x9D9D97), Color.fromRGB(0xABABAB)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表青色染料.
     * <p>原文：
     * Represents cyan dye.
     */
    CYAN(0x9, 0x6, Color.fromRGB(0x169C9C), Color.fromRGB(0x287697)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表紫色染料.
     * <p>原文：
     * Represents purple dye.
     */
    PURPLE(0xA, 0x5, Color.fromRGB(0x8932B8), Color.fromRGB(0x7B2FBE)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表蓝色染料.
     * <p>原文：
     * Represents blue dye.
     */
    BLUE(0xB, 0x4, Color.fromRGB(0x3C44AA), Color.fromRGB(0x253192)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表棕色染料.
     * <p>原文：
     * Represents brown dye.
     */
    BROWN(0xC, 0x3, Color.fromRGB(0x835432), Color.fromRGB(0x51301A)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表绿色染料.
     * <p>原文：
     * Represents green dye.
     */
    GREEN(0xD, 0x2, Color.fromRGB(0x5E7C16), Color.fromRGB(0x3B511A)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表红色染料.
     * <p>原文：
     * Represents red dye.
     */
    RED(0xE, 0x1, Color.fromRGB(0xB02E26), Color.fromRGB(0xB3312C)),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表黑色染料.
     * <p>原文：
     * Represents black dye.
     */
    BLACK(0xF, 0x0, Color.fromRGB(0x1D1D21), Color.fromRGB(0x1E1B1B));

    private final byte woolData;
    private final byte dyeData;
    private final Color color;
    private final Color firework;
    private static final DyeColor[] BY_WOOL_DATA;
    private static final DyeColor[] BY_DYE_DATA;
    private static final Map<Color, DyeColor> BY_COLOR;
    private static final Map<Color, DyeColor> BY_FIREWORK;

    private DyeColor(final int woolData, final int dyeData, /*@NotNull*/ Color color, /*@NotNull*/ Color firework) {
        this.woolData = (byte) woolData;
        this.dyeData = (byte) dyeData;
        this.color = color;
        this.firework = firework;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个颜色代表的相关羊毛的数据值.
     * <p>原文：
     * Gets the associated wool data value representing this color.
     *
     * @return 包含这种颜色的羊毛的数据值字节
     * <p>原文：A byte containing the wool data value of this color
     * @see #getDyeData()
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    public byte getWoolData() {
        return woolData;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取代表这种颜色的染料数据值.
     * <p>原文：
     * Gets the associated dye data value representing this color.
     *
     * @return 包含这种颜色的染料数据值字节
     * <p>原文：A byte containing the dye data value of this color
     * @see #getWoolData()
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public byte getDyeData() {
        return dyeData;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个染料代表的颜色.
     * <p>原文：
     * Gets the color that this dye represents.
     *
     * @return 染料代表的颜色({@link Color})
     * <p>原文：The {@link Color} that this dye represents
     */
    @NotNull
    public Color getColor() {
        return color;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个染料代表的烟花颜色.
     * <p>原文：
     * Gets the firework color that this dye represents.
     *
     * @return 染料代表的颜色({@link Color})
     * <p>原文：The {@link Color} that this dye represents
     */
    @NotNull
    public Color getFireworkColor() {
        return firework;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定的羊毛数据值的染料颜色.
     * <p>原文：
     * Gets the DyeColor with the given wool data value.
     *
     * @param data 将要获取的羊毛数据值
     * <p>原文：Wool data value to fetch
     * @return 给定值代表的染料颜色({@link DyeColor})，如果不存在则为null
     * <p>原文：The {@link DyeColor} representing the given value, or null if
     *     it doesn't exist
     * @see #getByDyeData(byte)
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    @Nullable
    public static DyeColor getByWoolData(final byte data) {
        int i = 0xff & data;
        if (i >= BY_WOOL_DATA.length) {
            return null;
        }
        return BY_WOOL_DATA[i];
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用给定的数据值获取DyeColor.
     * <p>原文：
     * Gets the DyeColor with the given dye data value.
     *
     * @param data 要获取的染料数据值
     * <p>原文：Dye data value to fetch
     * @return 给定值代表的染料颜色({@link DyeColor})，如果不存在则为null
     * <p>原文：The {@link DyeColor} representing the given value, or null if
     *     it doesn't exist
     * @see #getByWoolData(byte)
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    @Nullable
    public static DyeColor getByDyeData(final byte data) {
        int i = 0xff & data;
        if (i >= BY_DYE_DATA.length) {
            return null;
        }
        return BY_DYE_DATA[i];
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定的颜色值的染料颜色.
     * <p>原文：
     * Gets the DyeColor with the given color value.
     *
     * @param color 要获取的染料的颜色值
     * <p>原文：Color value to get the dye by
     * @return 给定值代表的染料颜色({@link DyeColor})，如果不存在则为null
     * <p>原文：The {@link DyeColor} representing the given value, or null if
     *     it doesn't exist
     */
    @Nullable
    public static DyeColor getByColor(@NotNull final Color color) {
        return BY_COLOR.get(color);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定的颜色颜色值的染料颜色.
     * <p>原文：
     * Gets the DyeColor with the given firework color value.
     *
     * @param color 要获取的染料的颜色值
     * <p>原文：Color value to get dye by
     * @return 给定值代表的染料颜色({@link DyeColor})，如果不存在则为null
     * <p>原文：The {@link DyeColor} representing the given value, or null if
     *     it doesn't exist
     */
    @Nullable
    public static DyeColor getByFireworkColor(@NotNull final Color color) {
        return BY_FIREWORK.get(color);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定名称的DyeColor，可能会进行旧版转换.
     * <p>原文：
     * Gets the DyeColor for the given name, possibly doing legacy transformations.
     *
     * @param name 染料名称
     * <p>原文：dye name
     * @return 染料颜色
     * <p>原文：dye color
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    @NotNull
    public static DyeColor legacyValueOf(@Nullable String name) {
        return "SILVER".equals(name) ? DyeColor.LIGHT_GRAY : DyeColor.valueOf(name);
    }

    static {
        BY_WOOL_DATA = values();
        BY_DYE_DATA = values();
        ImmutableMap.Builder<Color, DyeColor> byColor = ImmutableMap.builder();
        ImmutableMap.Builder<Color, DyeColor> byFirework = ImmutableMap.builder();

        for (DyeColor color : values()) {
            BY_WOOL_DATA[color.woolData & 0xff] = color;
            BY_DYE_DATA[color.dyeData & 0xff] = color;
            byColor.put(color.getColor(), color);
            byFirework.put(color.getFireworkColor(), color);
        }

        BY_COLOR = byColor.build();
        BY_FIREWORK = byFirework.build();
    }
}
