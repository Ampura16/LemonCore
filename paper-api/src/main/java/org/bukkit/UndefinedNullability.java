package org.bukkit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 用于标注那些空值性未明确定义的类型，因此既不适用
 * {@link org.jetbrains.annotations.NotNull} 也不适用
 * {@link org.jetbrains.annotations.Nullable}。例如当接口定义了一个方法，
 * 其空值性取决于具体实现时。
 * <p>原文：
 * Annotation for types, whose nullability is not well defined, so
 * {@link org.jetbrains.annotations.NotNull} nor
 * {@link org.jetbrains.annotations.Nullable} is applicable. For example when
 * interface defines a method, whose nullability depends on the implementation.
 *
 * @deprecated 通常不应在任何新 API 代码中使用，因为这暗示了不良的 API 设计。
 * <p>原文：This should generally not be used in any new API code as it
 * suggests a bad API design.
 */
@Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Documented // Paper
@ApiStatus.Internal
@Deprecated(since = "1.13.2")
public @interface UndefinedNullability {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 人类可读的描述，说明该类型在何种情况下可为空。
     * <p>原文：
     * Human readable description of the circumstances, in which the type is
     * nullable.
     *
     * @return 描述信息
     * <p>
     * 原文：Human readable description of the circumstances, in which the type is
     * nullable.
     * <p>原文：description
     */
    String value() default "";
}
