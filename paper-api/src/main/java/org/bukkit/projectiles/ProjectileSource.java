package org.bukkit.projectiles;

import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个有效的抛射物发射源。
 * <p>原文：
 * Represents a valid source of a projectile.
 */
public interface ProjectileSource {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让ProjectileSource发射一个{@link Projectile}。
     * <p>原文：
     * Launches a {@link Projectile} from the ProjectileSource.
     * <p>
     * The family of launchProjectile methods only promise the ability to launch projectile types
     * that the {@link ProjectileSource} is capable of firing in vanilla.
     * Any other types of projectiles *may* be implemented but are not part of the method contract.
     *
     * @param <T> a projectile subclass
     * @param projectile 要让ProjectileSource发射的抛射物类型
     * <p>原文：class of the projectile to launch
     * @return 发射的抛射物实例对象
     * <p>原文：the launched projectile
     */
    @NotNull
    default <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> projectile) {
        return this.launchProjectile(projectile, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让ProjectileSource以一个初始速度向量发射{@link Projectile}。
     * <p>原文：
     * Launches a {@link Projectile} from the ProjectileSource with an
     * initial velocity.
     * <p>
     * The family of launchProjectile methods only promise the ability to launch projectile types
     * that the {@link ProjectileSource} is capable of firing in vanilla.
     * Any other types of projectiles *may* be implemented but are not part of the method contract.
     *
     * @param <T> a projectile subclass
     * @param projectile 要让ProjectileSource发射的抛射物类型
     * <p>原文：class of the projectile to launch
     * @param velocity 抛射物的速度向量
     * <p>原文：the velocity with which to launch
     * @return 发射的抛射物实例对象
     * <p>原文：the launched projectile
     */
    @NotNull
    default <T extends Projectile> T launchProjectile(@NotNull Class<? extends T> projectile, @Nullable Vector velocity) {
        return this.launchProjectile(projectile, velocity, null);
    }

    // Paper start - add consumer to launchProjectile
    /**
     * Launches a {@link Projectile} from the ProjectileSource with an
     * initial velocity, with the supplied function run before the
     * entity is added to the world.
     * <br>
     * Note that when the function is run, the entity will not be actually in
     * the world. Any operation involving such as teleporting the entity is undefined
     * until after this function returns.
     * <p>
     * The family of launchProjectile methods only promise the ability to launch projectile types
     * that the {@link ProjectileSource} is capable of firing in vanilla.
     * Any other types of projectiles *may* be implemented but are not part of the method contract.
     * @param <T> a projectile subclass
     * @param projectile class of the projectile to launch
     * @param velocity the velocity with which to launch
     * @param function the function to be run before the entity is spawned
     * @return the launched projectile
     */
    <T extends Projectile> @NotNull T launchProjectile(@NotNull Class<? extends T> projectile, @Nullable Vector velocity, java.util.function.@Nullable Consumer<? super T> function);
    // Paper end - add consumer to launchProjectile
}
