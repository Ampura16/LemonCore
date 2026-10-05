package org.bukkit.inventory.view.builder;

import net.kyori.adventure.text.Component;
import org.bukkit.Server;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Merchant;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 用于创建商人视图的 InventoryViewBuilder
 * <p>原文：
 * An InventoryViewBuilder for creating merchant views
 *
 * @param <V> 通过此构建器创建的 InventoryView 的类型
 * <p>原文：the type of InventoryView created by this builder
 */
@ApiStatus.Experimental
public interface MerchantInventoryViewBuilder<V extends InventoryView> extends InventoryViewBuilder<V> {

    @Override
    MerchantInventoryViewBuilder<V> copy();

    @Override
    MerchantInventoryViewBuilder<V> title(final @Nullable Component title);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向此构建器添加商人
     * <p>原文：
     * Adds a merchant to this builder
     *
     * @param merchant 商人
     * <p>原文：the merchant
     * @return 此构建器
     * <p>原文：this builder
     * @see Server#createMerchant()
     */
    MerchantInventoryViewBuilder<V> merchant(final Merchant merchant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 确定服务器是否应检查玩家能否到达该位置。
     * <p>
     * 如果提供了 checkReachable 且通过 {@link Server#createMerchant(String)} 向构建器提供了虚拟商人，则此方法对实际菜单状态不会产生影响。
     * <p>原文：
     * Determines whether or not the server should check if the player can reach
     * the location.
     * <p>
     * Given checkReachable is provided and a virtual merchant is provided to
     * the builder from {@link Server#createMerchant()} this method will
     * have no effect on the actual menu status.
     *
     * @param checkReachable 是否检查视图是否"可到达"
     * <p>原文：whether or not to check if the view is "reachable"
     * @return 此构建器
     * <p>原文：this builder
     */
    MerchantInventoryViewBuilder<V> checkReachable(final boolean checkReachable);
}
