package io.papermc.paper.registry.data.dialog.type;

import io.papermc.paper.registry.data.dialog.ActionButton;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示通知对话框。
 * <p>原文：
 * Represents a notice dialog.
 * @see DialogType#notice(ActionButton)
 */
@ApiStatus.NonExtendable
public non-sealed interface NoticeType extends DialogType {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回与此通知类型关联的操作按钮。
     * <p>原文：
     * Returns the action button associated with this notice type.
     *
     * @return 操作按钮
     * <p>原文：the action button
     */
    @Contract(pure = true)
    ActionButton action();
}
