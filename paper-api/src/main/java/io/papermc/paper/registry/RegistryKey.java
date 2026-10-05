package io.papermc.paper.registry;

import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.tag.TagKey;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import net.kyori.adventure.key.Keyed;
import org.bukkit.Art;
import org.bukkit.Fluid;
import org.bukkit.GameEvent;
import org.bukkit.GameRule;
import org.bukkit.JukeboxSong;
import org.bukkit.MusicInstrument;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockType;
import org.bukkit.block.banner.PatternType;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.ZombieNautilus;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.inventory.ItemType;
import org.bukkit.inventory.MenuType;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.map.MapCursor;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jspecify.annotations.NullMarked;

import static io.papermc.paper.registry.RegistryKeyImpl.create;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 特定注册表的标识符。用于 {@link TypedKey} 和注册表修改 API。
 * <p>
 * 注册表分为两类：“内置”和“数据驱动”。前者无法通过数据包更改（这并不一定意味着无法通过 API 更改），并且最先加载。“数据驱动”注册表则全部通过读取原版及其他数据包中的数据创建。
 * <p>原文：
 * Identifier for a specific registry. For use with
 * {@link TypedKey} and the registry modification API.
 * <p>
 * There are 2 types of registries, identified as "built-in"
 * or "data-driven". The former are not changeable by datapacks (which
 * doesn't necessarily mean they aren't changeable in the API) and
 * are loaded first. "Data-driven" registries are all created by
 * reading in data from the vanilla and other datapacks.
 *
 * @param <T> 值类型
 * <p>原文：the value type
 */
@SuppressWarnings("unused")
@NullMarked
public sealed interface RegistryKey<T> extends Keyed permits RegistryKeyImpl {

    /* ******************* *
     * Built-in Registries *
     * ******************* */
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 游戏事件的内置注册表
     * <p>原文：
     * Built-in registry for game events
     * @see io.papermc.paper.registry.keys.GameEventKeys
     */
    RegistryKey<GameEvent> GAME_EVENT = create("game_event");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 结构类型的内置注册表。
     * <p>原文：
     * Built-in registry for structure types.
     * @see io.papermc.paper.registry.keys.StructureTypeKeys
     */
    RegistryKey<StructureType> STRUCTURE_TYPE = create("worldgen/structure_type");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 药水效果类型（生物效果）的内置注册表。
     * <p>原文：
     * Built-in registry for potion effect types (mob effects).
     * @see io.papermc.paper.registry.keys.MobEffectKeys
     */
    RegistryKey<PotionEffectType> MOB_EFFECT = create("mob_effect");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 方块类型的内置注册表。
     * <p>原文：
     * Built-in registry for block types.
     * @see io.papermc.paper.registry.keys.BlockTypeKeys
     */
    RegistryKey<BlockType> BLOCK = create("block");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 物品类型的内置注册表。
     * <p>原文：
     * Built-in registry for item types.
     * @see io.papermc.paper.registry.keys.ItemTypeKeys
     */
    RegistryKey<ItemType> ITEM = create("item");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 村民职业的内置注册表。
     * <p>原文：
     * Built-in registry for villager professions.
     * @see io.papermc.paper.registry.keys.VillagerProfessionKeys
     */
    RegistryKey<Villager.Profession> VILLAGER_PROFESSION = create("villager_profession");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 村民类型的内置注册表。
     * <p>原文：
     * Built-in registry for villager types.
     * @see io.papermc.paper.registry.keys.VillagerTypeKeys
     */
    RegistryKey<Villager.Type> VILLAGER_TYPE = create("villager_type");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 地图装饰类型的内置注册表。
     * <p>原文：
     * Built-in registry for map decoration types.
     * @see io.papermc.paper.registry.keys.MapDecorationTypeKeys
     */
    RegistryKey<MapCursor.Type> MAP_DECORATION_TYPE = create("map_decoration_type");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 菜单类型的内置注册表。
     * <p>原文：
     * Built-in registry for menu types.
     * @see io.papermc.paper.registry.keys.MenuTypeKeys
     */
    RegistryKey<MenuType> MENU = create("menu");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 属性的内置注册表。
     * <p>原文：
     * Built-in registry for attributes.
     * @see io.papermc.paper.registry.keys.AttributeKeys
     */
    RegistryKey<Attribute> ATTRIBUTE = create("attribute");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 流体的内置注册表。
     * <p>原文：
     * Built-in registry for fluids.
     * @see io.papermc.paper.registry.keys.FluidKeys
     */
    RegistryKey<Fluid> FLUID = create("fluid");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 声音事件的内置注册表。
     * <p>原文：
     * Built-in registry for sound events.
     * @see io.papermc.paper.registry.keys.SoundEventKeys
     */
    RegistryKey<Sound> SOUND_EVENT = create("sound_event");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 数据组件类型的内置注册表。
     * <p>原文：
     * Built-in registry for data component types.
     * @see io.papermc.paper.registry.keys.DataComponentTypeKeys
     */
    RegistryKey<DataComponentType> DATA_COMPONENT_TYPE = create("data_component_type");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 游戏规则的内置注册表。
     * <p>原文：
     * Built-in registry for game rules.
     * @see io.papermc.paper.registry.keys.GameRuleKeys
     */
    RegistryKey<GameRule<?>> GAME_RULE = create("game_rule");

