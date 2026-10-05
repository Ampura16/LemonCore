package io.papermc.paper.event.world;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.WorldEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当世界的游戏规则被命令或 API 更改时调用。
 * <p>原文：
 * Called when a world's gamerule is changed, either by command or by api.
 */
@NullMarked
public class WorldGameRuleChangeEvent extends WorldEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final @Nullable CommandSender commandSender;
    protected final GameRule<?> gameRule;
    private String value;
    private boolean cancelled;

    @ApiStatus.Internal
    public WorldGameRuleChangeEvent(final World world, final @Nullable CommandSender commandSender, final GameRule<?> gameRule, final String value) {
        super(world);
        this.commandSender = commandSender;
        this.gameRule = gameRule;
        this.value = value;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此事件关联的命令发送者。
     * <p>原文：
     * Gets the command sender associated with this event.
     *
     * @return 如果游戏规则是通过 API 更改的则为 {@code null}，否则为 {@link CommandSender}。
     * <p>原文：{@code null} if the gamerule was changed via api, otherwise the {@link CommandSender}.
     */
    public @Nullable CommandSender getCommandSender() {
        return this.commandSender;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此事件关联的游戏规则。
     * <p>原文：
     * Gets the game rule associated with this event.
     *
     * @return 正在被更改的游戏规则。
     * <p>原文：the gamerule being changed.
     */
    public GameRule<?> getGameRule() {
        return this.gameRule;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取游戏规则的新值。
     * <p>原文：
     * Gets the new value of the gamerule.
     *
     * @return 游戏规则的新值。
     * <p>原文：the new value of the gamerule.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此游戏规则的新值。
     * <p>原文：
     * Sets the new value of this gamerule.
     *
     * @param value 游戏规则的新值。
     * <p>原文：the new value of the gamerule.
     */
    public void setValue(final String value) {
        this.value = value;
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
