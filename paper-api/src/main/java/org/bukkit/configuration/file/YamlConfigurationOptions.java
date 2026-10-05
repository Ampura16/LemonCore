package org.bukkit.configuration.file;

import com.google.common.base.Preconditions;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 用于控制 {@link YamlConfiguration} 输入和输出的各种设置.
 * <p>原文：
 * Various settings for controlling the input and output of a {@link
 * YamlConfiguration}
 */
public class YamlConfigurationOptions extends FileConfigurationOptions {
    private int indent = 2;
    private int width = 80;
    private int codePointLimit = Integer.MAX_VALUE; // Paper - use upstream's default from YamlConfiguration

    protected YamlConfigurationOptions(@NotNull YamlConfiguration configuration) {
        super(configuration);
    }

    @NotNull
    @Override
    public YamlConfiguration configuration() {
        return (YamlConfiguration) super.configuration();
    }

    @NotNull
    @Override
    public YamlConfigurationOptions copyDefaults(boolean value) {
        super.copyDefaults(value);
        return this;
    }

    @NotNull
    @Override
    public YamlConfigurationOptions pathSeparator(char value) {
        super.pathSeparator(value);
        return this;
    }

    @NotNull
    @Override
    public YamlConfigurationOptions setHeader(@Nullable List<String> value) {
        super.setHeader(value);
        return this;
    }

    @NotNull
    @Override
    @Deprecated(since = "1.18.1")
    public YamlConfigurationOptions header(@Nullable String value) {
        super.header(value);
        return this;
    }

    @NotNull
    @Override
    public YamlConfigurationOptions setFooter(@Nullable List<String> value) {
        super.setFooter(value);
        return this;
    }

    @NotNull
    @Override
    public YamlConfigurationOptions parseComments(boolean value) {
        super.parseComments(value);
        return this;
    }

    @NotNull
    @Override
    @Deprecated(since = "1.18.1")
    public YamlConfigurationOptions copyHeader(boolean value) {
        super.copyHeader(value);
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每行缩进使用的空格数.
     * <p>
     * 最小值为 2, 最大值为 9.
     * <p>原文：
     * Gets how much spaces should be used to indent each line.
     * <p>
     * The minimum value this may be is 2, and the maximum is 9.
     *
     * @return 缩进量.
     * <p>原文：How much to indent by
     */
    public int indent() {
        return indent;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置每行缩进使用的空格数.
     * <p>
     * 最小值为 2, 最大值为 9.
     * <p>原文：
     * Sets how much spaces should be used to indent each line.
     * <p>
     * The minimum value this may be is 2, and the maximum is 9.
     *
     * @param value 新的缩进值.
     * <p>原文：New indent
     * @return 此对象, 用于链式调用.
     * <p>原文：This object, for chaining
     */
    @NotNull
    public YamlConfigurationOptions indent(int value) {
        Preconditions.checkArgument(value >= 2, "Indent must be at least 2 characters");
        Preconditions.checkArgument(value <= 9, "Indent cannot be greater than 9 characters");

        this.indent = value;
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取行在被拆分之前的最大长度.
     * <p>原文：
     * Gets how long a line can be, before it gets split.
     *
     * @return 最大行宽.
     * <p>原文：How the max line width
     */
    public int width() {
        return width;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置行在被拆分之前的最大长度.
     * <p>原文：
     * Sets how long a line can be, before it gets split.
     *
     * @param value 新的宽度.
     * <p>原文：New width
     * @return 此对象, 用于链式调用.
     * <p>原文：This object, for chaining
     */
    @NotNull
    public YamlConfigurationOptions width(int value) {
        this.width = value;
        return this;
    }

    // Paper start
    /**
     * Gets the maximum code point limit, that being, the maximum length of the document
     * in which the loader will read
     *
     * @return The current value
     */
    public int codePointLimit() {
        return codePointLimit;
    }

    /**
     * Sets the maximum code point limit, that being, the maximum length of the document
     * in which the loader will read
     *
     * @param codePointLimit new codepoint limit
     * @return This object, for chaining
     */
    @NotNull
    public YamlConfigurationOptions codePointLimit(int codePointLimit) {
        this.codePointLimit = codePointLimit;
        return this;
    }
    // Paper end
}
