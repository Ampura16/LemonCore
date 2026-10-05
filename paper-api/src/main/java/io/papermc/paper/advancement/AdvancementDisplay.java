package io.papermc.paper.advancement;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.translation.Translatable;
import net.kyori.adventure.util.Index;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 描述进度的显示方式。
 * <p>
 * 该显示用于聊天、提示框消息和进度界面。
 * <p>原文：
 * Describes the display of an advancement.
 * <p>
 * The display is used in the chat, in the toast messages and the advancements
 * screen.
 */
@NullMarked
public interface AdvancementDisplay {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取 {@link Frame}。
     * <p>
     * 它定义了进度界面中图块的外观以及完成时的文本。
     * <p>原文：
     * Gets the {@link Frame}.
     * <p>
     * This defines the appearance of the tile in the advancements screen and
     * the text when it's completed.
     *
     * @return 框架类型
     * <p>原文：the frame type
     */
    Frame frame();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取进度标题。
     * <p>原文：
     * Gets the advancement title.
     *
     * @return 标题
     * <p>原文：the title
     */
    Component title();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取描述。
     * <p>原文：
     * Gets the description.
     *
     * @return 描述
     * <p>原文：the description
     */
    Component description();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取进度界面中边框内显示的图标。
     * <p>原文：
     * Gets the icon shown in the frame in the advancements screen.
     *
     * @return 图标的副本
     * <p>原文：a copy of the icon
     */
    ItemStack icon();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否应显示提示框。
     * <p>
     * 提示框是显示在屏幕右上角的通知。
     * <p>原文：
     * Gets whether a toast should be displayed.
     * <p>
     * A toast is a notification that will be displayed in the top right corner
     * of the screen.
     *
     * @return 如果应显示提示则为 {@code true}
     * <p>原文：{@code true} if a toast should be shown
     */
    boolean doesShowToast();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否应在聊天中发送消息。
     * <p>原文：
     * Gets whether a message should be sent in the chat.
     *
     * @return 如果应发送消息则为 {@code true}
     * <p>原文：{@code true} if a message should be sent
     * @see org.bukkit.event.player.PlayerAdvancementDoneEvent#message() to edit
     * the message
     */
    boolean doesAnnounceToChat();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此进度是否隐藏。
     * <p>
     * 隐藏进度在解锁之前玩家无法查看。
     * <p>原文：
     * Gets whether this advancement is hidden.
     * <p>
     * Hidden advancements cannot be viewed by the player until they have been
     * unlocked.
     *
     * @return 如果被隐藏则为 {@code true}
     * <p>原文：{@code true} if hidden
     */
    boolean isHidden();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取选中时显示在进度树后方的纹理。
     * <p>
     * 仅影响没有父级的根进度。如果背景未指定或不存在，标签页背景将为缺失纹理。
     * <p>原文：
     * Gets the texture displayed behind the advancement tree when selected.
     * <p>
     * This only affects root advancements without any parent. If the background
     * is not specified or doesn't exist, the tab background will be the missing
     * texture.
     *
     * @return 背景纹理路径
     * <p>原文：the background texture path
     */
    @Nullable NamespacedKey backgroundPath();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此显示的格式化显示名称。它是玩家完成进度时聊天中显示的组件的一部分。
     * <p>原文：
     * Gets the formatted display name for this display. This
     * is a part of the component that would be shown in chat when a player
     * completes the advancement.
     *
     * @return 显示名称
     * <p>原文：the display name
     * @see org.bukkit.advancement.Advancement#displayName()
     */
    Component displayName();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 定义 {@link #icon()} 在进度界面中的外观，以及与 {@link #title() advancement name} 一起使用的颜色。
     * <p>原文：
     * Defines how the {@link #icon()} appears in the advancements screen and
     * the color used with the {@link #title() advancement name}.
     */
    enum Frame implements Translatable {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * “挑战完成”类进度。
         * <p>
         * 挑战完成并显示提示框时，客户端将播放 {@code ui.toast.challenge_complete} 音效。
         * <p>原文：
         * "Challenge complete" advancement.
         * <p>
         * The client will play the {@code ui.toast.challenge_complete} sound
         * when the challenge is completed and the toast is shown.
         */
        CHALLENGE("challenge", NamedTextColor.DARK_PURPLE),

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * “目标达成”类进度。
         * <p>原文：
         * "Goal reached" advancement.
         */
        GOAL("goal", NamedTextColor.GREEN),

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * “进步达成”类进度。
         * <p>原文：
         * "Advancement made" advancement.
         */
        TASK("task", NamedTextColor.GREEN);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 名称映射。
         * <p>原文：
         * The name map.
         */
        public static final Index<String, Frame> NAMES = Index.create(Frame.class, frame -> frame.name);
        private final String name;
        private final TextColor color;

        Frame(final String name, final TextColor color) {
            this.name = name;
            this.color = color;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取用于进度名称的 {@link TextColor}。
         * <p>原文：
         * Gets the {@link TextColor} used for the advancement name.
         *
         * @return 文本颜色
         * <p>原文：the text color
         */
        public TextColor color() {
            return this.color;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取完成进度时使用的翻译键。
         * <p>
         * 这是客户端显示的提示框的第一行。
         * <p>原文：
         * Gets the translation key used when an advancement is completed.
         * <p>
         * This is the first line of the toast displayed by the client.
         *
         * @return 提示消息键
         * <p>原文：the toast message key
         */
        @Override
        public String translationKey() {
            return "advancements.toast." + this.name;
        }
    }
}
