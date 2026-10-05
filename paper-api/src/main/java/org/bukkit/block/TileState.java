package org.bukkit.block;

import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表持有指定位置的 tile entity 的方块状态.
 *
 * 接口本身只是一个不提供任何数据的类型标记.
 *
 * 有关 tile entities 的数据由各自 tile entity 类型对应的接口提供.
 *
 * 在修改由 TileState 提供的数据后, 别忘了使用{@link #update()}来存储修改的数据.
 * <p>原文：
 * Represents a block state that also hosts a block entity at the given location.
 * <br>
 * This interface alone is merely a marker that does not provide any data.
 * <p>
 * Data about the block entities is provided by the respective interface for each
 * block entity type.
 * <p>
 * After modifying the data provided by a TileState, {@link #update()} needs to
 * be called to store the data.
 */
public interface TileState extends BlockState, PersistentDataHolder {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回一个可在此对象上存储标签的自定义标签容器.
     *
     * 注意:存储于此容器的标签全部存储于它们自己自定义的命名空间,
     * 因此使用本{@link PersistentDataHolder}修改默认标签是不可能的.
     * <p>
     * 本 {@link PersistentDataHolder} 对象只与由 {@link BlockState} 存储的快照实例相关联
     * (另注:实际的 tile entity 上数据更新时也不会反应到此容器中, 也就是说, 本方法获取到的容器也是一种快照).
     * 当更新存储于 {@link PersistentDataHolder} 中的内容时,
     * 更新的内容需要调用{@link #update()}方法才会应用于实际的 tile entity.
     * <p>原文：
     * Returns a custom tag container capable of storing tags on the object.
     * <p>
     * Note that the tags stored on this container are all stored under their
     * own custom namespace therefore modifying default tags using this
     * {@link PersistentDataHolder} is impossible.
     * <p>
     * This {@link PersistentDataHolder} is only linked to the snapshot instance
     * stored by the {@link BlockState}.
     * <p>
     * When storing changes on the {@link PersistentDataHolder}, the updated
     * content will only be applied to the actual block entity after one of the
     * {@link #update()} methods is called.
     *
     * @return 自定义标签容器
     * <p>原文：the custom tag container
     */
    @NotNull
    @Override
    PersistentDataContainer getPersistentDataContainer();

    // Paper start
    /**
     * Checks if this TileState is a snapshot or a live
     * representation of the underlying block entity.
     * <p>
     * NOTE: You may still have to call {@link BlockState#update()} on
     * live representations to update any visuals on the block.
     *
     * @return true if this is a snapshot
     * @see Block#getState(boolean)
     */
    boolean isSnapshot();
    // Paper end
}
