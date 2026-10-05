package org.bukkit.plugin;

import java.util.Set;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示插件所感知的概念.
 * <p>
 * 内部表示可以是单例或参数化实例, 但必须是不可变的.
 * <p>原文：
 * Represents a concept that a plugin is aware of.
 * <p>
 * The internal representation may be singleton, or be a parameterized
 * instance, but must be immutable.
 */
public interface PluginAwareness {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此处每个条目代表一个特定的插件感知. 可以通过 {@link PluginDescriptionFile#getAwareness()}.{@link Set#contains(Object) contains(flag)} 进行检查.
     * <p>原文：
     * Each entry here represents a particular plugin's awareness. These can
     * be checked by using {@link PluginDescriptionFile#getAwareness()}.{@link
     * Set#contains(Object) contains(flag)}.
     */
    public enum Flags implements PluginAwareness {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 指定插件 jar 中存储的所有 (文本) 资源使用 UTF-8 编码.
         * <p>原文：
         * This specifies that all (text) resources stored in a plugin's jar
         * use UTF-8 encoding.
         *
         * @deprecated 现在假定所有插件都具有 UTF-8 感知能力.
         * <p>原文：all plugins are now assumed to be UTF-8 aware.
         */
        @Deprecated(since = "1.9", forRemoval = true)
        UTF8,
        ;
    }
}
