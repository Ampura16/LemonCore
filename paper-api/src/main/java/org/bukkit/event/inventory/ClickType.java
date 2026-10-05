package org.bukkit.event.inventory;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 点击类型.
 * <p>原文：
 * What the client did to trigger this action (not the result).
 */
public enum ClickType {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 鼠标左键(或主键).
     * <p>原文：
     * The left (or primary) mouse button.
     */
    LEFT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * Shift+鼠标左键.
     * <p>原文：
     * Holding shift while pressing the left mouse button.
     */
    SHIFT_LEFT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 鼠标右键.
     * <p>原文：
     * The right mouse button.
     */
    RIGHT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * Shift+鼠标右键.
     * <p>原文：
     * Holding shift while pressing the right mouse button.
     */
    SHIFT_RIGHT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在物品栏界面的“灰色区域”单击鼠标左键.
     * <p>
     * 译注:“灰色区域”指Minecraft窗口范围内<em>超出物品栏范围</em>的部分
     * <p>原文：
     * Clicking the left mouse button on the grey area around the inventory.
     */
    WINDOW_BORDER_LEFT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在物品栏界面的“灰色区域”单击鼠标右键.
     * <p>
     * 译注:“灰色区域”指Minecraft窗口范围内<em>超出物品栏范围</em>的部分
     * <p>原文：
     * Clicking the right mouse button on the grey area around the inventory.
     */
    WINDOW_BORDER_RIGHT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 鼠标中键(或鼠标滚轮按键).
     * <p>原文：
     * The middle mouse button, or a "scrollwheel click".
     */
    MIDDLE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 数字键1-9，对应快捷栏的物品槽.
     * <p>原文：
     * One of the number keys 1-9, correspond to slots on the hotbar.
     */
    NUMBER_KEY,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 双击鼠标左键.
     * <p>原文：
     * Pressing the left mouse button twice in quick succession.
     */
    DOUBLE_CLICK,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 丢弃物品键(默认为Q).
     * <p>原文：
     * The "Drop" key (defaults to Q).
     */
    DROP,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * Ctrl+丢弃物品键(默认为Q).
     * <p>原文：
     * Holding Ctrl while pressing the "Drop" key (defaults to Q).
     */
    CONTROL_DROP,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创造模式物品栏的任何点击操作.
     * <p>原文：
     * Any action done with the Creative inventory open.
     */
    CREATIVE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 交换主副手物品的快捷键（默认为 F 键）
     * <p>原文：
     * The "swap item with offhand" key (defaults to F).
     */
    SWAP_OFFHAND,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 无法被Bukkit解析的物品栏点击类型.
     * <p>
     * 这个按键类型仅出现在Minecraft的版本过渡期, 且不应该被依赖. <br>
     * 任何对 ClickType.UNKNOWN 的调用都是在最努力的基础上的.
     * <p>
     * 译注: 一般只在支持最新快照版本的插件中才可能需要使用.
     * <p>原文：
     * A type of inventory manipulation not yet recognized by Bukkit.
     * <p>
     * This is only for transitional purposes on a new Minecraft update, and
     * should never be relied upon.
     * <p>
     * Any ClickType.UNKNOWN is called on a best-effort basis.
     */
    UNKNOWN,
    ;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断这个按键类型是否为键盘按键.
     * <p>原文：
     * Gets whether this ClickType represents the pressing of a key on a
     * keyboard.
     *
     * @return 如果这个按键类型为键盘按键则返回true，否则返回false.
     * <p>原文：{@code true} if this ClickType represents the pressing of a key
     */
    public boolean isKeyboardClick() {
        return (this == ClickType.NUMBER_KEY) || (this == ClickType.DROP) || (this == ClickType.CONTROL_DROP) || (this == ClickType.SWAP_OFFHAND);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断这个按键类型是否为鼠标按键.
     * <p>原文：
     * Gets whether this ClickType represents the pressing of a mouse button
     *
     * @return 如果这个按键类型为鼠标按键则返回true，否则返回false.
     * <p>原文：{@code true} if this ClickType represents the pressing of a mouse button
     */
    public boolean isMouseClick() {
        return (this == ClickType.DOUBLE_CLICK) || (this == ClickType.LEFT) || (this == ClickType.RIGHT) || (this == ClickType.MIDDLE)
                || (this == ClickType.WINDOW_BORDER_LEFT) || (this == ClickType.SHIFT_LEFT) || (this == ClickType.SHIFT_RIGHT) || (this == ClickType.WINDOW_BORDER_RIGHT);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断这个按键类型所定义的操作是否只能在创造模式被执行.
     * <p>原文：
     * Gets whether this ClickType represents an action that can only be
     * performed by a Player in creative mode.
     *
     * @return 如果这个按键类型要求玩家处在创造模式则返回true，否则返回false.
     * <p>原文：{@code true} if this action requires Creative mode
     */
    public boolean isCreativeAction() {
        // Why use middle click?
        return (this == ClickType.MIDDLE) || (this == ClickType.CREATIVE);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断这个按键类型是否使用了鼠标右键.
     * <p>原文：
     * Gets whether this ClickType represents a right click.
     *
     * @return 如果这个按键类型使用了鼠标右键则返回true，否则返回false.
     * <p>原文：{@code true} if this ClickType represents a right click
     */
    public boolean isRightClick() {
        return (this == ClickType.RIGHT) || (this == ClickType.SHIFT_RIGHT);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断这个按键类型是否使用了鼠标左键.
     * <p>原文：
     * Gets whether this ClickType represents a left click.
     *
     * @return 如果这个按键类型使用了鼠标左键则返回true，否则返回false.
     * <p>原文：{@code true} if this ClickType represents a left click
     */
    public boolean isLeftClick() {
        return (this == ClickType.LEFT) || (this == ClickType.SHIFT_LEFT) || (this == ClickType.DOUBLE_CLICK) || (this == ClickType.CREATIVE);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断这个按键类型是否使用了Shift键.
     * <p>原文：
     * Gets whether this ClickType indicates that the shift key was pressed
     * down when the click was made.
     *
     * @return 如果这个按键类型使用了Shift键则返回true，否则返回false.
     * <p>原文：{@code true} if the action uses Shift.
     */
    public boolean isShiftClick() {
        return (this == ClickType.SHIFT_LEFT) || (this == ClickType.SHIFT_RIGHT);
    }
}
