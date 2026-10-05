package io.papermc.paper.world.flag;

import java.util.Set;
import org.bukkit.FeatureFlag;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 由持有 {@link FeatureFlag FeatureFlags} 的类型实现，例如 {@link org.bukkit.generator.WorldInfo} 和 {@link org.bukkit.RegionAccessor}。
 * <p>原文：
 * Implemented by types that hold {@link FeatureFlag FeatureFlags} like
 * {@link org.bukkit.generator.WorldInfo} and {@link org.bukkit.RegionAccessor}.
 */
@NullMarked
@ApiStatus.NonExtendable
public interface FeatureFlagSetHolder {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 根据已加载的特性标志检查此项是否已启用。
     * <p>原文：
     * Checks if this is enabled based on the loaded feature flags.
     *
     * @return 如果已启用则为 true
     * <p>原文：true if enabled
     */
    default boolean isEnabled(final FeatureDependant featureDependant) {
        return this.getFeatureFlags().containsAll(featureDependant.requiredFeatures());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此世界中启用的所有 {@link FeatureFlag FeatureFlags}。
     * <p>原文：
     * Get all {@link FeatureFlag FeatureFlags} enabled in this world.
     *
     * @return 所有已启用的 {@link FeatureFlag FeatureFlags}
     * <p>原文：all enabled {@link FeatureFlag FeatureFlags}
     */
    @Unmodifiable Set<FeatureFlag> getFeatureFlags();
}
