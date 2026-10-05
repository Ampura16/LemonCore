package org.bukkit.entity;

import org.bukkit.inventory.AbstractHorseInventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个马基对象.
 * 译注: 基于马的对象,比如羊驼,驴.
 * <p>原文：
 * Represents a Horse-like creature.
 */
public interface AbstractHorse extends Vehicle, InventoryHolder, Tameable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此马的变种.
     * <p>
     * 马的变种决定了其外观和能力. 一匹马是普通马、驴、骡还是其他种类的马由变种决定.
     * <p>原文：
     * Gets the horse's variant.
     * <p>
     * A horse's variant defines its physical appearance and capabilities.
     * Whether a horse is a regular horse, donkey, mule, or other kind of horse
     * is determined using the variant.
     *
     * @return 代表此马变种的 {@link Horse.Variant}
     * <p>原文：a {@link Horse.Variant} representing the horse's variant
     * @deprecated 不同变种属于不同类
     * <p>原文：different variants are different classes
     */
    @Deprecated(since = "1.11", forRemoval = true)
    @NotNull
    public Horse.Variant getVariant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param variant 变种
     * <p>原文：variant
     * @deprecated 需要生成一个不同的实体
     * <p>原文：you are required to spawn a different entity
     */
    @Deprecated(since = "1.11", forRemoval = true)
    @Contract("_ -> fail")
    public void setVariant(Horse.Variant variant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此马的驯化等级.
     * <p>
     * 更高的驯化等级表明此马更容易被驯服, 当此马的驯化等级接近最高驯化等级时,驯服的几率会增加.
     * <p>原文：
     * Gets the domestication level of this horse.
     * <p>
     * A higher domestication level indicates that the horse is closer to
     * becoming tame. As the domestication level gets closer to the max
     * domestication level, the chance of the horse becoming tame increases.
     *
     * @return 驯化等级
     * <p>原文：domestication level
     */
    public int getDomestication();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此马的驯化等级.
     * <p>
     * 设置此马的驯化等级更高将让这匹马的驯化几率提升.
     * <p>
     * 驯化等级必须 大于0 且 小于最高驯化等级, 由 {@link #getMaxDomestication()} 定义.
     * <p>原文：
     * Sets the domestication level of this horse.
     * <p>
     * Setting the domestication level to a high value will increase the
     * horse's chances of becoming tame.
     * <p>
     * Domestication level must be greater than zero and no greater than
     * the max domestication level of the horse, determined with
     * {@link #getMaxDomestication()}
     *
     * @param level 驯化等级
     * <p>原文：domestication level
     */
    public void setDomestication(int level);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此马的最高驯化等级.
     * <p>
     * 此等级越高, 驯化的时间将会更长.
     * <p>原文：
     * Gets the maximum domestication level of this horse.
     * <p>
     * The higher this level is, the longer it will likely take
     * for the horse to be tamed.
     *
     * @return 最高驯化等级
     * <p>原文：the max domestication level
     */
    public int getMaxDomestication();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此马的最高驯化等级.
     * 设定一个更高的驯化等级将会增加需要驯化的次数(例如喂食, 骑乘), 反之减少.
     * <p>
     * 最高驯化等级必须大于0.
     * <p>原文：
     * Sets the maximum domestication level of this horse.
     * <p>
     * Setting a higher max domestication will increase the amount of
     * domesticating (feeding, riding, etc.) necessary in order to tame it,
     * while setting a lower max value will have the opposite effect.
     * <p>
     * Maximum domestication must be greater than zero.
     *
     * @param level 最高驯化等级
     * <p>原文：the max domestication level
     */
    public void setMaxDomestication(int level);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此马的跳跃强度.
     * <p>
     * 跳跃强度定义这匹马能跳多高. 更高的跳跃等级将增加跳跃高度.
     * <p>原文：
     * Gets the jump strength of this horse.
     * <p>
     * Jump strength defines how high the horse can jump. A higher jump strength
     * increases how high a jump will go.
     *
     * @return 跳跃等级
     * <p>原文：the horse's jump strength
     */
    public double getJumpStrength();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此马的跳跃强度.
     * <p>
     * 跳跃强度定义这匹马能跳多高. 更高的跳跃等级将增加跳跃高度.
     * 设置为0将导致马无法跳跃.
     * 只能设置0至2之间的数值.
     * <p>原文：
     * Sets the jump strength of this horse.
     * <p>
     * A higher jump strength increases how high a jump will go.
     * Setting a jump strength to 0 will result in no jump.
     * You cannot set a jump strength to a value below 0 or
     * above 2.
     *
     * @param strength 跳跃等级
     * <p>原文：jump strength for this horse
     */
    public void setJumpStrength(double strength);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此马是否正在吃干草.
     * <p>原文：
     * Gets whether the horse is currently grazing hay.
     *
     * @return 如果正在吃干草则为 true
     * <p>原文：true if eating hay
     * @deprecated use {@link #isEatingGrass()}, this name is incorrect
     */
    @Deprecated(forRemoval = true)
    boolean isEatingHaystack();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此马是否正在吃干草.
     * <p>原文：
     * Sets whether the horse is grazing hay.
     *
     * @param eatingHaystack 新的干草进食状态
     * <p>原文：new hay grazing status
     * @deprecated use {@link #setEatingGrass(boolean)}, this name is incorrect
     */
    @Deprecated(forRemoval = true)
    void setEatingHaystack(boolean eatingHaystack);

    @NotNull
    @Override
    public AbstractHorseInventory getInventory();

    // Paper start - Horse API
    /**
     * Gets if a horse is in their eating grass animation.
     *
     * @return eating grass animation is active
     */
    public boolean isEatingGrass();

    /**
     * Sets if a horse is in their eating grass animation.
     *
     * <p>When true, the horse will lower its neck.</p>
     *
     * @param eating eating grass animation is active
     */
    public void setEatingGrass(boolean eating);

    /**
     * Gets if a horse is in their rearing animation.
     *
     * @return rearing animation is active
     */
    public boolean isRearing();

    /**
     * Sets if a horse is in their rearing animation.
     *
     * <p>When true, the horse will stand on its hind legs.</p>
     *
     * @param rearing rearing animation is active
     */
    public void setRearing(boolean rearing);

    /**
     * Gets if a horse is in their eating animation.
     *
     * @return eating animation is active
     */
    public boolean isEating();

    /**
     * Sets if a horse is in their eating animation.
     *
     * <p>When true, the horse will bob its head.</p>
     *
     * @param eating eating animation is active
     */
    public void setEating(boolean eating);
    // Paper end - Horse API
}
