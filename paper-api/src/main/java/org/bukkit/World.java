package org.bukkit;

import io.papermc.paper.raytracing.PositionedRayTraceConfigurationBuilder;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.generator.structure.GeneratedStructure;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.Metadatable;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageRecipient;
import org.bukkit.util.BiomeSearchResult;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.StructureSearchResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个世界,包含了{@link Entity 实体},{@link Chunk 区块},{@link Block 方块}
 * <p>原文：
 * Represents a world, which may contain entities, chunks and blocks
 */
public interface World extends RegionAccessor, WorldInfo, PluginMessageRecipient, Metadatable, PersistentDataHolder, Keyed, net.kyori.adventure.audience.ForwardingAudience { // Paper

    // Paper start - void damage configuration
    /**
     * Checks if void damage is enabled on this world.
     *
     * @return true if enabled
     */
    boolean isVoidDamageEnabled();

    /**
     * Sets whether void damage is enabled on this world.
     *
     * @param enabled true to enable void damage
     */
    void setVoidDamageEnabled(boolean enabled);

    /**
     * Gets the damage applied to the entities when they are in the void in this world.
     * Check {@link #isVoidDamageEnabled()} to see if void damage is enabled.
     *
     * @return amount of damage to apply
     * @see #isVoidDamageEnabled()
     */
    float getVoidDamageAmount();

    /**
     * Sets the damage applied to the entities when they are in the void in this world.
     * Check {@link #isVoidDamageEnabled()} to see if void damage is enabled.
     *
     * @param voidDamageAmount amount of damage to apply
     */
    void setVoidDamageAmount(float voidDamageAmount);

    /**
     * Gets the offset applied to {@link #getMinHeight()} to determine the height at which void damage starts to apply.
     *
     * @return offset from min build height
     * @see #isVoidDamageEnabled()
     */
    double getVoidDamageMinBuildHeightOffset();

    /**
     * Sets the offset applied to {@link #getMinHeight()} to determine the height at which void damage starts to apply.
     *
     * @param minBuildHeightOffset offset from min build height
     */
    void setVoidDamageMinBuildHeightOffset(double minBuildHeightOffset);
    // Paper end - void damage configuration

    // Paper start
    /**
     * @return The amount of entities in this world
     */
    int getEntityCount();

    /**
     * @return The amount of block entities in this world
     */
    int getTileEntityCount();

    /**
     * @return The amount of tickable block entities in this world
     */
    int getTickableTileEntityCount();

    /**
     * @return The amount of chunks in this world
     */
    int getChunkCount();

    /**
     * @return The amount of players in this world
     */
    int getPlayerCount();
    // Paper end
    // Paper start - structure check API
    /**
     * Check if the naturally-generated structure exists at the position.
     * <p>
     * Note that if the position is not loaded, this may cause chunk loads/generation
     * to check if a structure is at that position. Use {@link #isPositionLoaded(io.papermc.paper.math.Position)}
     * to check if a position is loaded
     *
     * @param position the position to check at
     * @param structure the structure to check for
     * @return true if that structure exists at the position
     */
    boolean hasStructureAt(io.papermc.paper.math.@NotNull Position position, @NotNull Structure structure);

