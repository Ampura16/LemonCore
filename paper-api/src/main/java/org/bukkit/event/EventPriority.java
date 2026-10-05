package org.bukkit.event;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个事件的优先级.
 * <p>
 * Listeners with lower priority are called first
 * will listeners with higher priority are called last.
 * <p>
 * Listeners are called in following order:
 * {@link #LOWEST} -> {@link #LOW} -> {@link #NORMAL} -> {@link #HIGH} -> {@link #HIGHEST} -> {@link #MONITOR}
 * <p>原文：
 * Represents an event's priority in execution.
 * <p>
 * Listeners with lower priority are called first
 * will listeners with higher priority are called last.
 * <p>
 * Listeners are called in following order:
 * {@link #LOWEST} -> {@link #LOW} -> {@link #NORMAL} -> {@link #HIGH} -> {@link #HIGHEST} -> {@link #MONITOR}
 */
public enum EventPriority {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 事件中调用的重要性非常低,并且应该首先运行,允许其他插件以进一步自定义结果
     * <p>原文：
     * Event call is of very low importance and should be run first, to allow
     * other plugins to further customise the outcome
     */
    LOWEST(0),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 事件中调用的重要性比较低
     * <p>原文：
     * Event call is of low importance
     */
    LOW(1),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 事件中调用的重要性一般
     * <p>原文：
     * Event call is neither important nor unimportant, and may be run
     * normally
     */
    NORMAL(2),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 事件中调用的重要性比较高
     * <p>原文：
     * Event call is of high importance
     */
    HIGH(3),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 事件将变得非常重要，在这个事件发生什么都必须由这个事件决定
     * <p>原文：
     * Event call is critical and must have the final say in what happens
     * to the event
     */
    HIGHEST(4),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 没有修改的事件的优先级应该比这个低
     * <p>原文：
     * Event is listened to purely for monitoring the outcome of an event.
     * <p>
     * No modifications to the event should be made under this priority
     */
    MONITOR(5);

    private final int slot;

    EventPriority(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }
}
