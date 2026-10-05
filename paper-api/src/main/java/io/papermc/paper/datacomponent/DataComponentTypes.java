package io.papermc.paper.datacomponent;

import io.papermc.paper.datacomponent.item.AttackRange;
import io.papermc.paper.datacomponent.item.BannerPatternLayers;
import io.papermc.paper.datacomponent.item.BlockItemDataProperties;
import io.papermc.paper.datacomponent.item.BlocksAttacks;
import io.papermc.paper.datacomponent.item.BundleContents;
import io.papermc.paper.datacomponent.item.ChargedProjectiles;
import io.papermc.paper.datacomponent.item.Consumable;
import io.papermc.paper.datacomponent.item.CustomModelData;
import io.papermc.paper.datacomponent.item.DamageResistant;
import io.papermc.paper.datacomponent.item.DeathProtection;
import io.papermc.paper.datacomponent.item.DyedItemColor;
import io.papermc.paper.datacomponent.item.Enchantable;
import io.papermc.paper.datacomponent.item.Equippable;
import io.papermc.paper.datacomponent.item.Fireworks;
import io.papermc.paper.datacomponent.item.FoodProperties;
import io.papermc.paper.datacomponent.item.ItemAdventurePredicate;
import io.papermc.paper.datacomponent.item.ItemArmorTrim;
import io.papermc.paper.datacomponent.item.ItemAttributeModifiers;
import io.papermc.paper.datacomponent.item.ItemContainerContents;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import io.papermc.paper.datacomponent.item.ItemLore;
import io.papermc.paper.datacomponent.item.JukeboxPlayable;
import io.papermc.paper.datacomponent.item.KineticWeapon;
import io.papermc.paper.datacomponent.item.LodestoneTracker;
import io.papermc.paper.datacomponent.item.MapDecorations;
import io.papermc.paper.datacomponent.item.MapId;
import io.papermc.paper.datacomponent.item.MapItemColor;
import io.papermc.paper.datacomponent.item.OminousBottleAmplifier;
import io.papermc.paper.datacomponent.item.PiercingWeapon;
import io.papermc.paper.datacomponent.item.PotDecorations;
import io.papermc.paper.datacomponent.item.PotionContents;
import io.papermc.paper.datacomponent.item.Repairable;
import io.papermc.paper.datacomponent.item.ResolvableProfile;
import io.papermc.paper.datacomponent.item.SeededContainerLoot;
import io.papermc.paper.datacomponent.item.SuspiciousStewEffects;
import io.papermc.paper.datacomponent.item.SwingAnimation;
import io.papermc.paper.datacomponent.item.Tool;
import io.papermc.paper.datacomponent.item.TooltipDisplay;
import io.papermc.paper.datacomponent.item.UseCooldown;
import io.papermc.paper.datacomponent.item.UseEffects;
import io.papermc.paper.datacomponent.item.UseRemainder;
import io.papermc.paper.datacomponent.item.Weapon;
import io.papermc.paper.datacomponent.item.WritableBookContent;
import io.papermc.paper.datacomponent.item.WrittenBookContent;
import io.papermc.paper.item.MapPostProcessing;
import io.papermc.paper.registry.tag.TagKey;
import java.util.List;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import net.kyori.adventure.text.Component;
import org.bukkit.Art;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.MusicInstrument;
import org.bukkit.Registry;
import org.bukkit.block.banner.PatternType;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Salmon;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.ZombieNautilus;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.common.value.qual.IntRange;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link org.bukkit.inventory.ItemStack ItemStacks} 和 {@link org.bukkit.inventory.ItemType ItemTypes} 可以拥有的所有不同数据类型。
 * <p>原文：
 * All the different types of data that {@link org.bukkit.inventory.ItemStack ItemStacks}
 * and {@link org.bukkit.inventory.ItemType ItemTypes} can have.
 */
