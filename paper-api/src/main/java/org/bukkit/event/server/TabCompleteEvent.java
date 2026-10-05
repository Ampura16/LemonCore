package org.bukkit.event.server;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个 {@link CommandSender} 尝试补全命令时触发本事件.
 * <br>
 * 请注意由于客户端的更改, 如果命令发起者是玩家, 本事件
 * 将只有在指定了命令参数后触发, 而不是命令本身.建议希望从tab补全列表删除命令的插件
 * 确保客户端没有相关命令的执行权限, 或使用{@link PlayerCommandSendEvent}.
 * <p>原文：
 * Called when a {@link CommandSender} of any description (ie: player or
 * console) attempts to tab complete.
 * <br>
 * Note that due to client changes, if the sender is a Player, this event will
 * only begin to fire once command arguments are specified, not commands
 * themselves. Plugins wishing to remove commands from tab completion are
 * advised to ensure the client does not have permission for the relevant
 * commands, or use {@link PlayerCommandSendEvent}.
 *
 * @apiNote Only called for bukkit API commands {@link org.bukkit.command.Command} and
 * {@link org.bukkit.command.CommandExecutor} and not for brigadier commands ({@link io.papermc.paper.command.brigadier.Commands}).
 */
public class TabCompleteEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final CommandSender sender;
    private final String buffer;
    private final boolean isCommand;
    private final Location location;
    private List<String> completions;

    private boolean cancelled;

    @ApiStatus.Internal
    public TabCompleteEvent(@NotNull CommandSender sender, @NotNull String buffer, @NotNull List<String> completions) {
        this(sender, buffer, completions, sender instanceof ConsoleCommandSender || buffer.startsWith("/"), null);
    }

    @ApiStatus.Internal
    public TabCompleteEvent(@NotNull CommandSender sender, @NotNull String buffer, @NotNull List<String> completions, boolean isCommand, @Nullable Location location) {
        this.sender = sender;
        this.buffer = buffer;
        this.completions = new ArrayList<>(completions);
        this.isCommand = isCommand;
        this.location = location;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取正在补全这个命令的发送者.
     * <p>原文：
     * Get the sender completing this command.
     *
     * @return {@link CommandSender} 实例
     * <p>原文：the {@link CommandSender} instance
     */
    @NotNull
    public CommandSender getSender() {
        return this.sender;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回构成这个补全项的命令缓冲区.
     * <p>
     * 译注: 命令缓冲区是什么? 比如您对命令"/tp no"进行补全，"/tp no"即为这个buffer.
     * <p>原文：
     * Return the entire buffer which formed the basis of this completion.
     *
     * @return 命令缓冲区，即为你输入的内容
     * <p>原文：command buffer, as entered
     */
    @NotNull
    public String getBuffer() {
        return this.buffer;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 提供给发送者的补全项的列表.
     * 这个列表是可变的，可以编辑该列表以实现更改补全项.
     * <p>原文：
     * The list of completions which will be offered to the sender. Completions may be ordered alphanumerically later on in the tab completion process.
     * This list is mutable and reflects what will be offered.
     *
     * @return 提供的补全项的列表
     * <p>原文：a list of offered completions
     */
    @NotNull
    public List<String> getCompletions() {
        return this.completions;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置提供的补全项, 会覆盖已设置的补全项.
     * <p>原文：
     * Set the completions offered, overriding any already set.
     * <br>
     * The passed collection will be cloned to a new List. You must call {@link #getCompletions()} to mutate from here
     *
     * @param completions 新的补全项
     * <p>原文：the new completions
     */
    public void setCompletions(@NotNull List<String> completions) {
        Preconditions.checkArgument(completions != null, "completions cannot be null");
        this.completions = new ArrayList<>(completions);
    }

    /**
     * @return {@code true} if it is a command being tab completed, {@code false} if it is a chat message.
     */
    public boolean isCommand() {
        return this.isCommand;
    }

    /**
     * @return The position looked at by the sender, or {@code null} if none
     */
    @Nullable
    public Location getLocation() {
        return this.location != null ? this.location.clone() : null;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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
