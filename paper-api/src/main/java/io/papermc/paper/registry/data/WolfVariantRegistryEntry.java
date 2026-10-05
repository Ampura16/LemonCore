package io.papermc.paper.registry.data;

import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.data.client.ClientTextureAsset;
import org.bukkit.entity.Wolf;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link Wolf.Variant} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link Wolf.Variant} type.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface WolfVariantRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供狼变种愤怒时的客户端纹理资源，即要使用的纹理位置。
     * <p>原文：
     * Provides the client texture asset of the wolf variant for when it is angry, which is the location of the texture to use.
     *
     * @return 客户端纹理资源。
     * <p>原文：the client texture asset.
     */
    ClientTextureAsset angryClientTextureAsset();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供狼变种野生时的客户端纹理资源，即要使用的纹理位置。
     * <p>原文：
     * Provides the client texture asset of the wolf variant for when it is wild, which is the location of the texture to use.
     *
     * @return 客户端纹理资源。
     * <p>原文：the client texture asset.
     */
    ClientTextureAsset wildClientTextureAsset();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供狼变种被驯服时的客户端纹理资源，即要使用的纹理位置。
     * <p>原文：
     * Provides the client texture asset of the wolf variant for when it is tame, which is the location of the texture to use.
     *
     * @return 客户端纹理资源。
     * <p>原文：the client texture asset.
     */
    ClientTextureAsset tameClientTextureAsset();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link WolfVariantRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #angryClientTextureAsset(ClientTextureAsset)}</li>
     * <li>{@link #wildClientTextureAsset(ClientTextureAsset)}</li>
     * <li>{@link #tameClientTextureAsset(ClientTextureAsset)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link WolfVariantRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>{@link #angryClientTextureAsset(ClientTextureAsset)}</li>
     *     <li>{@link #wildClientTextureAsset(ClientTextureAsset)}</li>
     *     <li>{@link #tameClientTextureAsset(ClientTextureAsset)}</li>
     * </ul>
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends WolfVariantRegistryEntry, RegistryBuilder<Wolf.Variant> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置狼变种愤怒时的客户端纹理资源，即要使用的纹理位置。
         * <p>原文：
         * Sets the client texture asset of the wolf variant for when it is angry, which is the location of the texture to use.
         *
         * @param angryClientTextureAsset 客户端纹理资源。
         * <p>原文：the client texture asset.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see WolfVariantRegistryEntry#angryClientTextureAsset()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder angryClientTextureAsset(ClientTextureAsset angryClientTextureAsset);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置狼变种野生时的客户端纹理资源，即要使用的纹理位置。
         * <p>原文：
         * Sets the client texture asset of the wolf variant for when it is wild, which is the location of the texture to use.
         *
         * @param wildClientTextureAsset 客户端纹理资源。
         * <p>原文：the client texture asset.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see WolfVariantRegistryEntry#wildClientTextureAsset()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder wildClientTextureAsset(ClientTextureAsset wildClientTextureAsset);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置狼变种被驯服时的客户端纹理资源，即要使用的纹理位置。
         * <p>原文：
         * Sets the client texture asset of the wolf variant for when it is tame, which is the location of the texture to use.
         *
         * @param tameClientTextureAsset 客户端纹理资源。
         * <p>原文：the client texture asset.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see WolfVariantRegistryEntry#tameClientTextureAsset()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder tameClientTextureAsset(ClientTextureAsset tameClientTextureAsset);
    }
}
