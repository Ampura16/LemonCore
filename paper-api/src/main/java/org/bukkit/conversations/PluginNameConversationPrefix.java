package org.bukkit.conversations;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * PluginNameConversationPrefix 是一个 {@link ConversationPrefix} 实现，它在对话输出前显示插件名称。
 * <p>原文：
 * PluginNameConversationPrefix is a {@link ConversationPrefix} implementation
 * that displays the plugin name in front of conversation output.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public class PluginNameConversationPrefix implements ConversationPrefix {

    protected String separator;
    protected ChatColor prefixColor;
    protected Plugin plugin;

    private String cachedPrefix;

    public PluginNameConversationPrefix(@NotNull Plugin plugin) {
        this(plugin, " > ", ChatColor.LIGHT_PURPLE);
    }

    public PluginNameConversationPrefix(@NotNull Plugin plugin, @NotNull String separator, @NotNull ChatColor prefixColor) {
        this.separator = separator;
        this.prefixColor = prefixColor;
        this.plugin = plugin;

        cachedPrefix = prefixColor + plugin.getDescription().getName() + separator + ChatColor.WHITE;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在每条对话消息前添加插件名称。
     * <p>原文：
     * Prepends each conversation message with the plugin name.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @return 空字符串。
     * <p>原文：An empty string.
     */
    @Override
    @NotNull
    public String getPrefix(@NotNull ConversationContext context) {
        return cachedPrefix;
    }
}
