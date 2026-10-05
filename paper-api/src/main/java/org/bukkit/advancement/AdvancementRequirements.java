package org.bukkit.advancement;

import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 进度的需求列表.
 *
 * 需求是对条件的补充. 它们只是包含更多列表的列表, 这些列表中又包含等于条件名称的字符串.
 * 最终定义了为授予进度而完成条件的逻辑.
 * <p>原文：
 * The list of requirements for the advancement.
 *
 * Requirements are complimentary to criteria. They are just lists that contain
 * more lists, which in turn contains strings that equal the names of the
 * criteria. Ultimately defining the logic around how criteria are completed in
 * order to grant the advancement.
 *
 * @see <a href=https://minecraft.wiki/w/Advancement_definition>Advancement Definition</a>
 * @see <a href=https://www.minecraftforum.net/forums/minecraft-java-edition/redstone-discussion-and/commands-command-blocks-and/2809368-1-12-custom-advancements-aka-achievements#Requirements>Advancement Requirements</a>
 */
public interface AdvancementRequirements {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此进度中存在的所有需求.
     * <p>原文：
     * Get all the requirements present in this advancement.
     *
     * @return 所有需求的不可修改副本.
     * <p>原文：an unmodifiable copy of all requirements.
     */
    @NotNull
    List<AdvancementRequirement> getRequirements();
}
