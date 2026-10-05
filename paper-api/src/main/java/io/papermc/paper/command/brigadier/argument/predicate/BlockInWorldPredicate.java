package io.papermc.paper.command.brigadier.argument.predicate;

import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import org.bukkit.block.Block;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link Block} 的谓词。
 * <p>原文：
 * A predicate for a {@link Block}.
 *
 * @see ArgumentTypes#blockInWorldPredicate()
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface BlockInWorldPredicate {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查传入的方块是否匹配方块谓词。
     * <p>
     * 该方法会以同步方式加载方块所在的区块。
     * <p>原文：
     * Checks if the passed block matches the block predicate.
     * <p>
     * This method will load the chunk the block is at in a synchronous manner.
     *
     * @param block 要检查的方块实例
     * <p>原文：the block instance to check
     * @return 谓词结果。
     * <p>原文：the predicate result.
     */
    default Result testBlock(final Block block) {
        return this.testBlock(block, true);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查传入的方块是否匹配方块谓词。
     * <p>原文：
     * Checks if the passed block matches the block predicate.
     *
     * @param block 要检查的方块实例
     * <p>原文：the block instance to check
     * @param loadChunk 是否应加载该方块所在的区块。
     * <p>原文：if the chunk the block is located at should be loaded.
     * @return 谓词结果。
     * <p>原文：the predicate result.
     */
    Result testBlock(Block block, boolean loadChunk);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 谓词结果由 {@link BlockInWorldPredicate} 应用于方块时产生。
     * <p>原文：
     * The predicate result is yielded by the {@link BlockInWorldPredicate} when applied to a block.
     *
     * @see #testBlock(Block)
     * @see #testBlock(Block, boolean)
     */
    enum Result {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 传入谓词的方块与谓词匹配。
         * <p>原文：
         * The block passed to the predicate matches the predicate.
         */
        TRUE,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 传入谓词的方块与谓词不匹配。
         * <p>原文：
         * The block passed to the predicate does not match the predicate.
         */
        FALSE,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 传入谓词的方块位于未加载的区块中，且 {@code loadChunk} 标志为 false。
         * <p>原文：
         * The block passed to the predicate was in an unloaded chunk and the {@code loadChunk} flag was false.
         */
        UNLOADED_CHUNK,
        ;

        /**
         * Converts the result into an approximated boolean representation.
         * Specifically, the following mappings are applied:
         * <ul>
         * <li>{@link #TRUE} -> {@code true}</li>
         * <li>{@link #FALSE} -> {@code false}</li>
         * <li>{@link #UNLOADED_CHUNK} -> {@code false}</li>
         * </ul>
         *
         * @return 布尔表示。
         * <p>原文：the boolean representation.
         */
        public boolean asBoolean() {
            return this == TRUE;
        }
    }
}
