package org.bukkit.inventory;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import net.kyori.adventure.text.Component;
import org.bukkit.Keyed;
import org.bukkit.Registry;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.view.AnvilView;
import org.bukkit.inventory.view.BeaconView;
import org.bukkit.inventory.view.BrewingStandView;
import org.bukkit.inventory.view.CrafterView;
import org.bukkit.inventory.view.EnchantmentView;
import org.bukkit.inventory.view.FurnaceView;
import org.bukkit.inventory.view.LecternView;
import org.bukkit.inventory.view.LoomView;
import org.bukkit.inventory.view.MerchantView;
import org.bukkit.inventory.view.StonecutterView;
import org.bukkit.inventory.view.builder.InventoryViewBuilder;
import org.bukkit.inventory.view.builder.LocationInventoryViewBuilder;
import org.bukkit.inventory.view.builder.MerchantInventoryViewBuilder;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同类型的视图 (也称为菜单), 可由玩家创建和查看.
 * <p>原文：
 * Represents different kinds of views, also known as menus, which can be
 * created and viewed by the player.
 */
@NullMarked
@ApiStatus.Experimental
public interface MenuType extends Keyed, io.papermc.paper.world.flag.FeatureDependant { // Paper - make FeatureDependant

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表具有 1 行的箱子的菜单类型.
     * <p>原文：
     * A MenuType which represents a chest with 1 row.
     */
    MenuType.Typed<InventoryView, InventoryViewBuilder<InventoryView>> GENERIC_9X1 = get("generic_9x1");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表具有 2 行的箱子的菜单类型.
     * <p>原文：
     * A MenuType which represents a chest with 2 rows.
     */
    MenuType.Typed<InventoryView, InventoryViewBuilder<InventoryView>> GENERIC_9X2 = get("generic_9x2");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表具有 3 行的箱子的菜单类型.
     * <p>原文：
     * A MenuType which represents a chest with 3 rows.
     */
    MenuType.Typed<InventoryView, LocationInventoryViewBuilder<InventoryView>> GENERIC_9X3 = get("generic_9x3");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表具有 4 行的箱子的菜单类型.
     * <p>原文：
     * A MenuType which represents a chest with 4 rows.
     */
    MenuType.Typed<InventoryView, InventoryViewBuilder<InventoryView>> GENERIC_9X4 = get("generic_9x4");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表具有 5 行的箱子的菜单类型.
     * <p>原文：
     * A MenuType which represents a chest with 5 rows.
     */
    MenuType.Typed<InventoryView, InventoryViewBuilder<InventoryView>> GENERIC_9X5 = get("generic_9x5");
    /**
     * A MenuType which represents a chest with 6 rows.
     */
    MenuType.Typed<InventoryView, LocationInventoryViewBuilder<InventoryView>> GENERIC_9X6 = get("generic_9x6");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表具有 3 列 3 行的发射器/投掷器式菜单的菜单类型.
     * <p>原文：
     * A MenuType which represents a dispenser/dropper like menu with 3 columns
     * and 3 rows.
     */
    MenuType.Typed<InventoryView, LocationInventoryViewBuilder<InventoryView>> GENERIC_3X3 = get("generic_3x3");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表合成器的菜单类型.
     * <p>原文：
     * A MenuType which represents a crafter
     */
    MenuType.Typed<CrafterView, LocationInventoryViewBuilder<CrafterView>> CRAFTER_3X3 = get("crafter_3x3");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表铁砧的菜单类型.
     * <p>原文：
     * A MenuType which represents an anvil.
     */
    MenuType.Typed<AnvilView, LocationInventoryViewBuilder<AnvilView>> ANVIL = get("anvil");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表信标的菜单类型.
     * <p>原文：
     * A MenuType which represents a beacon.
     */
    MenuType.Typed<BeaconView, LocationInventoryViewBuilder<BeaconView>> BEACON = get("beacon");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表高炉的菜单类型.
     * <p>原文：
     * A MenuType which represents a blast furnace.
     */
    MenuType.Typed<FurnaceView, LocationInventoryViewBuilder<FurnaceView>> BLAST_FURNACE = get("blast_furnace");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表酿造台的菜单类型.
     * <p>原文：
     * A MenuType which represents a brewing stand.
     */
    MenuType.Typed<BrewingStandView, LocationInventoryViewBuilder<BrewingStandView>> BREWING_STAND = get("brewing_stand");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表工作台的菜单类型.
     * <p>原文：
     * A MenuType which represents a crafting table.
     */
    MenuType.Typed<InventoryView, LocationInventoryViewBuilder<InventoryView>> CRAFTING = get("crafting");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表附魔台的菜单类型.
     * <p>原文：
     * A MenuType which represents an enchantment table.
     */
    MenuType.Typed<EnchantmentView, LocationInventoryViewBuilder<EnchantmentView>> ENCHANTMENT = get("enchantment");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表熔炉的菜单类型.
     * <p>原文：
     * A MenuType which represents a furnace.
     */
    MenuType.Typed<FurnaceView, LocationInventoryViewBuilder<FurnaceView>> FURNACE = get("furnace");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表砂轮的菜单类型.
     * <p>原文：
     * A MenuType which represents a grindstone.
     */
    MenuType.Typed<InventoryView, LocationInventoryViewBuilder<InventoryView>> GRINDSTONE = get("grindstone");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表漏斗的菜单类型.
     * <p>原文：
     * A MenuType which represents a hopper.
     */
    MenuType.Typed<InventoryView, LocationInventoryViewBuilder<InventoryView>> HOPPER = get("hopper");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表讲台 (类似书本的视图) 的菜单类型.
     * <p>原文：
     * A MenuType which represents a lectern, a book like view.
     */
    MenuType.Typed<LecternView, LocationInventoryViewBuilder<LecternView>> LECTERN = get("lectern");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表织布机的菜单类型.
     * <p>原文：
     * A MenuType which represents a loom.
     */
    MenuType.Typed<LoomView, LocationInventoryViewBuilder<LoomView>> LOOM = get("loom");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表村民商人交易界面的菜单类型.
     * <p>原文：
     * A MenuType which represents a merchant.
     */
    MenuType.Typed<MerchantView, MerchantInventoryViewBuilder<MerchantView>> MERCHANT = get("merchant");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表潜影盒的菜单类型.
     * <p>原文：
     * A MenuType which represents a shulker box.
     */
    MenuType.Typed<InventoryView, LocationInventoryViewBuilder<InventoryView>> SHULKER_BOX = get("shulker_box");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表锻造台的菜单类型.
     * <p>原文：
     * A MenuType which represents a smithing table.
     */
    MenuType.Typed<InventoryView, LocationInventoryViewBuilder<InventoryView>> SMITHING = get("smithing");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表烟熏炉的菜单类型.
     * <p>原文：
     * A MenuType which represents a smoker.
     */
    MenuType.Typed<FurnaceView, LocationInventoryViewBuilder<FurnaceView>> SMOKER = get("smoker");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表制图台的菜单类型.
     * <p>原文：
     * A MenuType which represents a cartography table.
     */
    MenuType.Typed<InventoryView, LocationInventoryViewBuilder<InventoryView>> CARTOGRAPHY_TABLE = get("cartography_table");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表切石机的菜单类型.
     * <p>原文：
     * A MenuType which represents a stonecutter.
     */
    MenuType.Typed<StonecutterView, LocationInventoryViewBuilder<StonecutterView>> STONECUTTER = get("stonecutter");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * Typed 代表 {@link MenuType} 的一个子类型, 在编译时具有已知的 {@link InventoryView} 类型.
     * <p>原文：
     * Typed represents a subtype of {@link MenuType}s that have a known
     * {@link InventoryView} type at compile time.
     *
     * @param <V> 代表视图类型的 {@link InventoryView} 的泛型类型
     * <p>原文：the generic type of {@link InventoryView} that represents the
     * view type.
     * @param <B> 代表视图构建器的 {@link InventoryViewBuilder} 的构建器类型
     */
    interface Typed<V extends InventoryView, B extends InventoryViewBuilder<V>> extends MenuType {

