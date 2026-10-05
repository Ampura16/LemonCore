package io.papermc.paper.world.damagesource;

import net.kyori.adventure.translation.Translatable;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示实体摔落起始位置的类型。
 * <p>原文：
 * Represents a type of location from which the entity fell.
 */
@NullMarked
@ApiStatus.Experimental
public sealed interface FallLocationType extends Translatable permits FallLocationTypeImpl {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取摔落位置 ID。
     * <p>原文：
     * Gets the fall location id.
     *
     * @return 摔落位置 ID
     * <p>原文：the fall location id
     */
    String id();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取从此位置摔落致死时死亡消息所用的翻译键
     * <p>原文：
     * Gets the translation key used for a fall death message
     * caused by falling from this location
     *
     * @return 翻译键
     * <p>原文：the translation key
     */
    @Override
    String translationKey();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 实体不在特殊摔落位置中。
     * <p>原文：
     * The entity was not within a special fall location.
     */
    FallLocationType GENERIC = new FallLocationTypeImpl("generic");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 实体位于梯子中。
     * <p>原文：
     * The entity was within the ladder.
     */
    FallLocationType LADDER = new FallLocationTypeImpl("ladder");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 实体位于藤蔓中。
     * <p>原文：
     * The entity was in vines.
     */
    FallLocationType VINES = new FallLocationTypeImpl("vines");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 实体位于垂泪藤中。
     * <p>原文：
     * The entity was in weeping wines.
     */
    FallLocationType WEEPING_VINES = new FallLocationTypeImpl("weeping_vines");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 实体位于缠怨藤中。
     * <p>原文：
     * The entity was in twisting vines.
     */
    FallLocationType TWISTING_VINES = new FallLocationTypeImpl("twisting_vines");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 实体位于脚手架中。
     * <p>原文：
     * The entity was in scaffolding.
     */
    FallLocationType SCAFFOLDING = new FallLocationTypeImpl("scaffolding");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 实体位于其他某种可攀爬方块中。
     * <p>原文：
     * The entity was within some other climbable block.
     */
    FallLocationType OTHER_CLIMBABLE = new FallLocationTypeImpl("other_climbable");
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 实体位于水中。
     * <p>原文：
     * The entity was in water.
     */
    FallLocationType WATER = new FallLocationTypeImpl("water");

}
