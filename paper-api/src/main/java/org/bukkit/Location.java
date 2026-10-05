package org.bukkit;

import com.google.common.base.Preconditions;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import io.papermc.paper.math.FinePosition;
import io.papermc.paper.math.Rotation;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 世界中的三维位置,包含x,y,z.
 * <br>
 * 除了角度制只能是以度数为单位的限制外, 不对任何角度值施加任何约束.
 * 这意味着负的角度值或大于360°的角度值也是有效的, 但可以将它们转化为其它任何等效的表示.
 * <br>
 * 译注:yaw和pitch分别代表自转角(又称偏航角)、旋进角(又称进动角、俯仰角).
 * 两个值一起表示位置的朝向(与向量的概念重合, 本类有很多方法就是与{@link org.bukkit.util.Vector}重合的).
 * 这里的翻译分别取偏航角、俯仰角. 另请参见util包下的Vector(向量)、EulerAngle(欧拉角).
 * <p>原文：
 * Represents a 3-dimensional position in a world.
 * <br>
 * No constraints are placed on any angular values other than that they be
 * specified in degrees. This means that negative angles or angles of greater
 * magnitude than 360 are valid, but may be normalized to any other equivalent
 * representation by the implementation.
 */
public class Location implements Cloneable, ConfigurationSerializable, io.papermc.paper.math.FinePosition {
    private Reference<World> world;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以给定的坐标构造一个Location对象.
     * <p>原文：
     * Constructs a new Location with the given coordinates
     *
     * @param world 位置所在世界
     * <p>原文：The world in which this location resides
     * @param x 位置x轴坐标
     * <p>原文：The x-coordinate of this new location
     * @param y 位置y轴坐标
     * <p>原文：The y-coordinate of this new location
     * @param z 位置z轴坐标
     * <p>原文：The z-coordinate of this new location
     */
    public Location(@UndefinedNullability final World world, final double x, final double y, final double z) {
        this(world, x, y, z, 0, 0);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以给定的坐标和朝向构造一个Location对象.
     * <p>原文：
     * Constructs a new Location with the given coordinates and direction
     *
     * @param world 位置所在世界
     * <p>原文：The world in which this location resides
     * @param x 位置x轴坐标
     * <p>原文：The x-coordinate of this new location
     * @param y 位置y轴坐标
     * <p>原文：The y-coordinate of this new location
     * @param z 位置z轴坐标
     * <p>原文：The z-coordinate of this new location
     * @param yaw x轴平面上的绝对的旋转角度, 以度为单位
     * <p>原文：The absolute rotation on the x-plane, in degrees
     * @param pitch y轴平面上的绝对的旋转角度, 以度为单位
     * <p>原文：The absolute rotation on the y-plane, in degrees
     */
    public Location(@UndefinedNullability final World world, final double x, final double y, final double z, final float yaw, final float pitch) {
        if (world != null) {
            this.world = new WeakReference<>(world);
        }

        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置本位置所在的世界.
     * <p>原文：
     * Sets the world that this location resides in
     *
     * @param world 位置所在的新世界
     * <p>原文：New world that this location resides in
     */
    public void setWorld(@Nullable World world) {
        this.world = (world == null) ? null : new WeakReference<>(world);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查该位置所处世界是否存在并已加载.
     * <p>原文：
     * Checks if world in this location is present and loaded.
     *
     * @return true表示已加载
     * <p>原文：true if is loaded, otherwise false
     */
    public boolean isWorldLoaded() {
        if (this.world == null) {
            return false;
        }

        World world = this.world.get();
        return world != null && world.equals(Bukkit.getWorld(world.getUID()));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本位置所在的世界.
     * <p>原文：
     * Gets the world that this location resides in
     *
     * @return 世界, 若未设置返回null
     * <p>原文：World that contains this location, or {@code null} if it is not set
     * @throws IllegalArgumentException 当世界未加载时
     * <p>原文：when world is unloaded
     * @see #isWorldLoaded()
     */
    @UndefinedNullability
    public World getWorld() {
        if (this.world == null) {
            return null;
        }

        World world = this.world.get();
        Preconditions.checkArgument(world != null, "World unloaded");
        return world;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此位置位于哪一区块.
     * <p>原文：
     * Gets the chunk at the represented location
     *
     * @return 本位置所在区块
     * <p>原文：Chunk at the represented location
     */
    @NotNull
    public Chunk getChunk() {
        return getWorld().getChunkAt(this);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本位置对应的方块.
     * <p>原文：
     * Gets the block at the represented location
     *
     * @return 所在位置对应的方块
     * <p>原文：Block at the represented location
     */
    @NotNull
    public Block getBlock() {
        return getWorld().getBlockAt(this);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置本位置的x轴坐标.
     * <p>原文：
     * Sets the x-coordinate of this location
     *
     * @param x x轴坐标
     * <p>原文：X-coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本位置的x轴坐标.
     * <p>原文：
     * Gets the x-coordinate of this location
     *
     * @return x轴坐标
     * <p>原文：x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取向下取整的x轴坐标, 表示本位置包含的方块.
     * <p>原文：
     * Gets the floored value of the X component, indicating the block that
     * this location is contained with.
     *
     * @return 方块x轴坐标
     * <p>原文：block X
     */
    public int getBlockX() {
        return locToBlock(x);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置本位置的y轴坐标.
     * <p>原文：
     * Sets the y-coordinate of this location
     *
     * @param y y轴坐标
     * <p>原文：y-coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本位置的y轴坐标.
     * <p>原文：
     * Gets the y-coordinate of this location
     *
     * @return y轴坐标
     * <p>原文：y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取向下取整的y轴坐标, 表示本位置包含的方块.
     * <p>原文：
     * Gets the floored value of the Y component, indicating the block that
     * this location is contained with.
     *
     * @return 方块y轴坐标
     * <p>原文：block y
     */
    public int getBlockY() {
        return locToBlock(y);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置本位置的z坐标轴.
     * <p>原文：
     * Sets the z-coordinate of this location
     *
     * @param z z坐标轴
     * <p>原文：z-coordinate
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本位置的z坐标轴.
     * <p>原文：
     * Gets the z-coordinate of this location
     *
     * @return z坐标轴
     * <p>原文：z-coordinate
     */
    public double getZ() {
        return z;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取向下取整的z轴坐标, 表示本位置包含的方块.
     * <p>原文：
     * Gets the floored value of the Z component, indicating the block that
     * this location is contained with.
     *
     * @return 方块z轴坐标
     * <p>原文：block z
     */
    public int getBlockZ() {
        return locToBlock(z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置本位置的偏航角(yaw), 以度为单位.
     * <ul>
     * <li>偏航角为0或360°时表示z轴正方向.
     * <li>偏航角为180°时表示z轴负方向.
     * <li>偏航角为90°时表示x轴负方向.
     * <li>偏航角为270°时表示x轴正方向.
     * </ul>
     * 增加偏航角值相当于使你转向右侧,
     * 增加偏向下一个坐标轴的比例并减少偏向前一个轴的比例.
     * <p>原文：
     * Sets the yaw of this location, measured in degrees.
     * <ul>
     * <li>A yaw of 0 or 360 represents the positive z direction.
     * <li>A yaw of 180 represents the negative z direction.
     * <li>A yaw of 90 represents the negative x direction.
     * <li>A yaw of 270 represents the positive x direction.
     * </ul>
     * Increasing yaw values are the equivalent of turning to your
     * right-facing, increasing the scale of the next respective axis, and
     * decreasing the scale of the previous axis.
     *
     * @param yaw 自转角(偏航角)
     * <p>原文：new rotation's yaw
     */
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本位置的偏航角(yaw), 以度为单位.
     * <ul>
     * <li>偏航角为0或360°时表示z轴正方向.
     * <li>偏航角为180°时表示z轴负方向.
     * <li>偏航角为90°时表示x轴负方向.
     * <li>偏航角为270°时表示x轴正方向.
     * </ul>
     * 增加偏航角值相当于使你转向右侧,
     * 增加偏向下一个坐标轴的比例并减少偏向前一个轴的比例.
     * <p>原文：
     * Gets the yaw of this location, measured in degrees.
     * <ul>
     * <li>A yaw of 0 or 360 represents the positive z direction.
     * <li>A yaw of 180 represents the negative z direction.
     * <li>A yaw of 90 represents the negative x direction.
     * <li>A yaw of 270 represents the positive x direction.
     * </ul>
     * Increasing yaw values are the equivalent of turning to your
     * right-facing, increasing the scale of the next respective axis, and
     * decreasing the scale of the previous axis.
     *
     * @return 自转角(偏航角)
     * <p>原文：the rotation's yaw
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置本位置的俯仰角(pitch), 以度为单位.
     * <ul>
     * <li>俯仰角为0时表示水平朝向.
     * <li>俯仰角为90°时表示向下的朝向,或y轴的负方向
     * <li>俯仰角为-90°时表示向上的朝向,或y轴的正方向
     * </ul>
     * 增加俯仰角值相当于俯视的效果.
     * <p>原文：
     * Sets the pitch of this location, measured in degrees.
     * <ul>
     * <li>A pitch of 0 represents level forward facing.
     * <li>A pitch of 90 represents downward facing, or negative y
     *     direction.
     * <li>A pitch of -90 represents upward facing, or positive y direction.
     * </ul>
     * Increasing pitch values the equivalent of looking down.
     *
     * @param pitch 倾斜角(俯仰角)
     * <p>原文：new incline's pitch
     */
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本位置的俯仰角(pitch), 以度为单位.
     * <ul>
     * <li>俯仰角为0时表示水平朝向.
     * <li>俯仰角为90°时表示向下的朝向,或y轴的负方向
     * <li>俯仰角为-90°时表示向上的朝向,或y轴的正方向
     * </ul>
     * 增加俯仰角值相当于俯视的效果.
     * <p>原文：
     * Gets the pitch of this location, measured in degrees.
     * <ul>
     * <li>A pitch of 0 represents level forward facing.
     * <li>A pitch of 90 represents downward facing, or negative y
     *     direction.
     * <li>A pitch of -90 represents upward facing, or positive y direction.
     * </ul>
     * Increasing pitch values the equivalent of looking down.
     *
     * @return 倾斜角(俯仰角)
     * <p>原文：the incline's pitch
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本位置所面向的方向的单位向量.
     * <p>原文：
     * Gets a unit-vector pointing in the direction that this Location is
     * facing.
     *
     * @return 指向此位置的{@link
     *     #getYaw() 偏航角}和{@link #getPitch() 俯仰角}的向量
     * <p>原文：a vector pointing the direction of this location's {@link
     *     #getPitch() pitch} and {@link #getYaw() yaw}
     */
    @NotNull
    public Vector getDirection() {
        Vector vector = new Vector();

        double rotX = this.getYaw();
        double rotY = this.getPitch();

        vector.setY(-Math.sin(Math.toRadians(rotY)));

        double xz = Math.cos(Math.toRadians(rotY));

        vector.setX(-xz * Math.sin(Math.toRadians(rotX)));
        vector.setZ(xz * Math.cos(Math.toRadians(rotX)));

        return vector;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将{@link #getYaw() 偏航角}和{@link #getPitch() 俯仰角}设为指向某一向量的方向.
     * <p>原文：
     * Sets the {@link #getYaw() yaw} and {@link #getPitch() pitch} to point
     * in the direction of the vector.
     *
     * @param vector 方向向量
     * <p>原文：the direction vector
     * @return 相同的位置对象(不是位置相同,但位置对象相同)
     * <p>原文：the same location
     */
    @NotNull
    public Location setDirection(@NotNull Vector vector) {
        /*
         * Sin = Opp / Hyp
         * Cos = Adj / Hyp
         * Tan = Opp / Adj
         *
         * x = -Opp
         * z = Adj
         */
        final double _2PI = 2 * Math.PI;
        final double x = vector.getX();
        final double z = vector.getZ();

        if (x == 0 && z == 0) {
            pitch = vector.getY() > 0 ? -90 : 90;
            return this;
        }

        double theta = Math.atan2(-x, z);
        yaw = (float) Math.toDegrees((theta + _2PI) % _2PI);

        double x2 = NumberConversions.square(x);
        double z2 = NumberConversions.square(z);
        double xz = Math.sqrt(x2 + z2);
        pitch = (float) Math.toDegrees(Math.atan(-vector.getY() / xz));

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将本位置的坐标加上另一个位置的坐标.
     * <p>原文：
     * Adds the location by another.
     *
     * @param vec 另一个位置
     * <p>原文：The other location
     * @return 相同的位置对象(不是位置相同,但位置对象相同)
     * <p>原文：the same location
     * @throws IllegalArgumentException 若两个位置所处世界各不相同
     * <p>原文：for differing worlds
     * @see Vector
     */
    @NotNull
    public Location add(@NotNull Location vec) {
        if (vec == null || vec.getWorld() != getWorld()) {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }

        x += vec.x;
        y += vec.y;
        z += vec.z;
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将本位置的坐标加上一个向量的坐标.
     * <p>原文：
     * Adds the location by a vector.
     *
     * @param vec 向量
     * <p>原文：Vector to use
     * @return 相同的位置对象(不是位置相同,但位置对象相同)
     * <p>原文：the same location
     * @see Vector
     */
    @NotNull
    public Location add(@NotNull Vector vec) {
        this.x += vec.getX();
        this.y += vec.getY();
        this.z += vec.getZ();
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将本位置的坐标加上另一个位置的坐标. 与位置所处世界无关.
     * <p>原文：
     * Adds the location by another. Not world-aware.
     *
     * @param x X坐标
     * <p>原文：X coordinate
     * @param y Y坐标
     * <p>原文：Y coordinate
     * @param z Z坐标
     * <p>原文：Z coordinate
     * @return 相同的位置对象(不是位置相同,但位置对象相同)
     * <p>原文：the same location
     * @see Vector
     */
    @NotNull
    public Location add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    /**
     * Adds rotation in the form of yaw and patch to this location. Not world-aware.
     *
     * @param yaw   yaw, measured in degrees.
     * @param pitch pitch, measured in degrees.
     * @return the same location
     * @see Vector
     */
    @NotNull
    @Contract(value = "_,_ -> this", mutates = "this")
    public Location addRotation(final float yaw, final float pitch) {
        this.yaw += yaw;
        this.pitch += pitch;
        return this;
    }

    /**
     * Adds rotation to this location. Not world-aware.
     *
     * @param rotation the rotation to add.
     * @return the same location
     * @see Vector
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public Location addRotation(@NotNull Rotation rotation) {
        return addRotation(rotation.yaw(), rotation.pitch());
    }

    /**
     * Retrieves the rotation of this location.
     *
     * @return a new {@code Rotation} object
     */
    @NotNull
    @Contract(value = " -> new", pure = true)
    public Rotation getRotation() {
        return Rotation.rotation(yaw, pitch);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将本位置的坐标减去另一个位置的坐标.
     * <p>原文：
     * Subtracts the location by another.
     *
     * @param vec 另一个位置
     * <p>原文：The other location
     * @return 相同的位置对象(不是位置相同,但位置对象相同)
     * <p>原文：the same location
     * @throws IllegalArgumentException 若两个位置所处世界各不相同
     * <p>原文：for differing worlds
     * @see Vector
     */
    @NotNull
    public Location subtract(@NotNull Location vec) {
        if (vec == null || vec.getWorld() != getWorld()) {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }

        x -= vec.x;
        y -= vec.y;
        z -= vec.z;
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从本位置的坐标中减去向量的坐标.
     * <p>原文：
     * Subtracts the location by a vector.
     *
     * @param vec 向量
     * <p>原文：The vector to use
     * @return 相同的位置对象(不是位置相同,但位置对象相同)
     * <p>原文：the same location
     * @see Vector
     */
    @NotNull
    public Location subtract(@NotNull Vector vec) {
        this.x -= vec.getX();
        this.y -= vec.getY();
        this.z -= vec.getZ();
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从本位置的坐标中减去另一个位置的坐标.
     * 与位置所处世界无关且其方向角独立不参与运算.
     * <p>原文：
     * Subtracts the location by another. Not world-aware and
     * orientation independent.
     *
     * @param x X坐标
     * <p>原文：X coordinate
     * @param y Y坐标
     * <p>原文：Y coordinate
     * @param z Z坐标
     * <p>原文：Z coordinate
     * @return 相同的位置对象(不是位置相同,但位置对象相同)
     * <p>原文：the same location
     * @see Vector
     */
    @NotNull
    public Location subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    /**
     * Subtracts rotation in the form of yaw and patch from this location.
     *
     * @param yaw   yaw, measured in degrees.
     * @param pitch pitch, measured in degrees.
     * @return the same location
     * @see Vector
     */
    @NotNull
    @Contract(value = "_,_ -> this", mutates = "this")
    public Location subtractRotation(final float yaw, final float pitch) {
        this.yaw -= yaw;
        this.pitch -= pitch;
        return this;
    }

    /**
     * Subtracts rotation from this location.
     *
     * @param rotation the rotation to subtract.
     * @return the same location
     * @see Vector
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public Location subtractRotation(@NotNull Rotation rotation) {
        return subtractRotation(rotation.yaw(), rotation.pitch());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取位置的模值,定义为 sqrt(x^2+y^2+z^2).
     * 这个方法的返回值没有被缓存,且使用了开销较大的平方以及开根函数,
     * 所以不要反复调用这个方法来获取向量的模值.
     * 当向量的模过大时,开根函数有可能发生溢出,并会返回{@link Double#NaN}.
     * 与位置所处世界无关且其方向角独立不参与运算.
     * <p>原文：
     * Gets the magnitude of the location, defined as sqrt(x^2+y^2+z^2). The
     * value of this method is not cached and uses a costly square-root
     * function, so do not repeatedly call this method to get the location's
     * magnitude. NaN will be returned if the inner result of the sqrt()
     * function overflows, which will be caused if the length is too long. Not
     * world-aware and orientation independent.
     *
     * @return 模
     * <p>原文：the magnitude
     * @see Vector
     */
    public double length() {
        return Math.sqrt(NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取位置长度(又称模)的平方 (三个坐标分别平方然后加起来).
     * 与位置所处世界无关且其方向角独立不参与运算.
     * <p>原文：
     * Gets the magnitude of the location squared. Not world-aware and
     * orientation independent.
     *
     * @return 模的平方
     * <p>原文：the magnitude
     * @see Vector
     */
    public double lengthSquared() {
        return NumberConversions.square(x) + NumberConversions.square(y) + NumberConversions.square(z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本位置与与另一个位置之间的距离.
     * 这个方法的返回值没有被缓存,且使用了开销较大的平方以及开根函数,
     * 所以不要反复调用这个方法来获取位置的模值.
     * 当位置的距离过大时,开根函数有可能发生溢出,并会返回{@link Double#NaN}.
     * <p>原文：
     * Get the distance between this location and another. The value of this
     * method is not cached and uses a costly square-root function, so do not
     * repeatedly call this method to get the location's magnitude. NaN will
     * be returned if the inner result of the sqrt() function overflows, which
     * will be caused if the distance is too long.
     *
     * @param o 另一个位置
     * <p>原文：The other location
     * @return 距离
     * <p>原文：the distance
     * @throws IllegalArgumentException 若两个位置所处世界各不相同
     * <p>原文：for differing worlds
     * @see Vector
     */
    public double distance(@NotNull Location o) {
        return Math.sqrt(distanceSquared(o));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本位置与与另一个位置之间的距离的平方.
     * <p>原文：
     * Get the squared distance between this location and another.
     *
     * @param o 另一个位置
     * <p>原文：The other location
     * @return 距离的平方
     * <p>原文：the distance
     * @throws IllegalArgumentException 若两个位置所处世界各不相同
     * <p>原文：for differing worlds
     * @see Vector
     */
    public double distanceSquared(@NotNull Location o) {
        if (o == null) {
            throw new IllegalArgumentException("Cannot measure distance to a null location");
        } else if (o.getWorld() == null || getWorld() == null) {
            throw new IllegalArgumentException("Cannot measure distance to a null world");
        } else if (o.getWorld() != getWorld()) {
            throw new IllegalArgumentException("Cannot measure distance between " + getWorld().getName() + " and " + o.getWorld().getName());
        }

        return NumberConversions.square(x - o.x) + NumberConversions.square(y - o.y) + NumberConversions.square(z - o.z);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 坐标数乘, 将所有坐标轴上扩展某个倍数.
     * <p>原文：
     * Performs scalar multiplication, multiplying all components with a
     * scalar. Not world-aware.
     *
     * @param m 因数,即数乘的倍数,整数
     * <p>原文：The factor
     * @return 相同的位置对象(不是位置相同,但位置对象相同)
     * <p>原文：the same location
     * @see Vector
     */
    @NotNull
    public Location multiply(double m) {
        x *= m;
        y *= m;
        z *= m;
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 清零此位置的xyz坐标数值. 与位置所处世界无关.
     * <p>原文：
     * Zero this location's components. Not world-aware.
     *
     * @return 相同的位置对象(不是位置相同,但位置对象相同)
     * <p>原文：the same location
     * @see Vector
     */
    @NotNull
    public Location zero() {
        x = 0;
        y = 0;
        z = 0;
        return this;
    }

    public boolean isChunkLoaded() {
        return this.getWorld().isChunkLoaded(locToBlock(x) >> 4, locToBlock(z) >> 4);
    }

    /**
     * Checks if a {@link Chunk} has been generated at this location.
     *
     * @return true if a chunk has been generated at this location
     */
    public boolean isGenerated() {
        World world = this.getWorld();
        Preconditions.checkNotNull(world, "Location has no world!");
        return world.isChunkGenerated(locToBlock(x) >> 4, locToBlock(z) >> 4);
    }

    /**
     * Sets the position of this Location and returns itself
     * <p>
     * This mutates this object, clone first.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return self (not cloned)
     */
    @NotNull
    public Location set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    /**
     * Sets the rotation of this location and returns itself.
     * <p>
     * This mutates this object, clone first.
     *
     * @param yaw   yaw, measured in degrees.
     * @param pitch pitch, measured in degrees.
     * @return self (not cloned)
     */
    @NotNull
    @Contract(value = "_,_ -> this", mutates = "this")
    public Location setRotation(final float yaw, final float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        return this;
    }

    /**
     * Sets the rotation of this location and returns itself.
     * <p>
     * This mutates this object, clone first.
     *
     * @param rotation the new rotation.
     * @return self (not cloned)
     */
    @NotNull
    @Contract(value = "_ -> this", mutates = "this")
    public Location setRotation(@NotNull Rotation rotation) {
        return setRotation(rotation.yaw(), rotation.pitch());
    }

    /**
     * Takes the x/y/z from base and adds the specified x/y/z to it and returns self
     * <p>
     * This mutates this object, clone first.
     *
     * @param base The base coordinate to modify
     * @param x X coordinate to add to base
     * @param y Y coordinate to add to base
     * @param z Z coordinate to add to base
     * @return self (not cloned)
     */
    @NotNull
    public Location add(@NotNull Location base, double x, double y, double z) {
        return this.set(base.x + x, base.y + y, base.z + z);
    }

    /**
     * Takes the x/y/z from base and subtracts the specified x/y/z to it and returns self
     * <p>
     * This mutates this object, clone first.
     *
     * @param base The base coordinate to modify
     * @param x X coordinate to subtract from base
     * @param y Y coordinate to subtract from base
     * @param z Z coordinate to subtract from base
     * @return self (not cloned)
     */
    @NotNull
    public Location subtract(@NotNull Location base, double x, double y, double z) {
        return this.set(base.x - x, base.y - y, base.z - z);
    }

    /**
     * @return A new location where X/Y/Z are on the Block location (integer value of X/Y/Z)
     */
    @NotNull
    public Location toBlockLocation() {
        Location blockLoc = clone();
        blockLoc.setX(getBlockX());
        blockLoc.setY(getBlockY());
        blockLoc.setZ(getBlockZ());
        return blockLoc;
    }

    /**
     * @return The block key for this location's block location.
     * @see Block#getBlockKey(int, int, int)
     * @deprecated only encodes y block ranges from -512 to 511 and represents an already changed implementation detail
     */
    @Deprecated(since = "1.18.1")
    public long toBlockKey() {
        return Block.getBlockKey(getBlockX(), getBlockY(), getBlockZ());
    }

    /**
     * @return A new location where X/Y/Z are the center of the block
     */
    @NotNull
    public Location toCenterLocation() {
        Location centerLoc = clone();
        centerLoc.setX(getBlockX() + 0.5);
        centerLoc.setY(getBlockY() + 0.5);
        centerLoc.setZ(getBlockZ() + 0.5);
        return centerLoc;
    }

    /**
     * Returns a copy of this location except with y = getWorld().getHighestBlockYAt(this.getBlockX(), this.getBlockZ())
     * @return A copy of this location except with y = getWorld().getHighestBlockYAt(this.getBlockX(), this.getBlockZ())
     * @throws NullPointerException if {@link #getWorld()} is {@code null}
     */
    @NotNull
    public Location toHighestLocation() {
        return this.toHighestLocation(HeightMap.WORLD_SURFACE);
    }

    /**
     * Returns a copy of this location except with y = getWorld().getHighestBlockYAt(this.getBlockX(), this.getBlockZ(), heightMap)
     * @param heightMap The heightmap to use for finding the highest y location.
     * @return A copy of this location except with y = getWorld().getHighestBlockYAt(this.getBlockX(), this.getBlockZ(), heightMap)
     */
    @NotNull
    public Location toHighestLocation(@NotNull final HeightMap heightMap) {
        final Location ret = this.clone();
        ret.setY(this.getWorld().getHighestBlockYAt(this, heightMap));
        return ret;
    }

    /**
     * Creates explosion at this location with given power
     * <p>
     * Will break blocks and ignite blocks on fire.
     *
     * @param power The power of explosion, where 4F is TNT
     * @return false if explosion was canceled, otherwise true
     */
    public boolean createExplosion(float power) {
        return this.getWorld().createExplosion(this, power);
    }

    /**
     * Creates explosion at this location with given power and optionally
     * setting blocks on fire.
     * <p>
     * Will break blocks.
     *
     * @param power The power of explosion, where 4F is TNT
     * @param setFire Whether to set blocks on fire
     * @return false if explosion was canceled, otherwise true
     */
    public boolean createExplosion(float power, boolean setFire) {
        return this.getWorld().createExplosion(this, power, setFire);
    }

    /**
     * Creates explosion at this location with given power and optionally
     * setting blocks on fire.
     *
     * @param power The power of explosion, where 4F is TNT
     * @param setFire Whether to set blocks on fire
     * @param breakBlocks Whether to have blocks be destroyed
     * @return false if explosion was canceled, otherwise true
     */
    public boolean createExplosion(float power, boolean setFire, boolean breakBlocks) {
        return this.getWorld().createExplosion(this, power, setFire, breakBlocks);
    }

    /**
     * Creates explosion at this location with given power, with the specified entity as the source.
     * <p>
     * Will break blocks and ignite blocks on fire.
     *
     * @param source The source entity of the explosion
     * @param power The power of explosion, where 4F is TNT
     * @return false if explosion was canceled, otherwise true
     */
    public boolean createExplosion(@Nullable Entity source, float power) {
        return this.getWorld().createExplosion(source, this, power, true, true);
    }

    /**
     * Creates explosion at this location with given power and optionally
     * setting blocks on fire, with the specified entity as the source.
     * <p>
     * Will break blocks.
     *
     * @param source The source entity of the explosion
     * @param power The power of explosion, where 4F is TNT
     * @param setFire Whether to set blocks on fire
     * @return false if explosion was canceled, otherwise true
     */
    public boolean createExplosion(@Nullable Entity source, float power, boolean setFire) {
        return this.getWorld().createExplosion(source, this, power, setFire, true);
    }

    /**
     * Creates explosion at this location with given power and optionally
     * setting blocks on fire, with the specified entity as the source.
     *
     * @param source The source entity of the explosion
     * @param power The power of explosion, where 4F is TNT
     * @param setFire Whether to set blocks on fire
     * @param breakBlocks Whether to have blocks be destroyed
     * @return false if explosion was canceled, otherwise true
     */
    public boolean createExplosion(@Nullable Entity source, float power, boolean setFire, boolean breakBlocks) {
        return this.getWorld().createExplosion(source, this, power, setFire, breakBlocks);
    }

    /**
     * Returns a list of entities within a bounding box centered around a Location.
     * <p>
     * Some implementations may impose artificial restrictions on the size of the search bounding box.
     *
     * @param x 1/2 the size of the box along the x-axis
     * @param y 1/2 the size of the box along the y-axis
     * @param z 1/2 the size of the box along the z-axis
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    public @NotNull Collection<Entity> getNearbyEntities(final double x, final double y, final double z) {
        final World world = this.getWorld();
        if (world == null) {
            throw new IllegalArgumentException("Location has no world");
        }
        return world.getNearbyEntities(this, x, y, z);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param radius X Radius
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    public @NotNull Collection<LivingEntity> getNearbyLivingEntities(final double radius) {
        return this.getNearbyEntitiesByType(LivingEntity.class, radius, radius, radius);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param xzRadius X/Z Radius
     * @param yRadius Y Radius
     * @return the collection of living entities near location. This will always be a non-null collection.
     */
    public @NotNull Collection<LivingEntity> getNearbyLivingEntities(final double xzRadius, final double yRadius) {
        return this.getNearbyEntitiesByType(LivingEntity.class, xzRadius, yRadius, xzRadius);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param xRadius X Radius
     * @param yRadius Y Radius
     * @param zRadius Z radius
     * @return the collection of living entities near location. This will always be a non-null collection.
     */
    public @NotNull Collection<LivingEntity> getNearbyLivingEntities(final double xRadius, final double yRadius, final double zRadius) {
        return this.getNearbyEntitiesByType(LivingEntity.class, xRadius, yRadius, zRadius);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param radius Radius
     * @param predicate a predicate used to filter results
     * @return the collection of living entities near location. This will always be a non-null collection.
     */
    public @NotNull Collection<LivingEntity> getNearbyLivingEntities(final double radius, final @Nullable Predicate<? super LivingEntity> predicate) {
        return this.getNearbyEntitiesByType(LivingEntity.class, radius, radius, radius, predicate);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param xzRadius X/Z Radius
     * @param yRadius Y Radius
     * @param predicate a predicate used to filter results
     * @return the collection of living entities near location. This will always be a non-null collection.
     */
    public @NotNull Collection<LivingEntity> getNearbyLivingEntities(final double xzRadius, final double yRadius, final @Nullable Predicate<? super LivingEntity> predicate) {
        return this.getNearbyEntitiesByType(LivingEntity.class, xzRadius, yRadius, xzRadius, predicate);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param xRadius X Radius
     * @param yRadius Y Radius
     * @param zRadius Z radius
     * @param predicate a predicate used to filter results
     * @return the collection of living entities near location. This will always be a non-null collection.
     */
    public @NotNull Collection<LivingEntity> getNearbyLivingEntities(final double xRadius, final double yRadius, final double zRadius, final @Nullable Predicate<? super LivingEntity> predicate) {
        return this.getNearbyEntitiesByType(LivingEntity.class, xRadius, yRadius, zRadius, predicate);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param radius X/Y/Z Radius
     * @return the collection of players near location. This will always be a non-null collection.
     */
    public @NotNull Collection<Player> getNearbyPlayers(final double radius) {
        return this.getNearbyEntitiesByType(Player.class, radius, radius, radius);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param xzRadius X/Z Radius
     * @param yRadius Y Radius
     * @return the collection of players near location. This will always be a non-null collection.
     */
    public @NotNull Collection<Player> getNearbyPlayers(final double xzRadius, final double yRadius) {
        return this.getNearbyEntitiesByType(Player.class, xzRadius, yRadius, xzRadius);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param xRadius X Radius
     * @param yRadius Y Radius
     * @param zRadius Z Radius
     * @return the collection of players near location. This will always be a non-null collection.
     */
    public @NotNull Collection<Player> getNearbyPlayers(final double xRadius, final double yRadius, final double zRadius) {
        return this.getNearbyEntitiesByType(Player.class, xRadius, yRadius, zRadius);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param radius X/Y/Z Radius
     * @param predicate a predicate used to filter results
     * @return the collection of players near location. This will always be a non-null collection.
     */
    public @NotNull Collection<Player> getNearbyPlayers(final double radius, final @Nullable Predicate<? super Player> predicate) {
        return this.getNearbyEntitiesByType(Player.class, radius, radius, radius, predicate);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param xzRadius X/Z Radius
     * @param yRadius Y Radius
     * @param predicate a predicate used to filter results
     * @return the collection of players near location. This will always be a non-null collection.
     */
    public @NotNull Collection<Player> getNearbyPlayers(final double xzRadius, final double yRadius, final @Nullable Predicate<? super Player> predicate) {
        return this.getNearbyEntitiesByType(Player.class, xzRadius, yRadius, xzRadius, predicate);
    }

    /**
     * Gets nearby players within the specified radius (bounding box)
     *
     * @param xRadius X Radius
     * @param yRadius Y Radius
     * @param zRadius Z Radius
     * @param predicate a predicate used to filter results
     * @return the collection of players near location. This will always be a non-null collection.
     */
    public @NotNull Collection<Player> getNearbyPlayers(final double xRadius, final double yRadius, final double zRadius, final @Nullable Predicate<? super Player> predicate) {
        return this.getNearbyEntitiesByType(Player.class, xRadius, yRadius, zRadius, predicate);
    }

    /**
     * Gets all nearby entities of the specified type, within the specified radius (bounding box)
     *
     * @param clazz Type to filter by
     * @param radius X/Y/Z radius to search within
     * @param <T> the entity type
     * @return the collection of entities of type clazz near location. This will always be a non-null collection.
     */
    public @NotNull <T extends Entity> Collection<T> getNearbyEntitiesByType(final @Nullable Class<? extends T> clazz, final double radius) {
        return this.getNearbyEntitiesByType(clazz, radius, radius, radius, null);
    }

    /**
     * Gets all nearby entities of the specified type, within the specified radius, with x and x radius matching (bounding box)
     *
     * @param clazz Type to filter by
     * @param xzRadius X/Z radius to search within
     * @param yRadius Y radius to search within
     * @param <T> the entity type
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    public @NotNull <T extends Entity> Collection<T> getNearbyEntitiesByType(final @Nullable Class<? extends T> clazz, final double xzRadius, final double yRadius) {
        return this.getNearbyEntitiesByType(clazz, xzRadius, yRadius, xzRadius, null);
    }

    /**
     * Gets all nearby entities of the specified type, within the specified radius (bounding box)
     *
     * @param clazz Type to filter by
     * @param xRadius X Radius
     * @param yRadius Y Radius
     * @param zRadius Z Radius
     * @param <T> the entity type
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    public @NotNull <T extends Entity> Collection<T> getNearbyEntitiesByType(final @Nullable Class<? extends T> clazz, final double xRadius, final double yRadius, final double zRadius) {
        return this.getNearbyEntitiesByType(clazz, xRadius, yRadius, zRadius, null);
    }

    /**
     * Gets all nearby entities of the specified type, within the specified radius (bounding box)
     *
     * @param clazz Type to filter by
     * @param radius X/Y/Z radius to search within
     * @param predicate a predicate used to filter results
     * @param <T> the entity type
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    public @NotNull <T extends Entity> Collection<T> getNearbyEntitiesByType(final @Nullable Class<? extends T> clazz, final double radius, final @Nullable Predicate<? super T> predicate) {
        return this.getNearbyEntitiesByType(clazz, radius, radius, radius, predicate);
    }

    /**
     * Gets all nearby entities of the specified type, within the specified radius, with x and x radius matching (bounding box)
     *
     * @param clazz Type to filter by
     * @param xzRadius X/Z radius to search within
     * @param yRadius Y radius to search within
     * @param predicate a predicate used to filter results
     * @param <T> the entity type
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    public @NotNull <T extends Entity> Collection<T> getNearbyEntitiesByType(final @Nullable Class<? extends T> clazz, final double xzRadius, final double yRadius, final @Nullable Predicate<? super T> predicate) {
        return this.getNearbyEntitiesByType(clazz, xzRadius, yRadius, xzRadius, predicate);
    }

    /**
     * Gets all nearby entities of the specified type, within the specified radius (bounding box)
     *
     * @param clazz Type to filter by
     * @param xRadius X Radius
     * @param yRadius Y Radius
     * @param zRadius Z Radius
     * @param predicate a predicate used to filter results
     * @param <T> the entity type
     * @return the collection of entities near location. This will always be a non-null collection.
     */
    public @NotNull <T extends Entity> Collection<T> getNearbyEntitiesByType(final @Nullable Class<? extends T> clazz, final double xRadius, final double yRadius, final double zRadius, final @Nullable Predicate<? super T> predicate) {
        final World world = this.getWorld();
        if (world == null) {
            throw new IllegalArgumentException("Location has no world");
        }
        return world.getNearbyEntitiesByType(clazz, this, xRadius, yRadius, zRadius, predicate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;

        World world = (this.world == null) ? null : this.world.get();
        World otherWorld = (other.world == null) ? null : other.world.get();
        if (world != otherWorld && (world == null || !world.equals(otherWorld))) {
            return false;
        }
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        if (Float.floatToIntBits(this.pitch) != Float.floatToIntBits(other.pitch)) {
            return false;
        }
        if (Float.floatToIntBits(this.yaw) != Float.floatToIntBits(other.yaw)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;

        World world = (this.world == null) ? null : this.world.get();
        hash = 19 * hash + (world != null ? world.hashCode() : 0);
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        hash = 19 * hash + Float.floatToIntBits(this.pitch);
        hash = 19 * hash + Float.floatToIntBits(this.yaw);
        return hash;
    }

    @Override
    public String toString() {
        World world = (this.world == null) ? null : this.world.get();
        return "Location{" + "world=" + world + ",x=" + x + ",y=" + y + ",z=" + z + ",pitch=" + pitch + ",yaw=" + yaw + '}';
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 基于此位置构造一个新的{@link Vector 向量}.
     * <p>原文：
     * Constructs a new {@link Vector} based on this Location
     *
     * @return 包含该位置坐标的向量表示
     * <p>原文：New Vector containing the coordinates represented by this
     *     Location
     */
    @NotNull
    public Vector toVector() {
        return new Vector(x, y, z);
    }

    @Override
    @NotNull
    public Location clone() {
        try {
            return (Location) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测该Location的xyz坐标和偏航角、俯仰角是否合法
     * (限制的最大值为Double.MAX_VALUE).
     * <p>原文：
     * Check if each component of this Location is finite.
     *
     * @throws IllegalArgumentException 若有任意一个坐标/角度值不合法时抛出
     * <p>原文：if any component is not finite
     */
    public void checkFinite() throws IllegalArgumentException {
        NumberConversions.checkFinite(x, "x not finite");
        NumberConversions.checkFinite(y, "y not finite");
        NumberConversions.checkFinite(z, "z not finite");
        NumberConversions.checkFinite(pitch, "pitch not finite");
        NumberConversions.checkFinite(yaw, "yaw not finite");
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把double类型数值(位置坐标)安全地转换为int类型数值(方块坐标).
     * <p>原文：
     * Safely converts a double (location coordinate) to an int (block
     * coordinate)
     *
     * @param loc 精确坐标
     * <p>原文：Precise coordinate
     * @return 方块坐标
     * <p>原文：Block coordinate
     */
    public static int locToBlock(double loc) {
        return NumberConversions.floor(loc);
    }

    @Override
    @Utility
    @NotNull
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<String, Object>();

        if (this.world != null) {
            data.put("world", getWorld().getName());
        }

        data.put("x", this.x);
        data.put("y", this.y);
        data.put("z", this.z);

        data.put("yaw", this.yaw);
        data.put("pitch", this.pitch);

        return data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实现配置序列化与反序列化所需方法.
     * <p>原文：
     * Required method for deserialization
     *
     * @param args 需要反序列化的map
     * <p>原文：map to deserialize
     * @return 反序列化后的位置
     * <p>原文：deserialized location
     * @throws IllegalArgumentException 如果位置所处世界不存在
     * <p>原文：if the world don't exists
     * @see ConfigurationSerializable
     */
    @NotNull
    public static Location deserialize(@NotNull Map<String, Object> args) {
        World world = null;
        if (args.containsKey("world")) {
            world = Bukkit.getWorld((String) args.get("world"));
            if (world == null) {
                throw new IllegalArgumentException("unknown world");
            }
        }

        return new Location(world, NumberConversions.toDouble(args.get("x")), NumberConversions.toDouble(args.get("y")), NumberConversions.toDouble(args.get("z")), NumberConversions.toFloat(args.get("yaw")), NumberConversions.toFloat(args.get("pitch")));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将给定的偏航角归一化至<code>+/-180</code>范围内.
     * <p>
     * 译注:比如角度值的绝对值大于180, 用此方法可使角度的绝对值在0-180以内.
     * <p>原文：
     * Normalizes the given yaw angle to a value between <code>+/-180</code>
     * degrees.
     *
     * @param yaw 偏航角, 使用角度制
     * <p>原文：the yaw in degrees
     * @return 标准化的偏航角, 使用角度制
     * <p>原文：the normalized yaw in degrees
     * @see Location#getYaw()
     */
    public static float normalizeYaw(float yaw) {
        yaw %= 360.0f;
        if (yaw >= 180.0f) {
            yaw -= 360.0f;
        } else if (yaw < -180.0f) {
            yaw += 360.0f;
        }
        return yaw;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将给定的俯仰角归一化至<code>+/-180</code>范围内.
     * <p>
     * 译注:比如角度值的绝对值大于180, 用此方法可使角度的绝对值在0-180以内.
     * <p>原文：
     * Normalizes the given pitch angle to a value between <code>+/-90</code>
     * degrees.
     *
     * @param pitch 俯仰角, 使用角度制
     * <p>原文：the pitch in degrees
     * @return 标准化的俯仰角, 使用角度制
     * <p>原文：the normalized pitch in degrees
     * @see Location#getPitch()
     */
    public static float normalizePitch(float pitch) {
        if (pitch > 90.0f) {
            pitch = 90.0f;
        } else if (pitch < -90.0f) {
            pitch = -90.0f;
        }
        return pitch;
    }

    @Override
    public double x() {
        return this.getX();
    }

    @Override
    public double y() {
        return this.getY();
    }

    @Override
    public double z() {
        return this.getZ();
    }

    @Override
    public boolean isFinite() {
        return FinePosition.super.isFinite() && Float.isFinite(this.getYaw()) && Float.isFinite(this.getPitch());
    }

    @Override
    public @NotNull Location toLocation(@NotNull World world) {
        return new Location(world, this.x(), this.y(), this.z(), this.getYaw(), this.getPitch());
    }
}
