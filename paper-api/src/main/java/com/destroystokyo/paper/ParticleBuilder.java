package com.destroystokyo.paper;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collection;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.NumberConversions;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 帮助准备要发送给玩家的粒子。
 * <p>
 * 优先使用构建器，而不是冗长的 {@link World#spawnParticle(Particle, Location, int, double, double, double, double, Object)} API
 * <p>原文：
 * Helps prepare a particle to be sent to players.
 * <p>
 * Usage of the builder is preferred over the super long {@link World#spawnParticle(Particle, Location, int, double, double, double, double, Object)} API
 */
@NullMarked
public class ParticleBuilder implements Cloneable {

    private Particle particle;
    private @Nullable List<Player> receivers;
    private @Nullable Player source;
    private @Nullable Location location;
    private int count = 1;
    private double offsetX = 0, offsetY = 0, offsetZ = 0;
    private double extra = 1;
    private @Nullable Object data;
    private boolean force = true;

    public ParticleBuilder(final Particle particle) {
        this.particle = particle;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将粒子发送给所有接收玩家（或所有玩家）。该方法可安全地异步使用
     * <p>原文：
     * Sends the particle to all receiving players (or all). This method is safe to use
     * Asynchronously
     *
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder spawn() {
        if (this.location == null) {
            throw new IllegalStateException("Please specify location for this particle");
        }
        this.location.getWorld().spawnParticle(
            this.particle, this.receivers, this.source,
            this.location.getX(), this.location.getY(), this.location.getZ(),
            this.count, this.offsetX, this.offsetY, this.offsetZ, this.extra, this.data, this.force
        );
        return this;
    }

    /**
     * @return 将要发送的粒子
     * <p>原文：The particle going to be sent
     */
    public Particle particle() {
        return this.particle;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 更改将要发送的粒子
     * <p>原文：
     * Changes what particle will be sent
     *
     * @param particle 粒子
     * <p>原文：The particle
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder particle(final Particle particle) {
        this.particle = particle;
        return this;
    }

    /**
     * @return 将接收粒子的玩家列表；为 null 则表示世界中的所有玩家
     * <p>原文：List of players who will receive the particle, or null for all in world
     */
    public @Nullable List<Player> receivers() {
        return this.receivers;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用法示例：
     * <p>
     * builder.receivers(16); if (builder.hasReceivers()) { sendParticleAsync(builder); }
     * <p>原文：
     * Example use:
     * <p>
     * builder.receivers(16); if (builder.hasReceivers()) { sendParticleAsync(builder); }
     *
     * @return 此粒子是否会被发送给某人
     * <p>原文：If this particle is going to be sent to someone
     */
    public boolean hasReceivers() {
        return (this.receivers == null && this.location != null && !this.location.getWorld().getPlayers().isEmpty()) || (
            this.receivers != null && !this.receivers.isEmpty());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将此粒子发送给世界中的所有玩家。这相当不明智，你很可能不应该这样做。
     * <p>
     * 理智一点，按半径或集合指定接收者吧。
     * <p>原文：
     * Sends this particle to all players in the world. This is rather silly, and you should likely not
     * be doing this.
     * <p>
     * Just be a logical person and use receivers by radius or collection.
     *
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder allPlayers() {
        this.receivers = null;
        return this;
    }

    /**
     * @param receivers 接收此粒子的玩家列表，或 null 表示世界中的所有玩家
     * <p>原文：List of players to receive this particle, or null for all players in the
     *                  world
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder receivers(final @Nullable List<Player> receivers) {
        // Had to keep this as we first made API List<> and not Collection, but removing this may break plugins compiled on older jars
        // TODO: deprecate?
        this.receivers = receivers != null ? Lists.newArrayList(receivers) : null;
        return this;
    }

    /**
     * @param receivers 接收此粒子的玩家列表，或 null 表示世界中的所有玩家
     * <p>原文：List of players to receive this particle, or null for all players in the
     *                  world
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder receivers(final @Nullable Collection<Player> receivers) {
        this.receivers = receivers != null ? Lists.newArrayList(receivers) : null;
        return this;
    }

    /**
     * @param receivers 接收此粒子的玩家列表，或 null 表示世界中的所有玩家
     * <p>原文：List of players to receive this particle, or null for all players in the
     *                  world
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder receivers(final Player @Nullable... receivers) {
        this.receivers = receivers != null ? Lists.newArrayList(receivers) : null;
        return this;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 选择粒子位置周围指定边界框内长方体范围中的所有玩家。如需更接近球形的检测，请参阅 {@link #receivers(int,
     * boolean)}
     * <p>原文：
     * Selects all players within a cuboid selection around the particle location, within the
     * specified bounding box. If you want a more spherical check, see {@link #receivers(int,
     * boolean)}
     *
     * @param radius 在所有轴上增加的量
     * <p>原文：amount to add on all axis
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder receivers(final int radius) {
        return this.receivers(radius, radius);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 选择粒子位置周围指定半径内的所有玩家。如果 byDistance 为 false，将使用与 {@link #receivers(int, int)} 相同的长方体选择；如果 byDistance 为 true，将按球形距离检测半径
     * <p>原文：
     * Selects all players within the specified radius around the particle location. If byDistance is
     * false, behavior uses cuboid selection the same as {@link #receivers(int, int)} If byDistance is
     * true, radius is tested by distance in a spherical shape
     *
     * @param radius 在每个轴上增加的量
     * <p>原文：amount to add on each axis
     * @param byDistance true 表示使用球形半径，false 表示使用长方体
     * <p>原文：true to use a spherical radius, false to use a cuboid
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder receivers(final int radius, final boolean byDistance) {
        if (!byDistance) {
            return this.receivers(radius, radius, radius);
        } else {
            if (this.location == null) {
                throw new IllegalStateException("Please set location first");
            }
            this.receivers = Lists.newArrayList();
            for (final Player nearbyPlayer : this.location.getWorld()
                .getNearbyPlayers(this.location, radius, radius, radius)) {
                final Location loc = nearbyPlayer.getLocation();
                final double x = NumberConversions.square(this.location.getX() - loc.getX());
                final double y = NumberConversions.square(this.location.getY() - loc.getY());
                final double z = NumberConversions.square(this.location.getZ() - loc.getZ());
                if (Math.sqrt(x + y + z) > radius) {
                    continue;
                }
                this.receivers.add(nearbyPlayer);
            }
            return this;
        }
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 选择粒子位置周围指定边界框内长方体范围中的所有玩家。允许指定与 X、Z 不同的 Y 尺寸。如需更接近圆柱的检测，请参阅 {@link #receivers(int, int, boolean)}；如需更接近球形的检测，请参阅 {@link #receivers(int, boolean)}
     * <p>原文：
     * Selects all players within a cuboid selection around the particle location, within the
     * specified bounding box. Allows specifying a different Y size than X and Z If you want a more
     * cylinder check, see {@link #receivers(int, int, boolean)} If you want a more spherical check,
     * see {@link #receivers(int, boolean)}
     *
     * @param xzRadius 在 x/z 轴上增加的量
     * <p>原文：amount to add on the x/z axis
     * @param yRadius 在 y 轴上增加的量
     * <p>原文：amount to add on the y axis
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder receivers(final int xzRadius, final int yRadius) {
        return this.receivers(xzRadius, yRadius, xzRadius);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 选择粒子位置周围指定半径内的所有玩家。如果 byDistance 为 false，将使用与 {@link #receivers(int, int)} 相同的长方体选择；如果 byDistance 为 true，将分别在 y 方向和 x/z 平面上按距离检测半径，呈圆柱形。
     * <p>原文：
     * Selects all players within the specified radius around the particle location. If byDistance is
     * false, behavior uses cuboid selection the same as {@link #receivers(int, int)} If byDistance is
     * true, radius is tested by distance on the y plane and on the x/z plane, in a cylinder shape.
     *
     * @param xzRadius 在 x/z 轴上增加的量
     * <p>原文：amount to add on the x/z axis
     * @param yRadius 在 y 轴上增加的量
     * <p>原文：amount to add on the y axis
     * @param byDistance true 表示使用圆柱形，false 表示使用长方体
     * <p>原文：true to use a cylinder shape, false to use cuboid
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     * @throws IllegalStateException 如果尚未指定位置
     * <p>原文：if a location hasn't been specified yet
     */
    public ParticleBuilder receivers(final int xzRadius, final int yRadius, final boolean byDistance) {
        if (!byDistance) {
            return this.receivers(xzRadius, yRadius, xzRadius);
        } else {
            if (this.location == null) {
                throw new IllegalStateException("Please set location first");
            }
            this.receivers = Lists.newArrayList();
            for (final Player nearbyPlayer : this.location.getWorld()
                .getNearbyPlayers(this.location, xzRadius, yRadius, xzRadius)) {
                final Location loc = nearbyPlayer.getLocation();
                if (Math.abs(loc.getY() - this.location.getY()) > yRadius) {
                    continue;
                }
                final double x = NumberConversions.square(this.location.getX() - loc.getX());
                final double z = NumberConversions.square(this.location.getZ() - loc.getZ());
                if (x + z > NumberConversions.square(xzRadius)) {
                    continue;
                }
                this.receivers.add(nearbyPlayer);
            }
            return this;
        }
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 选择粒子位置周围指定边界框内长方体范围中的所有玩家。如需更接近圆柱的检测，请参阅 {@link #receivers(int, int,
     * boolean)}；如需更接近球形的检测，请参阅 {@link #receivers(int, boolean)}
     * <p>原文：
     * Selects all players within a cuboid selection around the particle location, within the
     * specified bounding box. If you want a more cylinder check, see {@link #receivers(int, int,
     * boolean)} If you want a more spherical check, see {@link #receivers(int, boolean)}
     *
     * @param xRadius 在 x 轴上增加的量
     * <p>原文：amount to add on the x axis
     * @param yRadius 在 y 轴上增加的量
     * <p>原文：amount to add on the y axis
     * @param zRadius 在 z 轴上增加的量
     * <p>原文：amount to add on the z axis
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder receivers(final int xRadius, final int yRadius, final int zRadius) {
        if (this.location == null) {
            throw new IllegalStateException("Please set location first");
        }
        return this.receivers(this.location.getWorld().getNearbyPlayers(this.location, xRadius, yRadius, zRadius));
    }

    /**
     * @return 被视为此粒子来源的玩家（用于可见性处理），或 null
     * <p>原文：The player considered the source of this particle (for Visibility concerns), or null
     */
    public @Nullable Player source() {
        return this.source;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此粒子的来源，用于可见性处理（Vanish API）
     * <p>原文：
     * Sets the source of this particle for visibility concerns (Vanish API)
     *
     * @param source 被视为来源的玩家
     * <p>原文：The player who is considered the source
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder source(final @Nullable Player source) {
        this.source = source;
        return this;
    }

    /**
     * @return 粒子将生成的位置
     * <p>原文：Location of where the particle will spawn
     */
    public @Nullable Location location() {
        return this.location;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置生成粒子的位置
     * <p>原文：
     * Sets the location of where to spawn the particle
     *
     * @param location 粒子的位置
     * <p>原文：The location of the particle
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder location(final Location location) {
        this.location = location.clone();
        return this;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置生成粒子的位置
     * <p>原文：
     * Sets the location of where to spawn the particle
     *
     * @param world 生成粒子的世界
     * <p>原文：World to spawn particle in
     * @param x X 位置
     * <p>原文：X location
     * @param y Y 位置
     * <p>原文：Y location
     * @param z Z 位置
     * <p>原文：Z location
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder location(final World world, final double x, final double y, final double z) {
        this.location = new Location(world, x, y, z);
        return this;
    }

    /**
     * @return 要生成的粒子数量
     * <p>原文：Number of particles to spawn
     */
    public int count() {
        return this.count;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置要生成的粒子数量
     * <p>原文：
     * Sets the number of particles to spawn
     *
     * @param count 粒子数量
     * <p>原文：Number of particles
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder count(final int count) {
        this.count = count;
        return this;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 粒子 X 偏移。其用途因粒子而异
     * <p>原文：
     * Particle offset X. Varies by particle on how this is used
     *
     * @return 粒子 X 偏移。
     * <p>原文：Particle offset X.
     */
    public double offsetX() {
        return this.offsetX;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 粒子 Y 偏移。其用途因粒子而异
     * <p>原文：
     * Particle offset Y. Varies by particle on how this is used
     *
     * @return 粒子 Y 偏移。
     * <p>原文：Particle offset Y.
     */
    public double offsetY() {
        return this.offsetY;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 粒子 Z 偏移。其用途因粒子而异
     * <p>原文：
     * Particle offset Z. Varies by particle on how this is used
     *
     * @return 粒子 Z 偏移。
     * <p>原文：Particle offset Z.
     */
    public double offsetZ() {
        return this.offsetZ;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子偏移。其用途因粒子而异
     * <p>原文：
     * Sets the particle offset. Varies by particle on how this is used
     *
     * @param offsetX 粒子 X 偏移
     * <p>原文：Particle offset X
     * @param offsetY 粒子 Y 偏移
     * <p>原文：Particle offset Y
     * @param offsetZ 粒子 Z 偏移
     * <p>原文：Particle offset Z
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder offset(final double offsetX, final double offsetY, final double offsetZ) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        return this;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取粒子的额外数据。其用途因粒子而异
     * <p>原文：
     * Gets the Particle extra data. Varies by particle on how this is used
     *
     * @return 额外的粒子数据
     * <p>原文：the extra particle data
     */
    public double extra() {
        return this.extra;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子的额外数据。其用途因粒子而异
     * <p>原文：
     * Sets the particle extra data. Varies by particle on how this is used
     *
     * @param extra 额外的粒子数据
     * <p>原文：the extra particle data
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder extra(final double extra) {
        this.extra = extra;
        return this;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取粒子的自定义数据。其用途因粒子而异
     * <p>原文：
     * Gets the particle custom data. Varies by particle on how this is used
     *
     * @param <T> 粒子数据类型
     * <p>原文：The Particle data type
     * @return 此粒子的 ParticleData
     * <p>原文：the ParticleData for this particle
     */
    public @Nullable <T> T data() {
        //noinspection unchecked
        return (T) this.data;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子的自定义数据。其用途因粒子而异
     * <p>原文：
     * Sets the particle custom data. Varies by particle on how this is used
     *
     * @param data 新的粒子数据
     * <p>原文：The new particle data
     * @param <T> 粒子数据类型
     * <p>原文：The Particle data type
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public <T> ParticleBuilder data(final @Nullable T data) {
        this.data = data;
        return this;
    }

    /**
     * @return 粒子是否被强制显示给玩家。
     * <p>原文：whether the particle is forcefully shown to players.
     */
    public boolean force() {
        return this.force;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子是否强制显示给玩家。如果强制，粒子将在玩家视距允许的范围内远距离显示；如果为 false，粒子将根据客户端的粒子设置显示。
     * <p>原文：
     * Sets whether the particle is forcefully shown to the player. If forced, the particle will show
     * faraway, as far as the player's view distance allows. If false, the particle will show
     * according to the client's particle settings.
     *
     * @param force true 表示强制，false 表示正常
     * <p>原文：true to force, false for normal
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder force(final boolean force) {
        this.force = force;
        return this;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子颜色。仅对数据类型为 {@link Color}、{@link Particle.DustOptions} 或 {@link Particle.Spell} 的粒子有效。
     * <p>原文：
     * Sets the particle Color.
     * Only valid for particles with a data type of {@link Color}, {@link Particle.DustOptions} or {@link Particle.Spell}.
     *
     * @param color 新的粒子颜色
     * <p>原文：the new particle color
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder color(final @Nullable Color color) {
        if (this.particle.getDataType() == Color.class) {
            return this.data(color);
        }
        return this.color(color, 1);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子的颜色以及大小或强度。仅对数据类型为 {@link Particle.DustOptions} 或 {@link Particle.Spell} 的粒子有效。
     * <p>原文：
     * Sets the particle Color and size or power.
     * Only valid for particles with a data type of {@link Particle.DustOptions} or {@link Particle.Spell}.
     *
     * @param color 新的粒子颜色
     * <p>原文：the new particle color
     * @param value 粒子的大小或强度
     * <p>原文：the size or power of the particle
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder color(final @Nullable Color color, final float value) {
        if (this.particle.getDataType() != Particle.DustOptions.class && this.particle.getDataType() != Particle.Spell.class && color != null) {
            throw new IllegalStateException("The combination of Color and float value cannot be set on this particle type.");
        }

        // We don't officially support reusing these objects, but here we go
        if (color == null) {
            if (this.data instanceof Particle.DustOptions || this.data instanceof Particle.Spell) {
                return this.data(null);
            } else {
                return this;
            }
        }

        if (this.particle.getDataType() == Particle.DustOptions.class) {
            return this.data(new Particle.DustOptions(color, value));
        } else {
            return this.data(new Particle.Spell(color, value));
        }
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子颜色。仅对数据类型为 {@link Color}、{@link Particle.DustOptions} 或 {@link Particle.Spell} 的粒子有效。
     * <p>原文：
     * Sets the particle Color.
     * Only valid for particles with a data type of {@link Color}, {@link Particle.DustOptions} or {@link Particle.Spell}.
     *
     * @param r 红色分量
     * <p>原文：red color component
     * @param g 绿色分量
     * <p>原文：green color component
     * @param b 蓝色分量
     * <p>原文：blue color component
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder color(final int r, final int g, final int b) {
        return this.color(Color.fromRGB(r, g, b));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子颜色。仅对数据类型为 {@link Color}、{@link Particle.DustOptions} 或 {@link Particle.Spell} 的粒子有效。
     * <p>
     * 该方法会检测提供的颜色整数是 RGB 还是 ARGB 格式：若 alpha 通道为零，则视为 RGB，否则视为 ARGB。
     * <p>原文：
     * Sets the particle Color.
     * Only valid for particles with a data type of {@link Color}, {@link Particle.DustOptions} or {@link Particle.Spell}.
     * <p>
     * This method detects if the provided color integer is in RGB or ARGB format.
     * If the alpha channel is zero, it treats the color as RGB. Otherwise, it treats it as ARGB.
     *
     * @param color 表示颜色分量的整数。如果最高字节（alpha 通道）为零，则颜色被视为 RGB；否则视为 ARGB。
     * <p>原文：an integer representing the color components. If the highest byte (alpha channel) is zero,
     *              the color is treated as RGB. Otherwise, it is treated as ARGB.
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder color(final int color) {
        final int alpha = (color >> 24) & 0xFF;
        if (alpha == 0) {
            return this.color(Color.fromRGB(color));
        }
        return this.color(Color.fromARGB(color));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子颜色。仅对数据类型为 {@link Color}、{@link Particle.DustOptions} 或 {@link Particle.Spell} 的粒子有效。
     * <p>原文：
     * Sets the particle Color.
     * Only valid for particles with a data type of {@link Color}, {@link Particle.DustOptions} or {@link Particle.Spell}.
     *
     * @param a alpha 颜色分量
     * <p>原文：alpha color component
     * @param r 红色分量
     * <p>原文：red color component
     * @param g 绿色分量
     * <p>原文：green color component
     * @param b 蓝色分量
     * <p>原文：blue color component
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     */
    public ParticleBuilder color(final int a, final int r, final int g, final int b) {
        return this.color(Color.fromARGB(a, r, g, b));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子的颜色过渡。仅对 {@link Particle#DUST_COLOR_TRANSITION} 有效。
     * <p>原文：
     * Sets the particle Color Transition.
     * Only valid for {@link Particle#DUST_COLOR_TRANSITION}.
     *
     * @param fromColor 新的粒子起始颜色
     * <p>原文：the new particle from color
     * @param toColor 新的粒子目标颜色
     * <p>原文：the new particle to color
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     * @throws IllegalArgumentException 如果粒子构建器的 {@link #particle()} 不是 {@link Particle#DUST_COLOR_TRANSITION}。
     * <p>原文：if the particle builder's {@link #particle()} isn't {@link Particle#DUST_COLOR_TRANSITION}.
     */
    public ParticleBuilder colorTransition(final Color fromColor, final Color toColor) {
        return this.colorTransition(fromColor, toColor, 1);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子的颜色过渡。仅对 {@link Particle#DUST_COLOR_TRANSITION} 有效。
     * <p>原文：
     * Sets the particle Color Transition.
     * Only valid for {@link Particle#DUST_COLOR_TRANSITION}.
     *
     * @param fromRed “起始”颜色的红色分量
     * <p>原文：red color component for the "from" color
     * @param fromGreen “起始”颜色的绿色分量
     * <p>原文：green color component for the "from" color
     * @param fromBlue “起始”颜色的蓝色分量
     * <p>原文：blue color component for the "from" color
     * @param toRed 目标颜色的红色分量
     * <p>原文：red color component for the to color
     * @param toGreen 目标颜色的绿色分量
     * <p>原文：green color component for the to color
     * @param toBlue 目标颜色的蓝色分量
     * <p>原文：blue color component for the to color
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     * @throws IllegalArgumentException 如果粒子构建器的 {@link #particle()} 不是 {@link Particle#DUST_COLOR_TRANSITION}。
     * <p>原文：if the particle builder's {@link #particle()} isn't {@link Particle#DUST_COLOR_TRANSITION}.
     */
    public ParticleBuilder colorTransition(
        final int fromRed, final int fromGreen, final int fromBlue,
        final int toRed, final int toGreen, final int toBlue
    ) {
        return this.colorTransition(Color.fromRGB(fromRed, fromGreen, fromBlue), Color.fromRGB(toRed, toGreen, toBlue));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子的颜色过渡。仅对 {@link Particle#DUST_COLOR_TRANSITION} 有效。
     * <p>原文：
     * Sets the particle Color Transition.
     * Only valid for {@link Particle#DUST_COLOR_TRANSITION}.
     *
     * @param fromRgb 表示“起始”颜色的红、绿、蓝分量的整数
     * <p>原文：an integer representing the red, green, and blue color components for the "from" color
     * @param toRgb 表示“目标”颜色的红、绿、蓝分量的整数
     * <p>原文：an integer representing the red, green, and blue color components for the "to" color
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     * @throws IllegalArgumentException 如果粒子构建器的 {@link #particle()} 不是 {@link Particle#DUST_COLOR_TRANSITION}。
     * <p>原文：if the particle builder's {@link #particle()} isn't {@link Particle#DUST_COLOR_TRANSITION}.
     */
    public ParticleBuilder colorTransition(final int fromRgb, final int toRgb) {
        return this.colorTransition(Color.fromRGB(fromRgb), Color.fromRGB(toRgb));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置粒子的颜色过渡和大小。仅对 {@link Particle#DUST_COLOR_TRANSITION} 有效。
     * <p>原文：
     * Sets the particle Color Transition and size.
     * Only valid for {@link Particle#DUST_COLOR_TRANSITION}.
     *
     * @param fromColor “起始”颜色的新粒子颜色。
     * <p>原文：the new particle color for the "from" color.
     * @param toColor “目标”颜色的新粒子颜色。
     * <p>原文：the new particle color for the "to" color.
     * @param size 粒子的大小
     * <p>原文：the size of the particle
     * @return 对此对象的引用。
     * <p>原文：a reference to this object.
     * @throws IllegalArgumentException 如果粒子构建器的 {@link #particle()} 不是 {@link Particle#DUST_COLOR_TRANSITION}。
     * <p>原文：if the particle builder's {@link #particle()} isn't {@link Particle#DUST_COLOR_TRANSITION}.
     */
    public ParticleBuilder colorTransition(final Color fromColor, final Color toColor, final float size) {
        Preconditions.checkArgument(fromColor != null, "Cannot define color transition with null fromColor.");
        Preconditions.checkArgument(toColor != null, "Cannot define color transition with null toColor.");
        Preconditions.checkArgument(this.particle() == Particle.DUST_COLOR_TRANSITION, "Can only define a color transition on particle DUST_COLOR_TRANSITION.");
        return this.data(new Particle.DustTransition(fromColor, toColor, size));
    }

    @Override
    public ParticleBuilder clone() {
        try {
            final ParticleBuilder builder = (ParticleBuilder) super.clone();
            if (this.location != null) builder.location = this.location.clone();
            if (this.receivers != null) builder.receivers = new ObjectArrayList<>(this.receivers);
            return builder;
        } catch (final CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
