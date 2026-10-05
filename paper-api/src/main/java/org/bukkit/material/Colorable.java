package org.bukkit.material;

import org.bukkit.DyeColor;
import org.bukkit.UndefinedNullability;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 可以被着色的对象。
 * <p>原文：
 * An object that can be colored.
 */
public interface Colorable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此对象的颜色。
     * <br>
     * 如果对象有特殊的默认颜色（例如潜影贝），则返回 null 表示对象的默认颜色。
     * <p>原文：
     * Gets the color of this object.
     * <br>
     * This may be null to represent the default color of an object, if the
     * object has a special default color (e.g Shulkers).
     *
     * @return 此对象的 DyeColor。
     * <p>
     * 原文：Gets the color of this object.
     * <p>原文：The DyeColor of this object.
     */
    @Nullable
    public DyeColor getColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此对象的颜色设置为指定的 DyeColor。
     * <br>
     * 如果对象有特殊的默认颜色（例如潜影贝），则可以传入 null 表示对象的默认颜色。
     * <p>原文：
     * Sets the color of this object to the specified DyeColor.
     * <br>
     * This may be null to represent the default color of an object, if the
     * object has a special default color (e.g Shulkers).
     *
     * @param color 对象的颜色，以 DyeColor 表示。
     * <p>原文：The color of the object, as a DyeColor.
     * @throws NullPointerException 如果参数为 null 且此实现不支持 null
     * <p>
     * 原文：Sets the color of this object to the specified DyeColor.
     * <p>原文：if argument is null and this implementation does not support null
     */
    public void setColor(@UndefinedNullability("defined by subclass") DyeColor color);

}
