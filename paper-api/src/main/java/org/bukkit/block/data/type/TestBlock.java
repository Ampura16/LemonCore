package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'mode' 表示此测试方块所处的模式.
 * <p>原文：
 * 'mode' is the reaction of the block to a redstone pulse or its supply.
 */
public interface TestBlock extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'mode' 属性的值.
     * <p>原文：
     * Gets the value of the 'mode' property.
     *
     * @return 'mode' 的值
     * <p>原文：the 'mode' value
     */
    @NotNull
    Mode getMode();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'mode' 属性的值.
     * <p>原文：
     * Sets the value of the 'mode' property.
     *
     * @param mode 新的 'mode' 值
     * <p>原文：the new 'mode' value
     */
    void setMode(@NotNull Mode mode);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 比较器将运行的模式.
     */
    enum Mode {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 测试开始时触发红石脉冲.
         */
        START,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 被红石充能时将消息记录到日志文件.
         */
        LOG,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 被红石充能时使测试失败.
         */
        FAIL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 被红石充能时完成测试.
         */
        ACCEPT
    }
}
