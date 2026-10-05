package io.papermc.paper.plugin.loader.library.impl;

import io.papermc.paper.plugin.loader.library.ClassPathLibrary;
import io.papermc.paper.plugin.loader.library.LibraryLoadingException;
import io.papermc.paper.plugin.loader.library.LibraryStore;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link ClassPathLibrary} 的简单 jar 库实现，允许 {@link io.papermc.paper.plugin.loader.PluginLoader} 将本地文件系统中存储的 jar 追加到其运行时类加载器。<p> 创建 jar 库类型的示例如下：<pre>{@code
 *   final JarLibrary customLibrary = new JarLibrary(Path.of("libs/custom-library-1.24.jar"));
 * }</pre> 这会得到一个在运行时向插件类加载器提供 {@code libs/custom-library-1.24.jar} 处 jar 的 jar 库。<p> 如果指定路径下的文件不存在，jar 库实现将报错。
 * <p>原文：
 * A simple jar library implementation of the {@link ClassPathLibrary} that allows {@link io.papermc.paper.plugin.loader.PluginLoader}s to
 * append a jar stored on the local file system into their runtime classloader.
 * <p>
 * An example creation of the jar library type may look like this:
 * <pre>{@code
 *   final JarLibrary customLibrary = new JarLibrary(Path.of("libs/custom-library-1.24.jar"));
 * }</pre>
 * resulting in a jar library that provides the jar at {@code libs/custom-library-1.24.jar} to the plugins classloader
 * at runtime.
 * <p>
 * The jar library implementation will error if the file does not exist at the specified path.
 */
@NullMarked
public class JarLibrary implements ClassPathLibrary {

    private final Path path;

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建引用所提供路径下 jar 文件的新 jar 库。
     * <p>原文：
     * Creates a new jar library that references the jar file found at the provided path.
     *
     * @param path 路径，相对于 JVM 的启动目录。
     * <p>原文：the path, relative to the JVMs start directory.
     */
    public JarLibrary(final Path path) {
        this.path = path;
    }

    @Override
    public void register(final LibraryStore store) throws LibraryLoadingException {
        if (Files.notExists(this.path)) {
            throw new LibraryLoadingException("Could not find library at " + this.path);
        }

        store.addLibrary(this.path);
    }
}
