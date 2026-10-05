package org.bukkit.permissions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 保存关于权限以及提供该权限的{@link PermissionAttachment}的信息
 * <p>原文：
 * Holds information on a permission and which {@link PermissionAttachment}
 * provides it
 */
public class PermissionAttachmentInfo {
    private final Permissible permissible;
    private final String permission;
    private final PermissionAttachment attachment;
    private final boolean value;

    public PermissionAttachmentInfo(@NotNull Permissible permissible, @NotNull String permission, @Nullable PermissionAttachment attachment, boolean value) {
        if (permissible == null) {
            throw new IllegalArgumentException("Permissible may not be null");
        } else if (permission == null) {
            throw new IllegalArgumentException("Permission may not be null");
        }

        this.permissible = permissible;
        this.permission = permission;
        this.attachment = attachment;
        this.value = value;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此权限附加到的可权限对象.
     * <p>原文：
     * Gets the permissible this is attached to
     *
     * @return 此权限所属的Permissible
     * <p>原文：Permissible this permission is for
     */
    @NotNull
    public Permissible getPermissible() {
        return permissible;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被设置的权限.
     * <p>原文：
     * Gets the permission being set
     *
     * @return 权限名称
     * <p>原文：Name of the permission
     */
    @NotNull
    public String getPermission() {
        return permission;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取提供此权限的附件. 对于默认权限(通常是父权限)可能为null.
     * <p>原文：
     * Gets the attachment providing this permission. This may be null for
     * default permissions (usually parent permissions).
     *
     * @return 附件
     * <p>原文：Attachment
     */
    @Nullable
    public PermissionAttachment getAttachment() {
        return attachment;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此权限的值.
     * <p>原文：
     * Gets the value of this permission
     *
     * @return 权限的值
     * <p>原文：Value of the permission
     */
    public boolean getValue() {
        return value;
    }
}
