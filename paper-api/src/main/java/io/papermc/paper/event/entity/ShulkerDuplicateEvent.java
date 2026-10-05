package io.papermc.paper.event.entity;

import org.bukkit.entity.Shulker;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当潜影贝通过生成新的潜影贝来复制自身时触发。
 * <p>
 * 该事件在新创建的潜影贝（可通过 {@link #getEntity()} 获取）被添加到世界之前触发。
 * <p>原文：
 * Fired when a shulker duplicates itself by spawning a new shulker.
 * <p>
 * The event is fired prior to the newly created shulker, accessible via {@link #getEntity()}, being added to the world.
 */
@NullMarked
public class ShulkerDuplicateEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Shulker parent;
    private boolean cancelled;

    @ApiStatus.Internal
    public ShulkerDuplicateEvent(final Shulker child, final Shulker parent) {
        super(child);
        this.parent = parent;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供新创建的潜影贝，它在复制之前并不存在。在该事件发生时，该潜影贝尚未成为世界的一部分。
     * <p>原文：
     * Provides the newly created shulker, which did not exist prior to the duplication.
     * At the point of this event, said shulker is not part of the world yet.
     *
     * @return 新复制出的潜影贝。
     * <p>原文：the newly duplicated shulker.
     */
    @Override
    public Shulker getEntity() {
        return (Shulker) super.getEntity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供新创建潜影贝的“亲代”。亲代潜影贝是发起复制的那一个。
     * <p>原文：
     * Provides the "parent" of the freshly created shulker.
     * The parent shulker is the one that initiated the duplication.
     *
     * @return 先前存在并进行了复制的潜影贝。
     * <p>原文：the previously existing shulker which duplicated.
     */
    public Shulker getParent() {
        return this.parent;
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
