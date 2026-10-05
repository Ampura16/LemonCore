package io.papermc.paper.command.brigadier;

import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link CommandRegistrationFlag} 在 {@link Commands} 注册中供内部使用。
 * <p>
 * 命令库可以借此更具体地自定义其命令的注册方式。
 * <p>原文：
 * A {@link CommandRegistrationFlag} is used in {@link Commands} registration for internal purposes.
 * <p>
 * A command library may use this to achieve more specific customization on how their commands are registered.
 * @apiNote 不保证这些标志的稳定性！此 API 不供公开使用。
 * <p>原文：Stability of these flags is not promised! This api is not intended for public use.
 */
@ApiStatus.Internal
public enum CommandRegistrationFlag {

    /**
     * @deprecated 现在这已是默认行为。
     * <p>原文：This is the default behavior now.
     */
    @Deprecated(since = "1.21.4")
    FLATTEN_ALIASES,
    /**
     * @deprecated 已移除，因为它现在会导致客户端出现警告。
     * <p>原文：Removed as it causes a warning to appear on the client now.
     */
    @Deprecated(since = "1.21.6", forRemoval = true)
    SERVER_ONLY
}
