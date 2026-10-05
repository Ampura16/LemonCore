package io.papermc.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家物品栏中某个槽位的内容改变时调用。
 * <p>原文：
 * Called when a slot contents change in a player's inventory.
 */
@NullMarked
public class PlayerInventorySlotChangeEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final int rawSlot;
    private final int slot;
    private final ItemStack oldItemStack;
    private final ItemStack newItemStack;
    private boolean triggerAdvancements = true;

    public PlayerInventorySlotChangeEvent(final Player player, final int rawSlot, final ItemStack oldItemStack, final ItemStack newItemStack) {
        super(player);
        this.rawSlot = rawSlot;
        this.slot = player.getOpenInventory().convertSlot(rawSlot);
        this.oldItemStack = oldItemStack;
        this.newItemStack = newItemStack;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 发生变化的原始槽位编号。
     * <p>原文：
     * The raw slot number that was changed.
     *
     * @return 原始槽位编号。
     * <p>原文：The raw slot number.
     */
    public int getRawSlot() {
        return this.rawSlot;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 发生变化的槽位编号，可直接传给 {@link Inventory#getItem(int)}。注意，由于一个视图关联两个不同的物品栏，可能存在两个槽位编号相同的槽位。
     * <p>
     * 如果没有打开物品栏，将使用内部合成视图进行转换。
     * <p>原文：
     * The slot number that was changed, ready for passing to
     * {@link Inventory#getItem(int)}. Note that there may be two slots with
     * the same slot number, since a view links two different inventories.
     * <p>
     * If no inventory is opened, internal crafting view is used for conversion.
     *
     * @return 槽位编号。
     * <p>原文：The slot number.
     */
    public int getSlot() {
        return this.slot;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 变更前槽位中 ItemStack 的副本。
     * <p>原文：
     * Clone of ItemStack that was in the slot before the change.
     *
     * @return 槽位中原来的 ItemStack。
     * <p>原文：The old ItemStack in the slot.
     */
    public ItemStack getOldItemStack() {
        return this.oldItemStack;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 变更后槽位中 ItemStack 的副本。
     * <p>原文：
     * Clone of ItemStack that is in the slot after the change.
     *
     * @return 槽位中新的 ItemStack。
     * <p>原文：The new ItemStack in the slot.
     */
    public ItemStack getNewItemStack() {
        return this.newItemStack;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否会触发槽位变化相关的进度。
     * <p>原文：
     * Gets whether the slot change advancements will be triggered.
     *
     * @return 是否会触发槽位变化相关的进度。
     * <p>原文：Whether the slot change advancements will be triggered.
     */
    public boolean shouldTriggerAdvancements() {
        return this.triggerAdvancements;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否会触发槽位变化相关的进度。
     * <p>原文：
     * Sets whether the slot change advancements will be triggered.
     *
     * @param triggerAdvancements 是否触发槽位变化相关的进度。
     * <p>原文：Whether the slot change advancements will be triggered.
     */
    public void setShouldTriggerAdvancements(final boolean triggerAdvancements) {
        this.triggerAdvancements = triggerAdvancements;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
