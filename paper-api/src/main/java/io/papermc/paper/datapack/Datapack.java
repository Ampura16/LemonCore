package io.papermc.paper.datapack;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 这是服务器上某个数据包的快照。数据包更新时它不会随之更新。
 * <p>原文：
 * This is a snapshot of a datapack on the server. It
 * won't be updated as datapacks are updated.
 */
@NullMarked
@ApiStatus.NonExtendable
public interface Datapack extends DiscoveredDatapack {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此数据包的启用状态。
     * <p>原文：
     * Gets the enabled state of this pack.
     *
     * @return 数据包当前是否已启用
     * <p>原文：whether the pack is currently enabled
     */
    boolean isEnabled();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 更改此数据包的启用状态。若有任何变化，将导致资源重载（{@code /minecraft:reload}）。
     * <p>原文：
     * Changes the enabled state of this pack. Will
     * cause a reload of resources ({@code /minecraft:reload}) if
     * any change happens.
     *
     * @param enabled true 表示启用，false 表示禁用
     * <p>原文：true to enable, false to disable
     * @apiNote 由于不希望在“快照”类型上提供 setter，此方法将来可能被弃用。
     * <p>原文：This method may be deprecated in the future as setters on a "snapshot" type are undesirable.
     */
    void setEnabled(boolean enabled);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 计算原版 Minecraft 用于显示此数据包的组件。包括 {@link #getSource()}、{@link #getDescription()}、{@link #getName()} 以及启用状态。
     * <p>原文：
     * Computes the component vanilla Minecraft uses
     * to display this datapack. Includes the {@link #getSource()},
     * {@link #getDescription()}, {@link #getName()}, and the enabled state.
     *
     * @return 新组件
     * <p>原文：a new component
     */
    @Contract(pure = true, value = "-> new")
    Component computeDisplayName();

    enum Compatibility {
        TOO_OLD,
        TOO_NEW,
        COMPATIBLE,
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 数据包在加载顺序中的位置。
     * <p>原文：
     * Position of the pack in the load order.
     */
    enum Position {
        TOP, BOTTOM
    }
}
