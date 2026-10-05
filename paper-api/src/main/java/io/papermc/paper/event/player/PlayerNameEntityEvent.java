package io.papermc.paper.event.player;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家尝试为生物重命名时调用
 * <p>原文：
 * Called when the player is attempting to rename a mob
 */
@NullMarked
public class PlayerNameEntityEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private LivingEntity entity;
    private @Nullable Component name;
    private boolean persistent;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerNameEntityEvent(final Player player, final LivingEntity entity, final Component name, final boolean persistent) {
        super(player);
        this.entity = entity;
        this.name = name;
        this.persistent = persistent;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取要赋予实体的名称。
     * <p>原文：
     * Gets the name to be given to the entity.
     *
     * @return 名称
     * <p>原文：the name
     */
    public @Nullable Component getName() {
        return this.name;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置要赋予实体的名称。
     * <p>原文：
     * Sets the name to be given to the entity.
     *
     * @param name 名称
     * <p>原文：the name
     */
    public void setName(final @Nullable Component name) {
        this.name = name;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件涉及的实体。
     * <p>原文：
     * Gets the entity involved in this event.
     *
     * @return 实体
     * <p>原文：the entity
     */
    public LivingEntity getEntity() {
        return this.entity;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此事件涉及的实体。
     * <p>原文：
     * Sets the entity involved in this event.
     *
     * @param entity 实体
     * <p>原文：the entity
     */
    public void setEntity(final LivingEntity entity) {
        this.entity = entity;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取这是否会将生物设为持久存在。
     * <p>原文：
     * Gets whether this will set the mob to be persistent.
     *
     * @return 是否持久存在
     * <p>原文：persistent
     */
    public boolean isPersistent() {
        return this.persistent;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置这是否会将生物设为持久存在。
     * <p>原文：
     * Sets whether this will set the mob to be persistent.
     *
     * @param persistent 是否持久存在
     * <p>原文：persistent
     */
    public void setPersistent(final boolean persistent) {
        this.persistent = persistent;
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
