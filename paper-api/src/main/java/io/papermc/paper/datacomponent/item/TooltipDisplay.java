package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.datacomponent.DataComponentType;
import java.util.Set;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface TooltipDisplay {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回用于创建 TooltipDisplay 的新构建器。
     * <p>原文：
     * Returns a new builder for creating a TooltipDisplay.
     *
     * @return 构建器
     * <p>原文：a builder
     */
    @Contract(value = "-> new", pure = true)
    static Builder tooltipDisplay() {
        return ItemComponentTypesBridge.bridge().tooltipDisplay();
    }

    boolean hideTooltip();

    Set<DataComponentType> hiddenComponents();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link TooltipDisplay} 的构建器。
     * <p>原文：
     * Builder for {@link TooltipDisplay}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<TooltipDisplay> {

        @Contract(value = "_ -> this", mutates = "this")
        Builder hideTooltip(boolean hide);

        @Contract(value = "_ -> this", mutates = "this")
        Builder addHiddenComponents(DataComponentType... components);

        @Contract(value = "_ -> this", mutates = "this")
        Builder hiddenComponents(Set<DataComponentType> components);
    }
}
