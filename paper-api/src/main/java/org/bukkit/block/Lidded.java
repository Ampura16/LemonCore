package org.bukkit.block;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表有盖容器.
 */
public interface Lidded {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置方块的动作状态为打开并阻止其关闭, 直到调用{@link #close()}.
     * <p>原文：
     * Sets the block's animated state to open and prevents it from being closed
     * until {@link #close()} is called.
     */
    void open();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置方块的动作状态为关闭, 即使玩家正在查看此方块.
     * <p>原文：
     * Sets the block's animated state to closed even if a player is currently
     * viewing this block.
     */
    void close();

    // Paper start - More Lidded Block API
    /**
     * Checks if the block's animation state.
     *
     * @return true if the block's animation state is set to open.
     */
    boolean isOpen();
    // Paper end - More Lidded Block API
}
