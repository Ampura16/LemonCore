package io.papermc.paper.event.player;

import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.LoomInventory;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家在织布机物品栏中选择旗帜图案时调用。
 * <p>原文：
 * Called when a player selects a banner pattern in a loom inventory.
 */
@NullMarked
public class PlayerLoomPatternSelectEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final LoomInventory loomInventory;
    private PatternType patternType;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerLoomPatternSelectEvent(final Player player, final LoomInventory loomInventory, final PatternType patternType) {
        super(player);
        this.loomInventory = loomInventory;
        this.patternType = patternType;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取涉及的织布机物品栏。
     * <p>原文：
     * Gets the loom inventory involved.
     *
     * @return 织布机物品栏
     * <p>原文：the loom inventory
     */
    public LoomInventory getLoomInventory() {
        return this.loomInventory;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取所选的图案类型。
     * <p>原文：
     * Gets the pattern type selected.
     *
     * @return 图案类型
     * <p>原文：the pattern type
     */
    public PatternType getPatternType() {
        return this.patternType;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置所选的图案类型。
     * <p>原文：
     * Sets the pattern type selected.
     *
     * @param patternType 图案类型
     * <p>原文：the pattern type
     */
    public void setPatternType(final PatternType patternType) {
        this.patternType = patternType;
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
