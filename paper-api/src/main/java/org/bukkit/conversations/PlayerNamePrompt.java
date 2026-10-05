package org.bukkit.conversations;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * PlayerNamePrompt 是任何需要玩家输入另一个玩家名称的提示的基类。
 * <p>原文：
 * PlayerNamePrompt is the base class for any prompt that requires the player
 * to enter another player's name.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public abstract class PlayerNamePrompt extends ValidatingPrompt {
    private Plugin plugin;

    public PlayerNamePrompt(@NotNull Plugin plugin) {
        super();
        this.plugin = plugin;
    }

    @Override
    protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
        return plugin.getServer().getPlayer(input) != null;
    }

    @Nullable
    @Override
    protected Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
        return acceptValidatedInput(context, plugin.getServer().getPlayer(input));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 覆盖此方法以对用户的玩家名称响应执行某些操作。
     * <p>原文：
     * Override this method to perform some action with the user's player name
     * response.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @param input 用户的玩家名称响应。
     * <p>原文：The user's player name response.
     * @return 提示图中的下一个 {@link Prompt}。
     * <p>原文：The next {@link Prompt} in the prompt graph.
     */
    @Nullable
    protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull Player input);
}
