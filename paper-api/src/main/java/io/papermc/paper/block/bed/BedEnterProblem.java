package io.papermc.paper.block.bed;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示阻止玩家继续睡眠过程的问题，使其无法入睡也无法设置重生点
 * <p>原文：
 * Represents a problem that prevents a player from continuing
 * the sleeping process, preventing it from sleeping and from setting
 * its spawn point
 */
@ApiStatus.NonExtendable
@ApiStatus.Experimental
public interface BedEnterProblem {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 当床离玩家太远时发生。不会爆炸，并有错误消息
     * <p>原文：
     * Happens when the bed is too far away from the player.
     * Makes no explosion and has an error message
     *
     * @see #errorMessage()
     */
    BedEnterProblem TOO_FAR_AWAY = BedEnterActionBridge.instance().createTooFarAwayProblem();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 当床上方一格有方块时发生。不会爆炸，并有错误消息
     * <p>原文：
     * Happens when the bed has blocks one block above it.
     * Makes no explosion and has an error message
     *
     * @see #errorMessage()
     */
    BedEnterProblem OBSTRUCTED = BedEnterActionBridge.instance().createObstructedProblem();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 当以床头为中心的 16x10x16 范围内有怪物且玩家不处于创造模式时发生。不会爆炸，并有错误消息
     * <p>原文：
     * Happens when there are monsters in a 16x10x16 box centered on the bed's head
     * and the player is not in creative mode. Makes no explosion and has an error message
     *
     * @see #errorMessage()
     */
    BedEnterProblem NOT_SAFE = BedEnterActionBridge.instance().createNotSafeProblem();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 当床被设定为会爆炸时发生。这由世界的环境属性定义。该情况没有固定的错误消息，参见 {@link BedEnterAction#errorMessage()}
     * <p>原文：
     * Happens when the bed is set to explode. This is defined in the environment attributes of the world.
     * This doesn't have a fixed error message, see {@link BedEnterAction#errorMessage()}
     */
    BedEnterProblem EXPLOSION = BedEnterActionBridge.instance().createExplosionProblem();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 当玩家在无效状态下尝试睡觉时发生，例如玩家已在睡觉或已死亡时尝试睡觉。这种情况完全没有错误消息，交互会被直接丢弃
     * <p>原文：
     * Happens when a player tries to sleep when an invalid state, for example when a player tries
     * to sleep but is already sleeping or is dead.
     * This doesn't have an error message at all, the interaction is simply discarded
     */
    BedEnterProblem OTHER = BedEnterActionBridge.instance().createOtherProblem();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回与此问题关联的错误消息（如果有）。出现此问题时，该组件会以动作栏消息的形式发送给客户端
     * <p>原文：
     * Returns the error message associated with this problem, if any.
     * This component is sent to the client as an actionbar message
     * when this problem occur
     *
     * @return 错误消息
     * <p>原文：the error message
     */
    @Nullable
    Component errorMessage();

}
