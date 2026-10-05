package io.papermc.paper.command;

import net.kyori.adventure.text.Component;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public interface CommandBlockHolder {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此命令方块在充能时将运行的命令。该方法永远不会返回 null。如果命令方块没有命令，则返回空字符串。
     * <p>原文：
     * Gets the command that this CommandBlock will run when powered.
     * This will never return null.  If the CommandBlock does not have a
     * command, an empty String will be returned instead.
     *
     * @return 此命令方块被激活时将运行的命令。
     * <p>原文：Command that this CommandBlock will run when activated.
     */
    String getCommand();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此命令方块在充能时将运行的命令。将命令设为 null 等同于设为空字符串。
     * <p>原文：
     * Sets the command that this CommandBlock will run when powered.
     * Setting the command to null is the same as setting it to an empty
     * String.
     *
     * @param command 此命令方块被激活时将运行的命令。
     * <p>原文：Command that this CommandBlock will run when activated.
     */
    void setCommand(@Nullable String command);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此命令方块的上一次输出。
     * <p>原文：
     * Gets the last output from this command block.
     *
     * @return 上一次输出
     * <p>原文：the last output
     */
    Component lastOutput();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此命令方块的上一次输出。
     * <p>原文：
     * Sets the last output from this command block.
     *
     * @param lastOutput 上一次输出
     * <p>原文：the last output
     */
    void lastOutput(@Nullable Component lastOutput);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此命令方块的成功次数。
     * <p>原文：
     * Gets the success count from this command block.
     * @see <a href="https://minecraft.wiki/wiki/Command_Block#Success_count">Command_Block#Success_count</a>
     *
     * @return 成功计数
     * <p>原文：the success count
     */
    int getSuccessCount();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此命令方块的成功次数。
     * <p>原文：
     * Sets the success count from this command block.
     * @see <a href="https://minecraft.wiki/wiki/Command_Block#Success_count">Command_Block#Success_count</a>
     *
     * @param successCount 成功计数
     * <p>原文：the success count
     */
    void setSuccessCount(int successCount);
}
