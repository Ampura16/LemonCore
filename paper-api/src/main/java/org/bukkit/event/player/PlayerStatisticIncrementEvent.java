package org.bukkit.event.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家统计信息增长时触发本事件.
 * <p>
 * 这个事件不会因一些高频率统计项(如玩家走动)的数值更变而触发.
 * <p>原文：
 * Called when a player statistic is incremented.
 * <p>
 * This event is not called for some high frequency statistics, e.g. movement
 * based statistics.
 */
public class PlayerStatisticIncrementEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    protected final Statistic statistic;
    private final int initialValue;
    private final int newValue;
    private final EntityType entityType;
    private final Material material;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerStatisticIncrementEvent(@NotNull Player player, @NotNull Statistic statistic, int initialValue, int newValue) {
        super(player);
        this.statistic = statistic;
        this.initialValue = initialValue;
        this.newValue = newValue;
        this.entityType = null;
        this.material = null;
    }

    @ApiStatus.Internal
    public PlayerStatisticIncrementEvent(@NotNull Player player, @NotNull Statistic statistic, int initialValue, int newValue, @NotNull EntityType entityType) {
        super(player);
        this.statistic = statistic;
        this.initialValue = initialValue;
        this.newValue = newValue;
        this.entityType = entityType;
        this.material = null;
    }

    @ApiStatus.Internal
    public PlayerStatisticIncrementEvent(@NotNull Player player, @NotNull Statistic statistic, int initialValue, int newValue, @NotNull Material material) {
        super(player);
        this.statistic = statistic;
        this.initialValue = initialValue;
        this.newValue = newValue;
        this.entityType = null;
        if (material != null && material.isLegacy()) {
            if (statistic.getType() == Statistic.Type.BLOCK) {
                material = Bukkit.getUnsafe().fromLegacy(new MaterialData(material), false);
            } else if (statistic.getType() == Statistic.Type.ITEM) {
                material = Bukkit.getUnsafe().fromLegacy(new MaterialData(material), true);
            } else {
                // Theoretically, this should not happen, can probably print a warning, but for now it should be fine.
                material = Bukkit.getUnsafe().fromLegacy(new MaterialData(material), false);
            }
        }
        this.material = material;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取正在增长的统计数据.
     * <p>原文：
     * Gets the statistic that is being incremented.
     *
     * @return 统计数据
     * <p>原文：the incremented statistic
     */
    @NotNull
    public Statistic getStatistic() {
        return this.statistic;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个统计信息之前的数据值.
     * <p>原文：
     * Gets the previous value of the statistic.
     *
     * @return 这个统计信息之前的数据值
     * <p>原文：the previous value of the statistic
     */
    public int getPreviousValue() {
        return this.initialValue;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个统计数据的新数据值.
     * <p>原文：
     * Gets the new value of the statistic.
     *
     * @return 这个统计数据的新数据值
     * <p>原文：the new value of the statistic
     */
    public int getNewValue() {
        return this.newValue;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果{@link #getStatistic() getStatistic()}是关于实体的统计信息，获取该统计数据的实体种类，否则返回null.
     * <p>原文：
     * Gets the EntityType if {@link #getStatistic()} is an
     * entity statistic otherwise returns {@code null}.
     *
     * @return 这个统计数据的实体种类
     * <p>原文：the EntityType of the statistic
     */
    @Nullable
    public EntityType getEntityType() {
        return this.entityType;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果{@link #getStatistic() getStatistic()}是关于方块或物品的统计信息，获取该统计数据的物品种类，否则返回null.
     * <p>原文：
     * Gets the Material if {@link #getStatistic()} is a block
     * or item statistic otherwise returns {@code null}.
     *
     * @return 这个统计数据的物品种类
     * <p>原文：the Material of the statistic
     */
    @Nullable
    public Material getMaterial() {
        return this.material;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
