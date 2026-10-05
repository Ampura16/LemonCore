package org.bukkit.event.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家打开物品栏时触发本事件
 * <p>原文：
 * Called when a player opens an inventory
 */
public class InventoryOpenEvent extends InventoryEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Component titleOverride;
    private boolean cancelled;

    @ApiStatus.Internal
    public InventoryOpenEvent(@NotNull InventoryView transaction) {
        super(transaction);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回涉及此事件的玩家.
     * <p>原文：
     * Returns the player involved in this event
     *
     * @return 涉及此事件的玩家
     * <p>原文：Player who is involved in this event
     */
    @NotNull
    public final HumanEntity getPlayer() {
        return this.transaction.getPlayer();
    }

    /**
     * Gets the title override set by another event or {@code null}
     * if not set.
     *
     * @return the title override or {@code null}
     */
    public @Nullable Component titleOverride() {
        return this.titleOverride;
    }

    /**
     * Sets the title override or clears the override.
     * <p>
     * This is only the title sent to the client in the open packet, this doesn't change
     * the title returned by {@link InventoryView#title()}, hence "override".
     * <p>
     * <b>NOTE:</b> Horse and nautilus inventories are a special case where setting this will
     * have no effect. These inventory titles are set by the entity's display name.
     *
     * @param titleOverride the title override or {@code null}
     */
    public void titleOverride(@Nullable Component titleOverride) {
        this.titleOverride = titleOverride;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件的取消状态. 一个取消的事件将不会在服务器上执行，但将仍然传递给其他插件.
     * <p>
     * 如果一个物品栏打开事件被取消，物品栏界面将不会显示.
     * <p>原文：
     * {@inheritDoc}
     * <p>
     * If this event is cancelled, the inventory screen will not
     * show.
     *
     * @return 事件取消状态
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此事件的取消状态. 一个取消的事件将不会在服务器上执行，但将仍然传递给其他插件.
     * <p>
     * 如果一个物品栏打开事件被取消，物品栏界面将不会显示.
     * <p>原文：
     * {@inheritDoc}
     * <p>
     * If this event is cancelled, the inventory screen will not
     * show.
     *
     * @param cancel 事件取消状态
     */
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
