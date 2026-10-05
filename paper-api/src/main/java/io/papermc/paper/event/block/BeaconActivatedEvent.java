package io.papermc.paper.event.block;

import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在信标被激活时调用。当信标光束变得可见时即视为激活。
 * <p>原文：
 * Called when a beacon is activated.
 * Activation occurs when the beacon beam becomes visible.
 */
@NullMarked
public class BeaconActivatedEvent extends BlockEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @ApiStatus.Internal
    public BeaconActivatedEvent(final Block beacon) {
        super(beacon);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回被激活的信标。
     * <p>原文：
     * Returns the beacon that was activated.
     *
     * @return 被激活的信标。
     * <p>原文：the beacon that was activated.
     */
    public Beacon getBeacon() {
        return (Beacon) this.block.getState();
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
