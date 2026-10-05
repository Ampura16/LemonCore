package org.bukkit;

import java.util.Locale;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示被服务器监测的统计数据
 * <p>原文：
 * Represents a countable statistic, which is tracked by the server.
 */
public enum Statistic implements Keyed {
    // Start generate - StatisticCustom
    ANIMALS_BRED,
    AVIATE_ONE_CM,
    BELL_RING,
    BOAT_ONE_CM,
    ARMOR_CLEANED,
    BANNER_CLEANED,
    CLEAN_SHULKER_BOX,
    CLIMB_ONE_CM,
    CROUCH_ONE_CM,
    DAMAGE_ABSORBED,
    DAMAGE_BLOCKED_BY_SHIELD,
    DAMAGE_DEALT,
    DAMAGE_DEALT_ABSORBED,
    DAMAGE_DEALT_RESISTED,
    DAMAGE_RESISTED,
    DAMAGE_TAKEN,
    DEATHS,
    DROP_COUNT,
    CAKE_SLICES_EATEN,
    ITEM_ENCHANTED,
    FALL_ONE_CM,
    CAULDRON_FILLED,
    FISH_CAUGHT,
    FLY_ONE_CM,
    HAPPY_GHAST_ONE_CM,
    HORSE_ONE_CM,
    DISPENSER_INSPECTED,
    DROPPER_INSPECTED,
    HOPPER_INSPECTED,
    INTERACT_WITH_ANVIL,
    BEACON_INTERACTION,
    INTERACT_WITH_BLAST_FURNACE,
    BREWINGSTAND_INTERACTION,
    INTERACT_WITH_CAMPFIRE,
    INTERACT_WITH_CARTOGRAPHY_TABLE,
    CRAFTING_TABLE_INTERACTION,
    FURNACE_INTERACTION,
    INTERACT_WITH_GRINDSTONE,
    INTERACT_WITH_LECTERN,
    INTERACT_WITH_LOOM,
    INTERACT_WITH_SMITHING_TABLE,
    INTERACT_WITH_SMOKER,
    INTERACT_WITH_STONECUTTER,
    JUMP,
    LEAVE_GAME,
    MINECART_ONE_CM,
    MOB_KILLS,
    NAUTILUS_ONE_CM,
    OPEN_BARREL,
    CHEST_OPENED,
    ENDERCHEST_OPENED,
    SHULKER_BOX_OPENED,
    PIG_ONE_CM,
    NOTEBLOCK_PLAYED,
    RECORD_PLAYED,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 名称具有误导性，实际上记录的是已游玩的tick数。
     */
    PLAY_ONE_MINUTE,
    PLAYER_KILLS,
    FLOWER_POTTED,
    RAID_TRIGGER,
    RAID_WIN,
    SLEEP_IN_BED,
    SNEAK_TIME,
    SPRINT_ONE_CM,
    STRIDER_ONE_CM,
    SWIM_ONE_CM,
    TALKED_TO_VILLAGER,
    TARGET_HIT,
    TIME_SINCE_DEATH,
    TIME_SINCE_REST,
    TOTAL_WORLD_TIME,
    TRADED_WITH_VILLAGER,
    TRAPPED_CHEST_TRIGGERED,
    NOTEBLOCK_TUNED,
    CAULDRON_USED,
    WALK_ON_WATER_ONE_CM,
    WALK_ONE_CM,
    WALK_UNDER_WATER_ONE_CM,
    // End generate - StatisticCustom
    // Start generate - StatisticType
    BREAK_ITEM(Type.ITEM),
    CRAFT_ITEM(Type.ITEM),
    DROP(Type.ITEM),
    KILL_ENTITY(Type.ENTITY),
    ENTITY_KILLED_BY(Type.ENTITY),
    MINE_BLOCK(Type.BLOCK),
    PICKUP(Type.ITEM),
    USE_ITEM(Type.ITEM);
    // End generate - StatisticType

    private final Type type;
    private final NamespacedKey key;

    private Statistic() {
        this(Type.UNTYPED);
    }

    private Statistic(/*@NotNull*/ Type type) {
        this.type = type;
        this.key = NamespacedKey.minecraft(name().toLowerCase(Locale.ROOT));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这项统计数据的种类.
     * <p>原文：
     * Gets the type of this statistic.
     *
     * @return 这项统计数据的种类
     * <p>原文：the type of this statistic
     */
    @NotNull
    public Type getType() {
        return type;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查该数据是否为次级统计数据.
     * <p>
     * 一项次级统计数据同时存在于每个方块、物品或实体(取决于{@link #getType()}).
     * <p>
     * 这是个多余的方法，相当于检查<code>getType() != Type.UNTYPED</code>.
     * <p>原文：
     * Checks if this is a substatistic.
     * <p>
     * A substatistic exists en masse for each block, item, or entitytype, depending on
     * {@link #getType()}.
     * <p>
     * This is a redundant method and equivalent to checking
     * <code>getType() != Type.UNTYPED</code>
     *
     * @return 如果为次级统计数据则返回true，否则返回false
     * <p>原文：true if this is a substatistic
     */
    public boolean isSubstatistic() {
        return type != Type.UNTYPED;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否为一项用于处理方块的次级统计数据.
     * <p>
     * 这是个多余的方法，相当于检查<code>getType() == Type.BLOCK</code>.
     * <p>原文：
     * Checks if this is a substatistic dealing with blocks.
     * <p>
     * This is a redundant method and equivalent to checking
     * <code>getType() == Type.BLOCK</code>
     *
     * @return 用于处理方块则返回true，否则返回false
     * <p>原文：true if this deals with blocks
     */
    public boolean isBlock() {
        return type == Type.BLOCK;
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 统计数据的种类
     * <p>原文：
     * The type of statistic.
     */
    public enum Type {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 无类型的统计数据（通用统计数据）
         * <p>原文：
         * Statistics of this type do not require a qualifier.
         */
        UNTYPED,

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 与物品有关的统计数据
         * <p>原文：
         * Statistics of this type require an Item Material qualifier.
         */
        ITEM,

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 与方块有关的统计数据
         * <p>原文：
         * Statistics of this type require a Block Material qualifier.
         */
        BLOCK,

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 与实体有关的统计数据
         * <p>原文：
         * Statistics of this type require an EntityType qualifier.
         */
        ENTITY;
    }
}
