package org.bukkit;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示来自幽匿感测体的振动。
 * <p>原文：
 * Represents a vibration from a Skulk sensor.
 */
public class Vibration {

    private final Location origin;
    private final Destination destination;
    private final int arrivalTime;

    public Vibration(@NotNull Destination destination, @NotNull int arrivalTime) {
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.origin = new Location(null, 0, 0, 0); // Dummy origin because getter expects not null
    }

    @Deprecated(forRemoval = true)
    public Vibration(@NotNull Location origin, @NotNull Destination destination, int arrivalTime) {
        this.origin = origin;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取振动的起源位置。
     * <p>原文：
     * Get the origin of the vibration.
     *
     * @deprecated unused as of 1.19
     * @return 起源位置
     * <p>
     * 原文：Get the origin of the vibration.
     * <p>原文：origin
     */
    @NotNull
    @Deprecated(forRemoval = true) // Paper
    public Location getOrigin() {
        return origin;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取振动的目的地。
     * <p>原文：
     * Get the vibration destination.
     *
     * @return 目的地
     * <p>
     * 原文：Get the vibration destination.
     * <p>原文：destination
     */
    @NotNull
    public Destination getDestination() {
        return destination;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取振动到达时间（以刻为单位）。
     * <p>原文：
     * Get the vibration arrival time in ticks.
     *
     * @return 到达时间
     * <p>
     * 原文：Get the vibration arrival time in ticks.
     * <p>原文：arrival time
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    public interface Destination {

        public static class EntityDestination implements Destination {

            private final Entity entity;

            public EntityDestination(@NotNull Entity entity) {
                this.entity = entity;
            }

            @NotNull
            public Entity getEntity() {
                return entity;
            }
        }

        public static class BlockDestination implements Destination {

            private final Location block;

            public BlockDestination(@NotNull Location block) {
                this.block = block.clone();
            }

            public BlockDestination(@NotNull Block block) {
                this(block.getLocation());
            }

            @NotNull
            public Location getLocation() {
                return block.clone();
            }

            @NotNull
            public Block getBlock() {
                return block.getBlock();
            }
        }
    }
}
