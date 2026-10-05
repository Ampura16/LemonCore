package io.papermc.paper.registry.data.dialog.type;

import io.papermc.paper.registry.data.dialog.ActionButton;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示确认对话框。此接口定义了带有“确认”和“拒绝”按钮的确认对话框结构。
 * <p>原文：
 * Represents a confirmation dialog.
 * This interface defines the structure for a confirmation dialog with "confirm" and "deny" buttons.
 * @see DialogType#confirmation(ActionButton, ActionButton)
 */
@ApiStatus.NonExtendable
public non-sealed interface ConfirmationType extends DialogType {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取确认操作的按钮。
     * <p>原文：
     * Gets the button for confirming the action.
     *
     * @return 确认按钮
     * <p>原文：the confirmation button
     */
    @Contract(pure = true)
    ActionButton yesButton();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取拒绝操作的按钮。
     * <p>原文：
     * Gets the button for denying the action.
     *
     * @return 拒绝按钮
     * <p>原文：the denial button
     */
    @Contract(pure = true)
    ActionButton noButton();
}
