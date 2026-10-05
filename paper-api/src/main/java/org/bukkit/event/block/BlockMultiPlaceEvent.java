package org.bukkit.event.block;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家放一个方块, 而连锁放置了第二个方块时(例如玩家放置一个床)触发本事件.
 * <p>
 * 通过 {@link #getBlockPlaced()} 和它的相关方法 返回的方块是:若该位置仅影响一个方块,
 * 则放置的方块将存在的方块.
 * <p>原文：
 * Fired when a single block placement action of a player triggers the
 * creation of multiple blocks(e.g. placing a bed block). The block returned
 * by {@link #getBlockPlaced()} and its related methods is the block where
 * the placed block would exist if the placement only affected a single
 * block.
 */
public class BlockMultiPlaceEvent extends BlockPlaceEvent {

    private final List<BlockState> states;

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public BlockMultiPlaceEvent(@NotNull List<BlockState> states, @NotNull Block clicked, @NotNull ItemStack itemInHand, @NotNull Player thePlayer, boolean canBuild) {
        this(states, clicked, itemInHand, thePlayer, canBuild, org.bukkit.inventory.EquipmentSlot.HAND);
    }

    @ApiStatus.Internal
    public BlockMultiPlaceEvent(@NotNull List<BlockState> states, @NotNull Block clicked, @NotNull ItemStack itemInHand, @NotNull Player thePlayer, boolean canBuild, @NotNull org.bukkit.inventory.EquipmentSlot hand) {
        super(states.get(0).getBlock(), states.get(0), clicked, itemInHand, thePlayer, canBuild, hand);
        this.states = ImmutableList.copyOf(states);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有被替换的旧方块的方块状态列表.
     * <p>
     * 这些方块中的大多数方块状态都是空气.
     * <p>原文：
     * Gets a list of blockstates for all blocks which were replaced by the
     * placement of the new blocks. Most of these blocks will just have a
     * Material type of AIR.
     *
     * @return 一个不能改变的BlockStates列表
     * <p>原文：immutable list of replaced BlockStates
     */
    @NotNull
    public List<BlockState> getReplacedBlockStates() {
        return this.states;
    }
}
