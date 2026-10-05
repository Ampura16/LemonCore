package org.bukkit;

import java.util.Collection;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.generator.structure.GeneratedStructure;
import org.bukkit.generator.structure.Structure;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个16*256*16的空间.
 * <p>
 * 如果区块尚未完全生成，但又被请求了数据，那么区块只会生成到足以提供所请求数据的程度。
 * <p>原文：
 * Represents a chunk of blocks.
 *
 * If the chunk is not yet fully generated and data is requested from the chunk,
 * then the chunk will only be generated as far as it needs to provide the
 * requested data.
 */
public interface Chunk extends PersistentDataHolder {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该区块的X轴坐标.
     * <p>原文：
     * Gets the X-coordinate of this chunk
     *
     * @return 区块X轴坐标
     * <p>原文：X-coordinate
     */
    int getX();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该区块的Z轴坐标.
     * <p>原文：
     * Gets the Z-coordinate of this chunk
     *
     * @return 区块的Z轴坐标
     * <p>原文：Z-coordinate
     */
    int getZ();

    // Paper start
    /**
     * @return The Chunks X and Z coordinates packed into a long
     */
    default long getChunkKey() {
        return getChunkKey(getX(), getZ());
    }

    /**
     * @param loc Location to get chunk key
     * @return Location's chunk coordinates packed into a long
     */
    static long getChunkKey(@NotNull Location loc) {
        return getChunkKey((int) Math.floor(loc.getX()) >> 4, (int) Math.floor(loc.getZ()) >> 4);
    }

