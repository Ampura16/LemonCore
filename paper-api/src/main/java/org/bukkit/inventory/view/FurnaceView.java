package org.bukkit.inventory.view;

import org.bukkit.block.Furnace;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个提供与熔炉视图数据相关额外方法的 {@link InventoryView} 实例。
 * <p>原文：
 * An instance of {@link InventoryView} which provides extra methods related to
 * furnace view data.
 */
public interface FurnaceView extends InventoryView {

    @NotNull
    @Override
    FurnaceInventory getTopInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此视图的烹饪时间。
     * <p>
     * 详见 {@link Furnace#getCookTime()}。
     * <p>原文：
     * The cook time for this view.
     * <p>
     * See {@link Furnace#getCookTime()} for more information.
     *
     * @return 一个0到1之间的数字
     * <p>
     * 原文：The cook time for this view.
     * <p>
     * See {@link Furnace#getCookTime()} for more information.
     * <p>原文：a number between 0 and 1
     */
    float getCookTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此视图的总燃烧时间。
     * <p>
     * 详见 {@link Furnace#getBurnTime()}。
     * <p>原文：
     * The total burn time for this view.
     * <p>
     * See {@link Furnace#getBurnTime()} for more information.
     *
     * @return 一个0到1之间的数字
     * <p>
     * 原文：The total burn time for this view.
     * <p>
     * See {@link Furnace#getBurnTime()} for more information.
     * <p>原文：a number between 0 and 1
     */
    float getBurnTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查熔炉是否正在燃烧。
     * <p>原文：
     * Checks whether or not the furnace is burning
     *
     * @return 如果熔炉正在燃烧则返回true
     * <p>
     * 原文：Checks whether or not the furnace is burning
     * <p>原文：true given that the furnace is burning
     */
    boolean isBurning();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置烹饪时间。
     * <p>
     * 设置烹饪时间需要同时操作烹饪进度和烹饪时长。此方法通过简单除法获取熔炉视觉进度条内的总进度。
     * 为获得清晰的视觉效果，(烹饪进度 / 烹饪时长) 应返回一个0到1之间的数字（包含0和1）。
     * <p>原文：
     * Sets the cook time
     * <p>
     * Setting cook time requires manipulation of both cookProgress and
     * cookDuration. This method does a simple division to get total progress
     * within the furnaces visual duration bar. For a clear visual effect
     * (cookProgress / cookDuration) should return a number between 0 and 1
     * inclusively.
     *
     * @param cookProgress 当前的烹饪进度
     * <p>原文：the current of the cooking
     * @param cookDuration 总烹饪时间
     * <p>
     * 原文：Sets the cook time
     * <p>
     * Setting cook time requires manipulation of both cookProgress and
     * cookDuration. This method does a simple division to get total progress
     * within the furnaces visual duration bar. For a clear visual effect
     * (cookProgress / cookDuration) should return a number between 0 and 1
     * inclusively.
     * <p>原文：the total cook time
     */
    void setCookTime(int cookProgress, int cookDuration);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置燃烧时间。
     * <p>
     * 设置燃烧时间需要同时操作燃烧进度和燃烧时长。此方法通过简单除法获取熔炉视觉燃烧条内的总进度。
     * 为获得清晰的视觉效果，(燃烧进度 / 燃烧时长) 应返回一个0到1之间的数字（包含0和1）。
     * <p>原文：
     * Sets the burn time
     * <p>
     * Setting burn time requires manipulation of both burnProgress and
     * burnDuration. This method does a simple division to get total progress
     * within the furnaces visual burning bar. For a clear visual effect
     * (burnProgress / burnDuration) should return a number between 0 and 1
     * inclusively.
     *
     * @param burnProgress 燃烧时长的进度
     * <p>原文：the progress towards the burnDuration
     * @param burnDuration 视图应保持燃烧的总时长
     * <p>
     * 原文：Sets the burn time
     * <p>
     * Setting burn time requires manipulation of both burnProgress and
     * burnDuration. This method does a simple division to get total progress
     * within the furnaces visual burning bar. For a clear visual effect
     * (burnProgress / burnDuration) should return a number between 0 and 1
     * inclusively.
     * <p>原文：the total duration the view should be lit
     */
    void setBurnTime(int burnProgress, int burnDuration);
}
