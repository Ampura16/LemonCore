package org.bukkit.event.entity;

import com.destroystokyo.paper.event.entity.EntityZapEvent;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 储存猪被闪电击中的数据。
 * <p>原文：
 * Stores data for pigs being zapped
 */
public class PigZapEvent extends EntityZapEvent implements Cancellable {

    private final PigZombie zombifiedPiglin;
    private final LightningStrike bolt;

    private boolean cancelled;

    @ApiStatus.Internal
    public PigZapEvent(@NotNull final Pig pig, @NotNull final LightningStrike bolt, @NotNull final PigZombie zombifiedPiglin) {
        super(pig, bolt, zombifiedPiglin);
        this.bolt = bolt;
        this.zombifiedPiglin = zombifiedPiglin;
    }

    @NotNull
    @Override
    public Pig getEntity() {
        return (Pig) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取击中猪的闪电。
     * <p>原文：
     * Gets the bolt which is striking the pig.
     *
     * @return 闪电实体
     * <p>原文：lightning entity
     */
    @NotNull
    public LightningStrike getLightning() {
        return this.bolt;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将替换猪的僵尸猪灵，前提是事件未被取消。
     * <p>原文：
     * Gets the zombified piglin that will replace the pig, provided the event is
     * not cancelled first.
     *
     * @return 生成的实体
     * <p>原文：resulting entity
     * @deprecated 使用 {@link EntityTransformEvent#getTransformedEntity()}
     * <p>原文：use {@link EntityTransformEvent#getTransformedEntity()}
     */
    @NotNull
    @Deprecated(since = "1.13.2")
    public PigZombie getPigZombie() {
        return this.zombifiedPiglin;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
