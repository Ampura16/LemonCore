package org.bukkit.block;

import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.block.structure.UsageMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BlockVector;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表结构方块, 其可从文件中加载方块或保存方块到文件.
 * 它们仅可被 OP 使用, 且生存模式下无法获取.
 * <p>原文：
 * Represents a structure block that can save and load blocks from a file. They
 * can only be used by OPs, and are not obtainable in survival.
 */
public interface Structure extends TileState {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取结构名.
     * <p>原文：
     * The name of this structure.
     *
     * @return 结构名
     * <p>原文：structure name
     */
    @NotNull
    String getStructureName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置结构名. 名称区分大小写.
     * {@link UsageMode#SAVE 储存模式}下的结构方块存储的结构名必须与{@link UsageMode#CORNER 角落模式}下的结构方块一致,
     * 否则尺寸计算将失败.
     * <p>原文：
     * Set the name of this structure. This is case-sensitive. The name of the
     * structure in the {@link UsageMode#SAVE} structure block MUST match the
     * name within the {@link UsageMode#CORNER} block or the size calculation
     * will fail.
     *
     * @param name 大小写敏感的结构名
     * <p>原文：the case-sensitive name of this structure
     */
    void setStructureName(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此结构的作者.
     * <p>原文：
     * Get the name of who created this structure.
     *
     * @return 此结构的作者
     * <p>原文：the name of whoever created this structure.
     */
    @NotNull
    String getAuthor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此结构的作者.
     * <p>原文：
     * Set the name of whoever created this structure.
     *
     * @param author 此结构的作者 (不可为空字符串或null)
     * <p>原文：whoever created this structure (not empty)
     */
    void setAuthor(@NotNull String author);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此结构的作者名为某个{@link LivingEntity}的名字.
     * <p>原文：
     * Set the name of whoever created this structure using a
     * {@link LivingEntity}.
     *
     * @param livingEntity 创造此结构的生物
     * <p>原文：the entity who created this structure
     */
    void setAuthor(@NotNull LivingEntity livingEntity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 基于此结构方块位置的(以其为原点的)结构轮廓相对位置. 对于任意方向, 允许的最大距离为48个方块.
     * <p>原文：
     * The relative position of the structure outline based on the position of
     * the structure block. Maximum allowed distance is 48 blocks in any
     * direction.
     *
     * @return 包含此结构与结构方块的相对距离的位置(向量)
     * <p>原文：a Location which contains the relative distance this structure is
     * from the structure block.
     */
    @NotNull
    BlockVector getRelativePosition();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置结构方块基于原点的相对位置 (起始位置). 对于任意方向, 允许的最大距离为48个方块.
     * <p>原文：
     * Set the relative position from the structure block. Maximum allowed
     * distance is 48 blocks in any direction.
     *
     * @param vector 包含相对位置坐标的{@link BlockVector}
     * <p>原文：the {@link BlockVector} containing the relative origin
     * coordinates of this structure.
     */
    void setRelativePosition(@NotNull BlockVector vector);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * (结构原点)与结构对角之间的距离. 结构最大尺寸为 48x48x48.
     * 当结构成功被计算时 (即处于最大允许距离之内), 将有白色边框包围这个结构.
     * <p>原文：
     * The distance to the opposite corner of this structure. The maximum
     * structure size is 48x48x48. When a structure has successfully been
     * calculated (i.e. it is within the maximum allowed distance) a white
     * border surrounds the structure.
     *
     * @return 包含此结构总尺寸的{@link BlockVector}
     * <p>原文：a {@link BlockVector} which contains the total size of the
     * structure.
     */
    @NotNull
    BlockVector getStructureSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此结构自原点的最大尺寸. 允许的最大尺寸为 48x48x48.
     * <p>原文：
     * Set the maximum size of this structure from the origin point. Maximum
     * allowed size is 48x48x48.
     *
     * @param vector 包含自原点起此结构尺寸的{@link BlockVector}
     * <p>原文：the {@link BlockVector} containing the size of this
     * structure, based off of the origin coordinates.
     */
    void setStructureSize(@NotNull BlockVector vector);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此结构方块的镜像方法.
     * <p>原文：
     * Sets the mirroring of the structure.
     *
     * @param mirror 镜像方法
     * <p>原文：the new mirroring method
     */
    void setMirror(@NotNull Mirror mirror);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此结构方块如何产生镜像.
     * <p>原文：
     * How this structure is mirrored.
     *
     * @return 结构方块的镜像方法
     * <p>原文：the current mirroring method
     */
    @NotNull
    Mirror getMirror();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此结构方块的旋转角度.
     * <p>原文：
     * Set how this structure is rotated.
     *
     * @param rotation 旋转角度
     * <p>原文：the new rotation
     */
    void setRotation(@NotNull StructureRotation rotation);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此结构方块的旋转角度.
     * <p>原文：
     * Get how this structure is rotated.
     *
     * @return 旋转角度
     * <p>原文：the new rotation
     */
    @NotNull
    StructureRotation getRotation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此结构方块的{@link UsageMode 使用模式}.
     * <p>原文：
     * Set the {@link UsageMode} of this structure block.
     *
     * @param mode 要设置的新模式
     * <p>原文：the new mode to set.
     */
    void setUsageMode(@NotNull UsageMode mode);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此结构方块所处的{@link UsageMode 使用模式}.
     * <p>原文：
     * Get the {@link UsageMode} of this structure block.
     *
     * @return 当前所处的{@link UsageMode 使用模式}
     * <p>原文：the mode this block is currently in.
     */
    @NotNull
    UsageMode getUsageMode();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 处于{@link UsageMode#SAVE 储存}模式时, 若本值为true, 保存结构时将忽略任何实体.
     * <br>
     * 处于{@link UsageMode#LOAD 加载}模式时, 若本值为true, 将忽略文件中存储的任何实体.
     * <p>原文：
     * While in {@link UsageMode#SAVE} mode, this will ignore any entities when
     * saving the structure.
     * <br>
     * While in {@link UsageMode#LOAD} mode this will ignore any entities that
     * were saved to file.
     *
     * @param ignoreEntities 要设置的 flag 值
     * <p>原文：the flag to set
     */
    void setIgnoreEntities(boolean ignoreEntities);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此结构方块是否忽略实体.
     * <p>原文：
     * Get if this structure block should ignore entities.
     *
     * @return 如果在合适的{@link UsageMode 使用模式}忽略实体则返回true
     * <p>原文：true if the appropriate {@link UsageMode} should ignore entities.
     */
    boolean isIgnoreEntities();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此结构方块是否显示所有空气方块.
     * <p>原文：
     * Set if the structure outline should show air blocks.
     *
     * @param showAir 是否显示所有空气方块
     * <p>原文：if the structure block should show air blocks
     */
    void setShowAir(boolean showAir);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此结构方块是否显示所有空气方块.
     * <p>原文：
     * Check if this structure block is currently showing all air blocks
     *
     * @return 是否显示所有空气方块
     * <p>原文：true if the structure block is showing all air blocks
     */
    boolean isShowAir();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此结构方块是否显示结构轮廓.
     * <p>原文：
     * Set if this structure box should show the bounding box.
     *
     * @param showBoundingBox 是否显示结构轮廓
     * <p>原文：if the structure box should be shown
     */
    void setBoundingBoxVisible(boolean showBoundingBox);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此结构方块是否显示结构轮廓.
     * <p>原文：
     * Get if this structure block is currently showing the bounding box.
     *
     * @return 是否显示结构轮廓
     * <p>原文：true if the bounding box is shown
     */
    boolean isBoundingBoxVisible();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此结构的完整性. 完整性数值必须处于 0.0 和 1.0 之间(闭区间).
     * 越小的完整性数值将导致加载结构时有越多的方块被移除.
     * 完整性和{@link #getSeed()}共同用于决定哪些方块被随机删除以模拟“衰变”.
     * <p>原文：
     * Set the integrity of the structure. Integrity must be between 0.0 and 1.0
     * Lower integrity values will result in more blocks being removed when
     * loading a structure. Integrity and {@link #getSeed()} are used together
     * to determine which blocks are randomly removed to mimic "decay."
     *
     * @param integrity 结构完整性
     * <p>原文：the integrity of this structure
     */
    void setIntegrity(float integrity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此结构的完整性.
     * <p>原文：
     * Get the integrity of this structure.
     *
     * @return 结构完整性
     * <p>原文：the integrity of this structure
     */
    float getIntegrity();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置用于决定结构方块加载时随机删除哪些方块的种子.
     * {@link #getIntegrity()} 和种子共同用于决定哪些方块被随机删除以模拟“衰变”.
     * <p>原文：
     * The seed used to determine which blocks will be removed upon loading.
     * {@link #getIntegrity()} and seed are used together to determine which
     * blocks are randomly removed to mimic "decay."
     *
     * @param seed 使用到的种子
     * <p>原文：the seed used to determine how many blocks will be removed
     */
    void setSeed(long seed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于决定加载结构时随机删除哪些方块的种子.
     * <p>原文：
     * The seed used to determine how many blocks are removed upon loading of
     * this structure.
     *
     * @return 使用到的种子
     * <p>原文：the seed used
     */
    long getSeed();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 仅在结构方块处于{@link UsageMode#DATA 数据模式}时应用. 元数据是可应用于结构标注位置的特定函数.
     * 查阅<a href="https://zh.minecraft.wiki/w/%E7%BB%93%E6%9E%84%E6%96%B9%E5%9D%97#%E6%95%B0%E6%8D%AE%E5%80%BC">Minecraft
     * Wiki</a>获取更多信息.
     * <p>原文：
     * Only applicable while in {@link UsageMode#DATA}. Metadata are specific
     * functions that can be applied to the structure location. Consult the
     * <a href="https://minecraft.wiki/w/Structure_Block#Data">Minecraft
     * wiki</a> for more information.
     *
     * @param metadata 要执行于标注位置的函数
     * <p>原文：the function to perform on the selected location
     */
    void setMetadata(@NotNull String metadata);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当此结构方块激活时将执行的函数元数据.
     * 查阅<a href="https://zh.minecraft.wiki/w/%E7%BB%93%E6%9E%84%E6%96%B9%E5%9D%97#%E6%95%B0%E6%8D%AE%E5%80%BC">Minecraft
     * Wiki</a>获取更多信息.
     * <p>原文：
     * Get the metadata function this structure block will perform when
     * activated. Consult the
     * <a href="https://minecraft.wiki/w/Structure_Block#Data">Minecraft
     * Wiki</a> for more information.
     *
     * @return 当此结构方块激活时将执行的函数
     * <p>原文：the function that will be performed when this block is activated
     */
    @NotNull
    String getMetadata();
}
