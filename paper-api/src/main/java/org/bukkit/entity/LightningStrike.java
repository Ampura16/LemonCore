package org.bukkit.entity;

import org.bukkit.GameEvent;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表雷击的实例，可能会或者不会造成伤害。
 * <p>原文：
 * Represents an instance of a lightning strike. May or may not do damage.
 */
public interface LightningStrike extends Entity {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回雷击是否是没有伤害的效果。
     * <p>原文：
     * Returns whether the strike is an effect that does no damage.
     *
     * @return 雷击是否为效果
     * <p>原文：whether the strike is an effect
     */
    public boolean isEffect();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取雷击被移除前将会发生的闪烁次数。默认情况下此值在 1 到 3 之间。
     * <p>原文：
     * Get the amount of flashes that will occur before the lightning is
     * removed. By default this value is between 1 and 3.
     *
     * @return 闪烁次数
     * <p>原文：the flashes
     * @deprecated use {@link #getFlashCount()}
     */
    @Deprecated // Paper
    public int getFlashes();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置雷击被移除前将会发生的闪烁次数。当此雷击的生命值降至 0 以下时将会发生一次闪烁。
     * <p>原文：
     * Set the amount of flashes that will occur before the lightning is
     * removed. One flash will occur after this lightning strike's life
     * has reduced below 0.
     *
     * @param flashes 闪烁次数
     * <p>原文：the flashes
     * @deprecated use {@link #setFlashCount(int)}
     */
    @Deprecated // Paper
    public void setFlashes(int flashes);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此雷击对其命中实体造成伤害的持续刻数。
     * <p>
     * 当生命刻数为负时，有随机几率触发另一次闪烁并将生命刻数重置为 1。
     * <p>原文：
     * Get the amount of ticks this lightning strike will inflict damage
     * upon its hit entities.
     * <p>
     * When life ticks are negative, there is a random chance that another
     * flash will be initiated and life ticks reset to 1.
     *
     * @return 生命刻数
     * <p>原文：the life ticks
     */
    public int getLifeTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此雷击对其命中实体造成伤害的持续刻数。
     * <p>
     * 当生命刻数为负时，有随机几率触发另一次闪烁并将生命刻数重置为 1。
     * 此外，如果生命刻数设置为 2（雷击生成时的默认值），将会触发一系列事件：
     * <ul>
     *   <li>播放冲击音效
     *   <li>生成火焰（取决于难度）
     *   <li>激活避雷针（如果被击中）
     *   <li>剥离铜锈（如果被击中）
     *   <li>触发 {@link GameEvent#LIGHTNING_STRIKE} 游戏事件
     * </ul>
     * <p>原文：
     * Get the amount of ticks this lightning strike will inflict damage
     * upon its hit entities.
     * <p>
     * When life ticks are negative, there is a random chance that another
     * flash will be initiated and life ticks reset to 1. Additionally, if
     * life ticks are set to 2 (the default value when a lightning strike
     * has been spawned), a list of events will occur:
     * <ul>
     *   <li>Impact sound effects will be played
     *   <li>Fire will be spawned (dependent on difficulty)
     *   <li>Lightning rods will be powered (if hit)
     *   <li>Copper will be stripped (if hit)
     *   <li>{@link GameEvent#LIGHTNING_STRIKE} will be dispatched
     * </ul>
     *
     * @param ticks 生命刻数
     * <p>原文：the life ticks
     */
    public void setLifeTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取导致此雷击的 {@link Player}。当玩家在暴风雨中向实体投掷带有
     * {@link Enchantment#CHANNELING 引雷} 附魔的三叉戟时会自然触发此情况。
     * <p>原文：
     * Get the {@link Player} that caused this lightning to strike. This
     * will occur naturally if a trident enchanted with
     * {@link Enchantment#CHANNELING Channeling} were thrown at an entity
     * during a storm.
     *
     * @return 导致雷击的玩家
     * <p>原文：the player
     */
    @Nullable
    public Player getCausingPlayer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置导致此雷击的 {@link Player}。
     * <p>原文：
     * Set the {@link Player} that caused this lightning to strike.
     *
     * @param player 导致雷击的玩家
     * <p>原文：the player
     */
    public void setCausingPlayer(@Nullable Player player);

    // Spigot start
    /**
     * @deprecated Unsupported api
     */
    @Deprecated(forRemoval = true)
    public class Spigot extends Entity.Spigot {

        /**
         * Returns whether the strike is silent.
         *
         * @return whether the strike is silent.
         * @deprecated sound is now client side and cannot be removed
         */
        @Deprecated(since = "1.20.4", forRemoval = true)
        public boolean isSilent() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    /**
     * @deprecated Unsupported api
     */
    @NotNull
    @Override
    @Deprecated(forRemoval = true)
    Spigot spigot();
    // Spigot end

    // Paper start
    /**
     * Returns the amount of flash iterations that will be done before the lightning dies.
     *
     * @see #getLifeTicks() for how long the current flash will last
     * @return amount of flashes that will be shown before the lightning dies
     */
    int getFlashCount();

    /**
     * Sets the amount of life iterations that will be done before the lightning dies.
     * Default number of flashes on creation is between 1-3.
     *
     * @param flashes amount of iterations that will be done before the lightning dies, must to be a positive number
     */
    void setFlashCount(int flashes);

    /**
     * Returns the potential entity that caused this lightning strike to spawn in the world.
     * <p>
     * As of implementing this method, only {@link Player}s are capable of causing a lightning strike, however as this
     * might change in future minecraft releases, this method does not guarantee a player as the cause of a lightning.
     * Consumers of this method should hence validate whether or not the entity is a player if they want to use player
     * specific methods through an {@code instanceOf} check.
     * </p>
     * <p>
     * A player is, as of implementing this method, responsible for a lightning, and will hence be returned here as
     * a cause, if they channeled a {@link Trident} to summon it or were explicitly defined as the cause of this
     * lightning through {@link #setCausingPlayer(Player)}.
     * </p>
     *
     * @return the entity that caused this lightning or null if the lightning was not caused by an entity (e.g. normal
     * weather)
     */
    @org.jetbrains.annotations.Nullable
    Entity getCausingEntity();
    // Paper end
}
