package io.papermc.paper.entity;

import java.util.Collection;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link org.bukkit.entity.Player#give(ItemStack...)} 及其重载方法所使用的结果类型。
 * <p>原文：
 * A result type used by {@link org.bukkit.entity.Player#give(ItemStack...)} and its overloads.
 */
@NullMarked
public interface PlayerGiveResult {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 因放不下而未能加入玩家物品栏的物品堆集合。该集合由待添加物品集合派生：为每个未完全加入物品栏的物品堆创建副本，并将未加入的数量设为其数量。
     * <p>
     * 此处的物品堆*可能*同时出现在 {@link #drops()} 集合中作为物品实体，因为给予逻辑可能已将其掉落。
     * <p>原文：
     * A collection of itemstacks that were not added to the player's inventory as they did not fit.
     * The collection is derived from the collections of items to add by creating copies of each stack that was not
     * fully added to the inventory and assigning the non-added count as their amount.
     * <p>
     * Itemstacks found here *may* also be found as item entities in the {@link #drops()} collection, as the
     * give logic may have dropped them.
     *
     * @return 因无法添加而剩余的物品堆的不可修改集合。每个元素都是其来源输入物品堆的副本。
     * <p>原文：the unmodifiable collection of itemstacks that are leftover as they could not be added. Each element is a
     * copy of the input stack they are derived from.
     */
    @Unmodifiable
    Collection<ItemStack> leftovers();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 本次调用 {@link org.bukkit.entity.Player#give(ItemStack...)} 所掉落的物品实体集合。由于插件可能取消物品实体的生成，此处包含的物品实体不保证与 {@link #leftovers()} 一致。
     * <p>原文：
     * A collection of item entities dropped as a result of this call to {@link org.bukkit.entity.Player#give(ItemStack...)}.
     * The item entities contained here are not guaranteed to match the {@link #leftovers()} as plugins may cancel the
     * spawning of item entities.
     *
     * @return 掉落物品实体的不可修改集合。
     * <p>原文：the unmodifiable collection of dropped item entities.
     */
    @Unmodifiable
    Collection<Item> drops();

}
