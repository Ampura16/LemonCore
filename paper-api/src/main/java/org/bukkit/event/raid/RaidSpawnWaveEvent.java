package org.bukkit.event.raid;

import java.util.Collections;
import java.util.List;
import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.entity.Raider;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当新一波袭击开始时触发本事件.
 * <p>原文：
 * Called when a raid wave spawns.
 */
public class RaidSpawnWaveEvent extends RaidEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final List<Raider> raiders;
    private final Raider leader;

    @ApiStatus.Internal
    public RaidSpawnWaveEvent(@NotNull Raid raid, @NotNull World world, @NotNull Raider leader, @NotNull List<Raider> raiders) {
        super(raid, world);
        this.raiders = raiders;
        this.leader = leader;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回本波袭击中已生成的所有{@link Raider 袭击者}.
     * <p>原文：
     * Returns all {@link Raider} that spawned in this wave.
     *
     * @return 不可变的{@link Raider 袭击者}的列表
     * <p>原文：an immutable list of raiders
     */
    @NotNull
    public @Unmodifiable List<Raider> getRaiders() {
        return Collections.unmodifiableList(this.raiders);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这一波袭击中的袭击队长.
     * <p>原文：
     * Returns the patrol leader.
     *
     * @return {@link Raider 袭击队长实体}
     * <p>原文：{@link Raider}
     */
    @NotNull
    public Raider getPatrolLeader() {
        return this.leader;
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
