package org.bukkit.event.enchantment;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 成功附魔物品的事件 (在附魔台里面附魔的)
 * <p>原文：
 * Called when an ItemStack is successfully enchanted (currently at
 * enchantment table)
 */
public class EnchantItemEvent extends InventoryEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player enchanter;
    private final Block table;
    private ItemStack item;
    private int level;
    private final Map<Enchantment, Integer> enchants;
    private final Enchantment enchantmentHint;
    private final int levelHint;
    private final int button;

    private boolean cancelled;

    @ApiStatus.Internal
    public EnchantItemEvent(@NotNull final Player enchanter, @NotNull final InventoryView view, @NotNull final Block table, @NotNull final ItemStack item, final int level, @NotNull final Map<Enchantment, Integer> enchants, @NotNull final Enchantment enchantmentHint, final int levelHint, final int button) {
        super(view);
        this.enchanter = enchanter;
        this.table = table;
        this.item = item;
        this.level = level;
        this.enchants = new HashMap<>(enchants);
        this.enchantmentHint = enchantmentHint;
        this.levelHint = levelHint;
        this.button = button;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取附魔这个物品的玩家。
     * <p>原文：
     * Gets the player enchanting the item
     *
     * @return 附魔这个物品的玩家
     * <p>原文：enchanting player
     */
    @NotNull
    public Player getEnchanter() {
        return this.enchanter;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取附魔这个物品的方块。
     * <p>原文：
     * Gets the block being used to enchant the item
     *
     * @return 附魔这个物品的方块
     * <p>原文：the block used for enchanting
     */
    @NotNull
    public Block getEnchantBlock() {
        return this.table;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被附魔的物品(可改动).
     * <p>原文：
     * Gets the item to be enchanted (can be modified)
     *
     * @return 被附魔的物品
     * <p>原文：ItemStack of item
     */
    @NotNull
    public ItemStack getItem() {
        return this.item;
    }

    /**
     * Sets the item to be enchanted
     */
    public void setItem(@NotNull final ItemStack item) {
        this.item = item;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取花费的经验等级(最低), 这个等级以数字形式显示在附魔台界面中附魔选项的右侧.
     * <p>原文：
     * Gets the cost (minimum level) which is displayed as a number on the right
     * hand side of the enchantment offer.
     *
     * @return 花费的经验等级
     * <p>原文：experience level cost
     */
    public int getExpLevelCost() {
        return this.level;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置花费的经验等级(最低), 这个等级以数字形式显示在附魔台界面中附魔选项的右侧.
     * <p>原文：
     * Sets the cost (minimum level) which is displayed as a number on the right
     * hand side of the enchantment offer.
     *
     * @param level - 花费的经验等级
     * <p>原文：cost in levels
     */
    public void setExpLevelCost(int level) {
        Preconditions.checkArgument(level > 0, "The cost must be greater than 0!");

        this.level = level;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被加到物品中的附魔的Map (等级, 附魔种类)（若要修改请直接修改Map） . 注意: 不能被添加
     * 到物品中的附魔会被忽略哦。
     * <p>原文：
     * Get map of enchantment (levels, keyed by type) to be added to item
     * (modify map returned to change values). Note: Any enchantments not
     * allowed for the item will be ignored
     *
     * @return 被加到物品中的附魔的Map (等级, 附魔种类)
     * <p>原文：map of enchantment levels, keyed by enchantment
     */
    @NotNull
    public Map<Enchantment, Integer> getEnchantsToAdd() {
        return this.enchants;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在选中的附魔选项中作为提示显示给玩家的 {@link Enchantment}.
     * <p>原文：
     * Get the {@link Enchantment} that was displayed as a hint to the player
     * on the selected enchantment offer.
     *
     * @return 提示的附魔
     * <p>原文：the hinted enchantment
     */
    @NotNull
    public Enchantment getEnchantmentHint() {
        return this.enchantmentHint;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在选中的附魔选项中作为提示显示给玩家的附魔等级.
     * <p>原文：
     * Get the level of the enchantment that was displayed as a hint to the
     * player on the selected enchantment offer.
     *
     * @return 提示的附魔等级
     * <p>原文：the level of the hinted enchantment
     */
    public int getLevelHint() {
        return this.levelHint;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家点击的附魔的按钮。
     * <p>原文：
     * Which button was pressed to initiate the enchanting.
     *
     * @return 按钮的序号 (0, 1, 2).
     * <p>原文：The button index (0, 1, or 2).
     */
    public int whichButton() {
        return this.button;
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
