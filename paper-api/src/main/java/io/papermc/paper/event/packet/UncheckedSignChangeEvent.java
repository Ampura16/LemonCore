package io.papermc.paper.event.packet;

import io.papermc.paper.math.BlockPosition;
import io.papermc.paper.math.Position;
import java.util.Collections;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当客户端尝试修改告示牌，但尚未检查应编辑的位置是否真的存在告示牌时调用。
 * <p>
 * 取消该事件将阻止对告示牌修改的后续处理，但由于客户端本地世界可能处于不一致状态，插件还需进一步处理。
 * <p>原文：
 * Called when a client attempts to modify a sign, but the location at which the sign should be edited
 * has not yet been checked for the existence of a real sign.
 * <p>
 * Cancelling this event will prevent further processing of the sign change, but needs further handling
 * by the plugin as the client's local world might be in an inconsistent state.
 *
 * @see Player#openVirtualSign(Position, Side)
 */
@NullMarked
@ApiStatus.Experimental
public class UncheckedSignChangeEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final BlockPosition editedBlockPosition;
    private final Side side;
    private final List<Component> lines;

    private boolean cancelled;

    @ApiStatus.Internal
    public UncheckedSignChangeEvent(
        final Player editor,
        final BlockPosition editedBlockPosition,
        final Side side,
        final List<Component> lines
    ) {
        super(editor);
        this.editedBlockPosition = editedBlockPosition;
        this.side = side;
        this.lines = lines;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取可能被编辑的告示牌所在的位置。
     * <p>原文：
     * Gets the location at which a potential sign was edited.
     *
     * @return 发生变化的位置
     * <p>原文：location where the change happened
     */
    public BlockPosition getEditedBlockPosition() {
        return this.editedBlockPosition;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取告示牌被编辑的是哪一面。
     * <p>原文：
     * Gets which side of the sign was edited.
     *
     * @return 被编辑的 {@link Side}
     * <p>原文：{@link Side} that was edited
     */
    public Side getSide() {
        return this.side;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家输入的文本行。
     * <p>原文：
     * Gets the lines that the player has entered.
     *
     * @return 行
     * <p>原文：the lines
     */
    public @Unmodifiable List<Component> lines() {
        return Collections.unmodifiableList(this.lines);
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

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
}
