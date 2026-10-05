package org.bukkit.block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表命令方块.
 * <p>原文：
 * Represents a captured state of a command block.
 */
public interface CommandBlock extends TileState, io.papermc.paper.command.CommandBlockHolder { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个命令方块激活时运行的命令。本方法不会返回null，若此命令方块还没有设置命令，会返回空字符.
     * <p>原文：
     * Gets the command that this CommandBlock will run when powered.
     * This will never return null. If the CommandBlock does not have a
     * command, an empty String will be returned instead.
     *
     * @return 当激活时这个命令方块将要运行的命令
     * <p>原文：Command that this CommandBlock will run when powered.
     */
    @NotNull
    public String getCommand();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个命令方块激活时运行的命令。设置这个命令为null等价于设置命令为空字符串.
     * <p>原文：
     * Sets the command that this CommandBlock will run when powered.
     * Setting the command to null is the same as setting it to an empty
     * String.
     *
     * @param command 当激活时这个命令方块将要运行的命令
     * <p>原文：Command that this CommandBlock will run when powered.
     */
    public void setCommand(@Nullable String command);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个命令方块的名字.这个名字被用于命令方块执行命令。这个名字永远不会为空，默认为“@”.
     * <p>原文：
     * Gets the name of this CommandBlock. The name is used with commands
     * that this CommandBlock executes. This name will never be null, and
     * by default is "@".
     *
     * @return 这个命令方块的名字
     * <p>原文：Name of this CommandBlock.
     * @deprecated in favour of {@link #name()}
     */
    @Deprecated // Paper
    @NotNull
    public String getName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个命令方块的名字。这个名字用于命令方块执行命令。设置名字为null等价于设置为“@”.
     * <p>原文：
     * Sets the name of this CommandBlock. The name is used with commands
     * that this CommandBlock executes. Setting the name to null is the
     * same as setting it to "@".
     *
     * @param name 这个命令方块的新名字
     * <p>原文：New name for this CommandBlock.
     * @deprecated in favour of {@link #name(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    public void setName(@Nullable String name);

    /**
     * Gets the name of this CommandBlock. The name is used with commands
     * that this CommandBlock executes. This name will never be null, and
     * by default is a {@link net.kyori.adventure.text.TextComponent} containing {@code @}.
     *
     * @return Name of this CommandBlock.
     */
    public net.kyori.adventure.text.@NotNull Component name();

    /**
     * Sets the name of this CommandBlock. The name is used with commands
     * that this CommandBlock executes. Setting the name to null is the
     * same as setting it to a {@link net.kyori.adventure.text.TextComponent} containing {@code @}.
     *
     * @param name New name for this CommandBlock.
     */
    public void name(net.kyori.adventure.text.@Nullable Component name);
}
