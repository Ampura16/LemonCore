package io.papermc.paper.registry.data;

import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.data.client.ClientTextureAsset;
import org.bukkit.entity.Frog;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link Frog.Variant} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link Frog.Variant} type.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface FrogVariantRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供青蛙变种的客户端纹理资源，表示要使用的纹理。
     * <p>原文：
     * Provides the client texture asset of the frog variant, which represents the texture to use.
     *
     * @return 客户端纹理资源。
     * <p>原文：the client texture asset.
     */
    ClientTextureAsset clientTextureAsset();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link FrogVariantRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #clientTextureAsset(ClientTextureAsset)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link FrogVariantRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>{@link #clientTextureAsset(ClientTextureAsset)}</li>
     * </ul>
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends FrogVariantRegistryEntry, RegistryBuilder<Frog.Variant> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置青蛙变种的客户端纹理资源，即要使用的纹理位置。
         * <p>原文：
         * Sets the client texture asset of the frog variant, which is the location of the texture to use.
         *
         * @param clientTextureAsset 客户端纹理资源。
         * <p>原文：the client texture asset.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see FrogVariantRegistryEntry#clientTextureAsset()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder clientTextureAsset(ClientTextureAsset clientTextureAsset);
    }
}
