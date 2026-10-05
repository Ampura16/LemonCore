package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示一只蝙蝠.
 * <p>原文：
 * Represents a Bat
 */
public interface Bat extends Ambient {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查这个蝙蝠的当前清醒状态.
     * <p>
     * 这并不意味着通过调用方法可以保持某状态.
     * <p>原文：
     * Checks the current waking state of this bat.
     * <p>
     * This does not imply any persistence of state past the method call.
     *
     * @return 如果蝙蝠当前是悬挂在方块上的,则返回true
     * <p>原文：true if the bat is awake or false if it is currently hanging
     *     from a block
     */
    boolean isAwake();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过此方法修改蝙蝠的清醒状态.
     * <p>
     * 这不意味着蝙蝠不会自发的清醒或重新悬挂在方块上.
     * <p>原文：
     * This method modifies the current waking state of this bat.
     * <p>
     * This does not prevent a bat from spontaneously awaking itself, or from
     * reattaching itself to a block.
     *
     * @param state 新状态
     * <p>原文：the new state
     */
    void setAwake(boolean state);

    // Paper start
    /**
     * Gets the location that this bat is currently trying to move towards.
     *
     * @return target location, or null if it's going to find a new location
     */
    @org.jetbrains.annotations.Nullable
    org.bukkit.Location getTargetLocation();

    /**
     * Sets the location that this bat is currently trying to move towards.
     * <p>
     * This can be set to null to cause the bat to recalculate its target location
     *
     * @param location location to move towards (world is ignored, will always use the entity's world)
     */
    void setTargetLocation(@org.jetbrains.annotations.Nullable org.bukkit.Location location);
    // Paper end
}
