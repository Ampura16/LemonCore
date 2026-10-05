package org.bukkit.entity.minecart;

import org.bukkit.entity.Minecart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表命令方块矿车.
 */
public interface CommandMinecart extends Minecart, io.papermc.paper.command.CommandBlockHolder { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个命令方块矿车激活时运行的命令。这将永远不会返回null，而会返回空的字符串。
     * <p>原文：
     * Gets the command that this CommandMinecart will run when activated.
     * This will never return null.  If the CommandMinecart does not have a
     * command, an empty String will be returned instead.
     *
     * @return 当激活时这个命令方块矿车将要运行的命令
     * <p>原文：Command that this CommandMinecart will run when powered.
     */
    @NotNull
    public String getCommand();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个命令方块激活时运行的命令。设置命令为null与设置命令为空字符串是一样的。
     * <p>原文：
     * Sets the command that this CommandMinecart will run when activated.
     * Setting the command to null is the same as setting it to an empty
     * String.
     *
     * @param command 当激活时这个命令方块将要运行的命令
     * <p>原文：Command that this CommandMinecart will run when
     *     activated.
     */
    public void setCommand(@Nullable String command);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个命令方块矿车的名字。这个名字用于命令方块执行命令。设置名字为null与设置为“@”是一样的。
     * <p>原文：
     * Sets the name of this CommandMinecart.  The name is used with commands
     * that this CommandMinecart executes.  Setting the name to null is the
     * same as setting it to "@".
     *
     * @param name 这个命令方块的新名字
     * <p>原文：New name for this CommandMinecart.
     * @deprecated in favour of {@link #customName(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    public void setName(@Nullable String name);

}
