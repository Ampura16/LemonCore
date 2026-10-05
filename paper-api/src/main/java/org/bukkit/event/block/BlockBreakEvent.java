package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个方块被玩家破坏的时候，调用本事件.
 * <p>
 * 如果你想要在方块被破坏之后掉落经验, 你必须设置经验值
 * 为0以上。 默认情况下, 如果满足以下条件，经验会被设置：
 * <ol>
 * <li>玩家不能是创造/冒险模式
 * <li>玩家能掠夺这个方块 - 即使用了正确的工具破坏方块, 不是完全破坏它(译注:完全破坏可理解为破坏方块不产生任何掉落物.
 * 这条情况的常见情景有开采矿石等)
 * <li>玩家没有精准采集
 * <li>方块在原版Minecraft中掉落经验
 * </ol>
 * <p>
 * 注意:
 * 插件要模拟一个方块掉落应该设置空气为方块，利用自己的方法确定方块被破坏后默认掉落什么，和要做什么。
 * <p>
 * 如果方块破坏事件（即本事件）被取消，这个方块将不会被破坏，并且经验不会掉落。
 * <p>原文：
 * Called when a block is broken by a player.
 * <p>
 * If you wish to have the block drop experience, you must set the experience
 * value above 0. By default, experience will be set in the event if:
 * <ol>
 * <li>The player is not in creative or adventure mode
 * <li>The player can loot the block (ie: does not destroy it completely, by
 *     using the correct tool)
 * <li>The player does not have silk touch
 * <li>The block drops experience in vanilla Minecraft
 * </ol>
 * <p>
 * Note:
 * Plugins wanting to simulate a traditional block drop should set the block
 * to air and utilize their own methods for determining what the default drop
 * for the block being broken is and what to do about it, if anything.
 * <p>
 * If this event is cancelled, the block will not break and
 * experience will not drop.
 */
public class BlockBreakEvent extends BlockExpEvent implements Cancellable {

    private final Player player;
    private boolean dropItems;

    private boolean cancelled;

    @ApiStatus.Internal
    public BlockBreakEvent(@NotNull final Block block, @NotNull final Player player) {
        super(block, 0);

        this.player = player;
        this.dropItems = true; // Defaults to dropping items as it normally would
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取破坏这个方块的玩家.
     * <p>原文：
     * Gets the Player that is breaking the block involved in this event.
     *
     * @return 破坏这个方块的玩家
     * <p>原文：The Player that is breaking the block involved in this event
     */
    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置破坏这个方块后是否像平常那样尝试掉落物品.
     * 当且仅当设为 false 时, {@link BlockDropItemEvent} 事件将不会在本事件后触发.
     * <p>原文：
     * Sets whether the block will attempt to drop items as it normally
     * would.
     * <p>
     * If and only if this is {@code false} then {@link BlockDropItemEvent} will not be
     * called after this event.
     *
     * @param dropItems 这个方块是否尝试掉落物品 (注:设为 true 时只代表可能掉落, 而非 100% 掉落)
     * <p>原文：Whether the block will attempt to drop items
     */
    public void setDropItems(boolean dropItems) {
        this.dropItems = dropItems;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取方块是否可能掉落物品.
     * <p>原文：
     * Gets whether the block will attempt to drop items.
     * <p>
     * If and only if this is {@code false} then {@link BlockDropItemEvent} will not be
     * called after this event.
     *
     * @return 这个方块是否掉落物品
     * <p>原文：Whether the block will attempt to drop items
     */
    public boolean isDropItems() {
        return this.dropItems;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
