package io.papermc.paper.event.entity;

import java.util.Collections;
import java.util.Map;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 每当检测到实体装备发生变化时调用。该事件在属性修饰符和魔咒的效果更新之后调用。
 * <p>
 * 可能触发该事件的操作示例：
 * <ul>
 * <li>实体被添加到世界中。</li>
 * <li>玩家登录。</li>
 * <li>装备物品的耐久度改变。</li>
 * <li>发射器为实体装备物品。</li>
 * <li>实体从地上捡起盔甲或武器物品。</li>
 * <li>玩家更换所穿戴的盔甲。</li>
 * <li>玩家更换当前手持的物品。</li>
 * </ul>
 * <p>原文：
 * Called whenever a change to an entity's equipment has been detected. This event is called after effects from
 * attribute modifiers and enchantments have been updated.
 * <p>
 * Examples of actions that can trigger this event:
 * <ul>
 *     <li>An entity being added to a world.</li>
 *     <li>A player logging in.</li>
 *     <li>The durability of an equipment item changing.</li>
 *     <li>A dispenser equipping an item onto an entity.</li>
 *     <li>An entity picking up an armor or weapon item from the ground.</li>
 *     <li>A player changing their equipped armor.</li>
 *     <li>A player changes their currently held item.</li>
 * </ul>
 */
@NullMarked
public class EntityEquipmentChangedEvent extends EntityEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Map<EquipmentSlot, EquipmentChange> equipmentChanges;

    @ApiStatus.Internal
    public EntityEquipmentChangedEvent(final LivingEntity entity, final Map<EquipmentSlot, EquipmentChange> equipmentChanges) {
        super(entity);

        this.equipmentChanges = equipmentChanges;
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) this.entity;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取发生变化的槽位到其各自装备变化的映射。
     * <p>原文：
     * Gets a map of changed slots to their respective equipment changes.
     *
     * @return 装备变化映射
     * <p>原文：the equipment changes map
     */
    public @Unmodifiable Map<EquipmentSlot, EquipmentChange> getEquipmentChanges() {
        return Collections.unmodifiableMap(this.equipmentChanges);
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
     * 表示单个装备槽位的装备变化。
     * <p>原文：
     * Represents a change in equipment for a single equipment slot.
     */
    @ApiStatus.NonExtendable
    public interface EquipmentChange {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取正被替换的现有物品。
         * <p>原文：
         * Gets the existing item that is being replaced.
         *
         * @return 已有的物品
         * <p>原文：the existing item
         */
        @Contract(pure = true, value = "-> new")
        ItemStack oldItem();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取替换现有物品的新物品。
         * <p>原文：
         * Gets the new item that is replacing the existing item.
         *
         * @return 新物品
         * <p>原文：the new item
         */
        @Contract(pure = true, value = "-> new")
        ItemStack newItem();
    }
}
