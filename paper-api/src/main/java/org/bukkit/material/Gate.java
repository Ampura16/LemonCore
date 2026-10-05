package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个栅栏门。
 * <p>原文：
 * Represents a fence gate
 *
 * @deprecated 所有 MaterialData 的用法都已弃用并将被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Gate extends MaterialData implements Directional, Openable {
    private static final byte OPEN_BIT = 0x4;
    private static final byte DIR_BIT = 0x3;
    private static final byte GATE_SOUTH = 0x0;
    private static final byte GATE_WEST = 0x1;
    private static final byte GATE_NORTH = 0x2;
    private static final byte GATE_EAST = 0x3;

    public Gate() {
        super(Material.LEGACY_FENCE_GATE);
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
     */
    @Deprecated(since = "1.13")
    public Gate(final Material type, final byte data) {
        super(type, data);
    }

    public Gate(byte data) {
        super(Material.LEGACY_FENCE_GATE, data);
    }

    @Override
    public void setFacingDirection(BlockFace face) {
        byte data = (byte) (getData() & ~DIR_BIT);

        switch (face) {
            default:
            case EAST:
                data |= GATE_SOUTH;
                break;
            case SOUTH:
                data |= GATE_WEST;
                break;
            case WEST:
                data |= GATE_NORTH;
                break;
            case NORTH:
                data |= GATE_EAST;
                break;
        }

        setData(data);
    }

    @Override
    public BlockFace getFacing() {
        switch (getData() & DIR_BIT) {
            case GATE_SOUTH:
                return BlockFace.EAST;
            case GATE_WEST:
                return BlockFace.SOUTH;
            case GATE_NORTH:
                return BlockFace.WEST;
            case GATE_EAST:
                return BlockFace.NORTH;
        }

        return BlockFace.EAST;
    }

    @Override
    public boolean isOpen() {
        return (getData() & OPEN_BIT) > 0;
    }

    @Override
    public void setOpen(boolean isOpen) {
        byte data = getData();

        if (isOpen) {
            data |= OPEN_BIT;
        } else {
            data &= ~OPEN_BIT;
        }

        setData(data);
    }

    @Override
    public String toString() {
        return (isOpen() ? "OPEN " : "CLOSED ") + " facing and opening " + getFacing();
    }

    @Override
    public Gate clone() {
        return (Gate) super.clone();
    }
}
