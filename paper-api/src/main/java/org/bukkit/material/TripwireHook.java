package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表绊线钩。
 * <p>原文：
 * Represents the tripwire hook
 *
 * @deprecated 所有 MaterialData 的用法都已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class TripwireHook extends SimpleAttachableMaterialData implements Redstone {

    public TripwireHook() {
        super(Material.LEGACY_TRIPWIRE_HOOK);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type 物品类型
     * <p>原文：the type
     * @param data 原始数据值
     * <p>原文：the raw data value
     * @deprecated 魔法值
     * <p>原文：Magic value
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public TripwireHook(final Material type, final byte data) {
        super(type, data);
    }

    public TripwireHook(BlockFace dir) {
        this();
        setFacingDirection(dir);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试绊线是否已连接。
     * <p>原文：
     * Test if tripwire is connected
     *
     * @return 如果已连接则返回 true，否则返回 false
     * <p>原文：Test if tripwire is connected
     * <p>原文：true if connected, false if not
     */
    public boolean isConnected() {
        return (getData() & 0x4) != 0;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置绊线的连接状态。
     * <p>原文：
     * Set tripwire connection state
     *
     * @param connected - 如果已连接则为 true，否则为 false
     * <p>原文：Set tripwire connection state
     * <p>原文：- true if connected, false if not
     */
    public void setConnected(boolean connected) {
        int dat = getData() & (0x8 | 0x3);
        if (connected) {
            dat |= 0x4;
        }
        setData((byte) dat);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试钩子当前是否被激活。
     * <p>原文：
     * Test if hook is currently activated
     *
     * @return 如果被激活则返回 true，否则返回 false
     * <p>原文：Test if hook is currently activated
     * <p>原文：true if activated, false if not
     */
    public boolean isActivated() {
        return (getData() & 0x8) != 0;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置钩子的激活状态。
     * <p>原文：
     * Set hook activated state
     *
     * @param act - 如果被激活则为 true，否则为 false
     * <p>原文：Set hook activated state
     * <p>原文：- true if activated, false if not
     */
    public void setActivated(boolean act) {
        int dat = getData() & (0x4 | 0x3);
        if (act) {
            dat |= 0x8;
        }
        setData((byte) dat);
    }

    @Override
    public void setFacingDirection(BlockFace face) {
        int dat = getData() & 0xC;
        switch (face) {
        case WEST:
            dat |= 0x1;
            break;
        case NORTH:
            dat |= 0x2;
            break;
        case EAST:
            dat |= 0x3;
            break;
        case SOUTH:
        default:
            break;
        }
        setData((byte) dat);
    }

    @Override
    public BlockFace getAttachedFace() {
        switch (getData() & 0x3) {
        case 0:
            return BlockFace.NORTH;
        case 1:
            return BlockFace.EAST;
        case 2:
            return BlockFace.SOUTH;
        case 3:
            return BlockFace.WEST;
        }
        return null;
    }

    @Override
    public boolean isPowered() {
        return isActivated();
    }

    @Override
    public TripwireHook clone() {
        return (TripwireHook) super.clone();
    }

    @Override
    public String toString() {
        return super.toString() + " facing " + getFacing() + (isActivated() ? " Activated" : "") + (isConnected() ? " Connected" : "");
    }
}
