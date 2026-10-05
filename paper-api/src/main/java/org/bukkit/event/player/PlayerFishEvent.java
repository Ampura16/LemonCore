package org.bukkit.event.player;

import io.papermc.paper.event.entity.FishHookStateChangeEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家钓鱼时触发本事件.
 * <p>原文：
 * Thrown when a player is fishing
 *
 * <p>If you want to monitor a fishhooks state transition, you can use {@link FishHookStateChangeEvent}.</p>
 */
public class PlayerFishEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity entity;
    private final FishHook hookEntity;
    private final EquipmentSlot hand;
    private final State state;
    private int exp;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerFishEvent(@NotNull final Player player, @Nullable final Entity entity, @NotNull final FishHook hookEntity, @Nullable EquipmentSlot hand, @NotNull final State state) {
        super(player);
        this.entity = entity;
        this.hookEntity = hookEntity;
        this.hand = hand;
        this.state = state;
    }

    @ApiStatus.Internal
    public PlayerFishEvent(@NotNull final Player player, @Nullable final Entity entity, @NotNull final FishHook hookEntity, @NotNull final State state) {
        this(player, entity, hookEntity, null, state);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家钓到的实体.
     * <p>原文：
     * Gets the entity caught by the player.
     * <p>
     * If player has fished successfully, the result may be cast to {@link
     * org.bukkit.entity.Item}.
     *
     * @return 玩家钓到的实体, 钓鱼时为实体, 如果鱼钩卡在地面上或什么都没钓到则为 null
     * <p>原文：Entity caught by the player, Entity if fishing, and {@code null} if
     *     bobber has gotten stuck in the ground or nothing has been caught
     */
    @Nullable
    public Entity getCaught() {
        return this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取钓鱼钩.
     * <p>原文：
     * Gets the fishing hook.
     *
     * @return 代表钓鱼钩/浮标的实体
     * <p>原文：the entity representing the fishing hook/bobber.
     */
    @NotNull
    public FishHook getHook() {
        return this.hookEntity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件中使用的手.
     * <p>原文：
     * Get the hand that was used in this event.
     * <p>
     * The hand used is only present for player interactions.
     * This means it will be {@code null} if state is set
     * to {@link State#BITE} or {@link State#FAILED_ATTEMPT}.
     *
     * @return 使用的手
     * <p>原文：the hand
     */
    @Nullable
    public EquipmentSlot getHand() {
        return this.hand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取钓鱼的状态.
     * <p>原文：
     * Gets the state of the fishing
     *
     * @return 描述钓鱼状态的枚举值
     * <p>原文：A State detailing the state of the fishing
     */
    @NotNull
    public State getState() {
        return this.state;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取钓鱼时获得的经验值.
     * <p>原文：
     * Gets the amount of experience received when fishing.
     * <p>
     * Note: This value has no default effect unless the event state is {@link
     * State#CAUGHT_FISH}.
     *
     * @return 掉落的经验值数量
     * <p>原文：the amount of experience to drop
     */
    public int getExpToDrop() {
        return this.exp;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置钓鱼时获得的经验值.
     * <p>原文：
     * Sets the amount of experience received when fishing.
     * <p>
     * Note: This value has no default effect unless the event state is {@link
     * State#CAUGHT_FISH}.
     *
     * @param amount 掉落的经验值数量
     * <p>原文：the amount of experience to drop
     */
    public void setExpToDrop(int amount) {
        this.exp = amount;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于指定钓鱼状态的枚举
     * <p>原文：
     * An enum to specify the state of the fishing
     */
    public enum State {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当玩家正在钓鱼时, 即抛出鱼线.
         * <p>原文：
         * When a player is fishing, ie casting the line out.
         */
        FISHING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当玩家成功钓到鱼并正在收线时.
         * 在这种情况下, "鱼" 是指通过钓鱼从水中获取的任何物品,
         * 即一个物品, 但不一定是鱼.
         * <p>原文：
         * When a player has successfully caught a fish and is reeling it in. In
         * this instance, a "fish" is any item retrieved from water as a result
         * of fishing, ie an item, but not necessarily a fish.
         */
        CAUGHT_FISH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当玩家成功钓到一个实体时.
         * 这指的是世界上任何已经被鱼钩直接钩住的已生成实体.
         * <p>原文：
         * When a player has successfully caught an entity. This refers to any
         * already spawned entity in the world that has been hooked directly by
         * the rod.
         */
        CAUGHT_ENTITY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当鱼钩卡在地面上时.
         * <p>原文：
         * When a bobber is stuck in the ground.
         */
        IN_GROUND,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当玩家在钓鱼时未能咬钩, 通常是由于时机不对.
         * <p>原文：
         * When a player fails to catch a bite while fishing usually due to
         * poor timing.
         */
        FAILED_ATTEMPT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当玩家收线但没有鱼上钩时.
         * <p>原文：
         * When a player reels in their hook without receiving any bites.
         */
        REEL_IN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当鱼钩上有鱼咬钩并准备收线时调用.
         * <p>原文：
         * Called when there is a bite on the hook and it is ready to be reeled
         * in.
         */
        BITE,
        /**
         * Called when a bobber was lured, and is now waiting to be hooked
         * (when a "fish" starts to swim toward the bobber to bite it).
         */
        LURED,
    }
}
