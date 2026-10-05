package org.bukkit.configuration.serialization;

import java.util.Map;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个可序列化的对象。
 * <p>
 * 除了本接口定义的方法外，这些对象还必须实现以下之一：
 * <ul>
 * <li>一个名为 "deserialize" 的静态方法，接受单个 {@link Map}&lt;
 * {@link String}, {@link Object}&gt; 并返回该类的实例。</li>
 * <li>一个名为 "valueOf" 的静态方法，接受单个 {@link Map}&lt;{@link
 * String}, {@link Object}&gt; 并返回该类的实例。</li>
 * <li>一个接受单个 {@link Map}&lt;{@link String},
 * {@link Object}&gt; 的构造函数。</li>
 * </ul>
 * 除了实现此接口外，你还必须通过
 * {@link ConfigurationSerialization#registerClass(Class)} 注册该类。
 * <p>原文：
 * Represents an object that may be serialized.
 * <p>
 * These objects MUST implement one of the following, in addition to the
 * methods as defined by this interface:
 * <ul>
 * <li>A static method "deserialize" that accepts a single {@link Map}&lt;
 * {@link String}, {@link Object}&gt; and returns the class.</li>
 * <li>A static method "valueOf" that accepts a single {@link Map}&lt;{@link
 * String}, {@link Object}&gt; and returns the class.</li>
 * <li>A constructor that accepts a single {@link Map}&lt;{@link String},
 * {@link Object}&gt;.</li>
 * </ul>
 * In addition to implementing this interface, you must register the class
 * with {@link ConfigurationSerialization#registerClass(Class)}.
 *
 * @see DelegateDeserialization
 * @see SerializableAs
 */
public interface ConfigurationSerializable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建此类的 Map 表示。
     * <p>
     * 此类必须提供一个方法来恢复此类，具体定义见
     * {@link ConfigurationSerializable} 接口的 javadoc。
     * <p>原文：
     * Creates a Map representation of this class.
     * <p>
     * This class must provide a method to restore this class, as defined in
     * the {@link ConfigurationSerializable} interface javadocs.
     *
     * nb: It is not intended for this method to be called directly, this will
     * be called by the {@link ConfigurationSerialization} class.
     *
     * @return 包含此类当前状态的 Map
     * <p>原文：Map containing the current state of this class
     */
    @NotNull
    @ApiStatus.OverrideOnly
    public Map<String, Object> serialize();
}
