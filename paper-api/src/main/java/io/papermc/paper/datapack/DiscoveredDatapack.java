package io.papermc.paper.datapack;

import java.util.Set;
import net.kyori.adventure.text.Component;
import org.bukkit.FeatureFlag;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示服务器通过搜索可用来源找到的数据包的快照。它可能已启用也可能未启用，且不保证可用。数据包更新时该对象不会随之更新。
 * <p>原文：
 * Represents a snapshot of a datapack that the server has found by
 * searching available sources. It may or may not be enabled and isn't
 * guaranteed to be available. This object won't be
 * updated as datapacks are updated.
 * @see DatapackRegistrar
 */
@NullMarked
@ApiStatus.NonExtendable
public interface DiscoveredDatapack {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此数据包的名称/ID。
     * <p>原文：
     * Gets the name/id of this datapack.
     *
     * @return 数据包名称
     * <p>原文：the name of the pack
     */
    @Contract(pure = true)
    String getName();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此数据包的标题组件。
     * <p>原文：
     * Gets the title component of this datapack.
     *
     * @return 标题
     * <p>原文：the title
     */
    Component getTitle();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此数据包的描述组件。
     * <p>原文：
     * Gets the description component of this datapack.
     *
     * @return 描述
     * <p>原文：the description
     */
    Component getDescription();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此数据包是否为必需。
     * <p>
     * “必需”的数据包在服务器启动时总会被启用，即使之前被禁用过。
     * <p>原文：
     * Gets if this datapack is required.
     * <p>
     * A "required" datapack will always be enabled on server startup, even if previously disabled.
     *
     * @return 如果数据包是必需的则为 true
     * <p>原文：true if the pack is required
     */
    boolean isRequired();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此数据包的兼容性状态。
     * <p>原文：
     * Gets the compatibility status of this pack.
     *
     * @return 数据包的兼容性
     * <p>原文：the compatibility of the pack
     */
    Datapack.Compatibility getCompatibility();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此数据包所需的特性集合。
     * <p>原文：
     * Gets the set of required features for this datapack.
     *
     * @return 所需特性集合
     * <p>原文：the set of required features
     */
    @Unmodifiable
    Set<FeatureFlag> getRequiredFeatures();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此数据包的来源。
     * <p>原文：
     * Gets the source for this datapack.
     *
     * @return 数据包来源
     * <p>原文：the pack source
     */
    DatapackSource getSource();
}
