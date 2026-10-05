package com.destroystokyo.paper.event.player;

import com.google.common.base.Preconditions;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 此事件在玩家握手期间触发。
 * <p>
 * 如果没有监听此事件的监听器，将运行服务器平台的默认逻辑。
 * <p>
 * 警告：篡改此事件可能很危险
 * </p>
 * <p>原文：
 * This event is fired during a player handshake.
 * <p>
 * If there are no listeners listening to this event, the logic default
 * to your server platform will be run.
 *
 * <p>WARNING: TAMPERING WITH THIS EVENT CAN BE DANGEROUS</p>
 */
@NullMarked
public class PlayerHandshakeEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final String originalHandshake;
    private final String originalSocketAddressHostname;
    private @Nullable String serverHostname;
    private @Nullable String socketAddressHostname;
    private @Nullable UUID uniqueId;
    private @Nullable String propertiesJson;
    private boolean failed;
    private Component failMessage = Component.text("If you wish to use IP forwarding, please enable it in your BungeeCord config as well!", NamedTextColor.YELLOW);

    private boolean cancelled;

    @Deprecated
    @ApiStatus.Internal
    public PlayerHandshakeEvent(final String originalHandshake, final boolean cancelled) {
        this(originalHandshake, "127.0.0.1", cancelled);
    }

    @ApiStatus.Internal
    public PlayerHandshakeEvent(final String originalHandshake, final String originalSocketAddressHostname, final boolean cancelled) {
        super(true);
        this.originalHandshake = originalHandshake;
        this.originalSocketAddressHostname = originalSocketAddressHostname;
        this.cancelled = cancelled;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取原始的握手字符串。
     * <p>原文：
     * Gets the original handshake string.
     *
     * @return 原始握手字符串
     * <p>原文：the original handshake string
     */
    public String getOriginalHandshake() {
        return this.originalHandshake;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取原始套接字地址的主机名。
     * <p>
     * 不包含端口。
     * </p>
     * <p>
     * 如果该事件是手动触发的且插件尚未更新，默认值为 {@code "127.0.0.1"}。
     * </p>
     * <p>原文：
     * Gets the original socket address hostname.
     *
     * <p>This does not include the port.</p>
     * <p>In cases where this event is manually fired and the plugin wasn't updated yet, the default is {@code "127.0.0.1"}.</p>
     *
     * @return 原始套接字地址主机名
     * <p>原文：the original socket address hostname
     */
    public String getOriginalSocketAddressHostname() {
        return this.originalSocketAddressHostname;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取服务器主机名字符串。
     * <p>
     * 不应包含端口。
     * </p>
     * <p>原文：
     * Gets the server hostname string.
     *
     * <p>This should not include the port.</p>
     *
     * @return 服务器主机名字符串
     * <p>原文：the server hostname string
     */
    public @Nullable String getServerHostname() {
        return this.serverHostname;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置服务器主机名字符串。
     * <p>
     * 不应包含端口。
     * </p>
     * <p>原文：
     * Sets the server hostname string.
     *
     * <p>This should not include the port.</p>
     *
     * @param serverHostname 服务器主机名字符串
     * <p>原文：the server hostname string
     */
    public void setServerHostname(final String serverHostname) {
        this.serverHostname = serverHostname;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取套接字地址主机名字符串。
     * <p>
     * 不应包含端口。
     * </p>
     * <p>原文：
     * Gets the socket address hostname string.
     *
     * <p>This should not include the port.</p>
     *
     * @return 套接字地址主机名字符串
     * <p>原文：the socket address hostname string
     */
    public @Nullable String getSocketAddressHostname() {
        return this.socketAddressHostname;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置套接字地址主机名字符串。
     * <p>
     * 不应包含端口。
     * </p>
     * <p>原文：
     * Sets the socket address hostname string.
     *
     * <p>This should not include the port.</p>
     *
     * @param socketAddressHostname 套接字地址主机名字符串
     * <p>原文：the socket address hostname string
     */
    public void setSocketAddressHostname(final String socketAddressHostname) {
        this.socketAddressHostname = socketAddressHostname;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取唯一 ID。
     * <p>原文：
     * Gets the unique id.
     *
     * @return 唯一 ID
     * <p>原文：the unique id
     */
    public @Nullable UUID getUniqueId() {
        return this.uniqueId;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置唯一 ID。
     * <p>原文：
     * Sets the unique id.
     *
     * @param uniqueId 唯一 ID
     * <p>原文：the unique id
     */
    public void setUniqueId(final UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取档案属性。
     * <p>
     * 应为有效的 JSON 字符串。
     * </p>
     * <p>原文：
     * Gets the profile properties.
     *
     * <p>This should be a valid JSON string.</p>
     *
     * @return 档案属性（JSON 格式）
     * <p>原文：the profile properties, as JSON
     */
    public @Nullable String getPropertiesJson() {
        return this.propertiesJson;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 判断验证是否失败。
     * <p>
     * 当为 {@code true} 时，正在连接的客户端将以 {@link #getFailMessage() fail message} 断开连接。
     * <p>原文：
     * Determines if authentication failed.
     * <p>
     * When {@code true}, the client connecting will be disconnected
     * with the {@link #getFailMessage() fail message}.
     *
     * @return 如果验证失败则为 {@code true}，否则为 {@code false}
     * <p>原文：{@code true} if authentication failed, {@code false} otherwise
     */
    public boolean isFailed() {
        return this.failed;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置验证是否失败以及是否应断开客户端连接。
     * <p>
     * 当为 {@code true} 时，正在连接的客户端将以 {@link #getFailMessage() fail message} 断开连接。
     * <p>原文：
     * Sets if authentication failed and the client should be disconnected.
     * <p>
     * When {@code true}, the client connecting will be disconnected
     * with the {@link #getFailMessage() fail message}.
     *
     * @param failed 如果验证失败则为 {@code true}，否则为 {@code false}
     * <p>原文：{@code true} if authentication failed, {@code false} otherwise
     */
    public void setFailed(final boolean failed) {
        this.failed = failed;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置档案属性。
     * <p>
     * 应为有效的 JSON 字符串。
     * </p>
     * <p>原文：
     * Sets the profile properties.
     *
     * <p>This should be a valid JSON string.</p>
     *
     * @param propertiesJson 档案属性（JSON 格式）
     * <p>原文：the profile properties, as JSON
     */
    public void setPropertiesJson(final String propertiesJson) {
        this.propertiesJson = propertiesJson;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取验证失败时显示给客户端的消息。
     * <p>原文：
     * Gets the message to display to the client when authentication fails.
     *
     * @return 显示给客户端的消息
     * <p>原文：the message to display to the client
     */
    public Component failMessage() {
        return this.failMessage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置验证失败时显示给客户端的消息。
     * <p>原文：
     * Sets the message to display to the client when authentication fails.
     *
     * @param failMessage 显示给客户端的消息
     * <p>原文：the message to display to the client
     */
    public void failMessage(final Component failMessage) {
        this.failMessage = failMessage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取验证失败时显示给客户端的消息。
     * <p>原文：
     * Gets the message to display to the client when authentication fails.
     *
     * @return 显示给客户端的消息
     * <p>原文：the message to display to the client
     * @deprecated 请使用 {@link #failMessage()}
     * <p>原文：use {@link #failMessage()}
     */
    @Deprecated
    public String getFailMessage() {
        return LegacyComponentSerializer.legacySection().serialize(this.failMessage());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置验证失败时显示给客户端的消息。
     * <p>原文：
     * Sets the message to display to the client when authentication fails.
     *
     * @param failMessage 显示给客户端的消息
     * <p>原文：the message to display to the client
     * @deprecated 请使用 {@link #failMessage(Component)}
     * <p>原文：use {@link #failMessage(Component)}
     */
    @Deprecated
    public void setFailMessage(final String failMessage) {
        Preconditions.checkArgument(failMessage != null && !failMessage.isEmpty(), "fail message cannot be null or empty");
        this.failMessage(LegacyComponentSerializer.legacySection().deserialize(failMessage));
    }

    /**
     * {@inheritDoc}
     * <p>
     * When this event is cancelled, custom handshake logic will not
     * be processed.
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * {@inheritDoc}
     * <p>
     * When this event is cancelled, custom handshake logic will not
     * be processed.
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
}
