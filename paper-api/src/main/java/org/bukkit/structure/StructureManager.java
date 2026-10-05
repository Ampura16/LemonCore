package org.bukkit.structure;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StructureManager {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前已注册的结构。
     * <p>原文：
     * Gets the currently registered structures.
     * <p>
     * These are the currently loaded structures that the StructureManager is
     * aware of. When a structure block refers to a structure, these structures
     * are checked first. If the specified structure is not found among the
     * currently registered structures, the StructureManager may dynamically
     * read the structure from the primary world folder, DataPacks, or the
     * server's own resources. Structures can be registered via {@link
     * #registerStructure(NamespacedKey, Structure)}
     *
     * @return 当前已注册结构的不可修改的浅拷贝。
     * <p>原文：an unmodifiable shallow copy of the currently registered
     * structures
     */
    @NotNull
    Map<NamespacedKey, Structure> getStructures();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取已注册的结构。
     * <p>原文：
     * Gets a registered Structure.
     *
     * @param structureKey 要获取结构的键。
     * <p>原文：The key for which to get the structure
     * @return 属于该键的结构，如果没有注册该键的结构则返回 <code>null</code>。
     * <p>原文：The structure that belongs to the structureKey or
     * <code>null</code> if there is none registered for that key.
     */
    @Nullable
    Structure getStructure(@NotNull NamespacedKey structureKey);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 注册给定的结构。参见 {@link #getStructures()}。
     * <p>原文：
     * Registers the given structure. See {@link #getStructures()}.
     *
     * @param structureKey 要注册结构的键。
     * <p>原文：The key for which to register the structure
     * @param structure 要注册的结构。
     * <p>原文：The structure to register
     * @return 指定键的结构，如果找不到结构则返回 <code>null</code>。
     * <p>原文：The structure for the specified key, or <code>null</code> if the
     * structure could not be found.
     */
    @Nullable
    Structure registerStructure(@NotNull NamespacedKey structureKey, @NotNull Structure structure);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 注销结构。注销指定的结构。如果结构仍然存在于主世界文件夹、数据包中，
     * 或是服务器自身资源的一部分，当插件或服务器本身请求时，它可能会被重新加载和注册。
     * <p>原文：
     * Unregisters a structure. Unregisters the specified structure. If the
     * structure still exists in the primary world folder, a DataPack, or is
     * part of the server's own resources, it may be loaded and registered again
     * when it is requested by a plugin or the server itself.
     *
     * @param structureKey 要注销结构的键。
     * <p>原文：The key for which to save the structure for
     * @return 该键注册的结构，如果没有则返回 <code>null</code>。
     * <p>原文：The structure that was registered for that key or
     * <code>null</code> if there was none
     */
    @Nullable
    Structure unregisterStructure(@NotNull NamespacedKey structureKey);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定键加载结构，并可选择 {@link #registerStructure(NamespacedKey, Structure) 注册} 它。
     * <p>原文：
     * Loads a structure for the specified key and optionally {@link
     * #registerStructure(NamespacedKey, Structure) registers} it.
     * <p>
     * This will first check the already loaded {@link #getStructures()
     * registered structures}, and otherwise load the structure from the primary
     * world folder, DataPacks, and the server's own resources (in this order).
     * <p>
     * When loading the structure from the primary world folder, the given key
     * is translated to a file as specified by
     * {@link #getStructureFile(NamespacedKey)}.
     *
     * @param structureKey 要加载结构的键。
     * <p>原文：The key for which to load the structure
     * @param register 是否注册加载的结构。
     * <p>原文：<code>true</code> to register the loaded structure.
     * @return 结构，如果未找到指定键的结构则返回 <code>null</code>。
     * <p>原文：The structure, or <code>null</code> if no structure was found for
     * the specified key
     */
    @Nullable
    Structure loadStructure(@NotNull NamespacedKey structureKey, boolean register);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 加载指定键的结构并自动注册它。参见 {@link #loadStructure(NamespacedKey, boolean)}。
     * <p>原文：
     * Loads the structure for the specified key and automatically registers it.
     * See {@link #loadStructure(NamespacedKey, boolean)}.
     *
     * @param structureKey 要加载结构的键。
     * <p>原文：The key for which to load the structure
     * @return 指定键的结构，如果找不到结构则返回 <code>null</code>。
     * <p>原文：The structure for the specified key, or <code>null</code> if the
     * structure could not be found.
     */
    @Nullable
    Structure loadStructure(@NotNull NamespacedKey structureKey);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将当前 {@link #getStructures() 已注册的结构} 保存到主世界文件夹，
     * 按照 {#getStructureFile(NamespacedKey} 指定的方式。
     * <p>原文：
     * Saves the currently {@link #getStructures() registered structure} for the
     * specified {@link NamespacedKey key} to the primary world folder as
     * specified by {@link #getStructureFile(NamespacedKey)}.
     *
     * @param structureKey 要保存结构的键。
     * <p>原文：The key for which to save the structure for
     */
    void saveStructure(@NotNull NamespacedKey structureKey);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将具有给定键的结构保存到主世界文件夹。
     * <p>原文：
     * Saves a structure with a given key to the primary world folder.
     *
     * @param structureKey 要保存结构的键。
     * <p>原文：The key for which to save the structure for
     * @param structure 要为此键保存的结构。
     * <p>原文：The structure to save for this structureKey
     */
    void saveStructure(@NotNull NamespacedKey structureKey, @NotNull Structure structure) throws IOException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 注销指定结构并从主世界文件夹中删除其 {@link #getStructureFile(NamespacedKey) 结构文件}。
     * 注意：此方法不能用于删除原版 Minecraft 结构或数据包中的结构。但注销这些结构是可以的。
     * <p>原文：
     * Unregisters the specified structure and deletes its {@link
     * #getStructureFile(NamespacedKey) structure file} from the primary world
     * folder. Note that this method cannot be used to delete vanilla Minecraft
     * structures, or structures from DataPacks. Unregistering these structures
     * will however work fine.
     *
     * @param structureKey 要删除的结构的键。
     * <p>原文：The key of the structure to remove
     * @throws IOException 如果文件因某种原因无法删除。
     * <p>原文：If the file could not be removed for some reason.
     */
    void deleteStructure(@NotNull NamespacedKey structureKey) throws IOException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从主世界文件夹中删除指定结构的 {@link #getStructureFile(NamespacedKey) 结构文件}。
     * 注意：此方法不能用于删除原版 Minecraft 结构或数据包中的结构。但注销这些结构是可以的。
     * <p>原文：
     * Deletes the {@link #getStructureFile(NamespacedKey) structure file} for
     * the specified structure from the primary world folder. Note that this
     * method cannot be used to delete vanilla Minecraft structures, or
     * structures from DataPacks. Unregistering these structures will however
     * work fine.
     *
     * @param structureKey 要删除的结构的键。
     * <p>原文：The key of the structure to remove
     * @param unregister 是否同时注销当前已加载的指定结构。
     * <p>原文：Whether to also unregister the specified structure if
     * it is currently loaded.
     * @throws IOException 如果文件因某种原因无法删除。
     * <p>原文：If the file could not be removed for some reason.
     */
    void deleteStructure(@NotNull NamespacedKey structureKey, boolean unregister) throws IOException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据 NamespacedKey 获取结构文件在主世界目录中的位置，使用格式
     * world/generated/{NAMESPACE}/structures/{KEY}.nbt。此方法总是返回一个文件，
     * 即使当前不存在。
     * <p>原文：
     * Gets the location where a structure file would exist in the primary world
     * directory based on the NamespacedKey using the format
     * world/generated/{NAMESPACE}/structures/{KEY}.nbt. This method will always
     * return a file, even if none exists at the moment.
     *
     * @param structureKey 用于构建文件路径的键。
     * <p>原文：The key to build the filepath from.
     * @return 该键对应的文件位置。
     * <p>原文：The location where a file with this key would be.
     */
    @NotNull
    File getStructureFile(@NotNull NamespacedKey structureKey);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从磁盘读取结构。
     * <p>原文：
     * Reads a Structure from disk.
     *
     * @param file 结构的文件。
     * <p>原文：The file of the structure
     * @return 读取的结构。
     * <p>原文：The read structure
     * @throws IOException 当给定文件无法读取时。
     * <p>原文：when the given file can not be read from
     */
    @NotNull
    Structure loadStructure(@NotNull File file) throws IOException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从流中读取结构。
     * <p>原文：
     * Reads a Structure from a stream.
     *
     * @param inputStream 结构的输入流。
     * <p>原文：The file of the structure
     * @return 读取的结构。
     * <p>原文：The read Structure
     */
    @NotNull
    Structure loadStructure(@NotNull InputStream inputStream) throws IOException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将结构保存到文件。如果文件已存在，将被覆盖.
     * <p>原文：
     * Save a structure to a file. This will overwrite a file if it already
     * exists.
     *
     * @param file 要保存到的目标文件.
     * <p>原文：the target to save to.
     * @param structure 要保存的结构.
     * <p>原文：the Structure to save.
     * @throws IOException 当给定文件无法写入时.
     * <p>原文：when the given file can not be written to.
     */
    void saveStructure(@NotNull File file, @NotNull Structure structure) throws IOException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将结构保存到流.
     * <p>原文：
     * Save a structure to a stream.
     *
     * @param outputStream 要写入的流.
     * <p>原文：the stream to write to.
     * @param structure 要保存的结构.
     * <p>原文：the Structure to save.
     * @throws IOException 当给定文件无法写入时.
     * <p>原文：when the given file can not be written to.
     */
    void saveStructure(@NotNull OutputStream outputStream, @NotNull Structure structure) throws IOException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个新的空结构.
     * <p>原文：
     * Creates a new empty structure.
     *
     * @return 一个空结构.
     * <p>原文：an empty structure.
     */
    @NotNull
    Structure createStructure();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建此结构的副本.
     * <p>原文：
     * Creates a copy of this structure.
     *
     * @param structure 要复制的结构.
     * <p>原文：The structure to copy
     * @return 结构的副本.
     * <p>原文：a copy of the structure
     */
    @NotNull
    Structure copy(@NotNull Structure structure);
}
