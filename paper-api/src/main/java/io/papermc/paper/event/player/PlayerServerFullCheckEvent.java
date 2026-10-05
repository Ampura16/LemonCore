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

package io.papermc.paper.event.player;

import com.destroystokyo.paper.profile.PlayerProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在计算服务器对某个玩家而言当前是否已满时触发。
 * <p>原文：
 * Fires when computing if a server is currently considered full for a player.
 */
@NullMarked
public class PlayerServerFullCheckEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PlayerProfile profile;
    private Component kickMessage;
    private boolean allow;

    @ApiStatus.Internal
    public PlayerServerFullCheckEvent(final PlayerProfile profile, final Component kickMessage, final boolean shouldKick) {
        this.profile = profile;
        this.kickMessage = kickMessage;
        this.allow = !shouldKick;
    }

    /**
     * @return 如果用户无法加入服务器，当前计划发送给用户的消息
     * <p>原文：the currently planned message to send to the user if they are unable to join the server
     */
    @Contract(pure = true)
    public Component kickMessage() {
        return this.kickMessage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 拒绝玩家加入此服务器。
     * <p>原文：
     * Denies the player access to join this server.
     *
     * @param kickMessage 如果无法加入，踢出时发送给玩家的消息。
     * <p>原文：The message to send to the player on kick if not able to join.
     */
    public void deny(final Component kickMessage) {
        this.kickMessage = kickMessage;
        this.allow = false;
    }

    /**
     * @return 尝试连接的玩家的档案
     * <p>原文：The profile of the player trying to connect
     */
    public PlayerProfile getPlayerProfile() {
        return this.profile;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置玩家能否加入此服务器。
     * <p>原文：
     * Sets whether the player is able to join this server.
     * @param allow 能否加入服务器
     * <p>原文：can join the server
     */
    public void allow(final boolean allow) {
        this.allow = allow;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家当前能否加入服务器。
     * <p>原文：
     * Gets if the player is currently able to join the server.
     *
     * @return 能否加入服务器；如果服务器应被视为已满则为 false
     * <p>原文：can join the server, or false if the server should be considered full
     */
    public boolean isAllowed() {
        return this.allow;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
