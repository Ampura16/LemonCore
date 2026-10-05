package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家编辑或签名书与笔时触发。如果事件中断取消，书与笔的元数据不会改变。
 * <p>原文：
 * Called when a player edits or signs a book and quill item. If the event is
 * cancelled, no changes are made to the BookMeta
 */
public class PlayerEditBookEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final BookMeta previousBookMeta;
    private final int slot;
    private BookMeta newBookMeta;
    private boolean isSigning;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerEditBookEvent(@NotNull Player player, int slot, @NotNull BookMeta previousBookMeta, @NotNull BookMeta newBookMeta, boolean isSigning) {
        super(player);

        this.previousBookMeta = previousBookMeta;
        this.newBookMeta = newBookMeta;
        this.slot = slot;
        this.isSigning = isSigning;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前书本元数据。
     * <p>
     * 注意：获取书本的元数据副本。你无法使用该对象来修改书本元数据。
     * <p>原文：
     * Gets the book meta currently on the book.
     * <p>
     * Note: this is a copy of the book meta. You cannot use this object to
     * change the existing book meta.
     *
     * @return 当前书本的元数据
     * <p>原文：the book meta currently on the book
     */
    @NotNull
    public BookMeta getPreviousBookMeta() {
        return this.previousBookMeta.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家试图新增的书本元数据。
     * <p>
     * 注意：获取的元数据为副本。使用 {@link #setNewBookMeta(BookMeta)}修改实际想要更新的书本元数据
     * <p>原文：
     * Gets the book meta that the player is attempting to add to the book.
     * <p>
     * Note: this is a copy of the proposed new book meta. Use {@link
     * #setNewBookMeta(BookMeta)} to change what will actually be added to the
     * book.
     *
     * @return 玩家试图新增的书本元数据
     * <p>原文：the book meta that the player is attempting to add
     */
    @NotNull
    public BookMeta getNewBookMeta() {
        return this.newBookMeta.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取触发事件时，书本在物品栏所在的格子序号.
     * <p>
     * 对应为玩家快捷操作栏，取值范围 0-8, 或-1代表副手.
     * <p>原文：
     * Gets the inventory slot number for the book item that triggered this
     * event.
     * <p>
     * This is a slot number on the player's hotbar in the range 0-8, or -1 for
     * off hand.
     *
     * @return 事件中书本在玩家物品栏所占用的格子序号
     * <p>原文：the inventory slot number that the book item occupies
     * @deprecated 书可能被副手签名
     * <p>原文：books may be signed from off hand
     */
    @Deprecated(since = "1.13.1", forRemoval = true)
    public int getSlot() {
        return this.slot;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置新增的书本元数据。
     * <p>原文：
     * Sets the book meta that will actually be added to the book.
     *
     * @param newBookMeta 构造书本元数据
     * <p>原文：new book meta
     * @throws IllegalArgumentException 当构造的书本元数据不为空值时抛出异常
     * <p>原文：if the new book meta is null
     */
    public void setNewBookMeta(@NotNull BookMeta newBookMeta) throws IllegalArgumentException {
        Preconditions.checkArgument(newBookMeta != null, "New book meta must not be null");
        Bukkit.getItemFactory().equals(newBookMeta, null);
        this.newBookMeta = newBookMeta.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测书本是否正在被签名。如果正在签名，书与笔将转变为成书。
     * <p>原文：
     * Gets whether the book is being signed. If a book is signed the
     * Material changes from BOOK_AND_QUILL to WRITTEN_BOOK.
     *
     * @return 当书本正在被签名时，返回true。
     * <p>原文：{@code true} if the book is being signed
     */
    public boolean isSigning() {
        return this.isSigning;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置书本是否正在被签名。如果正在签名，书与笔将转变为成书。
     * <p>原文：
     * Sets whether the book is being signed. If a book is signed the
     * Material changes from BOOK_AND_QUILL to WRITTEN_BOOK.
     *
     * @param signing 书本是否正在被签名
     * <p>原文：whether the book is being signed.
     */
    public void setSigning(boolean signing) {
        this.isSigning = signing;
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
