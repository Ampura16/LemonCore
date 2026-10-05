package org.bukkit.block;

import io.papermc.paper.datacomponent.item.ResolvableProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.block.data.BlockData;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表头颅.
 * <p>原文：
 * Represents a captured state of a skull block.
 */
public interface Skull extends TileState {

    /**
     * Gets the {@link ResolvableProfile} to use for this skull's skin.
     *
     * @return the profile, or null if not set
     */
    @Nullable ResolvableProfile getProfile();

    /**
     * Sets the {@link ResolvableProfile} to use for this skull's skin.
     *
     * @param profile the profile to set, or null to clear it
     */
    void setProfile(@Nullable ResolvableProfile profile);

    //<editor-fold desc="Deprecated profile methods" defaultstate="collapsed">
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测此头颅是否有主人.
     * <p>原文：
     * Checks to see if the skull has an owner
     *
     * @return true表示此头颅有主人
     * <p>原文：true if the skull has an owner
     * @deprecated Use {@link #getProfile()} {@code != null}.
     */
    @Deprecated(since = "1.21.9")
    public boolean hasOwner();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果存在，获取这个头颅的主人.
     * <p>原文：
     * Gets the owner of the skull, if one exists
     *
     * @return 头颅主人的名字，如果此头颅没有主人将返回null
     * <p>原文：the owner of the skull or null if the skull does not have an owner
     * @deprecated 请查阅 {@link #getOwningPlayer()}.
     * <p>原文：See {@link #getProfile()}.
     */
    @Deprecated(since = "1.9.4")
    @Nullable
    public String getOwner();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置头颅的主人.
     * <p>
     * 根据提供的名称获取配置文件数据时涉及一个可能的阻塞式Web请求.
     * <p>原文：
     * Sets the owner of the skull
     * <p>
     * Involves a potentially blocking web request to acquire the profile data for
     * the provided name.
     *
     * @param name 新的头颅主人的名字
     * <p>原文：the new owner of the skull
     * @return true表示成功设置这个头颅的主人
     * <p>原文：true if the owner was successfully set
     * @deprecated 请查阅 {@link #setOwningPlayer(org.bukkit.OfflinePlayer)}.
     * <p>原文：see {@link #setProfile(ResolvableProfile)}.
     */
    @Deprecated(since = "1.9.4")
    @Contract("null -> false")
    public boolean setOwner(@Nullable String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取拥有此头颅的玩家. 该头颅可能显示出玩家皮肤的头部, 这取决于头颅的类型.
     * <p>原文：
     * Get the player which owns the skull. This player may appear as the
     * texture depending on skull type.
     *
     * @return 头颅所有者
     * <p>原文：owning player
     * @deprecated see {@link #getProfile()}.
     */
    @Deprecated(since = "1.21.9")
    @Nullable
    public OfflinePlayer getOwningPlayer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置拥有此头颅的玩家. 该头颅可能显示出玩家皮肤的头部, 这取决于头颅的类型.
     * <p>原文：
     * Set the player which owns the skull. This player may appear as the
     * texture depending on skull type.
     *
     * @param player 头颅所有者
     * <p>原文：the owning player
     * @deprecated see {@link #setProfile(ResolvableProfile)}.
     */
    @Deprecated(since = "1.21.9")
    public void setOwningPlayer(@NotNull OfflinePlayer player);

    // Paper start
    /**
     * Sets this skull to use the supplied Player Profile, which can include textures already prefilled.
     * @param profile The profile to set this Skull to use, may not be null
     * @deprecated Use {@link #setProfile(ResolvableProfile)} instead.
     */
    @Deprecated(since = "1.21.9")
    void setPlayerProfile(@NotNull com.destroystokyo.paper.profile.PlayerProfile profile);

    /**
     * If the skull has an owner, per {@link #hasOwner()}, return the owners {@link com.destroystokyo.paper.profile.PlayerProfile}
     * @return The profile of the owner, if set
     * @deprecated Use {@link #getProfile()} instead.
     */
    @Deprecated(since = "1.21.9")
    @Nullable com.destroystokyo.paper.profile.PlayerProfile getPlayerProfile();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取拥有此头颅的玩家的资料. 此玩家资料所含的纹理取决于头颅类型.
     * <p>原文：
     * Gets the profile of the player who owns the skull. This player profile
     * may appear as the texture depending on skull type.
     *
     * @return 头颅所有者的资料
     * <p>原文：the profile of the owning player
     * @deprecated Use {@link #getProfile()} instead.
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
     * @deprecated Use {@link #setProfile(ResolvableProfile)} instead.
     */
    @Deprecated // Paper
    void setOwnerProfile(@Nullable PlayerProfile profile);
    //</editor-fold>

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取头颅放置在音符盒上时播放的音效.
     * <br>
     * <strong>注意:</strong> 这仅对玩家头颅有效. 其他类型的头颅,
     * 请参阅 {@link org.bukkit.Instrument}.
     * <p>原文：
     * Gets the sound to play if the skull is placed on a note block.
     * <br>
     * <strong>Note:</strong> This only works for player heads. For other heads,
     * see {@link org.bukkit.Instrument}.
     *
     * @return 音效的键值, 或null
     * <p>原文：the key of the sound, or null
     */
    @Nullable
    public NamespacedKey getNoteBlockSound();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置头颅放置在音符盒上时播放的音效.
     * <br>
     * <strong>注意:</strong> 这仅对玩家头颅有效. 其他类型的头颅,
     * 请参阅 {@link org.bukkit.Instrument}.
     * <p>原文：
     * Sets the sound to play if the skull is placed on a note block.
     * <br>
     * <strong>Note:</strong> This only works for player heads. For other heads,
     * see {@link org.bukkit.Instrument}.
     *
     * @param noteBlockSound 要播放的音效的键值, 或null
     * <p>原文：the key of the sound to be played, or null
     */
    public void setNoteBlockSound(@Nullable NamespacedKey noteBlockSound);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个头颅在世界的角度 (或头颅的朝向如果这个头颅被挂在墙上).
     * <p>原文：
     * Gets the rotation of the skull in the world (or facing direction if this
     * is a wall mounted skull).
     *
     * @return 头颅的角度
     * <p>原文：the rotation of the skull
     * @deprecated 请使用 {@link BlockData}
     * <p>原文：use {@link BlockData}
     */
    @Deprecated(since = "1.13")
    @NotNull
    public BlockFace getRotation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个头颅在世界的角度 (或头颅的朝向如果这个头颅被挂在墙上).
     * <p>原文：
     * Sets the rotation of the skull in the world (or facing direction if this
     * is a wall mounted skull).
     *
     * @param rotation 头颅的角度
     * <p>原文：the rotation of the skull
     * @deprecated 请使用 {@link BlockData}
     * <p>原文：use {@link BlockData}
     */
    @Deprecated(since = "1.13")
    public void setRotation(@NotNull BlockFace rotation);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取头颅的类型.
     * <p>原文：
     * Gets the type of skull
     *
     * @return 头颅的类型
     * <p>原文：the type of skull
     * @deprecated 请检查 {@link Material} 类型
     * <p>原文：check {@link Material} instead
     */
    @Deprecated(since = "1.13", forRemoval = true)
    @NotNull
    public SkullType getSkullType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置头颅的类型.
     * <p>原文：
     * Sets the type of skull
     *
     * @param skullType 头颅的类型
     * <p>原文：the type of skull
     * @deprecated 请检查 {@link Material} 类型
     * <p>原文：check {@link Material} instead
     */
    @Deprecated(since = "1.13", forRemoval = true)
    @Contract("_ -> fail")
    public void setSkullType(SkullType skullType);

    /**
     * Get the custom name of skull.
     * <p>This name is set when placing a skull item that has a custom name.
     * This name is only carried back to the item when broken for player heads
     * (skeleton/creeper heads will not retain the name).</p>
     *
     * @return Custom name of skull
     */
    public @Nullable Component customName();

    /**
     * Set the custom name of skull.
     * <p>This name is set when placing a skull item that has a custom name.
     * This name is only carried back to the item when broken for player heads
     * (skeleton/creeper heads will not retain the name).</p>
     *
     * @param customName Custom name of skull
     */
    public void customName(@Nullable Component customName);
}
