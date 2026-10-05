package io.papermc.paper.event.block;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家对方块使用剪刀时调用。
 * <p>
 * 玩家用剪刀破坏方块时<b>不会</b>调用该事件，而是在玩家对方块使用剪刀以收集掉落物和/或改变其状态时调用。
 * <p>
 * 例如剪南瓜将其变为雕刻南瓜，或剪蜂巢获取蜜脾。
 * <p>原文：
 * Called when a player uses shears on a block.
 * <p>
 * This event is <b>not</b> called when a player breaks blocks with shears, but rather when a
 * player uses the shears on a block to collect drops from it and/or modify its state.
 * <p>
 * Examples include shearing a pumpkin to turn it into a carved pumpkin or shearing a beehive to get honeycomb.
 */
@NullMarked
public class PlayerShearBlockEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block block;
    private final ItemStack item;
    private final EquipmentSlot hand;
    private final List<ItemStack> drops;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerShearBlockEvent(final Player player, final Block block, final ItemStack item, final EquipmentSlot hand, final List<ItemStack> drops) {
        super(player);
        this.block = block;
        this.item = item;
        this.hand = hand;
        this.drops = drops;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件中被剪的方块。
     * <p>原文：
     * Gets the block being sheared in this event.
     *
     * @return 此事件中被剪方块的 {@link Block}。
     * <p>原文：The {@link Block} which block is being sheared in this event.
     */
    public Block getBlock() {
        return this.block;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取用于剪方块的物品。
     * <p>原文：
     * Gets the item used to shear the block.
     *
     * @return 剪刀的 {@link ItemStack}。
     * <p>原文：The {@link ItemStack} of the shears.
     */
    public ItemStack getItem() {
        return this.item;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取用于剪方块的手。
     * <p>原文：
     * Gets the hand used to shear the block.
     *
     * @return {@link EquipmentSlot#HAND} 或 {@link EquipmentSlot#OFF_HAND} 之一。
     * <p>原文：Either {@link EquipmentSlot#HAND} OR {@link EquipmentSlot#OFF_HAND}.
     */
    public EquipmentSlot getHand() {
        return this.hand;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件最终的掉落物。
     * <p>原文：
     * Gets the resulting drops of this event.
     *
     * @return 将因此事件而掉落的 {@link ItemStack items} 的可变 {@link List list}。
     * <p>原文：A mutable {@link List list} of {@link ItemStack items} that will be dropped as result of this event.
     */
    public List<ItemStack> getDrops() {
        return this.drops;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否应取消剪方块。
     * <p>原文：
     * Gets whether the shearing of the block should be cancelled or not.
     *
     * @return 是否应取消剪方块。
     * <p>原文：Whether the shearing of the block should be cancelled or not.
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否应取消剪方块。
     * <p>原文：
     * Sets whether the shearing of the block should be cancelled or not.
     *
     * @param cancel 是否应取消对该方块的剪切。
     * <p>原文：whether the shearing of the block should be cancelled or not.
     */
    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
