package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface SwingAnimation {

    @Contract(value = "-> new", pure = true)
    static Builder swingAnimation() {
        return ItemComponentTypesBridge.bridge().swingAnimation();
    }

    @Contract(pure = true)
    Animation type();

    @Contract(pure = true)
    @Positive int duration();

    enum Animation {
        // Start generate - SwingAnimationType
        NONE,
        WHACK,
        STAB;
        // End generate - SwingAnimationType
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link SwingAnimation} 的构建器。
     * <p>原文：
     * Builder for {@link SwingAnimation}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<SwingAnimation> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置挥动动画类型。
         * <p>原文：
         * Sets the swing animation type.
         *
         * @param type 动画类型
         * <p>原文：animation type
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #type()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder type(Animation type);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置挥动动画的持续时间（刻）。
         * <p>原文：
         * Sets the swing animation duration in ticks.
         *
         * @param duration duration (>= 0)
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #duration()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder duration(@Positive int duration);
    }
}
