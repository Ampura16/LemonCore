package org.bukkit.event.player;

import org.bukkit.block.Lectern;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家点击按钮从讲台上取走书时触发此事件。如果此事件被取消，书将保留在讲台上.
 * <p>原文：
 * This event is called when a player clicks the button to take a book of a
 * Lectern. If this event is cancelled the book remains on the lectern.
 */
public class PlayerTakeLecternBookEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Lectern lectern;
    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerTakeLecternBookEvent(@NotNull Player player, @NotNull Lectern lectern) {
        super(player);
        this.lectern = lectern;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取涉及的讲台.
     * <p>原文：
     * Gets the lectern involved.
     *
     * @return 讲台
     * <p>原文：the Lectern
     */
    @NotNull
    public Lectern getLectern() {
        return this.lectern;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取讲台上的当前 ItemStack.
     * <p>原文：
     * Gets the current ItemStack on the lectern.
     *
     * @return 讲台上的 ItemStack
     * <p>原文：the ItemStack on the Lectern
     */
    @Nullable
    public ItemStack getBook() {
        return this.lectern.getInventory().getItem(0);
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
