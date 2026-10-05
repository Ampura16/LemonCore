package org.bukkit.event.player;

import com.google.common.base.Preconditions;
import java.util.Collection;
import org.bukkit.Warning;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家尝试补全聊天消息时触发本事件
 * <p>原文：
 * Called when a player attempts to tab-complete a chat message.
 *
 * @deprecated 因客户端的改变(1.13更新较大幅度改善了命令输入的体验), 该事件不再触发.
 * 1.13+版本可以参考:{@link PlayerCommandSendEvent}
 * <p>原文：This event is no longer fired due to client changes
 */
@Deprecated(since = "1.13")
@Warning(reason = "This event is no longer fired due to client changes")
public class PlayerChatTabCompleteEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final String message;
    private final String lastToken;
    private final Collection<String> completions;

    @ApiStatus.Internal
    public PlayerChatTabCompleteEvent(@NotNull final Player player, @NotNull final String message, @NotNull final Collection<String> completions) {
        super(player);
        Preconditions.checkArgument(message != null, "Message cannot be null");
        Preconditions.checkArgument(completions != null, "Completions cannot be null");
        this.message = message;
        int i = message.lastIndexOf(' ');
        if (i < 0) {
            this.lastToken = message;
        } else {
            this.lastToken = message.substring(i + 1);
        }
        this.completions = completions;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被补全的聊天消息.
     * <p>原文：
     * Gets the chat message being tab-completed.
     *
     * @return 聊天消息
     * <p>原文：the chat message
     */
    @NotNull
    public String getChatMessage() {
        return this.message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被补全消息的最后一个'标记'.
     * <p>
     * 标记是字符开始后的最后一个组成部分. (比如“res ? test”，这里的last token就是test)
     * <p>原文：
     * Gets the last 'token' of the message being tab-completed.
     * <p>
     * The token is the substring starting with the character after the last
     * space in the message.
     *
     * @return 聊天消息的最后一个标记
     * <p>原文：The last token for the chat message
     */
    @NotNull
    public String getLastToken() {
        return this.lastToken;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有补全项集合.
     * <p>原文：
     * This is the collection of completions for this event.
     *
     * @return 补全项
     * <p>原文：the current completions
     */
    @NotNull
    public Collection<String> getTabCompletions() {
        return this.completions;
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
