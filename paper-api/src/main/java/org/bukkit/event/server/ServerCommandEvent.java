package org.bukkit.event.server;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 这个事件当服务器后台发送指令时调用.
 * 本事件在命令处理早期被调用, 并且在本事件进行一些修改 (比如{@link #setCommand(String)}) 会影响后续的事件.
 * <p>
 * 很多插件<b>都不使用此事件</b>.如果这个事件不是必要的,你应该尽量避免使用它!
 * <p>
 * 正确使用该事件的示例如下:
 * <ul>
 * <li>将执行的命令记录在一个单独的文件里</li>
 * <li>变量替换.例如，用玩家Steve的ip替换<code>${ip:Steve}</code>，或者模拟<code>@a</code>和<code>@p</code>作为装饰命令方块，插件不处理它.</li>
 * <li>有条件地阻止其它插件的命令.</li>
 * <li>为个人命令发送者注册命令别名.例如，在控制台运行命令<code>/calias cr gamemode creative</code>后，下一次运行命令<code>/cr</code>，它将会被替换成<code>/gamemode creative</code>(全局命令别名应该通过注册的别名来完成)</li>
 * </ul>
 * <p>
 * 不正确使用该事件的示例如下:
 * <ul>
 * <li>使用该事件来运行命令逻辑</li>
 * </ul>
 * <p>
 * 如果事件被取消,处理命令将停止.
 * <p>
 * 无论消息开头是否含<code>"/"</code>, 都应保持其原样. 如果手动添加或删除<code>"/"</code>, 可能会导致一些意外结果.
 * <p>原文：
 * This event is called when a command is run by a non-player. It is
 * called early in the command handling process, and modifications in this
 * event (via {@link #setCommand(String)}) will be shown in the behavior.
 * <p>
 * Many plugins will have <b>no use for this event</b>, and you should
 * attempt to avoid using it if it is not necessary.
 * <p>
 * Some examples of valid uses for this event are:
 * <ul>
 * <li>Logging executed commands to a separate file
 * <li>Variable substitution. For example, replacing <code>${ip:Steve}</code>
 *     with the connection IP of the player named Steve, or simulating the
 *     <code>@a</code> and <code>@p</code> decorators used by Command Blocks
 *     for plugins that do not handle it.
 * <li>Conditionally blocking commands belonging to other plugins.
 * <li>Per-sender command aliases. For example, after the console runs the
 *     command <code>/calias cr gamemode creative</code>, the next time they
 *     run <code>/cr</code>, it gets replaced into
 *     <code>/gamemode creative</code>. (Global command aliases should be
 *     done by registering the alias.)
 * </ul>
 * <p>
 * Examples of incorrect uses are:
 * <ul>
 * <li>Using this event to run command logic
 * </ul>
 * <p>
 * If the event is cancelled, processing of the command will halt.
 * <p>
 * The state of whether there is a slash (<code>/</code>) at the
 * beginning of the message should be preserved. If a slash is added or
 * removed, unexpected behavior may result.
 */
public class ServerCommandEvent extends ServerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final CommandSender sender;
    private String command;

    private boolean cancelled;

    @ApiStatus.Internal
    public ServerCommandEvent(@NotNull final CommandSender sender, @NotNull final String command) {
        this.sender = sender;
        this.command = command;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到命令发送者(后台).
     * <p>原文：
     * Get the command sender.
     *
     * @return 发送者
     * <p>原文：The sender
     */
    @NotNull
    public CommandSender getSender() {
        return this.sender;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到从控制台执行的命令(触发这个事件的命令).
     * <p>原文：
     * Gets the command that the user is attempting to execute from the
     * console
     *
     * @return 尝试执行的命令
     * <p>原文：Command the user is attempting to execute
     */
    @NotNull
    public String getCommand() {
        return this.command;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将要执行的命令.
     * <p>原文：
     * Sets the command that the server will execute
     *
     * @param message 控制台将会执行的命令
     * <p>原文：New message that the server will execute
     */
    public void setCommand(@NotNull String message) {
        this.command = message;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
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
