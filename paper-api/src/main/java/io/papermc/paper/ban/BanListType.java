package io.papermc.paper.ban;

import org.bukkit.BanList;
import org.bukkit.ban.IpBanList;
import org.bukkit.ban.ProfileBanList;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示 {@link BanList} 可追踪的封禁类型。它在编译时强制正确的返回值。
 * <p>原文：
 * Represents a ban-type that a {@link BanList} may track.
 * It enforces the correct return value at compile time.
 */
@NullMarked
public interface BanListType<T> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 被封禁的 IP 地址
     * <p>原文：
     * Banned IP addresses
     */
    BanListType<IpBanList> IP = new BanListTypeImpl<>(IpBanList.class);
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 被封禁的玩家档案
     * <p>原文：
     * Banned player profiles
     */
    BanListType<ProfileBanList> PROFILE = new BanListTypeImpl<>(ProfileBanList.class);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回泛型使用的封禁列表的类型类
     * <p>原文：
     * Returns the type class of the ban list used generically
     *
     * @return 类型类
     * <p>原文：the type class
     */
    Class<T> typeClass();
}
