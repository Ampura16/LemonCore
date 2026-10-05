package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.datacomponent.item.blocksattacks.DamageReduction;
import io.papermc.paper.datacomponent.item.blocksattacks.ItemDamageFunction;
import io.papermc.paper.registry.tag.TagKey;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.damage.DamageType;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有对手持玩家的格挡攻击能力，例如盾牌。
 * <p>原文：
 * Holds block attacks to the holding player like Shield.
 *
 * @see io.papermc.paper.datacomponent.DataComponentTypes#BLOCKS_ATTACKS
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface BlocksAttacks {

    @Contract(value = "-> new", pure = true)
    static Builder blocksAttacks() {
        return ItemComponentTypesBridge.bridge().blocksAttacks();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取必须持续使用多长时间（秒）后才能成功格挡攻击。
     * <p>原文：
     * Gets the amount of time (in seconds) that use must be held before successfully blocking attacks.
     *
     * @return 延迟（秒）
     * <p>原文：the delay in seconds
     */
    @Contract(pure = true)
    @NonNegative float blockDelaySeconds();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取物品被禁用性攻击击中时，应用于物品冷却时间的乘数（即 {@link Weapon#disableBlockingForSeconds()} 的乘数）。<br> 如果设为 0，此物品永远不会被攻击禁用。
     * <p>原文：
     * Gets the multiplier applied to the cooldown time for the item when attacked by a disabling attack (the multiplier for {@link Weapon#disableBlockingForSeconds()}).
     * <br>
     * If set to 0, this item can never be disabled by attacks.
     *
     * @return 冷却时间的倍率
     * <p>原文：the multiplier for the cooldown time
     */
    @Contract(pure = true)
    @NonNegative float disableCooldownScale();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取一个 {@link DamageReduction} 列表，表示在给定攻击中应格挡多少伤害。
     * <p>原文：
     * Gets a list of {@link DamageReduction} of how much damage should be blocked in a given attack.
     *
     * @return 伤害减免列表
     * <p>原文：a list of damage reductions
     */
    @Contract(pure = true)
    List<DamageReduction> damageReductions();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取给定攻击应对物品施加的损伤值。
     * <p>原文：
     * Gets how much damage should be applied to the item from a given attack.
     *
     * @return 伤害函数
     * <p>原文：the damage function
     */
    @Contract(pure = true)
    ItemDamageFunction itemDamage();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取可以绕过格挡的 DamageType。
     * <p>原文：
     * Gets the DamageType that can bypass the blocking.
     *
     * @return 伤害类型标签键；如果没有该标签键则为 null
     * <p>原文：a damage type tag key, or null if there is no such tag key
     */
    @Contract(pure = true)
    @Nullable TagKey<DamageType> bypassedBy();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取成功格挡攻击时播放的音效键。
     * <p>原文：
     * Gets the key sound to play when an attack is successfully blocked.
     *
     * @return 声音的键
     * <p>原文：a key of the sound
     */
    @Contract(pure = true)
    @Nullable Key blockSound();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取物品因攻击而进入禁用冷却时播放的音效键。
     * <p>原文：
     * Gets the key sound to play when the item goes on its disabled cooldown due to an attack.
     *
     * @return 声音的键
     * <p>原文：a key of the sound
     */
    @Contract(pure = true)
    @Nullable Key disableSound();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link BlocksAttacks} 的构建器。
     * <p>原文：
     * Builder for {@link BlocksAttacks}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<BlocksAttacks> {

        @Contract(value = "_ -> this", mutates = "this")
        Builder blockDelaySeconds(@NonNegative float delay);

        @Contract(value = "_ -> this", mutates = "this")
        Builder disableCooldownScale(@NonNegative float scale);

        @Contract(value = "_ -> this", mutates = "this")
        Builder addDamageReduction(DamageReduction reduction);

        @Contract(value = "_ -> this", mutates = "this")
        Builder damageReductions(List<DamageReduction> reductions);

        @Contract(value = "_ -> this", mutates = "this")
        Builder itemDamage(ItemDamageFunction function);

        @Contract(value = "_ -> this", mutates = "this")
        Builder bypassedBy(@Nullable TagKey<DamageType> bypassedBy);

        @Contract(value = "_ -> this", mutates = "this")
        Builder blockSound(@Nullable Key sound);

        @Contract(value = "_ -> this", mutates = "this")
        Builder disableSound(@Nullable Key sound);
    }
}
