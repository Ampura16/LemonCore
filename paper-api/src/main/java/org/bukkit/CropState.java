package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示农作物的不同生长阶段.
 * <p>原文：
 * Represents the different growth states of crops
 */
@Deprecated(forRemoval = true, since = "1.13")
public enum CropState {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 最开始的播种阶段
     * <p>原文：
     * State when first seeded
     */
    SEEDED(0x0),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发芽阶段(第一生长阶段).
     * <p>原文：
     * First growth stage
     */
    GERMINATED(0x1),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 第二生长阶段.
     * <p>原文：
     * Second growth stage
     */
    VERY_SMALL(0x2),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 第三生长阶段.
     * <p>原文：
     * Third growth stage
     */
    SMALL(0x3),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 第四生长阶段
     * <p>原文：
     * Fourth growth stage
     */
    MEDIUM(0x4),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 第五生长阶段
     * <p>原文：
     * Fifth growth stage
     */
    TALL(0x5),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 即将成熟的阶段
     * <p>原文：
     * Almost ripe stage
     */
    VERY_TALL(0x6),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 成熟阶段
     * <p>原文：
     * Ripe stage
     */
    RIPE(0x7);

    private final byte data;
    private static final Map<Byte, CropState> BY_DATA = Maps.newHashMap();

    private CropState(final int data) {
        this.data = (byte) data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取表示这个生长阶段的相关数值.
     * <p>原文：
     * Gets the associated data value representing this growth state
     *
     * @return 一个包含这个生长阶段的数值的字节
     * <p>原文：A byte containing the data value of this growth state
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public byte getData() {
        return data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据指定的数值获取农作物阶段.
     * <p>原文：
     * Gets the CropState with the given data value
     *
     * @param data 用于获取农作物阶段的数值
     * <p>原文：Data value to fetch
     * @return 表示指定数值的农作物阶段{@link CropState}，如果不存在则返回null
     * <p>原文：The {@link CropState} representing the given value, or null if
     *     it doesn't exist
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    @Nullable
    public static CropState getByData(final byte data) {
        return BY_DATA.get(data);
    }

    static {
        for (CropState cropState : values()) {
            BY_DATA.put(cropState.getData(), cropState);
        }
    }
}
