package com.destroystokyo.paper.brigadier;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.suggestion.SuggestionProvider;

import java.util.function.Predicate;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 用于 Bukkit {@link Command} 的 Brigadier {@link Command}、{@link SuggestionProvider} 和权限检查器。
 * <p>原文：
 * Brigadier {@link Command}, {@link SuggestionProvider}, and permission checker for Bukkit {@link Command}s.
 *
 * @param <S> 命令源类型
 * <p>原文：command source type
 * @deprecated 将被移除，新版 Brigadier API 的用法请参阅 {@link io.papermc.paper.command.brigadier.Commands}。
 * <p>原文：For removal, see {@link io.papermc.paper.command.brigadier.Commands} on how to use the new Brigadier API.
 */
@Deprecated(forRemoval = true, since = "1.20.6")
public interface BukkitBrigadierCommand <S extends BukkitBrigadierCommandSource> extends Command<S>, Predicate<S>, SuggestionProvider<S> {
}
