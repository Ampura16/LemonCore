package io.papermc.paper.command.brigadier.argument.resolvers.selector;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.ArgumentResolver;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 能够使用 {@link CommandSourceStack} 解析选择器参数值的 {@link ArgumentResolver}。
 * <p>原文：
 * An {@link ArgumentResolver} that's capable of resolving
 * a selector argument value using a {@link CommandSourceStack}.
 *
 * @param <T> 解析后的类型
 * <p>原文：resolved type
 * @see <a href="https://minecraft.wiki/w/Target_selectors">Target Selectors</a>
 */
@ApiStatus.NonExtendable
public interface SelectorArgumentResolver<T> extends ArgumentResolver<T> {
}
