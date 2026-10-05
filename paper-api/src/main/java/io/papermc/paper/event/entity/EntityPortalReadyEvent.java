package io.papermc.paper.event.entity;

import org.bukkit.PortalType;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当实体准备好被插件传送时调用。目前仅在下界传送门所需的刻数经过后调用。
 * <p>
 * 取消该事件会重置实体关于当前传送门的就绪状态。
 * <p>原文：
 * Called when an entity is ready to be teleported by a plugin.
 * Currently, this is only called after the required
 * ticks have passed for a Nether Portal.
 * <p>
 * Cancelling this event resets the entity's readiness
 * regarding the current portal.
 */
@NullMarked
public class EntityPortalReadyEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PortalType portalType;
    private @Nullable World targetWorld;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityPortalReadyEvent(final Entity entity, final @Nullable World targetWorld, final PortalType portalType) {
        super(entity);
        this.targetWorld = targetWorld;
        this.portalType = portalType;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此传送门将传送到的世界。如果 server.properties 中“allow-nether”为 false，或存在其他没有可传送世界的情况，则可能为 {@code null}。
     * <p>
     * 该世界可能被之后的事件（如 {@link PlayerPortalEvent} 或 {@link EntityPortalEvent}）修改。
     * <p>原文：
     * Gets the world this portal will teleport to.
     * Can be {@code null} if "allow-nether" is false in server.properties
     * or if there is another situation where there is no world to teleport to.
     * <p>
     * This world may be modified by later events such as {@link PlayerPortalEvent}
     * or {@link EntityPortalEvent}.
     *
     * @return 传送门将把实体传送到的世界。
     * <p>原文：the world the portal will teleport the entity to.
     */
    public @Nullable World getTargetWorld() {
        return this.targetWorld;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此传送门将传送到的世界。设为 {@code null} 基本上会取消传送，并阻止触发 {@link PlayerPortalEvent} 等后续事件。
     * <p>
     * 该世界可能被之后的事件（如 {@link PlayerPortalEvent} 或 {@link EntityPortalEvent}）修改。
     * <p>原文：
     * Sets the world this portal will teleport to. A {@code null} value
     * will essentially cancel the teleport and prevent further events
     * such as {@link PlayerPortalEvent} from firing.
     * <p>
     * This world may be modified by later events such as {@link PlayerPortalEvent}
     * or {@link EntityPortalEvent}.
     *
     * @param targetWorld 世界
     * <p>原文：the world
     */
    public void setTargetWorld(final @Nullable World targetWorld) {
        this.targetWorld = targetWorld;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件的传送门类型。
     * <p>原文：
     * Gets the portal type for this event.
     *
     * @return 传送门类型
     * <p>原文：the portal type
     */
    public PortalType getPortalType() {
        return this.portalType;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

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
