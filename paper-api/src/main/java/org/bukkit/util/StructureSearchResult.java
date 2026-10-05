package org.bukkit.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 保存搜索结构的结果。
 * <p>原文：
 * Holds the result of searching for a structure.
 *
 * @see World#locateNearestStructure(Location, Structure, int, boolean)
 * @see World#locateNearestStructure(Location, StructureType, int, boolean)
 */
public interface StructureSearchResult {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回找到的结构。
     * <p>原文：
     * Return the structure which was found.
     *
     * @return 找到的结构
     * <p>原文：the found structure.
     */
    @NotNull
    Structure getStructure();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回结构的位置。
     * <p>原文：
     * Return the location of the structure.
     *
     * @return 找到结构的位置
     * <p>原文：the location the structure was found.
     */
    @NotNull
    Location getLocation();
}
