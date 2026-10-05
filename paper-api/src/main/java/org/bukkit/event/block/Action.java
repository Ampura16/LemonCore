package org.bukkit.event.block;

public enum Action {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 左击一个方块
     * <p>原文：
     * Left-clicking a block
     */
    LEFT_CLICK_BLOCK,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 右击一个方块
     * <p>原文：
     * Right-clicking a block
     */
    RIGHT_CLICK_BLOCK,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 左击空气
     * <p>原文：
     * Left-clicking the air
     */
    LEFT_CLICK_AIR,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 右击空气
     * <p>原文：
     * Right-clicking the air
     */
    RIGHT_CLICK_AIR,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 踩上一个方块
     *
     * 举几个例子:
     * <ul>
     * <li>在耕地跳跃
     * <li>站在压力板上
     * <li>让红石矿石发光(点击/踩踏)
     * <li>触发绊线钩
     * </ul>
     * <p>原文：
     * Stepping onto or into a block (Ass-pressure)
     *
     * Examples:
     * <ul>
     * <li>Jumping on soil
     * <li>Standing on pressure plate
     * <li>Triggering redstone ore
     * <li>Triggering tripwire
     * </ul>
     */
    // Paper start
    PHYSICAL;

    /**
     * Gets whether this action is a result of a left click.
     *
     * @return Whether it's a left click
     */
    public boolean isLeftClick() {
        return this == LEFT_CLICK_AIR || this == LEFT_CLICK_BLOCK;
    }

    /**
     * Gets whether this action is a result of a right click.
     *
     * @return Whether it's a right click
     */
    public boolean isRightClick() {
        return this == RIGHT_CLICK_AIR || this == RIGHT_CLICK_BLOCK;
    }
    // Paper end
}
