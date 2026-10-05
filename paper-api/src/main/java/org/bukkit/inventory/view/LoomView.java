package org.bukkit.inventory.view;

import java.util.List;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.LoomInventory;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个提供与织布机视图数据相关额外方法的 {@link InventoryView} 实例。
 * <p>原文：
 * An instance of {@link InventoryView} which provides extra methods related to
 * loom view data.
 */
public interface LoomView extends InventoryView {

    @NotNull
    @Override
    LoomInventory getTopInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家当前可选择的所有图案列表。
     * <p>原文：
     * Gets a list of all selectable to the player.
     *
     * @return 玩家当前可选择的 {@link PatternType} 副本
     * <p>
     * 原文：Gets a list of all selectable to the player.
     * <p>原文：A copy of the {@link PatternType}'s currently selectable by the
     * player
     */
    @NotNull
    List<PatternType> getSelectablePatterns();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取选中图案的索引。
     * <p>原文：
     * Gets an index of the selected pattern.
     *
     * @return 选中图案的索引
     * <p>
     * 原文：Gets an index of the selected pattern.
     * <p>原文：Index of the selected pattern
     */
    int getSelectedPatternIndex();
}
