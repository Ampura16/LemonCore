package io.papermc.paper.command.brigadier.argument;

import com.mojang.brigadier.arguments.ArgumentType;
import io.papermc.paper.command.brigadier.argument.predicate.BlockInWorldPredicate;
import io.papermc.paper.command.brigadier.argument.predicate.ItemStackPredicate;
import io.papermc.paper.command.brigadier.argument.range.DoubleRangeProvider;
import io.papermc.paper.command.brigadier.argument.range.IntegerRangeProvider;
import io.papermc.paper.command.brigadier.argument.resolvers.AngleResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.BlockPositionResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.ColumnBlockPositionResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.ColumnFinePositionResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.FinePositionResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.PlayerProfileListResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.RotationResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.EntitySelectorArgumentResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import io.papermc.paper.entity.LookAnchor;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import java.util.Set;
import java.util.UUID;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Axis;
import org.bukkit.GameMode;
import org.bukkit.HeightMap;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.jetbrains.annotations.ApiStatus;

import static io.papermc.paper.command.brigadier.argument.VanillaArgumentProvider.provider;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 原版 Minecraft 包含若干可被客户端识别的自定义 {@link ArgumentType}。其中许多参数类型包含客户端补全和校验，部分还包含命令签名上下文。
 * <p>
 * 该类允许创建这些类型的实例用于插件命令，并提供友好的 API 结果类型。
 * </p>
 * <p>
 * {@link CustomArgumentType} 用于在服务端自定义解析或结果类型，同时向客户端发送原版参数类型。
 * </p>
 * <p>原文：
 * Vanilla Minecraft includes several custom {@link ArgumentType}s that are recognized by the client.
 * Many of these argument types include client-side completions and validation, and some include command signing context.
 *
 * <p>This class allows creating instances of these types for use in plugin commands, with friendly API result types.</p>
 *
 * <p>{@link CustomArgumentType} is provided for customizing parsing or result types server-side, while sending the vanilla argument type to the client.</p>
 */
