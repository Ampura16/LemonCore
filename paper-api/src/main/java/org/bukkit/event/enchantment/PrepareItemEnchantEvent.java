package org.bukkit.event.enchantment;

import org.bukkit.block.Block;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.view.EnchantmentView;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 物品塞入附魔台的事件 - 可被多次调用。
 * <p>原文：
 * Called when an ItemStack is inserted in an enchantment table - can be
 * called multiple times
 */
public class PrepareItemEnchantEvent extends InventoryEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player enchanter;
    private final Block table;
    private final ItemStack item;
    private final EnchantmentOffer[] offers;
    private final int bonus;

    private boolean cancelled;

    @ApiStatus.Internal
    public PrepareItemEnchantEvent(@NotNull final Player enchanter, @NotNull EnchantmentView view, @NotNull final Block table, @NotNull final ItemStack item, @org.jetbrains.annotations.Nullable final EnchantmentOffer @NotNull [] offers, final int bonus) { // Paper - offers can contain null values
        super(view);
        this.enchanter = enchanter;
        this.table = table;
        this.item = item;
        this.offers = offers;
        this.bonus = bonus;
    }

    @NotNull
    @Override
    public EnchantmentView getView() {
        return (EnchantmentView) super.getView();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取正在为物品附魔的玩家.
     * <p>原文：
     * Gets the player enchanting the item
     *
     * @return 正在为物品附魔的玩家
     * <p>原文：enchanting player
     */
    @NotNull
    public Player getEnchanter() {
        return this.enchanter;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取附魔台方块.
     * <p>原文：
     * Gets the block being used to enchant the item
     *
     * @return 附魔台方块
     * <p>原文：the block used for enchanting
     */
    @NotNull
    public Block getEnchantBlock() {
        return this.table;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取准备附魔的物品(可改动).
     * <p>原文：
     * Gets the item to be enchanted.
     *
     * @return 附魔的物品ItemStack对象
     * <p>原文：ItemStack of item
     */
    @NotNull
    public ItemStack getItem() {
        return this.item;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取附魔所需要的等级的列表.
     * <p>原文：
     * Get a list of offered experience level costs of the enchantment.
     *
     * @return 附魔所需要的等级的列表
     * <p>原文：experience level costs offered
     * @deprecated 请换用 {@link #getOffers()} 方法
     * <p>原文：Use {@link #getOffers()} instead of this method
     */
    @NotNull
    @Deprecated(since = "1.20.5")
    public int[] getExpLevelCostsOffered() {
        int[] levelOffers = new int[this.offers.length];
        for (int i = 0; i < this.offers.length; i++) {
            levelOffers[i] = this.offers[i] != null ? this.offers[i].getCost() : 0;
        }
        return levelOffers;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取对玩家可用的的附魔选项列表. 你可以修改这些值以干预对玩家可用的附魔.
     * 如果附魔台中某个特定的格子没有附魔选项, 则这个附魔选项可能为null.
     * 附魔台中有3个附魔选项格可供编辑.
     * <p>原文：
     * Get a list of available {@link EnchantmentOffer} for the player. You can
     * modify the values to change the available offers for the player. An offer
     * may be null, if there isn't an enchantment offer at a specific slot. There
     * are 3 slots in the enchantment table available to modify.
     *
     * @return 可用的的附魔选项列表
     * <p>原文：list of available enchantment offers
     */
    public @Nullable EnchantmentOffer @NotNull[] getOffers() {
        return this.offers;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取生效的附魔等级加成 - 对应书架的数量.
     * <p>原文：
     * Get enchantment bonus in effect - corresponds to number of bookshelves
     *
     * @return 附魔等级加成
     * <p>原文：enchantment bonus
     */
    public int getEnchantmentBonus() {
        return this.bonus;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
