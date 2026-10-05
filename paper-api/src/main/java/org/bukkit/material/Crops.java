package org.bukkit.material;

import org.bukkit.CropState;
import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表处于不同生长阶段的不同类型的作物。
 * <p>原文：
 * Represents the different types of crops in different states of growth.
 *
 * @see Material#LEGACY_CROPS
 * @see Material#LEGACY_CARROT
 * @see Material#LEGACY_POTATO
 * @see Material#LEGACY_BEETROOT_BLOCK
 * @see Material#LEGACY_NETHER_WARTS
 * @deprecated 所有 MaterialData 的用法均已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Crops extends MaterialData {
    protected static final Material DEFAULT_TYPE = Material.LEGACY_CROPS;
    protected static final CropState DEFAULT_STATE = CropState.SEEDED;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个处于播种状态的小麦作物方块。
     * <p>原文：
     * Constructs a wheat crop block in the seeded state.
     */
    public Crops() {
        this(DEFAULT_TYPE, DEFAULT_STATE);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个处于给定生长状态的小麦作物方块。
     * <p>原文：
     * Constructs a wheat crop block in the given growth state
     *
     * @param state 作物的生长状态
     * <p>
     * 原文：Constructs a wheat crop block in the given growth state
     * <p>原文：The growth state of the crops
     */
    public Crops(CropState state) {
        this(DEFAULT_TYPE, state);
        setState(state);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定类型且处于给定生长状态的作物方块。
     * <p>原文：
     * Constructs a crop block of the given type and in the given growth state
     *
     * @param type 作物的类型
     * <p>原文：The type of crops
     * @param state 作物的生长状态
     * <p>
     * 原文：Constructs a crop block of the given type and in the given growth state
     * <p>原文：The growth state of the crops
     */
    public Crops(final Material type, final CropState state) {
        super(type);
        setState(state);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定类型且处于播种状态的作物方块。
     * <p>原文：
     * Constructs a crop block of the given type and in the seeded state
     *
     * @param type 作物的类型
     * <p>
     * 原文：Constructs a crop block of the given type and in the seeded state
     * <p>原文：The type of crops
     */
    public Crops(final Material type) {
        this(type, DEFAULT_STATE);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type 类型
     * <p>原文：the type
     * @param data 原始数据值
     * <p>原文：the raw data value
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public Crops(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此作物的当前生长状态。
     *
     * 对于只有四个生长阶段的作物（如甜菜根），只会返回 SEEDED、SMALL、TALL 和 RIPE。
     * <p>原文：
     * Gets the current growth state of this crop
     *
     * For crops with only four growth states such as beetroot, only the values SEEDED, SMALL, TALL and RIPE will be
     * returned.
     *
     * @return 此作物的 CropState
     * <p>
     * 原文：Gets the current growth state of this crop
     * <p>原文：CropState of this crop
     */
    public CropState getState() {
        switch (getItemType()) {
            case LEGACY_CROPS:
            case LEGACY_CARROT:
            case LEGACY_POTATO:
                // Mask the data just in case top bit set
                return CropState.getByData((byte) (getData() & 0x7));
            case LEGACY_BEETROOT_BLOCK:
            case LEGACY_NETHER_WARTS:
                // Mask the data just in case top bits are set
                // Will return SEEDED, SMALL, TALL, RIPE for the three growth data values
                return CropState.getByData((byte) (((getData() & 0x3) * 7 + 2) / 3));
            default:
                throw new IllegalArgumentException("Block type is not a crop");
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此作物的生长状态。
     *
     * 对于只有四个生长阶段的作物（如甜菜根），8 个 CropState 会被映射为四个状态：
     *
     * SEEDED、SMALL、TALL 和 RIPE
     *
     * GERMINATED 会变为 SEEDED
     * VERY_SMALL 会变为 SMALL
     * MEDIUM 会变为 TALL
     * VERY_TALL 会变为 RIPE
     * <p>原文：
     * Sets the growth state of this crop
     *
     * For crops with only four growth states such as beetroot, the 8 CropStates are mapped into four states:
     *
     * SEEDED, SMALL, TALL and RIPE
     *
     * GERMINATED will change to SEEDED
     * VERY_SMALL will change to SMALL
     * MEDIUM will change to TALL
     * VERY_TALL will change to RIPE
     *
     * @param state 此作物的新生长状态
     * <p>
     * 原文：Sets the growth state of this crop
     * <p>原文：New growth state of this crop
     */
    public void setState(CropState state) {
        switch (getItemType()) {
            case LEGACY_CROPS:
            case LEGACY_CARROT:
            case LEGACY_POTATO:
                // Preserve the top bit in case it is set
                setData((byte) ((getData() & 0x8) | state.getData()));
                break;
            case LEGACY_NETHER_WARTS:
            case LEGACY_BEETROOT_BLOCK:
                // Preserve the top bits in case they are set
                setData((byte) ((getData() & 0xC) | (state.getData() >> 1)));
                break;
            default:
                throw new IllegalArgumentException("Block type is not a crop");
        }
    }

    @Override
    public String toString() {
        return getState() + " " + super.toString();
    }

    @Override
    public Crops clone() {
        return (Crops) super.clone();
    }
}
