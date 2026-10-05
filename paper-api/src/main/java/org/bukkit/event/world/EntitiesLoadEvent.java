package org.bukkit.event.world;

import java.util.List;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体将被加载时调用.
 *
 * 事件提供的区块可能尚未加载.
 * <p>原文：
 * Called when entities are loaded.
 * <br>
 * The provided chunk may or may not be loaded.
 */
public class EntitiesLoadEvent extends ChunkEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final List<Entity> entities;

    @ApiStatus.Internal
    public EntitiesLoadEvent(@NotNull Chunk chunk, @NotNull List<Entity> entities) {
        super(chunk);
        this.entities = entities;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被加载的实体.
     * <p>原文：
     * Get the entities which are being loaded.
     *
     * @return 被加载的实体的不可变列表
     * <p>原文：unmodifiable list of loaded entities.
     */
    @NotNull
    public @Unmodifiable List<Entity> getEntities() {
        return this.entities;
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
