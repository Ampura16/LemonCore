package io.papermc.paper.command.brigadier.argument.resolvers.selector;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.ArgumentResolver;
import java.util.List;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 能够使用 {@link CommandSourceStack} 解析玩家选择器参数值的 {@link ArgumentResolver}。
 * <p>原文：
 * An {@link ArgumentResolver} that's capable of resolving
 * a player selector argument value using a {@link CommandSourceStack}.
 *
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes#player()
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes#players()
 */
@ApiStatus.NonExtendable
public interface PlayerSelectorArgumentResolver extends SelectorArgumentResolver<List<Player>> {
}
