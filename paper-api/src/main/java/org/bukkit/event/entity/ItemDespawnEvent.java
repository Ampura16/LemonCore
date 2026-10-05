package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当{@link org.bukkit.entity.Item 掉落物}存在时间超过5分钟后被移除时触发本事件
 * (注:掉落物存在时间因服务器配置和安装的插件而异).
 * <p>
 * 取消本事件将使掉落物可继续存留5分钟. 服务器不对这一表现(规律)作担保, 未来的版本或存变数.
 * <p>原文：
 * This event is called when a {@link org.bukkit.entity.Item} is removed from
 * the world because it has existed for 5 minutes.
 * <p>
 * Cancelling the event results in the item being allowed to exist for 5 more
 * minutes. This behavior is not guaranteed and may change in future versions.
 */
public class ItemDespawnEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Location location;

    private boolean cancelled;

    @ApiStatus.Internal
    public ItemDespawnEvent(@NotNull final Item despawnee, @NotNull final Location location) {
        super(despawnee);
        this.location = location;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个事件所涉及的实体.
     *
     * @return 参与此事件的实体
     */
    @NotNull
    @Override
    public Item getEntity() {
        return (Item) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品消失的位置.
     * <p>原文：
     * Gets the location at which the item is despawning.
     *
     * @return 将消失的掉落物的位置
     * <p>原文：The location at which the item is despawning
     */
    @NotNull
    public Location getLocation() {
        return this.location.clone();
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
