package io.papermc.paper.plugin;

import java.util.List;
import java.util.Set;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 权限管理器实现，用于对以前使用 Bukkit 权限管理器的现有插件保持部分向后兼容。
 * <p>原文：
 * A permission manager implementation to keep backwards compatibility partially alive with existing plugins that used
 * the bukkit one before.
 */
@ApiStatus.Experimental
@NullMarked
public interface PermissionManager {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 通过全限定名获取 {@link Permission}
     * <p>原文：
     * Gets a {@link Permission} from its fully qualified name
     *
     * @param name 权限名称
     * <p>原文：Name of the permission
     * @return 权限；没有则为 null
     * <p>原文：Permission, or null if none
     */
    @Nullable Permission getPermission(String name);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 向此插件管理器添加一个 {@link Permission}。
     * <p>
     * 如果已存在与新权限同名的权限，将抛出异常。
     * <p>原文：
     * Adds a {@link Permission} to this plugin manager.
     * <p>
     * If a permission is already defined with the given name of the new
     * permission, an exception will be thrown.
     *
     * @param perm 要添加的权限
     * <p>原文：Permission to add
     * @throws IllegalArgumentException 当同名权限已存在时抛出
     * <p>原文：Thrown when a permission with the same
     *                                  name already exists
     */
    void addPermission(Permission perm);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从此插件管理器中移除 {@link Permission} 的注册。
     * <p>
     * 如果指定的权限在此插件管理器中不存在，则不会发生任何事。
     * <p>
     * 移除权限注册<b>不会</b>从拥有该权限的任何 {@link Permissible} 中移除该权限。
     * <p>原文：
     * Removes a {@link Permission} registration from this plugin manager.
     * <p>
     * If the specified permission does not exist in this plugin manager,
     * nothing will happen.
     * <p>
     * Removing a permission registration will <b>not</b> remove the
     * permission from any {@link Permissible}s that have it.
     *
     * @param perm 要移除的权限
     * <p>原文：Permission to remove
     */
    void removePermission(Permission perm);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从此插件管理器中移除 {@link Permission} 的注册。
     * <p>
     * 如果指定的权限在此插件管理器中不存在，则不会发生任何事。
     * <p>
     * 移除权限注册<b>不会</b>从拥有该权限的任何 {@link Permissible} 中移除该权限。
     * <p>原文：
     * Removes a {@link Permission} registration from this plugin manager.
     * <p>
     * If the specified permission does not exist in this plugin manager,
     * nothing will happen.
     * <p>
     * Removing a permission registration will <b>not</b> remove the
     * permission from any {@link Permissible}s that have it.
     *
     * @param name 要移除的权限
     * <p>原文：Permission to remove
     */
    void removePermission(String name);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取给定 OP 状态的默认权限
     * <p>原文：
     * Gets the default permissions for the given op status
     *
     * @param op 要获取哪一组默认权限
     * <p>原文：Which set of default permissions to get
     * @return 默认权限
     * <p>原文：The default permissions
     */
    Set<Permission> getDefaultPermissions(boolean op);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 重新计算给定 {@link Permission} 的默认值。
     * <p>
     * 如果指定的权限未在此处注册，则不会有任何效果。
     * <p>原文：
     * Recalculates the defaults for the given {@link Permission}.
     * <p>
     * This will have no effect if the specified permission is not registered
     * here.
     *
     * @param perm 要重新计算的权限
     * <p>原文：Permission to recalculate
     */
    void recalculatePermissionDefaults(Permission perm);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按名称为给定的 Permissible 订阅所请求权限的相关信息。
     * <p>
     * 如果指定的权限发生任何形式的变化，将要求该 Permissible 重新计算。
     * <p>原文：
     * Subscribes the given Permissible for information about the requested
     * Permission, by name.
     * <p>
     * If the specified Permission changes in any form, the Permissible will
     * be asked to recalculate.
     *
     * @param permission 要订阅的权限
     * <p>原文：Permission to subscribe to
     * @param permissible 进行订阅的 Permissible
     * <p>原文：Permissible subscribing
     */
    void subscribeToPermission(String permission, Permissible permissible);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按名称取消给定 Permissible 对所请求权限相关信息的订阅。
     * <p>原文：
     * Unsubscribes the given Permissible for information about the requested
     * Permission, by name.
     *
     * @param permission 要取消订阅的权限
     * <p>原文：Permission to unsubscribe from
     * @param permissible 进行订阅的 Permissible
     * <p>原文：Permissible subscribing
     */
    void unsubscribeFromPermission(String permission, Permissible permissible);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取按名称订阅给定权限的所有 {@link Permissible} 的集合
     * <p>原文：
     * Gets a set containing all subscribed {@link Permissible}s to the given
     * permission, by name
     *
     * @param permission 要查询的权限
     * <p>原文：Permission to query for
     * @return 包含所有已订阅权限的集合
     * <p>原文：Set containing all subscribed permissions
     */
    Set<Permissible> getPermissionSubscriptions(String permission);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按 OP 状态订阅给定的默认权限
     * <p>
     * 如果指定的默认值发生任何形式的变化，将要求该 Permissible 重新计算。
     * <p>原文：
     * Subscribes to the given Default permissions by operator status
     * <p>
     * If the specified defaults change in any form, the Permissible will be
     * asked to recalculate.
     *
     * @param op 要订阅的默认列表
     * <p>原文：Default list to subscribe to
     * @param permissible 进行订阅的 Permissible
     * <p>原文：Permissible subscribing
     */
    void subscribeToDefaultPerms(boolean op, Permissible permissible);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按 OP 状态取消订阅给定的默认权限
     * <p>原文：
     * Unsubscribes from the given Default permissions by operator status
     *
     * @param op 要取消订阅的默认列表
     * <p>原文：Default list to unsubscribe from
     * @param permissible 进行订阅的 Permissible
     * <p>原文：Permissible subscribing
     */
    void unsubscribeFromDefaultPerms(boolean op, Permissible permissible);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取按 OP 状态订阅给定默认列表的所有 {@link Permissible} 的集合
     * <p>原文：
     * Gets a set containing all subscribed {@link Permissible}s to the given
     * default list, by op status
     *
     * @param op 要查询的默认列表
     * <p>原文：Default list to query for
     * @return 包含所有已订阅权限的集合
     * <p>原文：Set containing all subscribed permissions
     */
    Set<Permissible> getDefaultPermSubscriptions(boolean op);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取所有已注册权限的集合。
     * <p>
     * 该集合是副本，不会实时更新。
     * <p>原文：
     * Gets a set of all registered permissions.
     * <p>
     * This set is a copy and will not be modified live.
     *
     * @return 包含当前所有已注册权限的集合
     * <p>原文：Set containing all current registered permissions
     */
    Set<Permission> getPermissions();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 添加一组权限。
     * <p>
     * 这是一种优化，用于添加多个权限时不必逐个重新计算权限。
     * <p>原文：
     * Adds a list of permissions.
     * <p>
     * This is meant as an optimization for adding multiple permissions without recalculating each permission.
     *
     * @param perm 权限
     * <p>原文：permission
     */
    void addPermissions(List<Permission> perm);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 清除当前已注册的所有权限。
     * <p>
     * 用于重载。
     * <p>原文：
     * Clears all the current registered permissions.
     * <p>
     * This is used for reloading.
     */
    void clearPermissions();

}
