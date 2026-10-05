package io.papermc.paper.datacomponent.item;

import io.papermc.paper.registry.tag.TagKey;
import org.bukkit.damage.DamageType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有包含此物品的物品实体对哪些伤害类型免疫。
 * <p>原文：
 * Holds the contents of damage types that the item entity containing this item is invincible to.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#DAMAGE_RESISTANT
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface DamageResistant {

    @Contract(value = "_ -> new", pure = true)
    static DamageResistant damageResistant(final TagKey<DamageType> types) {
        return ItemComponentTypesBridge.bridge().damageResistant(types);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此伤害类型所免疫的类型。
     * <p>原文：
     * The types that this damage type is invincible to.
     *
     * @return 包含相应伤害类型的标签的键。
     * <p>原文：the key of the tag holding the respective damage types.
     */
    @Contract(value = "-> new", pure = true)
    TagKey<DamageType> types();
}
