package io.papermc.paper.plugin.provider.classloader;

import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 类加载器访问接口是一种<b>内部</b>表示，代表类访问能否看到来自其他 {@link ConfiguredPluginClassLoader} 的类型。
 * <p>
 * 例如，某个类加载器访问代表一个插件。这种情况下，只有当传入的类加载器属于该插件的直接或传递依赖时，该类加载器访问才会在调用 {@link #canAccess(ConfiguredPluginClassLoader)} 时返回 {@code true}，从而防止插件意外发现并使用由它未主动声明为依赖的插件/库所提供的类。
 * <p>原文：
 * The class loader access interface is an <b>internal</b> representation of a class accesses' ability to see types
 * from other {@link ConfiguredPluginClassLoader}.
 * <p>
 * An example of this would be a class loader access representing a plugin. The class loader access in that case would
 * only return {@code true} on calls for {@link #canAccess(ConfiguredPluginClassLoader)} if the passed class loader
 * is owned by a direct or transitive dependency of the plugin, preventing the plugin for accidentally discovering and
 * using class types that are supplied by plugins/libraries the plugin did not actively define as a dependency.
 */
@NullMarked
@ApiStatus.Internal
public interface ClassLoaderAccess {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 评估此类加载器访问是否被允许访问传入的 {@link
     * ConfiguredPluginClassLoader} 所提供的类型。
     * <p>
     * 该接口方法在接口层面不提供任何进一步约定，因为判断此类加载器访问可以从哪些类加载器获取类型的逻辑在很大程度上取决于访问类型。例如，旧版 Spigot 类型可以访问服务器上任何可用的类加载器，而现代 Paper 插件则被正确地限制在其依赖树内。
     * <p>原文：
     * Evaluates if this class loader access is allowed to access types provided by the passed {@link
     * ConfiguredPluginClassLoader}.
     * <p>
     * This interface method does not offer any further contracts on the interface level, as the logic to determine
     * what class loaders this class loader access is allowed to retrieve types from depends heavily on the type of
     * access.
     * Legacy spigot types for example may access any class loader available on the server, while modern paper plugins
     * are properly limited to their dependency tree.
     *
     * @param classLoader 要评估访问权限的类加载器
     * <p>原文：the class loader for which access should be evaluated
     * @return 一个简单的布尔标志，{@code true} 表示此类加载器访问被允许访问传入的已配置插件类加载器中的类型，{@code false} 表示不允许。
     * <p>原文：a plain boolean flag, {@code true} indicating that this class loader access is allowed to access types
     * from the passed configured plugin class loader, {@code false} indicating otherwise.
     */
    boolean canAccess(ConfiguredPluginClassLoader classLoader);

}
