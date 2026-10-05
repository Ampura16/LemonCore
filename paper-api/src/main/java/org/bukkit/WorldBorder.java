package org.bukkit;

import com.google.common.base.Preconditions;
import io.papermc.paper.util.Tick;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public interface WorldBorder {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取边界所在的 {@link World}.
     * <p>原文：
     * Get the {@link World} in which the border resides.
     *
     * @return 关联的世界，如果此世界边界未关联任何特定世界则返回 null，例如通过 {@link Server#createWorldBorder()} 创建的边界
     * <p>原文：the associated world, or null if this world border is not associated
     * with any specific world, such as those created via {@link Server#createWorldBorder()}
     */
    @Nullable World getWorld();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将世界的边界恢复为默认值.
     * <p>原文：
     * Resets the border to default values.
     */
    void reset();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得指定世界当前的边界长度.
     * <p>原文：
     * Gets the current side length of the border.
     *
     * @return 世界的当前边界长度.
     * <p>原文：The current side length of the border.
     */
    double getSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以方块为单位，将边界设置为一个指定长度的正方形.
     * <p>原文：
     * Sets the border to a square region with the specified side length in blocks.
     *
     * @param newSize 边界的新长度.
     * <p>原文：The new size of the border.
     * @throws IllegalArgumentException 如果 newSize 小于 1.0D 或大于 {@link #getMaxSize()}
     * <p>原文：if newSize is less than 1.0D or greater than {@link #getMaxSize()}
     * @see #changeSize(double, long)
     */
    void setSize(double newSize);

    /**
     * Sets the border to a square region with the specified side length in blocks.
     *
     * @param newSize The new side length of the border.
     * @param ticks The time in ticks in which the border grows or shrinks from the previous size to that being set.
     *
     * @throws IllegalArgumentException if newSize is less than 1.0D or greater than {@link #getMaxSize()}
     * @throws IllegalArgumentException if ticks is out of range
     * @see #setSize(double)
     */
    void changeSize(double newSize, @Range(from = 0, to = Integer.MAX_VALUE) long ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以方块为单位，将边界设置为一个指定长度的正方形.
     * <p>原文：
     * Sets the border to a square region with the specified side length in blocks.
     *
     * @param newSize 边界的新长度.
     * <p>原文：The new side length of the border.
     * @param seconds 边界从先前大小增长或缩小到新大小所需的时间（秒）.
     * <p>原文：The time in seconds in which the border grows or shrinks from the previous size to that being set.
     * @throws IllegalArgumentException 如果 newSize 小于 1.0D 或大于 {@link #getMaxSize()}
     * <p>原文：if seconds is out of range once converted in ticks
     * @deprecated Use {@link #changeSize(double, long)} instead
     */
    @Deprecated(since = "1.21.11", forRemoval = true)
    default void setSize(double newSize, long seconds) {
        this.changeSize(Math.clamp(newSize, 1.0D, this.getMaxSize()), Tick.tick().fromDuration(Duration.ofSeconds(seconds)));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以方块为单位，将边界设置为一个指定长度的正方形.
     * <p>原文：
     * Sets the border to a square region with the specified side length in blocks.
     *
     * @param newSize 边界的新长度.
     * <p>原文：The new side length of the border.
     * @param unit 时间单位.
     * <p>原文：The time unit.
     * @param time 边界从先前大小增长或缩小到新大小所需的时间.
     * <p>原文：The time in which the border grows or shrinks from the previous size to that being set.
     * @throws IllegalArgumentException 如果 unit 为 <code>null</code>，或 newSize 小于 1.0D 或大于 {@link #getMaxSize()}
     * <p>原文：if newSize is less than 1.0D or greater than {@link #getMaxSize()}
     * @throws IllegalArgumentException 如果 unit 为 <code>null</code>，或 newSize 小于 1.0D 或大于 {@link #getMaxSize()}
     * <p>原文：if time is out of range once converted in ticks
     * @deprecated Use {@link #changeSize(double, long)} instead
     */
    @Deprecated(since = "1.21.11", forRemoval = true)
    default void setSize(double newSize, @NotNull TimeUnit unit, @NonNegative long time) {
        Preconditions.checkArgument(unit != null, "TimeUnit cannot be null.");
        this.changeSize(newSize, Tick.tick().fromDuration(Duration.of(time, unit.toChronoUnit())));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到当前边界的中心.
     * <p>原文：
     * Gets the current border center.
     *
     * @return 当前边界的中心.
     * <p>原文：The current border center.
     */
    @NotNull Location getCenter();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置新的边界中心.
     * <p>原文：
     * Sets the new border center.
     *
     * @param x 新中心的x坐标.
     * <p>原文：The new center x-coordinate.
     * @param z 新中心的z坐标.
     * <p>原文：The new center z-coordinate.
     * @throws IllegalArgumentException 如果 x 或 z 的绝对值大于 {@link #getMaxCenterCoordinate()}
     * <p>原文：if the absolute value of x or z is higher than {@link #getMaxCenterCoordinate()}
     */
    void setCenter(double x, double z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置新的边界中心
     * <p>原文：
     * Sets the new border center.
     *
     * @param location 边界新中心的位置. (该位置只包含x z)
     * <p>原文：The new location of the border center. (Only x/z used)
     * @throws IllegalArgumentException 如果 location 为 <code>null</code>，或 {@link Location#getX()} 或 {@link Location#getZ()} 的绝对值大于 {@link #getMaxCenterCoordinate()}
     * <p>原文：if location is <code>null</code> or the absolute value of {@link Location#getX()} or {@link Location#getZ()} is higher than {@link #getMaxCenterCoordinate()}
     */
    void setCenter(@NotNull Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到当前边界的破坏缓冲.
     * <p>原文：
     * Gets the current border damage buffer.
     *
     * @return 当前边界的破坏缓存.
     * <p>原文：The current border damage buffer.
     */
    double getDamageBuffer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家在越过结界后几个方块后受到伤害（也就是玩家越界后安全的范围），切忌填写负数参数
     * <p>原文：
     * Sets the amount of blocks a player may safely be outside the border before taking damage.
     *
     * @param blocks 越过结界的缓冲方块数量，如果不设置的话默认是5个方块
     * <p>原文：The amount of blocks. (The default is 5 blocks.)
     */
    void setDamageBuffer(double blocks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取越过结界后所受到的伤害值
     * <p>原文：
     * Gets the current border damage amount.
     *
     * @return 返回越界后受到的伤害值.
     * <p>原文：The current border damage amount.
     */
    double getDamageAmount();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置越界后所受到的伤害值
     * <p>原文：
     * Sets the amount of damage a player takes when outside the border plus the border buffer.
     *
     * @param  damage 要设置的伤害值，默认的是超过一个方块一秒受到0.2伤害
     * <p>原文：The amount of damage. (The default is 0.2 damage per second per block.)
     */
    void setDamageAmount(double damage);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取临近结界的时候屏幕变红警告玩家的时间
     * <p>原文：
     * Gets the current border warning time in seconds.
     *
     * @return 接近结界的时候警告玩家的秒数.
     * <p>原文：The current border warning time in seconds.
     * @deprecated Use {@link #getWarningTimeTicks()} instead
     */
    @Deprecated(since = "1.21.11", forRemoval = true)
    default @NonNegative int getWarningTime() {
        return (int) Tick.of(this.getWarningTimeTicks()).toSeconds();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家接近结界的时候警告玩家的秒数（警告也就是当玩家接近结界的时候，玩家的屏幕会被渲染成红色边框以此来警告玩家不要越界）
     * <p>原文：
     * Sets the warning time that causes the screen to be tinted red when a contracting border will reach the player within the specified time.
     *
     * @param seconds 警告玩家的秒数，默认是15秒钟
     * <p>原文：The amount of time in seconds.
     * @deprecated Use {@link #setWarningTimeTicks(int)} instead
     */
    @Deprecated(since = "1.21.11", forRemoval = true)
    default void setWarningTime(@NonNegative int seconds) {
        Preconditions.checkArgument(seconds >= 0, "seconds cannot be lower than 0");

        this.setWarningTimeTicks(Tick.tick().fromDuration(Duration.ofSeconds(seconds)));
    }

    /**
     * Gets the current border warning time in ticks.
     *
     * @return The current border warning time in ticks.
     */
    @NonNegative int getWarningTimeTicks();

    /**
     * Sets the warning time that causes the screen to be tinted red when a contracting border will reach the player within the specified time.
     *
     * @param ticks The number of ticks.
     */
    void setWarningTimeTicks(@NonNegative int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家离方块多远时会被警告
     * <p>原文：
     * Gets the current border warning distance.
     *
     * @return 离结界多少方块的时候会被警告
     * <p>原文：The current border warning distance.
     */
    int getWarningDistance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家离结界几个方块的时候会被警告
     * <p>原文：
     * Sets the warning distance that causes the screen to be tinted red when the player is within the specified number of blocks from the border.
     *
     * @param distance 要设置玩家会被警告的距离
     * <p>原文：The distance in blocks. (The default is 5 blocks.)
     */
    void setWarningDistance(int distance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查指定的位置是否在这个边界里面.
     * <p>原文：
     * Check if the specified location is inside this border.
     *
     * @param location 要检查的位置
     * <p>原文：the location to check
     * @return 指定的位置是否在这个边界里面
     * <p>原文：if this location is inside the border or not
     */
    boolean isInside(@NotNull Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界边界的最大可能大小.
     * <p>原文：
     * Gets the maximum possible size of a WorldBorder.
     *
     * @return 世界边界的最大大小
     * <p>原文：The maximum size the WorldBorder
     */
    double getMaxSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界边界最大 x/z 中心坐标的绝对值.
     * <p>原文：
     * Gets the absolute value of the maximum x/z center coordinate of a
     * WorldBorder.
     *
     * @return 世界边界的最大中心坐标绝对值
     * <p>原文：The absolute maximum center coordinate of the WorldBorder
     */
    double getMaxCenterCoordinate();
}
