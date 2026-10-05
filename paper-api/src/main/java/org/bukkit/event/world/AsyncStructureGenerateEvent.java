package org.bukkit.event.world;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.bukkit.generator.structure.Structure;
import org.bukkit.util.BlockTransformer;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.EntityTransformer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 此事件有时会同步触发，取决于其触发方式。
 * <p>
 * 构造函数提供了一个布尔值来指示事件是同步还是异步触发的。当异步触发时，此事件可以从除主线程外的任意线程调用，并且对 API 的访问是受限的。
 * <p>
 * 如果 {@link Structure} 是在世界区块中自然生成的，则此事件将是异步的。如果玩家执行了 '/place structure' 命令，则此事件将是同步的。
 *
 * 允许注册可以修改结构放置的方块和生成的实体的转换器。
 * <p>
 * 应注意检查 {@link #isAsynchronous()} 并适当地处理事件。
 * <p>原文：
 * This event will sometimes fire synchronously, depending on how it was
 * triggered.
 * <p>
 * The constructor provides a boolean to indicate if the event was fired
 * synchronously or asynchronously. When asynchronous, this event can be called
 * from any thread, sans the main thread, and has limited access to the API.
 * <p>
 * If a {@link Structure} is naturally placed in a chunk of the world, this
 * event will be asynchronous. If a player executes the '/place structure'
 * command, this event will be synchronous.
 * <br>
 * Allows to register transformers that can modify the blocks placed and
 * entities spawned by the structure.
 * <p>
 * Care should be taken to check {@link #isAsynchronous()} and treat the event
 * appropriately.
 * <p>
 */
@ApiStatus.Experimental
public class AsyncStructureGenerateEvent extends WorldEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Cause cause;

    private final Structure structure;
    private final BoundingBox boundingBox;

    private final int chunkX, chunkZ;

    private final Map<NamespacedKey, BlockTransformer> blockTransformers = new LinkedHashMap<>();
    private final Map<NamespacedKey, EntityTransformer> entityTransformers = new LinkedHashMap<>();

    @ApiStatus.Internal
    public AsyncStructureGenerateEvent(@NotNull World world, boolean async, @NotNull Cause cause, @NotNull Structure structure, @NotNull BoundingBox boundingBox, int chunkX, int chunkZ) {
        super(world, async);
        this.cause = cause;
        this.structure = structure;
        this.boundingBox = boundingBox;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取事件原因。
     * <p>原文：
     * Gets the event cause.
     *
     * @return 事件原因
     * <p>原文：Gets the event cause.
     * <p>原文：the event cause
     */
    @NotNull
    public Cause getCause() {
        return this.cause;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取生成的结构引用。
     * <p>原文：
     * Get the structure reference that is generated.
     *
     * @return 结构
     * <p>原文：Get the structure reference that is generated.
     * <p>原文：the structure
     */
    @NotNull
    public Structure getStructure() {
        return this.structure;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取结构的边界框。
     * <p>原文：
     * Get the bounding box of the structure.
     *
     * @return 边界框
     * <p>原文：Get the bounding box of the structure.
     * <p>原文：the bounding box
     */
    @NotNull
    public BoundingBox getBoundingBox() {
        return this.boundingBox.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取结构起始区块的 x 坐标。
     * <p>原文：
     * Get the x coordinate of the origin chunk of the structure.
     *
     * @return 区块的 x 坐标
     * <p>原文：Get the x coordinate of the origin chunk of the structure.
     * <p>原文：the chunk x coordinate
     */
    public int getChunkX() {
        return this.chunkX;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取结构起始区块的 z 坐标。
     * <p>原文：
     * Get the z coordinate of the origin chunk of the structure.
     *
     * @return 区块的 z 坐标
     * <p>原文：Get the z coordinate of the origin chunk of the structure.
     * <p>原文：the chunk z coordinate
     */
    public int getChunkZ() {
        return this.chunkZ;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过键获取方块转换器。
     * <p>原文：
     * Gets a block transformer by key.
     *
     * @param key 方块转换器的键
     * <p>原文：the key of the block transformer
     * @return 方块转换器或 null
     * <p>原文：Gets a block transformer by key.
     * <p>原文：the block transformer or {@code null}
     */
    @Nullable
    public BlockTransformer getBlockTransformer(@NotNull NamespacedKey key) {
        Preconditions.checkArgument(key != null, "NamespacedKey cannot be null");
        return this.blockTransformers.get(key);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将方块转换器设置到指定键。
     * <p>原文：
     * Sets a block transformer to a key.
     *
     * @param key 键
     * <p>原文：the key
     * @param transformer 方块转换器
     * <p>原文：Sets a block transformer to a key.
     * <p>原文：the block transformer
     */
    public void setBlockTransformer(@NotNull NamespacedKey key, @NotNull BlockTransformer transformer) {
        Preconditions.checkArgument(key != null, "NamespacedKey cannot be null");
        Preconditions.checkArgument(transformer != null, "BlockTransformer cannot be null");
        this.blockTransformers.put(key, transformer);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除一个方块转换器。
     * <p>原文：
     * Removes a block transformer.
     *
     * @param key 方块转换器的键
     * <p>原文：Removes a block transformer.
     * <p>原文：the key of the block transformer
     */
    public void removeBlockTransformer(@NotNull NamespacedKey key) {
        Preconditions.checkArgument(key != null, "NamespacedKey cannot be null");
        this.blockTransformers.remove(key);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除所有方块转换器。
     * <p>原文：
     * Removes all block transformers.
     */
    public void clearBlockTransformers() {
        this.blockTransformers.clear();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有方块转换器的不可修改映射。
     * <p>原文：
     * Gets all block transformers in an unmodifiable map.
     *
     * @return 方块转换器的映射
     * <p>原文：Gets all block transformers in a unmodifiable map.
     * <p>原文：the block transformers in a map
     */
    @NotNull
    public @Unmodifiable Map<NamespacedKey, BlockTransformer> getBlockTransformers() {
        return Collections.unmodifiableMap(this.blockTransformers);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过键获取实体转换器。
     * <p>原文：
     * Gets an entity transformer by key.
     *
     * @param key 实体转换器的键
     * <p>原文：the key of the entity transformer
     * @return 实体转换器或 null
     * <p>原文：Gets a entity transformer by key.
     * <p>原文：the entity transformer or {@code null}
     */
    @Nullable
    public EntityTransformer getEntityTransformer(@NotNull NamespacedKey key) {
        Preconditions.checkArgument(key != null, "NamespacedKey cannot be null");
        return this.entityTransformers.get(key);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将实体转换器设置到指定键。
     * <p>原文：
     * Sets an entity transformer to a key.
     *
     * @param key 键
     * <p>原文：the key
     * @param transformer 实体转换器
     * <p>原文：Sets a entity transformer to a key.
     * <p>原文：the entity transformer
     */
    public void setEntityTransformer(@NotNull NamespacedKey key, @NotNull EntityTransformer transformer) {
        Preconditions.checkArgument(key != null, "NamespacedKey cannot be null");
        Preconditions.checkArgument(transformer != null, "EntityTransformer cannot be null");
        this.entityTransformers.put(key, transformer);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除一个实体转换器。
     * <p>原文：
     * Removes an entity transformer.
     *
     * @param key 实体转换器的键
     * <p>原文：Removes a entity transformer.
     * <p>原文：the key of the entity transformer
     */
    public void removeEntityTransformer(@NotNull NamespacedKey key) {
        Preconditions.checkArgument(key != null, "NamespacedKey cannot be null");
        this.entityTransformers.remove(key);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除所有实体转换器。
     * <p>原文：
     * Removes all entity transformers.
     */
    public void clearEntityTransformers() {
        this.entityTransformers.clear();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有实体转换器的不可修改映射。
     * <p>原文：
     * Gets all entity transformers in an unmodifiable map.
     *
     * @return 实体转换器的映射
     * <p>原文：Gets all entity transformers in a unmodifiable map.
     * <p>原文：the entity transformers in a map
     */
    @NotNull
    public @Unmodifiable Map<NamespacedKey, EntityTransformer> getEntityTransformers() {
        return Collections.unmodifiableMap(this.entityTransformers);
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public enum Cause {
        COMMAND,
        WORLD_GENERATION,
        CUSTOM
    }
}
