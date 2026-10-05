package org.bukkit.configuration.serialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 应用于 {@link ConfigurationSerializable} 的注解, 表示该类将把所有反序列化委托给另一个 {@link ConfigurationSerializable}.
 * <p>原文：
 * Applies to a {@link ConfigurationSerializable} that will delegate all
 * deserialization to another {@link ConfigurationSerializable}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DelegateDeserialization {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 应该使用哪个类作为此类反序列化的委托.
     * <p>原文：
     * Which class should be used as a delegate for this classes
     * deserialization
     *
     * @return 委托类.
     * <p>原文：Delegate class
     */
    @NotNull
    public Class<? extends ConfigurationSerializable> value();
}
