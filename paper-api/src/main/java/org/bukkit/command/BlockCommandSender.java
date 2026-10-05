package org.bukkit.command;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

public interface BlockCommandSender extends CommandSender {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回命令发送者的方块.
     * <p>原文：
     * Returns the block this command sender belongs to
     *
     * @return 命令发送者的方块
     * <p>原文：Block for the command sender
     */
    @NotNull
    public Block getBlock();
}
