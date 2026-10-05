package io.papermc.paper.registry.data.client;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 客户端纹理资源的以数据为中心、特定于版本的表示，由标识符和路径组成。与使用它的 RegistryEntry 类型遵循相同的、特定于版本的兼容性保证。
 * <p>原文：
 * A data-centric version-specific representation of a client texture asset, composed of an identifier and a path.
 * Follows the same, version-specific, compatibility guarantees as the RegistryEntry types it is used in.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ClientTextureAsset {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 客户端纹理资源的标识符，在客户端上唯一标识该资源。
     * <p>原文：
     * The identifier of the client texture asset, uniquely identifying the asset on the client.
     *
     * @return 标识符。
     * <p>原文：the identifier.
     */
    Key identifier();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 纹理在客户端上的路径，分为命名空间和路径/键两部分。
     * <p>原文：
     * The path of the texture on the client, split into a namespace and a path/key.
     *
     * @return 纹理路径。
     * <p>原文：the texture path.
     */
    Key texturePath();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定的标识符和纹理路径创建新的 {@link ClientTextureAsset}。
     * <p>原文：
     * Creates a new {@link ClientTextureAsset} with the specified identifier and texture path.
     *
     * @param identifier 客户端纹理资源的唯一标识符。
     * <p>原文：the unique identifier for the client texture asset.
     * @param texturePath 资源在客户端上的路径。
     * <p>原文：the path where the asset is located on the client.
     * @return 新的 {@code ClientAsset} 实例。
     * <p>原文：a new {@code ClientAsset} instance.
     */
    @Contract("_,_ -> new")
    static ClientTextureAsset clientTextureAsset(final Key identifier, final Key texturePath) {
        return new ClientTextureAssetImpl(identifier, texturePath);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用提供的标识符创建新的 {@link ClientTextureAsset}，并据此推断纹理路径。
     * <p>原文：
     * Creates a new {@link ClientTextureAsset} using the provided identifier and infers the texture path from it.
     *
     * @param identifier 客户端纹理资源的唯一标识符。
     * <p>原文：the unique identifier for the client texture asset.
     * @return 新的 {@code ClientAsset} 实例。
     * <p>原文：a new {@code ClientAsset} instance.
     */
    @Contract("_ -> new")
    static ClientTextureAsset clientTextureAsset(final Key identifier) {
        return new ClientTextureAssetImpl(identifier, ClientTextureAssetImpl.pathFromIdentifier(identifier));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从提供的字符串格式 {@link Key} 创建新的 {@link ClientTextureAsset}，并据此推断纹理路径。
     * <p>
     * 标识符字符串必须符合 {@link KeyPattern} 格式。
     * <p>原文：
     * Creates a new {@link ClientTextureAsset} from the provided string-formatted {@link Key}
     * and infers the texture path from it.
     * <p>
     * The identifier string must conform to the {@link KeyPattern} format.
     *
     * @param identifier 资源标识符的字符串表示。
     * <p>原文：the string representation of the asset's identifier.
     * @return 新的 {@code ClientAsset} 实例。
     * <p>原文：a new {@code ClientAsset} instance.
     */
    @Contract("_ -> new")
    static ClientTextureAsset clientTextureAsset(final @KeyPattern String identifier) {
        return clientTextureAsset(Key.key(identifier));
    }
}
