package io.papermc.paper.dialog;

import net.kyori.adventure.nbt.api.BinaryTagHolder;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 对话框可能响应的视图。不保证这确实是对某个对话框表单的响应，需由插件自行验证响应是否有效。
 * <p>原文：
 * A view for a possible response to a dialog.
 * There are no guarantees that this is an actual response to a
 * dialog form. It is on the plugin to validate that the response
 * is valid.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface DialogResponseView {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取响应的原始载荷。
     * <p>原文：
     * Gets the raw payload of the response.
     *
     * @return 原始负载
     * <p>原文：the raw payload
     */
    @Contract(pure = true)
    BinaryTagHolder payload();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取某个键处的文本值。
     * <p>原文：
     * Gets a text value at a key.
     *
     * @param key 键
     * <p>原文：the key
     * @return 值（如果不存在则为 null）
     * <p>原文：the value (or null if it doesn't exist)
     */
    @Contract(pure = true)
    @Nullable String getText(String key);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取某个键处的布尔值。
     * <p>原文：
     * Gets a boolean value at a key.
     *
     * @param key 键
     * <p>原文：the key
     * @return 值（如果不存在则为 null）
     * <p>原文：the value (or null if it doesn't exist)
     */
    @Contract(pure = true)
    @Nullable Boolean getBoolean(String key);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取某个键处的浮点值。
     * <p>原文：
     * Gets a float value at a key.
     *
     * @param key 键
     * <p>原文：the key
     * @return 值（如果不存在则为 null）
     * <p>原文：the value (or null if it doesn't exist)
     */
    @Contract(pure = true)
    @Nullable Float getFloat(String key);
}
