package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface PiercingWeapon {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回用于创建穿刺武器的新构建器。
     * <p>原文：
     * Returns a new builder for creating a Piercing Weapon.
     *
     * @return 构建器实例
     * <p>原文：a builder instance
     */
    static Builder piercingWeapon() {
        return ItemComponentTypesBridge.bridge().piercingWeapon();
    }

    boolean dealsKnockback();

    boolean dismounts();

    @Nullable Key sound();

    @Nullable Key hitSound();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link PiercingWeapon} 的构建器。
     * <p>原文：
     * Builder for {@link PiercingWeapon}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<PiercingWeapon> {

        @Contract(value = "_ -> this", mutates = "this")
        Builder dealsKnockback(boolean dealsKnockback);

        @Contract(value = "_ -> this", mutates = "this")
        Builder dismounts(boolean dismounts);

        @Contract(value = "_ -> this", mutates = "this")
        Builder sound(@Nullable Key sound);

        @Contract(value = "_ -> this", mutates = "this")
        Builder hitSound(@Nullable Key sound);
    }
}
