package org.bukkit.event.inventory;

import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.MenuType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示 Bukkit 中可用的不同类型的物品栏。
 * <br>
 * 只有标记为 {@link #isCreatable()} 的 InventoryType 才能被创建。
 * <br>
 * 当前无法通过 {@link org.bukkit.Bukkit#createInventory} 创建的物品栏列表是：<br>
 * <blockquote>
 *     {@link InventoryType#CREATIVE}、{@link InventoryType#CRAFTING} 和
 *     {@link InventoryType#MERCHANT}
 * </blockquote>
 *
 * 有关更多信息，请参阅 {@link org.bukkit.Bukkit#createInventory}。
 * <p>原文：
 * Represents the different kinds of inventories available in Bukkit.
 * <br>
 * Only InventoryTypes marked {@link #isCreatable()} can be created.
 * <br>
 * The current list of inventories that cannot be created via
 * {@link org.bukkit.Bukkit#createInventory} are:<br>
 * <blockquote>
 *     {@link InventoryType#CREATIVE}, {@link InventoryType#CRAFTING} and
 *     {@link InventoryType#MERCHANT}
 * </blockquote>
 *
 * See {@link org.bukkit.Bukkit#createInventory} for more information.
 *
 * @see org.bukkit.Bukkit#createInventory(InventoryHolder, InventoryType)
 */
public enum InventoryType {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 箱子物品栏，具有 0、9、18、27、36、45 或 54 个 CONTAINER 类型的槽位。
     * <p>原文：
     * A chest inventory, with 0, 9, 18, 27, 36, 45, or 54 slots of type
     * CONTAINER.
     */
    CHEST(27, "Chest", MenuType.GENERIC_9X3),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发射器物品栏，具有 9 个 CONTAINER 类型的槽位。
     * <p>原文：
     * A dispenser inventory, with 9 slots of type CONTAINER.
     */
    DISPENSER(9, "Dispenser", MenuType.GENERIC_3X3),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 投掷器物品栏，具有 9 个 CONTAINER 类型的槽位。
     * <p>原文：
     * A dropper inventory, with 9 slots of type CONTAINER.
     */
    DROPPER(9, "Dropper", MenuType.GENERIC_3X3),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 熔炉物品栏，具有一个 RESULT 槽位、一个 CRAFTING 槽位和一个 FUEL 槽位。
     * <p>原文：
     * A furnace inventory, with a RESULT slot, a CRAFTING slot, and a FUEL
     * slot.
     */
    FURNACE(3, "Furnace", MenuType.FURNACE),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 工作台物品栏，具有 9 个 CRAFTING 槽位和一个 RESULT 槽位。
     * <p>原文：
     * A workbench inventory, with 9 CRAFTING slots and a RESULT slot.
     */
    WORKBENCH(10, "Crafting", MenuType.CRAFTING),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家的合成物品栏，具有 4 个 CRAFTING 槽位和一个 RESULT 槽位。同时意味着 4 个 ARMOR 槽位是可访问的。
     * <p>原文：
     * A player's crafting inventory, with 4 CRAFTING slots and a RESULT slot.
     * Also implies that the 4 ARMOR slots are accessible.
     */
    CRAFTING(5, "Crafting", null, false),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 附魔台物品栏，具有两个 CRAFTING 槽位和三个附魔按钮。
     * <p>原文：
     * An enchantment table inventory, with two CRAFTING slots and three
     * enchanting buttons.
     */
    ENCHANTING(2, "Enchanting", MenuType.ENCHANTMENT),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 酿造台物品栏，具有一个 FUEL 槽位和四个 CRAFTING 槽位。
     * <p>原文：
     * A brewing stand inventory, with one FUEL slot and four CRAFTING slots.
     */
    BREWING(5, "Brewing", MenuType.BREWING_STAND),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家的物品栏，具有 9 个 QUICKBAR 槽位、27 个 CONTAINER 槽位、4 个 ARMOR 槽位和 1 个副手槽位。但 ARMOR 和副手槽位可能对玩家不可见。
     * <p>原文：
     * A player's inventory, with 9 QUICKBAR slots, 27 CONTAINER slots, 4 ARMOR
     * slots, 1 offhand slot, 1 body slot and 1 saddle slot.
     * <p>
     * The ARMOR and offhand slots are conditionally visible to the player,
     * while body and saddle slot are never visible.
     */
    PLAYER(43, "Player", MenuType.GENERIC_9X4),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创造模式物品栏，只有 9 个 QUICKBAR 槽位，没有其他。（实际的创造界面物品是客户端的，无法由服务器更改。）
     * <p>原文：
     * The creative mode inventory, with only 9 QUICKBAR slots and nothing
     * else. (The actual creative interface with the items is client-side and
     * cannot be altered by the server.)
     */
    CREATIVE(9, "Creative", null, false),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 商人物品栏，具有 2 个 CRAFTING 槽位和 1 个 RESULT 槽位。
     * <p>原文：
     * The merchant inventory, with 2 CRAFTING slots, and 1 RESULT slot.
     */
    MERCHANT(3, "Villager", MenuType.MERCHANT, false),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 末影箱物品栏，具有 27 个槽位。
     * <p>原文：
     * The ender chest inventory, with 27 slots.
     */
    ENDER_CHEST(27, "Ender Chest", MenuType.GENERIC_9X3),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 铁砧物品栏，具有 2 个 CRAFTING 槽位和 1 个 RESULT 槽位
     * <p>原文：
     * An anvil inventory, with 2 CRAFTING slots and 1 RESULT slot
     */
    ANVIL(3, "Repairing", MenuType.ANVIL),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 锻造台物品栏，具有 3 个 CRAFTING 槽位和 1 个 RESULT 槽位。
     * <p>原文：
     * A smithing inventory, with 3 CRAFTING slots and 1 RESULT slot.
     */
    SMITHING(4, "Upgrade Gear", MenuType.SMITHING),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 信标物品栏，具有 1 个 CRAFTING 槽位
     * <p>原文：
     * A beacon inventory, with 1 CRAFTING slot
     */
    BEACON(1, "container.beacon", MenuType.BEACON),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 漏斗物品栏，具有 5 个 CONTAINER 类型的槽位。
     * <p>原文：
     * A hopper inventory, with 5 slots of type CONTAINER.
     */
    HOPPER(5, "Item Hopper", MenuType.HOPPER),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 潜影盒物品栏，具有 27 个 CONTAINER 类型的槽位。
     * <p>原文：
     * A shulker box inventory, with 27 slots of type CONTAINER.
     */
    SHULKER_BOX(27, "Shulker Box", MenuType.SHULKER_BOX),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 桶物品栏，具有 27 个 CONTAINER 类型的槽位。
     * <p>原文：
     * A barrel box inventory, with 27 slots of type CONTAINER.
     */
    BARREL(27, "Barrel", MenuType.GENERIC_9X3),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 高炉物品栏，具有一个 RESULT 槽位、一个 CRAFTING 槽位和一个 FUEL 槽位。
     * <p>原文：
     * A blast furnace inventory, with a RESULT slot, a CRAFTING slot, and a
     * FUEL slot.
     */
    BLAST_FURNACE(3, "Blast Furnace", MenuType.BLAST_FURNACE),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 讲台物品栏，具有 1 个 BOOK 槽位。
     * <p>原文：
     * A lectern inventory, with 1 BOOK slot.
     */
    LECTERN(1, "Lectern", MenuType.LECTERN),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 烟熏炉物品栏，具有一个 RESULT 槽位、一个 CRAFTING 槽位和一个 FUEL 槽位。
     * <p>原文：
     * A smoker inventory, with a RESULT slot, a CRAFTING slot, and a FUEL slot.
     */
    SMOKER(3, "Smoker", MenuType.SMOKER),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 织布机物品栏，具有 3 个 CRAFTING 槽位和 1 个 RESULT 槽位。
     * <p>原文：
     * Loom inventory, with 3 CRAFTING slots, and 1 RESULT slot.
     */
    LOOM(4, "Loom", MenuType.LOOM),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 制图台物品栏，具有 2 个 CRAFTING 槽位和 1 个 RESULT 槽位。
     * <p>原文：
     * Cartography inventory with 2 CRAFTING slots, and 1 RESULT slot.
     */
    CARTOGRAPHY(3, "Cartography Table", MenuType.CARTOGRAPHY_TABLE),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 砂轮物品栏，具有 2 个 CRAFTING 槽位和 1 个 RESULT 槽位。
     * <p>原文：
     * Grindstone inventory with 2 CRAFTING slots, and 1 RESULT slot.
     */
    GRINDSTONE(3, "Repair & Disenchant", MenuType.GRINDSTONE),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 切石机物品栏，具有 1 个 CRAFTING 槽位和 1 个 RESULT 槽位。
     * <p>原文：
     * Stonecutter inventory with 1 CRAFTING slot, and 1 RESULT slot.
     */
    STONECUTTER(2, "Stonecutter", MenuType.STONECUTTER),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 伪堆肥桶物品栏，具有 0 或 1 个未定义类型的槽位。
     * <p>原文：
     * Pseudo composter inventory with 0 or 1 slots of undefined type.
     */
    COMPOSTER(1, "Composter", null, false),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 伪雕刻书架物品栏，具有 6 个未定义类型的槽位。
     * <p>原文：
     * Pseudo chiseled bookshelf inventory, with 6 slots of undefined type.
     */
    CHISELED_BOOKSHELF(6, "Chiseled Bookshelf", null, false),
    /**
     * Pseudo shelf inventory, with 3 slots of undefined type.
     */
    SHELF(3, "Shelf", null, false),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 伪唱片机物品栏，具有 1 个未定义类型的槽位。
     * <p>原文：
     * Pseudo jukebox inventory with 1 slot of undefined type.
     */
    JUKEBOX(1, "Jukebox", null, false),
    // Paper start - add missing type
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 伪装饰罐物品栏，具有 1 个未定义类型的槽位。
     * <p>原文：
     * Pseudo decorated pot with 1 slot of undefined type.
     */
    DECORATED_POT(1, "Decorated Pot", null, false),
    // Paper end - add missing type
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 合成器物品栏，具有 9 个 CRAFTING 槽位。
     * <p>原文：
     * A crafter inventory, with 9 CRAFTING slots.
     */
    CRAFTER(9, "Crafter", MenuType.CRAFTER_3X3),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 新的锻造台物品栏，具有 3 个 CRAFTING 槽位和 1 个 RESULT 槽位。
     * <p>原文：
     * The new smithing inventory, with 3 CRAFTING slots and 1 RESULT slot.
     *
     * @deprecated use {@link #SMITHING}
     */
    @Deprecated(since = "1.20.1", forRemoval = true) // Paper
    SMITHING_NEW(4, "Upgrade Gear", MenuType.SMITHING),
    ;

    private final int size;
    private final String title;
    private final MenuType menuType;
    private final boolean isCreatable;
    // Paper start
    private final net.kyori.adventure.text.Component defaultTitleComponent;

    /**
     * Gets the inventory's default title.
     *
     * @return the inventory's default title
     */
    public net.kyori.adventure.text.@NotNull Component defaultTitle() {
        return defaultTitleComponent;
    }
    // Paper end
    private InventoryType(int defaultSize, /*@NotNull*/ String defaultTitle, @Nullable MenuType type) {
        this(defaultSize, defaultTitle, type, true);
    }

    private InventoryType(int defaultSize, /*@NotNull*/ String defaultTitle, @Nullable MenuType type, boolean isCreatable) {
        size = defaultSize;
        title = defaultTitle;
        this.menuType = type;
        this.isCreatable = isCreatable;
        this.defaultTitleComponent = net.kyori.adventure.text.Component.text(defaultTitle); // Paper - Adventure
    }

    public int getDefaultSize() {
        return size;
    }

    @NotNull
    @Deprecated // Paper
    public String getDefaultTitle() {
        return title;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此 InventoryType 对应的 {@link MenuType}。
     * <p>
     * 并非所有 InventoryType 都对应一个 {@link MenuType}。这些 InventoryType 也是不可创建的。如果此方法返回 null，则 {@link #isCreatable()} 将返回 false，但 {@link #MERCHANT} 除外。
     * <p>
     * 除了不一定对应一个 {@link MenuType} 外，一些 InventoryType 对应相同的 {@link MenuType}，包括：
     * <ul>
     * <li>Dropper、Dispenser
     * <li>ShulkerBox、Barrel、Chest
     * </ul>
     * <p>原文：
     * Gets the corresponding {@link MenuType} of this InventoryType.
     * <p>
     * Not all InventoryType correspond to a {@link MenuType}. These
     * InventoryTypes are also not creatable. If this method returns null,
     * {@link #isCreatable()} will return false, with the exception of
     * {@link #MERCHANT}.
     * <p>
     * As well as not necessarily corresponding to a {@link MenuType} some
     * InventoryType correspond to the same {@link MenuType}, including:
     * <ul>
     * <li>Dropper, Dispenser
     * <li>ShulkerBox, Barrel, Chest
     * </ul>
     *
     * @return 对应的 {@link MenuType}
     * <p>原文：Gets the corresponding {@link MenuType} of this InventoryType.
     * <p>
     * Not all InventoryType correspond to a {@link MenuType}. These
     * InventoryTypes are also not creatable. If this method returns null,
     * {@link #isCreatable()} will return false, with the exception of
     * {@link #MERCHANT}.
     * <p>
     * As well as not necessarily corresponding to a {@link MenuType} some
     * InventoryType correspond to the same {@link MenuType}, including:
     * <ul>
     * <li>Dropper, Dispenser
     * <li>ShulkerBox, Barrel, Chest
     * </ul>
     * <p>原文：the corresponding {@link MenuType}
     */
    @Nullable
    public MenuType getMenuType() {
        return menuType;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示此 InventoryType 可以通过正常的 {@link org.bukkit.Bukkit#createInventory} 方法创建。
     * <p>原文：
     * Denotes that this InventoryType can be created via the normal
     * {@link org.bukkit.Bukkit#createInventory} methods.
     *
     * @return 此 InventoryType 是否可以被创建并展示给玩家
     * <p>原文：Denotes that this InventoryType can be created via the normal
     * {@link org.bukkit.Bukkit#createInventory} methods.
     * <p>原文：if this InventoryType can be created and shown to a player
     */
    public boolean isCreatable() {
        return isCreatable;
    }

    public enum SlotType {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 熔炉或合成物品栏中的结果槽位。
         * <p>原文：
         * A result slot in a furnace or crafting inventory.
         */
        RESULT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 合成矩阵中的槽位，或'输入'槽位。
         * <p>原文：
         * A slot in the crafting matrix, or an 'input' slot.
         */
        CRAFTING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家物品栏中的盔甲槽位。
         * <p>原文：
         * An armour slot in the player's inventory.
         */
        ARMOR,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 容器或玩家物品栏中的常规槽位；任何其他枚举值未涵盖的内容。
         * <p>原文：
         * A regular slot in the container or the player's inventory; anything
         * not covered by the other enum values.
         */
        CONTAINER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 底部行或快捷栏中的槽位。
         * <p>原文：
         * A slot in the bottom row or quickbar.
         */
        QUICKBAR,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 代表物品栏窗口外部区域的伪槽位。
         * <p>原文：
         * A pseudo-slot representing the area outside the inventory window.
         */
        OUTSIDE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 熔炉物品栏中的燃料槽位，或酿造台物品栏中的材料槽位。
         * <p>原文：
         * The fuel slot in a furnace inventory, or the ingredient slot in a
         * brewing stand inventory.
         */
        FUEL;
    }
}
