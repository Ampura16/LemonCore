package io.papermc.paper.command.brigadier.argument;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 用于从 {@link CommandContext} 中提取注册表相关参数的工具。
 * <p>原文：
 * Utilities for extracting registry-related arguments from a {@link CommandContext}.
 */
public final class RegistryArgumentExtractor {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从命令上下文获取类型化键参数。
     * <p>原文：
     * Gets a typed key argument from a command context.
     *
     * @param context 命令上下文
     * <p>原文：the command context
     * @param registryKey 类型化键的注册表键
     * <p>原文：the registry key for the typed key
     * @param name 参数名称
     * <p>原文：the argument name
     * @return 类型化键参数
     * <p>原文：the typed key argument
     * @param <T> 值类型
     * <p>原文：the value type
     * @param <S> 发送者类型
     * <p>原文：the sender type
     * @throws IllegalArgumentException 如果注册表键与类型化键不匹配
     * <p>原文：if the registry key doesn't match the typed key
     */
    @SuppressWarnings("unchecked")
    public static <T, S> TypedKey<T> getTypedKey(final CommandContext<S> context, final RegistryKey<T> registryKey, final String name) {
        final TypedKey<T> typedKey = context.getArgument(name, TypedKey.class);
        if (typedKey.registryKey().equals(registryKey)) {
            return typedKey;
        }
        throw new IllegalArgumentException(registryKey + " is not the correct registry for " + typedKey);
    }

    private RegistryArgumentExtractor() {
    }
}
