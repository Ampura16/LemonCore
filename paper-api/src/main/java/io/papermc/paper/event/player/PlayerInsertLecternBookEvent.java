package io.papermc.paper.event.player;

import com.google.common.base.Preconditions;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家点击讲台以放入书时调用此事件。如果此事件被取消，玩家将保留书，讲台仍为空。
 * <p>原文：
 * This event is called when a player clicks on a lectern to insert a book.
 * If this event is cancelled the player will keep the book and the lectern will remain empty.
 */
@NullMarked
public class PlayerInsertLecternBookEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block block;
    private ItemStack book;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerInsertLecternBookEvent(final Player player, final Block block, final ItemStack book) {
        super(player);
        this.block = block;
        this.book = book;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件涉及的讲台方块。
     * <p>原文：
     * Gets the block of the lectern involved in this event.
     *
     * @return 讲台方块
     * <p>原文：the block of the lectern
     */
    public Block getBlock() {
        return this.block;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取属于此事件的讲台方块状态。该方法会构造一个新的快照 {@link org.bukkit.block.BlockState}。
     * <p>原文：
     * Fetches the lectern block state that was part of this event.
     * This method constructs a new snapshot {@link org.bukkit.block.BlockState}.
     *
     * @return 所涉及讲台的新讲台状态快照
     * <p>原文：a new lectern state snapshot of the involved lectern
     * @throws IllegalStateException 如果位于 {@link #getBlock()} 的方块不再是讲台
     * <p>原文：if the block at {@link #getBlock()} is no longer a lectern
     */
    public Lectern getLectern() {
        final BlockState state = this.getBlock().getState();
        Preconditions.checkState(state instanceof Lectern, "Block state of lectern block is no longer a lectern tile state!");
        return (Lectern) state;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回玩家尝试放入的物品堆。这是物品的副本，对该物品堆所做的更改不会影响正被放上讲台的书。如果要修改最终放在讲台上的物品堆，请使用 {@link #setBook(ItemStack)}。
     * <p>原文：
     * Returns the itemstack the player tried to insert. This is a copy of the item,
     * changes made to this itemstack will not affect the book that is being placed in the lectern.
     * If you want to mutate the item stack that ends up in the lectern, use {@link #setBook(ItemStack)}.
     *
     * @return 正被放置的书
     * <p>原文：the book that is being placed
     */
    public ItemStack getBook() {
        return this.book.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置要放上讲台的物品堆。
     * <p>原文：
     * Sets the itemstack to insert into the lectern.
     *
     * @param book 要放入的书（非书物品会使讲台处于锁定状态，因为菜单无法打开，从而阻止取出物品）
     * <p>原文：the book to insert (non book items will leave the lectern in a locked
     *             state as the menu cannot be opened, preventing item extraction)
     */
    public void setBook(final ItemStack book) {
        Preconditions.checkArgument(book != null, "Cannot set book to null");
        this.book = book.clone();
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
