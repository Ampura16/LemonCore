package io.papermc.paper.command.brigadier.argument.resolvers;

import com.destroystokyo.paper.profile.PlayerProfile;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import java.util.Collection;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 能够使用 {@link CommandSourceStack} 解析参数值的 {@link ArgumentResolver}。
 * <p>原文：
 * An {@link ArgumentResolver} that's capable of resolving
 *  argument value using a {@link CommandSourceStack}.
 *
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes#playerProfiles()
 */
@ApiStatus.NonExtendable
public interface PlayerProfileListResolver extends ArgumentResolver<Collection<PlayerProfile>> {
}
