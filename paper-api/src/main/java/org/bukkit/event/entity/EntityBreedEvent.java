package org.bukkit.event.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个实体与另一个实体交配繁殖时触发本事件.
 * <p>原文：
 * Called when one Entity breeds with another Entity.
 */
public class EntityBreedEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final LivingEntity mother;
    private final LivingEntity father;
    private final LivingEntity breeder;
    private final ItemStack bredWith;
    private int experience;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityBreedEvent(@NotNull LivingEntity child, @NotNull LivingEntity mother, @NotNull LivingEntity father, @Nullable LivingEntity breeder, @Nullable ItemStack bredWith, int experience) {
        super(child);

        this.mother = mother;
        this.father = father;
        this.breeder = breeder; // Breeder can be null in the case of spontaneous conception
        this.bredWith = bredWith;
        this.experience = experience;
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取繁殖此实体的母实体.
     * <p>原文：
     * Gets the parent creating this entity.
     *
     * @return 母实体
     * <p>原文：The "birth" parent
     */
    @NotNull
    public LivingEntity getMother() {
        return this.mother;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此新生实体的父实体.
     * <p>原文：
     * Gets the other parent of the newly born entity.
     *
     * @return 父实体
     * <p>原文：the other parent
     */
    @NotNull
    public LivingEntity getFather() {
        return this.father;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取引起此次繁殖事件的实体. 如果繁殖是因自然或自发受精而起则为 null.
     * <p>原文：
     * Gets the Entity responsible for breeding. Breeder is {@code null} for spontaneous
     * conception.
     *
     * @return 引起此次繁殖的实体
     * <p>原文：The Entity who initiated breeding.
     */
    @Nullable
    public LivingEntity getBreeder() {
        return this.breeder;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于引起此次繁殖的物品 (若存在).
     * <p>原文：
     * The ItemStack that was used to initiate breeding, if present.
     *
     * @return 用于引起此次繁殖的物品
     * <p>原文：ItemStack used to initiate breeding.
     */
    @Nullable
    public ItemStack getBredWith() {
        return this.bredWith;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此次繁殖授予玩家的经验数量.
     * <p>原文：
     * Get the amount of experience granted by breeding.
     *
     * @return 经验
     * <p>原文：experience amount
     */
    public int getExperience() {
        return this.experience;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此次繁殖授予玩家的经验数量.
     * <p>原文：
     * Set the amount of experience granted by breeding.
     *
     * @param experience 经验
     * <p>原文：experience amount
     */
    public void setExperience(int experience) {
        Preconditions.checkArgument(experience >= 0, "Experience cannot be negative");
        this.experience = experience;
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
