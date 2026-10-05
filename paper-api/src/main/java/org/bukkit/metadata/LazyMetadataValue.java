package org.bukkit.metadata;

import com.google.common.base.Preconditions;
import java.lang.ref.SoftReference;
import java.util.concurrent.Callable;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * LazyMetadataValue类实现了一种在另一个插件请求之前不会计算的元数据类型.
 * <p>
 * 通过使元数据值变为惰性, 提供插件在绝对必要之前不会进行计算.
 * 此外, LazyMetadataValue对象在内部缓存其值, 除非被{@link CacheStrategy}覆盖或在个体或插件级别被无效化.
 * 一旦被无效化, LazyMetadataValue将在被请求时重新计算其值.
 * <p>原文：
 * The LazyMetadataValue class implements a type of metadata that is not
 * computed until another plugin asks for it.
 * <p>
 * By making metadata values lazy, no computation is done by the providing
 * plugin until absolutely necessary (if ever). Additionally,
 * LazyMetadataValue objects cache their values internally unless overridden
 * by a {@link CacheStrategy} or invalidated at the individual or plugin
 * level. Once invalidated, the LazyMetadataValue will recompute its value
 * when asked.
 *
 * @deprecated This system is extremely misleading and does not cleanup values for metadatable entities that have been
 * removed. It is recommended that when wanting persistent metadata, you use {@link org.bukkit.persistence.PersistentDataContainer}.
 * <p>
 * If you want temporary values on an entity, use the entity lifecycle events and a {@link java.util.Map} of your own. (See {@link com.destroystokyo.paper.event.entity.EntityAddToWorldEvent} and {@link com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent})
 */
@Deprecated
public class LazyMetadataValue extends MetadataValueAdapter {
    private Callable<Object> lazyValue;
    private CacheStrategy cacheStrategy;
    private SoftReference<Object> internalValue;
    private static final Object ACTUALLY_NULL = new Object();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用默认的CACHE_AFTER_FIRST_EVAL缓存策略初始化LazyMetadataValue对象.
     * <p>原文：
     * Initialized a LazyMetadataValue object with the default
     * CACHE_AFTER_FIRST_EVAL cache strategy.
     *
     * @param owningPlugin 创建此元数据值的{@link Plugin}.
     * <p>原文：the {@link Plugin} that created this metadata
     *     value.
     * @param lazyValue 分配给此元数据值的惰性值.
     * <p>原文：the lazy value assigned to this metadata value.
     */
    public LazyMetadataValue(@NotNull Plugin owningPlugin, @NotNull Callable<Object> lazyValue) {
        this(owningPlugin, CacheStrategy.CACHE_AFTER_FIRST_EVAL, lazyValue);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用特定缓存策略初始化LazyMetadataValue对象.
     * <p>原文：
     * Initializes a LazyMetadataValue object with a specific cache strategy.
     *
     * @param owningPlugin 创建此元数据值的{@link Plugin}.
     * <p>原文：the {@link Plugin} that created this metadata
     *     value.
     * @param cacheStrategy 确定此元数据值的缓存规则.
     * <p>原文：determines the rules for caching this metadata
     *     value.
     * @param lazyValue 分配给此元数据值的惰性值.
     * <p>原文：the lazy value assigned to this metadata value.
     */
    public LazyMetadataValue(@NotNull Plugin owningPlugin, @NotNull CacheStrategy cacheStrategy, @NotNull Callable<Object> lazyValue) {
        super(owningPlugin);
        Preconditions.checkArgument(cacheStrategy != null, "cacheStrategy cannot be null");
        Preconditions.checkArgument(lazyValue != null, "lazyValue cannot be null");
        this.internalValue = new SoftReference<Object>(null);
        this.lazyValue = lazyValue;
        this.cacheStrategy = cacheStrategy;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 受保护的特殊构造函数, 由FixedMetadataValue用于绕过标准设置.
     * <p>原文：
     * Protected special constructor used by FixedMetadataValue to bypass
     * standard setup.
     *
     * @param owningPlugin 拥有插件
     * <p>原文：the owning plugin
     */
    protected LazyMetadataValue(@NotNull Plugin owningPlugin) {
        super(owningPlugin);
    }

    @Override
    @Nullable
    public Object value() {
        eval();
        Object value = internalValue.get();
        if (value == ACTUALLY_NULL) {
            return null;
        }
        return value;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 惰性求值此元数据项的值.
     * <p>原文：
     * Lazily evaluates the value of this metadata item.
     *
     * @throws MetadataEvaluationException 如果计算元数据值失败.
     * <p>原文：if computing the metadata value
     *     fails.
     */
    private synchronized void eval() throws MetadataEvaluationException {
        if (cacheStrategy == CacheStrategy.NEVER_CACHE || internalValue.get() == null) {
            try {
                Object value = lazyValue.call();
                if (value == null) {
                    value = ACTUALLY_NULL;
                }
                internalValue = new SoftReference<Object>(value);
            } catch (Exception e) {
                throw new MetadataEvaluationException(e);
            }
        }
    }

    @Override
    public synchronized void invalidate() {
        if (cacheStrategy != CacheStrategy.CACHE_ETERNALLY) {
            internalValue.clear();
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 描述元数据的可能缓存策略.
     * <p>原文：
     * Describes possible caching strategies for metadata.
     */
    public enum CacheStrategy {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 元数据值被求值后, 在手动无效化之前不会重新求值.
         * <p>原文：
         * Once the metadata value has been evaluated, do not re-evaluate the
         * value until it is manually invalidated.
         */
        CACHE_AFTER_FIRST_EVAL,

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 每次请求时重新求值元数据项.
         * <p>原文：
         * Re-evaluate the metadata item every time it is requested
         */
        NEVER_CACHE,

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 元数据值被求值后, 即使手动无效化也不会重新求值.
         * <p>原文：
         * Once the metadata value has been evaluated, do not re-evaluate the
         * value in spite of manual invalidation.
         */
        CACHE_ETERNALLY
    }
}
