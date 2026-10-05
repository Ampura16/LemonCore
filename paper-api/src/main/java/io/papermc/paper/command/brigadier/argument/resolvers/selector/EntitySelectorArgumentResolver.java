package io.papermc.paper.command.brigadier.argument.resolvers.selector;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.ArgumentResolver;
import java.util.List;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 能够使用 {@link CommandSourceStack} 解析实体选择器参数值的 {@link ArgumentResolver}。
 * <p>原文：
 * An {@link ArgumentResolver} that's capable of resolving
 * an entity selector argument value using a {@link CommandSourceStack}.
 *
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes#entity()
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes#entities()
 */
@ApiStatus.NonExtendable
public interface EntitySelectorArgumentResolver extends SelectorArgumentResolver<List<Entity>> {
}
