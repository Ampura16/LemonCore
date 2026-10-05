package org.bukkit.generator.structure;

import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表{@link GeneratedStructure}的一个独立组成部分.
 * <p>原文：
 * Represents an individual part of a {@link GeneratedStructure}.
 *
 * @see GeneratedStructure
 */
public interface StructurePiece {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此结构部件的边界框.
     * <p>原文：
     * Gets the bounding box of this structure piece.
     *
     * @return 此结构部件的边界框
     * <p>原文：bounding box of this structure piece
     */
    @NotNull
    public BoundingBox getBoundingBox();
}
