package io.papermc.paper.command.brigadier.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 包装原生（原版）参数类型的参数类型。该参数会被特殊处理：原生参数类型会被发送到客户端，以便在客户端进行补全和语法校验。
 * <p>
 * 实现该类时，你必须基于 {@link StringReader} 编写自己的解析逻辑。如果只想从原生类型（{@code N}）转换为自定义类型（{@code T}），请改为实现 {@link Converted}。
 * <p>原文：
 * An argument type that wraps around a native-to-vanilla argument type.
 * This argument receives special handling in that the native argument type will
 * be sent to the client for possible client-side completions and syntax validation.
 * <p>
 * When implementing this class, you have to create your own parsing logic from a
 * {@link StringReader}. If only want to convert from the native type ({@code N}) to the custom
 * type ({@code T}), implement {@link Converted} instead.
 *
 * @param <T> 自定义类型
 * <p>原文：custom type
 * @param <N> 带有原版 Minecraft 原生参数的类型（来自 {@link ArgumentTypes}）
 * <p>原文：type with an argument native to vanilla Minecraft (from {@link ArgumentTypes})
 */
public interface CustomArgumentType<T, N> extends ArgumentType<T> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将参数解析为自定义类型（{@code T}）。请注意，该解析在服务器上进行。这意味着如果在解析期间抛出 {@link CommandSyntaxException}，只有在用户执行命令之后才会向其显示，而不是在其仍在输入时。
     * <p>原文：
     * Parses the argument into the custom type ({@code T}). Keep in mind
     * that this parsing will be done on the server. This means that if
     * you throw a {@link CommandSyntaxException} during parsing, this
     * will only show up to the user after the user has executed the command
     * not while they are still entering it.
     *
     * @param reader 字符串读取器输入
     * <p>原文：string reader input
     * @return 解析后的值
     * <p>原文：parsed value
     * @throws CommandSyntaxException 如果解析时发生错误
     * <p>原文：if an error occurs while parsing
     * @see #parse(StringReader, Object)
     */
    @Override
    T parse(final StringReader reader) throws CommandSyntaxException;

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将参数解析为自定义类型（{@code T}）。请注意，该解析在服务器上进行。这意味着如果在解析期间抛出 {@link CommandSyntaxException}，只有在用户执行命令之后才会向其显示，而不是在其仍在输入时。
     * <p>
     * 该方法提供命令源，作为解析时的附加上下文。你可能需要自行对 {@link io.papermc.paper.command.brigadier.CommandSourceStack} 进行 {@code instanceof} 检查。
     * <p>原文：
     * Parses the argument into the custom type ({@code T}). Keep in mind
     * that this parsing will be done on the server. This means that if
     * you throw a {@link CommandSyntaxException} during parsing, this
     * will only show up to the user after the user has executed the command
     * not while they are still entering it.
     * <p>
     * This method provides the command source for additional context when parsing. You
     * may have to do your own {@code instanceof} checks for {@link io.papermc.paper.command.brigadier.CommandSourceStack}.
     *
     * @param reader 字符串读取器输入
     * <p>原文：string reader input
     * @param source 命令的来源
     * <p>原文：source of the command
     * @return 解析后的值
     * <p>原文：parsed value
     * @throws CommandSyntaxException 如果解析时发生错误
     * <p>原文：if an error occurs while parsing
     */
    @Override
    default <S> T parse(final StringReader reader, final S source) throws CommandSyntaxException {
        return ArgumentType.super.parse(reader, source);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此参数使用的原生类型，即发送给客户端的类型。
     * <p>原文：
     * Gets the native type that this argument uses,
     * the type that is sent to the client.
     *
     * @return 原生参数类型
     * <p>原文：native argument type
     */
    ArgumentType<N> getNativeType();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 无法由服务器控制。在同一节点中存在多个参数时返回，这有助于区分并告诉玩家可能的输入是什么。
     * <p>原文：
     * Cannot be controlled by the server.
     * Returned in cases where there are multiple arguments in the same node.
     * This helps differentiate and tell the player what the possible inputs are.
     *
     * @return 客户端设置示例
     * <p>原文：client set examples
     */
    @Override
    @ApiStatus.NonExtendable
    default Collection<String> getExamples() {
        return this.getNativeType().getExamples();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供要显示给客户端的建议列表。
     * <p>原文：
     * Provides a list of suggestions to show to the client.
     *
     * @param context 命令上下文
     * <p>原文：command context
     * @param builder 建议构建器
     * <p>原文：suggestion builder
     * @return 建议
     * <p>原文：suggestions
     * @param <S> 上下文类型
     * <p>原文：context type
     */
    @Override
    default <S> CompletableFuture<Suggestions> listSuggestions(final CommandContext<S> context, final SuggestionsBuilder builder) {
        return ArgumentType.super.listSuggestions(context, builder);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 包装原生（原版）参数类型的参数类型。该参数会被特殊处理：原生参数类型会被发送到客户端，以便在客户端进行补全和语法校验。
     * <p>
     * 解析得到的原生类型将通过 {@link #convert(Object)} 转换。如果想手动处理类型解析，请实现 {@link CustomArgumentType}。
     * <p>原文：
     * An argument type that wraps around a native-to-vanilla argument type.
     * This argument receives special handling in that the native argument type will
     * be sent to the client for possible client-side completions and syntax validation.
     * <p>
     * The parsed native type will be converted via {@link #convert(Object)}.
     * Implement {@link CustomArgumentType} if you want to handle parsing the type manually.
     *
     * @param <T> 自定义类型
     * <p>原文：custom type
     * @param <N> 带有原版 Minecraft 原生参数的类型（来自 {@link ArgumentTypes}）
     * <p>原文：type with an argument native to vanilla Minecraft (from {@link ArgumentTypes})
     */
    interface Converted<T, N> extends CustomArgumentType<T, N> {

        @ApiStatus.NonExtendable
        @Override
        default T parse(final StringReader reader) throws CommandSyntaxException {
            return this.convert(this.getNativeType().parse(reader));
        }

        @ApiStatus.NonExtendable
        @Override
        default <S> T parse(final StringReader reader, final S source) throws CommandSyntaxException {
            return this.convert(this.getNativeType().parse(reader, source), source);
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将值从原生类型转换为自定义参数类型。
         * <p>原文：
         * Converts the value from the native type to the custom argument type.
         *
         * @param nativeType 原生参数提供的值
         * <p>原文：native argument provided value
         * @return 转换后的值
         * <p>原文：converted value
         * @throws CommandSyntaxException 如果解析时发生异常
         * <p>原文：if an exception occurs while parsing
         * @see #convert(Object, Object)
         */
        T convert(N nativeType) throws CommandSyntaxException;

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将值从原生类型转换为自定义参数类型。
         * <p>
         * 该方法提供命令源，作为转换时的附加上下文。你可能需要自行对 {@link io.papermc.paper.command.brigadier.CommandSourceStack} 进行 {@code instanceof} 检查。
         * <p>原文：
         * Converts the value from the native type to the custom argument type.
         * <p>
         * This method provides the command source for additional context when converting. You
         * may have to do your own {@code instanceof} checks for {@link io.papermc.paper.command.brigadier.CommandSourceStack}.
         *
         * @param nativeType 原生参数提供的值
         * <p>原文：native argument provided value
         * @param source 命令的来源
         * <p>原文：source of the command
         * @return 转换后的值
         * <p>原文：converted value
         * @throws CommandSyntaxException 如果解析时发生异常
         * <p>原文：if an exception occurs while parsing
         */
        default <S> T convert(final N nativeType, final S source) throws CommandSyntaxException {
            return this.convert(nativeType);
        }
    }
}
