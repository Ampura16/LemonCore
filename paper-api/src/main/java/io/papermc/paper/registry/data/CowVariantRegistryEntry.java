package io.papermc.paper.registry.data;

import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.data.client.ClientTextureAsset;
import org.bukkit.entity.Cow;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link Cow.Variant} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link Cow.Variant} type.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface CowVariantRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 牛变种用于渲染所配置纹理的模型。
     * <p>原文：
     * The model of the cow variant to render the configured texture on.
     */
    enum Model {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 普通牛模型。
         * <p>原文：
         * The normal cow model.
         */
        NORMAL,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 寒带牛模型。
         * <p>原文：
         * The cold cow model.
         */
        COLD,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 温带牛模型。
         * <p>原文：
         * The warm cow model.
         */
        WARM,
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供牛变种的客户端纹理资源，表示要使用的纹理。
     * <p>原文：
     * Provides the client texture asset of the cow variant, which represents the texture to use.
     *
     * @return 客户端纹理资源。
     * <p>原文：the client texture asset.
     */
    ClientTextureAsset clientTextureAsset();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供牛变种的模型。
     * <p>原文：
     * Provides the model of the cow variant.
     *
     * @return 模型。
     * <p>原文：the model.
     */
    Model model();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link CowVariantRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #clientTextureAsset(ClientTextureAsset)}</li>
     * <li>{@link #model(Model)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link CowVariantRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>{@link #clientTextureAsset(ClientTextureAsset)}</li>
     *     <li>{@link #model(Model)}</li>
     * </ul>
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends CowVariantRegistryEntry, RegistryBuilder<Cow.Variant> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置牛变种的客户端纹理资源，即要使用的纹理位置。
         * <p>原文：
         * Sets the client texture asset of the cow variant, which is the location of the texture to use.
         *
         * @param clientTextureAsset 客户端纹理资源。
         * <p>原文：the client texture asset.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see CowVariantRegistryEntry#clientTextureAsset()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder clientTextureAsset(ClientTextureAsset clientTextureAsset);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此牛变种使用的模型。
         * <p>原文：
         * Sets the model to use for this cow variant.
         *
         * @param model 模型。
         * <p>原文：the model.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see CowVariantRegistryEntry#model()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder model(Model model);
    }
}
