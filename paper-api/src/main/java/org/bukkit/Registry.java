package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.tag.Tag;
import io.papermc.paper.registry.tag.TagKey;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import net.kyori.adventure.key.Key;
import org.bukkit.advancement.Advancement;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockType;
import org.bukkit.block.banner.PatternType;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.inventory.ItemType;
import org.bukkit.inventory.MenuType;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.loot.LootTables;
import org.bukkit.map.MapCursor;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示可以通过 {@link NamespacedKey} 检索的 Bukkit 对象注册表。
 * <p>原文：
 * Represents a registry of Bukkit objects that may be retrieved by
 * {@link NamespacedKey}.
 *
 * @param <T> 注册表中项目的类型
 * <p>原文：type of item in the registry
 */
@NullMarked
public interface Registry<T extends Keyed> extends Iterable<T> {

    private static <A extends Keyed> Registry<A> registryFor(final RegistryKey<A> registryKey) {
        return RegistryAccess.registryAccess().getRegistry(registryKey);
    }

    @SuppressWarnings("removal")
    @Deprecated(forRemoval = true, since = "1.21.4")
    private static <A extends Keyed> Registry<A> legacyRegistryFor(final Class<A> clazz) {
        return Objects.requireNonNull(RegistryAccess.registryAccess().getRegistry(clazz), () -> "No registry present for " + clazz.getSimpleName() + ". This is a bug.");
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器进度。
     * <p>原文：
     * Server advancements.
     *
     * @see Bukkit#getAdvancement(NamespacedKey)
     * @see Bukkit#advancementIterator()
     * @deprecated Advancement 没有真正的服务端注册表。
     * <p>原文：use {@link Bukkit#getAdvancement(NamespacedKey)} and {@link Bukkit#advancementIterator()}
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    Registry<Advancement> ADVANCEMENT = new NotARegistry<>() {

        @Override
        public @Nullable Advancement get(final NamespacedKey key) {
            return Bukkit.getAdvancement(key);
        }

        @Override
        public Iterator<Advancement> iterator() {
            return Bukkit.advancementIterator();
        }
    };
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器艺术品。
     * <p>原文：
     * Server art.
     *
     * @see Art
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#PAINTING_VARIANT}
     */
    @Deprecated(since = "1.21.3") // Paper
    Registry<Art> ART = legacyRegistryFor(Art.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 属性。
     * <p>原文：
     * Attribute.
     *
     * @see Attribute
     */
    Registry<Attribute> ATTRIBUTE = registryFor(RegistryKey.ATTRIBUTE);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器旗帜图案。
     * <p>原文：
     * Server banner patterns.
     *
     * @see PatternType
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#BANNER_PATTERN}
     */
    @Deprecated(since = "1.21") // Paper
    Registry<PatternType> BANNER_PATTERN = legacyRegistryFor(PatternType.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器生物群系。
     * <p>原文：
     * Server biomes.
     *
     * @see Biome
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#BIOME}
     */
    @Deprecated(since = "1.21.3") // Paper
    Registry<Biome> BIOME = legacyRegistryFor(Biome.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器方块类型。
     * <p>原文：
     * Server block types.
     *
     * @see BlockType
     */
    Registry<BlockType> BLOCK = registryFor(RegistryKey.BLOCK);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 自定义 Boss 血条。
     * <p>原文：
     * Custom boss bars.
     *
     * @see Bukkit#getBossBar(org.bukkit.NamespacedKey)
     * @see Bukkit#getBossBars()
     * @deprecated BossBar 没有真正的服务端注册表。
     * <p>原文：use {@link Bukkit#getBossBar(NamespacedKey)} and {@link Bukkit#getBossBars()}
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    Registry<KeyedBossBar> BOSS_BARS = new NotARegistry<>() {

        @Override
        public @Nullable KeyedBossBar get(final NamespacedKey key) {
            return Bukkit.getBossBar(key);
        }

        @Override
        public Iterator<KeyedBossBar> iterator() {
            return Bukkit.getBossBars();
        }
    };
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器猫品种。
     * <p>原文：
     * Server cat types.
     *
     * @see Cat.Type
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#CAT_VARIANT}
     */
    @Deprecated(since = "1.21.5")
    Registry<Cat.Type> CAT_VARIANT = legacyRegistryFor(Cat.Type.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器附魔。
     * <p>原文：
     * Server enchantments.
     *
     * @see Enchantment
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#ENCHANTMENT}
     */
    @Deprecated(since = "1.21")
    Registry<Enchantment> ENCHANTMENT = legacyRegistryFor(Enchantment.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器实体类型。
     * <p>原文：
     * Server entity types.
     *
     * @see EntityType
     */
    Registry<EntityType> ENTITY_TYPE = registryFor(RegistryKey.ENTITY_TYPE);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器乐器。
     * <p>原文：
     * Server instruments.
     *
     * @see MusicInstrument
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#INSTRUMENT}
     */
    @Deprecated(since = "1.21.2")
    Registry<MusicInstrument> INSTRUMENT = legacyRegistryFor(MusicInstrument.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器物品类型。
     * <p>原文：
     * Server item types.
     *
     * @see ItemType
     */
    Registry<ItemType> ITEM = registryFor(RegistryKey.ITEM);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 默认服务器战利品表。
     * <p>原文：
     * Default server loot tables.
     *
     * @see LootTables
     */
    Registry<LootTables> LOOT_TABLES = new SimpleRegistry<>(LootTables.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器材质。
     * <p>原文：
     * Server materials.
     *
     * @see Material
     */
    Registry<Material> MATERIAL = new SimpleRegistry<>(Material.class, (mat) -> !mat.isLegacy());
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器菜单。
     * <p>原文：
     * Server menus.
     *
     * @see MenuType
     */
    @ApiStatus.Experimental
    Registry<MenuType> MENU = registryFor(RegistryKey.MENU);
    /**
     * Server mob effects.
     *
     * @see PotionEffectType
     */
    Registry<PotionEffectType> MOB_EFFECT = registryFor(RegistryKey.MOB_EFFECT);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器粒子。
     * <p>原文：
     * Server particles.
     *
     * @see Particle
     */
    Registry<Particle> PARTICLE_TYPE = registryFor(RegistryKey.PARTICLE_TYPE); // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器药水。
     * <p>原文：
     * Server potions.
     *
     * @see PotionType
     */
    Registry<PotionType> POTION = registryFor(RegistryKey.POTION); // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器统计。
     * <p>原文：
     * Server statistics.
     *
     * @see Statistic
     */
    Registry<Statistic> STATISTIC = new SimpleRegistry<>(Statistic.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器结构。
     * <p>原文：
     * Server structures.
     *
     * @see Structure
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#STRUCTURE}
     */
    @Deprecated(since = "1.20.6") // Paper
    Registry<Structure> STRUCTURE = legacyRegistryFor(Structure.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器结构类型。
     * <p>原文：
     * Server structure types.
     *
     * @see StructureType
     */
    Registry<StructureType> STRUCTURE_TYPE = registryFor(RegistryKey.STRUCTURE_TYPE);
    /**
     * Sound events.
     *
     * @see Sound
     */
    Registry<Sound> SOUND_EVENT = registryFor(RegistryKey.SOUND_EVENT);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 装饰材料。
     * <p>原文：
     * Trim materials.
     *
     * @see TrimMaterial
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#TRIM_MATERIAL}
     */
    @Deprecated(since = "1.20.6") // Paper
    Registry<TrimMaterial> TRIM_MATERIAL = legacyRegistryFor(TrimMaterial.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 装饰图案。
     * <p>原文：
     * Trim patterns.
     *
     * @see TrimPattern
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#TRIM_PATTERN}
     */
    @Deprecated(since = "1.20.6")
    Registry<TrimPattern> TRIM_PATTERN = legacyRegistryFor(TrimPattern.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 伤害类型。
     * <p>原文：
     * Damage types.
     *
     * @see DamageType
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#DAMAGE_TYPE}
     */
    @Deprecated(since = "1.20.6")
    Registry<DamageType> DAMAGE_TYPE = legacyRegistryFor(DamageType.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 唱片机歌曲。
     * <p>原文：
     * Jukebox songs.
     *
     * @see JukeboxSong
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#JUKEBOX_SONG}
     */
    @Deprecated(since = "1.21")
    Registry<JukeboxSong> JUKEBOX_SONG = legacyRegistryFor(JukeboxSong.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 村民职业。
     * <p>原文：
     * Villager profession.
     *
     * @see Villager.Profession
     */
    Registry<Villager.Profession> VILLAGER_PROFESSION = registryFor(RegistryKey.VILLAGER_PROFESSION);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 村民类型。
     * <p>原文：
     * Villager type.
     *
     * @see Villager.Type
     */
    Registry<Villager.Type> VILLAGER_TYPE = registryFor(RegistryKey.VILLAGER_TYPE);
    /**
     * Memory Keys.
     *
     * @see MemoryKey
     */
    Registry<MemoryKey<?>> MEMORY_MODULE_TYPE = new NotARegistry<>() {

        @Override
        public Iterator<MemoryKey<?>> iterator() {
            return MemoryKey.values().iterator();
        }

        @Override
        public int size() {
            return MemoryKey.values().size();
        }

        @Override
        public @Nullable MemoryKey<?> get(final NamespacedKey key) {
            return MemoryKey.getByKey(key);
        }
    };
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器流体。
     * <p>原文：
     * Server fluids.
     *
     * @see Fluid
     */
    Registry<Fluid> FLUID = registryFor(RegistryKey.FLUID);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 青蛙变种。
     * <p>原文：
     * Frog variants.
     *
     * @see Frog.Variant
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#FROG_VARIANT}
     */
    @Deprecated(since = "1.21.5")
    Registry<Frog.Variant> FROG_VARIANT = legacyRegistryFor(Frog.Variant.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 狼变种。
     * <p>原文：
     * Wolf variants.
     *
     * @see Wolf.Variant
     * @deprecated use {@link RegistryAccess#getRegistry(RegistryKey)} with {@link RegistryKey#WOLF_VARIANT}
     */
    @Deprecated(since = "1.20.6")
    Registry<Wolf.Variant> WOLF_VARIANT = legacyRegistryFor(Wolf.Variant.class);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 地图光标类型。
     * <p>原文：
     * Map cursor types.
     *
     * @see MapCursor.Type
     */
    Registry<MapCursor.Type> MAP_DECORATION_TYPE = registryFor(RegistryKey.MAP_DECORATION_TYPE);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 游戏事件。
     * <p>原文：
     * Game events.
     *
     * @see GameEvent
     * @see io.papermc.paper.registry.event.RegistryEvents#GAME_EVENT
     */
    Registry<GameEvent> GAME_EVENT = registryFor(RegistryKey.GAME_EVENT);
    /**
     * Data component types.
     *
     * @see DataComponentType
     */
    Registry<DataComponentType> DATA_COMPONENT_TYPE = registryFor(RegistryKey.DATA_COMPONENT_TYPE);
    /**
     * Game rules.
     *
     * @see GameRule
     */
    Registry<GameRule<?>> GAME_RULE = registryFor(RegistryKey.GAME_RULE);

    //<editor-fold desc="renames" defaultstate="collapsed">
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 服务器药水效果。
     *
     * @apiNote use {@link #MOB_EFFECT} instead
     * @hidden
     */
    @ApiStatus.Obsolete(since = "1.21.4")
    Registry<PotionEffectType> EFFECT = MOB_EFFECT;
    /**
     * @apiNote use {@link #MOB_EFFECT} instead
     * @hidden
     */
    @ApiStatus.Obsolete(since = "1.21.4")
    Registry<org.bukkit.potion.PotionEffectType> POTION_EFFECT_TYPE = EFFECT;
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 声音键。
     *
     * @apiNote use {@link #SOUND_EVENT}
     * @hidden
     */
    @ApiStatus.Obsolete(since = "1.21.4")
    Registry<Sound> SOUNDS = registryFor(RegistryKey.SOUND_EVENT);
    //</editor-fold>

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过键获取对象。
     * <p>原文：
     * Get the object by its key.
     *
     * @param key 非空键。
     * <p>原文：non-null key
     * @return 对象，如果不存在则返回 null。
     *
     * 原文：
     * Get the object by its key.
     * <p>原文：item or null if it does not exist
     */
    @Nullable T get(NamespacedKey key);
    // Paper start

    /**
     * Get the object by its key.
     *
     * @param key non-null key
     * @return item or null if it does not exist
     */
    default @Nullable T get(final Key key) {
        return key instanceof final NamespacedKey nsKey ? this.get(nsKey) : this.get(new NamespacedKey(key.namespace(), key.value()));
    }

    /**
     * Get the object by its typed key.
     *
     * @param typedKey non-null typed key
     * @return item or null if it does not exist
     */
    default @Nullable T get(final TypedKey<T> typedKey) {
        Preconditions.checkArgument(typedKey != null, "typedKey cannot be null");
        return this.get(typedKey.key());
    }
    // Paper end

    // Paper start - improve Registry

    /**
     * Gets the object by its key or throws if it doesn't exist.
     *
     * @param key the key to get the object of in this registry
     * @return the object for the key
     * @throws NoSuchElementException if the key doesn't point to an object in the registry
     */
    default T getOrThrow(final net.kyori.adventure.key.Key key) {
        Preconditions.checkArgument(key != null, "key cannot be null");
        final T value = this.get(key);
        if (value == null) {
            throw new NoSuchElementException("No value for " + key + " in " + this);
        }
        return value;
    }

    /**
     * Gets the object by its key or throws if it doesn't exist.
     *
     * @param key the key to get the object of in this registry
     * @return the object for the key
     * @throws NoSuchElementException if the key doesn't point to an object in the registry
     */
    default T getOrThrow(final TypedKey<T> key) {
        final T value = this.get(key);
        if (value == null) {
            throw new NoSuchElementException("No value for " + key + " in " + this);
        }
        return value;
    }

    /**
     * Gets the key for this object or throws if it doesn't exist.
     * <p>
     * Some types can exist without being in a registry
     * and such will have no key associated with them. This
     * method throw an exception if it isn't in this registry.
     *
     * @param value the value to get the key of in this registry
     * @return the key for the value
     * @throws NoSuchElementException if the value doesn't exist in this registry
     * @see #getKey(Keyed)
     */
    default NamespacedKey getKeyOrThrow(final T value) {
        final NamespacedKey key = this.getKey(value);
        if (key == null) {
            throw new NoSuchElementException(value + " has no key in " + this);
        }
        return key;
    }

    /**
     * Get the key for this object.
     * <p>
     * Some types can exist without being in a registry
     * and such will have no key associated with them. This
     * method will return null.
     *
     * @param value the value to get the key of in this registry
     * @return the key for the value or null if not in the registry
     * @see #getKeyOrThrow(Keyed)
     */
    @Nullable NamespacedKey getKey(T value);
    // Paper end - improve Registry

    // Paper start - RegistrySet API
    /**
     * Checks if this registry has a tag with the given key.
     *
     * @param key the key to check for
     * @return true if this registry has a tag with the given key, false otherwise
     * @throws UnsupportedOperationException if this registry doesn't have or support tags
     * @see #getTag(TagKey)
     */
    boolean hasTag(TagKey<T> key);

    /**
     * Gets the named registry set (tag) for the given key.
     *
     * @param key the key to get the tag for
     * @return the tag for the key
     * @throws NoSuchElementException if no tag with the given key is found
     * @throws UnsupportedOperationException    if this registry doesn't have or support tags
     * @see #hasTag(TagKey)
     * @see #getTagValues(TagKey)
     */
    @ApiStatus.Experimental
    Tag<T> getTag(TagKey<T> key);

    /**
     * Gets the named registry set (tag) for the given key and resolves it with this registry.
     *
     * @param key the key to get the tag for
     * @return the resolved values
     * @throws NoSuchElementException        if no tag with the given key is found
     * @throws UnsupportedOperationException if this registry doesn't have or support tags
     * @see #getTag(TagKey)
     * @see Tag#resolve(Registry)
     */
    @ApiStatus.Experimental
    default Collection<T> getTagValues(final TagKey<T> key) {
        Tag<T> tag = this.getTag(key);
        return tag.resolve(this);
    }

    /**
     * Gets all the tags in this registry.
     *
     * @return a stream of all tags in this registry
     * @throws UnsupportedOperationException if this registry doesn't have or support tags
     */
    @ApiStatus.Experimental
    Collection<Tag<T>> getTags();
    // Paper end - RegistrySet API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过键获取对象。
     *
     * 如果没有具有给定键的对象，将抛出异常。
     * <p>原文：
     * Get the object by its key.
     * <p>
     * If there is no object with the given key, an exception will be thrown.
     *
     * @param key 用于获取对象的键。
     * <p>原文：to get the object from
     * @return 具有给定键的对象。
     * <p>原文：object with the given key
     * @throws NoSuchElementException if there is no object with the given key
     */
    default T getOrThrow(final NamespacedKey key) {
        return this.getOrThrow((Key) key);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回一个包含所有已注册到注册表中的注册项的新流。
     * <p>原文：
     * Returns a new stream, which contains all registry items, which are registered to the registry.
     *
     * @return 所有注册项的流。
     *
     * 原文：
     * Returns a new stream, which contains all registry items, which are registered to the registry.
     * <p>原文：a stream of all registry items
     */
    Stream<T> stream();

    /**
     * Returns a new stream, which contains all registry keys, which are registered to the registry.
     *
     * @return a stream of all registry keys
     */
    Stream<NamespacedKey> keyStream();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试将注册对象与给定键匹配。
     * <p>
     * 将尝试根据提供的输入找到合理的匹配，可能通过未指定的方式进行。
     * <p>原文：
     * Attempts to match the registered object with the given key.
     * <p>
     * This will attempt to find a reasonable match based on the provided input
     * and may do so through unspecified means.
     *
     * @param input 非空输入。
     * <p>原文：non-null input
     * @return 注册对象，如果不存在则返回 null。
     *
     * 原文：
     * Attempts to match the registered object with the given key.
     * <p>
     * This will attempt to find a reasonable match based on the provided input
     * and may do so through unspecified means.
     * <p>原文：registered object or null if does not exist
     * @deprecated this method's behavior is broken and not useful. If you want to get an object
     * based on its vanilla name, or a key, wrap it in a {@link NamespacedKey} object and use {@link #get(NamespacedKey)}
     */
    @Deprecated(forRemoval = true)
    default @Nullable T match(final String input) {
        Preconditions.checkArgument(input != null, "input must not be null");

        final String filtered = input.toLowerCase(Locale.ROOT).replaceAll("\\s+", "_");
        final NamespacedKey namespacedKey = NamespacedKey.fromString(filtered);
        return (namespacedKey != null) ? this.get(namespacedKey) : null;
    }

    /**
     * Gets the size of the registry.
     *
     * @return the size of the registry
     */
    int size();

    /**
     * @hidden
     */
    @ApiStatus.Internal
    class SimpleRegistry<T extends Enum<T> & Keyed> extends NotARegistry<T> { // Paper - remove final

        private final Class<T> type;
        private final Map<NamespacedKey, T> map;

        protected SimpleRegistry(final Class<T> type) {
            this(type, Predicates.alwaysTrue());
        }

        protected SimpleRegistry(final Class<T> type, final Predicate<T> predicate) {
            final ImmutableMap.Builder<NamespacedKey, T> builder = ImmutableMap.builder();

            for (final T entry : type.getEnumConstants()) {
                if (predicate.test(entry)) {
                    builder.put(entry.getKey(), entry);
                }
            }

            this.map = builder.build();
            this.type = type;
        }

        @Override
        public @Nullable T get(final NamespacedKey key) {
            return this.map.get(key);
        }

        @Override
        public int size() {
            return map.size();
        }

        @Override
        public Iterator<T> iterator() {
            return this.map.values().iterator();
        }

        @Override
        public Stream<NamespacedKey> keyStream() {
            return this.map.keySet().stream();
        }

        @ApiStatus.Internal
        @Deprecated(since = "1.20.6", forRemoval = true)
        public Class<T> getType() {
            return this.type;
        }
    }

    /**
     * @hidden
     */
    @ApiStatus.Internal
    abstract class NotARegistry<A extends Keyed> implements Registry<A> {

        @Override
        public Stream<A> stream() {
            return StreamSupport.stream(this.spliterator(), false);
        }

        @Override
        public Stream<NamespacedKey> keyStream() {
            return stream().map(this::getKey);
        }

        @Override
        public int size() {
            return Iterables.size(this);
        }

        @Override
        public NamespacedKey getKey(final A value) {
            return value.getKey();
        }

        @Override
        public boolean hasTag(final TagKey<A> key) {
            throw new UnsupportedOperationException("This is not a real registry and therefore cannot support tags");
        }

        @Override
        public Tag<A> getTag(final TagKey<A> key) {
            throw new UnsupportedOperationException("This is not a real registry and therefore cannot support tags");
        }

        @Override
        public Collection<Tag<A>> getTags() {
            throw new UnsupportedOperationException("This is not a real registry and therefore cannot support tags");
        }
    }
}
