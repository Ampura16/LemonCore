package org.bukkit.event.inventory;

import com.google.common.collect.ImmutableSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家在物品栏中拖拽光标中的物品时调用此事件。ItemStack 将分布在 HumanEntity 拖拽过的槽位上。分布方式由 {@link #getType()} 返回的 DragType 描述。
 * <p>
 * 取消此事件将导致 {@link #getNewItems()} 中描述的更改都不会应用到物品栏。
 * <p>
 * 由于 InventoryDragEvent 发生在物品栏修改期间，并非所有与物品栏相关的方法都可以安全使用。
 * <p>
 * 以下方法绝不应该由 InventoryDragEvent 的事件处理器使用与此事件关联的 HumanEntity 或 InventoryView 来调用。
 * <ul>
 * <li>{@link HumanEntity#closeInventory()}
 * <li>{@link HumanEntity#openInventory(Inventory)}
 * <li>{@link HumanEntity#openWorkbench(Location, boolean)}
 * <li>{@link HumanEntity#openEnchanting(Location, boolean)}
 * <li>{@link InventoryView#close()}
 * </ul>
 * 要调用这些方法之一，请使用 {@link BukkitScheduler#runTask(Plugin, Runnable)} 安排任务，该任务将在下一个游戏刻运行。另请注意，此列表并非详尽无遗，其他方法也可能导致问题。
 * <p>
 * 假设与此事件关联的 EntityHuman 是 Player 的实例，则操作物品栏的最大堆叠大小或内容将需要调用 {@link Player#updateInventory()}。
 * <p>
 * 对由此 InventoryDragEvent 结果修改的槽位的任何更改都将被覆盖。要更改这些槽位，应取消此事件并应用更改。或者，使用 {@link BukkitScheduler#runTask(Plugin, Runnable)} 安排任务，该任务将在下一个游戏刻执行，也可以实现。
 * <p>原文：
 * This event is called when the player drags an item in their cursor across
 * the inventory. The ItemStack is distributed across the slots the
 * HumanEntity dragged over. The method of distribution is described by the
 * DragType returned by {@link #getType()}.
 * <p>
 * Canceling this event will result in none of the changes described in
 * {@link #getNewItems()} being applied to the Inventory.
 * <p>
 * Because InventoryDragEvent occurs within a modification of the Inventory,
 * not all Inventory related methods are safe to use.
 * <p>
 * The following should never be invoked by an EventHandler for
 * InventoryDragEvent using the HumanEntity or InventoryView associated with
 * this event.
 * <ul>
 * <li>{@link HumanEntity#closeInventory()}
 * <li>{@link HumanEntity#openInventory(Inventory)}
 * <li>{@link InventoryView#close()}
 * </ul>
 * To invoke one of these methods, schedule a task using
 * {@link BukkitScheduler#runTask(Plugin, Runnable)}, which will run the task
 * on the next tick.  Also be aware that this is not an exhaustive list, and
 * other methods could potentially create issues as well.
 * <p>
 * Assuming the EntityHuman associated with this event is an instance of a
 * Player, manipulating the MaxStackSize or contents of an Inventory will
 * require an Invocation of {@link Player#updateInventory()}.
 * <p>
 * Any modifications to slots that are modified by the results of this
 * InventoryDragEvent will be overwritten. To change these slots, this event
 * should be cancelled and the changes applied. Alternatively, scheduling a
 * task using {@link BukkitScheduler#runTask(Plugin, Runnable)}, which would
 * execute the task on the next tick, would work as well.
 */
public class InventoryDragEvent extends InventoryInteractEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final DragType type;
    private final Map<Integer, ItemStack> addedItems;
    private final Set<Integer> containerSlots;
    private final ItemStack oldCursor;
    private ItemStack newCursor;

    @ApiStatus.Internal
    public InventoryDragEvent(@NotNull InventoryView view, @Nullable ItemStack newCursor, @NotNull ItemStack oldCursor, boolean right, @NotNull Map<Integer, ItemStack> slots) {
        super(view);

        this.type = right ? DragType.SINGLE : DragType.EVEN;
        this.newCursor = newCursor;
        this.oldCursor = oldCursor;
        this.addedItems = slots;
        ImmutableSet.Builder<Integer> b = ImmutableSet.builder();
        for (Integer slot : slots.keySet()) {
            b.add(view.convertSlot(slot));
        }
        this.containerSlots = b.build();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取描述此 InventoryDragEvent 之后放置的 ItemStack 行为的 DragType。
     * <p>
     * 可以使用 {@link #getNewItems()} 找到 ItemStack 及其应用的原始槽位。
     * <p>原文：
     * Gets the DragType that describes the behavior of ItemStacks placed
     * after this InventoryDragEvent.
     * <p>
     * The ItemStacks and the raw slots that they're being applied to can be
     * found using {@link #getNewItems()}.
     *
     * @return 此 InventoryDragEvent 的 DragType
     * <p>原文：Gets the DragType that describes the behavior of ItemStacks placed
     * after this InventoryDragEvent.
     * <p>
     * The ItemStacks and the raw slots that they're being applied to can be
     * found using {@link #getNewItems()}.
     * <p>原文：the DragType of this InventoryDragEvent
     */
    @NotNull
    public DragType getType() {
        return this.type;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取拖拽完成后的结果光标。返回值是可变的。
     * <p>原文：
     * Gets the result cursor after the drag is done. The returned value is
     * mutable.
     *
     * @return 结果光标
     * <p>原文：Gets the result cursor after the drag is done. The returned value is
     * mutable.
     * <p>原文：the result cursor
     */
    @Nullable
    public ItemStack getCursor() {
        return this.newCursor;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置拖拽完成后的结果光标。
     * <p>
     * 更改此物品堆栈会更改光标物品。请注意，更改受影响的"拖拽"槽位不会更改此 ItemStack，更改此 ItemStack 也不会影响"拖拽"槽位。
     * <p>原文：
     * Sets the result cursor after the drag is done.
     * <p>
     * Changing this item stack changes the cursor item. Note that changing
     * the affected "dragged" slots does not change this ItemStack, nor does
     * changing this ItemStack affect the "dragged" slots.
     *
     * @param newCursor 新的光标 ItemStack
     * <p>原文：Sets the result cursor after the drag is done.
     * <p>
     * Changing this item stack changes the cursor item. Note that changing
     * the affected "dragged" slots does not change this ItemStack, nor does
     * changing this ItemStack affect the "dragged" slots.
     * <p>原文：the new cursor ItemStack
     */
    public void setCursor(@Nullable ItemStack newCursor) {
        this.newCursor = newCursor;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个 ItemStack，表示在此拖拽导致任何修改之前的光标。
     * <p>原文：
     * Gets an ItemStack representing the cursor prior to any modifications
     * as a result of this drag.
     *
     * @return 原始光标
     * <p>原文：Gets an ItemStack representing the cursor prior to any modifications
     * as a result of this drag.
     * <p>原文：the original cursor
     */
    @NotNull
    public ItemStack getOldCursor() {
        return this.oldCursor.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此次拖拽中将添加到物品栏的所有物品。
     * <p>原文：
     * Gets all items to be added to the inventory in this drag.
     *
     * @return 从原始槽位 ID 到新 ItemStack 的映射
     * <p>原文：Gets all items to be added to the inventory in this drag.
     * <p>原文：map from raw slot id to new ItemStack
     */
    @NotNull
    public Map<Integer, ItemStack> getNewItems() {
        return Collections.unmodifiableMap(this.addedItems);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此次拖拽中将被更改的原始槽位 ID。
     * <p>原文：
     * Gets the raw slot ids to be changed in this drag.
     *
     * @return 原始槽位 ID 列表，适用于 getView().getItem(int)
     * <p>原文：Gets the raw slot ids to be changed in this drag.
     * <p>原文：list of raw slot ids, suitable for getView().getItem(int)
     */
    @NotNull
    public Set<Integer> getRawSlots() {
        return Collections.unmodifiableSet(this.addedItems.keySet());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此次拖拽中将被更改的槽位。
     * <p>原文：
     * Gets the slots to be changed in this drag.
     *
     * @return 转换后的槽位 ID 列表，适用于 {@link
     *     org.bukkit.inventory.Inventory#getItem(int)}。
     * <p>原文：Gets the slots to be changed in this drag.
     * <p>原文：list of converted slot ids, suitable for {@link
     *     org.bukkit.inventory.Inventory#getItem(int)}.
     */
    @NotNull
    public Set<Integer> getInventorySlots() {
        return this.containerSlots;
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
