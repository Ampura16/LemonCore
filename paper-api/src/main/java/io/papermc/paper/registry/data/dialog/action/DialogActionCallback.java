package io.papermc.paper.registry.data.dialog.action;

import io.papermc.paper.dialog.DialogResponseView;
import net.kyori.adventure.audience.Audience;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 对话框操作的回调。
 * <p>原文：
 * A callback for a dialog action.
 */
@FunctionalInterface
public interface DialogActionCallback {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 处理对话框操作。
     * <p>原文：
     * Handles a dialog action.
     *
     * @param response 对该操作的响应
     * <p>原文：the response to the action
     * @param audience 接收响应的受众
     * <p>原文：the audience to send the response to
     */
    @ApiStatus.OverrideOnly
    void accept(DialogResponseView response, Audience audience);
}
