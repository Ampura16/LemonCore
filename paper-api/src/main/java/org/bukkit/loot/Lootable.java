package org.bukkit.loot;

import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示一个可以拥有战利品表的 {@link org.bukkit.block.Container} 或 {@link org.bukkit.entity.Mob}。
 * <br>
 * 容器的战利品只会在打开时生成，而且仅在容器<i>首次</i>打开时生成。
 * <br>
 * 实体只会在死亡时生成战利品。
 * <p>原文：
 * Represents a {@link org.bukkit.block.Container} or a
 * {@link org.bukkit.entity.Mob} that can have a loot table.
 * <br>
 * Container loot will only generate upon opening, and only when the container
 * is <i>first</i> opened.
 * <br>
 * Entities will only generate loot upon death.
 */
public interface Lootable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为容器或实体设置战利品表。
     * <br>
     * 要移除战利品表请使用 null。
     * <p>原文：
     * Set the loot table for a container or entity.
     * <br>
     * If the provided loot table is null, the loot table will be reset to its default behavior.
     *
     * @param table 此 {@link org.bukkit.block.Container} 或 {@link org.bukkit.entity.Mob} 将拥有的战利品表。
     * <p>原文：the Loot Table this {@link org.bukkit.block.Container} or
     * {@link org.bukkit.entity.Mob} will have.
     */
    void setLootTable(@Nullable LootTable table);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取附加到此方块或实体的战利品表。
     * <br>
     * 如果方块/实体没有战利品表，则返回 null，而不是空的战利品表。
     * <p>原文：
     * Gets the Loot Table attached to this block or entity.
     * <br>
     *
     * If a block/entity does not have a loot table, this will return null, NOT
     * an empty loot table.
     *
     * @return 附加到此方块或实体的战利品表。
     * <p>原文：the Loot Table attached to this block or entity.
     */
    @Nullable
    LootTable getLootTable();

    // Paper start
    /**
     * Set the loot table and seed for a container or entity at the same time.
     * <br>
     * If the provided loot table is null, the loot table will be reset to its default behavior.
     *
     * @param table the Loot Table this {@link org.bukkit.block.Container} or {@link org.bukkit.entity.Mob} will have.
     * @param seed the seed to used to generate loot. Default is 0.
     */
    void setLootTable(final @Nullable LootTable table, final long seed);

    /**
     * Returns whether or not this object has a Loot Table
     * @return Has a loot table
     */
    default boolean hasLootTable() {
        return this.getLootTable() != null;
    }

    /**
     * Clears the associated Loot Table to this object, essentially resetting it to default
     * @see #setLootTable(LootTable)
     */
    default void clearLootTable() {
        this.setLootTable(null);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此战利品表生成战利品时使用的种子。
     * <p>原文：
     * Set the seed used when this Loot Table generates loot.
     *
     * @param seed 用于生成战利品的种子。默认为 0。
     * <p>原文：the seed to used to generate loot. Default is 0.
     */
    void setSeed(long seed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取战利品表的种子。
     * <br>
     * 种子用于生成战利品。
     * <p>原文：
     * Get the Loot Table's seed.
     * <br>
     * The seed is used when generating loot.
     *
     * @return 种子
     * <p>原文：the seed
     */
    long getSeed();
}
