package org.bukkit.inventory.meta;

import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemAdventurePredicate;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.bukkit.inventory.meta.components.JukeboxPlayableComponent;
import org.bukkit.inventory.meta.components.ToolComponent;
import org.bukkit.inventory.meta.components.UseCooldownComponent;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.tag.DamageTypeTags;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 本类型代表物品附属数据的存储机制.
 * <p>
 * 接口的实现会处理 ItemMeta 的创建和应用.
 * 本类不应由实时环境中的插件实现.
 * <p>原文：
 * This type represents the storage mechanism for auxiliary item data.
 * <p>
 * An implementation will handle the creation and application for ItemMeta.
 * This class should not be implemented by a plugin in a live environment.
 */
public interface ItemMeta extends Cloneable, ConfigurationSerializable, PersistentDataHolder {

    // Paper start
    /**
     * Checks for existence of a custom name.
     *
     * @return true if this has a custom name
     */
    boolean hasCustomName();

    /**
     * Gets the custom name.
     *
     * <p>Plugins should check that {@link #hasCustomName()} returns {@code true} before calling this method.</p>
     *
     * @return the custom name
     */
    net.kyori.adventure.text.@Nullable Component customName();

    /**
     * Sets the custom name.
     *
     * @param customName the custom name to set
     */
    void customName(final net.kyori.adventure.text.@Nullable Component customName);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查物品是否有展示名.
     * <p>原文：
     * Checks for existence of a display name.
     *
     * @apiNote This method is obsolete, use {@link #hasCustomName()} instead.
     * @return 物品是否有展示名
     * <p>原文：true if this has a display name
     */
    @ApiStatus.Obsolete(since = "1.21.4")
    default boolean hasDisplayName() {
        return this.hasCustomName();
    }

    /**
     * Gets the display name.
     *
     * <p>Plugins should check that {@link #hasDisplayName()} returns <code>true</code> before calling this method.</p>
     *
     * @apiNote This method is obsolete, use {@link #customName()} instead.
     * @return the display name
     */
    @ApiStatus.Obsolete(since = "1.21.4")
    default net.kyori.adventure.text.@Nullable Component displayName() {
        return this.customName();
    }

    /**
     * Sets the display name.
     *
     * @param displayName the display name to set
     * @apiNote This method is obsolete, use {@link #customName(Component)} instead.
     */
    @ApiStatus.Obsolete(since = "1.21.4")
    default void displayName(final net.kyori.adventure.text.@Nullable Component displayName) {
        this.customName(displayName);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品的展示名.
     * <p>
     * 插件应该在调用这个方法之前检查 hasDisplayName() 是否返回 <code>true</code>.
     * <p>原文：
     * Gets the display name that is set.
     * <p>
     * Plugins should check that hasDisplayName() returns <code>true</code>
     * before calling this method.
     *
     * @return 物品的展示名
     * <p>原文：the display name that is set
     * @deprecated in favour of {@link #displayName()}
     */
    @Deprecated // Paper
    @NotNull
    String getDisplayName();

    // Paper start
    /**
     * Gets the display name that is set.
     * <p>
     * Plugins should check that hasDisplayName() returns <code>true</code>
     * before calling this method.
     *
     * @return the display name that is set
     * @deprecated use {@link #displayName()}
     */
    @NotNull
    @Deprecated
    net.md_5.bungee.api.chat.BaseComponent[] getDisplayNameComponent();
    // Paper end
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的展示名.
     * <p>原文：
     * Sets the display name.
     *
     * @param name 要设置的物品名
     * <p>原文：the name to set
     * @deprecated in favour of {@link #displayName(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setDisplayName(@Nullable String name);

    // Paper start
    /**
     * Sets the display name.
     *
     * @param component the name component to set
     * @deprecated use {@link #displayName(Component)}
     */
    @Deprecated
    void setDisplayNameComponent(@Nullable net.md_5.bungee.api.chat.BaseComponent[] component);
    // Paper end
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否存在物品名称。
     * <br>
     * 物品名称与显示名称的区别在于：它无法通过铁砧编辑、不会以斜体样式显示，且不展示标签文本。
     * <p>原文：
     * Checks for existence of an item name.
     * <br>
     * Item name differs from display name in that it is cannot be edited by an
     * anvil, is not styled with italics, and does not show labels.
     *
     * @return 若存在物品名称则返回 true
     * <p>原文：true if this has an item name
     */
    boolean hasItemName();

    // Paper start
    /**
     * Gets the item name component that is set.
     * <br>
     * Item name differs from display name in that it is cannot be edited by an
     * anvil, is not styled with italics, and does not show labels.
     * <p>
     * Plugins should check that {@link #hasItemName()} returns <code>true</code> before
     * calling this method.
     *
     * @return the item name that is set
     * @see #hasItemName()
     */
    @org.jetbrains.annotations.NotNull
    Component itemName();

    /**
     * Sets the item name.
     * <br>
     * Item name differs from display name in that it is cannot be edited by an
     * anvil, is not styled with italics, and does not show labels.
     *
     * @param name the name to set, null to remove it
     */
    void itemName(@Nullable final Component name);
    // Paper end
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取已设置的物品名称。
     * <br>
     * 物品名称与显示名称的区别在于：它无法通过铁砧编辑、不会以斜体样式显示，且不展示标签文本。
     * <p>
     * 插件在调用此方法前应确保 hasItemName() 返回 <code>true</code>。
     * <p>原文：
     * Gets the item name that is set.
     * <br>
     * Item name differs from display name in that it is cannot be edited by an
     * anvil, is not styled with italics, and does not show labels.
     * <p>
     * Plugins should check that hasItemName() returns <code>true</code> before
     * calling this method.
     *
     * @return 已设置的物品名称
     * <p>原文：the item name that is set
     * @deprecated in favour of {@link #itemName()}
     */
    @Deprecated // Paper
    @NotNull
    String getItemName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品名称。
     * <br>
     * 物品名称与显示名称的区别在于：它无法通过铁砧编辑、不会以斜体样式显示，且不展示标签文本。
     * <p>原文：
     * Sets the item name.
     * <br>
     * Item name differs from display name in that it is cannot be edited by an
     * anvil, is not styled with italics, and does not show labels.
     *
     * @param name 要设置的名称
     * <p>原文：the name to set
     * @deprecated in favour of {@link #itemName(Component)}
     */
    @Deprecated // Paper
    void setItemName(@Nullable String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查物品是否有本地化名称.
     * <p>原文：
     * Checks for existence of a localized name.
     *
     * @deprecated 属性不再存在
     * <p>原文：Use {@link ItemMeta#displayName()} and check if it is instanceof a {@link net.kyori.adventure.text.TranslatableComponent}.
     * @return 物品是否有本地化名称
     * <p>原文：true if this has a localized name
     */
    @Deprecated(since = "1.20.5", forRemoval = true)
    boolean hasLocalizedName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取为物品设置的本地化展示名.
     * <p>
     * 插件应该在调用这个方法之前检查 hasLocalizedName() 是否返回 <code>true</code>.
     * <p>原文：
     * Gets the localized display name that is set.
     * <p>
     * Plugins should check that hasLocalizedName() returns <code>true</code>
     * before calling this method.
     *
     * @deprecated 属性不再存在
     * <p>原文：Use {@link ItemMeta#displayName()} and cast it to a {@link net.kyori.adventure.text.TranslatableComponent}. No longer used by the client.
     * @return 物品的本地化名称
     * <p>原文：the localized name that is set
     */
    @NotNull
    @Deprecated(since = "1.20.5", forRemoval = true)
    String getLocalizedName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的本地化名称.
     * <p>原文：
     * Sets the localized name.
     *
     * @deprecated 属性不再存在
     * <p>原文：Use {@link ItemMeta#displayName(Component)} with a {@link net.kyori.adventure.text.TranslatableComponent}. No longer used by the client.
     * @param name 要设置的物品名
     * <p>原文：the name to set
     */
    @Deprecated(since = "1.20.5", forRemoval = true)
    void setLocalizedName(@Nullable String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查物品是否存在 lore.
     * <p>原文：
     * Checks for existence of lore.
     *
     * @return 物品是否存在 lore
     * <p>原文：true if this has lore
     */
    boolean hasLore();

    // Paper start
    /**
     * Gets the lore.
     *
     * <p>Plugins should check that {@link #hasLore()} returns <code>true</code> before calling this method.</p>
     *
     * @return the lore
     */
    @Nullable List<net.kyori.adventure.text.Component> lore();

    /**
     * Sets the lore.
     *
     * @param lore the lore to set
     */
    void lore(final @Nullable List<? extends net.kyori.adventure.text.Component> lore);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品的 lore.
     * <p>
     * 插件应该在调用这个方法之前检查 hasLore() 是否返回 <code>true</code>.
     * <p>原文：
     * Gets the lore that is set.
     * <p>
     * Plugins should check if hasLore() returns <code>true</code> before
     * calling this method.
     *
     * @return 物品的 lore 列表
     * <p>原文：a list of lore that is set
     * @deprecated in favour of {@link #lore()}
     */
    @Deprecated // Paper
    @Nullable
    List<String> getLore();

    /**
     * Gets the lore that is set.
     * <p>
     * Plugins should check if hasLore() returns <code>true</code> before
     * calling this method.
     *
     * @return a list of lore that is set
     * @deprecated use {@link #lore()}
     */
    @Nullable
    @Deprecated
    List<net.md_5.bungee.api.chat.BaseComponent[]> getLoreComponents();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为物品设置 lore.
     * 当传参为 null 时移除物品的 lore.
     * <p>原文：
     * Sets the lore for this item.
     * Removes lore when given null.
     *
     * @param lore 要设置的 lore
     * <p>原文：the lore that will be set
     * @deprecated in favour of {@link #lore(List)}
     */
    @Deprecated // Paper
    void setLore(@Nullable List<String> lore);

    /**
     * Sets the lore for this item.
     * Removes lore when given null.
     *
     * @param lore the lore that will be set
     * @deprecated use {@link #lore(List)}
     */
    @Deprecated
    void setLoreComponents(@Nullable List<net.md_5.bungee.api.chat.BaseComponent[]> lore);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查物品是否存在自定义模型数据.
     * <p>
     * CustomModelData 是一个整数, 可以将客户端与自定义的物品模型相关联.
     * <p>原文：
     * Checks for existence of custom model data.
     * <p>
     * CustomModelData is an integer that may be associated client side with a
     * custom item model.
     *
     * @return 物品是否存在自定义模型数据
     * <p>原文：true if this has custom model data
     * @deprecated 可以使用 {@link #hasCustomModelDataComponent()} 指定更复杂的自定义模型数据.
     * 旧版自定义模型数据中的整数等同于 {@link CustomModelDataComponent#getFloats()} 列表中的单个浮点数.
     * <p>原文：more complex custom model data can be specified with
     * {@link #hasCustomModelDataComponent()}. Integers from the old custom
     * model data are equivalent to a single float in the
     * {@link CustomModelDataComponent#getFloats()} list.
     */
    @Deprecated(since = "1.21.5")
    boolean hasCustomModelData();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品的自定义模型数据.
     * <p>
     * CustomModelData 是一个整数, 可以将客户端与自定义的物品模型相关联.
     * <p>
     * 插件应该在调用这个方法之前检查 hasCustomModelData() 是否返回 <code>true</code>.
     * <p>原文：
     * Gets the custom model data that is set.
     * <p>
     * CustomModelData is an integer that may be associated client side with a
     * custom item model.
     * <p>
     * Plugins should check that hasCustomModelData() returns <code>true</code>
     * before calling this method.
     *
     * @return 物品的自定义模型数据
     * <p>原文：the custom model data that is set
     * @deprecated 可以使用 {@link #getCustomModelDataComponent()} 指定更复杂的自定义模型数据.
     * 旧版自定义模型数据中的整数等同于 {@link CustomModelDataComponent#getFloats()} 列表中的单个浮点数.
     * <p>原文：more complex custom model data can be specified with
     * {@link #getCustomModelDataComponent()}. Integers from the old custom
     * model data are equivalent to a single float in the
     * {@link CustomModelDataComponent#getFloats()} list.
     */
    @Deprecated(since = "1.21.5")
    int getCustomModelData();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品上设置的自定义模型数据组件, 或创建一个空的自定义模型数据实例.
     * <p>
     * 返回的组件是其当前状态的快照, 不反映物品上数据的实时视图. 更改此组件上的任何值后,
     * 必须使用 {@link #setCustomModelDataComponent(CustomModelDataComponent)} 来应用更改.
     * <p>原文：
     * Gets the custom model data set on this item, or creates an empty custom
     * model data instance.
     * <p>
     * The returned component is a snapshot of its current state and does not
     * reflect a live view of what is on an item. After changing any value on
     * this component, it must be set with
     * {@link #setCustomModelDataComponent(CustomModelDataComponent)} to apply
     * the changes.
     *
     * @return 自定义模型数据组件
     * <p>原文：component
     */
    @NotNull
    CustomModelDataComponent getCustomModelDataComponent();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的自定义模型数据.
     * <p>
     * CustomModelData 是一个整数, 可以将客户端与自定义的物品模型相关联.
     * <p>原文：
     * Sets the custom model data.
     * <p>
     * CustomModelData is an integer that may be associated client side with a
     * custom item model.
     *
     * @param data 要设置的数据, 传递 null 以清除数据
     * <p>原文：the data to set, or null to clear
     * @deprecated 可以使用 {@link #setCustomModelDataComponent(org.bukkit.inventory.meta.components.CustomModelDataComponent)}
     * 指定更复杂的自定义模型数据. 旧版自定义模型数据中的整数等同于
     * {@link CustomModelDataComponent#setFloats(java.util.List)} 列表中的单个浮点数.
     * <p>原文：more complex custom model data can be specified with
     * {@link #setCustomModelDataComponent(org.bukkit.inventory.meta.components.CustomModelDataComponent)}.
     * Integers from the old custom model data are equivalent to a single float
     * in the {@link CustomModelDataComponent#setFloats(java.util.List)} list.
     */
    @Deprecated(since = "1.21.5")
    void setCustomModelData(@Nullable Integer data);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否设置了自定义模型数据组件.
     * <p>原文：
     * Checks if the custom model data component is set.
     *
     * @return 如果设置了自定义模型数据组件则返回 true
     * <p>原文：if a custom model data component is set
     */
    boolean hasCustomModelDataComponent();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置自定义模型数据组件.
     * <p>原文：
     * Sets the custom model data component.
     *
     * @param customModelData 新的自定义模型数据组件
     * <p>原文：new component
     */
    void setCustomModelDataComponent(@Nullable CustomModelDataComponent customModelData);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否设置了可附魔性组件.
     * <p>原文：
     * Gets if the enchantable component is set.
     *
     * @return 如果设置了可附魔性组件则返回 true
     * <p>原文：if an enchantable is set.
     */
    boolean hasEnchantable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可附魔性组件. 数值越高, 允许的附魔等级也越高.
     * <p>原文：
     * Gets the enchantable component. Higher values allow higher enchantments.
     *
     * @return 可附魔性等级
     * <p>原文：the enchantable value
     */
    int getEnchantable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置可附魔性组件. 数值越高, 允许的附魔等级也越高.
     * <p>原文：
     * Sets the enchantable. Higher values allow higher enchantments.
     *
     * @param enchantable 可附魔性等级
     * <p>原文：enchantable value, must be positive
     */
    void setEnchantable(@Nullable Integer enchantable);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查物品是否含有附魔.
     * <p>原文：
     * Checks for the existence of any enchantments.
     *
     * @return 物品是否含有附魔
     * <p>原文：true if an enchantment exists on this meta
     */
    boolean hasEnchants();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查物品是否存在指定的附魔.
     * <p>原文：
     * Checks for existence of the specified enchantment.
     *
     * @param enchant enchantment to check
     * @return 如果此附魔存在于此物品上则返回 true
     * <p>原文：true if this enchantment exists for this meta
     */
    boolean hasEnchant(@NotNull Enchantment enchant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品上指定附魔的等级.
     * <p>原文：
     * Checks for the level of the specified enchantment.
     *
     * @param enchant enchantment to check
     * @return 附魔等级, 0 代表不存在此附魔
     * <p>原文：The level that the specified enchantment has, or 0 if none
     */
    int getEnchantLevel(@NotNull Enchantment enchant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此物品上的附魔的 map 副本.<br>
     * 如果物品没有附魔则返回空 map.
     * <p>原文：
     * Returns a copy the enchantments in this ItemMeta. <br>
     * Returns an empty map if none.
     *
     * @return 物品所有附魔的不可变副本
     * <p>原文：An immutable copy of the enchantments
     */
    @NotNull
    Map<Enchantment, Integer> getEnchants();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为物品添加一个附魔.
     * <p>原文：
     * Adds the specified enchantment to this item meta.
     *
     * @param enchant Enchantment to add
     * @param level 附魔等级
     * <p>原文：Level for the enchantment
     * @param ignoreLevelRestriction 是否忽略附魔的等级限制
     * <p>原文：this indicates the enchantment should be
     *     applied, ignoring the level limit
     * @return 如果物品的 meta 因调用此方法而改变 (就是附魔添加成功的意思) 返回 true, false 反之
     * <p>原文：true if the item meta changed as a result of this call, false
     *     otherwise
     */
    boolean addEnchant(@NotNull Enchantment enchant, int level, boolean ignoreLevelRestriction);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除物品上指定的附魔.
     * <p>原文：
     * Removes the specified enchantment from this item meta.
     *
     * @param enchant Enchantment to remove
     * @return 移除附魔成功返回 true, false反之
     * <p>原文：true if the item meta changed as a result of this call, false
     *     otherwise
     */
    boolean removeEnchant(@NotNull Enchantment enchant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除物品上的全部附魔.
     * <p>原文：
     * Removes all enchantments from this item meta.
     */
    void removeEnchantments();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查指定的附魔是否与任何 ItemMeta 里的附魔冲突.
     * <p>原文：
     * Checks if the specified enchantment conflicts with any enchantments in
     * this ItemMeta.
     *
     * @param enchant enchantment to test
     * @return 有冲突的附魔返回 true, false 反之
     * <p>原文：true if the enchantment conflicts, false otherwise
     */
    boolean hasConflictingEnchant(@NotNull Enchantment enchant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的 flag, 用来指定客户端渲染物品堆信息时隐藏哪些属性.
     * 本方法会静默忽略双重设置的 itemFlags.
     * <p>原文：
     * Set itemflags which should be ignored when rendering a ItemStack in the Client. This Method does silently ignore double set itemFlags.
     *
     * @param itemFlags 希望哪些属性不显示出来
     * <p>原文：The hideflags which shouldn't be rendered
     */
    void addItemFlags(@NotNull ItemFlag... itemFlags);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除指定的一系列 itemFlags. 这将告诉客户端应再次显示被隐藏的属性.
     * 本方法会静默忽略双重设置的 itemFlags.
     * <p>原文：
     * Remove specific set of itemFlags. This tells the Client it should render it again. This Method does silently ignore double removed itemFlags.
     *
     * @param itemFlags 要移除的属性隐藏标志
     * <p>原文：Hideflags which should be removed
     */
    void removeItemFlags(@NotNull ItemFlag... itemFlags);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前设置的 itemFlags. 返回的集合是不可变的.
     * <p>原文：
     * Get current set itemFlags. The collection returned is unmodifiable.
     *
     * @return 当前设置的 itemFlags 的集合
     * <p>原文：A set of all itemFlags set
     */
    @NotNull
    Set<ItemFlag> getItemFlags();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查物品是否存在指定的 flag.
     * <p>原文：
     * Check if the specified flag is present on this item.
     *
     * @param flag 要检查的 flag
     * <p>原文：the flag to check
     * @return 是否存在指定的 flag
     * <p>原文：if it is present
     */
    boolean hasItemFlag(@NotNull ItemFlag flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品是否设置了隐藏工具提示. 设置此属性的物品将不会显示任何工具提示.
     * <p>原文：
     * Gets if this item has hide_tooltip set. An item with this set will not
     * show any tooltip whatsoever.
     *
     * @return 是否隐藏工具提示
     * <p>原文：hide_tooltip
     */
    boolean isHideTooltip();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此物品是否启用隐藏工具提示. 设置此属性的物品将不会显示任何工具提示.
     * <p>原文：
     * Sets if this item has hide_tooltip set. An item with this set will not
     * show any tooltip whatsoever.
     *
     * @param hideTooltip 是否隐藏工具提示
     * <p>原文：new hide_tooltip
     */
    void setHideTooltip(boolean hideTooltip);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品是否拥有自定义的工具提示样式.
     * <p>原文：
     * Gets if this item has a custom tooltip style.
     *
     * @return 如果设置了工具提示样式则返回 true
     * <p>原文：if a tooltip_style is set
     */
    boolean hasTooltipStyle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取自定义的工具提示样式.
     * <p>原文：
     * Gets the custom tooltip style.
     *
     * @return 工具提示样式对应的命名空间键, 如果未设置则返回 null
     * <p>原文：the tooltip style
     */
    @Nullable
    NamespacedKey getTooltipStyle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置自定义工具提示样式。
     * <p>原文：
     * Sets the custom tooltip style.
     *
     * @param tooltipStyle 新的样式
     * <p>
     * 原文：
     * Sets the custom tooltip style.
     * <p>原文：the new style
     */
    void setTooltipStyle(@Nullable NamespacedKey tooltipStyle);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品是否拥有自定义的物品模型.
     * <p>原文：
     * Gets if this item has a custom item model.
     *
     * @return 如果设置了自定义物品模型则返回 true
     * <p>原文：if an item_model is set
     */
    boolean hasItemModel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取自定义的物品模型.
     * <p>原文：
     * Gets the custom item model.
     *
     * @return 物品模型对应的命名空间键, 如果未设置则返回 null
     * <p>原文：the item model
     */
    @Nullable
    NamespacedKey getItemModel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置自定义的物品模型.
     * <p>原文：
     * Sets the custom item model.
     *
     * @param itemModel 新的物品模型对应的命名空间键
     * <p>原文：the new model
     */
    void setItemModel(@Nullable NamespacedKey itemModel);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回物品的 unbreakable 标签是否为 true.
     * 一个牢不可破的物品不会丧失耐久度.
     * <p>原文：
     * Return if the unbreakable tag is true. An unbreakable item will not lose
     * durability.
     *
     * @return 物品的 unbreakable 标签是否为 true
     * <p>原文：true if the unbreakable tag is true
     */
    boolean isUnbreakable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的 unbreakable 标签是否为 true.
     * 一个牢不可破的物品不会丧失耐久度.
     * <p>原文：
     * Sets the unbreakable tag. An unbreakable item will not lose durability.
     *
     * @param unbreakable 要使物品牢不可破则设为 true
     * <p>原文：true if set unbreakable
     */
    void setUnbreakable(boolean unbreakable);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否设置了附魔光效覆盖.
     * <p>原文：
     * Gets if an enchantment_glint_override is set.
     *
     * @return 如果设置了附魔光效覆盖则返回 true
     * <p>原文：if an enchantment_glint_override is set
     */
    boolean hasEnchantmentGlintOverride();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取附魔光效覆盖。若为true，物品将始终显示光效（即使无附魔）；
     * 若为false，物品将始终不显示光效（即使有附魔）。
     *
     * 插件在调用此方法前应检查 {@link #hasEnchantmentGlintOverride()}。
     * <p>原文：
     * Sets the enchantment_glint_override. If true, the item will glint, even
     * without enchantments; if false, the item will not glint, even with
     * enchantments.
     *
     * Plugins should check {@link #hasEnchantmentGlintOverride()} before
     * calling this method.
     *
     * @return 新的附魔光效覆盖值
     * <p>原文：enchantment_glint_override
     */
    @NotNull
    Boolean getEnchantmentGlintOverride();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置附魔光效覆盖。若为true，物品将始终显示光效（即使无附魔）；
     * 若为false，物品将始终不显示光效（即使有附魔）；若为null，则清除覆盖设置。
     * <p>原文：
     * Sets the enchantment_glint_override. If true, the item will glint, even
     * without enchantments; if false, the item will not glint, even with
     * enchantments. If null, the override will be cleared.
     *
     * @param override 新的附魔光效覆盖值
     * <p>原文：new enchantment_glint_override
     */
    void setEnchantmentGlintOverride(@Nullable Boolean override);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此物品是否为滑翔装备. 如果是, 当玩家装备此物品时将被允许滑翔.
     * <p>原文：
     * Checks if this item is a glider. If true, this item will allow players to
     * glide when it is equipped.
     *
     * @return 此物品是否为滑翔装备
     * <p>原文：glider
     */
    boolean isGlider();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此物品是否为滑翔装备. 如果是, 当玩家装备此物品时将被允许滑翔.
     * <p>原文：
     * Sets if this item is a glider. If true, this item will allow players to
     * glide when it is equipped.
     *
     * @param glider 是否设置为滑翔装备
     * <p>原文：glider
     */
    void setGlider(boolean glider);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此物品是否耐火. 如果为 true, 它将在火或熔岩中不会燃烧.
     * <p>原文：
     * Checks if this item is fire_resistant. If true, it will not burn in fire
     * or lava.
     *
     * @return 此物品是否耐火
     * <p>原文：fire_resistant
     * @deprecated 请使用 {@link #getDamageResistant()} 和 {@link DamageTypeTags#IS_FIRE}
     * <p>原文：use {@link #getDamageResistant()} and
     * {@link DamageTypeTags#IS_FIRE}
     */
    @Deprecated(since = "1.21.2")
    boolean isFireResistant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此物品是否耐火. 如果为 true, 它将在火或熔岩中不会燃烧.
     * <p>原文：
     * Sets if this item is fire_resistant. If true, it will not burn in fire
     * or lava.
     *
     * @param fireResistant 是否设置为耐火
     * <p>原文：fire_resistant
     * @deprecated 请使用 {@link #setDamageResistant(org.bukkit.Tag)} 和 {@link DamageTypeTags#IS_FIRE}
     * <p>原文：use {@link #setDamageResistant(org.bukkit.Tag)} and
     * {@link DamageTypeTags#IS_FIRE}
     */
    @Deprecated(since = "1.21.2")
    void setFireResistant(boolean fireResistant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品是否对特定类型的伤害有抗性.
     * <p>原文：
     * Gets if this item is resistant to certain types of damage.
     *
     * @return 如果设置了伤害抗性则返回 true
     * <p>原文：true if a resistance is set
     */
    boolean hasDamageResistant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当此物品以实体形式存在时, 其对何种伤害类型具有抗性.
     * <p>
     * 插件在调用此方法前应检查 {@link #hasDamageResistant()} 是否返回 true.
     * <p>原文：
     * Gets the type of damage this item will be resistant to when in entity
     * form.
     *
     * Plugins should check {@link #hasDamageResistant()} before calling this
     * method.
     *
     * @return 伤害类型标签, 如果未设置则返回 null
     * <p>原文：damage type
     */
    @Nullable
    Tag<DamageType> getDamageResistant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当此物品以实体形式存在时, 其对何种伤害类型具有抗性.
     * <p>原文：
     * Sets the type of damage this item will be resistant to when in entity
     * form.
     *
     * @param tag 伤害类型标签, 设置为 null 以清除
     * <p>原文：the tag, or null to clear
     */
    void setDamageResistant(@Nullable Tag<DamageType> tag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否设置了最大堆叠数量.
     * <p>原文：
     * Gets if the max_stack_size is set.
     *
     * @return 如果设置了最大堆叠数量则返回 true
     * <p>原文：if a max_stack_size is set.
     */
    boolean hasMaxStackSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取最大堆叠数量. 这是物品可以堆叠的最大数量.
     * <p>原文：
     * Gets the max_stack_size. This is the maximum amount which an item will
     * stack.
     *
     * @return 最大堆叠数量
     * <p>原文：max_stack_size
     */
    int getMaxStackSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置最大堆叠数量. 这是物品可以堆叠的最大数量.
     * <p>原文：
     * Sets the max_stack_size. This is the maximum amount which an item will
     * stack.
     *
     * @param max 最大堆叠数量, 必须在 1 到 99 之间(包含边界)
     * <p>原文：max_stack_size, between 1 and 99 (inclusive)
     */
    void setMaxStackSize(@Nullable Integer max);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否设置了稀有度.
     * <p>原文：
     * Gets if the rarity is set.
     *
     * @return 如果设置了稀有度则返回 true
     * <p>原文：rarity
     */
    boolean hasRarity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品的稀有度.
     * <p>
     * 插件在调用此方法前应检查 {@link #hasRarity()} 是否返回 true.
     * <p>原文：
     * Gets the item rarity.
     *
     * Plugins should check {@link #hasRarity()} before calling this method.
     *
     * @return 物品稀有度
     * <p>原文：rarity
     */
    @NotNull
    ItemRarity getRarity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的稀有度.
     * <p>原文：
     * Sets the item rarity.
     *
     * @param rarity 新的物品稀有度
     * <p>原文：new rarity
     */
    void setRarity(@Nullable ItemRarity rarity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否设置了使用后残留物.
     * <p>原文：
     * Checks if the use remainder is set.
     *
     * @return 如果设置了使用后残留物则返回 true
     * <p>原文：if a use remainder item is set
     */
    boolean hasUseRemainder();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当此物品被使用后, 其将转换为何种物品.
     * <p>
     * 返回的组件是其当前状态的快照, 不反映物品上数据的实时视图. 更改此组件上的任何值后,
     * 必须使用 {@link #setUseRemainder(ItemStack)} 来应用更改.
     * <p>原文：
     * Gets the item which this item will convert to when used.
     *
     * @return 使用后生成的物品堆栈, 如果未设置则返回 null
     * <p>原文：remainder
     */
    @Nullable
    ItemStack getUseRemainder();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当此物品被使用后, 其将转换为何种物品.
     * <p>原文：
     * Sets the item which this item will convert to when used.
     *
     * @param remainder 新的使用后生成的物品堆栈
     * <p>原文：new item
     */
    void setUseRemainder(@Nullable ItemStack remainder);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否设置了使用冷却.
     * <p>原文：
     * Checks if the use cooldown is set.
     *
     * @return 如果设置了使用冷却则返回 true
     * <p>原文：if a use cooldown is set
     */
    boolean hasUseCooldown();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品上设置的使用冷却组件, 或创建一个空的使用冷却实例.
     * <p>
     * 返回的组件是其当前状态的快照, 不反映物品上数据的实时视图. 更改此组件上的任何值后,
     * 必须使用 {@link #setUseCooldown(UseCooldownComponent)} 来应用更改.
     * <p>原文：
     * Gets the use cooldown set on this item, or creates an empty cooldown
     * instance.
     * <p>
     * The returned component is a snapshot of its current state and does not
     * reflect a live view of what is on an item. After changing any value on
     * this component, it must be set with
     * {@link #setUseCooldown(UseCooldownComponent)} to apply the changes.
     *
     * @return 使用冷却组件
     * <p>原文：cooldown
     */
    @NotNull
    UseCooldownComponent getUseCooldown();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的使用冷却组件.
     * <p>原文：
     * Sets the item use cooldown.
     *
     * @param cooldown 新的使用冷却组件
     * <p>原文：new cooldown
     */
    void setUseCooldown(@Nullable UseCooldownComponent cooldown);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否设置了食物组件.
     * <p>原文：
     * Checks if the food is set.
     *
     * @return 如果设置了食物组件则返回 true
     * <p>原文：if a food is set
     */
    boolean hasFood();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品上设置的食物组件, 或创建一个空的食物实例.
     * <p>
     * 返回的组件是其当前状态的快照, 不反映物品上数据的实时视图. 更改此组件上的任何值后,
     * 必须使用 {@link #setFood(FoodComponent)} 来应用更改.
     * <p>原文：
     * Gets the food set on this item, or creates an empty food instance.
     * <p>
     * The returned component is a snapshot of its current state and does not
     * reflect a live view of what is on an item. After changing any value on
     * this component, it must be set with {@link #setFood(FoodComponent)} to
     * apply the changes.
     *
     * @return 食物组件
     * <p>原文：food
     */
    @NotNull
    FoodComponent getFood();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的食物组件.
     * <p>原文：
     * Sets the item food.
     *
     * @param food 新的食物组件
     * <p>原文：new food
     */
    void setFood(@Nullable FoodComponent food);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否设置了工具组件.
     * <p>原文：
     * Checks if the tool is set.
     *
     * @return 如果设置了工具组件则返回 true
     * <p>原文：if a tool is set
     */
    boolean hasTool();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品上设置的工具组件, 或创建一个空的工具实例.
     * <p>
     * 返回的组件是其当前状态的快照, 不反映物品上数据的实时视图. 更改此组件上的任何值后,
     * 必须使用 {@link #setTool(ToolComponent)} 来应用更改.
     * <p>原文：
     * Gets the tool set on this item, or creates an empty tool instance.
     * <p>
     * The returned component is a snapshot of its current state and does not
     * reflect a live view of what is on an item. After changing any value on
     * this component, it must be set with {@link #setTool(ToolComponent)} to
     * apply the changes.
     *
     * @return 工具组件
     * <p>原文：tool
     */
    @NotNull
    ToolComponent getTool();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的工具组件.
     * <p>原文：
     * Sets the item tool.
     *
     * @param tool 新的工具组件
     * <p>原文：new tool
     */
    void setTool(@Nullable ToolComponent tool);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否设置了可装备组件.
     * <p>原文：
     * Checks if the equippable is set.
     *
     * @return 如果设置了可装备组件则返回 true
     * <p>原文：if an equippable is set
     */
    boolean hasEquippable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品上设置的可装备组件, 或创建一个空的可装备实例.
     * <p>
     * 返回的组件是其当前状态的快照, 不反映物品上数据的实时视图. 更改此组件上的任何值后,
     * 必须使用 {@link #setEquippable(EquippableComponent)} 来应用更改.
     * <p>原文：
     * Gets the equippable set on this item, or creates an empty equippable
     * instance.
     * <p>
     * The returned component is a snapshot of its current state and does not
     * reflect a live view of what is on an item. After changing any value on
     * this component, it must be set with
     * {@link #setEquippable(EquippableComponent)} to apply the changes.
     *
     * @return 可装备组件
     * <p>原文：equippable
     */
    @NotNull
    EquippableComponent getEquippable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的可装备组件.
     * <p>原文：
     * Sets the equippable tool.
     *
     * @param equippable 新的可装备组件
     * <p>原文：new equippable
     */
    void setEquippable(@Nullable EquippableComponent equippable);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否设置了唱片机可播放组件.
     * <p>原文：
     * Checks if the jukebox playable is set.
     *
     * @return 如果设置了唱片机可播放组件则返回 true
     * <p>原文：if a jukebox playable is set
     */
    boolean hasJukeboxPlayable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品上设置的唱片机可播放组件.
     * <p>
     * 返回的组件是其当前状态的快照, 不反映物品上数据的实时视图. 更改此组件上的任何值后,
     * 必须使用 {@link #setJukeboxPlayable(org.bukkit.inventory.meta.components.JukeboxComponent)} 来应用更改.
     * <p>原文：
     * Gets the jukebox playable component set on this item.
     * <p>
     * The returned component is a snapshot of its current state and does not
     * reflect a live view of what is on an item. After changing any value on
     * this component, it must be set with
     * {@link #setJukeboxPlayable(org.bukkit.inventory.meta.components.JukeboxPlayableComponent)}
     * to apply the changes.
     *
     * @return 唱片机可播放组件, 如果未设置则返回 null
     * <p>原文：component
     */
    @NotNull // Paper
    JukeboxPlayableComponent getJukeboxPlayable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的唱片机可播放组件.
     * <p>原文：
     * Sets the jukebox playable component.
     *
     * @param jukeboxPlayable 新的唱片机可播放组件
     * <p>原文：new component
     */
    void setJukeboxPlayable(@Nullable JukeboxPlayableComponent jukeboxPlayable);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查物品是否存在任何属性修饰符.
     * <p>原文：
     * Checks for the existence of any AttributeModifiers.
     *
     * @return 物品存在属性修饰符则返回 true
     * <p>原文：true if any AttributeModifiers exist
     */
    boolean hasAttributeModifiers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回所有属性及其修饰符的不可变副本.<br>
     * 如果不存在则返回 null.
     * <p>原文：
     * Return an immutable copy of all Attributes and
     * their modifiers in this ItemMeta.<br>
     * Returns null if none exist.
     *
     * @return 所有属性及其修饰符的不可变 {@link Multimap}, null 代表不存在修饰符
     * <p>原文：an immutable {@link Multimap} of Attributes
     *         and their AttributeModifiers, or null if none exist
     */
    @Nullable
    Multimap<Attribute, AttributeModifier> getAttributeModifiers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回指定 {@link EquipmentSlot} 的所有属性及其修饰符的不可变列表.
     * 设置了给定 {@link EquipmentSlot} 的任何 {@link AttributeModifier} 都将会返回.
     * 这是因为未指定槽位的修饰符可在任意槽位中生效.<br>
     * 如果未给指定槽位设置任何属性, 将返回一个空 map.
     * <p>原文：
     * Return an immutable copy of all {@link Attribute}s and their
     * {@link AttributeModifier}s for a given {@link EquipmentSlot}.<br>
     * Any {@link AttributeModifier} that does have a given
     * {@link EquipmentSlot} will be returned. This is because
     * AttributeModifiers without a slot are active in any slot.<br>
     * If there are no attributes set for the given slot, an empty map
     * will be returned.
     *
     * @param slot 要检查的 {@link EquipmentSlot}
     * <p>原文：the {@link EquipmentSlot} to check
     * @return 包含对应属性和修饰符的不可变 {@link Multimap},
     *         如果未设置任何属性则返回空 map.
     * <p>原文：the immutable {@link Multimap} with the
     *         respective Attributes and modifiers, or an empty map
     *         if no attributes are set.
     */
    @NotNull
    Multimap<Attribute, AttributeModifier> getAttributeModifiers(@NotNull EquipmentSlot slot);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回指定{@link Attribute 属性}的所有{@link AttributeModifier 修饰符}的不可变列表.
     * <p>原文：
     * Return an immutable copy of all {@link AttributeModifier}s
     * for a given {@link Attribute}
     *
     * @param attribute {@link Attribute 属性}
     * <p>原文：the {@link Attribute}
     * @return 包含所有{@link AttributeModifier 修饰符}的不可变集合,
     * 如果指定属性不存在修饰符则返回 null
     * <p>原文：an immutable collection of {@link AttributeModifier}s
     *          or null if no AttributeModifiers exist for the Attribute.
     * @throws NullPointerException 若 Attribute 为 null
     * <p>原文：if Attribute is null
     */
    @Nullable
    Collection<AttributeModifier> getAttributeModifiers(@NotNull Attribute attribute);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加一个属性和它的修饰符.
     * 属性修饰符现支持设置 {@link EquipmentSlot}.
     * 如果没有设置, 则{@link AttributeModifier 属性修饰符}将在所有槽位中生效.
     * <br>
     * 拥有相同 {@link java.util.UUID} 的两个 {@link AttributeModifier}
     * 不可同时存在于同一个属性上.
     * <p>原文：
     * Add an Attribute and it's Modifier.
     * AttributeModifiers can now support {@link EquipmentSlot}s.
     * If not set, the {@link AttributeModifier} will be active in ALL slots.
     * <br>
     * Two {@link AttributeModifier}s that have the same {@link java.util.UUID}
     * cannot exist on the same Attribute.
     *
     * @param attribute 要修改的{@link Attribute 属性}
     * <p>原文：the {@link Attribute} to modify
     * @param modifier 属性的{@link AttributeModifier 修饰符}
     * <p>原文：the {@link AttributeModifier} specifying the modification
     * @return 如果属性及其修饰符成功添加返回 true
     * <p>原文：true if the Attribute and AttributeModifier were
     *         successfully added
     * @throws NullPointerException 若 Attribute 为 null
     * <p>原文：if Attribute is null
     * @throws NullPointerException 若 Attribute 为 null
     * <p>原文：if AttributeModifier is null
     * @throws IllegalArgumentException 若 AttributeModifier 已存在于已设置的某一属性上
     * <p>原文：if AttributeModifier already exists
     */
    boolean addAttributeModifier(@NotNull Attribute attribute, @NotNull AttributeModifier modifier);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置所有{@link Attribute 属性}和它们的{@link AttributeModifier 属性修饰符}.
     * 要移除所有已设置的属性和属性修饰符, 可以指定参数为 null 或空的 Multimap.
     * 若参数既不为 null 也不为空 map, 本方法将筛选出非空的键值对并添加给物品堆.
     * <p>
     * 译注: 本方法会清空原有的属性, 添加请用 add 开头的相关方法.
     * <p>原文：
     * Set all {@link Attribute}s and their {@link AttributeModifier}s.
     * To clear all custom attribute modifiers, use {@code null}. To set
     * no modifiers (which will override the default modifiers), use an
     * empty map.
     * If not null nor empty, this will filter all entries that are not-null
     * and add them to the ItemStack.
     *
     * @param attributeModifiers 包含属性和它们的属性修饰符的新 Multimap
     * <p>原文：the new Multimap containing the Attributes
     *                           and their AttributeModifiers
     */
    void setAttributeModifiers(@Nullable Multimap<Attribute, AttributeModifier> attributeModifiers);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除与给定{@link Attribute 属性}有关的所有 {@link AttributeModifier 属性修饰符}.
     * 如果没有移除任何东西将会返回 false.
     * <p>原文：
     * Remove all {@link AttributeModifier}s associated with the given
     * {@link Attribute}.
     * This will return false if nothing was removed.
     *
     * @param attribute 要移除的属性
     * <p>原文：attribute to remove
     * @return 与此属性有关的所有修饰符均被移除则返回 true, 没有移除任何修饰符则返回 false
     * <p>原文：true if all modifiers were removed from a given
     *                  Attribute. Returns false if no attributes were
     *                  removed.
     * @throws NullPointerException 如果 Attribute 为 null
     * <p>原文：if Attribute is null
     */
    boolean removeAttributeModifier(@NotNull Attribute attribute);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定的 {@link EquipmentSlot} 移除其所有的{@link Attribute 属性}和{@link AttributeModifier 属性修饰符}.<br>
     * 如果给定的 {@link EquipmentSlot} 为 null, 这将移除所有没有设置 {@link EquipmentSlot} 的 {@link AttributeModifier}.
     * <p>原文：
     * Remove all {@link Attribute}s and {@link AttributeModifier}s for a
     * given {@link EquipmentSlot}.<br>
     * If the given {@link EquipmentSlot} is null, this will remove all
     * {@link AttributeModifier}s that do not have an EquipmentSlot set.
     *
     * @param slot 要操作的 {@link EquipmentSlot}
     * <p>原文：the {@link EquipmentSlot} to clear all Attributes and
     *             their modifiers for
     * @return 如果与给定 EquipmentSlot 匹配的所有修饰符被移除则返回true.
     * <p>原文：true if all modifiers were removed that match the given
     *         EquipmentSlot.
     */
    boolean removeAttributeModifier(@NotNull EquipmentSlot slot);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除一个指定的 {@link Attribute 属性} 和 {@link AttributeModifier 属性修饰符}.
     * 将根据它们的 {@link java.util.UUID} 匹配属性修饰符.
     * <p>原文：
     * Remove a specific {@link Attribute} and {@link AttributeModifier}.
     * AttributeModifiers are matched according to their {@link java.util.UUID}.
     *
     * @param attribute 要移除的{@link Attribute 属性}
     * <p>原文：the {@link Attribute} to remove
     * @param modifier 要移除的{@link AttributeModifier 属性修饰符}
     * <p>原文：the {@link AttributeModifier} to remove
     * @return 如果删除了任何属性修饰符
     * <p>原文：if any attribute modifiers were remove
     * @throws NullPointerException 若 Attribute 为 null
     * <p>原文：if the Attribute is null
     * @throws NullPointerException 若 Attribute 为 null
     * <p>原文：if the AttributeModifier is null
     * @see AttributeModifier#getKey()
     */
    boolean removeAttributeModifier(@NotNull Attribute attribute, @NotNull AttributeModifier modifier);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此 ItemMeta 转换为 NBT 字符串. 如果此 ItemMeta 没有任何 NBT 数据, 则返回 {@code "{}"}.
     * <p>
     * 译注: NBT (Named Binary Tag) 是 Minecraft 中用于存储数据的一种格式.
     * <p>
     * 此字符串<strong>绝不应</strong>被视为可序列化的值. 如果需要序列化, 则应使用 {@link ConfigurationSerializable} API.
     * <p>原文：
     * Get this ItemMeta as an NBT string. If this ItemMeta does not have any
     * NBT, then {@code "{}"} will be returned.
     * <p>
     * This string should <strong>NEVER</strong> be relied upon as a serializable value. If
     * serialization is desired, the {@link ConfigurationSerializable} API should be used
     * instead.
     *
     * @return NBT 字符串
     * <p>原文：the NBT string
     */
    @NotNull
    String getAsString();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此 ItemMeta 转换为组件兼容的字符串. 如果此 ItemMeta 不包含任何组件, 则返回 {@code "[]"}.
     * <p>
     * 此方法的结果应生成一个代表由此 ItemMeta 实例修改的组件的字符串. 当与前置的物品类型一起传递给 {@link ItemFactory#createItemStack(String)} 时,
     * 它将创建一个拥有与此 ItemMeta 实例完全匹配的 ItemMeta 的 ItemStack. 请注意, 此方法<strong>仅</strong>返回组件部分,
     * 不能单独传递给 createItemStack().
     * 示例如下:
     * <pre>
     * ItemStack itemStack = // ... 从某处获取的物品堆栈
     * ItemMeta itemMeta = itemStack.getItemMeta();
     *
     * String components = itemMeta.getAsComponentString(); // 示例: "[minecraft:damage=53]"
     * String itemTypeKey = itemStack.getType().getKey().toString(); // 示例: "minecraft:diamond_sword"
     * String itemAsString = itemTypeKey + components; // 结果: "minecraft:diamond_sword[minecraft:damage=53]"
     *
     * ItemStack recreatedItemStack = Bukkit.getItemFactory().createItemStack(itemAsString);
     * assert itemStack.isSimilar(recreatedItemStack); // 应为 true*
     * </pre>
     * <p>
     * *未被此 ItemMeta 实例表示或显式覆盖的组件将不会包含在结果字符串中, 因此可能导致 ItemStack <em>不完全</em>匹配.
     * 例如, 如果未设置 {@link #setDisplayName(String)}, 则自定义名称组件将不会被包含.
     * 或者, 如果此 ItemMeta 是 PotionMeta, 它将不包含与磁石指针、旗帜或书等相关的任何组件,
     * 仅包含可通过 PotionMeta 实例修改的组件.
     * <p>
     * 此字符串<strong>绝不应</strong>被视为可序列化的值. 如果需要序列化, 则应使用 {@link ConfigurationSerializable} API.
     * <p>原文：
     * Get this ItemMeta as a component-compliant string. If this ItemMeta does
     * not contain any components, then {@code "[]"} will be returned.
     * <p>
     * The result of this method should yield a string representing the components
     * altered by this ItemMeta instance. When passed to {@link ItemFactory#createItemStack(String)}
     * with a prepended item type, it will create an ItemStack that has an ItemMeta
     * matching this ItemMeta instance exactly. Note that this method returns <strong>
     * ONLY</strong> the components and cannot be passed to createItemStack() alone.
     * An example may look something like this:
     * <pre>
     * ItemStack itemStack = // ... an item stack obtained from somewhere
     * ItemMeta itemMeta = itemStack.getItemMeta();
     *
     * String components = itemMeta.getAsComponentString(); // example: "[minecraft:damage=53]"
     * String itemTypeKey = itemStack.getType().getKey().toString(); // example: "minecraft:diamond_sword"
     * String itemAsString = itemTypeKey + components; // results in: "minecraft:diamond_sword[minecraft:damage=53]"
     *
     * ItemStack recreatedItemStack = Bukkit.getItemFactory().createItemStack(itemAsString);
     * assert itemStack.isSimilar(recreatedItemStack); // Should be true*
     * </pre>
     * <p>
     * *Components not represented or explicitly overridden by this ItemMeta instance
     * will not be included in the resulting string and therefore may result in ItemStacks
     * that do not match <em>exactly</em>. For example, if {@link #setDisplayName(String)}
     * is not set, then the custom name component will not be included. Or if this ItemMeta
     * is a PotionMeta, it will not include any components related to lodestone compasses,
     * banners, or books, etc., only components modifiable by a PotionMeta instance.
     * <p>
     * This string should <strong>NEVER</strong> be relied upon as a serializable value. If
     * serialization is desired, the {@link ConfigurationSerializable} API should be used
     * instead.
     *
     * @return 组件兼容的字符串
     * <p>原文：the component-compliant string
     */
    @NotNull
    String getAsComponentString();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回一个公共的自定义标签容器, 其能够在物品上存储标签.
     *
     * 那些标签及其所有内容都会被发送到客户端, 因此客户端能够读取它们.
     * 这将导致玩家可以看到物品上有 NBT 标签的提醒.
     *
     * 一旦客户端处于创造模式, 这些标签也可被客户端修改.
     * <p>原文：
     * Returns a public custom tag container capable of storing tags on the
     * item.
     *
     * Those tags will be sent to the client with all of their content, so the
     * client is capable of reading them. This will result in the player seeing
     * a NBT Tag notification on the item.
     *
     * These tags can also be modified by the client once in creative mode
     *
     * @return 自定义的标签容器
     * <p>原文：the custom tag container
     * @deprecated 该 API 已被 {@link PersistentDataHolder} API 取代.
     * 请使用 {@link PersistentDataHolder#getPersistentDataContainer()}.
     * <p>原文：this API part has been replaced by the {@link PersistentDataHolder} API.
     * Please use {@link PersistentDataHolder#getPersistentDataContainer()} instead of this.
     */
    @NotNull
    @Deprecated(since = "1.14")
    CustomItemTagContainer getCustomTagContainer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 仅供内部使用! (插件)请勿在任何情况下使用!
     * <p>原文：
     * Internal use only! Do not use under any circumstances!
     *
     * @param version meta的版本
     * <p>原文：version
     * @apiNote 仅供内部使用
     * <p>原文：internal use only
     */
    @ApiStatus.Internal
    void setVersion(int version);

    @NotNull
    ItemMeta clone();

    /**
     * Gets set of materials what given item can destroy in {@link org.bukkit.GameMode#ADVENTURE}
     *
     * @return Set of materials
     * @deprecated this API part has been replaced by the {@link ItemAdventurePredicate} API.
     * Please use {@link ItemStack#getData(DataComponentType.Valued)} with {@link DataComponentTypes#CAN_BREAK} instead of this.
     */
    @Deprecated(forRemoval = true, since = "1.14")
    Set<org.bukkit.Material> getCanDestroy();

    /**
     * Sets set of materials what given item can destroy in {@link org.bukkit.GameMode#ADVENTURE}
     *
     * @param canDestroy Set of materials
     * @deprecated this API part has been replaced by the {@link ItemAdventurePredicate} API.
     * Please use {@link ItemStack#setData(DataComponentType.Valued, Object)} with {@link DataComponentTypes#CAN_BREAK} instead of this.
     */
    @Deprecated(forRemoval = true, since = "1.14")
    void setCanDestroy(Set<org.bukkit.Material> canDestroy);

    /**
     * Gets set of materials where given item can be placed on in {@link org.bukkit.GameMode#ADVENTURE}
     *
     * @return Set of materials
     * @deprecated this API part has been replaced by the {@link ItemAdventurePredicate} API.
     * Please use {@link ItemStack#getData(DataComponentType.Valued)} with {@link DataComponentTypes#CAN_PLACE_ON} instead of this.
     */
    @Deprecated(forRemoval = true, since = "1.14")
    Set<org.bukkit.Material> getCanPlaceOn();

    /**
     * Sets set of materials where given item can be placed on in {@link org.bukkit.GameMode#ADVENTURE}
     *
     * @param canPlaceOn Set of materials
     * @deprecated this API part has been replaced by the {@link ItemAdventurePredicate} API.
     * Please use {@link ItemStack#setData(DataComponentType.Valued, Object)} with {@link DataComponentTypes#CAN_PLACE_ON} instead of this.
     */
    @Deprecated(forRemoval = true, since = "1.14")
    void setCanPlaceOn(Set<org.bukkit.Material> canPlaceOn);

    /**
     * Gets the collection of namespaced keys that the item can destroy in {@link org.bukkit.GameMode#ADVENTURE}
     *
     * @return Set of {@link com.destroystokyo.paper.Namespaced}
     * @deprecated this API part has been replaced by the {@link ItemAdventurePredicate} API.
     * Please use {@link ItemStack#getData(DataComponentType.Valued)} with {@link DataComponentTypes#CAN_BREAK} instead of this.
     */
    @Deprecated(forRemoval = true, since = "1.20.6")
    @NotNull
    Set<com.destroystokyo.paper.Namespaced> getDestroyableKeys();

    /**
     * Sets the collection of namespaced keys that the item can destroy in {@link org.bukkit.GameMode#ADVENTURE}
     *
     * @param canDestroy Collection of {@link com.destroystokyo.paper.Namespaced}
     * @deprecated this API part has been replaced by the {@link ItemAdventurePredicate} API.
     * Please use {@link ItemStack#setData(DataComponentType.Valued, Object)} with {@link DataComponentTypes#CAN_BREAK} instead of this.
     */
    @Deprecated(forRemoval = true, since = "1.20.6")
    void setDestroyableKeys(@NotNull Collection<com.destroystokyo.paper.Namespaced> canDestroy);

    /**
     * Gets the collection of namespaced keys that the item can be placed on in {@link org.bukkit.GameMode#ADVENTURE}
     *
     * @return Set of {@link com.destroystokyo.paper.Namespaced}
     * @deprecated this API part has been replaced by the {@link ItemAdventurePredicate} API.
     * Please use {@link ItemStack#getData(DataComponentType.Valued)} with {@link DataComponentTypes#CAN_PLACE_ON} instead of this.
     */
    @NotNull
    @Deprecated(forRemoval = true, since = "1.20.6")
    Set<com.destroystokyo.paper.Namespaced> getPlaceableKeys();

    /**
     * Sets the set of namespaced keys that the item can be placed on in {@link org.bukkit.GameMode#ADVENTURE}
     *
     * @param canPlaceOn Collection of {@link com.destroystokyo.paper.Namespaced}
     * @deprecated this API part has been replaced by the {@link ItemAdventurePredicate} API.
     * Please use {@link ItemStack#setData(DataComponentType.Valued, Object)} with {@link DataComponentTypes#CAN_PLACE_ON} instead of this.
     */
    @Deprecated(forRemoval = true, since = "1.20.6")
    void setPlaceableKeys(@NotNull Collection<com.destroystokyo.paper.Namespaced> canPlaceOn);

    /**
     * Checks for the existence of any keys that the item can be placed on
     *
     * @return true if this item has placeable keys
     * @deprecated this API part has been replaced by the {@link ItemAdventurePredicate} API.
     * Please use {@link ItemStack#hasData(DataComponentType)} with {@link DataComponentTypes#CAN_PLACE_ON} instead of this.
     */
    @Deprecated(forRemoval = true, since = "1.20.6")
    boolean hasPlaceableKeys();

    /**
     * Checks for the existence of any keys that the item can destroy
     *
     * @return true if this item has destroyable keys
     * @deprecated this API part has been replaced by the {@link ItemAdventurePredicate} API.
     * Please use {@link ItemStack#hasData(DataComponentType)} with {@link DataComponentTypes#CAN_BREAK} instead of this.
     */
    @Deprecated(forRemoval = true, since = "1.20.6")
    boolean hasDestroyableKeys();
}
