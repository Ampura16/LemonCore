package io.papermc.paper.command.brigadier;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.InternalAPIBridge;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.configuration.PluginMeta;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.registrar.Registrar;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 自定义命令的注册器。支持 Brigadier 命令和 {@link BasicCommand}。<p> 下面是注册命令的示例 <pre>{@code
 * class YourPluginClass extends JavaPlugin {
 *
 *     @Override
 *     public void onEnable() {
 *         LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
 *         manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
 *             final Commands commands = event.registrar();
 *             commands.register(
 *                 Commands.literal("new-command")
 *                     .executes(ctx -> {
 *                         ctx.getSource().getSender().sendPlainMessage("some message");
 *                         return Command.SINGLE_SUCCESS;
 *                     })
 *                     .build(),
 *                 "some bukkit help description string",
 *                 List.of("an-alias")
 *             );
 *         });
 *     }
 * }
 * }</pre> <p> 你也可以在 {@link PluginBootstrap} 中通过从 {@link BootstrapContext} 获取 {@link LifecycleEventManager} 来注册命令。在 {@link PluginBootstrap} 中注册的命令可用于数据包的命令函数解析。注意，通过 {@link PluginBootstrap} 注册的、与原版命令字面量相同的命令，将在所有已加载的数据包中覆盖该命令。</p> <p>{@code register} 中<b>不带</b> {@link PluginMeta} 参数的方法，将隐式使用注册 {@link io.papermc.paper.plugin.lifecycle.event.handler.LifecycleEventHandler} 时所属插件的 {@link PluginMeta}。</p>
 * <p>原文：
 * The registrar for custom commands. Supports Brigadier commands and {@link BasicCommand}.
 * <p>
 * An example of a command being registered is below
 * <pre>{@code
 * class YourPluginClass extends JavaPlugin {
 *
 *     @Override
 *     public void onEnable() {
 *         LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
 *         manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
 *             final Commands commands = event.registrar();
 *             commands.register(
 *                 Commands.literal("new-command")
 *                     .executes(ctx -> {
 *                         ctx.getSource().getSender().sendPlainMessage("some message");
 *                         return Command.SINGLE_SUCCESS;
 *                     })
 *                     .build(),
 *                 "some bukkit help description string",
 *                 List.of("an-alias")
 *             );
 *         });
 *     }
 * }
 * }</pre>
 * <p>
 * You can also register commands in {@link PluginBootstrap} by getting the {@link LifecycleEventManager} from
 * {@link BootstrapContext}.
 * Commands registered in the {@link PluginBootstrap} will be available for datapack's
 * command function parsing.
 * Note that commands registered via {@link PluginBootstrap} with the same literals as a vanilla command will override
 * that command within all loaded datapacks.
 * </p>
 * <p>The {@code register} methods that <b>do not</b> have {@link PluginMeta} as a parameter will
 * implicitly use the {@link PluginMeta} for the plugin that the {@link io.papermc.paper.plugin.lifecycle.event.handler.LifecycleEventHandler}
 * was registered with.</p>
 *
 * @see io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents#COMMANDS
 */
