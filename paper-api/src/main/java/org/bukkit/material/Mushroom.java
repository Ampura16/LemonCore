package org.bukkit.material;

import com.google.common.base.Preconditions;
import java.util.EnumSet;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.material.types.MushroomBlockTexture;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个巨大的蘑菇方块，其某些面被设置为菌盖、菌孔或菌柄。
 * <p>原文：
 * Represents a huge mushroom block with certain combinations of faces set to
 * cap, pores or stem.
 *
 * @see Material#LEGACY_HUGE_MUSHROOM_1
 * @see Material#LEGACY_HUGE_MUSHROOM_2
 * @deprecated 所有 MaterialData 的使用都已弃用并可能被移除。
 * 使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Mushroom extends MaterialData {
    private static final byte NORTH_LIMIT = 4;
    private static final byte SOUTH_LIMIT = 6;
    private static final byte EAST_WEST_LIMIT = 3;
    private static final byte EAST_REMAINDER = 0;
    private static final byte WEST_REMAINDER = 1;
    private static final byte NORTH_SOUTH_MOD = 3;
    private static final byte EAST_WEST_MOD = 1;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个所有面都设置为菌孔的棕色/红色蘑菇方块。
     * <p>原文：
     * Constructs a brown/red mushroom block with all sides set to pores.
     *
     * @param shroom 棕色或红色蘑菇材质类型。
     * <p>原文：A brown or red mushroom material type.
     * @see Material#LEGACY_HUGE_MUSHROOM_1
     * @see Material#LEGACY_HUGE_MUSHROOM_2
     */
    public Mushroom(Material shroom) {
        super(shroom);
        Preconditions.checkArgument(shroom == Material.LEGACY_HUGE_MUSHROOM_1 || shroom == Material.LEGACY_HUGE_MUSHROOM_2, "Not a mushroom!");
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个棕色/红色蘑菇菌盖方块，其指定的一个或多个面被设置为菌盖纹理。
     *
     * 设置四个侧面中的任何一个也会将顶部设置为菌盖。
     *
     * 要同时设置两个侧面，请使用例如西北方向。
     *
     * 指定自身以同时设置所有六个面。
     * <p>原文：
     * Constructs a brown/red mushroom cap block with the specified face or
     * faces set to cap texture.
     *
     * Setting any of the four sides will also set the top to cap.
     *
     * To set two side faces at once use e.g. north-west.
     *
     * Specify self to set all six faces at once.
     *
     * @param shroom 棕色或红色蘑菇材质类型。
     * <p>原文：A brown or red mushroom material type.
     * @param capFace 要设置为蘑菇菌盖纹理的一个或多个面。
     * <p>原文：The face or faces to set to mushroom cap texture.
     * @see Material#LEGACY_HUGE_MUSHROOM_1
     * @see Material#LEGACY_HUGE_MUSHROOM_2
     * @see BlockFace
     */
    public Mushroom(Material shroom, BlockFace capFace) {
        this(shroom, MushroomBlockTexture.getCapByFace(capFace));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个具有指定纹理的棕色/红色蘑菇方块。
     * <p>原文：
     * Constructs a brown/red mushroom block with the specified textures.
     *
     * @param shroom 棕色或红色蘑菇材质类型。
     * <p>原文：A brown or red mushroom material type.
     * @param texture 纹理化的蘑菇面。
     * <p>原文：The textured mushroom faces.
     * @see Material#LEGACY_HUGE_MUSHROOM_1
     * @see Material#LEGACY_HUGE_MUSHROOM_2
     */
    public Mushroom(Material shroom, MushroomBlockTexture texture) {
        this(shroom, texture.getData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param shroom 类型
     * <p>原文：the type
     * @param data 原始数据值
     * <p>原文：the raw data value
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public Mushroom(Material shroom, byte data) {
        super(shroom, data);
        Preconditions.checkArgument(shroom == Material.LEGACY_HUGE_MUSHROOM_1 || shroom == Material.LEGACY_HUGE_MUSHROOM_2, "Not a mushroom!");
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 这是否是蘑菇菌柄。
     * <p>
     * 原文：Whether this is a mushroom stem.
     * <p>原文：Whether this is a mushroom stem.
     */
    public boolean isStem() {
        return getData() == MushroomBlockTexture.STEM_SIDES.getData() || getData() == MushroomBlockTexture.ALL_STEM.getData();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此设置为蘑菇菌柄。
     * <p>原文：
     * Sets this to be a mushroom stem.
     *
     * @see MushroomBlockTexture#STEM_SIDES
     * @see MushroomBlockTexture#ALL_STEM
     * @deprecated 使用
     * {@link #setBlockTexture(org.bukkit.material.types.MushroomBlockTexture)}
     * 配合 {@link MushroomBlockTexture#STEM_SIDES } 或
     * {@link MushroomBlockTexture#ALL_STEM}
     * <p>
     * 原文：Sets this to be a mushroom stem.
     * <p>原文：Use
     * {@link #setBlockTexture(org.bukkit.material.types.MushroomBlockTexture)}
     * with {@link MushroomBlockTexture#STEM_SIDES } or
     * {@link MushroomBlockTexture#ALL_STEM}
     */
    @Deprecated(since = "1.9")
    public void setStem() {
        setData((byte) MushroomBlockTexture.STEM_SIDES.getData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块的蘑菇纹理。
     * <p>原文：
     * Gets the mushroom texture of this block.
     *
     * @return 此方块的蘑菇纹理
     * <p>
     * 原文：Gets the mushroom texture of this block.
     * <p>原文：The mushroom texture of this block
     */
    public MushroomBlockTexture getBlockTexture() {
        return MushroomBlockTexture.getByData(getData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此方块的蘑菇纹理。
     * <p>原文：
     * Sets the mushroom texture of this block.
     *
     * @param texture 要设置的蘑菇纹理
     * <p>
     * 原文：Sets the mushroom texture of this block.
     * <p>原文：The mushroom texture to set
     */
    public void setBlockTexture(MushroomBlockTexture texture) {
        setData(texture.getData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查方块的一个面是否被绘制了菌盖纹理。
     * <p>原文：
     * Checks whether a face of the block is painted with cap texture.
     *
     * @param face 要检查的面。
     * <p>原文：The face to check.
     * @return 如果已绘制则返回true。
     * <p>
     * 原文：Checks whether a face of the block is painted with cap texture.
     * <p>原文：True if it is painted.
     */
    public boolean isFacePainted(BlockFace face) {
        byte data = getData();

        if (data == MushroomBlockTexture.ALL_PORES.getData() || data == MushroomBlockTexture.STEM_SIDES.getData()
                || data == MushroomBlockTexture.ALL_STEM.getData()) {
            return false;
        }

        switch (face) {
            case WEST:
                return data < NORTH_LIMIT;
            case EAST:
                return data > SOUTH_LIMIT;
            case NORTH:
                return data % EAST_WEST_LIMIT == EAST_REMAINDER;
            case SOUTH:
                return data % EAST_WEST_LIMIT == WEST_REMAINDER;
            case UP:
                return true;
            case DOWN:
            case SELF:
                return data == MushroomBlockTexture.ALL_CAP.getData();
            default:
                return false;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置方块的一个面是否被绘制。请注意，由于数据存储方式的性质，设置一个面是否被绘制并不保证其他面保持不变。
     * <p>原文：
     * Set a face of the block to be painted or not. Note that due to the
     * nature of how the data is stored, setting a face painted or not is not
     * guaranteed to leave the other faces unchanged.
     *
     * @param face 要绘制或取消绘制的面。
     * <p>原文：The face to paint or unpaint.
     * @param painted 如果要绘制则为true，如果要显示菌孔则为false。
     * <p>原文：True if you want to paint it, false if you want the
     *     pores to show.
     * @deprecated 使用MushroomBlockType的菌盖选项
     * <p>
     * 原文：Set a face of the block to be painted or not. Note that due to the nature of how the data is stored, setting a face painted or not is not guaranteed to leave the other faces unchanged.
     * <p>原文：Use MushroomBlockType cap options
     */
    @Deprecated(since = "1.9")
    public void setFacePainted(BlockFace face, boolean painted) {
        if (painted == isFacePainted(face)) {
            return;
        }

        byte data = getData();

        if (data == MushroomBlockTexture.ALL_PORES.getData() || isStem()) {
            data = MushroomBlockTexture.CAP_TOP.getData();
        }
        if (data == MushroomBlockTexture.ALL_CAP.getData() && !painted) {
            data = MushroomBlockTexture.CAP_TOP.getData();
            face = face.getOppositeFace();
            painted = true;
        }

        switch (face) {
            case WEST:
                if (painted) {
                    data -= NORTH_SOUTH_MOD;
                } else {
                    data += NORTH_SOUTH_MOD;
                }

                break;
            case EAST:
                if (painted) {
                    data += NORTH_SOUTH_MOD;
                } else {
                    data -= NORTH_SOUTH_MOD;
                }

                break;
            case NORTH:
                if (painted) {
                    data += EAST_WEST_MOD;
                } else {
                    data -= EAST_WEST_MOD;
                }

                break;
            case SOUTH:
                if (painted) {
                    data -= EAST_WEST_MOD;
                } else {
                    data += EAST_WEST_MOD;
                }

                break;
            case UP:
                if (!painted) {
                    data = MushroomBlockTexture.ALL_PORES.getData();
                }
                break;
            case SELF:
            case DOWN:
                if (painted) {
                    data = MushroomBlockTexture.ALL_CAP.getData();
                } else {
                    data = MushroomBlockTexture.ALL_PORES.getData();
                }
                break;
            default:
                throw new IllegalArgumentException("Can't paint that face of a mushroom!");
        }

        setData(data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 当前已绘制的所有面的集合（如果是菌柄则为空集合）
     * <p>
     * 原文：A set of all faces that are currently painted (an empty set if it is a stem)
     * <p>原文：A set of all faces that are currently painted (an empty set if
     *     it is a stem)
     */
    public Set<BlockFace> getPaintedFaces() {
        EnumSet<BlockFace> faces = EnumSet.noneOf(BlockFace.class);

        if (isFacePainted(BlockFace.WEST)) {
            faces.add(BlockFace.WEST);
        }

        if (isFacePainted(BlockFace.NORTH)) {
            faces.add(BlockFace.NORTH);
        }

        if (isFacePainted(BlockFace.SOUTH)) {
            faces.add(BlockFace.SOUTH);
        }

        if (isFacePainted(BlockFace.EAST)) {
            faces.add(BlockFace.EAST);
        }

        if (isFacePainted(BlockFace.UP)) {
            faces.add(BlockFace.UP);
        }

        if (isFacePainted(BlockFace.DOWN)) {
            faces.add(BlockFace.DOWN);
        }

        return faces;
    }

    @Override
    public String toString() {
        return getItemType() + (isStem() ? " STEM " : " CAP ") + getPaintedFaces();
    }

    @Override
    public Mushroom clone() {
        return (Mushroom) super.clone();
    }
}
