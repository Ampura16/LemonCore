package org.bukkit.event.entity;

import java.util.List;
import org.bukkit.PortalType;
import org.bukkit.block.BlockState;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.PortalCreateEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个{@link LivingEntity}在世界中创建传送门时触发该事件.
 * <p>原文：
 * Thrown when a Living Entity creates a portal in a world.
 *
 * @deprecated 请使用 {@link PortalCreateEvent}
 * <p>原文：Use {@link PortalCreateEvent}
 */
@Deprecated(since = "1.14.1")
public class EntityCreatePortalEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final List<BlockState> blocks;
    private final PortalType type;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityCreatePortalEvent(@NotNull final LivingEntity livingEntity, @NotNull final List<BlockState> blocks, @NotNull final PortalType type) {
        super(livingEntity);

        this.blocks = blocks;
        this.type = type;
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回与这个传送门有关的所有方块.
     * <p>原文：
     * Gets a list of all blocks associated with the portal.
     *
     * @return 将被改变的方块列表.
     * <p>原文：List of blocks that will be changed.
     */
    @NotNull
    public List<BlockState> getBlocks() {
        return this.blocks;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回正在尝试创建的传送门类型.
     * <p>原文：
     * Gets the type of portal that is trying to be created.
     *
     * @return 传送门类型
     * <p>原文：Type of portal.
     */
    @NotNull
    public PortalType getPortalType() {
        return this.type;
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
