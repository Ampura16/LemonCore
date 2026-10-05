package io.papermc.paper.plugin.provider.util;

import com.destroystokyo.paper.util.SneakyThrow;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InaccessibleObjectException;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 一个<b>内部</b>工具类型，包含从类加载器加载类似提供者（provider）类型的逻辑。至少在该工具的语境下，提供者将自身定义为某个特定父接口/类型（例如 {@link org.bukkit.plugin.java.JavaPlugin}）的实现，并实现无参构造器。
 * <p>原文：
 * An <b>internal</b> utility type that holds logic for loading a provider-like type from a classloaders.
 * Provides, at least in the context of this utility, define themselves as implementations of a specific parent
 * interface/type, e.g. {@link org.bukkit.plugin.java.JavaPlugin} and implement a no-args constructor.
 */
@NullMarked
@ApiStatus.Internal
public final class ProviderUtil {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从传入的类加载器加载位于所提供全限定类名处的类，使用（按照该方法约定应存在的）无参构造器创建其新实例，并将其转换为所提供的父类型。
     * <p>原文：
     * Loads the class found at the provided fully qualified class name from the passed classloader, creates a new
     * instance of it using the no-args constructor, that should exist as per this method contract, and casts it to the
     * provided parent type.
     *
     * @param clazz 要加载的类的全限定名
     * <p>原文：the fully qualified name of the class to load
     * @param classType 在 {@code clazz} 名称处找到并创建的对象应被转换成的父类型
     * <p>原文：the parent type that the created object found at the {@code clazz} name should be cast to
     * @param loader 应从中加载该类的加载器
     * <p>原文：the loader from which the class should be loaded
     * @param <T> 创建的对象将被转换成的父类的泛型类型
     * <p>原文：the generic type of the parent class the created object will be cast to
     * @return 从所提供全限定类名处的类实例化并转换为父类型的对象
     * <p>原文：the object instantiated from the class found at the provided FQN, cast to the parent type
     */
    public static <T> T loadClass(final String clazz, final Class<T> classType, final ClassLoader loader) {
        return loadClass(clazz, classType, loader, null);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从传入的类加载器加载位于所提供全限定类名处的类，使用（按照该方法约定应存在的）无参构造器创建其新实例，并将其转换为所提供的父类型。
     * <p>原文：
     * Loads the class found at the provided fully qualified class name from the passed classloader, creates a new
     * instance of it using the no-args constructor, that should exist as per this method contract, and casts it to the
     * provided parent type.
     *
     * @param clazz 要加载的类的全限定名
     * <p>原文：the fully qualified name of the class to load
     * @param classType 在 {@code clazz} 名称处找到并创建的对象应被转换成的父类型
     * <p>原文：the parent type that the created object found at the {@code clazz} name should be cast to
     * @param loader 应从中加载该类的加载器
     * <p>原文：the loader from which the class should be loaded
     * @param onError 在通过 sneaky throw 抛出任何未知异常之前执行的 runnable。
     * <p>原文：a runnable that is executed before any unknown exception is raised through a sneaky throw.
     * @param <T> 创建的对象将被转换成的父类的泛型类型
     * <p>原文：the generic type of the parent class the created object will be cast to
     * @return 从所提供全限定类名处的类实例化并转换为父类型的对象
     * <p>原文：the object instantiated from the class found at the provided fully qualified class name, cast to the
     * parent type
     */
    public static <T> T loadClass(final String clazz, final Class<T> classType, final ClassLoader loader, final @Nullable Runnable onError) {
        try {
            final T clazzInstance;

            try {
                final Class<?> jarClass = Class.forName(clazz, true, loader);

                final Class<? extends T> pluginClass;
                try {
                    pluginClass = jarClass.asSubclass(classType);
                } catch (final ClassCastException ex) {
                    throw new ClassCastException("class '%s' does not extend '%s'".formatted(clazz, classType));
                }

                final Constructor<? extends T> constructor = pluginClass.getDeclaredConstructor();
                try {
                    constructor.setAccessible(true); // Allow non-public constructors
                } catch (final InaccessibleObjectException | SecurityException ex) {
                    throw new RuntimeException("Inaccessible constructor");
                }

                clazzInstance = constructor.newInstance();
            } catch (final IllegalAccessException exception) {
                throw new RuntimeException("No public constructor");
            } catch (final InstantiationException exception) {
                throw new RuntimeException("Abnormal class instantiation", exception);
            }

            return clazzInstance;
        } catch (final Throwable e) {
            if (onError != null) {
                onError.run();
            }
            SneakyThrow.sneaky(e);
        }

        throw new AssertionError(); // Shouldn't happen
    }

}
