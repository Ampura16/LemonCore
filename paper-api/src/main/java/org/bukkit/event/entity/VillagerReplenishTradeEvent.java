package org.bukkit.event.entity;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Villager;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当 {@link Villager} 即将补充其交易之一时调用。
 * <p>
 * 如果此事件通过，村民将把受影响的 {@link #getRecipe() MerchantRecipe} 的 {@link MerchantRecipe#getUses() uses} 重置为 <code>0</code>。
 * <p>原文：
 * Called when a {@link Villager} is about to restock one of its trades.
 * <p>
 * If this event passes, the villager will reset the
 * {@link MerchantRecipe#getUses() uses} of the affected {@link #getRecipe()
 * MerchantRecipe} to <code>0</code>.
 */
public class VillagerReplenishTradeEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private MerchantRecipe recipe;
    private boolean cancelled;

    @ApiStatus.Internal
    public VillagerReplenishTradeEvent(@NotNull AbstractVillager villager, @NotNull MerchantRecipe recipe) {
        super(villager);
        this.recipe = recipe;
    }

    @NotNull
    @Override
    public AbstractVillager getEntity() {
        return (AbstractVillager) super.getEntity();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取要补充的配方。
     * <p>原文：
     * Get the recipe to replenish.
     *
     * @return 补充的配方
     * <p>原文：the replenished recipe
     */
    @NotNull
    public MerchantRecipe getRecipe() {
        return this.recipe;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置要补充的配方。
     * <p>原文：
     * Set the recipe to replenish.
     *
     * @param recipe 补充的配方
     * <p>原文：the replenished recipe
     */
    public void setRecipe(@NotNull MerchantRecipe recipe) {
        this.recipe = recipe;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取添加的额外使用次数。
     * <p>原文：
     * Get the bonus uses added.
     *
     * @return 添加的额外使用次数
     * <p>原文：the extra uses added
     * @deprecated MC 1.14 改变了村民补充交易的方式。使用 {@link MerchantRecipe#getUses()}。
     * <p>原文：MC 1.14 has changed how villagers restock their trades. Use
     * {@link MerchantRecipe#getUses()}.
     */
    @Deprecated(since = "1.18.1")
    public int getBonus() {
        return this.recipe.getUses();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置添加的额外使用次数。
     * <p>原文：
     * Set the bonus uses added.
     *
     * @param bonus 添加的额外使用次数
     * <p>原文：the extra uses added
     * @deprecated MC 1.14 改变了村民补充交易的方式。这不再有效。
     * <p>原文：MC 1.14 has changed how villagers restock their trades. This
     * has no effect anymore.
     */
    @Deprecated(since = "1.18.1")
    public void setBonus(int bonus) {
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
