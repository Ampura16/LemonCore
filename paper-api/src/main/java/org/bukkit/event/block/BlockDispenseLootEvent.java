package org.bukkit.event.block;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当方块从其指定的战利品表中发放战利品时触发.
 * 不要与 {@link BlockDispenseEvent} 等事件混淆，后者在从其库存容器中发放单个物品时触发.
 * <br><br>
 * 示例：玩家解锁试炼密室宝库，宝库方块发放其战利品.
 * <p>原文：
 * Called when a block dispenses loot from its designated LootTable.
 * <p>
 * This is not to be confused with events like {@link BlockDispenseEvent} which fires when a
 * singular item is dispensed from its inventory container.
 * <br>
 * Example: A player unlocks a trial chamber vault and the vault block dispenses
 * its loot.
 */
public class BlockDispenseLootEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private List<ItemStack> dispensedLoot;
    private final LootTable lootTable;

    private boolean cancelled;

    @ApiStatus.Internal
    public BlockDispenseLootEvent(@Nullable Player player, @NotNull Block block, @NotNull List<ItemStack> dispensedLoot, @NotNull LootTable lootTable) {
        super(block);
        this.player = player;
        this.dispensedLoot = dispensedLoot;
        this.lootTable = lootTable;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被发放的战利品.
     * <p>原文：
     * Gets the loot that will be dispensed.
     *
     * @return 将被发放的战利品
     * <p>原文：the loot that will be dispensed
     */
    @NotNull
    public List<ItemStack> getDispensedLoot() {
        return this.dispensedLoot;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将被发放的战利品.
     * <p>原文：
     * Sets the loot that will be dispensed.
     *
     * @param dispensedLoot 新的战利品
     * <p>原文：new loot to dispense
     */
    public void setDispensedLoot(@Nullable List<ItemStack> dispensedLoot) {
        this.dispensedLoot = dispensedLoot == null ? new ArrayList<>() : dispensedLoot;
    }

    /**
     * Gets the loot table used to generate the initial loot to dispense.
     *
     * @return the loot table used to generate the initial loot to dispense
     */
    @NotNull
    public LootTable getLootTable() {
        return this.lootTable;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与此事件关联的玩家.
     * <br>
     * <b>警告：</b> 某些事件实例，如 {@link org.bukkit.block.TrialSpawner} 发放其奖励战利品，可能没有关联的玩家，将返回 null.
     * <p>原文：
     * Gets the player associated with this event.
     * <br>
     * <b>Warning:</b> Some event instances like a
     * {@link org.bukkit.block.TrialSpawner} dispensing its reward loot may not
     * have a player associated with them and will return {@code null}.
     *
     * @return 解锁宝库的玩家
     * <p>原文：the player who unlocked the vault
     */
    @Nullable
    public Player getPlayer() {
        return this.player;
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
