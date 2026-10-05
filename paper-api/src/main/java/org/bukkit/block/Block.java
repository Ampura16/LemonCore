package org.bukkit.block;

import java.util.Collection;
import org.bukkit.Chunk;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Translatable;
import org.bukkit.World;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.Metadatable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.bukkit.util.VoxelShape;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个方块.
 * <p>
 * 这是一种动态的对象, 在同一个世界的同一个位置只可以存在一个方块.
 * <p>
 * 方块的一个实例可能会根据你对这个方块的一些操作而改变, 可以使用block.getState()来获取一个静态的, 不会被修改的Block对象.
 * <p>
 * 需要注意的是, 在世界生成的过程中调用这个类可能是不安全的, 比如BlockPhysicsEvent事件!!!!
 * <p>原文：
 * Represents a block. This is a live object, and only one Block may exist for
 * any given location in a world. The state of the block may change
 * concurrently to your own handling of it; use block.getState() to get a
 * snapshot state of a block which will not be modified.
 *
 * <br>
 * Note that parts of this class which require access to the world at large
 * (i.e. lighting and power) may not be able to be safely accessed during world
 * generation when used in cases like BlockPhysicsEvent!!!!
 */
public interface Block extends Metadatable, Translatable, net.kyori.adventure.translation.Translatable { // Paper - translatable

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块的元数据.
     * <p>原文：
     * Gets the metadata for this block
     *
     * @return 方块元数据
     * <p>原文：block specific metadata
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2", forRemoval = true)
    byte getData();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块的完整方块数据.
     * <p>原文：
     * Gets the complete block data for this block
     *
     * @return 方块数据
     * <p>原文：block specific data
     */
    @NotNull
    BlockData getBlockData();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以此方块为基点, 在指定的偏移量上获取方块.
     * <p>原文：
     * Gets the block at the given offsets
     *
     * @param modX X-coordinate offset
     * @param modY Y-coordinate offset
     * @param modZ Z-coordinate offset
     * @return 指定偏移量位置上的方块
     * <p>原文：Block at the given offsets
     */
    @NotNull
    Block getRelative(int modX, int modY, int modZ);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块某一面上紧邻的方块.
     * <p>
     * 此方法等同于getRelative(face, 1).
     * <p>原文：
     * Gets the block at the given face
     * <p>
     * This method is equal to getRelative(face, 1)
     *
     * @param face 方块的哪一面
     * <p>原文：Face of this block to return
     * @return 此面上紧邻的方块
     * <p>原文：Block at the given face
     * @see #getRelative(BlockFace, int)
     */
    @NotNull
    Block getRelative(@NotNull BlockFace face);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块某一面上指定距离的方块.
     * <p>
     * 一个例子, 如果我要在一个方块的上面3格放置一个水方块.
     * <pre>
     * Block block = world.getBlockAt(100, 100, 100); //获取到方块
     * Block shower = block.getRelative(BlockFace.UP, 2); //获取这个方块UP(上)面的2格的方块
     * shower.setType(Material.WATER); //设置这个方块为水
     * </pre>
     * <p>原文：
     * Gets the block at the given distance of the given face
     * <p>
     * For example, the following method places water at 100,102,100; two
     * blocks above 100,100,100.
     *
     * <pre>
     * Block block = world.getBlockAt(100, 100, 100);
     * Block shower = block.getRelative(BlockFace.UP, 2);
     * shower.setType(Material.WATER);
     * </pre>
     *
     * @param face 方块的哪一面
     * <p>原文：Face of this block to return
     * @param distance 距离
     * <p>原文：Distance to get the block at
     * @return 位于指定朝向指定距离的方块
     * <p>原文：Block at the given face
     */
    @NotNull
    Block getRelative(@NotNull BlockFace face, int distance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块的材质种类.
     * <p>原文：
     * Gets the type of this block
     *
     * @return 方块类型
     * <p>原文：block type
     */
    @NotNull
    Material getType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块的发光的亮度等级 (0-15).
     * <p>
     * 译注: 如果这个方块不发光则返回0.
     * <p>原文：
     * Gets the light level between 0-15
     *
     * @return 亮度等级
     * <p>原文：light level
     */
    byte getLightLevel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取从天空中照到此方块上的光照亮度等级.
     * <p>
     * 会忽略掉来自方块(火把、萤石等)的光源.
     * <p>原文：
     * Get the amount of light at this block from the sky.
     * <p>
     * Any light given from other sources (such as blocks like torches) will
     * be ignored.
     *
     * @return 光照亮度等级
     * <p>原文：Sky light level
     */
    byte getLightFromSky();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取从方块照到这个方块上的光源亮度等级.
     * <p>
     * 会忽略掉来自非方块(太阳光等)的光源.
     * <p>原文：
     * Get the amount of light at this block from nearby blocks.
     * <p>
     * Any light given from other sources (such as the sun) will be ignored.
     *
     * @return 光源亮度等级
     * <p>原文：Block light level
     */
    byte getLightFromBlocks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块所在的世界.
     * <p>原文：
     * Gets the world which contains this Block
     *
     * @return World containing this block
     */
    @NotNull
    World getWorld();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块的X轴坐标.
     * <p>原文：
     * Gets the x-coordinate of this block
     *
     * @return x-coordinate
     */
    int getX();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块的Y轴坐标.
     * <p>原文：
     * Gets the y-coordinate of this block
     *
     * @return y-coordinate
     */
    int getY();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块的Z轴坐标.
     * <p>原文：
     * Gets the z-coordinate of this block
     *
     * @return z-coordinate
     */
    int getZ();

    // Paper start
    /**
     * Returns this block's coordinates packed into a long value.
     * Computed via: {@code Block.getBlockKey(this.getX(), this.getY(), this.getZ())}
     * @see Block#getBlockKey(int, int, int)
     * @return This block's x, y, and z coordinates packed into a long value
     * @deprecated see {@link #getBlockKey(int, int, int)}
     */
    @Deprecated(since = "1.18.1")
    public default long getBlockKey() {
        return Block.getBlockKey(this.getX(), this.getY(), this.getZ());
    }

    /**
     * Returns the specified block coordinates packed into a long value
     * <p>
     * The return value can be computed as follows:
     * <br>
     * {@code long value = ((long)x & 0x7FFFFFF) | (((long)z & 0x7FFFFFF) << 27) | ((long)y << 54);}
     * </p>
     *
     * <p>
     * And may be unpacked as follows:
     * <br>
     * {@code int x = (int) ((packed << 37) >> 37);}
     * <br>
     * {@code int y = (int) (packed >> 54);}
     * <br>
     * {@code int z = (int) ((packed << 10) >> 37);}
     * </p>
     *
     * @return This block's x, y, and z coordinates packed into a long value
     * @deprecated only encodes y block ranges from -512 to 511 and represents an already changed implementation detail
     */
    @Deprecated(since = "1.18.1")
    public static long getBlockKey(int x, int y, int z) {
        return ((long)x & 0x7FFFFFF) | (((long)z & 0x7FFFFFF) << 27) | ((long)y << 54);
    }

    /**
     * Returns the x component from the packed value.
     * @param packed The packed value, as computed by {@link Block#getBlockKey(int, int, int)}
     * @see Block#getBlockKey(int, int, int)
     * @return The x component from the packed value.
     * @deprecated see {@link #getBlockKey(int, int, int)}
     */
    @Deprecated(since = "1.18.1")
    public static int getBlockKeyX(long packed) {
        return (int) ((packed << 37) >> 37);
    }

    /**
     * Returns the y component from the packed value.
     * @param packed The packed value, as computed by {@link Block#getBlockKey(int, int, int)}
     * @see Block#getBlockKey(int, int, int)
     * @return The y component from the packed value.
     * @deprecated see {@link #getBlockKey(int, int, int)}
     */
    @Deprecated(since = "1.18.1")
    public static int getBlockKeyY(long packed) {
        return (int) (packed >> 54);
    }

    /**
     * Returns the z component from the packed value.
     * @param packed The packed value, as computed by {@link Block#getBlockKey(int, int, int)}
     * @see Block#getBlockKey(int, int, int)
     * @return The z component from the packed value.
     * @deprecated see {@link #getBlockKey(int, int, int)}
     */
    @Deprecated(since = "1.18.1")
    public static int getBlockKeyZ(long packed) {
        return (int) ((packed << 10) >> 37);
    }
    // Paper end

    // Paper start - add isValidTool
    /**
     * Checks if the itemstack is a valid tool to
     * break the block with
     *
     * @param itemStack The (tool) itemstack
     * @return whether the block will drop items
     * @deprecated partially replaced by {@link Block#isPreferredTool(ItemStack)}
     */
    @Deprecated(since = "1.21", forRemoval = true) // Paper
    boolean isValidTool(@NotNull ItemStack itemStack);
    // Paper end - add isValidTool

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块的位置.
     * <p>原文：
     * Gets the Location of the block
     *
     * @return 方块的位置
     * <p>原文：Location of block
     */
    @NotNull
    Location getLocation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 存储此方块的位置到到给定的位置实例中.
     * <p>
     * 如果给定的位置实例为null, 则将不进行任何操作并返回null.
     * <p>
     * 译注:下面是译者的一个例子
     * <pre>
     * Location loc = new Location(World, 15, 255, 14);
     * Block block = World2.getBlockAt(28, 25, -18);
     * loc = block.getLocation(loc)
     * // 此时，loc的值为(World2, 28, 25, -18) 基本等效于 loc = block.getLocation()
     * </pre>
     * <p>原文：
     * Stores the location of the block in the provided Location object.
     * <p>
     * If the provided Location is null this method does nothing and returns
     * null.
     *
     * @param loc 要复制入的位置实例
     * <p>原文：the location to copy into
     * @return 给定的位置实例/null
     * <p>原文：The Location object provided or null
     */
    @Contract("null -> null; !null -> !null")
    @Nullable
    Location getLocation(@Nullable Location loc);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块所在的区块.
     * <p>原文：
     * Gets the chunk which contains this block
     *
     * @return 方块所在的区块
     * <p>原文：Containing Chunk
     */
    @NotNull
    Chunk getChunk();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此方块的BlockData.
     * <p>原文：
     * Sets the complete data for this block
     *
     * @param data 新方块数据
     * <p>原文：new block specific data
     */
    void setBlockData(@NotNull BlockData data);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置一个方块的BlockData, 并决定是否应用重力 (译者注: 更新方块的意思，如沙不掉落).
     * <br>
     * 请注意, applyPhysics = false 有时并不安全. 只有你需要避免周围方块的更新才应该使用这个参数.
     * 例如在创建一个 {@link Bisected} 方块时或者在使用自定义的 BlockPopulator 防止触发无限连锁更新的时候.
     * <p>
     * 不要使用这个方法来在一些 “不可能放置方块的地方” 放置方块. 即使可以成功放置, 这些方块也会在之后被移除.
     * 如果把大量这种方块放置在很接近的地方可能会使服务器物理引擎过载奔溃.
     * <p>原文：
     * Sets the complete data for this block
     *
     * <br>
     * Note that applyPhysics = false is not in general safe. It should only be
     * used when you need to avoid triggering a physics update of neighboring
     * blocks, for example when creating a {@link Bisected} block. If you are
     * using a custom populator, then this parameter may also be required to
     * prevent triggering infinite chunk loads on border blocks. This method
     * should NOT be used to "hack" physics by placing blocks in impossible
     * locations. Such blocks are liable to be removed on various events such as
     * world upgrades. Furthermore setting large amounts of such blocks in close
     * proximity may overload the server physics engine if an update is
     * triggered at a later point. If this occurs, the resulting behavior is
     * undefined.
     *
     * @param data 新方块数据
     * <p>原文：new block specific data
     * @param applyPhysics false 以取消方块的物理规则
     * <p>原文：false to cancel physics from the changed block
     */
    void setBlockData(@NotNull BlockData data, boolean applyPhysics);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个方块的材质种类.
     * <p>原文：
     * Sets the type of this block
     *
     * @param type 材质种类
     * <p>原文：Material to change this block to
     */
    void setType(@NotNull Material type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置一个方块的材质种类, 并决定是否应用重力 (译者注: 更新方块的意思, 如沙不掉落).
     * <br>
     * 请注意, applyPhysics = false 有时并不安全. 只有你需要避免周围方块的更新才应该使用这个参数.
     * 例如在创建一个 {@link Bisected} 方块时或者在使用自定义的 BlockPopulator 防止触发无限连锁更新的时候.
     * <p>
     * 不要使用这个方法来在一些 “不可能放置方块的地方” 放置方块. 即使可以成功放置, 这些方块也会在之后被移除.
     * 如果把大量这种方块放置在很接近的地方可能会使服务器物理引擎过载奔溃.
     * <p>原文：
     * Sets the type of this block
     *
     * <br>
     * Note that applyPhysics = false is not in general safe. It should only be
     * used when you need to avoid triggering a physics update of neighboring
     * blocks, for example when creating a {@link Bisected} block. If you are
     * using a custom populator, then this parameter may also be required to
     * prevent triggering infinite chunk loads on border blocks. This method
     * should NOT be used to "hack" physics by placing blocks in impossible
     * locations. Such blocks are liable to be removed on various events such as
     * world upgrades. Furthermore setting large amounts of such blocks in close
     * proximity may overload the server physics engine if an update is
     * triggered at a later point. If this occurs, the resulting behavior is
     * undefined.
     *
     * @param type 材质种类
     * <p>原文：Material to change this block to
     * @param applyPhysics false 以取消方块的物理规则
     * <p>原文：False to cancel physics on the changed block.
     */
    void setType(@NotNull Material type, boolean applyPhysics);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本方块的哪一面朝向给定的方块.
     * <p>
     * 例如:
     * <pre>{@code
     * Block current = world.getBlockAt(100, 100, 100); // 本方块
     * Block target = world.getBlockAt(100, 101, 100); // 目标方块
     *
     * current.getFace(target) == BlockFace.Up; // 朝上
     * }</pre>
     * <br>
     * 如果给定方块不与本方块相连接, 将可能返回 null.
     * <p>原文：
     * Gets the face relation of this block compared to the given block.
     * <p>
     * For example:
     * <pre>{@code
     * Block current = world.getBlockAt(100, 100, 100);
     * Block target = world.getBlockAt(100, 101, 100);
     *
     * current.getFace(target) == BlockFace.Up;
     * }</pre>
     * <br>
     * If the given block is not connected to this block, null may be returned
     *
     * @param block 要比较的方块
     * <p>原文：Block to compare against this block
     * @return 本方块相对于给定方块的朝向, 或为 null
     * <p>原文：BlockFace of this block which has the requested block, or null
     */
    @Nullable
    BlockFace getFace(@NotNull Block block);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 捕获本方块当前的状态. 你可以将此状态转换为任意可接受的类型的状态,
     * 例如, 转为 {@link Furnace} 或 {@link Sign}.
     * <p>
     * 返回的对象将永不更新 (注:可以理解为方块在某一时刻的状态的快照),
     * 并且你不能保证(举个例子)在你捕获此告示牌状态后此告示牌依然是告示牌.
     * <p>原文：
     * Captures the current state of this block. You may then cast that state
     * into any accepted type, such as Furnace or Sign.
     * <p>
     * The returned object will never be updated, and you are not guaranteed
     * that (for example) a sign is still a sign after you capture its state.
     *
     * @return 本方块当前的状态的 BlockState 对象
     * <p>原文：BlockState with the current state of this block.
     */
    @NotNull
    BlockState getState();

    // Paper start
    /**
     * @see #getState() optionally disables use of snapshot, to operate on real block data
     * @param useSnapshot if this block is a block entity, should we create a full copy of the BlockEntity
     * @return BlockState with the current state of this block
     */
    @NotNull
    BlockState getState(boolean useSnapshot);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回方块所在位置的生物群系.
     * <p>原文：
     * Returns the biome that this block resides in
     *
     * @return 生物群系
     * <p>原文：Biome type containing this block
     * @see #getComputedBiome()
     */
    @NotNull
    Biome getBiome();

    // Paper start
    /**
     * Gets the computed biome at the location of this Block.
     *
     * @return computed biome at the location of this Block.
     * @see org.bukkit.RegionAccessor#getComputedBiome(int, int, int)
     */
    @NotNull
    Biome getComputedBiome();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置方块所在位置的生物群系.
     * <p>原文：
     * Sets the biome that this block resides in
     *
     * @param bio 新的生物群系
     * <p>原文：new Biome type for this block
     */
    void setBiome(@NotNull Biome bio);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回方块是否被红石充能.
     * <p>原文：
     * Returns true if the block is being powered by Redstone.
     *
     * @return 是否被充能
     * <p>原文：True if the block is powered.
     */
    boolean isBlockPowered();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回方块是否被红石间接充能.
     * <p>原文：
     * Returns true if the block is being indirectly powered by Redstone.
     *
     * @return 是否被间接充能
     * <p>原文：True if the block is indirectly powered.
     */
    boolean isBlockIndirectlyPowered();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回方块的其中一面是否被红石充能.
     * <p>原文：
     * Returns true if the block face is being powered by Redstone.
     *
     * @param face 方块的面
     * <p>原文：The block face
     * @return 此面是否被充能
     * <p>原文：True if the block face is powered.
     */
    boolean isBlockFacePowered(@NotNull BlockFace face);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回方块的其中一面是否被红石间接充能.
     * <p>原文：
     * Returns true if the block face is being indirectly powered by Redstone.
     *
     * @param face 方块的面
     * <p>原文：The block face
     * @return 此面是否被间接充能
     * <p>原文：True if the block face is indirectly powered.
     */
    boolean isBlockFaceIndirectlyPowered(@NotNull BlockFace face);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回提供给方块某一面的红石充能等级.
     * <p>原文：
     * Returns the redstone power being provided to this block face
     *
     * @param face 要查询的方块的面或 BlockFace.SELF 查询方块本身
     * <p>原文：the face of the block to query or BlockFace.SELF for the
     *     block itself
     * @return 红石充能等级
     * <p>原文：The power level.
     */
    int getBlockPower(@NotNull BlockFace face);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回提供给此方块的红石充能等级.
     * <p>原文：
     * Returns the redstone power being provided to this block
     *
     * @return 红石充能等级
     * <p>原文：The power level.
     */
    int getBlockPower();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测此方块是否为空.
     * <p>
     * 当 {@link #getType()} 返回 {@link Material#AIR} 时认为此方块为空.
     * <p>原文：
     * Checks if this block is empty.
     * <p>
     * A block is considered empty when {@link #getType()} returns {@link
     * Material#AIR}.
     *
     * @return 此方块是否为空
     * <p>原文：true if this block is empty
     */
    boolean isEmpty();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测方块是否为流体.
     * 当 {@link #getType()} 返回 {@link Material#WATER} 或 {@link Material#LAVA} 时认为此方块为流体.
     * <p>原文：
     * Checks if this block is liquid.
     * <p>
     * A block is considered liquid when {@link #getType()} returns {@link
     * Material#WATER} or {@link Material#LAVA}.
     *
     * @return 方块是否为流体
     * <p>原文：true if this block is liquid
     */
    boolean isLiquid();

    // Paper start
    /**
     * Check if this block is solid
     * <p>
     * Determined by Minecraft, typically a block a player can use to place a new block to build things.
     * An example of a non buildable block would be liquids, flowers, or fire
     *
     * @return true if block is buildable
     */
    boolean isBuildable();
    /**
     * Check if this block is burnable
     * <p>
     * Determined by Minecraft, typically a block that fire can destroy (Wool, Wood)
     *
     * @return true if block is burnable
     */
    boolean isBurnable();
    /**
     * Checks if this block can be immediately replaced by another block, such as placing a new block in air or tall grass.
     * @return true if block is replaceable
     */
    boolean isReplaceable();
    /**
     * Check if this block is solid
     * <p>
     * Determined by Minecraft, typically a block a player can stand on and can't be passed through.
     *
     * This API is faster than accessing Material#isSolid as it avoids a material lookup and switch statement.
     * @return true if block is solid
     */
    boolean isSolid();

    /**
     * Checks if this block is collidable.
     *
     * @return true if collidable
     */
    boolean isCollidable();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取方块所在生物群系的环境温度.
     * <p>
     * 如果你要避免方块高度对温度的影响, 请使用{@link World#getTemperature(int, int)}.
     * <p>原文：
     * Gets the temperature of this block.
     *
     * @return 方块温度
     * <p>原文：Temperature of this block
     */
    double getTemperature();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取方块所在生物群系的环境湿度.
     * <p>原文：
     * Gets the humidity of the biome of this block
     *
     * @return 方块所在生物群系的环境湿度
     * <p>原文：Humidity of this block
     */
    double getHumidity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回当此方块被活塞推动时的反应.
     * <p>原文：
     * Returns the reaction of the block when moved by a piston
     *
     * @return 方块对活塞的反应
     * <p>原文：reaction
     */
    @NotNull
    PistonMoveReaction getPistonMoveReaction();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 破坏此方块并生成掉落物, 就像是玩家不使用工具时挖掘方块一样.
     * <p>原文：
     * Breaks the block and spawns items as if a player had digged it regardless
     * of the tool.
     *
     * @return 若方块成功被破坏则返回true
     * <p>原文：true if the block was destroyed
     */
    boolean breakNaturally();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 破坏此方块并生成掉落物, 就像是玩家手持工具挖掘方块一样.
     * <p>原文：
     * Breaks the block and spawns items as if a player had digged it with a
     * specific tool
     *
     * @param tool 使用何种工具挖掘方块
     * <p>原文：The tool or item in hand used for digging
     * @return true 若方块成功被破坏则返回true
     * <p>原文：true if the block was destroyed
     */
    boolean breakNaturally(@Nullable ItemStack tool);

    // Paper start
    /**
     * Breaks the block and spawns item drops as if a player had broken it
     *
     * @param triggerEffect Play the block break particle effect and sound
     * @return true if the block was destroyed
     * @see #breakNaturally(boolean, boolean) to trigger exp drops
     */
    default boolean breakNaturally(boolean triggerEffect) {
        return this.breakNaturally(triggerEffect, false);
    }

    /**
     * Breaks the block and spawns item drops as if a player had broken it
     *
     * @param triggerEffect Play the block break particle effect and sound
     * @param dropExperience drop exp if the block normally does so
     * @return true if the block was destroyed
     */
    boolean breakNaturally(boolean triggerEffect, boolean dropExperience);

    /**
     * Breaks the block and spawns item drops as if a player had broken it
     * with a specific tool
     *
     * @param tool The tool or item in hand used for digging
     * @param triggerEffect Play the block break particle effect and sound
     * @return true if the block was destroyed
     * @see #breakNaturally(ItemStack, boolean, boolean) to trigger exp drops
     */
    default boolean breakNaturally(@NotNull ItemStack tool, boolean triggerEffect) {
        return this.breakNaturally(tool, triggerEffect, false);
    }

    /**
     * Breaks the block and spawns item drops as if a player had broken it
     * with a specific tool
     *
     * @param tool The tool or item in hand used for digging
     * @param triggerEffect Play the block break particle effect and sound
     * @param dropExperience drop exp if the block normally does so
     * @return true if the block was destroyed
     */
    boolean breakNaturally(@NotNull ItemStack tool, boolean triggerEffect, boolean dropExperience);

    /**
     * Breaks the block and spawns item drops as if a player had broken it
     * with a specific tool
     *
     * @param tool The tool or item in hand used for digging
     * @param triggerEffect Play the block break particle effect and sound
     * @param dropExperience drop exp if the block normally does so
     * @param forceEffect Forces the break effect to be triggered even if the tool is not the correct tool for the block
     * @return true if the block was destroyed
     */
    boolean breakNaturally(@NotNull ItemStack tool, boolean triggerEffect, boolean dropExperience, boolean forceEffect);

    /**
     * Causes the block to be ticked, this is different from {@link Block#randomTick()},
     * in that it is usually scheduled to occur, for example
     * redstone components being activated, sand falling, etc.
     * <p>
     * This method may directly fire events relating to block ticking.
     *
     * @see #fluidTick()
     */
    void tick();

    /**
     * Causes the fluid to be ticked, this is different from {@link Block#randomTick()},
     * in that it is usually scheduled to occur, for example
     * causing waterlogged blocks to spread.
     * <p>
     * This method may directly fire events relating to fluid ticking.
     *
     * @see #tick()
     */
    void fluidTick();

    /**
     * Causes the block to be ticked randomly.
     * This has a chance to execute naturally if {@link BlockData#isRandomlyTicked()} is true.
     * <p>
     * For certain blocks, this behavior may be the same as {@link Block#tick()}.
     * <p>
     * This method may directly fire events relating to block random ticking.
     *
     * @see #tick()
     * @see #fluidTick()
     */
    void randomTick();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 模拟使用骨粉向本方块施肥 (若可能).
     * <p>原文：
     * Simulate bone meal application to this block (if possible).
     *
     * @param face 将骨粉施予方块的哪一面
     * <p>原文：the face on which bonemeal should be applied
     * @return 成功施肥则返回true
     * <p>原文：true if the block was bonemealed, false otherwise
     */
    boolean applyBoneMeal(@NotNull BlockFace face);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回当破坏此方块时掉落的物品.
     * <p>原文：
     * Returns a list of items which could drop by destroying this block.
     * <p>
     * The items are not guaranteed to be consistent across multiple calls to this
     * method as this just uses the block type's loot table.
     *
     * @return 方块掉落物列表
     * <p>原文：a list of dropped items for this type of block
     */
    @NotNull
    Collection<ItemStack> getDrops();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回当使用特定工具破坏此方块时掉落的物品.
     * <p>原文：
     * Returns a list of items which could drop by destroying this block with
     * a specific tool.
     * <p>
     * The items are not guaranteed to be consistent across multiple calls to this
     * method as this just uses the block type's loot table.
     *
     * @param tool 使用何种工具挖掘方块
     * <p>原文：The tool or item in hand used for digging
     * @return 方块掉落物列表
     * <p>原文：a list of dropped items for this type of block
     */
    @NotNull
    Collection<ItemStack> getDrops(@Nullable ItemStack tool);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回当某实体使用某工具破坏此方块时掉落的物品.
     * <p>原文：
     * Returns a list of items which could drop by the entity destroying this
     * block with a specific tool.
     * <p>
     * The items are not guaranteed to be consistent across multiple calls to this
     * method as this just uses the block type's loot table.
     *
     * @param tool 实体使用的工具
     * <p>原文：The tool or item in hand used for digging
     * @param entity 破坏此方块的实体
     * <p>原文：the entity destroying the block
     * @return 方块掉落物列表
     * <p>原文：a list of dropped items for this type of block
     */
    @NotNull
    Collection<ItemStack> getDrops(@Nullable ItemStack tool, @Nullable Entity entity); // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回给定物品是否是破坏此方块的首选工具.
     * <p>原文：
     * Returns if the given item is a preferred choice to break this Block.
     *
     * In some cases this determines if a block will drop anything or extra
     * loot.
     *
     * @param tool 用于破坏此方块的工具或物品
     * <p>原文：The tool or item used for breaking this block
     * @return 如果该工具是破坏此方块的首选则返回true
     * <p>原文：true if the tool is preferred for breaking this block.
     */
    boolean isPreferredTool(@NotNull ItemStack tool);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定玩家破坏此方块的速度, 考虑工具、药水效果、玩家是否在水中、附魔等因素.
     * <p>原文：
     * Gets the speed at which the given player would break this block, taking
     * into account tools, potion effects, whether or not the player is in
     * water, enchantments, etc.
     *
     * The returned value is the amount of progress made in breaking the block
     * each tick. When the total breaking progress reaches {@code 1.0f}, the
     * block is broken. Note that the break speed can change in the course of
     * breaking a block, e.g. if a potion effect is applied or expires, or the
     * player jumps/enters water.
     *
     * @param player 破坏方块的玩家
     * <p>原文：player breaking the block
     * @return 玩家破坏此方块的速度
     * <p>原文：the speed at which the player breaks this block
     */
    float getBreakSpeed(@NotNull Player player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测能否自由通过此方块.
     * <p>
     * 如果方块没有可阻碍玩家穿过的可碰撞部分, 就可自由通过此方块.
     * <p>
     * 例如: 高草丛、各种花、告示牌等都是可通过的,
     * 但开着的门、栅栏门、活板门等不可, 因为它们依然有可碰撞部分.
     * <p>原文：
     * Checks if this block is passable.
     * <p>
     * A block is passable if it has no colliding parts that would prevent
     * players from moving through it.
     * <p>
     * Examples: Tall grass, flowers, signs, etc. are passable, but open doors,
     * fence gates, trap doors, etc. are not because they still have parts that
     * can be collided with.
     *
     * @return 能否自由通过此方块
     * <p>原文：<code>true</code> if passable
     */
    boolean isPassable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 对此特定方块执行射线追踪, 使用其精确的碰撞形状检查碰撞情况.
     * <p>原文：
     * Performs a ray trace that checks for collision with this specific block
     * in its current state using its precise collision shape.
     *
     * @param start 起始位置
     * <p>原文：the start location
     * @param direction 射线方向
     * <p>原文：the ray direction
     * @param maxDistance 最大距离
     * <p>原文：the maximum distance
     * @param fluidCollisionMode 流体碰撞模式
     * <p>原文：the fluid collision mode
     * @return 射线追踪命中结果, 如果没有命中则返回 <code>null</code>
     * <p>原文：the ray trace hit result, or <code>null</code> if there is no hit
     */
    @Nullable
    RayTraceResult rayTrace(@NotNull Location start, @NotNull Vector direction, double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块的大致边界框.
     * <p>原文：
     * Gets the approximate bounding box for this block.
     * <p>
     * This isn't exact as some blocks {@link org.bukkit.block.data.type.Stairs}
     * contain many bounding boxes to establish their complete form.
     *
     * Also, the box may not be exactly the same as the collision shape (such as
     * cactus, which is 16/16 of a block with 15/16 collisional bounds).
     *
     * This method will return an empty bounding box if the geometric shape of
     * the block is empty (such as air blocks).
     *
     * @return 方块的大致边界框
     * <p>原文：the approximate bounding box of the block
     */
    @NotNull
    BoundingBox getBoundingBox();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块的碰撞形状.
     * <p>原文：
     * Gets the collision shape of this block.
     *
     * @return 表示此方块碰撞形状的 {@link VoxelShape}
     * <p>原文：a {@link VoxelShape} representing the collision shape of this
     * block.
     */
    @NotNull
    VoxelShape getCollisionShape();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此方块是否是指定方块数据的有效放置位置.
     * <p>原文：
     * Checks if this block is a valid placement location for the specified
     * block data.
     *
     * @param data 要检查的方块数据
     * <p>原文：the block data to check
     * @return 如果可以在此处放置方块数据则返回 <code>true</code>
     * <p>原文：<code>true</code> if the block data can be placed here
     */
    boolean canPlace(@NotNull BlockData data);

    // Paper start
    /**
     * Gets the {@link com.destroystokyo.paper.block.BlockSoundGroup} for this block.
     * <p>
     * This object contains the block, step, place, hit, and fall sounds.
     *
     * @return the sound group for this block
     * @deprecated use {@link #getBlockSoundGroup()}
     */
    @NotNull
    @Deprecated(forRemoval = true, since = "1.18.2")
    com.destroystokyo.paper.block.BlockSoundGroup getSoundGroup();

    /**
     * Gets the {@link org.bukkit.SoundGroup} for this block.
     *
     * @return the sound group for this block
     */
    @NotNull org.bukkit.SoundGroup getBlockSoundGroup();

    /**
     * @deprecated use {@link #translationKey()}
     */
    @NotNull
    @Deprecated(forRemoval = true)
    String getTranslationKey();
    // Paper end

    // Paper start - destroy speed API
    /**
     * Gets the speed at which this block will be destroyed by a given {@link ItemStack}
     * <p>
     * Default value is 1.0
     *
     * @param itemStack {@link ItemStack} used to mine this Block
     * @return the speed that this Block will be mined by the given {@link ItemStack}
     */
    default float getDestroySpeed(final @NotNull ItemStack itemStack) {
        return this.getBlockData().getDestroySpeed(itemStack);
    }

    /**
     * Gets the speed at which this block will be destroyed by a given {@link ItemStack}
     * <p>
     * Default value is 1.0
     *
     * @param itemStack {@link ItemStack} used to mine this Block
     * @param considerEnchants true to look at enchants on the itemstack
     * @return the speed that this Block will be mined by the given {@link ItemStack}
     */
    default float getDestroySpeed(@NotNull ItemStack itemStack, boolean considerEnchants) {
        return this.getBlockData().getDestroySpeed(itemStack, considerEnchants);
    }
    // Paper end - destroy speed API

    /**
     * Checks if the block can suffocate.
     *
     * @return {@code true} if the block can suffocate
     */
    boolean isSuffocating();
}
