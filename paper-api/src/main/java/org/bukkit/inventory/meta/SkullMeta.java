package org.bukkit.inventory.meta;

import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表头颅.
 * <p>原文：
 * Represents a skull that can have an owner.
 */
public interface SkullMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个头颅的主人.
     * <p>原文：
     * Gets the owner of the skull.
     *
     * @return 头颅的主人
     * <p>原文：the owner if the skull
     * @deprecated 另请参阅 {@link #getOwningPlayer()}..
     * <p>原文：see {@link #getOwningPlayer()}.
     */
    @Deprecated(since = "1.12.1")
    @Nullable
    String getOwner();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测这个头颅是否有主人.
     * <p>原文：
     * Checks to see if the skull has an owner.
     *
     * @return true 表示头颅有主人
     * <p>原文：true if the skull has an owner
     */
    boolean hasOwner();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个头颅的主人.
     * <p>原文：
     * Sets the owner of the skull.
     *
     * @param owner 头颅的新主人
     * <p>原文：the new owner of the skull
     * @return 如果头颅主人成功被设置则为 true
     * <p>原文：true if the owner was successfully set
     * @deprecated 另请参阅 {@link #setOwningPlayer(org.bukkit.OfflinePlayer)}.
     * <p>原文：see {@link #setOwningPlayer(org.bukkit.OfflinePlayer)}.
     */
    @Deprecated(since = "1.12.1")
    boolean setOwner(@Nullable String owner);

    // Paper start
    /**
     * Sets this skull to use the supplied Player Profile, which can include textures already prefilled.
     * @param profile The profile to set this Skull to use, or null to clear owner
     */
    void setPlayerProfile(@Nullable com.destroystokyo.paper.profile.PlayerProfile profile);

    /**
     * If the skull has an owner, per {@link #hasOwner()}, return the owners {@link com.destroystokyo.paper.profile.PlayerProfile}
     * @return The profile of the owner, if set
     */
    @Nullable com.destroystokyo.paper.profile.PlayerProfile getPlayerProfile();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个头颅的主人.
     * <p>原文：
     * Gets the owner of the skull.
     *
     * @return 头颅的主人
     * <p>原文：the owner if the skull
     */
    @Nullable
    OfflinePlayer getOwningPlayer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个头颅的主人.
     * <p>
     * 插件应该在调用这个方法之前检查 hasOwner() 是否返回true.
     * <p>原文：
     * Sets the owner of the skull.
     * <p>
     * Plugins should check that hasOwner() returns true before calling this
     * plugin.
     *
     * @param owner 头颅的新主人
     * <p>原文：the new owner of the skull
     * @return 如果头颅主人成功被设置则为 true
     * <p>原文：true if the owner was successfully set
     */
    boolean setOwningPlayer(@Nullable OfflinePlayer owner);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取拥有此头颅的玩家的资料. 此玩家资料所含的纹理取决于头颅类型.
     * <p>原文：
     * Gets the profile of the player who owns the skull. This player profile
     * may appear as the texture depending on skull type.
     *
     * @return 头颅所有者的资料
     * <p>原文：the profile of the owning player
     */
    @Nullable
    @Deprecated // Paper
    PlayerProfile getOwnerProfile();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置拥有此头颅的玩家的资料. 此玩家资料所含的纹理取决于头颅类型.
     * <p>
     * 此资料必须包含唯一 id 和皮肤纹理. 如果两者都缺, 则资料必须包含玩家名,
     * 服务器将利用玩家名来尝试查找其唯一 id 和皮肤纹理.
     * <p>原文：
     * Sets the profile of the player who owns the skull. This player profile
     * may appear as the texture depending on skull type.
     * <p>
     * The profile must contain both a unique id and a skin texture. If either
     * of these is missing, the profile must contain a name by which the server
     * will then attempt to look up the unique id and skin texture.
     *
     * @param profile 头颅所有者的资料
     * <p>原文：the profile of the owning player
     * @throws IllegalArgumentException 如果资料没有包含必要信息
     * <p>原文：if the profile does not contain the
     * necessary information
     */
    @Deprecated // Paper
    void setOwnerProfile(@Nullable PlayerProfile profile);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置在音符盒上放置此头颅时播放的音效.
     * <br>
     * <strong>注意：</strong> 这仅对玩家头颅有效. 对于其他类型的头颅,
     * 请参阅 {@link org.bukkit.Instrument}.
     * <p>原文：
     * Sets the sound to play if the skull is placed on a note block.
     * <br>
     * <strong>Note:</strong> This only works for player heads. For other heads,
     * see {@link org.bukkit.Instrument}.
     *
     * @param noteBlockSound 要播放的音效对应的命名空间键, 设置为 null 以清除
     * <p>原文：the key of the sound to be played, or null
     */
    void setNoteBlockSound(@Nullable NamespacedKey noteBlockSound);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在音符盒上放置此头颅时播放的音效.
     * <br>
     * <strong>注意：</strong> 这仅对玩家头颅有效. 对于其他类型的头颅,
     * 请参阅 {@link org.bukkit.Instrument}.
     * <p>原文：
     * Gets the sound to play if the skull is placed on a note block.
     * <br>
     * <strong>Note:</strong> This only works for player heads. For other heads,
     * see {@link org.bukkit.Instrument}.
     *
     * @return 音效对应的命名空间键, 如果未设置则返回 null
     * <p>原文：the key of the sound, or null
     */
    @Nullable
    NamespacedKey getNoteBlockSound();

    @Override
    @NotNull
    SkullMeta clone();
}
