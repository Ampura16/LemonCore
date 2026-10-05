package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.NetherWartsState;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表地狱疣
 * <p>原文：
 * Represents nether wart
 *
 * @deprecated 所有 MaterialData 的使用都已弃用并可能被移除。
 * 使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class NetherWarts extends MaterialData {
    public NetherWarts() {
        super(Material.LEGACY_NETHER_WARTS);
    }

    public NetherWarts(NetherWartsState state) {
        this();
        setState(state);
    }

    public NetherWarts(final Material type) {
        super(type);
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
    public NetherWarts(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此地狱疣的当前生长状态
     * <p>原文：
     * Gets the current growth state of this nether wart
     *
     * @return 此地狱疣的NetherWartsState
     * <p>
     * 原文：Gets the current growth state of this nether wart
     * <p>原文：NetherWartsState of this nether wart
     */
    public NetherWartsState getState() {
        switch (getData()) {
            case 0:
                return NetherWartsState.SEEDED;
            case 1:
                return NetherWartsState.STAGE_ONE;
            case 2:
                return NetherWartsState.STAGE_TWO;
            default:
                return NetherWartsState.RIPE;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此地狱疣的生长状态
     * <p>原文：
     * Sets the growth state of this nether wart
     *
     * @param state 此地狱疣的新生长状态
     * <p>
     * 原文：Sets the growth state of this nether wart
     * <p>原文：New growth state of this nether wart
     */
    public void setState(NetherWartsState state) {
        switch (state) {
            case SEEDED:
                setData((byte) 0x0);
                return;
            case STAGE_ONE:
                setData((byte) 0x1);
                return;
            case STAGE_TWO:
                setData((byte) 0x2);
                return;
            case RIPE:
                setData((byte) 0x3);
                return;
        }
    }

    @Override
    public String toString() {
        return getState() + " " + super.toString();
    }

    @Override
    public NetherWarts clone() {
        return (NetherWarts) super.clone();
    }
}
