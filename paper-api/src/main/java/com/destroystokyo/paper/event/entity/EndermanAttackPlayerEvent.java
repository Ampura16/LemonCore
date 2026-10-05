/*
 * Copyright (c) 2018 Daniel Ennis (Aikar) MIT License
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

package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当末影人判断是否应攻击玩家时触发。
 * <p>
 * 按照原版规则，如果玩家戴着南瓜头或没有注视末影人，该事件初始即为取消状态。
 * <p>原文：
 * Fired when an Enderman determines if it should attack a player or not.
 * <p>
 * Starts off cancelled if the player is wearing a pumpkin head or is not looking
 * at the Enderman, according to Vanilla rules.
 */
@NullMarked
public class EndermanAttackPlayerEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private boolean cancelled;

    @ApiStatus.Internal
    public EndermanAttackPlayerEvent(final Enderman entity, final Player player) {
        super(entity);
        this.player = player;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 正在考虑攻击的末影人
     * <p>原文：
     * The enderman considering attacking
     *
     * @return 正在考虑攻击的末影人
     * <p>原文：The enderman considering attacking
     */
    @Override
    public Enderman getEntity() {
        return (Enderman) super.getEntity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 末影人正在考虑攻击的玩家
     * <p>原文：
     * The player the Enderman is considering attacking
     *
     * @return 末影人正在考虑攻击的玩家
     * <p>原文：The player the Enderman is considering attacking
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @return 如果取消，末影人将不会攻击
     * <p>原文：If cancelled, the enderman will not attack
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * {@inheritDoc}
     * <br>
     * Cancels if the Enderman will attack this player
     */
    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
