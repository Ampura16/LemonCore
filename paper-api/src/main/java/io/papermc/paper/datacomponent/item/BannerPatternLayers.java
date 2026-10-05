package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import java.util.List;
import org.bukkit.block.banner.Pattern;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有旗帜上的图案层。
 * <p>原文：
 * Holds the layers of patterns on a banner.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#BANNER_PATTERNS
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface BannerPatternLayers {

    @Contract(value = "_ -> new", pure = true)
    static BannerPatternLayers bannerPatternLayers(final List<Pattern> patterns) {
        return bannerPatternLayers().addAll(patterns).build();
    }

    @Contract(value = "-> new", pure = true)
    static BannerPatternLayers.Builder bannerPatternLayers() {
        return ItemComponentTypesBridge.bridge().bannerPatternLayers();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取旗帜上的图案。
     * <p>原文：
     * Gets the patterns on the banner.
     *
     * @return 图案
     * <p>原文：the patterns
     */
    @Contract(pure = true)
    @Unmodifiable List<Pattern> patterns();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link BannerPatternLayers} 的构建器。
     * <p>原文：
     * Builder for {@link BannerPatternLayers}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<BannerPatternLayers> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向旗帜添加一个图案。
         * <p>原文：
         * Adds a pattern to the banner.
         *
         * @param pattern 图案
         * <p>原文：the pattern
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #patterns()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder add(Pattern pattern);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向旗帜添加多个图案。
         * <p>原文：
         * Adds multiple patterns to the banner.
         *
         * @param patterns 图案
         * <p>原文：the patterns
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #patterns()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addAll(List<Pattern> patterns);
    }
}
