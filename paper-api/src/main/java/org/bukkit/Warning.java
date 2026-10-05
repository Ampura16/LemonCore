package org.bukkit;

import com.google.common.collect.ImmutableMap;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 为指定项目指定一个警告声明。
 * <p>
 * 当服务器设置使用'default'警告时，如果{@link #value()}为true，则会输出警告。
 * <p>原文：
 * This designates the warning state for a specific item.
 * <p>
 * When the server settings dictate 'default' warnings, warnings are printed
 * if the {@link #value()} is true.
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Warning {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 这表示服务器过载时的警告。
     * <p>原文：
     * This represents the states that server verbose for warnings may be.
     */
    public enum WarningState {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示项目被废弃时输出的所有警告。
         * <p>原文：
         * Indicates all warnings should be printed for deprecated items.
         */
        ON,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示项目被废弃时不会输出警告。
         * <p>原文：
         * Indicates no warnings should be printed for deprecated items.
         */
        OFF,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示警告默认为{@link Warning}配置文件的注释信息，或注释信息找不到。
         * <p>原文：
         * Indicates each warning would default to the configured {@link
         * Warning} annotation, or always if annotation not found.
         */
        DEFAULT;

        private static final Map<String, WarningState> values = ImmutableMap.<String, WarningState>builder()
                .put("off", OFF)
                .put("false", OFF)
                .put("f", OFF)
                .put("no", OFF)
                .put("n", OFF)
                .put("on", ON)
                .put("true", ON)
                .put("t", ON)
                .put("yes", ON)
                .put("y", ON)
                .put("", DEFAULT)
                .put("d", DEFAULT)
                .put("default", DEFAULT)
                .build();

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 检查在这种状态下提供的警告是否应该被打印。
         * <p>原文：
         * This method checks the provided warning should be printed for this
         * state
         *
         * @param warning 添加到废弃项目的警告注释信息。
         * <p>原文：The warning annotation added to a deprecated item
         * @return <ul>
         *     <li>若为ON则返回True
         *     <li>若为OFF则返回false
         *     <li>若为DEFAULT则当且仅当注释非空并且{@link Warning#value()}指定为false时返回false，其他情况均返回true
         *     </ul>
         * <p>原文：<ul>
         *     <li>ON is always True
         *     <li>OFF is always false
         *     <li>DEFAULT is false if and only if annotation is not null and
         *     specifies false for {@link Warning#value()}, true otherwise.
         *     </ul>
         */
        public boolean printFor(@Nullable Warning warning) {
            if (Boolean.getBoolean("paper.alwaysPrintWarningState")) return true; // Paper
            if (this == DEFAULT) {
                return warning == null || warning.value();
            }
            return this == ON;
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 返回与指定的字符串数值相应的警告状态。
         * <p>原文：
         * This method returns the corresponding warning state for the given
         * string value.
         *
         * @param value 检查的字符串数值
         * <p>原文：The string value to check
         * @return 找不到则返回{@link #DEFAULT}，否则返回各自的警告状态
         * <p>原文：{@link #DEFAULT} if not found, or the respective
         *     WarningState
         */
        @NotNull
        public static WarningState value(@Nullable final String value) {
            if (value == null) {
                return DEFAULT;
            }
            WarningState state = values.get(value.toLowerCase(Locale.ROOT));
            if (state == null) {
                return DEFAULT;
            }
            return state;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置反对器在默认状态下是否会在注册中的事件被打印时警告。(不确定的翻译)
     * <p>原文：
     * This sets if the deprecation warnings when registering events gets
     * printed when the setting is in the default state.
     *
     * @return 正常时返回false，否则返回true并尝试输出警告。
     * <p>原文：false normally, or true to encourage warning printout
     */
    boolean value() default false;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 提供这个事件被废弃原因的细节信息。
     * <p>原文：
     * This can provide detailed information on why the event is deprecated.
     *
     * @return 这个事件被废弃的原因
     * <p>原文：The reason an event is deprecated
     */
    String reason() default "";
}
