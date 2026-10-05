package io.papermc.paper.world.flag;

import java.util.Set;
import org.bukkit.FeatureFlag;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 由内置注册表中受 {@link FeatureFlag FeatureFlags} 控制的类型实现。数据驱动注册表中受特性标志控制的类型则根本不会存在于该注册表中。
 * <p>原文：
 * Implemented by types in built-in registries that are controlled by {@link FeatureFlag FeatureFlags}.
 * Types in data-driven registries that are controlled by feature flags just will not exist in that registry.
 * @apiNote 当前实现此接口的类型转为数据驱动时，将在下一个主要版本中从该类型上移除此接口。
 * <p>原文：When a type that currently implements this interface transitions to being data-drive, this
 * interface will be removed from that type in the following major version.
 */
@NullMarked
@ApiStatus.NonExtendable
public interface FeatureDependant {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取启用此项所需的特性标志集合。
     * <p>原文：
     * Gets the set of required feature flags for this
     * to be enabled.
     *
     * @return 特性标志的不可变集合
     * <p>原文：the immutable set of feature flags
     */
    default @Unmodifiable Set<FeatureFlag> requiredFeatures() {
        return FeatureFlagProvider.provider().requiredFeatures(this);
    }
}
