package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.registry.set.RegistryKeySet;
import java.util.Collection;
import java.util.List;
import net.kyori.adventure.util.TriState;
import org.bukkit.block.BlockType;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 控制物品作为工具时的行为。
 * <p>原文：
 * Controls the behavior of the item as a tool.
 * @see DataComponentTypes#TOOL
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface Tool {

    @Contract(value = "-> new", pure = true)
    static Tool.Builder tool() {
        return ItemComponentTypesBridge.bridge().tool();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建挖掘规则，指定物品如何与某些方块类型交互。
     * <p>
     * 该方法允许你为一组方块类型定义规则，可选设置自定义挖掘速度，并决定挖掘这些方块时物品是否视为适用工具以正确掉落。
     * </p>
     * <p>原文：
     * Creates a mining rule that specifies how an item interacts with certain block types.
     *
     * <p>This method allows you to define a rule for a set of block types, optionally setting a custom mining speed
     * and determining whether the item should correct for drops when mining these blocks.</p>
     *
     * @param blocks 此规则适用的方块类型集合。
     * <p>原文：The set of block types this rule applies to.
     * @param speed 这些方块的自定义挖掘速度倍率。如果为 {@code null}，则使用默认速度。
     * <p>原文：The custom mining speed multiplier for these blocks. If {@code null}, the default speed is used.
     * @param correctForDrops 一个 {@link TriState}，指示如何处理物品掉落：<ul> <li>{@link TriState#TRUE} - 物品将会掉落。</li> <li>{@link TriState#FALSE} - 物品不会掉落。</li> <li>{@link TriState#NOT_SET} - 使用默认掉落行为。</li> </ul>
     * <p>原文：A {@link TriState} indicating how to handle item drops:
     *                        <ul>
     *                          <li>{@link TriState#TRUE} - Items will be dropped.</li>
     *                          <li>{@link TriState#FALSE} - Items will not be dropped.</li>
     *                          <li>{@link TriState#NOT_SET} - The default drop behavior is used.</li>
     *                        </ul>
     * @return 表示该挖掘规则的新 {@link Rule} 实例。
     * <p>原文：A new {@link Rule} instance representing the mining rule.
     */
    static Rule rule(final RegistryKeySet<BlockType> blocks, final @Nullable Float speed, final TriState correctForDrops) {
        return ItemComponentTypesBridge.bridge().rule(blocks, speed, correctForDrops);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在没有规则匹配且未覆盖挖掘速度时使用的挖掘速度。
     * <p>原文：
     * Mining speed to use if no rules match and don't override mining speed.
     *
     * @return 默认挖掘速度
     * <p>原文：default mining speed
     */
    @Contract(pure = true)
    float defaultMiningSpeed();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 每次使用此工具挖掘方块时扣除的耐久度。
     * <p>原文：
     * Amount of durability to remove each time a block is mined with this tool.
     *
     * @return 耐久度
     * <p>原文：durability
     */
    @Contract(pure = true)
    @NonNegative int damagePerBlock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 规则条目列表。
     * <p>原文：
     * List of rule entries.
     *
     * @return 规则
     * <p>原文：rules
     */
    @Contract(pure = true)
    @Unmodifiable List<Tool.Rule> rules();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此工具能否在创造模式下破坏方块。
     * <p>原文：
     * Whether this tool can destroy blocks in creative mode.
     *
     * @return 此工具能否在创造模式下破坏方块
     * <p>原文：whether this tool can destroy blocks in creative mode
     */
    @Contract(pure = true)
    boolean canDestroyBlocksInCreative();

    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Rule {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 要匹配的方块。
         * <p>原文：
         * Blocks to match.
         *
         * @return 方块
         * <p>原文：blocks
         */
        RegistryKeySet<BlockType> blocks();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 如果存在且匹配，则覆盖挖掘速度。
         * <p>原文：
         * Overrides the mining speed if present and matched.
         *
         * @return 速度覆盖
         * <p>原文：speed override
         */
        @Nullable Float speed();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 如果存在且匹配，则覆盖此工具是否被视为“适用”工具。
         * <p>
         * {@code true} 将使方块以最高效率的速度被挖掘，并在目标方块有此要求时掉落物品。
         * <p>原文：
         * Overrides whether this tool is considered 'correct' if present and matched.
         * <p>
         * {@code true} will cause the block to mine at its most efficient speed, and drop items if the targeted block requires that.
         *
         * @return 三态值
         * <p>原文：a tri-state
         */
        TriState correctForDrops();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link Tool} 的构建器。
     * <p>原文：
     * Builder for {@link Tool}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<Tool> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 控制每次使用此工具挖掘方块时扣除的耐久度。
         * <p>原文：
         * Controls the amount of durability to remove each time a block is mined with this tool.
         *
         * @param damage 要移除的耐久度
         * <p>原文：durability to remove
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #damagePerBlock()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder damagePerBlock(@NonNegative int damage);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 控制在没有规则匹配且未覆盖挖掘速度时使用的挖掘速度。
         * <p>原文：
         * Controls mining speed to use if no rules match and don't override mining speed.
         *
         * @param miningSpeed 挖掘速度
         * <p>原文：mining speed
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #defaultMiningSpeed()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder defaultMiningSpeed(float miningSpeed);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向工具添加一条规则，匹配时控制对该方块的破坏速度/每个方块的耐久消耗。
         * <p>原文：
         * Adds a rule to the tool that controls the breaking speed / damage per block if matched.
         *
         * @param rule 规则
         * <p>原文：rule
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #rules()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addRule(Rule rule);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 控制此工具能否在创造模式下破坏方块。
         * <p>原文：
         * Controls whether this tool can destroy blocks in creative mode.
         *
         * @param canDestroyBlocksInCreative 此工具能否在创造模式下破坏方块
         * <p>原文：whether this tool can destroy blocks in creative mode
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #canDestroyBlocksInCreative()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder canDestroyBlocksInCreative(boolean canDestroyBlocksInCreative);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向工具添加多条规则，匹配时控制对该方块的破坏速度/每个方块的耐久消耗。
         * <p>原文：
         * Adds rules to the tool that control the breaking speed / damage per block if matched.
         *
         * @param rules 规则
         * <p>原文：rules
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #rules()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addRules(Collection<Rule> rules);
    }
}
