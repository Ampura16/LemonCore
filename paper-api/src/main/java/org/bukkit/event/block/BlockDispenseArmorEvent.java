package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个可装备物品从某个方块发射出来并装备到附近的实体上时触发本事件.
 * <p>
 * 如果本事件被取消, 那么装备将不会装备到目标实体上.
 * <p>原文：
 * Called when an equippable item is dispensed from a block and equipped on a
 * nearby entity.
 * <p>
 * If this event is cancelled, the equipment will not be
 * equipped on the target entity.
 */
public class BlockDispenseArmorEvent extends BlockDispenseEvent {

    private final LivingEntity target;

    @ApiStatus.Internal
    public BlockDispenseArmorEvent(@NotNull Block block, @NotNull ItemStack dispensed, @NotNull LivingEntity target) {
        super(block, dispensed, new Vector(0, 0, 0));
        this.target = target;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取盔甲装备给的目标生物.
     * <p>原文：
     * Get the living entity on which the armor was dispensed.
     *
     * @return 目标实体
     * <p>原文：the target entity
     */
    @NotNull
    public LivingEntity getTargetEntity() {
        return this.target;
    }
}
