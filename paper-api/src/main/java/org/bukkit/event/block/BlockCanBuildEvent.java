package org.bukkit.event.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当我们尝试建造一个方块的时候，可以检测我们是否可以在此建造它。
 * <p>
 * 注意:
 * <ul>
 * <li>getBlock()返回的方块是我们正试图放置在其上面的方块,不是我们试图放置的方块。
 * <li>如果你想知道玩家放置的是什么方块, 用 {@link #getMaterial()}  来代替.
 * </ul>
 * <p>原文：
 * Called when we try to place a block, to see if we can build it here or not.
 * <p>
 * Note:
 * <ul>
 * <li>The Block returned by getBlock() is the block we are trying to place
 *     on, not the block we are trying to place.
 * <li>If you want to figure out what is being placed, use {@link
 *     #getMaterial()} instead.
 * </ul>
 */
public class BlockCanBuildEvent extends BlockEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final org.bukkit.inventory.EquipmentSlot hand;
    protected BlockData blockData;
    protected boolean buildable;

    @Deprecated(since = "1.13.2", forRemoval = true)
    @ApiStatus.Internal
    public BlockCanBuildEvent(@NotNull final Block block, @NotNull final BlockData type, final boolean canBuild) {
        this(block, null, type, canBuild, org.bukkit.inventory.EquipmentSlot.HAND); // Paper - expose hand
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param block 这个事件涉及的方块
     * @param player 正放置此方块的玩家
     * @param type 要放置的方块的类型
     * @param canBuild 是否可以建造
     */
    @Deprecated(forRemoval = true)
    @ApiStatus.Internal
    public BlockCanBuildEvent(@NotNull final Block block, @Nullable final Player player, @NotNull final BlockData type, final boolean canBuild) {
        this(block, player, type, canBuild, org.bukkit.inventory.EquipmentSlot.HAND); // Paper start - expose hand
    }

    @ApiStatus.Internal
    public BlockCanBuildEvent(@NotNull final Block block, @Nullable final Player player, @NotNull final BlockData type, final boolean canBuild, @NotNull final org.bukkit.inventory.EquipmentSlot hand) { // Paper end - expose hand
        super(block);
        this.player = player;
        this.buildable = canBuild;
        this.blockData = type;
        this.hand = hand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得放置这个方块的玩家.
     * <br>
     * 旧版本中这可能为null.
     * <p>原文：
     * Gets the player who placed the block involved in this event.
     * <br>
     * May be {@code null} for legacy calls of the event.
     *
     * @return 放置这个方块的玩家
     * <p>原文：The Player who placed the block involved in this event
     */
    @Nullable
    public Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取我们试图放置的方块的种类.
     * <p>原文：
     * Gets the Material that we are trying to place.
     *
     * @return 我们试图放置的方块的种类
     * <p>原文：The Material that we are trying to place
     */
    @NotNull
    public Material getMaterial() {
        return this.blockData.getMaterial();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取我们想要放置的方块的BlockData数据.
     * <p>原文：
     * Gets the BlockData that we are trying to place.
     *
     * @return 尝试放置的方块的BlockData数据
     * <p>原文：The BlockData that we are trying to place
     */
    @NotNull
    public BlockData getBlockData() {
        return this.blockData.clone();
    }

    /**
     * Gets the hand the player will use to place the block
     *
     * @return the EquipmentSlot representing the players hand.
     */
    @NotNull
    public org.bukkit.inventory.EquipmentSlot getHand() {
        return this.hand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取是否可以在此建造此方块.
     * <p>
     * 默认情况下, 是否可以在此建造返回的是 Minecraft 的值.
     * <p>原文：
     * Gets whether the block can be built here.
     * <p>
     * By default, returns Minecraft's answer on whether the block can be
     * built here or not.
     *
     * @return boolean 此方块是否可以在此建造
     * <p>原文：boolean whether the block can be built
     */
    public boolean isBuildable() {
        return this.buildable;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此方块是否可以在此建造.
     * <p>原文：
     * Sets whether the block can be built here or not.
     *
     * @param cancel 允许建造则为true, 无论 Minecraft 的默认行为是什么
     * <p>原文：{@code true} if you want to allow the block to be built here
     *     despite Minecraft's default behaviour
     */
    public void setBuildable(boolean cancel) {
        this.buildable = cancel;
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
