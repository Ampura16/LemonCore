package io.papermc.paper.entity;

import net.kyori.adventure.util.TriState;
import org.bukkit.entity.Entity;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示能够与空气和地面产生摩擦的 {@link Entity}。
 * <p>原文：
 * Represents an {@link Entity} that can experience friction with the air and ground.
 */
@NullMarked
public interface Frictional {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此实体的摩擦状态。设为 {@link TriState#TRUE} 时实体总会受到摩擦；设为 {@link TriState#FALSE} 时实体永远不会受到摩擦；设为 {@link TriState#NOT_SET} 时实体将回退到 Minecraft 的默认行为。
     * <p>原文：
     * Gets the friction state of this entity.
     * When set to {@link TriState#TRUE}, the entity will always experience friction.
     * When set to {@link TriState#FALSE}, the entity will never experience friction.
     * When set to {@link TriState#NOT_SET}, the entity will fall back to Minecraft's default behaviour.
     *
     * @return 实体的摩擦状态
     * <p>原文：the entity's friction state
     */
    TriState getFrictionState();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此实体的摩擦状态。设为 {@link TriState#TRUE} 时实体总会受到摩擦；设为 {@link TriState#FALSE} 时实体永远不会受到摩擦；设为 {@link TriState#NOT_SET} 时实体将回退到 Minecraft 的默认行为。
     * <p>
     * 请注意，更改此值对玩家不起作用。
     * <p>原文：
     * Sets the friction state of this entity.
     * When set to {@link TriState#TRUE}, the entity will always experience friction.
     * When set to {@link TriState#FALSE}, the entity will never experience friction.
     * When set to {@link TriState#NOT_SET}, the entity will fall back to Minecraft's default behaviour.
     * <p>
     * Please note that changing this value will do nothing for a player.
     *
     * @param state 要为实体设置的新摩擦状态
     * <p>原文：the new friction state to set for the entity
     */
    void setFrictionState(TriState state);

}
