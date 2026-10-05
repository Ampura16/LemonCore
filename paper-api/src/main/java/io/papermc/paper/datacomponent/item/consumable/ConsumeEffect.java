package io.papermc.paper.datacomponent.item.consumable;

import io.papermc.paper.registry.set.RegistryKeySet;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 食用物品时产生的效果。
 * <p>原文：
 * Effect that occurs when consuming an item.
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ConsumeEffect {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建食用时随机传送实体的食用效果。
     * <p>原文：
     * Creates a consume effect that randomly teleports the entity on consumption.
     *
     * @param diameter 随机传送的直径
     * <p>原文：diameter of random teleportation
     * @return 效果实例
     * <p>原文：the effect instance
     */
    @Contract(value = "_ -> new", pure = true)
    static TeleportRandomly teleportRandomlyEffect(final float diameter) {
        return ConsumableTypesBridge.bridge().teleportRandomlyEffect(diameter);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建食用时移除状态效果的食用效果。
     * <p>原文：
     * Creates a consume effect that removes status effects on consumption.
     *
     * @param effects 要移除的药水效果
     * <p>原文：the potion effects to remove
     * @return 效果实例
     * <p>原文：the effect instance
     */
    @Contract(value = "_ -> new", pure = true)
    static RemoveStatusEffects removeEffects(final RegistryKeySet<PotionEffectType> effects) {
        return ConsumableTypesBridge.bridge().removeStatusEffects(effects);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建食用时播放声音的食用效果。
     * <p>原文：
     * Creates a consume effect that plays a sound on consumption.
     *
     * @param key 要播放的钥匙音效
     * <p>原文：the key sound effect to play
     * @return 效果实例
     * <p>原文：the effect instance
     */
    @Contract(value = "_ -> new", pure = true)
    static PlaySound playSoundConsumeEffect(final Key key) {
        return ConsumableTypesBridge.bridge().playSoundEffect(key);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建清除所有状态效果的食用效果。
     * <p>原文：
     * Creates a consume effect that clears all status effects.
     *
     * @return 效果实例
     * <p>原文：the effect instance
     */
    @Contract(value = "-> new", pure = true)
    static ClearAllStatusEffects clearAllStatusEffects() {
        return ConsumableTypesBridge.bridge().clearAllStatusEffects();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建食用时给予药水效果的食用效果。
     * <p>原文：
     * Creates a consume effect that gives potion effects on consumption.
     *
     * @param effects 要施加的药水效果
     * <p>原文：the potion effects to apply
     * @param probability 施加这些效果的概率，介于 0 到 1 之间（含两端）
     * <p>原文：the probability of these effects being applied, between 0 and 1 inclusive
     * @return 效果实例
     * <p>原文：the effect instance
     */
    @Contract(value = "_, _ -> new", pure = true)
    static ApplyStatusEffects applyStatusEffects(final List<PotionEffect> effects, final float probability) {
        return ConsumableTypesBridge.bridge().applyStatusEffects(effects, probability);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示食用时随机传送实体的食用效果。
     * <p>原文：
     * Represents a consumable effect that randomly teleports the entity on consumption.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface TeleportRandomly extends ConsumeEffect {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 实体可被传送的最大范围。
         * <p>原文：
         * The max range that the entity can be teleported to.
         *
         * @return 传送直径
         * <p>原文：teleportation diameter
         */
        float diameter();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示食用时移除状态效果的食用效果。
     * <p>原文：
     * Represents a consumable effect that removes status effects on consumption.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface RemoveStatusEffects extends ConsumeEffect {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 要移除的药水效果。
         * <p>原文：
         * Potion effects to remove.
         *
         * @return 效果
         * <p>原文：effects
         */
        RegistryKeySet<PotionEffectType> removeEffects();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示食用时播放声音的食用效果。
     * <p>原文：
     * Represents a consumable effect that plays a sound on consumption.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface PlaySound extends ConsumeEffect {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 在世界中播放的音效。
         * <p>原文：
         * Sound effect to play in the world.
         *
         * @return 音效
         * <p>原文：sound effect
         */
        Key sound();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示食用时清除所有效果的食用效果。
     * <p>原文：
     * Represents a consumable effect that clears all effects on consumption.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface ClearAllStatusEffects extends ConsumeEffect {

    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示食用时按概率施加药水效果的食用效果。
     * <p>原文：
     * Represents a consumable effect that applies potion effects based on a probability on consumption.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface ApplyStatusEffects extends ConsumeEffect {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 要给予的药水效果实例。
         * <p>原文：
         * Potion effect instances to grant.
         *
         * @return 药水效果
         * <p>原文：potion effects
         */
        List<PotionEffect> effects();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 0 到 1 之间的浮点数，表示效果被施加的几率。
         * <p>原文：
         * Float between 0 and 1, chance for the effect to be applied.
         *
         * @return 几率
         * <p>原文：chance
         */
        float probability();
    }
}
