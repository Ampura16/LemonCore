package io.papermc.paper.datacomponent.item;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import io.papermc.paper.datacomponent.DataComponentBuilder;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.object.PlayerHeadObjectContents;
import org.bukkit.profile.PlayerTextures;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有可解析为 {@link PlayerProfile} 的玩家档案数据。
 * <p>原文：
 * Holds player profile data that can be resolved to a {@link PlayerProfile}.
 *
 * @see io.papermc.paper.datacomponent.DataComponentTypes#PROFILE
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ResolvableProfile extends PlayerHeadObjectContents.SkinSource {

    @Contract(value = "_ -> new", pure = true)
    static ResolvableProfile resolvableProfile(final PlayerProfile profile) {
        return ItemComponentTypesBridge.bridge().resolvableProfile(profile);
    }

    @Contract(value = "-> new", pure = true)
    static ResolvableProfile.Builder resolvableProfile() {
        return ItemComponentTypesBridge.bridge().resolvableProfile();
    }

    @Contract(pure = true)
    @Nullable
    UUID uuid();

    @Contract(pure = true)
    @Nullable
    String name();

    @Contract(pure = true)
    @Unmodifiable
    Collection<ProfileProperty> properties();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此可解析档案是否被标记为动态。
     * <p>
     * 动态可解析档案必须具有非 null 的 {@link #uuid()} 或 {@link #name()}，并由客户端通过网络调用解析。动态档案也不会包含任何属性。
     * <p>原文：
     * Whether this resolvable profile was marked as dynamic.
     * <p>
     * A dynamic resolvable profile is required to either have a non-null {@link #uuid()} or {@link #name()}
     * and will be resolved by the client via a network call.
     * A dynamic profile will also not include any properties.
     *
     * @return 如果此档案被标记为动态则为 {@code true}，否则为 {@code false}。
     * <p>原文：{@code true} if this profile is marked as dynamic, {@code false} otherwise.
     */
    @Contract(pure = true)
    boolean dynamic();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 基于此 {@link ResolvableProfile} 生成更新后的玩家档案，模仿客户端的行为。<ul> <li> 对于 {@link #dynamic()} 档案：<p>会尝试通过填充缺失的属性（名称、唯一 ID、纹理等）生成完整的档案，并将现有属性（例如名称）更新为官方的最新值。该操作不会修改当前档案，而是生成新的已更新 {@link PlayerProfile}。</p> <p>如果不存在具有此档案唯一 ID 或名称的玩家，该操作将产生与当前档案相同的档案，而它可能并不完整。</p> </li> <li> 对于静态（非 {@link #dynamic()}）档案：<p>以一个完全包含 {@link #properties()} 全部内容、且为 null 的 {@link #name()}/{@link #uuid()} 被替换为填充值的档案完成。</p> </li> </ul> 这是异步操作：更新档案可能会在另一个线程中发起外部连接以获取最新的档案属性。更新后的档案可用时，返回的 {@link CompletableFuture} 将完成。为避免阻塞服务器主线程，不应在服务器主线程上等待返回的 CompletableFuture 的结果。如果想在更新后的玩家档案可用时在服务器主线程上对其进行处理，可以这样做：<pre>
     * profile.resolve().thenAcceptAsync(updatedProfile -> {
     *     // Do something with the updated profile:
     *     // ...
     * }, runnable -> Bukkit.getScheduler().runTask(plugin, runnable));
     * </pre>
     * <p>原文：
     * Produces an updated player profile based on this {@link ResolvableProfile}, mirroring client behavior.
     *
     * <ul>
     *     <li>
     *         For {@link #dynamic()} profiles:
     *
     *         <p>This tries to produce a completed profile by filling in missing
     *         properties (name, unique id, textures, etc.), and updates existing
     *         properties (e.g. name) to their official and up-to-date
     *         values. This operation does not alter the current profile, but produces a
     *         new updated {@link PlayerProfile}.</p>
     *
     *         <p>If no player exists for the unique id or name of this profile, this
     *         operation yields a profile that is equal to the current profile, which
     *         might not be complete.</p>
     *     </li>
     *     <li>
     *         For static (non-{@link #dynamic()}) profiles:
     *         <p>Completes with a profile containing all of {@link #properties()} exactly,
     *         and null {@link #name()}/{@link #uuid()} replaced by filler values.</p>
     *     </li>
     * </ul>
     *
     * This is an asynchronous operation: Updating the profile can result in an
     * outgoing connection in another thread in order to fetch the latest
     * profile properties. The returned {@link CompletableFuture} will be
     * completed once the updated profile is available. In order to not block
     * the server's main thread, you should not wait for the result of the
     * returned CompletableFuture on the server's main thread. Instead, if you
     * want to do something with the updated player profile on the server's main
     * thread once it is available, you could do something like this:
     * <pre>
     * profile.resolve().thenAcceptAsync(updatedProfile -> {
     *     // Do something with the updated profile:
     *     // ...
     * }, runnable -> Bukkit.getScheduler().runTask(plugin, runnable));
     * </pre>
     */
    @Contract(pure = true)
    CompletableFuture<PlayerProfile> resolve();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取客户端用于渲染的皮肤补丁。会覆盖从档案解析出的任何值。
     * <p>原文：
     * Gets the skin patch used by the client for rendering. Overrides any values
     * resolved from the profile.
     *
     * @return 皮肤补丁
     * <p>原文：the skin patch
     */
    @Contract(pure = true)
    SkinPatch skinPatch();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 覆盖 {@link ResolvableProfile} 的渲染选项。
     * <p>原文：
     * Override rendering options for a {@link ResolvableProfile}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface SkinPatch {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 返回不覆盖任何内容的空皮肤补丁。
         * <p>原文：
         * Returns the empty skin patch that does not override anything.
         *
         * @return 空皮肤补丁
         * <p>原文：the empty skin patch
         */
        static SkinPatch empty() {
            final class Holder {
                static final SkinPatch INSTANCE = ItemComponentTypesBridge.bridge().emptySkinPatch();
            }
            return Holder.INSTANCE;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 创建皮肤补丁的新构建器。
         * <p>原文：
         * Creates a new builder for a skin patch.
         *
         * @return 新的皮肤补丁构建器
         * <p>原文：new skin patch builder
         */
        @Contract(value = "-> new", pure = true)
        static SkinPatchBuilder skinPatch() {
            return ItemComponentTypesBridge.bridge().skinPatch();
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取身体纹理键。
         * <p>原文：
         * Gets the body texture key.
         *
         * @return 身体纹理键；如果未设置则为 {@code null}
         * <p>原文：the body texture key, or {@code null} if not set
         */
        @Nullable Key body();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取披风纹理键。
         * <p>原文：
         * Gets the cape texture key.
         *
         * @return 披风纹理键；如果未设置则为 {@code null}
         * <p>原文：the cape texture key, or {@code null} if not set
         */
        @Nullable Key cape();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取鞘翅纹理键。
         * <p>原文：
         * Gets the elytra texture key.
         *
         * @return 鞘翅纹理键；如果未设置则为 {@code null}
         * <p>原文：the elytra texture key, or {@code null} if not set
         */
        @Nullable Key elytra();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取皮肤模型。
         * <p>原文：
         * Gets the skin model.
         *
         * @return 皮肤模型；如果未设置则为 {@code null}
         * <p>原文：the skin model, or {@code null} if not set
         */
        PlayerTextures.@Nullable SkinModel model();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 返回此皮肤补丁是否未覆盖任何值。
         * <p>原文：
         * Returns if this skin patch does not override any values.
         *
         * @return 如果此皮肤补丁为空则为 {@code true}
         * <p>原文：{@code true} if this skin patch is empty
         */
        default boolean isEmpty() {
            return this.body() == null && this.cape() == null && this.elytra() == null && this.model() == null;
        }
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link SkinPatch} 的构建器。
     * <p>原文：
     * Builder for {@link SkinPatch}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface SkinPatchBuilder extends DataComponentBuilder<SkinPatch> {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置身体纹理键。
         * <p>原文：
         * Sets the body texture key.
         *
         * @param body 身体纹理键，或 {@code null} 以取消设置
         * <p>原文：the body texture key, or {@code null} to unset it
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        SkinPatchBuilder body(@Nullable Key body);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置披风纹理键。
         * <p>原文：
         * Sets the cape texture key.
         *
         * @param cape 披风纹理键，或 {@code null} 以取消设置
         * <p>原文：the cape texture key, or {@code null} to unset it
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        SkinPatchBuilder cape(@Nullable Key cape);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置鞘翅纹理键。
         * <p>原文：
         * Sets the elytra texture key.
         *
         * @param elytra 鞘翅纹理键，或 {@code null} 以取消设置
         * <p>原文：the elytra texture key, or {@code null} to unset it
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        SkinPatchBuilder elytra(@Nullable Key elytra);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置皮肤模型。
         * <p>原文：
         * Sets the skin model.
         *
         * @param model 皮肤模型，或 {@code null} 以取消设置
         * <p>原文：the skin model, or {@code null} to unset it
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        SkinPatchBuilder model(PlayerTextures.@Nullable SkinModel model);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link ResolvableProfile} 的构建器。
     * <p>原文：
     * Builder for {@link ResolvableProfile}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<ResolvableProfile> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此档案的名称。长度不得超过 16 个字符，且不得包含无效字符。
         * <p>原文：
         * Sets the name for this profile. Must be 16-or-less
         * characters and not contain invalid characters.
         *
         * @param name 名称
         * <p>原文：the name
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #name()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder name(@Pattern("^[!-~]{0,16}$") @Nullable String name);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此档案的 UUID。
         * <p>原文：
         * Sets the UUID for this profile.
         *
         * @param uuid UUID
         * <p>原文：the UUID
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #uuid()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder uuid(@Nullable UUID uuid);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此档案添加一个属性。
         * <p>原文：
         * Adds a property to this profile.
         *
         * @param property 属性
         * <p>原文：the property
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #properties()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addProperty(ProfileProperty property);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此档案添加多个属性。
         * <p>原文：
         * Adds properties to this profile.
         *
         * @param properties 属性
         * <p>原文：the properties
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #properties()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addProperties(Collection<ProfileProperty> properties);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此档案的皮肤补丁。
         * <p>原文：
         * Sets the skin patch for this profile.
         *
         * @param patch 皮肤补丁
         * <p>原文：the skin patch
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #skinPatch()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder skinPatch(SkinPatch patch);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置此档案的皮肤补丁。
         * <p>原文：
         * Configures the skin patch for this profile.
         *
         * @param configure 配置消费者
         * <p>原文：the configuration consumer
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #skinPatch()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder skinPatch(Consumer<SkinPatchBuilder> configure);
    }
}
