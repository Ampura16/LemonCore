package io.papermc.paper.command.brigadier;

import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.tree.CommandNode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

/**
 * The command source type for Brigadier commands registered using Paper API.
 * <p>
 * While the general use case for CommandSourceStack is similar to that of {@link CommandSender}, it provides access to
 * important additional context for the command execution.
 * Specifically, commands such as {@literal /execute} may alter the location or executor of the source stack before
 * passing it to another command.
 * <p>The {@link CommandSender} returned by {@link #getSender()} may be a "no-op"
 * instance of {@link CommandSender} in cases where the server either doesn't
 * exist yet, or no specific sender is available. Methods on such a {@link CommandSender}
 * will either have no effect or throw an {@link UnsupportedOperationException}.</p>
 */
@ApiStatus.NonExtendable
public interface CommandSourceStack {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取执行此命令的位置。
     * <p>原文：
     * Gets the location that this command is being executed at.
     *
     * @return 克隆的位置实例。
     * <p>原文：a cloned location instance.
     */
    Location getLocation();

    /**
     * Gets the command sender that executed this command.
     * The sender of a command source stack is the one that initiated/triggered the execution of a command.
     * It differs to {@link #getExecutor()} as the executor can be changed by a command, e.g. {@literal /execute}.
     *
     * @return 命令发送者实例
     * <p>原文：the command sender instance
     */
    CommandSender getSender();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取执行此命令的实体。不一定总是 {@link #getSender()}，因为命令的执行者可以被更改为与触发命令的实体不同的实体。
     * <p>原文：
     * Gets the entity that executes this command.
     * May not always be {@link #getSender()} as the executor of a command can be changed to a different entity
     * than the one that triggered the command.
     *
     * @return 执行此命令的实体
     * <p>原文：entity that executes this command
     */
    @Nullable Entity getExecutor();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建具有不同位置的新 CommandSourceStack 对象，用于将命令重定向到其他节点。
     * <p>原文：
     * Creates a new CommandSourceStack object with a different location for redirecting commands to other nodes.
     *
     * @param location 用于创建新 CommandSourceStack 对象的位置
     * <p>原文：The location to create a new CommandSourceStack object with
     * @return 新创建的 CommandSourceStack
     * <p>原文：The newly created CommandSourceStack
     * @see #getLocation()
     * @see com.mojang.brigadier.builder.ArgumentBuilder#fork(CommandNode, RedirectModifier)
     */
    CommandSourceStack withLocation(Location location);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建具有不同执行者的新 CommandSourceStack 对象，用于将命令重定向到其他节点。
     * <p>原文：
     * Creates a new CommandSourceStack object with a different executor for redirecting commands to other nodes.
     *
     * @param executor 用于创建新 CommandSourceStack 对象的执行实体
     * <p>原文：The executing entity to create a new CommandSourceStack object with
     * @return 新创建的 CommandSourceStack
     * <p>原文：The newly created CommandSourceStack
     * @see #getExecutor()
     * @see com.mojang.brigadier.builder.ArgumentBuilder#fork(CommandNode, RedirectModifier)
     */
    CommandSourceStack withExecutor(Entity executor);
}
