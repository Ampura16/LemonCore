package org.bukkit.block.data.type;

import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'instrument' 为该音符盒被激活时发出的音色.
 * <br>
 * 'note' 为使用该乐器发音时指定的音调.
 * <p>原文：
 * 'instrument' is the type of sound made when this note block is activated.
 * <br>
 * 'note' is the specified tuned pitch that the instrument will be played in.
 */
public interface NoteBlock extends Powerable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'instrument' 属性的值.
     * <p>原文：
     * Gets the value of the 'instrument' property.
     *
     * @return 属性 'instrument' 的值
     * <p>原文：the 'instrument' value
     */
    @NotNull
    Instrument getInstrument();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'instrument' 属性的值.
     * <p>原文：
     * Sets the value of the 'instrument' property.
     *
     * @param instrument 新的 'instrument' 属性值
     * <p>原文：the new 'instrument' value
     */
    void setInstrument(@NotNull Instrument instrument);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'note' 属性的值.
     * <p>原文：
     * Gets the value of the 'note' property.
     *
     * @return 属性 'note' 的值
     * <p>原文：the 'note' value
     */
    @NotNull
    Note getNote();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'note' 属性的值.
     * <p>原文：
     * Sets the value of the 'note' property.
     *
     * @param note 新的 'note' 属性值
     * <p>原文：the new 'note' value
     */
    void setNote(@NotNull Note note);
}
