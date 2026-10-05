package io.papermc.paper.plugin.provider.entrypoint;

import io.papermc.paper.plugin.configuration.PluginMeta;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 依赖上下文是对能够解析插件间依赖关系的类型/概念的只读抽象。
 * <p>
 * 例如，它可以是服务器范围的插件管理器本身，能够验证两个 {@link PluginMeta} 实例之间是否存在依赖关系，但实现并不局限于这种具体用例。
 * <p>原文：
 * A dependency context is a read-only abstraction of a type/concept that can resolve dependencies between plugins.
 * <p>
 * This may for example be the server wide plugin manager itself, capable of validating if a dependency exists between
 * two {@link PluginMeta} instances, however the implementation is not limited to such a concrete use-case.
 */
@NullMarked
@ApiStatus.Internal
public interface DependencyContext {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 计算传入的 {@link PluginMeta} 是否将传入的依赖定义为传递依赖。顾名思义，传递依赖不一定由传入的插件直接配置，也可能只是依赖的依赖。<p> 一个简单的例子是 <pre>{@code
     * dependencyContext.isTransitiveDependency(pluginMetaA, pluginMetaC);
     * }</pre>：如果 {@code pluginMetaA} 直接或间接依赖 {@code pluginMetaC}，则返回 {@code true}。
     * <p>原文：
     * Computes if the passed {@link PluginMeta} defined the passed dependency as a transitive dependency.
     * A transitive dependency, as implied by its name, may not have been configured directly by the passed plugin
     * but could also simply be a dependency of a dependency.
     * <p>
     * A simple example of this method would be
     * <pre>{@code
     * dependencyContext.isTransitiveDependency(pluginMetaA, pluginMetaC);
     * }</pre>
     * which would return {@code true} if {@code pluginMetaA} directly or indirectly depends on {@code pluginMetaC}.
     *
     * @param plugin 此计算应视为传入潜在依赖的依赖状态请求者的插件元数据。
     * <p>原文：the plugin meta this computation should consider the requester of the dependency status for the
     *               passed potential dependency.
     * @param depend {@code plugin} 参数的潜在传递依赖。
     * <p>原文：the potential transitive dependency of the {@code plugin} parameter.
     * @return 一个简单的布尔标志，表示 {@code plugin} 是否将 {@code depend} 视为传递依赖。
     * <p>原文：a simple boolean flag indicating if {@code plugin} considers {@code depend} as a transitive dependency.
     */
    boolean isTransitiveDependency(PluginMeta plugin, PluginMeta depend);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 计算此依赖上下文是否知道某个提供/匹配传入标识符的依赖。
     * <p>
     * 在该方法的语境中，依赖是指任何可被依赖的构件。是否真的有东西依赖该构件并不重要，只要它作为潜在依赖存在，该方法就会将其视为依赖。因此，如果此依赖上下文知道具有匹配标识符的构件，该方法将返回 {@code true}。
     * <p>原文：
     * Computes if this dependency context is aware of a dependency that provides/matches the passed identifier.
     * <p>
     * A dependency in this methods context is any dependable artefact. It does not matter if anything actually depends
     * on said artefact, its mere existence as a potential dependency is enough for this method to consider it a
     * dependency. If this dependency context is hence aware of an artefact with the matching identifier, this
     * method returns {@code true}.
     *
     * @param pluginIdentifier 用于探测此依赖上下文的依赖的唯一标识符。
     * <p>原文：the unique identifier of the dependency with which to probe this dependency context.
     * @return 一个简单的布尔标志，表示此依赖上下文是否知道具有传入标识符的潜在依赖。
     * <p>原文：a plain boolean flag indicating if this dependency context is aware of a potential dependency with the
     * passed identifier.
     */
    boolean hasDependency(String pluginIdentifier);

}
