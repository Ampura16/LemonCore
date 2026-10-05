package org.bukkit.attribute;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 用于表示一个可以包含属性的对象.
 * <p>原文：
 * Represents an object which may contain attributes.
 */
public interface Attributable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从指定的一个对象内获取对应的属性.
     * <p>
     * 这个方法将直接返回包含属性的对象, 任何对此对象的变动将立即可见 (生效).
     * <p>原文：
     * Gets the specified attribute instance from the object. This instance will
     * be backed directly to the object and any changes will be visible at once.
     *
     * @param attribute Attribute 实例
     * <p>原文：the attribute to get
     * @return 这个方法会返回一个 AttributeInstance, 如果不适用于此对象, 将返回 null
     * <p>原文：the attribute instance or null if not applicable to this object
     */
    @Nullable
    AttributeInstance getAttribute(@NotNull Attribute attribute);

    // Paper start
    /**
     * Registers a generic attribute to that attributable instance.
     * Allows it to add attributes not registered by default to that entity.
     *
     * @param attribute the generic attribute to register
     */
    void registerAttribute(@NotNull Attribute attribute);
    // Paper end
}
