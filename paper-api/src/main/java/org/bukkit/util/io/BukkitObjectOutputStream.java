package org.bukkit.util.io;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 这个类旨在用于与 {@link ConfigurationSerializable} API 一起使用.
 * 它把对象转换为其内部表示,之后便可使用 {@link BukkitObjectInputStream} 反序列化.
 * <p>
 * 直接继承实现这个类的语法可能在未来版本被取消.
 * <p>原文：
 * This class is designed to be used in conjunction with the {@link
 * ConfigurationSerializable} API. It translates objects to an internal
 * implementation for later deserialization using {@link
 * BukkitObjectInputStream}.
 * <p>
 * Behavior of implementations extending this class is not guaranteed across
 * future versions.
 *
 * @deprecated Object streams on their own are not safe. For safer and more consistent serialization of items,
 * use {@link org.bukkit.inventory.ItemStack#serializeAsBytes()} or
 * {@link org.bukkit.inventory.ItemStack#serializeItemsAsBytes(java.util.Collection)}.
 */
@Deprecated(since = "1.21") // Paper
public class BukkitObjectOutputStream extends ObjectOutputStream {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造函数提供镜像连接父类的功能.
     * <p>原文：
     * Constructor provided to mirror super functionality.
     *
     * @throws IOException 创建输出流时发生 I/O 错误
     * <p>原文：if an I/O error occurs while creating this stream
     * @throws SecurityException 如果安全管理器存在并且拒绝启用subclassing
     * <p>原文：if a security manager exists and denies
     * enabling subclassing
     * @see ObjectOutputStream#ObjectOutputStream()
     */
    protected BukkitObjectOutputStream() throws IOException, SecurityException {
        super();
        super.enableReplaceObject(true);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 输出流作为参数的构造函数.
     * <p>原文：
     * Object output stream decoration constructor.
     *
     * @param out OutputStream对象
     * <p>原文：the stream to wrap
     * @throws IOException 如果向输入流写入头数据时发生错误
     * <p>原文：if an I/O error occurs while writing stream header
     * @see ObjectOutputStream#ObjectOutputStream(OutputStream)
     */
    public BukkitObjectOutputStream(OutputStream out) throws IOException {
        super(out);
        super.enableReplaceObject(true);
    }

    @Override
    protected Object replaceObject(Object obj) throws IOException {
        if (!(obj instanceof Serializable) && (obj instanceof ConfigurationSerializable)) {
            obj = Wrapper.newWrapper((ConfigurationSerializable) obj);
        }

        return super.replaceObject(obj);
    }
}
