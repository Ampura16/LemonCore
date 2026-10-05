package io.papermc.paper.entity;

import org.bukkit.entity.Entity;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示可以被拴绳拴住的实体。
 * <p>原文：
 * Represents an entity that can be leashed.
 */
@NullMarked
public interface Leashable extends Entity {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回实体当前是否被拴住。
     * <p>原文：
     * Returns whether the entity is currently leashed.
     *
     * @return 实体是否被拴住
     * <p>原文：whether the entity is leashed
     */
    boolean isLeashed();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取当前领导此实体的实体。
     * <p>原文：
     * Gets the entity that is currently leading this entity.
     *
     * @return 牵着拴绳的实体
     * <p>原文：the entity holding the leash
     * @throws IllegalStateException 如果当前未被拴住
     * <p>原文：if not currently leashed
     */
    Entity getLeashHolder() throws IllegalStateException;

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此实体的拴绳由所提供的实体牵着。
     * <p>
     * 该方法对玩家无效。
     * <p>原文：
     * Sets the leash on this entity to be held by the supplied entity.
     * <p>
     * This method has no effect on players.
     *
     * @param holder 要将此实体拴到的实体，或 {@code null} 以解除拴绳
     * <p>原文：the entity to leash this entity to, or {@code null} to unleash
     * @return 操作是否成功
     * <p>原文：whether the operation was successful
     */
    boolean setLeashHolder(@Nullable Entity holder);
}
