package org.bukkit.event.entity;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 储存伤害事件的数据
 * <p>原文：
 * Stores data for damage events
 */
public class EntityDamageEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private static final DamageModifier[] MODIFIERS = DamageModifier.values();
    private static final Function<? super Double, Double> ZERO = Functions.constant(-0.0);
    private final Map<DamageModifier, Double> modifiers;
    private final Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions;
    private final Map<DamageModifier, Double> originals;
    private final DamageCause cause;
    private final DamageSource damageSource;

    private boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(since = "1.20.4", forRemoval = true)
    public EntityDamageEvent(@NotNull final Entity damagee, @NotNull final DamageCause cause, final double damage) {
        this(damagee, cause, DamageSource.builder(DamageType.GENERIC).build(), damage);
    }

    @ApiStatus.Internal
    public EntityDamageEvent(@NotNull final Entity damagee, @NotNull final DamageCause cause, @NotNull final DamageSource damageSource, final double damage) {
        this(damagee, cause, damageSource, new EnumMap<>(ImmutableMap.of(DamageModifier.BASE, damage)), new EnumMap<DamageModifier, Function<? super Double, Double>>(ImmutableMap.of(DamageModifier.BASE, ZERO)));
    }

    @ApiStatus.Internal
    @Deprecated(since = "1.20.4", forRemoval = true)
    public EntityDamageEvent(@NotNull final Entity damagee, @NotNull final DamageCause cause, @NotNull final Map<DamageModifier, Double> modifiers, @NotNull final Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions) {
        this(damagee, cause, DamageSource.builder(DamageType.GENERIC).build(), modifiers, modifierFunctions);
    }

    @ApiStatus.Internal
    public EntityDamageEvent(@NotNull final Entity damagee, @NotNull final DamageCause cause, @NotNull final DamageSource damageSource, @NotNull final Map<DamageModifier, Double> modifiers, @NotNull final Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions) {
        super(damagee);
        Preconditions.checkArgument(modifiers.containsKey(DamageModifier.BASE), "BASE DamageModifier missing");
        Preconditions.checkArgument(!modifiers.containsKey(null), "Cannot have null DamageModifier");
        Preconditions.checkArgument(modifiers.values().stream().allMatch(Objects::nonNull), "Cannot have null modifier values");
        Preconditions.checkArgument(modifiers.keySet().equals(modifierFunctions.keySet()), "Must have a modifier function for each DamageModifier");
        Preconditions.checkArgument(modifierFunctions.values().stream().allMatch(Objects::nonNull), "Cannot have null modifier function");
        this.originals = new EnumMap<>(modifiers);
        this.cause = cause;
        this.modifiers = modifiers;
        this.modifierFunctions = modifierFunctions;
        this.damageSource = damageSource;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定伤害类型的原始伤害,作为本次事件的结构定义 .
     * <p>原文：
     * Gets the original damage for the specified modifier, as defined at this
     * event's construction.
     *
     * @param type (DamageModifier) 伤害类型
     * <p>原文：the modifier
     * @return 原始伤害
     * <p>原文：the original damage
     */
    public double getOriginalDamage(@NotNull DamageModifier type) throws IllegalArgumentException {
        Preconditions.checkArgument(type != null, "Cannot have null DamageModifier");
        final Double damage = this.originals.get(type);
        return (damage != null) ? damage : 0;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定伤害类型的伤害.
     * <p>原文：
     * Sets the damage for the specified modifier.
     *
     * @param type 伤害类型
     * <p>原文：the damage modifier
     * @param damage 伤害值
     * <p>原文：the scalar value of the damage's modifier
     * @throws UnsupportedOperationException 如果触发该事件的实体不支持该伤害类型,
     *     或者受到修改,当 {@link  #isApplicable(DamageModifier)} 返回false
     * <p>原文：if the caller does not support
     *     the particular DamageModifier, or to rephrase, when {@link
     *     #isApplicable(DamageModifier)} returns false
     * @see #getFinalDamage()
     */
    public void setDamage(@NotNull DamageModifier type, double damage) throws IllegalArgumentException, UnsupportedOperationException {
        Preconditions.checkArgument(type != null, "Cannot have null DamageModifier");
        if (!this.modifiers.containsKey(type)) {
            throw new UnsupportedOperationException(type + " is not applicable to " + getEntity());
        }
        this.modifiers.put(type, damage);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过特定修饰符返回伤害值.
     * <p>原文：
     * Gets the damage change for some modifier
     *
     * @param type 伤害类型
     * <p>原文：the damage modifier
     * @return 引起该事件的原始伤害
     * <p>原文：The raw amount of damage caused by the event
     * @see DamageModifier#BASE
     */
    public double getDamage(@NotNull DamageModifier type) throws IllegalArgumentException {
        Preconditions.checkArgument(type != null, "Cannot have null DamageModifier");
        final Double damage = this.modifiers.get(type);
        return damage == null ? 0 : damage;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 该方法将检查特定的伤害原因是否是本次触发的原因,
     * 例如 {@link #setDamage(DamageModifier, double)}
     * 将不会抛出 {@link UnsupportedOperationException} 异常
     * <p>
     * {@link DamageModifier#BASE} 总是适用的.
     * <p>原文：
     * This checks to see if a particular modifier is valid for this event's
     * caller, such that, {@link #setDamage(DamageModifier, double)} will not
     * throw an {@link UnsupportedOperationException}.
     * <p>
     * {@link DamageModifier#BASE} is always applicable.
     *
     * @param type 伤害原因
     * <p>原文：the modifier
     * @return 当伤害原因与事件触发的原因相同时返回true 反之返回false
     * <p>原文：{@code true} if the modifier is supported by the caller, {@code false} otherwise
     */
    public boolean isApplicable(@NotNull DamageModifier type) throws IllegalArgumentException {
        Preconditions.checkArgument(type != null, "Cannot have null DamageModifier");
        return this.modifiers.containsKey(type);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回本次事件收到伤害的值.
     * <p>原文：
     * Gets the raw amount of damage caused by the event
     *
     * @return 一个没有进过别的处理的 伤害值
     * <p>原文：The raw amount of damage caused by the event
     * @see DamageModifier#BASE
     */
    public double getDamage() {
        return this.getDamage(DamageModifier.BASE);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此次事件最终的伤害值 (经过护甲等的修改).
     * <p>原文：
     * Gets the amount of damage caused by the event after all damage
     * reduction is applied.
     *
     * @return 此次事件最终的伤害值
     * <p>原文：the amount of damage caused by the event
     */
    public final double getFinalDamage() {
        double damage = 0;
        for (DamageModifier modifier : MODIFIERS) {
            damage += this.getDamage(modifier);
        }
        return damage;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置事件造成的原始伤害值。
     * <p>
     * 为了兼容性，此方法还会重新计算修饰符，并根据前一个伤害值的修饰符与新值的差异进行缩放。
     * <p>原文：
     * Sets the raw amount of damage caused by the event.
     * <p>
     * For compatibility this also recalculates the modifiers and scales
     * them by the difference between the modifier for the previous damage
     * value and the new one.
     *
     * @param damage 事件造成的原始伤害值
     * <p>原文：The raw amount of damage caused by the event
     */
    public void setDamage(double damage) {
        // These have to happen in the same order as the server calculates them, keep the enum sorted
        double remaining = damage;
        double oldRemaining = this.getDamage(DamageModifier.BASE);
        for (DamageModifier modifier : MODIFIERS) {
            if (!this.isApplicable(modifier)) {
                continue;
            }

            Function<? super Double, Double> modifierFunction = modifierFunctions.get(modifier);
            double newVanilla = modifierFunction.apply(remaining);
            double oldVanilla = modifierFunction.apply(oldRemaining);
            double difference = oldVanilla - newVanilla;

            // Don't allow value to cross zero, assume zero values should be negative
            double old = this.getDamage(modifier);
            if (old > 0) {
                this.setDamage(modifier, Math.max(0, old - difference));
            } else {
                this.setDamage(modifier, Math.min(0, old - difference));
            }
            remaining += newVanilla;
            oldRemaining += oldVanilla;
        }

        this.setDamage(DamageModifier.BASE, damage);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取伤害的原因。
     * <p>
     * 虽然 DamageCause 可能表示 Bukkit 分配的特定伤害原因，
     * 但 {@link #getDamageSource()} 可能会暴露其他类型的伤害，
     * 例如数据包提供的自定义伤害类型，以及任何直接或间接的实体、
     * 位置或其他导致伤害的因素。通常更推荐使用后者，
     * 但如果开发者需要一个简单的原因，此事件提供的 DamageCause
     * 应该涵盖了大多数常见用例。
     * <p>原文：
     * Gets the cause of the damage.
     * <p>
     * While a DamageCause may indicate a specific Bukkit-assigned cause of damage,
     * {@link #getDamageSource()} may expose additional types of damage such as custom
     * damage types provided by data packs, as well as any direct or indirect entities,
     * locations, or other contributing factors to the damage being inflicted. The
     * alternative is generally preferred, but DamageCauses provided to this event
     * should largely encompass most common use cases for developers if a simple cause
     * is required.
     *
     * @return 伤害原因的 DamageCause 值。
     * <p>原文：a DamageCause value detailing the cause of the damage.
     */
    @NotNull
    public DamageCause getCause() {
        return this.cause;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取伤害来源。
     * <p>原文：
     * Get the source of damage.
     *
     * @return 伤害来源的 DamageSource 详情。
     * <p>原文：a DamageSource detailing the source of the damage.
     */
    @NotNull
    public DamageSource getDamageSource() {
        return this.damageSource;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 指定修饰符类型的枚举。
     * <p>原文：
     * An enum to specify the types of modifier
     *
     * @deprecated 这个API被废弃了，具体见后面的网址，很快就要被移除了，就不深入解释了。此API导致了大量实现问题，且通常难以持续维护。它很可能在后续版本中很快被移除。更多信息请参见
     * <a href="https://www.spigotmc.org/threads/194446/">this thread</a>。
     * <p>原文：This API is responsible for a large number of implementation
     * problems and is in general unsustainable to maintain.
     */
    @Deprecated(since = "1.12")
    public enum DamageModifier {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示造成的伤害量，也称为原始 {@link EntityDamageEvent#getDamage()}。
         * <p>原文：
         * This represents the amount of damage being done, also known as the
         * raw {@link EntityDamageEvent#getDamage()}.
         */
        BASE,
        INVULNERABILITY_REDUCTION, // Paper - fix invulnerability reduction in EntityDamageEvent - needs to be right under BASE as it's the first reduction all others are based on
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示冰冻状态增加的伤害。
         * <p>原文：
         * This represents the damage increased by freezing status.
         */
        FREEZING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示佩戴头盔时被掉落方块击中所减少的伤害。
         * <p>原文：
         * This represents the damage reduced by a wearing a helmet when hit
         * by a falling block.
         */
        HARD_HAT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示格挡造成的伤害减免，仅对 {@link Player 玩家} 有效。
         * <p>原文：
         * This represents  the damage reduction caused by blocking, only present for
         * {@link Player Players}.
         */
        BLOCKING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示穿戴护甲造成的伤害减免。
         * <p>原文：
         * This represents the damage reduction caused by wearing armor.
         */
        ARMOR,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示抗性药水效果造成的伤害减免。
         * <p>原文：
         * This represents the damage reduction caused by the Resistance potion effect.
         */
        RESISTANCE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示以下组合造成的伤害减免：
         * <ul>
         * <li>
         *     护甲附魔
         * </li><li>
         *     女巫的药水抗性
         * </li>
         * </ul>
         * <p>原文：
         * This represents the damage reduction caused by the combination of:
         * <ul>
         * <li>
         *     Armor enchantments
         * </li><li>
         *     Witch's potion resistance
         * </li>
         * </ul>
         */
        MAGIC,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示吸收药水效果造成的伤害减免。
         * <p>原文：
         * This represents the damage reduction caused by the absorption potion
         * effect.
         */
        ABSORPTION,
        ;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 造成伤害的原因.
     * <p>原文：
     * An enum to specify the cause of the damage
     */
    public enum DamageCause {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由 /kill 命令造成的伤害。
         * <p>
         * 伤害：{@link Float#MAX_VALUE}
         * <p>原文：
         * Damage caused by /kill command.
         * <p>
         * Damage: {@link Float#MAX_VALUE}
         */
        KILL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由世界边界造成的伤害。
         * <p>
         * 伤害：{@link WorldBorder#getDamageAmount()}
         * <p>原文：
         * Damage caused by the World Border.
         * <p>
         * Damage: {@link WorldBorder#getDamageAmount()} <!-- todo not accurate -->
         */
        WORLD_BORDER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 实体接触仙人掌、钟乳石（石笋）或浆果丛等方块时受到的伤害.
         * <p>
         * 伤害:动态改变.
         * <p>原文：
         * Damage caused when an entity contacts a block such as a Cactus,
         * Dripstone (Stalagmite) or Berry Bush.
         * <p>
         * Damage: variable
         */
        CONTACT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 一个实体攻击另一个实体时受到的伤害.
         * <p>
         * 伤害:动态改变.
         * <p>原文：
         * Damage caused when an entity attacks another entity.
         * <p>
         * Damage: variable
         */
        ENTITY_ATTACK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 一个实体在横扫范围中攻击到另一个实体时受到的伤害.
         * <p>
         * 伤害:动态改变.
         * <p>原文：
         * Damage caused when an entity attacks another entity in a sweep attack.
         * <p>
         * Damage: variable
         */
        ENTITY_SWEEP_ATTACK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 被抛射物攻击时受到的伤害.
         * <p>
         * 伤害:动态改变.
         * <p>原文：
         * Damage caused when attacked by a projectile.
         * <p>
         * Damage: variable
         */
        PROJECTILE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 被方块卡住受到的伤害.
         * <p>
         * 伤害:1.
         * <p>原文：
         * Damage caused by being put in a block.
         * <p>
         * Damage: 1
         */
        SUFFOCATION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 实体掉落高度大于 3 格时受到的伤害.
         * <p>
         * 伤害: 掉落高度 - 3.0.
         * <p>原文：
         * Damage caused when an entity falls a distance greater than the {@link org.bukkit.attribute.Attribute#SAFE_FALL_DISTANCE safe fall distance}.
         * <p>
         * Damage: fall height - {@link org.bukkit.attribute.Attribute#SAFE_FALL_DISTANCE safe fall distance} <!-- todo not accurate -->
         */
        FALL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 直接暴露在火中受到的伤害.
         * <p>
         * 伤害: 1.
         * <p>原文：
         * Damage caused by direct exposure to fire.
         * <p>
         * Damage: 1 or 2 (for soul fire)
         */
        FIRE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于烧伤造成的伤害.
         * <p>
         * 伤害: 1.
         * <p>原文：
         * Damage caused due to burns caused by fire.
         * <p>
         * Damage: 1
         */
        FIRE_TICK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 雪人由于融化受到的伤害.
         * <p>
         * 伤害: 1.
         * <p>原文：
         * Damage caused due to a snowman melting.
         * <p>
         * Damage: 1
         */
        MELTING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 直接暴露在熔浆受到的伤害.
         * <p>
         * 伤害: 4.
         * <p>原文：
         * Damage caused by direct exposure to lava.
         * <p>
         * Damage: 4
         */
        LAVA,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在水中耗尽空气受到的伤害.
         * <p>
         * 伤害: 2.
         * <p>原文：
         * Damage caused by running out of air while in water.
         * <p>
         * Damage: 1 or 2
         */
        DROWNING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在爆炸范围内受到的伤害.
         * <p>
         * 伤害: 动态改变.
         * <p>原文：
         * Damage caused by being in the area when a block explodes.
         * <p>
         * Damage: variable
         */
        BLOCK_EXPLOSION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在实体爆炸范围内受到的伤害,例如苦力怕的爆炸.
         * <p>
         * 伤害: 动态改变.
         * <p>原文：
         * Damage caused by being in the area when an entity, such as a
         * Creeper, explodes.
         * <p>
         * Damage: variable
         */
        ENTITY_EXPLOSION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 掉入虚空受到的伤害.
         * <p>
         * 伤害:  每半秒4点.
         * <p>原文：
         * Damage caused by falling into the void.
         * <p>
         * Damage: {@link org.bukkit.World#getVoidDamageAmount()}
         */
        VOID,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 雷击造成的伤害.
         * <p>
         * 伤害: 5.
         * <p>原文：
         * Damage caused by being struck by lightning.
         * <p>
         * Damage: 5 or {@link Float#MAX_VALUE} for turtle
         */
        LIGHTNING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由自杀造成的伤害。
         * <p>
         * <b>注意：</b> 目前仅由插件使用，默认命令如 /minecraft:kill 使用 {@link #KILL} 对玩家造成伤害。
         * <p>
         * 伤害：动态改变。
         * <p>原文：
         * Damage caused by committing suicide.
         * <p>
         * <b>Note:</b> This is currently only used by plugins, default commands
         * like /minecraft:kill use {@link #KILL} to damage players.
         * <p>
         * Damage: variable
         */
        SUICIDE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当饥饿值为空时受到的伤害.
         * <p>
         * 伤害: 1.
         * <p>原文：
         * Damage caused by starving due to having an empty hunger bar.
         * <p>
         * Damage: 1
         */
        STARVATION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于持续性毒药造成的伤害.
         * <p>
         * 伤害: 1.
         * <p>原文：
         * Damage caused due to an ongoing poison effect.
         * <p>
         * Damage: 1
         */
        POISON,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 伤害药水或魔法造成的伤害.
         * <p>
         * 伤害: 动态改变.
         * <p>原文：
         * Damage caused by being hit by a damage potion or spell.
         * <p>
         * Damage: variable
         */
        MAGIC,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 凋零效果造成的伤害.
         * <p>原文：
         * Damage caused by Wither potion effect
         */
        WITHER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 被掉落中的方块砸中造成的伤害
         * <p>
         * <b>注意:</b> 不是所有方块都会造成伤害.
         * <p>
         * 伤害: 动态改变.
         * <p>原文：
         * Damage caused by being hit by a falling block which deals damage.
         * <p>
         * <b>Note:</b> Not every block deals damage
         * <p>
         * Damage: variable
         */
        FALLING_BLOCK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 被荆棘反弹的攻击所造成的伤害(反伤).
         * <p>
         * 伤害: 1-4(荆棘).
         * <p>原文：
         * Damage caused in retaliation to another attack by the {@link org.bukkit.enchantments.Enchantment#THORNS}
         * enchantment or guardian.
         * <p>
         * Damage: 1-5 (thorns) or 2 (guardian)
         */
        THORNS,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 龙息造成的伤害 (恶龙咆哮~).
         * <p>
         * 伤害: 动态改变.
         * <p>原文：
         * Damage caused by a dragon breathing fire.
         * <p>
         * Damage: variable
         *
         * @deprecated never used without help of commands or plugins,
         * {@link #ENTITY_ATTACK} will be used instead
         */
        @Deprecated
        DRAGON_BREATH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 实体撞墙时造成的伤害.
         * <p>
         * 伤害: 动态改变.
         * <p>原文：
         * Damage caused when an entity runs into a wall.
         * <p>
         * Damage: variable
         */
        FLY_INTO_WALL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 实体站在{@link Material#MAGMA_BLOCK}上时收到的伤害.
         * <p>
         * 伤害: 1.
         * <p>原文：
         * Damage caused when an entity steps on {@link Material#MAGMA_BLOCK}.
         * <p>
         * Damage: 1
         */
        HOT_FLOOR,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体踩在 {@link Material#CAMPFIRE} 或 {@link Material#SOUL_CAMPFIRE} 上时造成的伤害。
         * <p>
         * 伤害：1
         * <p>原文：
         * Damage caused when an entity steps on {@link Material#CAMPFIRE} or {@link Material#SOUL_CAMPFIRE}.
         * <p>
         * Damage: 1 or 2 (for soul fire)
         */
        CAMPFIRE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于游戏规则:maxEntityCramming,一个实体和许多其他实体挤压受到的伤害.
         * <p>
         * 伤害: 6.
         * <p>原文：
         * Damage caused when an entity is colliding with too many entities due
         * to the {@link org.bukkit.GameRules#MAX_ENTITY_CRAMMING}.
         * <p>
         * Damage: 6
         */
        CRAMMING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 实体(例如:鱼)不在水中时受到的伤害.
         * <p>
         * 伤害: 1.
         * <p>原文：
         * Damage caused when an entity that should be in water is not.
         * <p>
         * Damage: 1 or 2
         */
        DRYOUT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 冻伤引起的伤害.
         * <p>
         * 伤害: 1 或 5.
         * <p>原文：
         * Damage caused from freezing.
         * <p>
         * Damage: 1 or 5 (for {@link org.bukkit.Tag#ENTITY_TYPES_FREEZE_HURTS_EXTRA_TYPES sensitive} entities)
         */
        FREEZE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 来自{@link org.bukkit.entity.Warden}音波攻击造成的伤害.
         * <p>
         * 伤害: 10.
         * <p>原文：
         * Damage caused by the Sonic Boom attack from {@link org.bukkit.entity.Warden}.
         * <p>
         * Damage: 10
         */
        SONIC_BOOM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 自定义伤害.
         * <p>
         * 伤害: 动态改变.
         * <p>原文：
         * Custom damage.
         * <p>
         * Damage: variable
         */
        CUSTOM;
    }
}