@NullMarked
@ApiStatus.Experimental
public final class DataComponentTypes {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制此物品的最大堆叠数量。<br> 大于 1 的值与 {@link #MAX_DAMAGE} 组件互斥。
     * <p>原文：
     * Controls the maximum stacking size of this item.
     * <br>
     * Values greater than 1 are mutually exclusive with the {@link #MAX_DAMAGE} component.
     */
    public static final DataComponentType.Valued<@IntRange(from = 1, to = 99) Integer> MAX_STACK_SIZE = valued("max_stack_size");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制物品可承受的最大损伤值；如果没有此组件，物品不会受损。<br> 与大于 1 的 {@link #MAX_STACK_SIZE} 组件互斥。
     * <p>原文：
     * Controls the maximum amount of damage than an item can take,
     * if not present, the item cannot be damaged.
     * <br>
     * Mutually exclusive with the {@link #MAX_STACK_SIZE} component greater than 1.
     *
     * @see #DAMAGE
     */
    public static final DataComponentType.Valued<@Positive Integer> MAX_DAMAGE = valued("max_damage");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 可损坏物品（具有 {@link #MAX_DAMAGE} 组件）被扣除的耐久度，隐式默认值为：{@code 0}。
     * <p>原文：
     * The amount of durability removed from an item,
     * for damageable items (with the {@link #MAX_DAMAGE} component), has an implicit default value of: {@code 0}.
     *
     * @see #MAX_DAMAGE
     */
    public static final DataComponentType.Valued<@NonNegative Integer> DAMAGE = valued("damage");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果设置，物品在使用时不会损失任何耐久度。
     * <p>原文：
     * If set, the item will not lose any durability when used.
     */
    public static final DataComponentType.NonValued UNBREAKABLE = unvalued("unbreakable");

    public static final DataComponentType.Valued<UseEffects> USE_EFFECTS = valued("use_effects");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 物品的自定义名称覆盖（即用铁砧重命名所设置的名称）。
     * <p>原文：
     * Custom name override for an item (as set by renaming with an Anvil).
     *
     * @see #ITEM_NAME
     */
    public static final DataComponentType.Valued<Component> CUSTOM_NAME = valued("custom_name");
    public static final DataComponentType.Valued<Float> MINIMUM_ATTACK_CHARGE = valued("minimum_attack_charge");
    public static final DataComponentType.Valued<DamageType> DAMAGE_TYPE = valued("damage_type");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 存在时，用所含的聊天组件替换默认物品名称。
     * <p>
     * 与 {@link #CUSTOM_NAME} 的区别：
     * <ul>
     * <li>无法在铁砧中更改或移除</li>
     * <li>显示给玩家时不使用斜体样式</li>
     * <li>在适用的地方不显示标签（例如：旗帜标记、物品展示框中的名称）</li>
     * </ul>
     * <p>原文：
     * When present, replaces default item name with contained chat component.
     * <p>
     * Differences from {@link #CUSTOM_NAME}:
     * <ul>
     * <li>can't be changed or removed in Anvil</li>
     * <li>is not styled with italics when displayed to player</li>
     * <li>does not show labels where applicable
     *      (for example: banner markers, names in item frames)</li>
     * </ul>
     *
     * @see #CUSTOM_NAME
     */
    public static final DataComponentType.Valued<Component> ITEM_NAME = valued("item_name");
    public static final DataComponentType.Valued<Key> ITEM_MODEL = valued("item_model");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 要添加到物品提示框中的额外行。
     * <p>原文：
     * Additional lines to include in an item's tooltip.
     */
    public static final DataComponentType.Valued<ItemLore> LORE = valued("lore");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制物品名称的颜色。
     * <p>原文：
     * Controls the color of the item name.
     */
    public static final DataComponentType.Valued<ItemRarity> RARITY = valued("rarity");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制物品上的魔咒。<br> 如果非附魔书的物品上没有此组件，该物品将无法在铁砧中使用。
     * <p>原文：
     * Controls the enchantments on an item.
     * <br>
     * If not present on a non-enchantment book, this item will not work in an anvil.
     *
     * @see #STORED_ENCHANTMENTS
     */
    public static final DataComponentType.Valued<ItemEnchantments> ENCHANTMENTS = valued("enchantments");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制冒险模式玩家可以用此物品在哪些方块上放置。
     * <p>原文：
     * Controls which blocks a player in Adventure mode can place on with this item.
     */
    public static final DataComponentType.Valued<ItemAdventurePredicate> CAN_PLACE_ON = valued("can_place_on");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制冒险模式玩家可以用此物品破坏哪些方块。
     * <p>原文：
     * Controls which blocks a player in Adventure mode can break with this item.
     */
    public static final DataComponentType.Valued<ItemAdventurePredicate> CAN_BREAK = valued("can_break");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有应用于任意物品的属性修饰符；如果未设置，则具有基于物品类型默认属性的隐式默认值（例如武器的攻击伤害）。
     * <p>原文：
     * Holds attribute modifiers applied to any item,
     * if not set, has an implicit default value based on the item type's
     * default attributes (e.g. attack damage for weapons).
     */
    public static final DataComponentType.Valued<ItemAttributeModifiers> ATTRIBUTE_MODIFIERS = valued("attribute_modifiers");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制物品模型中的 minecraft:custom_model_data 属性。
     * <p>原文：
     * Controls the minecraft:custom_model_data property in the item model.
     */
    public static final DataComponentType.Valued<CustomModelData> CUSTOM_MODEL_DATA = valued("custom_model_data");
    public static final DataComponentType.Valued<TooltipDisplay> TOOLTIP_DISPLAY = valued("tooltip_display");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在铁砧中修改物品所需的额外经验花费。如果不存在，隐式默认值为：{@code 0}。
     * <p>原文：
     * The additional experience cost required to modify an item in an Anvil.
     * If not present, has an implicit default value of: {@code 0}.
     */
    public static final DataComponentType.Valued<@NonNegative Integer> REPAIR_COST = valued("repair_cost");
    // /**
    //  * Causes an item to not be pickable in the creative menu, currently not very useful.
    //  */
    // public static final DataComponentType.NonValued CREATIVE_SLOT_LOCK = unvalued("creative_slot_lock");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 覆盖物品上的附魔光效。如果不存在，则使用默认行为。
     * <p>原文：
     * Overrides the enchantment glint effect on an item.
     * If not present, default behaviour is used.
     */
    public static final DataComponentType.Valued<Boolean> ENCHANTMENT_GLINT_OVERRIDE = valued("enchantment_glint_override");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 标记弹射物物品在发射时是无实体的（即只能由创造模式玩家捡起）。
     * <p>原文：
     * Marks that a projectile item would be intangible when fired
     * (i.e. can only be picked up by a creative mode player).
     */
    public static final DataComponentType.NonValued INTANGIBLE_PROJECTILE = unvalued("intangible_projectile");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制食用应用该组件的物品时可能获得的食物收益。首先需要 {@link #CONSUMABLE} 组件才能允许食用。
     * <p>原文：
     * Controls potential food benefits gained when consuming the item the component is applied on.
     * Requires the {@link #CONSUMABLE} component to allow consumption in the first place.
     */
    public static final DataComponentType.Valued<FoodProperties> FOOD = valued("food");
    public static final DataComponentType.Valued<Consumable> CONSUMABLE = valued("consumable");
    public static final DataComponentType.Valued<UseRemainder> USE_REMAINDER = valued("use_remainder");
    public static final DataComponentType.Valued<UseCooldown> USE_COOLDOWN = valued("use_cooldown");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果存在，此物品不会受到指定伤害类型的损伤。
     * <p>原文：
     * If present, this item will not take damage from the specified damage types.
     */
    public static final DataComponentType.Valued<DamageResistant> DAMAGE_RESISTANT = valued("damage_resistant");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制物品作为工具时的行为。
     * <p>原文：
     * Controls the behavior of the item as a tool.
     */
    public static final DataComponentType.Valued<Tool> TOOL = valued("tool");
    public static final DataComponentType.Valued<Weapon> WEAPON = valued("weapon");
    public static final DataComponentType.Valued<Enchantable> ENCHANTABLE = valued("enchantable");
    public static final DataComponentType.Valued<Equippable> EQUIPPABLE = valued("equippable");
    public static final DataComponentType.Valued<Repairable> REPAIRABLE = valued("repairable");
    public static final DataComponentType.NonValued GLIDER = unvalued("glider");
    public static final DataComponentType.Valued<Key> TOOLTIP_STYLE = valued("tooltip_style");
    public static final DataComponentType.Valued<DeathProtection> DEATH_PROTECTION = valued("death_protection");
    public static final DataComponentType.Valued<BlocksAttacks> BLOCKS_ATTACKS = valued("blocks_attacks");
    public static final DataComponentType.Valued<PiercingWeapon> PIERCING_WEAPON = valued("piercing_weapon");
    public static final DataComponentType.Valued<KineticWeapon> KINETIC_WEAPON = valued("kinetic_weapon");
    public static final DataComponentType.Valued<AttackRange> ATTACK_RANGE = valued("attack_range");
    public static final DataComponentType.Valued<SwingAnimation> SWING_ANIMATION = valued("swing_animation");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为附魔书存储魔咒及其等级的列表。与 {@link #ENCHANTMENTS} 不同，魔咒提供的效果不会通过此组件生效。<br> 如果附魔书上没有此组件，它将无法在铁砧中使用。
     * <p>
     * 如果此组件存在于非附魔书的物品上，行为未定义（目前该组件的存在使 {@link #ENCHANTMENTS} 中的魔咒可以像该物品是附魔书一样被应用）。
     * <p>原文：
     * Stores list of enchantments and their levels for an Enchanted Book.
     * Unlike {@link #ENCHANTMENTS}, the effects provided by enchantments
     * do not apply from this component.
     * <br>
     * If not present on an Enchanted Book, it will not work in an anvil.
     * <p>
     * Has an undefined behaviour if present on an item that is not an Enchanted Book
     * (currently the presence of this component allows enchantments from {@link #ENCHANTMENTS}
     * to be applied as if this item was an Enchanted Book).
     *
     * @see #ENCHANTMENTS
     */
    public static final DataComponentType.Valued<ItemEnchantments> STORED_ENCHANTMENTS = valued("stored_enchantments");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示应用于可染色物品（位于 {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys#DYEABLE} 物品标签中）的颜色。
     * <p>原文：
     * Represents a color applied to a dyeable item (in the {@link io.papermc.paper.registry.keys.tags.ItemTypeTagKeys#DYEABLE} item tag).
     */
    public static final DataComponentType.Valued<DyedItemColor> DYED_COLOR = valued("dyed_color");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示 {@link org.bukkit.inventory.ItemType#FILLED_MAP} 物品上装饰的色调。
     * <p>原文：
     * Represents the tint of the decorations on the {@link org.bukkit.inventory.ItemType#FILLED_MAP} item.
     */
    public static final DataComponentType.Valued<MapItemColor> MAP_COLOR = valued("map_color");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 引用持有 {@link org.bukkit.inventory.ItemType#FILLED_MAP} 内容和标记的共享地图状态。
     * <p>原文：
     * References the shared map state holding map contents and markers for a {@link org.bukkit.inventory.ItemType#FILLED_MAP}.
     */
    public static final DataComponentType.Valued<MapId> MAP_ID = valued("map_id");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有要放置在 {@link org.bukkit.inventory.ItemType#FILLED_MAP} 上的标记列表（用于探险家地图）。
     * <p>原文：
     * Holds a list of markers to be placed on a {@link org.bukkit.inventory.ItemType#FILLED_MAP} (used for Explorer Maps).
     */
    public static final DataComponentType.Valued<MapDecorations> MAP_DECORATIONS = valued("map_decorations");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 地图合成配方中使用的内部地图物品状态。
     * <p>原文：
     * Internal map item state used in the map crafting recipe.
     */
    public static final DataComponentType.Valued<MapPostProcessing> MAP_POST_PROCESSING = valued("map_post_processing");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有已装填到弩中的所有弹射物。如果不存在，则弩未装填。
     * <p>原文：
     * Holds all projectiles that have been loaded into a Crossbow.
     * If not present, the Crossbow is not charged.
     */
    public static final DataComponentType.Valued<ChargedProjectiles> CHARGED_PROJECTILES = valued("charged_projectiles");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有收纳袋中存放的所有物品。如果移除，将无法向收纳袋中添加物品。
     * <p>原文：
     * Holds all items stored inside a Bundle.
     * If removed, items cannot be added to the Bundle.
     */
    public static final DataComponentType.Valued<BundleContents> BUNDLE_CONTENTS = valued("bundle_contents");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有药水（药水、喷溅药水、滞留药水）的内容，或应用于药箭的药水。
     * <p>原文：
     * Holds the contents of a potion (Potion, Splash Potion, Lingering Potion),
     * or potion applied to a Tipped Arrow.
     */
    public static final DataComponentType.Valued<PotionContents> POTION_CONTENTS = valued("potion_contents");
    public static final DataComponentType.Valued<Float> POTION_DURATION_SCALE = valued("potion_duration_scale");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有食用迷之炖菜时将施加的效果。
     * <p>原文：
     * Holds the effects that will be applied when consuming Suspicious Stew.
     */
    public static final DataComponentType.Valued<SuspiciousStewEffects> SUSPICIOUS_STEW_EFFECTS = valued("suspicious_stew_effects");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有书与笔中的内容。
     * <p>原文：
     * Holds the contents in a Book and Quill.
     */
    public static final DataComponentType.Valued<WritableBookContent> WRITABLE_BOOK_CONTENT = valued("writable_book_content");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有成书的内容和元数据。
     * <p>原文：
     * Holds the contents and metadata of a Written Book.
     */
    public static final DataComponentType.Valued<WrittenBookContent> WRITTEN_BOOK_CONTENT = valued("written_book_content");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有配方中应用于物品的纹饰
     * <p>原文：
     * Holds the trims applied to an item in recipes
     */
    public static final DataComponentType.Valued<ItemArmorTrim> TRIM = valued("trim");
    // debug_stick_state - Block Property API
    // entity_data
    // bucket_entity_data
    // block_entity_data
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有山羊角使用的乐器类型。
     * <p>原文：
     * Holds the instrument type used by a Goat Horn.
     */
    public static final DataComponentType.Valued<MusicInstrument> INSTRUMENT = valued("instrument");
    // this is a either holder, but due to legacy item loading
    public static final DataComponentType.Valued<TrimMaterial> PROVIDES_TRIM_MATERIAL = valued("provides_trim_material");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制不祥之瓶所给予的不祥之兆效果的倍率。
     * <p>原文：
     * Controls the amplifier amount for an Ominous Bottle's Bad Omen effect.
     */
    public static final DataComponentType.Valued<OminousBottleAmplifier> OMINOUS_BOTTLE_AMPLIFIER = valued("ominous_bottle_amplifier");
    public static final DataComponentType.Valued<JukeboxPlayable> JUKEBOX_PLAYABLE = valued("jukebox_playable");
    public static final DataComponentType.Valued<TagKey<PatternType>> PROVIDES_BANNER_PATTERNS = valued("provides_banner_patterns");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用知识之书物品时应解锁的配方列表。
     * <p>原文：
     * List of recipes that should be unlocked when using the Knowledge Book item.
     */
    public static final DataComponentType.Valued<List<Key>> RECIPES = valued("recipes");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果存在，表示该指南针是磁石指针。
     * <p>原文：
     * If present, specifies that the Compass is a Lodestone Compass.
     */
    public static final DataComponentType.Valued<LodestoneTracker> LODESTONE_TRACKER = valued("lodestone_tracker");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 存储合成到烟火之星中的爆炸效果。
     * <p>原文：
     * Stores the explosion crafted in a Firework Star.
     */
    public static final DataComponentType.Valued<FireworkEffect> FIREWORK_EXPLOSION = valued("firework_explosion");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 存储合成到烟花火箭中的所有爆炸效果以及飞行时间。
     * <p>原文：
     * Stores all explosions crafted into a Firework Rocket, as well as flight duration.
     */
    public static final DataComponentType.Valued<Fireworks> FIREWORKS = valued("fireworks");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制玩家头颅上显示的皮肤。
     * <p>原文：
     * Controls the skin displayed on a Player Head.
     */
    public static final DataComponentType.Valued<ResolvableProfile> PROFILE = valued("profile");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制玩家头颅放在音符盒上时播放的声音。
     * <p>原文：
     * Controls the sound played by a Player Head when placed on a Note Block.
     */
    public static final DataComponentType.Valued<Key> NOTE_BLOCK_SOUND = valued("note_block_sound");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 存储应用于旗帜或盾牌的额外图案。
     * <p>原文：
     * Stores the additional patterns applied to a Banner or Shield.
     */
    public static final DataComponentType.Valued<BannerPatternLayers> BANNER_PATTERNS = valued("banner_patterns");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 存储盾牌的底色。
     * <p>原文：
     * Stores the base color for a Shield.
     */
    public static final DataComponentType.Valued<DyeColor> BASE_COLOR = valued("base_color");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 存储应用于饰纹陶罐各面的陶片。
     * <p>原文：
     * Stores the Sherds applied to each side of a Decorated Pot.
     */
    public static final DataComponentType.Valued<PotDecorations> POT_DECORATIONS = valued("pot_decorations");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 以物品形式持有容器方块（箱子、潜影盒）的内容。
     * <p>原文：
     * Holds the contents of container blocks (Chests, Shulker Boxes) in item form.
     */
    public static final DataComponentType.Valued<ItemContainerContents> CONTAINER = valued("container");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有放置方块时要应用的方块状态属性。
     * <p>原文：
     * Holds block state properties to apply when placing a block.
     */
    public static final DataComponentType.Valued<BlockItemDataProperties> BLOCK_DATA = valued("block_state");
    // bees
    // /**
    //  * Holds the lock state of a container-like block,
    //  * copied to container block when placed.
    //  * <br>
    //  * An item with a custom name of the same value must be used
    //  * to open this container.
    //  */
    // public static final DataComponentType.Valued<LockCode> LOCK = valued("lock");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有类容器方块尚未解析的战利品表和种子。
     * <p>原文：
     * Holds the unresolved loot table and seed of a container-like block.
     */
    public static final DataComponentType.Valued<SeededContainerLoot> CONTAINER_LOOT = valued("container_loot");
    public static final DataComponentType.Valued<Key> BREAK_SOUND = valued("break_sound");
    public static final DataComponentType.Valued<Villager.Type> VILLAGER_VARIANT = valued("villager/variant");
    public static final DataComponentType.Valued<Wolf.Variant> WOLF_VARIANT = valued("wolf/variant");
    public static final DataComponentType.Valued<Wolf.SoundVariant> WOLF_SOUND_VARIANT = valued("wolf/sound_variant");
    public static final DataComponentType.Valued<DyeColor> WOLF_COLLAR = valued("wolf/collar");
    public static final DataComponentType.Valued<Fox.Type> FOX_VARIANT = valued("fox/variant");
    public static final DataComponentType.Valued<Salmon.Variant> SALMON_SIZE = valued("salmon/size");
    public static final DataComponentType.Valued<Parrot.Variant> PARROT_VARIANT = valued("parrot/variant");
    public static final DataComponentType.Valued<TropicalFish.Pattern> TROPICAL_FISH_PATTERN = valued("tropical_fish/pattern");
    public static final DataComponentType.Valued<DyeColor> TROPICAL_FISH_BASE_COLOR = valued("tropical_fish/base_color");
    public static final DataComponentType.Valued<DyeColor> TROPICAL_FISH_PATTERN_COLOR = valued("tropical_fish/pattern_color");
    public static final DataComponentType.Valued<MushroomCow.Variant> MOOSHROOM_VARIANT = valued("mooshroom/variant");
    public static final DataComponentType.Valued<Rabbit.Type> RABBIT_VARIANT = valued("rabbit/variant");
    public static final DataComponentType.Valued<Pig.Variant> PIG_VARIANT = valued("pig/variant");
    public static final DataComponentType.Valued<Cow.Variant> COW_VARIANT = valued("cow/variant");
    public static final DataComponentType.Valued<Chicken.Variant> CHICKEN_VARIANT = valued("chicken/variant");
    // This is a eitherholder? Why specifically the chicken?? Oh wait this is prolly for chicken egg cause legacy item loading
    public static final DataComponentType.Valued<Frog.Variant> FROG_VARIANT = valued("frog/variant");
    public static final DataComponentType.Valued<Horse.Color> HORSE_VARIANT = valued("horse/variant");
    public static final DataComponentType.Valued<Art> PAINTING_VARIANT = valued("painting/variant");
    public static final DataComponentType.Valued<Llama.Color> LLAMA_VARIANT = valued("llama/variant");
    public static final DataComponentType.Valued<Axolotl.Variant> AXOLOTL_VARIANT = valued("axolotl/variant");
    public static final DataComponentType.Valued<ZombieNautilus.Variant> ZOMBIE_NAUTILUS_VARIANT = valued("zombie_nautilus/variant");
    public static final DataComponentType.Valued<Cat.Type> CAT_VARIANT = valued("cat/variant");
    public static final DataComponentType.Valued<DyeColor> CAT_COLLAR = valued("cat/collar");
    public static final DataComponentType.Valued<DyeColor> SHEEP_COLOR = valued("sheep/color");
    public static final DataComponentType.Valued<DyeColor> SHULKER_COLOR = valued("shulker/color");

    private static DataComponentType.NonValued unvalued(final @KeyPattern.Value String key) {
        final DataComponentType dataComponentType = Registry.DATA_COMPONENT_TYPE.getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
        if (dataComponentType instanceof DataComponentType.NonValued) {
            return (DataComponentType.NonValued) dataComponentType;
        }
        throw new IllegalStateException(key + " is not a valid unvalued type, it is a " + dataComponentType.getClass().getTypeName());
    }

    @SuppressWarnings("unchecked")
    private static <T> DataComponentType.Valued<T> valued(final @KeyPattern.Value String key) {
        final DataComponentType dataComponentType = Registry.DATA_COMPONENT_TYPE.getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
        if (dataComponentType instanceof DataComponentType.Valued) {
            return (DataComponentType.Valued<T>) dataComponentType;
        }
        throw new IllegalStateException(key + " is not a valid valued type, it is a " + dataComponentType.getClass().getTypeName());
    }

    private DataComponentTypes() {
    }
}
