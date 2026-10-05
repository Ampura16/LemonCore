package org.bukkit.event.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当末影龙切换控制器阶段时调用。
 * <p>原文：
 * Called when an EnderDragon switches controller phase.
 */
public class EnderDragonChangePhaseEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final EnderDragon.Phase currentPhase;
    private EnderDragon.Phase newPhase;

    private boolean cancelled;

    @ApiStatus.Internal
    public EnderDragonChangePhaseEvent(@NotNull EnderDragon enderDragon, @Nullable EnderDragon.Phase currentPhase, @NotNull EnderDragon.Phase newPhase) {
        super(enderDragon);
        this.currentPhase = currentPhase;
        this.newPhase = newPhase;
    }

    @NotNull
    @Override
    public EnderDragon getEntity() {
        return (EnderDragon) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取龙当前所处的阶段。当龙首次生成且尚未分配阶段时，此方法将返回 null。
     * <p>原文：
     * Gets the current phase that the dragon is in. This method will return null
     * when a dragon is first spawned and hasn't yet been assigned a phase.
     *
     * @return 当前龙阶段
     * <p>原文：the current dragon phase
     */
    @Nullable
    public EnderDragon.Phase getCurrentPhase() {
        return this.currentPhase;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取龙将要切换到的新阶段。
     * <p>原文：
     * Gets the new phase that the dragon will switch to.
     *
     * @return 新龙阶段
     * <p>原文：the new dragon phase
     */
    @NotNull
    public EnderDragon.Phase getNewPhase() {
        return this.newPhase;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置末影龙的新阶段。
     * <p>原文：
     * Sets the new phase for the ender dragon.
     *
     * @param newPhase 新龙阶段
     * <p>原文：the new dragon phase
     */
    public void setNewPhase(@NotNull EnderDragon.Phase newPhase) {
        Preconditions.checkArgument(newPhase != null, "New dragon phase cannot be null");
        this.newPhase = newPhase;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
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
