package org.bukkit.damage;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表{@link DamageSource}使用的死亡消息类型.
 * <p>原文：
 * Represents a type of death message used by a {@link DamageSource}.
 */
public enum DeathMessageType {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 不应用特殊的死亡消息逻辑.
     * <p>原文：
     * No special death message logic is applied.
     */
    DEFAULT,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 显示摔落伤害死亡的变体而非普通死亡消息.
     * <br>
     * <b>示例：</b>death.fell.assist.item
     * <p>原文：
     * Shows a variant of fall damage death instead of a regular death message.
     * <br>
     * <b>Example:</b> death.fell.assist.item
     */
    FALL_VARIANTS,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 显示有意设计的死亡消息而非普通死亡消息.
     * <p>原文：
     * Shows the intentional game design death message instead of a regular
     * death message.
     */
    INTENTIONAL_GAME_DESIGN;
}