        /**
         * Creates a view of the specified menu type.
         * <p>
         * The player provided to create this view must be the player the view
         * is opened for. See {@link HumanEntity#openInventory(InventoryView)}
         * for more information.
         *
         * @param player the player the view belongs to
         * @return the created {@link InventoryView}
         */
        default V create(HumanEntity player) {
            return create(player, (Component) null);
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 创建指定菜单类型的视图.
         * <p>
         * 提供给此方法创建视图的玩家必须是该视图所打开的玩家. 详见 {@link HumanEntity#openInventory(InventoryView)}.
         * <p>原文：
         * Creates a view of the specified menu type.
         * <p>
         * The player provided to create this view must be the player the view
         * is opened for. See {@link HumanEntity#openInventory(InventoryView)}
         * for more information.
         *
         * @param player 视图所属的玩家
         * <p>原文：the player the view belongs to
         * @param title 视图的标题
         * <p>原文：the title of the view
         * @return 创建的 {@link InventoryView}
         * <p>原文：the created {@link InventoryView}
         * @deprecated Use {@link #create(HumanEntity, Component)} instead.
         */
        @Deprecated(since = "1.21") // Paper - adventure
        V create(HumanEntity player, @Nullable String title);

        // Paper start - adventure
        /**
         * Creates a view of the specified menu type.
         * <p>
         * The player provided to create this view must be the player the view
         * is opened for. See {@link HumanEntity#openInventory(InventoryView)}
         * for more information.
         *
         * @param player the player the view belongs to
         * @param title the title of the view
         * @return the created {@link InventoryView}
         */
        V create(HumanEntity player, @Nullable Component title);
        // Paper end - adventure

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 创建此类型 InventoryView 的构建器.
         *
         * @return 新的构建器
         */
        B builder();
    }

    // Paper start - adventure
    /**
     * Creates a view of the specified menu type.
     * <p>
     * The player provided to create this view must be the player the view
     * is opened for. See {@link HumanEntity#openInventory(InventoryView)}
     * for more information.
     *
     * @param player the player the view belongs to
     * @param title the title of the view
     * @return the created {@link InventoryView}
     */
    InventoryView create(HumanEntity player, @Nullable Component title);
    // Paper end - adventure

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此 MenuType 作为使用普通 {@link InventoryView} 表示的类型化版本返回.
     * <p>原文：
     * Yields this MenuType as a typed version of itself with a plain
     * {@link InventoryView} representing it.
     *
     * @return 类型化的 MenuType
     * <p>原文：the typed MenuType.
     */
    MenuType.Typed<InventoryView, InventoryViewBuilder<InventoryView>> typed();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此 MenuType 作为使用特定 {@link InventoryView} 表示的类型化版本返回.
     * <p>原文：
     * Yields this MenuType as a typed version of itself with a specific
     * {@link InventoryView} representing it.
     *
     * @param viewClass 用于类型化此 {@link InventoryView} 的 {@link InventoryView} 类类型
     * <p>原文：the class type of the {@link InventoryView} to type this
     * {@link InventoryView} with.
     * @param <V> 获取此 MenuType 时使用的 InventoryView 的泛型类型
     * <p>原文：the generic type of the InventoryView to get this MenuType
     * with
     * @param <B> 获取此 MenuType 时使用的 InventoryViewBuilder 的泛型类型
     * <p>原文：the generic type of the InventoryViewBuilder to get this
     * MenuType with
     * @return 类型化的 MenuType
     * <p>原文：the typed MenuType
     * @throws IllegalArgumentException 如果提供的 viewClass 无法被类型化为此 MenuType
     * <p>原文：if the provided viewClass cannot be
     * typed to this MenuType
     */
    <V extends InventoryView, B extends InventoryViewBuilder<V>> MenuType.Typed<V, B> typed(final Class<V> viewClass) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此 MenuType 的 {@link InventoryView} 类.
     * <p>原文：
     * Gets the {@link InventoryView} class of this MenuType.
     *
     * @return 此 MenuType 的 {@link InventoryView} 类
     * <p>原文：the {@link InventoryView} class of this MenuType
     */
    Class<? extends InventoryView> getInventoryViewClass();

    @SuppressWarnings("unchecked")
    private static <T extends MenuType> T get(final @KeyPattern.Value String key) {
        return (T) Registry.MENU.getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
    }
}
