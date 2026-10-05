package org.bukkit;

import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表明此方块、实体或其它物体(对象)可接受一个自定义名称.
 * <p>原文：
 * Represents a block, entity, or other object that may receive a custom name.
 */
public interface Nameable {

    /**
     * Gets the custom name.
     *
     * <p>This value has no effect on players, they will always use their real name.</p>
     *
     * @return the custom name
     */
    net.kyori.adventure.text.@Nullable Component customName();

    /**
     * Sets the custom name.
     *
     * <p>This name will be used in death messages and can be sent to the client as a nameplate over the mob.</p>
     *
     * <p>Setting the name to {@code null} will clear it.</p>
     *
     * <p>This value has no effect on players, they will always use their real name.</p>
     *
     * @param customName the custom name to set
     */
    void customName(final net.kyori.adventure.text.@Nullable Component customName);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取生物或方块的自定义名称，若无则返回null.
     * <p>
     * 这个方法对玩家没有作用，玩家将一直使用他们的真名.
     * <p>原文：
     * Gets the custom name on a mob or block. If there is no name this method
     * will return null.
     * <p>
     * This value has no effect on players, they will always use their real
     * name.
     *
     * @deprecated in favour of {@link #customName()}
     * @return 生物或方块的自定义名称（若无则返回null）
     * <p>原文：name of the mob/block or null
     */
    @Deprecated // Paper
    @Nullable
    public String getCustomName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置生物或方块的自定义名称，该名称可被用于死亡信息，且能作为生物头顶的标签内容被发送到客户端.
     * <p>
     * 设置为空字符串或者null将会清除该生物或方块的自定义名称.
     * <p>
     * 这个方法对玩家没有作用，玩家将一直使用他们的真名.
     * <p>原文：
     * Sets a custom name on a mob or block. This name will be used in death
     * messages and can be sent to the client as a nameplate over the mob.
     * <p>
     * Setting the name to null or an empty string will clear it.
     * <p>
     * This value has no effect on players, they will always use their real
     * name.
     *
     * @deprecated in favour of {@link #customName(net.kyori.adventure.text.Component)}
     * @param name 自定义名称
     * <p>原文：the name to set
     */
    @Deprecated // Paper
    public void setCustomName(@Nullable String name);
}
