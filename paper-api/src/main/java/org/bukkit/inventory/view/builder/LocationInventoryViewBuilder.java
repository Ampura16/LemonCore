package org.bukkit.inventory.view.builder;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 可以通过世界中位置绑定的 InventoryViewBuilder
 * <p>原文：
 * An InventoryViewBuilder that can be bound by location within the world
 *
 * @param <V> 通过此构建器创建的 InventoryView 的类型
 * <p>原文：the type of InventoryView created from this builder
 */
@ApiStatus.Experimental
public interface LocationInventoryViewBuilder<V extends InventoryView> extends InventoryViewBuilder<V> {

    @Override
    LocationInventoryViewBuilder<V> copy();

    @Override
    LocationInventoryViewBuilder<V> title(final @Nullable Component title);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 确定服务器是否应检查玩家能否到达该位置。
     * <p>
     * 未提供位置但将 checkReachable 设置为 true 时，视图在打开后会自动关闭。
     * <p>
     * 如果将 checkReachable 设置为 false 且在构建器上设置了位置，当目标方块存在且此构建器是该方块的正确菜单（例如 MenuType.GENERIC_9X3 构建器对应的目标方块为箱子），则即使该方块被破坏，视图仍会持续存在。
     * <p>原文：
     * Determines whether or not the server should check if the player can reach
     * the location.
     * <p>
     * Not providing a location but setting checkReachable to true will
     * automatically close the view when opened.
     * <p>
     * If checkReachable is set to false and a location is set on the builder if
     * the target block exists and this builder is the correct menu for that
     * block, e.g. MenuType.GENERIC_9X3 builder and target block set to chest,
     * if that block is destroyed the view would persist.
     *
     * @param checkReachable 是否检查视图是否"可到达"
     * <p>原文：whether or not to check if the view is "reachable"
     * @return 此构建器
     * <p>原文：this builder
     */
    LocationInventoryViewBuilder<V> checkReachable(final boolean checkReachable);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将位置绑定到此构建器。
     * <p>
     * 将未加载区块中的位置绑定到此构建器时，很可能会导致该位置所在的区块被加载。这意味着构建此视图时可能会产生与区块加载相关的开销。
     * <p>
     * 提供一个方块实体的位置但使用不匹配的菜单类型，会产生额外的开销以确保创建正确的视图。
     * <p>原文：
     * Binds a location to this builder.
     * <p>
     * By binding a location in an unloaded chunk to this builder it is likely
     * that the given chunk the location is will load. That means that when,
     * building this view it may come with the costs associated with chunk
     * loading.
     * <p>
     * Providing a location of a block entity with a non matching menu comes with
     * extra costs associated with ensuring that the correct view is created.
     *
     * @param location 要绑定到此视图的位置
     * <p>原文：the location to bind to this view
     * @return 此构建器
     * <p>原文：this builder
     */
    LocationInventoryViewBuilder<V> location(final Location location);
}
