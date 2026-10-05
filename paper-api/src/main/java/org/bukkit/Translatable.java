package org.bukkit;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个具有可被Minecraft客户端翻译的文本表示的对象.
 * <p>原文：
 * Represents an object with a text representation that can be translated by the
 * Minecraft client.
 *
 * @deprecated use {@link net.kyori.adventure.translation.Translatable}
 */
@Deprecated(forRemoval = true) // Paper
public interface Translatable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取翻译键, 适用于在翻译组件中使用.
     * <p>原文：
     * Get the translation key, suitable for use in a translation component.
     *
     * @return 翻译键
     * <p>原文：the translation key
     * @deprecated look for a {@code translationKey()} method instead
     */
    @NotNull
    @Deprecated(forRemoval = true) // Paper
    String getTranslationKey();
}
