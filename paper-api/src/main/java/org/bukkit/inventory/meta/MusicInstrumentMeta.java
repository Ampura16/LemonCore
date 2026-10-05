package org.bukkit.inventory.meta;

import org.bukkit.MusicInstrument;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表(山羊角)乐器的物品元数据. (译注)
 */
public interface MusicInstrumentMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置山羊角的乐器类型.
     * <p>原文：
     * Sets the goat horn's instrument.
     *
     * @param instrument 要设置的乐器
     * <p>原文：the instrument to set
     */
    void setInstrument(@Nullable MusicInstrument instrument);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取山羊角的乐器类型.
     * <p>原文：
     * Gets the instrument of the goat horn.
     *
     * @return 山羊角的乐器类型
     * <p>原文：The instrument of the goat horn
     */
    @Nullable
    MusicInstrument getInstrument();

    @Override
    @NotNull
    MusicInstrumentMeta clone();
}
