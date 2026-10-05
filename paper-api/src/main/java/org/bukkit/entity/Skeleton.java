package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表骷髅.
 * <p>
 * 此接口仅代表服务器上的普通骷髅类型.
 * 其他类骷髅实体, 如 {@link WitherSkeleton} 或 {@link Stray} 与此类型无关.
 * <p>原文：
 * Represents a Skeleton.
 * <p>
 * This interface only represents the normal skeleton type on the server.
 * Other skeleton-like entities, such as the {@link WitherSkeleton} or the
 * {@link Stray} are not related to this type.
 */
public interface Skeleton extends AbstractSkeleton {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 计算此骷髅是否正在因被细雪冻结而转化为 {@link Stray}.
     * <p>原文：
     * Computes whether or not this skeleton is currently in the process of
     * converting to a {@link Stray} due to it being frozen by powdered snow.
     *
     * @return 此骷髅是否正在转化为流浪者
     * <p>原文：whether or not the skeleton is converting to a stray.
     */
    boolean isConverting();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体因被细雪方块冻结而转化为流浪者所需的剩余 tick 数.
     * <p>
     * 当此值降为 0 时, 该实体将被转化.
     * <p>原文：
     * Gets the amount of ticks until this entity will be converted to a stray
     * as a result of being frozen by a powdered snow block.
     * <p>
     * When this reaches 0, the entity will be converted.
     *
     * @return 以 tick 为单位的剩余转化时间
     * <p>原文：the conversion time left represented in ticks.
     * @throws IllegalStateException 如果 {@link #isConverting()} 返回 false
     * <p>原文：if {@link #isConverting()} is false.
     */
    int getConversionTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体因被细雪方块冻结而转化为流浪者所需的剩余 tick 数.
     * <p>
     * 当此值降为 0 时, 该实体将被转化. 小于 0 的值将停止当前的转化过程且不会转化当前实体.
     * <p>原文：
     * Sets the amount of ticks until this entity will be converted to a stray
     * as a result of being frozen by a powdered snow block.
     * <p>
     * When this reaches 0, the entity will be converted. A value of less than 0
     * will stop the current conversion process without converting the current
     * entity.
     *
     * @param time 以 tick 为单位的新转化剩余时间
     * <p>原文：the new conversion time left before the conversion in ticks.
     */
    void setConversionTime(int time);

    /**
     * Gets the time the skeleton
     * has been inside powdered snow.
     *
     * @return time in ticks
     */
    int inPowderedSnowTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 一个旧版枚举, 定义了服务器上类骷髅实体的不同变体.
     * <p>原文：
     * A legacy enum that defines the different variances of skeleton-like
     * entities on the server.
     *
     * @deprecated 各类骷髅已是不同的类型. 此接口仅保留在 Skeleton 接口中以保持向后兼容性.
     * <p>原文：classes are different types. This interface only remains in
     *     the Skeleton interface to preserve backwards compatibility.
     */
    @Deprecated(since = "1.11")
    public enum SkeletonType {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 标准骷髅类型.
         * <p>原文：
         * Standard skeleton type.
         */
        NORMAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 凋灵骷髅. 通常出现在下界要塞中.
         * <p>原文：
         * Wither skeleton. Generally found in Nether fortresses.
         */
        WITHER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 流浪者骷髅. 通常出现在冰原生物群系中. 会射出药箭.
         * <p>原文：
         * Stray skeleton. Generally found in ice biomes. Shoots tipped arrows.
         */
        STRAY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 沼骸骷髅.
         * <p>原文：
         * Bogged skeleton.
         */
        BOGGED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 枯骨骷髅.
         * <p>原文：
         * Parched skeleton.
         */
        PARCHED;
    }
}
