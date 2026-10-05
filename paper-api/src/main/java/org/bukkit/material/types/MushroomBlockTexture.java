package org.bukkit.material.types;

import com.google.common.collect.Maps;
import java.util.Map;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表蘑菇方块的不同材质.
 * <p>原文：
 * Represents the different textured blocks of mushroom.
 *
 * @deprecated use BlockData
 */
@Deprecated(forRemoval = true)
public enum MushroomBlockTexture {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 各个面都是气孔材质
     * <p>原文：
     * Pores on all faces.
     */
    ALL_PORES(0, null),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 上面、西面和北面是蘑菇盖材质
     * <p>原文：
     * Cap texture on the top, north and west faces, pores on remaining sides.
     */
    CAP_NORTH_WEST(1, BlockFace.NORTH_WEST),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 上面和北面是蘑菇盖材质
     * <p>原文：
     * Cap texture on the top and north faces, pores on remaining sides.
     */
    CAP_NORTH(2, BlockFace.NORTH),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 上面、北面和东面是蘑菇盖材质
     * <p>原文：
     * Cap texture on the top, north and east faces, pores on remaining sides.
     */
    CAP_NORTH_EAST(3, BlockFace.NORTH_EAST),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 上面和西面是蘑菇盖材质
     * <p>原文：
     * Cap texture on the top and west faces, pores on remaining sides.
     */
    CAP_WEST(4, BlockFace.WEST),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 上面是蘑菇盖材质
     * <p>原文：
     * Cap texture on the top face, pores on remaining sides.
     */
    CAP_TOP(5, BlockFace.UP),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 上面和东面是蘑菇盖材质
     * <p>原文：
     * Cap texture on the top and east faces, pores on remaining sides.
     */
    CAP_EAST(6, BlockFace.EAST),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 上面、南面和西面是蘑菇盖材质
     * <p>原文：
     * Cap texture on the top, south and west faces, pores on remaining sides.
     */
    CAP_SOUTH_WEST(7, BlockFace.SOUTH_WEST),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 上面和南面是蘑菇盖材质
     * <p>原文：
     * Cap texture on the top and south faces, pores on remaining sides.
     */
    CAP_SOUTH(8, BlockFace.SOUTH),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 上面、东面和南面是蘑菇盖材质
     * <p>原文：
     * Cap texture on the top, south and east faces, pores on remaining sides.
     */
    CAP_SOUTH_EAST(9, BlockFace.SOUTH_EAST),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 4个侧面都是蘑菇茎材质，上面和下面是气孔材质
     * <p>原文：
     * Stem texture on the north, east, south and west faces, pores on top and
     * bottom.
     */
    STEM_SIDES(10, null),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 6个面都是蘑菇盖材质
     * <p>原文：
     * Cap texture on all faces.
     */
    ALL_CAP(14, BlockFace.SELF),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 6个面都是蘑菇茎材质
     * <p>原文：
     * Stem texture on all faces.
     */
    ALL_STEM(15, null);
    private static final Map<Byte, MushroomBlockTexture> BY_DATA = Maps.newHashMap();
    private static final Map<BlockFace, MushroomBlockTexture> BY_BLOCKFACE = Maps.newHashMap();

    private final Byte data;
    private final BlockFace capFace;

    private MushroomBlockTexture(final int data, /*@Nullable*/ final BlockFace capFace) {
        this.data = (byte) data;
        this.capFace = capFace;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取代表这个蘑菇方块朝向的相关数据值.
     * <p>原文：
     * Gets the associated data value representing this mushroom block face.
     *
     * @return 包含这个蘑菇方块朝向的数据值
     * <p>原文：A byte containing the data value of this mushroom block face
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.9", forRemoval = true)
    public byte getData() {
        return data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个材质所对的朝向.
     * <p>原文：
     * Gets the face that has cap texture.
     *
     * @return 材质的朝向
     * <p>原文：The cap face
     */
    @Nullable
    public BlockFace getCapFace() {
        return capFace;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以给定的数据值获取 MushroomBlockType.
     * <p>原文：
     * Gets the MushroomBlockType with the given data value.
     *
     * @param data 数据值
     * <p>原文：Data value to fetch
     * @return 代表这个数据值的 {@link MushroomBlockTexture}，如果相关数据值不存在为null
     * <p>原文：The {@link MushroomBlockTexture} representing the given value, or
     * null if it doesn't exist
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.9", forRemoval = true)
    @Nullable
    public static MushroomBlockTexture getByData(final byte data) {
        return BY_DATA.get(data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取关于指定方块朝向的蘑菇盖的 MushroomBlockType.
     * <p>原文：
     * Gets the MushroomBlockType with cap texture on the given block face.
     *
     * @param face 方块朝向
     * <p>原文：the required block face with cap texture
     * @return 代表这个朝向的 {@link MushroomBlockTexture}，如果相关数据值不存在为null
     * <p>原文：The {@link MushroomBlockTexture} representing the given block
     * face, or null if it doesn't exist
     * @see BlockFace
     */
    @Nullable
    public static MushroomBlockTexture getCapByFace(@Nullable final BlockFace face) {
        return BY_BLOCKFACE.get(face);
    }

    static {
        for (MushroomBlockTexture type : values()) {
            BY_DATA.put(type.data, type);
            BY_BLOCKFACE.put(type.capFace, type);
        }
    }
}
