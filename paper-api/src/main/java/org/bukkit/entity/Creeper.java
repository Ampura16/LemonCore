package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表苦力怕.
 * <p>原文：
 * Represents a Creeper
 */
public interface Creeper extends Monster {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查这个苦力怕是否为高压的(触电).
     * <p>原文：
     * Checks if this Creeper is powered (Electrocuted)
     *
     * @return true if 如果苦力怕是高压的则为true
     * <p>原文：true if this creeper is powered
     */
    public boolean isPowered();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个苦力怕的高压状态.
     * <p>原文：
     * Sets the Powered status of this Creeper
     *
     * @param value 新的高压状态
     * <p>原文：New Powered status
     */
    public void setPowered(boolean value);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为苦力怕设置点燃tick值, 此tick值为苦力怕爆炸前点燃状态的维持时间.
     * <p>原文：
     * Set the maximum fuse ticks for this Creeper, where the maximum ticks
     * is the amount of time in which a creeper is allowed to be in the
     * primed state before exploding.
     *
     * @param ticks 新的点燃状态的最大tick值
     * <p>原文：the new maximum fuse ticks
     */
    public void setMaxFuseTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此苦力怕的点燃状态最大tick值, 此tick值为苦力怕爆炸前点燃状态的维持时间.
     * <p>原文：
     * Get the maximum fuse ticks for this Creeper, where the maximum ticks
     * is the amount of time in which a creeper is allowed to be in the
     * primed state before exploding.
     *
     * @return 点燃状态的最大tick值
     * <p>原文：the maximum fuse ticks
     */
    public int getMaxFuseTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置苦力怕点燃状态的tick值,此tick值为苦力怕爆炸前点燃状态的维持时间.
     * <p>原文：
     * Set the fuse ticks for this Creeper, where the ticks is the amount of
     * time in which a creeper has been in the primed state.
     *
     * @param ticks 新的点燃状态的tick值
     * <p>原文：the new fuse ticks
     */
    public void setFuseTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得苦力怕点燃状态的tick值,此tick值为苦力怕爆炸前点燃状态的维持时间.
     * <p>原文：
     * Get the maximum fuse ticks for this Creeper, where the ticks is the
     * amount of time in which a creeper has been in the primed state.
     *
     * @return 点燃状态的tick值
     * <p>原文：the fuse ticks
     */
    public int getFuseTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置苦力怕的爆炸半径.
     * <p>原文：
     * Set the explosion radius in which this Creeper's explosion will affect.
     *
     * @param radius 新的爆炸半径
     * <p>原文：the new explosion radius
     */
    public void setExplosionRadius(int radius);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得苦力怕的爆炸半径.
     * <p>原文：
     * Get the explosion radius in which this Creeper's explosion will affect.
     *
     * @return 爆炸半径
     * <p>原文：the explosion radius
     */
    public int getExplosionRadius();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使该苦力怕立刻爆炸.
     * <br>
     * 使用本方法引起的爆炸可被{@link org.bukkit.event.entity.ExplosionPrimeEvent}取消,
     * 并且遵循其所处世界的{@link org.bukkit.GameRule#MOB_GRIEFING mobGriefing游戏规则}.
     * <p>原文：
     * Makes this Creeper explode instantly.
     * <br>
     * The resulting explosion can be cancelled by an
     * {@link org.bukkit.event.entity.ExplosionPrimeEvent} and obeys the mob
     * griefing gamerule.
     */
    public void explode();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 点燃此苦力怕, 开始其爆炸计时.
     * <br>
     * 苦力怕从点燃到爆炸所需时间取决于{@link #setMaxFuseTicks}的设置,默认值为30.
     * 使用本方法引起的爆炸可被{@link org.bukkit.event.entity.ExplosionPrimeEvent}取消,
     * 并且遵循其所处世界的{@link org.bukkit.GameRule#MOB_GRIEFING mobGriefing游戏规则}.
     * <p>原文：
     * Ignites this Creeper, beginning its fuse.
     * <br>
     * The amount of time the Creeper takes to explode will depend on what
     * {@link #setMaxFuseTicks} is set as.
     * <br>
     * The resulting explosion can be cancelled by an
     * {@link org.bukkit.event.entity.ExplosionPrimeEvent} and obeys the mob
     * griefing gamerule.
     *
     * @param entity 点燃苦力怕的实体
     * <p>原文：the entity which ignited the creeper
     */
    public void ignite(@NotNull Entity entity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 点燃此苦力怕, 开始其爆炸计时.
     * <br>
     * 苦力怕从点燃到爆炸所需时间取决于{@link #setMaxFuseTicks}的设置,默认值为30.
     * 使用本方法引起的爆炸可被{@link org.bukkit.event.entity.ExplosionPrimeEvent}取消,
     * 并且遵循其所处世界的{@link org.bukkit.GameRule#MOB_GRIEFING mobGriefing游戏规则}.
     * <p>原文：
     * Ignites this Creeper, beginning its fuse.
     * <br>
     * The amount of time the Creeper takes to explode will depend on what
     * {@link #setMaxFuseTicks} is set as.
     * <br>
     * The resulting explosion can be cancelled by an
     * {@link org.bukkit.event.entity.ExplosionPrimeEvent} and obeys the mob
     * griefing gamerule.
     */
    public void ignite();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取点燃此苦力怕的实体（如存在）.
     * <p>原文：
     * Gets the entity which ignited the creeper, if available.
     *
     * @return 点燃此苦力怕的实体，不存在返回 null
     * <p>原文：the entity which ignited the creeper (if available) or null
     */
    @Nullable
    public Entity getIgniter();
    // Paper start

    /**
     * Set whether creeper is ignited or not (armed to explode)
     *
     * @param ignited New ignited state
     */
    public void setIgnited(boolean ignited);

    /**
     * Check if creeper is ignited or not (armed to explode)
     *
     * @return Ignited state
     */
    public boolean isIgnited();
    // Paper end
}
