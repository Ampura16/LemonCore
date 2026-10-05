package org.bukkit.inventory;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 生物的背包界面接口
 * <p>原文：
 * An interface to a creatures inventory
 */
public interface EntityEquipment {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将给定的 ItemStack 存储到物品栏中指定的装备槽位.
     * <p>原文：
     * Stores the ItemStack at the given equipment slot in the inventory.
     *
     * @param slot 要放置物品的槽位
     * <p>原文：the slot to put the ItemStack
     * @param item 要设置的物品
     * <p>原文：the ItemStack to set
     * @throws IllegalArgumentException if the slot is invalid for the entity
     * @see org.bukkit.entity.LivingEntity#canUseEquipmentSlot(EquipmentSlot)
     */
    public void setItem(@NotNull EquipmentSlot slot, @Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将给定的 ItemStack 存储到物品栏中指定的装备槽位.
     * <p>原文：
     * Stores the ItemStack at the given equipment slot in the inventory.
     *
     * @param slot 要放置物品的槽位
     * <p>原文：the slot to put the ItemStack
     * @param item 要设置的物品
     * <p>原文：the ItemStack to set
     * @param silent 是否播放装备动作的声音
     * <p>原文：whether the equip sound should be silenced
     * @throws IllegalArgumentException if the slot is invalid for the entity
     * @see org.bukkit.entity.LivingEntity#canUseEquipmentSlot(EquipmentSlot)
     */
    public void setItem(@NotNull EquipmentSlot slot, @Nullable ItemStack item, boolean silent);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品栏中指定装备槽位的 ItemStack.
     * <p>原文：
     * Gets the ItemStack at the given equipment slot in the inventory.
     *
     * @param slot 要获取物品的槽位
     * <p>原文：the slot to get the ItemStack
     * @return 指定槽位中的物品
     * <p>原文：the ItemStack in the given slot
     * @throws IllegalArgumentException if the slot is invalid for the entity
     * @see org.bukkit.entity.LivingEntity#canUseEquipmentSlot(EquipmentSlot)
     */
    @NotNull
    public ItemStack getItem(@NotNull EquipmentSlot slot);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体当前主手中手持的物品的拷贝.
     * <p>原文：
     * Gets the item the entity is currently holding
     * in their main hand.
     *
     * <p>
     * This returns a copy if this equipment instance is from a non-player,
     * or it's an empty stack (has AIR as its type).
     * For non-empty stacks from players, this returns a live mirror. You can check if this
     * will return a mirror with
     * <pre>{@code
     * EntityEquipment equipment = entity.getEquipment();
     * if (equipment instanceof PlayerInventory) {
     *     equipment.getItemInMainHand(); // will return a mirror
     * } else {
     *     equipment.getItemInMainHand(); // will return a copy
     * }
     * }</pre>
     *
     * @return 当前主手手持的物品
     * <p>原文：the currently held item
     */
    @NotNull
    ItemStack getItemInMainHand();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体在主手中手持的物品.
     * <p>原文：
     * Sets the item the entity is holding in their main hand.
     *
     * @param item 需要设置的物品
     * <p>原文：The item to put into the entities hand
     */
    void setItemInMainHand(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体在主手中手持的物品.
     * <p>原文：
     * Sets the item the entity is holding in their main hand.
     *
     * @param item 需要设置的物品
     * <p>原文：The item to put into the entities hand
     * @param silent 是否播放装备动作的声音
     * <p>原文：whether or not the equip sound should be silenced
     */
    void setItemInMainHand(@Nullable ItemStack item, boolean silent);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体当前副手中手持的物品的拷贝.
     * <p>原文：
     * Gets the item the entity is currently holding
     * in their off hand.
     *
     * <p>
     * This returns a copy if this equipment instance is from a non-player,
     * or it's an empty stack (has AIR as its type).
     * For non-empty stacks from players, this returns a live mirror. You can check if this
     * will return a mirror with
     * <pre>{@code
     * EntityEquipment equipment = entity.getEquipment();
     * if (equipment instanceof PlayerInventory) {
     *     equipment.getItemInOffHand(); // will return a mirror
     * } else {
     *     equipment.getItemInOffHand(); // will return a copy
     * }
     * }</pre>
     *
     * @return 当前副手手持的物品
     * <p>原文：the currently held item
     */
    @NotNull
    ItemStack getItemInOffHand();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体在副手中手持的物品.
     * <p>原文：
     * Sets the item the entity is holding in their off hand.
     *
     * @param item 需要设置的物品
     * <p>原文：The item to put into the entities hand
     */
    void setItemInOffHand(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体在副手中手持的物品.
     * <p>原文：
     * Sets the item the entity is holding in their off hand.
     *
     * @param item 需要设置的物品
     * <p>原文：The item to put into the entities hand
     * @param silent 是否播放装备动作的声音
     * <p>原文：whether or not the equip sound should be silenced
     */
    void setItemInOffHand(@Nullable ItemStack item, boolean silent);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体当前所持物品的拷贝.
     * <p>
     * 译注: 反编译其实现, 发现其也是获取主手的物品.
     * <p>原文：
     * Gets the item the entity is currently holding
     *
     * <p>
     * This returns a copy if this equipment instance is from a non-player,
     * or it's an empty stack (has AIR as its type).
     * For non-empty stacks from players, this returns a live mirror. You can check if this
     * will return a mirror with
     * <pre>{@code
     * EntityEquipment equipment = entity.getEquipment();
     * if (equipment instanceof PlayerInventory) {
     *     equipment.getItemInHand(); // will return a mirror
     * } else {
     *     equipment.getItemInHand(); // will return a copy
     * }
     * }</pre>
     *
     * @return the currently held item
     * @see #getItemInMainHand()
     * @see #getItemInOffHand()
     * @deprecated 现在实体可以双持, 所以需要特定的方法来获取指定的手
     * <p>原文：entities can duel wield now use the methods for the
     *      specific hand instead
     */
    @Deprecated(since = "1.9")
    @NotNull
    ItemStack getItemInHand();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体当前所持的物品.
     * <p>
     * 译注: 反编译其实现, 发现其也是设置主手的物品.
     * <p>原文：
     * Sets the item the entity is holding
     *
     * @param stack 需要设置的物品
     * <p>原文：The item to put into the entities hand
     * @see #setItemInMainHand(ItemStack)
     * @see #setItemInOffHand(ItemStack)
     * @deprecated 现在实体可以双持, 所以需要特定的方法来获取指定的手
     * <p>原文：entities can duel wield now use the methods for the
     *      specific hand instead
     */
    @Deprecated(since = "1.9")
    void setItemInHand(@Nullable ItemStack stack);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该实体当前正在佩戴的头盔的拷贝.
     * <p>原文：
     * Gets the helmet currently being worn by the entity
     *
     * <p>
     * This returns a copy if this equipment instance is from a non-player.
     * For stacks from players, this returns a live mirror (or null). You can check if this
     * will return a mirror with
     * <pre>{@code
     * EntityEquipment equipment = entity.getEquipment();
     * if (equipment instanceof PlayerInventory) {
     *     equipment.getHelmet(); // will return a mirror
     * } else {
     *     equipment.getHelmet(); // will return a copy
     * }
     * }</pre>
     *
     * @return 所佩戴的头盔
     * <p>原文：The helmet being worn
     */
    @org.bukkit.UndefinedNullability("not null for entities, nullable for players") // Paper
    ItemStack getHelmet();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该实体当前正在佩戴的头盔.
     * <p>原文：
     * Sets the helmet worn by the entity
     *
     * @param helmet 给定的头盔
     * <p>原文：The helmet to put on the entity
     */
    void setHelmet(@Nullable ItemStack helmet);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该实体当前正在佩戴的头盔.
     * <p>原文：
     * Sets the helmet worn by the entity
     *
     * @param helmet 给定的头盔
     * <p>原文：The helmet to put on the entity
     * @param silent 是否播放装备动作的声音
     * <p>原文：whether or not the equip sound should be silenced
     */
    void setHelmet(@Nullable ItemStack helmet, boolean silent);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该实体当前正在穿戴的胸甲的拷贝.
     * <p>原文：
     * Gets the chest plate currently being worn by the entity
     *
     * <p>
     * This returns a copy if this equipment instance is from a non-player.
     * For stacks from players, this returns a live mirror (or null). You can check if this
     * will return a mirror with
     * <pre>{@code
     * EntityEquipment equipment = entity.getEquipment();
     * if (equipment instanceof PlayerInventory) {
     *     equipment.getChestplate(); // will return a mirror
     * } else {
     *     equipment.getChestplate(); // will return a copy
     * }
     * }</pre>
     *
     * @return 实体所穿戴的胸甲
     * <p>原文：The chest plate being worn
     */
    @org.bukkit.UndefinedNullability("not null for entities, nullable for players") // Paper
    ItemStack getChestplate();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该实体当前正在穿戴的胸甲.
     * <p>原文：
     * Sets the chest plate worn by the entity
     *
     * @param chestplate 给定的胸甲
     * <p>原文：The chest plate to put on the entity
     */
    void setChestplate(@Nullable ItemStack chestplate);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该实体当前正在穿戴的胸甲.
     * <p>原文：
     * Sets the chest plate worn by the entity
     *
     * @param chestplate 给定的胸甲
     * <p>原文：The chest plate to put on the entity
     * @param silent 是否播放装备动作的声音
     * <p>原文：whether or not the equip sound should be silenced
     */
    void setChestplate(@Nullable ItemStack chestplate, boolean silent);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该实体当前正在穿戴的护腿的拷贝.
     * <p>原文：
     * Gets the leggings currently being worn by the entity
     *
     * <p>
     * This returns a copy if this equipment instance is from a non-player.
     * For stacks from players, this returns a live mirror (or null). You can check if this
     * will return a mirror with
     * <pre>{@code
     * EntityEquipment equipment = entity.getEquipment();
     * if (equipment instanceof PlayerInventory) {
     *     equipment.getLeggings(); // will return a mirror
     * } else {
     *     equipment.getLeggings(); // will return a copy
     * }
     * }</pre>
     *
     * @return 实体所穿戴的护腿
     * <p>原文：The leggings being worn
     */
    @org.bukkit.UndefinedNullability("not null for entities, nullable for players") // Paper
    ItemStack getLeggings();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该实体当前正在穿戴的护腿.
     * <p>原文：
     * Sets the leggings worn by the entity
     *
     * @param leggings 给定的护腿
     * <p>原文：The leggings to put on the entity
     */
    void setLeggings(@Nullable ItemStack leggings);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该实体当前正在穿戴的护腿.
     * <p>原文：
     * Sets the leggings worn by the entity
     *
     * @param leggings 给定的护腿
     * <p>原文：The leggings to put on the entity
     * @param silent 是否播放装备动作的声音
     * <p>原文：whether or not the equip sound should be silenced
     */
    void setLeggings(@Nullable ItemStack leggings, boolean silent);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该实体当前正在穿戴的鞋子的拷贝.
     * <p>原文：
     * Gets the boots currently being worn by the entity
     *
     * <p>
     * This returns a copy if this equipment instance is from a non-player.
     * For stacks from players, this returns a live mirror (or null). You can check if this
     * will return a mirror with
     * <pre>{@code
     * EntityEquipment equipment = entity.getEquipment();
     * if (equipment instanceof PlayerInventory) {
     *     equipment.getBoots(); // will return a mirror
     * } else {
     *     equipment.getBoots(); // will return a copy
     * }
     * }</pre>
     *
     * @return 实体所穿戴的鞋子
     * <p>原文：The boots being worn
     */
    @org.bukkit.UndefinedNullability("not null for entities, nullable for players") // Paper
    ItemStack getBoots();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该实体当前正在穿戴的鞋子.
     * <p>原文：
     * Sets the boots worn by the entity
     *
     * @param boots 给定的鞋子
     * <p>原文：The boots to put on the entity
     */
    void setBoots(@Nullable ItemStack boots);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该实体当前正在穿戴的鞋子.
     * <p>原文：
     * Sets the boots worn by the entity
     *
     * @param boots 给定的鞋子
     * <p>原文：The boots to put on the entity
     * @param silent 是否播放装备动作的声音
     * <p>原文：whether or not the equip sound should be silenced
     */
    void setBoots(@Nullable ItemStack boots, boolean silent);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得实体当前所有穿着盔甲的拷贝.
     * <p>
     * 译注: 其顺序为  鞋子、护腿、胸甲、头盔.
     * <p>原文：
     * Gets all ItemStacks from the armor slots.
     *
     * <p>
     * This returns a copy if this equipment instance is from a non-player,
     * or it's an empty stack (has AIR as its type).
     * For non-empty stacks from players, this returns a live mirror. You can check if this
     * will return a mirror with
     * <pre>{@code
     * EntityEquipment equipment = entity.getEquipment();
     * if (equipment instanceof PlayerInventory) {
     *     equipment.getArmorContents(); // will return an array of mirror
     * } else {
     *     equipment.getArmorContents(); // will return an array of copies
     * }
     * }</pre>
     *
     * @return all the ItemStacks from the armor slots. Individual items can be
     * null and are returned in a fixed order starting from the boots and going
     * up to the helmet
     */
    @org.bukkit.UndefinedNullability("not null elements for entities, nullable elements for players") ItemStack @NotNull [] getArmorContents(); // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将实体所穿戴的盔甲设置为提供的ItemStacks数组.
     * <p>
     * 译注:
     * <ul>
     * <li>该ItemStack数组的顺序应为  鞋子、护腿、胸甲、头盔
     * <li>当数组长度不够4个时, 将会把剩下的设置为null, 如为2时,胸甲和头盔将会自动设置为null
     * </ul>
     * <p>原文：
     * Sets the entities armor to the provided array of ItemStacks
     *
     * @param items 给定的盔甲数组. 个别条目可以为null
     * <p>原文：The items to set the armor as. Individual items may be null.
     */
    void setArmorContents(@NotNull ItemStack @NotNull [] items);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 清除实体所有的盔甲和手持的物品.
     * <p>原文：
     * Clears the entity of all armor and held items
     */
    void clear();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 掉落的几率
     * <p>原文：drop chance
     * @see #getItemInMainHandDropChance()
     * @see #getItemInOffHandDropChance()
     * @deprecated 现在实体可以双持, 所以需要特定的方法来设置指定的手
     * <p>原文：entities can duel wield now use the methods for the specific
     * hand instead
     */
    @Deprecated(since = "1.9")
    float getItemInHandDropChance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param chance 给定的掉落几率
     * <p>原文：drop chance
     * @see #setItemInMainHandDropChance(float)
     * @see #setItemInOffHandDropChance(float)
     * @deprecated 现在实体可以双持, 所以需要特定的方法来设置指定的手
     * <p>原文：entities can duel wield now use the methods for the specific
     * hand instead
     */
    @Deprecated(since = "1.9")
    void setItemInHandDropChance(float chance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得该生物在死亡时掉落其主手的物品的几率.
     * <p>原文：
     * Gets the chance of the main hand item being dropped upon this creature's
     * death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @return 当前生物在死亡时掉落主手物品的几率(在原版里, 对于非{@link Mob}类实体来说是1.0F)
     * <p>原文：chance of the currently held item being dropped (1 for non-{@link Mob})
     */
    float getItemInMainHandDropChance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该生物在死亡时掉落其主手的物品的几率.
     * <p>原文：
     * Sets the chance of the item this creature is currently holding in their
     * main hand being dropped upon this creature's death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @param chance 主手掉落物品的几率
     * <p>原文：the chance of the main hand item being dropped
     * @throws UnsupportedOperationException 当这一操作作用在非{@link Mob}类实体时抛出此异常
     * <p>原文：when called on non-{@link Mob}
     */
    void setItemInMainHandDropChance(float chance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得该生物在死亡时掉落其副手的物品的几率.
     * <p>原文：
     * Gets the chance of the off hand item being dropped upon this creature's
     * death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @return 当前生物在死亡时掉落副手物品的几率(在原版里, 对于非{@link Mob}类实体来说是1.0F)
     * <p>原文：chance of the off hand item being dropped (1 for non-{@link Mob})
     */
    float getItemInOffHandDropChance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该生物在死亡时掉落其副手的物品的几率.
     * <p>原文：
     * Sets the chance of the off hand item being dropped upon this creature's
     * death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @param chance 副手掉落物品的几率
     * <p>原文：the chance of off hand item being dropped
     * @throws UnsupportedOperationException 当这一操作作用在非{@link Mob}类实体时抛出此异常
     * <p>原文：when called on non-{@link Mob}
     */
    void setItemInOffHandDropChance(float chance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得该生物在死亡时掉落其头盔的几率.
     * <p>原文：
     * Gets the chance of the helmet being dropped upon this creature's death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @return 当前生物在死亡时掉落头盔的几率(在原版里, 对于非{@link Mob}类实体来说是1.0F)
     * <p>原文：the chance of the helmet being dropped (1 for non-{@link Mob})
     */
    float getHelmetDropChance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该生物在死亡时掉落其头盔的几率.
     * <p>原文：
     * Sets the chance of the helmet being dropped upon this creature's death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @param chance 掉落头盔的几率
     * <p>原文：of the helmet being dropped
     * @throws UnsupportedOperationException 当这一操作作用在非{@link Mob}类实体时抛出此异常
     * <p>原文：when called on non-{@link Mob}
     */
    void setHelmetDropChance(float chance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得该生物在死亡时掉落其胸甲的几率.
     * <p>原文：
     * Gets the chance of the chest plate being dropped upon this creature's
     * death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @return 当前生物在死亡时掉落胸甲的几率(在原版里, 对于非{@link Mob}类实体来说是1.0F)
     * <p>原文：the chance of the chest plate being dropped (1 for non-{@link Mob})
     */
    float getChestplateDropChance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该生物在死亡时掉落其胸甲的几率.
     * <p>原文：
     * Sets the chance of the chest plate being dropped upon this creature's
     * death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @param chance 掉落胸甲的几率
     * <p>原文：of the chest plate being dropped
     * @throws UnsupportedOperationException 当这一操作作用在非{@link Mob}类实体时抛出此异常
     * <p>原文：when called on non-{@link Mob}
     */
    void setChestplateDropChance(float chance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得该生物在死亡时掉落其护腿的几率.
     * <p>原文：
     * Gets the chance of the leggings being dropped upon this creature's
     * death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @return 当前生物在死亡时掉落护腿的几率(在原版里, 对于非{@link Mob}类实体来说是1.0F)
     * <p>原文：the chance of the leggings being dropped (1 for non-{@link Mob})
     */
    float getLeggingsDropChance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该生物在死亡时掉落其护腿的几率.
     * <p>原文：
     * Sets the chance of the leggings being dropped upon this creature's
     * death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @param chance 掉落护腿的几率
     * <p>原文：chance of the leggings being dropped
     * @throws UnsupportedOperationException 当这一操作作用在非{@link Mob}类实体时抛出此异常
     * <p>原文：when called on non-{@link Mob}
     */
    void setLeggingsDropChance(float chance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得该生物在死亡时掉落其鞋子的几率.
     * <p>原文：
     * Gets the chance of the boots being dropped upon this creature's death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @return 当前生物在死亡时掉落鞋子的几率(在原版里, 对于非{@link Mob}类实体来说是1.0F)
     * <p>原文：the chance of the boots being dropped (1 for non-{@link Mob})
     */
    float getBootsDropChance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该生物在死亡时掉落其鞋子的几率.
     * <p>原文：
     * Sets the chance of the boots being dropped upon this creature's death.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of exactly 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @param chance 掉落鞋子的几率
     * <p>原文：of the boots being dropped
     * @throws UnsupportedOperationException 当这一操作作用在非{@link Mob}类实体时抛出此异常
     * <p>原文：when called on non-{@link Mob}
     */
    void setBootsDropChance(float chance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此EntityEquipment所属的实体.
     * <p>原文：
     * Get the entity this EntityEquipment belongs to
     *
     * @return 这个EntityEquipment所属的实体
     * <p>原文：the entity this EntityEquipment belongs to
     */
    @NotNull // Paper
    Entity getHolder();
    // Paper start
    /**
     * Gets the drop chance of specified slot.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @param slot the slot to get the drop chance of
     * @return the drop chance for the slot
     */
    float getDropChance(@NotNull EquipmentSlot slot);

    /**
     * Sets the drop chance of the specified slot.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of 1.0F will always drop if killed by a player
     * <li>A drop chance of greater than 1.0F will always drop if killed by anything
     * </ul>
     *
     * @param slot the slot to set the drop chance of
     * @param chance the drop chance for the slot
     * @throws UnsupportedOperationException when called on non-{@link Mob} entities
     */
    void setDropChance(@NotNull EquipmentSlot slot, float chance);
    // Paper end
}
