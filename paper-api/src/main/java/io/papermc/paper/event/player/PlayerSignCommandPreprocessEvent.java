package io.papermc.paper.event.player;

import java.util.Set;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当 {@link Player} 点击告示牌的某一面导致运行命令时调用。
 * <p>
 * 该命令以提升的权限运行，玩家因此可以通过告示牌使用他们通常无法运行的命令。
 * <p>原文：
 * Called when a {@link Player} clicks a side on a sign that causes a command to run.
 * <p>
 * This command is run with elevated permissions which allows players to access commands on signs they wouldn't
 * normally be able to run.
 */
@NullMarked
public class PlayerSignCommandPreprocessEvent extends PlayerCommandPreprocessEvent {

    private final Sign sign;
    private final Side side;

    @ApiStatus.Internal
    public PlayerSignCommandPreprocessEvent(final Player player, final String message, final Set<Player> recipients, final Sign sign, final Side side) {
        super(player, message, recipients);
        this.sign = sign;
        this.side = side;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取命令来源的告示牌。
     * <p>原文：
     * Gets the sign that the command originated from.
     *
     * @return 告示牌
     * <p>原文：the sign
     */
    public Sign getSign() {
        return this.sign;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取命令来源的告示牌的那一面。
     * <p>原文：
     * Gets the side of the sign that the command originated from.
     *
     * @return 告示牌的面
     * <p>原文：the sign side
     */
    public Side getSide() {
        return this.side;
    }
}
