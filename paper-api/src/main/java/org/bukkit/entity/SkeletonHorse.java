package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表骷髅马 - {@link AbstractHorse} 的变种。
 * <p>原文：
 * Represents a SkeletonHorse - variant of {@link AbstractHorse}.
 */
public interface SkeletonHorse extends AbstractHorse {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此骷髅马是否被困住。
     * <p>
     * 当马被困住且玩家进入其10格范围内时，闪电会击中该马。
     * 被击中后，骷髅陷阱将激活，将马变成骷髅骑手，并在附近生成三个额外的骑手。
     * <p>原文：
     * Returns whether this skeleton horse is trapped.
     * <p>
     * When a horse is trapped and a player comes within 10 blocks of a trapped
     * horse, lightning will strike the horse. When struck, the skeleton trap
     * will activate, turning the horse into a skeleton horseman as well as
     * spawning three additional horsemen nearby.
     *
     * @return 如果被困住则为true
     * <p>原文：true if trapped
     */
    boolean isTrapped();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此骷髅马是否被困住。
     * <p>原文：
     * Sets if this skeleton horse is trapped.
     *
     * @param trapped 新的被困状态
     * <p>原文：new trapped state
     */
    void setTrapped(boolean trapped);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回马当前的陷阱时间（以tick为单位）。
     * <p>
     * 当 {@link #isTrapped()} 为true时，陷阱时间每tick增加。
     * 当达到18000 tick时，马将自动消失。
     * <p>原文：
     * Returns the horse's current trap time in ticks.
     *
     * Trap time is incremented every tick when {@link #isTrapped()} is true.
     * The horse automatically despawns when it reaches 18000 ticks.
     *
     * @return 当前陷阱时间
     * <p>原文：current trap time
     */
    int getTrapTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置马的陷阱时间。
     * <p>
     * 大于18000的值将导致马在下一个tick消失。
     * <p>原文：
     * Sets the trap time for the horse.
     *
     * Values greater than 18000 will cause the horse to despawn on the next
     * tick.
     *
     * @param trapTime 新的陷阱时间
     * <p>原文：new trap time
     */
    void setTrapTime(int trapTime);

    /**
     * @deprecated use {@link #isTrapped()}
     */
    @Deprecated
    default boolean isTrap() {
        return this.isTrapped();
    }

    /**
     * @deprecated use {@link #setTrapped(boolean)}
     */
    @Deprecated
    default void setTrap(boolean trap) {
        this.setTrapped(trap);
    }
}
