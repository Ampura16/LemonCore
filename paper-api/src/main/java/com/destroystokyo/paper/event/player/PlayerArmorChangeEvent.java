package com.destroystokyo.paper.event.player;

import java.util.Set;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import static org.bukkit.Material.*;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家自己更换其盔甲物品时调用
 * <p>
 * 目前不会因环境因素调用，但<strong>将来可能会</strong>
 * <p>原文：
 * Called when the player themselves change their armor items
 * <p>
 * Not currently called for environmental factors though it <strong>MAY BE IN THE FUTURE</strong>
 * @apiNote 所有实体装备变化请使用 {@link io.papermc.paper.event.entity.EntityEquipmentChangedEvent}
 * <p>原文：Use {@link io.papermc.paper.event.entity.EntityEquipmentChangedEvent} for all entity equipment changes
 */
@NullMarked
@ApiStatus.Obsolete(since = "1.21.4")
public class PlayerArmorChangeEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final SlotType slotType;
    private final ItemStack oldItem;
    private final ItemStack newItem;

    @ApiStatus.Internal
    public PlayerArmorChangeEvent(final Player player, final SlotType slotType, final ItemStack oldItem, final ItemStack newItem) {
        super(player);
        this.slotType = slotType;
        this.oldItem = oldItem;
        this.newItem = newItem;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在被更改的槽位类型。
     * <p>原文：
     * Gets the type of slot being altered.
     *
     * @return 被更改的槽位类型
     * <p>原文：type of slot being altered
     * @deprecated {@link SlotType} 无法准确表示每个槽位中哪些物品类型有效。请改用 {@link #getSlot()}。
     * <p>原文：{@link SlotType} does not accurately represent what item types are valid in each slot. Use {@link #getSlot()} instead.
     */
    @Deprecated(since = "1.21.4")
    public SlotType getSlotType() {
        return this.slotType;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在被更改的槽位。
     * <p>原文：
     * Gets the slot being altered.
     *
     * @return 正在被更改的槽位
     * <p>原文：slot being altered
     */
    public EquipmentSlot getSlot() {
        return switch (this.slotType) {
            case HEAD -> EquipmentSlot.HEAD;
            case CHEST -> EquipmentSlot.CHEST;
            case LEGS -> EquipmentSlot.LEGS;
            case FEET -> EquipmentSlot.FEET;
        };
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正被替换的现有物品
     * <p>原文：
     * Gets the existing item that's being replaced
     *
     * @return 旧物品
     * <p>原文：old item
     */
    public ItemStack getOldItem() {
        return this.oldItem;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取替换旧物品的新物品
     * <p>原文：
     * Gets the new item that's replacing the old
     *
     * @return 新物品
     * <p>原文：new item
     */
    public ItemStack getNewItem() {
        return this.newItem;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /**
     * @deprecated {@link SlotType} 无法准确表示每个槽位中哪些物品类型有效。
     * <p>原文：{@link SlotType} does not accurately represent what item types are valid in each slot.
     */
    @Deprecated(since = "1.21.4")
    public enum SlotType {
        HEAD(COPPER_HELMET, NETHERITE_HELMET, DIAMOND_HELMET, GOLDEN_HELMET, IRON_HELMET, CHAINMAIL_HELMET, LEATHER_HELMET, CARVED_PUMPKIN, PLAYER_HEAD, SKELETON_SKULL, ZOMBIE_HEAD, CREEPER_HEAD, WITHER_SKELETON_SKULL, TURTLE_HELMET, DRAGON_HEAD, PIGLIN_HEAD),
        CHEST(COPPER_CHESTPLATE, NETHERITE_CHESTPLATE, DIAMOND_CHESTPLATE, GOLDEN_CHESTPLATE, IRON_CHESTPLATE, CHAINMAIL_CHESTPLATE, LEATHER_CHESTPLATE, ELYTRA),
        LEGS(COPPER_LEGGINGS, NETHERITE_LEGGINGS, DIAMOND_LEGGINGS, GOLDEN_LEGGINGS, IRON_LEGGINGS, CHAINMAIL_LEGGINGS, LEATHER_LEGGINGS),
        FEET(COPPER_BOOTS, NETHERITE_BOOTS, DIAMOND_BOOTS, GOLDEN_BOOTS, IRON_BOOTS, CHAINMAIL_BOOTS, LEATHER_BOOTS);

        private final Set<Material> types;

        SlotType(final Material... types) {
            this.types = Set.of(types);
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取所有允许放入盔甲槽位的材料类型的不可变集合。
         * <p>原文：
         * Gets an immutable set of all allowed material types that can be placed in an
         * armor slot.
         *
         * @return 材料类型的不可变集合
         * <p>原文：immutable set of material types
         */
        public Set<Material> getTypes() {
            return this.types;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 通过指定材料获取槽位类型
         * <p>原文：
         * Gets the type of slot via the specified material
         *
         * @param material 用于获取槽位的材料
         * <p>原文：material to get slot by
         * @return 该材料将放入的槽位类型；如果不会放入则为 {@code null}
         * <p>原文：slot type the material will go in, or {@code null} if it won't
         */
        public static @Nullable SlotType getByMaterial(final Material material) {
            for (final SlotType slotType : values()) {
                if (slotType.getTypes().contains(material)) {
                    return slotType;
                }
            }
            return null;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取此材料能否装备到槽位
         * <p>原文：
         * Gets whether this material can be equipped to a slot
         *
         * @param material 要检查的材料
         * <p>原文：material to check
         * @return 此材料能否被装备
         * <p>原文：whether this material can be equipped
         */
        public static boolean isEquipable(final Material material) {
            return getByMaterial(material) != null;
        }
    }
}
