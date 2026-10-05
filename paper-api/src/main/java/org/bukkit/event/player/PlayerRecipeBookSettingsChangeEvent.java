package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家更改配方书设置时触发.
 * <p>原文：
 * Called when a player changes recipe book settings.
 */
public class PlayerRecipeBookSettingsChangeEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final RecipeBookType recipeBookType;
    private final boolean open;
    private final boolean filtering;

    @ApiStatus.Internal
    public PlayerRecipeBookSettingsChangeEvent(@NotNull final Player player, @NotNull final RecipeBookType recipeBookType, final boolean open, final boolean filtering) {
        super(player);
        this.recipeBookType = recipeBookType;
        this.open = open;
        this.filtering = filtering;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家正在更改设置的配方书类型.
     * <p>原文：
     * Gets the type of recipe book the player is changing the settings for.
     *
     * @return 配方书类型
     * <p>原文：the type of recipe book
     */
    @NotNull
    public RecipeBookType getRecipeBookType() {
        return this.recipeBookType;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查配方书是正在被打开还是关闭.
     * <p>原文：
     * Checks if the recipe book is being opened or closed.
     *
     * @return 如果正在打开则为 true
     * <p>原文：{@code true} if opening
     */
    public boolean isOpen() {
        return this.open;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查配方书过滤器是正在被启用还是禁用.
     * <p>原文：
     * Checks if the recipe book filter is being enabled or disabled.
     *
     * @return 如果正在启用则为 true
     * <p>原文：{@code true} if enabling
     */
    public boolean isFiltering() {
        return this.filtering;
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

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示各种配方书类型的枚举.
     * <br>
     * 不同类型的配方书在不同的 GUI 中显示.
     * <p>原文：
     * Enum representing the various types of recipe book.
     * <br>
     * Different types of recipe book are shown in different GUIs.
     */
    public enum RecipeBookType {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在工作台和玩家背包中看到的配方书.
         * <p>原文：
         * Recipe book seen in crafting table and player inventory.
         */
        CRAFTING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在熔炉中看到的配方书.
         * <p>原文：
         * Recipe book seen in furnace.
         */
        FURNACE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在高炉中看到的配方书.
         * <p>原文：
         * Recipe book seen in blast furnace.
         */
        BLAST_FURNACE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在烟熏炉中看到的配方书.
         * <p>原文：
         * Recipe book seen in smoker.
         */
        SMOKER;
    }
}
