package io.papermc.paper.registry.data.dialog.body;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 对话框的纯文本消息主体。
 * <p>
 * 通过 {@link DialogBody#plainMessage(Component, int)}
 * </p>
 * 创建
 * <p>原文：
 * A plain message body for a dialog.
 * <p>Created via {@link DialogBody#plainMessage(Component, int)}</p>
 */
public non-sealed interface PlainMessageDialogBody extends DialogBody {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 纯文本消息主体的内容。
     * <p>原文：
     * The contents of the plain message body.
     *
     * @return 组件内容
     * <p>原文：the component contents
     */
    @Contract(pure = true)
    Component contents();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 纯文本消息主体的宽度。
     * <p>原文：
     * The width of the plain message body.
     *
     * @return 宽度
     * <p>原文：the width
     */
    @Contract(pure = true)
    @Range(from = 1, to = 1024) int width();
}
