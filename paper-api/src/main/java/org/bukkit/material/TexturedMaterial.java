package org.bukkit.material;

import java.util.List;
import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表带有纹理的材质，如台阶和平滑砖。
 * <p>原文：
 * Represents textured materials like steps and smooth bricks
 *
 * @deprecated 所有 MaterialData 的用法都已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public abstract class TexturedMaterial extends MaterialData {

    public TexturedMaterial(Material m) {
        super(m);
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
    public TexturedMaterial(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检索可用纹理的列表。列表的第一个元素将用作默认值。
     * <p>原文：
     * Retrieve a list of possible textures. The first element of the list
     * will be used as a default.
     *
     * @return 此方块可用纹理的列表
     * <p>原文：Retrieve a list of possible textures. The first element of the list
     * will be used as a default.
     * <p>原文：a list of possible textures for this block
     */
    public abstract List<Material> getTextures();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块当前构成的材质。
     * <p>原文：
     * Gets the current Material this block is made of
     *
     * @return 此方块的 Material
     * <p>原文：Gets the current Material this block is made of
     * <p>原文：Material of this block
     */
    public Material getMaterial() {
        int n = getTextureIndex();
        if (n > getTextures().size() - 1) {
            n = 0;
        }

        return getTextures().get(n);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此方块构成的材质。
     * <p>原文：
     * Sets the material this block is made of
     *
     * @param material
     *            此方块的新材质
     * <p>原文：Sets the material this block is made of
     * <p>原文：New material of this block
     */
    public void setMaterial(Material material) {
        if (getTextures().contains(material)) {
            setTextureIndex(getTextures().indexOf(material));
        } else {
            setTextureIndex(0x0);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从数据中获取材质索引。
     * <p>原文：
     * Get material index from data
     *
     * @return 数据在纹理列表中的索引
     * <p>原文：index of data in textures list
     * @deprecated 魔法值
     * <p>原文：Get material index from data
     * <p>原文：Magic value
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    protected int getTextureIndex() {
        return getData(); // Default to using all bits - override for other mappings
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置材质索引。
     * <p>原文：
     * Set material index
     *
     * @param idx - 数据在纹理列表中的索引
     * <p>原文：- index of data in textures list
     * @deprecated 魔法值
     * <p>原文：Set material index
     * <p>原文：Magic value
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    protected void setTextureIndex(int idx) {
        setData((byte) idx); // Default to using all bits - override for other mappings
    }

    @Override
    public String toString() {
        return getMaterial() + " " + super.toString();
    }

    @Override
    public TexturedMaterial clone() {
        return (TexturedMaterial) super.clone();
    }
}
