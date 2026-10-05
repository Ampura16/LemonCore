package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @deprecated This system is extremely misleading and does not cleanup values for metadatable entities that have been
 * removed. It is recommended that when wanting persistent metadata, you use {@link org.bukkit.persistence.PersistentDataContainer}.
 * <p>
 * If you want temporary values on an entity, use the entity lifecycle events and a {@link java.util.Map} of your own. (See {@link com.destroystokyo.paper.event.entity.EntityAddToWorldEvent} and {@link com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent})
 */
@Deprecated
public interface MetadataValue {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此元数据项的值.
     * <p>原文：
     * Fetches the value of this metadata item.
     *
     * @return 元数据值.
     * <p>原文：the metadata value.
     */
    @Nullable
    public Object value();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试将此元数据项的值转换为int.
     * <p>原文：
     * Attempts to convert the value of this metadata item into an int.
     *
     * @return int类型的值.
     * <p>原文：the value as an int.
     */
    public int asInt();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试将此元数据项的值转换为float.
     * <p>原文：
     * Attempts to convert the value of this metadata item into a float.
     *
     * @return float类型的值.
     * <p>原文：the value as a float.
     */
    public float asFloat();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试将此元数据项的值转换为double.
     * <p>原文：
     * Attempts to convert the value of this metadata item into a double.
     *
     * @return double类型的值.
     * <p>原文：the value as a double.
     */
    public double asDouble();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试将此元数据项的值转换为long.
     * <p>原文：
     * Attempts to convert the value of this metadata item into a long.
     *
     * @return long类型的值.
     * <p>原文：the value as a long.
     */
    public long asLong();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试将此元数据项的值转换为short.
     * <p>原文：
     * Attempts to convert the value of this metadata item into a short.
     *
     * @return short类型的值.
     * <p>原文：the value as a short.
     */
    public short asShort();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试将此元数据项的值转换为byte.
     * <p>原文：
     * Attempts to convert the value of this metadata item into a byte.
     *
     * @return byte类型的值.
     * <p>原文：the value as a byte.
     */
    public byte asByte();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试将此元数据项的值转换为boolean.
     * <p>原文：
     * Attempts to convert the value of this metadata item into a boolean.
     *
     * @return boolean类型的值.
     * <p>原文：the value as a boolean.
     */
    public boolean asBoolean();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试将此元数据项的值转换为字符串.
     * <p>原文：
     * Attempts to convert the value of this metadata item into a string.
     *
     * @return 字符串类型的值.
     * <p>原文：the value as a string.
     */
    @NotNull
    public String asString();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回创建此元数据项的{@link Plugin}.
     * <p>原文：
     * Returns the {@link Plugin} that created this metadata item.
     *
     * @return 拥有此元数据值的插件. 如果插件已被卸载则可能为null.
     * <p>原文：the plugin that owns this metadata value. Could be null if the plugin was already unloaded.
     */
    @Nullable
    public Plugin getOwningPlugin();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使此元数据项无效, 强制其在下次访问时重新计算.
     * <p>原文：
     * Invalidates this metadata item, forcing it to recompute when next
     * accessed.
     */
    public void invalidate();
}
