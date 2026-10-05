package io.papermc.paper.datapack;

import java.util.Collection;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public interface DatapackManager {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 触发对可用和已选数据包的刷新。这可以发现新的数据包、移除旧的数据包，并更新现有数据包的元数据。其中某些更改只会在下一次 {@link org.bukkit.Server#reloadData()} 或 {@code /minecraft:reload} 之后生效。
     * <p>原文：
     * Triggers a refresh of the available and selected datapacks. This
     * can find new datapacks, remove old ones, and update the metadata for
     * existing datapacks. Some of these changes will only take effect
     * after the next {@link org.bukkit.Server#reloadData()} or {@code /minecraft:reload}.
     */
    void refreshPacks();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 按名称获取数据包。可能需要先调用 {@link #refreshPacks()} 以获取最新的数据包信息。
     * <p>原文：
     * Gets a datapack by name. May require calling {@link #refreshPacks()} before
     * to get the latest pack information.
     *
     * @param name 数据包的名称/ID
     * <p>原文：the name/id of the datapack
     * @return 数据包；如果未找到则为 null
     * <p>原文：the datapack, or null if not found
     */
    @Nullable Datapack getPack(String name);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取可用的数据包。可能需要先调用 {@link #refreshPacks()} 以获取最新的数据包信息。
     * <p>原文：
     * Gets the available datapacks. May require calling {@link #refreshPacks()} before
     * to get the latest pack information.
     *
     * @return 服务器已知的所有数据包
     * <p>原文：all the packs known to the server
     */
    @Unmodifiable Collection<Datapack> getPacks();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取已启用的数据包。可能需要先调用 {@link #refreshPacks()} 以获取最新的数据包信息。
     * <p>原文：
     * Gets the enabled datapacks. May require calling {@link #refreshPacks()} before
     * to get the latest pack information.
     *
     * @return 当前已启用的所有数据包
     * <p>原文：all the packs which are currently enabled
     */
    @Unmodifiable Collection<Datapack> getEnabledPacks();
}
