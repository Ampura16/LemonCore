package io.papermc.paper.event.player;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家使用选取物品功能（鼠标中键点击 {@link PlayerPickBlockEvent block} 或 {@link PlayerPickEntityEvent entity} 以获取对应物品）时触发的事件。处理完该事件后，来源槽位与目标槽位的内容将被交换，并将玩家当前选中的快捷栏槽位设为目标槽位。
 * <p>原文：
 * Event that is fired when a player uses the pick item functionality
 * (middle-clicking a {@link PlayerPickBlockEvent block}
 * or {@link PlayerPickEntityEvent entity} to get the appropriate item).
 * After the handling of this event, the contents of the source and the target slot will be swapped,
 * and the currently selected hotbar slot of the player will be set to the target slot.
 *
 * @see PlayerPickEntityEvent
 * @see PlayerPickBlockEvent
 */
@NullMarked
public abstract class PlayerPickItemEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final boolean includeData;

    private int targetSlot;
    private int sourceSlot;

    private boolean cancelled;

    @ApiStatus.Internal
    protected PlayerPickItemEvent(final Player player, final boolean includeData, final int targetSlot, final int sourceSlot) {
        super(player);
        this.includeData = includeData;
        this.targetSlot = targetSlot;
        this.sourceSlot = sourceSlot;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查玩家是否希望包含方块/实体数据。
     * <p>原文：
     * Checks whether the player wants block/entity data included.
     *
     * @return 如果包含数据则为 {@code true}，否则为 {@code false}。
     * <p>原文：{@code true} if data is included, otherwise {@code false}.
     */
    public boolean isIncludeData() {
        return includeData;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回被选取的物品要放入的槽位。
     * <p>原文：
     * Returns the slot the item that is being picked goes into.
     *
     * @return 快捷栏槽位（0-8，含两端）
     * <p>原文：hotbar slot (0-8 inclusive)
     */
    public @Range(from = 0, to = 8) int getTargetSlot() {
        return this.targetSlot;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 更改被选取的物品要放入的槽位。
     * <p>原文：
     * Changes the slot the item that is being picked goes into.
     *
     * @param targetSlot 快捷栏槽位（0-8，含两端）
     * <p>原文：hotbar slot (0-8 inclusive)
     */
    public void setTargetSlot(final @Range(from = 0, to = 8) int targetSlot) {
        Preconditions.checkArgument(targetSlot >= 0 && targetSlot <= 8, "Target slot must be in range 0 - 8 (inclusive)");
        this.targetSlot = targetSlot;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回将要放入玩家快捷栏的物品所在的槽位。
     * <p>
     * 如果物品不在玩家物品栏中，则返回 {@code -1}。这种情况下如果玩家处于创造模式，物品将被凭空生成。
     * <p>原文：
     * Returns the slot in which the item that will be put into the players hotbar is located.
     * <p>
     * Returns {@code -1} if the item is not in the player's inventory.
     * If this is the case and the player is in creative mode, the item will be spawned in.
     *
     * @return 玩家物品栏槽位（0-35，含两端；如果不在玩家物品栏中则为 {@code -1}）
     * <p>原文：player inventory slot (0-35 inclusive, or {@code -1} if not in the player inventory)
     */
    public @Range(from = -1, to = 35) int getSourceSlot() {
        return this.sourceSlot;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 更改将放入玩家快捷栏的物品的来源槽位。
     * <p>
     * 如果设为 {@code -1} 且玩家处于创造模式，物品将被凭空生成。
     * <p>原文：
     * Change the source slot from which the item that will be put in the players hotbar will be taken.
     * <p>
     * If set to {@code -1} and the player is in creative mode, the item will be spawned in.
     *
     * @param sourceSlot 玩家物品栏槽位（0-35，含两端；如果不在玩家物品栏中则为 {@code -1}）
     * <p>原文：player inventory slot (0-35 inclusive, or {@code -1} if not in the player inventory)
     */
    public void setSourceSlot(final @Range(from = -1, to = 35) int sourceSlot) {
        Preconditions.checkArgument(sourceSlot >= -1 && sourceSlot <= 35, "Source slot must be in range of the player's inventory slot, or -1");
        this.sourceSlot = sourceSlot;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

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