@ApiStatus.NonExtendable
public interface Commands extends Registrar {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于创建具有正确泛型的字面量命令节点构建器的工具。
     * <p>原文：
     * Utility to create a literal command node builder with the correct generic.
     *
     * @param literal 字面量名称
     * <p>原文：literal name
     * @return 新的构建器实例
     * <p>原文：a new builder instance
     */
    static LiteralArgumentBuilder<CommandSourceStack> literal(final String literal) {
        return LiteralArgumentBuilder.literal(literal);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于创建具有正确泛型的必需参数构建器的工具。
     * <p>原文：
     * Utility to create a required argument builder with the correct generic.
     *
     * @param name 参数的名称
     * <p>原文：the name of the argument
     * @param argumentType 参数的类型
     * <p>原文：the type of the argument
     * @param <T> 参数值的泛型类型
     * <p>原文：the generic type of the argument value
     * @return 新的必需参数构建器
     * <p>原文：a new required argument builder
     */
    static <T> RequiredArgumentBuilder<CommandSourceStack, T> argument(final String name, final ArgumentType<T> argumentType) {
        return RequiredArgumentBuilder.argument(name, argumentType);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建包装给定谓词的受限 {@link Predicate}。
     * <p>
     * 受限谓词会阻止在无人值守的上下文（例如聊天点击事件）中执行。执行命令前客户端会显示警告。
     * <p>
     * 原版用它来防止在玩家不知情的情况下调用敏感命令（例如 op）。
     * <p>原文：
     * Creates a restricted {@link Predicate} that wraps the given predicate.
     * <p>
     * A restricted predicate prevents execution in unattended contexts, such as from
     * chat click events. A warning is shown on the client before executing the command.
     * <p>
     * This is used by vanilla to prevent invocation of sensitive commands (like op) from
     * players without their knowledge.
     *
     * @param predicate 要包装的原始谓词
     * <p>原文：the original predicate to wrap
     * @return 具有受限执行行为的新谓词
     * <p>原文：a new predicate with restricted execution behavior
     */
    static Predicate<CommandSourceStack> restricted(final Predicate<CommandSourceStack> predicate) {
        return InternalAPIBridge.get().restricted(predicate);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取底层的 {@link CommandDispatcher}。
     * <p>
     * <b>注意：</b>这是一个精细的 API，必须谨慎使用以确保一致的用户体验。
     * </p>
     * <p>
     * 注册命令时，应尽可能优先使用 {@link #register(PluginMeta, LiteralCommandNode, String, Collection) register methods}，而不是直接注册到分发器。{@link #register(PluginMeta, LiteralCommandNode, String, Collection) Register methods} 会自动处理命令命名空间、命令帮助、插件与命令的关联等。
     * </p>
     * <p>
     * 该方法的使用场景<b>可能</b>包括：
     * <ul>
     * <li>在外部命令框架与 Paper 之间实现集成（尽管仍应尽可能优先使用 {@link #register(PluginMeta, LiteralCommandNode, String, Collection) register methods}）</li>
     * <li>向现有插件命令注册新的子节点（例如另一个插件的“附加”插件可能需要这样做）</li>
     * <li>获取现有命令节点以构建重定向</li>
     * </ul>
     * <p>原文：
     * Gets the underlying {@link CommandDispatcher}.
     *
     * <p><b>Note:</b> This is a delicate API that must be used with care to ensure a consistent user experience.</p>
     *
     * <p>When registering commands, it should be preferred to use the {@link #register(PluginMeta, LiteralCommandNode, String, Collection) register methods}
     * over directly registering to the dispatcher wherever possible.
     * {@link #register(PluginMeta, LiteralCommandNode, String, Collection) Register methods} automatically handle
     * command namespacing, command help, plugin association with commands, and more.</p>
     *
     * <p>Example use cases for this method <b>may</b> include:
     * <ul>
     *   <li>Implementing integration between an external command framework and Paper (although {@link #register(PluginMeta, LiteralCommandNode, String, Collection) register methods} should still be preferred where possible)</li>
     *   <li>Registering new child nodes to an existing plugin command (for example an "addon" plugin to another plugin may want to do this)</li>
     *   <li>Retrieving existing command nodes to build redirects</li>
     * </ul>
     *
     * @return 分发器实例
     * <p>原文：the dispatcher instance
     */
    @ApiStatus.Experimental
    CommandDispatcher<CommandSourceStack> getDispatcher();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为当前插件上下文注册命令。
     * <p>
     * 命令具有特定的覆盖行为：
     * <ul>
     * <li>别名不会覆盖已存在的命令（带命名空间的除外）</li>
     * <li>别名<b>不是</b> Brigadier 重定向，只是将命令复制到另一个标签</li>
     * <li>主命令/带命名空间的标签会覆盖已存在的命令</li>
     * </ul>
     * <p>原文：
     * Registers a command for the current plugin context.
     *
     * <p>Commands have certain overriding behavior:
     * <ul>
     *   <li>Aliases will not override already existing commands (excluding namespaced ones)</li>
     *   <li>Aliases are <b>not</b> Brigadier redirects, they just copy the command to a different label</li>
     *   <li>The main command/namespaced label will override already existing commands</li>
     * </ul>
     *
     * @param node 构建完成的字面量命令节点
     * <p>原文：the built literal command node
     * @return 成功注册的根命令标签（包括别名和带命名空间的变体）
     * <p>原文：successfully registered root command labels (including aliases and namespaced variants)
     */
    default @Unmodifiable Set<String> register(final LiteralCommandNode<CommandSourceStack> node) {
        return this.register(node, null, Collections.emptyList());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为当前插件上下文注册命令。
     * <p>
     * 命令具有特定的覆盖行为：
     * <ul>
     * <li>别名不会覆盖已存在的命令（带命名空间的除外）</li>
     * <li>别名<b>不是</b> Brigadier 重定向，只是将命令复制到另一个标签</li>
     * <li>主命令/带命名空间的标签会覆盖已存在的命令</li>
     * </ul>
     * <p>原文：
     * Registers a command for the current plugin context.
     *
     * <p>Commands have certain overriding behavior:
     * <ul>
     *   <li>Aliases will not override already existing commands (excluding namespaced ones)</li>
     *   <li>Aliases are <b>not</b> Brigadier redirects, they just copy the command to a different label</li>
     *   <li>The main command/namespaced label will override already existing commands</li>
     * </ul>
     *
     * @param node 构建完成的字面量命令节点
     * <p>原文：the built literal command node
     * @param description 根字面量节点的帮助描述
     * <p>原文：the help description for the root literal node
     * @return 成功注册的根命令标签（包括别名和带命名空间的变体）
     * <p>原文：successfully registered root command labels (including aliases and namespaced variants)
     */
    default @Unmodifiable Set<String> register(final LiteralCommandNode<CommandSourceStack> node, final @Nullable String description) {
        return this.register(node, description, Collections.emptyList());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为当前插件上下文注册命令。
     * <p>
     * 命令具有特定的覆盖行为：
     * <ul>
     * <li>别名不会覆盖已存在的命令（带命名空间的除外）</li>
     * <li>别名<b>不是</b> Brigadier 重定向，只是将命令复制到另一个标签</li>
     * <li>主命令/带命名空间的标签会覆盖已存在的命令</li>
     * </ul>
     * <p>原文：
     * Registers a command for the current plugin context.
     *
     * <p>Commands have certain overriding behavior:
     * <ul>
     *   <li>Aliases will not override already existing commands (excluding namespaced ones)</li>
     *   <li>Aliases are <b>not</b> Brigadier redirects, they just copy the command to a different label</li>
     *   <li>The main command/namespaced label will override already existing commands</li>
     * </ul>
     *
     * @param node 构建完成的字面量命令节点
     * <p>原文：the built literal command node
     * @param aliases 用于注册该字面量节点命令的别名集合
     * <p>原文：a collection of aliases to register the literal node's command to
     * @return 成功注册的根命令标签（包括别名和带命名空间的变体）
     * <p>原文：successfully registered root command labels (including aliases and namespaced variants)
     */
    default @Unmodifiable Set<String> register(final LiteralCommandNode<CommandSourceStack> node, final Collection<String> aliases) {
        return this.register(node, null, aliases);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为当前插件上下文注册命令。
     * <p>
     * 命令具有特定的覆盖行为：
     * <ul>
     * <li>别名不会覆盖已存在的命令（带命名空间的除外）</li>
     * <li>别名<b>不是</b> Brigadier 重定向，只是将命令复制到另一个标签</li>
     * <li>主命令/带命名空间的标签会覆盖已存在的命令</li>
     * </ul>
     * <p>原文：
     * Registers a command for the current plugin context.
     *
     * <p>Commands have certain overriding behavior:
     * <ul>
     *   <li>Aliases will not override already existing commands (excluding namespaced ones)</li>
     *   <li>Aliases are <b>not</b> Brigadier redirects, they just copy the command to a different label</li>
     *   <li>The main command/namespaced label will override already existing commands</li>
     * </ul>
     *
     * @param node 构建完成的字面量命令节点
     * <p>原文：the built literal command node
     * @param description 根字面量节点的帮助描述
     * <p>原文：the help description for the root literal node
     * @param aliases 用于注册该字面量节点命令的别名集合
     * <p>原文：a collection of aliases to register the literal node's command to
     * @return 成功注册的根命令标签（包括别名和带命名空间的变体）
     * <p>原文：successfully registered root command labels (including aliases and namespaced variants)
     */
    @Unmodifiable Set<String> register(LiteralCommandNode<CommandSourceStack> node, @Nullable String description, Collection<String> aliases);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为插件注册命令。
     * <p>
     * 命令具有特定的覆盖行为：
     * <ul>
     * <li>别名不会覆盖已存在的命令（带命名空间的除外）</li>
     * <li>别名<b>不是</b> Brigadier 重定向，只是将命令复制到另一个标签</li>
     * <li>主命令/带命名空间的标签会覆盖已存在的命令</li>
     * </ul>
     * <p>原文：
     * Registers a command for a plugin.
     *
     * <p>Commands have certain overriding behavior:
     * <ul>
     *   <li>Aliases will not override already existing commands (excluding namespaced ones)</li>
     *   <li>Aliases are <b>not</b> Brigadier redirects, they just copy the command to a different label</li>
     *   <li>The main command/namespaced label will override already existing commands</li>
     * </ul>
     *
     * @param pluginMeta 所属插件的元数据
     * <p>原文：the owning plugin's meta
     * @param node 构建完成的字面量命令节点
     * <p>原文：the built literal command node
     * @param description 根字面量节点的帮助描述
     * <p>原文：the help description for the root literal node
     * @param aliases 用于注册该字面量节点命令的别名集合
     * <p>原文：a collection of aliases to register the literal node's command to
     * @return 成功注册的根命令标签（包括别名和带命名空间的变体）
     * <p>原文：successfully registered root command labels (including aliases and namespaced variants)
     */
    @Unmodifiable Set<String> register(PluginMeta pluginMeta, LiteralCommandNode<CommandSourceStack> node, @Nullable String description, Collection<String> aliases);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于配置命令的注册方式，不供公开使用。更多信息请参阅 {@link Commands#register(PluginMeta, LiteralCommandNode, String, Collection)}。
     * <p>原文：
     * This allows configuring the registration of your command, which is not intended for public use.
     * See {@link Commands#register(PluginMeta, LiteralCommandNode, String, Collection)} for more information.
     *
     * @param pluginMeta 所属插件的元数据
     * <p>原文：the owning plugin's meta
     * @param node 构建完成的字面量命令节点
     * <p>原文：the built literal command node
     * @param description 根字面量节点的帮助描述
     * <p>原文：the help description for the root literal node
     * @param aliases 用于注册该字面量节点命令的别名集合
     * <p>原文：a collection of aliases to register the literal node's command to
     * @param flags 控制注册行为的注册标志集合。
     * <p>原文：a collection of registration flags that control registration behaviour.
     * @return 成功注册的根命令标签（包括别名和带命名空间的变体）
     * <p>原文：successfully registered root command labels (including aliases and namespaced variants)
     *
     * @apiNote 由于不供公开使用，此方法不保证稳定。关于此方法用例的更深入解释请参阅 {@link CommandRegistrationFlag}。
     * <p>原文：This method is not guaranteed to be stable as it is not intended for public use.
     * See {@link CommandRegistrationFlag} for a more indepth explanation of this method's use-case.
     */
    @ApiStatus.Internal
    @Unmodifiable Set<String> registerWithFlags(PluginMeta pluginMeta, LiteralCommandNode<CommandSourceStack> node, @Nullable String description, Collection<String> aliases, Set<CommandRegistrationFlag> flags);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按照与 {@link Commands#register(LiteralCommandNode, String, Collection)} 相同的逻辑注册命令。
     * <p>原文：
     * Registers a command under the same logic as {@link Commands#register(LiteralCommandNode, String, Collection)}.
     *
     * @param label 待注册命令的标签
     * <p>原文：the label of the to-be-registered command
     * @param basicCommand 要注册的基本命令实例
     * <p>原文：the basic command instance to register
     * @return 成功注册的根命令标签（包括别名和带命名空间的变体）
     * <p>原文：successfully registered root command labels (including aliases and namespaced variants)
     */
    default @Unmodifiable Set<String> register(final String label, final BasicCommand basicCommand) {
        return this.register(label, null, Collections.emptyList(), basicCommand);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按照与 {@link Commands#register(LiteralCommandNode, String, Collection)} 相同的逻辑注册命令。
     * <p>原文：
     * Registers a command under the same logic as {@link Commands#register(LiteralCommandNode, String, Collection)}.
     *
     * @param label 待注册命令的标签
     * <p>原文：the label of the to-be-registered command
     * @param description 根字面量节点的帮助描述
     * <p>原文：the help description for the root literal node
     * @param basicCommand 要注册的基本命令实例
     * <p>原文：the basic command instance to register
     * @return 成功注册的根命令标签（包括别名和带命名空间的变体）
     * <p>原文：successfully registered root command labels (including aliases and namespaced variants)
     */
    default @Unmodifiable Set<String> register(final String label, final @Nullable String description, final BasicCommand basicCommand) {
        return this.register(label, description, Collections.emptyList(), basicCommand);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按照与 {@link Commands#register(LiteralCommandNode, String, Collection)} 相同的逻辑注册命令。
     * <p>原文：
     * Registers a command under the same logic as {@link Commands#register(LiteralCommandNode, String, Collection)}.
     *
     * @param label 待注册命令的标签
     * <p>原文：the label of the to-be-registered command
     * @param aliases 用于注册该基本命令的别名集合。
     * <p>原文：a collection of aliases to register the basic command under.
     * @param basicCommand 要注册的基本命令实例
     * <p>原文：the basic command instance to register
     * @return 成功注册的根命令标签（包括别名和带命名空间的变体）
     * <p>原文：successfully registered root command labels (including aliases and namespaced variants)
     */
    default @Unmodifiable Set<String> register(final String label, final Collection<String> aliases, final BasicCommand basicCommand) {
        return this.register(label, null, aliases, basicCommand);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按照与 {@link Commands#register(LiteralCommandNode, String, Collection)} 相同的逻辑注册命令。
     * <p>原文：
     * Registers a command under the same logic as {@link Commands#register(LiteralCommandNode, String, Collection)}.
     *
     * @param label 待注册命令的标签
     * <p>原文：the label of the to-be-registered command
     * @param description 根字面量节点的帮助描述
     * <p>原文：the help description for the root literal node
     * @param aliases 用于注册该基本命令的别名集合。
     * <p>原文：a collection of aliases to register the basic command under.
     * @param basicCommand 要注册的基本命令实例
     * <p>原文：the basic command instance to register
     * @return 成功注册的根命令标签（包括别名和带命名空间的变体）
     * <p>原文：successfully registered root command labels (including aliases and namespaced variants)
     */
    @Unmodifiable Set<String> register(String label, @Nullable String description, Collection<String> aliases, BasicCommand basicCommand);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按照与 {@link Commands#register(PluginMeta, LiteralCommandNode, String, Collection)} 相同的逻辑注册命令。
     * <p>原文：
     * Registers a command under the same logic as {@link Commands#register(PluginMeta, LiteralCommandNode, String, Collection)}.
     *
     * @param pluginMeta 所属插件的元数据
     * <p>原文：the owning plugin's meta
     * @param label 待注册命令的标签
     * <p>原文：the label of the to-be-registered command
     * @param description 根字面量节点的帮助描述
     * <p>原文：the help description for the root literal node
     * @param aliases 用于注册该基本命令的别名集合。
     * <p>原文：a collection of aliases to register the basic command under.
     * @param basicCommand 要注册的基本命令实例
     * <p>原文：the basic command instance to register
     * @return 成功注册的根命令标签（包括别名和带命名空间的变体）
     * <p>原文：successfully registered root command labels (including aliases and namespaced variants)
     */
    @Unmodifiable Set<String> register(PluginMeta pluginMeta, String label, @Nullable String description, Collection<String> aliases, BasicCommand basicCommand);
}
