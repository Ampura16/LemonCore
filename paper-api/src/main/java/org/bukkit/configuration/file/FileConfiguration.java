package org.bukkit.configuration.file;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemoryConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 这是一个实现了 {@link Configuration} 的配置文件的基类
 * <p>原文：
 * This is a base class for all File based implementations of {@link
 * Configuration}
 */
public abstract class FileConfiguration extends MemoryConfiguration {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个空的，没有值默认值的 {@link FileConfiguration}.
     * <p>原文：
     * Creates an empty {@link FileConfiguration} with no default values.
     */
    public FileConfiguration() {
        super();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个空的 {@link FileConfiguration} 并且使用 {@link
     * Configuration} 内的所有默认值创建它.
     * <p>原文：
     * Creates an empty {@link FileConfiguration} using the specified {@link
     * Configuration} as a source for all default values.
     *
     * @param defaults 为其创建提供缺省值的Configuration.
     * <p>原文：Default value provider
     */
    public FileConfiguration(@Nullable Configuration defaults) {
        super(defaults);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以一个 {@link FileConfiguration} 调用该方法，将文件储存到指定位置.
     * <p>
     * 如果你指定储存的这个文件不存在,这个方法会帮你自动创建一个. 如果这个文件存在,那么该方法会把所有未保存的更改直接写入文件
     * 并且直接覆盖
     * <p>原文：
     * Saves this {@link FileConfiguration} to the specified location.
     * <p>
     * If the file does not exist, it will be created. If already exists, it
     * will be overwritten. If it cannot be overwritten or created, an
     * exception will be thrown.
     * <p>
     * This method will save using the system default encoding, or possibly
     * using UTF8.
     *
     * @param file 要储存的文件
     * <p>原文：File to save to.
     * @throws IOException 然后会给出无法创建或者保存的原因.
     * <p>原文：Thrown when the given file cannot be written to for
     *     any reason.
     * @throws IllegalArgumentException 如果文件为空，抛出该异常
     * <p>原文：Thrown when file is null.
     */
    public void save(@NotNull File file) throws IOException {
        Preconditions.checkArgument(file != null, "File cannot be null");

        Files.createParentDirs(file);

        String data = saveToString();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

        try {
            writer.write(data);
        } finally {
            writer.close();
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以一个 {@link FileConfiguration} 调用该方法，将文件储存到指定位置.
     * <p>
     * 如果你指定储存的这个文件不存在,这个方法会帮你自动创建一个. 如果这个文件存在,那么该方法会把所有未保存的更改直接写入文件
     * 并且直接覆盖
     * <p>原文：
     * Saves this {@link FileConfiguration} to the specified location.
     * <p>
     * If the file does not exist, it will be created. If already exists, it
     * will be overwritten. If it cannot be overwritten or created, an
     * exception will be thrown.
     * <p>
     * This method will save using the system default encoding, or possibly
     * using UTF8.
     *
     * @param file 要储存的文件
     * <p>原文：File to save to.
     * @throws IOException 然后会给出无法创建或者保存的原因.
     * <p>原文：Thrown when the given file cannot be written to for
     *     any reason.
     * @throws IllegalArgumentException 如果文件为空，泡出该异常
     * <p>原文：Thrown when file is null.
     */
    public void save(@NotNull String file) throws IOException {
        Preconditions.checkArgument(file != null, "File cannot be null");

        save(new File(file));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将这个 {@link FileConfiguration} 转化为String对象并且返回
     * <p>原文：
     * Saves this {@link FileConfiguration} to a string, and returns it.
     *
     * @return 这个FileConfiguration包含的所有String
     * <p>原文：String containing this configuration.
     */
    @NotNull
    public abstract String saveToString();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从指定位置加载 {@link FileConfiguration}.
     * <p>
     * 此配置中包含的所有值都将被移除,只保留设置和默认值,新的值将从给定的文件中加载.
     * <p>
     * 如果文件因任何原因无法加载,将会抛出异常.
     * <p>原文：
     * Loads this {@link FileConfiguration} from the specified location.
     * <p>
     * All the values contained within this configuration will be removed,
     * leaving only settings and defaults, and the new values will be loaded
     * from the given file.
     * <p>
     * If the file cannot be loaded for any reason, an exception will be
     * thrown.
     *
     * @param file 要加载的文件
     * <p>原文：File to load from.
     * @throws FileNotFoundException 当给定的文件无法被打开时抛出
     * <p>原文：Thrown when the given file cannot be
     *     opened.
     * @throws IOException 当给定的文件无法被读取时抛出
     * <p>原文：Thrown when the given file cannot be read.
     * @throws InvalidConfigurationException 当给定的文件不是有效的配置时抛出
     * <p>原文：Thrown when the given file is not
     *     a valid Configuration.
     * @throws IllegalArgumentException 当文件为null时抛出
     * <p>原文：Thrown when file is null.
     */
    public void load(@NotNull File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Preconditions.checkArgument(file != null, "File cannot be null");

        final FileInputStream stream = new FileInputStream(file);

        load(new InputStreamReader(stream, StandardCharsets.UTF_8));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从指定的读取器加载此 {@link FileConfiguration}.
     * <p>
     * 此配置中包含的所有值都将被移除,只保留设置和默认值,新的值将从给定的流中加载.
     * <p>原文：
     * Loads this {@link FileConfiguration} from the specified reader.
     * <p>
     * All the values contained within this configuration will be removed,
     * leaving only settings and defaults, and the new values will be loaded
     * from the given stream.
     *
     * @param reader 要从中加载的读取器
     * <p>原文：the reader to load from
     * @throws IOException 当底层读取器抛出IOException时抛出
     * <p>原文：thrown when underlying reader throws an IOException
     * @throws InvalidConfigurationException 当读取器不表示有效的配置时抛出
     * <p>原文：thrown when the reader does not
     *      represent a valid Configuration
     * @throws IllegalArgumentException 当reader为null时抛出
     * <p>原文：thrown when reader is null
     */
    public void load(@NotNull Reader reader) throws IOException, InvalidConfigurationException {
        BufferedReader input = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);

        StringBuilder builder = new StringBuilder();

        try {
            String line;

            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        } finally {
            input.close();
        }

        loadFromString(builder.toString());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从指定位置加载 {@link FileConfiguration}.
     * <p>
     * 此配置中包含的所有值都将被移除,只保留设置和默认值,新的值将从给定的文件中加载.
     * <p>
     * 如果文件因任何原因无法加载,将会抛出异常.
     * <p>原文：
     * Loads this {@link FileConfiguration} from the specified location.
     * <p>
     * All the values contained within this configuration will be removed,
     * leaving only settings and defaults, and the new values will be loaded
     * from the given file.
     * <p>
     * If the file cannot be loaded for any reason, an exception will be
     * thrown.
     *
     * @param file 要加载的文件
     * <p>原文：File to load from.
     * @throws FileNotFoundException 当给定的文件无法被打开时抛出
     * <p>原文：Thrown when the given file cannot be
     *     opened.
     * @throws IOException 当给定的文件无法被读取时抛出
     * <p>原文：Thrown when the given file cannot be read.
     * @throws InvalidConfigurationException 当给定的文件不是有效的配置时抛出
     * <p>原文：Thrown when the given file is not
     *     a valid Configuration.
     * @throws IllegalArgumentException 当文件为null时抛出
     * <p>原文：Thrown when file is null.
     */
    public void load(@NotNull String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Preconditions.checkArgument(file != null, "File cannot be null");

        load(new File(file));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从指定的字符串加载此 {@link FileConfiguration},而不是从文件加载.
     * <p>
     * 此配置中包含的所有值都将被移除,只保留设置和默认值,新的值将从给定的字符串中加载.
     * <p>
     * 如果字符串在任何方面无效,将会抛出异常.
     * <p>原文：
     * Loads this {@link FileConfiguration} from the specified string, as
     * opposed to from file.
     * <p>
     * All the values contained within this configuration will be removed,
     * leaving only settings and defaults, and the new values will be loaded
     * from the given string.
     * <p>
     * If the string is invalid in any way, an exception will be thrown.
     *
     * @param contents 要加载的配置内容
     * <p>原文：Contents of a Configuration to load.
     * @throws InvalidConfigurationException 如果指定的字符串无效则抛出
     * <p>原文：Thrown if the specified string is
     *     invalid.
     * @throws IllegalArgumentException 如果contents为null则抛出
     * <p>原文：Thrown if contents is null.
     */
    public abstract void loadFromString(@NotNull String contents) throws InvalidConfigurationException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 空白字符串
     * <p>原文：empty string
     * @deprecated 此方法仅为向后兼容而存在.它不会执行任何操作,请勿使用!请使用
     * {@link FileConfigurationOptions#getHeader()} 代替.
     * <p>原文：This method only exists for backwards compatibility. It will
     * do nothing and should not be used! Please use
     * {@link FileConfigurationOptions#getHeader()} instead.
     */
    @NotNull
    @Deprecated(since = "1.18.1")
    protected String buildHeader() {
        return "";
    }

    @NotNull
    @Override
    public FileConfigurationOptions options() {
        if (options == null) {
            options = new FileConfigurationOptions(this);
        }

        return (FileConfigurationOptions) options;
    }
}
