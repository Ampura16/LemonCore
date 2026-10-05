/*
 * Copyright (c) 2018 Daniel Ennis (Aikar) MIT License
 *
 *  Permission is hereby granted, free of charge, to any person obtaining
 *  a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including
 *  without limitation the rights to use, copy, modify, merge, publish,
 *  distribute, sublicense, and/or sell copies of the Software, and to
 *  permit persons to whom the Software is furnished to do so, subject to
 *  the following conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *  MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 *  LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 *  OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 *  WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.destroystokyo.paper;

import java.util.Objects;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示用于标识具有共同属性的材料的标签集合。对于缺失的标签会映射到 Minecraft 的标签，也包含一些可能有用的自定义标签。
 * <p>
 * 该类中的所有标签均不可修改，尝试修改将抛出 {@link UnsupportedOperationException}。
 * <p>原文：
 * Represents a collection tags to identify materials that share common properties.
 * Will map to minecraft for missing tags, as well as custom ones that may be useful.
 * <p>
 * All tags in this class are unmodifiable, attempting to modify them will throw an
 * {@link UnsupportedOperationException}.
 */
@SuppressWarnings({"NonFinalUtilityClass", "unused", "WeakerAccess"})
public class MaterialTags {

    private static NamespacedKey keyFor(String key) {
        return new NamespacedKey("paper", key + "_settag");
    }

    private static MaterialSetTag replacedBy(Tag<Material> vanillaTag) {
        return replacedBy(vanillaTag, Objects.requireNonNull(vanillaTag).key().value());
    }

    @SuppressWarnings("unchecked")
    private static MaterialSetTag replacedBy(Tag<Material> vanillaTag, String legacyKey) {
        Objects.requireNonNull(vanillaTag);
        return new MaterialSetTag(keyFor(legacyKey)).add(vanillaTag).lock();
    }

