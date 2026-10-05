package io.papermc.paper.event.player;

import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PlayerLecternPageChangeEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Lectern lectern;
    private final ItemStack book;
    private final PageChangeDirection pageChangeDirection;
    private final int oldPage;
    private int newPage;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerLecternPageChangeEvent(final Player player, final Lectern lectern, final ItemStack book, final PageChangeDirection pageChangeDirection, final int oldPage, final int newPage) {
        super(player);
        this.lectern = lectern;
        this.book = book;
        this.pageChangeDirection = pageChangeDirection;
        this.oldPage = oldPage;
        this.newPage = newPage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取涉及的讲台。
     * <p>原文：
     * Gets the lectern involved.
     *
     * @return 讲台
     * <p>原文：the Lectern
     */
    public Lectern getLectern() {
        return this.lectern;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取讲台上当前的 ItemStack。
     * <p>原文：
     * Gets the current ItemStack on the lectern.
     *
     * @return 讲台上的 ItemStack
     * <p>原文：the ItemStack on the Lectern
     */
    public ItemStack getBook() {
        return this.book;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取翻页方向。本质上返回玩家点击的是哪个按钮：左或右。
     * <p>原文：
     * Gets the page change direction. This is essentially returns which button the player clicked, left or right.
     *
     * @return 翻页方向
     * <p>原文：the page change direction
     */
    public PageChangeDirection getPageChangeDirection() {
        return this.pageChangeDirection;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取翻页前的页码。<i>页码从 0 开始。</i>
     * <p>原文：
     * Gets the page changed from. <i>Pages are 0-indexed.</i>
     *
     * @return 翻页前的页码
     * <p>原文：the page changed from
     */
    public int getOldPage() {
        return this.oldPage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取翻页后的页码。<i>页码从 0 开始。</i>
     * <p>原文：
     * Gets the page changed to. <i>Pages are 0-indexed.</i>
     *
     * @return 翻页后的页码
     * <p>原文：the page changed to
     */
    public int getNewPage() {
        return this.newPage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置翻页后的页码。<i>页码从 0 开始。</i> 大于总页数的页码将显示最后一页。
     * <p>原文：
     * Sets the page changed to. <i>Pages are 0-indexed.</i>
     * Page indices that are greater than the number of pages will show the last page.
     *
     * @param newPage 翻页后的新页码
     * <p>原文：the new paged changed to
     */
    public void setNewPage(final int newPage) {
        this.newPage = newPage;
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

    public enum PageChangeDirection {
        LEFT,
        RIGHT,
    }
}
