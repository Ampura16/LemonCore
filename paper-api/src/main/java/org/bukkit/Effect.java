package org.bukkit;

import com.google.common.base.Enums;
import com.google.common.collect.Maps;
import java.util.Map;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 效果列表
 * <p>原文：
 * A list of effects that the server is able to send to players.
 */
public enum Effect {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 另一种点击声
     * <p>原文：
     * An alternate click sound.
     */
    CLICK2(1000, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 点击声
     * <p>原文：
     * A click sound.
     */
    CLICK1(1001, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 弓的射击声
     * <p>原文：
     * Sound of a bow firing.
     */
    BOW_FIRE(1002, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 开门声（木门）
     * <p>原文：
     * Sound of a door opening.
     *
     * @deprecated 不复存在
     * <p>原文：no longer exists
     * @see Sound#BLOCK_WOODEN_DOOR_OPEN
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    DOOR_TOGGLE(1006, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 开门声（铁门）
     * <p>原文：
     * Sound of a door opening.
     *
     * @deprecated 不复存在
     * <p>原文：no longer exists
     * @see Sound#BLOCK_IRON_DOOR_OPEN
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    IRON_DOOR_TOGGLE(1005, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 开门声（木活板门）
     * <p>原文：
     * Sound of a trapdoor opening.
     *
     * @deprecated 不复存在
     * <p>原文：no longer exists
     * @see Sound#BLOCK_WOODEN_TRAPDOOR_OPEN
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    TRAPDOOR_TOGGLE(1007, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 开门声（铁活板门）
     * <p>原文：
     * Sound of a door opening.
     *
     * @deprecated 不复存在
     * <p>原文：no longer exists
     * @see Sound#BLOCK_IRON_TRAPDOOR_OPEN
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    IRON_TRAPDOOR_TOGGLE(1037, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 开门声（栅栏门）
     * <p>原文：
     * Sound of a door opening.
     *
     * @deprecated 不复存在
     * <p>原文：no longer exists
     * @see Sound#BLOCK_FENCE_GATE_OPEN
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    FENCE_GATE_TOGGLE(1008, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 关门声（木门）
     * <p>原文：
     * Sound of a door closing.
     *
     * @deprecated 不复存在
     * <p>原文：no longer exists
     * @see Sound#BLOCK_WOODEN_DOOR_CLOSE
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    DOOR_CLOSE(1012, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 关门声（铁门）
     * <p>原文：
     * Sound of a door closing.
     *
     * @deprecated 不复存在
     * <p>原文：no longer exists
     * @see Sound#BLOCK_IRON_DOOR_CLOSE
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    IRON_DOOR_CLOSE(1011, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 关门声（木活板门）
     * <p>原文：
     * Sound of a trapdoor closing.
     *
     * @deprecated 不复存在
     * <p>原文：no longer exists
     * @see Sound#BLOCK_WOODEN_TRAPDOOR_CLOSE
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    TRAPDOOR_CLOSE(1013, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 关门声（铁活板门）
     * <p>原文：
     * Sound of a door closing.
     *
     * @deprecated 不复存在
     * <p>原文：no longer exists
     * @see Sound#BLOCK_IRON_TRAPDOOR_CLOSE
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    IRON_TRAPDOOR_CLOSE(1036, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 关门声（栅栏门）
     * <p>原文：
     * Sound of a door closing.
     *
     * @deprecated 不复存在
     * <p>原文：no longer exists
     * @see Sound#BLOCK_FENCE_GATE_CLOSE
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    FENCE_GATE_CLOSE(1014, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 火焰熄灭的声音
     * <p>原文：
     * Sound of fire being extinguished.
     */
    EXTINGUISH(1009, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 播放唱片歌曲的声音
     * <p>原文：
     * A song from a record. Needs the record {@link Material} as additional info.
     */
    RECORD_PLAY(1010, Type.SOUND, Material.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 恶魂的尖叫声
     * <p>原文：
     * Sound of ghast shrieking.
     */
    GHAST_SHRIEK(1015, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 恶魂投射火球的声音
     * <p>原文：
     * Sound of ghast firing.
     */
    GHAST_SHOOT(1016, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 烈焰人投射火球的声音
     * <p>原文：
     * Sound of blaze firing.
     */
    BLAZE_SHOOT(1018, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 僵尸敲木门的声音
     * <p>原文：
     * Sound of zombies chewing on wooden doors.
     */
    ZOMBIE_CHEW_WOODEN_DOOR(1019, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 僵尸敲铁门的声音
     * <p>原文：
     * Sound of zombies chewing on iron doors.
     */
    ZOMBIE_CHEW_IRON_DOOR(1020, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 僵尸摧毁门的声音
     * <p>原文：
     * Sound of zombies destroying a door.
     */
    ZOMBIE_DESTROY_DOOR(1021, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 烟雾效果
     * <p>原文：
     * A visual smoke effect. Needs a {@link BlockFace} direction as additional info.
     */
    SMOKE(2000, Type.VISUAL, BlockFace.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 方块被破坏的声音
     * <p>原文：
     * Sound of a block breaking. Needs {@link org.bukkit.block.data.BlockData} as additional info.
     */
    STEP_SOUND(2001, Type.SOUND, org.bukkit.block.data.BlockData.class, Material.class), // Paper - block data is more correct, but the impl of the mtehods will still work with Material
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 药水的视觉效果
     * <p>原文：
     * Visual effect of a splash potion breaking. Needs {@link Color} data value as
     * additional info.
     */
    POTION_BREAK(2002, Type.VISUAL, Color.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 瞬间喷溅药水破碎时的视觉效果。需要颜色数据值作为附加信息.
     * <p>原文：
     * Visual effect of an instant splash potion breaking. Needs {@link Color} data
     * value as additional info.
     */
    INSTANT_POTION_BREAK(2007, Type.VISUAL, Color.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 末影颗粒视觉效果
     * <p>原文：
     * An ender eye signal; a visual effect.
     */
    ENDER_SIGNAL(2003, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 火焰颗粒视觉效果
     * <p>原文：
     * The flames seen on a mobspawner; a visual effect.
     */
    MOBSPAWNER_FLAMES(2004, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 酿造台酿造的声音
     * <p>原文：
     * The sound played by brewing stands when brewing
     */
    BREWING_STAND_BREW(1035, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 紫颂果生长的声音
     * <p>原文：
     * The sound played when a chorus flower grows
     */
    CHORUS_FLOWER_GROW(1033, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 紫颂果凋谢的声音
     * <p>原文：
     * The sound played when a chorus flower dies
     */
    CHORUS_FLOWER_DEATH(1034, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过传送门的声音
     * <p>原文：
     * The sound played when traveling through a portal
     */
    PORTAL_TRAVEL(1032, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 安置末影之眼的声音
     * <p>原文：
     * The sound played when launching an endereye
     *
     * @deprecated No longer exists
     */
    @Deprecated(forRemoval = true, since = "1.21") // Paper
    ENDEREYE_LAUNCH(1003, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发射烟花的声音
     * <p>原文：
     * The sound played when launching a firework
     */
    FIREWORK_SHOOT(1004, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 村民种植植物的声音
     * 数据是颗粒的数量
     * <p>原文：
     * Particles displayed when a villager grows a plant, data
     * is the number of particles
     *
     * @deprecated partially replaced by {@link #BEE_GROWTH}
     */
    @Deprecated(forRemoval = true, since = "1.20.5") // Paper
    VILLAGER_PLANT_GROW(2005, Type.VISUAL, Integer.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 末影龙攻击和呼吸的声音
     * <p>原文：
     * The sound/particles used by the enderdragon's breath
     * attack.
     */
    DRAGON_BREATH(2006, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 铁砧破坏的声音
     * <p>原文：
     * The sound played when an anvil breaks
     */
    ANVIL_BREAK(1029, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用铁砧的声音
     * <p>原文：
     * The sound played when an anvil is used
     */
    ANVIL_USE(1030, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 铁砧落地声音（坠落）
     * <p>原文：
     * The sound played when an anvil lands after
     * falling
     */
    ANVIL_LAND(1031, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 末影龙喷火的声音
     * <p>原文：
     * Sound of an enderdragon firing
     */
    ENDERDRAGON_SHOOT(1017, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 凋零破坏方块的声音
     * <p>原文：
     * The sound played when a wither breaks a block
     */
    WITHER_BREAK_BLOCK(1022, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 凋零射击的声音
     * <p>原文：
     * Sound of a wither shooting
     */
    WITHER_SHOOT(1024, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 僵尸感染目标（村民）的声音
     * <p>原文：
     * The sound played when a zombie infects a target
     */
    ZOMBIE_INFECT(1026, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 僵尸被转化成村民的声音
     * <p>原文：
     * The sound played when a villager is converted by
     * a zombie
     */
    ZOMBIE_CONVERTED_VILLAGER(1027, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 蝙蝠起飞的声音
     * <p>原文：
     * Sound played by a bat taking off
     */
    BAT_TAKEOFF(1025, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 末地传送门生成的声音和粒子效果
     * <p>原文：
     * The sound/particles caused by an end gateway spawning
     */
    END_GATEWAY_SPAWN(3000, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 末影龙咆哮的声音
     * <p>原文：
     * The sound of an enderdragon growling
     */
    ENDERDRAGON_GROWL(3001, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 幻翼咬人时播放的声音.
     * <p>原文：
     * The sound played when phantom bites.
     */
    PHANTOM_BITE(1039, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 僵尸转化为溺尸时播放的声音.
     * <p>原文：
     * The sound played when a zombie converts to a drowned.
     */
    ZOMBIE_CONVERTED_TO_DROWNED(1040, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尸壳转化为僵尸时播放的声音.
     * <p>原文：
     * The sound played when a husk converts to a zombie.
     */
    HUSK_CONVERTED_TO_ZOMBIE(1041, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用砂轮时播放的声音.
     * <p>原文：
     * The sound played when a grindstone is being used.
     */
    GRINDSTONE_USE(1042, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 翻书页时播放的声音.
     * <p>原文：
     * The sound played when a book page is being turned.
     */
    BOOK_PAGE_TURN(1043, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用锻造台时播放的声音.
     * <p>原文：
     * The sound played when a smithing table is being used.
     */
    SMITHING_TABLE_USE(1044, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尖锐滴水石落在表面时播放的声音.
     * <p>原文：
     * The sound played when a pointed dripstone hits the surface.
     */
    POINTED_DRIPSTONE_LAND(1045, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尖锐滴水石将岩浆滴入炼药锅时播放的声音.
     * <p>原文：
     * The sound played when a pointed dripstone drips lava into a cauldron.
     */
    POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON(1046, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尖锐滴水石将水滴入炼药锅时播放的声音.
     * <p>原文：
     * The sound played when a pointed dripstone drips water into a cauldron.
     */
    POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON(1047, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 骷髅转化为流浪者时播放的声音.
     * <p>原文：
     * The sound played when a skeleton converts to a stray.
     */
    SKELETON_CONVERTED_TO_STRAY(1048, Type.SOUND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试填充堆肥桶时播放的声音/显示的粒子.
     * <p>
     * 成功时为 true，失败时为 false.
     * <p>原文：
     * The sound played / particles shown when a composter is being attempted to
     * fill.
     *
     * True for a successful attempt false for an unsuccessful attempt.
     */
    COMPOSTER_FILL_ATTEMPT(1500, Type.VISUAL, Boolean.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 岩浆与世界交互时播放的声音/显示的粒子.
     * <p>
     * 例如形成石头、黑曜石、玄武岩，或破坏方块（如火把）.
     * <p>原文：
     * The sound played / particles shown when lava interacts with the world.
     *
     * For example by forming stone, obsidian, basalt or destroying blocks such
     * as torches.
     */
    LAVA_INTERACT(1501, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 红石火把烧毁时播放的声音/显示的粒子.
     * <p>原文：
     * The sound played / particles shown when a redstone torch burns out.
     */
    REDSTONE_TORCH_BURNOUT(1502, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将末影之眼放入末地传送门框架时播放的声音/显示的粒子.
     * <p>原文：
     * The sound played / particles shown when an eye of ender is placed into an
     * ender portal frame.
     */
    END_PORTAL_FRAME_FILL(1503, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 滴水石滴落岩浆或水时显示的粒子.
     * <p>
     * 此效果需要在该位置有滴水石，并且滴水石根部有岩浆或水.
     * <p>原文：
     * The particles shown when a dripstone drips lava or water.
     *
     * This effect requires a dripstone at the location as well as lava or water
     * at the root of the dripstone.
     */
    DRIPPING_DRIPSTONE(1504, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用骨粉催熟植物时播放的声音/显示的粒子.
     * <p>
     * 数据为粒子数量.
     * <p>原文：
     * The sound played / particles shown when bone meal is used to grow a
     * plant.
     *
     * Data is the number of particles.
     */
    BONE_MEAL_USE(1505, Type.VISUAL, Integer.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 末影龙破坏方块时显示的粒子.
     * <p>原文：
     * The particles shown when an ender dragon destroys blocks.
     */
    ENDER_DRAGON_DESTROY_BLOCK(2008, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 海绵在超温暖世界（下界）中干燥时显示的粒子.
     * <p>原文：
     * The particles shown when a sponge dries in an ultra warm world (nether).
     */
    SPONGE_DRY(2009, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 闪电击中避雷针或氧化铜时显示的粒子.
     * <p>
     * 数据为粒子显示的轴。如果未提供数据，将在方块面显示粒子.
     * <p>原文：
     * The particles shown when a lightning hits a lightning rod or oxidized
     * copper.
     *
     * Data is the {@link Axis} at which the particle should be shown. If no data is
     * provided it will show the particles at the block faces.
     */
    ELECTRIC_SPARK(3002, Type.VISUAL, Axis.class),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 对铜方块涂蜡时播放的声音/显示的粒子.
     * <p>原文：
     * The sound played / particles shown when wax is applied to a copper block.
     */
    COPPER_WAX_ON(3003, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从铜方块上除蜡时显示的粒子.
     * <p>原文：
     * The particles shown when wax is removed from a copper block.
     */
    COPPER_WAX_OFF(3004, Type.VISUAL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从氧化铜方块上刮除氧化层时显示的粒子.
     * <p>原文：
     * The particles shown when oxidation is scraped of an oxidized copper
     * block.
     */
    OXIDISED_COPPER_SCRAPE(3005, Type.VISUAL),
    // Paper start - add missing effects
    /**
     * The sound of a wither spawning
     */
    WITHER_SPAWNED(1023, Type.SOUND),
    /**
     * The sound of an ender dragon dying
     */
    ENDER_DRAGON_DEATH(1028, Type.SOUND),
    /**
     * The sound of an ender portal being created in the overworld
     */
    END_PORTAL_CREATED_IN_OVERWORLD(1038, Type.SOUND),

    SOUND_STOP_JUKEBOX_SONG(1011, Type.SOUND),

    CRAFTER_CRAFT(1049, Type.SOUND),

    CRAFTER_FAIL(1050, Type.SOUND),

    /**
     * {@link BlockFace} param is the direction to shoot
     */
    SHOOT_WHITE_SMOKE(2010, Type.VISUAL, BlockFace.class),

    /**
     * {@link Integer} param is the number of particles
     */
    BEE_GROWTH(2011, Type.VISUAL, Integer.class),

    /**
     * {@link Integer} param is the number of particles
     */
    TURTLE_EGG_PLACEMENT(2012, Type.VISUAL, Integer.class),

    /**
     * {@link Integer} param is relative to the number of particles
     */
    SMASH_ATTACK(2013, Type.VISUAL, Integer.class),

    PARTICLES_SCULK_CHARGE(3006, Type.VISUAL, Integer.class),

    PARTICLES_SCULK_SHRIEK(3007, Type.SOUND),

    /**
     * Requires a {@link org.bukkit.block.data.BlockData} param
     */
    PARTICLES_AND_SOUND_BRUSH_BLOCK_COMPLETE(3008, Type.VISUAL, org.bukkit.block.data.BlockData.class),

    PARTICLES_EGG_CRACK(3009, Type.VISUAL),

    @Deprecated(forRemoval = true, since = "1.20.5")
    GUST_DUST(3010, Type.VISUAL),

    /**
     * {@link Boolean} param is true for "ominous" vaults
     */
    TRIAL_SPAWNER_SPAWN(3011, Type.VISUAL, Boolean.class),

    /**
     * {@link Boolean} param is true for "ominous" vaults
     */
    TRIAL_SPAWNER_SPAWN_MOB_AT(3012, Type.VISUAL, Boolean.class),

    /**
     * {@link Integer} param is the number of players
     */
    TRIAL_SPAWNER_DETECT_PLAYER(3013, Type.VISUAL, Integer.class),

    TRIAL_SPAWNER_EJECT_ITEM(3014, Type.VISUAL),

    /**
     * {@link Boolean} param is true for "ominous" vaults
     */
    VAULT_ACTIVATE(3015, Type.VISUAL, Boolean.class),

    /**
     * {@link Boolean} param is true for "ominous" vaults
     */
    VAULT_DEACTIVATE(3016, Type.VISUAL, Boolean.class),

    VAULT_EJECT_ITEM(3017, Type.VISUAL),

    SPAWN_COBWEB(3018, Type.VISUAL),

    /**
     * {@link Integer} param is the number of players
     */
    TRIAL_SPAWNER_DETECT_PLAYER_OMINOUS(3019, Type.VISUAL, Integer.class),

    /**
     * {@link Boolean} param is true for changing to "ominous"
     */
    TRIAL_SPAWNER_BECOME_OMINOUS(3020, Type.VISUAL, Boolean.class),

    /**
     * {@link Boolean} param is true for "ominous" vaults
     */
    TRIAL_SPAWNER_SPAWN_ITEM(3021, Type.VISUAL, Boolean.class),

    SOUND_WITH_CHARGE_SHOT(1051, Type.SOUND),
    ;
    // Paper end

    private final int id;
    private final Type type;
    private final java.util.List<Class<?>> data; // Paper - support multiple data types
    private static final Map<Integer, Effect> BY_ID = Maps.newHashMap();

    Effect(int id, /*@NotNull*/ Type type) {
        this(id, type, (Class<?>[]) null); // Paper - support multiple data types
    }

    Effect(int id, /*@NotNull*/ Type type, /*@Nullable*/ Class<?>...data) { // Paper - support multiple data types
        this.id = id;
        this.type = type;
        this.data = data != null ? java.util.List.of(data) : null; // Paper - support multiple data types
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个效果的id.
     * <p>原文：
     * Gets the ID for this effect.
     *
     * @return 效果id
     * <p>原文：ID of this effect
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    public int getId() {
        return this.id;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 效果的类型
     * <p>原文：The type of the effect.
     * @deprecated some effects can be both or neither
     */
    @NotNull
    @Deprecated // Paper - both
    public Type getType() {
        return this.type;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 代表这个效果的类，不存在为null
     * <p>原文：The class which represents data for this effect, or null if
     *     none
     */
    @Nullable
    public Class<?> getData() {
        return this.data == null ? null : this.data.get(0); // Paper
    }

    // Paper start - support deprecated data types
    @org.jetbrains.annotations.ApiStatus.Internal
    public boolean isApplicable(Object obj) {
        return this.data != null && com.google.common.collect.Iterables.any(this.data, aClass -> aClass.isAssignableFrom(obj.getClass()));
    }
    // Paper end - support deprecated data types

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定id相关联的效果.
     * <p>原文：
     * Gets the Effect associated with the given ID.
     *
     * @param id 效果id
     * <p>原文：ID of the Effect to return
     * @return 给定id相关联效果
     * <p>原文：Effect with the given ID
     * @apiNote Internal Use Only
     */
    @org.jetbrains.annotations.ApiStatus.Internal // Paper
    @Nullable
    public static Effect getById(int id) {
        return BY_ID.get(id);
    }

    static {
        for (Effect effect : values()) {
            if (Enums.getField(effect).isAnnotationPresent(Deprecated.class)) {
                continue;
            }
            BY_ID.put(effect.id, effect);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表效果的类型
     * <p>原文：
     * Represents the type of an effect.
     *
     * @deprecated not representative of what Effect does
     */
    @Deprecated // Paper
    public enum Type { SOUND, VISUAL }
}