    /* ********************** *
     * Data-driven Registries *
     * ********************** */
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 生物群系的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for biomes.
     * @see io.papermc.paper.registry.keys.BiomeKeys
     */
    RegistryKey<Biome> BIOME = create("worldgen/biome");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 结构的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for structures.
     * @see io.papermc.paper.registry.keys.StructureKeys
     */
    RegistryKey<Structure> STRUCTURE = create("worldgen/structure");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 纹饰材料的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for trim materials.
     * @see io.papermc.paper.registry.keys.TrimMaterialKeys
     */
    RegistryKey<TrimMaterial> TRIM_MATERIAL = create("trim_material");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 纹饰图案的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for trim patterns.
     * @see io.papermc.paper.registry.keys.TrimPatternKeys
     */
    RegistryKey<TrimPattern> TRIM_PATTERN = create("trim_pattern");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 伤害类型的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for damage types.
     * @see io.papermc.paper.registry.keys.DamageTypeKeys
     */
    RegistryKey<DamageType> DAMAGE_TYPE = create("damage_type");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 狼变种的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for wolf variants.
     * @see io.papermc.paper.registry.keys.WolfVariantKeys
     */
    RegistryKey<Wolf.Variant> WOLF_VARIANT = create("wolf_variant");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 狼声音变种的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for wolf sound variants.
     * @see io.papermc.paper.registry.keys.WolfSoundVariantKeys
     */
    RegistryKey<Wolf.SoundVariant> WOLF_SOUND_VARIANT = create("wolf_sound_variant");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 魔咒的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for enchantments.
     * @see io.papermc.paper.registry.keys.EnchantmentKeys
     */
    RegistryKey<Enchantment> ENCHANTMENT = create("enchantment");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 唱片机歌曲的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for jukebox songs.
     * @see io.papermc.paper.registry.keys.JukeboxSongKeys
     */
    RegistryKey<JukeboxSong> JUKEBOX_SONG = create("jukebox_song");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 旗帜图案的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for banner patterns.
     * @see io.papermc.paper.registry.keys.BannerPatternKeys
     */
    RegistryKey<PatternType> BANNER_PATTERN = create("banner_pattern");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 画变种的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for painting variants.
     * @see io.papermc.paper.registry.keys.PaintingVariantKeys
     */
    RegistryKey<Art> PAINTING_VARIANT = create("painting_variant");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 乐器的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for instruments.
     * @see io.papermc.paper.registry.keys.InstrumentKeys
     */
    RegistryKey<MusicInstrument> INSTRUMENT = create("instrument");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 猫变种的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for cat variants.
     * @see io.papermc.paper.registry.keys.CatVariantKeys
     */
    RegistryKey<Cat.Type> CAT_VARIANT = create("cat_variant");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 青蛙变种的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for frog variants.
     * @see io.papermc.paper.registry.keys.FrogVariantKeys
     */
    RegistryKey<Frog.Variant> FROG_VARIANT = create("frog_variant");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 鸡变种的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for chicken variants.
     * @see io.papermc.paper.registry.keys.ChickenVariantKeys
     */
    RegistryKey<Chicken.Variant> CHICKEN_VARIANT = create("chicken_variant");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 牛变种的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for cow variants.
     * @see io.papermc.paper.registry.keys.CowVariantKeys
     */
    RegistryKey<Cow.Variant> COW_VARIANT = create("cow_variant");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 猪变种的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for pig variants.
     * @see io.papermc.paper.registry.keys.PigVariantKeys
     */
    RegistryKey<Pig.Variant> PIG_VARIANT = create("pig_variant");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 僵尸鹦鹉螺变种的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for zombie nautilus variants.
     * @see io.papermc.paper.registry.keys.ZombieNautilusVariantKeys
     */
    RegistryKey<ZombieNautilus.Variant> ZOMBIE_NAUTILUS_VARIANT = create("zombie_nautilus_variant");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 对话框的数据驱动注册表。
     * <p>原文：
     * Data-driven registry for dialogs.
     * @see io.papermc.paper.registry.keys.DialogKeys
     */
    RegistryKey<Dialog> DIALOG = create("dialog");


