package org.bukkit.event.block;

import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 在玩家设置牌子上的内容子时触发.
 * <p>
 * 若取消本事件,牌子将不会被更改.
 * <p>原文：
 * Called when a sign is changed by a player.
 * <p>
 * If this event is cancelled, the sign will not be changed.
 */
public class SignChangeEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final List<Component> adventure$lines;
    private final Side side;

    private boolean cancelled;

    @ApiStatus.Internal
    public SignChangeEvent(@NotNull final Block sign, @NotNull final Player player, @NotNull final java.util.List<net.kyori.adventure.text.Component> adventure$lines, @NotNull Side side) {
        super(sign);
        this.player = player;
        this.adventure$lines = adventure$lines;
        this.side = side;
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public SignChangeEvent(@NotNull final Block sign, @NotNull final Player player, @NotNull final java.util.List<net.kyori.adventure.text.Component> adventure$lines) {
        this(sign, player, adventure$lines, Side.FRONT);
    }

    @ApiStatus.Internal
    @Deprecated(since = "1.19.4", forRemoval = true)
    public SignChangeEvent(@NotNull final Block sign, @NotNull final Player thePlayer, @NotNull final String[] theLines) {
        this(sign, thePlayer, theLines, Side.FRONT);
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public SignChangeEvent(@NotNull final Block sign, @NotNull final Player thePlayer, @NotNull final String[] theLines, @NotNull Side side) {
        super(sign);
        this.player = thePlayer;
        this.adventure$lines = new java.util.ArrayList<>();
        for (String theLine : theLines) {
            this.adventure$lines.add(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(theLine));
        }
        this.side = side;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得设置这个牌子上的字的玩家.
     * <p>原文：
     * Gets the player changing the sign involved in this event.
     *
     * @return 玩家
     * <p>原文：the Player involved in this event
     */
    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets all of the lines of text from the sign involved in this event.
     *
     * @return the String array for the sign's lines new text
     */
    public @NotNull java.util.List<net.kyori.adventure.text.Component> lines() {
        return this.adventure$lines;
    }

    /**
     * Gets a single line of text from the sign involved in this event.
     *
     * @param index index of the line to get
     * @return the String containing the line of text associated with the
     *     provided index
     * @throws IndexOutOfBoundsException thrown when the provided index is {@literal > 3
     *     or < 0}
     */
    public net.kyori.adventure.text.@Nullable Component line(int index) throws IndexOutOfBoundsException {
        return this.adventure$lines.get(index);
    }

    /**
     * Sets a single line for the sign involved in this event
     *
     * @param index index of the line to set
     * @param line text to set
     * @throws IndexOutOfBoundsException thrown when the provided index is {@literal > 3
     *     or < 0}
     */
    public void line(int index, net.kyori.adventure.text.@Nullable Component line) throws IndexOutOfBoundsException {
        this.adventure$lines.set(index, line);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得牌子被写上的全部内容.
     * <p>原文：
     * Gets all of the lines of text from the sign involved in this event.
     *
     * @return 牌子被设置成的内容(长度为4的数组)
     * <p>原文：the String array for the sign's lines new text
     * @deprecated in favour of {@link #lines()}
     */
    @NotNull
    @Deprecated // Paper
    public String[] getLines() {
        return this.adventure$lines.stream().map(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection()::serialize).toArray(String[]::new); // Paper
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得牌子被写上的内容的某行.
     * <p>原文：
     * Gets a single line of text from the sign involved in this event.
     *
     * @param index 要获取的内容的行数,取值0~3
     * <p>原文：index of the line to get
     * @return 你获取的那行
     * <p>原文：the String containing the line of text associated with the
     *     provided index
     * @throws IndexOutOfBoundsException 如果行数 {@literal > 3} 或者 {@literal < 0}
     * <p>原文：thrown when the provided index is {@literal > 3
     *     or < 0}
     * @deprecated in favour of {@link #line(int)}
     */
    @Nullable
    @Deprecated // Paper
    public String getLine(int index) throws IndexOutOfBoundsException {
        return net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().serialize(this.adventure$lines.get(index)); // Paper
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 修改牌子被写上的字的某行.
     * <p>原文：
     * Sets a single line for the sign involved in this event
     *
     * @param index 要设定的内容的行数
     * <p>原文：index of the line to set
     * @param line 要设定的内容
     * <p>原文：text to set
     * @throws IndexOutOfBoundsException 如果行数 {@literal > 3} 或者 {@literal < 0}
     * <p>原文：thrown when the provided index is {@literal > 3
     *     or < 0}
     * @deprecated in favour of {@link #line(int, net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    public void setLine(int index, @Nullable String line) throws IndexOutOfBoundsException {
        this.adventure$lines.set(index, line != null ? net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(line) : null); // Paper
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回被更改的牌子面.
     * <p>原文：
     * Returns which side is changed.
     *
     * @return 牌子受影响的面
     * <p>原文：the affected side of the sign
     */
    @NotNull
    public Side getSide() {
        return this.side;
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
}
