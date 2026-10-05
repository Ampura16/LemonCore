package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表具有共享生成行为和生物上限的实体组。
 * <p>原文：
 * Represents groups of entities with shared spawn behaviors and mob caps.
 *
 * @see <a href="https://minecraft.wiki/w/Spawn#Java_Edition_mob_cap">Minecraft Wiki</a>
 */
public enum SpawnCategory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 与怪物相关的实体，例如：女巫、僵尸、苦力怕等。
     * <p>原文：
     * Entities related to Monsters, eg: Witch, Zombie, Creeper, etc.
     */
    MONSTER,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 与动物相关的实体，例如：炽足兽、牛、海龟等。
     * <p>原文：
     * Entities related to Animals, eg: Strider, Cow, Turtle, etc.
     */
    ANIMAL,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 与水生动物相关的实体，例如：鱿鱼或海豚。
     * <p>原文：
     * Entities related to Water Animals, eg: Squid or Dolphin.
     */
    WATER_ANIMAL,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 与水生环境生物相关的实体，例如：鳕鱼、河豚、热带鱼、鲑鱼等。
     * <p>原文：
     * Entities related to Water Ambient, eg: Cod, PufferFish, Tropical Fish,
     * Salmon, etc.
     */
    WATER_AMBIENT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 与水下生物相关的实体，例如：发光鱿鱼。
     * <p>原文：
     * Entities related to Water Underground, eg: Glow Squid.
     */
    WATER_UNDERGROUND_CREATURE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 与环境生物相关的实体，例如：蝙蝠。
     * <p>原文：
     * Entities related to Ambient, eg: Bat.
     */
    AMBIENT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 所有美西螈都属于此分类。
     * <p>原文：
     * All the Axolotl are represented by this Category.
     */
    AXOLOTL,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 与生物无关的实体，例如：玩家、盔甲架、船等。
     * <p>原文：
     * Entities not related to a mob, eg: Player, ArmorStand, Boat, etc.
     */
    MISC;
}
