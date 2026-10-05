package io.papermc.paper.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家尝试移动但被服务器阻止时运行
 * <p>原文：
 * Runs when a player attempts to move, but is prevented from doing so by the server
 */
@NullMarked
public class PlayerFailMoveEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final FailReason failReason;
    private final Location from;
    private final Location to;
    private boolean allowed;
    private boolean logWarning;

    @ApiStatus.Internal
    public PlayerFailMoveEvent(final Player player, final FailReason failReason, final boolean allowed, final boolean logWarning, final Location from, final Location to) {
        super(player);
        this.failReason = failReason;
        this.allowed = allowed;
        this.logWarning = logWarning;
        this.from = from;
        this.to = to;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取服务器阻止此次移动的原因
     * <p>原文：
     * Gets the reason this movement was prevented by the server
     *
     * @return 移动被阻止的原因
     * <p>原文：The reason the movement was prevented
     */
    public FailReason getFailReason() {
        return this.failReason;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此玩家移动前的位置
     * <p>原文：
     * Gets the location this player moved from
     *
     * @return 玩家移动前的位置
     * <p>原文：Location the player moved from
     */
    public Location getFrom() {
        return this.from.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此玩家尝试移动到的位置
     * <p>原文：
     * Gets the location this player tried to move to
     *
     * @return 玩家尝试移动到的位置
     * <p>原文：Location the player tried to move to
     */
    public Location getTo() {
        return this.to.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否应绕过检查，允许该移动
     * <p>原文：
     * Gets if the check should be bypassed, allowing the movement
     *
     * @return 是否绕过检查
     * <p>原文：whether to bypass the check
     */
    public boolean isAllowed() {
        return this.allowed;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否应绕过检查并允许该移动
     * <p>原文：
     * Set if the check should be bypassed and the movement should be allowed
     *
     * @param allowed 是否绕过检查
     * <p>原文：whether to bypass the check
     */
    public void setAllowed(final boolean allowed) {
        this.allowed = allowed;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否会在控制台打印警告，例如“Player123 moved too quickly!”
     * <p>原文：
     * Gets if warnings will be printed to console. e.g. "Player123 moved too quickly!"
     *
     * @return 是否记录警告
     * <p>原文：whether to log warnings
     */
    public boolean getLogWarning() {
        return this.logWarning;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否在控制台打印警告，例如“Player123 moved too quickly!”
     * <p>原文：
     * Set if a warning is printed to console. e.g. "Player123 moved too quickly!"
     *
     * @param logWarning 是否记录警告
     * <p>原文：whether to log warnings
     */
    public void setLogWarning(final boolean logWarning) {
        this.logWarning = logWarning;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public enum FailReason {
        MOVED_INTO_UNLOADED_CHUNK, // Only fired if the world setting prevent-moving-into-unloaded-chunks is true
        MOVED_TOO_QUICKLY,
        MOVED_WRONGLY,
        CLIPPED_INTO_BLOCK
    }

}
