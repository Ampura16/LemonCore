package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.BlockFace;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同类型的朝向特定方向的树木方块。
 * <p>原文：
 * Represents the different types of Tree block that face a direction.
 *
 * @see Material#LEGACY_LOG
 * @see Material#LEGACY_LOG_2
 * @deprecated 所有 MaterialData 的用法都已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Tree extends Wood {
    protected static final Material DEFAULT_TYPE = Material.LEGACY_LOG;
    protected static final BlockFace DEFAULT_DIRECTION = BlockFace.UP;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个树木方块。
     * <p>原文：
     * Constructs a tree block.
     */
    public Tree() {
        this(DEFAULT_TYPE, DEFAULT_SPECIES, DEFAULT_DIRECTION);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定树种的树木方块。
     * <p>原文：
     * Constructs a tree block of the given tree species.
     *
     * @param species 树木方块的树种
     * <p>原文：Constructs a tree block of the given tree species.
     * <p>原文：the species of the tree block
     */
    public Tree(TreeSpecies species) {
        this(DEFAULT_TYPE, species, DEFAULT_DIRECTION);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定树种且朝向给定方向的树木方块。
     * <p>原文：
     * Constructs a tree block of the given tree species, and facing the given
     * direction.
     *
     * @param species 树木方块的树种
     * <p>原文：the species of the tree block
     * @param dir 树木方块朝向的方向
     * <p>原文：Constructs a tree block of the given tree species, and facing the given
     * direction.
     * <p>原文：the direction the tree block is facing
     */
    public Tree(TreeSpecies species, BlockFace dir) {
        this(DEFAULT_TYPE, species, dir);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定类型的树木方块。
     * <p>原文：
     * Constructs a tree block of the given type.
     *
     * @param type 树木方块的类型
     * <p>原文：Constructs a tree block of the given type.
     * <p>原文：the type of tree block
     */
    public Tree(final Material type) {
        this(type, DEFAULT_SPECIES, DEFAULT_DIRECTION);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定类型和树种的树木方块。
     * <p>原文：
     * Constructs a tree block of the given type and tree species.
     *
     * @param type 树木方块的类型
     * <p>原文：the type of tree block
     * @param species 树木方块的树种
     * <p>原文：Constructs a tree block of the given type and tree species.
     * <p>原文：the species of the tree block
     */
    public Tree(final Material type, TreeSpecies species) {
        this(type, species, DEFAULT_DIRECTION);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定类型和树种且朝向给定方向的树木方块。
     * <p>原文：
     * Constructs a tree block of the given type and tree species, and facing
     * the given direction.
     *
     * @param type 树木方块的类型
     * <p>原文：the type of tree block
     * @param species 树木方块的树种
     * <p>原文：the species of the tree block
     * @param dir 树木方块朝向的方向
     * <p>原文：Constructs a tree block of the given type and tree species, and facing
     * the given direction.
     * <p>原文：the direction the tree block is facing
     */
    public Tree(final Material type, TreeSpecies species, BlockFace dir) {
        super(type, species);
        setDirection(dir);
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
    public Tree(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取原木的方向。
     * <p>原文：
     * Get direction of the log
     *
     * @return 以下之一：
     * <ul>
     * <li>BlockFace.UP 表示直立（默认）
     * <li>BlockFace.NORTH 表示东西方向
     * <li>BlockFace.WEST 表示南北方向
     * <li>BlockFace.SELF 表示无方向
     * </ul>
     * <p>原文：Get direction of the log
     * <p>原文：one of:
     * <ul>
     * <li>BlockFace.TOP for upright (default)
     * <li>BlockFace.NORTH (east-west)
     * <li>BlockFace.WEST (north-south)
     * <li>BlockFace.SELF (directionless)
     * </ul>
     */
    @SuppressWarnings("deprecation")
    public BlockFace getDirection() {
        switch ((getData() >> 2) & 0x3) {
            case 0: // Up-down
            default:
                return BlockFace.UP;
            case 1: // North-south
                return BlockFace.WEST;
            case 2: // East-west
                return BlockFace.NORTH;
            case 3: // Directionless (bark on all sides)
                return BlockFace.SELF;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置原木的方向。
     * <p>原文：
     * Set direction of the log
     *
     * @param dir - 原木末端的方向（BlockFace.SELF 表示无方向）
     * <p>原文：Set direction of the log
     * <p>原文：- direction of end of log (BlockFace.SELF for no direction)
     */
    @SuppressWarnings("deprecation")
    public void setDirection(BlockFace dir) {
        int dat;
        switch (dir) {
            case UP:
            case DOWN:
            default:
                dat = 0;
                break;
            case WEST:
            case EAST:
                dat = 4; // 1<<2
                break;
            case NORTH:
            case SOUTH:
                dat = 8; // 2<<2
                break;
            case SELF:
                dat = 12; // 3<<2
                break;
        }
        setData((byte) ((getData() & 0x3) | dat));
    }

    @Override
    public String toString() {
        return getSpecies() + " " + getDirection() + " " + super.toString();
    }

    @Override
    public Tree clone() {
        return (Tree) super.clone();
    }
}
