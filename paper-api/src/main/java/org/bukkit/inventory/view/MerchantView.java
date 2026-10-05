package org.bukkit.inventory.view;

import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个提供与商人视图数据相关额外方法的 {@link InventoryView} 实例。
 * <p>原文：
 * An instance of {@link InventoryView} which provides extra methods related to
 * merchant view data.
 */
public interface MerchantView extends InventoryView {

    @NotNull
    @Override
    MerchantInventory getTopInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此视图对应的商人。
     * <p>原文：
     * Gets the merchant that this view is for.
     *
     * @return 此视图使用的商人
     * <p>
     * 原文：Gets the merchant that this view is for.
     * <p>原文：The merchant that this view uses
     */
    @NotNull
    Merchant getMerchant();
}
