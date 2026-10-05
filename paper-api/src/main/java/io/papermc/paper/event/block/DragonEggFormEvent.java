package io.papermc.paper.event.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockFormEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当 {@link EnderDragon} 在 {@link DragonBattle} 中被击败（击杀）时调用，这可能会根据 {@link #isCancelled()} 导致出现 {@link Material#DRAGON_EGG}（更正式地说：{@link #getNewState()}）。
 * <p>
 * <b>根据 {@link DragonBattle#hasBeenPreviouslyKilled()} 和服务器配置等因素，该事件默认可能处于取消状态。</b>
 * <p>原文：
 * Called when the {@link EnderDragon} is defeated (killed) in a {@link DragonBattle},
 * causing a {@link Material#DRAGON_EGG} (more formally: {@link #getNewState()})
 * to possibly appear depending on {@link #isCancelled()}.
 * <p>
 * <b>This event might be cancelled by default depending on
 * e.g. {@link DragonBattle#hasBeenPreviouslyKilled()} and server configuration.</b>
 */
@NullMarked
public class DragonEggFormEvent extends BlockFormEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final DragonBattle dragonBattle;

    @ApiStatus.Internal
    public DragonEggFormEvent(final Block block, final BlockState newState, final DragonBattle dragonBattle) {
        super(block, newState);
        this.dragonBattle = dragonBattle;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此事件关联的 {@link DragonBattle}。请注意，调用此事件时 {@link EnderDragon} 已经死亡。
     * <p>原文：
     * Gets the {@link DragonBattle} associated with this event.
     * Keep in mind that the {@link EnderDragon} is already dead
     * when this event is called.
     *
     * @return 龙战
     * <p>原文：the dragon battle
     */
    public DragonBattle getDragonBattle() {
        return this.dragonBattle;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
