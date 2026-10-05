package io.papermc.paper.plugin.provider.classloader;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 插件类加载器组表示插件类加载器可访问的一组类加载器。
 * <p>
 * 例如，某个类加载器组持有插件声明的所有直接与传递依赖，使插件的类加载器可以通过该组访问这些依赖中的类。
 * <p>原文：
 * A plugin classloader group represents a group of classloaders that a plugins classloader may access.
 * <p>
 * An example of this would be a classloader group that holds all direct and transitive dependencies a plugin declared,
 * allowing a plugins classloader to access classes included in these dependencies via this group.
 */
@NullMarked
@ApiStatus.Internal
public interface PluginClassLoaderGroup {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 尝试使用传入的全限定名，在此插件类加载器组所含的任一类加载器中查找/加载类。
     * <p>
     * 在所含加载器之间的查找顺序在 API 层面未定义，完全取决于实现。
     * <p>原文：
     * Attempts to find/load a class from this plugin class loader group using the passed fully qualified name
     * in any of the classloaders that are part of this group.
     * <p>
     * The lookup order across the contained loaders is not defined on the API level and depends purely on the
     * implementation.
     *
     * @param name 要加载的类的全限定名
     * <p>原文：the fully qualified name of the class to load
     * @param resolve 是否应在需要时解析该类
     * <p>原文：whether the class should be resolved if needed or not
     * @param requester 从此加载器组请求该类的插件类加载器
     * <p>原文：plugin classloader that is requesting the class from this loader group
     * @return 在传入的全限定类名处找到的类。如果找不到该类，将返回 {@code null}。
     * <p>原文：the class found at the fully qualified class name passed. If the class could not be found, {@code null}
     * will be returned.
     * @see ConfiguredPluginClassLoader#loadClass(String, boolean, boolean, boolean)
     */
    @Nullable Class<?> getClassByName(String name, boolean resolve, ConfiguredPluginClassLoader requester);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从此类加载器组中移除已配置的插件类加载器。如果该类加载器当前不在列表中，该方法不执行任何操作。
     * <p>原文：
     * Removes a configured plugin classloader from this class loader group.
     * If the classloader is not currently in the list, this method will simply do nothing.
     *
     * @param configuredPluginClassLoader 要从组中移除的插件类加载器
     * <p>原文：the plugin classloader to remove from the group
     */
    @Contract(mutates = "this")
    void remove(ConfiguredPluginClassLoader configuredPluginClassLoader);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将传入的插件类加载器添加到此组，使此组可在 {@link #getClassByName(String, boolean, ConfiguredPluginClassLoader)} 查找期间使用它。
     * <p>
     * 该方法<b>不会</b>查询 {@link ClassLoaderAccess}（通过 {@link #getAccess()} 公开）来确认此组是否有权访问传入的类加载器。
     * <p>原文：
     * Adds the passed plugin classloader to this group, allowing this group to use it during
     * {@link #getClassByName(String, boolean, ConfiguredPluginClassLoader)} lookups.
     * <p>
     * This method does <b>not</b> query the {@link ClassLoaderAccess} (exposed via {@link #getAccess()}) to ensure
     * if this group has access to the class loader passed.
     *
     * @param configuredPluginClassLoader 要添加到此组的插件类加载器。
     * <p>原文：the plugin classloader to add to this group.
     */
    @Contract(mutates = "this")
    void add(ConfiguredPluginClassLoader configuredPluginClassLoader);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供守护并定义此类加载器组内容的类加载器访问。虽然约定上不作保证（参见 {@link #add(ConfiguredPluginClassLoader)}），但该访问通常负责定义哪些 {@link ConfiguredPluginClassLoader} 应属于此组，哪些不应属于。
     * <p>原文：
     * Provides the class loader access that guards and defines the content of this classloader group.
     * While not guaranteed contractually (see {@link #add(ConfiguredPluginClassLoader)}), the access generally is
     * responsible for defining which {@link ConfiguredPluginClassLoader}s should be part of this group and which ones
     * should not.
     *
     * @return 决定哪些类加载器应属于此组、哪些不应属于的类加载器访问。
     * <p>原文：the classloader access governing which classloaders should be part of this group and which ones should
     * not.
     */
    ClassLoaderAccess getAccess();

}
