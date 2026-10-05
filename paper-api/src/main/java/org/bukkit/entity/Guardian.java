package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 守卫海底遗迹的守卫者们.
 */
public interface Guardian extends Monster {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置守卫者的激光是否显示。
     *
     * 必须存在一个目标。如果没有目标，激光将不会显示，且此方法将返回 false。
     * <p>原文：
     * Sets whether the guardian laser should show or not.
     *
     * A target must be present. If no target is present the laser will not show
     * and the method will return false.
     *
     * @param activated 激光是否激活
     * <p>原文：whether the laser is active
     * @return 如果激光已激活则返回 true，否则返回 false
     * <p>原文：true if the laser was activated otherwise false
     * @see #getTarget()
     * @see #setTarget(LivingEntity)
     */
    boolean setLaser(boolean activated);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取守卫者的激光是否处于激活状态。
     * <p>原文：
     * Gets whether the guardian laser is active or not.
     *
     * @return 如果激光处于激活状态则返回 true，否则返回 false
     * <p>原文：true if the laser is active otherwise false
     */
    boolean hasLaser();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取激光攻击持续的时长（以 tick 为单位）。
     * <p>原文：
     * Get the duration (in ticks) that a laser attack takes.
     *
     * @return 激光持续时间（以 tick 为单位）
     * <p>原文：the laser duration in ticks
     */
    public int getLaserDuration();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置自守卫者发起激光攻击以来经过的 tick 数。如果设置为
     * {@link #getLaserDuration()} 或更大的值，守卫者将对其目标造成伤害，
     * 且激光攻击将完成。
     * <p>
     * 要使此值产生任何效果，守卫者必须有一个活动目标（参见
     * {@link #setTarget(LivingEntity)}）并且正在蓄力激光攻击（即
     * {@link #hasLaser()} 返回 true）。客户端可能显示与设置的 tick 数
     * 不同的守卫者激光动画。
     * <p>原文：
     * Set the amount of ticks that have elapsed since this guardian has initiated
     * a laser attack. If set to {@link #getLaserDuration()} or greater, the guardian
     * will inflict damage upon its target and the laser attack will complete.
     * <p>
     * For this value to have any effect, the guardian must have an active target
     * (see {@link #setTarget(LivingEntity)}) and be charging a laser attack (where
     * {@link #hasLaser()} is true). The client may display a different animation of
     * the guardian laser than the set ticks.
     *
     * @param ticks 要设置的 tick 数，必须至少为 -10
     * <p>原文：the ticks to set. Must be at least -10
     */
    public void setLaserTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取自守卫者发起激光攻击以来经过的 tick 数。
     * <p>
     * 此值是否有意义取决于守卫者是否有一个活动目标（{@link #getTarget()}）
     * 以及是否正在蓄力激光攻击（{@link #hasLaser()}）。此值在攻击成功后
     * 不会重置，也不会在下一次攻击中使用，并且当守卫者发起新的攻击时将被
     * 重置为最小值。
     * <p>原文：
     * Get the amount of ticks that have elapsed since this guardian has initiated
     * a laser attack.
     * <p>
     * This value may or may not be significant depending on whether or not the guardian
     * has an active target ({@link #getTarget()}) and is charging a laser attack
     * ({@link #hasLaser()}). This value is not reset after a successful attack nor used
     * in the next and will be reset to the minimum value when the guardian initiates a
     * new one.
     *
     * @return 激光 tick 数，范围从 -10 到 {@link #getLaserDuration()}
     * <p>原文：the laser ticks ranging from -10 to {@link #getLaserDuration()}
     */
    public int getLaserTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测此守卫者是否为远古守卫者.
     * <p>原文：
     * Check if the Guardian is an elder Guardian
     *
     * @return 此守卫者是否为远古守卫者
     * <p>原文：true if the Guardian is an Elder Guardian, false if not
     * @deprecated 应检测此实例是否为{@link ElderGuardian} (entity instanceof ElderGuardian)
     * <p>原文：should check if instance of {@link ElderGuardian}.
     */
    @Deprecated(since = "1.10.2")
    public boolean isElder();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param shouldBeElder shouldBeElder
     * @deprecated <b>必须</b>生成新的 {@link ElderGuardian}
     * <p>原文：Must spawn a new {@link ElderGuardian}.
     */
    @Deprecated(since = "1.10.2")
    public void setElder(boolean shouldBeElder);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测此守卫者是否正在移动。
     * <p>
     * 在移动时，守卫者的尖刺会收起，不会对攻击它的实体造成荆棘伤害。
     * 此外，移动中的守卫者无法攻击其他实体。如果静止不动（即此方法返回
     * {@code false}），则必定会造成荆棘伤害，且守卫者可能发起激光攻击。
     * <p>原文：
     * Check whether or not this guardian is moving.
     * <p>
     * While moving, the guardian's spikes are retracted and will not inflict thorns
     * damage upon entities that attack it. Additionally, a moving guardian cannot
     * attack another entity. If stationary (i.e. this method returns {@code false}),
     * thorns damage is guaranteed and the guardian may initiate laser attacks.
     *
     * @return 如果正在移动则返回 true，如果静止则返回 false
     * <p>原文：true if moving, false if stationary
     */
    public boolean isMoving();
}
