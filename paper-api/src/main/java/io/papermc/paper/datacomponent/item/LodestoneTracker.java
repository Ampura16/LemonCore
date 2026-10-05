package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.bukkit.Location;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 如果存在，指定指南针应指向的目标磁石。
 * <p>原文：
 * If present, specifies the target Lodestone that a Compass should point towards.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#LODESTONE_TRACKER
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface LodestoneTracker {

    @Contract(value = "_, _ -> new", pure = true)
    static LodestoneTracker lodestoneTracker(final @Nullable Location location, final boolean tracked) {
        return lodestoneTracker().location(location).tracked(tracked).build();
    }

    @Contract(value = "-> new", pure = true)
    static LodestoneTracker.Builder lodestoneTracker() {
        return ItemComponentTypesBridge.bridge().lodestoneTracker();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 指南针应指向的位置。
     * <p>原文：
     * The location that the compass should point towards.
     *
     * @return 位置
     * <p>原文：location
     */
    @Contract(value = "-> new", pure = true)
    @Nullable Location location();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果为 {@code true}，当目标位置的磁石被移除时，该组件也会被移除。
     * <p>原文：
     * If {@code true}, when the Lodestone at the target position is removed, the component will be removed.
     *
     * @return 是否被追踪
     * <p>原文：tracked
     */
    @Contract(pure = true)
    boolean tracked();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link LodestoneTracker} 的构建器。
     * <p>原文：
     * Builder for {@link LodestoneTracker}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<LodestoneTracker> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 为此构建器设置要指向的位置。
         * <p>原文：
         * Sets the location to point towards for this builder.
         *
         * @param location 要指向的位置
         * <p>原文：location to point towards
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #location()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder location(@Nullable Location location);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此构建器是否追踪此位置的磁石。
         * <p>原文：
         * Sets if this location lodestone is tracked for this builder.
         *
         * @param tracked 是否被追踪
         * <p>原文：is tracked
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #tracked()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder tracked(boolean tracked);
    }
}
