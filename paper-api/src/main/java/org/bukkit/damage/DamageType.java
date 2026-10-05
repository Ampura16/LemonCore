package org.bukkit.damage;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.bukkit.Keyed;
import org.bukkit.Translatable;
import org.jetbrains.annotations.NotNull;

/**
 * Represent a type of damage that an entity can receive.
 * <p>
 * Constants in this class include the base types provided by the vanilla
 * server. Data packs are capable of registering more types of damage which may
 * be obtained through {@link io.papermc.paper.registry.RegistryAccess#getRegistry(RegistryKey)} and {@link RegistryKey#DAMAGE_TYPE}.
 *
 * @see <a href="https://minecraft.wiki/w/Damage_type">Minecraft Wiki</a>
 */
public interface DamageType extends Keyed, Translatable {

    // Start generate - DamageType
    DamageType ARROW = getDamageType("arrow");

    DamageType BAD_RESPAWN_POINT = getDamageType("bad_respawn_point");

    DamageType CACTUS = getDamageType("cactus");

    DamageType CAMPFIRE = getDamageType("campfire");

    DamageType CRAMMING = getDamageType("cramming");

    DamageType DRAGON_BREATH = getDamageType("dragon_breath");

    DamageType DROWN = getDamageType("drown");

    DamageType DRY_OUT = getDamageType("dry_out");

    DamageType ENDER_PEARL = getDamageType("ender_pearl");

    DamageType EXPLOSION = getDamageType("explosion");

    DamageType FALL = getDamageType("fall");

    DamageType FALLING_ANVIL = getDamageType("falling_anvil");

    DamageType FALLING_BLOCK = getDamageType("falling_block");

    DamageType FALLING_STALACTITE = getDamageType("falling_stalactite");

    DamageType FIREBALL = getDamageType("fireball");

    DamageType FIREWORKS = getDamageType("fireworks");

    DamageType FLY_INTO_WALL = getDamageType("fly_into_wall");

    DamageType FREEZE = getDamageType("freeze");

    DamageType GENERIC = getDamageType("generic");

    DamageType GENERIC_KILL = getDamageType("generic_kill");

    DamageType HOT_FLOOR = getDamageType("hot_floor");

    DamageType IN_FIRE = getDamageType("in_fire");

    DamageType IN_WALL = getDamageType("in_wall");

    DamageType INDIRECT_MAGIC = getDamageType("indirect_magic");

    DamageType LAVA = getDamageType("lava");

    DamageType LIGHTNING_BOLT = getDamageType("lightning_bolt");

    DamageType MACE_SMASH = getDamageType("mace_smash");

    DamageType MAGIC = getDamageType("magic");

    DamageType MOB_ATTACK = getDamageType("mob_attack");

    DamageType MOB_ATTACK_NO_AGGRO = getDamageType("mob_attack_no_aggro");

    DamageType MOB_PROJECTILE = getDamageType("mob_projectile");

    DamageType ON_FIRE = getDamageType("on_fire");

    DamageType OUT_OF_WORLD = getDamageType("out_of_world");

    DamageType OUTSIDE_BORDER = getDamageType("outside_border");

    DamageType PLAYER_ATTACK = getDamageType("player_attack");

    DamageType PLAYER_EXPLOSION = getDamageType("player_explosion");

    DamageType SONIC_BOOM = getDamageType("sonic_boom");

    DamageType SPEAR = getDamageType("spear");

    DamageType SPIT = getDamageType("spit");

    DamageType STALAGMITE = getDamageType("stalagmite");

    DamageType STARVE = getDamageType("starve");

    DamageType STING = getDamageType("sting");

    DamageType SWEET_BERRY_BUSH = getDamageType("sweet_berry_bush");

    DamageType THORNS = getDamageType("thorns");

    DamageType THROWN = getDamageType("thrown");

    DamageType TRIDENT = getDamageType("trident");

    DamageType UNATTRIBUTED_FIREBALL = getDamageType("unattributed_fireball");

    DamageType WIND_CHARGE = getDamageType("wind_charge");

    DamageType WITHER = getDamageType("wither");

    DamageType WITHER_SKULL = getDamageType("wither_skull");
    // End generate - DamageType

    @NotNull
    private static DamageType getDamageType(@NotNull @KeyPattern.Value String key) {
        return RegistryAccess.registryAccess().getRegistry(RegistryKey.DAMAGE_TYPE).getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * {@inheritDoc}
     * <p>
     * 返回的键是当此伤害类型导致实体死亡时发送的死亡消息的键.
     * <p>
     * <strong>注意</strong> 此翻译键仅在{@link #getDeathMessageType()}为{@link DeathMessageType#DEFAULT}时使用
     * <p>原文：
     * {@inheritDoc}
     * <p>
     * The returned key is that of the death message sent when this damage type
     * is responsible for the death of an entity.
     * <p>
     * <strong>Note</strong> This translation key is only used if
     * {@link #getDeathMessageType()} is {@link DeathMessageType#DEFAULT}
     */
    @NotNull
    @Override
    public String getTranslationKey();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此伤害类型的{@link DamageScaling}.
     * <p>原文：
     * Get the {@link DamageScaling} for this damage type.
     *
     * @return 伤害缩放
     * <p>原文：the damage scaling
     */
    @NotNull
    public DamageScaling getDamageScaling();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此伤害类型的{@link DamageEffect}.
     * <p>原文：
     * Get the {@link DamageEffect} for this damage type.
     *
     * @return 伤害效果
     * <p>原文：the damage effect
     */
    @NotNull
    public DamageEffect getDamageEffect();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此伤害类型的{@link DeathMessageType}.
     * <p>原文：
     * Get the {@link DeathMessageType} for this damage type.
     *
     * @return 死亡消息类型
     * <p>原文：the death message type
     */
    @NotNull
    public DeathMessageType getDeathMessageType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此伤害类型造成的饥饿消耗量.
     * <p>原文：
     * Get the amount of hunger exhaustion caused by this damage type.
     *
     * @return 消耗量
     * <p>原文：the exhaustion
     */
    public float getExhaustion();
}
