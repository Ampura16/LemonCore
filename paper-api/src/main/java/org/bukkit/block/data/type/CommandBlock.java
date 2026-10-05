package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'conditional' 数据值表示触发此命令方块是否需要条件,
 * 换句话说只有在此命令方块前的命令方块执行成功时才会触发本命令方块.
 * <p>原文：
 * 'conditional' denotes whether this command block is conditional or not, i.e.
 * will only execute if the preceding command block also executed successfully.
 */
public interface CommandBlock extends Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'conditional' 数据值.
     * <p>原文：
     * Gets the value of the 'conditional' property.
     *
     * @return 'conditional' 数据值
     * <p>原文：the 'conditional' value
     */
    boolean isConditional();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'conditional' 数据值.
     * <p>原文：
     * Sets the value of the 'conditional' property.
     *
     * @param conditional 'conditional' 数据值
     * <p>原文：the new 'conditional' value
     */
    void setConditional(boolean conditional);
}
