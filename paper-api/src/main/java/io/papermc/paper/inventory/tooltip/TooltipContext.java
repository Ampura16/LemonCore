package io.papermc.paper.inventory.tooltip;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 通过 {@link org.bukkit.inventory.ItemStack#computeTooltipLines(TooltipContext, Player)} 计算物品堆提示框的上下文
 * <p>原文：
 * Context for computing itemstack tooltips via
 * {@link org.bukkit.inventory.ItemStack#computeTooltipLines(TooltipContext, Player)}
 */
@NullMarked
public interface TooltipContext {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用给定的高级模式和创造模式设置创建新上下文。
     * <p>原文：
     * Creates a new context with the given advanced and creative
     * mode settings.
     *
     * @param advanced 该上下文是否用于高级工具提示
     * <p>原文：whether the context is for advanced tooltips
     * @param creative 该上下文是否用于创造模式物品栏
     * <p>原文：whether the context is for the creative inventory
     * @return 新上下文
     * <p>原文：a new context
     */
    @Contract("_, _ -> new")
    static TooltipContext create(final boolean advanced, final boolean creative) {
        return new TooltipContextImpl(advanced, creative);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建既非高级也非创造模式的新上下文。
     * <p>原文：
     * Creates a new context that is neither advanced nor creative.
     *
     * @return 新上下文
     * <p>原文：a new context
     */
    @Contract("-> new")
    static TooltipContext create() {
        return new TooltipContextImpl(false, false);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回该上下文是否用于高级提示框。
     * <p>
     * 当玩家启用 {@code F3+H} 时，默认显示高级提示框。
     * <p>原文：
     * Returns whether the context is for advanced
     * tooltips.
     * <p>
     * Advanced tooltips are shown by default
     * when a player has {@code F3+H} enabled.
     *
     * @return 如果用于高级工具提示则为 true
     * <p>原文：true if for advanced tooltips
     */
    boolean isAdvanced();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回该上下文是否用于创造模式物品栏。
     * <p>
     * 当玩家处于创造模式物品栏中时，默认显示创造模式提示框。
     * <p>原文：
     * Returns whether the context is for the creative
     * mode inventory.
     * <p>
     * Creative tooltips are shown by default when a player is
     * in the creative inventory.
     *
     * @return 如果用于创造模式物品栏则为 true
     * <p>原文：true if for creative mode inventory
     */
    boolean isCreative();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回 {@link #isAdvanced()} 设为 true 的新上下文。
     * <p>原文：
     * Returns a new context with {@link #isAdvanced()}
     * set to true.
     *
     * @return 新上下文
     * <p>原文：a new context
     */
    @Contract("-> new")
    TooltipContext asAdvanced();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回 {@link #isCreative()} 设为 true 的新上下文。
     * <p>原文：
     * Returns a new context with {@link #isCreative()}
     * set to true.
     *
     * @return 新上下文
     * <p>原文：a new context
     */
    @Contract("-> new")
    TooltipContext asCreative();
}
