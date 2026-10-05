package io.papermc.paper.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当实体进入方块的碰撞箱时调用。仅对实体在其内部时会作出反应的方块调用。若取消，实体处于该方块中本应导致的任何行为都不会发生（例如炼药锅熄灭实体身上的火）。
 * <p>
 * 当前会调用该事件的方块：
 * <ul>
 * <li>大型垂滴叶</li>
 * <li>气泡柱</li>
 * <li>按钮</li>
 * <li>仙人掌</li>
 * <li>营火</li>
 * <li>炼药锅</li>
 * <li>农作物</li>
 * <li>末地折跃门</li>
 * <li>末地传送门</li>
 * <li>眼眸花</li>
 * <li>火</li>
 * <li>青蛙卵</li>
 * <li>蜂蜜块</li>
 * <li>漏斗</li>
 * <li>探测铁轨</li>
 * <li>下界传送门</li>
 * <li>瓶子草植株</li>
 * <li>细雪</li>
 * <li>压力板</li>
 * <li>甜浆果丛</li>
 * <li>绊线</li>
 * <li>睡莲</li>
 * <li>蜘蛛网</li>
 * <li>凋零玫瑰</li>
 * </ul>
 * <p>原文：
 * Called when an entity enters the hitbox of a block.
 * Only called for blocks that react when an entity is inside.
 * If cancelled, any action that would have resulted from that entity
 * being in the block will not happen (such as extinguishing an entity in a cauldron).
 * <p>
 * Blocks this is currently called for:
 * <ul>
 *     <li>Big dripleaf</li>
 *     <li>Bubble column</li>
 *     <li>Buttons</li>
 *     <li>Cactus</li>
 *     <li>Campfire</li>
 *     <li>Cauldron</li>
 *     <li>Crops</li>
 *     <li>End Gateway</li>
 *     <li>Ender Portal</li>
 *     <li>Eye blossom</li>
 *     <li>Fires</li>
 *     <li>Frogspawn</li>
 *     <li>Honey</li>
 *     <li>Hopper</li>
 *     <li>Detector rails</li>
 *     <li>Nether portals</li>
 *     <li>Pitcher crop</li>
 *     <li>Powdered snow</li>
 *     <li>Pressure plates</li>
 *     <li>Sweet berry bush</li>
 *     <li>Tripwire</li>
 *     <li>Waterlily</li>
 *     <li>Web</li>
 *     <li>Wither rose</li>
 * </ul>
 */
@NullMarked
public class EntityInsideBlockEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block block;
    private boolean cancelled;

    @ApiStatus.Internal
    public EntityInsideBlockEvent(final Entity entity, final Block block) {
        super(entity);
        this.block = block;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取方块。
     * <p>原文：
     * Gets the block.
     *
     * @return 方块
     * <p>原文：the block
     */
    public Block getBlock() {
        return this.block;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

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
