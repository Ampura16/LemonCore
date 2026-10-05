package io.papermc.paper.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.CopperGolem;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当运输物品的实体（通常是 {@link CopperGolem}，但也可能通过非 API 方式出现其他实体）为取出或存放物品而搜索或校验现有目标容器方块时调用。
 * <p>
 * 每个实体每刻可能调用多次，因此监听器应注意高效地实现检查。
 * </p>
 * <p>原文：
 * Called when an item-transporting entity (typically a {@link CopperGolem},
 * although other entities may be possible through non-API means)
 * is searching for or validating an existing target container block for
 * taking or depositing items.
 *
 * <p>This may be called multiple times per entity per tick, so listeners
 * should be careful to implement checks in an efficient manner.</p>
 */
@NullMarked
public class ItemTransportingEntityValidateTargetEvent extends EntityEvent {
    protected static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block block;
    private boolean allowed = true;

    @ApiStatus.Internal
    public ItemTransportingEntityValidateTargetEvent(
        final Entity entity,
        final Block block
    ) {
        super(entity);
        this.block = block;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否允许实体将 {@link #getBlock()} 作为目标。
     * <p>原文：
     * Sets if the entity is allowed to use {@link #getBlock()} as a target.
     *
     * @param allowed 目标是否被允许
     * <p>原文：whether the target is allowed
     */
    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否允许实体将 {@link #getBlock()} 作为目标。
     * <p>原文：
     * Gets if the entity is allowed to use {@link #getBlock()} as a target.
     *
     * @return 如果目标被允许则为 true
     * <p>原文：true if the target is allowed
     */
    public boolean isAllowed() {
        return this.allowed;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取实体正在校验的目标方块。
     * <p>原文：
     * Gets the target block the entity is validating.
     *
     * @return 目标方块
     * <p>原文：the target block
     */
    public Block getBlock() {
        return this.block;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
