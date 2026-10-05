package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表会施法的灾厄村民 (目前只有唤魔者和幻术师).
 * <p>原文：
 * Represents a spell casting "Illager".
 */
public interface Spellcaster extends Illager {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表实体当前使用的咒语.
     * <p>原文：
     * Represents the current spell the entity is using.
     */
    public enum Spell {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 未施法状态.
         * <p>原文：
         * No spell is being used.
         */
        NONE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 召唤恼鬼.
         * <p>原文：
         * The spell that summons Vexes.
         */
        SUMMON_VEX,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 召唤尖牙进行攻击.
         * <p>原文：
         * The spell that summons Fangs.
         */
        FANGS,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 发出"呜噜噜"的叫声.
         * <p>原文：
         * The "wololo" spell.
         */
        WOLOLO,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 使实体隐身的咒语.
         * <p>原文：
         * The spell that makes the casting entity invisible.
         */
        DISAPPEAR,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 使目标生物失明的咒语.
         * <p>原文：
         * The spell that makes the target blind.
         */
        BLINDNESS;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体当前使用的{@link Spell 咒语}.
     * <p>原文：
     * Gets the {@link Spell} the entity is currently using.
     *
     * @return 实体当前使用的{@link Spell 咒语}
     * <p>原文：the current spell
     */
    @NotNull
    Spell getSpell();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体将要使用的{@link Spell 咒语}.
     * <p>原文：
     * Sets the {@link Spell} the entity is currently using.
     *
     * @param spell 实体将要使用的{@link Spell 咒语}
     * <p>原文：the spell the entity should be using
     */
    void setSpell(@NotNull Spell spell);
}
