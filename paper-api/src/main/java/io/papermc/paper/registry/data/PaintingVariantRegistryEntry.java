package io.papermc.paper.registry.data;

import io.papermc.paper.registry.RegistryBuilder;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Art;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link Art} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link Art} type.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface PaintingVariantRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此画的宽度（方块数）。
     * <p>原文：
     * Provides the width of this painting in blocks.
     *
     * @return 宽度。
     * <p>原文：the width.
     * @see Art#getBlockWidth()
     */
    @Range(from = 1, to = 16) int width();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此画的高度（方块数）。
     * <p>原文：
     * Provides the height of this painting in blocks.
     *
     * @return 高度。
     * <p>原文：the height.
     * @see Art#getBlockHeight()
     */
    @Range(from = 1, to = 16) int height();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供创造模式物品栏中可见的画的标题。
     * <p>原文：
     * Provides the title of the painting visible in the creative inventory.
     *
     * @return 标题。
     * <p>原文：the title.
     * @see Art#title()
     */
    @Nullable Component title();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供创造模式物品栏中可见的画的作者。
     * <p>原文：
     * Provides the author of the painting visible in the creative inventory.
     *
     * @return 作者。
     * <p>原文：the author.
     * @see Art#author()
     */
    @Nullable Component author();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供画的资源 ID，即要使用的精灵图位置。
     * <p>原文：
     * Provides the asset id of the painting, which is the location of the sprite to use.
     *
     * @return 资源 ID。
     * <p>原文：the asset id.
     * @see Art#assetId()
     */
    Key assetId();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link PaintingVariantRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #width(int)}</li>
     * <li>{@link #height(int)}</li>
     * <li>{@link #assetId(Key)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link PaintingVariantRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>{@link #width(int)}</li>
     *     <li>{@link #height(int)}</li>
     *     <li>{@link #assetId(Key)}</li>
     * </ul>
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends PaintingVariantRegistryEntry, RegistryBuilder<Art> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置画的宽度（方块数）。
         * <p>原文：
         * Sets the width of the painting in blocks.
         *
         * @param width 宽度（方块）。
         * <p>原文：the width in blocks.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see PaintingVariantRegistryEntry#width()
         * @see Art#getBlockWidth()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder width(@Range(from = 1, to = 16) int width);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置画的高度（方块数）。
         * <p>原文：
         * Sets the height of the painting in blocks.
         *
         * @param height 高度（方块）。
         * <p>原文：the height in blocks.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see PaintingVariantRegistryEntry#height()
         * @see Art#getBlockHeight()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder height(@Range(from = 1, to = 16) int height);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置画的标题。
         * <p>原文：
         * Sets the title of the painting.
         *
         * @param title 标题。
         * <p>原文：the title.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see PaintingVariantRegistryEntry#title()
         * @see Art#title()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder title(@Nullable Component title);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置画的作者。
         * <p>原文：
         * Sets the author of the painting.
         *
         * @param author 作者。
         * <p>原文：the author.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see PaintingVariantRegistryEntry#author()
         * @see Art#author()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder author(@Nullable Component author);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置画的资源 ID，即要使用的精灵图位置。
         * <p>原文：
         * Sets the asset id of the painting, which is the location of the sprite to use.
         *
         * @param assetId 资源 ID。
         * <p>原文：the asset id.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see PaintingVariantRegistryEntry#assetId()
         * @see Art#assetId()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder assetId(Key assetId);
    }
}
