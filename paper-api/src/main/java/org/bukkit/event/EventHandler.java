package org.bukkit.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 用来标记要处理的事件的注释。
 * <p>原文：
 * An annotation to mark methods as being event handler methods
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 定义事件的优先级。
     * <p>
     * 事件被执行的顺序（先后排列）：
     * <ol>
     * <li>LOWEST</li>
     * <li>LOW</li>
     * <li>NORMAL</li>
     * <li>HIGH</li>
     * <li>HIGHEST</li>
     * <li>MONITOR</li>
     * </ol>
     * <p>原文：
     * Define the priority of the event.
     * <p>
     * First priority to the last priority executed:
     * <ol>
     * <li>LOWEST
     * <li>LOW
     * <li>NORMAL
     * <li>HIGH
     * <li>HIGHEST
     * <li>MONITOR
     * </ol>
     *
     * @return 优先级
     * <p>原文：the priority
     */
    EventPriority priority() default EventPriority.NORMAL;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 定义事件是否被忽略.
     * <p>
     * 如果 ignoreCancelled 为 true 并且事件被取消, 这个方法将
     * 不会被调用. 否则, 这个方法总是会调用。
     * <p>原文：
     * Define if the handler ignores a cancelled event.
     * <p>
     * If ignoreCancelled is {@code true} and the event is cancelled, the method is
     * not called. Otherwise, the method is always called.
     *
     * @return 事件是否被忽略
     * <p>原文：whether cancelled events should be ignored
     */
    boolean ignoreCancelled() default false;
}
