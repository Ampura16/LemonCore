package org.bukkit.event.entity;

import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class VillagerCareerChangeEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Profession profession;
    private final ChangeReason reason;

    private boolean cancelled;

    @ApiStatus.Internal
    public VillagerCareerChangeEvent(@NotNull Villager villager, @NotNull Profession profession, @NotNull ChangeReason reason) {
        super(villager);
        this.profession = profession;
        this.reason = reason;
    }

    @NotNull
    @Override
    public Villager getEntity() {
        return (Villager) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取村民的未来职业。
     * <p>原文：
     * Gets the future profession of the villager.
     *
     * @return 村民将要改变的职业
     * <p>原文：The profession the villager will change to
     */
    @NotNull
    public Profession getProfession() {
        return this.profession;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置村民将要成为的职业。
     * <p>原文：
     * Sets the profession the villager will become from this event.
     *
     * @param profession 新职业
     * <p>原文：new profession
     */
    public void setProfession(@NotNull Profession profession) {
        this.profession = profession;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取村民职业改变的原因。
     * <p>原文：
     * Gets the reason for why the villager's career is changing.
     *
     * @return 村民职业改变的原因
     * <p>原文：Reason for villager's profession changing
     */
    @NotNull
    public ChangeReason getReason() {
        return this.reason;
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

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 村民职业改变的原因。
     * <p>原文：
     * Reasons for the villager's profession changing.
     */
    public enum ChangeReason {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 村民因经验太少而失业。
         * <p>原文：
         * Villager lost their job due to too little experience.
         */
        LOSING_JOB,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 村民就业。
         * <p>原文：
         * Villager gained employment.
         */
        EMPLOYED;
    }
}
