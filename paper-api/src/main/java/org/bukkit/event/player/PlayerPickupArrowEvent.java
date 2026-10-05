package org.bukkit.event.player;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家从地上捡起箭时触发本事件.
 * <p>原文：
 * Thrown when a player picks up an arrow from the ground.
 */
public class PlayerPickupArrowEvent extends PlayerPickupItemEvent {

    private final AbstractArrow arrow;

    @ApiStatus.Internal
    public PlayerPickupArrowEvent(@NotNull final Player player, @NotNull final Item item, @NotNull final AbstractArrow arrow) {
        super(player, item, 0);
        this.arrow = arrow;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家捡起的箭.
     * <p>原文：
     * Get the arrow being picked up by the player
     *
     * @return 被捡起的箭
     * <p>原文：The arrow being picked up
     */
    @NotNull
    public AbstractArrow getArrow() {
        return this.arrow;
    }
}
