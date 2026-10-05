package io.papermc.paper.command.brigadier.argument.resolvers;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 可使用 {@link CommandSourceStack} 解析角度值的 {@link ArgumentResolver}。
 * <p>原文：
 * An {@link ArgumentResolver} capable of resolving
 * an angle value using a {@link CommandSourceStack}.
 *
 * @see ArgumentTypes#angle()
 */
@ApiStatus.Experimental
@NullMarked
@ApiStatus.NonExtendable
public interface AngleResolver {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用给定的命令源栈解析参数。
     * <p>原文：
     * Resolves the argument with the given
     * command source stack.
     *
     * @param sourceStack 源栈
     * <p>原文：source stack
     * @return 解析后的角度（度）。
     * <p>原文：the resolved angle in degrees.
     */
    float resolve(CommandSourceStack sourceStack) throws CommandSyntaxException;
}
