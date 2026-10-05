package org.bukkit.spawner;

import java.util.Collection;
import java.util.List;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.spawner.SpawnRule;
import org.bukkit.block.spawner.SpawnerEntry;
import org.bukkit.entity.EntitySnapshot;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个基础实体刷怪笼。<br>
 * 可能是 {@link SpawnerMinecart}、{@link CreatureSpawner} 或 {@link TrialSpawnerConfiguration}。
 * <p>原文：
 * Represents a basic entity spawner. <br>
 * May be a {@link SpawnerMinecart}, {@link CreatureSpawner} or {@link TrialSpawnerConfiguration}.
 */
@NullMarked
public interface BaseSpawner {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取刷出的生物的类型.
     * <p>原文：
     * Get the spawner's creature type.
     *
     * @return 生物类型, 如未设置返回 null
     * <p>原文：The creature type or null if it not set.
     */
    @Nullable
    EntityType getSpawnedType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置刷怪笼的生物类型。<br>
     * 这将覆盖任何通过 {@link #addPotentialSpawn} 添加的实体。
     * <p>原文：
     * Set the spawner's creature type. <br>
     * This will override any entities that have been added with {@link #addPotentialSpawn}
     *
     * @param creatureType 生物类型，设为 null 以清除。
     * <p>原文：The creature type or null to clear.
     */
    void setSpawnedType(@Nullable EntityType creatureType);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取刷怪笼的刷怪延迟.
     * <br>
     * 延迟时间以tick为单位.
     * <p>原文：
     * Get the spawner's delay.
     * <br>
     * This is the delay, in ticks, until the spawner will spawn its next mob.
     *
     * @return 延迟.
     * <p>原文：The delay.
     */
    int getDelay();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置刷怪笼刷怪延迟.
     * <p>原文：
     * Set the spawner's delay.
     *
     * @param delay 延迟.
     * <p>原文：The delay.
     */
    void setDelay(int delay);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家使刷怪笼激活所需的最远距离.
     * <br>
     * 如果该值小于或等于0, 则此刷怪笼永远处于激活状态.
     * <br>
     * 默认值为 16.
     * <p>原文：
     * Get the maximum distance(squared) a player can be in order for this
     * spawner to be active.
     * <br>
     * If this value is less than or equal to 0, this spawner is always active
     * (given that there are players online).
     * <br>
     * Default value is 16.
     *
     * @return 激活刷怪笼所需最远距离
     * <p>原文：the maximum distance(squared) a player can be in order for this
     * spawner to be active.
     */
    int getRequiredPlayerRange();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家使刷怪笼激活所需的最远距离.
     * <br>
     * 如果设置小于或等于0的值, 将使刷怪笼永远处于激活状态.
     * <p>原文：
     * Set the maximum distance (squared) a player can be in order for this
     * spawner to be active.
     * <br>
     * Setting this value to less than or equal to 0 will make this spawner
     * always active (given that there are players online).
     *
     * @param requiredPlayerRange 激活刷怪笼所需最远距离
     * <p>原文：the maximum distance (squared) a player can be
     * in order for this spawner to be active.
     */
    void setRequiredPlayerRange(int requiredPlayerRange);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取刷怪笼刷怪区域的半径.
     * <br>
     * 这个区域形为正方形, 以刷怪笼所在地为中心, 向 x,z 轴方向延伸(不含刷怪笼本身).
     * <br>
     * 这个区域以刷怪笼的y轴(它的底部)为中心算起, 高2个方块,
     * 这允许生物有足够空间在这个区域的顶部和底部平面下一格方块的区域内生成.
     * <br>
     * 默认值为 4.
     * <p>原文：
     * Get the radius around which the spawner will attempt to spawn mobs in.
     * <br>
     * This area is square, includes the block the spawner is in, and is
     * centered on the spawner's x,z coordinates - not the spawner itself.
     * <br>
     * It is 2 blocks high, centered on the spawner's y-coordinate (its bottom);
     * thus allowing mobs to spawn as high as its top surface and as low
     * as 1 block below its bottom surface.
     * <br>
     * Default value is 4.
     *
     * @return 刷怪半径
     * <p>原文：the spawn range
     */
    int getSpawnRange();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置新的刷怪半径.
     * <p>原文：
     * Set the new spawn range.
     * <br>
     *
     * @param spawnRange 半径
     * <p>原文：the new spawn range
     * @see #getSpawnRange()
     */
    void setSpawnRange(int spawnRange);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将由此刷怪笼生成的 {@link EntitySnapshot}，如果未为此刷怪笼分配任何实体则返回 null。<br>
     * <p>
     * 刷怪笼中所有适用的数据都将被复制，例如自定义名称、生命值和速度。<br>
     * <p>原文：
     * Gets the {@link EntitySnapshot} that will be spawned by this spawner or null
     * if no entities have been assigned to this spawner. <br>
     * <p>
     * All applicable data from the spawner will be copied, such as custom name,
     * health, and velocity. <br>
     *
     * @return 实体快照，如果未为此刷怪笼分配任何实体则返回 null。
     * <p>原文：the entity snapshot or null if no entities have been assigned to this
     *         spawner.
     */
    @Nullable
    EntitySnapshot getSpawnedEntity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将由此刷怪笼生成的实体。<br>
     * 这将覆盖任何先前通过 {@link #addPotentialSpawn} 添加的条目。
     * <p>
     * 快照中所有适用的数据都将被复制，例如自定义名称、生命值和速度。<br>
     * <p>原文：
     * Sets the entity that will be spawned by this spawner. <br>
     * This will override any previous entries that have been added with
     * {@link #addPotentialSpawn}
     * <p>
     * All applicable data from the snapshot will be copied, such as custom name,
     * health, and velocity. <br>
     *
     * @param snapshot 实体快照，设为 null 以清除。
     * <p>原文：the entity snapshot or null to clear
     */
    void setSpawnedEntity(@Nullable EntitySnapshot snapshot);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将由此刷怪笼生成的 {@link SpawnerEntry}。<br>
     * 这将覆盖任何先前通过 {@link #addPotentialSpawn} 添加的条目。
     * <p>原文：
     * Sets the {@link SpawnerEntry} that will be spawned by this spawner. <br>
     * This will override any previous entries that have been added with
     * {@link #addPotentialSpawn}
     *
     * @param spawnerEntry 要使用的刷怪笼条目。
     * <p>原文：the spawner entry to use
     */
    void setSpawnedEntity(SpawnerEntry spawnerEntry);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向此刷怪笼可生成的实体列表中添加一个新的 {@link EntitySnapshot}。
     * <p>
     * 权重将决定此条目被选中生成的频率，权重较高的条目将比权重较低的条目更频繁地生成。<br>
     * {@link SpawnRule} 将决定此条目在什么条件下可以生成，传入 null 将使用给定实体的默认条件。
     * <p>原文：
     * Adds a new {@link EntitySnapshot} to the list of entities this spawner can
     * spawn.
     * <p>
     * The weight will determine how often this entry is chosen to spawn, higher
     * weighted entries will spawn more often than lower-weighted ones. <br>
     * The {@link SpawnRule} will determine under what conditions this entry can
     * spawn, passing null will use the default conditions for the given entity.
     *
     * @param snapshot  将被生成的快照
     * <p>原文：the snapshot that will be spawned
     * @param weight    权重
     * <p>原文：the weight
     * @param spawnRule 此实体的生成规则，或 null
     * <p>原文：the spawn rule for this entity, or null
     */
    void addPotentialSpawn(EntitySnapshot snapshot, int weight, @Nullable SpawnRule spawnRule);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向此刷怪笼可生成的实体列表中添加一个新的 {@link SpawnerEntry}。
     * <p>原文：
     * Adds a new {@link SpawnerEntry} to the list of entities this spawner can
     * spawn.
     *
     * @param spawnerEntry 要使用的刷怪笼条目
     * <p>原文：the spawner entry to use
     * @see #addPotentialSpawn(EntitySnapshot, int, SpawnRule)
     */
    void addPotentialSpawn(final SpawnerEntry spawnerEntry);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此刷怪笼可生成的 {@link SpawnerEntry} 列表。<br>
     * 这将覆盖任何先前通过 {@link #addPotentialSpawn} 添加的条目。
     * <p>原文：
     * Sets the list of {@link SpawnerEntry} this spawner can spawn. <br>
     * This will override any previous entries added with
     * {@link #addPotentialSpawn}
     *
     * @param entries 条目列表
     * <p>原文：the list of entries
     */
    void setPotentialSpawns(final Collection<SpawnerEntry> entries);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此刷怪笼的潜在生成列表，如果未为此刷怪笼分配任何实体则返回空列表。<br>
     * 对返回列表所做的更改不会反映在刷怪笼中，除非通过 {@link #setPotentialSpawns} 应用。
     * <p>原文：
     * Gets a list of potential spawns from this spawner or an empty list if no
     * entities have been assigned to this spawner. <br>
     * Changes made to the returned list will not be reflected in the spawner unless
     * applied with {@link #setPotentialSpawns}
     *
     * @return 此刷怪笼的潜在生成列表，如果未为此刷怪笼分配任何实体则返回空列表
     * <p>原文：a list of potential spawns from this spawner, or an empty list if no
     *         entities have been assigned to this spawner
     * @see #getSpawnedType()
     */
    List<SpawnerEntry> getPotentialSpawns();
}
