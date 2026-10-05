package org.bukkit.event.player;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import java.util.List;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家对某实体使用剪刀采集物品时调用此事件.
 * <p>
 * 译注:截止 Minecraft 1.15版本, 本事件发生的场景如下:
 * <ul>
 * <li>对羊使用, 采集羊毛.</li>
 * <li>对哞菇使用, 采集蘑菇, 并使哞菇变成牛.</li>
 * <li>对雪傀儡使用, 摘掉雪傀儡戴着的南瓜头, 但不会掉落南瓜物品.</li>
 * </ul>
 * <br>
 * 注意:如果玩家与实体交互被拒, 将不会触发本事件(譬如{@link PlayerInteractEntityEvent}被取消).
 * <p>原文：
 * Called when a player shears an entity
 */
public class PlayerShearEntityEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity entity;
    private final ItemStack item;
    private final EquipmentSlot hand;
    private List<ItemStack> drops;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerShearEntityEvent(@NotNull Player player, @NotNull Entity entity, @NotNull ItemStack item, @NotNull EquipmentSlot hand, final @NotNull List<ItemStack> drops) {
        super(player);
        this.entity = entity;
        this.item = item;
        this.hand = hand;
        this.drops = drops;
    }

    @ApiStatus.Internal
    @Deprecated(since = "1.15.2", forRemoval = true)
    public PlayerShearEntityEvent(@NotNull final Player player, @NotNull final Entity entity) {
        this(player, entity, new ItemStack(Material.SHEARS), EquipmentSlot.HAND, java.util.Collections.emptyList());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家正在剪切的实体.
     * <p>原文：
     * Gets the entity the player is shearing
     *
     * @return 被剪实体
     * <p>原文：the entity the player is shearing
     */
    @NotNull
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于剪切实体的物品.
     * <p>原文：
     * Gets the item used to shear the entity.
     *
     * @return 剪刀
     * <p>原文：the shears
     */
    @NotNull
    public ItemStack getItem() {
        return this.item.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于剪切实体的手.
     * <p>原文：
     * Gets the hand used to shear the entity.
     *
     * @return 手
     * <p>原文：the hand
     */
    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
    }

    /**
     * Get an immutable list of drops for this shearing.
     *
     * @return the shearing drops
     * @see #setDrops(java.util.List)
     */
    public @NotNull @Unmodifiable List<ItemStack> getDrops() {
        return this.drops;
    }

    /**
     * Sets the drops for the shearing.
     *
     * @param drops the shear drops
     */
    public void setDrops(final @NotNull List<ItemStack> drops) {
        this.drops = List.copyOf(drops);
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
}
