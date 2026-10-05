package org.bukkit.entity;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface AnimalTamer {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 这是指定的AnimalTamer的名称.
     * <p>原文：
     * This is the name of the specified AnimalTamer.
     *
     * @return 如果名称不能被引用，改名称可以引用被驯养的动物或为
     * <p>原文：The name to reference on tamed animals or null if a name cannot be obtained
     */
    @Nullable
    public String getName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 这是指定的AnimalTamer的UUID.
     * <p>原文：
     * This is the UUID of the specified AnimalTamer.
     *
     * @return AnimalTamer引用的UUID
     * <p>原文：The UUID to reference on tamed animals
     */
    @NotNull
    public UUID getUniqueId();
}
