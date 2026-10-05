package io.papermc.paper.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Raider;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当 {@link Raider} 被钟显形时调用。
 * <p>原文：
 * Called when a {@link Raider} is revealed by a bell.
 *
 * @deprecated 请使用 {@link org.bukkit.event.block.BellResonateEvent}
 * <p>原文：use {@link org.bukkit.event.block.BellResonateEvent}
 */
@Deprecated(since = "1.19.4")
public class BellRevealRaiderEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Raider raider;
    private boolean cancelled;

    @ApiStatus.Internal
    public BellRevealRaiderEvent(@NotNull Block bell, @NotNull Raider raider) {
        super(bell);
        this.raider = raider;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取被钟显形的袭击者。
     * <p>原文：
     * Gets the raider that the bell revealed.
     *
     * @return 袭击者
     * <p>原文：The raider
     */
    @NotNull
    public Raider getEntity() {
        return this.raider;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This does not cancel the particle effects shown on the bell, only the entity.
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
