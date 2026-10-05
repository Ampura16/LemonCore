package io.papermc.paper.event.entity;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当两个实体交配且交配过程导致受精时调用。受精不同于由 {@link EntityBreedEvent} 表示的普通繁殖，它不会立即在世界中生成幼体实体。
 * <p>
 * 例如：
 * <ul>
 * <li>青蛙被标记为“is_pregnant”，稍后产下 {@link Material#FROGSPAWN}。</li>
 * <li>嗅探兽产出 {@link Material#SNIFFER_EGG} 物品，需要放置后才会开始孵化。</li>
 * <li>海龟被标记为“HasEgg”，稍后产下 {@link Material#TURTLE_EGG}。</li>
 * </ul>
 * <p>
 * 因此该事件只公开受精过程中的两个亲代实体，无法提供幼体实体，因为幼体要到之后才会存在。
 * <p>原文：
 * Called when two entities mate and the mating process results in a fertilization.
 * Fertilization differs from normal breeding, as represented by the {@link EntityBreedEvent}, as
 * it does not result in the immediate creation of the child entity in the world.
 * <p>
 * An example of this would be:
 * <ul>
 * <li>A frog being marked as "is_pregnant" and laying {@link Material#FROGSPAWN} later.</li>
 * <li>Sniffers producing the {@link Material#SNIFFER_EGG} item, which needs to be placed before it can begin to hatch.</li>
 * <li>A turtle being marked with "HasEgg" and laying a {@link Material#TURTLE_EGG} later.</li>
 * </ul>
 * <p>
 * The event hence only exposes the two parent entities in the fertilization process and cannot provide the child entity, as it will only exist at a later point in time.
 */
@NullMarked
public class EntityFertilizeEggEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final LivingEntity father;
    private final @Nullable Player breeder;
    private final @Nullable ItemStack bredWith;
    private int experience;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityFertilizeEggEvent(final LivingEntity mother, final LivingEntity father, final @Nullable Player breeder, final @Nullable ItemStack bredWith, final int experience) {
        super(mother);
        this.father = father;
        this.breeder = breeder;
        this.bredWith = bredWith;
        this.experience = experience;
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供受精过程中最终负责“产生”后代的实体，无论是放置一个之后会孵化的方块，还是掉落一个需要放置的蛋。
     * <p>原文：
     * Provides the entity in the fertilization process that will eventually be responsible for "creating" offspring,
     * may that be by setting a block that later hatches or dropping an egg that has to be placed.
     *
     * @return “母方”实体。
     * <p>原文：The "mother" entity.
     */
    public LivingEntity getMother() {
        return this.getEntity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供受精过程中不负责发起后代生成的“父方”实体。
     * <p>原文：
     * Provides the "father" entity in the fertilization process that is not responsible for initiating the offspring
     * creation.
     *
     * @return 另一个亲代
     * <p>原文：the other parent
     */
    public LivingEntity getFather() {
        return this.father;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取负责受精的实体。自然受孕时繁殖者为 {@code null}。
     * <p>原文：
     * Gets the Entity responsible for fertilization. Breeder is {@code null} for spontaneous
     * conception.
     *
     * @return 引发受精的实体。
     * <p>原文：The Entity who initiated fertilization.
     */
    public @Nullable Player getBreeder() {
        return this.breeder;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于引发受精的 ItemStack（如果有）。
     * <p>原文：
     * The ItemStack that was used to initiate fertilization, if present.
     *
     * @return 用于引发受精的 ItemStack。
     * <p>原文：ItemStack used to initiate fertilization.
     */
    public @Nullable ItemStack getBredWith() {
        return this.bredWith;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取受精所给予的经验值。
     * <p>原文：
     * Get the amount of experience granted by fertilization.
     *
     * @return 经验值
     * <p>原文：experience amount
     */
    public int getExperience() {
        return this.experience;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置受精所给予的经验值。如果数量为负数或零，则不会掉落经验。
     * <p>原文：
     * Set the amount of experience granted by fertilization.
     * If the amount is negative or zero, no experience will be dropped.
     *
     * @param experience 经验值
     * <p>原文：experience amount
     */
    public void setExperience(final int experience) {
        this.experience = experience;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
