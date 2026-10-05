package org.bukkit.event.player;

import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家对通过 {@link Player#setResourcePack(java.lang.String)} 发起的资源包请求采取动作时触发本事件.
 * <p>原文：
 * Called when a player takes action on a resource pack request.
 *
 * @see Player#setResourcePack(String, String)
 * @see Player#setResourcePack(String, String, boolean)
 */
public class PlayerResourcePackStatusEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final UUID id;
    private final Status status;

    @ApiStatus.Internal
    public PlayerResourcePackStatusEvent(@NotNull final Player player, @NotNull UUID id, @NotNull Status resourcePackStatus) {
        super(player);
        this.id = id;
        this.status = resourcePackStatus;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取资源包的唯一ID.
     * <p>原文：
     * Gets the unique ID of this pack.
     *
     * @return 资源包的唯一ID
     * <p>原文：unique resource pack ID.
     */
    @NotNull
    public UUID getID() {
        return this.id;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个资源包的状态.
     * <p>原文：
     * Gets the status of this pack.
     *
     * @return 当前的状态
     * <p>原文：the current status
     */
    @NotNull
    public Status getStatus() {
        return this.status;
    }

    /**
     * @deprecated This is no longer sent from the client and will always be null
     */
    @Deprecated(forRemoval = true)
    @Contract("-> null")
    public String getHash() {
        return null;
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

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 资源包状态
     * <p>原文：
     * Status of the resource pack.
     */
    public enum Status {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 资源包成功地下载并应用到了客户端.
         * <p>原文：
         * The resource pack has been successfully downloaded and applied to the
         * client.
         */
        SUCCESSFULLY_LOADED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 客户端拒绝接受资源包
         * <p>原文：
         * The client refused to accept the resource pack.
         */
        DECLINED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 客户端接受了资源包, 但下载失败
         * <p>原文：
         * The client accepted the pack, but download failed.
         */
        FAILED_DOWNLOAD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 客户端接受了资源包, 并开始下载
         * <p>原文：
         * The client accepted the pack and is beginning a download of it.
         */
        ACCEPTED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 客户端成功下载了资源包.
         * <p>原文：
         * The client successfully downloaded the pack.
         */
        DOWNLOADED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 资源包的 URL 无效.
         * <p>原文：
         * The pack URL was invalid.
         */
        INVALID_URL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 客户端无法重新加载资源包.
         * <p>原文：
         * The client was unable to reload the pack.
         */
        FAILED_RELOAD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 资源包被客户端丢弃.
         * <p>原文：
         * The pack was discarded by the client.
         */
        DISCARDED
    }
}
