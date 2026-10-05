package io.papermc.paper.text;

import java.io.IOException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.flattener.ComponentFlattener;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * Paper API 特有的方法，用于处理 {@link Component} 及相关内容。
 * <p>原文：
 * Paper API-specific methods for working with {@link Component}s and related.
 */
@NullMarked
public final class PaperComponents {

    private PaperComponents() {
        throw new RuntimeException("PaperComponents is not to be instantiated!");
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用特定的命令发送者和主体解析组件。
     * <p>
     * 注意，在原版中，在各种组件类型中使用“@”选择器通常需要提升的权限，但该方法不应检查发送者的此类权限。
     * <p>
     * 解析以下内容需要 {@link CommandSender} 参数：
     * <ul>
     * <li>{@link net.kyori.adventure.text.NBTComponent}</li>
     * <li>{@link net.kyori.adventure.text.ScoreComponent}</li>
     * <li>{@link net.kyori.adventure.text.SelectorComponent}</li>
     * </ul>
     * {@link Entity} 参数为可选，可帮助解析：
     * <ul>
     * <li>{@link net.kyori.adventure.text.ScoreComponent}</li>
     * </ul>
     * {@link net.kyori.adventure.text.TranslatableComponent} 不需要任何额外参数。
     * <p>原文：
     * Resolves a component with a specific command sender and subject.
     * <p>
     * Note that in Vanilla, elevated permissions are usually required to use
     * '@' selectors in various component types, but this method should not
     * check such permissions from the sender.
     * <p>
     * A {@link CommandSender} argument is required to resolve:
     * <ul>
     *     <li>{@link net.kyori.adventure.text.NBTComponent}</li>
     *     <li>{@link net.kyori.adventure.text.ScoreComponent}</li>
     *     <li>{@link net.kyori.adventure.text.SelectorComponent}</li>
     * </ul>
     * A {@link Entity} argument is optional to help resolve:
     * <ul>
     *     <li>{@link net.kyori.adventure.text.ScoreComponent}</li>
     * </ul>
     * {@link net.kyori.adventure.text.TranslatableComponent}s don't require any extra arguments.
     *
     * @param input 要解析的组件
     * <p>原文：the component to resolve
     * @param context 用于解析的命令发送者
     * <p>原文：the command sender to resolve with
     * @param scoreboardSubject 要使用的记分板主体（用于 {@link net.kyori.adventure.text.ScoreComponent}）
     * <p>原文：the scoreboard subject to use (for use with {@link net.kyori.adventure.text.ScoreComponent}s)
     * @return 解析后的组件
     * <p>原文：the resolved component
     * @throws IOException 如果解析时触发了语法错误
     * <p>原文：if a syntax error tripped during resolving
     */
    public static Component resolveWithContext(final Component input, final @Nullable CommandSender context, final @Nullable Entity scoreboardSubject) throws IOException {
        return resolveWithContext(input, context, scoreboardSubject, true);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用特定的命令发送者和主体解析组件。
     * <p>
     * 注意，在原版中，在各种组件类型中使用“@”选择器需要提升的权限。如果布尔参数 {@code bypassPermissions} 为 {@code false}，将使用 {@link CommandSender} 参数查询这些权限。
     * <p>
     * 解析以下内容需要 {@link CommandSender} 参数：
     * <ul>
     * <li>{@link net.kyori.adventure.text.NBTComponent}</li>
     * <li>{@link net.kyori.adventure.text.ScoreComponent}</li>
     * <li>{@link net.kyori.adventure.text.SelectorComponent}</li>
     * </ul>
     * {@link Entity} 参数为可选，可帮助解析：
     * <ul>
     * <li>{@link net.kyori.adventure.text.ScoreComponent}</li>
     * </ul>
     * {@link net.kyori.adventure.text.TranslatableComponent} 不需要任何额外参数。
     * <p>原文：
     * Resolves a component with a specific command sender and subject.
     * <p>
     * Note that in Vanilla, elevated permissions are required to use
     * '@' selectors in various component types. If the boolean {@code bypassPermissions}
     * argument is {@code false}, the {@link CommandSender} argument will be used to query
     * those permissions.
     * <p>
     * A {@link CommandSender} argument is required to resolve:
     * <ul>
     *     <li>{@link net.kyori.adventure.text.NBTComponent}</li>
     *     <li>{@link net.kyori.adventure.text.ScoreComponent}</li>
     *     <li>{@link net.kyori.adventure.text.SelectorComponent}</li>
     * </ul>
     * A {@link Entity} argument is optional to help resolve:
     * <ul>
     *     <li>{@link net.kyori.adventure.text.ScoreComponent}</li>
     * </ul>
     * {@link net.kyori.adventure.text.TranslatableComponent}s don't require any extra arguments.
     *
     * @param input 要解析的组件
     * <p>原文：the component to resolve
     * @param context 用于解析的命令发送者
     * <p>原文：the command sender to resolve with
     * @param scoreboardSubject 要使用的记分板主体（用于 {@link net.kyori.adventure.text.ScoreComponent}）
     * <p>原文：the scoreboard subject to use (for use with {@link net.kyori.adventure.text.ScoreComponent}s)
     * @param bypassPermissions true 表示解析组件时绕过权限检查
     * <p>原文：true to bypass permissions checks for resolving components
     * @return 解析后的组件
     * <p>原文：the resolved component
     * @throws IOException 如果解析时触发了语法错误
     * <p>原文：if a syntax error tripped during resolving
     */
    @SuppressWarnings("deprecation") // using unsafe as a bridge
    public static Component resolveWithContext(final Component input, final @Nullable CommandSender context, final @Nullable Entity scoreboardSubject, final boolean bypassPermissions) throws IOException {
        return Bukkit.getUnsafe().resolveWithContext(input, context, scoreboardSubject, bypassPermissions);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回可利用游戏数据解析组件额外信息的组件展平器。
     * <p>原文：
     * Return a component flattener that can use game data to resolve extra information about components.
     *
     * @return 组件展平器
     * <p>原文：a component flattener
     */
    @SuppressWarnings("deprecation") // using unsafe as a bridge
    public static ComponentFlattener flattener() {
        return Bukkit.getUnsafe().componentFlattener();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将组件转换为纯文本字符串的 {@link Component} 序列化器。
     * <p>
     * 实现可以提供能够处理需要访问实现细节的信息的序列化器。
     * </p>
     * <p>原文：
     * Get a serializer for {@link Component}s that will convert components to
     * a plain-text string.
     *
     * <p>Implementations may provide a serializer capable of processing any
     * information that requires access to implementation details.</p>
     *
     * @return 纯文本序列化器
     * <p>原文：a serializer to plain text
     * @deprecated 将在 adventure 5.0.0 中移除，请使用 {@link PlainTextComponentSerializer#plainText()}
     * <p>原文：will be removed in adventure 5.0.0, use {@link PlainTextComponentSerializer#plainText()}
     */
    @Deprecated(forRemoval = true, since = "1.18.1")
    public static PlainComponentSerializer plainSerializer() {
        return Bukkit.getUnsafe().plainComponentSerializer();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将组件转换为纯文本字符串的 {@link Component} 序列化器。
     * <p>
     * 实现可以提供能够处理需要访问实现细节的信息的序列化器。
     * </p>
     * <p>原文：
     * Get a serializer for {@link Component}s that will convert components to
     * a plain-text string.
     *
     * <p>Implementations may provide a serializer capable of processing any
     * information that requires access to implementation details.</p>
     *
     * @return 纯文本序列化器
     * <p>原文：a serializer to plain text
     * @deprecated 请使用 {@link PlainTextComponentSerializer#plainText()}
     * <p>原文：use {@link PlainTextComponentSerializer#plainText()}
     */
    @Deprecated(forRemoval = true, since = "1.18.2")
    public static PlainTextComponentSerializer plainTextSerializer() {
        return Bukkit.getUnsafe().plainTextSerializer();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取使用 Gson 在标准 JSON 序列化格式与 {@link Component} 之间相互转换的序列化器。
     * <p>
     * 实现可以提供能够处理需要实现细节的信息（例如旧版（1.16 之前）悬停事件）的序列化器。
     * </p>
     * <p>原文：
     * Get a serializer for {@link Component}s that will convert to and from the
     * standard JSON serialization format using Gson.
     *
     * <p>Implementations may provide a serializer capable of processing any
     * information that requires implementation details, such as legacy
     * (pre-1.16) hover events.</p>
     *
     * @return JSON 组件序列化器
     * <p>原文：a json component serializer
     * @deprecated 请使用 {@link GsonComponentSerializer#gson()}
     * <p>原文：use {@link GsonComponentSerializer#gson()}
     */
    @Deprecated(forRemoval = true, since = "1.18.2")
    public static GsonComponentSerializer gsonSerializer() {
        return Bukkit.getUnsafe().gsonComponentSerializer();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取使用 Gson 在标准 JSON 序列化格式与 {@link Component} 之间相互转换的序列化器，并将所有 RGB 颜色降级为最接近的 {@link NamedTextColor}。
     * <p>
     * 实现可以提供能够处理需要实现细节的信息（例如旧版（1.16 之前）悬停事件）的序列化器。
     * </p>
     * <p>原文：
     * Get a serializer for {@link Component}s that will convert to and from the
     * standard JSON serialization format using Gson, downsampling any RGB colors
     * to their nearest {@link NamedTextColor} counterpart.
     *
     * <p>Implementations may provide a serializer capable of processing any
     * information that requires implementation details, such as legacy
     * (pre-1.16) hover events.</p>
     *
     * @return JSON 组件序列化器
     * <p>原文：a json component serializer
     * @deprecated 请使用 {@link GsonComponentSerializer#colorDownsamplingGson()}
     * <p>原文：use {@link GsonComponentSerializer#colorDownsamplingGson()}
     */
    @Deprecated(forRemoval = true, since = "1.18.2")
    public static GsonComponentSerializer colorDownsamplingGsonSerializer() {
        return Bukkit.getUnsafe().colorDownsamplingGsonComponentSerializer();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取在 Bukkit 使用的旧版组件格式与 {@link Component} 之间相互转换的序列化器。该序列化器使用 {@link LegacyComponentSerializer.Builder#useUnusualXRepeatedCharacterHexFormat()} 选项以匹配上游行为。
     * <p>
     * 该旧版序列化器使用标准的分节符（§）标记格式字符。
     * </p>
     * <p>
     * 实现可以提供能够处理需要访问实现细节的信息的序列化器。
     * </p>
     * <p>原文：
     * Get a serializer for {@link Component}s that will convert to and from the
     * legacy component format used by Bukkit. This serializer uses the
     * {@link LegacyComponentSerializer.Builder#useUnusualXRepeatedCharacterHexFormat()}
     * option to match upstream behavior.
     *
     * <p>This legacy serializer uses the standard section symbol to mark
     * formatting characters.</p>
     *
     * <p>Implementations may provide a serializer capable of processing any
     * information that requires access to implementation details.</p>
     *
     * @return 分节符序列化器
     * <p>原文：a section serializer
     * @deprecated 请使用 {@link LegacyComponentSerializer#legacySection()}
     * <p>原文：use {@link LegacyComponentSerializer#legacySection()}
     */
    @Deprecated(forRemoval = true, since = "1.18.2")
    public static LegacyComponentSerializer legacySectionSerializer() {
        return Bukkit.getUnsafe().legacyComponentSerializer();
    }
}
