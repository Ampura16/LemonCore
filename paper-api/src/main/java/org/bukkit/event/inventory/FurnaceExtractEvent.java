package org.bukkit.event.inventory;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockExpEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家从熔炉取出产物时触发这个事件.
 * <p>原文：
 * This event is called when a player takes items out of a furnace-like block such as a
 * {@link org.bukkit.block.Furnace}, {@link org.bukkit.block.Smoker}, or
 * {@link org.bukkit.block.BlastFurnace}.
 */
public class FurnaceExtractEvent extends BlockExpEvent {

    private final Player player;
    private final ItemStack itemStack;
    private final int itemAmount;

    @ApiStatus.Internal
    public FurnaceExtractEvent(@NotNull Player player, @NotNull Block block, @NotNull ItemStack itemStack, int itemAmount, int exp) {
        super(block, exp);
        this.player = player;
        this.itemStack = itemStack;
        this.itemAmount = itemAmount;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取触发这个事件的玩家.
     * <p>原文：
     * Get the player that triggered the event
     *
     * @return 触发这个事件的玩家(译注:即拿取熔炼产物的玩家)
     * <p>原文：the relevant player
     */
    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被取出的物品的类型.
     * <p>原文：
     * Get the Material of the item being retrieved
     *
     * @return 物品类型
     * <p>原文：the material of the item
     */
    @NotNull
    public Material getItemType() {
        return this.itemStack.getType();
    }

    /**
     * Get the ItemStack of the item triggering the event
     *
     * @return the item stack
     */
    @NotNull
    public ItemStack getItemStack() {
        return this.itemStack.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被取出的物品的数量.
     * <p>原文：
     * Get the item count being retrieved
     *
     * @return 物品数量
     * <p>原文：the amount of the item
     */
    public int getItemAmount() {
        return this.itemAmount;
    }
}
