package org.bukkit;

import com.google.common.base.Preconditions;
import java.util.Random;
import org.bukkit.command.CommandSender;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示可能用于创建一个世界的各种类型的设置。
 * <p>原文：
 * Represents various types of options that may be used to create a world.
 */
public class WorldCreator {
    private final NamespacedKey key; // Paper
    private final String name;
    private long seed;
    private World.Environment environment = World.Environment.NORMAL;
    private ChunkGenerator generator = null;
    private BiomeProvider biomeProvider = null;
    private WorldType type = WorldType.NORMAL;
    private boolean generateStructures = true;
    private String generatorSettings = "";
    private boolean hardcore = false;
    private boolean bonusChest = false;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定世界的名称创建一个空的WorldCreationOptions类型。
     * <p>原文：
     * Creates an empty WorldCreationOptions for the given world name
     *
     * @param name 将被创建的世界的名称
     * <p>原文：Name of the world that will be created
     */
    public WorldCreator(@NotNull String name) {
        // Paper start
        this(name, getWorldKey(name));
    }

    private static NamespacedKey getWorldKey(String name) {
        final String mainLevelName = Bukkit.getUnsafe().getMainLevelName();
        if (name.equals(mainLevelName)) {
            return NamespacedKey.minecraft("overworld");
        } else if (name.equals(mainLevelName + "_nether")) {
            return NamespacedKey.minecraft("the_nether");
        } else if (name.equals(mainLevelName + "_the_end")) {
            return NamespacedKey.minecraft("the_end");
        } else {
            return NamespacedKey.minecraft(name.toLowerCase(java.util.Locale.ENGLISH).replace(" ", "_"));
        }
    }

    /**
     * Creates an empty WorldCreator for the given world name and key
     *
     * @param levelName LevelName of the world that will be created
     * @param worldKey NamespacedKey of the world that will be created
     */
    public WorldCreator(@NotNull String levelName, @NotNull NamespacedKey worldKey) {
        if (levelName == null || worldKey == null) {
            throw new IllegalArgumentException("World name and key cannot be null");
        }
        this.name = levelName;
        this.seed = (new Random()).nextLong();
        this.key = worldKey;
    }

    /**
     * Creates an empty WorldCreator for the given key.
     * LevelName will be the Key part of the NamespacedKey.
     *
     * @param worldKey NamespacedKey of the world that will be created
     */
    public WorldCreator(@NotNull NamespacedKey worldKey) {
        this(worldKey.getKey(), worldKey);
    }

    /**
     * Gets the key for this WorldCreator
     *
     * @return the key
     */
    @NotNull
    public NamespacedKey key() {
        return key;
    }

    /**
     * Creates an empty WorldCreator for the given world name and key
     *
     * @param levelName LevelName of the world that will be created
     * @param worldKey NamespacedKey of the world that will be created
     */
    @NotNull
    public static WorldCreator ofNameAndKey(@NotNull String levelName, @NotNull NamespacedKey worldKey) {
        return new WorldCreator(levelName, worldKey);
    }

