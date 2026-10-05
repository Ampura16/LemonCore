package com.destroystokyo.paper.event.entity;

import java.util.Collections;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityTransformEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当闪电击中实体时触发
 * <p>原文：
 * Fired when lightning strikes an entity
 */
@NullMarked
public class EntityZapEvent extends EntityTransformEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final LightningStrike bolt;
    private boolean cancelled;

    @ApiStatus.Internal
    public EntityZapEvent(final Entity entity, final LightningStrike bolt, final Entity replacementEntity) {
        super(entity, Collections.singletonList(replacementEntity), TransformReason.LIGHTNING);
        this.bolt = bolt;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取击中实体的闪电。
     * <p>原文：
     * Gets the lightning bolt that is striking the entity.
     *
     * @return 导致此事件的闪电
     * <p>原文：The lightning bolt responsible for this event
     */
    public LightningStrike getBolt() {
        return this.bolt;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将取代被击中实体的实体。
     * <p>原文：
     * Gets the entity that will replace the struck entity.
     *
     * @return 将取代被击中实体的实体
     * <p>原文：The entity that will replace the struck entity
     */
    public Entity getReplacementEntity() {
        return super.getTransformedEntity();
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
