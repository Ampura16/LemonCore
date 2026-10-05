package io.papermc.paper.tag;

import java.util.Objects;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.entity.EntityType;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 该类中的所有标签均不可修改，尝试修改将抛出 {@link UnsupportedOperationException}。
 * <p>原文：
 * All tags in this class are unmodifiable, attempting to modify them will throw an
 * {@link UnsupportedOperationException}.
 */
public class EntityTags {

    private static NamespacedKey keyFor(String key) {
        return new NamespacedKey("paper", key + "_settag");
    }

    private static EntitySetTag replacedBy(Tag<EntityType> vanillaTag) {
        return replacedBy(vanillaTag, Objects.requireNonNull(vanillaTag).key().value());
    }

    @SuppressWarnings("unchecked")
    private static EntitySetTag replacedBy(Tag<EntityType> vanillaTag, String legacyKey) {
        Objects.requireNonNull(vanillaTag);
        return new EntitySetTag(keyFor(legacyKey)).add(vanillaTag).lock();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖亡灵生物
     * <p>原文：
     * Covers undead mobs
     *
     * @see <a href="https://minecraft.wiki/wiki/Mob#Undead_mobs">https://minecraft.wiki/wiki/Mob#Undead_mobs</a>
     * @deprecated 改用 {@link Tag#ENTITY_TYPES_UNDEAD}
     * <p>原文：in favour of {@link Tag#ENTITY_TYPES_UNDEAD}
     */
    @Deprecated(since = "1.21.8")
    public static final EntitySetTag UNDEADS = replacedBy(Tag.ENTITY_TYPES_UNDEAD, "undeads");

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有马
     * <p>原文：
     * Covers all horses
     */
    public static final EntitySetTag HORSES = new EntitySetTag(keyFor("horses"))
        .contains("HORSE")
        .ensureSize("HORSES", 3).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有矿车
     * <p>原文：
     * Covers all minecarts
     */
    public static final EntitySetTag MINECARTS = new EntitySetTag(keyFor("minecarts"))
        .contains("MINECART")
        .ensureSize("MINECARTS", 7).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖会分裂成更小生物的生物
     * <p>原文：
     * Covers mobs that split into smaller mobs
     */
    public static final EntitySetTag SPLITTING_MOBS = new EntitySetTag(keyFor("splitting_mobs"))
        .add(EntityType.SLIME, EntityType.MAGMA_CUBE).lock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 涵盖所有水生生物
     * <p>原文：
     * Covers all water based mobs
     *
     * @see <a href="https://minecraft.wiki/wiki/Mob#Aquatic_mobs">https://minecraft.wiki/wiki/Mob#Aquatic_mobs</a>
     * @deprecated 改用 {@link Tag#ENTITY_TYPES_AQUATIC}
     * <p>原文：in favour of {@link Tag#ENTITY_TYPES_AQUATIC}
     */
    @Deprecated(since = "1.21")
    public static final EntitySetTag WATER_BASED = replacedBy(Tag.ENTITY_TYPES_AQUATIC, "water_based");
}
