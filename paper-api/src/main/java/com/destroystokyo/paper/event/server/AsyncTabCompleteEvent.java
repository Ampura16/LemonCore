/*
 * Copyright (c) 2017 Daniel Ennis (Aikar) MIT License
 *
 *  Permission is hereby granted, free of charge, to any person obtaining
 *  a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including
 *  without limitation the rights to use, copy, modify, merge, publish,
 *  distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to
 *  the following conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 *  LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 *  OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 *  WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.destroystokyo.paper.event.server;

import com.google.common.base.Preconditions;
import io.papermc.paper.util.TransformingRandomAccessList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import net.kyori.examination.string.StringExaminer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 允许插件异步计算 Tab 补全结果。
 * <p>
 * 如果该事件提供了补全，则不会再触发标准的同步流程来填充结果；但同步的 TabCompleteEvent 仍会携带异步结果触发。
 * <p>
 * 只允许一个流程提供补全：要么是异步事件，要么是标准流程。
 * <p>原文：
 * Allows plugins to compute tab completion results asynchronously.
 * <p>
 * If this event provides completions, then the standard synchronous process
 * will not be fired to populate the results.
 * However, the synchronous TabCompleteEvent will fire with the Async results.
 * <p>
 * Only 1 process will be allowed to provide completions, the Async Event, or the standard process.
 */
