package org.bukkit.block;

import java.util.Collection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.spawner.TrialSpawnerConfiguration;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个捕获的试炼刷怪笼状态.
 * <p>原文：
 * Represents a captured state of a trial spawner.
 */
@NullMarked
public interface TrialSpawner extends TileState {

    /**
     * Gets the game time in ticks when the cooldown ends. 0 if not currently in cooldown.
     *
     * @return the game time in ticks
     * @see org.bukkit.World#getGameTime()
     */
    long getCooldownEnd();

    /**
     * Sets the game time in ticks when the cooldown ends.
     *
     * @param ticks the game time in ticks for the new cooldown
     */
    void setCooldownEnd(long ticks);

    /**
     * Gets the game time in ticks when the next spawn attempt happens. 0 if not currently active.
     *
     * @return the game time in ticks
     * @see org.bukkit.World#getGameTime()
     */
    long getNextSpawnAttempt();

    /**
     * Sets the game time in ticks when the next spawn attempt happens.
     *
     * @param ticks the game time in ticks for the next mob spawn
     */
    void setNextSpawnAttempt(long ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取刷怪笼冷却状态的持续时间(以游戏刻为单位).
     * <p>原文：
     * Gets the length in ticks the spawner will stay in cooldown for.
     *
     * @return 游戏刻数量
     * <p>原文：the number of ticks
     */
    int getCooldownLength();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置刷怪笼冷却状态的持续时间(以游戏刻为单位).
     * <p>原文：
     * Sets the length in ticks the spawner will stay in cooldown for.
     *
     * @param ticks 游戏刻数量
     * <p>原文：the number of ticks
     */
    void setCooldownLength(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家必须在此范围内才能使此刷怪笼激活的最大距离.
     * <br>
     * 如果此值小于或等于0, 则此刷怪笼始终处于激活状态(前提是有玩家在线).
     * <br>
     * 默认值为16.
     * <p>原文：
     * Get the maximum distance(squared) a player can be in order for this
     * spawner to be active.
     * <br>
     * If this value is less than or equal to 0, this spawner is always active
     * (given that there are players online).
     * <br>
     * Default value is 16.
     *
     * @return 玩家必须在此范围内才能使此刷怪笼激活的最大距离
     * <p>原文：the maximum distance(squared) a player can be in order for this
     * spawner to be active.
     */
    int getRequiredPlayerRange();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家必须在此范围内才能使此刷怪笼激活的最大距离.
     * <br>
     * 将此值设置为小于或等于0将使此刷怪笼始终处于激活状态(前提是有玩家在线).
     * <p>原文：
     * Set the maximum distance (squared) a player can be in order for this
     * spawner to be active.
     * <br>
     * Setting this value to less than or equal to 0 will make this spawner
     * always active (given that there are players online).
     *
     * @param requiredPlayerRange 玩家必须在此范围内才能使此刷怪笼激活的最大距离
     * <p>原文：the maximum distance (squared) a player can be
     * in order for this spawner to be active.
     */
    void setRequiredPlayerRange(int requiredPlayerRange);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此刷怪笼当前正在追踪的玩家.
     * <p>
     * <b>注意:</b> 返回的集合是不可变的, 请使用
     * {@link #startTrackingPlayer(Player)} 或 {@link #stopTrackingPlayer(Player)}
     * 代替.
     * <p>原文：
     * Gets the players this spawner is currently tracking.
     * <p>
     * <b>Note:</b> the returned collection is immutable, use
     * {@link #startTrackingPlayer(Player)} or {@link #stopTrackingPlayer(Player)}
     * instead.
     *
     * @return 此刷怪笼正在追踪的玩家集合, 如果没有则为空集合
     * <p>原文：a collection of players this spawner is tracking or an empty
     *         collection if there aren't any
     */
    Collection<Player> getTrackedPlayers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此刷怪笼当前是否正在追踪指定的玩家.
     * <p>原文：
     * Checks if this spawner is currently tracking the provided player.
     *
     * @param player 玩家
     * <p>原文：the player
     * @return 如果此刷怪笼正在追踪指定的玩家则返回true
     * <p>原文：true if this spawner is tracking the provided player
     */
    boolean isTrackingPlayer(final Player player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 强制此刷怪笼开始追踪指定的玩家.
     * <p>
     * <b>注意:</b> 刷怪笼可能在任何时候决定停止追踪此玩家.
     * <p>原文：
     * Force this spawner to start tracking the provided player.
     * <p>
     * <b>Note:</b> the spawner may decide to stop tracking this player at any given
     * time.
     *
     * @param player 玩家
     * <p>原文：the player
     */
    void startTrackingPlayer(final Player player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 强制此刷怪笼停止追踪指定的玩家.
     * <p>
     * <b>注意:</b> 刷怪笼可能在任何时候决定重新开始追踪此玩家.
     * <p>原文：
     * Force this spawner to stop tracking the provided player.
     * <p>
     * <b>Note:</b> the spawner may decide to start tracking this player again at
     * any given time.
     *
     * @param player 玩家
     * <p>原文：the player
     */
    void stopTrackingPlayer(final Player player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此刷怪笼当前正在追踪的实体列表.
     * <p>
     * <b>注意:</b> 返回的集合是不可变的, 请使用
     * {@link #startTrackingEntity(Entity)} 或 {@link #stopTrackingEntity(Entity)}
     * 代替.
     * <p>原文：
     * Gets a list of entities this spawner is currently tracking.
     * <p>
     * <b>Note:</b> the returned collection is immutable, use
     * {@link #startTrackingEntity(Entity)} or {@link #stopTrackingEntity(Entity)}
     * instead.
     *
     * @return 此刷怪笼正在追踪的实体集合, 如果没有则为空集合
     * <p>原文：a collection of entities this spawner is tracking or an empty
     *         collection if there aren't any
     */
    Collection<Entity> getTrackedEntities();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此刷怪笼当前是否正在追踪指定的实体.
     * <p>原文：
     * Checks if this spawner is currently tracking the provided entity.
     *
     * @param entity 实体
     * <p>原文：the entity
     * @return 如果此刷怪笼正在追踪指定的实体则返回true
     * <p>原文：true if this spawner is tracking the provided entity
     */
    boolean isTrackingEntity(final Entity entity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 强制此刷怪笼开始追踪指定的实体.
     * <p>
     * <b>注意:</b> 刷怪笼可能在任何时候决定停止追踪此实体.
     * <p>原文：
     * Force this spawner to start tracking the provided entity.
     * <p>
     * <b>Note:</b> the spawner may decide to stop tracking this entity at any given
     * time.
     *
     * @param entity 实体
     * <p>原文：the entity
     */
    void startTrackingEntity(final Entity entity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 强制此刷怪笼停止追踪指定的实体.
     * <p>
     * <b>注意:</b> 刷怪笼可能在任何时候决定重新开始追踪此实体.
     * <p>原文：
     * Force this spawner to stop tracking the provided entity.
     * <p>
     * <b>Note:</b> the spawner may decide to start tracking this entity again at
     * any given time.
     *
     * @param entity 实体
     * <p>原文：the entity
     */
    void stopTrackingEntity(final Entity entity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此刷怪笼是否正在使用不祥的
     * {@link TrialSpawnerConfiguration}.
     * <p>原文：
     * Checks if this spawner is using the ominous
     * {@link TrialSpawnerConfiguration}.
     *
     * @return 如果正在使用不祥配置则返回true
     * <p>原文：true is using the ominous configuration
     */
    boolean isOminous();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在普通和不祥的{@link TrialSpawnerConfiguration}之间切换此刷怪笼.
     * <p>原文：
     * Changes this spawner between the normal and ominous
     * {@link TrialSpawnerConfiguration}.
     *
     * @param ominous true表示使用不祥的TrialSpawnerConfiguration, false表示
     *                使用普通的配置
     * <p>原文：true to use the ominous TrialSpawnerConfiguration, false to
     *                use the normal one.
     */
    void setOminous(boolean ominous);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当{@link #isOminous()}为false时使用的{@link TrialSpawnerConfiguration}.
     * <p>原文：
     * Gets the {@link TrialSpawnerConfiguration} used when {@link #isOminous()} is
     * false.
     *
     * @return the TrialSpawnerConfiguration
     */
    @ApiStatus.Experimental
    TrialSpawnerConfiguration getNormalConfiguration();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当{@link #isOminous()}为true时使用的{@link TrialSpawnerConfiguration}.
     * <p>原文：
     * Gets the {@link TrialSpawnerConfiguration} used when {@link #isOminous()} is
     * true.
     *
     * @return the TrialSpawnerConfiguration
     */
    @ApiStatus.Experimental
    TrialSpawnerConfiguration getOminousConfiguration();
}
