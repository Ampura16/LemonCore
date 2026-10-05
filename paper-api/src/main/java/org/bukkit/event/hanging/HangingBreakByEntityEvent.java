package org.bukkit.event.hanging;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Hanging;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个悬挂实体被一个实体移除时调用本事件
 * <p>原文：
 * Triggered when a hanging entity is removed by an entity
 */
public class HangingBreakByEntityEvent extends HangingBreakEvent {

    private final Entity remover;

    @ApiStatus.Internal
    public HangingBreakByEntityEvent(@NotNull final Hanging hanging, @NotNull final Entity remover) {
        this(hanging, remover, HangingBreakEvent.RemoveCause.ENTITY);
    }

    @ApiStatus.Internal
    public HangingBreakByEntityEvent(@NotNull final Hanging hanging, @NotNull final Entity remover, @NotNull final HangingBreakEvent.RemoveCause cause) {
        super(hanging, cause);
        this.remover = remover;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取移除这个悬挂实体的实体.
     * 可能为null, 比如当此实体被爆炸破坏时.
     * <p>原文：
     * Gets the entity that removed the hanging entity.
     *
     * @return 移除这个悬挂实体的实体
     * <p>原文：the entity that removed the hanging entity
     */
    @NotNull
    public Entity getRemover() {
        return this.remover;
    }
}
