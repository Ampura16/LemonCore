package org.bukkit.event.world;

import java.util.List;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当传送门被创建时调用.
 * <p>原文：
 * Called when a portal is created
 */
public class PortalCreateEvent extends WorldEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final List<BlockState> blocks;
    private final Entity entity;
    private final CreateReason reason;

    private boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(since = "1.14.1", forRemoval = true)
    public PortalCreateEvent(@NotNull final List<BlockState> blocks, @NotNull final World world, @NotNull CreateReason reason) {
        this(blocks, world, null, reason);
    }

    @ApiStatus.Internal
    public PortalCreateEvent(@NotNull final List<BlockState> blocks, @NotNull final World world, @Nullable Entity entity, @NotNull CreateReason reason) {
        super(world);

        this.blocks = blocks;
        this.entity = entity;
        this.reason = reason;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与创建的传送门相关联的所有组成方块的数组列表.
     * <p>原文：
     * Gets an array list of all the blocks associated with the created portal
     *
     * @return 与创建的门相关联的所有方块的数组列表
     * <p>原文：array list of all the blocks associated with the created portal
     */
    @NotNull
    public List<BlockState> getBlocks() {
        return this.blocks;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回导致此传送门创建的实体 (若存在).
     * <p>原文：
     * Returns the Entity that triggered this portal creation (if available)
     *
     * @return 与此事件有关的实体
     * <p>原文：Entity involved in this event
     */
    @Nullable
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个传送门的创建理由.
     * <p>原文：
     * Gets the reason for the portal's creation
     *
     * @return 传送门创建的CreateReason对象
     * <p>原文：CreateReason for the portal's creation
     */
    @NotNull
    public CreateReason getReason() {
        return this.reason;
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
     * 指定传送门创建的各种原因的一个枚举
     * <p>原文：
     * An enum to specify the various reasons for a portal's creation
     */
    public enum CreateReason {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 传送门被火点燃时.
         * <p>原文：
         * When the blocks inside a portal are created due to a portal frame
         * being set on fire.
         */
        FIRE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在地狱创建通往主世界的地狱出口传送门时.
         * <p>原文：
         * When a nether portal frame and portal is created at the exit of an
         * entered nether portal.
         */
        NETHER_PAIR,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家进入末地传送门, 生成落脚平台时.
         * <p>原文：
         * When the target end platform is created as a result of a player
         * entering an end portal.
         */
        END_PLATFORM
    }
}