    /**
     * @param x X Coordinate
     * @param z Z Coordinate
     * @return Chunk coordinates packed into a long
     */
    static long getChunkKey(int x, int z) {
        return (long) x & 0xffffffffL | ((long) z & 0xffffffffL) << 32;
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该区块属于哪个世界.
     * <p>原文：
     * Gets the world containing this chunk
     *
     * @return 世界名
     * <p>原文：Parent World
     */
    @NotNull
    World getWorld();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从区块中获取某个方块({@link Block}).
     * <p>原文：
     * Gets a block from this chunk
     *
     * @param x 获取方块的X轴坐标,在0-15之间
     * <p>原文：0-15
     * @param y 获取方块的Y轴坐标,在 [世界最低高度, 世界最高高度) 之间
     * <p>原文：world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 获取方块的Z轴坐标,在0-15之间
     * <p>原文：0-15
     * @return 方块对象
     * <p>原文：the Block
     */
    @NotNull
    Block getBlock(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取区块数据的线程安全只读快照.
     * <p>原文：
     * Capture thread-safe read-only snapshot of chunk data
     *
     * @return 区块快照
     * <p>原文：ChunkSnapshot
     */
    @NotNull
    default ChunkSnapshot getChunkSnapshot() {
        return this.getChunkSnapshot(true, false, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取区块数据的线程安全只读快照.
     * <p>原文：
     * Capture thread-safe read-only snapshot of chunk data
     *
     * @param includeMaxBlockY - if true, snapshot includes per-coordinate
     *     maximum Y values
     * @param includeBiome 如果为true，快照将包含每个坐标的生物群系类型
     * <p>原文：- if true, snapshot includes per-coordinate biome
     *     type
     * @param includeBiomeTempRain 如果为true，快照将包含每个坐标的原始生物群系温度和降雨量
     * <p>原文：- if true, snapshot includes per-coordinate
     *     raw biome temperature and rainfall
     * @return 区块快照
     * <p>原文：ChunkSnapshot
     */
    @NotNull
    default ChunkSnapshot getChunkSnapshot(boolean includeMaxBlockY, boolean includeBiome, boolean includeBiomeTempRain) {
        return this.getChunkSnapshot(includeMaxBlockY, includeBiome, includeBiomeTempRain, true);
    }

    // Paper start - Add getChunkSnapshot includeLightData parameter
    /**
     * Capture thread-safe read-only snapshot of chunk data
     *
     * @param includeMaxBlockY if true, snapshot includes per-coordinate
     *     maximum Y values
     * @param includeBiome if true, snapshot includes per-coordinate biome
     *     type
     * @param includeBiomeTempRain if true, snapshot includes per-coordinate
     *     raw biome temperature and rainfall
     * @param includeLightData Whether to include per-coordinate light emitted by blocks and sky light data
     * @return ChunkSnapshot
     */
    @NotNull
    ChunkSnapshot getChunkSnapshot(boolean includeMaxBlockY, boolean includeBiome, boolean includeBiomeTempRain, boolean includeLightData);
    // Paper end - Add getChunkSnapshot includeLightData parameter

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此区块中的实体是否已加载.
     * <p>原文：
     * Checks if entities in this chunk are loaded.
     *
     * @return 如果实体已加载则返回true
     * <p>原文：True if entities are loaded.
     */
    boolean isEntitiesLoaded();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该区块内的所有实体.
     * 这将强制加载尚未加载的任意实体.
     * <p>原文：
     * Get a list of all entities in the chunk.
     * This will force load any entities, which are not loaded.
     *
     * @return 所有实体
     * <p>原文：The entities.
     */
    @NotNull Entity @NotNull [] getEntities();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该区块内所有的TileEntity的列表.
     * <p>原文：
     * Get a list of all block entities in the chunk.
     *
     * @return 实体列表数组
     * <p>原文：The block entities.
     */
    @NotNull
    // Paper start
    default BlockState @NotNull [] getTileEntities() {
        return getTileEntities(true);
    }

    /**
     * Get a list of all block entities in the chunk.
     *
     * @param useSnapshot Take snapshots or direct references
     * @return The block entities.
     */
    @NotNull
    BlockState @NotNull [] getTileEntities(boolean useSnapshot);

    /**
     * Get a list of all block entities that match a given predicate in the chunk.
     *
     * @param blockPredicate The predicate of blocks to return block entities for
     * @param useSnapshot Take snapshots or direct references
     * @return The block entities.
     */
    @NotNull
    Collection<BlockState> getTileEntities(java.util.function.@NotNull Predicate<? super Block> blockPredicate, boolean useSnapshot);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查区块是否已完全生成.
     * <p>原文：
     * Checks if the chunk is fully generated.
     *
     * @return 如果已完全生成则返回true
     * <p>原文：True if it is fully generated.
     */
    boolean isGenerated();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回该区块是否被加载.
     * <p>原文：
     * Checks if the chunk is loaded.
     *
     * @return 成功加载返回true,否则返回false
     * <p>原文：True if it is loaded.
     */
    boolean isLoaded();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 加载区块.
     * <p>原文：
     * Loads the chunk.
     *
     * @param generate 如果该区块不存在是否自动生成
     * <p>原文：Whether or not to generate a chunk if it doesn't
     *     already exist
     * @return 成功加载返回true,反之返回false
     * <p>原文：true if the chunk has loaded successfully, otherwise false
     */
    boolean load(boolean generate);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 加载区块.
     * <p>原文：
     * Loads the chunk.
     *
     * @return 成功加载返回true,反之返回false
     * <p>原文：true if the chunk has loaded successfully, otherwise false
     */
    boolean load();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 卸载该区块.可以选择是否保存.
     * <p>原文：
     * Unloads and optionally saves the Chunk
     *
     * @param save 卸载时是否保存.
     * <p>原文：Controls whether the chunk is saved
     * @return 成功卸载返回true,反之返回false
     * <p>原文：true if the chunk has unloaded successfully, otherwise false
     */
    boolean unload(boolean save);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 卸载并可选是否保存区块.
     * <p>原文：
     * Unloads and optionally saves the Chunk
     *
     * @return 成功卸载返回true,反之返回false
     * <p>原文：true if the chunk has unloaded successfully, otherwise false
     */
    boolean unload();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此区块是否能在非沼泽生物群系中生成史莱姆.
     * <p>原文：
     * Checks if this chunk can spawn slimes without being a swamp biome.
     *
     * @return 如果史莱姆能在此区块生成则返回true
     * <p>原文：true if slimes are able to spawn in this chunk
     */
    boolean isSlimeChunk();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标处的区块是否被强制加载.
     * <p>
     * 强制加载的区块不会因为缺少玩家活动而被卸载.
     * <p>原文：
     * Gets whether the chunk at the specified chunk coordinates is force
     * loaded.
     * <p>
     * A force loaded chunk will not be unloaded due to lack of player activity.
     *
     * @return 强制加载状态
     * <p>原文：force load status
     * @see World#isChunkForceLoaded(int, int)
     */
    boolean isForceLoaded();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定坐标处的区块是否被强制加载.
     * <p>
     * 强制加载的区块不会因为缺少玩家活动而被卸载.
     * <p>原文：
     * Sets whether the chunk at the specified chunk coordinates is force
     * loaded.
     * <p>
     * A force loaded chunk will not be unloaded due to lack of player activity.
     *
     * @param forced 强制加载状态
     * <p>原文：force load status
     * @see World#setChunkForceLoaded(int, int, boolean)
     */
    void setForceLoaded(boolean forced);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为此区块添加一个插件票据，如果区块尚未加载则会加载该区块.
     * <p>
     * 插件票据会阻止区块被卸载，直到被显式移除。每个插件实例在每个区块上只能有一个票据，但每个区块可以有多个插件票据.
     * <p>原文：
     * Adds a plugin ticket for this chunk, loading this chunk if it is not
     * already loaded.
     * <p>
     * A plugin ticket will prevent a chunk from unloading until it is
     * explicitly removed. A plugin instance may only have one ticket per chunk,
     * but each chunk can have multiple plugin tickets.
     * </p>
     *
     * @param plugin 拥有该票据的插件
     * <p>原文：Plugin which owns the ticket
     * @return 如果添加了插件票据则返回{@code true}，如果该插件的票据已存在则返回{@code false}
     * <p>原文：{@code true} if a plugin ticket was added, {@code false} if the
     * ticket already exists for the plugin
     * @throws IllegalStateException 如果指定的插件未启用
     * <p>原文：If the specified plugin is not enabled
     * @see World#addPluginChunkTicket(int, int, Plugin)
     */
    boolean addPluginChunkTicket(@NotNull Plugin plugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除指定插件在此区块上的票据.
     * <p>
     * 插件票据会阻止区块被卸载，直到被显式移除。每个插件实例在每个区块上只能有一个票据，但每个区块可以有多个插件票据.
     * <p>原文：
     * Removes the specified plugin's ticket for this chunk
     * <p>
     * A plugin ticket will prevent a chunk from unloading until it is
     * explicitly removed. A plugin instance may only have one ticket per chunk,
     * but each chunk can have multiple plugin tickets.
     * </p>
     *
     * @param plugin 拥有该票据的插件
     * <p>原文：Plugin which owns the ticket
     * @return 如果移除了插件票据则返回{@code true}，如果该区块没有该插件的票据则返回{@code false}
     * <p>原文：{@code true} if the plugin ticket was removed, {@code false} if
     * there is no plugin ticket for the chunk
     * @see World#removePluginChunkTicket(int, int, Plugin)
     */
    boolean removePluginChunkTicket(@NotNull Plugin plugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定哪些插件在此区块拥有票据的集合。当插件票据被添加或移除时，此集合不会更新.
     * <p>
     * 插件票据会阻止区块被卸载，直到被显式移除。每个插件实例在每个区块上只能有一个票据，但每个区块可以有多个插件票据.
     * <p>原文：
     * Retrieves a collection specifying which plugins have tickets for this
     * chunk. This collection is not updated when plugin tickets are added or
     * removed to this chunk.
     * <p>
     * A plugin ticket will prevent a chunk from unloading until it is
     * explicitly removed. A plugin instance may only have one ticket per chunk,
     * but each chunk can have multiple plugin tickets.
     * </p>
     *
     * @return 包含在此区块拥有票据的插件的不可修改集合
     * <p>原文：unmodifiable collection containing which plugins have tickets for
     * this chunk
     * @see World#getPluginChunkTickets(int, int)
     */
    @NotNull
    Collection<Plugin> getPluginChunkTickets();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此区块已被居住的时间（以tick为单位）.
     * <p>
     * 注意：对于在怪物生成距离内的每个玩家，每个tick时间会增加一次.
     * <p>原文：
     * Gets the amount of time in ticks that this chunk has been inhabited.
     *
     * Note that the time is incremented once per tick per player within mob
     * spawning distance of this chunk.
     *
     * @return 已被居住的时间
     * <p>原文：inhabited time
     */
    long getInhabitedTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此区块已被居住的时间（以tick为单位）.
     * <p>原文：
     * Sets the amount of time in ticks that this chunk has been inhabited.
     *
     * @param ticks 新的居住时间
     * <p>原文：new inhabited time
     */
    void setInhabitedTime(long ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试此区块是否包含指定的方块.
     * <p>原文：
     * Tests if this chunk contains the specified block.
     *
     * @param block 要测试的方块
     * <p>原文：block to test
     * @return 如果区块包含该方块则返回true
     * <p>原文：if the block is contained within
     */
    boolean contains(@NotNull BlockData block);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试此区块是否包含指定的生物群系.
     * <p>原文：
     * Tests if this chunk contains the specified biome.
     *
     * @param biome 要测试的生物群系
     * <p>原文：biome to test
     * @return 如果区块包含该生物群系则返回true
     * <p>原文：if the biome is contained within
     */
    boolean contains(@NotNull Biome biome);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此区块的加载级别，该级别决定了会处理哪些游戏逻辑.
     * <p>原文：
     * Gets the load level of this chunk, which determines what game logic is
     * processed.
     *
     * @return 加载级别
     * <p>原文：the load level
     */
    @NotNull
    LoadLevel getLoadLevel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与此区块相交的所有已生成结构.
     * <p>
     * 如果没有结构存在，将返回空集合.
     * <p>原文：
     * Gets all generated structures that intersect this chunk. <br>
     * If no structures are present an empty collection will be returned.
     *
     * @return 此区块中已放置结构的集合
     * <p>原文：a collection of placed structures in this chunk
     */
    @NotNull
    Collection<GeneratedStructure> getStructures();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与此区块相交的所有给定 {@link Structure} 类型的已生成结构.
     * <p>
     * 如果没有结构存在，将返回空集合.
     * <p>原文：
     * Gets all generated structures of a given {@link Structure} that intersect
     * this chunk. <br>
     * If no structures are present an empty collection will be returned.
     *
     * @param structure 要查找的结构类型
     * <p>原文：the structure to find
     * @return 此区块中已放置结构的集合
     * <p>原文：a collection of placed structures in this chunk
     */
    @NotNull
    Collection<GeneratedStructure> getStructures(@NotNull Structure structure);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有能从客户端查看此区块的玩家列表.
     * <p>
     * 如果没有玩家正在查看此区块，或区块已卸载，则此列表为空.
     * <p>原文：
     * Get a list of all players who are can view the chunk from their client
     * <p>
     * This list will be empty if no players are viewing the chunk, or the chunk
     * is unloaded.
     *
     * @return 能看到此区块的玩家集合
     * <p>原文：collection of players who can see the chunk
     */
    @NotNull
    public Collection<Player> getPlayersSeeingChunk();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于指定区块加载级别的枚举.
     * <p>原文：
     * An enum to specify the load level of a chunk.
     */
    public enum LoadLevel {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 不处理任何游戏逻辑，但世界生成可能仍然发生.
         * <p>原文：
         * No game logic is processed, world generation may still occur.
         */
        INACCESSIBLE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 大部分游戏逻辑不会被处理，包括实体和红石.
         * <p>原文：
         * Most game logic is not processed, including entities and redstone.
         */
        BORDER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 除实体外的所有游戏逻辑都会被处理.
         * <p>原文：
         * All game logic except entities is processed.
         */
        TICKING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 所有游戏逻辑都会被处理.
         * <p>原文：
         * All game logic is processed.
         */
        ENTITY_TICKING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 此区块未加载.
         * <p>原文：
         * This chunk is not loaded.
         */
        UNLOADED;
    }
}
