package com.destroystokyo.paper.event.block;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当铁砧因使用而损坏时调用
 * <p>原文：
 * Called when an anvil is damaged from being used
 */
@NullMarked
public class AnvilDamagedEvent extends InventoryEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private DamageState damageState;
    private boolean cancelled;

    @ApiStatus.Internal
    public AnvilDamagedEvent(final InventoryView inventory, final @Nullable BlockData blockData) {
        super(inventory);
        this.damageState = DamageState.getState(blockData);
    }

    @Override
    public AnvilInventory getInventory() {
        return (AnvilInventory) super.getInventory();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取铁砧新的损坏状态
     * <p>原文：
     * Gets the new state of damage on the anvil
     *
     * @return 损坏状态
     * <p>原文：Damage state
     */
    public DamageState getDamageState() {
        return this.damageState;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置铁砧新的损坏状态
     * <p>原文：
     * Sets the new state of damage on the anvil
     *
     * @param damageState 损坏状态
     * <p>原文：Damage state
     */
    public void setDamageState(final DamageState damageState) {
        this.damageState = damageState;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取铁砧是否在此次使用中损坏
     * <p>原文：
     * Gets if anvil is breaking on this use
     *
     * @return 如果正在破坏则为 {@code true}
     * <p>原文：{@code true} if breaking
     */
    public boolean isBreaking() {
        return this.damageState == DamageState.BROKEN;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置铁砧是否在此次使用中损坏
     * <p>原文：
     * Sets if anvil is breaking on this use
     *
     * @param breaking 如果正在破坏则为 {@code true}
     * <p>原文：{@code true} if breaking
     */
    public void setBreaking(final boolean breaking) {
        if (breaking) {
            this.damageState = DamageState.BROKEN;
        } else if (this.damageState == DamageState.BROKEN) {
            this.damageState = DamageState.DAMAGED;
        }
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

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示铁砧方块的损坏程度
     * <p>原文：
     * Represents the amount of damage on an anvil block
     */
    public enum DamageState {
        FULL(Material.ANVIL),
        CHIPPED(Material.CHIPPED_ANVIL),
        DAMAGED(Material.DAMAGED_ANVIL),
        BROKEN(Material.AIR);

        private final Material material;

        DamageState(final Material material) {
            this.material = material;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取此状态的方块材料
         * <p>原文：
         * Get block material of this state
         *
         * @return 材料
         * <p>原文：Material
         */
        public Material getMaterial() {
            return this.material;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 根据方块数据获取损坏状态
         * <p>原文：
         * Get damaged state by block data
         *
         * @param blockData 方块数据
         * <p>原文：Block data
         * @return DamageState
         * @throws IllegalArgumentException 如果给定的方块数据不是铁砧
         * <p>原文：If non anvil block data is given
         */
        public static DamageState getState(final @Nullable BlockData blockData) {
            return blockData == null ? BROKEN : getState(blockData.getMaterial());
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 根据方块材料获取损坏状态
         * <p>原文：
         * Get damaged state by block material
         *
         * @param material 方块材料
         * <p>原文：Block material
         * @return DamageState
         * @throws IllegalArgumentException 如果给定的材料不是铁砧
         * <p>原文：If non anvil material is given
         */
        public static DamageState getState(final @Nullable Material material) {
            if (material == null) {
                return BROKEN;
            }
            for (final DamageState state : values()) {
                if (state.getMaterial() == material) {
                    return state;
                }
            }
            throw new IllegalArgumentException("Material is not an anvil state");
        }
    }
}
