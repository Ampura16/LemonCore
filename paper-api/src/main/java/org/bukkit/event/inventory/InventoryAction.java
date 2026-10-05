package org.bukkit.event.inventory;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 用于判断点击物品栏的动作结果.
 * <p>原文：
 * An estimation of what the result will be.
 */
public enum InventoryAction {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 这一次点击什么都不会发生.
     * <p>
     * There may be cases where nothing will happen and this value is not
     * provided, but it is guaranteed that this value is accurate when given.
     * <p>原文：
     * Nothing will happen from the click.
     * <p>
     * There may be cases where nothing will happen and this is value is not
     * provided, but it is guaranteed that this value is accurate when given.
     */
    NOTHING,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 所单击的格子的物品(<b>所有</b>)被移到光标上.
     * <p>原文：
     * All of the items on the clicked slot are moved to the cursor.
     */
    PICKUP_ALL,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 所单击的格子的物品(<b>部分</b>)被移到光标上.
     * <p>原文：
     * Some of the items on the clicked slot are moved to the cursor.
     */
    PICKUP_SOME,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 所单击的格子的物品(<b>一半</b>)被移到光标上.
     * <p>原文：
     * Half of the items on the clicked slot are moved to the cursor.
     */
    PICKUP_HALF,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 所单击的格子的物品(<b>一个</b>)被移到光标上.
     * <p>原文：
     * One of the items on the clicked slot are moved to the cursor.
     */
    PICKUP_ONE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 光标上的物品(<b>所有</b>)被移到所单击的格子中.
     * <p>原文：
     * All of the items on the cursor are moved to the clicked slot.
     */
    PLACE_ALL,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 光标上的物品(<b>部分</b>)被移到所单击的格子中(通常会提高到该物品的最大堆叠数量).
     * <p>原文：
     * Some of the items from the cursor are moved to the clicked slot
     * (usually up to the max stack size).
     */
    PLACE_SOME,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 光标上的物品(<b>一个</b>)被移到所单击的格子中.
     * <p>原文：
     * A single item from the cursor is moved to the clicked slot.
     */
    PLACE_ONE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 光标上的物品与所单击的格子中的物品相互交换.
     * <p>原文：
     * The clicked item and the cursor are exchanged.
     */
    SWAP_WITH_CURSOR,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 丢弃光标上的物品(<b>所有</b>).
     * <p>原文：
     * The entire cursor item is dropped.
     */
    DROP_ALL_CURSOR,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 丢弃光标上的物品(<b>一个</b>).
     * <p>原文：
     * One item is dropped from the cursor.
     */
    DROP_ONE_CURSOR,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 丢弃所单击的格子中的物品(<b>所有</b>).
     * <p>原文：
     * The entire clicked slot is dropped.
     */
    DROP_ALL_SLOT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 丢弃所单击的格子中的物品(<b>一个</b>).
     * <p>原文：
     * One item is dropped from the clicked slot.
     */
    DROP_ONE_SLOT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将所单击的格子中的物品移动到对面的物品栏中去(如果有空位).
     * <p>原文：
     * The item is moved to the opposite inventory if a space is found.
     */
    MOVE_TO_OTHER_INVENTORY,
    /**
     * The clicked item is moved to the hotbar, and the item currently there
     * is re-added to the player's inventory.
     *
     * The hotbar includes the player's off hand.
     * @deprecated This action no longer happens, they are all {@link #HOTBAR_SWAP}s.
     */
    @Deprecated(since = "1.20.6", forRemoval = true) // Paper
    HOTBAR_MOVE_AND_READD,
    /**
     * The clicked slot and the picked hotbar slot are swapped.
     *
     * The hotbar includes the player's off hand.
     */
    HOTBAR_SWAP,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将所单击的物品按最大堆叠数量复制到光标上.
     * <p>原文：
     * A max-size stack of the clicked item is put on the cursor.
     */
    CLONE_STACK,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在物品栏中寻找完全相同的物品，并添加到光标上，直到达到{@link org.bukkit.Material#getMaxStackSize() 物品的最大堆叠数量}.
     * <p>原文：
     * The inventory is searched for the same material, and they are put on
     * the cursor up to {@link org.bukkit.Material#getMaxStackSize()}.
     */
    COLLECT_TO_CURSOR,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 无法识别的{@link ClickType 点击类型}.
     * <p>原文：
     * An unrecognized ClickType.
     */
    UNKNOWN,
    /**
     * The first stack of items in the clicked bundle is moved to the cursor.
     */
    PICKUP_FROM_BUNDLE,
    /**
     * All of the items on the clicked slot are moved into the bundle on the cursor.
     */
    PICKUP_ALL_INTO_BUNDLE,
    /**
     * Some of the items on the clicked slot are moved into the bundle on the cursor.
     */
    PICKUP_SOME_INTO_BUNDLE,
    /**
     * The first stack of items is moved to the clicked slot.
     */
    PLACE_FROM_BUNDLE,
    /**
     * All of the items on the cursor are moved into the bundle in the clicked slot.
     */
    PLACE_ALL_INTO_BUNDLE,
    /**
     * Some of the items on the cursor are moved into the bundle in the clicked slot.
     */
    PLACE_SOME_INTO_BUNDLE,
    ;
}
