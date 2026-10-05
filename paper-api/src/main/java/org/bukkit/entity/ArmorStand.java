package org.bukkit.entity;

import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ArmorStand extends LivingEntity {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回盔甲架当前握持的物品.
     * <p>原文：
     * Returns the item the armor stand is currently holding.
     *
     * @return 盔甲架当前握持的物品
     * <p>原文：the held item
     * @see #getEquipment()
     * @deprecated prefer {@link ArmorStand#getItem(EquipmentSlot)}
     */
    @NotNull
    @Deprecated(since = "1.15.2")
    ItemStack getItemInHand();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置盔甲架当前握持的物品.
     * <p>原文：
     * Sets the item the armor stand is currently holding.
     *
     * @param item 盔甲架当前握持的物品
     * <p>原文：the item to hold
     * @see #getEquipment()
     * @deprecated prefer
     * {@link ArmorStand#setItem(EquipmentSlot, ItemStack)}
     */
    @Deprecated(since = "1.15.2")
    void setItemInHand(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回盔甲架当前穿在脚上的物品.
     * <p>原文：
     * Returns the item currently being worn by the armor stand on its feet.
     *
     * @return 穿戴的物品
     * <p>原文：the worn item
     * @see #getEquipment()
     * @deprecated prefer {@link EntityEquipment#getBoots()}
     */
    @NotNull
    @Deprecated(since = "1.15.2")
    ItemStack getBoots();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置盔甲架当前穿在脚上的物品.
     * <p>原文：
     * Sets the item currently being worn by the armor stand on its feet.
     *
     * @param item 穿戴的物品
     * <p>原文：the item to wear
     * @see #getEquipment()
     * @deprecated prefer
     * {@link EntityEquipment#setBoots(org.bukkit.inventory.ItemStack)}
     */
    @Deprecated(since = "1.15.2")
    void setBoots(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回盔甲架当前穿在腿上的物品.
     * <p>原文：
     * Returns the item currently being worn by the armor stand on its legs.
     *
     * @return 穿戴的物品
     * <p>原文：the worn item
     * @see #getEquipment()
     * @deprecated prefer {@link EntityEquipment#getLeggings()}
     */
    @NotNull
    @Deprecated(since = "1.15.2")
    ItemStack getLeggings();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置盔甲架当前穿在腿上的物品.
     * <p>原文：
     * Sets the item currently being worn by the armor stand on its legs.
     *
     * @param item 穿戴的物品
     * <p>原文：the item to wear
     * @see #getEquipment()
     * @deprecated prefer
     * {@link EntityEquipment#setLeggings(org.bukkit.inventory.ItemStack)}
     */
    @Deprecated(since = "1.15.2")
    void setLeggings(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回盔甲架当前穿在胸上的物品.
     * <p>原文：
     * Returns the item currently being worn by the armor stand on its chest.
     *
     * @return 穿戴的物品
     * <p>原文：the worn item
     * @see #getEquipment()
     * @deprecated prefer {@link EntityEquipment#getChestplate()}
     */
    @NotNull
    @Deprecated(since = "1.15.2")
    ItemStack getChestplate();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置盔甲架当前穿在胸上的物品.
     * <p>原文：
     * Sets the item currently being worn by the armor stand on its chest.
     *
     * @param item 穿戴的物品
     * <p>原文：the item to wear
     * @see #getEquipment()
     * @deprecated prefer
     * {@link EntityEquipment#setChestplate(org.bukkit.inventory.ItemStack)}
     */
    @Deprecated(since = "1.15.2")
    void setChestplate(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回盔甲架当前戴在头上的物品.
     * <p>原文：
     * Returns the item currently being worn by the armor stand on its head.
     *
     * @return 穿戴的物品
     * <p>原文：the worn item
     * @see #getEquipment()
     * @deprecated prefer {@link EntityEquipment#getHelmet()}
     */
    @NotNull
    @Deprecated(since = "1.15.2")
    ItemStack getHelmet();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置盔甲架当前戴在头上的物品.
     * <p>原文：
     * Sets the item currently being worn by the armor stand on its head.
     *
     * @param item 穿戴的物品
     * <p>原文：the item to wear
     * @see #getEquipment()
     * @deprecated prefer
     * {@link EntityEquipment#setHelmet(org.bukkit.inventory.ItemStack)}
     */
    @Deprecated(since = "1.15.2")
    void setHelmet(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式返回盔甲架身体的姿势.
     * <p>原文：
     * Returns the armor stand's body's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @return 当前姿势
     * <p>原文：the current pose
     */
    @NotNull
    EulerAngle getBodyPose();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式设置盔甲架身体的姿势.
     * <p>原文：
     * Sets the armor stand's body's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @param pose 当前姿势
     * <p>原文：the current pose
     */
    void setBodyPose(@NotNull EulerAngle pose);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式返回盔甲架左臂的姿势.
     * <p>原文：
     * Returns the armor stand's left arm's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @return 当前姿势
     * <p>原文：the current pose
     */
    @NotNull
    EulerAngle getLeftArmPose();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式设置盔甲架左臂的姿势.
     * <p>原文：
     * Sets the armor stand's left arm's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @param pose 当前姿势
     * <p>原文：the current pose
     */
    void setLeftArmPose(@NotNull EulerAngle pose);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式返回盔甲架右臂的姿势.
     * <p>原文：
     * Returns the armor stand's right arm's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @return 当前姿势
     * <p>原文：the current pose
     */
    @NotNull
    EulerAngle getRightArmPose();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式设置盔甲架右臂的姿势.
     * <p>原文：
     * Sets the armor stand's right arm's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @param pose 当前姿势
     * <p>原文：the current pose
     */
    void setRightArmPose(@NotNull EulerAngle pose);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式返回盔甲架左腿的姿势.
     * <p>原文：
     * Returns the armor stand's left leg's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @return 当前姿势
     * <p>原文：the current pose
     */
    @NotNull
    EulerAngle getLeftLegPose();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式设置盔甲架身左腿的姿势.
     * <p>原文：
     * Sets the armor stand's left leg's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @param pose 当前姿势
     * <p>原文：the current pose
     */
    void setLeftLegPose(@NotNull EulerAngle pose);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式返回盔甲架右腿的姿势.
     * <p>原文：
     * Returns the armor stand's right leg's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @return 当前姿势
     * <p>原文：the current pose
     */
    @NotNull
    EulerAngle getRightLegPose();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式设置盔甲架右腿的姿势.
     * <p>原文：
     * Sets the armor stand's right leg's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @param pose 当前姿势
     * <p>原文：the current pose
     */
    void setRightLegPose(@NotNull EulerAngle pose);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式返回盔甲架头的姿势.
     * <p>原文：
     * Returns the armor stand's head's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @return 当前姿势
     * <p>原文：the current pose
     */
    @NotNull
    EulerAngle getHeadPose();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以{@link org.bukkit.util.EulerAngle}的形式设置盔甲架头的姿势.
     * <p>原文：
     * Sets the armor stand's head's current pose as a
     * {@link org.bukkit.util.EulerAngle}.
     *
     * @param pose 当前姿势
     * <p>原文：the current pose
     */
    void setHeadPose(@NotNull EulerAngle pose);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回盔甲架是否有底盘.
     * <p>原文：
     * Returns whether the armor stand has a base plate.
     *
     * @return 是否有底盘
     * <p>原文：whether it has a base plate
     */
    boolean hasBasePlate();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置盔甲架是否有底盘.
     * <p>原文：
     * Sets whether the armor stand has a base plate.
     *
     * @param basePlate 是否有底盘
     * <p>原文：whether it has a base plate
     */
    void setBasePlate(boolean basePlate);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回盔甲架是否可视.
     * <p>原文：
     * Returns whether the armor stand should be visible or not.
     *
     * @return 盔甲架是否可视
     * <p>原文：whether the stand is visible or not
     */
    boolean isVisible();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置盔甲架是否可视.
     * <p>原文：
     * Sets whether the armor stand should be visible or not.
     *
     * @param visible 盔甲架是否可视
     * <p>原文：whether the stand is visible or not
     */
    void setVisible(boolean visible);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回盔甲架是否有双臂.
     * <p>原文：
     * Returns whether this armor stand has arms.
     *
     * @return 是否有双臂
     * <p>原文：whether this has arms or not
     */
    boolean hasArms();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置盔甲架是否有双臂.
     * <p>原文：
     * Sets whether this armor stand has arms.
     *
     * @param arms 是否有双臂
     * <p>原文：whether this has arms or not
     */
    void setArms(boolean arms);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回盔甲架是否被缩小了.
     * <p>原文：
     * Returns whether this armor stand is scaled down.
     *
     * @return 是否被缩小
     * <p>原文：whether this is scaled down
     */
    boolean isSmall();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置盔甲架是否被缩小.
     * <p>原文：
     * Sets whether this armor stand is scaled down.
     *
     * @param small 是否被缩小
     * <p>原文：whether this is scaled down
     */
    void setSmall(boolean small);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回盔甲架的marker属性，即是否具有非常小的碰撞箱.
     * <p>原文：
     * Returns whether this armor stand is a marker, meaning it has a very small
     * collision box.
     *
     * @return 盔甲架的marker属性
     * <p>原文：whether this is a marker
     */
    boolean isMarker();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置盔甲架的marker属性，即是否具有非常小的碰撞箱.
     * <p>原文：
     * Sets whether this armor stand is a marker, meaning it has a very small
     * collision box.
     *
     * @param marker 盔甲架的marker属性
     * <p>原文：whether this is a marker
     */
    void setMarker(boolean marker);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用指定的 {@link LockType 锁定机制} 锁定装备槽位.
     * <p>原文：
     * Locks the equipment slot with the specified
     * {@link LockType locking mechanism}.
     *
     * @param slot 要锁定的装备槽位
     * <p>原文：the equipment slot to lock
     * @param lockType 用于锁定装备槽位的 LockType
     * <p>原文：the LockType to lock the equipment slot with
     */
    void addEquipmentLock(@NotNull EquipmentSlot slot, @NotNull LockType lockType);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除 {@link LockType 锁定机制}.
     * <p>原文：
     * Remove a {@link LockType locking mechanism}.
     *
     * @param slot 要更改的装备槽位
     * <p>原文：the equipment slot to change
     * @param lockType 要移除的 LockType
     * <p>原文：the LockType to remove
     */
    void removeEquipmentLock(@NotNull EquipmentSlot slot, @NotNull LockType lockType);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此盔甲架是否具有指定的 {@link LockType 锁定机制}.
     * <p>原文：
     * Returns if the ArmorStand has the specified
     * {@link LockType locking mechanism}.
     *
     * @param slot 要检测的装备槽位
     * <p>原文：the EquipmentSlot to test
     * @param lockType 要检测的 LockType
     * <p>原文：the LockType to test
     * @return 此盔甲架是否已被指定参数锁定
     * <p>原文：if the ArmorStand has been locked with the parameters specified
     */
    boolean hasEquipmentLock(@NotNull EquipmentSlot slot, @NotNull LockType lockType);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表盔甲架装备的锁定机制类型.
     * <p>原文：
     * Represents types of locking mechanisms for ArmorStand equipment.
     */
    public enum LockType {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 阻止添加或更改相应装备 - 玩家无法用新物品替换空槽位,
         * 也无法在自身与盔甲架之间交换物品.
         * <p>原文：
         * Prevents adding or changing the respective equipment - players cannot
         * replace the empty slot with a new item or swap the items between
         * themselves and the ArmorStand.
         */
        ADDING_OR_CHANGING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 阻止移除或更改相应装备 - 玩家无法从槽位取出物品,
         * 也无法在自身与盔甲架之间交换物品.
         * <p>原文：
         * Prevents removing or changing the respective equipment - players
         * cannot take an item from the slot or swap the items between
         * themselves and the ArmorStand.
         */
        REMOVING_OR_CHANGING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 阻止添加相应装备 - 玩家无法用新物品替换空槽位,
         * 但可以在自身与盔甲架之间交换物品.
         * <p>原文：
         * Prevents adding the respective equipment - players cannot replace the
         * empty slot with a new item, but can swap items between themselves and
         * the ArmorStand.
         */
        ADDING;
    }
    // Paper start
    /**
     * Tests if this armor stand can move.
     *
     * <p>The default value is {@code true}.</p>
     *
     * @return {@code true} if this armour stand can move, {@code false} otherwise
     */
    boolean canMove();

    /**
     * Sets if this armor stand can move.
     *
     * @param move {@code true} if this armour stand can move, {@code false} otherwise
     */
    void setCanMove(boolean move);

    @Override
    org.bukkit.inventory.@NotNull EntityEquipment getEquipment();

    /**
     * Tests if this armor stand can tick.
     *
     * <p>The default value is defined in {@code paper.yml}.</p>
     *
     * @return {@code true} if this armour stand can tick, {@code false} otherwise
     */
    boolean canTick();

    /**
     * Sets if this armor stand can tick.
     *
     * @param tick {@code true} if this armour stand can tick, {@code false} otherwise
     */
    void setCanTick(final boolean tick);

    /**
     * Returns the item the armor stand has
     * equipped in the given equipment slot
     *
     * @param slot the equipment slot to get
     * @return the ItemStack in the equipment slot
     * @throws IllegalArgumentException if the slot is invalid for the entity
     */
    @NotNull
    ItemStack getItem(@NotNull final org.bukkit.inventory.EquipmentSlot slot);

    /**
     * Sets the item the armor stand has
     * equipped in the given equipment slot
     *
     * @param slot the equipment slot to set
     * @param item the item to hold
     * @throws IllegalArgumentException if the slot is invalid for the entity
     */
    void setItem(@NotNull final org.bukkit.inventory.EquipmentSlot slot, @Nullable final ItemStack item);

    /**
     * Get the list of disabled slots
     *
     * @return list of disabled slots
     */
    @NotNull
    java.util.Set<org.bukkit.inventory.EquipmentSlot> getDisabledSlots();

    /**
     * Set the disabled slots
     *
     * This makes it so a player is unable to interact with the Armor Stand to place, remove, or replace an item in the given slot(s)
     * Note: Once a slot is disabled, the only way to get an item back it to break the armor stand.
     *
     * @param slots var-arg array of slots to lock
     */
    void setDisabledSlots(@NotNull org.bukkit.inventory.EquipmentSlot... slots);

    /**
     * Disable specific slots, adding them
     * to the currently disabled slots
     *
     * This makes it so a player is unable to interact with the Armor Stand to place, remove, or replace an item in the given slot(s)
     * Note: Once a slot is disabled, the only way to get an item back it to break the armor stand.
     *
     * @param slots var-arg array of slots to lock
     */
    void addDisabledSlots(@NotNull final org.bukkit.inventory.EquipmentSlot... slots);

    /**
     * Remove the given slots from the disabled
     * slots list, enabling them.
     *
     * This makes it so a player is able to interact with the Armor Stand to place, remove, or replace an item in the given slot(s)
     *
     * @param slots var-arg array of slots to unlock
     */
    void removeDisabledSlots(@NotNull final org.bukkit.inventory.EquipmentSlot... slots);

    /**
     * Check if a specific slot is disabled
     *
     * @param slot The slot to check
     * @return {@code true} if the slot is disabled, else {@code false}.
     */
    boolean isSlotDisabled(@NotNull org.bukkit.inventory.EquipmentSlot slot);

    /**
     * Returns the ArmorStand's body rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @return the current rotations
     */
    @NotNull io.papermc.paper.math.Rotations getBodyRotations();

    /**
     * Sets the ArmorStand's body rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @param rotations the current rotations
     */
    void setBodyRotations(@NotNull io.papermc.paper.math.Rotations rotations);

    /**
     * Returns the ArmorStand's left arm rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @return the current rotations
     */
    @NotNull io.papermc.paper.math.Rotations getLeftArmRotations();

    /**
     * Sets the ArmorStand's left arm rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @param rotations the current rotations
     */
    void setLeftArmRotations(@NotNull io.papermc.paper.math.Rotations rotations);

    /**
     * Returns the ArmorStand's right arm rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @return the current rotations
     */
    @NotNull io.papermc.paper.math.Rotations getRightArmRotations();

    /**
     * Sets the ArmorStand's right arm rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @param rotations the current rotations
     */
    void setRightArmRotations(@NotNull io.papermc.paper.math.Rotations rotations);

    /**
     * Returns the ArmorStand's left leg rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @return the current rotations
     */
    @NotNull io.papermc.paper.math.Rotations getLeftLegRotations();

    /**
     * Sets the ArmorStand's left leg rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @param rotations the current rotations
     */
    void setLeftLegRotations(@NotNull io.papermc.paper.math.Rotations rotations);

    /**
     * Returns the ArmorStand's right leg rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @return the current rotations
     */
    @NotNull io.papermc.paper.math.Rotations getRightLegRotations();

    /**
     * Sets the ArmorStand's right leg rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @param rotations the current rotations
     */
    void setRightLegRotations(@NotNull io.papermc.paper.math.Rotations rotations);

    /**
     * Returns the ArmorStand's head rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @return the current rotations
     */
    @NotNull io.papermc.paper.math.Rotations getHeadRotations();

    /**
     * Sets the ArmorStand's head rotations as
     * {@link io.papermc.paper.math.Rotations}.
     *
     * @param rotations the current rotations
     */
    void setHeadRotations(@NotNull io.papermc.paper.math.Rotations rotations);
    // Paper end
}
