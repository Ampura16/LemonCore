package io.papermc.paper.registry.data;

import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.event.RegistryComposeEvent;
import io.papermc.paper.registry.set.RegistryKeySet;
import io.papermc.paper.registry.set.RegistrySet;
import io.papermc.paper.registry.tag.TagKey;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link Enchantment} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link Enchantment} type.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface EnchantmentRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此魔咒条目显示给客户端的描述，例如锋利魔咒显示为“锋利”。
     * <p>原文：
     * Provides the description of this enchantment entry as displayed to the client, e.g. "Sharpness" for the sharpness
     * enchantment.
     *
     * @return 描述组件。
     * <p>原文：the description component.
     */
    Component description();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此魔咒所支持物品的注册表键集合。
     * <p>原文：
     * Provides the registry key set referencing the items this enchantment is supported on.
     *
     * @return 注册表键集合。
     * <p>原文：the registry key set.
     */
    RegistryKeySet<ItemType> supportedItems();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供在附魔台附魔时此魔咒可应用的物品类型的注册表键集合。
     * <p>
     * 如果该值为 {@code null}，则在附魔台上下文中改为使用 {@link #supportedItems()}。此外，标签 {@link io.papermc.paper.registry.keys.tags.EnchantmentTagKeys#IN_ENCHANTING_TABLE} 决定了哪些魔咒能够出现在附魔台中。
     * <p>原文：
     * Provides the registry key set referencing the item types this enchantment can be applied to when
     * enchanting in an enchantment table.
     * <p>
     * If this value is {@code null}, {@link #supportedItems()} will be sourced instead in the context of an enchantment table.
     * Additionally, the tag {@link io.papermc.paper.registry.keys.tags.EnchantmentTagKeys#IN_ENCHANTING_TABLE} defines
     * which enchantments can even show up in an enchantment table.
     *
     * @return 注册表键集合。
     * <p>原文：the registry key set.
     */
    @Nullable RegistryKeySet<ItemType> primaryItems();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供选择魔咒时加权随机所使用的此魔咒权重。
     * <p>原文：
     * Provides the weight of this enchantment used by the weighted random when selecting enchantments.
     *
     * @return 权重值。
     * <p>原文：the weight value.
     * @see <a href="https://minecraft.wiki/w/Enchanting">https://minecraft.wiki/w/Enchanting</a> for examplary weights.
     */
    @Range(from = 1, to = 1024) int weight();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此魔咒应用时可具有的最大等级。
     * <p>原文：
     * Provides the maximum level this enchantment can have when applied.
     *
     * @return 最高等级。
     * <p>原文：the maximum level.
     */
    @Range(from = 1, to = 255) int maxLevel();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供用此魔咒附魔物品所需的最小花费。
     * <p>
     * 注意，花费与消耗的经验并不直接相关。
     * <p>原文：
     * Provides the minimum cost needed to enchant an item with this enchantment.
     * <p>
     * Note that a cost is not directly related to the consumed xp.
     *
     * @return 魔咒花费。
     * <p>原文：the enchantment cost.
     * @see <a href="https://minecraft.wiki/w/Enchanting/Levels">https://minecraft.wiki/w/Enchanting/Levels</a> for
     * examplary costs.
     */
    EnchantmentCost minimumCost();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供用此魔咒附魔物品允许的最大花费。
     * <p>
     * 注意，花费与消耗的经验并不直接相关。
     * <p>原文：
     * Provides the maximum cost allowed to enchant an item with this enchantment.
     * <p>
     * Note that a cost is not directly related to the consumed xp.
     *
     * @return 魔咒花费。
     * <p>原文：the enchantment cost.
     * @see <a href="https://minecraft.wiki/w/Enchanting/Levels">https://minecraft.wiki/w/Enchanting/Levels</a> for
     * examplary costs.
     */
    EnchantmentCost maximumCost();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供使用铁砧应用此魔咒的花费。
     * <p>
     * 注意，使用附魔书时该值减半，并且会乘以魔咒的等级。更多信息请参阅 <a href="https://minecraft.wiki/w/Anvil_mechanics">https://minecraft.wiki/w/Anvil_mechanics</a>。
     * </p>
     * <p>原文：
     * Provides the cost of applying this enchantment using an anvil.
     * <p>
     * Note that this is halved when using an enchantment book, and is multiplied by the level of the enchantment.
     * See <a href="https://minecraft.wiki/w/Anvil_mechanics">https://minecraft.wiki/w/Anvil_mechanics</a> for more
     * information.
     * </p>
     *
     * @return 此魔咒的铁砧花费
     * <p>原文：the anvil cost of this enchantment
     */
    @Range(from = 0, to = Integer.MAX_VALUE) int anvilCost();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此魔咒可在哪些槽位组中生效的列表。
     * <p>
     * 如果附有此魔咒的物品装备在返回列表及其组未覆盖的槽位中，该魔咒的效果（如属性修饰符）将不会激活。
     * <p>原文：
     * Provides a list of slot groups this enchantment may be active in.
     * <p>
     * If the item enchanted with this enchantment is equipped in a slot not covered by the returned list and its
     * groups, the enchantment's effects, like attribute modifiers, will not activate.
     *
     * @return 装备槽位组列表。
     * <p>原文：a list of equipment slot groups.
     * @see Enchantment#getActiveSlotGroups()
     */
    @Unmodifiable List<EquipmentSlotGroup> activeSlots();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供与此魔咒互斥的魔咒注册表键集合。
     * <p>
     * 如果物品上已存在互斥魔咒，则禁止将此魔咒应用于该物品。
     * <p>原文：
     * Provides the registry key set of enchantments that this enchantment is exclusive with.
     * <p>
     * Exclusive enchantments prohibit the application of this enchantment to an item if they are already present on
     * said item.
     *
     * @return 与此魔咒互斥的魔咒注册表集合。
     * <p>原文：a registry set of enchantments exclusive to this one.
     */
    RegistryKeySet<Enchantment> exclusiveWith();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link EnchantmentRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #description(Component)}</li>
     * <li>{@link #supportedItems(RegistryKeySet)}</li>
     * <li>{@link #weight(int)}</li>
     * <li>{@link #maxLevel(int)}</li>
     * <li>{@link #minimumCost(EnchantmentCost)}</li>
     * <li>{@link #maximumCost(EnchantmentCost)}</li>
     * <li>{@link #anvilCost(int)}</li>
     * <li>{@link #activeSlots(Iterable)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link EnchantmentRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>{@link #description(Component)}</li>
     *     <li>{@link #supportedItems(RegistryKeySet)}</li>
     *     <li>{@link #weight(int)}</li>
     *     <li>{@link #maxLevel(int)}</li>
     *     <li>{@link #minimumCost(EnchantmentCost)}</li>
     *     <li>{@link #maximumCost(EnchantmentCost)}</li>
     *     <li>{@link #anvilCost(int)}</li>
     *     <li>{@link #activeSlots(Iterable)}</li>
     * </ul>
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends EnchantmentRegistryEntry, RegistryBuilder<Enchantment> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置此魔咒条目显示给客户端的描述，例如锋利魔咒显示为“锋利”。
         * <p>原文：
         * Configures the description of this enchantment entry as displayed to the client, e.g. "Sharpness" for the
         * sharpness enchantment.
         *
         * @param description 描述组件。
         * <p>原文：the description component.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder description(Component description);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置此魔咒可以应用的受支持物品集合。可以是通过 {@link RegistrySet#keySet(io.papermc.paper.registry.RegistryKey, Iterable)} 创建的 {@link RegistryKeySet}，也可以是通过 {@link RegistryComposeEvent#getOrCreateTag(TagKey)} 获取的标签，标签键见 {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys}，例如 {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys#ENCHANTABLE_ARMOR} 和 {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys#ENCHANTABLE_MELEE_WEAPON}。
         * <p>原文：
         * Configures the set of supported items this enchantment can be applied on. This
         * can be a {@link RegistryKeySet} created via {@link RegistrySet#keySet(io.papermc.paper.registry.RegistryKey, Iterable)} or
         * a tag obtained via {@link RegistryComposeEvent#getOrCreateTag(TagKey)} with
         * tag keys found in {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys} such as
         * {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys#ENCHANTABLE_ARMOR} and
         * {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys#ENCHANTABLE_MELEE_WEAPON}.
         *
         * @param supportedItems 表示支持物品的注册表键集合。
         * <p>原文：the registry key set representing the supported items.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see RegistrySet#keySet(RegistryKey, TypedKey[])
         * @see RegistryComposeEvent#getOrCreateTag(TagKey)
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder supportedItems(RegistryKeySet<ItemType> supportedItems);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置在附魔台附魔时此魔咒可以自然应用的物品类型集合。可以是通过 {@link RegistrySet#keySet(io.papermc.paper.registry.RegistryKey, Iterable)} 创建的 {@link RegistryKeySet}，也可以是通过 {@link RegistryComposeEvent#getOrCreateTag(TagKey)} 获取的标签，标签键见 {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys}，例如 {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys#ENCHANTABLE_ARMOR} 和 {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys#ENCHANTABLE_MELEE_WEAPON}。
         * <p>
         * 默认为 {@code null}，表示所有 {@link #supportedItems()} 都被视为主要物品。此外，标签 {@link io.papermc.paper.registry.keys.tags.EnchantmentTagKeys#IN_ENCHANTING_TABLE} 决定了哪些魔咒能够出现在附魔台中。
         * <p>原文：
         * Configures a set of item types this enchantment can naturally be applied to, when enchanting in an
         * enchantment table.This can be a {@link RegistryKeySet} created via
         * {@link RegistrySet#keySet(io.papermc.paper.registry.RegistryKey, Iterable)} or a tag obtained via
         * {@link RegistryComposeEvent#getOrCreateTag(TagKey)} with
         * tag keys found in {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys} such as
         * {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys#ENCHANTABLE_ARMOR} and
         * {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys#ENCHANTABLE_MELEE_WEAPON}.
         * <p>
         * Defaults to {@code null} which means all {@link #supportedItems()} are considered primary items.
         * Additionally, the tag {@link io.papermc.paper.registry.keys.tags.EnchantmentTagKeys#IN_ENCHANTING_TABLE} defines
         * which enchantments can even show up in an enchantment table.
         *
         * @param primaryItems 表示主要物品的注册表键集合。
         * <p>原文：the registry key set representing the primary items.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see RegistrySet#keySet(RegistryKey, TypedKey[])
         * @see RegistryComposeEvent#getOrCreateTag(TagKey)
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder primaryItems(@Nullable RegistryKeySet<ItemType> primaryItems);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置选择魔咒时加权随机所使用的此魔咒权重。
         * <p>原文：
         * Configures the weight of this enchantment used by the weighted random when selecting enchantments.
         *
         * @param weight 权重值。
         * <p>原文：the weight value.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see <a href="https://minecraft.wiki/w/Enchanting">https://minecraft.wiki/w/Enchanting</a> for examplary weights.
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder weight(@Range(from = 1, to = 1024) int weight);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置此魔咒应用时可具有的最大等级。
         * <p>原文：
         * Configures the maximum level this enchantment can have when applied.
         *
         * @param maxLevel 最高等级。
         * <p>原文：the maximum level.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder maxLevel(@Range(from = 1, to = 255) int maxLevel);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置用此魔咒附魔物品所需的最小花费。
         * <p>
         * 注意，花费与消耗的经验并不直接相关。
         * <p>原文：
         * Configures the minimum cost needed to enchant an item with this enchantment.
         * <p>
         * Note that a cost is not directly related to the consumed xp.
         *
         * @param minimumCost 魔咒花费。
         * <p>原文：the enchantment cost.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see <a href="https://minecraft.wiki/w/Enchanting/Levels">https://minecraft.wiki/w/Enchanting/Levels</a> for
         * examplary costs.
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder minimumCost(EnchantmentCost minimumCost);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置用此魔咒附魔物品的最大花费。
         * <p>
         * 注意，花费与消耗的经验并不直接相关。
         * <p>原文：
         * Configures the maximum cost to enchant an item with this enchantment.
         * <p>
         * Note that a cost is not directly related to the consumed xp.
         *
         * @param maximumCost 魔咒花费。
         * <p>原文：the enchantment cost.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see <a href="https://minecraft.wiki/w/Enchanting/Levels">https://minecraft.wiki/w/Enchanting/Levels</a> for
         * examplary costs.
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder maximumCost(EnchantmentCost maximumCost);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置使用铁砧应用此魔咒的花费。
         * <p>
         * 注意，使用附魔书时该值减半，并且会乘以魔咒的等级。更多信息请参阅 <a href="https://minecraft.wiki/w/Anvil_mechanics">https://minecraft.wiki/w/Anvil_mechanics</a>。
         * </p>
         * <p>原文：
         * Configures the cost of applying this enchantment using an anvil.
         * <p>
         * Note that this is halved when using an enchantment book, and is multiplied by the level of the enchantment.
         * See <a href="https://minecraft.wiki/w/Anvil_mechanics">https://minecraft.wiki/w/Anvil_mechanics</a> for more information.
         * </p>
         *
         * @param anvilCost 此魔咒的铁砧花费
         * <p>原文：the anvil cost of this enchantment
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see Enchantment#getAnvilCost()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder anvilCost(@Range(from = 0, to = Integer.MAX_VALUE) int anvilCost);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置此魔咒可在哪些槽位组中生效。
         * <p>
         * 如果附有此魔咒的物品装备在返回列表及其组未覆盖的槽位中，该魔咒的效果（如属性修饰符）将不会激活。
         * <p>原文：
         * Configures the list of slot groups this enchantment may be active in.
         * <p>
         * If the item enchanted with this enchantment is equipped in a slot not covered by the returned list and its
         * groups, the enchantment's effects, like attribute modifiers, will not activate.
         *
         * @param activeSlots 装备槽位组列表。
         * <p>原文：a list of equipment slot groups.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see Enchantment#getActiveSlotGroups()
         */
        @Contract(value = "_ -> this", mutates = "this")
        default Builder activeSlots(final EquipmentSlotGroup... activeSlots) {
            return this.activeSlots(List.of(activeSlots));
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置此魔咒可在哪些槽位组中生效。
         * <p>
         * 如果附有此魔咒的物品装备在返回列表及其组未覆盖的槽位中，该魔咒的效果（如属性修饰符）将不会激活。
         * <p>原文：
         * Configures the list of slot groups this enchantment may be active in.
         * <p>
         * If the item enchanted with this enchantment is equipped in a slot not covered by the returned list and its
         * groups, the enchantment's effects, like attribute modifiers, will not activate.
         *
         * @param activeSlots 装备槽位组列表。
         * <p>原文：a list of equipment slot groups.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see Enchantment#getActiveSlotGroups()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder activeSlots(Iterable<EquipmentSlotGroup> activeSlots);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置与此魔咒互斥的魔咒注册表键集合。
         * <p>
         * 如果物品上已存在互斥魔咒，则禁止将此魔咒应用于该物品。
         * <p>
         * 默认为空集合，即无论存在何种其他魔咒都可以应用此魔咒。
         * <p>原文：
         * Configures the registry key set of enchantments that this enchantment is exclusive with.
         * <p>
         * Exclusive enchantments prohibit the application of this enchantment to an item if they are already present on
         * said item.
         * <p>
         * Defaults to an empty set allowing this enchantment to be applied regardless of other enchantments.
         *
         * @param exclusiveWith 与此魔咒互斥的魔咒注册表集合。
         * <p>原文：a registry set of enchantments exclusive to this one.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see RegistrySet#keySet(RegistryKey, TypedKey[])
         * @see RegistryComposeEvent#getOrCreateTag(TagKey)
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder exclusiveWith(RegistryKeySet<Enchantment> exclusiveWith);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 魔咒花费接口表示应用魔咒的花费，并拆分为各个组成部分。
     * <p>原文：
     * The enchantment cost interface represents the cost of applying an enchantment, split up into its different components.
     */
    interface EnchantmentCost {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 返回此魔咒花费的基础花费，与魔咒等级无关。
         * <p>原文：
         * Returns the base cost of this enchantment cost, no matter what level the enchantment has.
         *
         * @return 以等级计的花费。
         * <p>原文：the cost in levels.
         */
        int baseCost();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 返回要应用的魔咒每级增加的额外花费。该花费对第一级以上的每一级生效。
         * <p>原文：
         * Returns the additional cost added per level of the enchantment to be applied.
         * This cost is applied per level above the first.
         *
         * @return 第一级以上每级在 {@link #baseCost()} 基础上增加的花费。
         * <p>原文：the cost added to the {@link #baseCost()} for each level above the first.
         */
        int additionalPerLevelCost();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 根据传入的值创建新的魔咒花费实例。
         * <p>原文：
         * Creates a new enchantment cost instance based on the passed values.
         *
         * @param baseCost 魔咒花费的基础值，与 {@link #baseCost()} 返回的值相同
         * <p>原文：the base cost of the enchantment cost as returned by {@link #baseCost()}
         * @param additionalPerLevelCost 每级附加花费，与 {@link #additionalPerLevelCost()} 返回的值相同
         * <p>原文：the additional cost per level, as returned by {@link #additionalPerLevelCost()}
         * @return 创建的实例。
         * <p>原文：the created instance.
         */
        @Contract(value = "_,_ -> new", pure = true)
        static EnchantmentCost of(final int baseCost, final int additionalPerLevelCost) {
            record Impl(int baseCost, int additionalPerLevelCost) implements EnchantmentCost {
            }

            return new Impl(baseCost, additionalPerLevelCost);
        }
    }
}
