package com.destroystokyo.paper.loottable;

import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.loot.Lootable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示关联了战利品表、首次打开时会自动填充的物品栏。
 * <p>
 * 提供了新的特性和 API，支持在经过可配置的时间后根据该战利品表自动刷新物品栏内容。
 * <p>
 * 物品栏如何根据战利品表填充的行为可能因 Minecraft 版本和战利品表特性而异。
 * <p>原文：
 * Represents an Inventory that contains a Loot Table associated to it that will
 * automatically fill on first open.
 * <p>
 * A new feature and API is provided to support automatically refreshing the contents
 * of the inventory based on that Loot Table after a configurable amount of time has passed.
 * <p>
 * The behavior of how the Inventory is filled based on the loot table may vary based
 * on Minecraft versions and the Loot Table feature.
 */
@NullMarked
public interface LootableInventory extends Lootable {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 服务器所有者必须启用世界中的对象是否应重新填充
     * <p>原文：
     * Server owners have to enable whether an object in a world should refill
     *
     * @return 此物品栏当前所在的世界是否启用了可补充战利品
     * <p>原文：If the world this inventory is currently in has Replenishable Lootables enabled
     */
    boolean isRefillEnabled();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此对象是否曾被填充过
     * <p>原文：
     * Whether this object has ever been filled
     *
     * @return 是否曾被填充过
     * <p>原文：Has ever been filled
     */
    boolean hasBeenFilled();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此玩家是否曾搜刮过此方块
     * <p>原文：
     * Has this player ever looted this block
     *
     * @param player 要检查的玩家
     * <p>原文：The player to check
     * @return 此玩家是否已搜刮过此方块
     * <p>原文：Whether this player has looted this block
     */
    default boolean hasPlayerLooted(final Player player) {
        return this.hasPlayerLooted(player.getUniqueId());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此玩家能否搜刮此方块。会考虑“限制玩家重复搜刮”设置
     * <p>原文：
     * Checks if this player can loot this block. Takes into account the "restrict player reloot" settings
     *
     * @param player 要检查的玩家
     * <p>原文：the player to check
     * @return 此玩家能否搜刮此方块
     * <p>原文：Whether this player can loot this block
     */
    boolean canPlayerLoot(UUID player);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此玩家是否曾搜刮过此方块
     * <p>原文：
     * Has this player ever looted this block
     *
     * @param player 要检查的玩家
     * <p>原文：The player to check
     * @return 此玩家是否已搜刮过此方块
     * <p>原文：Whether this player has looted this block
     */
    boolean hasPlayerLooted(UUID player);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家上次搜刮此对象的时间戳（毫秒）
     * <p>原文：
     * Gets the timestamp, in milliseconds, of when the player last looted this object
     *
     * @param player 要检查的玩家
     * <p>原文：The player to check
     * @return 上次搜刮的时间戳；如果玩家未搜刮过此对象则为 null
     * <p>原文：Timestamp last looted, or null if player has not looted this object
     */
    default @Nullable Long getLastLooted(final Player player) {
        return this.getLastLooted(player.getUniqueId());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家上次搜刮此对象的时间戳（毫秒）
     * <p>原文：
     * Gets the timestamp, in milliseconds, of when the player last looted this object
     *
     * @param player 要检查的玩家
     * <p>原文：The player to check
     * @return 上次搜刮的时间戳；如果玩家未搜刮过此对象则为 null
     * <p>原文：Timestamp last looted, or null if player has not looted this object
     */
    @Nullable Long getLastLooted(UUID player);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 更改玩家是否已搜刮过此方块的状态
     * <p>原文：
     * Change the state of whether a player has looted this block
     *
     * @param player 要更改状态的玩家
     * <p>原文：The player to change state for
     * @param looted true 表示将玩家加入已掠夺列表，false 表示移除
     * <p>原文：true to add player to looted list, false to remove
     * @return 玩家之前是否已搜刮过此处的状态
     * <p>原文：The previous state of whether the player had looted this or not
     */
    default boolean setHasPlayerLooted(final Player player, final boolean looted) {
        return this.setHasPlayerLooted(player.getUniqueId(), looted);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 更改玩家是否已搜刮过此方块的状态
     * <p>原文：
     * Change the state of whether a player has looted this block
     *
     * @param player 要更改状态的玩家
     * <p>原文：The player to change state for
     * @param looted true 表示将玩家加入已掠夺列表，false 表示移除
     * <p>原文：true to add player to looted list, false to remove
     * @return 玩家之前是否已搜刮过此处的状态
     * <p>原文：The previous state of whether the player had looted this or not
     */
    boolean setHasPlayerLooted(UUID player, boolean looted);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此对象是否已被填充且现在有待进行的重新填充
     * <p>原文：
     * Returns Whether this object has been filled and now has a pending refill
     *
     * @return 是否有待进行的重新填充
     * <p>原文：Has pending refill
     */
    boolean hasPendingRefill();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取 Lootable 对象上次重新填充的时间戳（毫秒）
     * <p>原文：
     * Gets the timestamp in milliseconds that the Lootable object was last refilled
     *
     * @return 若从未重新填充过则为 -1，否则为时间戳（毫秒）
     * <p>原文：-1 if it was never refilled, or timestamp in milliseconds
     */
    long getLastFilled();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取 Lootable 对象将要重新填充的时间戳（毫秒）
     * <p>原文：
     * Gets the timestamp in milliseconds that the Lootable object will refill
     *
     * @return 若未安排重新填充则为 -1，否则为时间戳（毫秒）
     * <p>原文：-1 if it is not scheduled for refill, or timestamp in milliseconds
     */
    long getNextRefill();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此对象下次重新填充的时间戳（毫秒）
     * <p>原文：
     * Sets the timestamp in milliseconds of the next refill for this object
     *
     * @param refillAt 时间戳（毫秒）。-1 表示清除下次补充
     * <p>原文：timestamp in milliseconds. -1 to clear next refill
     * @return 之前安排的重新填充时间；如果未安排则为 -1
     * <p>原文：The previous scheduled time to refill, or -1 if was not scheduled
     */
    long setNextRefill(long refillAt);
}
