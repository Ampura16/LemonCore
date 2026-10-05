package io.papermc.paper.text;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示该类型可由客户端过滤，并可能根据玩家的设置以不同方式显示。
 * <p>原文：
 * Denotes that this type is filterable by the client, and may be shown differently
 * depending on the player's set configuration.
 *
 * @param <T> 值的类型
 * <p>原文：type of value
 */
@ApiStatus.Experimental
@NullMarked
public interface Filtered<T> {

    @Contract(value = "_, _ -> new", pure = true)
    static <T> Filtered<T> of(final T raw, final @Nullable T filtered) {
        @ApiStatus.Internal
        record Instance<T>(T raw, @Nullable T filtered) implements Filtered<T> {}

        return new Instance<>(raw, filtered);
    }

    @Contract(pure = true)
    T raw();

    @Contract(pure = true)
    @Nullable
    T filtered();
}
