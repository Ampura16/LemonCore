package org.bukkit;

import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示爆炸的结果。
 * <p>原文：
 * Represents the outcome of an explosion.
 */
@ApiStatus.Experimental
public enum ExplosionResult {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示未发生任何变化的爆炸。
     *
     * 当 {@link org.bukkit.GameRule#MOB_GRIEFING} 被禁用时会出现此情况。
     * <p>原文：
     * Represents an explosion where no change took place.
     *
     * This is the case when {@link org.bukkit.GameRules#MOB_GRIEFING} is
     * disabled.
     */
    KEEP,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示所有被破坏的方块都会掉落物品的爆炸。
     *
     * 当 {@link org.bukkit.GameRule#TNT_EXPLOSION_DROP_DECAY} 或
     * {@link org.bukkit.GameRule#BLOCK_EXPLOSION_DROP_DECAY} 被禁用时会出现此情况。
     * <p>原文：
     * Represents an explosion where all destroyed blocks drop their items.
     *
     * This is the case when
     * {@link org.bukkit.GameRules#TNT_EXPLOSION_DROP_DECAY} or
     * {@link org.bukkit.GameRules#BLOCK_EXPLOSION_DROP_DECAY} is disabled.
     */
    DESTROY,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示爆炸仅导致部分方块掉落的爆炸。
     * <p>原文：
     * Represents an explosion where explosions cause only some blocks to drop.
     */
    DESTROY_WITH_DECAY,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示已发生方块变化/更新的爆炸。
     *
     * 例如，使用风弹时会导致附近的按钮、拉杆和钟被激活。
     * <p>原文：
     * Represents an explosion where a block change/update has happened.
     *
     * For example, when a wind charge is used it will cause nearby buttons,
     * levers and bells to be activated.
     */
    TRIGGER_BLOCK
}
