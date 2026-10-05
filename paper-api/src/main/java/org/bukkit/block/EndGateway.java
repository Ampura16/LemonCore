package org.bukkit.block;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表末地折跃门.
 * <p>原文：
 * Represents a captured state of an end gateway.
 */
public interface EndGateway extends TileState {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当实体进入折跃门时被传送到的位置.
     * <p>
     * 如果本方块尚未放置, 位置对象中的 'world' 将为 null.
     * <p>原文：
     * Gets the location that entities are teleported to when
     * entering the gateway portal.
     * <p>
     * If this block state is not placed the location's world will be null.
     *
     * @return 折跃门传送目的地
     * <p>原文：the gateway exit location
     */
    @Nullable
    Location getExitLocation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置进入折跃门时传送的位置.
     * <p>
     * 如果本方块尚未放置, 位置对象中的 'world' 必须为 null.
     * <p>原文：
     * Sets the exit location that entities are teleported to when
     * they enter the gateway portal.
     * <p>
     * If this block state is not placed the location's world has to be null.
     *
     * @param location 新传送地点
     * <p>原文：the new exit location
     * @throws IllegalArgumentException 方块所在世界与设置的世界不同
     * <p>原文：for differing worlds
     */
    void setExitLocation(@Nullable Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取折跃门是否精确传送实体至指定位置, 而不是传送到目标位置附近.
     * <p>原文：
     * Gets whether this gateway will teleport entities directly to
     * the exit location instead of finding a nearby location.
     *
     * @return 是否精确传送
     * <p>原文：true if the gateway is teleporting to the exact location
     */
    boolean isExactTeleport();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置折跃门是否精确传送实体至指定位置, 而不是传送到目标位置附近.
     * <p>原文：
     * Sets whether this gateway will teleport entities directly to
     * the exit location instead of finding a nearby location.
     *
     * @param exact 是否精确传送
     * <p>原文：whether to teleport to the exact location
     */
    void setExactTeleport(boolean exact);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取末地折跃门方块的年龄 (以 tick 为单位).
     * <br>
     * 如果年龄小于200 tick, 折跃门会发出一束品红色光束.
     * 同时折跃门每隔2400 tick(约2分钟)发出一束紫色光束.
     * <p>原文：
     * Gets the age in ticks of the gateway.
     * <br>
     * If the age is less than 200 ticks a magenta beam will be emitted, whilst
     * if it is a multiple of 2400 ticks a purple beam will be emitted.
     *
     * @return 年龄
     * <p>原文：age in ticks
     */
    long getAge();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置末地折跃门方块的年龄 (以 tick 为单位).
     * <br>
     * 如果年龄小于200 tick, 折跃门会发出一束品红色光束.
     * 同时折跃门每隔2400 tick(约2分钟)发出一束紫色光束.
     * <p>原文：
     * Sets the age in ticks of the gateway.
     * <br>
     * If the age is less than 200 ticks a magenta beam will be emitted, whilst
     * if it is a multiple of 2400 ticks a purple beam will be emitted.
     *
     * @param age 年龄
     * <p>原文：new age in ticks
     */
    void setAge(long age);
}
