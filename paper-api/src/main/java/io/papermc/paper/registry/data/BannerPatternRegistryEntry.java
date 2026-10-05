package io.papermc.paper.registry.data;

import io.papermc.paper.registry.RegistryBuilder;
import net.kyori.adventure.key.Key;
import org.bukkit.block.banner.PatternType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link PatternType} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link PatternType} type.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface BannerPatternRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供图案类型的资源 ID，即要使用的精灵图位置。
     * <p>原文：
     * Provides the asset id of the pattern type, which is the location of the sprite to use.
     *
     * @return 资源 ID。
     * <p>原文：the asset id.
     */
    Key assetId();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供在旗帜提示框中显示图案的翻译键。
     * <p>原文：
     * Provides the translation key for displaying the pattern inside the banner's tooltip.
     *
     * @return 翻译键。
     * <p>原文：the translation key.
     */
    String translationKey();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link BannerPatternRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #assetId(Key)}</li>
     * <li>{@link #translationKey(String)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link BannerPatternRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>{@link #assetId(Key)}</li>
     *     <li>{@link #translationKey(String)}</li>
     * </ul>
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends BannerPatternRegistryEntry, RegistryBuilder<PatternType> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置图案类型的资源 ID，即要使用的精灵图位置。
         * <p>原文：
         * Sets the asset id of the pattern type, which is the location of the sprite to use.
         *
         * @param assetId 资源 ID。
         * <p>原文：the asset id.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see BannerPatternRegistryEntry#assetId()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder assetId(Key assetId);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置在旗帜提示框中显示图案的翻译键。
         * <p>原文：
         * Sets the translation key for displaying the pattern inside the banner's tooltip.
         *
         * @param translationKey 翻译键。
         * <p>原文：the translation key.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see BannerPatternRegistryEntry#translationKey()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder translationKey(String translationKey);

    }

}
