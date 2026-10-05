package org.bukkit.entity;

import java.util.Set;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一只猪灵。
 * <p>原文：
 * Represents a Piglin.
 */
public interface Piglin extends PiglinAbstract, InventoryHolder, com.destroystokyo.paper.entity.RangedEntity { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取猪灵是否能猎杀疣猪兽。
     * <p>原文：
     * Get whether the piglin is able to hunt hoglins.
     *
     * @return 猪灵是否能猎杀疣猪兽
     * <p>原文：Whether the piglin is able to hunt hoglins
     */
    public boolean isAbleToHunt();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置猪灵是否能猎杀疣猪兽。
     * <p>原文：
     * Sets whether the piglin is able to hunt hoglins.
     *
     * @param flag 猪灵是否能猎杀疣猪兽
     * <p>原文：Whether the piglin is able to hunt hoglins.
     */
    public void setIsAbleToHunt(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加一个材料到允许以物易物的材料列表中。
     * <p>原文：
     * Adds a material to the allowed list of materials to barter with.
     *
     * @param material 要添加的材料
     * <p>原文：The material to add
     * @return 如果物品已成功添加则为 true，否则为 false
     * <p>原文：true if the item has been added successfully, false otherwise
     */
    public boolean addBarterMaterial(@NotNull Material material);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从允许以物易物的材料列表中移除一个材料。
     * <p>原文：
     * Removes a material from the allowed list of materials to barter with.
     *
     * <strong>Note:</strong> It's not possible to override the default
     * bartering item gold_ingots as payment. To block gold_ingots see
     * {@link org.bukkit.event.entity.PiglinBarterEvent}.
     *
     * @param material 要移除的材料
     * <p>原文：The material to remove
     * @return 如果物品已成功移除则为 true，否则为 false
     * <p>原文：true if the item has been removed successfully, false otherwise
     */
    public boolean removeBarterMaterial(@NotNull Material material);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加一个猪灵会拾取并存储在其物品栏中的材料。
     * <p>原文：
     * Adds a material the piglin will pickup and store in his inventory.
     *
     * @param material 你希望猪灵感兴趣的材料
     * <p>原文：The material you want the piglin to be interested in
     * @return 如果物品已成功添加则为 true，否则为 false
     * <p>原文：true if the item has been added successfully, false otherwise
     */
    public boolean addMaterialOfInterest(@NotNull Material material);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从猪灵会拾取的材料列表中移除一个材料。
     * <p>原文：
     * Removes a material from the list of materials the piglin will pickup.
     *
     * <strong>Note:</strong> It's not possible to override the default list of
     * item the piglin will pickup. To cancel pickup see
     * {@link org.bukkit.event.entity.EntityPickupItemEvent}.
     *
     * @param material 你希望从兴趣列表中移除的材料
     * <p>原文：The material you want removed from the interest list
     * @return 如果物品已成功移除则为 true，否则为 false
     * <p>原文：true if the item has been removed successfully, false otherwise
     */
    public boolean removeMaterialOfInterest(@NotNull Material material);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回猪灵会拾取的材料的不可变集合。
     * <p>原文：
     * Returns an immutable set of materials the piglins will pickup.
     * <br>
     * <strong>Note:</strong> This set will not include the items that are set
     * by default. To interact with those items see
     * {@link org.bukkit.event.entity.EntityPickupItemEvent}.
     *
     * @return 一个不可变的材料集合
     * <p>原文：An immutable materials set
     */
    @NotNull
    public Set<Material> getInterestList();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回猪灵会以物易物的材料的不可变集合。
     * <p>原文：
     * Returns an immutable set of materials the piglins will barter with.
     *
     * <strong>Note:</strong> This set will not include the items that are set
     * by default. To interact with those items see
     * {@link org.bukkit.event.entity.PiglinBarterEvent}.
     *
     * @return 一个不可变的材料集合
     * <p>原文：An immutable materials set
     */
    @NotNull
    public Set<Material> getBarterList();

    /**
     * Causes the piglin to appear as if they are charging
     * a crossbow.
     * <p>
     * This works with any item currently held in the piglin's hand.
     *
     * @param chargingCrossbow is charging
     */
    void setChargingCrossbow(boolean chargingCrossbow);

    /**
     * Gets if the piglin is currently charging the
     * item in their hand.
     *
     * @return is charging
     */
    boolean isChargingCrossbow();

    /**
     * Sets whether the Piglin is dancing or not
     *
     * @param dancing is dancing
     */
    void setDancing(boolean dancing);

    /**
     * Causes the piglin to dance for a
     * specified amount of time
     *
     * @param duration duration of the dance in ticks
     */
    void setDancing(long duration);

    /**
     * Gets if the piglin is currently dancing
     *
     * @return is dancing
     */
    boolean isDancing();

}
