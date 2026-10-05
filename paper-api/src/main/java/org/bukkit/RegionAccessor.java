package org.bukkit;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * RegionAccessor 提供了获取、修改和生成 {@link Biome 生物群系}、{@link BlockState 方块状态} 和 {@link Entity 实体} 的能力，
 * 同时也支持生成一些基础结构。
 * <p>原文：
 * A RegionAccessor gives access to getting, modifying and spawning {@link Biome}, {@link BlockState} and {@link Entity},
 * as well as generating some basic structures.
 */
public interface RegionAccessor extends Keyed, io.papermc.paper.world.flag.FeatureFlagSetHolder { // Paper - feature flag API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定{@link Location 位置}的{@link Biome 生物群系}.
     * <p>原文：
     * Gets the {@link Biome} at the given {@link Location}.
     *
     * @param location 位置
     * <p>原文：the location of the biome
     * @return 此位置处的生物群系
     * <p>原文：Biome at the given location
     * @see #getComputedBiome(int, int, int)
     */
    @NotNull
    default Biome getBiome(@NotNull Location location) {
        return this.getBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标的生物群系.
     * <p>原文：
     * Gets the {@link Biome} at the given coordinates.
     *
     * @param x 方块的x坐标
     * <p>原文：X-coordinate of the block
     * @param y 方块的y坐标
     * <p>原文：Y-coordinate of the block
     * @param z 方块的z坐标
     * <p>原文：Z-coordinate of the block
     * @return 指定坐标位置处的生物群系
     * <p>原文：Biome at the given coordinates
     * @see #getComputedBiome(int, int, int)
     */
    @NotNull
    Biome getBiome(int x, int y, int z);

    // Paper start
    /**
     * Gets the computed {@link Biome} at the given coordinates.
     *
     * <p>The computed Biome is the Biome as seen by clients for rendering
     * purposes and in the "F3" debug menu. This is computed by looking at the noise biome
     * at this and surrounding quarts and applying complex math operations.</p>
     *
     * <p>Most other Biome-related methods named getBiome, setBiome, and similar
     * operate on the "noise biome", which is stored per-quart, or in other words,
     * 1 Biome per 4x4x4 block region. This is how Biomes are currently generated and
     * stored on disk.</p>
     *
     * @param x X-coordinate of the block
     * @param y Y-coordinate of the block
     * @param z Z-coordinate of the block
     * @return Biome at the given coordinates
     */
    @NotNull
    Biome getComputedBiome(int x, int y, int z);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定{@link Location 位置}的{@link Biome 生物群系}.
     * <p>原文：
     * Sets the {@link Biome} at the given {@link Location}.
     *
     * @param location 位置
     * <p>原文：the location of the biome
     * @param biome 此方块的新生物群系
     * <p>原文：New Biome type for this block
     */
    default void setBiome(@NotNull Location location, @NotNull Biome biome) {
        this.setBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ(), biome);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定方块坐标的{@link Biome 生物群系}.
     * <p>原文：
     * Sets the {@link Biome} for the given block coordinates
     *
     * @param x 方块的x坐标
     * <p>原文：X-coordinate of the block
     * @param y 方块的y坐标
     * <p>原文：Y-coordinate of the block
     * @param z 方块的z坐标
     * <p>原文：Z-coordinate of the block
     * @param biome 此方块的新生物群系
     * <p>原文：New Biome type for this block
     */
    void setBiome(int x, int y, int z, @NotNull Biome biome);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定{@link Location 位置}的{@link BlockState 方块状态}.
     * <p>原文：
     * Gets the {@link BlockState} at the given {@link Location}.
     *
     * @param location 方块状态的位置
     * <p>原文：The location of the block state
     * @return 指定位置的方块状态
     * <p>原文：Block state at the given location
     */
    @NotNull
    default BlockState getBlockState(@NotNull Location location) {
        return this.getBlockState(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标的{@link BlockState 方块状态}.
     * <p>原文：
     * Gets the {@link BlockState} at the given coordinates.
     *
     * @param x 方块状态的x坐标
     * <p>原文：X-coordinate of the block state
     * @param y 方块状态的y坐标
     * <p>原文：Y-coordinate of the block state
     * @param z 方块状态的z坐标
     * <p>原文：Z-coordinate of the block state
     * @return 指定坐标处的方块状态
     * <p>原文：Block state at the given coordinates
     */
    @NotNull
    BlockState getBlockState(int x, int y, int z);

    // Paper start - FluidState API
    /**
     * Gets the {@link io.papermc.paper.block.fluid.FluidData} at the specified position.
     *
     * @param x The x-coordinate of the position
     * @param y The y-coordinate of the position
     * @param z The z-coordinate of the position
     * @return The {@link io.papermc.paper.block.fluid.FluidData} at the specified position
     */
    @NotNull
    io.papermc.paper.block.fluid.FluidData getFluidData(int x, int y, int z);

    /**
     * Gets the {@link io.papermc.paper.block.fluid.FluidData} at the given position
     *
     * @param position The position of the fluid
     * @return The fluid data at the given position
     */
    @NotNull
    default io.papermc.paper.block.fluid.FluidData getFluidData(@NotNull io.papermc.paper.math.Position position) {
        return getFluidData(position.blockX(), position.blockY(), position.blockZ());
    }

    /**
     * Gets the {@link io.papermc.paper.block.fluid.FluidData} at the given position
     *
     * @param location The location of the fluid
     * @return The fluid data at the given position
     */
    @NotNull
    default io.papermc.paper.block.fluid.FluidData getFluidData(@NotNull Location location) {
        return getFluidData(location.blockX(), location.blockY(), location.blockZ());
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定{@link Location 位置}的{@link BlockData 方块数据}.
     * <p>原文：
     * Gets the {@link BlockData} at the given {@link Location}.
     *
     * @param location 方块数据的位置
     * <p>原文：The location of the block data
     * @return 指定位置的方块数据
     * <p>原文：Block data at the given location
     */
    @NotNull
    default BlockData getBlockData(@NotNull Location location) {
        return this.getBlockData(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标的{@link BlockData 方块数据}.
     * <p>原文：
     * Gets the {@link BlockData} at the given coordinates.
     *
     * @param x 方块数据的x坐标
     * <p>原文：X-coordinate of the block data
     * @param y 方块数据的y坐标
     * <p>原文：Y-coordinate of the block data
     * @param z 方块数据的z坐标
     * <p>原文：Z-coordinate of the block data
     * @return 指定坐标处的方块数据
     * <p>原文：Block data at the given coordinates
     */
    @NotNull
    BlockData getBlockData(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定{@link Location 位置}的方块{@link Material 类型}.
     * <p>原文：
     * Gets the type of the block at the given {@link Location}.
     *
     * @param location 方块的位置
     * <p>原文：The location of the block
     * @return 指定坐标处的材质类型
     * <p>原文：Material at the given coordinates
     */
    @NotNull
    default Material getType(@NotNull Location location) {
        return this.getType(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标的方块{@link Material 类型}.
     * <p>原文：
     * Gets the type of the block at the given coordinates.
     *
     * @param x 方块的x坐标
     * <p>原文：X-coordinate of the block
     * @param y 方块的y坐标
     * <p>原文：Y-coordinate of the block
     * @param z 方块的z坐标
     * <p>原文：Z-coordinate of the block
     * @return 指定坐标处的材质类型
     * <p>原文：Material at the given coordinates
     */
    @NotNull
    Material getType(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定{@link Location 位置}的{@link BlockData 方块数据}.
     * <p>原文：
     * Sets the {@link BlockData} at the given {@link Location}.
     *
     * @param location 方块的位置
     * <p>原文：The location of the block
     * @param blockData 要设置的方块数据
     * <p>原文：The block data to set the block to
     */
    default void setBlockData(@NotNull Location location, @NotNull BlockData blockData) {
        this.setBlockData(location.getBlockX(), location.getBlockY(), location.getBlockZ(), blockData);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定坐标的{@link BlockData 方块数据}.
     * <p>原文：
     * Sets the {@link BlockData} at the given coordinates.
     *
     * @param x 方块的x坐标
     * <p>原文：X-coordinate of the block
     * @param y 方块的y坐标
     * <p>原文：Y-coordinate of the block
     * @param z 方块的z坐标
     * <p>原文：Z-coordinate of the block
     * @param blockData 要设置的方块数据
     * <p>原文：The block data to set the block to
     */
    void setBlockData(int x, int y, int z, @NotNull BlockData blockData);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定{@link Location 位置}的{@link Material 方块类型}.
     * <p>原文：
     * Sets the {@link Material} at the given {@link Location}.
     *
     * @param location 方块的位置
     * <p>原文：The location of the block
     * @param material 要设置的方块类型
     * <p>原文：The type to set the block to
     */
    default void setType(@NotNull Location location, @NotNull Material material) {
        this.setType(location.getBlockX(), location.getBlockY(), location.getBlockZ(), material);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定坐标的{@link Material 方块类型}.
     * <p>原文：
     * Sets the {@link Material} at the given coordinates.
     *
     * @param x 方块的x坐标
     * <p>原文：X-coordinate of the block
     * @param y 方块的y坐标
     * <p>原文：Y-coordinate of the block
     * @param z 方块的z坐标
     * <p>原文：Z-coordinate of the block
     * @param material 要设置的方块类型
     * <p>原文：The type to set the block to
     */
    default void setType(int x, int y, int z, @NotNull Material material) {
        this.setBlockData(x, y, z, material.createBlockData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定{@link Location 位置}创建一棵树.
     * <p>原文：
     * Creates a tree at the given {@link Location}
     *
     * @param location 生成树的位置
     * <p>原文：Location to spawn the tree
     * @param random 用于生成树的随机数生成器
     * <p>原文：Random to use to generate the tree
     * @param type 要创建的树的类型
     * <p>原文：Type of the tree to create
     * @return 如果树创建成功则返回true，否则返回false
     * <p>原文：true if the tree was created successfully, otherwise false
     */
    boolean generateTree(@NotNull Location location, @NotNull Random random, @NotNull TreeType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定{@link Location 位置}创建一棵树.
     * <p>
     * 提供的 consumer 会在因树木生成而改变的每个方块上调用。当 consumer 被调用时，
     * 世界尚未被修改。这意味着在 consumer 中调用 {@link #getBlockState(Location)}
     * 会返回生成前方块的状态。
     * <p>
     * 在 consumer 中对 {@link BlockState 方块状态} 的修改会被保留，
     * 因此无需调用 {@link BlockState#update()}.
     * <p>原文：
     * Creates a tree at the given {@link Location}
     * <p>
     * The provided consumer gets called for every block which gets changed
     * as a result of the tree generation. When the consumer gets called no
     * modifications to the world are done yet. Which means, that calling
     * {@link #getBlockState(Location)} in the consumer will return the state
     * of the block before the generation.
     * <p>
     * Modifications done to the {@link BlockState} in the consumer are respected,
     * which means that it is not necessary to call {@link BlockState#update()}
     *
     * @param location 生成树的位置
     * <p>原文：Location to spawn the tree
     * @param random 用于生成树的随机数生成器
     * <p>原文：Random to use to generate the tree
     * @param type 要创建的树的类型
     * <p>原文：Type of the tree to create
     * @param stateConsumer 在每个被改变的方块上调用的 consumer
     * <p>原文：The consumer which should get called for every block which gets changed
     * @return 如果树创建成功则返回true，否则返回false
     * <p>原文：true if the tree was created successfully, otherwise false
     */
    boolean generateTree(@NotNull Location location, @NotNull Random random, @NotNull TreeType type, @Nullable Consumer<? super BlockState> stateConsumer);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定{@link Location 位置}创建一棵树.
     * <p>
     * 提供的 predicate 会在因树木生成而改变的每个方块上调用。当 predicate 被调用时，
     * 世界尚未被修改。这意味着在 predicate 中调用 {@link #getBlockState(Location)}
     * 会返回生成前方块的状态。
     * <p>
     * 如果 predicate 返回 {@code true}，则该方块会被设置到世界中。
     * 如果返回 {@code false}，则该方块不会被设置到世界中。
     * <p>原文：
     * Creates a tree at the given {@link Location}
     * <p>
     * The provided predicate gets called for every block which gets changed
     * as a result of the tree generation. When the predicate gets called no
     * modifications to the world are done yet. Which means, that calling
     * {@link #getBlockState(Location)} in the predicate will return the state
     * of the block before the generation.
     * <p>
     * If the predicate returns {@code true} the block gets set in the world.
     * If it returns {@code false} the block won't get set in the world.
     *
     * @param location 生成树的位置
     * <p>原文：Location to spawn the tree
     * @param random 用于生成树的随机数生成器
     * <p>原文：Random to use to generate the tree
     * @param type 要创建的树的类型
     * <p>原文：Type of the tree to create
     * @param statePredicate 用于测试是否应设置方块的 predicate
     * <p>原文：The predicate which should get used to test if a block should be set or not.
     * @return 如果树创建成功则返回true，否则返回false
     * <p>原文：true if the tree was created successfully, otherwise false
     */
    boolean generateTree(@NotNull Location location, @NotNull Random random, @NotNull TreeType type, @Nullable Predicate<? super BlockState> statePredicate);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}创建一个实体.
     * <p>原文：
     * Creates an entity at the given {@link Location}
     *
     * @param location 生成实体的位置
     * <p>原文：The location to spawn the entity
     * @param type 生成的实体
     * <p>原文：The entity to spawn
     * @return 生成成功则返回此方法创建的实体
     * <p>原文：Resulting Entity of this method
     */
    @NotNull
    default Entity spawnEntity(@NotNull Location location, @NotNull EntityType type) {
        return this.spawn(location, type.getEntityClass());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定{@link Location 位置}创建一个新实体.
     * <p>原文：
     * Creates a new entity at the given {@link Location}.
     *
     * @param loc 实体将被生成的位置
     * <p>原文：the location at which the entity will be spawned.
     * @param type 决定要生成的实体类型的实体类型
     * <p>原文：the entity type that determines the entity to spawn.
     * @param randomizeData 是否应在生成前随机化实体的数据。默认情况下，实体在生成前会根据其装备、年龄、
     *                      属性等进行随机化。随机化的例子包括羊的颜色、怪物装备上的随机附魔，
     *                      甚至僵尸成为骑鸡小僵尸。如果设置为false，实体在生成前不会被随机化，
     *                      这意味着它们的所有数据将保持默认状态，不会对实体进行进一步修改。
     *                      值得注意的是，只有继承 {@link org.bukkit.entity.Mob} 接口的实体
     *                      才提供生成随机化逻辑，因此此参数对其他类型的实体无效。
     * <p>原文：whether or not the entity's data should be randomised
     *                      before spawning. By default entities are randomised
     *                      before spawning in regards to their equipment, age,
     *                      attributes, etc.
     *                      An example of this randomization would be the color of
     *                      a sheep, random enchantments on the equipment of mobs
     *                      or even a zombie becoming a chicken jockey.
     *                      If set to false, the entity will not be randomised
     *                      before spawning, meaning all their data will remain
     *                      in their default state and not further modifications
     *                      to the entity will be made.
     *                      Notably only entities that extend the
     *                      {@link org.bukkit.entity.Mob} interface provide
     *                      randomization logic for their spawn.
     *                      This parameter is hence useless for any other type
     *                      of entity.
     * @return 生成的实体实例
     * <p>原文：the spawned entity instance.
     */
    @NotNull
    public Entity spawnEntity(@NotNull Location loc, @NotNull EntityType type, boolean randomizeData);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此 RegionAccessor 中所有实体的列表.
     * <p>原文：
     * Get a list of all entities in this RegionAccessor
     *
     * @return 当前驻留在此世界访问器中的所有实体的列表
     * <p>原文：A List of all Entities currently residing in this world accessor
     */
    @NotNull
    List<Entity> getEntities();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此 RegionAccessor 中所有生物实体的列表.
     * <p>原文：
     * Get a list of all living entities in this RegionAccessor
     *
     * @return 当前驻留在此世界访问器中的所有生物实体的列表
     * <p>原文：A List of all LivingEntities currently residing in this world accessor
     */
    @NotNull
    List<LivingEntity> getLivingEntities();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此 RegionAccessor 中匹配给定类/接口的所有实体的集合.
     * <p>原文：
     * Get a collection of all entities in this RegionAccessor matching the given
     * class/interface
     *
     * @param <T> 实体子类
     * <p>原文：an entity subclass
     * @param cls 表示要匹配的实体类型的类
     * <p>原文：The class representing the type of entity to match
     * @return 当前驻留在此世界访问器中的所有匹配给定类/接口的实体的列表
     * <p>原文：A List of all Entities currently residing in this world accessor
     *     that match the given class/interface
     */
    @NotNull
    <T extends Entity> Collection<T> getEntitiesByClass(@NotNull Class<T> cls);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此 RegionAccessor 中匹配给定任意类/接口的所有实体的集合.
     * <p>原文：
     * Get a collection of all entities in this RegionAccessor matching any of the
     * given classes/interfaces
     *
     * @param classes 表示要匹配的实体类型的类
     * <p>原文：The classes representing the types of entity to match
     * @return 当前驻留在此世界访问器中的所有匹配给定一个或多个类/接口的实体的列表
     * <p>原文：A List of all Entities currently residing in this world accessor
     *     that match one or more of the given classes/interfaces
     */
    @NotNull
    Collection<Entity> getEntitiesByClasses(@NotNull Class<?>... classes);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定{@link Location 位置}创建一个特定类的实体，但不会将其生成到世界中.
     * <p>
     * <b>注意：</b>创建的实体会保持对其创建时所在世界的引用，
     * 应注意确保实体的生命周期不超过世界实例，否则会导致内存泄漏。
     * <p>原文：
     * Creates an entity of a specific class at the given {@link Location} but
     * does not spawn it in the world.
     * <p>
     * <b>Note:</b> The created entity keeps a reference to the world it was
     * created in, care should be taken that the entity does not outlive the
     * world instance as this will lead to memory leaks.
     *
     * @param <T> 要创建的{@link Entity 实体}的类
     * <p>原文：the class of the {@link Entity} to create
     * @param location 创建实体的{@link Location 位置}
     * <p>原文：the {@link Location} to create the entity at
     * @param clazz 要生成的{@link Entity 实体}的类
     * <p>原文：the class of the {@link Entity} to spawn
     * @return 创建的{@link Entity 实体}实例
     * <p>原文：an instance of the created {@link Entity}
     * @see #addEntity(Entity)
     * @see Entity#createSnapshot()
     */
    @NotNull
    <T extends Entity> T createEntity(@NotNull Location location, @NotNull Class<T> clazz);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}根据给定的类生成一个实体.
     * <p>原文：
     * Spawn an entity of a specific class at the given {@link Location}
     *
     * @param location 生成实体的{@link Location 位置}
     * <p>原文：the {@link Location} to spawn the entity at
     * @param clazz 生成{@link Entity 实体}的类
     * <p>原文：the class of the {@link Entity} to spawn
     * @param <T> 生成{@link Entity 实体}的类
     * <p>原文：the class of the {@link Entity} to spawn
     * @return 一个生成的{@link Entity 实体}实例
     * <p>原文：an instance of the spawned {@link Entity}
     * @throws IllegalArgumentException 如果参数为空或被要求生成的{@link Entity 实体}不能被生成则抛出错误
     * <p>原文：if either parameter is null or the
     *     {@link Entity} requested cannot be spawned
     */
    @NotNull
    default <T extends Entity> T spawn(@NotNull Location location, @NotNull Class<T> clazz) throws IllegalArgumentException {
        return this.spawn(location, clazz, null, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定{@link Location 位置}根据给定的类生成一个实体，并在实体被添加到世界之前运行提供的函数.
     * <br>
     * 请注意，当函数运行时，实体实际上尚未进入世界。在此函数返回之前，
     * 任何涉及传送实体等操作都是未定义的。
     * <p>原文：
     * Spawn an entity of a specific class at the given {@link Location}, with
     * the supplied function run before the entity is added to the world.
     * <br>
     * Note that when the function is run, the entity will not be actually in
     * the world. Any operation involving such as teleporting the entity is undefined
     * until after this function returns.
     *
     * @param location 生成实体的{@link Location 位置}
     * <p>原文：the {@link Location} to spawn the entity at
     * @param clazz 要生成的{@link Entity 实体}的类
     * <p>原文：the class of the {@link Entity} to spawn
     * @param function 在实体生成前运行的函数
     * <p>原文：the function to be run before the entity is spawned.
     * @param <T> 要生成的{@link Entity 实体}的类
     * <p>原文：the class of the {@link Entity} to spawn
     * @return 生成的{@link Entity 实体}实例
     * <p>原文：an instance of the spawned {@link Entity}
     * @throws IllegalArgumentException 如果参数为空或被要求生成的{@link Entity 实体}不能被生成则抛出错误
     * <p>原文：if either parameter is null or the
     *     {@link Entity} requested cannot be spawned
     */
    // Paper start
    default <T extends Entity> @NotNull T spawn(final @NotNull Location location, final @NotNull Class<T> clazz, final @Nullable Consumer<? super T> function) throws IllegalArgumentException {
        return this.spawn(location, clazz, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.CUSTOM, function);
    }

    default @NotNull <T extends Entity> T spawn(final @NotNull Location location, final @NotNull Class<T> clazz, final org.bukkit.event.entity.CreatureSpawnEvent.@NotNull SpawnReason reason) throws IllegalArgumentException {
        return this.spawn(location, clazz, reason, null);
    }

    default @NotNull <T extends Entity> T spawn(final @NotNull Location location, final @NotNull Class<T> clazz, final org.bukkit.event.entity.CreatureSpawnEvent.@NotNull SpawnReason reason, final @Nullable Consumer<? super T> function) throws IllegalArgumentException {
        return this.spawn(location, clazz, function, reason);
    }

    default @NotNull Entity spawnEntity(final @NotNull Location loc, final @NotNull EntityType type, final org.bukkit.event.entity.CreatureSpawnEvent.@NotNull SpawnReason reason) {
        com.google.common.base.Preconditions.checkArgument(type.getEntityClass() != null, "%s is not a valid EntityType, must have an entity class", type);
        return this.spawn(loc, type.getEntityClass(), reason, null);
    }

    default @NotNull Entity spawnEntity(final @NotNull Location loc, final @NotNull EntityType type, final org.bukkit.event.entity.CreatureSpawnEvent.@NotNull SpawnReason reason, final @Nullable Consumer<? super Entity> function) {
        com.google.common.base.Preconditions.checkArgument(type.getEntityClass() != null, "%s is not a valid EntityType, must have an entity class", type);
        return this.spawn(loc, type.getEntityClass(), reason, function);
    }

    <T extends Entity> @NotNull T spawn(@NotNull Location location, @NotNull Class<T> clazz, @Nullable Consumer<? super T> function, org.bukkit.event.entity.CreatureSpawnEvent.@NotNull SpawnReason reason) throws IllegalArgumentException;
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定{@link Location 位置}创建一个新实体.
     * <p>
     * 提供的函数会在实体被添加到世界之前运行。
     * <br>
     * 请注意，当函数运行时，实体实际上尚未进入世界。在此函数返回之前，
     * 任何涉及传送实体等操作都是未定义的。
     * 传入的函数在潜在的实体生成随机化之后运行，因此已经允许访问 mob 的值，
     * 无论这些值是否已被随机化，例如属性或实体装备。
     * <p>原文：
     * Creates a new entity at the given {@link Location} with the supplied
     * function run before the entity is added to the world.
     * <br>
     * Note that when the function is run, the entity will not be actually in
     * the world. Any operation involving such as teleporting the entity is undefined
     * until after this function returns.
     * The passed function however is run after the potential entity's spawn
     * randomization and hence already allows access to the values of the mob,
     * whether or not those were randomized, such as attributes or the entity
     * equipment.
     *
     * @param location 实体将被生成的位置
     * <p>原文：the location at which the entity will be spawned.
     * @param clazz 要生成的{@link Entity 实体}的类
     * <p>原文：the class of the {@link Entity} that is to be spawned.
     * @param <T> 要生成的{@link Entity 实体}的泛型类型
     * <p>原文：the generic type of the entity that is being created.
     * @param randomizeData 是否应在生成前随机化实体的数据。默认情况下，实体在生成前会根据其装备、年龄、
     *                      属性等进行随机化。随机化的例子包括羊的颜色、怪物装备上的随机附魔，
     *                      甚至僵尸成为骑鸡小僵尸。如果设置为false，实体在生成前不会被随机化，
     *                      这意味着它们的所有数据将保持默认状态，不会对实体进行进一步修改。
     *                      值得注意的是，只有继承 {@link org.bukkit.entity.Mob} 接口的实体
     *                      才提供生成随机化逻辑，因此此参数对其他类型的实体无效。
     * <p>原文：whether or not the entity's data should be randomised
     *                      before spawning. By default entities are randomised
     *                      before spawning in regards to their equipment, age,
     *                      attributes, etc.
     *                      An example of this randomization would be the color of
     *                      a sheep, random enchantments on the equipment of mobs
     *                      or even a zombie becoming a chicken jockey.
     *                      If set to false, the entity will not be randomised
     *                      before spawning, meaning all their data will remain
     *                      in their default state and not further modifications
     *                      to the entity will be made.
     *                      Notably only entities that extend the
     *                      {@link org.bukkit.entity.Mob} interface provide
     *                      randomization logic for their spawn.
     *                      This parameter is hence useless for any other type
     *                      of entity.
     * @param function 在实体生成前运行的函数
     * <p>原文：the function to be run before the entity is spawned.
     * @return 生成的实体实例
     * <p>原文：the spawned entity instance.
     * @throws IllegalArgumentException 如果 world 或 clazz 参数为空
     * <p>原文：if either the world or clazz parameter are null.
     */
    @NotNull
    public <T extends Entity> T spawn(@NotNull Location location, @NotNull Class<T> clazz, boolean randomizeData, @Nullable Consumer<? super T> function) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标处最高非空（不可通过）方块的坐标.
     * <p>原文：
     * Gets the highest non-empty (impassable) coordinate at the given
     * coordinates.
     *
     * @param x 方块的x坐标
     * <p>原文：X-coordinate of the blocks
     * @param z 方块的z坐标
     * <p>原文：Z-coordinate of the blocks
     * @return 最高非空方块的y坐标
     * <p>原文：Y-coordinate of the highest non-empty block
     */
    public int getHighestBlockYAt(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定{@link Location 位置}处最高非空（不可通过）方块的坐标.
     * <p>原文：
     * Gets the highest non-empty (impassable) coordinate at the given
     * {@link Location}.
     *
     * @param location 方块的位置
     * <p>原文：Location of the blocks
     * @return 最高非空方块的y坐标
     * <p>原文：Y-coordinate of the highest non-empty block
     */
    public int getHighestBlockYAt(@NotNull Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标处对应于{@link HeightMap 高度图}的最高坐标.
     * <p>原文：
     * Gets the highest coordinate corresponding to the {@link HeightMap} at the
     * given coordinates.
     *
     * @param x 方块的x坐标
     * <p>原文：X-coordinate of the blocks
     * @param z 方块的z坐标
     * <p>原文：Z-coordinate of the blocks
     * @param heightMap 用于确定最高点的高度图
     * <p>原文：the heightMap that is used to determine the highest
     * point
     * @return 对应于{@link HeightMap 高度图}的最高方块的y坐标
     * <p>原文：Y-coordinate of the highest block corresponding to the
     * {@link HeightMap}
     */
    public int getHighestBlockYAt(int x, int z, @NotNull HeightMap heightMap);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定{@link Location 位置}处对应于{@link HeightMap 高度图}的最高坐标.
     * <p>原文：
     * Gets the highest coordinate corresponding to the {@link HeightMap} at the
     * given {@link Location}.
     *
     * @param location 方块的位置
     * <p>原文：Location of the blocks
     * @param heightMap 用于确定最高点的高度图
     * <p>原文：the heightMap that is used to determine the highest
     * point
     * @return 对应于{@link HeightMap 高度图}的最高方块的y坐标
     * <p>原文：Y-coordinate of the highest block corresponding to the
     * {@link HeightMap}
     */
    public int getHighestBlockYAt(@NotNull Location location, @NotNull HeightMap heightMap);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中生成一个先前创建的实体.
     * <br>
     * 提供的实体必须尚未在世界中生成。
     * <p>原文：
     * Spawns a previously created entity in the world. <br>
     * The provided entity must not have already been spawned in a world.
     *
     * @param <T> 要添加的实体的泛型类型
     * <p>原文：the generic type of the entity that is being added.
     * @param entity 要添加的实体
     * <p>原文：the entity to add
     * @return 现在位于世界中的实体
     * <p>原文：the entity now in the world
     */
    @NotNull
    public <T extends Entity> T addEntity(@NotNull T entity);

    // Paper start
    /**
     * @return the current moon phase at the current time in the world
     */
    @NotNull
    io.papermc.paper.world.MoonPhase getMoonPhase();

    /**
     * Get the world's key
     *
     * @return the world's key
     */
    @NotNull
    @Override
    NamespacedKey getKey();

    /**
     * Tell whether a line of sight exists between the given locations
     * @param from Location to start at
     * @param to target Location
     * @return whether a line of sight exists between {@code from} and {@code to}
     */
    public boolean lineOfSightExists(@NotNull Location from, @NotNull Location to);

    /**
     * Checks if the world collides with the given boundingbox.
     * This will check for any colliding hard entities (boats, shulkers) / worldborder / blocks.
     * Does not load chunks that are within the bounding box.
     *
     * @param boundingBox the box to check collisions in
     * @return collides or not
     */
    boolean hasCollisionsIn(@NotNull org.bukkit.util.BoundingBox boundingBox);
    // Paper end
}
