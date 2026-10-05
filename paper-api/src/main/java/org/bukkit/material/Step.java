package org.bukkit.material;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同的台阶.
 * <p>原文：
 * Represents the different types of steps.
 *
 * @deprecated 所有 MaterialData 的用法都已弃用并将被移除.
 * 请使用 {@link org.bukkit.block.data.BlockData}.
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Step extends TexturedMaterial {
    private static final List<Material> textures = new ArrayList<Material>();
    static {
        textures.add(Material.LEGACY_STONE);
        textures.add(Material.LEGACY_SANDSTONE);
        textures.add(Material.LEGACY_WOOD);
        textures.add(Material.LEGACY_COBBLESTONE);
        textures.add(Material.LEGACY_BRICK);
        textures.add(Material.LEGACY_SMOOTH_BRICK);
        textures.add(Material.LEGACY_NETHER_BRICK);
        textures.add(Material.LEGACY_QUARTZ_BLOCK);
    }

    public Step() {
        super(Material.LEGACY_STEP);
    }

    public Step(final Material type) {
        super((textures.contains(type)) ? Material.LEGACY_STEP : type);
        if (textures.contains(type)) {
            setMaterial(type);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type 材质类型
     * <p>原文：the type
     * @param data 原始数据值
     * <p>原文：the raw data value
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public Step(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public List<Material> getTextures() {
        return textures;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此台阶是否被反转.
     * <p>原文：
     * Test if step is inverted
     *
     * @return 上半台阶(反转的台阶)为true，普通台阶(底部台阶)为false
     * <p>原文：true if inverted (top half), false if normal (bottom half)
     */
    public boolean isInverted() {
        return ((getData() & 0x8) != 0);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置台阶是否被反转.
     * <p>原文：
     * Set step inverted state
     *
     * @param inv - 上半台阶(反转的台阶)为true，普通台阶(底部台阶)为false
     * <p>原文：- true if step is inverted (top half), false if step is
     *     normal (bottom half)
     */
    public void setInverted(boolean inv) {
        int dat = getData() & 0x7;
        if (inv) {
            dat |= 0x8;
        }
        setData((byte) dat);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * {@inheritDoc}
     *
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Override
    @Deprecated(since = "1.20.5")
    protected int getTextureIndex() {
        return getData() & 0x7;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * {@inheritDoc}
     *
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    @Override
    protected void setTextureIndex(int idx) {
        setData((byte) ((getData() & 0x8) | idx));
    }

    @Override
    public Step clone() {
        return (Step) super.clone();
    }

    @Override
    public String toString() {
        return super.toString() + (isInverted() ? "inverted" : "");
    }
}
