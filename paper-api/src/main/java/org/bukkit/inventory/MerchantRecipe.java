package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个商人交易。
 * <p>
 * 交易可以使用一种或两种材料，并提供一种结果。材料的 ItemStack 数量在交易中会被考虑。
 * <p>
 * 交易有最大使用次数。{@link Villager} 可以通过将其商人配方的
 * {@link #getUses 使用次数} 重置为 <code>0</code> 来定期补充交易，从而允许再次使用。
 * <p>
 * 交易完成时可能会也可能不会奖励经验值。
 * <p>
 * 在交易期间，{@link MerchantRecipe} 会根据以下标准动态调整其第一种材料的数量：
 * <ul>
 * <li>{@link #getDemand() 需求}：此值由拥有此商人配方的村民根据该配方自上次补货以来的使用频率
 * 相对于其 {@link #getMaxUses 最大使用次数} 进行定期更新。需求对第一种材料数量的影响
 * 由配方的 {@link #getPriceMultiplier 价格倍率} 缩放，且永远不会低于零。
 * <li>{@link #getSpecialPrice() 特殊价格}：每当玩家开始或停止与拥有此商人配方的村民交易时，
 * 此值会动态更新。它基于玩家与村民的个人声誉，以及玩家当前激活的状态效果（参见
 * {@link PotionEffectType#HERO_OF_THE_VILLAGE}）。玩家声誉对特殊价格的影响
 * 由配方的 {@link #getPriceMultiplier 价格倍率} 缩放。
 * </ul>
 * 第一种材料的调整数量通过以下方式计算：将第一种材料的原始数量、
 * 经配方 {@link #getPriceMultiplier 价格倍率} 缩放并截断为大于或等于 0 的最小整数的需求值、
 * 以及特殊价格相加，然后将结果值限制在 <code>1</code> 到物品堆的
 * {@link ItemStack#getMaxStackSize() 最大堆叠大小} 之间。
 * <p>原文：
 * Represents a merchant's trade.
 * <p>
 * Trades can take one or two ingredients, and provide one result. The
 * ingredients' ItemStack amounts are respected in the trade.
 * <p>
 * A trade has a maximum number of uses. A {@link Villager} may periodically
 * replenish its trades by resetting the {@link #getUses uses} of its merchant
 * recipes to <code>0</code>, allowing them to be used again.
 * <p>
 * A trade may or may not reward experience for being completed.
 * <p>
 * During trades, the {@link MerchantRecipe} dynamically adjusts the amount of
 * its first ingredient based on the following criteria:
 * <ul>
 * <li>{@link #getDemand() Demand}: This value is periodically updated by the
 * villager that owns this merchant recipe based on how often the recipe has
 * been used since it has been last restocked in relation to its
 * {@link #getMaxUses maximum uses}. The amount by which the demand influences
 * the amount of the first ingredient is scaled by the recipe's
 * {@link #getPriceMultiplier price multiplier}, and can never be below zero.
 * <li>{@link #getSpecialPrice() Special price}: This value is dynamically
 * updated whenever a player starts and stops trading with a villager that owns
 * this merchant recipe. It is based on the player's individual reputation with
 * the villager, and the player's currently active status effects (see
 * {@link PotionEffectType#HERO_OF_THE_VILLAGE}). The influence of the player's
 * reputation on the special price is scaled by the recipe's
 * {@link #getPriceMultiplier price multiplier}.
 * </ul>
 * The adjusted amount of the first ingredient is calculated by adding up the
 * original amount of the first ingredient, the demand scaled by the recipe's
 * {@link #getPriceMultiplier price multiplier} and truncated to the next lowest
 * integer value greater than or equal to 0, and the special price, and then
 * constraining the resulting value between <code>1</code> and the item stack's
 * {@link ItemStack#getMaxStackSize() maximum stack size}.
 */
public class MerchantRecipe implements Recipe {

    private ItemStack result;
    private List<ItemStack> ingredients = new ArrayList<ItemStack>();
    private int uses;
    private int maxUses;
    private boolean experienceReward;
    private int specialPrice;
    private int demand;
    private int villagerExperience;
    private float priceMultiplier;
    private boolean ignoreDiscounts; // Paper

    public MerchantRecipe(@NotNull ItemStack result, int maxUses) {
        this(result, 0, maxUses, false);
    }

    public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward) {
        this(result, uses, maxUses, experienceReward, 0, 0.0F, 0, 0);
    }

    public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward, int villagerExperience, float priceMultiplier) {
        this(result, uses, maxUses, experienceReward, villagerExperience, priceMultiplier, 0, 0);
    }

    public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward, int villagerExperience, float priceMultiplier, int demand, int specialPrice) {
        // Paper start - add ignoreDiscounts param
        this(result, uses, maxUses, experienceReward, villagerExperience, priceMultiplier, demand, specialPrice, false);
    }
    public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward, int villagerExperience, float priceMultiplier, boolean ignoreDiscounts) {
        this(result, uses, maxUses, experienceReward, villagerExperience, priceMultiplier, 0, 0, ignoreDiscounts);
    }
    public MerchantRecipe(@NotNull ItemStack result, int uses, int maxUses, boolean experienceReward, int villagerExperience, float priceMultiplier, int demand, int specialPrice, boolean ignoreDiscounts) {
        Preconditions.checkArgument(!result.isEmpty(), "Recipe cannot have an empty result."); // Paper
        this.ignoreDiscounts = ignoreDiscounts;
        // Paper end
        this.result = result;
        this.uses = uses;
        this.maxUses = maxUses;
        this.experienceReward = experienceReward;
        this.villagerExperience = villagerExperience;
        this.priceMultiplier = priceMultiplier;
        this.demand = demand;
        this.specialPrice = specialPrice;
    }

    // Paper start - add copy ctor
    public MerchantRecipe(@NotNull MerchantRecipe recipe) {
        this(recipe.result.clone(), recipe.uses, recipe.maxUses, recipe.experienceReward, recipe.villagerExperience, recipe.priceMultiplier, recipe.demand, recipe.specialPrice, recipe.ignoreDiscounts);
        this.setIngredients(recipe.ingredients);
    }
    // Paper end

    @NotNull
    @Override
    public ItemStack getResult() {
        return result.clone(); // Paper
    }

    public void addIngredient(@NotNull ItemStack item) {
        Preconditions.checkState(ingredients.size() < 2, "MerchantRecipe can only have maximum 2 ingredients");
        Preconditions.checkArgument(!item.isEmpty(), "Recipe cannot have an empty itemstack ingredient."); // Paper
        ingredients.add(item.clone());
    }

    public void removeIngredient(int index) {
        ingredients.remove(index);
    }

    public void setIngredients(@NotNull List<ItemStack> ingredients) {
        Preconditions.checkState(ingredients.size() <= 2, "MerchantRecipe can only have maximum 2 ingredients");
        this.ingredients = new ArrayList<ItemStack>();
        for (ItemStack item : ingredients) {
            Preconditions.checkArgument(!item.isEmpty(), "Recipe cannot have an empty itemstack ingredient."); // Paper
            this.ingredients.add(item.clone());
        }
    }

    @NotNull
    public List<ItemStack> getIngredients() {
        List<ItemStack> copy = new ArrayList<ItemStack>();
        for (ItemStack item : ingredients) {
            copy.add(item.clone());
        }
        return copy;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 {@link #adjust(ItemStack) 调整后的} 第一种材料。
     * <p>原文：
     * Gets the {@link #adjust(ItemStack) adjusted} first ingredient.
     *
     * @return 调整后的第一种材料，如果此配方没有材料则返回 <code>null</code>
     * <p>原文：the adjusted first ingredient, or <code>null</code> if this
     * recipe has no ingredients
     * @see #adjust(ItemStack)
     */
    @Nullable
    public ItemStack getAdjustedIngredient1() {
        if (this.ingredients.isEmpty()) {
            return null;
        }

        ItemStack firstIngredient = this.ingredients.get(0).clone();
        adjust(firstIngredient);
        return firstIngredient;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以与 MerchantRecipe 在交易期间动态调整第一种材料数量相同的方式修改给定 {@link ItemStack} 的数量。
     * <br>
     * 计算方式为：将物品的原始数量、经配方
     * {@link #getPriceMultiplier 价格倍率} 缩放并截断为大于或等于 0 的最小整数的需求值、
     * 以及特殊价格相加，然后将结果值限制在 <code>1</code> 到
     * {@link ItemStack} 的 {@link ItemStack#getMaxStackSize()
     * 最大堆叠大小} 之间。
     * <p>原文：
     * Modifies the amount of the given {@link ItemStack} in the same way as
     * MerchantRecipe dynamically adjusts the amount of the first ingredient
     * during trading.
     * <br>
     * This is calculated by adding up the original amount of the item, the
     * demand scaled by the recipe's
     * {@link #getPriceMultiplier price multiplier} and truncated to the next
     * lowest integer value greater than or equal to 0, and the special price,
     * and then constraining the resulting value between <code>1</code> and the
     * {@link ItemStack}'s {@link ItemStack#getMaxStackSize()
     * maximum stack size}.
     *
     * @param itemStack 要调整的物品
     * <p>原文：the item to adjust
     */
    public void adjust(@Nullable ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR || itemStack.getAmount() <= 0) {
            return;
        }

        int amount = itemStack.getAmount();
        int demandAdjustment = Math.max(0, NumberConversions.floor((float) (amount * getDemand()) * getPriceMultiplier()));
        itemStack.setAmount(Math.max(1, Math.min(itemStack.getMaxStackSize(), amount + demandAdjustment + getSpecialPrice())));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此交易的需求量。
     * <p>原文：
     * Get the demand for this trade.
     *
     * @return 需求量
     * <p>原文：the demand
     */
    public int getDemand() {
        return demand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此交易的需求量。
     * <p>原文：
     * Set the demand for this trade.
     *
     * @param demand 新的需求量
     * <p>原文：the new demand
     */
    public void setDemand(int demand) {
        this.demand = demand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此交易的特殊价格。
     * <p>原文：
     * Get the special price for this trade.
     *
     * @return 特殊价格值
     * <p>原文：special price value
     */
    public int getSpecialPrice() {
        return specialPrice;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此交易的特殊价格。
     * <p>原文：
     * Set the special price for this trade.
     *
     * @param specialPrice 特殊价格值
     * <p>原文：special price value
     */
    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此交易已被使用的次数。
     * <p>原文：
     * Get the number of times this trade has been used.
     *
     * @return 使用次数
     * <p>原文：the number of uses
     */
    public int getUses() {
        return uses;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此交易已被使用的次数。
     * <p>原文：
     * Set the number of times this trade has been used.
     *
     * @param uses 使用次数
     * <p>原文：the number of uses
     */
    public void setUses(int uses) {
        this.uses = uses;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此交易的最大使用次数。
     * <p>原文：
     * Get the maximum number of uses this trade has.
     *
     * @return 最大使用次数
     * <p>原文：the maximum number of uses
     */
    public int getMaxUses() {
        return maxUses;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此交易的最大使用次数。
     * <p>原文：
     * Set the maximum number of uses this trade has.
     *
     * @param maxUses 此交易可被使用的最大次数
     * <p>原文：the maximum number of time this trade can be used
     */
    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 是否为交易奖励玩家经验值。
     * <p>原文：
     * Whether to reward experience to the player for the trade.
     *
     * @return 完成此交易时是否奖励玩家经验值
     * <p>原文：whether to reward experience to the player for completing this
     * trade
     */
    public boolean hasExperienceReward() {
        return experienceReward;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置是否为交易奖励玩家经验值。
     * <p>原文：
     * Set whether to reward experience to the player for the trade.
     *
     * @param flag 完成此交易时是否奖励玩家经验值
     * <p>原文：whether to reward experience to the player for completing
     * this trade
     */
    public void setExperienceReward(boolean flag) {
        this.experienceReward = flag;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取村民从此交易中获得的经验值。
     * <p>原文：
     * Gets the amount of experience the villager earns from this trade.
     *
     * @return 村民经验值
     * <p>原文：villager experience
     */
    public int getVillagerExperience() {
        return villagerExperience;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置村民从此交易中获得的经验值。
     * <p>原文：
     * Sets the amount of experience the villager earns from this trade.
     *
     * @param villagerExperience 新经验值数量
     * <p>原文：new experience amount
     */
    public void setVillagerExperience(int villagerExperience) {
        this.villagerExperience = villagerExperience;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此交易成本的价格倍率。
     * <p>原文：
     * Gets the price multiplier for the cost of this trade.
     *
     * @return 价格倍率
     * <p>原文：price multiplier
     */
    public float getPriceMultiplier() {
        return priceMultiplier;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此交易成本的价格倍率。
     * <p>原文：
     * Sets the price multiplier for the cost of this trade.
     *
     * @param priceMultiplier 新价格倍率
     * <p>原文：new price multiplier
     */
    public void setPriceMultiplier(float priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    // Paper start
    /**
     * @return Whether all discounts on this trade should be ignored.
     */
    public boolean shouldIgnoreDiscounts() {
        return ignoreDiscounts;
    }

    /**
     * @param ignoreDiscounts Whether all discounts on this trade should be ignored.
     */
    public void setIgnoreDiscounts(boolean ignoreDiscounts) {
        this.ignoreDiscounts = ignoreDiscounts;
    }
    // Paper end
}
