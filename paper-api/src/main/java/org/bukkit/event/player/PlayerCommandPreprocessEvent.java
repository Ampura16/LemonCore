package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 这个事件是,当一个玩家执行一个命令的时候将会被触发(也就是在聊天框里面输入信息以/开头的时候，算作命令，就会触发此事件)。
 * 且这个事件是早于插件的onCommand接收的命令的。
 * 如果你此时调用{@link #setMessage(String)}方法的话,其他插件收到的命令将会是你更改以后的命令。
 * 警告:如果没必要，请避免使用此事件.
 * 如果你不知道这个事件有啥用,下面的例子可能有助于你理解该事件的作用.
 * <ul>
 * <li>接收到一个带有变量的命令,把它用对应要替换成的
 * <li>量来替换这个变量. 举个例子
 *     <code>${nearbyPlayer}</code> 是一个变量，然后我们需要把它替换成离发送命令最近的玩家的名字
 *     又或者是替换成 <code>@a</code> 和 <code>@p</code> 这样的变量
 *     通过插件调用命令方块来执行它，而不是插件.
 * <li>你可以用这个事件来阻止其他插件的命令执行. 比
 *     如, 阻止玩家在竞技场内使用 <code>/home</code> 这个命令.
 * <li>你可以简化原本复杂的命令,将它用简单的命令替代. 比如啊, 在一个玩家输
 *     入 <code>/calias cr gamemode creative</code> 这个命令之后, 你可以将其拦截以后替换
 *     成 <code>/cr</code> 这个命令, 然后再把这个命令直接
 *     替换成 <code>/gamemode creative</code>. (全局性的命令别名依然
 *     需要在Plugin.yml内注册.)
 * </ul>
 * <p>
 * 切忌这样使用,比如:
 * <ul>
 * <li>强制去执行一个命令
 * </ul>
 * <p>
 * 如果该事件满足条件被取消掉,对应的命令将会无法发挥任何作用.
 * <p>
 * 另外,命令内的{@link #getMessage()}这个方法,返回的String会带有一个/
 * 切忌删除此/,否则将出现无法预料的错误.
 * <p>原文：
 * This event is called whenever a player runs a command (by placing a slash
 * at the start of their message). It is called early in the command handling
 * process, and modifications in this event (via {@link #setMessage(String)})
 * will be shown in the behavior.
 * <p>
 * Many plugins will have <b>no use for this event</b>, and you should
 * attempt to avoid using it if it is not necessary.
 * <p>
 * Some examples of valid uses for this event are:
 * <ul>
 * <li>Logging executed commands to a separate file
 * <li>Variable substitution. For example, replacing
 *     <code>${nearbyPlayer}</code> with the name of the nearest other
 *     player, or simulating the <code>@a</code> and <code>@p</code>
 *     decorators used by Command Blocks in plugins that do not handle it.
 * <li>Conditionally blocking commands belonging to other plugins. For
 *     example, blocking the use of the <code>/home</code> command in a
 *     combat arena.
 * <li>Per-sender command aliases. For example, after a player runs the
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
public class PlayerCommandPreprocessEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private String message;
    private final Set<Player> recipients;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerCommandPreprocessEvent(@NotNull final Player player, @NotNull final String message) {
        super(player);
        this.recipients = new HashSet<>(player.getServer().getOnlinePlayers());
        this.message = message;
    }

    @ApiStatus.Internal
    public PlayerCommandPreprocessEvent(@NotNull final Player player, @NotNull final String message, @NotNull final Set<Player> recipients) {
        super(player);
        this.recipients = recipients;
        this.message = message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所发送的命令的所有字符串.
     * <p>
     * 虽然所获取的命令字符串会带有一个“/”,但你执行命令的时候,不用输入这个“/”。
     * <p>原文：
     * Gets the command that the player is attempting to send.
     * <p>
     * All commands begin with a special character; implementations do not
     * consider the first character when executing the content.
     *
     * @return 返回玩家所输入的命令所有字符串,包括/
     * <p>原文：Message the player is attempting to send
     */
    @NotNull
    public String getMessage() {
        return this.message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家即将要发送的命令的字符串.
     * <p>
     * 虽然所获取的命令字符串会带有一个"/",但你执行命令的时候,不用输入这个"/".
     * <p>原文：
     * Sets the command that the player will send.
     * <p>
     * All commands begin with a special character; implementations do not
     * consider the first character when executing the content.
     *
     * @param command 设置即将要发送的命令
     * <p>原文：New message that the player will send
     * @throws IllegalArgumentException 如果这个命令为Null或者为空
     * <p>原文：if command is {@code null} or empty
     */
    public void setMessage(@NotNull String command) throws IllegalArgumentException {
        Preconditions.checkArgument(command != null, "Command cannot be null");
        Preconditions.checkArgument(!command.isEmpty(), "Command cannot be empty");
        this.message = command;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有能看到这个消息的玩家
     * <p>
     * 本方法返回的集合不保证可以改变和访问时可能自动填充。
     * 任何监听器访问这个返回的集合应该知道对于一个lazy set的实现可能会降低性能.
     * 监听器应注意到如果事件传唤者提供了一个不可修改的Set集合的话修改这个列表可能会抛出{@link
     * UnsupportedOperationException}异常。
     * <p>原文：
     * Gets a set of recipients that this chat message will be displayed to.
     * <p>
     * The set returned is not guaranteed to be mutable and may auto-populate
     * on access. Any listener accessing the returned set should be aware that
     * it may reduce performance for a lazy set implementation. Listeners
     * should be aware that modifying the list may throw {@link
     * UnsupportedOperationException} if the event caller provides an
     * unmodifiable set.
     *
     * @return 所有看见该消息的玩家
     * <p>原文：All Players who will see this chat message
     * @deprecated 该方法无法保证在每个版本上的效果
     * <p>原文：This is simply the online players. Modifications have no effect
     */
    @NotNull
    @Deprecated(since = "1.3.1", forRemoval = true)
    public Set<Player> getRecipients() {
        return this.recipients;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个命令的执行者
     * <p>原文：
     * Sets the player that this command will be executed as.
     *
     * @param player 新的命令执行者
     * <p>原文：New player which this event will execute as
     * @throws IllegalArgumentException 如果这个玩家为null
     * <p>原文：if the player provided is null
     * @deprecated Only works for sign commands; use {@link Player#performCommand(String)}, including those cases
     */
    @Deprecated(forRemoval = true)
    public void setPlayer(@NotNull final Player player) throws IllegalArgumentException {
        Preconditions.checkArgument(player != null, "Player cannot be null");
        this.player = player;
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
