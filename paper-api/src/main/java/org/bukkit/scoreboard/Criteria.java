package org.bukkit.scoreboard;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.Statistic.Type;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示记分板条件，可以是自定义的或 Minecraft 服务器内置的，用于跟踪和手动或自动更改记分板上的分数。
 * <p>
 * 虽然此类概述了标准条件的常量，请参阅 {@link #statistic(Statistic)}（及其重载）以创建基于统计数据的条件实例。
 * <p>原文：
 * Represents a scoreboard criteria, either custom or built-in to the Minecraft server, used to
 * keep track of and manually or automatically change scores on a scoreboard.
 * <p>
 * While this class outlines constants for standard criteria, see {@link #statistic(Statistic)}
 * (and its overloads) to create instances for statistically-backed criteria.
 */
public interface Criteria {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 虚拟条件。不会被服务器更改。
     * <p>原文：
     * The dummy criteria. Not changed by the server.
     */
    public static final Criteria DUMMY = Bukkit.getScoreboardCriteria("dummy");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 触发条件。当玩家对目标运行 /trigger 命令时更改。
     * <p>原文：
     * The trigger criteria. Changed when a player runs the /trigger command for an objective.
     */
    public static final Criteria TRIGGER = Bukkit.getScoreboardCriteria("trigger");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家死亡时自动递增。
     * <p>原文：
     * Increments automatically when a player dies.
     */
    public static final Criteria DEATH_COUNT = Bukkit.getScoreboardCriteria("deathCount");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player.
     */
    public static final Criteria PLAYER_KILL_COUNT = Bukkit.getScoreboardCriteria("playerKillCount");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死另一个生物实体时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another living entity.
     */
    public static final Criteria TOTAL_KILL_COUNT = Bukkit.getScoreboardCriteria("totalKillCount");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 反映玩家的生命值（0 为无生命，20 为默认最大生命值）。
     * <p>原文：
     * Mirrors the player's health points (0 for no health, 20 for maximum default health).
     */
    public static final Criteria HEALTH = Bukkit.getScoreboardCriteria("health");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 反映玩家的饥饿值（0 为无饥饿，20 为最大饥饿值）。
     * <p>原文：
     * Mirrors the player's food points (0 for no food, 20 for maximum food).
     */
    public static final Criteria FOOD = Bukkit.getScoreboardCriteria("food");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 反映玩家的氧气供应（0 为无氧气，300 为最大氧气）。
     * <p>原文：
     * Mirrors the player's air supply (0 for no air, 300 for maximum air).
     */
    public static final Criteria AIR = Bukkit.getScoreboardCriteria("air");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 反映玩家的护甲值（0 为无护甲，20 为最大护甲）。
     * <p>原文：
     * Mirrors the player's armor points (0 for no armor, 20 for maximum armor).
     */
    public static final Criteria ARMOR = Bukkit.getScoreboardCriteria("armor");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 反映玩家的经验值。
     * <p>原文：
     * Mirrors the player's experience points.
     */
    public static final Criteria XP = Bukkit.getScoreboardCriteria("xp");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 反映玩家的经验等级。
     * <p>原文：
     * Mirrors the player's experience level.
     */
    public static final Criteria LEVEL = Bukkit.getScoreboardCriteria("level");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死黑色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the black team.
     */
    public static final Criteria TEAM_KILL_BLACK = Bukkit.getScoreboardCriteria("teamkill.black");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死深蓝色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the dark blue team.
     */
    public static final Criteria TEAM_KILL_DARK_BLUE = Bukkit.getScoreboardCriteria("teamkill.dark_blue");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死深绿色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the dark green team.
     */
    public static final Criteria TEAM_KILL_DARK_GREEN = Bukkit.getScoreboardCriteria("teamkill.dark_green");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死青色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the dark aqua team.
     */
    public static final Criteria TEAM_KILL_DARK_AQUA = Bukkit.getScoreboardCriteria("teamkill.dark_aqua");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死深红色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the dark red team.
     */
    public static final Criteria TEAM_KILL_DARK_RED = Bukkit.getScoreboardCriteria("teamkill.dark_red");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死深紫色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the dark purple team.
     */
    public static final Criteria TEAM_KILL_DARK_PURPLE = Bukkit.getScoreboardCriteria("teamkill.dark_purple");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死金色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the gold team.
     */
    public static final Criteria TEAM_KILL_GOLD = Bukkit.getScoreboardCriteria("teamkill.gold");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死灰色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the gray team.
     */
    public static final Criteria TEAM_KILL_GRAY = Bukkit.getScoreboardCriteria("teamkill.gray");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死深灰色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the dark gray team.
     */
    public static final Criteria TEAM_KILL_DARK_GRAY = Bukkit.getScoreboardCriteria("teamkill.dark_gray");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死蓝色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the blue team.
     */
    public static final Criteria TEAM_KILL_BLUE = Bukkit.getScoreboardCriteria("teamkill.blue");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死绿色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the green team.
     */
    public static final Criteria TEAM_KILL_GREEN = Bukkit.getScoreboardCriteria("teamkill.green");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死青色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the aqua team.
     */
    public static final Criteria TEAM_KILL_AQUA = Bukkit.getScoreboardCriteria("teamkill.aqua");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死红色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the red team.
     */
    public static final Criteria TEAM_KILL_RED = Bukkit.getScoreboardCriteria("teamkill.red");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死浅紫色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the light purple team.
     */
    public static final Criteria TEAM_KILL_LIGHT_PURPLE = Bukkit.getScoreboardCriteria("teamkill.light_purple");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死黄色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the yellow team.
     */
    public static final Criteria TEAM_KILL_YELLOW = Bukkit.getScoreboardCriteria("teamkill.yellow");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家杀死白色队伍的另一个玩家时自动递增。
     * <p>原文：
     * Increments automatically when a player kills another player on the white team.
     */
    public static final Criteria TEAM_KILL_WHITE = Bukkit.getScoreboardCriteria("teamkill.white");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被黑色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the black team.
     */
    public static final Criteria KILLED_BY_TEAM_BLACK = Bukkit.getScoreboardCriteria("killedByTeam.black");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被深蓝色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the dark blue team.
     */
    public static final Criteria KILLED_BY_TEAM_DARK_BLUE = Bukkit.getScoreboardCriteria("killedByTeam.dark_blue");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被深绿色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the dark green team.
     */
    public static final Criteria KILLED_BY_TEAM_DARK_GREEN = Bukkit.getScoreboardCriteria("killedByTeam.dark_green");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被青色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the dark aqua team.
     */
    public static final Criteria KILLED_BY_TEAM_DARK_AQUA = Bukkit.getScoreboardCriteria("killedByTeam.dark_aqua");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被深红色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the dark red team.
     */
    public static final Criteria KILLED_BY_TEAM_DARK_RED = Bukkit.getScoreboardCriteria("killedByTeam.dark_red");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被深紫色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the dark purple team.
     */
    public static final Criteria KILLED_BY_TEAM_DARK_PURPLE = Bukkit.getScoreboardCriteria("killedByTeam.dark_purple");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被金色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the gold team.
     */
    public static final Criteria KILLED_BY_TEAM_GOLD = Bukkit.getScoreboardCriteria("killedByTeam.gold");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被灰色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the gray team.
     */
    public static final Criteria KILLED_BY_TEAM_GRAY = Bukkit.getScoreboardCriteria("killedByTeam.gray");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被深灰色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the dark gray team.
     */
    public static final Criteria KILLED_BY_TEAM_DARK_GRAY = Bukkit.getScoreboardCriteria("killedByTeam.dark_gray");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被蓝色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the blue team.
     */
    public static final Criteria KILLED_BY_TEAM_BLUE = Bukkit.getScoreboardCriteria("killedByTeam.blue");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被绿色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the green team.
     */
    public static final Criteria KILLED_BY_TEAM_GREEN = Bukkit.getScoreboardCriteria("killedByTeam.green");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被青色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the aqua team.
     */
    public static final Criteria KILLED_BY_TEAM_AQUA = Bukkit.getScoreboardCriteria("killedByTeam.aqua");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被红色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the red team.
     */
    public static final Criteria KILLED_BY_TEAM_RED = Bukkit.getScoreboardCriteria("killedByTeam.red");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被浅紫色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the light purple team.
     */
    public static final Criteria KILLED_BY_TEAM_LIGHT_PURPLE = Bukkit.getScoreboardCriteria("killedByTeam.light_purple");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被黄色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the yellow team.
     */
    public static final Criteria KILLED_BY_TEAM_YELLOW = Bukkit.getScoreboardCriteria("killedByTeam.yellow");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当玩家被白色队伍的另一个玩家杀死时自动递增。
     * <p>原文：
     * Increments automatically when a player is killed by a player on the white team.
     */
    public static final Criteria KILLED_BY_TEAM_WHITE = Bukkit.getScoreboardCriteria("killedByTeam.white");

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此条件的名称（其唯一标识符）。
     * <p>原文：
     * Get the name of this criteria (its unique id).
     *
     * @return 名称
     * <p>原文：the name
     */
    @NotNull
    public String getName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此条件是否为只读。如果为只读，则使用此条件的记分板无法更改其分数。
     * <p>原文：
     * Get whether or not this criteria is read only. If read only, scoreboards with this criteria
     * cannot have their scores changed.
     *
     * @return 如果为只读则返回 true，否则返回 false
     * <p>原文：true if read only, false otherwise
     */
    public boolean isReadOnly();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此条件默认使用的 {@link RenderType}。
     * <p>原文：
     * Get the {@link RenderType} used by default for this criteria.
     *
     * @return 默认渲染类型
     * <p>原文：the default render type
     */
    @NotNull
    public RenderType getDefaultRenderType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与方块或物品相关的指定统计信息的 {@link Criteria}。
     * <p>
     * 此方法期望一个 {@link Type#BLOCK} 或 {@link Type#ITEM} 的 {@link Statistic} 以及与该类型匹配的 {@link Material}（例如，BLOCK 统计需要 {@link Material#isBlock()} 为 true 的材料）。这是一个便捷方法，用于创建更复杂的复合条件，例如在方块破坏或物品使用时递增的条件。一个例子是 {@code Criteria.statistic(Statistic.CRAFT_ITEM, Material.STICK)}，返回表示 "minecraft.crafted:minecraft.stick" 的条件，当玩家制作棍子时会递增。
     * <p>
     * 如果提供的统计信息不需要额外数据，则会调用并返回 {@link #statistic(Statistic)}。
     * <p>
     * 此方法不保证任何给定条件存在于原版服务器上。
     * <p>原文：
     * Get a {@link Criteria} for the specified statistic pertaining to blocks or items.
     * <p>
     * This method expects a {@link Statistic} of {@link Type#BLOCK} or {@link Type#ITEM} and the
     * {@link Material} matching said type (e.g. BLOCK statistics require materials where
     * {@link Material#isBlock()} is true). This acts as a convenience to create more complex
     * compound criteria such as those that increment on block breaks, or item uses. An example
     * would be {@code Criteria.statistic(Statistic.CRAFT_ITEM, Material.STICK)}, returning a
     * Criteria representing "minecraft.crafted:minecraft.stick" which will increment when the
     * player crafts a stick.
     * <p>
     * If the provided statistic does not require additional data, {@link #statistic(Statistic)}
     * is called and returned instead.
     * <p>
     * This method provides no guarantee that any given criteria exists on the vanilla server.
     *
     * @param statistic 要获取条件的统计信息
     * <p>原文：the statistic for which to get a criteria
     * @param material 相关材料
     * <p>原文：the relevant material
     * @return 条件
     * <p>原文：the criteria
     * @throws IllegalArgumentException 如果 {@link Statistic#getType()} 不是 {@link Type#BLOCK} 或 {@link Type#ITEM}
     * <p>原文：if {@link Statistic#getType()} is anything other than
     * {@link Type#BLOCK} or {@link Type#ITEM}
     * @throws IllegalArgumentException 如果 {@link Statistic#getType()} 不是 {@link Type#BLOCK} 或 {@link Type#ITEM}
     * <p>原文：if {@link Statistic#getType()} is {@link Type#BLOCK}, but
     * {@link Material#isBlock()} is false
     * @throws IllegalArgumentException 如果 {@link Statistic#getType()} 不是 {@link Type#BLOCK} 或 {@link Type#ITEM}
     * <p>原文：if {@link Statistic#getType()} is {@link Type#ITEM}, but
     * {@link Material#isItem()} is false
     */
    @NotNull
    public static Criteria statistic(@NotNull Statistic statistic, @NotNull Material material) {
        Preconditions.checkArgument(statistic != null, "statistic must not be null");
        Preconditions.checkArgument(material != null, "material must not be null");

        Type type = statistic.getType();
        Preconditions.checkArgument(type == Type.BLOCK || type == Type.ITEM, "statistic type must be either BLOCK or ITEM, given %s", type);
        Preconditions.checkArgument(type != Type.BLOCK || material.isBlock(), "statistic type is BLOCK but got non-block Material, %s", material);
        Preconditions.checkArgument(type != Type.ITEM || material.isItem(), "statistic type is ITEM but got non-item Material, %s", material);

        // Good use case for a switch expression
        if (type == Type.BLOCK) {
            switch (statistic) {
                case MINE_BLOCK:
                    return Bukkit.getScoreboardCriteria("minecraft.mined:minecraft." + material.getKey().getKey());
                default:
                    break;
            }
        } else if (type == Type.ITEM) {
            switch (statistic) {
                case BREAK_ITEM:
                    return Bukkit.getScoreboardCriteria("minecraft.broken:minecraft." + material.getKey().getKey());
                case CRAFT_ITEM:
                    return Bukkit.getScoreboardCriteria("minecraft.crafted:minecraft." + material.getKey().getKey());
                case USE_ITEM:
                    return Bukkit.getScoreboardCriteria("minecraft.used:minecraft." + material.getKey().getKey());
                case PICKUP:
                    return Bukkit.getScoreboardCriteria("minecraft.picked_up:minecraft." + material.getKey().getKey());
                case DROP:
                    return Bukkit.getScoreboardCriteria("minecraft.dropped:minecraft." + material.getKey().getKey());
                default:
                    break;
            }
        }

        return statistic(statistic); // Fallback to a regular statistic
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与实体类型相关的指定统计信息的 {@link Criteria}。
     * <p>
     * 此方法期望一个 {@link Type#ENTITY} 的 {@link Statistic}。这是一个便捷方法，用于创建更复杂的复合条件，例如被特定实体类型杀死的条件。一个例子是 {@code Criteria.statistic(Statistic.KILL_ENTITY, EntityType.CREEPER)}，返回表示 "minecraft.killed:minecraft.creeper" 的条件，当玩家杀死苦力怕时会递增。
     * <p>
     * 如果提供的统计信息不需要额外数据，则会调用并返回 {@link #statistic(Statistic)}。
     * <p>
     * 此方法不保证任何给定条件存在于原版服务器上。
     * <p>原文：
     * Get a {@link Criteria} for the specified statistic pertaining to an entity type.
     * <p>
     * This method expects a {@link Statistic} of {@link Type#ENTITY}. This acts as a convenience
     * to create more complex compound criteria such as being killed by a specific entity type.
     * An example would be {@code Criteria.statistic(Statistic.KILL_ENTITY, EntityType.CREEPER)},
     * returning a Criteria representing "minecraft.killed:minecraft.creeper" which will increment
     * when the player kills a creepers.
     * <p>
     * If the provided statistic does not require additional data, {@link #statistic(Statistic)}
     * is called and returned instead.
     * <p>
     * This method provides no guarantee that any given criteria exists on the vanilla server.
     *
     * @param statistic 要获取条件的统计信息
     * <p>原文：the statistic for which to get a criteria
     * @param entityType 相关实体类型
     * <p>原文：the relevant entity type
     * @return 条件
     * <p>原文：the criteria
     * @throws IllegalArgumentException 如果 {@link Statistic#getType()} 不是 {@link Type#ENTITY}
     * <p>原文：if {@link Statistic#getType()} is not {@link Type#ENTITY}
     */
    @NotNull
    public static Criteria statistic(@NotNull Statistic statistic, @NotNull EntityType entityType) {
        Preconditions.checkArgument(statistic != null, "statistic must not be null");
        Preconditions.checkArgument(entityType != null, "entityType must not be null");
        Preconditions.checkArgument(statistic.getType() == Type.ENTITY, "statistic type must be ENTITY, given %s", statistic.getType());

        switch (statistic) {
            case KILL_ENTITY:
                return Bukkit.getScoreboardCriteria("minecraft.killed:minecraft." + entityType.getKey().getKey());
            case ENTITY_KILLED_BY:
                return Bukkit.getScoreboardCriteria("minecraft.killed_by:minecraft." + entityType.getKey().getKey());
            default:
                break;
        }

        return statistic(statistic); // Fallback to a regular statistic
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定统计信息的 {@link Criteria}。
     * <p>
     * 一个例子是 {@code Criteria.statistic(Statistic.FLY_ONE_CM)}，返回表示 "minecraft.custom:minecraft.aviate_one_cm" 的条件，当玩家使用鞘翅飞行时会递增。
     * <p>
     * 此方法不保证任何给定条件存在于原版服务器上。所有统计信息都被接受，但如果需要额外数据，某些可能无法按预期运行。对于方块/物品相关的统计信息，请参阅 {@link #statistic(Statistic, Material)}；对于实体相关的统计信息，请参阅 {@link #statistic(Statistic, EntityType)}。
     * <p>原文：
     * Get a {@link Criteria} for the specified statistic.
     * <p>
     * An example would be {@code Criteria.statistic(Statistic.FLY_ONE_CM)}, returning a Criteria
     * representing "minecraft.custom:minecraft.aviate_one_cm" which will increment when the player
     * flies with an elytra.
     * <p>
     * This method provides no guarantee that any given criteria exists on the vanilla server. All
     * statistics are accepted, however some may not operate as expected if additional data is
     * required. For block/item-related statistics, see {@link #statistic(Statistic, Material)},
     * and for entity-related statistics, see {@link #statistic(Statistic, EntityType)}
     *
     * @param statistic 要获取条件的统计信息
     * <p>原文：the statistic for which to get a criteria
     * @return 条件
     * <p>原文：the criteria
     */
    @NotNull
    public static Criteria statistic(@NotNull Statistic statistic) {
        Preconditions.checkArgument(statistic != null, "statistic must not be null");
        return Bukkit.getScoreboardCriteria(org.bukkit.Bukkit.getUnsafe().getStatisticCriteriaKey(statistic)); // Paper
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过名称获取（或创建）一个新的 {@link Criteria}。
     * <p>原文：
     * Get (or create) a new {@link Criteria} by its name.
     *
     * @param name 条件名称
     * <p>原文：the criteria name
     * @return 创建的条件
     * <p>原文：the created criteria
     */
    @NotNull
    public static Criteria create(@NotNull String name) {
        return Bukkit.getScoreboardCriteria(name);
    }

}