    /**
     * @deprecated 改用 {@link Tag#ITEMS_ARROWS}
     * <p>原文：in favour of {@link Tag#ITEMS_ARROWS}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag ARROWS = replacedBy(Tag.ITEMS_ARROWS);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有颜色的床。
     * <p>原文：
     * Covers all colors of beds.
     *
     * @deprecated 改用 {@link Tag#BEDS}
     * <p>原文：in favour of {@link Tag#BEDS}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag BEDS = replacedBy(Tag.BEDS);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有桶类物品。
     * <p>原文：
     * Covers all bucket items.
     */
    public static final MaterialSetTag BUCKETS = new MaterialSetTag(keyFor("buckets"))
        .endsWith("BUCKET")
        .ensureSize("BUCKETS", 11).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖煤炭和木炭。
     * <p>原文：
     * Covers coal and charcoal.
     *
     * @deprecated 改用 {@link Tag#ITEMS_COALS}
     * <p>原文：in favour of {@link Tag#ITEMS_COALS}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag COALS = replacedBy(Tag.ITEMS_COALS);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖两种圆石墙变种。
     * <p>原文：
     * Covers both cobblestone wall variants.
     */
    public static final MaterialSetTag COBBLESTONE_WALLS = new MaterialSetTag(keyFor("cobblestone_walls"))
        .endsWith("COBBLESTONE_WALL")
        .ensureSize("COBBLESTONE_WALLS", 2).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖圆石和苔石。
     * <p>原文：
     * Covers both cobblestone and mossy Cobblestone.
     */
    public static final MaterialSetTag COBBLESTONES = new MaterialSetTag(keyFor("cobblestones"))
        .add(Material.COBBLESTONE, Material.MOSSY_COBBLESTONE).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有颜色的混凝土。
     * <p>原文：
     * Covers all colors of concrete.
     */
    public static final MaterialSetTag CONCRETES = new MaterialSetTag(keyFor("concretes"))
        .endsWith("_CONCRETE")
        .ensureSize("CONCRETES", 16).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有颜色的混凝土粉末。
     * <p>原文：
     * Covers all colors of concrete powder.
     *
     * @deprecated 改用 {@link Tag#CONCRETE_POWDER}
     * <p>原文：in favour of {@link Tag#CONCRETE_POWDER}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag CONCRETE_POWDER = replacedBy(Tag.CONCRETE_POWDER);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖两种熟鱼。
     * <p>原文：
     * Covers the two types of cooked fish.
     */
    public static final MaterialSetTag COOKED_FISH = new MaterialSetTag(keyFor("cooked_fish"))
        .add(Material.COOKED_COD, Material.COOKED_SALMON).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有门变种。
     * <p>原文：
     * Covers all variants of doors.
     *
     * @deprecated 改用 {@link Tag#DOORS}
     * <p>原文：in favour of {@link Tag#DOORS}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag DOORS = replacedBy(Tag.DOORS);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有染料。
     * <p>原文：
     * Covers all dyes.
     */
    public static final MaterialSetTag DYES = new MaterialSetTag(keyFor("dyes"))
        .endsWith("_DYE")
        .ensureSize("DYES", 16).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有栅栏门变种。
     * <p>原文：
     * Covers all variants of gates.
     *
     * @deprecated 改用 {@link Tag#FENCE_GATES}
     * <p>原文：in favour of {@link Tag#FENCE_GATES}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag FENCE_GATES = replacedBy(Tag.FENCE_GATES);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有栅栏变种。
     * <p>原文：
     * Covers all variants of fences.
     *
     * @deprecated 改用 {@link Tag#FENCES}
     * <p>原文：in favour of {@link Tag#FENCES}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag FENCES = replacedBy(Tag.FENCES);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有鱼桶变种。
     * <p>原文：
     * Covers all variants of fish buckets.
     */
    public static final MaterialSetTag FISH_BUCKETS = new MaterialSetTag(keyFor("fish_buckets"))
        .add(Material.COD_BUCKET, Material.PUFFERFISH_BUCKET, Material.SALMON_BUCKET, Material.TROPICAL_FISH_BUCKET).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖无色玻璃和 16 种染色玻璃（不含玻璃板）。
     * <p>原文：
     * Covers the non-colored glass and 16 stained glasses (not panes).
     */
    public static final MaterialSetTag GLASS = new MaterialSetTag(keyFor("glass"))
        .endsWith("_GLASS")
        .add(Material.GLASS)
        .ensureSize("GLASS", 18).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖无色玻璃板和染色玻璃板（仅玻璃板）。
     * <p>原文：
     * Covers the non-colored glass panes and stained glass panes (panes only).
     */
    public static final MaterialSetTag GLASS_PANES = new MaterialSetTag(keyFor("glass_panes"))
        .endsWith("GLASS_PANE")
        .ensureSize("GLASS_PANES", 17).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有带釉陶瓦方块。
     * <p>原文：
     * Covers all glazed terracotta blocks.
     */
    public static final MaterialSetTag GLAZED_TERRACOTTA = new MaterialSetTag(keyFor("glazed_terracotta"))
        .endsWith("GLAZED_TERRACOTTA")
        .ensureSize("GLAZED_TERRACOTTA", 16).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖各色染色陶瓦。
     * <p>原文：
     * Covers the colors of stained terracotta.
     */
    public static final MaterialSetTag STAINED_TERRACOTTA = new MaterialSetTag(keyFor("stained_terracotta"))
        .endsWith("TERRACOTTA")
        .not(Material.TERRACOTTA)
        .notEndsWith("GLAZED_TERRACOTTA")
        .ensureSize("STAINED_TERRACOTTA", 16).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖陶瓦及其染色变种。
     * <p>原文：
     * Covers terracotta along with the stained variants.
     */
    public static final MaterialSetTag TERRACOTTA = new MaterialSetTag(keyFor("terracotta"))
        .endsWith("TERRACOTTA")
        .ensureSize("TERRACOTTA", 33).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖两种金苹果。
     * <p>原文：
     * Covers both golden apples.
     */
    public static final MaterialSetTag GOLDEN_APPLES = new MaterialSetTag(keyFor("golden_apples"))
        .endsWith("GOLDEN_APPLE")
        .ensureSize("GOLDEN_APPLES", 2).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖马铠的变种。
     * <p>原文：
     * Covers the variants of horse armor.
     */
    public static final MaterialSetTag HORSE_ARMORS = new MaterialSetTag(keyFor("horse_armors"))
        .endsWith("_HORSE_ARMOR")
        .ensureSize("HORSE_ARMORS", 6).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖被虫蚀方块的变种。
     * <p>原文：
     * Covers the variants of infested blocks.
     */
    public static final MaterialSetTag INFESTED_BLOCKS = new MaterialSetTag(keyFor("infested_blocks"))
        .startsWith("INFESTED_")
        .ensureSize("INFESTED_BLOCKS", 7).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖蘑菇方块的变种。
     * <p>原文：
     * Covers the variants of mushroom blocks.
     */
    public static final MaterialSetTag MUSHROOM_BLOCKS = new MaterialSetTag(keyFor("mushroom_blocks"))
        .endsWith("MUSHROOM_BLOCK")
        .add(Material.MUSHROOM_STEM)
        .ensureSize("MUSHROOM_BLOCKS", 3).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有蘑菇。
     * <p>原文：
     * Covers all mushrooms.
     */
    public static final MaterialSetTag MUSHROOMS = new MaterialSetTag(keyFor("mushrooms"))
        .add(Material.BROWN_MUSHROOM, Material.RED_MUSHROOM).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有音乐唱片物品。
     * <p>原文：
     * Covers all music disc items.
     */
    public static final MaterialSetTag MUSIC_DISCS = new MaterialSetTag(keyFor("music_discs"))
        .startsWith("MUSIC_DISC_").lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有矿石。
     * <p>原文：
     * Covers all ores.
     */
    @SuppressWarnings("unchecked")
    public static final MaterialSetTag ORES = new MaterialSetTag(keyFor("ores"))
        .add(Tag.COAL_ORES, Tag.COPPER_ORES, Tag.IRON_ORES, Tag.GOLD_ORES,
            Tag.LAPIS_ORES, Tag.REDSTONE_ORES, Tag.DIAMOND_ORES, Tag.EMERALD_ORES)
        .add(Material.ANCIENT_DEBRIS, Material.NETHER_QUARTZ_ORE).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有活塞类物品和方块，包括活塞头和移动中的活塞。
     * <p>原文：
     * Covers all piston typed items and blocks including the piston head and moving piston.
     */
    public static final MaterialSetTag PISTONS = new MaterialSetTag(keyFor("pistons"))
        .contains("PISTON")
        .ensureSize("PISTONS", 4).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有马铃薯物品。
     * <p>原文：
     * Covers all potato items.
     */
    public static final MaterialSetTag POTATOES = new MaterialSetTag(keyFor("potatoes"))
        .endsWith("POTATO")
        .ensureSize("POTATOES", 3).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有木质压力板、测重压力板和石质压力板。
     * <p>原文：
     * Covers all wooden pressure plates and the weighted pressure plates and the stone pressure plate.
     *
     * @deprecated 改用 {@link Tag#PRESSURE_PLATES}
     * <p>原文：in favour of {@link Tag#PRESSURE_PLATES}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag PRESSURE_PLATES = replacedBy(Tag.PRESSURE_PLATES);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖海晶石方块的变种。
     * <p>原文：
     * Covers the variants of prismarine blocks.
     */
    public static final MaterialSetTag PRISMARINE = new MaterialSetTag(keyFor("prismarine"))
        .add(Material.PRISMARINE, Material.PRISMARINE_BRICKS, Material.DARK_PRISMARINE).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖海晶石台阶的变种。
     * <p>原文：
     * Covers the variants of prismarine slabs.
     */
    public static final MaterialSetTag PRISMARINE_SLABS = new MaterialSetTag(keyFor("prismarine_slabs"))
        .add(Material.PRISMARINE_SLAB, Material.PRISMARINE_BRICK_SLAB, Material.DARK_PRISMARINE_SLAB).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖海晶石楼梯的变种。
     * <p>原文：
     * Covers the variants of prismarine stairs.
     */
    public static final MaterialSetTag PRISMARINE_STAIRS = new MaterialSetTag(keyFor("prismarine_stairs"))
        .add(Material.PRISMARINE_STAIRS, Material.PRISMARINE_BRICK_STAIRS, Material.DARK_PRISMARINE_STAIRS).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖南瓜的变种。
     * <p>原文：
     * Covers the variants of pumpkins.
     */
    public static final MaterialSetTag PUMPKINS = new MaterialSetTag(keyFor("pumpkins"))
        .add(Material.CARVED_PUMPKIN, Material.JACK_O_LANTERN, Material.PUMPKIN).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖石英方块的变种。
     * <p>原文：
     * Covers the variants of quartz blocks.
     */
    public static final MaterialSetTag QUARTZ_BLOCKS = new MaterialSetTag(keyFor("quartz_blocks"))
        .add(Material.QUARTZ_BLOCK, Material.QUARTZ_PILLAR, Material.CHISELED_QUARTZ_BLOCK, Material.SMOOTH_QUARTZ).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有生鱼物品。
     * <p>原文：
     * Covers all uncooked fish items.
     */
    public static final MaterialSetTag RAW_FISH = new MaterialSetTag(keyFor("raw_fish"))
        .add(Material.COD, Material.PUFFERFISH, Material.SALMON, Material.TROPICAL_FISH).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖红砂岩方块的变种。
     * <p>原文：
     * Covers the variants of red sandstone blocks.
     */
    public static final MaterialSetTag RED_SANDSTONES = new MaterialSetTag(keyFor("red_sandstones"))
        .endsWith("RED_SANDSTONE")
        .ensureSize("RED_SANDSTONES", 4).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖砂岩方块的变种。
     * <p>原文：
     * Covers the variants of sandstone blocks.
     */
    public static final MaterialSetTag SANDSTONES = new MaterialSetTag(keyFor("sandstones"))
        .add(Material.SANDSTONE, Material.CHISELED_SANDSTONE, Material.CUT_SANDSTONE, Material.SMOOTH_SANDSTONE).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖海绵和湿海绵。
     * <p>原文：
     * Covers sponge and wet sponge.
     */
    public static final MaterialSetTag SPONGES = new MaterialSetTag(keyFor("sponges"))
        .endsWith("SPONGE")
        .ensureSize("SPONGES", 2).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖无色和彩色的潜影盒。
     * <p>原文：
     * Covers the non-colored and colored shulker boxes.
     *
     * @deprecated 改用 {@link Tag#SHULKER_BOXES}
     * <p>原文：in favour of {@link Tag#SHULKER_BOXES}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag SHULKER_BOXES = replacedBy(Tag.SHULKER_BOXES);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖僵尸、苦力怕、骷髅、龙和玩家的头颅。
     * <p>原文：
     * Covers zombie, creeper, skeleton, dragon, and player heads.
     */
    public static final MaterialSetTag SKULLS = new MaterialSetTag(keyFor("skulls"))
        .endsWith("_HEAD")
        .endsWith("_SKULL")
        .not(Material.PISTON_HEAD)
        .ensureSize("SKULLS", 14).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有刷怪蛋物品。
     * <p>原文：
     * Covers all spawn egg items.
     */
    public static final MaterialSetTag SPAWN_EGGS = new MaterialSetTag(keyFor("spawn_eggs"))
        .endsWith("_SPAWN_EGG")
        .ensureSize("SPAWN_EGGS", 87).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有颜色的染色玻璃。
     * <p>原文：
     * Covers all colors of stained glass.
     */
    public static final MaterialSetTag STAINED_GLASS = new MaterialSetTag(keyFor("stained_glass"))
        .endsWith("_STAINED_GLASS")
        .ensureSize("STAINED_GLASS", 16).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有颜色的染色玻璃板。
     * <p>原文：
     * Covers all colors of stained glass panes.
     */
    public static final MaterialSetTag STAINED_GLASS_PANES = new MaterialSetTag(keyFor("stained_glass_panes"))
        .endsWith("STAINED_GLASS_PANE")
        .ensureSize("STAINED_GLASS_PANES", 16).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有活板门变种。
     * <p>原文：
     * Covers all variants of trapdoors.
     *
     * @deprecated 改用 {@link Tag#TRAPDOORS}
     * <p>原文：in favour of {@link Tag#TRAPDOORS}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag TRAPDOORS = replacedBy(Tag.TRAPDOORS);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖门的所有木质变种。
     * <p>原文：
     * Covers all wood variants of doors.
     *
     * @deprecated 改用 {@link Tag#WOODEN_DOORS}
     * <p>原文：in favour of {@link Tag#WOODEN_DOORS}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag WOODEN_DOORS = replacedBy(Tag.WOODEN_DOORS);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖栅栏的所有木质变种。
     * <p>原文：
     * Covers all wood variants of fences.
     *
     * @deprecated 改用 {@link Tag#WOODEN_FENCES}
     * <p>原文：in favour of {@link Tag#WOODEN_FENCES}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag WOODEN_FENCES = replacedBy(Tag.WOODEN_FENCES);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖活板门的所有木质变种。
     * <p>原文：
     * Covers all wood variants of trapdoors.
     *
     * @deprecated 改用 {@link Tag#WOODEN_TRAPDOORS}
     * <p>原文：in favour of {@link Tag#WOODEN_TRAPDOORS}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag WOODEN_TRAPDOORS = replacedBy(Tag.WOODEN_TRAPDOORS);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖栅栏门的木质变种。
     * <p>原文：
     * Covers the wood variants of gates.
     *
     * @deprecated 改用 {@link Tag#FENCE_GATES}
     * <p>原文：in favour of {@link Tag#FENCE_GATES}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag WOODEN_GATES = replacedBy(Tag.FENCE_GATES, "wooden_gates");

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖紫珀的变种。
     * <p>原文：
     * Covers the variants of purpur.
     */
    public static final MaterialSetTag PURPUR = new MaterialSetTag(keyFor("purpur"))
        .startsWith("PURPUR_")
        .ensureSize("PURPUR", 4).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖告示牌的变种。
     * <p>原文：
     * Covers the variants of signs.
     *
     * @deprecated 改用 {@link Tag#ALL_SIGNS}
     * <p>原文：in favour of {@link Tag#ALL_SIGNS}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag SIGNS = replacedBy(Tag.ALL_SIGNS, "signs");

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖普通火把的变种。
     * <p>原文：
     * Covers the variants of a regular torch.
     */
    public static final MaterialSetTag TORCH = new MaterialSetTag(keyFor("torch"))
        .add(Material.TORCH, Material.WALL_TORCH)
        .ensureSize("TORCH", 2).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖红石火把的变种。
     * <p>原文：
     * Covers the variants of a redstone torch.
     */
    public static final MaterialSetTag REDSTONE_TORCH = new MaterialSetTag(keyFor("redstone_torch"))
        .add(Material.REDSTONE_TORCH, Material.REDSTONE_WALL_TORCH)
        .ensureSize("REDSTONE_TORCH", 2).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖灵魂火把的变种。
     * <p>原文：
     * Covers the variants of a soul torch.
     */
    public static final MaterialSetTag SOUL_TORCH = new MaterialSetTag(keyFor("soul_torch"))
        .add(Material.SOUL_TORCH, Material.SOUL_WALL_TORCH)
        .ensureSize("SOUL_TORCH", 2).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖铜火把的变种。
     * <p>原文：
     * Covers the variants of a copper torch.
     */
    public static final MaterialSetTag COPPER_TORCH = new MaterialSetTag(keyFor("copper_torch"))
        .add(Material.COPPER_TORCH, Material.COPPER_WALL_TORCH)
        .ensureSize("COPPER_TORCH", 2).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖火把的变种。
     * <p>原文：
     * Covers the variants of torches.
     */
    public static final MaterialSetTag TORCHES = new MaterialSetTag(keyFor("torches"))
        .add(TORCH, REDSTONE_TORCH, SOUL_TORCH, COPPER_TORCH)
        .ensureSize("TORCHES", 8).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖灯笼的变种。
     * <p>原文：
     * Covers the variants of lanterns.
     *
     * @deprecated 改用 {@link Tag#LANTERNS}
     * <p>原文：in favour of {@link Tag#LANTERNS}
     */
    @Deprecated(since = "1.21.9")
    public static final MaterialSetTag LANTERNS = replacedBy(Tag.LANTERNS);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖铁轨的变种。
     * <p>原文：
     * Covers the variants of rails.
     *
     * @deprecated 改用 {@link Tag#RAILS}
     * <p>原文：in favour of {@link Tag#RAILS}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag RAILS = replacedBy(Tag.RAILS);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖剑的变种。
     * <p>原文：
     * Covers the variants of swords.
     *
     * @deprecated 改用 {@link Tag#ITEMS_SWORDS}
     * <p>原文：in favour of {@link Tag#ITEMS_SWORDS}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag SWORDS = replacedBy(Tag.ITEMS_SWORDS);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖锹的变种。
     * <p>原文：
     * Covers the variants of shovels.
     *
     * @deprecated 改用 {@link Tag#ITEMS_SHOVELS}
     * <p>原文：in favour of {@link Tag#ITEMS_SHOVELS}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag SHOVELS = replacedBy(Tag.ITEMS_SHOVELS);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖镐的变种。
     * <p>原文：
     * Covers the variants of pickaxes.
     *
     * @deprecated 改用 {@link Tag#ITEMS_PICKAXES}
     * <p>原文：in favour of {@link Tag#ITEMS_PICKAXES}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag PICKAXES = replacedBy(Tag.ITEMS_PICKAXES);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖斧的变种。
     * <p>原文：
     * Covers the variants of axes.
     *
     * @deprecated 改用 {@link Tag#ITEMS_AXES}
     * <p>原文：in favour of {@link Tag#ITEMS_AXES}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag AXES = replacedBy(Tag.ITEMS_AXES);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖锄的变种。
     * <p>原文：
     * Covers the variants of hoes.
     *
     * @deprecated 改用 {@link Tag#ITEMS_HOES}
     * <p>原文：in favour of {@link Tag#ITEMS_HOES}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag HOES = replacedBy(Tag.ITEMS_HOES);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖头盔的变种。
     * <p>原文：
     * Covers the variants of helmets.
     *
     * @deprecated 改用 {@link Tag#ITEMS_HEAD_ARMOR}
     * <p>原文：in favour of {@link Tag#ITEMS_HEAD_ARMOR}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag HELMETS = replacedBy(Tag.ITEMS_HEAD_ARMOR, "helmets");

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖可装备在头盔槽位的物品变种。
     * <p>原文：
     * Covers the variants of items that can be equipped in the helmet slot.
     *
     * @deprecated 只要设置了正确的数据组件，任何物品都可以被装备
     * <p>原文：any item can be equippable with the right data component set on it
     */
    @Deprecated(since = "1.21.8")
    @SuppressWarnings("unchecked")
    public static final MaterialSetTag HEAD_EQUIPPABLE = new MaterialSetTag(keyFor("head_equippable"))
        .add(Tag.ITEMS_HEAD_ARMOR, Tag.ITEMS_SKULLS)
        .add(Material.CARVED_PUMPKIN).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖胸甲的变种。
     * <p>原文：
     * Covers the variants of chestplate.
     *
     * @deprecated 改用 {@link Tag#ITEMS_CHEST_ARMOR}
     * <p>原文：in favour of {@link Tag#ITEMS_CHEST_ARMOR}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag CHESTPLATES = replacedBy(Tag.ITEMS_CHEST_ARMOR, "chestplates");

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖可装备在胸部槽位的物品变种。
     * <p>原文：
     * Covers the variants of items that can be equipped in the chest slot.
     *
     * @deprecated 只要设置了正确的数据组件，任何物品都可以被装备
     * <p>原文：any item can be equippable with the right data component set on it
     */
    @Deprecated(since = "1.21.8")
    @SuppressWarnings("unchecked")
    public static final MaterialSetTag CHEST_EQUIPPABLE = new MaterialSetTag(keyFor("chest_equippable"))
        .add(Tag.ITEMS_CHEST_ARMOR)
        .add(Material.ELYTRA).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖护腿的变种。
     * <p>原文：
     * Covers the variants of leggings.
     *
     * @deprecated 改用 {@link Tag#ITEMS_LEG_ARMOR}
     * <p>原文：in favour of {@link Tag#ITEMS_LEG_ARMOR}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag LEGGINGS = replacedBy(Tag.ITEMS_LEG_ARMOR, "leggings");

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖靴子的变种。
     * <p>原文：
     * Covers the variants of boots.
     *
     * @deprecated 改用 {@link Tag#ITEMS_FOOT_ARMOR}
     * <p>原文：in favour of {@link Tag#ITEMS_FOOT_ARMOR}
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag BOOTS = replacedBy(Tag.ITEMS_FOOT_ARMOR, "boots");

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有盔甲变种。
     * <p>原文：
     * Covers all variants of armor.
     */
    @SuppressWarnings("unchecked")
    public static final MaterialSetTag ARMOR = new MaterialSetTag(keyFor("armor"))
        .add(Tag.ITEMS_HEAD_ARMOR, Tag.ITEMS_CHEST_ARMOR, Tag.ITEMS_LEG_ARMOR, Tag.ITEMS_FOOT_ARMOR)
        .lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖弓的变种。
     * <p>原文：
     * Covers the variants of bows.
     */
    public static final MaterialSetTag BOWS = new MaterialSetTag(keyFor("bows"))
        .add(Material.BOW)
        .add(Material.CROSSBOW)
        .lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖玩家可投掷的弹射物变种（无需弓或其他“辅助”）。
     * <p>原文：
     * Covers the variants of player-throwable projectiles (not requiring a bow or any other "assistance").
     */
    @SuppressWarnings("unchecked")
    public static final MaterialSetTag THROWABLE_PROJECTILES = new MaterialSetTag(keyFor("throwable_projectiles"))
        .add(Tag.ITEMS_EGGS)
        .add(Material.SNOWBALL, Material.SPLASH_POTION, Material.LINGERING_POTION,
            Material.TRIDENT, Material.ENDER_PEARL, Material.EXPERIENCE_BOTTLE, Material.FIREWORK_ROCKET,
            Material.WIND_CHARGE)
        .lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖可着色的材料，例如羊毛、潜影盒、染色玻璃等。
     * <p>原文：
     * Covers materials that can be colored, such as wool, shulker boxes, stained glasses etc.
     */
    @SuppressWarnings("unchecked")
    public static final MaterialSetTag COLORABLE = new MaterialSetTag(keyFor("colorable"))
        .add(Tag.WOOL, Tag.WOOL_CARPETS, Tag.SHULKER_BOXES, Tag.BEDS)
        .add(STAINED_GLASS, STAINED_GLASS_PANES, CONCRETES)
        .lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖珊瑚的变种。
     * <p>原文：
     * Covers the variants of coral.
     */
    public static final MaterialSetTag CORAL = new MaterialSetTag(keyFor("coral"))
        .endsWith("_CORAL")
        .ensureSize("CORAL", 10).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖珊瑚扇的变种。
     * <p>原文：
     * Covers the variants of coral fans.
     */
    public static final MaterialSetTag CORAL_FANS = new MaterialSetTag(keyFor("coral_fans"))
        .endsWith("_CORAL_FAN")
        .endsWith("_CORAL_WALL_FAN")
        .ensureSize("CORAL_FANS", 20).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖珊瑚块的变种。
     * <p>原文：
     * Covers the variants of coral blocks.
     */
    public static final MaterialSetTag CORAL_BLOCKS = new MaterialSetTag(keyFor("coral_blocks"))
        .endsWith("_CORAL_BLOCK")
        .ensureSize("CORAL_BLOCKS", 10).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有可在附魔台或铁砧上附魔的物品。
     * <p>原文：
     * Covers all items that can be enchanted from the enchantment table or anvil.
     *
     * @deprecated 改用 {@link Tag#ITEMS_ENCHANTABLE_VANISHING} 及其他类似的可附魔标签
     * <p>原文：in favour of {@link Tag#ITEMS_ENCHANTABLE_VANISHING} and other similar enchantable tags
     */
    @Deprecated(since = "1.21.8")
    public static final MaterialSetTag ENCHANTABLE = replacedBy(Tag.ITEMS_ENCHANTABLE_VANISHING, "enchantable"); // this tag already cover the others

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖粗矿的变种。
     * <p>原文：
     * Covers the variants of raw ores.
     */
    public static final MaterialSetTag RAW_ORES = new MaterialSetTag(keyFor("raw_ores"))
        .add(Material.RAW_COPPER, Material.RAW_GOLD, Material.RAW_IRON).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有命令方块类型。
     * <p>原文：
     * Covers all command block types.
     */
    public static final MaterialSetTag COMMAND_BLOCKS = new MaterialSetTag(keyFor("command_blocks"))
        .endsWith("COMMAND_BLOCK")
        .ensureSize("COMMAND_BLOCKS", 3).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖深板岩矿石的变种。
     * <p>原文：
     * Covers the variants of deepslate ores.
     */
    public static final MaterialSetTag DEEPSLATE_ORES = new MaterialSetTag(keyFor("deepslate_ores"))
        .add(material -> material.name().startsWith("DEEPSLATE_") && material.name().endsWith("_ORE"))
        .ensureSize("DEEPSLATE_ORES", 8).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖粗矿块的变种。
     * <p>原文：
     * Covers the variants of raw ore blocks.
     */
    public static final MaterialSetTag RAW_ORE_BLOCKS = new MaterialSetTag(keyFor("raw_ore_blocks"))
        .add(Material.RAW_COPPER_BLOCK, Material.RAW_GOLD_BLOCK, Material.RAW_IRON_BLOCK).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有氧化的铜方块。
     * <p>原文：
     * Covers all oxidized copper blocks.
     */
    public static final MaterialSetTag OXIDIZED_COPPER_BLOCKS = new MaterialSetTag(keyFor("oxidized_copper_blocks"))
        .startsWith("OXIDIZED_").startsWith("WAXED_OXIDIZED_").ensureSize("OXIDIZED_COPPER_BLOCKS", 30).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有锈蚀的铜方块。
     * <p>原文：
     * Covers all weathered copper blocks.
     */
    public static final MaterialSetTag WEATHERED_COPPER_BLOCKS = new MaterialSetTag(keyFor("weathered_copper_blocks"))
        .startsWith("WEATHERED_").startsWith("WAXED_WEATHERED_").ensureSize("WEATHERED_COPPER_BLOCKS", 30).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有斑驳的铜方块。
     * <p>原文：
     * Covers all exposed copper blocks.
     */
    public static final MaterialSetTag EXPOSED_COPPER_BLOCKS = new MaterialSetTag(keyFor("exposed_copper_blocks"))
        .startsWith("EXPOSED_").startsWith("WAXED_EXPOSED_").ensureSize("EXPOSED_COPPER_BLOCKS", 30).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有未锈蚀的铜方块。
     * <p>原文：
     * Covers all un-weathered copper blocks.
     */
    public static final MaterialSetTag UNAFFECTED_COPPER_BLOCKS = new MaterialSetTag(keyFor("unaffected_copper_blocks"))
        .startsWith("CUT_COPPER").startsWith("WAXED_CUT_COPPER")
        .startsWith("WAXED_COPPER_").startsWith("COPPER_")
        .add(Material.CHISELED_COPPER, Material.WAXED_CHISELED_COPPER)
        .not(Material.COPPER_INGOT, Material.COPPER_ORE, Material.COPPER_NUGGET)
        .not(Material.COPPER_HORSE_ARMOR, Material.COPPER_GOLEM_SPAWN_EGG)
        .not(Material.COPPER_HELMET, Material.COPPER_CHESTPLATE, Material.COPPER_LEGGINGS, Material.COPPER_BOOTS)
        .not(Material.COPPER_AXE, Material.COPPER_HOE, Material.COPPER_PICKAXE, Material.COPPER_SHOVEL, Material.COPPER_SWORD)
        .not(Material.COPPER_NAUTILUS_ARMOR, Material.COPPER_SPEAR)
        .ensureSize("UNAFFECTED_COPPER_BLOCKS", 30).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有涂蜡的铜方块。
     * <p>
     * 可与其他铜相关标签组合，以筛选是否已涂蜡。
     * <p>原文：
     * Covers all waxed copper blocks.
     * <p>
     * Combine with other copper-related tags to filter is-waxed or not.
     */
    public static final MaterialSetTag WAXED_COPPER_BLOCKS = new MaterialSetTag(keyFor("waxed_copper_blocks"))
        .add(m -> m.name().startsWith("WAXED_") && m.name().contains("COPPER")).ensureSize("WAXED_COPPER_BLOCKS", 56).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有未涂蜡的铜方块。
     * <p>
     * 可与其他铜相关标签组合，以筛选是否未涂蜡。
     * <p>原文：
     * Covers all un-waxed copper blocks.
     * <p>
     * Combine with other copper-related tags to filter is-un-waxed or not.
     */
    public static final MaterialSetTag UNWAXED_COPPER_BLOCKS = new MaterialSetTag(keyFor("unwaxed_copper_blocks"))
        .startsWith("EXPOSED_").startsWith("WEATHERED_").startsWith("OXIDIZED_")
        .startsWith("CUT_COPPER")
        .add(Material.COPPER_BLOCK, Material.CHISELED_COPPER, Material.COPPER_DOOR, Material.COPPER_TRAPDOOR, Material.COPPER_GRATE, Material.COPPER_BULB)
        .add(Material.COPPER_BARS, Material.COPPER_TORCH, Material.COPPER_CHEST, Material.COPPER_CHAIN, Material.COPPER_WALL_TORCH, Material.COPPER_LANTERN, Material.COPPER_GOLEM_STATUE)
        .ensureSize("UNWAXED_COPPER_BLOCKS", 61).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有铜方块变种。
     * <p>原文：
     * Covers all copper block variants.
     */
    public static final MaterialSetTag COPPER_BLOCKS = new MaterialSetTag(keyFor("copper_blocks"))
        .add(WAXED_COPPER_BLOCKS).add(UNWAXED_COPPER_BLOCKS).ensureSize("COPPER_BLOCKS", 117).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖普通铜块的所有氧化/涂蜡状态。
     * <p>原文：
     * Covers all weathering/waxed states of the plain copper block.
     */
    public static final MaterialSetTag FULL_COPPER_BLOCKS = new MaterialSetTag(keyFor("full_copper_blocks"))
        .endsWith("OXIDIZED_COPPER")
        .endsWith("WEATHERED_COPPER")
        .endsWith("EXPOSED_COPPER")
        .endsWith("COPPER_BLOCK")
        .not(Material.RAW_COPPER_BLOCK)
        .ensureSize("FULL_COPPER_BLOCKS", 8).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖切制铜块的所有氧化/涂蜡状态。
     * <p>原文：
     * Covers all weathering/waxed states of the cut copper block.
     */
    public static final MaterialSetTag CUT_COPPER_BLOCKS = new MaterialSetTag(keyFor("cut_copper_blocks"))
        .endsWith("CUT_COPPER").ensureSize("CUT_COPPER_BLOCKS", 8).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖切制铜楼梯的所有氧化/涂蜡状态。
     * <p>原文：
     * Covers all weathering/waxed states of the cut copper stairs.
     */
    public static final MaterialSetTag CUT_COPPER_STAIRS = new MaterialSetTag(keyFor("cut_copper_stairs"))
        .endsWith("CUT_COPPER_STAIRS").ensureSize("CUT_COPPER_STAIRS", 8).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖切制铜台阶的所有氧化/涂蜡状态。
     * <p>原文：
     * Covers all weathering/waxed states of the cut copper slab.
     */
    public static final MaterialSetTag CUT_COPPER_SLABS = new MaterialSetTag(keyFor("cut_copper_slabs"))
        .endsWith("CUT_COPPER_SLAB").ensureSize("CUT_COPPER_SLABS", 8).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有木工具。
     * <p>原文：
     * Covers all Wooden Tools.
     */
    public static final MaterialSetTag WOODEN_TOOLS = new MaterialSetTag(keyFor("wooden_tools"))
        .add(Material.WOODEN_AXE, Material.WOODEN_HOE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL, Material.WOODEN_SWORD, Material.WOODEN_SPEAR)
        .lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有石工具。
     * <p>原文：
     * Covers all Stone Tools.
     */
    public static final MaterialSetTag STONE_TOOLS = new MaterialSetTag(keyFor("stone_tools"))
        .add(Material.STONE_AXE, Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_SHOVEL, Material.STONE_SWORD, Material.STONE_SPEAR)
        .lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有铜工具。
     * <p>原文：
     * Covers all copper Tools.
     */
    public static final MaterialSetTag COPPER_TOOLS = new MaterialSetTag(keyFor("copper_tools"))
        .add(Material.COPPER_AXE, Material.COPPER_HOE, Material.COPPER_PICKAXE, Material.COPPER_SHOVEL, Material.COPPER_SWORD, Material.COPPER_SPEAR)
        .lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有铁工具。
     * <p>原文：
     * Covers all Iron Tools.
     */
    public static final MaterialSetTag IRON_TOOLS = new MaterialSetTag(keyFor("iron_tools"))
        .add(Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SHOVEL, Material.IRON_SWORD, Material.IRON_SPEAR)
        .lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有金工具。
     * <p>原文：
     * Covers all Gold Tools.
     */
    public static final MaterialSetTag GOLDEN_TOOLS = new MaterialSetTag(keyFor("golden_tools"))
        .add(Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL, Material.GOLDEN_SWORD, Material.GOLDEN_SPEAR)
        .lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有钻石工具。
     * <p>原文：
     * Covers all Diamond Tools.
     */
    public static final MaterialSetTag DIAMOND_TOOLS = new MaterialSetTag(keyFor("diamond_tools"))
        .add(Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_SWORD, Material.DIAMOND_SPEAR)
        .lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有下界合金工具。
     * <p>原文：
     * Covers all Netherite Tools.
     */
    public static final MaterialSetTag NETHERITE_TOOLS = new MaterialSetTag(keyFor("netherite_tools"))
        .add(Material.NETHERITE_AXE, Material.NETHERITE_HOE, Material.NETHERITE_PICKAXE, Material.NETHERITE_SHOVEL, Material.NETHERITE_SWORD, Material.NETHERITE_SPEAR)
        .lock();

}
