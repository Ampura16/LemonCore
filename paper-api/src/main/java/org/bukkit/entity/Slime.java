package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表史莱姆.
 * <p>原文：
 * Represents a Slime.
 */
public interface Slime extends Mob, Enemy {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 史莱姆的大小
     * <p>原文：The size of the slime
     */
    public int getSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * Setting the size of the slime (regardless of previous size)
     * will set the following attributes:
     * <ul>
     *     <li>{@link org.bukkit.attribute.Attribute#MAX_HEALTH}</li>
     *     <li>{@link org.bukkit.attribute.Attribute#MOVEMENT_SPEED}</li>
     *     <li>{@link org.bukkit.attribute.Attribute#ATTACK_DAMAGE}</li>
     * </ul>
     * to their per-size defaults and heal the
     * slime to its max health (assuming it's alive).
     *
     * @param sz 史莱姆新的大小.
     * <p>原文：The new size of the slime.
     */
    public void setSize(int sz);

    /**
     * Get whether this slime can randomly wander/jump around on its own
     *
     * @return true if can wander
     */
    public boolean canWander();

    /**
     * Set whether this slime can randomly wander/jump around on its own
     *
     * @param canWander true if can wander
     */
    public void setWander(boolean canWander);
}
