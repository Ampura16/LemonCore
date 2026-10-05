package org.bukkit.util;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 包含枚举中常见方法的类。
 * <p>原文：
 * Class which holds common methods which are present in an enum.
 *
 * @param <T> 旧枚举的类型
 * <p>原文：the type of the old enum.
 * @deprecated 仅用于向后兼容
 * <p>原文：only for backwards compatibility.
 */
@ApiStatus.Internal
@Deprecated(since = "1.21", forRemoval = true) @ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
public interface OldEnum<T extends OldEnum<T>> extends Comparable<T> {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param other 要比较的对象
     * <p>原文：to compare to.
     * @return 如果此旧枚举较低则返回负数，相等返回零，高于给定旧枚举返回正数
     * <p>原文：negative if this old enum is lower, zero if equal and positive if
     * higher than the given old enum.
     * @deprecated 仅用于向后兼容，旧枚举无法比较
     * <p>原文：only for backwards compatibility, old enums can not be
     * compared.
     */
    @Deprecated(since = "1.21", forRemoval = true) @ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
    @Override
    int compareTo(@NotNull T other);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 旧枚举的名称
     * <p>原文：the name of the old enum.
     * @deprecated 仅用于向后兼容
     * <p>原文：only for backwards compatibility.
     */
    @NotNull
    @Deprecated(since = "1.21", forRemoval = true) @ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
    String name();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 旧枚举的序数
     * <p>原文：the ordinal of the old enum.
     * @deprecated 仅用于向后兼容，不保证旧枚举始终具有相同的序数
     * <p>原文：only for backwards compatibility, it is not guaranteed that
     * an old enum always has the same ordinal.
     */
    @Deprecated(since = "1.21", forRemoval = true) @ApiStatus.ScheduledForRemoval(inVersion = "1.22") // Paper - will be removed via asm-utils
    int ordinal();
}
