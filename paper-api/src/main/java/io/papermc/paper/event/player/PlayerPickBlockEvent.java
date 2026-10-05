package io.papermc.paper.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家对方块使用选取物品功能（鼠标中键点击方块以获取对应物品）时触发的事件。处理完该事件后，来源槽位与目标槽位的内容将被交换，并将玩家当前选中的快捷栏槽位设为目标槽位。
 * <p>原文：
 * Event that is fired when a player uses the pick item functionality on a block
 * (middle-clicking a block to get the appropriate item).
 * After the handling of this event, the contents of the source and the target slot will be swapped,
 * and the currently selected hotbar slot of the player will be set to the target slot.
 */
@NullMarked
public class PlayerPickBlockEvent extends PlayerPickItemEvent {

    private final Block block;

    @ApiStatus.Internal
    public PlayerPickBlockEvent(final Player player, final Block block, final boolean includeData, final int targetSlot, final int sourceSlot) {
        super(player, includeData, targetSlot, sourceSlot);
        this.block = block;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此事件关联的方块。
     * <p>原文：
     * Retrieves the block associated with this event.
     *
     * @return 事件涉及的方块
     * <p>原文：the block involved in the event
     */
    public Block getBlock() {
        return this.block;
    }
}
