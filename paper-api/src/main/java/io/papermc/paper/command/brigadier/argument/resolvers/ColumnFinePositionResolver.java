package io.papermc.paper.command.brigadier.argument.resolvers;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.position.ColumnFinePosition;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 能够使用 {@link CommandSourceStack} 解析列精确坐标参数值的 {@link ArgumentResolver}。
 * <p>原文：
 * An {@link ArgumentResolver} that's capable of resolving
 * a column fine position argument value using a {@link CommandSourceStack}.
 *
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes#columnFinePosition()
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes#columnFinePosition(boolean)
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ColumnFinePositionResolver extends ArgumentResolver<ColumnFinePosition> {
}
