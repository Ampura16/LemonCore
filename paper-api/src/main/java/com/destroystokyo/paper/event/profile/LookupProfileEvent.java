package com.destroystokyo.paper.event.profile;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import java.util.Objects;
import java.util.UUID;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 允许插件在从 Mojang API 查询档案之后的任意时刻收到通知。这是查看响应并在必要时缓存内容的机会。
 * <p>
 * 不保证该事件的线程执行上下文。如需了解，请检查 {@link Event#isAsynchronous()}
 * <p>原文：
 * Allows a plugin to be notified anytime AFTER a Profile has been looked up from the Mojang API
 * This is an opportunity to view the response and potentially cache things.
 * <p>
 * No guarantees are made about thread execution context for this event. If you need to know, check
 * {@link Event#isAsynchronous()}
 */
@NullMarked
public class LookupProfileEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PlayerProfile profile;

    @ApiStatus.Internal
    public LookupProfileEvent(final PlayerProfile profile) {
        super(!Bukkit.isPrimaryThread());
        this.profile = profile;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取所查询档案的名称。
     * <p>原文：
     * Gets the name of the profile that was looked up.
     *
     * @return 档案名称
     * <p>原文：the name of the profile
     */
    public String getName() {
        return Objects.requireNonNull(this.profile.getName(), "profile name");
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取所查询档案的 UUID。
     * <p>原文：
     * Gets the UUID of the profile that was looked up.
     *
     * @return 档案的 UUID
     * <p>原文：the UUID of the profile
     */
    public UUID getId() {
        return Objects.requireNonNull(this.profile.getId(), "profile id");
    }

    /**
     * @return 刚刚查询到的档案。该档案可以被修改
     * <p>原文：The profile that was recently looked up. This profile can be mutated
     * @deprecated 此事件仅在 UUID 查询之后调用，此处设置的属性将被忽略。设置属性请使用 {@link FillProfileEvent}。
     * <p>原文：This event is only called after UUID lookups, properties set here will be ignored. Use {@link FillProfileEvent} for setting properties.
     */
    @Deprecated(forRemoval = true, since = "1.21.9")
    public PlayerProfile getPlayerProfile() {
        return this.profile;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
