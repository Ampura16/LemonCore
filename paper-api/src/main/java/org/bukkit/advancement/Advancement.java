package org.bukkit.advancement;

import java.util.Collection;
import org.bukkit.Keyed;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表可以被授予给玩家的进度. 这个类不是指针安全的, 因为底层的进度可能会被重载.
 * <p>原文：
 * Represents an advancement that may be awarded to a player. This class is not
 * reference safe as the underlying advancement may be reloaded.
 */
public interface Advancement extends Keyed {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此进度的所有标准. (即达成该进度所需的条件).
     * <p>原文：
     * Get all the criteria present in this advancement.
     *
     * @return 一个不可编辑的所有条件的副本
     * <p>原文：an unmodifiable copy of all criteria
     */
    @NotNull
    Collection<String> getCriteria();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此进度的需求条件.
     * <p>原文：
     * Returns the requirements for this advancement.
     *
     * @return 一个 AdvancementRequirements 对象
     * <p>原文：an AdvancementRequirements object.
     */
    @NotNull
    AdvancementRequirements getRequirements();

    // Paper start
    /**
     * Get the display info of this advancement.
     * <p>
     * Will be {@code null} when totally hidden, for example with crafting
     * recipes.
     *
     * @return the display info
     */
    @Nullable
    io.papermc.paper.advancement.AdvancementDisplay getDisplay();

    /**
     * Gets the formatted display name for this display. This
     * is part of the component that would be shown in chat when a player
     * completes the advancement. Will return the same as
     * {@link io.papermc.paper.advancement.AdvancementDisplay#displayName()} when an
     * {@link io.papermc.paper.advancement.AdvancementDisplay} is present.
     *
     * @return the display name
     * @see io.papermc.paper.advancement.AdvancementDisplay#displayName()
     */
    @NotNull net.kyori.adventure.text.Component displayName();

    /**
     * Gets the parent advancement, if any.
     *
     * @return the parent advancement
     */
    @Nullable
    Advancement getParent();

    /**
     * Gets all the direct children advancements.
     *
     * @return the children advancements
     */
    @NotNull
    @org.jetbrains.annotations.Unmodifiable
    Collection<Advancement> getChildren();

    /**
     * Gets the root advancement of the tree this is located in.
     *
     * @return the root advancement
     */
    @NotNull
    Advancement getRoot();
    // Paper end
}
