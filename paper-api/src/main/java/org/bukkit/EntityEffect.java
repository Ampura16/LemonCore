package org.bukkit;

import com.google.common.base.Preconditions;
import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Armadillo;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Creaking;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Goat;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Ravager;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Sniffer;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Warden;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zoglin;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 实体能发生的效果列表。
 * <p>原文：
 * A list of all effects that can happen to entities.
 */
public enum EntityEffect {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 箭矢上的彩色粒子.
     * <p>原文：
     * Colored particles from an arrow.
     */
    ARROW_PARTICLES(0, Arrow.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 兔子跳跃.
     * <p>原文：
     * Rabbit jumping.
     */
    RABBIT_JUMP(1, Rabbit.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将刷怪笼矿车的延迟重置为 200. 不影响实际刷怪延迟, 仅影响刷怪笼中实体旋转的速度.
     * <p>原文：
     * Resets a spawner minecart's delay to 200. Does not affect actual spawning
     * delay, only the speed at which the entity in the spawner spins.
     */
    RESET_SPAWNER_MINECART_DELAY(1, SpawnerMinecart.class),
    /**
     * When a kinetic weapon hits an entity.
     */
    HIT(2, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当怪物收到伤害.
     * <p>原文：
     * When mobs get hurt.
     *
     * @deprecated 请使用 {@link LivingEntity#playHurtAnimation(float)}
     * <p>原文：use {@link LivingEntity#playHurtAnimation(float)}
     */
    @Deprecated(since = "1.20.1", forRemoval = true)
    HURT(2, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当怪物死亡.
     * <p>原文：
     * When a mob dies.
     * <p>
     * <b>This will cause client-glitches!</b>
     *
     * @deprecated 已分为单独的效果
     * <p>原文：split into individual effects
     * @see #PROJECTILE_CRACK
     * @see #ENTITY_DEATH
     */
    @Deprecated(since = "1.12.2", forRemoval = true)
    DEATH(3, Entity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生成鸡蛋破碎粒子.
     * <p>原文：
     * Spawns the egg breaking particles.
     *
     * @deprecated use {@link #PROJECTILE_CRACK}
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    EGG_BREAK(3, Egg.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生成雪球破碎粒子.
     * <p>原文：
     * Spawns the snowball breaking particles.
     *
     * @deprecated use {@link #PROJECTILE_CRACK}
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    SNOWBALL_BREAK(3, Snowball.class),
    /**
     * Shows the crack particles when a projectile
     * hits something.
     */
    PROJECTILE_CRACK(3, Egg.class, Snowball.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 播放实体死亡音效和动画.
     * <p>
     * <b>这会导致客户端故障!</b>
     * <p>原文：
     * Plays the entity death sound and animation.
     * <p>
     * <b>This will cause client-glitches!</b>
     */
    ENTITY_DEATH(3, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 播放尖牙攻击动画.
     * <p>原文：
     * Plays the evoker's fang attack animation.
     *
     * @deprecated use {@link #ENTITY_ATTACK}
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    FANG_ATTACK(4, EvokerFangs.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 播放疣猪兽攻击动画.
     * <p>原文：
     * Plays the hoglin attack animation.
     *
     * @deprecated use {@link #ENTITY_ATTACK}
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    HOGLIN_ATTACK(4, Hoglin.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 播放铁傀儡攻击动画.
     * <p>原文：
     * Plays the iron golem attack animation.
     *
     * @deprecated use {@link #ENTITY_ATTACK}
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    IRON_GOLEN_ATTACK(4, IronGolem.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 播放劫掠兽攻击动画.
     * <p>原文：
     * Plays the ravager attack animation.
     *
     * @deprecated use {@link #ENTITY_ATTACK}
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    RAVAGER_ATTACK(4, Ravager.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 播放监守者攻击动画.
     * <p>原文：
     * Plays the warden attack animation.
     *
     * @deprecated use {@link #ENTITY_ATTACK}
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    WARDEN_ATTACK(4, Warden.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 播放僵尸疣猪兽攻击动画.
     * <p>原文：
     * Plays the zoglin attack animation.
     *
     * @deprecated use {@link #ENTITY_ATTACK}
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    ZOGLIN_ATTACK(4, Zoglin.class),
    /**
     * Plays an attack animation for the respective entities.
     */
    ENTITY_ATTACK(4, EvokerFangs.class, Hoglin.class, IronGolem.class, Ravager.class, Warden.class, Zoglin.class, Creaking.class),
    // 5 - unused
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 驯服实体失败时的烟雾.
     * <p>原文：
     * The smoke when taming an entity fails.
     *
     * @deprecated use {@link EntityEffect#TAMING_FAILED}
     */
    @Deprecated(since = "1.21", forRemoval = true)
    WOLF_SMOKE(6, Tameable.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 驯服实体成功时的红心.
     * <p>原文：
     * The hearts when taming an entity succeeds.
     *
     * @deprecated use {@link EntityEffect#TAMING_SUCCEEDED}
     */
    @Deprecated(since = "1.21", forRemoval = true)
    WOLF_HEARTS(7, Tameable.class),
    /**
     * The smoke when taming an entity fails.
     */
    TAMING_FAILED(6, Tameable.class),
    /**
     * The hearts when taming an entity succeeds.
     */
    TAMING_SUCCEEDED(7, Tameable.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 狼在湿水后抖动身体.
     * <p>原文：
     * When a wolf shakes (after being wet).
     *
     * @see EntityEffect#WOLF_SHAKE_STOP
     */
    WOLF_SHAKE(8, Wolf.class),
    // 9 - internal
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 羊吃草方块.
     * <p>原文：
     * When an entity eats a LONG_GRASS block.
     *
     * @deprecated 已分为单独的效果
     * <p>原文：split into individual effects
     * @see #SHEEP_EAT_GRASS
     * @see #TNT_MINECART_IGNITE
     */
    @Deprecated(since = "1.12.2", forRemoval = true)
    SHEEP_EAT(10, Entity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 播放绵羊吃草动画.
     * <p>原文：
     * Plays the sheep eating grass animation.
     */
    SHEEP_EAT_GRASS(10, Sheep.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使TNT矿车点燃, 不播放点燃音效.
     * <p>
     * <b>这会导致客户端故障!</b>
     * <p>原文：
     * Causes the TNT minecart to ignite, does not play the ignition sound
     * <p>
     * <b>This will cause client-glitches!</b>
     */
    TNT_MINECART_IGNITE(10, ExplosiveMinecart.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 铁傀儡给予玫瑰(译注：应为poppy，即罂粟).
     * <p>原文：
     * When an Iron Golem gives a rose.
     */
    IRON_GOLEM_ROSE(11, IronGolem.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 村民的红心.
     * <p>原文：
     * Hearts from a villager.
     */
    VILLAGER_HEART(12, Villager.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 村民生气时.
     * <p>原文：
     * When a villager is angry.
     */
    VILLAGER_ANGRY(13, Villager.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 村民高兴时的粒子效果。
     * <p>原文：
     * Happy particles from a villager.
     */
    VILLAGER_HAPPY(14, Villager.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 女巫的魔法粒子效果.
     * <p>原文：
     * Magic particles from a witch.
     */
    WITCH_MAGIC(15, Witch.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 僵尸被剧烈抖动转化为村民(译注：僵尸村民被喷溅虚弱药水并喂食金苹果转化为村民)。
     * <p>原文：
     * Plays the sound when a zombie villager is
     * cured.
     */
    ZOMBIE_TRANSFORM(16, ZombieVillager.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 烟花爆炸.
     * <p>原文：
     * When a firework explodes.
     */
    FIREWORK_EXPLODE(17, Firework.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 繁殖实体时的爱心.
     * <p>原文：
     * Hearts from a breeding entity
     * or when an Allay duplicates.
     */
    LOVE_HEARTS(18, Animals.class, Allay.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 重置鱿鱼旋转.
     * <p>原文：
     * Resets squid rotation.
     */
    SQUID_ROTATE(19, Squid.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 蠹虫进入石头, 刷怪笼刷怪.
     * <p>原文：
     * Silverfish entering block, spawner spawning.
     */
    ENTITY_POOF(20, Mob.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 守卫者播放攻击音效.
     * <p>原文：
     * Guardian plays the attack sound effect.
     */
    GUARDIAN_TARGET(21, Guardian.class),
    // 22-28 player internal flags
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 盾牌阻挡攻击.
     * <p>原文：
     * Shield blocks attack.
     *
     * @deprecated replaced by the {@code blocks_attacks} item data component
     */
    @Deprecated(since = "1.21.5", forRemoval = true)
    SHIELD_BLOCK(29, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 护盾损坏.
     * <p>原文：
     * Shield breaks.
     *
     * @deprecated replaced by the {@code blocks_attacks} item data component
     */
    @Deprecated(since = "1.21.5", forRemoval = true)
    SHIELD_BREAK(30, LivingEntity.class),
    // 31 - unused
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 盔甲架被击中.
     * <p>原文：
     * Armor stand is hit.
     */
    ARMOR_STAND_HIT(32, ArmorStand.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体受到荆棘攻击伤害.
     * <p>原文：
     * Entity hurt by thorns attack.
     *
     * @deprecated in favor of {@link LivingEntity#playHurtAnimation(float)} or {@link Entity#broadcastHurtAnimation(java.util.Collection)}
     */
    @Deprecated(since = "1.19.4", forRemoval = true)
    THORNS_HURT(33, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 铁傀儡收起玫瑰.
     * <p>原文：
     * Iron golem puts away rose.
     */
    IRON_GOLEM_SHEATH(34, IronGolem.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 图腾阻止实体死亡.
     * <p>原文：
     * Totem prevents entity death.
     *
     * @deprecated see {@link #PROTECTED_FROM_DEATH}
     */
    @Deprecated(since = "1.21.2", forRemoval = true)
    TOTEM_RESURRECT(35, LivingEntity.class),
    /**
     * Item with {@link DataComponentTypes#DEATH_PROTECTION} prevents entity death.
     * For player, the item selected will be shown for a moment on the screen, if the
     * item is not found a totem will appear.
     */
    PROTECTED_FROM_DEATH(35, Entity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体因溺水伤害而受伤.
     * <p>原文：
     * Entity hurt due to drowning damage.
     *
     * @deprecated in favor of {@link LivingEntity#playHurtAnimation(float)} or {@link Entity#broadcastHurtAnimation(java.util.Collection)}
     */
    @Deprecated(since = "1.19.4", forRemoval = true)
    HURT_DROWN(36, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体因爆炸伤害而受伤.
     * <p>原文：
     * Entity hurt due to explosion damage.
     *
     * @deprecated in favor of {@link LivingEntity#playHurtAnimation(float)} or {@link Entity#broadcastHurtAnimation(java.util.Collection)}
     */
    @Deprecated(since = "1.19.4", forRemoval = true)
    HURT_EXPLOSION(37, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 海豚被喂食后正在定位结构.
     * <p>原文：
     * Dolphin has been fed and is locating a structure.
     */
    DOLPHIN_FED(38, Dolphin.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 劫掠兽被击晕 40 tick.
     * <p>原文：
     * Ravager has been stunned for 40 ticks.
     */
    RAVAGER_STUNNED(39, Ravager.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 猫驯服失败.
     * <p>原文：
     * Cat taming failed.
     *
     * @deprecated use {@link #TRUSTING_FAILED}
     */
    @Deprecated(since = "1.14", forRemoval = true)
    CAT_TAME_FAIL(40, Cat.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 猫驯服成功.
     * <p>原文：
     * Cat taming succeeded.
     *
     * @deprecated use {@link #TRUSTING_SUCCEEDED}
     */
    @Deprecated(since = "1.14", forRemoval = true)
    CAT_TAME_SUCCESS(41, Cat.class),
    /**
     * Ocelot trusting failed.
     */
    TRUSTING_FAILED(40, Ocelot.class),
    /**
     * Ocelot trusting succeeded.
     */
    TRUSTING_SUCCEEDED(41, Ocelot.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 村民在袭击中溅出粒子.
     * <p>原文：
     * Villager splashes particles during a raid.
     */
    VILLAGER_SPLASH(42, Villager.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家的不祥之兆效果被移除, 以开始或增加袭击难度.
     * <p>原文：
     * Player's bad omen effect removed to start or increase raid difficult.
     *
     * @deprecated raid system was overhauled in 1.20.5
     */
    @Deprecated(since = "1.20.5", forRemoval = true)
    PLAYER_BAD_OMEN_RAID(43, Player.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体因浆果丛受伤. 好痛!
     * <p>原文：
     * Entity hurt due to berry bush. Prickly!
     *
     * @deprecated in favor of {@link LivingEntity#playHurtAnimation(float)} or {@link Entity#broadcastHurtAnimation(java.util.Collection)}
     */
    @Deprecated(since = "1.19.4", forRemoval = true)
    HURT_BERRY_BUSH(44, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 狐狸咀嚼嘴里的食物.
     * <p>原文：
     * Fox chews the food in its mouth.
     */
    FOX_CHEW(45, Fox.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体因紫颂果或作为末影人而传送.
     * <p>原文：
     * Entity teleported as a result of chorus fruit or as an enderman.
     */
    TELEPORT_ENDER(46, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体损坏主手物品.
     * <p>原文：
     * Entity breaks item in main hand.
     *
     * @see org.bukkit.inventory.EquipmentSlot#HAND
     */
    BREAK_EQUIPMENT_MAIN_HAND(47, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体损坏副手物品.
     * <p>原文：
     * Entity breaks item in off-hand.
     *
     * @see org.bukkit.inventory.EquipmentSlot#OFF_HAND
     */
    BREAK_EQUIPMENT_OFF_HAND(48, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体损坏头盔槽物品.
     * <p>原文：
     * Entity breaks item in helmet slot.
     *
     * @see org.bukkit.inventory.EquipmentSlot#HEAD
     */
    BREAK_EQUIPMENT_HELMET(49, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体损坏胸甲槽物品.
     * <p>原文：
     * Entity breaks item in chestplate slot.
     *
     * @see org.bukkit.inventory.EquipmentSlot#CHEST
     */
    BREAK_EQUIPMENT_CHESTPLATE(50, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体损坏护腿槽物品.
     * <p>原文：
     * Entity breaks item in legging slot.
     *
     * @see org.bukkit.inventory.EquipmentSlot#LEGS
     */
    BREAK_EQUIPMENT_LEGGINGS(51, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体损坏靴子槽物品.
     * <p>原文：
     * Entity breaks item in boot slot.
     *
     * @see org.bukkit.inventory.EquipmentSlot#FEET
     */
    BREAK_EQUIPMENT_BOOTS(52, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在实体脚部生成蜂蜜块滑动粒子.
     * <p>原文：
     * Spawns honey block slide particles at the entity's feet.
     */
    HONEY_BLOCK_SLIDE_PARTICLES(53, Entity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在实体脚部生成蜂蜜块坠落粒子.
     * <p>原文：
     * Spawns honey block fall particles at the entity's feet.
     */
    HONEY_BLOCK_FALL_PARTICLES(54, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体交换主手和副手物品.
     * <p>原文：
     * Entity swaps the items in their hand and offhand
     */
    SWAP_HAND_ITEMS(55, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 停止正在抖动的狼.
     * <p>原文：
     * Stops a wolf that is currently shaking.
     *
     * @see EntityEffect#WOLF_SHAKE
     */
    WOLF_SHAKE_STOP(56, Wolf.class),
    // 57 - unused
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 山羊低头准备冲撞.
     * <p>原文：
     * Goat lowers its head for ramming.
     *
     * @see #GOAT_RAISE_HEAD
     */
    GOAT_LOWER_HEAD(58, Goat.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 山羊抬头.
     * <p>原文：
     * Goat raises its head.
     *
     * @see #GOAT_LOWER_HEAD
     */
    GOAT_RAISE_HEAD(59, Goat.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生成死亡烟雾粒子.
     * <p>原文：
     * Spawns death smoke particles.
     */
    SPAWN_DEATH_SMOKE(60, LivingEntity.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 监守者抖动触须.
     * <p>原文：
     * Warden shakes its tendrils.
     */
    WARDEN_TENDRIL_SHAKE(61, Warden.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 监守者执行音波攻击动画.
     * <p>
     * 不播放音效或发射光束.
     * <p>原文：
     * Warden performs sonic attack animation.
     * <br>
     * Does not play the sound or fire the beam.
     */
    WARDEN_SONIC_ATTACK(62, Warden.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 播放嗅探兽挖掘音效.
     * <p>
     * 嗅探兽必须有目标且处于 {@link Sniffer.State#SEARCHING} 或
     * {@link Sniffer.State#DIGGING} 状态.
     * <p>原文：
     * Plays sniffer digging sound.
     * <br>
     * Sniffer must have a target and be in {@link Sniffer.State#SEARCHING} or
     * {@link Sniffer.State#DIGGING}.
     */
    SNIFFER_DIG(63, Sniffer.class),
    /**
     * Armadillo peeks out of its shell
     */
    ARMADILLO_PEEK(64, Armadillo.class),
    /**
     * {@link org.bukkit.inventory.EquipmentSlot#BODY} armor piece breaks.
     *
     * @deprecated use {@link #BREAK_EQUIPMENT_BODY}
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    BODY_BREAK(65, LivingEntity.class),
    /**
     * Entity breaks item in body slot.
     *
     * @see org.bukkit.inventory.EquipmentSlot#BODY
     */
    BREAK_EQUIPMENT_BODY(65, LivingEntity.class),
    /**
     * A creaking shaking when being hit.
     */
    SHAKE(66, Creaking.class),
    /**
     * Drown particles for entities.
     */
    DROWN_PARTICLES(67, LivingEntity.class),
    /**
     * Entity breaks item in saddle slot.
     *
     * @see org.bukkit.inventory.EquipmentSlot#SADDLE
     */
    BREAK_EQUIPMENT_SADDLE(68, LivingEntity.class),
    /**
     * Ravager roars.
     */
    RAVAGER_ROARED(69, Ravager.class),
    ;

    private final byte data;
    private final Set<Class<? extends Entity>> applicableClasses;

    EntityEffect(int data, Class<? extends Entity>... applicableClasses) {
        Preconditions.checkState(applicableClasses.length > 0, "Unknown applicable classes");
        this.data = (byte) data;
        this.applicableClasses = Set.of(applicableClasses);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体效果的数据值, 可能不是唯一的.
     * <p>原文：
     * Gets the data value of this entity effect, may not be unique.
     *
     * @return 实体效果的数据值
     * <p>原文：the data value
     */
    @ApiStatus.Internal
    public byte getData() {
        return this.data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该效果能应用到的实体超类.
     * <p>原文：
     * Gets entity superclass which this entity effect is applicable to.
     *
     * @return applicable class
     * @deprecated an entity effect can apply to multiple superclasses, see {@link #getApplicableClasses()}
     */
    @NotNull
    @Deprecated(since = "1.21.4")
    public Class<? extends Entity> getApplicable() {
        return this.applicableClasses.iterator().next();
    }

    /**
     * Gets the entity superclasses which this entity effect is applicable to.
     *
     * @return the applicable classes
     */
    @NotNull
    public Set<Class<? extends Entity>> getApplicableClasses() {
        return this.applicableClasses;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此效果是否适用于给定实体.
     * <p>原文：
     * Checks if this entity effect is applicable to the given entity.
     *
     * @param entity 要检查的实体
     * <p>原文：the entity to check
     * @return 如果适用则为 true
     * <p>原文：{@code true} if applicable
     */
    public boolean isApplicableTo(@NotNull Entity entity) {
        Preconditions.checkArgument(entity != null, "Entity cannot be null");

        return this.isApplicableTo(entity.getClass());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此效果是否适用于给定的实体类.
     * <p>原文：
     * Checks if this entity effect is applicable to the given entity class.
     *
     * @param clazz 要检查的实体类
     * <p>原文：the entity class to check
     * @return 如果适用则为 true
     * <p>原文：{@code true} if applicable
     */
    public boolean isApplicableTo(@NotNull Class<? extends Entity> clazz) {
        Preconditions.checkArgument(clazz != null, "Class cannot be null");

        for (Class<? extends Entity> applicableClass : this.applicableClasses) {
            if (applicableClass.isAssignableFrom(clazz)) {
                return true;
            }
        }
        return false;
    }
}
