package com.destroystokyo.paper.event.profile;

import com.destroystokyo.paper.profile.ProfileProperty;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 允许插件拦截按名称进行的档案查询
 * <p>
 * 在事件触发时，UUID 和属性均未设置。
 * <p>
 * 如果插件设置了 UUID（以及可选的属性），则可能跳过查询档案的 API 调用。
 * <p>
 * 不保证该事件的线程执行上下文。如需了解，请检查 {@link Event#isAsynchronous()}
 * <p>原文：
 * Allows a plugin to intercept a Profile Lookup for a Profile by name
 * <p>
 * At the point of event fire, the UUID and properties are unset.
 * <p>
 * If a plugin sets the UUID, and optionally the properties, the API call to look up the profile may be skipped.
 * <p>
 * No guarantees are made about thread execution context for this event. If you need to know, check
 * {@link Event#isAsynchronous()}
 */
@NullMarked
public class PreLookupProfileEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final String name;

    private @Nullable UUID uuid;
    private Set<ProfileProperty> properties = new HashSet<>();

    @ApiStatus.Internal
    public PreLookupProfileEvent(final String name) {
        super(!Bukkit.isPrimaryThread());
        this.name = name;
    }

    /**
     * @return 档案的名称
     * <p>原文：Name of the profile
     */
    public String getName() {
        return this.name;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果事件调用结束时该值仍为 {@code null}，服务器将调用 Mojang API 查询 UUID（网络请求），随后触发 {@link LookupProfileEvent}
     * <p>原文：
     * If this value is left {@code null} by the completion of the event call, then the server will
     * trigger a call to the Mojang API to look up the UUID (Network Request), and subsequently, fire a
     * {@link LookupProfileEvent}
     *
     * @return 如果插件已提供，则为档案的 UUID
     * <p>原文：The UUID of the profile if it has already been provided by a plugin
     */
    public @Nullable UUID getUUID() {
        return this.uuid;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为此玩家名称设置 UUID。这将跳过查找玩家 UUID 的初始 API 调用。
     * <p>
     * 但如果服务器需要档案属性，你还必须设置它们，否则仍可能发起 API 调用。
     * <p>原文：
     * Sets the UUID for this player name. This will skip the initial API call to find the players UUID.
     * <p>
     * However, if Profile Properties are needed by the server, you must also set them or else an API call might still be made.
     *
     * @param uuid 为档案设置的 UUID，或 {@code null} 以重置
     * <p>原文：the UUID to set for the profile or {@code null} to reset
     */
    public void setUUID(final @Nullable UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * @return 当前待处理的预填充属性。该集合中的任何属性都会自动预填充到此档案中
     * <p>原文：The currently pending pre-populated properties.
     * Any property in this Set will be automatically prefilled on this Profile
     * @deprecated 此事件仅在 UUID 查询时调用，此处设置的属性将被忽略。设置属性请使用 {@link PreFillProfileEvent}。
     * <p>原文：This event is only called for UUID lookups, properties set here will be ignored. Use {@link PreFillProfileEvent} for setting properties.
     */
    @Deprecated(forRemoval = true, since = "1.21.9")
    public Set<ProfileProperty> getProfileProperties() {
        return this.properties;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 清除现有的所有预填充属性并使用提供的属性。该集合中的任何属性都会自动预填充到此档案中
     * <p>原文：
     * Clears any existing pre-populated properties and uses the supplied properties
     * Any property in this Set will be automatically prefilled on this Profile
     *
     * @param properties 要添加的属性
     * <p>原文：The properties to add
     * @deprecated 此事件仅在 UUID 查询时调用，此处设置的属性将被忽略。设置属性请使用 {@link PreFillProfileEvent}。
     * <p>原文：This event is only called for UUID lookups, properties set here will be ignored. Use {@link PreFillProfileEvent} for setting properties.
     */
    @Deprecated(forRemoval = true, since = "1.21.9")
    public void setProfileProperties(final Set<ProfileProperty> properties) {
        this.properties = new HashSet<>();
        this.properties.addAll(properties);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将当前缺失的属性添加到预填充属性集中，并替换已设置的属性。设置属性请使用 {@link PreFillProfileEvent}。该集合中的任何属性都会自动预填充到此档案中
     * <p>原文：
     * Adds any properties currently missing to the pre-populated properties set, replacing any that already were set. Use {@link PreFillProfileEvent} for setting properties.
     * Any property in this Set will be automatically prefilled on this Profile
     *
     * @param properties 要添加的属性
     * <p>原文：The properties to add
     * @deprecated 此事件仅在 UUID 查询时调用，此处设置的属性将被忽略。
     * <p>原文：This event is only called for UUID lookups, properties set here will be ignored.
     */
    @Deprecated(forRemoval = true, since = "1.21.9")
    public void addProfileProperties(final Set<ProfileProperty> properties) {
        this.properties.addAll(properties);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}
