package io.papermc.paper.event.world;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.WorldEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当世界难度被命令或 API 更改时调用。
 * <p>
 * 如果世界处于 {@link World#isHardcore() hardcore}，最终难度将始终为 {@link Difficulty#HARD}
 * <p>原文：
 * Called when a world's difficulty is changed, either by command or by api.
 * <p>
 * If the world is in {@link World#isHardcore() hardcore}
 * the resulting difficulty will always be {@link Difficulty#HARD}
 */
@NullMarked
public class WorldDifficultyChangeEvent extends WorldEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final @Nullable CommandSourceStack commandSource;
    private final Difficulty difficulty;

    @ApiStatus.Internal
    public WorldDifficultyChangeEvent(final World world, final @Nullable CommandSourceStack commandSource, final Difficulty difficulty) {
        super(world);
        this.commandSource = commandSource;
        this.difficulty = difficulty;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此事件关联的命令源。
     * <p>原文：
     * Gets the command source associated with this event.
     *
     * @return 如果难度是通过 API 更改的则为 {@code null}，否则为 {@link CommandSourceStack}。
     * <p>原文：{@code null} if the difficulty was changed via api, otherwise the {@link CommandSourceStack}.
     */
    public @Nullable CommandSourceStack getCommandSource() {
        return this.commandSource;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取世界的新难度。
     * <p>原文：
     * Gets the new difficulty of the world.
     *
     * @return 新的难度。
     * <p>原文：the new difficulty.
     */
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
