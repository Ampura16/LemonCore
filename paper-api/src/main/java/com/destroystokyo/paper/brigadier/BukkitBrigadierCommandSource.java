package com.destroystokyo.paper.brigadier;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

/**
 * @deprecated 将被移除，新版 Brigadier API 的用法请参阅 {@link io.papermc.paper.command.brigadier.Commands}。
 * <p>原文：For removal, see {@link io.papermc.paper.command.brigadier.Commands} on how to use the new Brigadier API.
 */
@Deprecated(forRemoval = true)
public interface BukkitBrigadierCommandSource {

    @Nullable
    Entity getBukkitEntity();

    @Nullable
    World getBukkitWorld();

    @Nullable
    Location getBukkitLocation();

    CommandSender getBukkitSender();
}
