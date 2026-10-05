package org.bukkit.packs;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个资源包.
 * <p>原文：
 * Represents a resource pack.
 *
 * @see <a href="https://minecraft.wiki/w/Resource_pack">Minecraft wiki</a>
 */
public interface ResourcePack {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取资源包的ID.
     * <p>原文：
     * Gets the id of the resource pack.
     *
     * @return the id
     */
    @NotNull
    public UUID getId();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取资源包的URL.
     * <p>原文：
     * Gets the url of the resource pack.
     *
     * @return the url
     */
    @NotNull
    public String getUrl();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取资源包的哈希值.
     * <p>原文：
     * Gets the hash of the resource pack.
     *
     * @return 哈希值
     * <p>原文：the hash
     */
    @Nullable
    public String getHash();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取资源包显示的提示信息.
     * <p>原文：
     * Gets the prompt to show of the resource pack.
     *
     * @return 提示信息
     * <p>原文：the prompt
     */
    @Nullable
    public String getPrompt();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取资源包是否被服务器要求.
     * <p>原文：
     * Gets if the resource pack is required by the server.
     *
     * @return 如果是必需的则为True
     * <p>原文：True if is required
     */
    public boolean isRequired();
}
