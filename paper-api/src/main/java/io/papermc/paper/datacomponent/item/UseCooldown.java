package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有物品使用时的冷却信息内容。
 * <p>原文：
 * Holds the contents of cooldown information when an item is used.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#USE_COOLDOWN
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface UseCooldown {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建使用冷却的新构建器。
     * <p>原文：
     * Creates a new builder for use cooldown.
     *
     * @param seconds 持续时间（秒），必须为正数
     * <p>原文：the duration in seconds; must be positive
     * @return 构建器
     * <p>原文：builder
     */
    @Contract(value = "_ -> new", pure = true)
    static UseCooldown.Builder useCooldown(final float seconds) {
        return ItemComponentTypesBridge.bridge().useCooldown(seconds);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 冷却持续的秒数。
     * <p>原文：
     * The amount of seconds the cooldown will be active for.
     *
     * @return 冷却秒数
     * <p>原文：cooldown seconds
     */
    @Contract(pure = true)
    float seconds();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于标识此冷却组的唯一资源位置。
     * <p>
     * 如果存在，这使物品可以与同一冷却组中的其他物品共享冷却。
     * <p>原文：
     * The unique resource location to identify this cooldown group.
     * <p>
     * This allows items to share cooldowns with other items in the same cooldown group, if present.
     *
     * @return 冷却组；如果不存在则为 null
     * <p>原文：cooldown group, or null if not present
     */
    @Contract(pure = true)
    @Nullable Key cooldownGroup();

    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<UseCooldown> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 为此冷却组设置唯一的资源位置。
         * <p>
         * 这使物品可以与同一冷却组中的其他物品共享冷却。
         * </p>
         * <p>原文：
         * Sets a unique resource location for this cooldown group.
         * <p>
         * This allows items to share cooldowns with other items in the same cooldown group.
         * </p>
         *
         * @param key 唯一资源位置；可以为 null
         * <p>原文：the unique resource location; can be null
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #cooldownGroup()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder cooldownGroup(@Nullable Key key);
    }
}
