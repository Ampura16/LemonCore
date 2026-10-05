package io.papermc.paper.registry;

import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 由用于修改注册表的任何类型实现。
 * <p>原文：
 * To be implemented by any type used for modifying registries.
 *
 * @param <T> 注册表值类型
 * <p>原文：registry value type
 */
@ApiStatus.Experimental
@NullMarked
@ApiStatus.NonExtendable
public interface RegistryBuilder<T> {
}
