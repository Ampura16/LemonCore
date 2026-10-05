package io.papermc.paper.entity;

import net.kyori.adventure.sound.Sound;
import org.bukkit.entity.Entity;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示可以被剪的实体。
 * <p>原文：
 * Represents an entity that can be sheared.
 */
@NullMarked
public interface Shearable extends Entity {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 强制剪下该实体，然后播放如同被玩家剪下时的效果。即使 {@link Shearable#readyToBeSheared()} 为 false，也会剪下该实体。
     * <p>
     * 某些剪切行为可能导致实体被另一个实体取代，从而不再有效。
     * <p>原文：
     * Forces the entity to be sheared and then play the effect as if it were sheared by a player.
     * This will cause the entity to be sheared, even if {@link Shearable#readyToBeSheared()} is false.
     * <p>
     * Some shearing behavior may cause the entity to no longer be valid
     * due to it being replaced by a different entity.
     */
    default void shear() {
        this.shear(Sound.Source.PLAYER);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 强制剪下该实体，然后播放如同被所提供来源剪下时的效果。即使 {@link Shearable#readyToBeSheared()} 为 false，也会剪下该实体。
     * <p>
     * 某些剪切行为可能导致实体被另一个实体取代，从而不再有效。
     * <p>
     * 这会模拟实际剪切的行为，可能导致对哞菇调用 EntityTransformEvent、对羊调用 EntityDropItemEvent 等事件。
     * <p>原文：
     * Forces the entity to be sheared and then play the effect as if it were sheared by the provided source.
     * This will cause the entity to be sheared, even if {@link Shearable#readyToBeSheared()} is false.
     * <p>
     * Some shearing behavior may cause the entity to no longer be valid
     * due to it being replaced by a different entity.
     * <p>
     * This simulates the behavior of an actual shearing, which may cause events like EntityTransformEvent to be called
     * for mooshrooms, and EntityDropItemEvent to be called for sheep.
     *
     * @param source 播放音效所用的声音来源
     * <p>原文：Sound source to play any sound effects on
     */
    void shear(Sound.Source source);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取实体能否自然地被剪刀剪下。
     * <p>原文：
     * Gets if the entity would be able to be sheared or not naturally using shears.
     *
     * @return 实体能否被剪
     * <p>原文：if the entity can be sheared
     */
    boolean readyToBeSheared();
}
