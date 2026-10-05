package org.bukkit.event.player;

import net.kyori.adventure.translation.Translator;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import java.util.Locale;
import java.util.Objects;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家改变他们的语言设置时触发本事件.
 * <p>原文：
 * Called when a player changes their locale in the client settings.
 */
public class PlayerLocaleChangeEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final String locale;
    private final Locale adventure$locale;

    @ApiStatus.Internal
    public PlayerLocaleChangeEvent(@NotNull Player player, @NotNull String locale) {
        super(player);
        this.locale = locale;
        this.adventure$locale = Objects.requireNonNullElse(Translator.parseLocale(locale), Locale.US);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 玩家选择的语言
     * <p>原文：the player's new locale
     * @see Player#getLocale()
     * @deprecated in favour of {@link #locale()}
     */
    @NotNull
    @Deprecated
    public String getLocale() {
        return this.locale;
    }

    /**
     * @see Player#locale()
     * @return the player's new locale
     */
    public @NotNull Locale locale() {
        return this.adventure$locale;
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
