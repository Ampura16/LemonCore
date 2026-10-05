package org.bukkit.inventory.view.builder;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 没有特殊属性或参数的通用 InventoryView 构建器
 * <p>原文：
 * Generic Builder for InventoryView's with no special attributes or parameters
 *
 * @param <V> 通过此构建器创建的 InventoryView 的类型
 * <p>原文：the type of InventoryView created from this builder
 */
@ApiStatus.Experimental
public interface InventoryViewBuilder<V extends InventoryView> {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建此构建器的副本
     * <p>原文：
     * Makes a copy of this builder
     *
     * @return 此构建器的副本
     * <p>原文：a copy of this builder
     */
    InventoryViewBuilder<V> copy();

    /**
     * Sets the title of the builder
     *
     * @param title the title, or null for a default title
     * @return this builder
     */
    InventoryViewBuilder<V> title(@Nullable final Component title);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此构建器构建为 InventoryView
     * <p>原文：
     * Builds this builder into a InventoryView
     *
     * @param player 要分配给视图的玩家
     * <p>原文：the player to assign to the view
     * @return 创建的 InventoryView
     * <p>原文：the created InventoryView
     */
    V build(final HumanEntity player);
}
