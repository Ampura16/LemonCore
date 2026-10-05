package org.bukkit.block;

import java.util.Collection;
import org.bukkit.Nameable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表信标.
 * <p>原文：
 * Represents a captured state of a beacon.
 */
public interface Beacon extends io.papermc.paper.block.LockableTileState { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回在此信标影响范围内受其效果影响的所有玩家.
     * <p>
     * 如果本状态代表的方块不再是信标, 将返回一个空列表(不是null).
     * <p>原文：
     * Returns the list of players within the beacon's range of effect.
     * <p>
     * This will return an empty list if the block represented by this state is
     * no longer a beacon.
     *
     * @return 范围内的玩家
     * <p>原文：the players in range
     * @throws IllegalStateException 如果方块尚未被放置
     * <p>原文：if this block state is not placed
     */
    @NotNull
    Collection<LivingEntity> getEntitiesInRange();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回信标金字塔的层叠数 (0-4). 层数代表信标的驱动力等级 (等级越高影响范围越大),
     * 取决于金字塔方块的层数. 层叠数为1时信标底下至少有一层9个方块
     * (译注:由于我们可以塑造各种形状的金字塔, 金字塔总方块数是不固定的).
     * <p>原文：
     * Returns the tier of the beacon pyramid (0-4). The tier refers to the
     * beacon's power level, based on how many layers of blocks are in the
     * pyramid. Tier 1 refers to a beacon with one layer of 9 blocks under it.
     *
     * @return 信标金字塔层叠数
     * <p>原文：the beacon tier
     */
    int getTier();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回信标设置的主效果.
     * <p>原文：
     * Returns the primary effect set on the beacon
     *
     * @return 主效果, 若未设置则为null
     * <p>原文：the primary effect or null if not set
     */
    @Nullable
    PotionEffect getPrimaryEffect();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置信标的主效果, 如要清除则为 null.
     * <p>原文：
     * Set the primary effect on this beacon, or null to clear.
     *
     * @param effect 新主效果
     * <p>原文：new primary effect
     */
    void setPrimaryEffect(@Nullable PotionEffectType effect);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回信标设置的辅助效果.
     * <p>原文：
     * Returns the secondary effect set on the beacon.
     *
     * @return 辅助效果, 若未设置则为null
     * <p>原文：the secondary effect or null if no secondary effect
     */
    @Nullable
    PotionEffect getSecondaryEffect();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置信标的辅助效果, 如要清除则为 null. 请注意层数必须 &gt;= 4 才能使辅助效果生效.
     * <p>原文：
     * Set the secondary effect on this beacon, or null to clear. Note that tier
     * must be &gt;= 4 for this effect to be active.
     *
     * @param effect 辅助效果
     * <p>原文：desired secondary effect
     */
    void setSecondaryEffect(@Nullable PotionEffectType effect);

    // Paper start - Custom effect ranges
    /**
     * Gets the effect range of this beacon.
     * A negative range value means the beacon is using its default range based on tier.
     * @return Either the custom range set with {@link #setEffectRange(double)} or the range based on the beacon tier.
     */
    double getEffectRange();

    /**
     * Sets the effect range of the beacon
     * A negative range value means the beacon is using its default range based on tier.
     * @param range Radius of effect range.
     */
    void setEffectRange(double range);

    /**
     * Resets the custom range from this beacon and falls back to the range based on the beacon tier.
     * Shortcut for setting the effect range to a negative number.
     */
    void resetEffectRange();
    // Paper end
}
