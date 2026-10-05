package com.destroystokyo.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示玩家点击未知实体时调用的事件。适用于处理虚拟实体（并未真正在服务器上生成的实体）的插件。<br> 每次交互中该事件可能被调用多次，使用不同的交互手，并可能带或不带点击位置。
 * <p>原文：
 * Represents an event that is called when a player clicks an unknown entity.
 * Useful for plugins dealing with virtual entities (entities that aren't actually spawned on the server).
 * <br>
 * This event may be called multiple times per interaction with different interaction hands
 * and with or without the clicked position.
 */
@NullMarked
public class PlayerUseUnknownEntityEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final int entityId;
    private final boolean attack;
    private final EquipmentSlot hand;
    private final @Nullable Vector clickedPosition;

    @ApiStatus.Internal
    public PlayerUseUnknownEntityEvent(final Player player, final int entityId, final boolean attack, final EquipmentSlot hand, final @Nullable Vector clickedPosition) {
        super(player);
        this.entityId = entityId;
        this.attack = attack;
        this.hand = hand;
        this.clickedPosition = clickedPosition;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回被交互的未知实体的实体 ID。
     * <p>原文：
     * Returns the entity id of the unknown entity that was interacted with.
     *
     * @return 被交互实体的实体 ID
     * <p>原文：the entity id of the entity that was interacted with
     */
    public int getEntityId() {
        return this.entityId;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回该交互是否为攻击。
     * <p>原文：
     * Returns whether the interaction was an attack.
     *
     * @return 如果玩家正在攻击该实体则为 {@code true}，如果玩家正在与该实体交互则为 {@code false}
     * <p>原文：{@code true} if the player is attacking the entity, {@code false} if the player is interacting with the entity
     */
    public boolean isAttack() {
        return this.attack;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回执行此交互所用的手。
     * <p>原文：
     * Returns the hand used to perform this interaction.
     *
     * @return 用于交互的手
     * <p>原文：the hand used to interact
     */
    public EquipmentSlot getHand() {
        return this.hand;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回相对于被点击实体的位置；如果不可用则返回 {@code null}。详情参见 {@link PlayerInteractAtEntityEvent}。
     * <p>原文：
     * Returns the position relative to the entity that was clicked, or {@code null} if not available.
     * See {@link PlayerInteractAtEntityEvent} for more details.
     *
     * @return 相对于被点击实体的位置；如果不可用则为 {@code null}
     * <p>原文：the position relative to the entity that was clicked, or {@code null} if not available
     * @see PlayerInteractAtEntityEvent
     */
    public @Nullable Vector getClickedRelativePosition() {
        return this.clickedPosition != null ? this.clickedPosition.clone() : null;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
