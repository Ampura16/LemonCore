package io.papermc.paper.enchantments;

/**
 * @deprecated 自 1.20.5 起魔咒不再具有“稀有度”
 * <p>原文：Enchantments do not have a "rarity" since 1.20.5
 */
@Deprecated(forRemoval = true, since = "1.20.5")
public enum EnchantmentRarity {

    COMMON(10),
    UNCOMMON(5),
    RARE(2),
    VERY_RARE(1);

    private final int weight;

    EnchantmentRarity(int weight) {
        this.weight = weight;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取该稀有度的权重。
     * <p>原文：
     * Gets the weight for the rarity.
     *
     * @return 权重
     * <p>原文：the weight
     */
    public int getWeight() {
        return weight;
    }
}