    /* ******************* *
     * API-only Registries *
     * ******************* */
    RegistryKey<EntityType> ENTITY_TYPE = create("entity_type");
    RegistryKey<Particle> PARTICLE_TYPE = create("particle_type");
    RegistryKey<PotionType> POTION = create("potion");
    RegistryKey<MemoryKey<?>> MEMORY_MODULE_TYPE = create("memory_module_type");

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 根据类型化键的键为此注册表构造新的 {@link TypedKey}。
     * <p>原文：
     * Constructs a new {@link TypedKey} for this registry given the typed key's key.
     *
     * @param key 类型化键的键。
     * <p>原文：the key of the typed key.
     * @return 构造的类型化键。
     * <p>原文：the constructed typed key.
     */
    default TypedKey<T> typedKey(final Key key) {
        return TypedKey.create(this, key);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 根据类型化键的键为此注册表构造新的 {@link TypedKey}。
     * <p>原文：
     * Constructs a new {@link TypedKey} for this registry given the typed key's key.
     *
     * @param key 将传递给 {@link Key#key(String)} 的键的字符串表示。
     * <p>原文：the string representation of the key that will be passed to {@link Key#key(String)}.
     * @return 构造的类型化键。
     * <p>原文：the constructed typed key.
     */
    default TypedKey<T> typedKey(@KeyPattern final String key) {
        return TypedKey.create(this, key);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 根据标签键的键为此注册表构造新的 {@link TagKey}。
     * <p>原文：
     * Constructs a new {@link TagKey} for this registry given the tag key's key.
     *
     * @param key 类型化键的键。
     * <p>原文：the key of the typed key.
     * @return 构造的标签键。
     * <p>原文：the constructed tag key.
     */
    default TagKey<T> tagKey(final Key key) {
        return TagKey.create(this, key);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 根据标签键的键为此注册表构造新的 {@link TagKey}。
     * <p>原文：
     * Constructs a new {@link TagKey} for this registry given the tag key's key.
     *
     * @param key 将传递给 {@link Key#key(String)} 的键的字符串表示。
     * <p>原文：the string representation of the key that will be passed to {@link Key#key(String)}.
     * @return 构造的标签键。
     * <p>原文：the constructed tag key.
     */
    default TagKey<T> tagKey(@KeyPattern final String key) {
        return TagKey.create(this, key);
    }
}
