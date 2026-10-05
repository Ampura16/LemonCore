package org.bukkit.event.raid;

import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.event.world.WorldEvent;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 与袭击有关的事件.
 * <p>原文：
 * Represents events related to raids.
 */
public abstract class RaidEvent extends WorldEvent {

    private final Raid raid;

    protected RaidEvent(@NotNull Raid raid, @NotNull World world) {
        super(world);
        this.raid = raid;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回本事件所涉及的袭击.
     * <p>原文：
     * Returns the raid involved with this event.
     *
     * @return 袭击
     * <p>原文：the raid
     */
    @NotNull
    public Raid getRaid() {
        return this.raid;
    }
}
