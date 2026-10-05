package org.bukkit.event.inventory;

import org.bukkit.Location;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryType.SlotType;
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
 * 当玩家点击物品栏中的格子时触发事件事件.<br>
 * 译注：事实上打开任意容器的物品栏(包括玩家的背包)后点击任何一个地方都会触发这个事件。
 * <p>
 * 由于{@link InventoryClickEvent}是通过修改{@link org.bukkit.inventory.Inventory 物品栏}
 * 的实现类来触发的，所以并非所有与{@link org.bukkit.inventory.Inventory 物品栏}相关的方法都是安全的。
 * <p>
 * 下面这些属于{@link HumanEntity}和 {@link InventoryView}的方法不应该被处理
 * {@link InventoryClickEvent}事件的 {@link org.bukkit.event.EventHandler 事件处理器}调用
 * <ul>
 * <li>{@link HumanEntity#closeInventory()}
 * <li>{@link HumanEntity#openInventory(Inventory)}
 * <li>{@link HumanEntity#openWorkbench(Location, boolean)}
 * <li>{@link HumanEntity#openEnchanting(Location, boolean)}
 * <li>{@link InventoryView#close()}
 * </ul>
 * 如果一定要调用这些方法，请使用 {@link BukkitScheduler#runTask(Plugin, Runnable)}来执行
 * ，这个方法将在下一个tick执行你的任务。<br>
 * 注意：上面列出来的这些方法并不全，可能还有其它的方法也存在问题。
 * <p>
 * 假设与此事件关联的 EntityHuman 是一个 Player 实例，那么操作物品栏的最大堆叠数量或内容
 * 将需要调用 {@link Player#updateInventory()}。
 * <p>
 * 对此 InventoryClickEvent 结果所修改的格子的更改可能会被覆盖。要更改这些格子，
 * 应取消此事件并应用所有对物品栏的期望更改。
 * 或者，使用 {@link BukkitScheduler#runTask(Plugin, Runnable)} 调度一个任务
 * 也可以实现，该方法将在下一个 tick 执行你的任务。
 * <p>原文：
 * This event is called when a player clicks in an inventory.
 * <p>
 * In case of a drag action within an inventory, InventoryClickEvent is never called.
 * Instead, {@link InventoryDragEvent} is called at the end of the drag.
 * <p>
 * Because InventoryClickEvent occurs within a modification of the Inventory,
 * not all Inventory related methods are safe to use.
 * <p>
 * Methods that change the view a player is looking at should never be invoked
 * by an EventHandler for InventoryClickEvent using the HumanEntity or
 * InventoryView associated with this event.
 * Examples of these include:
 * <ul>
 * <li>{@link HumanEntity#closeInventory()}
 * <li>{@link HumanEntity#openInventory(Inventory)}
 * <li>{@link InventoryView#close()}
 * </ul>
 * To invoke one of these methods, schedule a task using
 * {@link BukkitScheduler#runTask(Plugin, Runnable)}, which will run the task
 * on the next tick. Also be aware that this is not an exhaustive list, and
 * other methods could potentially create issues as well.
 * <p>
 * Assuming the EntityHuman associated with this event is an instance of a
 * Player, manipulating the MaxStackSize or contents of an Inventory will
 * require an Invocation of {@link Player#updateInventory()}.
 * <p>
 * Modifications to slots that are modified by the results of this
 * InventoryClickEvent can be overwritten. To change these slots, this event
 * should be cancelled and all desired changes to the inventory applied.
 * Alternatively, scheduling a task using {@link BukkitScheduler#runTask(
 * Plugin, Runnable)}, which would execute the task on the next tick, would
 * work as well.
 */
public class InventoryClickEvent extends InventoryInteractEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ClickType click;
    private final InventoryAction action;
    private final SlotType slotType;
    private final int whichSlot;
    private final int rawSlot;
    private ItemStack current = null;
    private int hotbarKey = -1;

    @ApiStatus.Internal
    public InventoryClickEvent(@NotNull InventoryView view, @NotNull SlotType type, int slot, @NotNull ClickType click, @NotNull InventoryAction action) {
        super(view);
        this.slotType = type;
        this.rawSlot = slot;
        this.whichSlot = view.convertSlot(slot);
        this.click = click;
        this.action = action;
    }

    @ApiStatus.Internal
    public InventoryClickEvent(@NotNull InventoryView view, @NotNull SlotType type, int slot, @NotNull ClickType click, @NotNull InventoryAction action, int key) {
        this(view, type, slot, click, action);
        this.hotbarKey = key;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被点击的格子的类型.
     * <p>原文：
     * Gets the type of slot that was clicked.
     *
     * @return 格子类型
     * <p>原文：the slot type
     */
    @NotNull
    public SlotType getSlotType() {
        return this.slotType;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被光标所拿起来的物品
     * <p>原文：
     * Gets the current ItemStack on the cursor.
     *
     * @return 被光标所拿起来的物品
     * <p>原文：the cursor ItemStack
     */
    @NotNull
    public ItemStack getCursor() {
        return this.getView().getCursor();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被点击的格子的物品
     * <p>原文：
     * Gets the ItemStack currently in the clicked slot.
     *
     * @return 被点击的物品
     * <p>原文：the item in the clicked slot
     */
    @Nullable
    public ItemStack getCurrentItem() {
        if (this.slotType == SlotType.OUTSIDE) {
            return this.current;
        }
        return this.getView().getItem(this.rawSlot);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否右击背包
     * <p>原文：
     * Gets whether the ClickType for this event represents a right
     * click.
     *
     * @return 是否右击背包
     * <p>原文：{@code true} if the ClickType uses the right mouse button.
     * @see ClickType#isRightClick()
     */
    public boolean isRightClick() {
        return this.click.isRightClick();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否左击背包
     * <p>原文：
     * Gets whether the ClickType for this event represents a left
     * click.
     *
     * @return 是否左击背包
     * <p>原文：{@code true} if the ClickType uses the left mouse button.
     * @see ClickType#isLeftClick()
     */
    public boolean isLeftClick() {
        return this.click.isLeftClick();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否按住shift点击背包
     * <p>原文：
     * Gets whether the ClickType for this event indicates that the key was
     * pressed down when the click was made.
     *
     * @return 是否使用Shift或Ctrl按键
     * <p>原文：{@code true} if the ClickType uses Shift or Ctrl.
     * @see ClickType#isShiftClick()
     */
    public boolean isShiftClick() {
        return this.click.isShiftClick();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置光标上的物品.
     * <p>原文：
     * Sets the item on the cursor.
     *
     * @param stack 新的光标物品
     * <p>原文：the new cursor item
     * @deprecated 这会在对物品栏应用任何计算之前更改他们手中的 ItemStack，
     *             这往往会导致玩家与服务器之间的不一致，
     *             并对被点击的物品栏的行为产生意外更改。
     * <p>原文：This changes the ItemStack in their hand before any
     *     calculations are applied to the Inventory, which has a tendency to
     *     create inconsistencies between the Player and the server, and to
     *     make unexpected changes in the behavior of the clicked Inventory.
     */
    @Deprecated(since = "1.5.2")
    public void setCursor(@Nullable ItemStack stack) {
        this.getView().setCursor(stack);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置被点击格子中的物品.
     * <p>原文：
     * Sets the ItemStack currently in the clicked slot.
     *
     * @param stack 要放入当前格子的物品
     * <p>原文：the item to be placed in the current slot
     */
    public void setCurrentItem(@Nullable ItemStack stack) {
        if (this.slotType == SlotType.OUTSIDE) {
            this.current = stack;
        } else {
            getView().setItem(this.rawSlot, stack);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被点击格子对应的物品栏.
     * <p>原文：
     * Gets the inventory corresponding to the clicked slot.
     *
     * @return 物品栏，如果点击了外部则为 null
     * <p>原文：inventory, or {@code null} if clicked outside
     * @see InventoryView#getInventory(int)
     */
    @Nullable
    public Inventory getClickedInventory() {
        return this.getView().getInventory(rawSlot);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回点击的格子序号，可传递给{@link Inventory#getItem(int)}。注意由于连接两个不同背包的视图，可能两个格子都是同一个格子序号
     * <p>原文：
     * The slot number that was clicked, ready for passing to
     * {@link Inventory#getItem(int)}. Note that there may be two slots with
     * the same slot number, since a view links two different inventories.
     *
     * @return 格子序号
     * <p>原文：the slot number
     */
    public int getSlot() {
        return this.whichSlot;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回点击的原始格子序号，可传递给{@link InventoryView #getItem(int)}。这个序号对每个视图是唯一的。
     * <p>原文：
     * The raw slot number clicked, ready for passing to {@link InventoryView
     * #getItem(int)} This slot number is unique for the view.
     *
     * @return 格子序号
     * <p>原文：the slot number
     */
    public int getRawSlot() {
        return this.rawSlot;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果ClickType是NUMVER_KEY（按下数字切换物品），这个方法将返回按下的键的索引（0-8）
     * <p>原文：
     * If the ClickType is NUMBER_KEY, this method will return the index of
     * the pressed key (0-8) and -1 if player swapped with off-hand (or the action is not NUMBER_KEY).
     *
     * @return 按下的数字减1（范围0-8），如果不是NUMBER_KEY动作为-1
     * <p>原文：the number on the key minus 1 (range 0-8);
     * or -1 if ClickType is NUMBER_KEY and player did an off-hand swap. Is also -1 if ClickType is not NUMBER_KEY
     */
    public int getHotbarButton() {
        return this.hotbarKey;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取触发此事件的 InventoryAction.
     * <p>
     * 此操作无法更改，它代表此事件的正常结果。
     * 要更改此 InventoryClickEvent 的行为，必须手动应用更改。
     * <p>原文：
     * Gets the InventoryAction that triggered this event.
     * <p>
     * This action cannot be changed, and represents what the normal outcome
     * of the event will be. To change the behavior of this
     * InventoryClickEvent, changes must be manually applied.
     *
     * @return 触发此事件的 InventoryAction
     * <p>原文：the InventoryAction that triggered this event.
     */
    @NotNull
    public InventoryAction getAction() {
        return this.action;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本事件的ClickType.
     * <p>
     * 获取到的结果与其它插件对其的改动相隔离.
     * <p>原文：
     * Gets the ClickType for this event.
     * <p>
     * This is insulated against changes to the inventory by other plugins.
     *
     * @return 背包点击类型
     * <p>原文：the type of inventory click
     */
    @NotNull
    public ClickType getClick() {
        return this.click;
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
