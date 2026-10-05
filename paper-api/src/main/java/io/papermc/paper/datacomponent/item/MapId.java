package io.papermc.paper.datacomponent.item;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 引用持有已填充地图内容和标记的共享地图状态。
 * <p>原文：
 * References the shared map state holding map contents and markers for a Filled Map.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#MAP_ID
 */
@NullMarked
@ApiStatus.NonExtendable
@ApiStatus.Experimental
public interface MapId {

    @Contract(value = "_ -> new", pure = true)
    static MapId mapId(final int id) {
        return ItemComponentTypesBridge.bridge().mapId(id);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 地图 ID。
     * <p>原文：
     * The map id.
     *
     * @return ID
     * <p>原文：id
     */
    @Contract(pure = true)
    int id();
}
