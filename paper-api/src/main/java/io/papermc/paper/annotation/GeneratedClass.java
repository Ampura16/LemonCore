package io.papermc.paper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 用于标记生成的类。任何手动修改都会被覆盖。
 * <p>原文：
 * Used to mark classes which are generated.
 * Any manual changes will be overwritten.
 */
@ApiStatus.Internal
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GeneratedClass {
}
