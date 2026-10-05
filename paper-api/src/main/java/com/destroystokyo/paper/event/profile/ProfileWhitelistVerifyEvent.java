/*
 * Copyright (c) 2017 - Daniel Ennis (Aikar) - MIT License
 *
 *  Permission is hereby granted, free of charge, to any person obtaining
 *  a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including
 *  without limitation the rights to use, copy, modify, merge, publish,
 *  distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to
 *  the following conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 *  LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 *  OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 *  WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.destroystokyo.paper.event.profile;

import com.destroystokyo.paper.profile.PlayerProfile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当服务器需要验证玩家是否在白名单中时触发。
 * <p>
 * 插件可以借此事件覆盖/控制服务器白名单，并动态更改踢出消息。
 * <p>原文：
 * Fires when the server needs to verify if a player is whitelisted.
 * <p>
 * Plugins may override/control the servers whitelist with this event,
 * and dynamically change the kick message.
 */
@NullMarked
public class ProfileWhitelistVerifyEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PlayerProfile profile;
    private final boolean whitelistEnabled;
    private final boolean isOp;
    private boolean whitelisted;
    private @Nullable Component kickMessage;

    @ApiStatus.Internal
    public ProfileWhitelistVerifyEvent(final PlayerProfile profile, final boolean whitelistEnabled, final boolean whitelisted, final boolean isOp, final @Nullable Component kickMessage) {
        this.profile = profile;
        this.whitelistEnabled = whitelistEnabled;
        this.whitelisted = whitelisted;
        this.isOp = isOp;
        this.kickMessage = kickMessage;
    }

    /**
     * @return 如果用户不在白名单中，当前计划发送给用户的消息
     * <p>原文：the currently planned message to send to the user if they are not whitelisted
     * @deprecated 请使用 {@link #kickMessage()}
     * <p>原文：use {@link #kickMessage()}
     */
    @Deprecated
    public @Nullable String getKickMessage() {
        return this.kickMessage == null ? null : LegacyComponentSerializer.legacySection().serialize(this.kickMessage);
    }

    /**
     * @param kickMessage 如果不在白名单中，踢出时发送给玩家的消息。可设置为 {@code null} 以使用服务器配置的默认值
     * <p>原文：The message to send to the player on kick if not whitelisted. May set to {@code null} to use the server configured default
     * @deprecated 请使用 {@link #kickMessage(Component)}
     * <p>原文：Use {@link #kickMessage(Component)}
     */
    @Deprecated
    public void setKickMessage(final @Nullable String kickMessage) {
        this.kickMessage(kickMessage == null ? null : LegacyComponentSerializer.legacySection().deserialize(kickMessage));
    }

    /**
     * @return 如果用户不在白名单中，当前计划发送给用户的消息
     * <p>原文：the currently planned message to send to the user if they are not whitelisted
     */
    @Contract(pure = true)
    public @Nullable Component kickMessage() {
        return this.kickMessage;
    }

    /**
     * @param kickMessage 如果不在白名单中，踢出时发送给玩家的消息。可设置为 {@code null} 以使用服务器配置的默认值
     * <p>原文：The message to send to the player on kick if not whitelisted. May set to {@code null} to use the server configured default
     */
    public void kickMessage(final @Nullable Component kickMessage) {
        this.kickMessage = kickMessage;
    }

    /**
     * @return 尝试连接的玩家的档案
     * <p>原文：The profile of the player trying to connect
     */
    public PlayerProfile getPlayerProfile() {
        return this.profile;
    }

    /**
     * @return 玩家是否在白名单中可以在此服务器上游戏（为 true 也可能是因为白名单未开启）
     * <p>原文：Whether the player is whitelisted to play on this server (whitelist may be off is why it's true)
     */
    public boolean isWhitelisted() {
        return this.whitelisted;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 更改玩家的白名单状态。{@code false} 将拒绝登录
     * <p>原文：
     * Changes the players whitelisted state. {@code false} will deny the login
     *
     * @param whitelisted 新的白名单状态
     * <p>原文：The new whitelisted state
     */
    public void setWhitelisted(final boolean whitelisted) {
        this.whitelisted = whitelisted;
    }

    /**
     * @return 玩家是否因拥有 OP 而获得白名单资格
     * <p>原文：if the player obtained whitelist status by having op
     */
    public boolean isOp() {
        return this.isOp;
    }

    /**
     * @return 服务器是否开启了白名单
     * <p>原文：if the server even has whitelist on
     */
    public boolean isWhitelistEnabled() {
        return this.whitelistEnabled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
