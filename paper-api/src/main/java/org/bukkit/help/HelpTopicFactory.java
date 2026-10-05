package org.bukkit.help;

import org.bukkit.command.Command;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * HelpTopicFactory用于从继承自公共基类的命令或执行器继承自公共基类的执行器创建自定义{@link HelpTopic}对象.
 * 你可以使用自定义HelpTopic来更改插件中所有命令在帮助中的显示方式.
 * 如果你的插件实现了复杂的权限系统, 自定义帮助主题也可能很合适.
 * <p>
 * 要自动将插件的命令绑定到自定义HelpTopic实现, 首先确保所有命令或执行器都派生自自定义基类(它不需要做任何事情).
 * 接下来实现一个接受自定义命令基类并从中实例化自定义HelpTopic的HelpTopicFactory.
 * 最后, 使用{@link HelpMap#registerHelpTopicFactory(Class, HelpTopicFactory)}方法将HelpTopicFactory注册到命令基类.
 * <p>
 * 当帮助系统遍历所有注册的命令以创建帮助主题时, 它首先检查是否有为该命令基类注册的HelpTopicFactory.
 * 如果有, 则使用工厂创建帮助主题而不是通用帮助主题.
 * 如果没有找到工厂且命令派生自{@link org.bukkit.command.PluginCommand}, 则会检查命令执行器的类型以查找注册的HelpTopicFactory.
 * 最后, 如果没有找到工厂, 则为该命令创建通用帮助主题.
 * <p>原文：
 * A HelpTopicFactory is used to create custom {@link HelpTopic} objects from
 * commands that inherit from a common base class or have executors that
 * inherit from a common base class. You can use a custom HelpTopic to change
 * the way all the commands in your plugin display in the help. If your plugin
 * implements a complex permissions system, a custom help topic may also be
 * appropriate.
 * <p>
 * To automatically bind your plugin's commands to your custom HelpTopic
 * implementation, first make sure all your commands or executors derive from
 * a custom base class (it doesn't have to do anything). Next implement a
 * custom HelpTopicFactory that accepts your custom command base class and
 * instantiates an instance of your custom HelpTopic from it. Finally,
 * register your HelpTopicFactory against your command base class using the
 * {@link HelpMap#registerHelpTopicFactory(Class, HelpTopicFactory)} method.
 * <p>
 * As the help system iterates over all registered commands to make help
 * topics, it first checks to see if there is a HelpTopicFactory registered
 * for the command's base class. If so, the factory is used to make a help
 * topic rather than a generic help topic. If no factory is found for the
 * command's base class and the command derives from {@link
 * org.bukkit.command.PluginCommand}, then the type of the command's executor
 * is inspected looking for a registered HelpTopicFactory. Finally, if no
 * factory is found, a generic help topic is created for the command.
 *
 * @param <TCommand> 自定义命令的基类.
 * <p>原文：The base class for your custom commands.
 */
public interface HelpTopicFactory<TCommand extends Command> {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此方法接受派生自自定义命令基类的命令并为其构建自定义HelpTopic.
     * <p>原文：
     * This method accepts a command deriving from a custom command base class
     * and constructs a custom HelpTopic for it.
     *
     * @param command 要为其构建帮助主题的自定义命令.
     * <p>原文：The custom command to build a help topic for.
     * @return 一个新的自定义帮助主题, 或{@code null}表示故意不创建主题.
     * <p>原文：A new custom help topic or {@code null} to intentionally NOT
     *     create a topic.
     */
    @Nullable
    public HelpTopic createTopic(@NotNull TCommand command);
}
