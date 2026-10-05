package io.papermc.paper.command.brigadier.argument.resolvers;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link ArgumentResolver} 能够使用 {@link CommandSourceStack} 解析参数值。
 * <p>原文：
 * An {@link ArgumentResolver} is capable of resolving
 * an argument value using a {@link CommandSourceStack}.
 *
 * @param <T> 解析后的类型
 * <p>原文：resolved type
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes
 */
@ApiStatus.NonExtendable
public interface ArgumentResolver<T> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用给定的命令源栈解析参数。
     * <p>原文：
     * Resolves the argument with the given
     * command source stack.
     * @param sourceStack 源栈
     * <p>原文：source stack
     * @return 是否已解析
     * <p>原文：resolved
     */
    T resolve(CommandSourceStack sourceStack) throws CommandSyntaxException;
}
