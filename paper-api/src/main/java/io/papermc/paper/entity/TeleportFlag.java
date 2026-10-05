package io.papermc.paper.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示可在传送时设置、可能稍微改变传送行为的标志。
 * <p>原文：
 * Represents a flag that can be set on teleportation that may
 * slightly modify the behavior.
 *
 * @see Relative
 */
public sealed interface TeleportFlag permits TeleportFlag.EntityState, TeleportFlag.Relative {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 相对标志使实体在传送时不会损失特定于该标志的轴/上下文中的速度。
     * <p>原文：
     * Relative flags enable an entity to not lose their velocity in the flag-specific axis/context when teleporting.
     *
     * @apiNote API 中公开的相对标志*并不*对应原版已知的所有标志，因为传送总是需要绝对位置，与位置相关的相对标志并不适用。
     * <p>原文：The relative flags exposed in the API do *not* mirror all flags known to vanilla, as relative flags concerning
     * the position are non-applicable given teleports always expect an absolute location.
     * @see Player#teleport(Location, PlayerTeleportEvent.TeleportCause, TeleportFlag...)
     * @see Entity#teleport(Location, PlayerTeleportEvent.TeleportCause, TeleportFlag...)
     */
    enum Relative implements TeleportFlag {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置玩家在传送期间不损失 x 轴上的速度。
         * <p>原文：
         * Configures the player to not lose velocity in their x axis during the teleport.
         */
        VELOCITY_X,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置玩家在传送期间不损失 y 轴上的速度。
         * <p>原文：
         * Configures the player to not lose velocity in their y axis during the teleport.
         */
        VELOCITY_Y,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置玩家在传送期间不损失 z 轴上的速度。
         * <p>原文：
         * Configures the player to not lose velocity in their z axis during the teleport.
         */
        VELOCITY_Z,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置玩家在传送期间不损失当前旋转方向上的速度。
         * <p>原文：
         * Configures the player to not lose velocity in their current rotation during the teleport.
         */
        VELOCITY_ROTATION;
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置玩家在传送期间不损失 x 轴上的速度。
         * <p>原文：
         * Configures the player to not lose velocity in their x axis during the teleport.
         *
         * @deprecated 自 1.21.3 起，原版将相对传送标志拆分为与速度相关和与位置相关的两类。由于 API 不处理与位置相关的标志，该名称已不再适用，请改用 {@link #VELOCITY_X}。
         * <p>原文：Since 1.21.3, vanilla split up the relative teleport flags into velocity and position related
         * ones. As the API does not deal with position relative flags, this name is no longer applicable.
         * Use {@link #VELOCITY_X} instead.
         */
        @Deprecated(since = "1.21.3", forRemoval = true)
        public static final Relative X = VELOCITY_X;
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置玩家在传送期间不损失 y 轴上的速度。
         * <p>原文：
         * Configures the player to not lose velocity in their y axis during the teleport.
         *
         * @deprecated 自 1.21.3 起，原版将相对传送标志拆分为与速度相关和与位置相关的两类。由于 API 不处理与位置相关的标志，该名称已不再适用，请改用 {@link #VELOCITY_Y}。
         * <p>原文：Since 1.21.3, vanilla split up the relative teleport flags into velocity and position related
         * ones. As the API does not deal with position relative flags, this name is no longer applicable.
         * Use {@link #VELOCITY_Y} instead.
         */
        @Deprecated(since = "1.21.3", forRemoval = true)
        public static final Relative Y = VELOCITY_Y;
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 配置玩家在传送期间不损失 z 轴上的速度。
         * <p>原文：
         * Configures the player to not lose velocity in their z axis during the teleport.
         *
         * @deprecated 自 1.21.3 起，原版将相对传送标志拆分为与速度相关和与位置相关的两类。由于 API 不处理与位置相关的标志，该名称已不再适用，请改用 {@link #VELOCITY_Z}。
         * <p>原文：Since 1.21.3, vanilla split up the relative teleport flags into velocity and position related
         * ones. As the API does not deal with position relative flags, this name is no longer applicable.
         * Use {@link #VELOCITY_Z} instead.
         */
        @Deprecated(since = "1.21.3", forRemoval = true)
        public static final Relative Z = VELOCITY_Z;
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 表示玩家的偏航角
         * <p>原文：
         * Represents the player's yaw
         *
         * @deprecated 相对速度标志现在允许整个旋转都是相对的，而不再为偏航角和俯仰角分别提供选项。请改用 {@link #VELOCITY_ROTATION}。
         * <p>原文：relative velocity flags now allow for the whole rotation to be relative, instead of the yaw and
         * pitch having individual options. Use {@link #VELOCITY_ROTATION} instead.
         */
        @Deprecated(since = "1.21.3", forRemoval = true)
        public static final Relative YAW = VELOCITY_ROTATION;
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 表示玩家的俯仰角
         * <p>原文：
         * Represents the player's pitch
         *
         * @deprecated 相对速度标志现在允许整个旋转都是相对的，而不再为偏航角和俯仰角分别提供选项。请改用 {@link #VELOCITY_ROTATION}。
         * <p>原文：relative velocity flags now allow for the whole rotation to be relative, instead of the yaw and
         * pitch having individual options. Use {@link #VELOCITY_ROTATION} instead.
         */
        @Deprecated(since = "1.21.3", forRemoval = true)
        public static final Relative PITCH = VELOCITY_ROTATION;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示影响实体传送时状态的标志。
     * <p>原文：
     * Represents flags that effect the entity's state on
     * teleportation.
     *
     * @deprecated 自 1.21.10 起，传送的默认行为已与原版一致，这意味着这些标志的功能都已默认生效。
     * <p>原文：As of 1.21.10, the default behavior for teleportation is now aligned with vanilla
     * behavior. This means all of these flags are functionally done by default.
     */
    @Deprecated(since = "1.21.10", forRemoval = true)
    enum EntityState implements TeleportFlag {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 是否不要求在传送前移除所有乘客。
         * <p>
         * 注意：如果带着此标志传送到另一个世界且实体上有其他实体骑乘，此次传送将返回 false 且不会发生。
         * <p>原文：
         * If all passengers should not be required to be removed prior to teleportation.
         * <p>
         * Note:
         * Teleporting to a different world with this flag present while the entity has entities riding it
         * will cause this teleportation to return false and not occur.
         *
         * @deprecated 现在这已是传送的默认行为。如果要让所有乘客下来，请使用 {@link Entity#removePassenger(Entity)} 移除它们。
         * <p>原文：This is now default behavior in teleportation. If you want to dismount all passengers,
         * remove them with {@link Entity#removePassenger(Entity)}.
         */
        @Deprecated(since = "1.21.10", forRemoval = true)
        RETAIN_PASSENGERS,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 如果实体正在骑乘另一实体，则不让其下坐骑。
         * <p>
         * 注意：如果带着此标志传送到另一个世界而此实体正骑乘另一实体，此次传送将返回 false 且不会发生。
         * <p>原文：
         * If the entity should not be dismounted if they are riding another entity.
         * <p>
         * Note:
         * Teleporting to a different world with this flag present while this entity is riding another entity will
         * cause this teleportation to return false and not occur.
         *
         * @deprecated 该行为技术性很强，由于客户端限制无法复现，并且已经多个版本未起作用。
         * <p>原文：This behavior was highly technical and is not replicatable due to client limitations,
         * and has not functioned for many updates.
         */
        @Deprecated(since = "1.21.10", forRemoval = true)
        RETAIN_VEHICLE,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 表示玩家传送时不应关闭其当前打开的物品栏。
         * <p>
         * 注意：传送到另一个世界时将忽略此选项。
         * <p>原文：
         * Indicates that a player should not have their current open inventory closed when teleporting.
         * <p>
         * Note:
         * This option will be ignored when teleported to a different world.
         *
         * @deprecated 传送时不再执行此操作，请使用 {@link Player#closeInventory()} 自行处理。
         * <p>原文：No longer done on teleportation, use {@link Player#closeInventory()} to do this yourself.
         */
        @Deprecated(since = "1.21.10", forRemoval = true)
        RETAIN_OPEN_INVENTORY;
    }

}
