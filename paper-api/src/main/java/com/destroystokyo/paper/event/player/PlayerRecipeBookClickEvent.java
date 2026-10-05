package com.destroystokyo.paper.event.player;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在玩家点击配方书中的配方时调用
 * <p>原文：
 * Called when a player clicks a recipe in the recipe book
 */
@NullMarked
public class PlayerRecipeBookClickEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private NamespacedKey recipe;
    private boolean makeAll;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerRecipeBookClickEvent(final Player player, final NamespacedKey recipe, final boolean makeAll) {
        super(player);
        this.recipe = recipe;
        this.makeAll = makeAll;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家点击的配方的命名空间键
     * <p>原文：
     * Gets the namespaced key of the recipe that was clicked by the player
     *
     * @return 配方的命名空间键
     * <p>原文：The namespaced key of the recipe
     */
    public NamespacedKey getRecipe() {
        return this.recipe;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 更改所请求的配方。将请求的配方设为具有给定键的配方
     * <p>原文：
     * Changes what recipe is requested. This sets the requested recipe to the recipe with the given key
     *
     * @param recipe 应请求的配方的键
     * <p>原文：The key of the recipe that should be requested
     */
    public void setRecipe(final NamespacedKey recipe) {
        this.recipe = recipe;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取一个布尔值，指示玩家是否请求合成最大数量的结果。如果在配方书中点击配方时按住 Shift，则为 {@code true}
     * <p>原文：
     * Gets a boolean which indicates whether the player requested to make the maximum amount of results. This is
     * {@code true} if shift is pressed while the recipe is clicked in the recipe book
     *
     * @return 如果点击配方时按住了 Shift 则为 {@code true}
     * <p>原文：{@code true} if shift is pressed while the recipe is clicked
     */
    public boolean isMakeAll() {
        return this.makeAll;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否应合成最大数量的结果。如果为 {@code true}，该请求将按玩家点击配方时按住 Shift 的情况处理
     * <p>原文：
     * Sets whether the maximum amount of results should be made. If this is {@code true}, the request is handled as if
     * the player had pressed shift while clicking on the recipe
     *
     * @param makeAll 如果请求应尝试生成最大数量的结果则为 {@code true}
     * <p>原文：{@code true} if the request should attempt to make the maximum amount of results
     */
    public void setMakeAll(final boolean makeAll) {
        this.makeAll = makeAll;
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
