package com.destroystokyo.paper.event.entity;

import java.util.Collection;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当末影龙火球与方块/实体碰撞并生成 AreaEffectCloud 时触发
 * <p>原文：
 * Fired when a DragonFireball collides with a block/entity and spawns an AreaEffectCloud
 */
@NullMarked
public class EnderDragonFireballHitEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Collection<LivingEntity> targets;
    private final AreaEffectCloud areaEffectCloud;
    private boolean cancelled;

    @ApiStatus.Internal
    public EnderDragonFireballHitEvent(final DragonFireball fireball, final Collection<LivingEntity> targets, final AreaEffectCloud areaEffectCloud) {
        super(fireball);
        this.targets = targets;
        this.areaEffectCloud = areaEffectCloud;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此事件涉及的火球
     * <p>原文：
     * The fireball involved in this event
     */
    @Override
    public DragonFireball getEntity() {
        return (DragonFireball) super.getEntity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 被火球击中的生物实体
     * <p>原文：
     * The living entities hit by fireball
     *
     * @return 目标
     * <p>原文：the targets
     */
    public Collection<LivingEntity> getTargets() {
        return this.targets;
    }

    /**
     * @return 此次碰撞中生成的区域效果云
     * <p>原文：The area effect cloud spawned in this collision
     */
    public AreaEffectCloud getAreaEffectCloud() {
        return this.areaEffectCloud;
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
