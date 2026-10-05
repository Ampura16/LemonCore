package com.destroystokyo.paper.profile;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import net.kyori.adventure.text.object.PlayerHeadObjectContents;
import org.bukkit.profile.PlayerTextures;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import static net.kyori.adventure.text.object.PlayerHeadObjectContents.property;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示玩家在游戏中的档案，例如 UUID、名称和纹理。
 * <p>原文：
 * Represents a players profile for the game, such as UUID, Name, and textures.
 */
@NullMarked
public interface PlayerProfile extends org.bukkit.profile.PlayerProfile, PlayerHeadObjectContents.SkinSource {

    /**
     * @return 玩家名称（如果已设置）
     * <p>原文：The players name, if set
     */
    @Override
    @Nullable
    String getName();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此档案的名称
     * <p>原文：
     * Sets this profiles Name
     *
     * @param name 新名称
     * <p>原文：The new Name
     * @return 原来的名称
     * <p>原文：The previous Name
     */
    @Deprecated(forRemoval = true, since = "1.18.1")
    String setName(@Nullable String name);

    /**
     * @return 玩家的唯一标识符（如果已设置）
     * <p>原文：The players unique identifier, if set
     */
    @Nullable
    UUID getId();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此档案的 UUID
     * <p>原文：
     * Sets this profiles UUID
     *
     * @param uuid 新 UUID
     * <p>原文：The new UUID
     * @return 原来的 UUID
     * <p>原文：The previous UUID
     */
    @Deprecated(forRemoval = true, since = "1.18.1")
    @Nullable
    UUID setId(@Nullable UUID uuid);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此档案的 {@link PlayerTextures}。请求时会在 PlayerTextures 中构建当前纹理数据的快照。
     * <p>原文：
     * Gets the {@link PlayerTextures} of this profile.
     * This will build a snapshot of the current texture data once
     * requested inside PlayerTextures.
     *
     * @return 纹理
     * <p>原文：the textures
     */
    @Override
    PlayerTextures getTextures();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 复制给定的纹理。
     * <p>原文：
     * Copies the given textures.
     *
     * @param textures 要复制的纹理，或 {@code null} 以清除纹理
     * <p>原文：the textures to copy, or {@code null} to clear the
     * textures
     */
    @Override
    void setTextures(@Nullable PlayerTextures textures);

