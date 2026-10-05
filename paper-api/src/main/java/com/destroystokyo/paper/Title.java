package com.destroystokyo.paper;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示可以发送给 {@link Player} 的标题。
 * <p>
 * 标题可以不带副标题文本发送。
 * </p>
 * <p>原文：
 * Represents a title to may be sent to a {@link Player}.
 *
 * <p>A title can be sent without subtitle text.</p>
 *
 * @deprecated 请使用 {@link net.kyori.adventure.title.Title}
 * <p>原文：use {@link net.kyori.adventure.title.Title}
 */
@Deprecated(since = "1.16.5")
public final class Title {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 标题淡入的默认刻数。
     * <p>原文：
     * The default number of ticks for the title to fade in.
     */
    public static final int DEFAULT_FADE_IN = 20;
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 标题停留的默认刻数。
     * <p>原文：
     * The default number of ticks for the title to stay.
     */
    public static final int DEFAULT_STAY = 200;
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 标题淡出的默认刻数。
     * <p>原文：
     * The default number of ticks for the title to fade out.
     */
    public static final int DEFAULT_FADE_OUT = 20;

    private final BaseComponent[] title;
    private final BaseComponent[] subtitle;
    private final int fadeIn;
    private final int stay;
    private final int fadeOut;

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建使用默认时间值且无副标题的标题。
     * <p>
     * 时间使用默认值。
     * </p>
     * <p>原文：
     * Create a title with the default time values and no subtitle.
     *
     * <p>Times use default values.</p>
     *
     * @param title 标题的主文本
     * <p>原文：the main text of the title
     * @throws NullPointerException 如果标题为 null
     * <p>原文：if the title is null
     */
    public Title(@NotNull BaseComponent title) {
        this(title, null);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建使用默认时间值且无副标题的标题。
     * <p>
     * 时间使用默认值。
     * </p>
     * <p>原文：
     * Create a title with the default time values and no subtitle.
     *
     * <p>Times use default values.</p>
     *
     * @param title 标题的主文本
     * <p>原文：the main text of the title
     * @throws NullPointerException 如果标题为 null
     * <p>原文：if the title is null
     */
    public Title(@NotNull BaseComponent[] title) {
        this(title, null);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建使用默认时间值且无副标题的标题。
     * <p>
     * 时间使用默认值。
     * </p>
     * <p>原文：
     * Create a title with the default time values and no subtitle.
     *
     * <p>Times use default values.</p>
     *
     * @param title 标题的主文本
     * <p>原文：the main text of the title
     * @throws NullPointerException 如果标题为 null
     * <p>原文：if the title is null
     */
    public Title(@NotNull String title) {
        this(title, null);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建使用默认时间值的标题。
     * <p>
     * 时间使用默认值。
     * </p>
     * <p>原文：
     * Create a title with the default time values.
     *
     * <p>Times use default values.</p>
     *
     * @param title 标题的主文本
     * <p>原文：the main text of the title
     * @param subtitle 标题的副文本
     * <p>原文：the secondary text of the title
     */
    public Title(@NotNull BaseComponent title, @Nullable BaseComponent subtitle) {
        this(title, subtitle, DEFAULT_FADE_IN, DEFAULT_STAY, DEFAULT_FADE_OUT);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建使用默认时间值的标题。
     * <p>
     * 时间使用默认值。
     * </p>
     * <p>原文：
     * Create a title with the default time values.
     *
     * <p>Times use default values.</p>
     *
     * @param title 标题的主文本
     * <p>原文：the main text of the title
     * @param subtitle 标题的副文本
     * <p>原文：the secondary text of the title
     */
    public Title(@NotNull BaseComponent[] title, @Nullable BaseComponent[] subtitle) {
        this(title, subtitle, DEFAULT_FADE_IN, DEFAULT_STAY, DEFAULT_FADE_OUT);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建使用默认时间值的标题。
     * <p>
     * 时间使用默认值。
     * </p>
     * <p>原文：
     * Create a title with the default time values.
     *
     * <p>Times use default values.</p>
     *
     * @param title 标题的主文本
     * <p>原文：the main text of the title
     * @param subtitle 标题的副文本
     * <p>原文：the secondary text of the title
     */
    public Title(@NotNull String title, @Nullable String subtitle) {
        this(title, subtitle, DEFAULT_FADE_IN, DEFAULT_STAY, DEFAULT_FADE_OUT);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的标题。
     * <p>原文：
     * Creates a new title.
     *
     * @param title 标题的主文本
     * <p>原文：the main text of the title
     * @param subtitle 标题的副文本
     * <p>原文：the secondary text of the title
     * @param fadeIn 标题淡入的刻数
     * <p>原文：the number of ticks for the title to fade in
     * @param stay 标题在屏幕上停留的刻数
     * <p>原文：the number of ticks for the title to stay on screen
     * @param fadeOut 标题淡出的刻数
     * <p>原文：the number of ticks for the title to fade out
     * @throws IllegalArgumentException 如果任一时间为负数
     * <p>原文：if any of the times are negative
     */
    public Title(@NotNull BaseComponent title, @Nullable BaseComponent subtitle, int fadeIn, int stay, int fadeOut) {
        this(
                new BaseComponent[]{checkNotNull(title, "title")},
                subtitle == null ? null : new BaseComponent[]{subtitle},
                fadeIn,
                stay,
                fadeOut
        );
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的标题。
     * <p>原文：
     * Creates a new title.
     *
     * @param title 标题的主文本
     * <p>原文：the main text of the title
     * @param subtitle 标题的副文本
     * <p>原文：the secondary text of the title
     * @param fadeIn 标题淡入的刻数
     * <p>原文：the number of ticks for the title to fade in
     * @param stay 标题在屏幕上停留的刻数
     * <p>原文：the number of ticks for the title to stay on screen
     * @param fadeOut 标题淡出的刻数
     * <p>原文：the number of ticks for the title to fade out
     * @throws IllegalArgumentException 如果任一时间为负数
     * <p>原文：if any of the times are negative
     */
    public Title(@Nullable BaseComponent[] title, @NotNull BaseComponent[] subtitle, int fadeIn, int stay, int fadeOut) {
        checkArgument(fadeIn >= 0, "Negative fadeIn: %s", fadeIn);
        checkArgument(stay >= 0, "Negative stay: %s", stay);
        checkArgument(fadeOut >= 0, "Negative fadeOut: %s", fadeOut);
        this.title = checkNotNull(title, "title");
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的标题。
     * <p>
     * 建议使用 {@link BaseComponent} 构造器。
     * </p>
     * <p>原文：
     * Creates a new title.
     *
     * <p>It is recommended to use the {@link BaseComponent} constructors.</p>
     *
     * @param title 标题的主文本
     * <p>原文：the main text of the title
     * @param subtitle 标题的副文本
     * <p>原文：the secondary text of the title
     * @param fadeIn 标题淡入的刻数
     * <p>原文：the number of ticks for the title to fade in
     * @param stay 标题在屏幕上停留的刻数
     * <p>原文：the number of ticks for the title to stay on screen
     * @param fadeOut 标题淡出的刻数
     * <p>原文：the number of ticks for the title to fade out
     */
    public Title(@NotNull String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        this(
                TextComponent.fromLegacyText(checkNotNull(title, "title")),
                subtitle == null ? null : TextComponent.fromLegacyText(subtitle),
                fadeIn,
                stay,
                fadeOut
        );
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此标题的文本
     * <p>原文：
     * Gets the text of this title
     *
     * @return 文本
     * <p>原文：the text
     */
    @NotNull
    public BaseComponent[] getTitle() {
        return this.title;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此标题副标题的文本
     * <p>原文：
     * Gets the text of this title's subtitle
     *
     * @return 文本
     * <p>原文：the text
     */
    @Nullable
    public BaseComponent[] getSubtitle() {
        return this.subtitle;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取淡入的刻数。
     * <p>
     * 返回值永远不会为负。
     * </p>
     * <p>原文：
     * Gets the number of ticks to fade in.
     *
     * <p>The returned value is never negative.</p>
     *
     * @return 淡入的刻数
     * <p>原文：the number of ticks to fade in
     */
    public int getFadeIn() {
        return this.fadeIn;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取停留的刻数。
     * <p>
     * 返回值永远不会为负。
     * </p>
     * <p>原文：
     * Gets the number of ticks to stay.
     *
     * <p>The returned value is never negative.</p>
     *
     * @return 停留的刻数
     * <p>原文：the number of ticks to stay
     */
    public int getStay() {
        return this.stay;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取淡出的刻数。
     * <p>
     * 返回值永远不会为负。
     * </p>
     * <p>原文：
     * Gets the number of ticks to fade out.
     *
     * <p>The returned value is never negative.</p>
     *
     * @return 淡出的刻数
     * <p>原文：the number of ticks to fade out
     */
    public int getFadeOut() {
        return this.fadeOut;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将标题直接发送给玩家
     * <p>原文：
     * Sends the title directly to a player
     *
     * @param player 标题的接收者
     * <p>原文：the receiver of the title
     */
    public void send(@NotNull Player player) {
        player.sendTitle(this);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将标题直接发送给指定的玩家
     * <p>原文：
     * Sends the title directly to the defined players
     *
     * @param players 标题的接收者
     * <p>原文：the receivers of the title
     */
    public void send(@NotNull Collection<? extends Player> players) {
        for (Player player : players) {
            player.sendTitle(this);
        }
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将标题直接发送给指定的玩家
     * <p>原文：
     * Sends the title directly to the defined players
     *
     * @param players 标题的接收者
     * <p>原文：the receivers of the title
     */
    public void send(@NotNull Player[] players) {
        for (Player player : players) {
            player.sendTitle(this);
        }
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将标题直接发送给所有在线玩家
     * <p>原文：
     * Sends the title directly to all online players
     */
    public void broadcast() {
        send(Bukkit.getOnlinePlayers());
    }

    @NotNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于创建标题的构建器
     * <p>原文：
     * A builder for creating titles
     */
    public static final class Builder {

        private BaseComponent[] title;
        private BaseComponent[] subtitle;
        private int fadeIn = DEFAULT_FADE_IN;
        private int stay = DEFAULT_STAY;
        private int fadeOut = DEFAULT_FADE_OUT;

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将标题设为给定文本。
         * <p>原文：
         * Sets the title to the given text.
         *
         * @param title 标题文本
         * <p>原文：the title text
         * @return 此构建器实例
         * <p>原文：this builder instance
         * @throws NullPointerException 如果标题为 null
         * <p>原文：if the title is null
         */
        @NotNull
        public Builder title(@NotNull BaseComponent title) {
            return this.title(new BaseComponent[]{checkNotNull(title, "title")});
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将标题设为给定文本。
         * <p>原文：
         * Sets the title to the given text.
         *
         * @param title 标题文本
         * <p>原文：the title text
         * @return 此构建器实例
         * <p>原文：this builder instance
         * @throws NullPointerException 如果标题为 null
         * <p>原文：if the title is null
         */
        @NotNull
        public Builder title(@NotNull BaseComponent[] title) {
            this.title = checkNotNull(title, "title");
            return this;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将标题设为给定文本。
         * <p>
         * 建议使用 {@link BaseComponent} 方法。
         * </p>
         * <p>原文：
         * Sets the title to the given text.
         *
         * <p>It is recommended to use the {@link BaseComponent} methods.</p>
         *
         * @param title 标题文本
         * <p>原文：the title text
         * @return 此构建器实例
         * <p>原文：this builder instance
         * @throws NullPointerException 如果标题为 null
         * <p>原文：if the title is null
         */
        @NotNull
        public Builder title(@NotNull String title) {
            return this.title(TextComponent.fromLegacyText(checkNotNull(title, "title")));
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将副标题设为给定文本。
         * <p>原文：
         * Sets the subtitle to the given text.
         *
         * @param subtitle 标题文本
         * <p>原文：the title text
         * @return 此构建器实例
         * <p>原文：this builder instance
         */
        @NotNull
        public Builder subtitle(@Nullable BaseComponent subtitle) {
            return this.subtitle(subtitle == null ? null : new BaseComponent[]{subtitle});
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将副标题设为给定文本。
         * <p>原文：
         * Sets the subtitle to the given text.
         *
         * @param subtitle 标题文本
         * <p>原文：the title text
         * @return 此构建器实例
         * <p>原文：this builder instance
         */
        @NotNull
        public Builder subtitle(@Nullable BaseComponent[] subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将副标题设为给定文本。
         * <p>
         * 建议使用 {@link BaseComponent} 方法。
         * </p>
         * <p>原文：
         * Sets the subtitle to the given text.
         *
         * <p>It is recommended to use the {@link BaseComponent} methods.</p>
         *
         * @param subtitle 标题文本
         * <p>原文：the title text
         * @return 此构建器实例
         * <p>原文：this builder instance
         */
        @NotNull
        public Builder subtitle(@Nullable String subtitle) {
            return this.subtitle(subtitle == null ? null : TextComponent.fromLegacyText(subtitle));
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置标题淡入的刻数
         * <p>原文：
         * Sets the number of ticks for the title to fade in
         *
         * @param fadeIn 淡入的刻数
         * <p>原文：the number of ticks to fade in
         * @return 此构建器实例
         * <p>原文：this builder instance
         * @throws IllegalArgumentException 如果为负数
         * <p>原文：if it is negative
         */
        @NotNull
        public Builder fadeIn(int fadeIn) {
            checkArgument(fadeIn >= 0, "Negative fadeIn: %s", fadeIn);
            this.fadeIn = fadeIn;
            return this;
        }


        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置标题停留的刻数。
         * <p>原文：
         * Sets the number of ticks for the title to stay.
         *
         * @param stay 停留的刻数
         * <p>原文：the number of ticks to stay
         * @return 此构建器实例
         * <p>原文：this builder instance
         * @throws IllegalArgumentException 如果为负数
         * <p>原文：if it is negative
         */
        @NotNull
        public Builder stay(int stay) {
            checkArgument(stay >= 0, "Negative stay: %s", stay);
            this.stay = stay;
            return this;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置标题淡出的刻数。
         * <p>原文：
         * Sets the number of ticks for the title to fade out.
         *
         * @param fadeOut 淡出的刻数
         * <p>原文：the number of ticks to fade out
         * @return 此构建器实例
         * <p>原文：this builder instance
         * @throws IllegalArgumentException 如果为负数
         * <p>原文：if it is negative
         */
        @NotNull
        public Builder fadeOut(int fadeOut) {
            checkArgument(fadeOut >= 0, "Negative fadeOut: %s", fadeOut);
            this.fadeOut = fadeOut;
            return this;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 根据构建器中的值创建标题。
         * <p>原文：
         * Create a title based on the values in the builder.
         *
         * @return 根据此构建器中的值生成的标题
         * <p>原文：a title from the values in this builder
         * @throws IllegalStateException 如果未指定标题
         * <p>原文：if title isn't specified
         */
        @NotNull
        public Title build() {
            checkState(title != null, "Title not specified");
            return new Title(this.title, this.subtitle, this.fadeIn, this.stay, this.fadeOut);
        }
    }
}
