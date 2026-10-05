package io.papermc.paper.plugin.lifecycle.event.registrar;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 公开可重载的 {@link Registrar} 的生命周期事件。
 * <p>原文：
 * A lifecycle event that exposes a {@link Registrar} that is
 * reloadable.
 *
 * @param <R> 注册器类型
 * <p>原文：the registrar type
 * @see RegistrarEvent
 */
@ApiStatus.NonExtendable
public interface ReloadableRegistrarEvent<R extends Registrar> extends RegistrarEvent<R> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此次重载的原因。
     * <p>原文：
     * Get the cause of this reload.
     *
     * @return 原因
     * <p>原文：the cause
     */
    @Contract(pure = true)
    Cause cause();

    enum Cause {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 服务器的初次加载。
         * <p>原文：
         * The initial load of the server.
         */
        INITIAL,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 重载，通过 Bukkit 或 Minecraft 重载命令等多种机制之一触发。
         * <p>原文：
         * A reload, triggered via one of the various mechanisms like
         * the bukkit or minecraft reload commands.
         */
        RELOAD
    }
}
