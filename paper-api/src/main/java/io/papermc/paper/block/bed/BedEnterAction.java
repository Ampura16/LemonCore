package io.papermc.paper.block.bed;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示在 {@link org.bukkit.event.player.PlayerBedEnterEvent} 之后将发生、且在 {@link io.papermc.paper.event.player.PlayerBedFailEnterEvent} 期间正在发生的操作的操作类型
 * <p>原文：
 * An action type that represents the action that will happen after
 * {@link org.bukkit.event.player.PlayerBedEnterEvent} and that is
 * happening during {@link io.papermc.paper.event.player.PlayerBedFailEnterEvent}
 */
@ApiStatus.NonExtendable
@ApiStatus.Experimental
public interface BedEnterAction {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否允许玩家在床上睡觉。该值可以与 {@link #canSetSpawn()} 同时为 {@link BedRuleResult#ALLOWED}
     * <p>
     * 建议先检查 {@link BedEnterProblem}，因为只要其返回值不是 {@code null}，与床的交互就会被阻止
     * <p>原文：
     * Gets if the player is allowed to sleep in the bed.
     * This can be {@link BedRuleResult#ALLOWED} at the same time as {@link #canSetSpawn()}
     * <p>
     * It is advised to check for {@link BedEnterProblem}s first, as if it returns anything but {@code null}
     * the bed interaction is prevented
     *
     * @return 是否允许玩家睡觉
     * <p>原文：whether the player is allowed to sleep
     */
    BedRuleResult canSleep();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否允许玩家在床上保存重生点。该值可以与 {@link #canSleep()} 同时为 {@link BedRuleResult#ALLOWED}
     * <p>
     * 建议先检查 {@link BedEnterProblem}，因为只要其返回值不是 {@code null}，与床的交互就会被阻止
     * <p>原文：
     * Gets if the player is allowed to save its spawn point in the bed.
     * This can be {@link BedRuleResult#ALLOWED} at the same time as {@link #canSleep()}
     * <p>
     * It is advised to check for {@link BedEnterProblem}s first, as if it returns anything but {@code null}
     * the bed interaction is prevented
     *
     * @return 是否允许玩家保存其出生点
     * <p>原文：whether the player is allowed to save its spawn point
     */
    BedRuleResult canSetSpawn();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * “问题”是指阻止玩家睡觉并保存重生点的情况。未发现问题并不代表玩家成功入睡或设置了重生点，各自是否成功请参阅 {@link #canSleep()} 和 {@link #canSetSpawn()}
     * <p>原文：
     * A problem is an issue that prevents the player from sleeping and from saving its spawn point.
     * No problem being found doesn't mean the player successfully slept or set its spawn point,
     * see {@link #canSleep()} and {@link #canSetSpawn()} for individual successes
     *
     * @return 如果找到则为任一 {@link BedEnterProblem}，否则为 {@code null}
     * <p>原文：any of {@link BedEnterProblem}s if one is found, otherwise {@code null}
     */
    @Nullable
    BedEnterProblem problem();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回以动作栏消息形式向玩家显示的默认错误消息
     * <p>原文：
     * Returns the default error message to be shown as an actionbar message to the player
     *
     * @return 错误消息
     * <p>原文：the error message
     */
    @Nullable
    Component errorMessage();

}
