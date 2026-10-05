package org.bukkit.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 各种物品栏的接口。有关{@link
 * Material#AIR}的行为时是未指定的.
 *
 * <br>
 * <b>请注意虽然 {@link #iterator()} 能处理整个物品栏, 但add/contains/remove方法只能用于物品栏的存储内容.</b>
 * <br>
 * <b>针对特定的物品迭代, 可考虑使用 {@link #getContents()} 和 {@link #getStorageContents()}</b>
 * <p>原文：
 * Interface to the various inventories. Behavior relating to {@link
 * Material#AIR} is unspecified.
 *
 * <br>
 * <b>Note that whilst {@link #iterator()} deals with the entire inventory, add
 * / contains / remove methods deal only with the storage contents.</b>
 * <br>
 * <b>Consider using {@link #getContents()} and {@link #getStorageContents()} for
 * specific iteration.</b>
 *
 * @see #getContents()
 * @see #getStorageContents()
 */
public interface Inventory extends Iterable<ItemStack> {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此物品栏的大小.
     * <p>原文：
     * Returns the size of the inventory
     *
     * @return 此物品栏的大小
     * <p>原文：The size of the inventory
     */
    public int getSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个物品栏的最大物品堆叠数量.
     * <p>原文：
     * Returns the maximum stack size for an ItemStack in this inventory.
     *
     * @return 最大物品堆叠数量
     * <p>原文：The maximum size for an ItemStack in this inventory.
     */
    public int getMaxStackSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此方法可以让你改变一个物品栏的最大物品堆叠数量.
     * <p>
     * <b>警告:</b>
     * <ul>
     * <li>不是所有种类的的物品栏都遵循本值。
     * <li>若本值大于127，当世界保存时可能会被缩减。
     * <li>本值不保证被保存；一定要在设置一个格子的最大堆叠数之前修改本值
     * <li>若本值大于这种物品栏默认的大小，可能不会正确地在客户端上显示
     * </ul>
     * <p>原文：
     * This method allows you to change the maximum stack size for an
     * inventory.
     * <p>
     * <b>Caveats:</b>
     * <ul>
     * <li>Not all inventories respect this value.
     * <li>Stacks larger than 99 will throw errors when serialized.
     * <li>This value is not guaranteed to be preserved; be sure to set it
     *     before every time you want to set a slot over the max stack size.
     * <li>Stacks larger than the default max size for this type of inventory
     *     are ignored by the client, resulting in the vanilla client
     *     always trimming it down to default maximum stack size.
     * <li>Most operations ignore this value if it is over {@link ItemStack#getMaxStackSize()}
     * </ul>
     *
     * @param size 最大物品堆叠数量
     * <p>原文：The new maximum stack size for items in this inventory.
     */
    public void setMaxStackSize(int size);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回在指定索引的物品堆.
     * <p>原文：
     * Returns the ItemStack found in the slot at the given index
     *
     * @param index 要查找的格子
     * <p>原文：The index of the Slot's ItemStack to return
     * @return 在此格子的物品堆
     * <p>原文：The ItemStack in the slot
     */
    @Nullable
    public ItemStack getItem(int index);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在物品栏指定索引存放物品堆.
     * <p>原文：
     * Stores the ItemStack at the given index of the inventory.
     *
     * @param index 在哪里存放这个物品堆
     * <p>原文：The index where to put the ItemStack
     * @param item 要设置的物品堆
     * <p>原文：The ItemStack to set
     */
    public void setItem(int index, @Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向物品栏添加所给的物品堆.本方法将尽可能完美地尝试填充已有的但还未达到堆叠上限的物品堆
     * 和空格子.
     * <p>
     * 返回一个包含无法添加的物品堆的HashMap，键是传入的参数中那一物品堆所在的索引,
     * 值是传入的可变参数中指定索引处的物品堆.如果所有物品都被添加, 将返回一个空的HashMap.
     * <p>
     * 如果传入的物品堆超过了这一物品材质的最大堆叠数量,首先它们将被按照
     * 物品材质的最大堆叠量，添加一部分至堆叠数量未满的物品堆.如果没有堆叠数量未满的物品堆剩余,
     * 物品堆将被以物品栏所允许的物品堆最大堆叠数量分割, 允许超出物品材质的最大堆叠数量.
     * <p>
     * 在对此方法的一些已知实现中，会将传入的物品堆参数的数量设置为超出而无法添加的物品堆数量.
     * <p>
     * 译注:可能指部分实现中，此方法会直接操作传入参数对象，而不是将传入参数对象进行复制后再操作其数量
     * <p>原文：
     * Stores the given ItemStacks in the inventory. This will try to fill
     * existing stacks and empty slots as well as it can.
     * <p>
     * The returned HashMap contains what it couldn't store, where the key is
     * the index of the parameter, and the value is the ItemStack at that
     * index of the varargs parameter. If all items are stored, it will return
     * an empty HashMap.
     * <p>
     * Items resulted from this method will not exceed the minimum
     * of {@link ItemStack#getMaxStackSize()} and {@link #getMaxStackSize()}.
     * <p>
     * First, this method will try to fill all the partial stacks in the inventory.
     * Then it will try to fill empty slots, over-stacked items being able to
     * fill several empty slots. The rest are placed in the returned map.
     * <p>
     * It is known that in some implementations this method will also set
     * the inputted argument amount to the number of that item not placed in
     * slots.
     *
     * @param items 要存放的物品
     * <p>原文：The ItemStacks to add
     * @return 包含不能被存放的物品的HashMap
     * <p>原文：A HashMap containing items that didn't fit.
     * @throws IllegalArgumentException 如果传入的可变参数中的任一元素为null
     * <p>原文：if items or any element in it is null
     */
    @NotNull
    public HashMap<Integer, ItemStack> addItem(@NotNull ItemStack... items) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除在物品栏内的指定物品堆.
     * <p>
     * 本方法将按照你所给的物品堆的数量和种类作为参数尽可能多地移除物品堆.
     * <p>
     * 返回的HashMap包含不能移除的物品堆，map的键
     * 是你所给的参数的索引值，值是在你所给的参数指定索引处的物品堆.如果
     * 所给的物品堆全部被移除，将返回一个空HashMap.
     * <p>原文：
     * Removes the given ItemStacks from the storage contents of the inventory.
     * For removing ItemStacks from the inventories that have other content groups,
     * like Player inventories, see {@link #removeItemAnySlot(ItemStack...)}.
     * <p>
     * It will try to remove 'as much as possible' from the types and amounts
     * you give as arguments.
     * <p>
     * The returned HashMap contains what it couldn't remove, where the key is
     * the index of the parameter, and the value is the ItemStack at that
     * index of the varargs parameter. If all the given ItemStacks are
     * removed, it will return an empty HashMap.
     * <p>
     * It is known that in some implementations this method will also set the
     * inputted argument amount to the number of that item not removed from
     * slots.
     *
     * @param items 要移除的物品堆
     * <p>原文：The ItemStacks to remove
     * @return 包含不能被移除的物品堆的HashMap
     * <p>原文：A HashMap containing items that couldn't be removed.
     * @throws IllegalArgumentException 如果items为null
     * <p>原文：if items is null
     * @see #removeItemAnySlot(ItemStack...)
     */
    @NotNull
    public HashMap<Integer, ItemStack> removeItem(@NotNull ItemStack... items) throws IllegalArgumentException;

    // Paper start
    /**
     * Searches all possible inventory slots in order to remove the given ItemStacks.
     * <p>
     * Similar to {@link Inventory#removeItem(ItemStack...)} in behavior, except this
     * method will check all possible slots in the inventory, rather than just the main
     * storage contents.
     * <p>
     * It will try to remove 'as much as possible' from the types and amounts
     * you give as arguments.
     * <p>
     * The returned HashMap contains what it couldn't remove, where the key is
     * the index of the parameter, and the value is the ItemStack at that
     * index of the varargs parameter. If all the given ItemStacks are
     * removed, it will return an empty HashMap.
     * <p>
     * It is known that in some implementations this method will also set the
     * inputted argument amount to the number of that item not removed from
     * slots.
     *
     * @param items The ItemStacks to remove
     * @return A HashMap containing items that couldn't be removed.
     * @throws IllegalArgumentException if items is null
     */
    @NotNull
    public HashMap<Integer, ItemStack> removeItemAnySlot(@NotNull ItemStack... items) throws IllegalArgumentException;
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个物品栏内的所有物品堆.
     * <p>原文：
     * Returns all ItemStacks from the inventory
     *
     * @return 存储了此物品栏所有物品的数组. 个别条目可能为null
     * <p>原文：An array of ItemStacks from the inventory. Individual items may be null.
     */
    public @Nullable ItemStack @NotNull [] getContents(); // Paper - make array elements nullable instead array

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 完全地覆盖物品栏的内容.移除所有存在的内容并替换为指定的物品堆.
     * <p>原文：
     * Completely replaces the inventory's contents. Removes all existing
     * contents and replaces it with the ItemStacks given in the array.
     *
     * @param items 内容的完全替换;数组长度必须小于或等于{@link #getSize()}.
     * <p>原文：A complete replacement for the contents; the length must
     *     be less than or equal to {@link #getSize()}.
     * @throws IllegalArgumentException 如果数组容量超越了物品栏的容量
     * <p>原文：If the array has more items than the
     *     inventory.
     */
    public void setContents(@Nullable ItemStack @NotNull [] items) throws IllegalArgumentException; // Paper - make array elements nullable instead array

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回物品栏中可以合理存放物品的区域的内容. 大部分情况下本方法返回的内容代表整个物品栏,
     * 但某些情况下返回的内容可能不包括盔甲或产物格子.
     * <br>
     * 这些内容会被用于add/contains/remove方法查找指定的物品堆.
     * <p>原文：
     * Return the contents from the section of the inventory where items can
     * reasonably be expected to be stored. In most cases this will represent
     * the entire inventory, but in some cases it may exclude armor or result
     * slots.
     * <br>
     * It is these contents which will be used for add / contains / remove
     * methods which look for a specific stack.
     *
     * @return 物品栏存放的物品堆. 个别条目可能为null
     * <p>原文：inventory storage contents. Individual items may be null.
     */
    public @Nullable ItemStack @NotNull [] getStorageContents(); // Paper - make array elements nullable instead array

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 放置所给的物品堆至物品栏.
     * <p>原文：
     * Put the given ItemStacks into the storage slots
     *
     * @param items 作为物品栏内容的物品堆
     * <p>原文：The ItemStacks to use as storage contents
     * @throws IllegalArgumentException 如果数组容量超越了物品栏的容量
     * <p>原文：If the array has more items than the
     * inventory.
     */
    public void setStorageContents(@Nullable ItemStack @NotNull [] items) throws IllegalArgumentException; // Paper - make array elements nullable instead array

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测这个物品栏是否含有指定的物品.
     * <p>原文：
     * Checks if the inventory contains any ItemStacks with the given
     * material.
     *
     * @param material 要检测的物品
     * <p>原文：The material to check for
     * @return 是否含有此物品
     * <p>原文：true if an ItemStack is found with the given Material
     * @throws IllegalArgumentException 如果material为null
     * <p>原文：if material is null
     */
    public boolean contains(@NotNull Material material) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测这个物品栏是否含有与给定的物品堆匹配的物品堆.
     * <p>
     * 当物品堆的种类和数量都匹配时才返回true.
     * <p>原文：
     * Checks if the inventory contains any ItemStacks matching the given
     * ItemStack.
     * <p>
     * This will only return true if both the type and the amount of the stack
     * match.
     *
     * @param item 要匹配的物品堆
     * <p>原文：The ItemStack to match against
     * @return 如果item为null返回false，如果有完全匹配的物品堆找到返回true
     * <p>原文：false if item is null, true if any exactly matching ItemStacks
     *     were found
     */
    @Contract("null -> false")
    public boolean contains(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查物品栏内是否包含指定的物品，且其数量至少满足预期值 (amount的值).
     * <p>原文：
     * Checks if the inventory contains any ItemStacks with the given
     * material, adding to at least the minimum amount specified.
     *
     * @param material 要检测的物品
     * <p>原文：The material to check for
     * @param amount 物品数量最小值
     * <p>原文：The minimum amount
     * @return 如果参数amount小于1返回true; 如果物品数量小于等于 amount 返回true
     * <p>原文：true if amount is less than 1, true if enough ItemStacks were
     *     found to add to the given amount
     * @throws IllegalArgumentException 如果material为null
     * <p>原文：if material is null
     */
    public boolean contains(@NotNull Material material, int amount) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测物品栏是否含有一定数量的完全匹配的物品堆.
     * <p>
     * 如果物品堆的种类和数量都匹配时才会计数.
     * <p>原文：
     * Checks if the inventory contains at least the minimum amount specified
     * of exactly matching ItemStacks.
     * <p>
     * An ItemStack only counts if both the type and the amount of the stack
     * match.
     *
     * @param item 要匹配的物品
     * <p>原文：the ItemStack to match against
     * @param amount 要检查多少相同的物品堆
     * <p>原文：how many identical stacks to check for
     * @return item为null返回false;amount小于1返回true;如果完全匹配物品堆指定次数返回true
     * <p>原文：false if item is null, true if amount less than 1, true if
     *     amount of exactly matching ItemStacks were found
     * @see #containsAtLeast(ItemStack, int)
     */
    @Contract("null, _ -> false")
    public boolean contains(@Nullable ItemStack item, int amount);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测物品栏是否含有与所给物品堆匹配的并数量足够的物品堆.
     * <p>原文：
     * Checks if the inventory contains ItemStacks matching the given
     * ItemStack whose amounts sum to at least the minimum amount specified.
     *
     * @param item 要匹配的物品堆
     * <p>原文：the ItemStack to match against
     * @param amount 物品堆数量最小值
     * <p>原文：the minimum amount
     * @return item为null返回false;amount小于1返回true;如果物品堆的数量小于等于参数amount返回true
     * <p>原文：false if item is null, true if amount less than 1, true if
     *     enough ItemStacks were found to add to the given amount
     */
    @Contract("null, _ -> false")
    public boolean containsAtLeast(@Nullable ItemStack item, int amount);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找在物品栏内的包含指定物品的所有格子与物品堆.
     * <p>
     * 返回的HashMap的键是格子的索引，值是在那个格子的物品堆.如果
     * 没有找到指定的物品，将返回一个空map.
     * <p>原文：
     * Returns a HashMap with all slots and ItemStacks in the inventory with
     * the given Material.
     * <p>
     * The HashMap contains entries where, the key is the slot index, and the
     * value is the ItemStack in that slot. If no matching ItemStack with the
     * given Material is found, an empty map is returned.
     *
     * @param material 要查找的物品
     * <p>原文：The material to look for
     * @return 包含格子索引和对应物品堆的map
     * <p>原文：A HashMap containing the slot index, ItemStack pairs
     * @throws IllegalArgumentException 如果material为null
     * <p>原文：if material is null
     */
    @NotNull
    public HashMap<Integer, ? extends ItemStack> all(@NotNull Material material) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找在物品栏内的包含指定物品堆的所有格子与物品堆.只有
     * 物品堆的数目和种类都相同时才会匹配.
     * <p>
     * 返回的HashMap的键是格子的索引，值是在那个格子的物品堆.如果
     * 没有找到指定的物品堆，将返回一个空map.
     * <p>原文：
     * Finds all slots in the inventory containing any ItemStacks with the
     * given ItemStack. This will only match slots if both the type and the
     * amount of the stack match
     * <p>
     * The HashMap contains entries where, the key is the slot index, and the
     * value is the ItemStack in that slot. If no matching ItemStack with the
     * given Material is found, an empty map is returned.
     *
     * @param item 要查找的物品堆
     * <p>原文：The ItemStack to match against
     * @return 包含格子索引和对应物品堆的map
     * <p>原文：A map from slot indexes to item at index
     */
    @NotNull
    public HashMap<Integer, ? extends ItemStack> all(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找包含此物品的第一个格子.
     * <p>原文：
     * Finds the first slot in the inventory containing an ItemStack with the
     * given material
     *
     * @param material 要查找的物品
     * <p>原文：The material to look for
     * @return 包含此物品的格子序号
     * <p>原文：The slot index of the given Material or -1 if not found
     * @throws IllegalArgumentException 如果material为null
     * <p>原文：if material is null
     */
    public int first(@NotNull Material material) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找包含此物品堆的第一个格子。若物品堆的种类和数量都匹配才会匹配对应格子.
     * <p>原文：
     * Returns the first slot in the inventory containing an ItemStack with
     * the given stack. This will only match a slot if both the type and the
     * amount of the stack match
     *
     * @param item 要匹配的物品堆
     * <p>原文：The ItemStack to match against
     * @return 给定物品堆所在的格子序号，如果未找到返回-1
     * <p>原文：The slot index of the given ItemStack or -1 if not found
     */
    public int first(@NotNull ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回第一个空格子的格子数.
     * <p>原文：
     * Returns the first empty Slot.
     *
     * @return 第一个空格子的格子数，-1就没有空格子
     * <p>原文：The first empty Slot found, or -1 if no empty slots.
     */
    public int firstEmpty();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测物品栏是否为空. 当物品栏里任何格子内都没有物品堆时认为此物品栏为空.
     * <p>原文：
     * Check whether or not this inventory is empty. An inventory is considered
     * to be empty if there are no ItemStacks in any slot of this inventory.
     *
     * @return 物品栏是否为空
     * <p>原文：true if empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除在物品栏内与给定物品匹配的所有物品堆.
     * <p>原文：
     * Removes all stacks in the inventory matching the given material.
     *
     * @param material 要移除的物品
     * <p>原文：The material to remove
     * @throws IllegalArgumentException 若material为null
     * <p>原文：if material is null
     */
    public void remove(@NotNull Material material) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除在此物品栏内与给定物品堆匹配的所有物品堆.
     * <p>
     * 只有物品堆的种类和数目相应匹配时对应的格子才会被匹配.
     * <p>原文：
     * Removes all stacks in the inventory matching the given stack.
     * <p>
     * This will only match a slot if both the type and the amount of the
     * stack match
     *
     * @param item 想要移除的物品堆
     * <p>原文：The ItemStack to match against
     */
    public void remove(@NotNull ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 清除单个格子.
     * <p>原文：
     * Clears out a particular slot in the index.
     *
     * @param index 格子索引
     * <p>原文：The index to empty.
     */
    public void clear(int index);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 清除整个物品栏.
     * <p>原文：
     * Clears out the whole Inventory.
     */
    public void clear();

    // Paper start
    /**
     * Closes the inventory for all viewers.
     *
     * @return the number of viewers the inventory was closed for
     */
    public int close();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得正在查看此物品栏的玩家. (以下翻译仅供参考，不保证准确性)注意:玩家被认为是在查看他们自己的物品栏
     * 和合成屏幕甚至当所说的物品栏没有打开.他们将通常被考虑为查看他们
     * 的物品栏甚至当他们打开了不同的物品栏，但对自定义物品栏来说有可能排除查看者
     * 的物品栏，所以返回的内容应该从不被呈现为非空(？？？).
     * <p>原文：
     * Gets a list of players viewing the inventory. Note that a player is
     * considered to be viewing their own inventory and internal crafting
     * screen even when said inventory is not open. They will normally be
     * considered to be viewing their inventory even when they have a
     * different inventory screen open, but it's possible for customized
     * inventory screens to exclude the viewer's inventory, so this should
     * never be assumed to be non-empty.
     *
     * @return 正在查看此物品栏的HumanEntities列表
     * <p>原文：A list of HumanEntities who are viewing this Inventory.
     */
    @NotNull
    public List<HumanEntity> getViewers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个物品栏的种类.
     * <p>原文：
     * Returns what type of inventory this is.
     *
     * @return 物品栏的种类
     * <p>原文：The InventoryType representing the type of inventory.
     */
    @NotNull
    public InventoryType getType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此物品栏的持有者(方块或实体).
     * <p>原文：
     * Gets the block or entity belonging to the open inventory
     *
     * @return 物品栏的持有者;null表示没有归属者
     * <p>原文：The holder of the inventory; null if it has no holder.
     */
    @Nullable
    public InventoryHolder getHolder();

    // Paper start - getHolder without snapshot
    /**
     * Gets the block or entity belonging to the open inventory
     *
     * @param useSnapshot Create a snapshot if the holder is a block entity
     * @return The holder of the inventory; null if it has no holder.
     */
    @Nullable
    public InventoryHolder getHolder(boolean useSnapshot);
    // Paper end

    @NotNull
    @Override
    public ListIterator<ItemStack> iterator();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回一个从指定索引开始的迭代器. 如果此索引是正数,
     * 那么第一次对next()的调用将返回处在那个索引的物品;
     * 如果索引值为负的,那么第一次对next()的调用将返回
     * 处在索引值(getSize() + index)的物品.
     * <p>原文：
     * Returns an iterator starting at the given index. If the index is
     * positive, then the first call to next() will return the item at that
     * index; if it is negative, the first call to previous will return the
     * item at index (getSize() + index).
     *
     * @param index 索引值
     * <p>原文：The index.
     * @return 迭代器
     * <p>原文：An iterator.
     */
    @NotNull
    public ListIterator<ItemStack> iterator(int index);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得对应于此物品栏的方块或实体的位置. 若此容器是被定制创建的或是一个虚拟的容器/子容器时可能返回null.
     * <p>原文：
     * Get the location of the block or entity which corresponds to this inventory. May return null if this container
     * was custom created or is a virtual / subcontainer.
     *
     * @return 对应位置，若返回null表示此属性不适用
     * <p>原文：location or null if not applicable.
     */
    @Nullable
    public Location getLocation();
}
