package org.bukkit.entity;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示已点燃的TNT。
 * <p>原文：
 * Represents a Primed TNT.
 */
public interface TNTPrimed extends Explosive {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置TNT点燃后爆炸的刻数。
     * <p>原文：
     * Set the number of ticks until the TNT blows up after being primed.
     *
     * @param fuseTicks 引信刻数
     * <p>
     * 原文：Set the number of ticks until the TNT blows up after being primed.
     * <p>原文：The fuse ticks
     */
    public void setFuseTicks(int fuseTicks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此TNTPrimed实体爆炸的剩余刻数。
     * <p>原文：
     * Retrieve the number of ticks until the explosion of this TNTPrimed
     * entity
     *
     * @return 此TNTPrimed爆炸的剩余刻数
     * <p>
     * 原文：Retrieve the number of ticks until the explosion of this TNTPrimed
     * entity
     * <p>原文：the number of ticks until this TNTPrimed explodes
     */
    public int getFuseTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此已点燃TNT的来源。来源是指创建此已点燃TNT的实体。（例如：玩家使用打火石点燃TNT。）请注意，如果没有合适的来源，这可能为null。（例如由{@link org.bukkit.World#spawn(Location, Class)}方法创建。）
     * <p>
     * 如果此已点燃TNT所在的区块被卸载后重新加载，来源将变为null。来源实体可能无效，例如它已经死亡或被卸载。调用者应检查{@link Entity#isValid()}。
     * <p>原文：
     * Gets the source of this primed TNT. The source is the entity
     * responsible for the creation of this primed TNT. (I.E. player ignites
     * TNT with flint and steel.) Take note that this can be null if there is
     * no suitable source. (created by the {@link
     * org.bukkit.World#spawn(Location, Class)} method, for example.)
     * <p>
     * The source will become null if the chunk this primed TNT is in is
     * unloaded then reloaded. The source entity may be invalid if for example
     * it has since died or been unloaded. Callers should check
     * {@link Entity#isValid()}.
     *
     * @return 此已点燃TNT的来源
     * <p>
     * 原文：Gets the source of this primed TNT. The source is the entity
     * responsible for the creation of this primed TNT. (I.E. player ignites
     * TNT with flint and steel.) Take note that this can be null if there is
     * no suitable source. (created by the {@link
     * org.bukkit.World#spawn(Location, Class)} method, for example.)
     * <p>
     * The source will become null if the chunk this primed TNT is in is
     * unloaded then reloaded. The source entity may be invalid if for example
     * it has since died or been unloaded. Callers should check
     * {@link Entity#isValid()}.
     * <p>原文：the source of this primed TNT
     */
    @Nullable
    public Entity getSource();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此已点燃TNT的来源。
     *
     * 来源是指创建此已点燃TNT的实体。
     * <p>
     * 必须是{@link org.bukkit.entity.LivingEntity}的实例，否则将被设置为null。参数类型为{@link org.bukkit.entity.Entity}，以与{@link org.bukkit.entity.TNTPrimed#getSource()}方法保持一致。
     * <p>原文：
     * Sets the source of this primed TNT.
     *
     * The source is the entity responsible for the creation of this primed TNT.
     * <p>
     * Must be an instance of {@link org.bukkit.entity.LivingEntity} otherwise will
     * be set to null. The parameter is typed {@link
     * org.bukkit.entity.Entity} to be consistent with {@link
     * org.bukkit.entity.TNTPrimed#getSource()} method.
     *
     * @param source 此已点燃TNT的来源
     * <p>
     * 原文：Sets the source of this primed TNT.
     *
     * The source is the entity responsible for the creation of this primed TNT.
     * <p>
     * Must be instance of {@link org.bukkit.entity.LivingEntity} otherwise will
     * be set to null. The parameter is typed {@link
     * org.bukkit.entity.Entity} to be consistent with {@link
     * org.bukkit.entity.TNTPrimed#getSource()} method.
     * <p>原文：the source of this primed TNT
     */
    public void setSource(@Nullable Entity source);

    /**
     * Gets the source block location of the TNTPrimed
     *
     * @return the source block location the TNTPrimed was spawned from
     * @deprecated replaced by {@link Entity#getOrigin()}
     */
    @Deprecated
    default org.bukkit.Location getSourceLoc() {
        return this.getOrigin();
    }

    /**
     * Sets the visual block data of this
     * primed tnt.
     * <br>
     * The explosion of the tnt stays the
     * same and is not affected by this change.
     *
     * @param data the visual block data
     */
    void setBlockData(@org.jetbrains.annotations.NotNull org.bukkit.block.data.BlockData data);

    /**
     * Gets the visual block data of this
     * primed tnt.
     *
     * @return the visual block data
     */
    @org.jetbrains.annotations.NotNull
    org.bukkit.block.data.BlockData getBlockData();
}