public final class ArgumentTypes {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示可以捕获任意单个实体的选择器。
     * <p>原文：
     * Represents a selector that can capture any
     * single entity.
     *
     * @return 接受一个实体的参数
     * <p>原文：argument that takes one entity
     */
    public static ArgumentType<EntitySelectorArgumentResolver> entity() {
        return provider().entity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示可以捕获多个实体的选择器。
     * <p>原文：
     * Represents a selector that can capture multiple
     * entities.
     *
     * @return 接受多个实体的参数
     * <p>原文：argument that takes multiple entities
     */
    public static ArgumentType<EntitySelectorArgumentResolver> entities() {
        return provider().entities();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示可以捕获单个玩家实体的选择器。
     * <p>原文：
     * Represents a selector that can capture a
     * singular player entity.
     *
     * @return 接受一个玩家的参数
     * <p>原文：argument that takes one player
     */
    public static ArgumentType<PlayerSelectorArgumentResolver> player() {
        return provider().player();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示可以捕获多个玩家实体的选择器。
     * <p>原文：
     * Represents a selector that can capture multiple
     * player entities.
     *
     * @return 接受多个玩家的参数
     * <p>原文：argument that takes multiple players
     */
    public static ArgumentType<PlayerSelectorArgumentResolver> players() {
        return provider().players();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供玩家档案列表的选择器参数。
     * <p>原文：
     * A selector argument that provides a list
     * of player profiles.
     *
     * @return 玩家档案参数
     * <p>原文：player profile argument
     */
    public static ArgumentType<PlayerProfileListResolver> playerProfiles() {
        return provider().playerProfiles();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 方块坐标参数。
     * <p>原文：
     * A block position argument.
     *
     * @return 方块坐标参数
     * <p>原文：block position argument
     */
    public static ArgumentType<BlockPositionResolver> blockPosition() {
        return provider().blockPosition();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 列方块坐标参数。
     * <p>原文：
     * A column block position argument.
     *
     * @return 列方块坐标参数
     * <p>原文：column block position argument
     */
    @ApiStatus.Experimental
    public static ArgumentType<ColumnBlockPositionResolver> columnBlockPosition() {
        return provider().columnBlockPosition();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 方块谓词参数。
     * <p>原文：
     * A block predicate argument.
     *
     * @return 方块谓词参数
     * <p>原文：block predicate argument
     */
    @ApiStatus.Experimental
    public static ArgumentType<BlockInWorldPredicate> blockInWorldPredicate() {
        return provider().blockInWorldPredicate();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 精确坐标参数。
     * <p>原文：
     * A fine position argument.
     *
     * @return 精确坐标参数
     * <p>原文：fine position argument
     * @see #finePosition(boolean) to center whole numbers
     */
    public static ArgumentType<FinePositionResolver> finePosition() {
        return finePosition(false);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 精确坐标参数。
     * <p>原文：
     * A fine position argument.
     *
     * @param centerIntegers 整数是否应居中（+0.5）
     * <p>原文：if whole numbers should be centered (+0.5)
     * @return 精确坐标参数
     * <p>原文：fine position argument
     */
    public static ArgumentType<FinePositionResolver> finePosition(final boolean centerIntegers) {
        return provider().finePosition(centerIntegers);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 列精确坐标参数。
     * <p>原文：
     * A column fine position argument.
     *
     * @return 列精确坐标参数
     * <p>原文：column fine position argument
     * @see #columnFinePosition(boolean) to center whole numbers
     */
    @ApiStatus.Experimental
    public static ArgumentType<ColumnFinePositionResolver> columnFinePosition() {
        return columnFinePosition(false);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 列精确坐标参数。
     * <p>原文：
     * A column fine position argument.
     *
     * @param centerIntegers 整数是否应居中（+0.5）
     * <p>原文：if whole numbers should be centered (+0.5)
     * @return 列精确坐标参数
     * <p>原文：column fine position argument
     */
    @ApiStatus.Experimental
    public static ArgumentType<ColumnFinePositionResolver> columnFinePosition(final boolean centerIntegers) {
        return provider().columnFinePosition(centerIntegers);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 旋转参数。
     * <p>原文：
     * A rotation argument.
     *
     * @return 旋转参数
     * <p>原文：rotation argument
     */
    public static ArgumentType<RotationResolver> rotation() {
        return provider().rotation();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 角度参数。
     * <p>原文：
     * An angle argument.
     *
     * @return 角度参数
     * <p>原文：angle argument
     */
    @ApiStatus.Experimental
    public static ArgumentType<AngleResolver> angle() {
        return provider().angle();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于解析一组坐标轴的参数。
     * <p>原文：
     * An argument used to resolve a set of axes.
     *
     * @return 坐标轴集合。
     * <p>原文：a set of axes.
     * @see org.bukkit.Axis
     */
    @ApiStatus.Experimental
    public static ArgumentType<AxisSet> axes() {
        return provider().axes();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 方块状态参数，可对指定的具体方块变种以及（如适用）方块实体 NBT 进行丰富的解析。
     * <p>原文：
     * A blockstate argument which will provide rich parsing for specifying
     * the specific block variant and then the block entity NBT if applicable.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<BlockState> blockState() {
        return provider().blockState();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * ItemStack 参数，可对物品材料和物品 NBT 信息进行丰富的解析。
     * <p>原文：
     * An ItemStack argument which provides rich parsing for
     * specifying item material and item NBT information.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<ItemStack> itemStack() {
        return provider().itemStack();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 物品谓词参数。
     * <p>原文：
     * An item predicate argument.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<ItemStackPredicate> itemPredicate() {
        return provider().itemStackPredicate();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于解析 {@link NamedTextColor} 的参数。
     * <p>原文：
     * An argument for parsing {@link NamedTextColor}s.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<NamedTextColor> namedColor() {
        return provider().namedColor();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 十六进制颜色参数。
     * <p>原文：
     * A hex color argument.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<TextColor> hexColor() {
        return provider().hexColor();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 组件参数。
     * <p>原文：
     * A component argument.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<Component> component() {
        return provider().component();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 样式参数。
     * <p>原文：
     * A style argument.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<Style> style() {
        return provider().style();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 签名消息参数。可解析该参数以获取底层的签名消息。
     * <p>原文：
     * A signed message argument.
     * This argument can be resolved to retrieve the underlying
     * signed message.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<SignedMessageResolver> signedMessage() {
        return provider().signedMessage();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 计分板显示位置参数。
     * <p>原文：
     * A scoreboard display slot argument.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<DisplaySlot> scoreboardDisplaySlot() {
        return provider().scoreboardDisplaySlot();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 命名空间键参数。
     * <p>原文：
     * A namespaced key argument.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<NamespacedKey> namespacedKey() {
        return provider().namespacedKey();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 键参数。
     * <p>原文：
     * A key argument.
     *
     * @return 参数
     * <p>原文：argument
     */
    // include both key types as we are slowly moving to use adventure's key
    public static ArgumentType<Key> key() {
        return provider().key();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 整数的闭区间，任一端都可以无界。
     * <p>原文：
     * An inclusive range of integers that may be unbounded on either end.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<IntegerRangeProvider> integerRange() {
        return provider().integerRange();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * double 的闭区间，任一端都可以无界。
     * <p>原文：
     * An inclusive range of doubles that may be unbounded on either end.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<DoubleRangeProvider> doubleRange() {
        return provider().doubleRange();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 世界参数。
     * <p>原文：
     * A world argument.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<World> world() {
        return provider().world();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 游戏模式参数。
     * <p>原文：
     * A game mode argument.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<GameMode> gameMode() {
        return provider().gameMode();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于获取高度图类型的参数。
     * <p>原文：
     * An argument for getting a heightmap type.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<HeightMap> heightMap() {
        return provider().heightMap();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * UUID 参数。
     * <p>原文：
     * A uuid argument.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<UUID> uuid() {
        return provider().uuid();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 记分项准则参数
     * <p>原文：
     * An objective criteria argument
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<Criteria> objectiveCriteria() {
        return provider().objectiveCriteria();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 实体锚点参数。
     * <p>原文：
     * An entity anchor argument.
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<LookAnchor> entityAnchor() {
        return provider().entityAnchor();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 时间参数，返回刻数。
     * <p>
     * 示例：
     * <ul>
     * <li>"1d"
     * <li>"5s"
     * <li>"2"
     * <li>"6t"
     * </ul>
     * <p>原文：
     * A time argument, returning the number of ticks.
     * <p>Examples:
     * <ul>
     * <li> "1d"
     * <li> "5s"
     * <li> "2"
     * <li> "6t"
     * </ul>
     *
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<Integer> time() {
        return time(0);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 时间参数，返回刻数。
     * <p>
     * 示例：
     * <ul>
     * <li>"1d"
     * <li>"5s"
     * <li>"2"
     * <li>"6t"
     * </ul>
     * <p>原文：
     * A time argument, returning the number of ticks.
     * <p>Examples:
     * <ul>
     * <li> "1d"
     * <li> "5s"
     * <li> "2"
     * <li> "6t"
     * </ul>
     *
     * @param mintime 此参数所需的最短时间。
     * <p>原文：The minimum time required for this argument.
     * @return 参数
     * <p>原文：argument
     */
    public static ArgumentType<Integer> time(final int mintime) {
        return provider().time(mintime);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 结构模板镜像参数
     * <p>原文：
     * A template mirror argument
     *
     * @return 参数
     * <p>原文：argument
     * @see Mirror
     */
    public static ArgumentType<Mirror> templateMirror() {
        return provider().templateMirror();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 结构模板旋转参数。
     * <p>原文：
     * A template rotation argument.
     *
     * @return 参数
     * <p>原文：argument
     * @see StructureRotation
     */
    public static ArgumentType<StructureRotation> templateRotation() {
        return provider().templateRotation();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link org.bukkit.Registry} 中资源的参数。
     * <p>原文：
     * An argument for a resource in a {@link org.bukkit.Registry}.
     *
     * @param registryKey 注册表的键
     * <p>原文：the registry's key
     * @return 参数
     * <p>原文：argument
     * @param <T> 注册表值类型
     * <p>原文：the registry value type
     */
    public static <T> ArgumentType<T> resource(final RegistryKey<T> registryKey) {
        return provider().resource(registryKey);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link org.bukkit.Registry} 的类型化键参数。
     * <p>原文：
     * An argument for a typed key for a {@link org.bukkit.Registry}.
     *
     * @param registryKey 注册表的键
     * <p>原文：the registry's key
     * @return 参数
     * <p>原文：argument
     * @param <T> 注册表值类型
     * <p>原文：the registry value type
     * @see RegistryArgumentExtractor#getTypedKey(com.mojang.brigadier.context.CommandContext, RegistryKey, String)
     */
    public static <T> ArgumentType<TypedKey<T>> resourceKey(final RegistryKey<T> registryKey) {
        return provider().resourceKey(registryKey);
    }

    private ArgumentTypes() {
    }
}
