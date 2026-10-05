package org.bukkit.advancement;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 保存游戏如何显示此进度的信息.
 * <p>原文：
 * Holds information about how the advancement is displayed by the game.
 *
 * @deprecated use {@link io.papermc.paper.advancement.AdvancementDisplay}
 */
@Deprecated(forRemoval = true) // Paper
public interface AdvancementDisplay {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此进度的标题.
     * <p>原文：
     * Gets the title of the advancement.
     *
     * @return 不含颜色代码的进度标题
     * <p>原文：The advancement title without colour codes.
     */
    @NotNull
    String getTitle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此进度的可见描述.
     * <p>原文：
     * Gets the visible description of the advancement.
     *
     * @return 不含颜色代码的进度描述
     * <p>原文：The advancement description without colour codes.
     */
    @NotNull
    String getDescription();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于此进度的图标.
     * <p>原文：
     * The icon that is used for this advancement.
     *
     * @return 代表此进度的ItemStack
     * <p>原文：an ItemStack that represents the advancement.
     */
    @NotNull
    ItemStack getIcon();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当此进度完成时是否向玩家显示一个提示.
     * <p>原文：
     * Whether to show a toast to the player when this advancement has been
     * completed.
     *
     * @return 如果显示提示则返回true
     * <p>原文：true if a toast is shown.
     */
    boolean shouldShowToast();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当此进度完成时是否在聊天中公告.
     * <p>原文：
     * Whether to announce in the chat when this advancement has been completed.
     *
     * @return 如果在聊天中公告则返回true
     * <p>原文：true if announced in chat.
     */
    boolean shouldAnnounceChat();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在完成此进度前是否在进度界面中隐藏此进度及其所有子进度.
     * <p>原文：
     * Whether to hide this advancement and all its children from the
     * advancement screen until this advancement have been completed.
     *
     * Has no effect on root advancements themselves, but still affects all
     * their children.
     *
     * @return 如果隐藏则返回true
     * <p>原文：true if hidden.
     */
    boolean isHidden();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此进度在进度界面中的X位置.
     * <p>原文：
     * The X position of the advancement in the advancement screen.
     *
     * @return X坐标, 浮点数形式
     * <p>原文：the X coordinate as float
     */
    float getX();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此进度在进度界面中的Y位置.
     * <p>原文：
     * The Y position of the advancement in the advancement screen.
     *
     * @return Y坐标, 浮点数形式
     * <p>原文：the Y coordinate as float
     */
    float getY();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此进度的显示类型.
     * <p>原文：
     * The display type of this advancement.
     *
     * @return 表示类型的枚举
     * <p>原文：an enum representing the type.
     */
    @NotNull
    AdvancementDisplayType getType();
}
