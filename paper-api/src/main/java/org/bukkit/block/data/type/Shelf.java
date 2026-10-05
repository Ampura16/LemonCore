package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.SideChaining;
import org.bukkit.block.data.Waterlogged;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'side_chain' 表示方块侧面的链条状态.
 */
@NullMarked
public interface Shelf extends Directional, SideChaining, Waterlogged, Powerable {
}
