package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家等级改变事件.
 * <p>原文：
 * Called when a players level changes
 */
public class PlayerLevelChangeEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final int oldLevel;
    private final int newLevel;

    @ApiStatus.Internal
    public PlayerLevelChangeEvent(@NotNull final Player player, final int oldLevel, final int newLevel) {
        super(player);
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此玩家的旧等级.
     * <p>原文：
     * Gets the old level of the player
     *
     * @return 旧等级
     * <p>原文：The old level of the player
     */
    public int getOldLevel() {
        return this.oldLevel;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此玩家升级/降级后的等级.
     * <p>原文：
     * Gets the new level of the player
     *
     * @return 升级/降级后的等级
     * <p>原文：The new (current) level of the player
     */
    public int getNewLevel() {
        return this.newLevel;
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
