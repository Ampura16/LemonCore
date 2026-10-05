package org.bukkit.inventory.meta;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一种实体可以穿戴且可以染色的盔甲.
 * <p>原文：
 * Represents armor that an entity can equip and can also be colored.
 */
public interface ColorableArmorMeta extends ArmorMeta, LeatherArmorMeta {

    @Override
    @NotNull
    ColorableArmorMeta clone();
}
