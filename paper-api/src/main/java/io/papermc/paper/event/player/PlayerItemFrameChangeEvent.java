package io.papermc.paper.event.player;

import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当 {@link ItemFrame} 中的物品被旋转、添加或移除时调用。
 * <p>原文：
 * Called when an {@link ItemFrame} is having an item rotated, added, or removed from it.
 */
@NullMarked
public class PlayerItemFrameChangeEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemFrame itemFrame;
    private final ItemFrameChangeAction action;
    private ItemStack itemStack;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerItemFrameChangeEvent(final Player player, final ItemFrame itemFrame, final ItemStack itemStack, final ItemFrameChangeAction action) {
        super(player);
        this.itemFrame = itemFrame;
        this.itemStack = itemStack;
        this.action = action;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件涉及的 {@link ItemFrame}。
     * <p>原文：
     * Gets the {@link ItemFrame} involved in this event.
     *
     * @return {@link ItemFrame}
     * <p>原文：the {@link ItemFrame}
     */
    public ItemFrame getItemFrame() {
        return this.itemFrame;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件涉及的 {@link ItemStack}。即正在被添加到 {@link ItemFrame}、在其中旋转或从中移除的物品。
     * <p>
     * 如果该方法返回空气，则物品展示框中的最终物品将为空。
     * <p>原文：
     * Gets the {@link ItemStack} involved in this event.
     * This is the item being added, rotated, or removed from the {@link ItemFrame}.
     * <p>
     * If this method returns air, then the resulting item in the ItemFrame will be empty.
     *
     * @return 正被添加、旋转或移除的 {@link ItemStack}
     * <p>原文：the {@link ItemStack} being added, rotated, or removed
     */
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此 {@link ItemFrame} 持有的 {@link ItemStack}。如果提供 {@code null}，该 ItemStack 将变为空气，物品展示框中的结果将为空。
     * <p>原文：
     * Sets the {@link ItemStack} that this {@link ItemFrame} holds.
     * If {@code null} is provided, the ItemStack will become air and the result in the ItemFrame will be empty.
     *
     * @param itemStack {@link ItemFrame} 物品
     * <p>原文：{@link ItemFrame} item
     */
    public void setItemStack(final @Nullable ItemStack itemStack) {
        this.itemStack = itemStack == null ? ItemStack.empty() : itemStack;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取在此 {@link ItemFrame} 上执行的操作。
     * <p>原文：
     * Gets the action that was performed on this {@link ItemFrame}.
     *
     * @return 此事件中对物品展示框执行的操作
     * <p>原文：action performed on the item frame in this event
     */
    public ItemFrameChangeAction getAction() {
        return this.action;
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

    public enum ItemFrameChangeAction {
        PLACE,
        REMOVE,
        ROTATE
    }
}
