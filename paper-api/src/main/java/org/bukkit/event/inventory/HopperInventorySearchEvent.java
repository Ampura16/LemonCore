package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 每次漏斗尝试查找其源/附加容器时调用的事件。
 * <p>原文：
 * Event that gets called each time a Hopper attempts to find its
 * source/attached containers.
 */
public class HopperInventorySearchEvent extends BlockEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Inventory inventory;
    private final ContainerType containerType;
    private final Block searchBlock;

    public enum ContainerType {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 漏斗正在寻找的源容器。
         *
         * 这是漏斗上方的物品栏，漏斗从中提取物品。
         * <p>原文：
         * The source container the hopper is looking for.
         *
         * This is the Inventory above the Hopper where it extracts items from.
         */
        SOURCE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 漏斗所附加的容器。
         *
         * 这是漏斗将物品推入的物品栏。
         * <p>原文：
         * The container the hopper is attached to.
         *
         * This is the Inventory the Hopper pushes items into.
         */
        DESTINATION;
    }

    @ApiStatus.Internal
    public HopperInventorySearchEvent(@Nullable Inventory inventory, @NotNull ContainerType containerType, @NotNull Block hopper, @NotNull Block searchBlock) { // Paper
        super(hopper);
        this.inventory = inventory;
        this.containerType = containerType;
        this.searchBlock = searchBlock;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置漏斗将用于其源/附加容器的 {@link Inventory}。
     * <p>原文：
     * Set the {@link Inventory} that the Hopper will use for its
     * source/attached Container.
     *
     * @param inventory 要使用的物品栏
     * <p>原文：Set the {@link Inventory} that the Hopper will use for its
     * source/attached Container.
     * <p>原文：the inventory to use
     */
    public void setInventory(@Nullable Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取漏斗将用于其源/附加容器的 {@link Inventory}。
     * <p>原文：
     * Gets the {@link Inventory} that the Hopper will use for its
     * source/attached Container.
     *
     * @return 将被使用的物品栏
     * <p>原文：Gets the {@link Inventory} that the Hopper will use for its
     * source/attached Container.
     * <p>原文：the inventory which will be used
     */
    @Nullable
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取漏斗正在搜索的容器类型。
     * <p>原文：
     * Gets the Container type the Hopper is searching for.
     *
     * @return 正在搜索的容器类型
     * <p>原文：Gets the Container type the Hopper is searching for.
     * <p>原文：the container type being searched for
     */
    @NotNull
    public ContainerType getContainerType() {
        return this.containerType;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取正在被搜索物品栏的方块。
     * <p>原文：
     * Gets the Block that is being searched for an inventory.
     *
     * @return 正在被搜索物品栏的方块
     * <p>原文：Gets the Block that is being searched for an inventory.
     * <p>原文：block being searched for an inventory
     */
    @NotNull
    public Block getSearchBlock() {
        return this.searchBlock;
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
