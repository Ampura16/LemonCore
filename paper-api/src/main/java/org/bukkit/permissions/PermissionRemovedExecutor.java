package org.bukkit.permissions;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个在{@link PermissionAttachment}从{@link Permissible}中移除时被通知的类.
 * <p>原文：
 * Represents a class which is to be notified when a {@link
 * PermissionAttachment} is removed from a {@link Permissible}
 */
public interface PermissionRemovedExecutor {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当{@link PermissionAttachment}从{@link Permissible}中移除时调用.
     * <p>原文：
     * Called when a {@link PermissionAttachment} is removed from a {@link
     * Permissible}
     *
     * @param attachment 被移除的附件
     * <p>原文：Attachment which was removed
     */
    public void attachmentRemoved(@NotNull PermissionAttachment attachment);
}
