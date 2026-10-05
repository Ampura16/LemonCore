package org.bukkit.event.entity;

import java.util.List;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当滞留药水应用它的效果时触发本事件，期间每5 tick发生一次。
 * <p>原文：
 * Called when a lingering potion applies its effects. Happens
 * once every 5 ticks
 */
public class AreaEffectCloudApplyEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final List<LivingEntity> affectedEntities;

    private boolean cancelled;

    @ApiStatus.Internal
    public AreaEffectCloudApplyEvent(@NotNull final AreaEffectCloud entity, @NotNull final List<LivingEntity> affectedEntities) {
        super(entity);
        this.affectedEntities = affectedEntities;
    }

    @Override
    @NotNull
    public AreaEffectCloud getEntity() {
        return (AreaEffectCloud) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个受影响实体的可变列表。
     * <p>
     * 特别注意不是在列表里的每个实体都保证受影响。
     * 药水效果云可能由于{@link AreaEffectCloud#getDurationOnUse()}或{@link AreaEffectCloud#getRadiusOnUse()}的消耗死在应用过程中的影响
     * <p>原文：
     * Retrieves a mutable list of the effected entities
     * <p>
     * It is important to note that not every entity in this list
     * is guaranteed to be effected.  The cloud may die during the
     * application of its effects due to the depletion of {@link AreaEffectCloud#getDurationOnUse()}
     * or {@link AreaEffectCloud#getRadiusOnUse()}
     *
     * @return 受影响的实体列表
     * <p>原文：the affected entity list
     */
    @NotNull
    public List<LivingEntity> getAffectedEntities() {
        return this.affectedEntities;
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
