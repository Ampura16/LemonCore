package org.bukkit.entity;

import com.destroystokyo.paper.entity.RangedEntity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表雪人实体.
 * <p>原文：
 * Represents a snowman entity
 */
public interface Snowman extends Golem, RangedEntity, io.papermc.paper.entity.Shearable { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此雪人是否处于" derp 模式", 即它没有戴着南瓜.
     * <p>原文：
     * Gets whether this snowman is in "derp mode", meaning it is not wearing a
     * pumpkin.
     *
     * @return 如果雪人没有戴南瓜则返回true, 如果戴着南瓜则返回false
     * <p>原文：True if the snowman is bald, false if it is wearing a pumpkin
     */
    boolean isDerp();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此雪人是否处于" derp 模式", 即它没有戴着南瓜.
     * <br>
     * 注意: 此值不会持久化到磁盘, 因此当区块重新加载时会重置.
     * <p>原文：
     * Sets whether this snowman is in "derp mode", meaning it is not wearing a
     * pumpkin. NOTE: This value is not persisted to disk and will therefore
     * reset when the chunk is reloaded.
     *
     * @param derpMode 为true以移除南瓜, 为false以添加南瓜
     * <p>原文：True to remove the pumpkin, false to add a pumpkin
     */
    void setDerp(boolean derpMode);
}
