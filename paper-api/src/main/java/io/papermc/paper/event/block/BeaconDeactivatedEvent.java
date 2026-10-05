package io.papermc.paper.event.block;

import org.bukkit.Material;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在信标因其基座方块或自身被破坏而失效时调用。
 * <p>原文：
 * Called when a beacon is deactivated, either because its base block(s) or itself were destroyed.
 */
@NullMarked
public class BeaconDeactivatedEvent extends BlockEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @ApiStatus.Internal
    public BeaconDeactivatedEvent(final Block beacon) {
        super(beacon);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回失效的信标。如果信标不存在则返回 {@code null}（这可能发生在已损坏信标失效之后）。
     * <p>原文：
     * Returns the beacon that was deactivated.
     * This will return {@code null} if the beacon does not exist.
     * (which can occur after the deactivation of a now broken beacon)
     *
     * @return 失效的信标；如果不存在则为 {@code null}。
     * <p>原文：The beacon that got deactivated, or {@code null} if it does not exist.
     */
    public @Nullable Beacon getBeacon() {
        return this.block.getType() == Material.BEACON ? (Beacon) this.block.getState() : null;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
