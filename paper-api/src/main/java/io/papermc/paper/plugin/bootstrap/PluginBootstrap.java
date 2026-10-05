package io.papermc.paper.plugin.bootstrap;

import io.papermc.paper.plugin.provider.util.ProviderUtil;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 插件引导器（bootstrap）用于在服务器加载之前加载插件的某些部分。
 * <p>
 * 插件引导允许在服务器中某些运行期间不允许初始化的位置初始化值。
 * <p>
 * 你的引导类与 JavaPlugin 位于同一类加载器中。
 * <p>
 * <b>所有对 Bukkit 的调用都可能抛出 NullPointerException 或意外返回 null。你只应调用明确注明可在引导器中使用的 API 方法</b>
 * <p>原文：
 * A plugin bootstrap is meant for loading certain parts of the plugin before the server is loaded.
 * <p>
 * Plugin bootstrapping allows values to be initialized in certain parts of the server that might not be allowed
 * when the server is running.
 * <p>
 * Your bootstrap class will be on the same classloader as your JavaPlugin.
 * <p>
 * <b>All calls to Bukkit may throw a NullPointerExceptions or return null unexpectedly. You should only call api methods that are explicitly documented to work in the bootstrapper</b>
 */
@ApiStatus.Experimental
@NullMarked
@ApiStatus.OverrideOnly
public interface PluginBootstrap {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 由服务器调用，让你使用一个提供日志记录器、共享插件配置文件等内容的上下文来引导插件。
     * <p>原文：
     * Called by the server, allowing you to bootstrap the plugin with a context that provides things like a logger and your shared plugin configuration file.
     *
     * @param context 服务器提供的上下文
     * <p>原文：the server provided context
     */
    void bootstrap(BootstrapContext context);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 由服务器调用以实例化你的主类。插件可以重写该逻辑，为该实例定义自定义的创建逻辑，例如传递额外的构造器参数。
     * <p>原文：
     * Called by the server to instantiate your main class.
     * Plugins may override this logic to define custom creation logic for said instance, like passing additional
     * constructor arguments.
     *
     * @param context 服务器创建的引导对象
     * <p>原文：the server created bootstrap object
     * @return 服务器请求的插件主类实例。
     * <p>原文：the server requested instance of the plugins main class.
     */
    default JavaPlugin createPlugin(final PluginProviderContext context) {
        return ProviderUtil.loadClass(context.getConfiguration().getMainClass(), JavaPlugin.class, this.getClass().getClassLoader());
    }
}
