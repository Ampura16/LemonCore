package org.bukkit.entity;

import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表复杂生物 --- 由多种较小实体部分组成 (目前只有末影龙属于此行列).
 * <p>原文：
 * Represents a complex living entity - one that is made up of various smaller
 * parts
 */
public interface ComplexLivingEntity extends LivingEntity {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取组成此复杂生物的实体部件.
     * <p>原文：
     * Gets a list of parts that belong to this complex entity
     *
     * @return 组成此复杂生物的实体部件
     * <p>原文：List of parts
     */
    @NotNull
    public Set<ComplexEntityPart> getParts();
}
