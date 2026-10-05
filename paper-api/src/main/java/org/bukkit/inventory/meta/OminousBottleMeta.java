package org.bukkit.inventory.meta;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不祥之瓶的物品元数据.
 * <p>原文：
 * Represents an ominous bottle with an amplifier of the bad omen effect.
 */
public interface OminousBottleMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否设置了不祥之瓶的厄运效果放大器.
     * <p>原文：
     * Checks for the presence of an amplifier.
     *
     * @return 如果设置了自定义的厄运效果放大器则返回 true
     * <p>原文：true if a customer amplifier is applied
     */
    boolean hasAmplifier();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取不祥之瓶的厄运效果放大器等级.
     * <p>
     * 译注: 厄运效果(不祥之兆)的等级会影响突袭的波次.
     * <p>
     * 插件在调用此方法前应检查 hasAmplifier() 是否返回 true.
     * <p>原文：
     * Gets the amplifier amount for an Ominous Bottle's bad omen effect.
     * <p>
     * Plugins should check that hasAmplifier() returns true before calling this
     * method.
     *
     * @return 厄运效果放大器等级
     * <p>原文：amplifier
     */
    int getAmplifier();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置不祥之瓶的厄运效果放大器等级.
     * <p>原文：
     * Sets the amplifier amount for an Ominous Bottle's bad omen effect.
     *
     * @param amplifier 厄运效果放大器等级, 应在 0 到 4 之间(包含边界)
     * <p>原文：between 0 and 4
     */
    void setAmplifier(int amplifier);

    @Override
    @NotNull
    OminousBottleMeta clone();
}
