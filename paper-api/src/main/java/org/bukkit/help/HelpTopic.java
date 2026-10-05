package org.bukkit.help;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * HelpTopic实现在用户使用/help命令时显示给用户.
 * <p>
 * 此类的自定义实现可以在两个层面上工作.
 * 简单实现只需要在构造函数中设置{@code name}、{@code shortText}和{@code fullText}的值. 此基类将处理其余部分.
 * <p>
 * 复杂实现可以通过覆盖此类中所有方法的行为来创建.
 * <p>原文：
 * HelpTopic implementations are displayed to the user when the user uses the
 * /help command.
 * <p>
 * Custom implementations of this class can work at two levels. A simple
 * implementation only needs to set the value of {@code name}, {@code
 * shortText}, and {@code fullText} in the constructor. This base class will
 * take care of the rest.
 * <p>
 * Complex implementations can be created by overriding the behavior of all
 * the methods in this class.
 */
public abstract class HelpTopic {
    protected String name = "";
    protected String shortText = "";
    protected String fullText = "";
    protected String amendedPermission = null;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 确定{@link Player}是否被允许查看此帮助主题.
     * <p>
     * HelpTopic实现在确定主题可见性时应考虑服务器管理员通过{@link HelpTopic#amendCanSee(String)}函数设置的意愿.
     * <p>原文：
     * Determines if a {@link Player} is allowed to see this help topic.
     * <p>
     * HelpTopic implementations should take server administrator wishes into
     * account as set by the {@link HelpTopic#amendCanSee(String)} function.
     *
     * @param player 相关的Player.
     * <p>原文：The Player in question.
     * @return 如果Player可以看到此帮助主题则为True, 否则为false.
     * <p>原文：True of the Player can see this help topic, false otherwise.
     */
    public abstract boolean canSee(@NotNull CommandSender player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许服务器管理员覆盖查看帮助主题所需的权限.
     * <p>
     * HelpTopic实现在{@link HelpTopic#canSee(org.bukkit.command.CommandSender)}函数中确定主题可见性时应考虑这一点.
     * <p>原文：
     * Allows the server administrator to override the permission required to
     * see a help topic.
     * <p>
     * HelpTopic implementations should take this into account when
     * determining topic visibility on the {@link
     * HelpTopic#canSee(org.bukkit.command.CommandSender)} function.
     *
     * @param amendedPermission 服务器管理员希望应用于此主题的权限节点.
     * <p>原文：The permission node the server administrator
     *     wishes to apply to this topic.
     */
    public void amendCanSee(@Nullable String amendedPermission) {
        this.amendedPermission = amendedPermission;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此帮助主题的名称.
     * <p>原文：
     * Returns the name of this help topic.
     *
     * @return 主题名称.
     * <p>原文：The topic name.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回将在主题索引中显示的简要描述.
     * <p>原文：
     * Returns a brief description that will be displayed in the topic index.
     *
     * @return 简要的主题描述.
     * <p>原文：A brief topic description.
     */
    @NotNull
    public String getShortText() {
        return shortText;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回当用户请求此主题详情时显示的完整帮助主题描述.
     * <p>
     * 结果将被分页以适当适应用户的客户端.
     * <p>原文：
     * Returns the full description of this help topic that is displayed when
     * the user requests this topic's details.
     * <p>
     * The result will be paginated to properly fit the user's client.
     *
     * @param forWho 请求完整文本的玩家或控制台. 对于在自定义实现中基于子权限进一步安全裁剪命令完整文本很有用.
     * <p>原文：The player or console requesting the full text. Useful
     *     for further security trimming the command's full text based on
     *     sub-permissions in custom implementations.
     * @return 完整的主题描述.
     * <p>原文：A full topic description.
     */
    @NotNull
    public String getFullText(@NotNull CommandSender forWho) {
        return fullText;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许服务器管理员(或另一个插件)添加或替换帮助主题的内容.
     * <p>
     * 任一参数为null将保留该部分主题不变. 在任一修改参数中, 字符串{@literal <text>}将被替换为帮助主题中的现有内容.
     * 使用此方法可以将额外内容追加或预置到自动生成的帮助主题中.
     * <p>原文：
     * Allows the server admin (or another plugin) to add or replace the
     * contents of a help topic.
     * <p>
     * A null in either parameter will leave that part of the topic unchanged.
     * In either amending parameter, the string {@literal <text>} is replaced
     * with the existing contents in the help topic. Use this to append or
     * prepend additional content into an automatically generated help topic.
     *
     * @param amendedShortText 要使用的新主题短文本, 或null保持不变.
     * <p>原文：The new topic short text to use, or null to
     *     leave alone.
     * @param amendedFullText 要使用的新主题完整文本, 或null保持不变.
     * <p>原文：The new topic full text to use, or null to leave
     *     alone.
     */
    public void amendTopic(@Nullable String amendedShortText, @Nullable String amendedFullText) {
        shortText = applyAmendment(shortText, amendedShortText);
        fullText = applyAmendment(fullText, amendedFullText);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实现自定义HelpTopic的开发者可以使用此工具方法来确保其实现符合{@link HelpTopic#amendTopic(String, String)}方法的预期行为.
     * <p>原文：
     * Developers implementing their own custom HelpTopic implementations can
     * use this utility method to ensure their implementations comply with the
     * expected behavior of the {@link HelpTopic#amendTopic(String, String)}
     * method.
     *
     * @param baseText 帮助主题的现有文本.
     * <p>原文：The existing text of the help topic.
     * @param amendment 来自amendTopic()方法的修改文本.
     * <p>原文：The amending text from the amendTopic() method.
     * @return 根据amendTopic()的预期规则将修改文本应用于现有文本的结果.
     * <p>原文：The application of the amending text to the existing text,
     *     according to the expected rules of amendTopic().
     */
    @NotNull
    protected String applyAmendment(@NotNull String baseText, @Nullable String amendment) {
        if (amendment == null) {
            return baseText;
        } else {
            return amendment.replaceAll("<text>", baseText);
        }
    }
}
