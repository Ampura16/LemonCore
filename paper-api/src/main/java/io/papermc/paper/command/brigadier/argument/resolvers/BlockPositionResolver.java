package io.papermc.paper.command.brigadier.argument.resolvers;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.math.BlockPosition;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 能够使用 {@link CommandSourceStack} 解析方块坐标参数值的 {@link ArgumentResolver}。
 * <p>原文：
 * An {@link ArgumentResolver} that's capable of resolving
 * a block position argument value using a {@link CommandSourceStack}.
 *
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes#blockPosition()
 */
@ApiStatus.NonExtendable
public interface BlockPositionResolver extends ArgumentResolver<BlockPosition> {
}
