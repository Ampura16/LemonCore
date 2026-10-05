package org.bukkit.block;

import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表幽匿催发体的捕获状态.
 * <p>原文：
 * Represents a captured state of a sculk catalyst.
 */
public interface SculkCatalyst extends TileState {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 导致一次新的幽匿蔓延, 就好像有实体刚在这个催发体周围死亡一样.
     * <p>
     * 通常, 能量值应该设置为生物的经验奖励值
     * ({@link EntityDeathEvent#getDroppedExp()}), 动物通常是
     * 3-5, 普通生物是5-10(凋灵骷髅最高可达50).
     * 粗略来说, 每点能量值会多放置一个幽匿方块.
     * <p>
     * 如果 <code>charges > 1000</code>, 将在方块中生成多个游标.
     * <p>原文：
     * Causes a new sculk bloom, as if an entity just died around this catalyst.
     * <p>
     * Typically, charges should be set to the exp reward of a mob
     * ({@link EntityDeathEvent#getDroppedExp()}), which is usually
     * 3-5 for animals, and 5-10 for the average mob (up to 50 for
     * wither skeletons). Roughly speaking, for each charge, 1 more
     * sculk block will be placed.
     * <p>
     * If <code>charges > 1000</code>, multiple cursors will be spawned in the
     * block.
     *
     * @param block 在哪个方块中生成游标
     * <p>原文：which block to spawn the cursor in
     * @param charges 要生成多少能量值
     * <p>原文：how much charge to spawn.
     */
    void bloom(@NotNull Block block, int charges);

    // Paper start - SculkCatalyst bloom API
    /**
     * Bloom at the specified position as if an entity that drops experience just died there.
     *
     * @param position position to bloom at
     * @param charge charge to bloom with, normally the amount of experience dropped from the dead entity
     */
    void bloom(@NotNull io.papermc.paper.math.Position position, int charge);
    // Paper end - SculkCatalyst bloom API
}
