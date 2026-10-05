/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 用于提供在每种Minecraft概念上的插件指定数据的数据层.
 * <p>原文：
 * Classes dedicated to providing a layer of plugin specified data on various
 * Minecraft concepts.
 *
 * @deprecated This system is extremely misleading and does not cleanup values for metadatable entities that have been
 * removed. It is recommended that when wanting persistent metadata, you use {@link org.bukkit.persistence.PersistentDataContainer}.
 * <p>
 * If you want temporary values on an entity, use the entity lifecycle events and a {@link java.util.Map} of your own. (See {@link com.destroystokyo.paper.event.entity.EntityAddToWorldEvent} and {@link com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent})
 */
@Deprecated
package org.bukkit.metadata;

