package io.papermc.paper.block;

import org.bukkit.block.BlockFace;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface MovingPiston extends TileState {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取被推动的方块
     * <p>原文：
     * Gets the block that is being pushed
     *
     * @return 被推动的方块
     * <p>原文：the pushed block
     */
    BlockData getMovingBlock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 当前移动中的活塞推/拉方块的方向。
     * <p>原文：
     * The direction that the current moving piston
     * is pushing/pulling a block in.
     *
     * @return 方向
     * <p>原文：the direction
     */
    BlockFace getDirection();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取活塞是否正在伸出。如果活塞正在收回则返回 false。
     * <p>原文：
     * Gets if the piston is extending or not.
     * Returns false if the piston is retracting.
     *
     * @return 是否正在伸出
     * <p>原文：is extending or not
     */
    boolean isExtending();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此移动中的活塞是否表示原活塞的主活塞头。
     * <p>原文：
     * Returns if this moving piston represents the main piston head
     * from the original piston.
     *
     * @return 是否为活塞头
     * <p>原文：is the piston head or not
     */
    boolean isPistonHead();

}