    /**
     * @return 此玩家属性（例如纹理）的可变集合。此处指定的值受实现细节影响。
     * <p>原文：A Mutable set of this player's properties, such as textures.
     * Values specified here are subject to implementation details.
     */
    Set<ProfileProperty> getProperties();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查档案是否具有指定属性
     * <p>原文：
     * Check if the Profile has the specified property
     *
     * @param property 要检查的属性名称
     * <p>原文：Property name to check
     * @return 该属性是否已设置
     * <p>原文：If the property is set
     */
    boolean hasProperty(@Nullable String property);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置一个属性。如果该属性已存在，将替换原有属性
     * <p>原文：
     * Sets a property. If the property already exists, the previous one will be replaced
     *
     * @param property 要设置的属性。
     * <p>原文：Property to set.
     * @throws IllegalArgumentException 如果设置该属性会导致属性超过 16 个
     * <p>原文：if setting the property results in more than 16 properties
     */
    void setProperty(ProfileProperty property);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置多个属性。如果设置的任何属性已存在，将被替换
     * <p>原文：
     * Sets multiple properties. If any of the set properties already exist, it will be replaced
     *
     * @param properties 要设置的属性
     * <p>原文：The properties to set
     * @throws IllegalArgumentException 如果属性数量超过 16 个
     * <p>原文：if the number of properties exceeds 16
     */
    void setProperties(Collection<ProfileProperty> properties);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从此档案中移除特定属性
     * <p>原文：
     * Removes a specific property from this profile
     *
     * @param property 要移除的属性
     * <p>原文：The property to remove
     * @return 是否有属性被移除
     * <p>原文：If a property was removed
     */
    boolean removeProperty(@Nullable String property);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从此档案中移除特定属性
     * <p>原文：
     * Removes a specific property from this profile
     *
     * @param property 要移除的属性
     * <p>原文：The property to remove
     * @return 是否有属性被移除
     * <p>原文：If a property was removed
     */
    default boolean removeProperty(final ProfileProperty property) {
        return this.removeProperty(property.getName());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 移除集合中的所有属性
     * <p>原文：
     * Removes all properties in the collection
     *
     * @param properties 要移除的属性
     * <p>原文：The properties to remove
     * @return 是否有任何属性被移除
     * <p>原文：If any property was removed
     */
    default boolean removeProperties(final Collection<ProfileProperty> properties) {
        boolean removed = false;
        for (final ProfileProperty property : properties) {
            if (this.removeProperty(property)) {
                removed = true;
            }
        }
        return removed;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 清除此档案上的所有属性
     * <p>原文：
     * Clears all properties on this profile
     */
    void clearProperties();

    /**
     * @return 档案现在是否完整（具有 UUID 和名称）
     * <p>原文：If the profile is now complete (has UUID and Name)
     */
    @Override
    boolean isComplete();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 类似 {@link #complete(boolean)}，但只尝试从缓存中获取，不发起网络调用。不考虑纹理。
     * <p>原文：
     * Like {@link #complete(boolean)} but will try only from cache, and not make network calls
     * Does not account for textures.
     *
     * @return 档案现在是否完整（具有 UUID 和名称）
     * <p>原文：If the profile is now complete (has UUID and Name)
     */
    boolean completeFromCache();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 类似 {@link #complete(boolean)}，但只尝试从缓存中获取，不发起网络调用。不考虑纹理。
     * <p>原文：
     * Like {@link #complete(boolean)} but will try only from cache, and not make network calls
     * Does not account for textures.
     *
     * @param onlineMode 是否将其视为正版模式
     * <p>原文：Treat this as online mode or not
     * @return 档案现在是否完整（具有 UUID 和名称）
     * <p>原文：If the profile is now complete (has UUID and Name)
     */
    boolean completeFromCache(boolean onlineMode);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 类似 {@link #complete(boolean)}，但只尝试从缓存中获取，不发起网络调用。不考虑纹理。
     * <p>原文：
     * Like {@link #complete(boolean)} but will try only from cache, and not make network calls
     * Does not account for textures.
     *
     * @param lookupUUID 如果只提供了名称，是否应查找 UUID
     * <p>原文：If only name is supplied, should we do a UUID lookup
     * @param onlineMode 是否将其视为正版模式
     * <p>原文：Treat this as online mode or not
     * @return 档案现在是否完整（具有 UUID 和名称）
     * <p>原文：If the profile is now complete (has UUID and Name)
     */
    boolean completeFromCache(boolean lookupUUID, boolean onlineMode);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果此档案不完整，则调用 API 将其补全。这是阻塞操作，应异步执行。
     * <p>
     * 这也会补全纹理。如果不想加载纹理，请使用 {{@link #complete(boolean)}}
     * <p>原文：
     * If this profile is not complete, then make the API call to complete it.
     * This is a blocking operation and should be done asynchronously.
     * <p>
     * This will also complete textures. If you do not want to load textures, use {{@link #complete(boolean)}}
     *
     * @return 档案现在是否完整（具有 UUID 和名称）（如果触发速率限制，该操作可能失败）
     * <p>原文：If the profile is now complete (has UUID and Name) (if you get rate limited, this operation may fail)
     */
    default boolean complete() {
        return this.complete(true);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果此档案不完整，则调用 API 将其补全。这是阻塞操作，应异步执行。
     * <p>
     * 可选择同时填充纹理。
     * <p>
     * 将自动判断正版验证模式
     * <p>原文：
     * If this profile is not complete, then make the API call to complete it.
     * This is a blocking operation and should be done asynchronously.
     * <p>
     * Optionally will also fill textures.
     * <p>
     * Online mode will be automatically determined
     *
     * @param textures 控制是否应为档案填充纹理属性
     * <p>原文：controls if we should fill the profile with texture properties
     * @return 档案现在是否完整（具有 UUID 和名称）（如果触发速率限制，该操作可能失败）
     * <p>原文：If the profile is now complete (has UUID and Name) (if you get rate limited, this operation may fail)
     */
    boolean complete(boolean textures);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果此档案不完整，则调用 API 将其补全。这是阻塞操作，应异步执行。
     * <p>
     * 可选择同时填充纹理。
     * <p>原文：
     * If this profile is not complete, then make the API call to complete it.
     * This is a blocking operation and should be done asynchronously.
     * <p>
     * Optionally will also fill textures.
     *
     * @param textures 控制是否应为档案填充纹理属性
     * <p>原文：controls if we should fill the profile with texture properties
     * @param onlineMode 是否将此服务器视为正版模式
     * <p>原文：Treat this server as online mode or not
     * @return 档案现在是否完整（具有 UUID 和名称）（如果触发速率限制，该操作可能失败）
     * <p>原文：If the profile is now complete (has UUID and Name) (if you get rate limited, this operation may fail)
     */
    boolean complete(boolean textures, boolean onlineMode);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 基于此档案生成更新后的玩家档案。<p> 会尝试通过填充缺失的属性（名称、唯一 ID、纹理等）生成完整的档案，并将现有属性（例如名称、纹理等）更新为官方的最新值。该操作不会修改当前档案，而是生成新的已更新 {@link PlayerProfile}。<p> 如果不存在具有此档案唯一 ID 或名称的玩家，该操作将产生与当前档案相同的档案，而它可能并不完整。<p> 这是异步操作：更新档案可能会在另一个线程中发起外部连接以获取最新的档案属性。更新后的档案可用时，返回的 {@link CompletableFuture} 将完成。为避免阻塞服务器主线程，不应在服务器主线程上等待返回的 CompletableFuture 的结果。如果想在更新后的玩家档案可用时在服务器主线程上对其进行处理，可以这样做：<pre>
     * profile.update().thenAcceptAsync(updatedProfile -> {
     *     // Do something with the updated profile:
     *     // ...
     * }, runnable -> Bukkit.getScheduler().runTask(plugin, runnable));
     * </pre>
     * <p>原文：
     * Produces an updated player profile based on this profile.
     * <p>
     * This tries to produce a completed profile by filling in missing
     * properties (name, unique id, textures, etc.), and updates existing
     * properties (e.g. name, textures, etc.) to their official and up-to-date
     * values. This operation does not alter the current profile, but produces a
     * new updated {@link PlayerProfile}.
     * <p>
     * If no player exists for the unique id or name of this profile, this
     * operation yields a profile that is equal to the current profile, which
     * might not be complete.
     * <p>
     * This is an asynchronous operation: Updating the profile can result in an
     * outgoing connection in another thread in order to fetch the latest
     * profile properties. The returned {@link CompletableFuture} will be
     * completed once the updated profile is available. In order to not block
     * the server's main thread, you should not wait for the result of the
     * returned CompletableFuture on the server's main thread. Instead, if you
     * want to do something with the updated player profile on the server's main
     * thread once it is available, you could do something like this:
     * <pre>
     * profile.update().thenAcceptAsync(updatedProfile -> {
     *     // Do something with the updated profile:
     *     // ...
     * }, runnable -> Bukkit.getScheduler().runTask(plugin, runnable));
     * </pre>
     */
    @Override
    CompletableFuture<PlayerProfile> update();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此档案是否关联了纹理
     * <p>原文：
     * Whether this Profile has textures associated to it
     *
     * @return 是否具有纹理属性
     * <p>原文：If it has a textures property
     */
    default boolean hasTextures() {
        return this.hasProperty("textures");
    }

    /**
     * {@inheritDoc}
     *
     * @return 克隆的玩家档案。
     * <p>原文：the cloned player profile.
     */
    @Override
    PlayerProfile clone();

    @Override
    default void applySkinToPlayerHeadContents(final PlayerHeadObjectContents.Builder builder) {
        if (this.getProperties().isEmpty() && (this.getName() != null) != (this.getId() != null)) {
            if (this.getId() != null) {
                builder.id(this.getId());
            } else {
                builder.name(this.getName());
            }
            return;
        }
        builder.id(this.getId())
            .name(this.getName())
            .profileProperties(this.getProperties().stream().map(prop -> property(prop.getName(), prop.getValue(), prop.getSignature())).toList());
    }
}
