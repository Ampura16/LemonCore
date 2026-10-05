package org.bukkit.event.player;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家对一个对象或空气进行交互时触发本事件.
 * <p>
 * 无论玩家使用的是左手还是右手, 都有可能分别触发本事件. 你可以使用 {@link #getHand()} 方法来确定玩家使用的是哪只手.
 * <p>
 * 如果是原版行为触发了本事件, 则本事件将会被取消(例如与空气进行交互). 为避免歧义, 这意味着事件仅在因服务器做出的预测而触发且后续代码不会运行时才会处于取消状态, 而非在后续交互活动(例如在非法位置放置方块({@link BlockCanBuildEvent}))将会失败时.
 * <p>原文：
 * Represents an event that is called when a player interacts with an object or
 * air, potentially fired once for each hand. The hand can be determined using
 * {@link #getHand()}.
 * <p>
 * This event will fire as cancelled if the vanilla behavior is to do nothing
 * (e.g. interacting with air). For the purpose of avoiding doubt, this means
 * that the event will only be in the cancelled state if it is fired as a result
 * of some prediction made by the server where no subsequent code will run,
 * rather than when the subsequent interaction activity (e.g. placing a block in
 * an illegal position ({@link BlockCanBuildEvent}) will fail).
 */
public class PlayerInteractEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Action action;
    private final ItemStack item;
    private final Block blockClicked;
    private final BlockFace blockFace;
    private final EquipmentSlot hand;
    private final Vector clickedPosition;
    private Result useItemInHand;
    private Result useClickedBlock;

    @ApiStatus.Internal
    public PlayerInteractEvent(@NotNull final Player player, @NotNull final Action action, @Nullable final ItemStack item, @Nullable final Block clickedBlock, @NotNull final BlockFace clickedFace) {
        this(player, action, item, clickedBlock, clickedFace, EquipmentSlot.HAND);
    }

    @ApiStatus.Internal
    public PlayerInteractEvent(@NotNull final Player player, @NotNull final Action action, @Nullable final ItemStack item, @Nullable final Block clickedBlock, @NotNull final BlockFace clickedFace, @Nullable final EquipmentSlot hand) {
        this(player, action, item, clickedBlock, clickedFace, hand, null);
    }

    @ApiStatus.Internal
    public PlayerInteractEvent(@NotNull final Player player, @NotNull final Action action, @Nullable final ItemStack item, @Nullable final Block clickedBlock, @NotNull final BlockFace clickedFace, @Nullable final EquipmentSlot hand, @Nullable final Vector clickedPosition) {
        super(player);
        this.action = action;
        this.item = item;
        this.blockClicked = clickedBlock;
        this.blockFace = clickedFace;
        this.hand = hand;
        this.clickedPosition = clickedPosition;

        this.useItemInHand = Result.DEFAULT;
        this.useClickedBlock = clickedBlock == null ? Result.DENY : Result.ALLOW;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本次交互的动作类型.
     * <p>原文：
     * Returns the action type
     *
     * @return Action 返回交互的动作类型
     * <p>原文：Action returns the type of interaction
     */
    @NotNull
    public Action getAction() {
        return this.action;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个事件是否被取消.
     * 如果你想防止通过水桶放水等操作, 则可以取消这个事件.
     * <p>原文：
     * Gets the cancellation state of this event. Set to {@code true} if you want to
     * prevent buckets from placing water and so forth
     *
     * @return boolean 取消状态
     * <p>原文：boolean cancellation state
     * @deprecated This event has two possible cancellation states, one for
     * {@link #useInteractedBlock()} and one for {@link #useItemInHand()}. It is
     * possible a call might have the former false, but the latter {@code true}, e.g. in
     * the case of using a firework whilst gliding. Callers should check the
     * relevant methods individually.
     */
    @Deprecated(since = "1.14")
    @Override
    public boolean isCancelled() {
        return this.useInteractedBlock() == Result.DENY;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 取消这个事件. 一个被取消的事件不会在
     * 服务器里被执行，但是仍然会传递事件到其他插件.
     * <p>
     * 取消本事件将阻止玩家食用食物(玩家不会失去食物), 阻止弓箭/雪球/蛋的发射等(玩家不会失去弹药).
     * <p>原文：
     * {@inheritDoc}
     * <p>
     * Canceling this event will prevent use of food (player won't lose the
     * food item), prevent bows/snowballs/eggs from firing, etc. (player won't
     * lose the ammo)
     *
     * @param cancel 设置为 true 时将会阻止本事件触发
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.setUseInteractedBlock(cancel ? Result.DENY : this.useInteractedBlock() == Result.DENY ? Result.DEFAULT : this.useInteractedBlock());
        this.setUseItemInHand(cancel ? Result.DENY : this.useItemInHand() == Result.DENY ? Result.DEFAULT : this.useItemInHand());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家手中的物品, 手中未拿物品时会返回null.
     * <p>原文：
     * Returns the item in hand represented by this event
     *
     * @return ItemStack 使用的物品
     * <p>原文：ItemStack the item used
     */
    @Nullable
    public ItemStack getItem() {
        return this.item;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回玩家所用物品的材质 (相当于getItem()后再getMateria(),但更简便一些).
     * <p>原文：
     * Convenience method. Returns the material of the item represented by
     * this event
     *
     * @return Material 所用物品的材质
     * <p>原文：Material the material of the item used
     */
    @NotNull
    public Material getMaterial() {
        if (!this.hasItem()) {
            return Material.AIR;
        }

        return this.item.getType();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查交互事件是否涉及方块(与方块交互、放置方块等).
     * <p>原文：
     * Check if this event involved a block
     *
     * @return boolean 如果涉及到则返回true
     * <p>原文：boolean {@code true} if it did
     */
    public boolean hasBlock() {
        return this.blockClicked != null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查玩家交互时是否手拿物品或与物品交互/交互是否涉及到物品.
     * <p>
     * 猜测(译注):只要不是与空气交互一般都会返回一个物品?等待验证.
     * <p>原文：
     * Check if this event involved an item
     *
     * @return boolean 如果涉及到则返回true
     * <p>原文：boolean {@code true} if it did
     */
    public boolean hasItem() {
        return this.item != null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断本次交互事件是否为方块放置事件 (由玩家放置方块引起的交互事件).
     * <p>原文：
     * Convenience method to inform the user whether this was a block
     * placement event.
     *
     * @return boolean 如果玩家交互时手拿方块,返回true
     * <p>原文：boolean {@code true} if the item in hand was a block
     */
    public boolean isBlockInHand() {
        if (!this.hasItem()) {
            return false;
        }

        return this.item.getType().isBlock();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回被交互的方块, 即使交互方式为 {@link Action#PHYSICAL} 而非点击方块.
     * <p>原文：
     * Returns the clicked block
     *
     * @return Block 被交互的方块
     * <p>原文：Block returns the block clicked with this item.
     */
    @Nullable
    public Block getClickedBlock() {
        return this.blockClicked;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回被交互的方块的朝向.
     * <p>原文：
     * Returns the face of the block that was clicked
     *
     * @return BlockFace 被交互的方块的朝向
     * <p>原文：BlockFace returns the face of the block that was clicked
     */
    @NotNull
    public BlockFace getBlockFace() {
        return this.blockFace;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用来进行本次交互的手, 但当玩家的交互动作类型为 {@link Action#PHYSICAL} 时可能为空.
     * <p>原文：
     * The hand used to perform this interaction. May be {@code null} in the case of
     * {@link Action#PHYSICAL}.
     *
     * @return 用来交互的手, 可能为null
     * <p>原文：the hand used to interact. May be {@code null}.
     */
    @Nullable
    public EquipmentSlot getHand() {
        return this.hand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家交互方块时的精确位置, 在 {@link Action#RIGHT_CLICK_BLOCK} 之外的情况下为 null.
     * <p>原文：
     * Gets the exact position on the block the player interacted with, this will
     * be {@code null} outside of {@link Action#RIGHT_CLICK_BLOCK}.
     * <p>
     * All vector components are between 0.0 and 1.0 inclusive.
     *
     * @deprecated misleading, use {@link #getInteractionPoint()}
     * @return 被点击的位置. 可能为 null.
     * <p>原文：the clicked position. May be {@code null}.
     */
    @Nullable
    @Deprecated
    public Vector getClickedPosition() {
        if (this.clickedPosition == null) {
            return null;
        }
        return this.clickedPosition.clone();
    }

    /**
     * The exact point at which the interaction occurred. May be {@code null}.
     *
     * @return the exact interaction point. May be {@code null}.
     */
    @Nullable
    public Location getInteractionPoint() {
        if (this.blockClicked == null || this.clickedPosition == null) {
            return null;
        }
        return this.blockClicked.getLocation().add(this.clickedPosition);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取(控制见对应set方法)对被交互的方块采取的动作(如果有).
     * 任意方块都能触发本事件, 不过大部分情况下都没有默认动作.
     * <p>原文：
     * This controls the action to take with the block (if any) that was
     * clicked on. This event gets processed for all blocks, but most don't
     * have a default action
     *
     * @return 对被交互的方块采取的动作
     * <p>原文：the action to take with the interacted block
     */
    @NotNull
    public Result useInteractedBlock() {
        return this.useClickedBlock;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param useInteractedBlock 对被交互的方块采取的动作
     * <p>原文：the action to take with the interacted block
     */
    public void setUseInteractedBlock(@NotNull Result useInteractedBlock) {
        this.useClickedBlock = useInteractedBlock;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取(控制见下一个方法)对玩家手持的物品采取的动作/行动.
     * 同时包含方块和物品(如打火石、铁锭或唱片).
     * 当其动作被设为默认值, 如果未对玩家所交互的方块采取行动, 此交互动作将被允许.
     * <p>原文：
     * This controls the action to take with the item the player is holding.
     * This includes both blocks and items (such as flint and steel or
     * records). When this is set to default, it will be allowed if no action
     * is taken on the interacted block.
     *
     * @return 对玩家手持的物品采取的动作(the action to take with the item in hand)
     * <p>原文：the action to take with the item in hand
     */
    @NotNull
    public Result useItemInHand() {
        return this.useItemInHand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param useItemInHand 对玩家手持的物品采取的动作(the action to take with the item in hand)
     * <p>原文：the action to take with the item in hand
     */
    public void setUseItemInHand(@NotNull Result useItemInHand) {
        this.useItemInHand = useItemInHand;
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