    /**
     * Creates an empty WorldCreator for the given key.
     * LevelName will be the Key part of the NamespacedKey.
     *
     * @param worldKey NamespacedKey of the world that will be created
     */
    @NotNull
    public static WorldCreator ofKey(@NotNull NamespacedKey worldKey) {
        return new WorldCreator(worldKey);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从指定的世界复制设置。
     * <p>原文：
     * Copies the options from the specified world
     *
     * @param world 用于复制设置的世界来源
     * <p>原文：World to copy options from
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator copy(@NotNull World world) {
        Preconditions.checkArgument(world != null, "World cannot be null");

        seed = world.getSeed();
        environment = world.getEnvironment();
        generator = world.getGenerator();
        biomeProvider = world.getBiomeProvider();
        type = world.getWorldType();
        generateStructures = world.canGenerateStructures();
        hardcore = world.isHardcore();
        bonusChest = world.hasBonusChest();

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从指定的{@link WorldCreator}复制设置。
     * <p>原文：
     * Copies the options from the specified {@link WorldCreator}
     *
     * @param creator 用于复制设置的世界来源
     * <p>原文：World creator to copy options from
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator copy(@NotNull WorldCreator creator) {
        Preconditions.checkArgument(creator != null, "Creator cannot be null");

        seed = creator.seed();
        environment = creator.environment();
        generator = creator.generator();
        biomeProvider = creator.biomeProvider();
        type = creator.type();
        generateStructures = creator.generateStructures();
        generatorSettings = creator.generatorSettings();
        hardcore = creator.hardcore();
        bonusChest = creator.bonusChest();

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被加载或创建的世界的名称。
     * <p>原文：
     * Gets the name of the world that is to be loaded or created.
     *
     * @return 世界名称
     * <p>原文：World name
     */
    @NotNull
    public String name() {
        return name;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于创建这个世界的种子。
     * <p>原文：
     * Gets the seed that will be used to create this world
     *
     * @return 世界种子
     * <p>原文：World seed
     */
    public long seed() {
        return seed;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置用于创建这个世界的种子。
     * <p>原文：
     * Sets the seed that will be used to create this world
     *
     * @param seed 世界种子
     * <p>原文：World seed
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator seed(long seed) {
        this.seed = seed;

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于创建或加载世界的环境。
     * <p>原文：
     * Gets the environment that will be used to create or load the world
     *
     * @return 世界的环境
     * <p>原文：World environment
     */
    @NotNull
    public World.Environment environment() {
        return environment;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置用于创建或加载世界的环境。
     * <p>原文：
     * Sets the environment that will be used to create or load the world
     *
     * @param env 世界的环境
     * <p>原文：World environment
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator environment(@NotNull World.Environment env) {
        this.environment = env;

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被创建或加载的世界的类型。
     * <p>原文：
     * Gets the type of the world that will be created or loaded
     *
     * @return 世界的类型
     * <p>原文：World type
     */
    @NotNull
    public WorldType type() {
        return type;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将被创建或加载的世界的类型。
     * <p>原文：
     * Sets the type of the world that will be created or loaded
     *
     * @param type 世界的类型
     * <p>原文：World type
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator type(@NotNull WorldType type) {
        this.type = type;

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于创建或加载世界的生成器。
     * <p>
     * 可能为null值，在这种情况下会使用这个环境的"natural"型生成器。
     * <p>原文：
     * Gets the generator that will be used to create or load the world.
     * <p>
     * This may be null, in which case the "natural" generator for this
     * environment will be used.
     *
     * @return 区块生成器
     * <p>原文：Chunk generator
     */
    @Nullable
    public ChunkGenerator generator() {
        return generator;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将被用于创建或加载世界的生成器。
     * <p>
     * 可能为null值，在这种情况下会使用这个环境的"natural"型生成器。
     * <p>原文：
     * Sets the generator that will be used to create or load the world.
     * <p>
     * This may be null, in which case the "natural" generator for this
     * environment will be used.
     *
     * @param generator 区块生成器
     * <p>原文：Chunk generator
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator generator(@Nullable ChunkGenerator generator) {
        this.generator = generator;

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将被用于创建或加载世界的生成器。
     * <p>
     * 可能为null值，在这种情况下会使用这个环境的"natural"型生成器。
     * <p>
     * 如果找不到指定名字的生成器，就会使用自然环境生成器以代替并且会在控制台上输出一个警告。
     * <p>原文：
     * Sets the generator that will be used to create or load the world.
     * <p>
     * This may be null, in which case the "natural" generator for this
     * environment will be used.
     * <p>
     * If the generator cannot be found for the given name, the natural
     * environment generator will be used instead and a warning will be
     * printed to the console.
     *
     * @param generator 使用的生成器的名字，形式为"plugin:id"
     * <p>原文：Name of the generator to use, in "plugin:id" notation
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator generator(@Nullable String generator) {
        this.generator = getGeneratorForName(name, generator, Bukkit.getConsoleSender());

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将用于创建或加载的生成器。
     * <p>
     * 可能为null值，在这种情况下会使用这个环境的"natural"型生成器。
     * <p>
     * 如果找不到指定名字的生成器，就会使用自然环境生成器以代替并且会在控制台上输出一个警告。
     * <p>原文：
     * Sets the generator that will be used to create or load the world.
     * <p>
     * This may be null, in which case the "natural" generator for this
     * environment will be used.
     * <p>
     * If the generator cannot be found for the given name, the natural
     * environment generator will be used instead and a warning will be
     * printed to the specified output
     *
     * @param generator 使用的生成器的名字，形式为"plugin:id"
     * <p>原文：Name of the generator to use, in "plugin:id" notation
     * @param output 将用于接受任何错误信息的{@link CommandSender}
     * <p>原文：{@link CommandSender} that will receive any error
     *     messages
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator generator(@Nullable String generator, @Nullable CommandSender output) {
        this.generator = getGeneratorForName(name, generator, output);

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被用于创建或加载世界的生物群系提供器。
     * <p>
     * 可能为null值，在这种情况下会使用{@link ChunkGenerator}的生物群系提供器。
     * 如果没有指定{@link ChunkGenerator}，则会使用此环境的"natural"型生物群系提供器。
     * <p>原文：
     * Gets the biome provider that will be used to create or load the world.
     * <p>
     * This may be null, in which case the biome provider from the {@link ChunkGenerator}
     * will be used. If no {@link ChunkGenerator} is specific the "natural" biome provider
     * for this environment will be used.
     *
     * @return 生物群系提供器
     * <p>原文：Biome provider
     */
    @Nullable
    public BiomeProvider biomeProvider() {
        return biomeProvider;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将被用于创建或加载世界的生物群系提供器。
     * <p>
     * 可能为null值，在这种情况下会使用{@link ChunkGenerator}的生物群系提供器。
     * 如果没有指定{@link ChunkGenerator}，则会使用此环境的"natural"型生物群系提供器。
     * <p>原文：
     * Sets the biome provider that will be used to create or load the world.
     * <p>
     * This may be null, in which case the biome provider from the
     * {@link ChunkGenerator} will be used. If no {@link ChunkGenerator} is
     * specific the "natural" biome provider for this environment will be used.
     *
     * @param biomeProvider 生物群系提供器
     * <p>原文：Biome provider
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator biomeProvider(@Nullable BiomeProvider biomeProvider) {
        this.biomeProvider = biomeProvider;

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将被用于创建或加载世界的生物群系提供器。
     * <p>
     * 可能为null值，在这种情况下会使用{@link ChunkGenerator}的生物群系提供器。
     * 如果没有指定{@link ChunkGenerator}，则会使用此环境的"natural"型生物群系提供器。
     * <p>
     * 如果找不到指定名字的生物群系提供器且没有指定{@link ChunkGenerator}，则会使用自然环境的生物群系提供器以代替并且会向指定的输出处输出一条警告。
     * <p>原文：
     * Sets the biome provider that will be used to create or load the world.
     * <p>
     * This may be null, in which case the biome provider from the
     * {@link ChunkGenerator} will be used. If no {@link ChunkGenerator} is
     * specific the "natural" biome provider for this environment will be used.
     * <p>
     * If the biome provider cannot be found for the given name and no
     * {@link ChunkGenerator} is specific, the natural environment biome
     * provider will be used instead and a warning will be printed to the
     * specified output
     *
     * @param biomeProvider 使用的生物群系提供器的名字，形式为"plugin:id"
     * <p>原文：Name of the biome provider to use, in "plugin:id"
     * notation
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator biomeProvider(@Nullable String biomeProvider) {
        this.biomeProvider = getBiomeProviderForName(name, biomeProvider, Bukkit.getConsoleSender());

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将被用于创建或加载世界的生物群系提供器。
     * <p>
     * 可能为null值，在这种情况下会使用{@link ChunkGenerator}的生物群系提供器。
     * 如果没有指定{@link ChunkGenerator}，则会使用此环境的"natural"型生物群系提供器。
     * <p>
     * 如果找不到指定名字的生物群系提供器且没有指定{@link ChunkGenerator}，则会使用自然环境的生物群系提供器以代替并且会向指定的输出处输出一条警告。
     * <p>原文：
     * Sets the biome provider that will be used to create or load the world.
     * <p>
     * This may be null, in which case the biome provider from the
     * {@link ChunkGenerator} will be used. If no {@link ChunkGenerator} is
     * specific the "natural" biome provider for this environment will be used.
     * <p>
     * If the biome provider cannot be found for the given name and no
     * {@link ChunkGenerator} is specific, the natural environment biome
     * provider will be used instead and a warning will be printed to the
     * specified output
     *
     * @param biomeProvider 使用的生物群系提供器的名字，形式为"plugin:id"
     * <p>原文：Name of the biome provider to use, in "plugin:id"
     * notation
     * @param output 将用于接收任何错误信息的{@link CommandSender}
     * <p>原文：{@link CommandSender} that will receive any error messages
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator biomeProvider(@Nullable String biomeProvider, @Nullable CommandSender output) {
        this.biomeProvider = getBiomeProviderForName(name, biomeProvider, output);

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将被创建或加载的世界的生成器配置。
     * <p>
     * 目前只有{@link WorldType#FLAT}使用这些配置，并且期望它们是JSON格式且定义了有效的生物群系（1.18.2及以上版本）。一个有效的配置示例如下：
     * <code>{"layers": [{"block": "stone", "height": 1}, {"block": "grass_block", "height": 1}], "biome":"plains"}</code>
     * <p>原文：
     * Sets the generator settings of the world that will be created or loaded.
     * <p>
     * Currently only {@link WorldType#FLAT} uses these settings, and expects
     * them to be in JSON format with a valid biome (1.18.2 and
     * above) defined. An example valid configuration is as follows:
     * <code>{"layers": [{"block": "stone", "height": 1}, {"block": "grass_block", "height": 1}], "biome":"plains"}</code>
     *
     * @param generatorSettings 生成器应使用的配置
     * <p>原文：The settings that should be used by the
     * generator
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     * @see <a href="https://minecraft.wiki/w/Custom_dimension">Custom
     * dimension</a> (scroll to "When the generator ID type is
     * <code>minecraft:flat</code>)"
     */
    @NotNull
    public WorldCreator generatorSettings(@NotNull String generatorSettings) {
        this.generatorSettings = generatorSettings;

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被创建或加载的世界的生成器的配置。
     * <p>原文：
     * Gets the generator settings of the world that will be created or loaded.
     *
     * @return 将被使用的生成器的配置
     * <p>原文：The settings that should be used by the generator
     * @see #generatorSettings(java.lang.String)
     */
    @NotNull
    public String generatorSettings() {
        return generatorSettings;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置被这个生成器创建或加载的世界是否会拥有建筑。
     * <p>原文：
     * Sets whether or not worlds created or loaded with this creator will
     * have structures.
     *
     * @param generate 是否生成建筑
     * <p>原文：Whether to generate structures
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator generateStructures(boolean generate) {
        this.generateStructures = generate;

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界是否生成建筑。
     * <p>原文：
     * Gets whether or not structures will be generated in the world.
     *
     * @return True 如果生成建筑则返回true
     * <p>原文：True if structures will be generated
     */
    public boolean generateStructures() {
        return generateStructures;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置世界是否为极限模式。
     * <p>
     * 在极限模式世界中，难度将被锁定为困难。
     * <p>原文：
     * Sets whether the world will be hardcore or not.
     *
     * In a hardcore world the difficulty will be locked to hard.
     *
     * @param hardcore 世界是否为极限模式
     * <p>原文：Whether the world will be hardcore
     * @return 用于连接的对象
     * <p>原文：This object, for chaining
     */
    @NotNull
    public WorldCreator hardcore(boolean hardcore) {
        this.hardcore = hardcore;

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界是否为极限模式。
     * <p>
     * 在极限模式世界中，难度将被锁定为困难。
     * <p>原文：
     * Gets whether the world will be hardcore or not.
     * <p>
     * In a hardcore world the difficulty will be locked to hard.
     *
     * @return 极限模式状态
     * <p>原文：hardcore status
     */
    public boolean hardcore() {
        return hardcore;
    }

    /**
     * Sets whether a bonus chest should be generated or not.
     *
     * @param bonusChest indicating whether the bonus chest should be generated
     * @return This object, for chaining
     */
    @NotNull
    public WorldCreator bonusChest(final boolean bonusChest) {
        this.bonusChest = bonusChest;
        return this;
    }

    /**
     * Gets whether the bonus chest feature is enabled.
     *
     * @return true if the bonus chest is enabled, false otherwise.
     */
    public boolean bonusChest() {
        return bonusChest;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用指定的设置创建一个世界。
     * <p>
     * 如果世界存在，它就会从磁盘被加载，一些设置可能被忽略。
     * <p>原文：
     * Creates a world with the specified options.
     * <p>
     * If the world already exists, it will be loaded from disk and some
     * options may be ignored.
     *
     * @return 最近创建或加载的世界
     * <p>原文：Newly created or loaded world
     */
    @Nullable
    public World createWorld() {
        return Bukkit.createWorld(this);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定的世界名创建一个新的{@link WorldCreator}。
     * <p>原文：
     * Creates a new {@link WorldCreator} for the given world name
     *
     * @param name 加载或创建的世界名
     * <p>原文：Name of the world to load or create
     * @return 创建的世界生成器
     * <p>原文：Resulting WorldCreator
     */
    @NotNull
    public static WorldCreator name(@NotNull String name) {
        return new WorldCreator(name);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 试图使用指定的名称获取{@link ChunkGenerator}。
     * <p>
     * 如果生成器找不到，则会返回null并会向指定的{@link CommandSender}输出一条信息来解释原因。
     * <p>
     * 名称的形式必须为"plugin:id"或"plugin"形式，请求的插件的"plugin"为一个插件的安全名称并且"id"是生成器的可选的唯一的标识符。
     * <p>原文：
     * Attempts to get the {@link ChunkGenerator} with the given name.
     * <p>
     * If the generator is not found, null will be returned and a message will
     * be printed to the specified {@link CommandSender} explaining why.
     * <p>
     * The name must be in the "plugin:id" notation, or optionally just
     * "plugin", where "plugin" is the safe-name of a plugin and "id" is an
     * optional unique identifier for the generator you wish to request from
     * the plugin.
     *
     * @param world 将被使用的世界名
     * <p>原文：Name of the world this will be used for
     * @param name 检索的生成器的名字
     * <p>原文：Name of the generator to retrieve
     * @param output 错误发生时的输出处
     * <p>原文：Where to output if errors are present
     * @return 若存在则返回获取的生成器，否则返回null
     * <p>原文：Resulting generator, or null
     */
    @Nullable
    public static ChunkGenerator getGeneratorForName(@NotNull String world, @Nullable String name, @Nullable CommandSender output) {
        Preconditions.checkArgument(world != null, "World name must be specified");
        ChunkGenerator result = null;

        if (output == null) {
            output = Bukkit.getConsoleSender();
        }

        if (name != null) {
            String[] split = name.split(":", 2);
            String id = (split.length > 1) ? split[1] : null;
            Plugin plugin = Bukkit.getPluginManager().getPlugin(split[0]);

            if (plugin == null) {
                output.sendMessage("Could not set generator for world '" + world + "': Plugin '" + split[0] + "' does not exist");
            } else if (!plugin.isEnabled()) {
                output.sendMessage("Could not set generator for world '" + world + "': Plugin '" + plugin.getDescription().getFullName() + "' is not enabled");
            } else {
                result = plugin.getDefaultWorldGenerator(world, id);
            }
        }

        return result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 试图使用指定的名称获取{@link BiomeProvider}。
     * <p>
     * 如果生物群系提供器找不到，则会返回null并会向指定的{@link CommandSender}输出一条信息来解释原因。
     * <p>
     * 名称的形式必须为"plugin:id"或"plugin"形式，请求的插件的"plugin"为一个插件的安全名称并且"id"是生物群系提供器的可选的唯一的标识符。
     * <p>原文：
     * Attempts to get the {@link BiomeProvider} with the given name.
     * <p>
     * If the biome provider is not found, null will be returned and a message
     * will be printed to the specified {@link CommandSender} explaining why.
     * <p>
     * The name must be in the "plugin:id" notation, or optionally just
     * "plugin", where "plugin" is the safe-name of a plugin and "id" is an
     * optional unique identifier for the biome provider you wish to request
     * from the plugin.
     *
     * @param world 将被使用的世界名
     * <p>原文：Name of the world this will be used for
     * @param name 检索的生物群系提供器的名字
     * <p>原文：Name of the biome provider to retrieve
     * @param output 错误发生时的输出处
     * <p>原文：Where to output if errors are present
     * @return 若存在则返回获取的生物群系提供器，否则返回null
     * <p>原文：Resulting biome provider, or null
     */
    @Nullable
    public static BiomeProvider getBiomeProviderForName(@NotNull String world, @Nullable String name, @Nullable CommandSender output) {
        Preconditions.checkArgument(world != null, "World name must be specified");
        BiomeProvider result = null;

        if (output == null) {
            output = Bukkit.getConsoleSender();
        }

        if (name != null) {
            String[] split = name.split(":", 2);
            String id = (split.length > 1) ? split[1] : null;
            Plugin plugin = Bukkit.getPluginManager().getPlugin(split[0]);

            if (plugin == null) {
                output.sendMessage("Could not set biome provider for world '" + world + "': Plugin '" + split[0] + "' does not exist");
            } else if (!plugin.isEnabled()) {
                output.sendMessage("Could not set set biome provider for world '" + world + "': Plugin '" + plugin.getDescription().getFullName() + "' is not enabled");
            } else {
                result = plugin.getDefaultBiomeProvider(world, id);
            }
        }

        return result;
    }

    // Paper start - keep spawn loaded tristate
    /**
     * Returns the current intent to keep the world loaded, @see {@link WorldCreator#keepSpawnLoaded(net.kyori.adventure.util.TriState)}
     *
     * @return the current tristate value
     * @deprecated completely unfunctional as the server no longer has always loaded spawn chunks.
     */
    @NotNull
    @Deprecated(forRemoval = true, since = "1.21.9")
    public net.kyori.adventure.util.TriState keepSpawnLoaded() {
        return net.kyori.adventure.util.TriState.FALSE;
    }

    /**
     * Controls if a world should be kept loaded or not, default (NOT_SET) will use the servers standard
     * configuration, otherwise, will act as an override towards this setting
     *
     * @param keepSpawnLoaded the new value
     * @return This object, for chaining
     * @deprecated completely unfunctional as the server no longer has always loaded spawn chunks.
     */
    @NotNull
    @Deprecated(forRemoval = true, since = "1.21.9")
    public WorldCreator keepSpawnLoaded(@NotNull net.kyori.adventure.util.TriState keepSpawnLoaded) {
        return this;
    }
    // Paper end - keep spawn loaded tristate
}
