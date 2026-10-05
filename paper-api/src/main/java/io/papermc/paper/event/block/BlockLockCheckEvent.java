package io.papermc.paper.event.block;

import com.google.common.base.Preconditions;
import io.papermc.paper.block.LockableTileState;
import java.util.Objects;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在服务器尝试检查可上锁方块实体的锁时调用 <br> 要改变行为请参阅 {@link #setResult(Result)}
 * <p>原文：
 * Called when the server tries to check the lock on a lockable block entity.
 * <br>
 * See {@link #setResult(Result)} to change behavior
 */
@NullMarked
public class BlockLockCheckEvent extends BlockEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private @Nullable Component lockedMessage;
    private @Nullable Sound lockedSound;
    private @Nullable ItemStack itemStack;
    private Result result = Result.DEFAULT;

    @ApiStatus.Internal
    public BlockLockCheckEvent(final Block block, final Player player, final Component lockedMessage, final Sound lockedSound) {
        super(block);
        this.player = player;
        this.lockedMessage = lockedMessage;
        this.lockedSound = lockedSound;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在检查其锁的方块实体的快照 {@link LockableTileState}。
     * <p>原文：
     * Gets the snapshot {@link LockableTileState} of the block entity
     * whose lock is being checked.
     *
     * @return 快照方块状态。
     * <p>原文：the snapshot block state.
     */
    public LockableTileState getBlockState() {
        final BlockState blockState = this.getBlock().getState();
        Preconditions.checkState(blockState instanceof LockableTileState, "Block state of lock-checked block is no longer a lockable tile state!");
        return (LockableTileState) blockState;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取参与此次锁检查的玩家。
     * <p>原文：
     * Get the player involved this lock check.
     *
     * @return 玩家
     * <p>原文：the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将用作钥匙物品堆的物品堆。初始时为玩家主手中的物品，但可以通过 {@link #setKeyItem(ItemStack)} 设置覆盖。使用 {@link #isUsingCustomKeyItemStack()} 检查是否设置了自定义钥匙物品堆。
     * <p>原文：
     * Gets the itemstack that will be used as the key itemstack. Initially
     * this will be the item in the player's main hand but an override can be set
     * with {@link #setKeyItem(ItemStack)}. Use {@link #isUsingCustomKeyItemStack()}
     * to check if a custom key stack has been set.
     *
     * @return 用作钥匙物品的物品
     * <p>原文：the item being used as the key item
     * @see #isUsingCustomKeyItemStack()
     */
    public ItemStack getKeyItem() {
        return Objects.requireNonNullElseGet(this.itemStack, this.player.getInventory()::getItemInMainHand);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置将用作钥匙物品的物品堆。
     * <p>原文：
     * Sets the itemstack that will be used as the key item.
     *
     * @param stack 用作键的物品堆
     * <p>原文：the stack to use as a key
     * @see #resetKeyItem() to clear a custom key item
     */
    public void setKeyItem(final ItemStack stack) {
        Preconditions.checkArgument(stack != null, "stack cannot be null");
        this.itemStack = stack;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将钥匙物品堆重置为默认值（玩家的主手）。
     * <p>原文：
     * Reset the key stack to the default (the player's main hand).
     */
    public void resetKeyItem() {
        this.itemStack = null;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查是否设置了自定义键物品堆。
     * <p>原文：
     * Checks if a custom key stack has been set.
     *
     * @return 如果已设置自定义钥匙物品堆则为 {@code true}
     * <p>原文：{@code true} if a custom key itemstack has been set
     */
    public boolean isUsingCustomKeyItemStack() {
        return this.itemStack != null;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件的结果。
     * <p>原文：
     * Gets the result of this event.
     *
     * @return 结果
     * <p>原文：the result
     * @see #setResult(Result)
     */
    public Result getResult() {
        return this.result;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件的结果。{@link Result#DEFAULT} 为默认值，允许原版逻辑检查此方块的锁。设为 {@link Result#ALLOW} 或 {@link Result#DENY} 可覆盖该行为。
     * <p>
     * 设为 {@link Result#ALLOW} 会绕过旁观者检查。
     * <p>原文：
     * Gets the result of this event. {@link Result#DEFAULT} is the default
     * allowing the vanilla logic to check the lock of this block. Set to {@link Result#ALLOW}
     * or {@link Result#DENY} to override that behavior.
     * <p>
     * Setting this to {@link Result#ALLOW} bypasses the spectator check.
     *
     * @param result 此事件的结果
     * <p>原文：the result of this event
     */
    public void setResult(final Result result) {
        this.result = result;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将 {@link #getResult()} 设为 {@link Result#DENY}，并同时设置上锁消息和上锁音效的简写方法。
     * <p>原文：
     * Shorthand method to set the {@link #getResult()} to {@link Result#DENY},
     * the locked message and locked sound.
     *
     * @param lockedMessage 锁定时显示的消息（或 {@code null} 表示无）
     * <p>原文：the message to show if locked (or {@code null} for none)
     * @param lockedSound 锁定时播放的音效（或 {@code null} 表示无）
     * <p>原文：the sound to play if locked (or {@code null} for none)
     */
    public void denyWithMessageAndSound(final @Nullable Component lockedMessage, final @Nullable Sound lockedSound) {
        this.result = Result.DENY;
        this.lockedMessage = lockedMessage;
        this.lockedSound = lockedSound;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家无法打开方块时将发送的上锁消息。
     * <p>原文：
     * Gets the locked message that will be sent if the
     * player cannot open the block.
     *
     * @return 锁定消息（如果没有则为 {@code null}）
     * <p>原文：the locked message (or {@code null} if none)
     */
    public @Nullable Component getLockedMessage() {
        return this.lockedMessage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置玩家无法打开方块时将发送的上锁消息。
     * <p>原文：
     * Sets the locked message that will be sent if the
     * player cannot open the block.
     *
     * @param lockedMessage 锁定消息（或 {@code null} 表示无）
     * <p>原文：the locked message (or {@code null} for none)
     */
    public void setLockedMessage(final @Nullable Component lockedMessage) {
        this.lockedMessage = lockedMessage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家无法打开方块时将播放的上锁音效。
     * <p>原文：
     * Gets the locked sound that will play if the
     * player cannot open the block.
     *
     * @return 锁定音效（如果没有则为 {@code null}）
     * <p>原文：the locked sound (or {@code null} if none)
     */
    public @Nullable Sound getLockedSound() {
        return this.lockedSound;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置玩家无法打开方块时将播放的上锁音效。
     * <p>原文：
     * Sets the locked sound that will play if the
     * player cannot open the block.
     *
     * @param lockedSound 锁定音效（或 {@code null} 表示无）
     * <p>原文：the locked sound (or {@code null} for none)
     */
    public void setLockedSound(final @Nullable Sound lockedSound) {
        this.lockedSound = lockedSound;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
