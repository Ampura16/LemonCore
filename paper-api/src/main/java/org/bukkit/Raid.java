package org.bukkit;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Raider;
import org.bukkit.persistence.PersistentDataHolder;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表袭击事件 (<a href="https://zh.minecraft.wiki/w/%E8%A2%AD%E5%87%BB" target="_blank">Minecraft Wiki - 袭击</a>).
 * <p>原文：
 * Represents a raid event.
 */
@NullMarked
public interface Raid extends PersistentDataHolder {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断袭击是否已开始.
     * <p>原文：
     * Get whether this raid started.
     *
     * @return 袭击是否已开始
     * <p>原文：whether raid is started
     */
    boolean isStarted();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取袭击的已持续时长.
     * <p>原文：
     * Gets the amount of ticks this raid has existed.
     *
     * @return 袭击已持续的时长, 以游戏刻为单位
     * <p>原文：active ticks
     */
    long getActiveTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此次袭击中不祥之兆状态的等级.
     * <p>原文：
     * Gets the Bad Omen level of this raid.
     *
     * @return 不祥之兆状态等级 (范围为0-5)
     * <p>原文：Bad Omen level (between 0 and 5)
     */
    int getBadOmenLevel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此次袭击中不祥之兆状态的等级.
     * <br>
     * 如果此等级大于1, 将会有额外的与最终波强度一致的袭击波.
     * <p>原文：
     * Sets the Bad Omen level.
     * <br>
     * If the level is higher than 1, there will be an additional wave that as
     * strong as the final wave.
     *
     * @param badOmenLevel 新的不祥之兆状态等级 (范围为0-5)
     * <p>原文：new Bad Omen level (from 0-5)
     * @throws IllegalArgumentException 等级数值不在有效范围之内
     * <p>原文：if invalid Bad Omen level
     */
    void setBadOmenLevel(int badOmenLevel);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取袭击的中心位置.
     * <p>原文：
     * Gets the center location where the raid occurs.
     *
     * @return 袭击中心位置
     * <p>原文：location
     */
    Location getLocation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此袭击的状态.
     * <br>
     * 请不要使用本方法来检查袭击是否已开始, 应使用{@link #isStarted()}.
     * <p>原文：
     * Gets the current status of the raid.
     * <br>
     * Do not use this method to check if the raid has been started, call
     * {@link #isStarted()} instead.
     *
     * @return 袭击状态
     * <p>原文：Raids status
     */
    RaidStatus getStatus();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取袭击中已生成的怪物的波数.
     * <p>原文：
     * Gets the number of raider groups which have spawned.
     *
     * @return 已生成的怪物的波数
     * <p>原文：total spawned groups
     */
    int getSpawnedGroups();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取袭击中生成的怪物的总波数.
     * <br>
     * 该方法返回的结果也包括额外的袭击波数 (若存在).
     * <br>
     * 译注:不祥之兆的等级会影响是否有额外的袭击波数 (大于1会有额外的一波或数波袭击).
     * <p>原文：
     * Gets the number of raider groups which would spawn.
     * <br>
     * This also includes the group which spawns in the additional wave (if
     * present).
     *
     * @return 袭击总波数 (含额外袭击波数)
     * <p>原文：total groups
     */
    int getTotalGroups();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取袭击的总波数 (排除额外的袭击波数).
     * <p>原文：
     * Gets the number of waves in this raid (exclude the additional wave).
     *
     * @return 袭击总波数 (不含额外袭击波数)
     * <p>原文：number of waves
     */
    int getTotalWaves();

    /**
     * Sets the number of waves in this raid.
     *
     * @param totalWaves number of waves
     * @throws IllegalArgumentException if totalWaves is negative or zero
     * @throws IllegalArgumentException if totalWaves is larger than 7, which is the most waves a vanilla raid can have.
     * @throws IllegalArgumentException if the totalWaves is less than {@link #getSpawnedGroups()}
     */
    void setTotalWaves(int totalWaves);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有袭击者的最大生命值总和.
     * <p>原文：
     * Gets the sum of all raider's health.
     *
     * @return 袭击者最大生命值总和
     * <p>原文：total raiders health
     */
    float getTotalHealth();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本次袭击中获胜的英雄(玩家)们的UUID.
     * <p>原文：
     * Get the UUID of all heroes in this raid.
     *
     * @return 获胜的英雄(玩家)们的UUID
     * <p>原文：a set of unique ids
     */
    Set<UUID> getHeroes();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前一波袭击中所有存活的{@link Raider 袭击者}.
     * <p>原文：
     * Gets all remaining {@link Raider} in the present wave.
     *
     * @return 所有存活的{@link Raider 袭击者}的列表
     * <p>原文：a list of current raiders
     */
    List<Raider> getRaiders();

    /**
     * Gets the id of this raid.
     *
     * @return the raid id
     * @deprecated Raid identifiers are magic internal values and may or may not be present.
     * -1 is returned for raids without an assigned id.
     */
    @Deprecated(forRemoval = true, since = "1.21.5")
    int getId();

    /**
     * Get the boss bar to be displayed for this raid.
     *
     * @return the boss bar
     */
    BossBar getBossBar();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表{@link Raid 袭击}的状态.
     * <p>原文：
     * Represents the status of a {@link Raid}.
     */
    enum RaidStatus {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 袭击正在进行中.
         * <p>原文：
         * The raid is in progress.
         */
        ONGOING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 英雄(玩家们)击败了所有的袭击者.
         * <p>原文：
         * The raid was beaten by heroes.
         */
        VICTORY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 村庄已被摧毁 (即所有村民被杀死).
         * <p>原文：
         * The village has fallen (i.e. all villagers died).
         */
        LOSS,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 袭击被终止.
         * <p>原文：
         * The raid was terminated.
         */
        STOPPED;
    }
}
