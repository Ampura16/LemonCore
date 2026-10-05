package org.bukkit.event.entity;

import org.bukkit.Chunk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.world.ChunkLoadEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个生物体在世界中出生时触发该事件.
 * <p>
 * 如果该事件被取消了,那么这个生物将不会出生.
 * <p>原文：
 * Called when a creature is spawned into a world.
 * <p>
 * If this event is cancelled, the creature will not spawn.
 */
public class CreatureSpawnEvent extends EntitySpawnEvent {

    private final SpawnReason spawnReason;

    @ApiStatus.Internal
    public CreatureSpawnEvent(@NotNull final LivingEntity spawnee, @NotNull final SpawnReason spawnReason) {
        super(spawnee);
        this.spawnReason = spawnReason;
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回生物出生的原因
     * <p>原文：
     * Gets the reason for why the creature is being spawned.
     *
     * @return 出生原因
     * <p>原文：A SpawnReason value detailing the reason for the creature being
     *     spawned
     */
    @NotNull
    public SpawnReason getSpawnReason() {
        return this.spawnReason;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 生成原因的枚举类.
     * <p>原文：
     * An enum to specify the type of spawning
     */
    public enum SpawnReason {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 自然生成
         * <p>原文：
         * When something spawns from natural means
         */
        NATURAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体因为乘骑而被生成时 (大多是蜘蛛骑士)
         * <p>原文：
         * When an entity spawns as a jockey of another entity (mostly spider
         * jockeys)
         */
        JOCKEY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当区块产生而生成生物时
         * <p>原文：
         * When a creature spawns due to chunk generation
         *
         * @deprecated 不再调用, 区块与已经存在的实体一同生成.
         * 请考虑使用{@link ChunkLoadEvent#isNewChunk()} 和 {@link Chunk#getEntities()}
         * 以达到类似效果
         * <p>原文：no longer called, chunks are generated with entities
         * already existing. Consider using {@link ChunkLoadEvent},
         * {@link ChunkLoadEvent#isNewChunk()} and {@link Chunk#getEntities()}
         * for similar effect.
         */
        @Deprecated(since = "1.14", forRemoval = true)
        CHUNK_GEN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当生物由于刷怪箱生成时
         * <p>原文：
         * When a creature spawns from a spawner
         */
        SPAWNER,
        /**
         * When a creature spawns from a trial spawner
         */
        TRIAL_SPAWNER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当生物由于蛋生成时 (不是刷怪蛋,是普通的鸡蛋)
         * <p>原文：
         * When a creature spawns from an egg
         */
        EGG,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当生物由于刷怪蛋生成时
         * <p>原文：
         * When a creature spawns from a Spawner Egg
         */
        SPAWNER_EGG,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当生物由于闪电而生成时
         * <p>原文：
         * When a creature spawns because of a lightning strike
         */
        LIGHTNING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当雪人被建造时
         * <p>原文：
         * When a snowman is spawned by being built
         */
        BUILD_SNOWMAN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当一个铁傀儡被建造时
         * <p>原文：
         * When an iron golem is spawned by being built
         */
        BUILD_IRONGOLEM,
        /**
         * When a copper golem is spawned by being built
         */
        BUILD_COPPERGOLEM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当一个凋零被建造时
         * <p>原文：
         * When a wither boss is spawned by being built
         */
        BUILD_WITHER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当村庄生成保卫的铁傀儡时
         * <p>原文：
         * When an iron golem is spawned to defend a village
         */
        VILLAGE_DEFENSE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当一个僵尸进攻村庄而生成时.
         * <p>原文：
         * When a zombie is spawned to invade a village
         */
        VILLAGE_INVASION,
        /**
         * When an entity breeds to create a child, this also include Shulker and Allay
         */
        BREEDING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当史莱姆分裂时
         * <p>原文：
         * When a slime splits
         */
        SLIME_SPLIT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当一个实体请求支援时
         * <p>原文：
         * When an entity calls for reinforcements
         */
        REINFORCEMENTS,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当生物由于地狱传送门而生成时
         * <p>原文：
         * When a creature is spawned by nether portal
         */
        NETHER_PORTAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当生物由于投掷器丢出鸡蛋而生成时
         * <p>原文：
         * When a creature is spawned by a dispenser dispensing an egg
         */
        DISPENSE_EGG,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当一个僵尸感染一个村民时
         * <p>原文：
         * When a zombie infects a villager
         */
        INFECTION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当村民从僵尸状态痊愈时
         * <p>原文：
         * When a villager is cured from infection
         */
        CURED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当豹猫为了照顾自己的孩子而出生时
         * <p>原文：
         * When an ocelot has a baby spawned along with them
         */
        OCELOT_BABY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当一条蠹虫从方块中生成时
         * <p>原文：
         * When a silverfish spawns from a block
         */
        SILVERFISH_BLOCK,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当一个实体成为其他实体坐骑时 (大多数时是鸡骑士)
         * <p>原文：
         * When an entity spawns as a mount of another entity (mostly chicken
         * jockeys)
         */
        MOUNT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体作为陷阱陷害玩家时
         * <p>原文：
         * When an entity spawns as a trap for players approaching
         */
        TRAP,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于末影珍珠的使用而生成.
         * <p>原文：
         * When an entity is spawned as a result of ender pearl usage
         */
        ENDER_PEARL,
        /**
         * When an entity is spawned as a result of the entity it is being
         * perched on jumping or being damaged
         */
        SHOULDER_ENTITY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当一个实体溺亡而生成时
         * <p>原文：
         * When a creature is spawned by another entity drowning
         */
        DROWNED,
        /**
         * When a cow is spawned by shearing a mushroom cow
         */
        SHEARED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于苦力怕等发生爆炸产生效果云时/When eg an effect cloud is spawned as a result of a creeper exploding
         * <p>原文：
         * When an entity is spawned as a result of an explosion. Like an area effect cloud from
         * a creeper or a dragon fireball.
         */
        EXPLOSION,
        /**
         * When an entity is spawned as part of a raid
         */
        RAID,
        /**
         * When an entity is spawned as part of a patrol
         */
        PATROL,
        /**
         * When a bee is released from a beehive/bee nest
         */
        BEEHIVE,
        /**
         * When a piglin is converted to a zombified piglin.
         */
        PIGLIN_ZOMBIFIED,
        /**
         * When an entity is created by a cast spell.
         */
        SPELL,
        /**
         * When an entity is shaking in Powder Snow and a new entity spawns.
         */
        FROZEN,
        /**
         * When a tadpole converts to a frog
         */
        METAMORPHOSIS,
        /**
         * When an Allay duplicate itself
         */
        DUPLICATION,
        /**
         * When a creature is spawned by the "/summon" command
         */
        COMMAND,
        /**
         * When a creature is spawned by an enchantment
         */
        ENCHANTMENT,
        /**
         * When an entity spawns from an ominous item spawner
         */
        OMINOUS_ITEM_SPAWNER,
        /**
         * When an entity spawns from a bucket
         */
        BUCKET,
        /**
         * When a creature is spawned by a potion effect, for example:
         * {@link org.bukkit.potion.PotionType#OOZING}, {@link org.bukkit.potion.PotionType#INFESTED}
         */
        POTION_EFFECT,
        /**
         * When a Copper Golem Statue turns back into a Copper Golem
         *
         * @apiNote Canceling a {@link CreatureSpawnEvent} with this reason does not prevent the statue block from being removed, use {@link EntityChangeBlockEvent} to account for all side effects
         */
        REANIMATE,
        /**
         * When a creature is spawned by being rehydrated
         */
        REHYDRATION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当生物被插件生成时
         * <p>原文：
         * When a creature is spawned by plugins
         */
        CUSTOM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 实体由于其他原因而生成
         * <p>原文：
         * When an entity is missing a SpawnReason
         */
        DEFAULT
    }
}
