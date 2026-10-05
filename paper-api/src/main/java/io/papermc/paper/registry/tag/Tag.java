package io.papermc.paper.registry.tag;

import io.papermc.paper.registry.set.RegistryKeySet;
import org.bukkit.Keyed;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 通过数据包标签系统创建的具名 {@link RegistryKeySet}。
 * <p>原文：
 * A named {@link RegistryKeySet} which are created
 * via the datapack tag system.
 *
 * @param <T>
 * @see org.bukkit.Tag
 * @see org.bukkit.Registry#getTag(TagKey)
 */
@ApiStatus.Experimental
@NullMarked
public interface Tag<T extends Keyed> extends RegistryKeySet<T> { // TODO remove Keyed

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此具名集合的标识符。
     * <p>原文：
     * Get the identifier for this named set.
     *
     * @return 标签键标识符
     * <p>原文：the tag key identifier
     */
    TagKey<T> tagKey();
}
