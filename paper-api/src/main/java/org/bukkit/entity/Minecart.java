package org.bukkit.entity;

import org.bukkit.GameRules;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表矿车实体.
 * <p>原文：
 * Represents a minecart entity.
 */
public interface Minecart extends Vehicle, io.papermc.paper.entity.Frictional { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置对矿车的伤害.
     * <p>原文：
     * Sets a minecart's damage.
     *
     * @param damage 超过40的伤害将"杀死"矿车
     * <p>原文：over 40 to "kill" a minecart
     */
    public void setDamage(double damage);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取对矿车的伤害.
     * <p>原文：
     * Gets a minecart's damage.
     *
     * @return 伤害值
     * <p>原文：The damage
     */
    public double getDamage();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取矿车的最大速率. 这是速率不是速度.
     * <p>原文：
     * Gets the maximum speed of a minecart. The speed is unrelated to the
     * velocity.
     *
     * @return 最大速率
     * <p>原文：The max speed
     */
    public double getMaxSpeed();

    /**
     * Sets the maximum speed of a minecart. Must be nonnegative. Default is
     * 0.4D or {@link GameRules#MAX_MINECART_SPEED}.
     *
     * @param speed The max speed
     */
    public void setMaxSpeed(double speed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此矿车在无乘客乘坐时是否会加快减速 (可理解为有更大的负的加速度).
     * <p>原文：
     * Returns whether this minecart will slow down faster without a passenger
     * occupying it
     *
     * @return 是否加快减速
     * <p>原文：Whether it decelerates faster
     */
    public boolean isSlowWhenEmpty();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此矿车在无乘客乘坐时是否会加快减速 (可理解为有更大的负的加速度).
     * <p>原文：
     * Sets whether this minecart will slow down faster without a passenger
     * occupying it
     *
     * @param slow 是否加快减速
     * <p>原文：Whether it will decelerate faster
     */
    public void setSlowWhenEmpty(boolean slow);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取飞行中的速率修正值. 用于处于半空中的矿车. 飞行中的矿车的速度每刻都会乘以该修正值.
     * <p>原文：
     * Gets the flying velocity modifier. Used for minecarts that are in
     * mid-air. A flying minecart's velocity is multiplied by this factor each
     * tick.
     *
     * @return 速率修正向量
     * <p>原文：The vector factor
     */
    @NotNull
    public Vector getFlyingVelocityMod();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置飞行中的速率修正值. 用于处于半空中的矿车. 飞行中的矿车的速度每刻都会乘以该修正值.
     * <p>原文：
     * Sets the flying velocity modifier. Used for minecarts that are in
     * mid-air. A flying minecart's velocity is multiplied by this factor each
     * tick.
     *
     * @param flying 速率修正向量
     * <p>原文：velocity modifier vector
     */
    public void setFlyingVelocityMod(@NotNull Vector flying);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取脱轨时的速率修正值. 用于处于地面上但不在铁轨上的矿车.
     * 脱轨矿车的速度每刻都会乘以该修正值.
     * <p>原文：
     * Gets the derailed velocity modifier. Used for minecarts that are on the
     * ground, but not on rails.
     * <p>
     * A derailed minecart's velocity is multiplied by this factor each tick.
     *
     * @return 脱轨时的可见速率
     * <p>原文：derailed visible speed
     */
    @NotNull
    public Vector getDerailedVelocityMod();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置脱轨时的速率修正值. 用于处于地面上但不在铁轨上的矿车. 脱轨矿车的速度每刻都会乘以该修正值.
     * <p>原文：
     * Sets the derailed velocity modifier. Used for minecarts that are on the
     * ground, but not on rails. A derailed minecart's velocity is multiplied
     * by this factor each tick.
     *
     * @param derailed 脱轨时的可见速率
     * <p>原文：visible speed
     */
    public void setDerailedVelocityMod(@NotNull Vector derailed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置示在矿车中的方块.
     * 传递null值清除矿车中展示的方块.
     * <p>原文：
     * Sets the display block for this minecart.
     * Passing a null value will set the minecart to have no display block.
     *
     * @param material 设置为展示方块的物品
     * <p>原文：the material to set as display block.
     * @deprecated use {@link #setDisplayBlockData(BlockData)}
     */
    @Deprecated(forRemoval = true, since = "1.13")
    public void setDisplayBlock(@Nullable MaterialData material);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取展示在矿车中的方块.
     * 如果矿车内无方块, 会返回AIR类型.
     * <p>原文：
     * Gets the display block for this minecart.
     * This function will return the type AIR if none is set.
     *
     * @return 展示在矿车中的方块
     * <p>原文：the block displayed by this minecart.
     * @deprecated use {@link #getDisplayBlockData()}
     */
    @NotNull
    @Deprecated(forRemoval = true, since = "1.13")
    public MaterialData getDisplayBlock();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置展示在矿车中的方块.
     * 传递null值清除矿车中展示的方块.
     * <p>原文：
     * Sets the display block for this minecart.
     * Passing a null value will set the minecart to have no display block.
     *
     * @param blockData 设置为展示方块的物品
     * <p>原文：the material to set as display block.
     */
    public void setDisplayBlockData(@Nullable BlockData blockData);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取展示在矿车中的方块的方块数据.
     * 如果矿车内无方块, 会返回AIR类型.
     * <p>原文：
     * Gets the display block for this minecart.
     * This function will return the type AIR if none is set.
     *
     * @return 展示在矿车中的方块
     * <p>原文：the block displayed by this minecart.
     */
    @NotNull
    public BlockData getDisplayBlockData();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置展示在矿车中的方块的偏移像素值.
     * <p>原文：
     * Sets the offset of the display block.
     *
     * @param offset 展示在矿车中的方块的偏移像素值
     * <p>原文：the block offset to set for this minecart.
     */
    public void setDisplayBlockOffset(int offset);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取展示在矿车中的方块的偏移像素值.
     * <p>原文：
     * Gets the offset of the display block.
     *
     * @return 展示在矿车中的方块的偏移像素值
     * <p>原文：the current block offset for this minecart.
     */
    public int getDisplayBlockOffset();

    // Paper start
    /**
     * Gets the {@link Material} that represents this Minecart type.
     *
     * @return the minecart material.
     */
    @NotNull
    public Material getMinecartMaterial();
    // Paper end
}
