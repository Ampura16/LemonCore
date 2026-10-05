package io.papermc.paper.command.brigadier.argument.resolvers;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.math.Rotation;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 能够使用 {@link CommandSourceStack} 解析旋转参数值的 {@link ArgumentResolver}。
 * <p>原文：
 * An {@link ArgumentResolver} that's capable of resolving
 * a rotation argument value using a {@link CommandSourceStack}.
 *
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes#rotation()
 */
@ApiStatus.NonExtendable
public interface RotationResolver extends ArgumentResolver<Rotation> {
}
