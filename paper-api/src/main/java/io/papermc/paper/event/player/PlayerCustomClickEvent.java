package io.papermc.paper.event.player;

import io.papermc.paper.connection.PlayerCommonConnection;
import io.papermc.paper.dialog.DialogResponseView;
import io.papermc.paper.registry.data.dialog.action.DialogActionCallback;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.api.BinaryTagHolder;
import net.kyori.adventure.text.event.ClickCallback;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 此事件对所有自定义点击事件触发。
 * <p>原文：
 * This event is fired for any custom click events.
 *
 * @see net.kyori.adventure.text.event.ClickEvent#custom(Key, BinaryTagHolder)
 * @see io.papermc.paper.registry.data.dialog.action.DialogAction#customClick(DialogActionCallback, ClickCallback.Options)
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
@NullMarked
public abstract class PlayerCustomClickEvent extends Event {

    private final Key identifier;
    private final PlayerCommonConnection commonConnection;

    @ApiStatus.Internal
    protected PlayerCustomClickEvent(final Key identifier, final PlayerCommonConnection commonConnection) {
        this.identifier = identifier;
        this.commonConnection = commonConnection;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 自定义点击事件的标识符。
     * <p>原文：
     * The identifier of the custom click event.
     *
     * @return 标识符
     * <p>原文：the identifier
     */
    public final Key getIdentifier() {
        return this.identifier;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 自定义点击事件的标签载荷。
     * <p>原文：
     * The tag payload of the custom click event.
     *
     * @return 标签（如果有）
     * <p>原文：the tag (if any)
     */
    public abstract @Nullable BinaryTagHolder getTag();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 自定义点击事件的对话框响应视图。
     * <p>原文：
     * The dialog response view of the custom click event.
     *
     * @return 对话框响应视图
     * <p>原文：the dialog response view
     */
    public abstract @Nullable DialogResponseView getDialogResponseView();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 玩家的通用连接。
     * <p>原文：
     * The common connection of the player.
     *
     * @return 通用连接
     * <p>原文：the common connection
     */
    public final PlayerCommonConnection getCommonConnection() {
        return this.commonConnection;
    }

    @Override
    public HandlerList getHandlers() {
        // this will be how handler lists will work on interfaces
        return PlayerCustomClickEvent.getHandlerList();
    }

    public static HandlerList getHandlerList() {
        final class Holder {
            private static final HandlerList HANDLER_LIST = new HandlerList();
        }
        return Holder.HANDLER_LIST;
    }
}
