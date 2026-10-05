package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 实体形成方块时触发此事件
 * <p>
 * 举个例子:
 * <ul>
 * <li>雪傀儡移动时形成的雪
 * <li>穿戴冰霜行者附魔的鞋子形成的冰霜
 * </ul>
 * <p>原文：
 * Called when a block is formed by entities.
 * <p>
 * Examples:
 * <ul>
 * <li>Snow formed by a {@link org.bukkit.entity.Snowman}.
 * <li>Frosted Ice formed by the Frost Walker enchantment.
 * </ul>
 */
public class EntityBlockFormEvent extends BlockFormEvent {

    private final Entity entity;

    @ApiStatus.Internal
    public EntityBlockFormEvent(@NotNull final Entity entity, @NotNull final Block block, @NotNull final BlockState blockstate) {
        super(block, blockstate);

        this.entity = entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被建造的实体.
     * <p>原文：
     * Get the entity that formed the block.
     *
     * @return 这个事件的实体
     * <p>原文：Entity involved in event
     */
    @NotNull
    public Entity getEntity() {
        return this.entity;
    }
}
