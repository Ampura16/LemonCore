package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.bukkit.block.BlockType;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有方块物品的 {@link BlockData} 属性。
 * <p>原文：
 * Holds the {@link BlockData} properties of a block item.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#BLOCK_DATA
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface BlockItemDataProperties {

    @Contract(value = "-> new", pure = true)
    static BlockItemDataProperties.Builder blockItemStateProperties() {
        return ItemComponentTypesBridge.bridge().blockItemStateProperties();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为给定的 {@link BlockType} 创建新的 {@link BlockData} 实例。
     * <p>原文：
     * Creates a new {@link BlockData} instance for the given {@link BlockType}.
     *
     * @param blockType 方块类型
     * <p>原文：the block type
     * @return 方块数据
     * <p>原文：the block data
     */
    @Contract(pure = true)
    BlockData createBlockData(BlockType blockType);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将这些属性应用到给定的 {@link BlockData}。不会修改参数，而是返回应用了属性的新实例。
     * <p>原文：
     * Applies the properties to the given {@link BlockData}. Doesn't
     * mutate the parameter, but returns a new instance with the properties applied.
     *
     * @param blockData 要应用属性的方块数据
     * <p>原文：the block data to apply the properties to
     * @return 应用了属性的方块数据
     * <p>原文：the block data with the properties applied
     */
    @Contract(pure = true)
    BlockData applyTo(BlockData blockData);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link BlockItemDataProperties} 的构建器。
     * <p>原文：
     * Builder for {@link BlockItemDataProperties}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<BlockItemDataProperties> {
        // building this requires BlockProperty API, so an empty builder for now (essentially read-only)
    }
}
