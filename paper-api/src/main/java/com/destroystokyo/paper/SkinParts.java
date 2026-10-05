package com.destroystokyo.paper;

import io.papermc.paper.InternalAPIBridge;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示玩家皮肤中可以开启或关闭的各个部分。
 * <p>原文：
 * Represents the different parts of a player's skin that can be toggled on or off.
 */
@NullMarked
public interface SkinParts {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建启用所有部分的 {@link SkinParts} 新实例。
     * <p>原文：
     * Creates a new instance of {@link SkinParts} with all parts enabled.
     *
     * @return 新的 {@link SkinParts} 实例
     * <p>原文：a new {@link SkinParts} instance
     */
    @Contract(value = "-> new", pure = true)
    static SkinParts.Mutable allParts() {
        return InternalAPIBridge.get().allSkinParts();
    }

    boolean hasCapeEnabled();

    boolean hasJacketEnabled();

    boolean hasLeftSleeveEnabled();

    boolean hasRightSleeveEnabled();

    boolean hasLeftPantsEnabled();

    boolean hasRightPantsEnabled();

    boolean hasHatsEnabled();

    int getRaw();

    Mutable mutableCopy();

    interface Mutable extends SkinParts {

        void setCapeEnabled(boolean enabled);

        void setJacketEnabled(boolean enabled);

        void setLeftSleeveEnabled(boolean enabled);

        void setRightSleeveEnabled(boolean enabled);

        void setLeftPantsEnabled(boolean enabled);

        void setRightPantsEnabled(boolean enabled);

        void setHatsEnabled(boolean enabled);

        SkinParts immutableCopy();
    }
}
