package io.papermc.paper.datacomponent.item;

import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有物品是否可附魔，使该类型的魔咒可以在附魔台中出现。
 * <p>原文：
 * Holds if an item is enchantable, allowing for enchantments of the type to be seen in an enchanting table.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#ENCHANTABLE
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface Enchantable {

    @Contract(value = "_ -> new", pure = true)
    static Enchantable enchantable(final @Positive int level) {
        return ItemComponentTypesBridge.bridge().enchantable(level);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取当前允许的附魔值等级，值越高，越能选中花费更高的魔咒。
     * <p>原文：
     * Gets the current enchantment value level allowed,
     * a higher value allows enchantments with a higher cost to be picked.
     *
     * @return 值
     * <p>原文：the value
     * @see <a href="https://minecraft.wiki/w/Enchanting_mechanics#Java_Edition_2">Minecraft Wiki</a>
     */
    @Contract(pure = true)
    @Positive int value();
}