    /**
     * Checks if this position is loaded.
     *
     * @param position position to check
     * @return true if loaded
     */
    default boolean isPositionLoaded(io.papermc.paper.math.@NotNull Position position) {
        return this.isChunkLoaded(position.blockX() >> 4, position.blockZ() >> 4);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取坐标所指的{@link Block 方块}.
     * <p>原文：
     * Gets the {@link Block} at the given coordinates
     *
     * @param x 方块的X坐标
     * <p>原文：X-coordinate of the block
     * @param y 方块的Y坐标
     * <p>原文：Y-coordinate of the block
     * @param z 方块的Z坐标
     * <p>原文：Z-coordinate of the block
     * @return 在指定坐标的方块
     * <p>原文：Block at the given coordinates
     */
    @NotNull
    public Block getBlockAt(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定{@link Location 位置}的{@link Block 方块}.
     * <p>原文：
     * Gets the {@link Block} at the given {@link Location}
     *
     * @param location 要获取的方块的位置
     * <p>原文：Location of the block
     * @return 在指定位置的方块
     * <p>原文：Block at the given location
     */
    @NotNull
    default Block getBlockAt(@NotNull Location location) {
        return this.getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    // Paper start
    /**
     * Gets the {@link Block} at the given block key
     *
     * @param key The block key. See {@link Block#getBlockKey()}
     * @return Block at the key
     * @see Block#getBlockKey(int, int, int)
     * @deprecated only encodes y block ranges from -512 to 511 and represents an already changed implementation detail
     */
    @NotNull
    @Deprecated(since = "1.18.1")
    public default Block getBlockAtKey(long key) {
        int x = Block.getBlockKeyX(key);
        int y = Block.getBlockKeyY(key);
        int z = Block.getBlockKeyZ(key);
        return getBlockAt(x, y, z);
    }

    /**
     * Gets the {@link Location} at the given block key
     *
     * @param key The block key. See {@link Location#toBlockKey()}
     * @return Location at the key
     * @see Block#getBlockKey(int, int, int)
     */
    @NotNull
    @Deprecated(since = "1.18.1")
    public default Location getLocationAtKey(long key) {
        int x = Block.getBlockKeyX(key);
        int y = Block.getBlockKeyY(key);
        int z = Block.getBlockKeyZ(key);
        return new Location(this, x, y, z);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标最高处不是空气且不可逾越的方块.
     * <p>原文：
     * Gets the highest non-empty (impassable) block at the given coordinates.
     *
     * @param x 方块X坐标
     * <p>原文：X-coordinate of the block
     * @param z 方块Z坐标
     * <p>原文：Z-coordinate of the block
     * @return 最高处不是空气的方块.
     * <p>原文：Highest non-empty block
     */
    @NotNull
    default Block getHighestBlockAt(int x, int z) {
        return this.getBlockAt(x, this.getHighestBlockYAt(x, z), z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定{@link Location 位置}最高处不是空气且不可逾越的方块.
     * <p>原文：
     * Gets the highest non-empty (impassable) block at the given coordinates.
     *
     * @param location 需要获取最高的方块的位置
     * <p>原文：Coordinates to get the highest block
     * @return 最高的不是空气的方块
     * <p>原文：Highest non-empty block
     */
    @NotNull
    default Block getHighestBlockAt(@NotNull Location location) {
        return this.getHighestBlockAt(location.getBlockX(), location.getBlockZ());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定坐标处对应于指定{@link HeightMap 高度映射}的最高方块.
     * <p>原文：
     * Gets the highest block corresponding to the {@link HeightMap} at the
     * given coordinates.
     *
     * @param x 方块的X坐标
     * <p>原文：X-coordinate of the block
     * @param z 方块的Z坐标
     * <p>原文：Z-coordinate of the block
     * @param heightMap 用于确定最高点的高度映射
     * <p>原文：the heightMap that is used to determine the highest
     * point
     * @return 对应于指定{@link HeightMap 高度映射}的最高方块
     * <p>原文：Highest block corresponding to the {@link HeightMap}
     */
    @NotNull
    default Block getHighestBlockAt(int x, int z, @NotNull HeightMap heightMap) {
        return this.getBlockAt(x, this.getHighestBlockYAt(x, z, heightMap), z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定{@link Location 位置}处对应于指定{@link HeightMap 高度映射}的最高方块.
     * <p>原文：
     * Gets the highest block corresponding to the {@link HeightMap} at the
     * given coordinates.
     *
     * @param location 获取最高方块的坐标位置
     * <p>原文：Coordinates to get the highest block
     * @param heightMap 用于确定最高点的高度映射
     * <p>原文：the heightMap that is used to determine the highest
     * point
     * @return 对应于指定{@link HeightMap 高度映射}的最高方块
     * <p>原文：Highest block corresponding to the {@link HeightMap}
     */
    @NotNull
    default Block getHighestBlockAt(@NotNull Location location, @NotNull HeightMap heightMap) {
        return this.getHighestBlockAt(location.getBlockX(), location.getBlockZ(), heightMap);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定坐标所在的{@link Chunk 区块}.
     * <p>原文：
     * Gets the {@link Chunk} at the given coordinates
     *
     * @param x X坐标
     * <p>原文：X-coordinate of the chunk
     * @param z Z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 给定坐标所在的区块
     * <p>原文：Chunk at the given coordinates
     */
    @NotNull
    public Chunk getChunkAt(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定坐标所在的{@link Chunk 区块}.
     * <p>原文：
     * Gets the {@link Chunk} at the given coordinates
     *
     * @param x 区块的X坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的Z坐标
     * <p>原文：Z-coordinate of the chunk
     * @param generate 如果区块未完全生成, 是否应该被完整生成
     * <p>原文：Whether the chunk should be fully generated or not
     * @return 给定坐标所在的区块
     * <p>原文：Chunk at the given coordinates
     */
    @NotNull
    public Chunk getChunkAt(int x, int z, boolean generate);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定{@link Location 位置}所在的{@link Chunk 区块}.
     * <p>原文：
     * Gets the {@link Chunk} at the given {@link Location}
     *
     * @param location 方块的位置
     * <p>原文：Location of the chunk
     * @return 给定位置的区块
     * <p>原文：Chunk at the given location
     */
    @NotNull
    default Chunk getChunkAt(@NotNull Location location) {
        return this.getChunkAt(location.getBlockX() >> 4, location.getBlockZ() >> 4);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个{@link Block 方块}所处的{@link Chunk 区块}.
     * <p>原文：
     * Gets the {@link Chunk} that contains the given {@link Block}
     *
     * @param block 需要检测的方块
     * <p>原文：Block to get the containing chunk from
     * @return 方块所处的区块
     * <p>原文：The chunk that contains the given block
     */
    @NotNull
    public Chunk getChunkAt(@NotNull Block block);

    // Paper start - chunk long key API
    /**
     * Gets the chunk at the specified chunk key, which is the X and Z packed into a long.
     * <p>
     * See {@link Chunk#getChunkKey()} for easy access to the key, or you may calculate it as:
     * long chunkKey = (long) chunkX &amp; 0xffffffffL | ((long) chunkZ &amp; 0xffffffffL) &gt;&gt; 32;
     *
     * @param chunkKey The Chunk Key to look up the chunk by
     * @return The chunk at the specified key
     */
    @NotNull
    default Chunk getChunkAt(long chunkKey) {
        return getChunkAt(chunkKey, true);
    }

    /**
     * Gets the chunk at the specified chunk key, which is the X and Z packed into a long.
     * <p>
     * See {@link Chunk#getChunkKey()} for easy access to the key, or you may calculate it as:
     * long chunkKey = (long) chunkX &amp; 0xffffffffL | ((long) chunkZ &amp; 0xffffffffL) &gt;&gt; 32;
     *
     * @param chunkKey The Chunk Key to look up the chunk by
     * @param generate Whether the chunk should be fully generated or not
     * @return The chunk at the specified key
     */
    @NotNull
    default Chunk getChunkAt(long chunkKey, boolean generate) {
        return getChunkAt((int) chunkKey, (int) (chunkKey >> 32), generate);
    }
    // Paper end - chunk long key API

    // Paper start - isChunkGenerated API
    /**
     * Checks if a {@link Chunk} has been generated at the specified chunk key,
     * which is the X and Z packed into a long.
     *
     * @param chunkKey The Chunk Key to look up the chunk by
     * @return true if the chunk has been generated, otherwise false
     */
    default boolean isChunkGenerated(long chunkKey) {
        return isChunkGenerated((int) chunkKey, (int) (chunkKey >> 32));
    }
    // Paper end - isChunkGenerated API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查指定{@link Chunk 区块}是否已经被加载.
     * <p>原文：
     * Checks if the specified {@link Chunk} is loaded
     *
     * @param chunk 需要检查的区块
     * <p>原文：The chunk to check
     * @return 如果区块已经被加载则返回true，否则返回false
     * <p>原文：true if the chunk is loaded, otherwise false
     */
    public boolean isChunkLoaded(@NotNull Chunk chunk);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个所有被加载的{@link Chunk 区块}的数组.
     * <p>原文：
     * Gets an array of all loaded {@link Chunk}s
     *
     * @return 包含所有被加载区块的数组Chunk[]
     * <p>原文：Chunk[] containing all loaded chunks
     */
    public @NotNull Chunk @NotNull [] getLoadedChunks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 加载指定的{@link Chunk 区块}.
     * <p>
     * <b>本方法将使指定的区块保持加载状态直到卸载方法被手动调用.
     * 建议开发者使用 getChunkAt 方法, 其方法只会临时加载请求的区块, 而不是本方法.</b>
     * <p>原文：
     * Loads the specified {@link Chunk}.
     * <p>
     * <b>This method will keep the specified chunk loaded until one of the
     * unload methods is manually called. Callers are advised to instead use
     * getChunkAt which will only temporarily load the requested chunk.</b>
     *
     * @param chunk 待加载的区块
     * <p>原文：The chunk to load
     */
    public void loadChunk(@NotNull Chunk chunk);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查在指定坐标的{@link Chunk 区块}是否被加载.
     * <p>原文：
     * Checks if the {@link Chunk} at the specified coordinates is loaded
     *
     * @param x 区块的x坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 如果区块已经被加载则返回true，否则返回false
     * <p>原文：true if the chunk is loaded, otherwise false
     */
    public boolean isChunkLoaded(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查指定坐标处的{@link Chunk 区块}是否已生成.
     * <p>原文：
     * Checks if the {@link Chunk} at the specified coordinates is generated
     *
     * @param x 区块x坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 区块已生成则返回true, 反之为false
     * <p>原文：true if the chunk is generated, otherwise false
     */
    public boolean isChunkGenerated(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查指定坐标的{@link Chunk 区块}是否被加载且被一个或更多的玩家使用.
     * <p>原文：
     * Checks if the {@link Chunk} at the specified coordinates is loaded and
     * in use by one or more players
     *
     * @param x 区块的x坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 如果区块被加载且被一个或更多的玩家使用则返回true，否则返回false
     * <p>原文：true if the chunk is loaded and in use by one or more players,
     *     otherwise false
     * @deprecated 本方法曾被添加用于促进区块的垃圾回收.
     * 由于当前 Minecraft 的区块被严格管理, 并且除非那些区块正被使用, 否则不会加载超过1tick时长
     * (意思是mc现在对区块管理更好了, 不用的区块会被及时卸载并回收)
     * <p>原文：This method was added to facilitate chunk garbage collection.
     *     As of the current Minecraft version chunks are now strictly managed and
     *     will not be loaded for more than 1 tick unless they are in use.
     */
    @Deprecated(since = "1.14")
    public boolean isChunkInUse(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 加载指定坐标的{@link Chunk 区块}.
     * <p>
     * <b>本方法将使指定的区块保持加载状态直到卸载方法被手动调用.
     * 建议开发者使用 getChunkAt 方法, 其方法只会临时加载请求的区块, 而不是本方法.</b>
     * <p>
     * 如果区块不存在则会被生成。
     * <p>
     * 这个方法类似于当generate值为true时的{@link #loadChunk(int, int, boolean)}.
     * <p>原文：
     * Loads the {@link Chunk} at the specified coordinates.
     * <p>
     * <b>This method will keep the specified chunk loaded until one of the
     * unload methods is manually called. Callers are advised to instead use
     * getChunkAt which will only temporarily load the requested chunk.</b>
     * <p>
     * If the chunk does not exist, it will be generated.
     * <p>
     * This method is analogous to {@link #loadChunk(int, int, boolean)} where
     * generate is true.
     *
     * @param x 区块的x坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的z坐标
     * <p>原文：Z-coordinate of the chunk
     */
    default void loadChunk(int x, int z) {
        this.loadChunk(x, z, true);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 加载指定坐标的{@link Chunk 区块}.
     * <p>
     * <b>本方法将使指定的区块保持加载状态直到卸载方法被手动调用.
     * 建议开发者使用 getChunkAt 方法, 其方法只会临时加载请求的区块, 而不是本方法.</b>
     * <p>原文：
     * Loads the {@link Chunk} at the specified coordinates.
     * <p>
     * <b>This method will keep the specified chunk loaded until one of the
     * unload methods is manually called. Callers are advised to instead use
     * getChunkAt which will only temporarily load the requested chunk.</b>
     *
     * @param x 区块的x坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的z坐标
     * <p>原文：Z-coordinate of the chunk
     * @param generate 如果区块不存在是否生成
     * <p>原文：Whether or not to generate a chunk if it doesn't
     *     already exist
     * @return 如果区块被成功加载则返回true，否则返回false
     * <p>原文：true if the chunk has loaded successfully, otherwise false
     */
    public boolean loadChunk(int x, int z, boolean generate);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 安全的卸载并保存指定坐标的{@link Chunk 区块}.
     * <p>
     * 这个方法类似于当safe值为true时的{@link #unloadChunk(int, int, boolean)}.
     * <p>原文：
     * Safely unloads and saves the {@link Chunk} at the specified coordinates
     * <p>
     * This method is analogous to {@link #unloadChunk(int, int, boolean)}
     * where save is true.
     *
     * @param chunk 卸载的区块
     * <p>原文：the chunk to unload
     * @return 如果区块被成功卸载则返回true，否则返回false
     * <p>原文：true if the chunk has unloaded successfully, otherwise false
     */
    default boolean unloadChunk(@NotNull Chunk chunk) {
        return this.unloadChunk(chunk.getX(), chunk.getZ());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 安全的卸载并保存指定坐标的{@link Chunk 区块}.
     * <p>
     * 这个方法类似于当safe值为true时的{@link #unloadChunk(int, int, boolean)}.
     * <p>原文：
     * Safely unloads and saves the {@link Chunk} at the specified coordinates
     * <p>
     * This method is analogous to {@link #unloadChunk(int, int, boolean)}
     * where save is true.
     *
     * @param x 区块的x坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 如果区块被成功卸载则返回true，否则返回false
     * <p>原文：true if the chunk has unloaded successfully, otherwise false
     */
    default boolean unloadChunk(int x, int z) {
        return this.unloadChunk(x, z, true);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 安全的卸载并选择是否保存指定坐标的{@link Chunk 区块}.
     * <p>原文：
     * Safely unloads and optionally saves the {@link Chunk} at the specified
     * coordinates.
     *
     * @param x 区块的x坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的z坐标
     * <p>原文：Z-coordinate of the chunk
     * @param save 是否保存区块
     * <p>原文：Whether or not to save the chunk
     * @return 如果区块被成功卸载则返回true，否则返回false
     * <p>原文：true if the chunk has unloaded successfully, otherwise false
     */
    public boolean unloadChunk(int x, int z, boolean save);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 安全地将卸载指定坐标的{@link Chunk 区块}请求列入队列.
     * <p>原文：
     * Safely queues the {@link Chunk} at the specified coordinates for
     * unloading.
     *
     * @param x 区块的x坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 如果尝试列入队列成功则返回true，否则返回false
     * <p>原文：true is the queue attempt was successful, otherwise false
     */
    public boolean unloadChunkRequest(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 重新生成指定坐标的{@link Chunk 区块}.
     * <p>原文：
     * Regenerates the {@link Chunk} at the specified coordinates
     *
     * @param x 区块的x坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 区块是否真的被重新生成
     * <p>原文：Whether the chunk was actually regenerated
     * @throws UnsupportedOperationException not implemented
     * @deprecated 无法保证重新生成单个区块会产生与之前相同的区块, 因为地形装饰可分布在区块上.
     * 应避免使用本方法, 由于已知本方法易产生bug
     * <p>原文：regenerating a single chunk is not likely to produce the same
     * chunk as before as terrain decoration may be spread across chunks. It may
     * or may not change blocks in the adjacent chunks as well.
     */
    @Deprecated(since = "1.13", forRemoval = true)
    default boolean regenerateChunk(int x, int z) {
        throw new UnsupportedOperationException("Not supported in this Minecraft version! This is not a bug.");
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将{@link Chunk 区块}重新发送给所有的客户端.
     * <p>原文：
     * Resends the {@link Chunk} to all clients
     *
     * @param x 区块的x坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 区块是否真的被刷新
     * <p>原文：Whether the chunk was actually refreshed
     */
    public boolean refreshChunk(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可以从客户端查看指定{@link Chunk 区块}的所有玩家列表.
     * <p>
     * 如果没有玩家正在查看该区块, 或者该区块已卸载, 则此列表为空.
     * <p>原文：
     * Get a list of all players who are can view the specified chunk from their
     * client
     * <p>
     * This list will be empty if no players are viewing the chunk, or the chunk
     * is unloaded.
     *
     * @param chunk 要检查的区块
     * <p>原文：the chunk to check
     * @return 可以看到该区块的玩家集合
     * <p>原文：collection of players who can see the chunk
     */
    @NotNull
    public Collection<Player> getPlayersSeeingChunk(@NotNull Chunk chunk);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可以从客户端查看指定{@link Chunk 区块}的所有玩家列表.
     * <p>
     * 如果没有玩家正在查看该区块, 或者该区块已卸载, 则此列表为空.
     * <p>原文：
     * Get a list of all players who are can view the specified chunk from their
     * client
     * <p>
     * This list will be empty if no players are viewing the chunk, or the chunk
     * is unloaded.
     *
     * @param x 区块的X坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的Z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 可以看到该区块的玩家集合
     * <p>原文：collection of players who can see the chunk
     */
    @NotNull
    public Collection<Player> getPlayersSeeingChunk(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标处的{@link Chunk 区块}是否被强制加载.
     * <p>
     * 被强制加载的区块不会因缺少玩家活动而被卸载.
     * <p>原文：
     * Gets whether the chunk at the specified chunk coordinates is force
     * loaded.
     * <p>
     * A force loaded chunk will not be unloaded due to lack of player activity.
     *
     * @param x 区块的X坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的Z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 强制加载状态
     * <p>原文：force load status
     */
    public boolean isChunkForceLoaded(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定坐标处的{@link Chunk 区块}是否被强制加载.
     * <p>
     * 被强制加载的区块不会因缺少玩家活动而被卸载.
     * <p>原文：
     * Sets whether the chunk at the specified chunk coordinates is force
     * loaded.
     * <p>
     * A force loaded chunk will not be unloaded due to lack of player activity.
     *
     * @param x 区块的X坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的Z坐标
     * <p>原文：Z-coordinate of the chunk
     * @param forced 强制加载状态
     * <p>原文：force load status
     */
    public void setChunkForceLoaded(int x, int z, boolean forced);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个世界中所有被强制加载的区块.
     * <p>
     * 被强制加载的区块不会因缺少玩家活动而被卸载.
     * <p>原文：
     * Returns all force loaded chunks in this world.
     * <p>
     * A force loaded chunk will not be unloaded due to lack of player activity.
     *
     * @return 不可修改的强制加载区块集合
     * <p>原文：unmodifiable collection of force loaded chunks
     */
    @NotNull
    public Collection<Chunk> getForceLoadedChunks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定区块添加一个插件票证, 如果该区块尚未加载则会加载它.
     * <p>
     * 插件票证会阻止区块被卸载, 直到该票证被显式移除. 每个插件实例对每个区块只能持有一张票证,
     * 但每个区块可以拥有多张来自不同插件的票证.
     * </p>
     * <p>原文：
     * Adds a plugin ticket for the specified chunk, loading the chunk if it is
     * not already loaded.
     * <p>
     * A plugin ticket will prevent a chunk from unloading until it is
     * explicitly removed. A plugin instance may only have one ticket per chunk,
     * but each chunk can have multiple plugin tickets.
     * </p>
     *
     * @param x 区块的X坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的Z坐标
     * <p>原文：Z-coordinate of the chunk
     * @param plugin 拥有该票证的插件
     * <p>原文：Plugin which owns the ticket
     * @return {@code true} 如果成功添加了插件票证, {@code false} 如果该插件已持有该区块的票证
     * <p>原文：{@code true} if a plugin ticket was added, {@code false} if the
     * ticket already exists for the plugin
     * @throws IllegalStateException 如果指定的插件未启用
     * <p>原文：If the specified plugin is not enabled
     * @see #removePluginChunkTicket(int, int, Plugin)
     */
    public boolean addPluginChunkTicket(int x, int z, @NotNull Plugin plugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除指定插件对指定区块的票证.
     * <p>
     * 插件票证会阻止区块被卸载, 直到该票证被显式移除. 每个插件实例对每个区块只能持有一张票证,
     * 但每个区块可以拥有多张来自不同插件的票证.
     * </p>
     * <p>原文：
     * Removes the specified plugin's ticket for the specified chunk
     * <p>
     * A plugin ticket will prevent a chunk from unloading until it is
     * explicitly removed. A plugin instance may only have one ticket per chunk,
     * but each chunk can have multiple plugin tickets.
     * </p>
     *
     * @param x 区块的X坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的Z坐标
     * <p>原文：Z-coordinate of the chunk
     * @param plugin 拥有该票证的插件
     * <p>原文：Plugin which owns the ticket
     * @return {@code true} 如果成功移除了插件票证, {@code false} 如果该区块没有来自该插件的票证
     * <p>原文：{@code true} if the plugin ticket was removed, {@code false} if
     * there is no plugin ticket for the chunk
     * @see #addPluginChunkTicket(int, int, Plugin)
     */
    public boolean removePluginChunkTicket(int x, int z, @NotNull Plugin plugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除指定插件的所有票证.
     * <p>
     * 插件票证会阻止区块被卸载, 直到该票证被显式移除. 每个插件实例对每个区块只能持有一张票证,
     * 但每个区块可以拥有多张来自不同插件的票证.
     * </p>
     * <p>原文：
     * Removes all plugin tickets for the specified plugin
     * <p>
     * A plugin ticket will prevent a chunk from unloading until it is
     * explicitly removed. A plugin instance may only have one ticket per chunk,
     * but each chunk can have multiple plugin tickets.
     * </p>
     *
     * @param plugin 指定的插件
     * <p>原文：Specified plugin
     * @see #addPluginChunkTicket(int, int, Plugin)
     * @see #removePluginChunkTicket(int, int, Plugin)
     */
    public void removePluginChunkTickets(@NotNull Plugin plugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个集合, 指定哪些插件对指定区块持有票证. 当对该区块添加或移除插件票证时, 该集合不会被更新.
     * <p>
     * 插件票证会阻止区块被卸载, 直到该票证被显式移除. 每个插件实例对每个区块只能持有一张票证,
     * 但每个区块可以拥有多张来自不同插件的票证.
     * </p>
     * <p>原文：
     * Retrieves a collection specifying which plugins have tickets for the
     * specified chunk. This collection is not updated when plugin tickets are
     * added or removed to the chunk.
     * <p>
     * A plugin ticket will prevent a chunk from unloading until it is
     * explicitly removed. A plugin instance may only have one ticket per chunk,
     * but each chunk can have multiple plugin tickets.
     * </p>
     *
     * @param x 区块的X坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的Z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 不可修改的集合, 包含对该区块持有票证的插件
     * <p>原文：unmodifiable collection containing which plugins have tickets for
     * the chunk
     * @see #addPluginChunkTicket(int, int, Plugin)
     * @see #removePluginChunkTicket(int, int, Plugin)
     */
    @NotNull
    public Collection<Plugin> getPluginChunkTickets(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回一个映射, 包含哪些插件对哪些区块持有票证. 返回的映射在添加或移除插件票证时不会被更新.
     * 如果某个插件没有持有任何票证, 则不会出现在映射中.
     * <p>
     * 插件票证会阻止区块被卸载, 直到该票证被显式移除. 每个插件实例对每个区块只能持有一张票证,
     * 但每个区块可以拥有多张来自不同插件的票证.
     * </p>
     * <p>原文：
     * Returns a map of which plugins have tickets for what chunks. The returned
     * map is not updated when plugin tickets are added or removed to chunks. If
     * a plugin has no tickets, it will be absent from the map.
     * <p>
     * A plugin ticket will prevent a chunk from unloading until it is
     * explicitly removed. A plugin instance may only have one ticket per chunk,
     * but each chunk can have multiple plugin tickets.
     * </p>
     *
     * @return 不可修改的映射, 包含哪些插件对哪些区块持有票证
     * <p>原文：unmodifiable map containing which plugins have tickets for what
     * chunks
     * @see #addPluginChunkTicket(int, int, Plugin)
     * @see #removePluginChunkTicket(int, int, Plugin)
     */
    @NotNull
    public Map<Plugin, Collection<Chunk>> getPluginChunkTickets();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与给定{@link BoundingBox 边界框}相交的所有区块.
     * <p>原文：
     * Gets all Chunks intersecting the given BoundingBox.
     *
     * @param box 要检查的边界框
     * <p>原文：BoundingBox to check
     * @return 与给定边界框相交的区块集合
     * <p>原文：A collection of Chunks intersecting the given BoundingBox
     */
    @NotNull
    public Collection<Chunk> getIntersectingChunks(@NotNull BoundingBox box);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}丢出一个物品.
     * <p>原文：
     * Drops an item at the specified {@link Location}
     *
     * @param location 丢出物品的位置
     * <p>原文：Location to drop the item
     * @param item 丢出的物品堆
     * <p>原文：ItemStack to drop
     * @return 这个方法会创建一个ItemDrop（物品掉落）实体作为结果
     * <p>原文：ItemDrop entity created as a result of this method
     */
    @NotNull
    default Item dropItem(@NotNull Location location, @NotNull ItemStack item) {
        return this.dropItem(location, item, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}丢出一个物品.
     * <p>
     * 注意, 函数将在实体生成之前运行.
     * <p>原文：
     * Drops an item at the specified {@link Location}
     * Note that functions will run before the entity is spawned
     *
     * @param location 丢出物品的位置
     * <p>原文：Location to drop the item
     * @param item 丢出的物品堆
     * <p>原文：ItemStack to drop
     * @param function 在实体生成之前运行的函数
     * <p>原文：the function to be run before the entity is spawned.
     * @return 这个方法会创建一个ItemDrop（物品掉落）实体作为结果
     * <p>原文：ItemDrop entity created as a result of this method
     */
    @NotNull
    public Item dropItem(@NotNull Location location, @NotNull ItemStack item, @Nullable Consumer<? super Item> function);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}丢出一个随机偏移的物品.
     * <p>原文：
     * Drops an item at the specified {@link Location} with a random offset
     *
     * @param location 丢出物品的位置
     * <p>原文：Location to drop the item
     * @param item 丢出的物品堆
     * <p>原文：ItemStack to drop
     * @return 这个方法会创建一个ItemDrop（物品掉落）实体作为结果
     * <p>原文：ItemDrop entity created as a result of this method
     */
    @NotNull
    default Item dropItemNaturally(@NotNull Location location, @NotNull ItemStack item) {
        return this.dropItemNaturally(location, item, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}丢出一个随机偏移的物品.
     * <p>
     * 注意, 函数将在实体生成之前运行.
     * <p>原文：
     * Drops an item at the specified {@link Location} with a random offset
     * Note that functions will run before the entity is spawned
     *
     * @param location 丢出物品的位置
     * <p>原文：Location to drop the item
     * @param item 丢出的物品堆
     * <p>原文：ItemStack to drop
     * @param function 在实体生成之前运行的函数
     * <p>原文：the function to be run before the entity is spawned.
     * @return 这个方法会创建一个ItemDrop（物品掉落）实体作为结果
     * <p>原文：ItemDrop entity created as a result of this method
     */
    @NotNull
    public Item dropItemNaturally(@NotNull Location location, @NotNull ItemStack item, @Nullable Consumer<? super Item> function);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}创建一个{@link Arrow 箭}的实体.
     * <p>原文：
     * Creates an {@link Arrow} entity at the given {@link Location}
     *
     * @param location 生成箭的位置
     * <p>原文：Location to spawn the arrow
     * @param direction 箭射向的方向
     * <p>原文：Direction to shoot the arrow in
     * @param speed 箭的射速,建议为0.6
     * <p>原文：Speed of the arrow. A recommend speed is 0.6
     * @param spread 箭存在的时间(箭在多久后会消失)
     * <p>原文：Spread of the arrow. A recommend spread is 12
     * @return 这个方法会生成一个Arrow（箭）实体作为结果
     * <p>原文：Arrow entity spawned as a result of this method
     */
    @NotNull
    default Arrow spawnArrow(@NotNull Location location, @NotNull Vector direction, float speed, float spread) {
        return this.spawnArrow(location, direction, speed, spread, Arrow.class);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在给定的{@link Location 位置}生成指定类型的箭矢实体.
     * <p>原文：
     * Creates an arrow entity of the given class at the given {@link Location}
     *
     * @param <T> 要生成的箭矢类型
     * <p>原文：type of arrow to spawn
     * @param location 生成箭矢的位置
     * <p>原文：Location to spawn the arrow
     * @param direction 箭矢射击的方向
     * <p>原文：Direction to shoot the arrow in
     * @param speed 箭矢的速度, 建议值为0.6
     * <p>原文：Speed of the arrow. A recommend speed is 0.6
     * @param spread 箭矢的散射度, 建议值为12
     * <p>原文：Spread of the arrow. A recommend spread is 12
     * @param clazz 箭矢的实体类
     * {@link org.bukkit.entity.SpectralArrow},{@link org.bukkit.entity.Arrow},{@link org.bukkit.entity.TippedArrow}
     * <p>原文：the Entity class for the arrow
     * {@link org.bukkit.entity.SpectralArrow},{@link org.bukkit.entity.Arrow},{@link org.bukkit.entity.TippedArrow}
     * @return 作为此方法结果生成的箭矢实体
     * <p>原文：Arrow entity spawned as a result of this method
     */
    @NotNull
    public <T extends AbstractArrow> T spawnArrow(@NotNull Location location, @NotNull Vector direction, float speed, float spread, @NotNull Class<T> clazz);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}创建一颗树.
     * <p>原文：
     * Creates a tree at the given {@link Location}
     *
     * @param location 生成树的位置
     * <p>原文：Location to spawn the tree
     * @param type 创建的树的类型
     * <p>原文：Type of the tree to create
     * @return 如果树被成功生成则返回true，否则返回false
     * <p>原文：true if the tree was created successfully, otherwise false
     * @deprecated in favor of {@link #generateTree(Location, java.util.Random, TreeType)} to specify its own random instance
     * and this method is not accessible through {@link RegionAccessor}
     */
    @Deprecated(since = "1.21.6")
    public boolean generateTree(@NotNull Location location, @NotNull TreeType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}创建一颗树.
     * <p>原文：
     * Creates a tree at the given {@link Location}
     *
     * @param loc 生成树的位置
     * <p>原文：Location to spawn the tree
     * @param type 创建的树的类型
     * <p>原文：Type of the tree to create
     * @param delegate 这个方法会返回一个用于调用每个方块的改变的类作为结果
     * <p>原文：A class to call for each block changed as a result of
     *     this method
     * @return 如果树被成功生成则返回true，否则返回false
     * <p>原文：true if the tree was created successfully, otherwise false
     * @see #generateTree(org.bukkit.Location, java.util.Random, org.bukkit.TreeType, java.util.function.Consumer)
     * @deprecated 此方法不处理方块实体（蜂巢）
     * <p>原文：this method does not handle block entities (bee nests)
     */
    @Deprecated(since = "1.17.1")
    public boolean generateTree(@NotNull Location loc, @NotNull TreeType type, @NotNull BlockChangeDelegate delegate);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}劈下闪电.
     * <p>原文：
     * Strikes lightning at the given {@link Location}
     *
     * @param loc 劈下闪电的位置
     * <p>原文：The location to strike lightning
     * @return lightning（闪电）实体
     * <p>原文：The lightning entity.
     */
    @NotNull
    public LightningStrike strikeLightning(@NotNull Location loc);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}劈下不会造成伤害的闪电.
     * <p>原文：
     * Strikes lightning at the given {@link Location} without doing damage
     *
     * @param loc 劈下闪电的位置
     * <p>原文：The location to strike lightning
     * @return lightning（闪电）实体
     * <p>原文：The lightning entity.
     */
    @NotNull
    public LightningStrike strikeLightningEffect(@NotNull Location loc);

    // Paper start
    /**
     * Finds the location of the nearest unobstructed Lightning Rod in a 128-block
     * radius around the given location. Returns {@code null} if no Lightning Rod is found.
     *
     * <p>Note: To activate a Lightning Rod, the position one block above it must be struck by lightning.</p>
     *
     * @param location {@link Location} to search for Lightning Rod around
     * @return {@link Location} of Lightning Rod or {@code null}
     */
    @Nullable
    public Location findLightningRod(@NotNull Location location);

    /**
     * Finds a target {@link Location} for lightning to strike.
     * <p>It selects from (in the following order):</p>
     * <ol>
     *  <li>the block above the nearest Lightning Rod, found using {@link World#findLightningRod(Location)}</li>
     *  <li>a random {@link LivingEntity} that can see the sky in a 6x6 cuboid
     *      around input X/Z coordinates. Y ranges from <i>the highest motion-blocking
     *      block at the input X/Z - 3</i> to <i>the height limit + 3</i></li>
     * </ol>
     * <p>Returns {@code null} if no target is found.</p>
     *
     * @param location {@link Location} to search for target around
     * @return lightning target or {@code null}
     */
    @Nullable
    public Location findLightningTarget(@NotNull Location location);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个这个世界所有实体的列表.
     * <p>原文：
     * Get a list of all entities in this World
     *
     * @return 一个当前处在这个世界的所有实体的列表
     * <p>原文：A List of all Entities currently residing in this world
     */
    @NotNull
    public List<Entity> getEntities();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个这个世界所有生物实体的列表.
     * <p>原文：
     * Get a list of all living entities in this World
     *
     * @return 一个当前处在这个世界的所有生物实体的列表
     * <p>原文：A List of all LivingEntities currently residing in this world
     */
    @NotNull
    public List<LivingEntity> getLivingEntities();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个在这个世界的所有与指定的类/接口相匹配的实体的集合.
     * <p>原文：
     * Get a collection of all entities in this World matching the given
     * class/interface
     *
     * @param <T> 一个实体子类
     * <p>原文：an entity subclass
     * @param classes 用于匹配的表示实体类型的类
     * <p>原文：The classes representing the types of entity to match
     * @return 一个当前处在这个世界的所有与指定的类/接口相匹配的实体的列表
     * <p>原文：A List of all Entities currently residing in this world that
     *     match the given class/interface
     */
    @Deprecated(since = "1.1")
    @NotNull
    public <T extends Entity> Collection<T> getEntitiesByClass(@NotNull Class<T>... classes);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个在这个世界的所有与指定的类/接口相匹配的实体的集合.
     * <p>原文：
     * Get a collection of all entities in this World matching the given
     * class/interface
     *
     * @param <T> 一个实体子类
     * <p>原文：an entity subclass
     * @param cls 用于匹配的表示实体类型的类
     * <p>原文：The class representing the type of entity to match
     * @return  一个当前处在这个世界的所有与指定的类/接口相匹配的实体的列表
     * <p>原文：A List of all Entities currently residing in this world that
     *     match the given class/interface
     */
    @NotNull
    public <T extends Entity> Collection<T> getEntitiesByClass(@NotNull Class<T> cls);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个在这个世界的所有与任一指定的类/接口相匹配的实体的集合.
     * <p>原文：
     * Get a collection of all entities in this World matching any of the
     * given classes/interfaces
     *
     * @param classes 用于匹配的表示实体类型的类
     * <p>原文：The classes representing the types of entity to match
     * @return 一个当前处在这个世界的所有与一个或更多指定的类/接口相匹配的实体的列表
     * <p>原文：A List of all Entities currently residing in this world that
     *     match one or more of the given classes/interfaces
     */
    @NotNull
    public Collection<Entity> getEntitiesByClasses(@NotNull Class<?>... classes);

    // Paper start - additional getNearbyEntities API
    /**
     * Gets nearby LivingEntities within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param radius Radius
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    default @NotNull Collection<LivingEntity> getNearbyLivingEntities(final @NotNull Location loc, final double radius) {
        return this.getNearbyEntitiesByType(LivingEntity.class, loc, radius, radius, radius);
    }

    /**
     * Gets nearby LivingEntities within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param xzRadius X/Z Radius
     * @param yRadius Y Radius
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    default @NotNull Collection<LivingEntity> getNearbyLivingEntities(final @NotNull Location loc, final double xzRadius, final double yRadius) {
        return this.getNearbyEntitiesByType(LivingEntity.class, loc, xzRadius, yRadius, xzRadius);
    }

    /**
     * Gets nearby LivingEntities within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param xRadius X Radius
     * @param yRadius Y Radius
     * @param zRadius Z radius
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    default @NotNull Collection<LivingEntity> getNearbyLivingEntities(final @NotNull Location loc, final double xRadius, final double yRadius, final double zRadius) {
        return this.getNearbyEntitiesByType(LivingEntity.class, loc, xRadius, yRadius, zRadius);
    }

    /**
     * Gets nearby LivingEntities within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param radius X Radius
     * @param predicate a predicate used to filter results
     * @return the collection of living entities near location. This will always be a non-null collection
     */
    default @NotNull Collection<LivingEntity> getNearbyLivingEntities(final @NotNull Location loc, final double radius, final @Nullable Predicate<? super LivingEntity> predicate) {
        return this.getNearbyEntitiesByType(LivingEntity.class, loc, radius, radius, radius, predicate);
    }

    /**
     * Gets nearby LivingEntities within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param xzRadius X/Z Radius
     * @param yRadius Y Radius
     * @param predicate a predicate used to filter results
     * @return the collection of living entities near location. This will always be a non-null collection
     */
    default @NotNull Collection<LivingEntity> getNearbyLivingEntities(final @NotNull Location loc, final double xzRadius, final double yRadius, final @Nullable Predicate<? super LivingEntity> predicate) {
        return this.getNearbyEntitiesByType(LivingEntity.class, loc, xzRadius, yRadius, xzRadius, predicate);
    }

    /**
     * Gets nearby LivingEntities within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param xRadius X Radius
     * @param yRadius Y Radius
     * @param zRadius Z radius
     * @param predicate a predicate used to filter results
     * @return the collection of living entities near location. This will always be a non-null collection.
     */
    default @NotNull Collection<LivingEntity> getNearbyLivingEntities(final @NotNull Location loc, final double xRadius, final double yRadius, final double zRadius, final @Nullable Predicate<? super LivingEntity> predicate) {
        return this.getNearbyEntitiesByType(LivingEntity.class, loc, xRadius, yRadius, zRadius, predicate);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param radius X/Y/Z Radius
     * @return the collection of living entities near location. This will always be a non-null collection.
     */
    default @NotNull Collection<Player> getNearbyPlayers(final @NotNull Location loc, final double radius) {
        return this.getNearbyEntitiesByType(Player.class, loc, radius, radius, radius);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param xzRadius X/Z Radius
     * @param yRadius Y Radius
     * @return the collection of living entities near location. This will always be a non-null collection.
     */
    default @NotNull Collection<Player> getNearbyPlayers(final @NotNull Location loc, final double xzRadius, final double yRadius) {
        return this.getNearbyEntitiesByType(Player.class, loc, xzRadius, yRadius, xzRadius);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param xRadius X Radius
     * @param yRadius Y Radius
     * @param zRadius Z Radius
     * @return the collection of players near location. This will always be a non-null collection.
     */
    default @NotNull Collection<Player> getNearbyPlayers(final @NotNull Location loc, final double xRadius, final double yRadius, final double zRadius) {
        return this.getNearbyEntitiesByType(Player.class, loc, xRadius, yRadius, zRadius);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param radius X/Y/Z Radius
     * @param predicate a predicate used to filter results
     * @return the collection of players near location. This will always be a non-null collection.
     */
    default @NotNull Collection<Player> getNearbyPlayers(final @NotNull Location loc, final double radius, final @Nullable Predicate<? super Player> predicate) {
        return this.getNearbyEntitiesByType(Player.class, loc, radius, radius, radius, predicate);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param xzRadius X/Z Radius
     * @param yRadius Y Radius
     * @param predicate a predicate used to filter results
     * @return the collection of players near location. This will always be a non-null collection.
     */
    default @NotNull Collection<Player> getNearbyPlayers(final @NotNull Location loc, final double xzRadius, final double yRadius, final @Nullable Predicate<? super Player> predicate) {
        return this.getNearbyEntitiesByType(Player.class, loc, xzRadius, yRadius, xzRadius, predicate);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param loc Center location
     * @param xRadius X Radius
     * @param yRadius Y Radius
     * @param zRadius Z Radius
     * @param predicate a predicate used to filter results
     * @return the collection of players near location. This will always be a non-null collection.
     */
    default @NotNull Collection<Player> getNearbyPlayers(final @NotNull Location loc, final double xRadius, final double yRadius, final double zRadius, final @Nullable Predicate<? super Player> predicate) {
        return this.getNearbyEntitiesByType(Player.class, loc, xRadius, yRadius, zRadius, predicate);
    }

    /**
     * Gets all nearby entities of the specified type, within the specified radius (bounding box)
     *
     * @param clazz Type to filter by
     * @param loc Center location
     * @param radius X/Y/Z radius to search within
     * @param <T> the entity type
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    default @NotNull <T extends Entity> Collection<T> getNearbyEntitiesByType(final @Nullable Class<? extends T> clazz, final @NotNull Location loc, final double radius) {
        return this.getNearbyEntitiesByType(clazz, loc, radius, radius, radius, null);
    }

    /**
     * Gets all nearby entities of the specified type, within the specified radius, with x and x radius matching (bounding box)
     *
     * @param clazz Type to filter by
     * @param loc Center location
     * @param xzRadius X/Z radius to search within
     * @param yRadius Y radius to search within
     * @param <T> the entity type
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    default @NotNull <T extends Entity> Collection<T> getNearbyEntitiesByType(final @Nullable Class<? extends T> clazz, final @NotNull Location loc, final double xzRadius, final double yRadius) {
        return this.getNearbyEntitiesByType(clazz, loc, xzRadius, yRadius, xzRadius, null);
    }

    /**
     * Gets all nearby entities of the specified type, within the specified radius (bounding box)
     *
     * @param clazz Type to filter by
     * @param loc Center location
     * @param xRadius X Radius
     * @param yRadius Y Radius
     * @param zRadius Z Radius
     * @param <T> the entity type
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    default @NotNull <T extends Entity> Collection<T> getNearbyEntitiesByType(final @Nullable Class<? extends T> clazz, final @NotNull Location loc, final double xRadius, final double yRadius, final double zRadius) {
        return this.getNearbyEntitiesByType(clazz, loc, xRadius, yRadius, zRadius, null);
    }

    /**
     * Gets all nearby entities of the specified type, within the specified radius (bounding box)
     *
     * @param clazz Type to filter by
     * @param loc Center location
     * @param radius X/Y/Z radius to search within
     * @param predicate a predicate used to filter results
     * @param <T> the entity type
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    default @NotNull <T extends Entity> Collection<T> getNearbyEntitiesByType(final @Nullable Class<? extends T> clazz, final @NotNull Location loc, final double radius, final @Nullable Predicate<? super T> predicate) {
        return this.getNearbyEntitiesByType(clazz, loc, radius, radius, radius, predicate);
    }

    /**
     * Gets all nearby entities of the specified type, within the specified radius, with x and x radius matching (bounding box)
     *
     * @param clazz Type to filter by
     * @param loc Center location
     * @param xzRadius X/Z radius to search within
     * @param yRadius Y radius to search within
     * @param predicate a predicate used to filter results
     * @param <T> the entity type
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    default @NotNull <T extends Entity> Collection<T> getNearbyEntitiesByType(final @Nullable Class<? extends T> clazz, final @NotNull Location loc, final double xzRadius, final double yRadius, final @Nullable Predicate<? super T> predicate) {
        return this.getNearbyEntitiesByType(clazz, loc, xzRadius, yRadius, xzRadius, predicate);
    }

     /**
      * Gets all nearby entities of the specified type, within the specified radius (bounding box)
      *
      * @param clazz Type to filter by
      * @param loc Center location
      * @param xRadius X Radius
      * @param yRadius Y Radius
      * @param zRadius Z Radius
      * @param predicate a predicate used to filter results
      * @param <T> the entity type
      * @return the collection of entities near location. This will always be a non-null collection.
      */
    default <T extends Entity> @NotNull Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends T> clazz, final @NotNull Location loc, final double xRadius, final double yRadius, final double zRadius, final @Nullable Predicate<? super T> predicate) {
        final List<T> nearby = new ArrayList<>();
        for (final Entity entity : this.getNearbyEntities(loc, xRadius, yRadius, zRadius)) {
            //noinspection unchecked
            if ((clazz == null || clazz.isInstance(entity)) && (predicate == null || predicate.test((T) entity))) {
                //noinspection unchecked
                nearby.add((T) entity);
            }
        }
        return nearby;
    }
    // Paper end - additional getNearbyEntities API

    // Paper start - async chunks API
    /**
     * This is the Legacy API before Java 8 was supported. Java 8 Consumer is provided,
     * as well as future support
     *
     * Used by {@link World#getChunkAtAsync(Location,ChunkLoadCallback)} methods
     * to request a {@link Chunk} to be loaded, with this callback receiving
     * the chunk when it is finished.
     *
     * This callback will be executed on synchronously on the main thread.
     *
     * Timing and order this callback is fired is intentionally not defined
     * and subject to change.
     *
     * @deprecated Use either the Future or the Consumer based methods
     */
    @Deprecated(since = "1.13.1")
    public static interface ChunkLoadCallback extends java.util.function.Consumer<Chunk> {
        public void onLoad(@NotNull Chunk chunk);

        // backwards compat to old api
        @Override
        default void accept(@NotNull Chunk chunk) {
            onLoad(chunk);
        }
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The {@link ChunkLoadCallback} will always be executed synchronously
     * on the main Server Thread.
     *
     * @deprecated Use either the Future or the Consumer based methods
     * @param x Chunk x-coordinate
     * @param z Chunk z-coordinate
     * @param cb Callback to receive the chunk when it is loaded.
     *           will be executed synchronously
     */
    @Deprecated(since = "1.13.1")
    public default void getChunkAtAsync(int x, int z, @NotNull ChunkLoadCallback cb) {
        this.getChunkAtAsync(x, z, (java.util.function.Consumer<Chunk>)cb);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given {@link Location}
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The {@link ChunkLoadCallback} will always be executed synchronously
     * on the main Server Thread.
     *
     * @deprecated Use either the Future or the Consumer based methods
     * @param loc Location of the chunk
     * @param cb Callback to receive the chunk when it is loaded.
     *           will be executed synchronously
     */
    @Deprecated(since = "1.13.1")
    public default void getChunkAtAsync(@NotNull Location loc, @NotNull ChunkLoadCallback cb) {
        this.getChunkAtAsync(loc.getBlockX() >> 4, loc.getBlockZ() >> 4, cb);
    }

    /**
     * Requests {@link Chunk} to be loaded that contains the given {@link Block}
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The {@link ChunkLoadCallback} will always be executed synchronously
     * on the main Server Thread.
     *
     * @deprecated Use either the Future or the Consumer based methods
     * @param block Block to get the containing chunk from
     * @param cb Callback to receive the chunk when it is loaded.
     *           will be executed synchronously
     */
    @Deprecated(since = "1.13.1")
    public default void getChunkAtAsync(@NotNull Block block, @NotNull ChunkLoadCallback cb) {
        this.getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, cb);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The {@link java.util.function.Consumer} will always be executed synchronously
     * on the main Server Thread.
     *
     * @param x Chunk x-coordinate
     * @param z Chunk z-coordinate
     * @param cb Callback to receive the chunk when it is loaded.
     *           will be executed synchronously
     */
    default void getChunkAtAsync(final int x, final int z, final @NotNull Consumer<? super Chunk> cb) {
        this.getChunkAtAsync(x, z, true, cb);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The {@link java.util.function.Consumer} will always be executed synchronously
     * on the main Server Thread.
     *
     * @param x Chunk x-coordinate
     * @param z Chunk z-coordinate
     * @param gen Should we generate a chunk if it doesn't exist or not
     * @param cb Callback to receive the chunk when it is loaded.
     *           will be executed synchronously
     */
    default void getChunkAtAsync(final int x, final int z, final boolean gen, final @NotNull Consumer<? super Chunk> cb) {
        this.getChunkAtAsync(x, z, gen, false, cb);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The {@link java.util.function.Consumer} will always be executed synchronously
     * on the main Server Thread.
     *
     * @param x Chunk x-coordinate
     * @param z Chunk z-coordinate
     * @param gen Should we generate a chunk if it doesn't exist or not
     * @param urgent If true, the chunk may be prioritised to be loaded above other chunks in queue
     * @param cb Callback to receive the chunk when it is loaded.
     *           will be executed synchronously
     */
    void getChunkAtAsync(final int x, final int z, final boolean gen, final boolean urgent, final @NotNull Consumer<? super Chunk> cb);

    /**
     * Requests all chunks with x between [minX, maxZ] and z
     * between [minZ, maxZ] to be loaded.
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will invoke the callback at possibly a later time.
     *
     * You should use this method if you need chunks loaded but do not need them
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The {@link Runnable} will always be executed synchronously
     * on the main Server Thread, and when invoked all chunks requested will be loaded.
     *
     * @param minX Minimum Chunk x-coordinate
     * @param minZ Minimum Chunk z-coordinate
     * @param maxX Maximum Chunk x-coordinate
     * @param maxZ Maximum Chunk z-coordinate
     * @param urgent If true, the chunks may be prioritised to be loaded above other chunks in queue
     * @param cb Callback to invoke when all chunks are loaded.
     *           Will be executed synchronously
     * @see Chunk
     */
    void getChunksAtAsync(final int minX, final int minZ, final int maxX, final int maxZ, final boolean urgent,
                          final @NotNull Runnable cb);

    /**
     * Requests a {@link Chunk} to be loaded at the given {@link Location}
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The {@link java.util.function.Consumer} will always be executed synchronously
     * on the main Server Thread.
     *
     * @param loc Location of the chunk
     * @param cb Callback to receive the chunk when it is loaded.
     *           will be executed synchronously
     */
    default void getChunkAtAsync(final @NotNull Location loc, final @NotNull Consumer<? super Chunk> cb) {
        this.getChunkAtAsync((int) Math.floor(loc.getX()) >> 4, (int) Math.floor(loc.getZ()) >> 4, true, cb);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given {@link Location}
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The {@link java.util.function.Consumer} will always be executed synchronously
     * on the main Server Thread.
     *
     * @param loc Location of the chunk
     * @param gen Should the chunk generate if it doesn't exist
     * @param cb Callback to receive the chunk when it is loaded.
     *           will be executed synchronously
     */
    default void getChunkAtAsync(final @NotNull Location loc, final boolean gen, final @NotNull Consumer<? super Chunk> cb) {
        this.getChunkAtAsync((int) Math.floor(loc.getX()) >> 4, (int) Math.floor(loc.getZ()) >> 4, gen, cb);
    }

    /**
     * Requests {@link Chunk} to be loaded that contains the given {@link Block}
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The {@link java.util.function.Consumer} will always be executed synchronously
     * on the main Server Thread.
     *
     * @param block Block to get the containing chunk from
     * @param cb Callback to receive the chunk when it is loaded.
     *           will be executed synchronously
     */
    default void getChunkAtAsync(final @NotNull Block block, final @NotNull Consumer<? super Chunk> cb) {
        this.getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, true, cb);
    }

    /**
     * Requests {@link Chunk} to be loaded that contains the given {@link Block}
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The {@link java.util.function.Consumer} will always be executed synchronously
     * on the main Server Thread.
     *
     * @param block Block to get the containing chunk from
     * @param gen Should the chunk generate if it doesn't exist
     * @param cb Callback to receive the chunk when it is loaded.
     *           will be executed synchronously
     */
    default void getChunkAtAsync(final @NotNull Block block, final boolean gen, final @NotNull Consumer<? super Chunk> cb) {
        this.getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, gen, cb);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The future will always be executed synchronously
     * on the main Server Thread.
     * @param loc Location to load the corresponding chunk from
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsync(final @NotNull Location loc) {
        return this.getChunkAtAsync((int) Math.floor(loc.getX()) >> 4, (int) Math.floor(loc.getZ()) >> 4, true);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The future will always be executed synchronously
     * on the main Server Thread.
     * @param loc Location to load the corresponding chunk from
     * @param gen Should the chunk generate if it doesn't exist
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsync(final @NotNull Location loc, final boolean gen) {
        return this.getChunkAtAsync((int) Math.floor(loc.getX()) >> 4, (int) Math.floor(loc.getZ()) >> 4, gen);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The future will always be executed synchronously
     * on the main Server Thread.
     * @param block Block to load the corresponding chunk from
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsync(final @NotNull Block block) {
        return this.getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, true);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The future will always be executed synchronously
     * on the main Server Thread.
     * @param block Block to load the corresponding chunk from
     * @param gen Should the chunk generate if it doesn't exist
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsync(final @NotNull Block block, final boolean gen) {
        return this.getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, gen);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The future will always be executed synchronously
     * on the main Server Thread.
     *
     * @param x Chunk x-coordinate
     * @param z Chunk z-coordinate
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsync(final int x, final int z) {
        return this.getChunkAtAsync(x, z, true);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     *
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     *
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     *
     * The future will always be executed synchronously
     * on the main Server Thread.
     *
     * @param x Chunk x-coordinate
     * @param z Chunk z-coordinate
     * @param gen Should we generate a chunk if it doesn't exist or not
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsync(final int x, final int z, final boolean gen) {
        return this.getChunkAtAsync(x, z, gen, false);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     * <p>
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     * <p>
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish for it to be prioritised over other
     * chunk loads in queue.
     * <p>
     * The future will always be executed synchronously
     * on the main Server Thread.
     * @param loc Location to load the corresponding chunk from
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsyncUrgently(final @NotNull Location loc) {
        return this.getChunkAtAsync((int) Math.floor(loc.getX()) >> 4, (int) Math.floor(loc.getZ()) >> 4, true, true);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     * <p>
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     * <p>
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish for it to be prioritised over other
     * chunk loads in queue.
     * <p>
     * The future will always be executed synchronously
     * on the main Server Thread.
     * @param loc Location to load the corresponding chunk from
     * @param gen Should the chunk generate if it doesn't exist
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsyncUrgently(final @NotNull Location loc, final boolean gen) {
        return this.getChunkAtAsync((int) Math.floor(loc.getX()) >> 4, (int) Math.floor(loc.getZ()) >> 4, gen, true);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     * <p>
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     * <p>
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish for it to be prioritised over other
     * chunk loads in queue.
     * <p>
     * The future will always be executed synchronously
     * on the main Server Thread.
     * @param block Block to load the corresponding chunk from
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsyncUrgently(final @NotNull Block block) {
        return this.getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, true, true);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     * <p>
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     * <p>
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish for it to be prioritised over other
     * chunk loads in queue.
     * <p>
     * The future will always be executed synchronously
     * on the main Server Thread.
     * @param block Block to load the corresponding chunk from
     * @param gen Should the chunk generate if it doesn't exist
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsyncUrgently(final @NotNull Block block, final boolean gen) {
        return this.getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, gen, true);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates
     * <p>
     * This method makes no guarantee on how fast the chunk will load,
     * and will complete the future at a later time.
     * <p>
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish for it to be prioritised over other
     * chunk loads in queue.
     * <p>
     * The future will always be executed synchronously
     * on the main Server Thread.
     *
     * @param x Chunk x-coordinate
     * @param z Chunk z-coordinate
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsyncUrgently(final int x, final int z) {
        return this.getChunkAtAsync(x, z, true, true);
    }

    /**
     * Requests a {@link Chunk} to be loaded at the given coordinates.
     * <p>
     * This method makes no guarantee on how fast the chunk will load,
     * and will return the chunk to the callback at a later time.
     * <p>
     * You should use this method if you need a chunk but do not need it
     * immediately, and you wish to let the server control the speed
     * of chunk loads, keeping performance in mind.
     * <p>
     * The future will always be executed synchronously
     * on the main Server Thread.
     *
     * @param x Chunk x-coordinate
     * @param z Chunk z-coordinate
     * @param gen Should the chunk generate if it doesn't exist
     * @param urgent If true, the chunk may be prioritised to be loaded above other chunks in queue
     *
     * @return Future that will resolve when the chunk is loaded
     */
    default @NotNull java.util.concurrent.CompletableFuture<Chunk> getChunkAtAsync(int x, int z, boolean gen, boolean urgent) {
        java.util.concurrent.CompletableFuture<Chunk> ret = new java.util.concurrent.CompletableFuture<>();
        this.getChunkAtAsync(x, z, gen, urgent, ret::complete);
        return ret;
    }
    // Paper end - async chunks API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个这个世界的所有玩家的列表.
     * <p>原文：
     * Get a list of all players in this World
     *
     * @return 一个当前处在这个世界的所有玩家的列表
     * <p>原文：A list of all Players currently residing in this world
     */
    @NotNull
    public List<Player> getPlayers();

    // Paper start
    @NotNull
    @Override
    default Iterable<? extends net.kyori.adventure.audience.Audience> audiences() {
        return this.getPlayers();
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回一个以这个位置为中心的包围着的所有实体的列表(译注:这个可能不太好理解，就是在这个位置，按指定的搜索范围，搜索这个范围里的所有实体).
     * <p>
     * 这可能不会考虑当前尚未加载的区块中的实体. 一些实现可能会对搜索的范围的大小施加限制.
     * <p>原文：
     * Returns a list of entities within a bounding box centered around a
     * Location.
     * <p>
     * This may not consider entities in currently unloaded chunks. Some
     * implementations may impose artificial restrictions on the size of the
     * search bounding box.
     *
     * @param location 搜索范围的中心
     * <p>原文：The center of the bounding box
     * @param x 搜索范围的x半轴长度
     * <p>原文：1/2 the size of the box along x axis
     * @param y 搜索范围的y半轴长度
     * <p>原文：1/2 the size of the box along y axis
     * @param z 搜索范围的z半轴长度
     * <p>原文：1/2 the size of the box along z axis
     * @return 在位置附近的实体的集合,一般不为空
     * <p>原文：the collection of entities near location. This will always be a
     *      non-null collection.
     */
    @NotNull
    default Collection<Entity> getNearbyEntities(@NotNull Location location, double x, double y, double z) {
        return this.getNearbyEntities(location, x, y, z, null);
    }

    // Paper start - getEntity by UUID API
    /**
     * Gets an entity in this world by its UUID
     *
     * @param uuid the UUID of the entity
     * @return the entity with the given UUID, or null if it isn't found
     */
    @Nullable
    public Entity getEntity(@NotNull java.util.UUID uuid);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回一个以这个位置为中心的包围着的所有实体的列表.
     * <p>
     * 这可能不会考虑当前尚未加载的区块中的实体. 一些实现可能会对搜索的范围的大小施加限制.
     * <p>原文：
     * Returns a list of entities within a bounding box centered around a
     * Location.
     * <p>
     * This may not consider entities in currently unloaded chunks. Some
     * implementations may impose artificial restrictions on the size of the
     * search bounding box.
     *
     * @param location 搜索范围的中心
     * <p>原文：The center of the bounding box
     * @param x 搜索范围的x半轴长度
     * <p>原文：1/2 the size of the box along x axis
     * @param y 搜索范围的y半轴长度
     * <p>原文：1/2 the size of the box along y axis
     * @param z 搜索范围的z半轴长度
     * <p>原文：1/2 the size of the box along z axis
     * @param filter 只考虑满足此过滤器的实体, 或者 <code>null</code> 考虑所有实体
     * <p>原文：only entities that fulfill this predicate are considered,
     *     or <code>null</code> to consider all entities
     * @return 在位置附近的实体的集合, 一般不为空
     * <p>原文：the collection of entities near location. This will always be a
     *     non-null collection.
     */
    @NotNull
    public Collection<Entity> getNearbyEntities(@NotNull Location location, double x, double y, double z, @Nullable Predicate<? super Entity> filter);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回给定{@link BoundingBox 边界框}内的所有实体列表.
     * <p>
     * 这可能不会考虑当前尚未加载的区块中的实体. 一些实现可能会对搜索的范围的大小施加限制.
     * <p>原文：
     * Returns a list of entities within the given bounding box.
     * <p>
     * This may not consider entities in currently unloaded chunks. Some
     * implementations may impose artificial restrictions on the size of the
     * search bounding box.
     *
     * @param boundingBox 边界框
     * <p>原文：the bounding box
     * @return 边界框内的实体集合, 总是非null的集合
     * <p>原文：the collection of entities within the bounding box, will always
     *     be a non-null collection
     */
    @NotNull
    default Collection<Entity> getNearbyEntities(@NotNull BoundingBox boundingBox) {
        return this.getNearbyEntities(boundingBox, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回给定{@link BoundingBox 边界框}内的所有实体列表.
     * <p>
     * 这可能不会考虑当前尚未加载的区块中的实体. 一些实现可能会对搜索的范围的大小施加限制.
     * <p>原文：
     * Returns a list of entities within the given bounding box.
     * <p>
     * This may not consider entities in currently unloaded chunks. Some
     * implementations may impose artificial restrictions on the size of the
     * search bounding box.
     *
     * @param boundingBox 边界框
     * <p>原文：the bounding box
     * @param filter 只考虑满足此过滤器的实体, 或者 <code>null</code> 考虑所有实体
     * <p>原文：only entities that fulfill this predicate are considered,
     *     or <code>null</code> to consider all entities
     * @return 边界框内的实体集合, 总是非null的集合
     * <p>原文：the collection of entities within the bounding box, will always
     *     be a non-null collection
     */
    @NotNull
    public Collection<Entity> getNearbyEntities(@NotNull BoundingBox boundingBox, @Nullable Predicate<? super Entity> filter);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行检查实体碰撞的射线跟踪.
     * <p>
     * 这可能不考虑当前已经卸载的区块中的实体. 一些实现可能会对最大距离施加人为限制.
     * <p>原文：
     * Performs a ray trace that checks for entity collisions.
     * <p>
     * This may not consider entities in currently unloaded chunks. Some
     * implementations may impose artificial restrictions on the maximum
     * distance.
     * <p>
     * <b>Note:</b> Due to display entities having a zero size hitbox, this method will not detect them.
     * To detect display entities use {@link #rayTraceEntities(Location, Vector, double, double)} with a positive raySize
     *
     * @param start 起始位置
     * <p>原文：the start position
     * @param direction 射线方向
     * <p>原文：the ray direction
     * @param maxDistance 最大距离
     * <p>原文：the maximum distance
     * @return 最近的射线跟踪命中结果, 如果没有命中, 则为<code>null</code>
     * <p>原文：the closest ray trace hit result, or <code>null</code> if there
     *     is no hit
     * @see #rayTraceEntities(Location, Vector, double, double, Predicate)
     */
    @Nullable
    default RayTraceResult rayTraceEntities(@NotNull Location start, @NotNull Vector direction, double maxDistance) {
        return this.rayTraceEntities(start, direction, maxDistance, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行检查实体碰撞的射线跟踪.
     * <p>
     * 这可能不考虑当前已经卸载的区块中的实体. 一些实现可能会对最大距离施加人为限制.
     * <p>原文：
     * Performs a ray trace that checks for entity collisions.
     * <p>
     * This may not consider entities in currently unloaded chunks. Some
     * implementations may impose artificial restrictions on the maximum
     * distance.
     *
     * @param start 起始位置
     * <p>原文：the start position
     * @param direction 射线方向
     * <p>原文：the ray direction
     * @param maxDistance 最大距离
     * <p>原文：the maximum distance
     * @param raySize 在进行碰撞检查之前, 实体边界框将按此值均匀地放大(或缩小)
     * <p>原文：entity bounding boxes will be uniformly expanded (or
     *     shrunk) by this value before doing collision checks
     * @return 最近的射线跟踪命中结果, 如果没有命中, 则为<code>null</code>
     * <p>原文：the closest ray trace hit result, or <code>null</code> if there
     *     is no hit
     * @see #rayTraceEntities(Location, Vector, double, double, Predicate)
     */
    @Nullable
    default RayTraceResult rayTraceEntities(@NotNull Location start, @NotNull Vector direction, double maxDistance, double raySize) {
        return this.rayTraceEntities(start, direction, maxDistance, raySize, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行检查实体碰撞的射线跟踪.
     * <p>
     * 这可能不考虑当前已经卸载的区块中的实体. 一些实现可能会对最大距离施加人为限制.
     * <p>原文：
     * Performs a ray trace that checks for entity collisions.
     * <p>
     * This may not consider entities in currently unloaded chunks. Some
     * implementations may impose artificial restrictions on the maximum
     * distance.
     * <p>
     * <b>Note:</b> Due to display entities having a zero size hitbox, this method will not detect them.
     * To detect display entities use {@link #rayTraceEntities(Location, Vector, double, double, Predicate)} with a positive raySize
     *
     * @param start 起始位置
     * <p>原文：the start position
     * @param direction 射线方向
     * <p>原文：the ray direction
     * @param maxDistance 最大距离
     * <p>原文：the maximum distance
     * @param filter 只考虑满足此过滤器的实体, 或者 <code>null</code> 考虑所有实体
     * <p>原文：only entities that fulfill this predicate are considered,
     *     or <code>null</code> to consider all entities
     * @return 最近的射线跟踪命中结果, 如果没有命中, 则为<code>null</code>
     * <p>原文：the closest ray trace hit result, or <code>null</code> if there
     *     is no hit
     * @see #rayTraceEntities(Location, Vector, double, double, Predicate)
     */
    @Nullable
    default RayTraceResult rayTraceEntities(@NotNull Location start, @NotNull Vector direction, double maxDistance, @Nullable Predicate<? super Entity> filter) {
        return this.rayTraceEntities(start, direction, maxDistance, 0.0D, filter);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行检查实体碰撞的射线跟踪.
     * <p>
     * 这可能不考虑当前已经卸载的区块中的实体. 一些实现可能会对最大距离施加人为限制.
     * <p>原文：
     * Performs a ray trace that checks for entity collisions.
     * <p>
     * This may not consider entities in currently unloaded chunks. Some
     * implementations may impose artificial restrictions on the maximum
     * distance.
     *
     * @param start 起始位置
     * <p>原文：the start position
     * @param direction 射线方向
     * <p>原文：the ray direction
     * @param maxDistance 最大距离
     * <p>原文：the maximum distance
     * @param raySize 在进行碰撞检查之前, 实体边界框将按此值均匀地放大(或缩小)
     * <p>原文：entity bounding boxes will be uniformly expanded (or
     *     shrunk) by this value before doing collision checks
     * @param filter 只考虑满足此过滤器的实体, 或者 <code>null</code> 考虑所有实体
     * <p>原文：only entities that fulfill this predicate are considered,
     *     or <code>null</code> to consider all entities
     * @return 最近的射线跟踪命中结果, 如果没有命中, 则为<code>null</code>
     * <p>原文：the closest ray trace hit result, or <code>null</code> if there
     *     is no hit
     */
    @Nullable
    default RayTraceResult rayTraceEntities(@NotNull Location start, @NotNull Vector direction, double maxDistance, double raySize, @Nullable Predicate<? super Entity> filter) {
        return rayTraceEntities((io.papermc.paper.math.Position) start, direction, maxDistance, raySize, filter);
    }

    // Paper start
    /**
     * Performs a ray trace that checks for entity collisions.
     * <p>
     * This may not consider entities in currently unloaded chunks. Some
     * implementations may impose artificial restrictions on the maximum
     * distance.
     *
     * @param start the start position
     * @param direction the ray direction
     * @param maxDistance the maximum distance
     * @param raySize entity bounding boxes will be uniformly expanded (or
     *     shrunk) by this value before doing collision checks
     * @param filter only entities that fulfill this predicate are considered,
     *     or <code>null</code> to consider all entities
     * @return the closest ray trace hit result, or <code>null</code> if there
     *     is no hit
     */
    @Nullable RayTraceResult rayTraceEntities(io.papermc.paper.math.@NotNull Position start, @NotNull Vector direction, double maxDistance, double raySize, @Nullable Predicate<? super Entity> filter);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行射线跟踪, 使用方块的精确碰撞形状来检查方块碰撞.
     * <p>
     * 这将考虑与可穿过的方块的碰撞, 但忽略流体.
     * <p>
     * 这可能会导致区块加载! 一些实现可能会对最大距离施加人为限制.
     * <p>原文：
     * Performs a ray trace that checks for block collisions using the blocks'
     * precise collision shapes.
     * <p>
     * This takes collisions with passable blocks into account, but ignores
     * fluids.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param start 起始位置
     * <p>原文：the start location
     * @param direction 射线方向
     * <p>原文：the ray direction
     * @param maxDistance 最大距离
     * <p>原文：the maximum distance
     * @return 最近的射线跟踪命中结果, 如果没有命中, 则为<code>null</code>
     * <p>原文：the ray trace hit result, or <code>null</code> if there is no hit
     * @see #rayTraceBlocks(Location, Vector, double, FluidCollisionMode, boolean)
     */
    @Nullable
    default RayTraceResult rayTraceBlocks(@NotNull Location start, @NotNull Vector direction, double maxDistance) {
        return this.rayTraceBlocks(start, direction, maxDistance, FluidCollisionMode.NEVER);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行射线跟踪, 使用方块的精确碰撞形状来检查方块碰撞.
     * <p>
     * 这将考虑与可穿过的方块的碰撞.
     * <p>
     * 这可能会导致区块加载! 一些实现可能会对最大距离施加人为限制.
     * <p>原文：
     * Performs a ray trace that checks for block collisions using the blocks'
     * precise collision shapes.
     * <p>
     * This takes collisions with passable blocks into account.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param start 起始位置
     * <p>原文：the start location
     * @param direction 射线方向
     * <p>原文：the ray direction
     * @param maxDistance 最大距离
     * <p>原文：the maximum distance
     * @param fluidCollisionMode 流体碰撞模式
     * <p>原文：the fluid collision mode
     * @return 最近的射线跟踪命中结果, 如果没有命中, 则为<code>null</code>
     * <p>原文：the ray trace hit result, or <code>null</code> if there is no hit
     * @see #rayTraceBlocks(Location, Vector, double, FluidCollisionMode, boolean)
     */
    @Nullable
    default RayTraceResult rayTraceBlocks(@NotNull Location start, @NotNull Vector direction, double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        return this.rayTraceBlocks(start, direction, maxDistance, fluidCollisionMode, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行射线跟踪, 使用方块的精确碰撞形状来检查方块碰撞.
     * <p>
     * 如果忽略与可穿过的方块的碰撞, 则无论流体碰撞模式如何, 也会忽略流体碰撞.
     * <p>
     * 只有当射线从传送门方块内部开始时, 其才被认为是可穿过的. 除此之外, 即使忽略了与可穿过方块的碰撞, 也将考虑与传送门方块的碰撞.
     * <p>
     * 这可能会导致区块加载! 一些实现可能会对最大距离施加人为限制.
     * <p>原文：
     * Performs a ray trace that checks for block collisions using the blocks'
     * precise collision shapes.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param start 起始位置
     * <p>原文：the start location
     * @param direction 射线方向
     * <p>原文：the ray direction
     * @param maxDistance 最大距离
     * <p>原文：the maximum distance
     * @param fluidCollisionMode 流体碰撞模式
     * <p>原文：the fluid collision mode
     * @param ignorePassableBlocks 是否忽略可穿过但可碰撞的方块(例如高草丛、告示牌、液体等)
     * <p>原文：whether to ignore passable but collidable
     *     blocks (ex. tall grass, signs, fluids, ..)
     * @return 最近的射线跟踪命中结果, 如果没有命中, 则为<code>null</code>
     * <p>原文：the ray trace hit result, or <code>null</code> if there is no hit
     */
    @Nullable
    default RayTraceResult rayTraceBlocks(@NotNull Location start, @NotNull Vector direction, double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks) {
        return this.rayTraceBlocks(start, direction, maxDistance, fluidCollisionMode, ignorePassableBlocks, null);
    }

    /**
     * Performs a ray trace that checks for block collisions using the blocks'
     * precise collision shapes.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param start the start position
     * @param direction the ray direction
     * @param maxDistance the maximum distance
     * @param fluidCollisionMode the fluid collision mode
     * @param ignorePassableBlocks whether to ignore passable but collidable
     *     blocks (ex. tall grass, signs, fluids, ..)
     * @param canCollide predicate for blocks the ray can potentially collide
     *     with, or <code>null</code> to consider all blocks
     * @return the ray trace hit result, or <code>null</code> if there is no hit
     */
    @Nullable RayTraceResult rayTraceBlocks(io.papermc.paper.math.@NotNull Position start, @NotNull Vector direction, double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks, @Nullable Predicate<? super Block> canCollide);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 执行射线跟踪, 检查方块碰撞和实体碰撞.
     * <p>
     * 方块碰撞使用方块的精确碰撞形状. <code>raySize</code> 参数仅用于实体的碰撞检查.
     * <p>
     * 如果忽略与可穿过方块的碰撞, 则无论流体碰撞模式如何, 也会忽略流体碰撞.
     * <p>
     * 只有当射线从传送门方块内部开始时, 其才被认为是可穿过的. 除此之外, 即使忽略了与可穿过方块的碰撞, 也将考虑与传送门方块的碰撞.
     * <p>
     * 这可能会导致区块加载! 一些实现可能会对最大距离施加人为限制.
     * <p>原文：
     * Performs a ray trace that checks for both block and entity collisions.
     * <p>
     * Block collisions use the blocks' precise collision shapes. The
     * <code>raySize</code> parameter is only taken into account for entity
     * collision checks.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param start 起始位置
     * <p>原文：the start location
     * @param direction 射线方向
     * <p>原文：the ray direction
     * @param maxDistance 最大距离
     * <p>原文：the maximum distance
     * @param fluidCollisionMode 流体碰撞模式
     * <p>原文：the fluid collision mode
     * @param ignorePassableBlocks 是否忽略可穿过但可碰撞的方块(例如高草丛、告示牌、液体等)
     * <p>原文：whether to ignore passable but collidable
     *     blocks (ex. tall grass, signs, fluids, ..)
     * @param raySize 在进行碰撞检查之前, 实体边界框将按此值均匀地放大(或缩小)
     * <p>原文：entity bounding boxes will be uniformly expanded (or
     *     shrunk) by this value before doing collision checks
     * @param filter 只考虑满足此过滤器的实体, 或者 <code>null</code> 考虑所有实体
     * <p>原文：only entities that fulfill this predicate are considered,
     *     or <code>null</code> to consider all entities
     * @return 最近的方块或实体的射线跟踪命中结果, 如果没有命中, 则为<code>null</code>
     * <p>原文：the closest ray trace hit result with either a block or an
     *     entity, or <code>null</code> if there is no hit
     */
    @Nullable
    default RayTraceResult rayTrace(@NotNull Location start, @NotNull Vector direction, double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks, double raySize, @Nullable Predicate<? super Entity> filter) {
        return this.rayTrace(start, direction, maxDistance, fluidCollisionMode, ignorePassableBlocks, raySize, filter, null);
    }

    /**
     * Performs a ray trace that checks for both block and entity collisions.
     * <p>
     * Block collisions use the blocks' precise collision shapes. The
     * <code>raySize</code> parameter is only taken into account for entity
     * collision checks.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param start the start position
     * @param direction the ray direction
     * @param maxDistance the maximum distance
     * @param fluidCollisionMode the fluid collision mode
     * @param ignorePassableBlocks whether to ignore passable but collidable
     *     blocks (ex. tall grass, signs, fluids, ..)
     * @param raySize entity bounding boxes will be uniformly expanded (or
     *     shrunk) by this value before doing collision checks
     * @param filter only entities that fulfill this predicate are considered,
     *     or <code>null</code> to consider all entities
     * @param canCollide predicate for blocks the ray can potentially collide
     *     with, or <code>null</code> to consider all blocks
     * @return the closest ray trace hit result with either a block or an
     *     entity, or <code>null</code> if there is no hit
     */
    @Nullable RayTraceResult rayTrace(io.papermc.paper.math.@NotNull Position start, @NotNull Vector direction, double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode, boolean ignorePassableBlocks, double raySize, @Nullable Predicate<? super Entity> filter, @Nullable Predicate<? super Block> canCollide);

    /**
     * Performs a ray trace that checks for collisions with the specified
     * targets.
     * <p>
     * This may cause loading of chunks! Some implementations may impose
     * artificial restrictions on the maximum distance.
     *
     * @param builderConsumer a consumer to configure the ray trace configuration.
     *     The received builder is not valid for use outside the consumer
     * @return the closest ray trace hit result with either a block or an
     *     entity, or <code>null</code> if there is no hit
     */
    @Nullable RayTraceResult rayTrace(@NotNull Consumer<PositionedRayTraceConfigurationBuilder> builderConsumer);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界的默认出生点{@link Location 位置}.
     * <p>原文：
     * Gets the default spawn {@link Location} of this world
     *
     * @return 这个世界的出生点位置
     * <p>原文：The spawn location of this world
     * @see Server#getRespawnWorld()
     */
    @NotNull
    public Location getSpawnLocation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界的出生点位置.
     * <br>
     * 提供的位置所处世界必须与这个世界相同.
     * <p>原文：
     * Sets the spawn location of the world.
     * <br>
     * The location provided must be equal to this world.
     *
     * @param location 要设置的出生点位置
     * <p>原文：The {@link Location} to set the spawn for this world at.
     * @return 若成功设置返回true
     * <p>原文：True if it was successfully set.
     * @see Server#setRespawnWorld(World)
     */
    public boolean setSpawnLocation(@NotNull Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界的出生点位置.
     * <p>原文：
     * Sets the spawn location of the world
     *
     * @param x X 坐标
     * <p>原文：X coordinate
     * @param y Y 坐标
     * <p>原文：Y coordinate
     * @param z Z 坐标
     * <p>原文：Z coordinate
     * @param yaw the yaw
     * @return 若成功设置返回true
     * <p>原文：True if it was successfully set.
     * @see Server#setRespawnWorld(World)
     */
    public boolean setSpawnLocation(int x, int y, int z, float yaw);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界的出生点位置.
     * <p>原文：
     * Sets the spawn location of the world
     *
     * @param x x坐标
     * <p>原文：X coordinate
     * @param y y坐标
     * <p>原文：Y coordinate
     * @param z z坐标
     * <p>原文：Z coordinate
     * @return 如果成功设置则返回true
     * <p>原文：True if it was successfully set.
     * @see Server#setRespawnWorld(World)
     */
    default boolean setSpawnLocation(int x, int y, int z) {
        return this.setSpawnLocation(x, y, z, 0.0F);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界在游戏中的相对时间.
     * <p>
     * 相对时间类似于小时数*1000（译注：意思是，如果这个世界在游戏中的时间为一个小时则相对时间显示为1000，一小时十二分为1200).
     * <p>原文：
     * Gets the relative in-game time of this world.
     * <p>
     * The relative time is analogous to hours * 1000
     *
     * @return 当前相对时间
     * <p>原文：The current relative time
     * @see #getFullTime() Returns an absolute time of this world
     */
    public long getTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置服务器的在游戏中的相对时间.
     * <p>
     * 相对时间类似于小时数*1000（译注：意思是，如果这个世界在游戏中的时间为一个小时则相对时间显示为1000，一小时十二分为1200).
     * <p>
     * 注意设置的相对时间如果小于当前相对时间则实际上是将时钟向前移动了一天。如果你要倒回时间，请使用{@link #setFullTime(long)}.
     * <p>原文：
     * Sets the relative in-game time on the server.
     * <p>
     * The relative time is analogous to hours * 1000
     * <p>
     * Note that setting the relative time below the current relative time
     * will actually move the clock forward a day. If you require to rewind
     * time, please see {@link #setFullTime(long)}
     *
     * @param time 设置的在游戏中的新的相对时间（格式为小时数*1000）
     * <p>原文：The new relative time to set the in-game time to (in
     *     hours*1000)
     * @see #setFullTime(long) Sets the absolute time of this world
     */
    public void setTime(long time);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界完整的游戏时间.
     * <p>原文：
     * Gets the full in-game time on this world
     *
     * @return 当前绝对时间
     * <p>原文：The current absolute time
     * @see #getTime() Returns a relative time of this world
     */
    public long getFullTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置服务器的游戏时间.
     * <p>
     * 注意这是设置全世界的时间，可能产生不好的影响，比如破坏红石钟或任一预定事件.
     * <p>原文：
     * Sets the in-game time on the server
     * <p>
     * Note that this sets the full time of the world, which may cause adverse
     * effects such as breaking redstone clocks and any scheduled events
     *
     * @param time 设置的世界的新绝对时间
     * <p>原文：The new absolute time to set this world to
     * @see #setTime(long) Sets the relative time of this world
     */
    public void setFullTime(long time);

    // Paper start

    /**
     * Check if it is currently daytime in this world
     *
     * @return True if it is daytime
     */
    public boolean isDayTime();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界自世界生成以来的完整游戏时间.
     * <p>原文：
     * Gets the full in-game time on this world since the world generation
     *
     * @return 自世界生成以来的当前绝对时间
     * <p>原文：The current absolute time since the world generation
     * @see #getTime() Returns a relative time of this world
     * @see #getFullTime() Returns an absolute time of this world
     */
    public long getGameTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回世界现在是否有雷暴.
     * <p>原文：
     * Returns whether the world has an ongoing storm.
     *
     * @return 是否有雷暴
     * <p>原文：Whether there is an ongoing storm
     */
    public boolean hasStorm();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置是否有风暴. 会为新的天气设置一段持续时间.
     *
     * 本方法会隐式调用 {@link #setClearWeatherDuration(int)}, 指定 0 tick 来重置世界的晴朗天气.
     * <p>原文：
     * Set whether there is a storm. A duration will be set for the new
     * current conditions.
     *
     * This will implicitly call {@link #setClearWeatherDuration(int)} with 0
     * ticks to reset the world's clear weather.
     *
     * @param hasStorm 是否下雨或下雪
     * <p>原文：Whether there is rain and snow
     */
    public void setStorm(boolean hasStorm);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前天气的剩余时间，单位为tick.
     * <p>原文：
     * Get the remaining time in ticks of the current conditions.
     *
     * @return 时间，单位为tick
     * <p>原文：Time in ticks
     */
    public int getWeatherDuration();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前天气的剩余时间，单位为tick.
     * <p>原文：
     * Set the remaining time in ticks of the current conditions.
     *
     * @param duration 时间，单位为tick
     * <p>原文：Time in ticks
     */
    public void setWeatherDuration(int duration);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个世界是否在打雷.
     * <p>原文：
     * Returns whether there is thunder.
     *
     * @return 是否打雷
     * <p>原文：Whether there is thunder
     */
    public boolean isThundering();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界是否在打雷.
     *
     * 本方法会隐式调用 {@link #setClearWeatherDuration(int)}, 指定 0 tick 来重置世界的晴朗天气.
     * <p>原文：
     * Set whether it is thundering.
     *
     * This will implicitly call {@link #setClearWeatherDuration(int)} with 0
     * ticks to reset the world's clear weather.
     *
     * @param thundering 是否打雷
     * <p>原文：Whether it is thundering
     */
    public void setThundering(boolean thundering);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界打雷的持续时间.
     * <p>原文：
     * Get the thundering duration.
     *
     * @return 持续时间，单位为tick
     * <p>原文：Duration in ticks
     */
    public int getThunderDuration();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界打雷持续时间。
     * <p>原文：
     * Set the thundering duration.
     *
     * @param duration 持续时间，单位为tick
     * <p>原文：Duration in ticks
     */
    public void setThunderDuration(int duration);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回世界是否为晴天.
     *
     * {@link #isThundering() 雷}{@link #hasStorm() 雨}退散, 便是晴天.
     * <p>原文：
     * Returns whether the world has clear weather.
     *
     * This will be true such that {@link #isThundering()} and
     * {@link #hasStorm()} are both false.
     *
     * @return 是否为晴天
     * <p>原文：true if clear weather
     */
    public boolean isClearWeather();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置晴朗天气的持续时间 (单位为 tick).
     *
     * 晴朗天气的持续时间决定着是否允许世界下雨或打雷.
     * 如果持续时间为 &gt; 0, 那么在持续时间过后, 世界也不会自然地下雨/雪.
     *
     * 本方法等效于 {@code /weather clear 持续时间}.
     * <p>原文：
     * Set the clear weather duration.
     *
     * The clear weather ticks determine whether or not the world will be
     * allowed to rain or storm. If clear weather ticks are &gt; 0, the world will
     * not naturally do either until the duration has elapsed.
     *
     * This method is equivalent to calling {@code /weather clear} with a set
     * amount of ticks.
     *
     * @param duration 持续时间, 单位为 tick
     * <p>原文：duration in ticks
     */
    public void setClearWeatherDuration(int duration);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取晴朗天气的持续时间.
     * <p>原文：
     * Get the clear weather duration.
     *
     * @return 持续时间, 单位为 tick
     * <p>原文：duration in ticks
     */
    public int getClearWeatherDuration();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定坐标生成指定威力的爆炸.
     * <p>原文：
     * Creates explosion at given coordinates with given power
     *
     * @param x x坐标
     * <p>原文：X coordinate
     * @param y y坐标
     * <p>原文：Y coordinate
     * @param z z坐标
     * <p>原文：Z coordinate
     * @param power 爆炸的威力，一个TNT当量为4F
     * <p>原文：The power of explosion, where 4F is TNT
     * @return 如果爆炸被取消则返回false，否则返回true
     * <p>原文：false if explosion was canceled, otherwise true
     */
    default boolean createExplosion(double x, double y, double z, float power) {
        return this.createExplosion(x, y, z, power, false, true);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的坐标生成指定威力的爆炸并设置方块是否会着火.
     * <p>原文：
     * Creates explosion at given coordinates with given power and optionally
     * setting blocks on fire.
     *
     * @param x x坐标
     * <p>原文：X coordinate
     * @param y y坐标
     * <p>原文：Y coordinate
     * @param z z坐标
     * <p>原文：Z coordinate
     * @param power 爆炸的威力，一个TNT当量为4F
     * <p>原文：The power of explosion, where 4F is TNT
     * @param setFire 方块是否会着火
     * <p>原文：Whether or not to set blocks on fire
     * @return  如果爆炸被取消则返回false，否则返回true
     * <p>原文：false if explosion was canceled, otherwise true
     */
    default boolean createExplosion(double x, double y, double z, float power, boolean setFire) {
        return this.createExplosion(x, y, z, power, setFire, true);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的坐标生成指定威力的爆炸并设置方块是否会着火或被破坏.
     * <p>原文：
     * Creates explosion at given coordinates with given power and optionally
     * setting blocks on fire or breaking blocks.
     *
     * @param x x坐标
     * <p>原文：X coordinate
     * @param y y坐标
     * <p>原文：Y coordinate
     * @param z z坐标
     * <p>原文：Z coordinate
     * @param power 爆炸的威力，一个TNT当量为4F
     * <p>原文：The power of explosion, where 4F is TNT
     * @param setFire 方块是否会着火
     * <p>原文：Whether or not to set blocks on fire
     * @param breakBlocks 是否破坏方块
     * <p>原文：Whether or not to have blocks be destroyed
     * @return 如果爆炸被取消则返回false，否则返回true
     * <p>原文：false if explosion was canceled, otherwise true
     */
    default boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks) {
        return this.createExplosion(x, y, z, power, setFire, breakBlocks, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的坐标生成指定威力的爆炸并设置方块是否会着火或被破坏.
     * <p>
     * 注意, 如果提供了非null的{@code source}实体且{@code breakBlocks}为{@code true},
     * 当爆炸所在世界中的{@link GameRule#MOB_GRIEFING}为{@code false}时,
     * {@code breakBlocks}的值将被忽略. 换言之, 如果不允许爆炸, mob griefing游戏规则将优先于{@code breakBlocks}.
     * <p>原文：
     * Creates explosion at given coordinates with given power and optionally
     * setting blocks on fire or breaking blocks.
     * <p>
     * Note that if a non-null {@code source} Entity is provided and {@code
     * breakBlocks} is {@code true}, the value of {@code breakBlocks} will be
     * ignored if {@link GameRules#MOB_GRIEFING} is {@code false} in the world
     * in which the explosion occurs. In other words, the mob griefing gamerule
     * will take priority over {@code breakBlocks} if explosions are not allowed.
     *
     * @param x X坐标
     * <p>原文：X coordinate
     * @param y Y坐标
     * <p>原文：Y coordinate
     * @param z Z坐标
     * <p>原文：Z coordinate
     * @param power 爆炸的威力, 其中4F相当于TNT
     * <p>原文：The power of explosion, where 4F is TNT
     * @param setFire 是否设置方块着火
     * <p>原文：Whether or not to set blocks on fire
     * @param breakBlocks 是否允许方块被破坏
     * <p>原文：Whether or not to have blocks be destroyed
     * @param source 用于追踪伤害来源的实体
     * <p>原文：the source entity, used for tracking damage
     * @return 如果爆炸被取消则返回false, 否则返回true
     * <p>原文：false if explosion was canceled, otherwise true
     */
    public boolean createExplosion(double x, double y, double z, float power, boolean setFire, boolean breakBlocks, @Nullable Entity source);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定坐标生成指定威力的爆炸.
     * <p>原文：
     * Creates explosion at given coordinates with given power
     *
     * @param loc 爆炸位置
     * <p>原文：Location to blow up
     * @param power 爆炸的威力，一个TNT当量为4F
     * <p>原文：The power of explosion, where 4F is TNT
     * @return 如果爆炸被取消则返回false，否则返回true
     * <p>原文：false if explosion was canceled, otherwise true
     */
    default boolean createExplosion(@NotNull Location loc, float power) {
        return this.createExplosion(loc, power, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的坐标生成指定威力的爆炸并设置方块是否会着火.
     * <p>原文：
     * Creates explosion at given coordinates with given power and optionally
     * setting blocks on fire.
     *
     * @param loc 爆炸位置
     * <p>原文：Location to blow up
     * @param power 爆炸的威力，一个TNT当量为4F
     * <p>原文：The power of explosion, where 4F is TNT
     * @param setFire 方块是否会着火
     * <p>原文：Whether or not to set blocks on fire
     * @return 如果爆炸被取消则返回false，否则返回true
     * <p>原文：false if explosion was canceled, otherwise true
     */
    default boolean createExplosion(@NotNull Location loc, float power, boolean setFire) {
        return this.createExplosion(loc, power, setFire, true);
    }

    // Paper start
    /**
     * Creates explosion at given location with given power and optionally
     * setting blocks on fire, with the specified entity as the source.
     *
     * @param source The source entity of the explosion
     * @param loc Location to blow up
     * @param power The power of explosion, where 4F is TNT
     * @param setFire Whether or not to set blocks on fire
     * @param breakBlocks Whether or not to have blocks be destroyed
     * @param excludeSourceFromDamage whether the explosion should exclude the passed source from taking damage like vanilla explosions do.
     * @return false if explosion was canceled, otherwise true
     */
    public boolean createExplosion(@Nullable Entity source, @NotNull Location loc, float power, boolean setFire, boolean breakBlocks, boolean excludeSourceFromDamage);

    /**
     * Creates explosion at given location with given power and optionally
     * setting blocks on fire, with the specified entity as the source.
     *
     * @param source The source entity of the explosion
     * @param loc Location to blow up
     * @param power The power of explosion, where 4F is TNT
     * @param setFire Whether or not to set blocks on fire
     * @param breakBlocks Whether or not to have blocks be destroyed
     * @return false if explosion was canceled, otherwise true
     */
    default boolean createExplosion(@Nullable Entity source, @NotNull Location loc, float power, boolean setFire, boolean breakBlocks) {
        return createExplosion(source, loc, power, setFire, breakBlocks, true);
    }

    /**
     * Creates explosion at given location with given power and optionally
     * setting blocks on fire, with the specified entity as the source.
     *
     * Will destroy other blocks
     *
     * @param source The source entity of the explosion
     * @param loc Location to blow up
     * @param power The power of explosion, where 4F is TNT
     * @param setFire Whether or not to set blocks on fire
     * @return false if explosion was canceled, otherwise true
     */
    public default boolean createExplosion(@Nullable Entity source, @NotNull Location loc, float power, boolean setFire) {
        return createExplosion(source, loc, power, setFire, true);
    }
    /**
     * Creates explosion at given location with given power, with the specified entity as the source.
     * Will set blocks on fire and destroy blocks.
     *
     * @param source The source entity of the explosion
     * @param loc Location to blow up
     * @param power The power of explosion, where 4F is TNT
     * @return false if explosion was canceled, otherwise true
     */
    public default boolean createExplosion(@Nullable Entity source, @NotNull Location loc, float power) {
        return createExplosion(source, loc, power, true, true);
    }
    /**
     * Creates explosion at given entities location with given power and optionally
     * setting blocks on fire, with the specified entity as the source.
     *
     * @param source The source entity of the explosion
     * @param power The power of explosion, where 4F is TNT
     * @param setFire Whether or not to set blocks on fire
     * @param breakBlocks Whether or not to have blocks be destroyed
     * @return false if explosion was canceled, otherwise true
     */
    public default boolean createExplosion(@NotNull Entity source, float power, boolean setFire, boolean breakBlocks) {
        return createExplosion(source, source.getLocation(), power, setFire, breakBlocks);
    }
    /**
     * Creates explosion at given entities location with given power and optionally
     * setting blocks on fire, with the specified entity as the source.
     *
     * Will destroy blocks.
     *
     * @param source The source entity of the explosion
     * @param power The power of explosion, where 4F is TNT
     * @param setFire Whether or not to set blocks on fire
     * @return false if explosion was canceled, otherwise true
     */
    public default boolean createExplosion(@NotNull Entity source, float power, boolean setFire) {
        return createExplosion(source, source.getLocation(), power, setFire, true);
    }

    /**
     * Creates explosion at given entities location with given power and optionally
     * setting blocks on fire, with the specified entity as the source.
     *
     * @param source The source entity of the explosion
     * @param power The power of explosion, where 4F is TNT
     * @return false if explosion was canceled, otherwise true
     */
    public default boolean createExplosion(@NotNull Entity source, float power) {
        return createExplosion(source, source.getLocation(), power, true, true);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置生成指定威力的爆炸并设置方块是否会着火或被破坏.
     * <p>原文：
     * Creates explosion at given coordinates with given power and optionally
     * setting blocks on fire or breaking blocks.
     *
     * @param loc 爆炸位置
     * <p>原文：Location to blow up
     * @param power 爆炸的威力, 其中4F相当于TNT
     * <p>原文：The power of explosion, where 4F is TNT
     * @param setFire 是否设置方块着火
     * <p>原文：Whether or not to set blocks on fire
     * @param breakBlocks 是否允许方块被破坏
     * <p>原文：Whether or not to have blocks be destroyed
     * @return 如果爆炸被取消则返回false, 否则返回true
     * <p>原文：false if explosion was canceled, otherwise true
     */
    default boolean createExplosion(@NotNull Location loc, float power, boolean setFire, boolean breakBlocks) {
        return this.createExplosion(loc, power, setFire, breakBlocks, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置生成指定威力的爆炸并设置方块是否会着火或被破坏.
     * <p>
     * 注意, 如果提供了非null的{@code source}实体且{@code breakBlocks}为{@code true},
     * 当爆炸所在世界中的{@link GameRule#MOB_GRIEFING}为{@code false}时,
     * {@code breakBlocks}的值将被忽略. 换言之, 如果不允许爆炸, mob griefing游戏规则将优先于{@code breakBlocks}.
     * <p>原文：
     * Creates explosion at given coordinates with given power and optionally
     * setting blocks on fire or breaking blocks.
     * <p>
     * Note that if a non-null {@code source} Entity is provided and {@code
     * breakBlocks} is {@code true}, the value of {@code breakBlocks} will be
     * ignored if {@link GameRules#MOB_GRIEFING} is {@code false} in the world
     * in which the explosion occurs. In other words, the mob griefing gamerule
     * will take priority over {@code breakBlocks} if explosions are not allowed.
     *
     * @param loc 爆炸位置
     * <p>原文：Location to blow up
     * @param power 爆炸的威力, 其中4F相当于TNT
     * <p>原文：The power of explosion, where 4F is TNT
     * @param setFire 是否设置方块着火
     * <p>原文：Whether or not to set blocks on fire
     * @param breakBlocks 是否允许方块被破坏
     * <p>原文：Whether or not to have blocks be destroyed
     * @param source 用于追踪伤害来源的实体
     * <p>原文：the source entity, used for tracking damage
     * @return 如果爆炸被取消则返回false, 否则返回true
     * <p>原文：false if explosion was canceled, otherwise true
     */
    public boolean createExplosion(@NotNull Location loc, float power, boolean setFire, boolean breakBlocks, @Nullable Entity source);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界当前的PVP设置.
     * <p>原文：
     * Gets the current PVP setting for this world.
     *
     * @return 如果允许PVP则返回true
     * <p>原文：True if PVP is enabled
     * @deprecated use {@link GameRules#PVP} instead
     */
    @Deprecated(since = "1.21.9")
    public boolean getPVP();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置世界的PVP设置.
     * <p>原文：
     * Sets the PVP setting for this world.
     *
     * @param pvp 是否允许PVP，允许为true，不允许为false.
     * <p>原文：True/False whether PVP should be Enabled.
     * @deprecated use {@link GameRules#PVP} instead
     */
    @Deprecated(since = "1.21.9")
    public void setPVP(boolean pvp);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界的区块生成器.
     * <p>原文：
     * Gets the chunk generator for this world
     *
     * @return 这个世界相关的{@link ChunkGenerator 区块生成器}
     * <p>原文：ChunkGenerator associated with this world
     */
    @Nullable
    public ChunkGenerator getGenerator();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界使用的{@link BiomeProvider 生物群系提供器}.
     * <p>原文：
     * Gets the biome provider for this world
     *
     * @return 与这个世界关联的生物群系提供器
     * <p>原文：BiomeProvider associated with this world
     */
    @Nullable
    public BiomeProvider getBiomeProvider();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 保存世界到磁盘.
     * <p>原文：
     * Saves the world to disk
     */
    default void save() {
        save(false);
    }

    /**
     * Saves the world to disk
     * @param flush Whether to wait for the chunk writer to finish
     */
    void save(boolean flush);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个这个世界使用的所有{@link BlockPopulator 方块填充器}的列表.
     * <p>原文：
     * Gets a list of all applied {@link BlockPopulator}s for this World
     *
     * @return 这个世界使用的所有方块填充器的列表
     * <p>原文：List containing any or none BlockPopulators
     */
    @NotNull
    public List<BlockPopulator> getPopulators();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在给定的{@link Location 位置}创建一个新实体, 并在实体被添加到世界之前运行提供的函数.
     * <br>
     * 注意, 当函数运行时, 实体实际上还未进入世界. 在此函数返回之前, 任何涉及该实体的操作(如传送实体)都是未定义的.
     * 但是, 传入的函数在潜在实体的生成随机化之后运行, 因此已经允许访问生物的值,
     * 无论这些值是否被随机化, 例如属性或实体装备.
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
     * @param location      实体将被生成的位置.
     * <p>原文：the location at which the entity will be spawned.
     * @param clazz         要生成的{@link LivingEntity 生物实体}的类.
     * <p>原文：the class of the {@link LivingEntity} that is to be spawned.
     * @param <T>           正在创建的实体的泛型类型.
     * <p>原文：the generic type of the entity that is being created.
     * @param spawnReason   在{@link CreatureSpawnEvent}调用期间提供的原因.
     * <p>原文：the reason provided during the {@link CreatureSpawnEvent} call.
     * @param randomizeData 实体的数据是否应该在生成前被随机化. 默认情况下, 实体在生成前会对其装备、年龄、属性等进行随机化.
     *                      这种随机化的示例包括羊的颜色、生物装备上的随机附魔, 甚至僵尸变成骑鸡僵尸.
     *                      如果设置为false, 实体在生成前不会被随机化, 意味着所有数据将保持默认状态, 不会对实体进行进一步修改.
     *                      值得注意的是, 只有继承{@link org.bukkit.entity.Mob}接口的实体才提供生成随机化逻辑.
     *                      因此此参数对任何其他类型的实体无用.
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
     * @param function      在实体生成之前运行的函数.
     * <p>原文：the function to be run before the entity is spawned.
     * @return 生成的实体实例.
     * <p>原文：the spawned entity instance.
     * @throws IllegalArgumentException 如果world或clazz参数为null.
     * <p>原文：if either the world or clazz parameter are null.
     */
    @NotNull
    public <T extends LivingEntity> T spawn(@NotNull Location location, @NotNull Class<T> clazz, @NotNull CreatureSpawnEvent.SpawnReason spawnReason, boolean randomizeData, @Nullable Consumer<? super T> function) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在给定的{@link Location 位置}生成一个{@link FallingBlock 掉落中的方块}实体, 使用指定的{@link MaterialData}.
     * MaterialData决定下落的内容. 当下落方块碰到地面时, 它将放置该方块.
     * <p>
     * Material必须是方块类型, 可通过{@link Material#isBlock()
     * data.getItemType().isBlock()}检查. Material不能是空气.
     * <p>原文：
     * Spawn a {@link FallingBlock} entity at the given {@link Location} of
     * the specified {@link MaterialData}. The MaterialData dictates what is falling.
     * When the FallingBlock hits the ground, it will place that block.
     * <p>
     * The Material must be a block type, check with {@link Material#isBlock()
     * data.getItemType().isBlock()}. The Material may not be air.
     *
     * @param location 生成{@link FallingBlock 掉落中的方块}的{@link Location 位置}
     * <p>原文：The {@link Location} to spawn the FallingBlock
     * @param data 方块数据
     * <p>原文：The block data
     * @return 生成的{@link FallingBlock 掉落中的方块}实例
     * <p>原文：The spawned {@link FallingBlock} instance
     * @throws IllegalArgumentException 如果{@link Location}或{@link
     *     MaterialData}为null, 或{@link MaterialData}的{@link Material}不是方块
     * <p>原文：if {@link Location} or {@link
     *     MaterialData} are null or {@link Material} of the {@link MaterialData} is not a block
     * @deprecated Use {@link #spawn(Location, Class, Consumer)} (or a variation thereof) in combination with {@link FallingBlock#setBlockData(BlockData)}
     */
    @NotNull
    @Deprecated(since = "1.20.2", forRemoval = true)
    public FallingBlock spawnFallingBlock(@NotNull Location location, @NotNull MaterialData data) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}根据给定的{@link BlockData}生成一个{@link FallingBlock 掉落中的方块}实体。
     * BlockData决定下落的东西。当下落方块碰到地时就会放置这个方块.
     * <p>原文：
     * Spawn a {@link FallingBlock} entity at the given {@link Location} of
     * the specified {@link BlockData}. The BlockData dictates what is falling.
     * When the FallingBlock hits the ground, it will place that block.
     *
     * @param location 生成下落方块的{@link Location 位置}
     * <p>原文：The {@link Location} to spawn the FallingBlock
     * @param data FallingBlock的{@link BlockData 方块数据}
     * <p>原文：The {@link BlockData} of the FallingBlock to spawn
     * @return 生成的{@link FallingBlock 正在下落的方块}实例
     * <p>原文：The spawned {@link FallingBlock} instance
     * @throws IllegalArgumentException 如果 {@link Location} 或 {@link BlockData} 为null
     * <p>原文：if {@link Location} or {@link
     *     BlockData} are null
     * @deprecated Use {@link #spawn(Location, Class, Consumer)} (or a variation thereof) in combination with {@link FallingBlock#setBlockData(BlockData)}
     */
    @NotNull
    @org.jetbrains.annotations.ApiStatus.Obsolete(since = "1.20.2") // Paper
    public FallingBlock spawnFallingBlock(@NotNull Location location, @NotNull BlockData data) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的{@link Location 位置}根据指定的方块{@link Material 物品}生成一个{@link FallingBlock 掉落中的方块}实体.
     * 物品决定下落的东西。当下落方块碰到地时就会放置这个方块.
     * <p>
     * 物品必须是一个经过{@link Material#isBlock()}检验的方块类型,可能不是空气.
     * <p>原文：
     * Spawn a {@link FallingBlock} entity at the given {@link Location} of the
     * specified {@link Material}. The material dictates what is falling.
     * When the FallingBlock hits the ground, it will place that block.
     * <p>
     * The Material must be a block type, check with {@link Material#isBlock()
     * material.isBlock()}. The Material may not be air.
     *
     * @param location 生成下落方块的{@link Location 位置}
     * <p>原文：The {@link Location} to spawn the FallingBlock
     * @param material 方块 {@link Material} 类型
     * <p>原文：The block {@link Material} type
     * @param data 方块数据
     * <p>原文：The block data
     * @return 生成的{@link FallingBlock 正在下落的方块}实例
     * <p>原文：The spawned {@link FallingBlock} instance
     * @throws IllegalArgumentException 如果 {@link Location} 或 {@link
     *     Material} 为null 或 {@link Material} 不是方块
     * <p>原文：if {@link Location} or {@link
     *     Material} are null or {@link Material} is not a block
     * @deprecated 不安全的参数
     * <p>原文：Magic value. Use {@link #spawn(Location, Class, Consumer)} (or a variation thereof) in combination with {@link FallingBlock#setBlockData(BlockData)}
     */
    @Deprecated(since = "1.7.5", forRemoval = true)
    @NotNull
    public FallingBlock spawnFallingBlock(@NotNull Location location, @NotNull Material material, byte data) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向以指定位置为圆心的默认半径内的所有玩家施加(给予)一个效果.
     * <p>原文：
     * Plays an effect to all players within a default radius around a given
     * location.
     *
     * @param location 在这个{@link Location 位置}周围给玩家施加效果
     * <p>原文：the {@link Location} around which players must be to
     *     hear the sound
     * @param effect {@link Effect 效果}
     * <p>原文：the {@link Effect}
     * @param data 一些效果需要的数据
     * <p>原文：a data bit needed for some effects
     */
    default void playEffect(@NotNull Location location, @NotNull Effect effect, int data) {
        this.playEffect(location, effect, data, 64);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向在以指定位置为圆心的指定半径内的所有玩家施加(给予)一个效果。
     * <p>原文：
     * Plays an effect to all players within a given radius around a location.
     *
     * @param location 在这个{@link Location 位置}周围给玩家施加效果
     * <p>原文：the {@link Location} around which players must be to
     *     hear the effect
     * @param effect {@link Effect 效果}
     * <p>原文：the {@link Effect}
     * @param data 一些效果需要的数据
     * <p>原文：a data bit needed for some effects
     * @param radius 半径
     * <p>原文：the radius around the location
     */
    public void playEffect(@NotNull Location location, @NotNull Effect effect, int data, int radius);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向在以指定位置为圆心的默认半径内的所有玩家施加(给予)一个效果。
     * <p>原文：
     * Plays an effect to all players within a default radius around a given
     * location.
     *
     * @param <T> 与效果类型相关的数据
     * <p>原文：data dependant on the type of effect
     * @param location 在这个{@link Location 位置}周围给玩家施加效果
     * <p>原文：the {@link Location} around which players must be to
     *     hear the sound
     * @param effect {@link Effect 效果}
     * <p>原文：the {@link Effect}
     * @param data 一些效果需要的数据
     * <p>原文：a data bit needed for some effects
     */
    default <T> void playEffect(@NotNull Location location, @NotNull Effect effect, @Nullable T data) {
        this.playEffect(location, effect, data, 64);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向在以指定位置为圆心的指定半径内的所有玩家施加(给予)一个效果。
     * <p>原文：
     * Plays an effect to all players within a given radius around a location.
     *
     * @param <T> 与效果类型相关的数据
     * <p>原文：data dependant on the type of effect
     * @param location 在这个{@link Location 位置}周围给玩家施加效果
     * <p>原文：the {@link Location} around which players must be to
     *     hear the effect
     * @param effect {@link Effect 效果}
     * <p>原文：the {@link Effect}
     * @param data 一些效果需要的数据
     * <p>原文：a data bit needed for some effects
     * @param radius 半径
     * <p>原文：the radius around the location
     */
    public <T> void playEffect(@NotNull Location location, @NotNull Effect effect, @Nullable T data, int radius);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取空区块的快照（相当于所有空气方块），可设置包含有效生物群系数据。用于表示一个未生成的区块，或者只用于获取生物群系数据而不加载区块。
     * <p>原文：
     * Get empty chunk snapshot (equivalent to all air blocks), optionally
     * including valid biome data. Used for representing an ungenerated chunk,
     * or for fetching only biome data without loading a chunk.
     *
     * @param x 区块x坐标
     * <p>原文：- chunk x coordinate
     * @param z 区块z坐标
     * <p>原文：- chunk z coordinate
     * @param includeBiome 如果为true，则快照会包含每个坐标的生物群系类型
     * <p>原文：- if true, snapshot includes per-coordinate biome
     *     type
     * @param includeBiomeTemp 如果为true，则快照会包含每个坐标的原始生物群系温度
     * <p>原文：- if true, snapshot includes per-coordinate
     *     raw biome temperature
     * @return 空快照
     * <p>原文：The empty snapshot.
     */
    @NotNull
    public ChunkSnapshot getEmptyChunkSnapshot(int x, int z, boolean includeBiome, boolean includeBiomeTemp);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为这个世界设置出生标识。
     * <p>原文：
     * Sets the spawn flags for this.
     * <p>Note that setting {@code false} for either only affects
     * natural spawning. It doesn't affect spawn eggs, summon command, mobs
     * spawned from structure generation, spawners, etc.</p>
     *
     * @param allowMonsters 如果为true，则允许怪物在这个世界生成
     * <p>原文：- if true, monsters are allowed to spawn in this
     *     world via natural spawning mechanisms.
     * @param allowAnimals 如果为true，则允许动物在这个世界生成
     * <p>原文：- if true, animals are allowed to spawn in this
     *     world via natural spawning mechanisms.
     */
    public void setSpawnFlags(boolean allowMonsters, boolean allowAnimals);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取动物能不能在这个世界生成。
     * <p>原文：
     * Gets whether animals can spawn in this world.
     *
     * @return 动物能不能在这个世界生成
     * <p>原文：whether animals can spawn in this world.
     */
    public boolean getAllowAnimals();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取怪物能不能在这个世界生成。
     * <p>原文：
     * Gets whether monsters can spawn in this world.
     *
     * @return 怪物能不能在这个世界生成
     * <p>原文：whether monsters can spawn in this world.
     */
    public boolean getAllowMonsters();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定方块坐标的生物群系。
     * <p>原文：
     * Gets the biome for the given block coordinates.
     *
     * @param x 方块的x坐标
     * <p>原文：X coordinate of the block
     * @param z 方块的z坐标
     * <p>原文：Z coordinate of the block
     * @return 所查方块的生物群系
     * <p>原文：Biome of the requested block
     * @deprecated 1.15更新后生物群系已三维化
     * <p>原文：biomes are now 3-dimensional
     */
    @NotNull
    @Deprecated(since = "1.15")
    default Biome getBiome(int x, int z) {
        return this.getBiome(x, 0, z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定方块坐标的生物群系.
     * <p>原文：
     * Sets the biome for the given block coordinates
     *
     * @param x 方块的x坐标
     * <p>原文：X coordinate of the block
     * @param z 方块的z坐标
     * <p>原文：Z coordinate of the block
     * @param bio 这个方块的新生物群系类型
     * <p>原文：new Biome type for this block
     * @deprecated 1.15更新后生物群系已三维化
     * <p>原文：biomes are now 3-dimensional
     */
    @Deprecated(since = "1.15")
    void setBiome(int x, int z, @NotNull Biome bio);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定方块坐标的温度。
     * <p>
     * 方块不存在时运行这个方法是安全的，它不会创建方块.
     * <p>
     * 本方法将返回原始温度值, 不以方块高度所带来的影响而判断.
     * <p>原文：
     * Gets the temperature for the given block coordinates.
     * <p>
     * It is safe to run this method when the block does not exist, it will
     * not create the block.
     *
     * @param x 方块的x坐标
     * <p>原文：X coordinate of the block
     * @param z 方块的z坐标
     * <p>原文：Z coordinate of the block
     * @return 查询方块的温度
     * <p>原文：Temperature of the requested block
     * @deprecated 1.15更新后生物群系已三维化
     * <p>原文：biomes are now 3-dimensional
     */
    @Deprecated(since = "1.15")
    default double getTemperature(int x, int z) {
        return this.getTemperature(x, 0, z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定方块坐标的温度.
     * <p>
     * 方块不存在时运行这个方法是安全的, 它不会创建方块.
     * <p>
     * 本方法将返回原始温度值, 不以方块高度所带来的影响而判断.
     * <p>原文：
     * Gets the temperature for the given block coordinates.
     * <p>
     * It is safe to run this method when the block does not exist, it will
     * not create the block.
     *
     * @param x 方块的X坐标
     * <p>原文：X coordinate of the block
     * @param y 方块的Y坐标
     * <p>原文：Y coordinate of the block
     * @param z 方块的Z坐标
     * <p>原文：Z coordinate of the block
     * @return 查询方块的温度
     * <p>原文：Temperature of the requested block
     */
    public double getTemperature(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定方块坐标的湿度。
     * <p>
     * 方块不存在时运行这个方法是安全的，它不会创建方块。
     * <p>原文：
     * Gets the humidity for the given block coordinates.
     * <p>
     * It is safe to run this method when the block does not exist, it will
     * not create the block.
     *
     * @param x 方块的x坐标
     * <p>原文：X coordinate of the block
     * @param z 方块的z坐标
     * <p>原文：Z coordinate of the block
     * @return 查询方块的湿度
     * <p>原文：Humidity of the requested block
     * @deprecated 1.15更新后生物群系已三维化
     * <p>原文：biomes are now 3-dimensional
     */
    @Deprecated(since = "1.15")
    default double getHumidity(int x, int z) {
        return this.getHumidity(x, 0, z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定方块坐标的湿度.
     * <p>
     * 方块不存在时运行这个方法是安全的, 它不会创建方块.
     * <p>原文：
     * Gets the humidity for the given block coordinates.
     * <p>
     * It is safe to run this method when the block does not exist, it will
     * not create the block.
     *
     * @param x 方块的X坐标
     * <p>原文：X coordinate of the block
     * @param y 方块的Y坐标
     * <p>原文：Y coordinate of the block
     * @param z 方块的Z坐标
     * <p>原文：Z coordinate of the block
     * @return 查询方块的湿度
     * <p>原文：Humidity of the requested block
     */
    public double getHumidity(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取紫颂果和下界传送门可以将玩家传送到此维度的最大高度.
     * <p>
     * 这不包括已在限制之上建造的传送门, 因为它们仍然可以正常连接. 不会大于{@link #getMaxHeight()}.
     * <p>原文：
     * Gets the maximum height to which chorus fruits and nether portals can
     * bring players within this dimension.
     *
     * This excludes portals that were already built above the limit as they
     * still connect normally. May not be greater than {@link #getMaxHeight()}.
     *
     * @return 紫颂果和下界传送门的最大逻辑高度
     * <p>原文：maximum logical height for chorus fruits and nether portals
     */
    public int getLogicalHeight();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界是否为自然世界.
     * <p>
     * 当为false时, 指南针会随机旋转, 使用床设置重生点或睡觉将被禁用. 当为true时, 下界传送门可以生成僵尸猪灵.
     * <p>原文：
     * Gets if this world is natural.
     * <p>
     * When false, the moon is not visible and eyeblossoms do not open/close
     *
     * @return 如果世界是自然世界则返回true
     * <p>原文：true if world is natural
     * @deprecated replaced by the gameplay/eyeblossom_open and gameplay/creaking_active environment attributes
     */
    @Deprecated(since = "1.21.11")
    public boolean isNatural();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取床在这个世界是否有效.
     * <p>
     * 无效的床在尝试睡觉时会爆炸. {@link #isNatural()}定义了床是否可用于设置重生点.
     * <p>原文：
     * Gets if beds work in this world.
     * <p>
     * A non-working bed can blow up when trying to sleep, but that may
     * not always be the case.
     *
     * @return 如果床在此世界有效则返回true
     * <p>原文：true if beds work in this world
     * @deprecated Due to 1.21.11 beds changes, a boolean no longer
     * represents if they work. There is no replacement API yet
     */
    @ApiStatus.Obsolete(since = "1.21.11")
    public boolean isBedWorks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界是否有天空光照.
     * <p>原文：
     * Gets if this world has skylight access.
     *
     * @return 如果这个世界有天空光照则返回true
     * <p>原文：true if this world has skylight access
     */
    public boolean hasSkyLight();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界是否有天花板.
     * <p>原文：
     * Gets if this world has a ceiling.
     *
     * @return 如果这个世界有基岩天花板则返回true
     * <p>原文：true if this world has a bedrock ceiling
     */
    public boolean hasCeiling();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界是否允许猪灵在不颤抖和不变身为僵尸猪灵的情况下存活.
     * <p>原文：
     * Gets if this world allow to piglins to survive without shaking and
     * transforming to zombified piglins.
     *
     * @return 如果猪灵不会变身为僵尸猪灵则返回true
     * <p>原文：true if piglins will not transform to zombified piglins
     */
    public boolean isPiglinSafe();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界是否允许玩家充能和使用重生锚.
     * <p>原文：
     * Gets if this world allows players to charge and use respawn anchors.
     *
     * @return 如果玩家可以充能和使用重生锚则返回true
     * <p>原文：true if players can charge and use respawn anchors
     */
    public boolean isRespawnAnchorWorks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在此世界中带有不祥之兆效果的玩家是否将触发袭击.
     * <p>原文：
     * Gets if players with the bad omen effect in this world will trigger a
     * raid.
     *
     * @return 如果袭击将被触发则返回true
     * <p>原文：true if raids will be triggered
     */
    public boolean hasRaids();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此世界是否会触发各种水/岩浆机制, 例如:
     * <br>
     * <ul>
     * <li>水被蒸发</li>
     * <li>海绵变干</li>
     * <li>岩浆扩散更快更远</li>
     * </ul>
     * <p>原文：
     * Gets if various water/lava mechanics will be triggered in this world, eg:
     * <br>
     * <ul>
     * <li>Water is evaporated</li>
     * <li>Sponges dry</li>
     * <li>Lava spreads faster and further</li>
     * </ul>
     *
     * @return 如果这个世界有上述机制则返回true
     * <p>原文：true if this world has the above mechanics
     * @deprecated as of 1.21.11, ultra warm is replaced by the WATER_EVAPORATES, FAST_LAVA, and DEFAULT_DRIPSTONE_PARTICLE environment attributes.
     */
    @Deprecated(since = "1.21.11")
    public boolean isUltraWarm();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界的海平面。
     * <p>
     * 这个值总是{@link #getMaxHeight()}的一半
     * <p>原文：
     * Gets the sea level for this world.
     * <p>
     * This is often half of {@link #getMaxHeight()}
     *
     * @return 海平面高度
     * <p>原文：Sea level
     */
    public int getSeaLevel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界的出生点是否会在内存中保存加载。
     * <p>原文：
     * Gets whether the world's spawn area should be kept loaded into memory
     * or not.
     *
     * @return 如果世界的出生地区会在内存中保存加载则返回true
     * <p>原文：true if the world's spawn area will be kept loaded into memory.
     * @deprecated "出生点区块"的概念已被移除, 使用
     * {@link #isChunkForceLoaded(int, int)} 以更好地控制
     * <p>原文：No longer functional since 1.21.9, the vanilla server does not have the concept of spawn chunks anymore.
     */
    @Deprecated(since = "1.20.5", forRemoval = true)
    default boolean getKeepSpawnInMemory() {
        return false;
    }

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 设置世界的出生点是否会在内存中保存加载。
     * <p>原文：
     * Sets whether the world's spawn area should be kept loaded into memory
     * or not.
     *
     * @param keepLoaded if true then the world's spawn area will be kept
     *     loaded into memory.
     * @deprecated No longer functional since 1.21.9, the vanilla server does not have the concept of spawn chunks anymore.
     */
    @Deprecated(since = "1.20.5", forRemoval = true)
    default void setKeepSpawnInMemory(boolean keepLoaded) {

    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界是否会自动保存。
     * <p>原文：
     * Gets whether or not the world will automatically save
     *
     * @return 如果世界会自动保存则返回true，否则返回false
     * <p>原文：true if the world will automatically save, otherwise false
     */
    public boolean isAutoSave();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置世界是否会自动保存。
     * <p>原文：
     * Sets whether or not the world will automatically save
     *
     * @param value 如果为true则世界会自动保存，否则为false
     * <p>原文：true if the world should automatically save, otherwise
     *     false
     * @apiNote This does not disable saving entirely, the world will still be saved on shutdown.<br>
     * The intended use of this method is to disable the periodical autosave by the game.
     */
    public void setAutoSave(boolean value);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置世界的游戏难度。
     * <p>原文：
     * Sets the Difficulty of the world.
     *
     * @param difficulty 设置的世界的新难度
     * <p>原文：the new difficulty you want to set the world to
     */
    public void setDifficulty(@NotNull Difficulty difficulty);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界的游戏难度。
     * <p>原文：
     * Gets the Difficulty of the world.
     *
     * @return 世界的难度
     * <p>原文：The difficulty of the world.
     */
    @NotNull
    public Difficulty getDifficulty();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界使用的视距.
     * <p>原文：
     * Returns the view distance used for this world.
     *
     * @return 这个世界使用的视距
     * <p>原文：the view distance used for this world
     */
    int getViewDistance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界使用的模拟距离.
     * <p>原文：
     * Returns the simulation distance used for this world.
     *
     * @return 这个世界使用的模拟距离
     * <p>原文：the simulation distance used for this world
     */
    int getSimulationDistance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界保存在磁盘的哪个文件夹。
     * <p>原文：
     * Gets the folder of this world on disk.
     *
     * @return 这个世界所在的文件夹
     * <p>原文：The folder of this world.
     */
    @NotNull
    default File getWorldFolder() {
        return getWorldPath().toFile();
    }

    /**
     * Gets the path of this world on disk.
     *
     * @return The path of this world.
     */
    @NotNull
    Path getWorldPath();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界类型。
     * <p>原文：
     * Gets the type of this world.
     *
     * @return 世界类型
     * <p>原文：Type of this world.
     * @deprecated 世界类型仅用于选择默认的世界生成设置, 且不会存储在原版世界中, 因此此方法无法始终返回正确的值.
     * <p>原文：world type is only used to select the default word generation
     * settings and is not stored in Vanilla worlds, making it impossible for
     * this method to always return the correct value.
     */
    @Nullable
    @Deprecated(since = "1.16.1")
    public WorldType getWorldType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取建筑物是否会被生成。
     * <p>原文：
     * Gets whether or not structures are being generated.
     *
     * @return 如果建筑物会被生成则返回true
     * <p>原文：True if structures are being generated.
     */
    public boolean canGenerateStructures();

    /**
     * Checks if the bonus chest is enabled.
     *
     * @return {@code true} if the bonus chest is enabled, {@code false} otherwise
     */
    boolean hasBonusChest();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界是否为极限模式.
     * <p>
     * 在极限模式世界中, 难度被锁定为困难.
     * <p>原文：
     * Gets whether the world is hardcore or not.
     *
     * In a hardcore world the difficulty is locked to hard.
     *
     * @return 极限模式状态
     * <p>原文：hardcore status
     */
    public boolean isHardcore();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界是否为极限模式.
     * <p>
     * 在极限模式世界中, 难度被锁定为困难.
     * <p>原文：
     * Sets whether the world is hardcore or not.
     *
     * In a hardcore world the difficulty is locked to hard.
     *
     * @param hardcore 世界是否为极限模式
     * <p>原文：Whether the world is hardcore
     */
    public void setHardcore(boolean hardcore);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界生成动物的时间间隔（单位为tick）。
     * <p>
     * 这个数值决定每次尝试生成动物之间的时间间隔（单位为tick）。
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成动物。
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成动物。
     * <li>数值低于0则会被重置为Minecraft的默认值。
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0，则会禁止这个世界生成动物。我们一般使用{@link #setSpawnFlags(boolean, boolean)}来代替。
     * <p>
     * Minecraft的默认值：400。
     * <p>原文：
     * Gets the world's ticks per animal spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn animals.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn animals in
     *     this world every tick.
     * <li>A value of 400 will mean the server will attempt to spawn animals
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, animal spawning will be disabled for this world. We
     * recommend using {@link #setSpawnFlags(boolean, boolean)} to control
     * this instead.
     * <p>
     * Minecraft default: 400.
     *
     * @return 世界生成动物的时间间隔（单位为tick）
     * <p>原文：The world's ticks per animal spawns value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default long getTicksPerAnimalSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.ANIMAL);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置世界生成动物的时间间隔（单位为tick）。
     * <p>
     * 这个数值决定每次尝试生成动物之间的时间间隔（单位为tick）。
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成动物。
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成动物。
     * <li>数值低于0则会被重置为Minecraft的默认值。
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0，则会禁止这个世界生成动物。我们一般使用{@link #setSpawnFlags(boolean, boolean)}来代替。
     * <p>
     * Minecraft的默认值：400。
     * <p>原文：
     * Sets the world's ticks per animal spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn animals.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn animals in
     *     this world every tick.
     * <li>A value of 400 will mean the server will attempt to spawn animals
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, animal spawning will be disabled for this world. We
     * recommend using {@link #setSpawnFlags(boolean, boolean)} to control
     * this instead.
     * <p>
     * Minecraft default: 400.
     *
     * @param ticksPerAnimalSpawns 设置的世界生成动物的时间间隔（单位为tick）
     * <p>原文：the ticks per animal spawns value you want
     *     to set the world to
     * @deprecated 建议使用 {@link #setTicksPerSpawns(SpawnCategory, int)}
     * <p>原文：Deprecated in favor of {@link #setTicksPerSpawns(SpawnCategory, int)}
     */
    @Deprecated(since = "1.18.1")
    default void setTicksPerAnimalSpawns(int ticksPerAnimalSpawns) {
        this.setTicksPerSpawns(SpawnCategory.ANIMAL, ticksPerAnimalSpawns);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界生成怪物的时间间隔（单位为tick）。
     * <p>
     * 这个数值决定每次尝试生成怪物之间的时间间隔（单位为tick）。
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成怪物。
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成怪物。
     * <li>数值低于0则会被重置为Minecraft的默认值。
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0，则会禁止这个世界生成怪物。我们一般使用{@link #setSpawnFlags(boolean, boolean)}来代替。
     * <p>
     * Minecraft的默认值：1。
     * <p>原文：
     * Gets the world's ticks per monster spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn monsters.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn monsters in
     *     this world every tick.
     * <li>A value of 400 will mean the server will attempt to spawn monsters
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, monsters spawning will be disabled for this world. We
     * recommend using {@link #setSpawnFlags(boolean, boolean)} to control
     * this instead.
     * <p>
     * Minecraft default: 1.
     *
     * @return 世界生成怪物的时间间隔（单位为tick）
     * <p>原文：The world's ticks per monster spawns value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default long getTicksPerMonsterSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.MONSTER);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置世界生成怪物的时间间隔（单位为tick）。
     * <p>
     * 这个数值决定每次尝试生成怪物之间的时间间隔（单位为tick）。
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成怪物。
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成怪物。
     * <li>数值低于0则会被重置为Minecraft的默认值。
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0，则会禁止这个世界生成怪物。我们一般使用{@link #setSpawnFlags(boolean, boolean)}来代替。
     * <p>
     * Minecraft的默认值：1。
     * <p>原文：
     * Sets the world's ticks per monster spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn monsters.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn monsters in
     *     this world on every tick.
     * <li>A value of 400 will mean the server will attempt to spawn monsters
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, monsters spawning will be disabled for this world. We
     * recommend using {@link #setSpawnFlags(boolean, boolean)} to control
     * this instead.
     * <p>
     * Minecraft default: 1.
     *
     * @param ticksPerMonsterSpawns 设置的世界生成怪物的时间间隔（单位为tick）
     * <p>原文：the ticks per monster spawns value you
     *     want to set the world to
     * @deprecated 建议使用 {@link #setTicksPerSpawns(SpawnCategory, int)}
     * <p>原文：Deprecated in favor of {@link #setTicksPerSpawns(SpawnCategory, int)}
     */
    @Deprecated(since = "1.18.1")
    default void setTicksPerMonsterSpawns(int ticksPerMonsterSpawns) {
        this.setTicksPerSpawns(SpawnCategory.MONSTER, ticksPerMonsterSpawns);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界生成水生生物的时间间隔（单位为tick）.
     * <p>
     * 这个数值决定每次尝试生成水生生物之间的时间间隔（单位为tick）.
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成水生生物.
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成水生生物.
     * <li>数值低于0则会被重置为Minecraft的默认值.
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0, 则会禁止这个世界生成水生生物.
     * <p>
     * Minecraft的默认值：1.
     * <p>原文：
     * Gets the world's ticks per water mob spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn water mobs.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water mobs in
     *     this world every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water mobs
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, water mobs spawning will be disabled for this world.
     * <p>
     * Minecraft default: 1.
     *
     * @return 世界生成水生生物的时间间隔（单位为tick）
     * <p>原文：The world's ticks per water mob spawns value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default long getTicksPerWaterSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_ANIMAL);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置世界生成水生生物的时间间隔（单位为tick）.
     * <p>
     * 这个数值决定每次尝试生成水生生物之间的时间间隔（单位为tick）.
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成水生生物.
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成水生生物.
     * <li>数值低于0则会被重置为Minecraft的默认值.
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0, 则会禁止这个世界生成水生生物.
     * <p>
     * Minecraft的默认值：1.
     * <p>原文：
     * Sets the world's ticks per water mob spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn water mobs.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water mobs in
     *     this world on every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water mobs
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, water mobs spawning will be disabled for this world.
     * <p>
     * Minecraft default: 1.
     *
     * @param ticksPerWaterSpawns 要设置的世界生成水生生物的时间间隔（单位为tick）
     * <p>原文：the ticks per water mob spawns value you
     *     want to set the world to
     * @deprecated 建议使用 {@link #setTicksPerSpawns(SpawnCategory, int)}
     * <p>原文：Deprecated in favor of {@link #setTicksPerSpawns(SpawnCategory, int)}
     */
    @Deprecated(since = "1.18.1")
    default void setTicksPerWaterSpawns(int ticksPerWaterSpawns) {
        this.setTicksPerSpawns(SpawnCategory.WATER_ANIMAL, ticksPerWaterSpawns);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取默认的水生环境生物生成时间间隔（单位为tick）.
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试生成水生环境生物.
     * <li>数值为400意味着服务器每400tick会尝试生成水生环境生物.
     * <li>数值低于0则会被重置为Minecraft的默认值.
     * </ul>
     * <p>
     * <b>注意：</b> 如果设置为0, 则水生环境生物的生成将被禁用.
     * <p>
     * Minecraft的默认值：1.
     * <p>原文：
     * Gets the default ticks per water ambient mob spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water ambient mobs
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water ambient mobs
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, ambient mobs spawning will be disabled.
     * <p>
     * Minecraft default: 1.
     *
     * @return 默认的水生环境生物生成时间间隔（单位为tick）
     * <p>原文：the default ticks per water ambient mobs spawn value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default long getTicksPerWaterAmbientSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_AMBIENT);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置世界生成水生环境生物的时间间隔（单位为tick）.
     * <p>
     * 这个数值决定每次尝试生成水生环境生物之间的时间间隔（单位为tick）.
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成水生环境生物.
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成水生环境生物.
     * <li>数值低于0则会被重置为Minecraft的默认值.
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0, 则会禁止这个世界生成水生环境生物.
     * <p>
     * Minecraft的默认值：1.
     * <p>原文：
     * Sets the world's ticks per water ambient mob spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn water ambient mobs.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water ambient mobs in
     *     this world on every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water ambient mobs
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, water ambient mobs spawning will be disabled for this world.
     * <p>
     * Minecraft default: 1.
     *
     * @param ticksPerWaterAmbientSpawns the ticks per water ambient mob spawns value you
     *     want to set the world to
     * @deprecated 建议使用 {@link #setTicksPerSpawns(SpawnCategory, int)}
     * <p>原文：Deprecated in favor of {@link #setTicksPerSpawns(SpawnCategory, int)}
     */
    @Deprecated(since = "1.18.1")
    default void setTicksPerWaterAmbientSpawns(int ticksPerWaterAmbientSpawns) {
        this.setTicksPerSpawns(SpawnCategory.WATER_AMBIENT, ticksPerWaterAmbientSpawns);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取默认的水生地下生物生成时间间隔（单位为tick）.
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试生成水生地下生物.
     * <li>数值为400意味着服务器每400tick会尝试生成水生地下生物.
     * <li>数值低于0则会被重置为Minecraft的默认值.
     * </ul>
     * <p>
     * <b>注意：</b> 如果设置为0, 则水生地下生物的生成将被禁用.
     * <p>
     * Minecraft的默认值：1.
     * <p>原文：
     * Gets the default ticks per water underground creature spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water underground creature
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water underground creature
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, water underground creature spawning will be disabled.
     * <p>
     * Minecraft default: 1.
     *
     * @return 默认的水生地下生物生成时间间隔（单位为tick）
     * <p>原文：the default ticks per water underground creature spawn value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default long getTicksPerWaterUndergroundCreatureSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_UNDERGROUND_CREATURE);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置世界生成水生地下生物的时间间隔（单位为tick）.
     * <p>
     * 这个数值决定每次尝试生成水生地下生物之间的时间间隔（单位为tick）.
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成水生地下生物.
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成水生地下生物.
     * <li>数值低于0则会被重置为Minecraft的默认值.
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0, 则会禁止这个世界生成水生地下生物.
     * <p>
     * Minecraft的默认值：1.
     * <p>原文：
     * Sets the world's ticks per water underground creature spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn water underground creature.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water underground creature in
     *     this world on every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water underground creature
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, water underground creature spawning will be disabled for this world.
     * <p>
     * Minecraft default: 1.
     *
     * @param ticksPerWaterUndergroundCreatureSpawns 要设置的世界生成水生地下生物的时间间隔（单位为tick）
     * <p>原文：the ticks per water underground creature spawns value you
     *     want to set the world to
     * @deprecated 建议使用 {@link #setTicksPerSpawns(SpawnCategory, int)}
     * <p>原文：Deprecated in favor of {@link #setTicksPerSpawns(SpawnCategory, int)}
     */
    @Deprecated(since = "1.18.1")
    default void setTicksPerWaterUndergroundCreatureSpawns(int ticksPerWaterUndergroundCreatureSpawns) {
        this.setTicksPerSpawns(SpawnCategory.WATER_UNDERGROUND_CREATURE, ticksPerWaterUndergroundCreatureSpawns);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界生成环境生物的时间间隔（单位为tick）.
     * <p>
     * 这个数值决定每次尝试生成环境生物之间的时间间隔（单位为tick）.
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成环境生物.
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成环境生物.
     * <li>数值低于0则会被重置为Minecraft的默认值.
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0, 则会禁止这个世界生成环境生物.
     * <p>
     * Minecraft的默认值：1.
     * <p>原文：
     * Gets the world's ticks per ambient mob spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn ambient mobs.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn ambient mobs in
     *     this world every tick.
     * <li>A value of 400 will mean the server will attempt to spawn ambient mobs
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, ambient mobs spawning will be disabled for this world.
     * <p>
     * Minecraft default: 1.
     *
     * @return 默认的环境生物生成时间间隔（单位为tick）
     * <p>原文：the default ticks per ambient mobs spawn value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default long getTicksPerAmbientSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.AMBIENT);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置世界生成环境生物的时间间隔（单位为tick）.
     * <p>
     * 这个数值决定每次尝试生成环境生物之间的时间间隔（单位为tick）.
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成环境生物.
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成环境生物.
     * <li>数值低于0则会被重置为Minecraft的默认值.
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0, 则会禁止这个世界生成环境生物.
     * <p>
     * Minecraft的默认值：1.
     * <p>原文：
     * Sets the world's ticks per ambient mob spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn ambient mobs.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn ambient mobs in
     *     this world on every tick.
     * <li>A value of 400 will mean the server will attempt to spawn ambient mobs
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, ambient mobs spawning will be disabled for this world.
     * <p>
     * Minecraft default: 1.
     *
     * @param ticksPerAmbientSpawns 要设置的世界生成环境生物的时间间隔（单位为tick）
     * <p>原文：the ticks per ambient mob spawns value you
     *     want to set the world to
     * @deprecated 建议使用 {@link #setTicksPerSpawns(SpawnCategory, int)}
     * <p>原文：Deprecated in favor of {@link #setTicksPerSpawns(SpawnCategory, int)}
     */
    @Deprecated(since = "1.18.1")
    default void setTicksPerAmbientSpawns(int ticksPerAmbientSpawns) {
        this.setTicksPerSpawns(SpawnCategory.AMBIENT, ticksPerAmbientSpawns);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界生成指定{@link SpawnCategory 生成类别}生物的时间间隔（单位为tick）.
     * <p>
     * 这个数值决定每次尝试生成指定{@link SpawnCategory 生成类别}生物之间的时间间隔（单位为tick）.
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成指定{@link SpawnCategory 生成类别}的生物.
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成指定{@link SpawnCategory 生成类别}的生物.
     * <li>数值低于0则会被重置为Minecraft的默认值.
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0, 则会禁止这个世界生成指定{@link SpawnCategory 生成类别}的生物.
     * <p>
     * Minecraft的默认值：1.
     * <p>原文：
     * Gets the world's ticks per {@link SpawnCategory} mob spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn {@link SpawnCategory} mobs.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn {@link SpawnCategory} mobs in
     *     this world every tick.
     * <li>A value of 400 will mean the server will attempt to spawn {@link SpawnCategory} mobs
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, {@link SpawnCategory} mobs spawning will be disabled for this world.
     * <p>
     * Minecraft default: 1.
     *
     * @param spawnCategory 生成类别
     * <p>原文：the category spawn
     * @return 世界生成指定{@link SpawnCategory 生成类别}生物的时间间隔（单位为tick）
     * <p>原文：The world's ticks per {@link SpawnCategory} mob spawns value
     */
    public long getTicksPerSpawns(@NotNull SpawnCategory spawnCategory);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置世界生成指定{@link SpawnCategory 生成类别}生物的时间间隔（单位为tick）.
     * <p>
     * 这个数值决定每次尝试生成指定{@link SpawnCategory 生成类别}生物之间的时间间隔（单位为tick）.
     * <p>
     * <b>用法示例：</b>
     * <ul>
     * <li>数值为1意味着服务器每1tick都会尝试在这个世界生成指定{@link SpawnCategory 生成类别}的生物.
     * <li>数值为400意味着服务器每400tick会尝试在这个世界生成指定{@link SpawnCategory 生成类别}的生物.
     * <li>数值低于0则会被重置为Minecraft的默认值.
     * </ul>
     * <p>
     * <b>注意：</b>
     * 如果设置为0, 则会禁止这个世界生成指定{@link SpawnCategory 生成类别}的生物.
     * <p>
     * Minecraft的默认值：1.
     * <p>原文：
     * Sets the world's ticks per {@link SpawnCategory} mob spawns value
     * <p>
     * This value determines how many ticks there are between attempts to
     * spawn {@link SpawnCategory} mobs.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn {@link SpawnCategory} mobs in
     *     this world on every tick.
     * <li>A value of 400 will mean the server will attempt to spawn {@link SpawnCategory} mobs
     *     in this world every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b>
     * If set to 0, {@link SpawnCategory} mobs spawning will be disabled for this world.
     * <p>
     * Minecraft default: 1.
     *
     * @param spawnCategory 生成类别
     * <p>原文：the category spawn
     * @param ticksPerCategorySpawn 要设置的世界生成指定{@link SpawnCategory 生成类别}生物的时间间隔（单位为tick）
     * <p>原文：the ticks per {@link SpawnCategory} mob spawns value you
     *     want to set the world to
     */
    public void setTicksPerSpawns(@NotNull SpawnCategory spawnCategory, int ticksPerCategorySpawn);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界一个区块内的怪物生成数限制。
     * <p>原文：
     * Gets limit for number of monsters that can spawn in a chunk in this
     * world
     *
     * @return 怪物生成限制
     * <p>原文：The monster spawn limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getMonsterSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.MONSTER);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界一个区块内的怪物生成数限制。
     * <p>
     * <b>注意：</b>如果设置为负数则这个世界会使用服务器普遍的生成限制来代替。
     * <p>原文：
     * Sets the limit for number of monsters that can spawn in a chunk in this
     * world
     * <p>
     * <b>Note:</b> If set to a negative number the world will use the
     * server-wide spawn limit instead.
     *
     * @param limit 新的怪物限制
     * <p>原文：the new mob limit
     * @deprecated 建议使用 {@link #setSpawnLimit(SpawnCategory, int)}
     * <p>原文：Deprecated in favor of {@link #setSpawnLimit(SpawnCategory, int)}
     */
    @Deprecated(since = "1.18.1")
    default void setMonsterSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.MONSTER, limit);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界一个区块内的动物生成数限制。
     * <p>原文：
     * Gets the limit for number of animals that can spawn in a chunk in this
     * world
     *
     * @return 动物生成限制
     * <p>原文：The animal spawn limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getAnimalSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.ANIMAL);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界一个区块内的动物生成数限制。
     * <p>
     * <b>注意：</b>如果设置为负数则这个世界会使用服务器普遍的生成限制来代替。
     * <p>原文：
     * Sets the limit for number of animals that can spawn in a chunk in this
     * world
     * <p>
     * <b>Note:</b> If set to a negative number the world will use the
     * server-wide spawn limit instead.
     *
     * @param limit 新的动物限制
     * <p>原文：the new mob limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default void setAnimalSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.ANIMAL, limit);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界一个区块内的水生动物生成数限制。
     * <p>原文：
     * Gets the limit for number of water animals that can spawn in a chunk in
     * this world
     *
     * @return 水生动物生成限制
     * <p>原文：The water animal spawn limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getWaterAnimalSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_ANIMAL);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界一个区块内的水生动物生成数限制。
     * <p>
     * <b>注意：</b>如果设置为负数则这个世界会使用服务器普遍的生成限制来代替。
     * <p>原文：
     * Sets the limit for number of water animals that can spawn in a chunk in
     * this world
     * <p>
     * <b>Note:</b> If set to a negative number the world will use the
     * server-wide spawn limit instead.
     *
     * @param limit 新的水生动物限制
     * <p>原文：the new mob limit
     * @deprecated 建议使用 {@link #setSpawnLimit(SpawnCategory, int)}
     * <p>原文：Deprecated in favor of {@link #setSpawnLimit(SpawnCategory, int)}
     */
    @Deprecated(since = "1.18.1")
    default void setWaterAnimalSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.WATER_ANIMAL, limit);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界一个区块内的水生地下生物生成数限制.
     * <p>原文：
     * Gets the limit for number of water underground creature that can spawn in a chunk in
     * this world
     *
     * @return 水生地下生物生成限制
     * <p>原文：The water underground creature spawn limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getWaterUndergroundCreatureSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_UNDERGROUND_CREATURE);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界一个区块内的水生地下生物生成数限制.
     * <p>
     * <b>注意：</b>如果设置为负数则这个世界会使用服务器普遍的生成限制来代替.
     * <p>原文：
     * Sets the limit for number of water underground creature that can spawn in a chunk in
     * this world
     * <p>
     * <b>Note:</b> If set to a negative number the world will use the
     * server-wide spawn limit instead.
     *
     * @param limit 新的生物限制
     * <p>原文：the new mob limit
     * @deprecated 建议使用 {@link #setSpawnLimit(SpawnCategory, int)}
     * <p>原文：Deprecated in favor of {@link #setSpawnLimit(SpawnCategory, int)}
     */
    @Deprecated(since = "1.18.1")
    default void setWaterUndergroundCreatureSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.WATER_UNDERGROUND_CREATURE, limit);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界一个区块内的水生环境生物生成数限制.
     * <p>原文：
     * Gets user-specified limit for number of water ambient mobs that can spawn
     * in a chunk.
     *
     * @return 水生环境生物生成限制
     * <p>原文：the water ambient spawn limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getWaterAmbientSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_AMBIENT);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界一个区块内的水生环境生物生成数限制.
     * <p>
     * <b>注意：</b>如果设置为负数则这个世界会使用服务器普遍的生成限制来代替.
     * <p>原文：
     * Sets the limit for number of water ambient mobs that can spawn in a chunk
     * in this world
     * <p>
     * <b>Note:</b> If set to a negative number the world will use the
     * server-wide spawn limit instead.
     *
     * @param limit 新的生物限制
     * <p>原文：the new mob limit
     * @deprecated 建议使用 {@link #setSpawnLimit(SpawnCategory, int)}
     * <p>原文：Deprecated in favor of {@link #setSpawnLimit(SpawnCategory, int)}
     */
    @Deprecated(since = "1.18.1")
    default void setWaterAmbientSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.WATER_AMBIENT, limit);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界一个区块内周围的怪物的生成数限制。
     * <p>原文：
     * Gets the limit for number of ambient mobs that can spawn in a chunk in
     * this world
     *
     * @return 周围的怪物的生成限制
     * <p>原文：The ambient spawn limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getAmbientSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.AMBIENT);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界一个区块内周围的怪物的生成数限制。
     * <p>
     * <b>注意：</b>如果设置为负数则这个世界会使用服务器普遍的生成限制来代替。
     * <p>原文：
     * Sets the limit for number of ambient mobs that can spawn in a chunk in
     * this world
     * <p>
     * <b>Note:</b> If set to a negative number the world will use the
     * server-wide spawn limit instead.
     *
     * @param limit 新的周围的怪物的限制
     * <p>原文：the new mob limit
     * @deprecated 建议使用 {@link #setSpawnLimit(SpawnCategory, int)}
     * <p>原文：Deprecated in favor of {@link #setSpawnLimit(SpawnCategory, int)}
     */
    @Deprecated(since = "1.18.1")
    default void setAmbientSpawnLimit(int limit) {
        this.setSpawnLimit(SpawnCategory.AMBIENT, limit);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界一个区块内指定{@link SpawnCategory 生成类别}实体的生成数限制.
     * <p>原文：
     * Gets the limit for number of {@link SpawnCategory} entities that can spawn in a chunk in
     * this world
     *
     * @param spawnCategory 实体生成类别
     * <p>原文：the entity category
     * @return 指定类别的生成限制
     * <p>原文：The ambient spawn limit
     */
    int getSpawnLimit(@NotNull SpawnCategory spawnCategory);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个世界一个区块内指定{@link SpawnCategory 生成类别}实体的生成数限制.
     * <p>
     * <b>注意：</b>如果设置为负数则这个世界会使用服务器普遍的生成限制来代替.
     * <p>原文：
     * Sets the limit for number of {@link SpawnCategory} entities that can spawn in a chunk in
     * this world
     * <p>
     * <b>Note:</b> If set to a negative number the world will use the
     * server-wide spawn limit instead.
     *
     * @param spawnCategory 实体生成类别
     * <p>原文：the entity category
     * @param limit 新的生物限制
     * <p>原文：the new mob limit
     */
    void setSpawnLimit(@NotNull SpawnCategory spawnCategory, int limit);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定的位置播放一个音符. <br>
     * 这个方法<i>可以</i>与蛋糕一起使用.
     * <p>
     * 当使用{@link Instrument#CUSTOM_HEAD}调用时, 此方法会静默失败.
     * <p>原文：
     * Play a note at the provided Location in the World. <br>
     * This <i>will</i> work with cake.
     * <p>
     * This method will fail silently when called with {@link Instrument#CUSTOM_HEAD}.
     *
     * @param loc 播放音符的位置
     * <p>原文：The location to play the note
     * @param instrument 乐器
     * <p>原文：The instrument
     * @param note 音符
     * <p>原文：The note
     */
    default void playNote(@NotNull Location loc, @NotNull Instrument instrument, @NotNull Note note) {
        this.playSound(loc, instrument.getSound(), SoundCategory.RECORDS, 3f, note.getPitch());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定的位置播放一个声音。
     * <p>
     * 如果位置或声音为空则这个函数会失效。
     * <p>原文：
     * Play a Sound at the provided Location in the World.
     * <p>
     * This function will fail silently if Location or Sound are null.
     *
     * @param location 演奏声音的位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     */
    default void playSound(@NotNull Location location, @NotNull Sound sound, float volume, float pitch) {
        this.playSound(location, sound, SoundCategory.MASTER, volume, pitch);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定的位置播放一个声音.
     * <p>
     * 如果位置或声音为空则这个函数会失效. 如果客户端没有对应传入值的声音, 玩家将不会听到任何声音.
     * <p>原文：
     * Play a Sound at the provided Location in the World.
     * <p>
     * This function will fail silently if Location or Sound are null. No
     * sound will be heard by the players if their clients do not have the
     * respective sound for the value passed.
     *
     * @param location 播放声音的位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的内部声音名称
     * <p>原文：The internal sound name to play
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     */
    default void playSound(@NotNull Location location, @NotNull String sound, float volume, float pitch) {
        this.playSound(location, sound, SoundCategory.MASTER, volume, pitch);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定的位置播放一个声音.
     * <p>
     * 如果位置或声音为空则这个函数会失效.
     * <p>原文：
     * Play a Sound at the provided Location in the World.
     * <p>
     * This function will fail silently if Location or Sound are null.
     *
     * @param location 播放声音的位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音类别
     * <p>原文：the category of the sound
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     */
    void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定的位置播放一个声音.
     * <p>
     * 如果位置或声音为空则这个函数会失效. 如果客户端没有对应传入值的声音, 玩家将不会听到任何声音.
     * <p>原文：
     * Play a Sound at the provided Location in the World.
     * <p>
     * This function will fail silently if Location or Sound are null. No sound
     * will be heard by the players if their clients do not have the respective
     * sound for the value passed.
     *
     * @param location 播放声音的位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的内部声音名称
     * <p>原文：The internal sound name to play
     * @param category 声音类别
     * <p>原文：The category of the sound
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     */
    void playSound(@NotNull Location location, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定的位置播放一个声音. 对于有多个变体的声音, 传递相同的种子将始终播放相同的变体.
     * <p>
     * 如果位置或声音为空则这个函数会失效.
     * <p>原文：
     * Play a Sound at the provided Location in the World. For sounds with multiple
     * variations passing the same seed will always play the same variation.
     * <p>
     * This function will fail silently if Location or Sound are null.
     *
     * @param location 播放声音的位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音类别
     * <p>原文：the category of the sound
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     * @param seed 声音种子
     * <p>原文：The seed for the sound
     */
    void playSound(@NotNull Location location, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch, long seed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定的位置播放一个声音. 对于有多个变体的声音, 传递相同的种子将始终播放相同的变体.
     * <p>
     * 如果位置或声音为空则这个函数会失效. 如果客户端没有对应传入值的声音, 玩家将不会听到任何声音.
     * <p>原文：
     * Play a Sound at the provided Location in the World. For sounds with multiple
     * variations passing the same seed will always play the same variation.
     * <p>
     * This function will fail silently if Location or Sound are null. No sound will
     * be heard by the players if their clients do not have the respective sound for
     * the value passed.
     *
     * @param location 播放声音的位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的内部声音名称
     * <p>原文：The internal sound name to play
     * @param category 声音类别
     * <p>原文：the category of the sound
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     * @param seed 声音种子
     * <p>原文：The seed for the sound
     */
    void playSound(@NotNull Location location, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch, long seed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定实体的位置播放一个声音.
     * <p>
     * 如果实体或声音为空则这个函数会失效.
     * <p>原文：
     * Play a Sound at the location of the provided entity in the World.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     */
    default void playSound(@NotNull Entity entity, @NotNull Sound sound, float volume, float pitch) {
        this.playSound(entity, sound, SoundCategory.MASTER, volume, pitch);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定实体的位置播放一个声音.
     * <p>
     * 如果实体或声音为空则这个函数会失效.
     * <p>原文：
     * Play a Sound at the location of the provided entity in the World.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     */
    default void playSound(@NotNull Entity entity, @NotNull String sound, float volume, float pitch) {
        this.playSound(entity, sound, SoundCategory.MASTER, volume, pitch);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定实体的位置播放一个声音.
     * <p>
     * 如果实体或声音为空则这个函数会失效.
     * <p>原文：
     * Play a Sound at the location of the provided entity in the World.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音类别
     * <p>原文：The category of the sound
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     */
    void playSound(@NotNull Entity entity, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定实体的位置播放一个声音.
     * <p>
     * 如果实体或声音为空则这个函数会失效.
     * <p>原文：
     * Play a Sound at the location of the provided entity in the World.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音类别
     * <p>原文：The category of the sound
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     */
    void playSound(@NotNull Entity entity, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定实体的位置播放一个声音. 对于有多个变体的声音, 传递相同的种子将始终播放相同的变体.
     * <p>
     * 如果实体或声音为空则这个函数会失效.
     * <p>原文：
     * Play a Sound at the location of the provided entity in the World. For sounds
     * with multiple variations passing the same seed will always play the same
     * variation.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音类别
     * <p>原文：The category of the sound
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     * @param seed 声音种子
     * <p>原文：The seed for the sound
     */
    void playSound(@NotNull Entity entity, @NotNull Sound sound, @NotNull SoundCategory category, float volume, float pitch, long seed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在世界中指定实体的位置播放一个声音. 对于有多个变体的声音, 传递相同的种子将始终播放相同的变体.
     * <p>
     * 如果实体或声音为空则这个函数会失效.
     * <p>原文：
     * Play a Sound at the location of the provided entity in the World. For sounds
     * with multiple variations passing the same seed will always play the same
     * variation.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音类别
     * <p>原文：The category of the sound
     * @param volume 声音音量
     * <p>原文：The volume of the sound
     * @param pitch 声音音调
     * <p>原文：The pitch of the sound
     * @param seed 声音种子
     * <p>原文：The seed for the sound
     */
    void playSound(@NotNull Entity entity, @NotNull String sound, @NotNull SoundCategory category, float volume, float pitch, long seed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取包含所有{@link GameRule 游戏规则}的数组.
     * <p>原文：
     * Get an array containing the names of all the {@link GameRule}s.
     *
     * @return {@link GameRule 游戏规则}名列表.
     * <p>原文：An array of {@link GameRule} names.
     */
    public @NotNull String @NotNull [] getGameRules();

    /**
     * Gets the current state of the specified rule
     * <p>
     * Will return null if rule passed is null
     *
     * @param rule Rule to look up value of
     * @return String value of rule
     * @deprecated use {@link #getGameRuleValue(GameRule)} instead
     */
    @Deprecated(since = "1.21.11", forRemoval = true)
    @Contract("null -> null; !null -> !null")
    @Nullable
    public String getGameRuleValue(@Nullable String rule);

    /**
     * Set the specified gamerule to specified value.
     * <p>
     * The rule may attempt to validate the value passed, will return true if
     * value was set.
     * <p>
     * If rule is null, the function will return false.
     *
     * @param rule Rule to set
     * @param value Value to set rule to
     * @return True if rule was set
     * @deprecated use {@link #setGameRule(GameRule, Object)} instead.
     */
    @Deprecated(since = "1.13", forRemoval = true)
    public boolean setGameRuleValue(@NotNull String rule, @NotNull String value);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查字符串是否是一个有效的游戏规则.
     * <p>原文：
     * Checks if string is a valid game rule
     *
     * @param rule 要检测的规则
     * <p>原文：Rule to check
     * @return 如果规则存在则返回true
     * <p>原文：True if rule exists
     */
    public boolean isGameRule(@NotNull String rule);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定的{@link GameRule 游戏规则}的数据值.
     * <p>原文：
     * Get the current value for a given {@link GameRule}.
     *
     * @param rule 游戏规则
     * <p>原文：the GameRule to check
     * @param <T> 游戏规则数据类型
     * <p>原文：the GameRule's type
     * @return 游戏规则值
     * <p>原文：the current value
     */
    @Nullable
    public <T> T getGameRuleValue(@NotNull GameRule<T> rule);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定{@link GameRule 游戏规则}的默认值. 不保证该值与当前值匹配.
     * <p>原文：
     * Get the default value for a given {@link GameRule}. This value is not
     * guaranteed to match the current value.
     *
     * @param rule 游戏规则
     * <p>原文：the rule to return a default value for
     * @param <T> 游戏规则数据类型
     * <p>原文：the type of GameRule
     * @return 游戏规则默认值
     * <p>原文：the default value
     */
    @Nullable
    public <T> T getGameRuleDefault(@NotNull GameRule<T> rule);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置给定{@link GameRule 游戏规则}的数据值.
     * <p>原文：
     * Set the given {@link GameRule}'s new value.
     *
     * @param rule 要更新的游戏规则
     * <p>原文：the GameRule to update
     * @param newValue 值
     * <p>原文：the new value
     * @param <T> 对应游戏规则的数据类型
     * <p>原文：the value type of the GameRule
     * @return 若设置成功返回true
     * <p>原文：true if the value was successfully set
     */
    public <T> boolean setGameRule(@NotNull GameRule<T> rule, @NotNull T newValue);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界的世界边界对象。
     * <p>原文：
     * Gets the world border for this world.
     *
     * @return 这个世界的世界边界对象
     * <p>原文：The world border for this world.
     */
    @NotNull
    public WorldBorder getWorldBorder();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param location 生成粒子的位置
     * <p>原文：the location to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     */
    default void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param x 生成粒子的X轴位置
     * <p>原文：the position on the x axis to spawn at
     * @param y 生成粒子的Y轴位置
     * <p>原文：the position on the y axis to spawn at
     * @param z 生成粒子的Z轴位置
     * <p>原文：the position on the z axis to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     */
    default void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count) {
        this.spawnParticle(particle, x, y, z, count, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param <T> 粒子数据类型（参见{@link Particle#getDataType()}）
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param location 生成粒子的位置
     * <p>原文：the location to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param data 用于粒子的数据或null, 其类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, @Nullable T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param <T> 粒子数据类型（参见{@link Particle#getDataType()}）
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param x 生成粒子的X轴位置
     * <p>原文：the position on the x axis to spawn at
     * @param y 生成粒子的Y轴位置
     * <p>原文：the position on the y axis to spawn at
     * @param z 生成粒子的Z轴位置
     * <p>原文：the position on the z axis to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param data 用于粒子的数据或null, 其类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, @Nullable T data) {
        this.spawnParticle(particle, x, y, z, count, 0, 0, 0, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）. 每个粒子的位置将根据偏移参数在每个轴上进行正向和负向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param location 生成粒子的位置
     * <p>原文：the location to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     */
    default void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）. 每个粒子的位置将根据偏移参数在每个轴上进行正向和负向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param x 生成粒子的X轴位置
     * <p>原文：the position on the x axis to spawn at
     * @param y 生成粒子的Y轴位置
     * <p>原文：the position on the y axis to spawn at
     * @param z 生成粒子的Z轴位置
     * <p>原文：the position on the z axis to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     */
    default void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）. 每个粒子的位置将根据偏移参数在每个轴上进行正向和负向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子数据类型（参见{@link Particle#getDataType()}）
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param location 生成粒子的位置
     * <p>原文：the location to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param data 用于粒子的数据或null, 其类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, @Nullable T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）. 每个粒子的位置将根据偏移参数在每个轴上进行正向和负向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子数据类型（参见{@link Particle#getDataType()}）
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param x 生成粒子的X轴位置
     * <p>原文：the position on the x axis to spawn at
     * @param y 生成粒子的Y轴位置
     * <p>原文：the position on the y axis to spawn at
     * @param z 生成粒子的Z轴位置
     * <p>原文：the position on the z axis to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param data 用于粒子的数据或null, 其类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, @Nullable T data) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 1, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）. 每个粒子的位置将根据偏移参数在每个轴上进行正向和负向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param location 生成粒子的位置
     * <p>原文：the location to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param extra 此粒子的额外数据, 取决于使用的粒子（通常是速度）
     * <p>原文：the extra data for this particle, depends on the
     *              particle used (normally speed)
     */
    default void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）. 每个粒子的位置将根据偏移参数在每个轴上进行正向和负向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param x 生成粒子的X轴位置
     * <p>原文：the position on the x axis to spawn at
     * @param y 生成粒子的Y轴位置
     * <p>原文：the position on the y axis to spawn at
     * @param z 生成粒子的Z轴位置
     * <p>原文：the position on the z axis to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param extra 此粒子的额外数据, 取决于使用的粒子（通常是速度）
     * <p>原文：the extra data for this particle, depends on the
     *              particle used (normally speed)
     */
    default void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）. 每个粒子的位置将根据偏移参数在每个轴上进行正向和负向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子数据类型（参见{@link Particle#getDataType()}）
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param location 生成粒子的位置
     * <p>原文：the location to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param extra 此粒子的额外数据, 取决于使用的粒子（通常是速度）
     * <p>原文：the extra data for this particle, depends on the
     *              particle used (normally speed)
     * @param data 用于粒子的数据或null, 其类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）. 每个粒子的位置将根据偏移参数在每个轴上进行正向和负向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子数据类型（参见{@link Particle#getDataType()}）
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param x 生成粒子的X轴位置
     * <p>原文：the position on the x axis to spawn at
     * @param y 生成粒子的Y轴位置
     * <p>原文：the position on the y axis to spawn at
     * @param z 生成粒子的Z轴位置
     * <p>原文：the position on the z axis to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param extra 此粒子的额外数据, 取决于使用的粒子（通常是速度）
     * <p>原文：the extra data for this particle, depends on the
     *              particle used (normally speed)
     * @param data 用于粒子的数据或null, 其类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data) {
        this.spawnParticle(particle, null, null, x, y, z, count, offsetX, offsetY, offsetZ, extra, data, true); // todo this is never called actually
    }

    // Paper start - Expand Particle API
    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param receivers List of players to receive the particles, or null for all in world
     * @param source Source of the particles to be used in visibility checks, or null if no player source
     * @param x the position on the x axis to spawn at
     * @param y the position on the y axis to spawn at
     * @param z the position on the z axis to spawn at
     * @param count the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param extra the extra data for this particle, depends on the
     *              particle used (normally speed)
     * @param data the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     * @param <T> Type
     */
    public default <T> void spawnParticle(@NotNull Particle particle, @Nullable List<Player> receivers, @NotNull Player source, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data) { spawnParticle(particle, receivers, source, x, y, z, count, offsetX, offsetY, offsetZ, extra, data, true); }
    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle the particle to spawn
     * @param receivers List of players to receive the particles, or null for all in world
     * @param source Source of the particles to be used in visibility checks, or null if no player source
     * @param x the position on the x axis to spawn at
     * @param y the position on the y axis to spawn at
     * @param z the position on the z axis to spawn at
     * @param count the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param extra the extra data for this particle, depends on the
     *              particle used (normally speed)
     * @param data the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     * @param <T> Type
     * @param force allows the particle to be seen further away from the player
     *              and shows to players using any vanilla client particle settings
     */
    public <T> void spawnParticle(@NotNull Particle particle, @Nullable List<Player> receivers, @Nullable Player source, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data, boolean force);
    // Paper end


    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）. 每个粒子的位置将根据偏移参数在每个轴上进行正向和负向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子数据类型（参见{@link Particle#getDataType()}）
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param location 生成粒子的位置
     * <p>原文：the location to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param extra 此粒子的额外数据, 取决于使用的粒子（通常是速度）
     * <p>原文：the extra data for this particle, depends on the
     *              particle used (normally speed)
     * @param data 用于粒子的数据或null, 其类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     * @param force 是否将粒子发送给扩展范围内的玩家并强制其客户端渲染, 无论设置如何
     * <p>原文：whether to send the particle to players within an extended
     *              range and encourage their client to render it regardless of
     *              settings
     */
    default <T> void spawnParticle(@NotNull Particle particle, @NotNull Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data, boolean force) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data, force);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在目标位置生成粒子（由count指定数量）. 每个粒子的位置将根据偏移参数在每个轴上进行正向和负向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子数据类型（参见{@link Particle#getDataType()}）
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 要生成的粒子
     * <p>原文：the particle to spawn
     * @param x 生成粒子的X轴位置
     * <p>原文：the position on the x axis to spawn at
     * @param y 生成粒子的Y轴位置
     * <p>原文：the position on the y axis to spawn at
     * @param z 生成粒子的Z轴位置
     * <p>原文：the position on the z axis to spawn at
     * @param count 粒子数量
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param extra 此粒子的额外数据, 取决于使用的粒子（通常是速度）
     * <p>原文：the extra data for this particle, depends on the
     *              particle used (normally speed)
     * @param data 用于粒子的数据或null, 其类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     * @param force 是否将粒子发送给扩展范围内的玩家并强制其客户端渲染, 无论设置如何
     * <p>原文：whether to send the particle to players within an extended
     *              range and encourage their client to render it regardless of
     *              settings
     */
    default <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data, boolean force) {
        this.spawnParticle(particle, null, null, x, y, z, count, offsetX, offsetY, offsetZ, extra, data, force);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找最近的指定{@link StructureType 结构类型}的结构.
     * 查找未探索的结构可能会阻塞, 如果世界正在查看尚未生成的区块. 这可能导致世界在定位未探索的结构时暂时冻结.
     * <p>
     * {@code radius}不是严格的方形半径. 每种结构可能改变每次迭代检查的区块数量. 不要假设只会检查半径*半径的区块区域. 例如,
     * {@link StructureType#WOODLAND_MANSION}可能会检查距离20000个方块（或更多）远的地方, 无论使用的半径是多少.
     * <p>
     * 此方法<i>不会</i>加载或生成区块. 如果你只查找未探索的结构, 这也可能导致服务器挂起的情况. 这是因为它会不断向外寻找以找到该结构.
     * <p>原文：
     * Find the closest nearby structure of a given {@link StructureType}.
     * Finding unexplored structures can, and will, block if the world is
     * looking in chunks that have not generated yet. This can lead to the world
     * temporarily freezing while locating an unexplored structure.
     * <p>
     * The {@code radius} is not a rigid square radius. Each structure may alter
     * how many chunks to check for each iteration. Do not assume that only a
     * radius x radius chunk area will be checked. For example,
     * {@link StructureType#WOODLAND_MANSION} can potentially check up to 20,000
     * blocks away (or more) regardless of the radius used.
     * <p>
     * This will <i>not</i> load or generate chunks. This can also lead to
     * instances where the server can hang if you are only looking for
     * unexplored structures. This is because it will keep looking further and
     * further out in order to find the structure.
     *
     * @param origin 开始查找结构的位置
     * <p>原文：where to start looking for a structure
     * @param structureType 要查找的结构类型
     * <p>原文：the type of structure to find
     * @param radius 搜索半径（以区块为单位）
     * <p>原文：the radius, in chunks, around which to search
     * @param findUnexplored 是否只查找未探索的结构
     * <p>原文：true to only find unexplored structures
     * @return 最近的{@link Location 位置}, 如果不存在指定类型的结构则返回null
     * <p>原文：the closest {@link Location}, or null if no structure of the
     * specified type exists.
     * @see #locateNearestStructure(Location, Structure, int, boolean)
     * @see #locateNearestStructure(Location, StructureType, int, boolean)
     * @deprecated 建议使用
     * {@link #locateNearestStructure(Location, Structure, int, boolean)} 或
     * {@link #locateNearestStructure(Location, StructureType, int, boolean)}
     * <p>原文：Use
     * {@link #locateNearestStructure(Location, Structure, int, boolean)} or
     * {@link #locateNearestStructure(Location, StructureType, int, boolean)}
     * instead.
     */
    @Nullable
    @Deprecated(since = "1.19")
    public Location locateNearestStructure(@NotNull Location origin, @NotNull org.bukkit.StructureType structureType, int radius, boolean findUnexplored);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找最近的指定{@link StructureType 结构类型}的结构.
     * 查找未探索的结构可能会阻塞, 如果世界正在查看尚未生成的区块. 这可能导致世界在定位未探索的结构时暂时冻结.
     * <p>
     * {@code radius}不是严格的方形半径. 每种结构可能改变每次迭代检查的区块数量. 不要假设只会检查半径*半径的区块区域. 例如,
     * {@link StructureType#WOODLAND_MANSION}可能会检查距离20000个方块（或更多）远的地方, 无论使用的半径是多少.
     * <p>
     * 此方法<i>不会</i>加载或生成区块. 如果你只查找未探索的结构, 这也可能导致服务器挂起的情况. 这是因为它会不断向外寻找以找到该结构.
     * <p>
     * 搜索{@link StructureType}和{@link Structure}的区别在于, {@link StructureType}可以引用多个{@link Structure 结构},
     * 而搜索{@link Structure}只会搜索给定的{@link Structure}.
     * <p>原文：
     * Find the closest nearby structure of a given {@link StructureType}.
     * Finding unexplored structures can, and will, block if the world is
     * looking in chunks that have not generated yet. This can lead to the world
     * temporarily freezing while locating an unexplored structure.
     * <p>
     * The {@code radius} is not a rigid square radius. Each structure may alter
     * how many chunks to check for each iteration. Do not assume that only a
     * radius x radius chunk area will be checked. For example,
     * {@link StructureType#WOODLAND_MANSION} can potentially check up to 20,000
     * blocks away (or more) regardless of the radius used.
     * <p>
     * This will <i>not</i> load or generate chunks. This can also lead to
     * instances where the server can hang if you are only looking for
     * unexplored structures. This is because it will keep looking further and
     * further out in order to find the structure.
     * <p>
     * The difference between searching for a {@link StructureType} and a
     * {@link Structure} is, that a {@link StructureType} can refer to multiple
     * {@link Structure Structures} while searching for a {@link Structure}
     * while only search for the given {@link Structure}.
     *
     * @param origin 开始查找结构的位置
     * <p>原文：where to start looking for a structure
     * @param structureType 要查找的结构类型
     * <p>原文：the type of structure to find
     * @param radius 搜索半径（以区块为单位）
     * <p>原文：the radius, in chunks, around which to search
     * @param findUnexplored 是否只查找未探索的结构
     * <p>原文：true to only find unexplored structures
     * @return 最近的{@link Location 位置}和{@link Structure 结构}, 如果不存在指定类型的结构则返回null
     * <p>原文：the closest {@link Location} and {@link Structure}, or null if no
     * structure of the specified type exists.
     * @see #locateNearestStructure(Location, Structure, int, boolean)
     */
    @Nullable
    StructureSearchResult locateNearestStructure(@NotNull Location origin, @NotNull StructureType structureType, int radius, boolean findUnexplored);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找最近的指定{@link Structure 结构}的结构.
     * 查找未探索的结构可能会阻塞, 如果世界正在查看尚未生成的区块. 这可能导致世界在定位未探索的结构时暂时冻结.
     * <p>
     * {@code radius}不是严格的方形半径. 每种结构可能改变每次迭代检查的区块数量. 不要假设只会检查半径*半径的区块区域. 例如,
     * {@link Structure#MANSION}可能会检查距离20000个方块（或更多）远的地方, 无论使用的半径是多少.
     * <p>
     * 此方法<i>不会</i>加载或生成区块. 如果你只查找未探索的结构, 这也可能导致服务器挂起的情况. 这是因为它会不断向外寻找以找到该结构.
     * <p>
     * 搜索{@link StructureType}和{@link Structure}的区别在于, {@link StructureType}可以引用多个{@link Structure 结构},
     * 而搜索{@link Structure}只会搜索给定的{@link Structure}.
     * <p>原文：
     * Find the closest nearby structure of a given {@link Structure}. Finding
     * unexplored structures can, and will, block if the world is looking in
     * chunks that have not generated yet. This can lead to the world
     * temporarily freezing while locating an unexplored structure.
     * <p>
     * The {@code radius} is not a rigid square radius. Each structure may alter
     * how many chunks to check for each iteration. Do not assume that only a
     * radius x radius chunk area will be checked. For example,
     * {@link Structure#MANSION} can potentially check up to 20,000 blocks away
     * (or more) regardless of the radius used.
     * <p>
     * This will <i>not</i> load or generate chunks. This can also lead to
     * instances where the server can hang if you are only looking for
     * unexplored structures. This is because it will keep looking further and
     * further out in order to find the structure.
     * <p>
     * The difference between searching for a {@link StructureType} and a
     * {@link Structure} is, that a {@link StructureType} can refer to multiple
     * {@link Structure Structures} while searching for a {@link Structure}
     * while only search for the given {@link Structure}.
     *
     * @param origin 开始查找结构的位置
     * <p>原文：where to start looking for a structure
     * @param structure 要查找的结构
     * <p>原文：the structure to find
     * @param radius 搜索半径（以区块为单位）
     * <p>原文：the radius, in chunks, around which to search
     * @param findUnexplored 是否只查找未探索的结构
     * <p>原文：true to only find unexplored structures
     * @return 最近的{@link Location 位置}和{@link Structure 结构}, 如果未找到结构则返回null
     * <p>原文：the closest {@link Location} and {@link Structure}, or null if no
     * structure was found.
     * @see #locateNearestStructure(Location, StructureType, int, boolean)
     */
    @Nullable
    StructureSearchResult locateNearestStructure(@NotNull Location origin, @NotNull Structure structure, int radius, boolean findUnexplored);

    // Paper start
    /**
     * Locates the nearest biome based on an origin, biome type, and radius to search.
     * Step defaults to {@code 8}.
     *
     * @param origin Origin location
     * @param biome Biome to find
     * @param radius radius to search
     * @return Location of biome or null if not found in specified radius
     * @deprecated use {@link #locateNearestBiome(Location, int, Biome...)}
     */
    @Deprecated
    @Nullable
    default Location locateNearestBiome(@NotNull Location origin, @NotNull Biome biome, int radius) {
        return java.util.Optional.ofNullable(this.locateNearestBiome(origin, radius, 8, 8, biome)).map(BiomeSearchResult::getLocation).orElse(null);
    }

    /**
     * Locates the nearest biome based on an origin, biome type, and radius to search
     * and step
     *
     * @param origin Origin location
     * @param biome Biome to find
     * @param radius radius to search
     * @param step Search step 1 would mean checking every block, 8 would be every 8th block
     * @return Location of biome or null if not found in specified radius
     * @deprecated use {@link #locateNearestBiome(Location, int, int, int, Biome...)}
     */
    @Deprecated
    @Nullable
    default Location locateNearestBiome(@NotNull Location origin, @NotNull Biome biome, int radius, int step) {
        return java.util.Optional.ofNullable(this.locateNearestBiome(origin, radius, step, step, biome)).map(BiomeSearchResult::getLocation).orElse(null);
    }

    /**
     * Gets the coordinate scaling of this world.
     *
     * @return the coordinate scale
     */
    double getCoordinateScale();

    /**
     * Checks if this world has a fixed time
     *
     * @return whether this world has fixed time
     */
    boolean isFixedTime();

    /**
     * Gets the collection of materials that burn infinitely in this world.
     *
     * @return the materials that will forever stay lit by fire
     */
    @NotNull
    Collection<Material> getInfiniburn();

    /**
     * Posts a specified game event at a location
     *
     * @param sourceEntity optional source entity
     * @param gameEvent the game event to post
     * @param position the position in the world where to post the event to listeners
     */
    void sendGameEvent(@Nullable Entity sourceEntity, @NotNull GameEvent gameEvent, @NotNull Vector position);
    // Paper end

    // Spigot start
    @Deprecated(forRemoval = true) // Paper
    public class Spigot {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在给定的{@link Location 位置}劈下闪电, 可能不发出声音.
         * <p>原文：
         * Strikes lightning at the given {@link Location} and possibly without sound
         *
         * @param loc 劈下闪电的位置
         * <p>原文：The location to strike lightning
         * @param isSilent 此次闪电是否不发出声音
         * <p>原文：Whether this strike makes no sound
         * @return 闪电实体
         * <p>原文：The lightning entity.
         * @deprecated 声音现在是客户端侧的, 无法移除
         * <p>原文：sound is now client side and cannot be removed
         * @see World#strikeLightning(org.bukkit.Location)
         */
        @NotNull
        @Deprecated(since = "1.20.4")
        public LightningStrike strikeLightning(@NotNull Location loc, boolean isSilent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在给定的{@link Location 位置}劈下不会造成伤害的闪电, 可能不发出声音.
         * <p>原文：
         * Strikes lightning at the given {@link Location} without doing damage and possibly without sound
         *
         * @param loc 劈下闪电的位置
         * <p>原文：The location to strike lightning
         * @param isSilent 此次闪电是否不发出声音
         * <p>原文：Whether this strike makes no sound
         * @return 闪电实体
         * <p>原文：The lightning entity.
         * @deprecated 声音现在是客户端侧的, 无法移除
         * <p>原文：sound is now client side and cannot be removed
         * @see World#strikeLightningEffect(org.bukkit.Location)
         */
        @NotNull
        @Deprecated(since = "1.20.4")
        public LightningStrike strikeLightningEffect(@NotNull Location loc, boolean isSilent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    /**
     * @deprecated Unsupported api
     */
    @NotNull
    @Deprecated // Paper
    Spigot spigot();
    // Spigot end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找最近的与提供的{@link Biome 生物群系}匹配的位置.
     * 查找生物群系可能会阻塞, 如果世界正在查看尚未生成的区块. 这可能导致世界在定位生物群系时暂时冻结.
     * <p>
     * <b>注意：</b>此方法<i>不会</i>反映世界生成后所做的更改, 此方法只能看到世界生成时的生物群系.
     * 此方法<i>不会</i>加载或生成区块.
     * <p>
     * 如果提供了多个生物群系, {@link BiomeSearchResult#getBiome()}将指示定位到的是哪一个.
     * <p>
     * 此方法将使用32的水平间隔和64的垂直间隔, 等同于/locate命令.
     * <p>原文：
     * Find the closest nearby location with a biome matching the provided
     * {@link Biome}(s). Finding biomes can, and will, block if the world is looking
     * in chunks that have not generated yet. This can lead to the world temporarily
     * freezing while locating a biome.
     * <p>
     * <b>Note:</b> This will <i>not</i> reflect changes made to the world after
     * generation, this method only sees the biome at the time of world generation.
     * This will <i>not</i> load or generate chunks.
     * <p>
     * If multiple biomes are provided {@link BiomeSearchResult#getBiome()} will
     * indicate which one was located.
     * <p>
     * This method will use a horizontal interval of 32 and a vertical interval of
     * 64, equal to the /locate command.
     *
     * @param origin 开始查找生物群系的位置
     * <p>原文：where to start looking for a biome
     * @param radius 搜索半径（以方块为单位）
     * <p>原文：the radius, in blocks, around which to search
     * @param biomes 要搜索的生物群系
     * <p>原文：the biomes to search for
     * @return 包含最近的{@link Location 位置}和{@link Biome 生物群系}的BiomeSearchResult, 如果未找到生物群系则返回null
     * <p>原文：a BiomeSearchResult containing the closest {@link Location} and
     *         {@link Biome}, or null if no biome was found.
     * @see #locateNearestBiome(Location, int, int, int, Biome...)
     */
    @Nullable
    default BiomeSearchResult locateNearestBiome(@NotNull Location origin, int radius, @NotNull Biome... biomes) {
        return this.locateNearestBiome(origin, radius, 32, 64, biomes);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查找最近的与提供的{@link Biome 生物群系}匹配的位置.
     * 查找生物群系可能会阻塞, 如果世界正在查看尚未生成的区块. 这可能导致世界在定位生物群系时暂时冻结.
     * <p>
     * <b>注意：</b>此方法<i>不会</i>反映世界生成后所做的更改, 此方法只能看到世界生成时的生物群系.
     * 此方法<i>不会</i>加载或生成区块.
     * <p>
     * 如果提供了多个生物群系, {@link BiomeSearchResult#getBiome()}将指示定位到的是哪一个.
     * 较高的{@code horizontalInterval}和{@code verticalInterval}值将导致更快的搜索, 但可能会遗漏较小的生物群系.
     * <p>原文：
     * Find the closest nearby location with a biome matching the provided
     * {@link Biome}(s). Finding biomes can, and will, block if the world is looking
     * in chunks that have not generated yet. This can lead to the world temporarily
     * freezing while locating a biome.
     * <p>
     * <b>Note:</b> This will <i>not</i> reflect changes made to the world after
     * generation, this method only sees the biome at the time of world generation.
     * This will <i>not</i> load or generate chunks.
     * <p>
     * If multiple biomes are provided {@link BiomeSearchResult#getBiome()} will
     * indicate which one was located. Higher values for {@code horizontalInterval}
     * and {@code verticalInterval} will result in faster searches, but may lead to
     * small biomes being missed.
     *
     * @param origin             开始查找生物群系的位置
     * <p>原文：where to start looking for a biome
     * @param radius             搜索半径（以方块为单位）
     * <p>原文：the radius, in blocks, around which to search
     * @param horizontalInterval 每次检查之间的水平距离
     * <p>原文：the horizontal distance between each check
     * @param verticalInterval   每次检查之间的垂直距离
     * <p>原文：the vertical distance between each check
     * @param biomes             要搜索的生物群系
     * <p>原文：the biomes to search for
     * @return 包含最近的{@link Location 位置}和{@link Biome 生物群系}的BiomeSearchResult, 如果未找到生物群系则返回null
     * <p>原文：a BiomeSearchResult containing the closest {@link Location} and
     *         {@link Biome}, or null if no biome was found.
     * @see #locateNearestBiome(Location, int, Biome...)
     */
    @Nullable
    BiomeSearchResult locateNearestBiome(@NotNull Location origin, int radius, int horizontalInterval, int verticalInterval, @NotNull Biome... biomes);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 寻找与给定位置相距最近的袭击.
     * <p>原文：
     * Finds the nearest raid close to the given location.
     *
     * @param location 源位置
     * <p>原文：the origin location
     * @param radius 搜索半径
     * <p>原文：the radius
     * @return 最近的{@link Raid 袭击}, 如果没有找到袭击事件返回null.
     * <p>原文：the closest {@link Raid}, or null if no raids were found
     */
    @Nullable
    public Raid locateNearestRaid(@NotNull Location location, int radius);

    // Paper start - more Raid API
    /**
     * Get a raid with the specific id from {@link Raid#getId}
     * from this world.
     *
     * @param id the id of the raid
     * @return the raid or null if none with that id
     */
    @Nullable Raid getRaid(int id);
    // Paper end - more Raid API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本世界正在进行的所有袭击.
     * <p>原文：
     * Gets all raids that are going on over this world.
     *
     * @return 本世界所有活跃的袭击
     * <p>原文：the list of all active raids
     */
    @NotNull
    public List<Raid> getRaids();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与此世界关联的{@link DragonBattle 末影龙战斗}.
     * <p>
     * 如果此世界的环境不是{@link Environment#THE_END}, 将返回null.
     * <p>
     * 如果是末地世界, 无论世界中是否存在末影龙或是否已激活战斗序列, 都将返回一个末影龙战斗实例. 末影龙战斗实例用作状态持有者.
     * <p>原文：
     * Get the {@link DragonBattle} associated with this world.
     *
     * If this world's environment is not {@link Environment#THE_END}, null will
     * be returned.
     * <p>
     * If an end world, a dragon battle instance will be returned regardless of
     * whether or not a dragon is present in the world or a fight sequence has
     * been activated. The dragon battle instance acts as a state holder.
     *
     * @return 末影龙战斗实例
     * <p>原文：the dragon battle instance
     */
    @Nullable
    public DragonBattle getEnderDragonBattle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此世界启用的所有{@link FeatureFlag 功能标志}.
     * <p>原文：
     * Get all {@link FeatureFlag} enabled in this world.
     *
     * @return 所有启用的{@link FeatureFlag 功能标志}
     * <p>原文：all enabled {@link FeatureFlag}
     */
    @NotNull
    public Set<FeatureFlag> getFeatureFlags();

    // Paper start - view distance api
    /**
     * Sets the view distance for this world.
     * @param viewDistance view distance in [2, 32]
     */
    void setViewDistance(int viewDistance);

    /**
     * Sets the simulation distance for this world.
     * @param simulationDistance simulation distance in [2, 32]
     */
    void setSimulationDistance(int simulationDistance);

    /**
     * Returns the no-tick view distance for this world.
     * <p>
     * No-tick view distance is the view distance where chunks will load, however the chunks and their entities will not
     * be set to tick.
     * </p>
     * @return The no-tick view distance for this world.
     * @deprecated Use {@link #getViewDistance()}
     */
    @Deprecated
    default int getNoTickViewDistance() {
        return this.getViewDistance();
    }

    /**
     * Sets the no-tick view distance for this world.
     * <p>
     * No-tick view distance is the view distance where chunks will load, however the chunks and their entities will not
     * be set to tick.
     * </p>
     * @param viewDistance view distance in [2, 32]
     * @deprecated Use {@link #setViewDistance(int)}
     */
    @Deprecated
    default void setNoTickViewDistance(int viewDistance) {
        this.setViewDistance(viewDistance);
    }

    /**
     * Gets the sending view distance for this world.
     * <p>
     * Sending view distance is the view distance where chunks will load in for players in this world.
     * </p>
     * @return The sending view distance for this world.
     */
    int getSendViewDistance();

    /**
     * Sets the sending view distance for this world.
     * <p>
     * Sending view distance is the view distance where chunks will load in for players in this world.
     * </p>
     * @param viewDistance view distance in [2, 32] or -1
     */
    void setSendViewDistance(int viewDistance);
    // Paper end - view distance api

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与给定坐标区块相交的所有已生成结构. <br>
     * 如果不存在结构, 则返回空集合.
     * <p>原文：
     * Gets all generated structures that intersect the chunk at the given
     * coordinates. <br>
     * If no structures are present an empty collection will be returned.
     *
     * @param x 区块的X坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的Z坐标
     * <p>原文：Z-coordinate of the chunk
     * @return 给定坐标区块中已放置的结构集合
     * <p>原文：a collection of placed structures in the chunk at the given
     * coordinates
     */
    @NotNull
    public Collection<GeneratedStructure> getStructures(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与给定坐标区块相交的所有指定{@link Structure 结构}的已生成结构. <br>
     * 如果不存在结构, 则返回空集合.
     * <p>原文：
     * Gets all generated structures of a given {@link Structure} that intersect
     * the chunk at the given coordinates. <br>
     * If no structures are present an empty collection will be returned.
     *
     * @param x 区块的X坐标
     * <p>原文：X-coordinate of the chunk
     * @param z 区块的Z坐标
     * <p>原文：Z-coordinate of the chunk
     * @param structure 要查找的结构
     * <p>原文：the structure to find
     * @return 给定坐标区块中已放置的结构集合
     * <p>原文：a collection of placed structures in the chunk at the given
     * coordinates
     */
    @NotNull
    public Collection<GeneratedStructure> getStructures(int x, int z, @NotNull Structure structure);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示世界可能的各种地图环境类型.
     * <p>原文：
     * Represents various map environment types that a world may be
     */
    public enum Environment {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示"normal"/"surface world"地图.
         * <p>原文：
         * Represents the "normal"/"surface world" map
         */
        NORMAL(0),
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示一个基于"hell"地图的地狱.
         * <p>原文：
         * Represents a nether based map ("hell")
         */
        NETHER(-1),
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示"end"地图.
         * <p>原文：
         * Represents the "end" map
         */
        THE_END(1),
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 代表自定义维度.
         * <p>原文：
         * Represents a custom dimension
         */
        CUSTOM(-999);

        private final int id;
        private static final Map<Integer, Environment> lookup = new HashMap<Integer, Environment>();

        private Environment(int id) {
            this.id = id;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取这个环境的ID.
         * <p>原文：
         * Gets the dimension ID of this environment
         *
         * @return dimension ID
         * @apiNote Internal Use Only
         */
        @org.jetbrains.annotations.ApiStatus.Internal // Paper
        public int getId() {
            return id;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 根据ID获取环境.
         * <p>原文：
         * Get an environment by ID
         *
         * @param id 环境ID
         * <p>原文：The ID of the environment
         * @return 环境
         * <p>原文：The environment
         * @apiNote Internal Use Only
         */
        @org.jetbrains.annotations.ApiStatus.Internal // Paper
        @Nullable
        public static Environment getEnvironment(int id) {
            return lookup.get(id);
        }

        static {
            for (Environment env : values()) {
                lookup.put(env.getId(), env);
            }
        }
    }
}
