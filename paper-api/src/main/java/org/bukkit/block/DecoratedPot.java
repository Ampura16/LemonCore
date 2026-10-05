package org.bukkit.block;

import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.DecoratedPotInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个捕获的饰纹陶罐状态.
 * <p>原文：
 * Represents a captured state of a decorated pot.
 */
public interface DecoratedPot extends io.papermc.paper.block.TileStateInventoryHolder, org.bukkit.loot.Lootable { // Paper - expose loot table & TileStateInventoryHolder

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定的侧面上设置陶片.
     * <p>原文：
     * Set the sherd on the provided side.
     *
     * @param side 要设置的侧面
     * <p>原文：the side to set
     * @param sherd 陶片, 或null来设置空白侧面
     * <p>原文：the sherd, or null to set a blank side.
     * @throws IllegalArgumentException 如果陶片既没有被{@link Tag#ITEMS_DECORATED_POT_SHERDS}标记,
     * 也不是{@link Material#BRICK}, 也不是{@code null}
     * <p>原文：if the sherd is not either
     * tagged by {@link Tag#ITEMS_DECORATED_POT_SHERDS}, {@link Material#BRICK},
     * or {@code null}
     */
    public void setSherd(@NotNull Side side, @Nullable Material sherd);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定侧面上的陶片.
     * <p>原文：
     * Get the sherd on the provided side.
     *
     * @param side 要获取的侧面
     * <p>原文：the side to get
     * @return 侧面上的陶片, 如果是空白则返回{@link Material#BRICK}
     * <p>原文：the sherd on the side or {@link Material#BRICK} if it's blank
     */
    @NotNull
    public Material getSherd(@NotNull Side side);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此饰纹陶罐所有侧面及其上陶片的Map.
     * 如果某个侧面没有特定的陶片, 则使用{@link Material#BRICK}作为该侧面的值.
     * <p>原文：
     * Gets a Map of all sides on this decorated pot and the sherds on them.
     * If a side does not have a specific sherd on it, {@link Material#BRICK}
     * will be the value of that side.
     *
     * @return 陶片
     * <p>原文：the sherds
     */
    @NotNull
    public Map<Side, Material> getSherds();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此饰纹陶罐上的陶片. 对于没有特定陶片的面,
     * 使用{@link Material#BRICK}代替.
     * <p>原文：
     * Gets the sherds on this decorated pot. For faces without a specific sherd,
     * {@link Material#BRICK} is used in its place.
     *
     * @return 陶片
     * <p>原文：the sherds
     * @deprecated 请使用{@link #getSherds()}
     * <p>原文：in favor of {@link #getSherds()}
     */
    @Deprecated(since = "1.20.1")
    @NotNull
    public List<Material> getShards();

    // Paper - move docs to TileStateInventoryHolder
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 物品栏
     * @see Container#getInventory()
     */
    @NotNull
    @Override
    public DecoratedPotInventory getInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 快照物品栏
     * @see Container#getSnapshotInventory()
     */
    @Override // Paper - move docs to TileStateInventoryHolder
    @NotNull
    public DecoratedPotInventory getSnapshotInventory();

    /**
     * Run the specified animation on the decorated pot.
     *
     * @param style the animation style
     * @throws IllegalStateException if this block state is not placed
     */
    public void startWobble(@NotNull WobbleStyle style);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 饰纹陶罐的一个侧面. 侧面相对于{@link org.bukkit.block.data.type.DecoratedPot}的朝向状态.
     * <p>原文：
     * A side on a decorated pot. Sides are relative to the facing state of a
     * {@link org.bukkit.block.data.type.DecoratedPot}.
     */
    public static enum Side {
        BACK,
        LEFT,
        RIGHT,
        FRONT
    }

    /**
     * Style a DecoratedPot can wobble in.
     */
    public static enum WobbleStyle {
        POSITIVE,
        NEGATIVE
    }
}
