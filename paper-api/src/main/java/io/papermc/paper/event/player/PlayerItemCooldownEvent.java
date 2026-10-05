package io.papermc.paper.event.player;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家使用物品并获得物品冷却时触发。
 * <p>原文：
 * Fired when a player receives an item cooldown when using an item.
 *
 * @see PlayerItemGroupCooldownEvent for a more general event when applied to a group of items
 */
@NullMarked
public class PlayerItemCooldownEvent extends PlayerItemGroupCooldownEvent {

    private final Material type;

    @ApiStatus.Internal
    public PlayerItemCooldownEvent(final Player player, final Material type, final NamespacedKey cooldownGroup, final int cooldown) {
        super(player, cooldownGroup, cooldown);
        this.type = type;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取受冷却影响的物品的材料。
     * <p>原文：
     * Get the material of the item affected by the cooldown.
     *
     * @return 受冷却影响的材料
     * <p>原文：material affected by the cooldown
     */
    public Material getType() {
        return this.type;
    }
}
