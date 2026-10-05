package com.destroystokyo.paper.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockExpEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 每当服务器因某种触发原因打算“破坏”方块时触发。并非每次方块被设为空气时都会触发，而仅在更直接的触发下触发，例如物理更新、活塞、实体改变方块、设置为“Destroy”的命令等。
 * <p>
 * 该事件与游戏在相应方块处播放音效相关联，适用于可以描述为“打算破坏该处方块”的情形。
 * <p>
 * 树叶腐烂、活塞收回（方块被移动）等情况不会触发该事件。
 * <p>原文：
 * Fired anytime the server intends to 'destroy' a block through some triggering reason.
 * This does not fire anytime a block is set to air, but only with more direct triggers such
 * as physics updates, pistons, entities changing blocks, commands set to "Destroy".
 * <p>
 * This event is associated with the game playing a sound effect at the block in question, when
 * something can be described as "intend to destroy what is there",
 * <p>
 * Events such as leaves decaying, pistons retracting (where the block is moving), does NOT fire this event.
 */
@NullMarked
public class BlockDestroyEvent extends BlockExpEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final BlockData newState;
    private boolean willDrop;
    private boolean playEffect = true;
    private BlockData effectBlock;

    private boolean cancelled;

    @ApiStatus.Internal
    public BlockDestroyEvent(final Block block, final BlockData newState, final BlockData effectBlock, final int xp, final boolean willDrop) {
        super(block, xp);
        this.newState = newState;
        this.effectBlock = effectBlock;
        this.willDrop = willDrop;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取方块被破坏时将播放的效果。
     * <p>原文：
     * Gets the effect that will be played when the block is broken.
     *
     * @return 方块破坏效果
     * <p>原文：block break effect
     */
    public BlockData getEffectBlock() {
        return this.effectBlock;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置方块被破坏时将播放的效果。注意：{@link BlockDestroyEvent#playEffect()} 必须为 {@code true} 才会播放此效果。
     * <p>原文：
     * Sets the effect that will be played when the block is broken.
     * Note: {@link BlockDestroyEvent#playEffect()} must be {@code true} in order for this effect to be
     * played.
     *
     * @param effectBlock 方块效果
     * <p>原文：block effect
     */
    public void setEffectBlock(final BlockData effectBlock) {
        this.effectBlock = effectBlock.clone();
    }

    /**
     * @return 此方块的新状态（空气或某种流体）
     * <p>原文：The new state of this block (Air, or a Fluid type)
     */
    public BlockData getNewState() {
        return this.newState.clone();
    }

    /**
     * @return 服务器是否会在此破坏事件中掉落相应方块
     * <p>原文：If the server is going to drop the block in question with this destroy event
     */
    public boolean willDrop() {
        return this.willDrop;
    }

    /**
     * @param willDrop 服务器是否会随此破坏事件掉落该方块
     * <p>原文：If the server is going to drop the block in question with this destroy event
     */
    public void setWillDrop(final boolean willDrop) {
        this.willDrop = willDrop;
    }

    /**
     * @return 服务器是否会为此次破坏播放音效
     * <p>原文：If the server is going to play the sound effect for this destruction
     */
    public boolean playEffect() {
        return this.playEffect;
    }

    /**
     * @param playEffect 服务器是否应为此次破坏播放音效
     * <p>原文：If the server should play the sound effect for this destruction
     */
    public void setPlayEffect(final boolean playEffect) {
        this.playEffect = playEffect;
    }

    /**
     * @return 事件是否被取消，即方块将不会被破坏
     * <p>原文：If the event is cancelled, meaning the block will not be destroyed
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果事件被取消，方块将保持其先前的状态。
     * <p>原文：
     * If the event is cancelled, the block will remain in its previous state.
     */
    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