@NullMarked
public class AsyncTabCompleteEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final CommandSender sender;
    private final String buffer;
    private final boolean isCommand;
    private final @Nullable Location location;
    private final List<Completion> completions = new ArrayList<>();
    private final List<String> stringCompletions = new TransformingRandomAccessList<>(
        this.completions,
        Completion::suggestion,
        Completion::completion
    );
    private boolean handled;
    private boolean cancelled;

    @ApiStatus.Internal
    public AsyncTabCompleteEvent(final CommandSender sender, final String buffer, final boolean isCommand, final @Nullable Location loc) {
        super(true);
        this.sender = sender;
        this.buffer = buffer;
        this.isCommand = isCommand;
        this.location = loc;
    }

    @Deprecated
    @ApiStatus.Internal
    public AsyncTabCompleteEvent(final CommandSender sender, final List<String> completions, final String buffer, final boolean isCommand, final @Nullable Location loc) {
        super(true);
        this.sender = sender;
        this.completions.addAll(fromStrings(completions));
        this.buffer = buffer;
        this.isCommand = isCommand;
        this.location = loc;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在补全此命令的发送者。
     * <p>原文：
     * Get the sender completing this command.
     *
     * @return {@link CommandSender} 实例
     * <p>原文：the {@link CommandSender} instance
     */
    public CommandSender getSender() {
        return this.sender;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将按顺序提供给发送者的补全列表。该列表是可变的，反映了将要提供的内容。
     * <p>
     * 如果事件触发后该集合不为空，则不会执行调用 {@link Command#tabComplete(CommandSender, String, String[])} 或当前玩家名称的标准流程。
     * <p>原文：
     * The list of completions which will be offered to the sender, in order.
     * This list is mutable and reflects what will be offered.
     * <p>
     * If this collection is not empty after the event is fired, then
     * the standard process of calling {@link Command#tabComplete(CommandSender, String, String[])}
     * or current player names will not be called.
     *
     * @return 提供的补全列表
     * <p>原文：a list of offered completions
     */
    public List<String> getCompletions() {
        return this.stringCompletions;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置提供的补全，覆盖已设置的补全。如果事件触发后该集合不为空，则不会执行调用 {@link Command#tabComplete(CommandSender, String, String[])} 或当前玩家名称的标准流程。
     * <p>
     * 传入的集合将被克隆为新的 {@code List}。此后必须调用 {@link #getCompletions()} 进行修改
     * <p>原文：
     * Set the completions offered, overriding any already set.
     * If this collection is not empty after the event is fired, then
     * the standard process of calling {@link Command#tabComplete(CommandSender, String, String[])}
     * or current player names will not be called.
     * <p>
     * The passed collection will be cloned to a new {@code List}. You must call {@link #getCompletions()} to mutate from here
     *
     * @param completions 新的补全项
     * <p>原文：the new completions
     */
    public void setCompletions(final List<String> completions) {
        Preconditions.checkArgument(completions != null, "Completions list cannot be null");
        if (completions == this.stringCompletions) {
            return;
        }
        this.completions.clear();
        this.completions.addAll(fromStrings(completions));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将按顺序提供给发送者的 {@link Completion completions} 列表。该列表是可变的，反映了将要提供的内容。
     * <p>
     * 如果事件触发后该集合不为空，则不会执行调用 {@link Command#tabComplete(CommandSender, String, String[])} 或当前玩家名称的标准流程。
     * <p>原文：
     * The list of {@link Completion completions} which will be offered to the sender, in order.
     * This list is mutable and reflects what will be offered.
     * <p>
     * If this collection is not empty after the event is fired, then
     * the standard process of calling {@link Command#tabComplete(CommandSender, String, String[])}
     * or current player names will not be called.
     *
     * @return 提供的补全列表
     * <p>原文：a list of offered completions
     */
    public List<Completion> completions() {
        return this.completions;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置提供的 {@link Completion completions}，覆盖已设置的内容。如果事件触发后该集合不为空，则不会执行调用 {@link Command#tabComplete(CommandSender, String, String[])} 或当前玩家名称的标准流程。
     * <p>
     * 传入的集合将被克隆为新的 {@code List}。此后必须调用 {@link #completions()} 进行修改
     * <p>原文：
     * Set the {@link Completion completions} offered, overriding any already set.
     * If this collection is not empty after the event is fired, then
     * the standard process of calling {@link Command#tabComplete(CommandSender, String, String[])}
     * or current player names will not be called.
     * <p>
     * The passed collection will be cloned to a new {@code List}. You must call {@link #completions()} to mutate from here
     *
     * @param newCompletions 新的补全项
     * <p>原文：the new completions
     */
    public void completions(final List<Completion> newCompletions) {
        Preconditions.checkArgument(newCompletions != null, "new completions cannot be null");
        this.completions.clear();
        this.completions.addAll(newCompletions);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回构成此补全基础的整个缓冲区。
     * <p>原文：
     * Return the entire buffer which formed the basis of this completion.
     *
     * @return 输入的命令缓冲区（原样）
     * <p>原文：command buffer, as entered
     */
    public String getBuffer() {
        return this.buffer;
    }

    /**
     * @return 如果是正在进行 Tab 补全的命令则为 {@code true}，如果是聊天消息则为 {@code false}。
     * <p>原文：{@code true} if it is a command being tab completed, {@code false} if it is a chat message.
     */
    public boolean isCommand() {
        return this.isCommand;
    }

    /**
     * @return 发送者注视的位置；如果没有则为 {@code null}
     * <p>原文：The position looked at by the sender, or {@code null} if none
     */
    public @Nullable Location getLocation() {
        return this.location != null ? this.location.clone() : null;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果为 {@code true}，将不会执行调用 {@link Command#tabComplete(CommandSender, String, String[])} 或当前玩家名称的标准流程。
     * <p>原文：
     * If {@code true}, the standard process of calling {@link Command#tabComplete(CommandSender, String, String[])}
     * or current player names will not be called.
     *
     * @return 补全是否被视为已处理。如果补全不为空，则始终为 {@code true}。
     * <p>原文：Is completions considered handled. Always {@code true} if completions is not empty.
     */
    public boolean isHandled() {
        return !this.completions.isEmpty() || this.handled;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否将补全请求视为已处理。如果为 {@code true}，将不会执行调用 {@link Command#tabComplete(CommandSender, String, String[])} 或当前玩家名称的标准流程。
     * <p>原文：
     * Sets whether to consider the completion request handled.
     * If {@code true}, the standard process of calling {@link Command#tabComplete(CommandSender, String, String[])}
     * or current player names will not be called.
     *
     * @param handled 此补全是否应被标记为已处理
     * <p>原文：if this completion should be marked as being handled
     */
    public void setHandled(final boolean handled) {
        this.handled = handled;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * {@inheritDoc}
     * <br>
     * Will provide no completions, and will not fire the synchronous process
     */
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

    private static List<Completion> fromStrings(final List<String> suggestions) {
        final List<Completion> list = new ArrayList<>(suggestions.size());
        for (final String suggestion : suggestions) {
            list.add(new CompletionImpl(suggestion, null));
        }
        return list;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 富 Tab 补全，由字符串建议和可为 null 的 {@link Component} 提示组成。
     * <p>
     * <b>警告：</b>在将来的更新中，该类将不再实现 {@link Examinable}。
     * <p>原文：
     * A rich tab completion, consisting of a string suggestion, and a nullable {@link Component} tooltip.
     * <p>
     * <b>Warning:</b> In a future update, this class will no longer implement {@link Examinable}.
     */
    public interface Completion extends Examinable {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取此 {@link Completion} 的建议字符串。
         * <p>原文：
         * Get the suggestion string for this {@link Completion}.
         *
         * @return 建议字符串
         * <p>原文：suggestion string
         */
        String suggestion();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取此 {@link Completion} 的建议提示。
         * <p>原文：
         * Get the suggestion tooltip for this {@link Completion}.
         *
         * @return 工具提示组件
         * <p>原文：tooltip component
         */
        @Nullable Component tooltip();

        @Override
        @Deprecated(forRemoval = true, since = "1.21.11")
        default Stream<? extends ExaminableProperty> examinableProperties() {
            return Stream.of(ExaminableProperty.of("suggestion", this.suggestion()), ExaminableProperty.of("tooltip", this.tooltip()));
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 从建议字符串创建新的 {@link Completion}。
         * <p>原文：
         * Create a new {@link Completion} from a suggestion string.
         *
         * @param suggestion 建议字符串
         * <p>原文：suggestion string
         * @return 新的补全实例
         * <p>原文：new completion instance
         */
        static Completion completion(final String suggestion) {
            return new CompletionImpl(suggestion, null);
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 从建议字符串和提示 {@link Component} 创建新的 {@link Completion}。
         * <p>
         * 如果提供的组件为 {@code null}，该建议将没有提示。
         * <p>原文：
         * Create a new {@link Completion} from a suggestion string and a tooltip {@link Component}.
         * <p>
         * If the provided component is {@code null}, the suggestion will not have a tooltip.
         *
         * @param suggestion 建议字符串
         * <p>原文：suggestion string
         * @param tooltip 工具提示组件，或 {@code null}
         * <p>原文：tooltip component, or {@code null}
         * @return 新的补全实例
         * <p>原文：new completion instance
         */
        static Completion completion(final String suggestion, final @Nullable Component tooltip) {
            return new CompletionImpl(suggestion, tooltip);
        }
    }

    @ApiStatus.Internal
    static final class CompletionImpl implements Completion {

        private final String suggestion;
        private final @Nullable Component tooltip;

        CompletionImpl(final String suggestion, final @Nullable Component tooltip) {
            this.suggestion = suggestion;
            this.tooltip = tooltip;
        }

        @Override
        public String suggestion() {
            return this.suggestion;
        }

        @Override
        public @Nullable Component tooltip() {
            return this.tooltip;
        }

        @Override
        public boolean equals(final @Nullable Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final CompletionImpl that = (CompletionImpl) o;
            return this.suggestion.equals(that.suggestion)
                && Objects.equals(this.tooltip, that.tooltip);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.suggestion, this.tooltip);
        }

        @Override
        public String toString() {
            return StringExaminer.simpleEscaping().examine(this);
        }
    }
}
