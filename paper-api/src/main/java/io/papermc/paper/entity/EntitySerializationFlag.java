package io.papermc.paper.entity;

import org.bukkit.UnsafeValues;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示实体序列化的标志。
 * <p>原文：
 * Represents flags for entity serialization.
 *
 * @see UnsafeValues#serializeEntity(Entity, EntitySerializationFlag... serializationFlags)
 * @since 1.21.4
 */
public enum EntitySerializationFlag {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 序列化通常不会被序列化的实体（例如已死亡、已消失、非持久化的实体等）。
     * <p>原文：
     * Serialize entities that wouldn't be serialized normally
     * (e.g. dead, despawned, non-persistent, etc.).
     *
     * @see Entity#isValid()
     * @see Entity#isPersistent()
     */
    FORCE,
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 序列化闪电、钓鱼浮漂等其他不可保存的实体。<br>注意：玩家需要单独的标志：{@link #PLAYER}。
     * <p>原文：
     * Serialize misc non-saveable entities like lighting bolts, fishing bobbers, etc.
     * <br>Note: players require a separate flag: {@link #PLAYER}.
     */
    MISC,
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在序列化数据中包含乘客。
     * <p>原文：
     * Include passengers in the serialized data.
     */
    PASSENGERS,
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 允许序列化 {@link Player}。
     * <p>
     * 注意：反序列化玩家数据总是会失败。
     * <p>原文：
     * Allow serializing {@link Player}s.
     * <p>Note: deserializing player data will always fail.
     */
    PLAYER

}
