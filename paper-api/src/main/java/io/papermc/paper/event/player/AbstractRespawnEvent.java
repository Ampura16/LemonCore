package io.papermc.paper.event.player;

import com.google.common.collect.ImmutableSet;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;
import java.util.Set;

@NullMarked
public abstract class AbstractRespawnEvent extends PlayerEvent {

    protected Location respawnLocation;
    private final boolean isBedSpawn;
    private final boolean isAnchorSpawn;
    private final boolean missingRespawnBlock;
    private final PlayerRespawnEvent.RespawnReason respawnReason;
    private final Set<PlayerRespawnEvent.RespawnFlag> respawnFlags;

    protected AbstractRespawnEvent(
        final Player respawnPlayer, final Location respawnLocation, final boolean isBedSpawn,
        final boolean isAnchorSpawn, final boolean missingRespawnBlock, final PlayerRespawnEvent.RespawnReason respawnReason
    ) {
        super(respawnPlayer);
        this.respawnLocation = respawnLocation;
        this.isBedSpawn = isBedSpawn;
        this.isAnchorSpawn = isAnchorSpawn;
        this.missingRespawnBlock = missingRespawnBlock;
        this.respawnReason = respawnReason;
        ImmutableSet.Builder<PlayerRespawnEvent.RespawnFlag> builder = ImmutableSet.builder();
        if (respawnReason == PlayerRespawnEvent.RespawnReason.END_PORTAL) builder.add(PlayerRespawnEvent.RespawnFlag.END_PORTAL);
        if (this.isBedSpawn) builder.add(PlayerRespawnEvent.RespawnFlag.BED_SPAWN);
        if (this.isAnchorSpawn) builder.add(PlayerRespawnEvent.RespawnFlag.ANCHOR_SPAWN);
        this.respawnFlags = builder.build();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取当前的重生位置。
     * <p>原文：
     * Gets the current respawn location.
     *
     * @return 当前的重生位置
     * <p>原文：the current respawn location
     */
    public Location getRespawnLocation() {
        return this.respawnLocation.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取重生位置是否为玩家的床。
     * <p>原文：
     * Gets whether the respawn location is the player's bed.
     *
     * @return 如果重生位置是玩家的床则为 {@code true}
     * <p>原文：{@code true} if the respawn location is the player's bed
     */
    public boolean isBedSpawn() {
        return this.isBedSpawn;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取重生位置是否为玩家的重生锚。
     * <p>原文：
     * Gets whether the respawn location is the player's respawn anchor.
     *
     * @return 如果重生位置是玩家的重生锚则为 {@code true}
     * <p>原文：{@code true} if the respawn location is the player's respawn anchor
     */
    public boolean isAnchorSpawn() {
        return this.isAnchorSpawn;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家是否缺少有效的重生方块。
     * <p>
     * 如果玩家的重生方块被阻挡，或是在其被破坏（对于重生锚则是充能耗尽）后的第一次死亡，就会出现这种情况。
     * <p>原文：
     * Gets whether the player is missing a valid respawn block.
     * <p>
     * This will occur if the players respawn block is obstructed,
     * or it is the first death after it was either destroyed or
     * in case of a respawn anchor, ran out of charges.
     *
     * @return 玩家是否缺少有效的重生方块
     * <p>原文：whether the player is missing a valid respawn block
     */
    public boolean isMissingRespawnBlock() {
        return this.missingRespawnBlock;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取调用此重生事件的原因。
     * <p>原文：
     * Gets the reason this respawn event was called.
     *
     * @return 调用该事件的原因
     * <p>原文：the reason the event was called
     */
    public PlayerRespawnEvent.RespawnReason getRespawnReason() {
        return this.respawnReason;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取适用于此次重生的标志集合。
     * <p>原文：
     * Gets the set of flags that apply to this respawn.
     *
     * @return 适用于此次重生的标志的不可变集合
     * <p>原文：an immutable set of the flags that apply to this respawn
     * @deprecated 改用 {@link #getRespawnReason()}/{@link #isBedSpawn}/{@link #isAnchorSpawn()}
     * <p>原文：in favour of {@link #getRespawnReason()}/{@link #isBedSpawn}/{@link #isAnchorSpawn()}
     */
    @Deprecated
    public @Unmodifiable Set<PlayerRespawnEvent.RespawnFlag> getRespawnFlags() {
        return this.respawnFlags;
    }
}
