package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表唤魔者.
 * <p>原文：
 * Represents an Evoker "Illager".
 */
public interface Evoker extends Spellcaster {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表唤魔者当前使用的咒语.
     * <p>原文：
     * Represents the current spell the Evoker is using.
     *
     * @deprecated 将来 Minecraft 会有更多的可施法的生物
     * <p>原文：future versions of Minecraft have additional spell casting
     * entities.
     */
    @Deprecated(since = "1.11.2")
    public enum Spell {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 未施法状态.
         * <p>原文：
         * No spell is being evoked.
         */
        NONE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 召唤恼鬼.
         * <p>原文：
         * The spell that summons Vexes.
         */
        SUMMON,
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
     * 获取唤魔者当前使用的{@link Spell 咒语}.
     * <p>原文：
     * Gets the {@link Spell} the Evoker is currently using.
     *
     * @return 唤魔者当前使用的咒语
     * <p>原文：the current spell
     * @deprecated 将来 Minecraft 会有更多的可施法的生物
     * <p>原文：future versions of Minecraft have additional spell casting
     * entities.
     */
    @Deprecated(since = "1.11.2")
    @NotNull
    Spell getCurrentSpell();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置唤魔者将要使用的{@link Spell 咒语}.
     * <p>原文：
     * Sets the {@link Spell} the Evoker is currently using.
     *
     * @param spell 唤魔者应使用的咒语
     * <p>原文：the spell the evoker should be using
     * @deprecated 将来 Minecraft 会有更多的可施法的生物
     * <p>原文：future versions of Minecraft have additional spell casting
     * entities.
     */
    @Deprecated(since = "1.11.2")
    void setCurrentSpell(@Nullable Spell spell);

    /**
     * @return the sheep being targeted by the {@link Spell#WOLOLO wololo spell}, or {@code null} if none
     */
    @Nullable
    Sheep getWololoTarget();

    /**
     * Set the sheep to be the target of the {@link Spell#WOLOLO wololo spell}, or {@code null} to clear.
     *
     * @param sheep new wololo target
     */
    void setWololoTarget(@Nullable Sheep sheep);
}
