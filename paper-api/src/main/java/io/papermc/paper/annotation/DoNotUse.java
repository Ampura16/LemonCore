package io.papermc.paper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 用于标记不应被调用的方法或构造器的注解。
 * <p>
 * 与 {@link Deprecated} 分开，以便与大量的弃用标记区分开来。
 * </p>
 * <p>原文：
 * Annotation used to mark methods or constructors which should not be called.
 *
 * <p>Separate from {@link Deprecated} to differentiate from the large amount of deprecations.</p>
 */
@ApiStatus.Internal
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface DoNotUse {
}
